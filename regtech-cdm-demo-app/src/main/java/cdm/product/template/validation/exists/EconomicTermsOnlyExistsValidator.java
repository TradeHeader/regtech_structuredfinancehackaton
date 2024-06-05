package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.observable.asset.CalculationAgent;
import cdm.product.collateral.Collateral;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.TerminationProvision;
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

public class EconomicTermsOnlyExistsValidator implements ValidatorWithArg<EconomicTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EconomicTerms> ValidationResult<EconomicTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("effectiveDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getEffectiveDate()))
				.put("terminationDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getTerminationDate()))
				.put("dateAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getDateAdjustments()))
				.put("payout", ExistenceChecker.isSet((Payout) o.getPayout()))
				.put("terminationProvision", ExistenceChecker.isSet((TerminationProvision) o.getTerminationProvision()))
				.put("calculationAgent", ExistenceChecker.isSet((CalculationAgent) o.getCalculationAgent()))
				.put("nonStandardisedTerms", ExistenceChecker.isSet((Boolean) o.getNonStandardisedTerms()))
				.put("collateral", ExistenceChecker.isSet((Collateral) o.getCollateral()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EconomicTerms", ValidationType.ONLY_EXISTS, "EconomicTerms", path, "");
		}
		return failure("EconomicTerms", ValidationType.ONLY_EXISTS, "EconomicTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
