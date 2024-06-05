package cdm.base.math.validation;

import cdm.base.math.DatedValue;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class DatedValueValidator implements Validator<DatedValue> {

	private List<ComparisonResult> getComparisonResults(DatedValue o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("date", (Date) o.getDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("value", (BigDecimal) o.getValue() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<DatedValue> validate(RosettaPath path, DatedValue o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DatedValue", ValidationType.CARDINALITY, "DatedValue", path, "", error);
		}
		return success("DatedValue", ValidationType.CARDINALITY, "DatedValue", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DatedValue o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DatedValue", ValidationType.CARDINALITY, "DatedValue", path, "", res.getError());
				}
				return success("DatedValue", ValidationType.CARDINALITY, "DatedValue", path, "");
			})
			.collect(toList());
	}

}
