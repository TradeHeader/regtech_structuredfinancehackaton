package cdm.product.collateral.validation;

import cdm.product.collateral.AgencyRatingCriteria;
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

public class AgencyRatingCriteriaTypeFormatValidator implements Validator<AgencyRatingCriteria> {

	private List<ComparisonResult> getComparisonResults(AgencyRatingCriteria o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AgencyRatingCriteria> validate(RosettaPath path, AgencyRatingCriteria o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AgencyRatingCriteria", ValidationType.TYPE_FORMAT, "AgencyRatingCriteria", path, "", error);
		}
		return success("AgencyRatingCriteria", ValidationType.TYPE_FORMAT, "AgencyRatingCriteria", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AgencyRatingCriteria o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AgencyRatingCriteria", ValidationType.TYPE_FORMAT, "AgencyRatingCriteria", path, "", res.getError());
				}
				return success("AgencyRatingCriteria", ValidationType.TYPE_FORMAT, "AgencyRatingCriteria", path, "");
			})
			.collect(toList());
	}

}
