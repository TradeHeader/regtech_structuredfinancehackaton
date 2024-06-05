package cdm.event.common.functions;

import cdm.base.math.Quantity;
import cdm.base.math.UnitType;
import cdm.observable.asset.Money;
import cdm.observable.asset.Money.MoneyBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ToMoney.ToMoneyDefault.class)
public abstract class ToMoney implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param quantity 
	* @return money 
	*/
	public Money evaluate(Quantity quantity) {
		Money.MoneyBuilder moneyBuilder = doEvaluate(quantity);
		
		final Money money;
		if (moneyBuilder == null) {
			money = null;
		} else {
			money = moneyBuilder.build();
			objectValidator.validate(Money.class, money);
		}
		
		return money;
	}

	protected abstract Money.MoneyBuilder doEvaluate(Quantity quantity);

	public static class ToMoneyDefault extends ToMoney {
		@Override
		protected Money.MoneyBuilder doEvaluate(Quantity quantity) {
			Money.MoneyBuilder money = Money.builder();
			return assignOutput(money, quantity);
		}
		
		protected Money.MoneyBuilder assignOutput(Money.MoneyBuilder money, Quantity quantity) {
			money
				.setValue(MapperS.of(quantity).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get());
			
			money
				.getOrCreateUnit()
				.setCurrencyValue(MapperS.of(quantity).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			return Optional.ofNullable(money)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
