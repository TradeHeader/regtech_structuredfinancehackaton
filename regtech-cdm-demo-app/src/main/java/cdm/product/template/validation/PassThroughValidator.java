package cdm.product.template.validation;

import cdm.product.template.PassThrough;
import cdm.product.template.PassThroughItem;
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

public class PassThroughValidator implements Validator<PassThrough> {

	private List<ComparisonResult> getComparisonResults(PassThrough o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("passThroughItem", (List<? extends PassThroughItem>) o.getPassThroughItem() == null ? 0 : ((List<? extends PassThroughItem>) o.getPassThroughItem()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<PassThrough> validate(RosettaPath path, PassThrough o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PassThrough", ValidationType.CARDINALITY, "PassThrough", path, "", error);
		}
		return success("PassThrough", ValidationType.CARDINALITY, "PassThrough", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PassThrough o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PassThrough", ValidationType.CARDINALITY, "PassThrough", path, "", res.getError());
				}
				return success("PassThrough", ValidationType.CARDINALITY, "PassThrough", path, "");
			})
			.collect(toList());
	}

}
