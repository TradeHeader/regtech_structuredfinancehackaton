package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ObservationDate;
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

public class ObservationDateValidator implements Validator<ObservationDate> {

	private List<ComparisonResult> getComparisonResults(ObservationDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("unadjustedDate", (Date) o.getUnadjustedDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedDate", (Date) o.getAdjustedDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("weight", (BigDecimal) o.getWeight() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationReference", (String) o.getObservationReference() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ObservationDate> validate(RosettaPath path, ObservationDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationDate", ValidationType.CARDINALITY, "ObservationDate", path, "", error);
		}
		return success("ObservationDate", ValidationType.CARDINALITY, "ObservationDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationDate", ValidationType.CARDINALITY, "ObservationDate", path, "", res.getError());
				}
				return success("ObservationDate", ValidationType.CARDINALITY, "ObservationDate", path, "");
			})
			.collect(toList());
	}

}
