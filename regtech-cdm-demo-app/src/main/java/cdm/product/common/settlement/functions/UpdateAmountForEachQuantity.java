package cdm.product.common.settlement.functions;

import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.PriceQuantity.PriceQuantityBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(UpdateAmountForEachQuantity.UpdateAmountForEachQuantityDefault.class)
public abstract class UpdateAmountForEachQuantity implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param priceQuantity List of price quantities to update.
	* @param amount The new amount.
	* @return updatedPriceQuantity List of price quantities with all quantity amounts updated.
	*/
	public List<? extends PriceQuantity> evaluate(List<? extends PriceQuantity> priceQuantity, BigDecimal amount) {
		List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantityBuilder = doEvaluate(priceQuantity, amount);
		
		final List<? extends PriceQuantity> updatedPriceQuantity;
		if (updatedPriceQuantityBuilder == null) {
			updatedPriceQuantity = null;
		} else {
			updatedPriceQuantity = updatedPriceQuantityBuilder.stream().map(PriceQuantity::build).collect(Collectors.toList());
			objectValidator.validate(PriceQuantity.class, updatedPriceQuantity);
		}
		
		return updatedPriceQuantity;
	}

	protected abstract List<PriceQuantity.PriceQuantityBuilder> doEvaluate(List<? extends PriceQuantity> priceQuantity, BigDecimal amount);

	public static class UpdateAmountForEachQuantityDefault extends UpdateAmountForEachQuantity {
		@Override
		protected List<PriceQuantity.PriceQuantityBuilder> doEvaluate(List<? extends PriceQuantity> priceQuantity, BigDecimal amount) {
			if (priceQuantity == null) {
				priceQuantity = Collections.emptyList();
			}
			List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantity = new ArrayList<>();
			return assignOutput(updatedPriceQuantity, priceQuantity, amount);
		}
		
		protected List<PriceQuantity.PriceQuantityBuilder> assignOutput(List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantity, List<? extends PriceQuantity> priceQuantity, BigDecimal amount) {
			return Optional.ofNullable(updatedPriceQuantity)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
