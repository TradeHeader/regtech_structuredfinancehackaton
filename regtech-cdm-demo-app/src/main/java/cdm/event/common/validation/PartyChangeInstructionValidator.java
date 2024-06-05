package cdm.event.common.validation;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.TradeIdentifier;
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

public class PartyChangeInstructionValidator implements Validator<PartyChangeInstruction> {

	private List<ComparisonResult> getComparisonResults(PartyChangeInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("counterparty", (Counterparty) o.getCounterparty() != null ? 1 : 0, 1, 1), 
				checkCardinality("ancillaryParty", (AncillaryParty) o.getAncillaryParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("partyRole", (PartyRole) o.getPartyRole() != null ? 1 : 0, 0, 1), 
				checkCardinality("tradeId", (List<? extends TradeIdentifier>) o.getTradeId() == null ? 0 : ((List<? extends TradeIdentifier>) o.getTradeId()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<PartyChangeInstruction> validate(RosettaPath path, PartyChangeInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyChangeInstruction", ValidationType.CARDINALITY, "PartyChangeInstruction", path, "", error);
		}
		return success("PartyChangeInstruction", ValidationType.CARDINALITY, "PartyChangeInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyChangeInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyChangeInstruction", ValidationType.CARDINALITY, "PartyChangeInstruction", path, "", res.getError());
				}
				return success("PartyChangeInstruction", ValidationType.CARDINALITY, "PartyChangeInstruction", path, "");
			})
			.collect(toList());
	}

}
