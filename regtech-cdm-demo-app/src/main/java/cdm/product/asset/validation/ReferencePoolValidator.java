package cdm.product.asset.validation;

import cdm.product.asset.ReferencePool;
import cdm.product.asset.ReferencePoolItem;
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

public class ReferencePoolValidator implements Validator<ReferencePool> {

	private List<ComparisonResult> getComparisonResults(ReferencePool o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referencePoolItem", (List<? extends ReferencePoolItem>) o.getReferencePoolItem() == null ? 0 : ((List<? extends ReferencePoolItem>) o.getReferencePoolItem()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<ReferencePool> validate(RosettaPath path, ReferencePool o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferencePool", ValidationType.CARDINALITY, "ReferencePool", path, "", error);
		}
		return success("ReferencePool", ValidationType.CARDINALITY, "ReferencePool", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferencePool o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferencePool", ValidationType.CARDINALITY, "ReferencePool", path, "", res.getError());
				}
				return success("ReferencePool", ValidationType.CARDINALITY, "ReferencePool", path, "");
			})
			.collect(toList());
	}

}
