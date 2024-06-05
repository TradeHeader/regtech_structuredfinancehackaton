package cdm.event.common.validation;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.Confirmation;
import cdm.event.common.ConfirmationStatusEnum;
import cdm.event.common.Lineage;
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

public class ConfirmationValidator implements Validator<Confirmation> {

	private List<ComparisonResult> getComparisonResults(Confirmation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (List<? extends Identifier>) o.getIdentifier() == null ? 0 : ((List<? extends Identifier>) o.getIdentifier()).size(), 1, 0), 
				checkCardinality("party", (List<? extends Party>) o.getParty() == null ? 0 : ((List<? extends Party>) o.getParty()).size(), 1, 0), 
				checkCardinality("partyRole", (List<? extends PartyRole>) o.getPartyRole() == null ? 0 : ((List<? extends PartyRole>) o.getPartyRole()).size(), 1, 0), 
				checkCardinality("lineage", (Lineage) o.getLineage() != null ? 1 : 0, 0, 1), 
				checkCardinality("status", (ConfirmationStatusEnum) o.getStatus() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Confirmation> validate(RosettaPath path, Confirmation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Confirmation", ValidationType.CARDINALITY, "Confirmation", path, "", error);
		}
		return success("Confirmation", ValidationType.CARDINALITY, "Confirmation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Confirmation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Confirmation", ValidationType.CARDINALITY, "Confirmation", path, "", res.getError());
				}
				return success("Confirmation", ValidationType.CARDINALITY, "Confirmation", path, "");
			})
			.collect(toList());
	}

}
