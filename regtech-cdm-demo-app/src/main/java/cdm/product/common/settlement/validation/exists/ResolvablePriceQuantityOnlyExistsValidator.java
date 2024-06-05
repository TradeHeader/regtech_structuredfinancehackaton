package cdm.product.common.settlement.validation.exists;

import cdm.base.math.Quantity;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.asset.FutureValueAmount;
import cdm.product.common.settlement.CumulationFeature;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.metafields.ReferenceWithMetaResolvablePriceQuantity;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ResolvablePriceQuantityOnlyExistsValidator implements ValidatorWithArg<ResolvablePriceQuantity, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ResolvablePriceQuantity> ValidationResult<ResolvablePriceQuantity> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("quantityCumulation", ExistenceChecker.isSet((List<? extends CumulationFeature>) o.getQuantityCumulation()))
				.put("resolvedQuantity", ExistenceChecker.isSet((Quantity) o.getResolvedQuantity()))
				.put("quantitySchedule", ExistenceChecker.isSet((ReferenceWithMetaNonNegativeQuantitySchedule) o.getQuantitySchedule()))
				.put("quantityReference", ExistenceChecker.isSet((ReferenceWithMetaResolvablePriceQuantity) o.getQuantityReference()))
				.put("quantityMultiplier", ExistenceChecker.isSet((QuantityMultiplier) o.getQuantityMultiplier()))
				.put("reset", ExistenceChecker.isSet((Boolean) o.getReset()))
				.put("futureValueNotional", ExistenceChecker.isSet((FutureValueAmount) o.getFutureValueNotional()))
				.put("priceSchedule", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getPriceSchedule()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ResolvablePriceQuantity", ValidationType.ONLY_EXISTS, "ResolvablePriceQuantity", path, "");
		}
		return failure("ResolvablePriceQuantity", ValidationType.ONLY_EXISTS, "ResolvablePriceQuantity", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
