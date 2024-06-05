package cdm.event.workflow.validation.exists;

import cdm.event.workflow.MessageInformation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class MessageInformationOnlyExistsValidator implements ValidatorWithArg<MessageInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MessageInformation> ValidationResult<MessageInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("messageId", ExistenceChecker.isSet((FieldWithMetaString) o.getMessageId()))
				.put("sentBy", ExistenceChecker.isSet((FieldWithMetaString) o.getSentBy()))
				.put("sentTo", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getSentTo()))
				.put("copyTo", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getCopyTo()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MessageInformation", ValidationType.ONLY_EXISTS, "MessageInformation", path, "");
		}
		return failure("MessageInformation", ValidationType.ONLY_EXISTS, "MessageInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
