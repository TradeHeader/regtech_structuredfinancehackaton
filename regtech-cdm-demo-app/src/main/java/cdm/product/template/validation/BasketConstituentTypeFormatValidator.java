package cdm.product.template.validation;

import cdm.product.template.BasketConstituent;
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

public class BasketConstituentTypeFormatValidator implements Validator<BasketConstituent> {

	private List<ComparisonResult> getComparisonResults(BasketConstituent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BasketConstituent> validate(RosettaPath path, BasketConstituent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BasketConstituent", ValidationType.TYPE_FORMAT, "BasketConstituent", path, "", error);
		}
		return success("BasketConstituent", ValidationType.TYPE_FORMAT, "BasketConstituent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BasketConstituent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BasketConstituent", ValidationType.TYPE_FORMAT, "BasketConstituent", path, "", res.getError());
				}
				return success("BasketConstituent", ValidationType.TYPE_FORMAT, "BasketConstituent", path, "");
			})
			.collect(toList());
	}

}
