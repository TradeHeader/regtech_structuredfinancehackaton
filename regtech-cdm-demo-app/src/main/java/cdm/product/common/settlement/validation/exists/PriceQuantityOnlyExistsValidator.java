package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.Observable;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
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

public class PriceQuantityOnlyExistsValidator implements ValidatorWithArg<PriceQuantity, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PriceQuantity> ValidationResult<PriceQuantity> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("price", ExistenceChecker.isSet((List<? extends FieldWithMetaPriceSchedule>) o.getPrice()))
				.put("quantity", ExistenceChecker.isSet((List<? extends FieldWithMetaNonNegativeQuantitySchedule>) o.getQuantity()))
				.put("observable", ExistenceChecker.isSet((Observable) o.getObservable()))
				.put("buyerSeller", ExistenceChecker.isSet((BuyerSeller) o.getBuyerSeller()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("effectiveDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getEffectiveDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PriceQuantity", ValidationType.ONLY_EXISTS, "PriceQuantity", path, "");
		}
		return failure("PriceQuantity", ValidationType.ONLY_EXISTS, "PriceQuantity", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
