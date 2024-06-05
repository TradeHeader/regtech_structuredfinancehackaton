package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
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

public class AncillaryPartyValidator implements Validator<AncillaryParty> {

	private List<ComparisonResult> getComparisonResults(AncillaryParty o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("role", (AncillaryRoleEnum) o.getRole() != null ? 1 : 0, 1, 1), 
				checkCardinality("partyReference", (List<? extends ReferenceWithMetaParty>) o.getPartyReference() == null ? 0 : ((List<? extends ReferenceWithMetaParty>) o.getPartyReference()).size(), 1, 0), 
				checkCardinality("onBehalfOf", (CounterpartyRoleEnum) o.getOnBehalfOf() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AncillaryParty> validate(RosettaPath path, AncillaryParty o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AncillaryParty", ValidationType.CARDINALITY, "AncillaryParty", path, "", error);
		}
		return success("AncillaryParty", ValidationType.CARDINALITY, "AncillaryParty", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AncillaryParty o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AncillaryParty", ValidationType.CARDINALITY, "AncillaryParty", path, "", res.getError());
				}
				return success("AncillaryParty", ValidationType.CARDINALITY, "AncillaryParty", path, "");
			})
			.collect(toList());
	}

}
