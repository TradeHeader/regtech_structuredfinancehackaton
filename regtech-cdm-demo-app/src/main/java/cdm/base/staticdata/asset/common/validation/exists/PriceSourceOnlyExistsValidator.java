package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.PriceSource;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PriceSourceOnlyExistsValidator implements ValidatorWithArg<PriceSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PriceSource> ValidationResult<PriceSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("pricePublisher", ExistenceChecker.isSet((FieldWithMetaString) o.getPricePublisher()))
				.put("priceSourceLocation", ExistenceChecker.isSet((String) o.getPriceSourceLocation()))
				.put("priceSourceHeading", ExistenceChecker.isSet((String) o.getPriceSourceHeading()))
				.put("priceSourceTime", ExistenceChecker.isSet((LocalTime) o.getPriceSourceTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PriceSource", ValidationType.ONLY_EXISTS, "PriceSource", path, "");
		}
		return failure("PriceSource", ValidationType.ONLY_EXISTS, "PriceSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
