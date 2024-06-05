package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.StubPeriod;
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

public class StubPeriodTypeFormatValidator implements Validator<StubPeriod> {

	private List<ComparisonResult> getComparisonResults(StubPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<StubPeriod> validate(RosettaPath path, StubPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StubPeriod", ValidationType.TYPE_FORMAT, "StubPeriod", path, "", error);
		}
		return success("StubPeriod", ValidationType.TYPE_FORMAT, "StubPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StubPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StubPeriod", ValidationType.TYPE_FORMAT, "StubPeriod", path, "", res.getError());
				}
				return success("StubPeriod", ValidationType.TYPE_FORMAT, "StubPeriod", path, "");
			})
			.collect(toList());
	}

}
