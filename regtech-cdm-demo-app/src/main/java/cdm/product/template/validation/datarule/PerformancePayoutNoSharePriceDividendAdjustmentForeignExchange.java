package cdm.product.template.validation.datarule;

import cdm.product.asset.ForeignExchange;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange")
@ImplementedBy(PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange.Default.class)
public interface PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange extends Validator<PerformancePayout> {
	
	String NAME = "PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange";
	String DEFINITION = "if underlier -> foreignExchange exists then returnTerms -> varianceReturnTerms -> sharePriceDividendAdjustment is absent and returnTerms -> volatilityReturnTerms -> sharePriceDividendAdjustment is absent";
	
	ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout);
	
	class Default implements PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			ComparisonResult result = executeDataRule(performancePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PerformancePayout performancePayout) {
			try {
				if (exists(MapperS.of(performancePayout).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())).getOrDefault(false)) {
					return notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VarianceReturnTerms>map("getVarianceReturnTerms", returnTerms -> returnTerms.getVarianceReturnTerms()).<Boolean>map("getSharePriceDividendAdjustment", returnTermsBase -> returnTermsBase.getSharePriceDividendAdjustment())).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VolatilityReturnTerms>map("getVolatilityReturnTerms", returnTerms -> returnTerms.getVolatilityReturnTerms()).<Boolean>map("getSharePriceDividendAdjustment", returnTermsBase -> returnTermsBase.getSharePriceDividendAdjustment())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
		}
	}
}
