package cdm.product.template.validation;

import cdm.product.template.TradableProduct;
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

public class TradableProductTypeFormatValidator implements Validator<TradableProduct> {

	private List<ComparisonResult> getComparisonResults(TradableProduct o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradableProduct", ValidationType.TYPE_FORMAT, "TradableProduct", path, "", error);
		}
		return success("TradableProduct", ValidationType.TYPE_FORMAT, "TradableProduct", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradableProduct o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradableProduct", ValidationType.TYPE_FORMAT, "TradableProduct", path, "", res.getError());
				}
				return success("TradableProduct", ValidationType.TYPE_FORMAT, "TradableProduct", path, "");
			})
			.collect(toList());
	}

}
