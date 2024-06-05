package cdm.product.template.validation;

import cdm.product.template.CalendarSpread;
import cdm.product.template.StrategyFeature;
import cdm.product.template.StrikeSpread;
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

public class StrategyFeatureValidator implements Validator<StrategyFeature> {

	private List<ComparisonResult> getComparisonResults(StrategyFeature o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("strikeSpread", (StrikeSpread) o.getStrikeSpread() != null ? 1 : 0, 0, 1), 
				checkCardinality("calendarSpread", (CalendarSpread) o.getCalendarSpread() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<StrategyFeature> validate(RosettaPath path, StrategyFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StrategyFeature", ValidationType.CARDINALITY, "StrategyFeature", path, "", error);
		}
		return success("StrategyFeature", ValidationType.CARDINALITY, "StrategyFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StrategyFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StrategyFeature", ValidationType.CARDINALITY, "StrategyFeature", path, "", res.getError());
				}
				return success("StrategyFeature", ValidationType.CARDINALITY, "StrategyFeature", path, "");
			})
			.collect(toList());
	}

}
