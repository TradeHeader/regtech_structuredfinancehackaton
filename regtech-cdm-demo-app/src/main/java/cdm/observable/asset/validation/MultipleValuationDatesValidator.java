package cdm.observable.asset.validation;

import cdm.observable.asset.MultipleValuationDates;
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

public class MultipleValuationDatesValidator implements Validator<MultipleValuationDates> {

	private List<ComparisonResult> getComparisonResults(MultipleValuationDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("businessDays", (Integer) o.getBusinessDays() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessDaysThereafter", (Integer) o.getBusinessDaysThereafter() != null ? 1 : 0, 0, 1), 
				checkCardinality("numberValuationDates", (Integer) o.getNumberValuationDates() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<MultipleValuationDates> validate(RosettaPath path, MultipleValuationDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MultipleValuationDates", ValidationType.CARDINALITY, "MultipleValuationDates", path, "", error);
		}
		return success("MultipleValuationDates", ValidationType.CARDINALITY, "MultipleValuationDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MultipleValuationDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MultipleValuationDates", ValidationType.CARDINALITY, "MultipleValuationDates", path, "", res.getError());
				}
				return success("MultipleValuationDates", ValidationType.CARDINALITY, "MultipleValuationDates", path, "");
			})
			.collect(toList());
	}

}
