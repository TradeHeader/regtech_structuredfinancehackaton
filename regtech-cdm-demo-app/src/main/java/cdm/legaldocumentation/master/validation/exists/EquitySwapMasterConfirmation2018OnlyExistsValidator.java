package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.observable.asset.InterpolationMethodEnum;
import cdm.observable.asset.ValuationDates;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.ReturnTypeEnum;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.SettlementTerms;
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

public class EquitySwapMasterConfirmation2018OnlyExistsValidator implements ValidatorWithArg<EquitySwapMasterConfirmation2018, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EquitySwapMasterConfirmation2018> ValidationResult<EquitySwapMasterConfirmation2018> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("typeOfSwapElection", ExistenceChecker.isSet((ReturnTypeEnum) o.getTypeOfSwapElection()))
				.put("pricingMethodElection", ExistenceChecker.isSet((PriceReturnTerms) o.getPricingMethodElection()))
				.put("linearInterpolationElection", ExistenceChecker.isSet((InterpolationMethodEnum) o.getLinearInterpolationElection()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("valuationDates", ExistenceChecker.isSet((ValuationDates) o.getValuationDates()))
				.put("equityCashSettlementDates", ExistenceChecker.isSet((PaymentDates) o.getEquityCashSettlementDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EquitySwapMasterConfirmation2018", ValidationType.ONLY_EXISTS, "EquitySwapMasterConfirmation2018", path, "");
		}
		return failure("EquitySwapMasterConfirmation2018", ValidationType.ONLY_EXISTS, "EquitySwapMasterConfirmation2018", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
