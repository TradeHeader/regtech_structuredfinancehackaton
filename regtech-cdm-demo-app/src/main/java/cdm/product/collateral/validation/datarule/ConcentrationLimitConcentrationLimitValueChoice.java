package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.ConcentrationLimit;
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
@RosettaDataRule("ConcentrationLimitConcentrationLimitValueChoice")
@ImplementedBy(ConcentrationLimitConcentrationLimitValueChoice.Default.class)
public interface ConcentrationLimitConcentrationLimitValueChoice extends Validator<ConcentrationLimit> {
	
	String NAME = "ConcentrationLimitConcentrationLimitValueChoice";
	String DEFINITION = "required choice valueLimit, percentageLimit";
	
	ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit);
	
	class Default implements ConcentrationLimitConcentrationLimitValueChoice {
	
		@Override
		public ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit) {
			ComparisonResult result = executeDataRule(concentrationLimit);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ConcentrationLimit concentrationLimit) {
			try {
				return choice(MapperS.of(concentrationLimit), Arrays.asList("valueLimit", "percentageLimit"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ConcentrationLimitConcentrationLimitValueChoice {
	
		@Override
		public ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION);
		}
	}
}
