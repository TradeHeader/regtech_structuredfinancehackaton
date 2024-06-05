package cdm.event.common.functions;

import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.InterestRatePayout.InterestRatePayoutBuilder;
import cdm.product.common.schedule.PaymentDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(NewFloatingPayout.NewFloatingPayoutDefault.class)
public abstract class NewFloatingPayout implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param masterConfirmation 
	* @return interestRatePayout 
	*/
	public InterestRatePayout evaluate(EquitySwapMasterConfirmation2018 masterConfirmation) {
		InterestRatePayout.InterestRatePayoutBuilder interestRatePayoutBuilder = doEvaluate(masterConfirmation);
		
		final InterestRatePayout interestRatePayout;
		if (interestRatePayoutBuilder == null) {
			interestRatePayout = null;
		} else {
			interestRatePayout = interestRatePayoutBuilder.build();
			objectValidator.validate(InterestRatePayout.class, interestRatePayout);
		}
		
		// post-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(masterConfirmation)).getOrDefault(false)) {
				return areEqual(MapperS.of(interestRatePayout).<PaymentDates>map("getPaymentDates", _interestRatePayout -> _interestRatePayout.getPaymentDates()), MapperS.of(masterConfirmation).<PaymentDates>map("getEquityCashSettlementDates", equitySwapMasterConfirmation2018 -> equitySwapMasterConfirmation2018.getEquityCashSettlementDates()), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"Interest rate payout must inherit terms from the Master Confirmation Agreement when it exists.");
		
		return interestRatePayout;
	}

	protected abstract InterestRatePayout.InterestRatePayoutBuilder doEvaluate(EquitySwapMasterConfirmation2018 masterConfirmation);

	public static class NewFloatingPayoutDefault extends NewFloatingPayout {
		@Override
		protected InterestRatePayout.InterestRatePayoutBuilder doEvaluate(EquitySwapMasterConfirmation2018 masterConfirmation) {
			InterestRatePayout.InterestRatePayoutBuilder interestRatePayout = InterestRatePayout.builder();
			return assignOutput(interestRatePayout, masterConfirmation);
		}
		
		protected InterestRatePayout.InterestRatePayoutBuilder assignOutput(InterestRatePayout.InterestRatePayoutBuilder interestRatePayout, EquitySwapMasterConfirmation2018 masterConfirmation) {
			return Optional.ofNullable(interestRatePayout)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
