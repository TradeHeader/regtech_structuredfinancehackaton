package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.LoanParticipation;
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

public class LoanParticipationOnlyExistsValidator implements ValidatorWithArg<LoanParticipation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends LoanParticipation> ValidationResult<LoanParticipation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("applicable", ExistenceChecker.isSet((Boolean) o.getApplicable()))
				.put("partialCashSettlement", ExistenceChecker.isSet((Boolean) o.getPartialCashSettlement()))
				.put("qualifyingParticipationSeller", ExistenceChecker.isSet((String) o.getQualifyingParticipationSeller()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("LoanParticipation", ValidationType.ONLY_EXISTS, "LoanParticipation", path, "");
		}
		return failure("LoanParticipation", ValidationType.ONLY_EXISTS, "LoanParticipation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
