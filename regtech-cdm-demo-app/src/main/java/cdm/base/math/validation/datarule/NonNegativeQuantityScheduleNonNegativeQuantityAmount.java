package cdm.base.math.validation.datarule;

import cdm.base.math.DatedValue;
import cdm.base.math.NonNegativeQuantitySchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("NonNegativeQuantityScheduleNonNegativeQuantity_amount")
@ImplementedBy(NonNegativeQuantityScheduleNonNegativeQuantityAmount.Default.class)
public interface NonNegativeQuantityScheduleNonNegativeQuantityAmount extends Validator<NonNegativeQuantitySchedule> {
	
	String NAME = "NonNegativeQuantityScheduleNonNegativeQuantity_amount";
	String DEFINITION = "if value exists then value >= 0.0 and if datedValue exists then datedValue -> value all >= 0.0";
	
	ValidationResult<NonNegativeQuantitySchedule> validate(RosettaPath path, NonNegativeQuantitySchedule nonNegativeQuantitySchedule);
	
	class Default implements NonNegativeQuantityScheduleNonNegativeQuantityAmount {
	
		@Override
		public ValidationResult<NonNegativeQuantitySchedule> validate(RosettaPath path, NonNegativeQuantitySchedule nonNegativeQuantitySchedule) {
			ComparisonResult result = executeDataRule(nonNegativeQuantitySchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeQuantitySchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NonNegativeQuantitySchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NonNegativeQuantitySchedule nonNegativeQuantitySchedule) {
			try {
				if (exists(MapperS.of(nonNegativeQuantitySchedule).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (exists(MapperS.of(nonNegativeQuantitySchedule).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue())).getOrDefault(false)) {
						ifThenElseResult = greaterThanEquals(MapperS.of(nonNegativeQuantitySchedule).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue()).<BigDecimal>map("getValue", datedValue -> datedValue.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return greaterThanEquals(MapperS.of(nonNegativeQuantitySchedule).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NonNegativeQuantityScheduleNonNegativeQuantityAmount {
	
		@Override
		public ValidationResult<NonNegativeQuantitySchedule> validate(RosettaPath path, NonNegativeQuantitySchedule nonNegativeQuantitySchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeQuantitySchedule", path, DEFINITION);
		}
	}
}
