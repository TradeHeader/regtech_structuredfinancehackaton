package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.asset.common.Index;
import cdm.product.collateral.ListingType;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ListingTypeOnlyExistsValidator implements ValidatorWithArg<ListingType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ListingType> ValidationResult<ListingType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("exchange", ExistenceChecker.isSet((FieldWithMetaString) o.getExchange()))
				.put("sector", ExistenceChecker.isSet((FieldWithMetaString) o.getSector()))
				.put("index", ExistenceChecker.isSet((Index) o.getIndex()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ListingType", ValidationType.ONLY_EXISTS, "ListingType", path, "");
		}
		return failure("ListingType", ValidationType.ONLY_EXISTS, "ListingType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
