package cdm.product.common.settlement.validation.datarule;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDateRange;
import cdm.product.common.settlement.SettlementDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("SettlementDateBusinessDays")
@ImplementedBy(SettlementDateBusinessDays.Default.class)
public interface SettlementDateBusinessDays extends Validator<SettlementDate> {
	
	String NAME = "SettlementDateBusinessDays";
	String DEFINITION = "if cashSettlementBusinessDays exists then cashSettlementBusinessDays >= 0 else adjustableOrRelativeDate exists or valueDate exists or adjustableDates exists or businessDateRange exists";
	
	ValidationResult<SettlementDate> validate(RosettaPath path, SettlementDate settlementDate);
	
	class Default implements SettlementDateBusinessDays {
	
		@Override
		public ValidationResult<SettlementDate> validate(RosettaPath path, SettlementDate settlementDate) {
			ComparisonResult result = executeDataRule(settlementDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SettlementDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SettlementDate settlementDate) {
			try {
				if (exists(MapperS.of(settlementDate).<Integer>map("getCashSettlementBusinessDays", _settlementDate -> _settlementDate.getCashSettlementBusinessDays())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(settlementDate).<Integer>map("getCashSettlementBusinessDays", _settlementDate -> _settlementDate.getCashSettlementBusinessDays()), MapperS.of(0), CardinalityOperator.All);
				}
				return exists(MapperS.of(settlementDate).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", _settlementDate -> _settlementDate.getAdjustableOrRelativeDate())).or(exists(MapperS.of(settlementDate).<Date>map("getValueDate", _settlementDate -> _settlementDate.getValueDate()))).or(exists(MapperS.of(settlementDate).<AdjustableDates>map("getAdjustableDates", _settlementDate -> _settlementDate.getAdjustableDates()))).or(exists(MapperS.of(settlementDate).<BusinessDateRange>map("getBusinessDateRange", _settlementDate -> _settlementDate.getBusinessDateRange())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SettlementDateBusinessDays {
	
		@Override
		public ValidationResult<SettlementDate> validate(RosettaPath path, SettlementDate settlementDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementDate", path, DEFINITION);
		}
	}
}
