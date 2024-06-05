package cdm.observable.asset.validation.exists;

import cdm.base.staticdata.asset.common.metafields.FieldWithMetaCommodity;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.observable.asset.Observable;
import cdm.observable.asset.OptionReferenceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaQuotedCurrencyPair;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ObservableOnlyExistsValidator implements ValidatorWithArg<Observable, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Observable> ValidationResult<Observable> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rateOption", ExistenceChecker.isSet((FieldWithMetaFloatingRateOption) o.getRateOption()))
				.put("commodity", ExistenceChecker.isSet((FieldWithMetaCommodity) o.getCommodity()))
				.put("productIdentifier", ExistenceChecker.isSet((List<? extends FieldWithMetaProductIdentifier>) o.getProductIdentifier()))
				.put("currencyPair", ExistenceChecker.isSet((FieldWithMetaQuotedCurrencyPair) o.getCurrencyPair()))
				.put("optionReferenceType", ExistenceChecker.isSet((OptionReferenceTypeEnum) o.getOptionReferenceType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Observable", ValidationType.ONLY_EXISTS, "Observable", path, "");
		}
		return failure("Observable", ValidationType.ONLY_EXISTS, "Observable", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
