package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Index;
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

public class IndexTypeFormatValidator implements Validator<Index> {

	private List<ComparisonResult> getComparisonResults(Index o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Index> validate(RosettaPath path, Index o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Index", ValidationType.TYPE_FORMAT, "Index", path, "", error);
		}
		return success("Index", ValidationType.TYPE_FORMAT, "Index", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Index o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Index", ValidationType.TYPE_FORMAT, "Index", path, "", res.getError());
				}
				return success("Index", ValidationType.TYPE_FORMAT, "Index", path, "");
			})
			.collect(toList());
	}

}
