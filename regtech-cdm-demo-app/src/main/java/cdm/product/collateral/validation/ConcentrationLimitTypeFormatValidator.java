package cdm.product.collateral.validation;

import cdm.product.collateral.ConcentrationLimit;
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

public class ConcentrationLimitTypeFormatValidator implements Validator<ConcentrationLimit> {

	private List<ComparisonResult> getComparisonResults(ConcentrationLimit o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ConcentrationLimit", ValidationType.TYPE_FORMAT, "ConcentrationLimit", path, "", error);
		}
		return success("ConcentrationLimit", ValidationType.TYPE_FORMAT, "ConcentrationLimit", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ConcentrationLimit o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ConcentrationLimit", ValidationType.TYPE_FORMAT, "ConcentrationLimit", path, "", res.getError());
				}
				return success("ConcentrationLimit", ValidationType.TYPE_FORMAT, "ConcentrationLimit", path, "");
			})
			.collect(toList());
	}

}
