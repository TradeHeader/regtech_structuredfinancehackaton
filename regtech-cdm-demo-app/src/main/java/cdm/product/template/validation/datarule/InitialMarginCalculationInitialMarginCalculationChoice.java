package cdm.product.template.validation.datarule;

import cdm.product.template.InitialMarginCalculation;
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
@RosettaDataRule("InitialMarginCalculationInitialMarginCalculationChoice")
@ImplementedBy(InitialMarginCalculationInitialMarginCalculationChoice.Default.class)
public interface InitialMarginCalculationInitialMarginCalculationChoice extends Validator<InitialMarginCalculation> {
	
	String NAME = "InitialMarginCalculationInitialMarginCalculationChoice";
	String DEFINITION = "required choice marginRatio, haircut";
	
	ValidationResult<InitialMarginCalculation> validate(RosettaPath path, InitialMarginCalculation initialMarginCalculation);
	
	class Default implements InitialMarginCalculationInitialMarginCalculationChoice {
	
		@Override
		public ValidationResult<InitialMarginCalculation> validate(RosettaPath path, InitialMarginCalculation initialMarginCalculation) {
			ComparisonResult result = executeDataRule(initialMarginCalculation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialMarginCalculation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InitialMarginCalculation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InitialMarginCalculation initialMarginCalculation) {
			try {
				return choice(MapperS.of(initialMarginCalculation), Arrays.asList("marginRatio", "haircut"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InitialMarginCalculationInitialMarginCalculationChoice {
	
		@Override
		public ValidationResult<InitialMarginCalculation> validate(RosettaPath path, InitialMarginCalculation initialMarginCalculation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialMarginCalculation", path, DEFINITION);
		}
	}
}
