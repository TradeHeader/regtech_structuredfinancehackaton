package cdm.event.common.validation.exists;

import cdm.event.common.Reset;
import cdm.observable.asset.Price;
import cdm.observable.event.metafields.ReferenceWithMetaObservation;
import cdm.product.template.AveragingCalculation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class ResetOnlyExistsValidator implements ValidatorWithArg<Reset, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Reset> ValidationResult<Reset> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("resetValue", ExistenceChecker.isSet((Price) o.getResetValue()))
				.put("resetDate", ExistenceChecker.isSet((Date) o.getResetDate()))
				.put("rateRecordDate", ExistenceChecker.isSet((Date) o.getRateRecordDate()))
				.put("observations", ExistenceChecker.isSet((List<? extends ReferenceWithMetaObservation>) o.getObservations()))
				.put("averagingMethodology", ExistenceChecker.isSet((AveragingCalculation) o.getAveragingMethodology()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Reset", ValidationType.ONLY_EXISTS, "Reset", path, "");
		}
		return failure("Reset", ValidationType.ONLY_EXISTS, "Reset", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
