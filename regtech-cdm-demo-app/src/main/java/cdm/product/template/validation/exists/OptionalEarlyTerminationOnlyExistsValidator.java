package cdm.product.template.validation.exists;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.CalculationAgent;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.OptionalEarlyTermination;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
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

public class OptionalEarlyTerminationOnlyExistsValidator implements ValidatorWithArg<OptionalEarlyTermination, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OptionalEarlyTermination> ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("singlePartyOption", ExistenceChecker.isSet((BuyerSeller) o.getSinglePartyOption()))
				.put("mutualEarlyTermination", ExistenceChecker.isSet((Boolean) o.getMutualEarlyTermination()))
				.put("americanExercise", ExistenceChecker.isSet((AmericanExercise) o.getAmericanExercise()))
				.put("bermudaExercise", ExistenceChecker.isSet((BermudaExercise) o.getBermudaExercise()))
				.put("europeanExercise", ExistenceChecker.isSet((EuropeanExercise) o.getEuropeanExercise()))
				.put("exerciseNotice", ExistenceChecker.isSet((List<? extends ExerciseNotice>) o.getExerciseNotice()))
				.put("followUpConfirmation", ExistenceChecker.isSet((Boolean) o.getFollowUpConfirmation()))
				.put("calculationAgent", ExistenceChecker.isSet((CalculationAgent) o.getCalculationAgent()))
				.put("cashSettlement", ExistenceChecker.isSet((SettlementTerms) o.getCashSettlement()))
				.put("optionalEarlyTerminationAdjustedDates", ExistenceChecker.isSet((OptionalEarlyTerminationAdjustedDates) o.getOptionalEarlyTerminationAdjustedDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OptionalEarlyTermination", ValidationType.ONLY_EXISTS, "OptionalEarlyTermination", path, "");
		}
		return failure("OptionalEarlyTermination", ValidationType.ONLY_EXISTS, "OptionalEarlyTermination", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
