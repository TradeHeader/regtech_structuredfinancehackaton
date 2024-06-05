package cdm.product.asset.validation.exists;

import cdm.base.math.Quantity;
import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class CalculationScheduleDeliveryPeriodsOnlyExistsValidator implements ValidatorWithArg<CalculationScheduleDeliveryPeriods, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationScheduleDeliveryPeriods> ValidationResult<CalculationScheduleDeliveryPeriods> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("profile", ExistenceChecker.isSet((List<? extends AssetDeliveryProfile>) o.getProfile()))
				.put("startDate", ExistenceChecker.isSet((Date) o.getStartDate()))
				.put("endDate", ExistenceChecker.isSet((Date) o.getEndDate()))
				.put("deliveryCapacity", ExistenceChecker.isSet((Quantity) o.getDeliveryCapacity()))
				.put("priceTimeIntervalQuantity", ExistenceChecker.isSet((Price) o.getPriceTimeIntervalQuantity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationScheduleDeliveryPeriods", ValidationType.ONLY_EXISTS, "CalculationScheduleDeliveryPeriods", path, "");
		}
		return failure("CalculationScheduleDeliveryPeriods", ValidationType.ONLY_EXISTS, "CalculationScheduleDeliveryPeriods", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
