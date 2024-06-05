package cdm.product.asset.validation.datarule;

import cdm.product.asset.BasketReferenceInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("BasketReferenceInformationBasketReferenceInformationChoice")
@ImplementedBy(BasketReferenceInformationBasketReferenceInformationChoice.Default.class)
public interface BasketReferenceInformationBasketReferenceInformationChoice extends Validator<BasketReferenceInformation> {
	
	String NAME = "BasketReferenceInformationBasketReferenceInformationChoice";
	String DEFINITION = "required choice nthToDefault, tranche";
	
	ValidationResult<BasketReferenceInformation> validate(RosettaPath path, BasketReferenceInformation basketReferenceInformation);
	
	class Default implements BasketReferenceInformationBasketReferenceInformationChoice {
	
		@Override
		public ValidationResult<BasketReferenceInformation> validate(RosettaPath path, BasketReferenceInformation basketReferenceInformation) {
			ComparisonResult result = executeDataRule(basketReferenceInformation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BasketReferenceInformation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "BasketReferenceInformation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(BasketReferenceInformation basketReferenceInformation) {
			try {
				return choice(MapperS.of(basketReferenceInformation), Arrays.asList("nthToDefault", "tranche"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements BasketReferenceInformationBasketReferenceInformationChoice {
	
		@Override
		public ValidationResult<BasketReferenceInformation> validate(RosettaPath path, BasketReferenceInformation basketReferenceInformation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BasketReferenceInformation", path, DEFINITION);
		}
	}
}
