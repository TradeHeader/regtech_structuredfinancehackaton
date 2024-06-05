package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Bond;
import cdm.base.staticdata.asset.common.ProductIdentifier;
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

public class BondValidator implements Validator<Bond> {

	private List<ComparisonResult> getComparisonResults(Bond o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("productIdentifier", (ProductIdentifier) o.getProductIdentifier() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Bond> validate(RosettaPath path, Bond o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Bond", ValidationType.CARDINALITY, "Bond", path, "", error);
		}
		return success("Bond", ValidationType.CARDINALITY, "Bond", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Bond o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Bond", ValidationType.CARDINALITY, "Bond", path, "", res.getError());
				}
				return success("Bond", ValidationType.CARDINALITY, "Bond", path, "");
			})
			.collect(toList());
	}

}
