package cdm.observable.asset.validation;

import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.ValuationDates;
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

public class ValuationDatesValidator implements Validator<ValuationDates> {

	private List<ComparisonResult> getComparisonResults(ValuationDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("valuationDatesInitial", (PerformanceValuationDates) o.getValuationDatesInitial() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDatesInterim", (PerformanceValuationDates) o.getValuationDatesInterim() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDatesFinal", (PerformanceValuationDates) o.getValuationDatesFinal() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ValuationDates> validate(RosettaPath path, ValuationDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationDates", ValidationType.CARDINALITY, "ValuationDates", path, "", error);
		}
		return success("ValuationDates", ValidationType.CARDINALITY, "ValuationDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationDates", ValidationType.CARDINALITY, "ValuationDates", path, "", res.getError());
				}
				return success("ValuationDates", ValidationType.CARDINALITY, "ValuationDates", path, "");
			})
			.collect(toList());
	}

}
