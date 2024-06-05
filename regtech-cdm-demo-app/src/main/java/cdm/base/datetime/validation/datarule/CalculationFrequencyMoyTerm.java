package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.CalculationFrequency;
import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodEnum;
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
@RosettaDataRule("CalculationFrequencyMoyTerm")
@ImplementedBy(CalculationFrequencyMoyTerm.Default.class)
public interface CalculationFrequencyMoyTerm extends Validator<CalculationFrequency> {
	
	String NAME = "CalculationFrequencyMoyTerm";
	String DEFINITION = "if monthOfYear exists then (period -> period = PeriodEnum -> Y)";
	
	ValidationResult<CalculationFrequency> validate(RosettaPath path, CalculationFrequency calculationFrequency);
	
	class Default implements CalculationFrequencyMoyTerm {
	
		@Override
		public ValidationResult<CalculationFrequency> validate(RosettaPath path, CalculationFrequency calculationFrequency) {
			ComparisonResult result = executeDataRule(calculationFrequency);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationFrequency", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CalculationFrequency", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CalculationFrequency calculationFrequency) {
			try {
				if (exists(MapperS.of(calculationFrequency).<BigDecimal>map("getMonthOfYear", _calculationFrequency -> _calculationFrequency.getMonthOfYear())).getOrDefault(false)) {
					return areEqual(MapperS.of(calculationFrequency).<Period>map("getPeriod", _calculationFrequency -> _calculationFrequency.getPeriod()).<PeriodEnum>map("getPeriod", period -> period.getPeriod()), MapperS.of(PeriodEnum.Y), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CalculationFrequencyMoyTerm {
	
		@Override
		public ValidationResult<CalculationFrequency> validate(RosettaPath path, CalculationFrequency calculationFrequency) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationFrequency", path, DEFINITION);
		}
	}
}
