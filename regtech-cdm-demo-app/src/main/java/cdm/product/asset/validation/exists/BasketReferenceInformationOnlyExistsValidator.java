package cdm.product.asset.validation.exists;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.ReferencePool;
import cdm.product.asset.Tranche;
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

public class BasketReferenceInformationOnlyExistsValidator implements ValidatorWithArg<BasketReferenceInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BasketReferenceInformation> ValidationResult<BasketReferenceInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("basketName", ExistenceChecker.isSet((FieldWithMetaString) o.getBasketName()))
				.put("basketId", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getBasketId()))
				.put("referencePool", ExistenceChecker.isSet((ReferencePool) o.getReferencePool()))
				.put("nthToDefault", ExistenceChecker.isSet((Integer) o.getNthToDefault()))
				.put("mthToDefault", ExistenceChecker.isSet((Integer) o.getMthToDefault()))
				.put("tranche", ExistenceChecker.isSet((Tranche) o.getTranche()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BasketReferenceInformation", ValidationType.ONLY_EXISTS, "BasketReferenceInformation", path, "");
		}
		return failure("BasketReferenceInformation", ValidationType.ONLY_EXISTS, "BasketReferenceInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
