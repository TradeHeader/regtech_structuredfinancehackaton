package cdm.product.template.validation;

import cdm.product.template.ConstituentWeight;
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

public class ConstituentWeightTypeFormatValidator implements Validator<ConstituentWeight> {

	private List<ComparisonResult> getComparisonResults(ConstituentWeight o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ConstituentWeight> validate(RosettaPath path, ConstituentWeight o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ConstituentWeight", ValidationType.TYPE_FORMAT, "ConstituentWeight", path, "", error);
		}
		return success("ConstituentWeight", ValidationType.TYPE_FORMAT, "ConstituentWeight", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ConstituentWeight o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ConstituentWeight", ValidationType.TYPE_FORMAT, "ConstituentWeight", path, "", res.getError());
				}
				return success("ConstituentWeight", ValidationType.TYPE_FORMAT, "ConstituentWeight", path, "");
			})
			.collect(toList());
	}

}
