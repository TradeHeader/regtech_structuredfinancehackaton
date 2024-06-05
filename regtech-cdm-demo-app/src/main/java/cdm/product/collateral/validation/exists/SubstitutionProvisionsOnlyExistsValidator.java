package cdm.product.collateral.validation.exists;

import cdm.base.datetime.Period;
import cdm.product.collateral.SubstitutionProvisions;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class SubstitutionProvisionsOnlyExistsValidator implements ValidatorWithArg<SubstitutionProvisions, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SubstitutionProvisions> ValidationResult<SubstitutionProvisions> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("numberOfSubstitutionsAllowed", ExistenceChecker.isSet((Integer) o.getNumberOfSubstitutionsAllowed()))
				.put("noticeDeadlinePeriod", ExistenceChecker.isSet((Period) o.getNoticeDeadlinePeriod()))
				.put("noticeDeadlineDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getNoticeDeadlineDateTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SubstitutionProvisions", ValidationType.ONLY_EXISTS, "SubstitutionProvisions", path, "");
		}
		return failure("SubstitutionProvisions", ValidationType.ONLY_EXISTS, "SubstitutionProvisions", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
