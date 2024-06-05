package cdm.product.template.validation.datarule;

import cdm.product.template.MandatoryEarlyTerminationAdjustedDates;
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
@RosettaDataRule("MandatoryEarlyTerminationAdjustedDatesFpML_ird_44")
@ImplementedBy(MandatoryEarlyTerminationAdjustedDatesFpMLIrd44.Default.class)
public interface MandatoryEarlyTerminationAdjustedDatesFpMLIrd44 extends Validator<MandatoryEarlyTerminationAdjustedDates> {
	
	String NAME = "MandatoryEarlyTerminationAdjustedDatesFpML_ird_44";
	String DEFINITION = "adjustedEarlyTerminationDate <= adjustedCashSettlementValuationDate and adjustedCashSettlementValuationDate <= adjustedCashSettlementPaymentDate";
	
	ValidationResult<MandatoryEarlyTerminationAdjustedDates> validate(RosettaPath path, MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates);
	
	class Default implements MandatoryEarlyTerminationAdjustedDatesFpMLIrd44 {
	
		@Override
		public ValidationResult<MandatoryEarlyTerminationAdjustedDates> validate(RosettaPath path, MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates) {
			ComparisonResult result = executeDataRule(mandatoryEarlyTerminationAdjustedDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MandatoryEarlyTerminationAdjustedDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MandatoryEarlyTerminationAdjustedDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates) {
			try {
				return lessThanEquals(MapperS.of(mandatoryEarlyTerminationAdjustedDates).<Date>map("getAdjustedEarlyTerminationDate", _mandatoryEarlyTerminationAdjustedDates -> _mandatoryEarlyTerminationAdjustedDates.getAdjustedEarlyTerminationDate()), MapperS.of(mandatoryEarlyTerminationAdjustedDates).<Date>map("getAdjustedCashSettlementValuationDate", _mandatoryEarlyTerminationAdjustedDates -> _mandatoryEarlyTerminationAdjustedDates.getAdjustedCashSettlementValuationDate()), CardinalityOperator.All).and(lessThanEquals(MapperS.of(mandatoryEarlyTerminationAdjustedDates).<Date>map("getAdjustedCashSettlementValuationDate", _mandatoryEarlyTerminationAdjustedDates -> _mandatoryEarlyTerminationAdjustedDates.getAdjustedCashSettlementValuationDate()), MapperS.of(mandatoryEarlyTerminationAdjustedDates).<Date>map("getAdjustedCashSettlementPaymentDate", _mandatoryEarlyTerminationAdjustedDates -> _mandatoryEarlyTerminationAdjustedDates.getAdjustedCashSettlementPaymentDate()), CardinalityOperator.All));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MandatoryEarlyTerminationAdjustedDatesFpMLIrd44 {
	
		@Override
		public ValidationResult<MandatoryEarlyTerminationAdjustedDates> validate(RosettaPath path, MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MandatoryEarlyTerminationAdjustedDates", path, DEFINITION);
		}
	}
}
