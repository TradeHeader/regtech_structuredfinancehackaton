package cdm.product.asset.validation.exists;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.asset.FPVFinalPriceElectionFallbackEnum;
import cdm.product.asset.ValuationTerms;
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

public class ValuationTermsOnlyExistsValidator implements ValidatorWithArg<ValuationTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ValuationTerms> ValidationResult<ValuationTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("futuresPriceValuation", ExistenceChecker.isSet((Boolean) o.getFuturesPriceValuation()))
				.put("optionsPriceValuation", ExistenceChecker.isSet((Boolean) o.getOptionsPriceValuation()))
				.put("numberOfValuationDates", ExistenceChecker.isSet((Integer) o.getNumberOfValuationDates()))
				.put("dividendValuationDates", ExistenceChecker.isSet((AdjustableRelativeOrPeriodicDates) o.getDividendValuationDates()))
				.put("fPVFinalPriceElectionFallback", ExistenceChecker.isSet((FPVFinalPriceElectionFallbackEnum) o.getFPVFinalPriceElectionFallback()))
				.put("multipleExchangeIndexAnnexFallback", ExistenceChecker.isSet((Boolean) o.getMultipleExchangeIndexAnnexFallback()))
				.put("componentSecurityIndexAnnexFallback", ExistenceChecker.isSet((Boolean) o.getComponentSecurityIndexAnnexFallback()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ValuationTerms", ValidationType.ONLY_EXISTS, "ValuationTerms", path, "");
		}
		return failure("ValuationTerms", ValidationType.ONLY_EXISTS, "ValuationTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
