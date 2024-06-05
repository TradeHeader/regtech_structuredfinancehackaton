package cdm.event.common.validation.datarule;

import cdm.event.common.SettlementOrigin;
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
@RosettaDataRule("SettlementOriginOneOf0")
@ImplementedBy(SettlementOriginOneOf0.Default.class)
public interface SettlementOriginOneOf0 extends Validator<SettlementOrigin> {
	
	String NAME = "SettlementOriginOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<SettlementOrigin> validate(RosettaPath path, SettlementOrigin settlementOrigin);
	
	class Default implements SettlementOriginOneOf0 {
	
		@Override
		public ValidationResult<SettlementOrigin> validate(RosettaPath path, SettlementOrigin settlementOrigin) {
			ComparisonResult result = executeDataRule(settlementOrigin);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementOrigin", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SettlementOrigin", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SettlementOrigin settlementOrigin) {
			try {
				return choice(MapperS.of(settlementOrigin), Arrays.asList("commodityPayout", "creditDefaultPayout", "forwardPayout", "interestRatePayout", "optionPayout", "assetPayout", "settlementTerms", "performancePayout", "fixedPricePayout"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SettlementOriginOneOf0 {
	
		@Override
		public ValidationResult<SettlementOrigin> validate(RosettaPath path, SettlementOrigin settlementOrigin) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementOrigin", path, DEFINITION);
		}
	}
}
