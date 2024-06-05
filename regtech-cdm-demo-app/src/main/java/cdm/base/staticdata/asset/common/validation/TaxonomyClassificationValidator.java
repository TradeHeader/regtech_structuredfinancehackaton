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
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TaxonomyClassificationValidator implements Validator<TaxonomyClassification> {

	private List<ComparisonResult> getComparisonResults(TaxonomyClassification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("className", (String) o.getClassName() != null ? 1 : 0, 0, 1), 
				checkCardinality("value", (String) o.getValue() != null ? 1 : 0, 1, 1), 
				checkCardinality("description", (String) o.getDescription() != null ? 1 : 0, 0, 1), 
				checkCardinality("ordinal", (Integer) o.getOrdinal() != null ? 1 : 0, 0, 1)
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
			return failure("TaxonomyClassification", ValidationType.CARDINALITY, "TaxonomyClassification", path, "", error);
		}
		return success("TaxonomyClassification", ValidationType.CARDINALITY, "TaxonomyClassification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TaxonomyClassification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TaxonomyClassification", ValidationType.CARDINALITY, "TaxonomyClassification", path, "", res.getError());
				}
				return success("TaxonomyClassification", ValidationType.CARDINALITY, "TaxonomyClassification", path, "");
			})
			.collect(toList());
	}

}
