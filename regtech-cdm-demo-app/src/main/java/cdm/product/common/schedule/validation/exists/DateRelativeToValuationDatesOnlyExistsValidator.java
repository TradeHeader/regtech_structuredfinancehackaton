package cdm.product.common.schedule.validation.exists;

import cdm.observable.asset.metafields.ReferenceWithMetaPerformanceValuationDates;
import cdm.product.common.schedule.DateRelativeToValuationDates;
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

public class DateRelativeToValuationDatesOnlyExistsValidator implements ValidatorWithArg<DateRelativeToValuationDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DateRelativeToValuationDates> ValidationResult<DateRelativeToValuationDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("valuationDatesReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPerformanceValuationDates>) o.getValuationDatesReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DateRelativeToValuationDates", ValidationType.ONLY_EXISTS, "DateRelativeToValuationDates", path, "");
		}
		return failure("DateRelativeToValuationDates", ValidationType.ONLY_EXISTS, "DateRelativeToValuationDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
