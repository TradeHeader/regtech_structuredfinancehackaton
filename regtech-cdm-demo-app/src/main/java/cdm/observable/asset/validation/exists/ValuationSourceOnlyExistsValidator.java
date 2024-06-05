package cdm.observable.asset.validation.exists;

import cdm.base.staticdata.party.AncillaryEntity;
import cdm.base.staticdata.party.ReferenceBanks;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.SettlementRateOption;
import cdm.observable.asset.ValuationSource;
import cdm.observable.asset.metafields.ReferenceWithMetaQuotedCurrencyPair;
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

public class ValuationSourceOnlyExistsValidator implements ValidatorWithArg<ValuationSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ValuationSource> ValidationResult<ValuationSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("quotedCurrencyPair", ExistenceChecker.isSet((ReferenceWithMetaQuotedCurrencyPair) o.getQuotedCurrencyPair()))
				.put("informationSource", ExistenceChecker.isSet((FxSpotRateSource) o.getInformationSource()))
				.put("settlementRateOption", ExistenceChecker.isSet((SettlementRateOption) o.getSettlementRateOption()))
				.put("referenceBanks", ExistenceChecker.isSet((ReferenceBanks) o.getReferenceBanks()))
				.put("dealerOrCCP", ExistenceChecker.isSet((AncillaryEntity) o.getDealerOrCCP()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ValuationSource", ValidationType.ONLY_EXISTS, "ValuationSource", path, "");
		}
		return failure("ValuationSource", ValidationType.ONLY_EXISTS, "ValuationSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
