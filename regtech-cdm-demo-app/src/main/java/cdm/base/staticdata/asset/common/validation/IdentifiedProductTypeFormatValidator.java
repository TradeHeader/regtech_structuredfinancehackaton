package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.IdentifiedProduct;
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

public class IdentifiedProductTypeFormatValidator implements Validator<IdentifiedProduct> {

	private List<ComparisonResult> getComparisonResults(IdentifiedProduct o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<IdentifiedProduct> validate(RosettaPath path, IdentifiedProduct o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IdentifiedProduct", ValidationType.TYPE_FORMAT, "IdentifiedProduct", path, "", error);
		}
		return success("IdentifiedProduct", ValidationType.TYPE_FORMAT, "IdentifiedProduct", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IdentifiedProduct o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IdentifiedProduct", ValidationType.TYPE_FORMAT, "IdentifiedProduct", path, "", res.getError());
				}
				return success("IdentifiedProduct", ValidationType.TYPE_FORMAT, "IdentifiedProduct", path, "");
			})
			.collect(toList());
	}

}
