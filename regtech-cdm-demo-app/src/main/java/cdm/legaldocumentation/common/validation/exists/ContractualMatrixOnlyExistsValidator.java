package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTermEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTypeEnum;
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

public class ContractualMatrixOnlyExistsValidator implements ValidatorWithArg<ContractualMatrix, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ContractualMatrix> ValidationResult<ContractualMatrix> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("matrixType", ExistenceChecker.isSet((FieldWithMetaMatrixTypeEnum) o.getMatrixType()))
				.put("matrixTerm", ExistenceChecker.isSet((FieldWithMetaMatrixTermEnum) o.getMatrixTerm()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ContractualMatrix", ValidationType.ONLY_EXISTS, "ContractualMatrix", path, "");
		}
		return failure("ContractualMatrix", ValidationType.ONLY_EXISTS, "ContractualMatrix", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
