package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.Money;
import cdm.observable.asset.QuotationRateTypeEnum;
import cdm.observable.asset.ValuationMethod;
import cdm.observable.asset.ValuationMethodEnum;
import cdm.observable.asset.ValuationSource;
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

public class ValuationMethodOnlyExistsValidator implements ValidatorWithArg<ValuationMethod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ValuationMethod> ValidationResult<ValuationMethod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("valuationSource", ExistenceChecker.isSet((ValuationSource) o.getValuationSource()))
				.put("quotationMethod", ExistenceChecker.isSet((QuotationRateTypeEnum) o.getQuotationMethod()))
				.put("valuationMethod", ExistenceChecker.isSet((ValuationMethodEnum) o.getValuationMethod()))
				.put("quotationAmount", ExistenceChecker.isSet((Money) o.getQuotationAmount()))
				.put("minimumQuotationAmount", ExistenceChecker.isSet((Money) o.getMinimumQuotationAmount()))
				.put("cashCollateralValuationMethod", ExistenceChecker.isSet((CashCollateralValuationMethod) o.getCashCollateralValuationMethod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ValuationMethod", ValidationType.ONLY_EXISTS, "ValuationMethod", path, "");
		}
		return failure("ValuationMethod", ValidationType.ONLY_EXISTS, "ValuationMethod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
