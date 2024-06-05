package cdm.product.template.validation.datarule;

import cdm.observable.asset.DividendApplicability;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.qualification.functions.Qualify_UnderlierProduct_Equity;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PerformancePayoutEquitySpecificAttributes")
@ImplementedBy(PerformancePayoutEquitySpecificAttributes.Default.class)
public interface PerformancePayoutEquitySpecificAttributes extends Validator<PerformancePayout> {
	
	String NAME = "PerformancePayoutEquitySpecificAttributes";
	String DEFINITION = "if Qualify_UnderlierProduct_Equity(underlier) = False then returnTerms -> varianceReturnTerms -> dividendApplicability is absent and returnTerms -> varianceReturnTerms -> equityUnderlierProvisions is absent and returnTerms -> varianceReturnTerms -> sharePriceDividendAdjustment is absent and returnTerms -> volatilityReturnTerms -> dividendApplicability is absent and returnTerms -> volatilityReturnTerms -> equityUnderlierProvisions is absent and returnTerms -> volatilityReturnTerms -> sharePriceDividendAdjustment is absent and returnTerms -> correlationReturnTerms -> dividendApplicability is absent and returnTerms -> correlationReturnTerms -> equityUnderlierProvisions is absent and returnTerms -> correlationReturnTerms -> sharePriceDividendAdjustment is absent";
	
	ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout);
	
	class Default implements PerformancePayoutEquitySpecificAttributes {
	
		@Inject protected Qualify_UnderlierProduct_Equity qualify_UnderlierProduct_Equity;
		
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
				if (areEqual(MapperS.of(qualify_UnderlierProduct_Equity.evaluate(MapperS.of(performancePayout).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).get())), MapperS.of(false), CardinalityOperator.All).getOrDefault(false)) {
					return notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VarianceReturnTerms>map("getVarianceReturnTerms", returnTerms -> returnTerms.getVarianceReturnTerms()).<DividendApplicability>map("getDividendApplicability", returnTermsBase -> returnTermsBase.getDividendApplicability())).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VarianceReturnTerms>map("getVarianceReturnTerms", returnTerms -> returnTerms.getVarianceReturnTerms()).<EquityUnderlierProvisions>map("getEquityUnderlierProvisions", returnTermsBase -> returnTermsBase.getEquityUnderlierProvisions()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VarianceReturnTerms>map("getVarianceReturnTerms", returnTerms -> returnTerms.getVarianceReturnTerms()).<Boolean>map("getSharePriceDividendAdjustment", returnTermsBase -> returnTermsBase.getSharePriceDividendAdjustment()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VolatilityReturnTerms>map("getVolatilityReturnTerms", returnTerms -> returnTerms.getVolatilityReturnTerms()).<DividendApplicability>map("getDividendApplicability", returnTermsBase -> returnTermsBase.getDividendApplicability()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VolatilityReturnTerms>map("getVolatilityReturnTerms", returnTerms -> returnTerms.getVolatilityReturnTerms()).<EquityUnderlierProvisions>map("getEquityUnderlierProvisions", returnTermsBase -> returnTermsBase.getEquityUnderlierProvisions()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<VolatilityReturnTerms>map("getVolatilityReturnTerms", returnTerms -> returnTerms.getVolatilityReturnTerms()).<Boolean>map("getSharePriceDividendAdjustment", returnTermsBase -> returnTermsBase.getSharePriceDividendAdjustment()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<CorrelationReturnTerms>map("getCorrelationReturnTerms", returnTerms -> returnTerms.getCorrelationReturnTerms()).<DividendApplicability>map("getDividendApplicability", returnTermsBase -> returnTermsBase.getDividendApplicability()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<CorrelationReturnTerms>map("getCorrelationReturnTerms", returnTerms -> returnTerms.getCorrelationReturnTerms()).<EquityUnderlierProvisions>map("getEquityUnderlierProvisions", returnTermsBase -> returnTermsBase.getEquityUnderlierProvisions()))).and(notExists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<CorrelationReturnTerms>map("getCorrelationReturnTerms", returnTerms -> returnTerms.getCorrelationReturnTerms()).<Boolean>map("getSharePriceDividendAdjustment", returnTermsBase -> returnTermsBase.getSharePriceDividendAdjustment())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PerformancePayoutEquitySpecificAttributes {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
		}
	}
}
