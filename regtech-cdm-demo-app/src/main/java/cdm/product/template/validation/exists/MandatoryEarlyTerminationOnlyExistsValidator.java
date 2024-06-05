package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableDate;
import cdm.observable.asset.CalculationAgent;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.MandatoryEarlyTermination;
import cdm.product.template.MandatoryEarlyTerminationAdjustedDates;
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

public class MandatoryEarlyTerminationOnlyExistsValidator implements ValidatorWithArg<MandatoryEarlyTermination, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MandatoryEarlyTermination> ValidationResult<MandatoryEarlyTermination> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("mandatoryEarlyTerminationDate", ExistenceChecker.isSet((AdjustableDate) o.getMandatoryEarlyTerminationDate()))
				.put("calculationAgent", ExistenceChecker.isSet((CalculationAgent) o.getCalculationAgent()))
				.put("cashSettlement", ExistenceChecker.isSet((SettlementTerms) o.getCashSettlement()))
				.put("mandatoryEarlyTerminationAdjustedDates", ExistenceChecker.isSet((MandatoryEarlyTerminationAdjustedDates) o.getMandatoryEarlyTerminationAdjustedDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MandatoryEarlyTermination", ValidationType.ONLY_EXISTS, "MandatoryEarlyTermination", path, "");
		}
		return failure("MandatoryEarlyTermination", ValidationType.ONLY_EXISTS, "MandatoryEarlyTermination", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
