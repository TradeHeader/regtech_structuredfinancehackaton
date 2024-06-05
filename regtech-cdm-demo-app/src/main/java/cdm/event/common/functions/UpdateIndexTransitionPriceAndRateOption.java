package cdm.event.common.functions;

import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.PriceQuantity.PriceQuantityBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(UpdateIndexTransitionPriceAndRateOption.UpdateIndexTransitionPriceAndRateOptionDefault.class)
public abstract class UpdateIndexTransitionPriceAndRateOption implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param priceQuantity 
	* @param instruction 
	* @return updatedPriceQuantity 
	*/
	public PriceQuantity evaluate(PriceQuantity priceQuantity, PriceQuantity instruction) {
		PriceQuantity.PriceQuantityBuilder updatedPriceQuantityBuilder = doEvaluate(priceQuantity, instruction);
		
		final PriceQuantity updatedPriceQuantity;
		if (updatedPriceQuantityBuilder == null) {
			updatedPriceQuantity = null;
		} else {
			updatedPriceQuantity = updatedPriceQuantityBuilder.build();
			objectValidator.validate(PriceQuantity.class, updatedPriceQuantity);
		}
		
		return updatedPriceQuantity;
	}

	protected abstract PriceQuantity.PriceQuantityBuilder doEvaluate(PriceQuantity priceQuantity, PriceQuantity instruction);

	public static class UpdateIndexTransitionPriceAndRateOptionDefault extends UpdateIndexTransitionPriceAndRateOption {
		@Override
		protected PriceQuantity.PriceQuantityBuilder doEvaluate(PriceQuantity priceQuantity, PriceQuantity instruction) {
			PriceQuantity.PriceQuantityBuilder updatedPriceQuantity = PriceQuantity.builder();
			return assignOutput(updatedPriceQuantity, priceQuantity, instruction);
		}
		
		protected PriceQuantity.PriceQuantityBuilder assignOutput(PriceQuantity.PriceQuantityBuilder updatedPriceQuantity, PriceQuantity priceQuantity, PriceQuantity instruction) {
			updatedPriceQuantity = toBuilder(priceQuantity);
			
			final BigDecimal ifThenElseResult0;
			if (exists(MapperS.of(instruction)).getOrDefault(false)) {
				ifThenElseResult0 = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(MapperS.of(MapperS.of(priceQuantity).<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).get()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(MapperS.of(instruction).<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).get()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).get();
			} else {
				ifThenElseResult0 = MapperS.of(MapperS.of(priceQuantity).<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).get()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get();
			}
			updatedPriceQuantity
				.getOrCreatePrice(0).getOrCreateValue()
				.setValue(ifThenElseResult0);
			
			final FloatingRateOption ifThenElseResult1;
			if (exists(MapperS.of(instruction)).getOrDefault(false)) {
				ifThenElseResult1 = MapperS.of(instruction).<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).get();
			} else {
				ifThenElseResult1 = MapperS.of(priceQuantity).<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).get();
			}
			updatedPriceQuantity
				.getOrCreateObservable()
				.setRateOptionValue(ifThenElseResult1);
			
			return Optional.ofNullable(updatedPriceQuantity)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
