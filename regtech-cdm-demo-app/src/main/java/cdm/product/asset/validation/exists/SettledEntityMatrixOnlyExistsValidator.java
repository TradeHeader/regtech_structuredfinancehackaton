package cdm.product.asset.validation.exists;

import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.metafields.FieldWithMetaSettledEntityMatrixSourceEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class SettledEntityMatrixOnlyExistsValidator implements ValidatorWithArg<SettledEntityMatrix, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SettledEntityMatrix> ValidationResult<SettledEntityMatrix> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("matrixSource", ExistenceChecker.isSet((FieldWithMetaSettledEntityMatrixSourceEnum) o.getMatrixSource()))
				.put("publicationDate", ExistenceChecker.isSet((Date) o.getPublicationDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SettledEntityMatrix", ValidationType.ONLY_EXISTS, "SettledEntityMatrix", path, "");
		}
		return failure("SettledEntityMatrix", ValidationType.ONLY_EXISTS, "SettledEntityMatrix", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
