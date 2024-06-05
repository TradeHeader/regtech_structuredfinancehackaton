package cdm.product.template.validation;

import cdm.product.template.Basket;
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

public class BasketTypeFormatValidator implements Validator<Basket> {

	private List<ComparisonResult> getComparisonResults(Basket o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Basket> validate(RosettaPath path, Basket o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Basket", ValidationType.TYPE_FORMAT, "Basket", path, "", error);
		}
		return success("Basket", ValidationType.TYPE_FORMAT, "Basket", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Basket o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Basket", ValidationType.TYPE_FORMAT, "Basket", path, "", res.getError());
				}
				return success("Basket", ValidationType.TYPE_FORMAT, "Basket", path, "");
			})
			.collect(toList());
	}

}
