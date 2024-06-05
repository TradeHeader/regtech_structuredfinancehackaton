package cdm.observable.event.validation;

import cdm.base.math.AveragingCalculationMethodEnum;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.event.DeterminationMethodology;
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

public class DeterminationMethodologyValidator implements Validator<DeterminationMethodology> {

	private List<ComparisonResult> getComparisonResults(DeterminationMethodology o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("determinationMethod", (DeterminationMethodEnum) o.getDeterminationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingMethod", (AveragingCalculationMethodEnum) o.getAveragingMethod() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DeterminationMethodology> validate(RosettaPath path, DeterminationMethodology o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeterminationMethodology", ValidationType.CARDINALITY, "DeterminationMethodology", path, "", error);
		}
		return success("DeterminationMethodology", ValidationType.CARDINALITY, "DeterminationMethodology", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeterminationMethodology o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeterminationMethodology", ValidationType.CARDINALITY, "DeterminationMethodology", path, "", res.getError());
				}
				return success("DeterminationMethodology", ValidationType.CARDINALITY, "DeterminationMethodology", path, "");
			})
			.collect(toList());
	}

}
