package cdm.product.asset.validation;

import cdm.product.asset.BondReference;
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

public class BondReferenceTypeFormatValidator implements Validator<BondReference> {

	private List<ComparisonResult> getComparisonResults(BondReference o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BondReference> validate(RosettaPath path, BondReference o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BondReference", ValidationType.TYPE_FORMAT, "BondReference", path, "", error);
		}
		return success("BondReference", ValidationType.TYPE_FORMAT, "BondReference", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondReference o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondReference", ValidationType.TYPE_FORMAT, "BondReference", path, "", res.getError());
				}
				return success("BondReference", ValidationType.TYPE_FORMAT, "BondReference", path, "");
			})
			.collect(toList());
	}

}
