package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.RelatedParty;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class RelatedPartyTypeFormatValidator implements Validator<RelatedParty> {

	private List<ComparisonResult> getComparisonResults(RelatedParty o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<RelatedParty> validate(RosettaPath path, RelatedParty o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RelatedParty", ValidationType.TYPE_FORMAT, "RelatedParty", path, "", error);
		}
		return success("RelatedParty", ValidationType.TYPE_FORMAT, "RelatedParty", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RelatedParty o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RelatedParty", ValidationType.TYPE_FORMAT, "RelatedParty", path, "", res.getError());
				}
				return success("RelatedParty", ValidationType.TYPE_FORMAT, "RelatedParty", path, "");
			})
			.collect(toList());
	}

}
