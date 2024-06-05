package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("TaxonomyValueValueExists")
@ImplementedBy(TaxonomyValueValueExists.Default.class)
public interface TaxonomyValueValueExists extends Validator<TaxonomyValue> {
	
	String NAME = "TaxonomyValueValueExists";
	String DEFINITION = "name exists or classification exists";
	
	ValidationResult<TaxonomyValue> validate(RosettaPath path, TaxonomyValue taxonomyValue);
	
	class Default implements TaxonomyValueValueExists {
	
		@Override
		public ValidationResult<TaxonomyValue> validate(RosettaPath path, TaxonomyValue taxonomyValue) {
			ComparisonResult result = executeDataRule(taxonomyValue);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TaxonomyValue", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TaxonomyValue", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TaxonomyValue taxonomyValue) {
			try {
				return exists(MapperS.of(taxonomyValue).<FieldWithMetaString>map("getName", _taxonomyValue -> _taxonomyValue.getName()).<String>map("getValue", _f->_f.getValue())).or(exists(MapperS.of(taxonomyValue).<TaxonomyClassification>mapC("getClassification", _taxonomyValue -> _taxonomyValue.getClassification())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TaxonomyValueValueExists {
	
		@Override
		public ValidationResult<TaxonomyValue> validate(RosettaPath path, TaxonomyValue taxonomyValue) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TaxonomyValue", path, DEFINITION);
		}
	}
}
