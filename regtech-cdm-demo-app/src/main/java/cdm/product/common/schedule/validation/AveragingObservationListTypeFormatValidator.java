package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.AveragingObservationList;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AveragingObservationListTypeFormatValidator implements Validator<AveragingObservationList> {

	private List<ComparisonResult> getComparisonResults(AveragingObservationList o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AveragingObservationList> validate(RosettaPath path, AveragingObservationList o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingObservationList", ValidationType.TYPE_FORMAT, "AveragingObservationList", path, "", error);
		}
		return success("AveragingObservationList", ValidationType.TYPE_FORMAT, "AveragingObservationList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingObservationList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingObservationList", ValidationType.TYPE_FORMAT, "AveragingObservationList", path, "", res.getError());
				}
				return success("AveragingObservationList", ValidationType.TYPE_FORMAT, "AveragingObservationList", path, "");
			})
			.collect(toList());
	}

}
