package cdm.product.template.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradeLot;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class TradeLotOnlyExistsValidator implements ValidatorWithArg<TradeLot, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TradeLot> ValidationResult<TradeLot> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("lotIdentifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getLotIdentifier()))
				.put("priceQuantity", ExistenceChecker.isSet((List<? extends PriceQuantity>) o.getPriceQuantity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TradeLot", ValidationType.ONLY_EXISTS, "TradeLot", path, "");
		}
		return failure("TradeLot", ValidationType.ONLY_EXISTS, "TradeLot", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
