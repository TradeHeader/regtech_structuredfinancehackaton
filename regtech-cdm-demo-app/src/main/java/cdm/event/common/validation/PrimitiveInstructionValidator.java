package cdm.event.common.validation;

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
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PrimitiveInstructionValidator implements Validator<PrimitiveInstruction> {

	private List<ComparisonResult> getComparisonResults(PrimitiveInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("contractFormation", (ContractFormationInstruction) o.getContractFormation() != null ? 1 : 0, 0, 1), 
				checkCardinality("execution", (ExecutionInstruction) o.getExecution() != null ? 1 : 0, 0, 1), 
				checkCardinality("exercise", (ExerciseInstruction) o.getExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("partyChange", (PartyChangeInstruction) o.getPartyChange() != null ? 1 : 0, 0, 1), 
				checkCardinality("quantityChange", (QuantityChangeInstruction) o.getQuantityChange() != null ? 1 : 0, 0, 1), 
				checkCardinality("reset", (ResetInstruction) o.getReset() != null ? 1 : 0, 0, 1), 
				checkCardinality("split", (SplitInstruction) o.getSplit() != null ? 1 : 0, 0, 1), 
				checkCardinality("termsChange", (TermsChangeInstruction) o.getTermsChange() != null ? 1 : 0, 0, 1), 
				checkCardinality("transfer", (TransferInstruction) o.getTransfer() != null ? 1 : 0, 0, 1), 
				checkCardinality("indexTransition", (IndexTransitionInstruction) o.getIndexTransition() != null ? 1 : 0, 0, 1), 
				checkCardinality("stockSplit", (StockSplitInstruction) o.getStockSplit() != null ? 1 : 0, 0, 1), 
				checkCardinality("observation", (ObservationInstruction) o.getObservation() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuation", (ValuationInstruction) o.getValuation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PrimitiveInstruction> validate(RosettaPath path, PrimitiveInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PrimitiveInstruction", ValidationType.CARDINALITY, "PrimitiveInstruction", path, "", error);
		}
		return success("PrimitiveInstruction", ValidationType.CARDINALITY, "PrimitiveInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PrimitiveInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PrimitiveInstruction", ValidationType.CARDINALITY, "PrimitiveInstruction", path, "", res.getError());
				}
				return success("PrimitiveInstruction", ValidationType.CARDINALITY, "PrimitiveInstruction", path, "");
			})
			.collect(toList());
	}

}
