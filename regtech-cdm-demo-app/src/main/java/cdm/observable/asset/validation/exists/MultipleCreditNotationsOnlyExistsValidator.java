package cdm.observable.asset.validation.exists;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.observable.asset.MultipleCreditNotations;
import cdm.observable.asset.metafields.FieldWithMetaCreditNotation;
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

public class MultipleCreditNotationsOnlyExistsValidator implements ValidatorWithArg<MultipleCreditNotations, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MultipleCreditNotations> ValidationResult<MultipleCreditNotations> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("condition", ExistenceChecker.isSet((QuantifierEnum) o.getCondition()))
				.put("creditNotation", ExistenceChecker.isSet((List<? extends FieldWithMetaCreditNotation>) o.getCreditNotation()))
				.put("mismatchResolution", ExistenceChecker.isSet((CreditNotationMismatchResolutionEnum) o.getMismatchResolution()))
				.put("referenceAgency", ExistenceChecker.isSet((CreditRatingAgencyEnum) o.getReferenceAgency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MultipleCreditNotations", ValidationType.ONLY_EXISTS, "MultipleCreditNotations", path, "");
		}
		return failure("MultipleCreditNotations", ValidationType.ONLY_EXISTS, "MultipleCreditNotations", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
