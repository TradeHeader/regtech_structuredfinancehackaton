package cdm.product.asset.validation;

import cdm.product.asset.StubFloatingRate;
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

public class StubFloatingRateTypeFormatValidator implements Validator<StubFloatingRate> {

	private List<ComparisonResult> getComparisonResults(StubFloatingRate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<StubFloatingRate> validate(RosettaPath path, StubFloatingRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StubFloatingRate", ValidationType.TYPE_FORMAT, "StubFloatingRate", path, "", error);
		}
		return success("StubFloatingRate", ValidationType.TYPE_FORMAT, "StubFloatingRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StubFloatingRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StubFloatingRate", ValidationType.TYPE_FORMAT, "StubFloatingRate", path, "", res.getError());
				}
				return success("StubFloatingRate", ValidationType.TYPE_FORMAT, "StubFloatingRate", path, "");
			})
			.collect(toList());
	}

}
