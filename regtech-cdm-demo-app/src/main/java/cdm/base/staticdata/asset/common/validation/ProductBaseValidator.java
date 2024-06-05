package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.ProductBase;
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

public class ProductBaseValidator implements Validator<ProductBase> {

	private List<ComparisonResult> getComparisonResults(ProductBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ProductBase> validate(RosettaPath path, ProductBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ProductBase", ValidationType.CARDINALITY, "ProductBase", path, "", error);
		}
		return success("ProductBase", ValidationType.CARDINALITY, "ProductBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ProductBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ProductBase", ValidationType.CARDINALITY, "ProductBase", path, "", res.getError());
				}
				return success("ProductBase", ValidationType.CARDINALITY, "ProductBase", path, "");
			})
			.collect(toList());
	}

}
