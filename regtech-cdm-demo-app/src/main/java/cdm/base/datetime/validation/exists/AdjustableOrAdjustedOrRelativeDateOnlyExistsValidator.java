package cdm.base.datetime.validation.exists;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.RelativeDateOffset;
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

public class AdjustableOrAdjustedOrRelativeDateOnlyExistsValidator implements ValidatorWithArg<AdjustableOrAdjustedOrRelativeDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AdjustableOrAdjustedOrRelativeDate> ValidationResult<AdjustableOrAdjustedOrRelativeDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("unadjustedDate", ExistenceChecker.isSet((Date) o.getUnadjustedDate()))
				.put("dateAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getDateAdjustments()))
				.put("adjustedDate", ExistenceChecker.isSet((FieldWithMetaDate) o.getAdjustedDate()))
				.put("relativeDate", ExistenceChecker.isSet((RelativeDateOffset) o.getRelativeDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AdjustableOrAdjustedOrRelativeDate", ValidationType.ONLY_EXISTS, "AdjustableOrAdjustedOrRelativeDate", path, "");
		}
		return failure("AdjustableOrAdjustedOrRelativeDate", ValidationType.ONLY_EXISTS, "AdjustableOrAdjustedOrRelativeDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
