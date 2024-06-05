package cdm.base.datetime.validation.exists;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.BusinessDayAdjustments;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AdjustableOrAdjustedDateOnlyExistsValidator implements ValidatorWithArg<AdjustableOrAdjustedDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AdjustableOrAdjustedDate> ValidationResult<AdjustableOrAdjustedDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("unadjustedDate", ExistenceChecker.isSet((Date) o.getUnadjustedDate()))
				.put("dateAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getDateAdjustments()))
				.put("adjustedDate", ExistenceChecker.isSet((FieldWithMetaDate) o.getAdjustedDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AdjustableOrAdjustedDate", ValidationType.ONLY_EXISTS, "AdjustableOrAdjustedDate", path, "");
		}
		return failure("AdjustableOrAdjustedDate", ValidationType.ONLY_EXISTS, "AdjustableOrAdjustedDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
