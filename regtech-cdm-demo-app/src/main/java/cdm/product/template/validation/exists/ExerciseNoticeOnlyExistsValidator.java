package cdm.product.template.validation.exists;

import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.ExerciseNoticeGiverEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ExerciseNoticeOnlyExistsValidator implements ValidatorWithArg<ExerciseNotice, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExerciseNotice> ValidationResult<ExerciseNotice> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("exerciseNoticeGiver", ExistenceChecker.isSet((ExerciseNoticeGiverEnum) o.getExerciseNoticeGiver()))
				.put("exerciseNoticeReceiver", ExistenceChecker.isSet((AncillaryRoleEnum) o.getExerciseNoticeReceiver()))
				.put("businessCenter", ExistenceChecker.isSet((FieldWithMetaBusinessCenterEnum) o.getBusinessCenter()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExerciseNotice", ValidationType.ONLY_EXISTS, "ExerciseNotice", path, "");
		}
		return failure("ExerciseNotice", ValidationType.ONLY_EXISTS, "ExerciseNotice", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
