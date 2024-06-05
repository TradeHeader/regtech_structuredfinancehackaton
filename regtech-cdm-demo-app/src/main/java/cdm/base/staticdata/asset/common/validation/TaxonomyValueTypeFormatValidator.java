package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.TaxonomyValue;
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

public class TaxonomyValueTypeFormatValidator implements Validator<TaxonomyValue> {

	private List<ComparisonResult> getComparisonResults(TaxonomyValue o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TaxonomyValue> validate(RosettaPath path, TaxonomyValue o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TaxonomyValue", ValidationType.TYPE_FORMAT, "TaxonomyValue", path, "", error);
		}
		return success("TaxonomyValue", ValidationType.TYPE_FORMAT, "TaxonomyValue", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TaxonomyValue o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TaxonomyValue", ValidationType.TYPE_FORMAT, "TaxonomyValue", path, "", res.getError());
				}
				return success("TaxonomyValue", ValidationType.TYPE_FORMAT, "TaxonomyValue", path, "");
			})
			.collect(toList());
	}

}
