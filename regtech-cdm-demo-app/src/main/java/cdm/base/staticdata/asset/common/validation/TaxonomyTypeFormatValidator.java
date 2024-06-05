package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Taxonomy;
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

public class TaxonomyTypeFormatValidator implements Validator<Taxonomy> {

	private List<ComparisonResult> getComparisonResults(Taxonomy o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Taxonomy> validate(RosettaPath path, Taxonomy o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Taxonomy", ValidationType.TYPE_FORMAT, "Taxonomy", path, "", error);
		}
		return success("Taxonomy", ValidationType.TYPE_FORMAT, "Taxonomy", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Taxonomy o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Taxonomy", ValidationType.TYPE_FORMAT, "Taxonomy", path, "", res.getError());
				}
				return success("Taxonomy", ValidationType.TYPE_FORMAT, "Taxonomy", path, "");
			})
			.collect(toList());
	}

}
