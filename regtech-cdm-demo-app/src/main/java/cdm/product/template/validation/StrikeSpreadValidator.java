package cdm.product.template.validation;

import cdm.product.template.OptionStrike;
import cdm.product.template.StrikeSpread;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StrikeSpreadValidator implements Validator<StrikeSpread> {

	private List<ComparisonResult> getComparisonResults(StrikeSpread o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("upperStrike", (OptionStrike) o.getUpperStrike() != null ? 1 : 0, 1, 1), 
				checkCardinality("upperStrikeNumberOfOptions", (BigDecimal) o.getUpperStrikeNumberOfOptions() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<StrikeSpread> validate(RosettaPath path, StrikeSpread o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StrikeSpread", ValidationType.CARDINALITY, "StrikeSpread", path, "", error);
		}
		return success("StrikeSpread", ValidationType.CARDINALITY, "StrikeSpread", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StrikeSpread o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StrikeSpread", ValidationType.CARDINALITY, "StrikeSpread", path, "", res.getError());
				}
				return success("StrikeSpread", ValidationType.CARDINALITY, "StrikeSpread", path, "");
			})
			.collect(toList());
	}

}
