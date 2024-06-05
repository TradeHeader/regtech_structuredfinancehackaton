package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.PhysicalSettlementPeriod;
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
@RosettaDataRule("PhysicalSettlementPeriodOneOf0")
@ImplementedBy(PhysicalSettlementPeriodOneOf0.Default.class)
public interface PhysicalSettlementPeriodOneOf0 extends Validator<PhysicalSettlementPeriod> {
	
	String NAME = "PhysicalSettlementPeriodOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod physicalSettlementPeriod);
	
	class Default implements PhysicalSettlementPeriodOneOf0 {
	
		@Override
		public ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod physicalSettlementPeriod) {
			ComparisonResult result = executeDataRule(physicalSettlementPeriod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PhysicalSettlementPeriod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PhysicalSettlementPeriod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PhysicalSettlementPeriod physicalSettlementPeriod) {
			try {
				return choice(MapperS.of(physicalSettlementPeriod), Arrays.asList("businessDaysNotSpecified", "businessDays", "maximumBusinessDays"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PhysicalSettlementPeriodOneOf0 {
	
		@Override
		public ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod physicalSettlementPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PhysicalSettlementPeriod", path, DEFINITION);
		}
	}
}
