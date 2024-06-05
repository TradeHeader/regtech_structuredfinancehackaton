package cdm.product.asset.validation;

import cdm.product.asset.BoundedCorrelation;
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

public class BoundedCorrelationTypeFormatValidator implements Validator<BoundedCorrelation> {

	private List<ComparisonResult> getComparisonResults(BoundedCorrelation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BoundedCorrelation> validate(RosettaPath path, BoundedCorrelation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BoundedCorrelation", ValidationType.TYPE_FORMAT, "BoundedCorrelation", path, "", error);
		}
		return success("BoundedCorrelation", ValidationType.TYPE_FORMAT, "BoundedCorrelation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BoundedCorrelation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BoundedCorrelation", ValidationType.TYPE_FORMAT, "BoundedCorrelation", path, "", res.getError());
				}
				return success("BoundedCorrelation", ValidationType.TYPE_FORMAT, "BoundedCorrelation", path, "");
			})
			.collect(toList());
	}

}
