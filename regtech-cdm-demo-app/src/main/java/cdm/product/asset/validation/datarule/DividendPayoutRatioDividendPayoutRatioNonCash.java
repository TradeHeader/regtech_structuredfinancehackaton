package cdm.product.asset.validation.datarule;

import cdm.product.asset.DividendPayoutRatio;
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
@RosettaDataRule("DividendPayoutRatioDividendPayoutRatioNonCash")
@ImplementedBy(DividendPayoutRatioDividendPayoutRatioNonCash.Default.class)
public interface DividendPayoutRatioDividendPayoutRatioNonCash extends Validator<DividendPayoutRatio> {
	
	String NAME = "DividendPayoutRatioDividendPayoutRatioNonCash";
	String DEFINITION = "if nonCashRatio exists then nonCashRatio >= 0 and totalRatio <= 1";
	
	ValidationResult<DividendPayoutRatio> validate(RosettaPath path, DividendPayoutRatio dividendPayoutRatio);
	
	class Default implements DividendPayoutRatioDividendPayoutRatioNonCash {
	
		@Override
		public ValidationResult<DividendPayoutRatio> validate(RosettaPath path, DividendPayoutRatio dividendPayoutRatio) {
			ComparisonResult result = executeDataRule(dividendPayoutRatio);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendPayoutRatio", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DividendPayoutRatio", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DividendPayoutRatio dividendPayoutRatio) {
			try {
				if (exists(MapperS.of(dividendPayoutRatio).<BigDecimal>map("getNonCashRatio", _dividendPayoutRatio -> _dividendPayoutRatio.getNonCashRatio())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(dividendPayoutRatio).<BigDecimal>map("getNonCashRatio", _dividendPayoutRatio -> _dividendPayoutRatio.getNonCashRatio()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).and(lessThanEquals(MapperS.of(dividendPayoutRatio).<BigDecimal>map("getTotalRatio", _dividendPayoutRatio -> _dividendPayoutRatio.getTotalRatio()), MapperS.of(BigDecimal.valueOf(1)), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendPayoutRatioDividendPayoutRatioNonCash {
	
		@Override
		public ValidationResult<DividendPayoutRatio> validate(RosettaPath path, DividendPayoutRatio dividendPayoutRatio) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendPayoutRatio", path, DEFINITION);
		}
	}
}
