package cdm.product.collateral.validation;

import cdm.base.staticdata.party.PartyContactInformation;
import cdm.product.collateral.ContactElection;
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

public class ContactElectionValidator implements Validator<ContactElection> {

	private List<ComparisonResult> getComparisonResults(ContactElection o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("partyElection", (List<? extends PartyContactInformation>) o.getPartyElection() == null ? 0 : ((List<? extends PartyContactInformation>) o.getPartyElection()).size(), 2, 2)
			);
	}

	@Override
	public ValidationResult<ContactElection> validate(RosettaPath path, ContactElection o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContactElection", ValidationType.CARDINALITY, "ContactElection", path, "", error);
		}
		return success("ContactElection", ValidationType.CARDINALITY, "ContactElection", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContactElection o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContactElection", ValidationType.CARDINALITY, "ContactElection", path, "", res.getError());
				}
				return success("ContactElection", ValidationType.CARDINALITY, "ContactElection", path, "");
			})
			.collect(toList());
	}

}
