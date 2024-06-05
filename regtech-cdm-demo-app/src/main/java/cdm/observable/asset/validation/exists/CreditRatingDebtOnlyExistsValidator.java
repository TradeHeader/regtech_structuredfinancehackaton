package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CreditRatingDebt;
import cdm.observable.asset.MultipleDebtTypes;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CreditRatingDebtOnlyExistsValidator implements ValidatorWithArg<CreditRatingDebt, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditRatingDebt> ValidationResult<CreditRatingDebt> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("debtType", ExistenceChecker.isSet((FieldWithMetaString) o.getDebtType()))
				.put("debtTypes", ExistenceChecker.isSet((MultipleDebtTypes) o.getDebtTypes()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditRatingDebt", ValidationType.ONLY_EXISTS, "CreditRatingDebt", path, "");
		}
		return failure("CreditRatingDebt", ValidationType.ONLY_EXISTS, "CreditRatingDebt", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
