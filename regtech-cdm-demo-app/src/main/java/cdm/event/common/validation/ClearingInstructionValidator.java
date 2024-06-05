package cdm.event.common.validation;

import cdm.base.staticdata.party.Party;
import cdm.event.common.ClearingInstruction;
import cdm.event.common.TradeState;
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

public class ClearingInstructionValidator implements Validator<ClearingInstruction> {

	private List<ComparisonResult> getComparisonResults(ClearingInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("alphaContract", (TradeState) o.getAlphaContract() != null ? 1 : 0, 1, 1), 
				checkCardinality("clearingParty", (Party) o.getClearingParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("party1", (Party) o.getParty1() != null ? 1 : 0, 1, 1), 
				checkCardinality("party2", (Party) o.getParty2() != null ? 1 : 0, 1, 1), 
				checkCardinality("clearerParty1", (Party) o.getClearerParty1() != null ? 1 : 0, 0, 1), 
				checkCardinality("clearerParty2", (Party) o.getClearerParty2() != null ? 1 : 0, 0, 1), 
				checkCardinality("isOpenOffer", (Boolean) o.getIsOpenOffer() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ClearingInstruction> validate(RosettaPath path, ClearingInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ClearingInstruction", ValidationType.CARDINALITY, "ClearingInstruction", path, "", error);
		}
		return success("ClearingInstruction", ValidationType.CARDINALITY, "ClearingInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ClearingInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ClearingInstruction", ValidationType.CARDINALITY, "ClearingInstruction", path, "", res.getError());
				}
				return success("ClearingInstruction", ValidationType.CARDINALITY, "ClearingInstruction", path, "");
			})
			.collect(toList());
	}

}
