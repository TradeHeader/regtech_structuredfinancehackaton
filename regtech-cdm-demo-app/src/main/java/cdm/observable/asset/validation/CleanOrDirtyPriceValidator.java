package cdm.observable.asset.validation;

import cdm.observable.asset.CleanOrDirtyPrice;
import cdm.observable.asset.CleanPrice;
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

public class CleanOrDirtyPriceValidator implements Validator<CleanOrDirtyPrice> {

	private List<ComparisonResult> getComparisonResults(CleanOrDirtyPrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cleanPrice", (CleanPrice) o.getCleanPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("dirtyPrice", (BigDecimal) o.getDirtyPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CleanOrDirtyPrice> validate(RosettaPath path, CleanOrDirtyPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CleanOrDirtyPrice", ValidationType.CARDINALITY, "CleanOrDirtyPrice", path, "", error);
		}
		return success("CleanOrDirtyPrice", ValidationType.CARDINALITY, "CleanOrDirtyPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CleanOrDirtyPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CleanOrDirtyPrice", ValidationType.CARDINALITY, "CleanOrDirtyPrice", path, "", res.getError());
				}
				return success("CleanOrDirtyPrice", ValidationType.CARDINALITY, "CleanOrDirtyPrice", path, "");
			})
			.collect(toList());
	}

}
