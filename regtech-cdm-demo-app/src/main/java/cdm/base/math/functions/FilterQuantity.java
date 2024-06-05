package cdm.base.math.functions;

import cdm.base.math.Quantity;
import cdm.base.math.Quantity.QuantityBuilder;
import cdm.base.math.UnitType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterQuantity.FilterQuantityDefault.class)
public abstract class FilterQuantity implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param quantities List of quantities to filter.
	* @param unit Currency unit type.
	* @return filteredQuantities 
	*/
	public List<? extends Quantity> evaluate(List<? extends Quantity> quantities, UnitType unit) {
		List<Quantity.QuantityBuilder> filteredQuantitiesBuilder = doEvaluate(quantities, unit);
		
		final List<? extends Quantity> filteredQuantities;
		if (filteredQuantitiesBuilder == null) {
			filteredQuantities = null;
		} else {
			filteredQuantities = filteredQuantitiesBuilder.stream().map(Quantity::build).collect(Collectors.toList());
			objectValidator.validate(Quantity.class, filteredQuantities);
		}
		
		return filteredQuantities;
	}

	protected abstract List<Quantity.QuantityBuilder> doEvaluate(List<? extends Quantity> quantities, UnitType unit);

	public static class FilterQuantityDefault extends FilterQuantity {
		@Override
		protected List<Quantity.QuantityBuilder> doEvaluate(List<? extends Quantity> quantities, UnitType unit) {
			if (quantities == null) {
				quantities = Collections.emptyList();
			}
			List<Quantity.QuantityBuilder> filteredQuantities = new ArrayList<>();
			return assignOutput(filteredQuantities, quantities, unit);
		}
		
		protected List<Quantity.QuantityBuilder> assignOutput(List<Quantity.QuantityBuilder> filteredQuantities, List<? extends Quantity> quantities, UnitType unit) {
			filteredQuantities.addAll(toBuilder(MapperC.<Quantity>of(quantities)
				.filterItemNullSafe(item -> areEqual(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()), MapperS.of(unit), CardinalityOperator.All).get()).getMulti()));
			
			return Optional.ofNullable(filteredQuantities)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
