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
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OffsetCalculationValidator implements Validator<OffsetCalculation> {

	private List<ComparisonResult> getComparisonResults(OffsetCalculation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("offsetDays", (Integer) o.getOffsetDays() != null ? 1 : 0, 0, 1)
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
			return failure("OffsetCalculation", ValidationType.CARDINALITY, "OffsetCalculation", path, "", error);
		}
		return success("OffsetCalculation", ValidationType.CARDINALITY, "OffsetCalculation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OffsetCalculation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OffsetCalculation", ValidationType.CARDINALITY, "OffsetCalculation", path, "", res.getError());
				}
				return success("OffsetCalculation", ValidationType.CARDINALITY, "OffsetCalculation", path, "");
			})
			.collect(toList());
	}

}
