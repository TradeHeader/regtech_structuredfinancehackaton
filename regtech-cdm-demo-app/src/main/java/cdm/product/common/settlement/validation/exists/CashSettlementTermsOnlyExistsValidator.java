package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.Money;
import cdm.observable.asset.ValuationMethod;
import cdm.product.common.settlement.CashSettlementMethodEnum;
import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.ValuationDate;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CashSettlementTermsOnlyExistsValidator implements ValidatorWithArg<CashSettlementTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CashSettlementTerms> ValidationResult<CashSettlementTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cashSettlementMethod", ExistenceChecker.isSet((CashSettlementMethodEnum) o.getCashSettlementMethod()))
				.put("valuationMethod", ExistenceChecker.isSet((ValuationMethod) o.getValuationMethod()))
				.put("valuationDate", ExistenceChecker.isSet((ValuationDate) o.getValuationDate()))
				.put("valuationTime", ExistenceChecker.isSet((BusinessCenterTime) o.getValuationTime()))
				.put("cashSettlementAmount", ExistenceChecker.isSet((Money) o.getCashSettlementAmount()))
				.put("recoveryFactor", ExistenceChecker.isSet((BigDecimal) o.getRecoveryFactor()))
				.put("fixedSettlement", ExistenceChecker.isSet((Boolean) o.getFixedSettlement()))
				.put("accruedInterest", ExistenceChecker.isSet((Boolean) o.getAccruedInterest()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CashSettlementTerms", ValidationType.ONLY_EXISTS, "CashSettlementTerms", path, "");
		}
		return failure("CashSettlementTerms", ValidationType.ONLY_EXISTS, "CashSettlementTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
