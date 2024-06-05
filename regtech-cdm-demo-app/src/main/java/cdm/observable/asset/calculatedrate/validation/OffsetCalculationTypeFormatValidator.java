package cdm.observable.asset.calculatedrate.validation;

import cdm.observable.asset.calculatedrate.OffsetCalculation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OffsetCalculationTypeFormatValidator implements Validator<OffsetCalculation> {

	private List<ComparisonResult> getComparisonResults(OffsetCalculation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("offsetDays", o.getOffsetDays(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<OffsetCalculation> validate(RosettaPath path, OffsetCalculation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OffsetCalculation", ValidationType.TYPE_FORMAT, "OffsetCalculation", path, "", error);
		}
		return success("OffsetCalculation", ValidationType.TYPE_FORMAT, "OffsetCalculation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OffsetCalculation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OffsetCalculation", ValidationType.TYPE_FORMAT, "OffsetCalculation", path, "", res.getError());
				}
				return success("OffsetCalculation", ValidationType.TYPE_FORMAT, "OffsetCalculation", path, "");
			})
			.collect(toList());
	}

}
