package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import cdm.base.staticdata.asset.common.functions.DifferentOrdinalsCondition;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("TaxonomyDifferentOrdinals")
@ImplementedBy(TaxonomyDifferentOrdinals.Default.class)
public interface TaxonomyDifferentOrdinals extends Validator<Taxonomy> {
	
	String NAME = "TaxonomyDifferentOrdinals";
	String DEFINITION = "if value -> classification -> ordinal exists then DifferentOrdinalsCondition";
	
	ValidationResult<Taxonomy> validate(RosettaPath path, Taxonomy taxonomy);
	
	class Default implements TaxonomyDifferentOrdinals {
	
		@Inject protected DifferentOrdinalsCondition differentOrdinalsCondition;
		
		@Override
		public ValidationResult<Taxonomy> validate(RosettaPath path, Taxonomy taxonomy) {
			ComparisonResult result = executeDataRule(taxonomy);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Taxonomy", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Taxonomy", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Taxonomy taxonomy) {
			try {
				if (exists(MapperS.of(taxonomy).<TaxonomyValue>map("getValue", _taxonomy -> _taxonomy.getValue()).<TaxonomyClassification>mapC("getClassification", taxonomyValue -> taxonomyValue.getClassification()).<Integer>map("getOrdinal", taxonomyClassification -> taxonomyClassification.getOrdinal())).getOrDefault(false)) {
					return ComparisonResult.of(MapperS.of(differentOrdinalsCondition.evaluate(taxonomy)));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TaxonomyDifferentOrdinals {
	
		@Override
		public ValidationResult<Taxonomy> validate(RosettaPath path, Taxonomy taxonomy) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Taxonomy", path, DEFINITION);
		}
	}
}
