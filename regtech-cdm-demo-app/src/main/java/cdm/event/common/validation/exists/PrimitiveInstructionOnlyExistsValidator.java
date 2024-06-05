package cdm.event.common.validation.exists;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.ResetInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.StockSplitInstruction;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TransferInstruction;
import cdm.event.common.ValuationInstruction;
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

public class PrimitiveInstructionOnlyExistsValidator implements ValidatorWithArg<PrimitiveInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PrimitiveInstruction> ValidationResult<PrimitiveInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("contractFormation", ExistenceChecker.isSet((ContractFormationInstruction) o.getContractFormation()))
				.put("execution", ExistenceChecker.isSet((ExecutionInstruction) o.getExecution()))
				.put("exercise", ExistenceChecker.isSet((ExerciseInstruction) o.getExercise()))
				.put("partyChange", ExistenceChecker.isSet((PartyChangeInstruction) o.getPartyChange()))
				.put("quantityChange", ExistenceChecker.isSet((QuantityChangeInstruction) o.getQuantityChange()))
				.put("reset", ExistenceChecker.isSet((ResetInstruction) o.getReset()))
				.put("split", ExistenceChecker.isSet((SplitInstruction) o.getSplit()))
				.put("termsChange", ExistenceChecker.isSet((TermsChangeInstruction) o.getTermsChange()))
				.put("transfer", ExistenceChecker.isSet((TransferInstruction) o.getTransfer()))
				.put("indexTransition", ExistenceChecker.isSet((IndexTransitionInstruction) o.getIndexTransition()))
				.put("stockSplit", ExistenceChecker.isSet((StockSplitInstruction) o.getStockSplit()))
				.put("observation", ExistenceChecker.isSet((ObservationInstruction) o.getObservation()))
				.put("valuation", ExistenceChecker.isSet((ValuationInstruction) o.getValuation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PrimitiveInstruction", ValidationType.ONLY_EXISTS, "PrimitiveInstruction", path, "");
		}
		return failure("PrimitiveInstruction", ValidationType.ONLY_EXISTS, "PrimitiveInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
