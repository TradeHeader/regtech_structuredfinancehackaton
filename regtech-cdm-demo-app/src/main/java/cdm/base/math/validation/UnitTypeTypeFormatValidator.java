package cdm.base.math.validation;

import cdm.base.math.UnitType;
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

public class UnitTypeTypeFormatValidator implements Validator<UnitType> {

	private List<ComparisonResult> getComparisonResults(UnitType o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<UnitType> validate(RosettaPath path, UnitType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UnitType", ValidationType.TYPE_FORMAT, "UnitType", path, "", error);
		}
		return success("UnitType", ValidationType.TYPE_FORMAT, "UnitType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UnitType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UnitType", ValidationType.TYPE_FORMAT, "UnitType", path, "", res.getError());
				}
				return success("UnitType", ValidationType.TYPE_FORMAT, "UnitType", path, "");
			})
			.collect(toList());
	}

}
