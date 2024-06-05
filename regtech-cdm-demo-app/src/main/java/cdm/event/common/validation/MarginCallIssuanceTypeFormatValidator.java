package cdm.event.common.validation;

import cdm.event.common.MarginCallIssuance;
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

public class MarginCallIssuanceTypeFormatValidator implements Validator<MarginCallIssuance> {

	private List<ComparisonResult> getComparisonResults(MarginCallIssuance o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MarginCallIssuance> validate(RosettaPath path, MarginCallIssuance o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MarginCallIssuance", ValidationType.TYPE_FORMAT, "MarginCallIssuance", path, "", error);
		}
		return success("MarginCallIssuance", ValidationType.TYPE_FORMAT, "MarginCallIssuance", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MarginCallIssuance o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MarginCallIssuance", ValidationType.TYPE_FORMAT, "MarginCallIssuance", path, "", res.getError());
				}
				return success("MarginCallIssuance", ValidationType.TYPE_FORMAT, "MarginCallIssuance", path, "");
			})
			.collect(toList());
	}

}
