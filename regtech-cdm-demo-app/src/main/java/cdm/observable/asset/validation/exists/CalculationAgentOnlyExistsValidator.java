package cdm.observable.asset.validation.exists;

import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.observable.asset.PartyDeterminationEnum;
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

public class CalculationAgentOnlyExistsValidator implements ValidatorWithArg<CalculationAgent, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationAgent> ValidationResult<CalculationAgent> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationAgentParty", ExistenceChecker.isSet((AncillaryRoleEnum) o.getCalculationAgentParty()))
				.put("calculationAgentPartyEnum", ExistenceChecker.isSet((PartyDeterminationEnum) o.getCalculationAgentPartyEnum()))
				.put("calculationAgentBusinessCenter", ExistenceChecker.isSet((FieldWithMetaBusinessCenterEnum) o.getCalculationAgentBusinessCenter()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationAgent", ValidationType.ONLY_EXISTS, "CalculationAgent", path, "");
		}
		return failure("CalculationAgent", ValidationType.ONLY_EXISTS, "CalculationAgent", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
