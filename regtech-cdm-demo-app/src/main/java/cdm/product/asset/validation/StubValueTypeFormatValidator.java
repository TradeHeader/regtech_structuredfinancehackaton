package cdm.product.asset.validation;

import cdm.product.asset.StubValue;
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

public class StubValueTypeFormatValidator implements Validator<StubValue> {

	private List<ComparisonResult> getComparisonResults(StubValue o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<StubValue> validate(RosettaPath path, StubValue o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StubValue", ValidationType.TYPE_FORMAT, "StubValue", path, "", error);
		}
		return success("StubValue", ValidationType.TYPE_FORMAT, "StubValue", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StubValue o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StubValue", ValidationType.TYPE_FORMAT, "StubValue", path, "", res.getError());
				}
				return success("StubValue", ValidationType.TYPE_FORMAT, "StubValue", path, "");
			})
			.collect(toList());
	}

}
