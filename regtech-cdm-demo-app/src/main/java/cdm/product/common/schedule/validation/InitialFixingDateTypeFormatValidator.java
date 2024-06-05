package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.InitialFixingDate;
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

public class InitialFixingDateTypeFormatValidator implements Validator<InitialFixingDate> {

	private List<ComparisonResult> getComparisonResults(InitialFixingDate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InitialFixingDate> validate(RosettaPath path, InitialFixingDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InitialFixingDate", ValidationType.TYPE_FORMAT, "InitialFixingDate", path, "", error);
		}
		return success("InitialFixingDate", ValidationType.TYPE_FORMAT, "InitialFixingDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InitialFixingDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InitialFixingDate", ValidationType.TYPE_FORMAT, "InitialFixingDate", path, "", res.getError());
				}
				return success("InitialFixingDate", ValidationType.TYPE_FORMAT, "InitialFixingDate", path, "");
			})
			.collect(toList());
	}

}
