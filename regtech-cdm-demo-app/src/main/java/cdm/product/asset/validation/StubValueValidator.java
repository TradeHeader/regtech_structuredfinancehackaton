package cdm.product.asset.validation;

import cdm.observable.asset.Money;
import cdm.product.asset.StubFloatingRate;
import cdm.product.asset.StubValue;
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

public class StubValueValidator implements Validator<StubValue> {

	private List<ComparisonResult> getComparisonResults(StubValue o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("floatingRate", (List<? extends StubFloatingRate>) o.getFloatingRate() == null ? 0 : ((List<? extends StubFloatingRate>) o.getFloatingRate()).size(), 0, 2), 
				checkCardinality("stubRate", (BigDecimal) o.getStubRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("stubAmount", (Money) o.getStubAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<StubValue> validate(RosettaPath path, StubValue o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StubValue", ValidationType.CARDINALITY, "StubValue", path, "", error);
		}
		return success("StubValue", ValidationType.CARDINALITY, "StubValue", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StubValue o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StubValue", ValidationType.CARDINALITY, "StubValue", path, "", res.getError());
				}
				return success("StubValue", ValidationType.CARDINALITY, "StubValue", path, "");
			})
			.collect(toList());
	}

}
