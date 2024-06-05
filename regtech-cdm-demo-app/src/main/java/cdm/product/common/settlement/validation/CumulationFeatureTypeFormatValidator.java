package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.CumulationFeature;
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

public class CumulationFeatureTypeFormatValidator implements Validator<CumulationFeature> {

	private List<ComparisonResult> getComparisonResults(CumulationFeature o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CumulationFeature> validate(RosettaPath path, CumulationFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CumulationFeature", ValidationType.TYPE_FORMAT, "CumulationFeature", path, "", error);
		}
		return success("CumulationFeature", ValidationType.TYPE_FORMAT, "CumulationFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CumulationFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CumulationFeature", ValidationType.TYPE_FORMAT, "CumulationFeature", path, "", res.getError());
				}
				return success("CumulationFeature", ValidationType.TYPE_FORMAT, "CumulationFeature", path, "");
			})
			.collect(toList());
	}

}
