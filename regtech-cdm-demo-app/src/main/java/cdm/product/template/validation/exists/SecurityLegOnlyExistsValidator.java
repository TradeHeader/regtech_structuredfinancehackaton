package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.ExchangeRate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.DeliveryMethodEnum;
import cdm.product.template.SecurityLeg;
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

public class SecurityLegOnlyExistsValidator implements ValidatorWithArg<SecurityLeg, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SecurityLeg> ValidationResult<SecurityLeg> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("buyerSeller", ExistenceChecker.isSet((BuyerSeller) o.getBuyerSeller()))
				.put("settlementDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getSettlementDate()))
				.put("settlementAmount", ExistenceChecker.isSet((Money) o.getSettlementAmount()))
				.put("settlementCurrency", ExistenceChecker.isSet((String) o.getSettlementCurrency()))
				.put("fxRate", ExistenceChecker.isSet((ExchangeRate) o.getFxRate()))
				.put("deliveryDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getDeliveryDate()))
				.put("deliveryMethod", ExistenceChecker.isSet((DeliveryMethodEnum) o.getDeliveryMethod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SecurityLeg", ValidationType.ONLY_EXISTS, "SecurityLeg", path, "");
		}
		return failure("SecurityLeg", ValidationType.ONLY_EXISTS, "SecurityLeg", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
