package cdm.regulation.validation;

import cdm.regulation.AcctOwnr;
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

public class AcctOwnrTypeFormatValidator implements Validator<AcctOwnr> {

	private List<ComparisonResult> getComparisonResults(AcctOwnr o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AcctOwnr> validate(RosettaPath path, AcctOwnr o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AcctOwnr", ValidationType.TYPE_FORMAT, "AcctOwnr", path, "", error);
		}
		return success("AcctOwnr", ValidationType.TYPE_FORMAT, "AcctOwnr", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AcctOwnr o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AcctOwnr", ValidationType.TYPE_FORMAT, "AcctOwnr", path, "", res.getError());
				}
				return success("AcctOwnr", ValidationType.TYPE_FORMAT, "AcctOwnr", path, "");
			})
			.collect(toList());
	}

}
