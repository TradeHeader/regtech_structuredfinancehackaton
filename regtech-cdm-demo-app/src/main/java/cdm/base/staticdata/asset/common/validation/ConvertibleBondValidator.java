package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.ConvertibleBond;
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

public class ConvertibleBondValidator implements Validator<ConvertibleBond> {

	private List<ComparisonResult> getComparisonResults(ConvertibleBond o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("productIdentifier", (ProductIdentifier) o.getProductIdentifier() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ConvertibleBond> validate(RosettaPath path, ConvertibleBond o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ConvertibleBond", ValidationType.CARDINALITY, "ConvertibleBond", path, "", error);
		}
		return success("ConvertibleBond", ValidationType.CARDINALITY, "ConvertibleBond", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ConvertibleBond o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ConvertibleBond", ValidationType.CARDINALITY, "ConvertibleBond", path, "", res.getError());
				}
				return success("ConvertibleBond", ValidationType.CARDINALITY, "ConvertibleBond", path, "");
			})
			.collect(toList());
	}

}
