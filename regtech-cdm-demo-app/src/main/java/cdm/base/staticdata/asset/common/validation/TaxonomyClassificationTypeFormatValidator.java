package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.TaxonomyClassification;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TaxonomyClassificationTypeFormatValidator implements Validator<TaxonomyClassification> {

	private List<ComparisonResult> getComparisonResults(TaxonomyClassification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("ordinal", o.getOrdinal(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<TaxonomyClassification> validate(RosettaPath path, TaxonomyClassification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TaxonomyClassification", ValidationType.TYPE_FORMAT, "TaxonomyClassification", path, "", error);
		}
		return success("TaxonomyClassification", ValidationType.TYPE_FORMAT, "TaxonomyClassification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TaxonomyClassification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TaxonomyClassification", ValidationType.TYPE_FORMAT, "TaxonomyClassification", path, "", res.getError());
				}
				return success("TaxonomyClassification", ValidationType.TYPE_FORMAT, "TaxonomyClassification", path, "");
			})
			.collect(toList());
	}

}
