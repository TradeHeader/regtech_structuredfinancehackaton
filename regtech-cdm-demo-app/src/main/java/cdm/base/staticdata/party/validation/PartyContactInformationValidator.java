package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.PartyContactInformation;
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

public class PartyContactInformationValidator implements Validator<PartyContactInformation> {

	private List<ComparisonResult> getComparisonResults(PartyContactInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("partyReference", (ReferenceWithMetaParty) o.getPartyReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("contactInformation", (ContactInformation) o.getContactInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("additionalInformation", (String) o.getAdditionalInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PartyContactInformation> validate(RosettaPath path, PartyContactInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyContactInformation", ValidationType.CARDINALITY, "PartyContactInformation", path, "", error);
		}
		return success("PartyContactInformation", ValidationType.CARDINALITY, "PartyContactInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyContactInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyContactInformation", ValidationType.CARDINALITY, "PartyContactInformation", path, "", res.getError());
				}
				return success("PartyContactInformation", ValidationType.CARDINALITY, "PartyContactInformation", path, "");
			})
			.collect(toList());
	}

}
