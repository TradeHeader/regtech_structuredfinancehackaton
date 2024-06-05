package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
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

public class PartyRoleValidator implements Validator<PartyRole> {

	private List<ComparisonResult> getComparisonResults(PartyRole o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("partyReference", (ReferenceWithMetaParty) o.getPartyReference() != null ? 1 : 0, 1, 1), 
				checkCardinality("role", (PartyRoleEnum) o.getRole() != null ? 1 : 0, 1, 1), 
				checkCardinality("ownershipPartyReference", (ReferenceWithMetaParty) o.getOwnershipPartyReference() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PartyRole> validate(RosettaPath path, PartyRole o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyRole", ValidationType.CARDINALITY, "PartyRole", path, "", error);
		}
		return success("PartyRole", ValidationType.CARDINALITY, "PartyRole", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyRole o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyRole", ValidationType.CARDINALITY, "PartyRole", path, "", res.getError());
				}
				return success("PartyRole", ValidationType.CARDINALITY, "PartyRole", path, "");
			})
			.collect(toList());
	}

}
