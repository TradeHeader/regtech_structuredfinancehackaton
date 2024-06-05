package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.ProductTaxonomy;
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

public class ProductTaxonomyTypeFormatValidator implements Validator<ProductTaxonomy> {

	private List<ComparisonResult> getComparisonResults(ProductTaxonomy o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ProductTaxonomy", ValidationType.TYPE_FORMAT, "ProductTaxonomy", path, "", error);
		}
		return success("ProductTaxonomy", ValidationType.TYPE_FORMAT, "ProductTaxonomy", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ProductTaxonomy o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ProductTaxonomy", ValidationType.TYPE_FORMAT, "ProductTaxonomy", path, "", res.getError());
				}
				return success("ProductTaxonomy", ValidationType.TYPE_FORMAT, "ProductTaxonomy", path, "");
			})
			.collect(toList());
	}

}
