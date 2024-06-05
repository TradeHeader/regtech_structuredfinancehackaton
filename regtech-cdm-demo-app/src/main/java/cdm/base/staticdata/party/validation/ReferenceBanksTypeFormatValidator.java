package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.ReferenceBanks;
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

public class ReferenceBanksTypeFormatValidator implements Validator<ReferenceBanks> {

	private List<ComparisonResult> getComparisonResults(ReferenceBanks o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReferenceBanks> validate(RosettaPath path, ReferenceBanks o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferenceBanks", ValidationType.TYPE_FORMAT, "ReferenceBanks", path, "", error);
		}
		return success("ReferenceBanks", ValidationType.TYPE_FORMAT, "ReferenceBanks", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferenceBanks o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferenceBanks", ValidationType.TYPE_FORMAT, "ReferenceBanks", path, "", res.getError());
				}
				return success("ReferenceBanks", ValidationType.TYPE_FORMAT, "ReferenceBanks", path, "");
			})
			.collect(toList());
	}

}
