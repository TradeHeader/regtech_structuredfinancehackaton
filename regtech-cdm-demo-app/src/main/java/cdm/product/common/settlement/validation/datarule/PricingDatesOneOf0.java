package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.PricingDates;
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
@RosettaDataRule("PricingDatesOneOf0")
@ImplementedBy(PricingDatesOneOf0.Default.class)
public interface PricingDatesOneOf0 extends Validator<PricingDates> {
	
	String NAME = "PricingDatesOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<PricingDates> validate(RosettaPath path, PricingDates pricingDates);
	
	class Default implements PricingDatesOneOf0 {
	
		@Override
		public ValidationResult<PricingDates> validate(RosettaPath path, PricingDates pricingDates) {
			ComparisonResult result = executeDataRule(pricingDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PricingDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PricingDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PricingDates pricingDates) {
			try {
				return choice(MapperS.of(pricingDates), Arrays.asList("specifiedDates", "parametricDates"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PricingDatesOneOf0 {
	
		@Override
		public ValidationResult<PricingDates> validate(RosettaPath path, PricingDates pricingDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PricingDates", path, DEFINITION);
		}
	}
}
