package cdm.product.template.validation;

import cdm.product.template.ForwardPayout;
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

public class ForwardPayoutTypeFormatValidator implements Validator<ForwardPayout> {

	private List<ComparisonResult> getComparisonResults(ForwardPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ForwardPayout", ValidationType.TYPE_FORMAT, "ForwardPayout", path, "", error);
		}
		return success("ForwardPayout", ValidationType.TYPE_FORMAT, "ForwardPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ForwardPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ForwardPayout", ValidationType.TYPE_FORMAT, "ForwardPayout", path, "", res.getError());
				}
				return success("ForwardPayout", ValidationType.TYPE_FORMAT, "ForwardPayout", path, "");
			})
			.collect(toList());
	}

}
