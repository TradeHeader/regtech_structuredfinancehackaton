package cdm.product.common.settlement.validation;

import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.common.settlement.FixedPrice;
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

public class FixedPriceValidator implements Validator<FixedPrice> {

	private List<ComparisonResult> getComparisonResults(FixedPrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("price", (ReferenceWithMetaPriceSchedule) o.getPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FixedPrice> validate(RosettaPath path, FixedPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FixedPrice", ValidationType.CARDINALITY, "FixedPrice", path, "", error);
		}
		return success("FixedPrice", ValidationType.CARDINALITY, "FixedPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FixedPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FixedPrice", ValidationType.CARDINALITY, "FixedPrice", path, "", res.getError());
				}
				return success("FixedPrice", ValidationType.CARDINALITY, "FixedPrice", path, "");
			})
			.collect(toList());
	}

}
