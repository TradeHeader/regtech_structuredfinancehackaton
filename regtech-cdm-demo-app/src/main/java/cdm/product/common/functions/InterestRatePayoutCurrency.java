package cdm.product.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(InterestRatePayoutCurrency.InterestRatePayoutCurrencyDefault.class)
public abstract class InterestRatePayoutCurrency implements RosettaFunction {

	/**
	* @param interestRatePayouts List of interest rate payouts.
	* @return currency List of currencies collected from the payouts.
	*/
	public List<String> evaluate(List<? extends InterestRatePayout> interestRatePayouts) {
		List<String> currency = doEvaluate(interestRatePayouts);
		
		return currency;
	}

	protected abstract List<String> doEvaluate(List<? extends InterestRatePayout> interestRatePayouts);

	public static class InterestRatePayoutCurrencyDefault extends InterestRatePayoutCurrency {
		@Override
		protected List<String> doEvaluate(List<? extends InterestRatePayout> interestRatePayouts) {
			if (interestRatePayouts == null) {
				interestRatePayouts = Collections.emptyList();
			}
			List<String> currency = new ArrayList<>();
			return assignOutput(currency, interestRatePayouts);
		}
		
		protected List<String> assignOutput(List<String> currency, List<? extends InterestRatePayout> interestRatePayouts) {
			currency.addAll(MapperC.<InterestRatePayout>of(interestRatePayouts).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).getMulti());
			
			currency.addAll(MapperC.<InterestRatePayout>of(interestRatePayouts).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<QuantityMultiplier>map("getQuantityMultiplier", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantityMultiplier()).<FxLinkedNotionalSchedule>map("getFxLinkedNotionalSchedule", quantityMultiplier -> quantityMultiplier.getFxLinkedNotionalSchedule()).<FieldWithMetaString>map("getVaryingNotionalCurrency", fxLinkedNotionalSchedule -> fxLinkedNotionalSchedule.getVaryingNotionalCurrency()).<String>map("getValue", _f->_f.getValue()).getMulti());
			
			return currency;
		}
	}
}
