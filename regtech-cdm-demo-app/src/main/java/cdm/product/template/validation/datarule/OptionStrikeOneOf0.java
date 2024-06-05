package cdm.product.template.validation.datarule;

import cdm.product.template.OptionStrike;
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
@RosettaDataRule("OptionStrikeOneOf0")
@ImplementedBy(OptionStrikeOneOf0.Default.class)
public interface OptionStrikeOneOf0 extends Validator<OptionStrike> {
	
	String NAME = "OptionStrikeOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<OptionStrike> validate(RosettaPath path, OptionStrike optionStrike);
	
	class Default implements OptionStrikeOneOf0 {
	
		@Override
		public ValidationResult<OptionStrike> validate(RosettaPath path, OptionStrike optionStrike) {
			ComparisonResult result = executeDataRule(optionStrike);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionStrike", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionStrike", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionStrike optionStrike) {
			try {
				return choice(MapperS.of(optionStrike), Arrays.asList("strikePrice", "strikeReference", "referenceSwapCurve", "averagingStrikeFeature"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionStrikeOneOf0 {
	
		@Override
		public ValidationResult<OptionStrike> validate(RosettaPath path, OptionStrike optionStrike) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionStrike", path, DEFINITION);
		}
	}
}
