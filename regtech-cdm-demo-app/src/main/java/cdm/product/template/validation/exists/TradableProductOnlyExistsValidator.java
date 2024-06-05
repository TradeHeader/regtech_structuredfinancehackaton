package cdm.product.template.validation.exists;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
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

public class TradableProductOnlyExistsValidator implements ValidatorWithArg<TradableProduct, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TradableProduct> ValidationResult<TradableProduct> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("product", ExistenceChecker.isSet((Product) o.getProduct()))
				.put("tradeLot", ExistenceChecker.isSet((List<? extends TradeLot>) o.getTradeLot()))
				.put("counterparty", ExistenceChecker.isSet((List<? extends Counterparty>) o.getCounterparty()))
				.put("ancillaryParty", ExistenceChecker.isSet((List<? extends AncillaryParty>) o.getAncillaryParty()))
				.put("adjustment", ExistenceChecker.isSet((NotionalAdjustmentEnum) o.getAdjustment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TradableProduct", ValidationType.ONLY_EXISTS, "TradableProduct", path, "");
		}
		return failure("TradableProduct", ValidationType.ONLY_EXISTS, "TradableProduct", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
