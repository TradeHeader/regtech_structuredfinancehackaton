package cdm.product.template.validation;

import cdm.product.template.EarlyTerminationEvent;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
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

public class OptionalEarlyTerminationAdjustedDatesValidator implements Validator<OptionalEarlyTerminationAdjustedDates> {

	private List<ComparisonResult> getComparisonResults(OptionalEarlyTerminationAdjustedDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("earlyTerminationEvent", (List<? extends EarlyTerminationEvent>) o.getEarlyTerminationEvent() == null ? 0 : ((List<? extends EarlyTerminationEvent>) o.getEarlyTerminationEvent()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<OptionalEarlyTerminationAdjustedDates> validate(RosettaPath path, OptionalEarlyTerminationAdjustedDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionalEarlyTerminationAdjustedDates", ValidationType.CARDINALITY, "OptionalEarlyTerminationAdjustedDates", path, "", error);
		}
		return success("OptionalEarlyTerminationAdjustedDates", ValidationType.CARDINALITY, "OptionalEarlyTerminationAdjustedDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionalEarlyTerminationAdjustedDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionalEarlyTerminationAdjustedDates", ValidationType.CARDINALITY, "OptionalEarlyTerminationAdjustedDates", path, "", res.getError());
				}
				return success("OptionalEarlyTerminationAdjustedDates", ValidationType.CARDINALITY, "OptionalEarlyTerminationAdjustedDates", path, "");
			})
			.collect(toList());
	}

}
