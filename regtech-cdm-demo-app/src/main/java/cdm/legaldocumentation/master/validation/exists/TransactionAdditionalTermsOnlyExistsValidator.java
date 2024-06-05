package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.FxAdditionalTerms;
import cdm.legaldocumentation.master.TransactionAdditionalTerms;
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

public class TransactionAdditionalTermsOnlyExistsValidator implements ValidatorWithArg<TransactionAdditionalTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TransactionAdditionalTerms> ValidationResult<TransactionAdditionalTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("equityAdditionalTerms", ExistenceChecker.isSet((EquityAdditionalTerms) o.getEquityAdditionalTerms()))
				.put("foreignExchangeAdditionalTerms", ExistenceChecker.isSet((FxAdditionalTerms) o.getForeignExchangeAdditionalTerms()))
				.put("commoditiesAdditionalTerms", ExistenceChecker.isSet((String) o.getCommoditiesAdditionalTerms()))
				.put("creditAdditionalTerms", ExistenceChecker.isSet((String) o.getCreditAdditionalTerms()))
				.put("interestRateAdditionalTerms", ExistenceChecker.isSet((String) o.getInterestRateAdditionalTerms()))
				.put("digitalAssetAdditionalTerms", ExistenceChecker.isSet((String) o.getDigitalAssetAdditionalTerms()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TransactionAdditionalTerms", ValidationType.ONLY_EXISTS, "TransactionAdditionalTerms", path, "");
		}
		return failure("TransactionAdditionalTerms", ValidationType.ONLY_EXISTS, "TransactionAdditionalTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
