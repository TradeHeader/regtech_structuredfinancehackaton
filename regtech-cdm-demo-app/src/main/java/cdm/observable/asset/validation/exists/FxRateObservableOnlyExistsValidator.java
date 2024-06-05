package cdm.observable.asset.validation.exists;

import cdm.observable.asset.FxRateObservable;
import cdm.observable.asset.InformationSource;
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

public class FxRateObservableOnlyExistsValidator implements ValidatorWithArg<FxRateObservable, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxRateObservable> ValidationResult<FxRateObservable> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("quotedCurrencyPair", ExistenceChecker.isSet((ReferenceWithMetaQuotedCurrencyPair) o.getQuotedCurrencyPair()))
				.put("primaryFxSpotRateSource", ExistenceChecker.isSet((InformationSource) o.getPrimaryFxSpotRateSource()))
				.put("secondaryFxSpotRateSource", ExistenceChecker.isSet((InformationSource) o.getSecondaryFxSpotRateSource()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxRateObservable", ValidationType.ONLY_EXISTS, "FxRateObservable", path, "");
		}
		return failure("FxRateObservable", ValidationType.ONLY_EXISTS, "FxRateObservable", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
