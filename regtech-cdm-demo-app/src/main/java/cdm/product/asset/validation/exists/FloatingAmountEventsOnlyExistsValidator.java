package cdm.product.asset.validation.exists;

import cdm.product.asset.AdditionalFixedPayments;
import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.FloatingAmountProvisions;
import cdm.product.asset.InterestShortFall;
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

public class FloatingAmountEventsOnlyExistsValidator implements ValidatorWithArg<FloatingAmountEvents, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingAmountEvents> ValidationResult<FloatingAmountEvents> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("failureToPayPrincipal", ExistenceChecker.isSet((Boolean) o.getFailureToPayPrincipal()))
				.put("interestShortfall", ExistenceChecker.isSet((InterestShortFall) o.getInterestShortfall()))
				.put("writedown", ExistenceChecker.isSet((Boolean) o.getWritedown()))
				.put("impliedWritedown", ExistenceChecker.isSet((Boolean) o.getImpliedWritedown()))
				.put("floatingAmountProvisions", ExistenceChecker.isSet((FloatingAmountProvisions) o.getFloatingAmountProvisions()))
				.put("additionalFixedPayments", ExistenceChecker.isSet((AdditionalFixedPayments) o.getAdditionalFixedPayments()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingAmountEvents", ValidationType.ONLY_EXISTS, "FloatingAmountEvents", path, "");
		}
		return failure("FloatingAmountEvents", ValidationType.ONLY_EXISTS, "FloatingAmountEvents", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
