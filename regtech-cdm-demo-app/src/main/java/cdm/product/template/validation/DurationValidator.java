package cdm.product.template.validation;

import cdm.product.template.Duration;
import cdm.product.template.DurationTypeEnum;
import cdm.product.template.EvergreenProvision;
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

public class DurationValidator implements Validator<Duration> {

	private List<ComparisonResult> getComparisonResults(Duration o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("durationType", (DurationTypeEnum) o.getDurationType() != null ? 1 : 0, 1, 1), 
				checkCardinality("evergreenProvision", (EvergreenProvision) o.getEvergreenProvision() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Duration> validate(RosettaPath path, Duration o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Duration", ValidationType.CARDINALITY, "Duration", path, "", error);
		}
		return success("Duration", ValidationType.CARDINALITY, "Duration", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Duration o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Duration", ValidationType.CARDINALITY, "Duration", path, "", res.getError());
				}
				return success("Duration", ValidationType.CARDINALITY, "Duration", path, "");
			})
			.collect(toList());
	}

}
