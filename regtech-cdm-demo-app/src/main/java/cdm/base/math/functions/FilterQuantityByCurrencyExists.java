package cdm.base.math.functions;

import cdm.base.math.QuantitySchedule;
import cdm.base.math.QuantitySchedule.QuantityScheduleBuilder;
import cdm.base.math.UnitType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterQuantityByCurrencyExists.FilterQuantityByCurrencyExistsDefault.class)
public abstract class FilterQuantityByCurrencyExists implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param quantities List of quantities to filter.
	* @return filteredQuantities 
	*/
	public List<? extends QuantitySchedule> evaluate(List<? extends QuantitySchedule> quantities) {
		List<QuantitySchedule.QuantityScheduleBuilder> filteredQuantitiesBuilder = doEvaluate(quantities);
		
		final List<? extends QuantitySchedule> filteredQuantities;
		if (filteredQuantitiesBuilder == null) {
			filteredQuantities = null;
		} else {
			filteredQuantities = filteredQuantitiesBuilder.stream().map(QuantitySchedule::build).collect(Collectors.toList());
			objectValidator.validate(QuantitySchedule.class, filteredQuantities);
		}
		
		return filteredQuantities;
	}

	protected abstract List<QuantitySchedule.QuantityScheduleBuilder> doEvaluate(List<? extends QuantitySchedule> quantities);

	public static class FilterQuantityByCurrencyExistsDefault extends FilterQuantityByCurrencyExists {
		@Override
		protected List<QuantitySchedule.QuantityScheduleBuilder> doEvaluate(List<? extends QuantitySchedule> quantities) {
			if (quantities == null) {
				quantities = Collections.emptyList();
			}
			List<QuantitySchedule.QuantityScheduleBuilder> filteredQuantities = new ArrayList<>();
			return assignOutput(filteredQuantities, quantities);
		}
		
		protected List<QuantitySchedule.QuantityScheduleBuilder> assignOutput(List<QuantitySchedule.QuantityScheduleBuilder> filteredQuantities, List<? extends QuantitySchedule> quantities) {
			filteredQuantities.addAll(toBuilder(MapperC.<QuantitySchedule>of(quantities)
				.filterItemNullSafe(item -> exists(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue())).get()).getMulti()));
			
			return Optional.ofNullable(filteredQuantities)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
