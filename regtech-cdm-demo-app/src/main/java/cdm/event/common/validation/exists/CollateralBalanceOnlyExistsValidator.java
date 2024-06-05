package cdm.event.common.validation.exists;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralStatusEnum;
import cdm.event.common.HaircutIndicatorEnum;
import cdm.observable.asset.Money;
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

public class CollateralBalanceOnlyExistsValidator implements ValidatorWithArg<CollateralBalance, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralBalance> ValidationResult<CollateralBalance> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("collateralBalanceStatus", ExistenceChecker.isSet((CollateralStatusEnum) o.getCollateralBalanceStatus()))
				.put("haircutIndicator", ExistenceChecker.isSet((HaircutIndicatorEnum) o.getHaircutIndicator()))
				.put("amountBaseCurrency", ExistenceChecker.isSet((Money) o.getAmountBaseCurrency()))
				.put("payerReceiver", ExistenceChecker.isSet((PartyReferencePayerReceiver) o.getPayerReceiver()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralBalance", ValidationType.ONLY_EXISTS, "CollateralBalance", path, "");
		}
		return failure("CollateralBalance", ValidationType.ONLY_EXISTS, "CollateralBalance", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
