package cdm.product.asset.validation.datarule;

import cdm.observable.asset.Price;
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
@RosettaDataRule("VarianceReturnTermsNonNegativeStrikePrice")
@ImplementedBy(VarianceReturnTermsNonNegativeStrikePrice.Default.class)
public interface VarianceReturnTermsNonNegativeStrikePrice extends Validator<VarianceReturnTerms> {
	
	String NAME = "VarianceReturnTermsNonNegativeStrikePrice";
	String DEFINITION = "if volatilityStrikePrice -> value exists then volatilityStrikePrice -> value >= 0 and if varianceStrikePrice -> value exists then varianceStrikePrice -> value >= 0";
	
	ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms);
	
	class Default implements VarianceReturnTermsNonNegativeStrikePrice {
	
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
				if (exists(MapperS.of(varianceReturnTerms).<Price>map("getVolatilityStrikePrice", _varianceReturnTerms -> _varianceReturnTerms.getVolatilityStrikePrice()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (exists(MapperS.of(varianceReturnTerms).<Price>map("getVarianceStrikePrice", _varianceReturnTerms -> _varianceReturnTerms.getVarianceStrikePrice()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getOrDefault(false)) {
						ifThenElseResult = greaterThanEquals(MapperS.of(varianceReturnTerms).<Price>map("getVarianceStrikePrice", _varianceReturnTerms -> _varianceReturnTerms.getVarianceStrikePrice()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return greaterThanEquals(MapperS.of(varianceReturnTerms).<Price>map("getVolatilityStrikePrice", _varianceReturnTerms -> _varianceReturnTerms.getVolatilityStrikePrice()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VarianceReturnTermsNonNegativeStrikePrice {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
		}
	}
}
