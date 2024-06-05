package cdm.event.position.validation.exists;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.metafields.ReferenceWithMetaTrade;
import cdm.event.position.AggregationParameters;
import cdm.event.position.PositionStatusEnum;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AggregationParametersOnlyExistsValidator implements ValidatorWithArg<AggregationParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AggregationParameters> ValidationResult<AggregationParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dateTime", ExistenceChecker.isSet((ZonedDateTime) o.getDateTime()))
				.put("totalPosition", ExistenceChecker.isSet((Boolean) o.getTotalPosition()))
				.put("positionStatus", ExistenceChecker.isSet((PositionStatusEnum) o.getPositionStatus()))
				.put("party", ExistenceChecker.isSet((List<? extends ReferenceWithMetaParty>) o.getParty()))
				.put("product", ExistenceChecker.isSet((List<? extends Product>) o.getProduct()))
				.put("productQualifier", ExistenceChecker.isSet((List<String>) o.getProductQualifier()))
				.put("tradeReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaTrade>) o.getTradeReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AggregationParameters", ValidationType.ONLY_EXISTS, "AggregationParameters", path, "");
		}
		return failure("AggregationParameters", ValidationType.ONLY_EXISTS, "AggregationParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
