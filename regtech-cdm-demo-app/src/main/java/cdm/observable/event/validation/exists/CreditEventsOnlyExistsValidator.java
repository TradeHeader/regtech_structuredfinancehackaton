package cdm.observable.event.validation.exists;

import cdm.observable.asset.Money;
import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.CreditEvents;
import cdm.observable.event.FailureToPay;
import cdm.observable.event.Restructuring;
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

public class CreditEventsOnlyExistsValidator implements ValidatorWithArg<CreditEvents, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditEvents> ValidationResult<CreditEvents> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("bankruptcy", ExistenceChecker.isSet((Boolean) o.getBankruptcy()))
				.put("failureToPay", ExistenceChecker.isSet((FailureToPay) o.getFailureToPay()))
				.put("failureToPayPrincipal", ExistenceChecker.isSet((Boolean) o.getFailureToPayPrincipal()))
				.put("failureToPayInterest", ExistenceChecker.isSet((Boolean) o.getFailureToPayInterest()))
				.put("obligationDefault", ExistenceChecker.isSet((Boolean) o.getObligationDefault()))
				.put("obligationAcceleration", ExistenceChecker.isSet((Boolean) o.getObligationAcceleration()))
				.put("repudiationMoratorium", ExistenceChecker.isSet((Boolean) o.getRepudiationMoratorium()))
				.put("restructuring", ExistenceChecker.isSet((Restructuring) o.getRestructuring()))
				.put("governmentalIntervention", ExistenceChecker.isSet((Boolean) o.getGovernmentalIntervention()))
				.put("distressedRatingsDowngrade", ExistenceChecker.isSet((Boolean) o.getDistressedRatingsDowngrade()))
				.put("maturityExtension", ExistenceChecker.isSet((Boolean) o.getMaturityExtension()))
				.put("writedown", ExistenceChecker.isSet((Boolean) o.getWritedown()))
				.put("impliedWritedown", ExistenceChecker.isSet((Boolean) o.getImpliedWritedown()))
				.put("defaultRequirement", ExistenceChecker.isSet((Money) o.getDefaultRequirement()))
				.put("creditEventNotice", ExistenceChecker.isSet((CreditEventNotice) o.getCreditEventNotice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditEvents", ValidationType.ONLY_EXISTS, "CreditEvents", path, "");
		}
		return failure("CreditEvents", ValidationType.ONLY_EXISTS, "CreditEvents", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
