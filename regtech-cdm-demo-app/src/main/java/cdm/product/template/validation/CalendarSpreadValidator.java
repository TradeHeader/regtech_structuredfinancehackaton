package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.template.CalendarSpread;
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

public class CalendarSpreadValidator implements Validator<CalendarSpread> {

	private List<ComparisonResult> getComparisonResults(CalendarSpread o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("expirationDateTwo", (AdjustableOrRelativeDate) o.getExpirationDateTwo() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CalendarSpread> validate(RosettaPath path, CalendarSpread o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalendarSpread", ValidationType.CARDINALITY, "CalendarSpread", path, "", error);
		}
		return success("CalendarSpread", ValidationType.CARDINALITY, "CalendarSpread", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalendarSpread o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalendarSpread", ValidationType.CARDINALITY, "CalendarSpread", path, "", res.getError());
				}
				return success("CalendarSpread", ValidationType.CARDINALITY, "CalendarSpread", path, "");
			})
			.collect(toList());
	}

}
