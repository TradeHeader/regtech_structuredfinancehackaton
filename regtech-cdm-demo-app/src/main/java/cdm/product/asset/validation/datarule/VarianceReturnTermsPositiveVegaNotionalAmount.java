package cdm.product.asset.validation.datarule;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.product.asset.VarianceReturnTerms;
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
@RosettaDataRule("VarianceReturnTermsPositive_VegaNotionalAmount")
@ImplementedBy(VarianceReturnTermsPositiveVegaNotionalAmount.Default.class)
public interface VarianceReturnTermsPositiveVegaNotionalAmount extends Validator<VarianceReturnTerms> {
	
	String NAME = "VarianceReturnTermsPositive_VegaNotionalAmount";
	String DEFINITION = "if vegaNotionalAmount -> value exists then vegaNotionalAmount -> value > 0";
	
	ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms);
	
	class Default implements VarianceReturnTermsPositiveVegaNotionalAmount {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			ComparisonResult result = executeDataRule(varianceReturnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VarianceReturnTerms varianceReturnTerms) {
			try {
				if (exists(MapperS.of(varianceReturnTerms).<NonNegativeQuantitySchedule>map("getVegaNotionalAmount", _varianceReturnTerms -> _varianceReturnTerms.getVegaNotionalAmount()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getOrDefault(false)) {
					return greaterThan(MapperS.of(varianceReturnTerms).<NonNegativeQuantitySchedule>map("getVegaNotionalAmount", _varianceReturnTerms -> _varianceReturnTerms.getVegaNotionalAmount()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VarianceReturnTermsPositiveVegaNotionalAmount {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
		}
	}
}
