package cdm.event.common.functions;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.observable.asset.ValuationDates;
import cdm.product.asset.ReturnTypeEnum;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.PerformancePayout;
import cdm.product.template.PerformancePayout.PerformancePayoutBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(NewSingleNameEquityPerformancePayout.NewSingleNameEquityPerformancePayoutDefault.class)
public abstract class NewSingleNameEquityPerformancePayout implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param security 
	* @param masterConfirmation 
	* @return performancePayout 
	*/
	public PerformancePayout evaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
		// pre-conditions
		conditionValidator.validate(() -> areEqual(MapperS.of(security).<SecurityTypeEnum>map("getSecurityType", _security -> _security.getSecurityType()), MapperS.of(SecurityTypeEnum.EQUITY), CardinalityOperator.All),
			"Security must be equity (single name).");
		
		PerformancePayout.PerformancePayoutBuilder performancePayoutBuilder = doEvaluate(security, masterConfirmation);
		
		final PerformancePayout performancePayout;
		if (performancePayoutBuilder == null) {
			performancePayout = null;
		} else {
			performancePayout = performancePayoutBuilder.build();
			objectValidator.validate(PerformancePayout.class, performancePayout);
		}
		
		return performancePayout;
	}

	protected abstract PerformancePayout.PerformancePayoutBuilder doEvaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation);

	public static class NewSingleNameEquityPerformancePayoutDefault extends NewSingleNameEquityPerformancePayout {
		@Override
		protected PerformancePayout.PerformancePayoutBuilder doEvaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
			PerformancePayout.PerformancePayoutBuilder performancePayout = PerformancePayout.builder();
			return assignOutput(performancePayout, security, masterConfirmation);
		}
		
		protected PerformancePayout.PerformancePayoutBuilder assignOutput(PerformancePayout.PerformancePayoutBuilder performancePayout, Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
			performancePayout
				.getOrCreateReturnTerms()
				.getOrCreatePriceReturnTerms()
				.setReturnType(MapperS.of(masterConfirmation).<ReturnTypeEnum>map("getTypeOfSwapElection", equitySwapMasterConfirmation2018 -> equitySwapMasterConfirmation2018.getTypeOfSwapElection()).get());
			
			performancePayout
				.setValuationDates(MapperS.of(masterConfirmation).<ValuationDates>map("getValuationDates", equitySwapMasterConfirmation2018 -> equitySwapMasterConfirmation2018.getValuationDates()).get());
			
			performancePayout
				.setPaymentDates(MapperS.of(masterConfirmation).<PaymentDates>map("getEquityCashSettlementDates", equitySwapMasterConfirmation2018 -> equitySwapMasterConfirmation2018.getEquityCashSettlementDates()).get());
			
			performancePayout
				.setSettlementTerms(MapperS.of(masterConfirmation).<SettlementTerms>map("getSettlementTerms", equitySwapMasterConfirmation2018 -> equitySwapMasterConfirmation2018.getSettlementTerms()).get());
			
			return Optional.ofNullable(performancePayout)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
