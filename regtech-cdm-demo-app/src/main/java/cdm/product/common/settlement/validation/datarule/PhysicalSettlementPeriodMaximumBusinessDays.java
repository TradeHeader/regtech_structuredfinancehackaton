package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.PhysicalSettlementPeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PhysicalSettlementPeriodMaximumBusinessDays")
@ImplementedBy(PhysicalSettlementPeriodMaximumBusinessDays.Default.class)
public interface PhysicalSettlementPeriodMaximumBusinessDays extends Validator<PhysicalSettlementPeriod> {
	
	String NAME = "PhysicalSettlementPeriodMaximumBusinessDays";
	String DEFINITION = "if maximumBusinessDays exists then maximumBusinessDays >= 0";
	
	ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod physicalSettlementPeriod);
	
	class Default implements PhysicalSettlementPeriodMaximumBusinessDays {
	
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
				if (exists(MapperS.of(physicalSettlementPeriod).<Integer>map("getMaximumBusinessDays", _physicalSettlementPeriod -> _physicalSettlementPeriod.getMaximumBusinessDays())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(physicalSettlementPeriod).<Integer>map("getMaximumBusinessDays", _physicalSettlementPeriod -> _physicalSettlementPeriod.getMaximumBusinessDays()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PhysicalSettlementPeriodMaximumBusinessDays {
	
		@Override
		public ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod physicalSettlementPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PhysicalSettlementPeriod", path, DEFINITION);
		}
	}
}
