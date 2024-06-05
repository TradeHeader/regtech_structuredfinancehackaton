package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.PeriodExtendedEnum;
import cdm.base.datetime.RollConventionEnum;
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
@RosettaDataRule("CalculationPeriodFrequencyFpML_ird_58")
@ImplementedBy(CalculationPeriodFrequencyFpMLIrd58.Default.class)
public interface CalculationPeriodFrequencyFpMLIrd58 extends Validator<CalculationPeriodFrequency> {
	
	String NAME = "CalculationPeriodFrequencyFpML_ird_58";
	String DEFINITION = "if period = PeriodExtendedEnum -> W then rollConvention = RollConventionEnum -> NONE or rollConvention = RollConventionEnum -> SFE or rollConvention = RollConventionEnum -> MON or rollConvention = RollConventionEnum -> TUE or rollConvention = RollConventionEnum -> WED or rollConvention = RollConventionEnum -> THU or rollConvention = RollConventionEnum -> FRI or rollConvention = RollConventionEnum -> SAT or rollConvention = RollConventionEnum -> SUN";
	
	ValidationResult<CalculationPeriodFrequency> validate(RosettaPath path, CalculationPeriodFrequency calculationPeriodFrequency);
	
	class Default implements CalculationPeriodFrequencyFpMLIrd58 {
	
		@Override
		public ValidationResult<CalculationPeriodFrequency> validate(RosettaPath path, CalculationPeriodFrequency calculationPeriodFrequency) {
			ComparisonResult result = executeDataRule(calculationPeriodFrequency);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationPeriodFrequency", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CalculationPeriodFrequency", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CalculationPeriodFrequency calculationPeriodFrequency) {
			try {
				if (areEqual(MapperS.of(calculationPeriodFrequency).<PeriodExtendedEnum>map("getPeriod", frequency -> frequency.getPeriod()), MapperS.of(PeriodExtendedEnum.W), CardinalityOperator.All).getOrDefault(false)) {
					return areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.NONE), CardinalityOperator.All).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.SFE), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.MON), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.TUE), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.WED), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.THU), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.FRI), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.SAT), CardinalityOperator.All)).or(areEqual(MapperS.of(calculationPeriodFrequency).<RollConventionEnum>map("getRollConvention", _calculationPeriodFrequency -> _calculationPeriodFrequency.getRollConvention()), MapperS.of(RollConventionEnum.SUN), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CalculationPeriodFrequencyFpMLIrd58 {
	
		@Override
		public ValidationResult<CalculationPeriodFrequency> validate(RosettaPath path, CalculationPeriodFrequency calculationPeriodFrequency) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationPeriodFrequency", path, DEFINITION);
		}
	}
}
