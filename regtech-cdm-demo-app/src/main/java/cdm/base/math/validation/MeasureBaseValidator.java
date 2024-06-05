package cdm.base.math.validation;

import cdm.base.math.MeasureBase;
import cdm.base.math.UnitType;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MeasureBaseValidator implements Validator<MeasureBase> {

	private List<ComparisonResult> getComparisonResults(MeasureBase o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("value", (BigDecimal) o.getValue() != null ? 1 : 0, 0, 1), 
				checkCardinality("unit", (UnitType) o.getUnit() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<MeasureBase> validate(RosettaPath path, MeasureBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MeasureBase", ValidationType.CARDINALITY, "MeasureBase", path, "", error);
		}
		return success("MeasureBase", ValidationType.CARDINALITY, "MeasureBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MeasureBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MeasureBase", ValidationType.CARDINALITY, "MeasureBase", path, "", res.getError());
				}
				return success("MeasureBase", ValidationType.CARDINALITY, "MeasureBase", path, "");
			})
			.collect(toList());
	}

}
