package cdm.event.common.validation;

import cdm.event.common.Reset;
import cdm.observable.asset.Price;
import cdm.observable.event.metafields.ReferenceWithMetaObservation;
import cdm.product.template.AveragingCalculation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class ResetValidator implements Validator<Reset> {

	private List<ComparisonResult> getComparisonResults(Reset o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("resetValue", (Price) o.getResetValue() != null ? 1 : 0, 1, 1), 
				checkCardinality("resetDate", (Date) o.getResetDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("rateRecordDate", (Date) o.getRateRecordDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("observations", (List<? extends ReferenceWithMetaObservation>) o.getObservations() == null ? 0 : ((List<? extends ReferenceWithMetaObservation>) o.getObservations()).size(), 1, 0), 
				checkCardinality("averagingMethodology", (AveragingCalculation) o.getAveragingMethodology() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Reset> validate(RosettaPath path, Reset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Reset", ValidationType.CARDINALITY, "Reset", path, "", error);
		}
		return success("Reset", ValidationType.CARDINALITY, "Reset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Reset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Reset", ValidationType.CARDINALITY, "Reset", path, "", res.getError());
				}
				return success("Reset", ValidationType.CARDINALITY, "Reset", path, "");
			})
			.collect(toList());
	}

}
