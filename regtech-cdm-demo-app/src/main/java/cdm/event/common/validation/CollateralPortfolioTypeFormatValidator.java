package cdm.event.common.validation;

import cdm.event.common.CollateralPortfolio;
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

public class CollateralPortfolioTypeFormatValidator implements Validator<CollateralPortfolio> {

	private List<ComparisonResult> getComparisonResults(CollateralPortfolio o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralPortfolio> validate(RosettaPath path, CollateralPortfolio o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralPortfolio", ValidationType.TYPE_FORMAT, "CollateralPortfolio", path, "", error);
		}
		return success("CollateralPortfolio", ValidationType.TYPE_FORMAT, "CollateralPortfolio", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralPortfolio o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralPortfolio", ValidationType.TYPE_FORMAT, "CollateralPortfolio", path, "", res.getError());
				}
				return success("CollateralPortfolio", ValidationType.TYPE_FORMAT, "CollateralPortfolio", path, "");
			})
			.collect(toList());
	}

}
