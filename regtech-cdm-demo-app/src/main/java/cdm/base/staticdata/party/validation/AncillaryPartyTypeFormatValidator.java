package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.AncillaryParty;
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

public class AncillaryPartyTypeFormatValidator implements Validator<AncillaryParty> {

	private List<ComparisonResult> getComparisonResults(AncillaryParty o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("AncillaryParty", ValidationType.TYPE_FORMAT, "AncillaryParty", path, "", error);
		}
		return success("AncillaryParty", ValidationType.TYPE_FORMAT, "AncillaryParty", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AncillaryParty o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AncillaryParty", ValidationType.TYPE_FORMAT, "AncillaryParty", path, "", res.getError());
				}
				return success("AncillaryParty", ValidationType.TYPE_FORMAT, "AncillaryParty", path, "");
			})
			.collect(toList());
	}

}
