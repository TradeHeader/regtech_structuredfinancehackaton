package cdm.product.asset.validation.datarule;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.GeneralTerms;
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
@RosettaDataRule("GeneralTermsBasketReferenceInformationNameOrId")
@ImplementedBy(GeneralTermsBasketReferenceInformationNameOrId.Default.class)
public interface GeneralTermsBasketReferenceInformationNameOrId extends Validator<GeneralTerms> {
	
	String NAME = "GeneralTermsBasketReferenceInformationNameOrId";
	String DEFINITION = "if basketReferenceInformation exists then basketReferenceInformation -> basketName exists or basketReferenceInformation -> basketId exists";
	
	ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms);
	
	class Default implements GeneralTermsBasketReferenceInformationNameOrId {
	
		@Override
		public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms) {
			ComparisonResult result = executeDataRule(generalTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(GeneralTerms generalTerms) {
			try {
				if (exists(MapperS.of(generalTerms).<BasketReferenceInformation>map("getBasketReferenceInformation", _generalTerms -> _generalTerms.getBasketReferenceInformation())).getOrDefault(false)) {
					return exists(MapperS.of(generalTerms).<BasketReferenceInformation>map("getBasketReferenceInformation", _generalTerms -> _generalTerms.getBasketReferenceInformation()).<FieldWithMetaString>map("getBasketName", basketReferenceInformation -> basketReferenceInformation.getBasketName()).<String>map("getValue", _f->_f.getValue())).or(exists(MapperS.of(generalTerms).<BasketReferenceInformation>map("getBasketReferenceInformation", _generalTerms -> _generalTerms.getBasketReferenceInformation()).<FieldWithMetaString>mapC("getBasketId", basketReferenceInformation -> basketReferenceInformation.getBasketId()).<String>map("getValue", _f->_f.getValue())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements GeneralTermsBasketReferenceInformationNameOrId {
	
		@Override
		public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION);
		}
	}
}
