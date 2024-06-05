package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.Resource;
import cdm.legaldocumentation.common.ResourceLength;
import cdm.legaldocumentation.common.metafields.FieldWithMetaResourceTypeEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ResourceOnlyExistsValidator implements ValidatorWithArg<Resource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Resource> ValidationResult<Resource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("resourceId", ExistenceChecker.isSet((FieldWithMetaString) o.getResourceId()))
				.put("resourceType", ExistenceChecker.isSet((FieldWithMetaResourceTypeEnum) o.getResourceType()))
				.put("language", ExistenceChecker.isSet((FieldWithMetaString) o.getLanguage()))
				.put("sizeInBytes", ExistenceChecker.isSet((BigDecimal) o.getSizeInBytes()))
				.put("length", ExistenceChecker.isSet((ResourceLength) o.getLength()))
				.put("mimeType", ExistenceChecker.isSet((FieldWithMetaString) o.getMimeType()))
				.put("name", ExistenceChecker.isSet((String) o.getName()))
				.put("comments", ExistenceChecker.isSet((String) o.getComments()))
				.put("string", ExistenceChecker.isSet((String) o.getString()))
				.put("url", ExistenceChecker.isSet((String) o.getUrl()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Resource", ValidationType.ONLY_EXISTS, "Resource", path, "");
		}
		return failure("Resource", ValidationType.ONLY_EXISTS, "Resource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
