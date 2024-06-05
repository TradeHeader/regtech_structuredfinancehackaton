package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.DebtEconomics;
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

public class DebtEconomicsTypeFormatValidator implements Validator<DebtEconomics> {

	private List<ComparisonResult> getComparisonResults(DebtEconomics o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DebtEconomics> validate(RosettaPath path, DebtEconomics o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DebtEconomics", ValidationType.TYPE_FORMAT, "DebtEconomics", path, "", error);
		}
		return success("DebtEconomics", ValidationType.TYPE_FORMAT, "DebtEconomics", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DebtEconomics o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DebtEconomics", ValidationType.TYPE_FORMAT, "DebtEconomics", path, "", res.getError());
				}
				return success("DebtEconomics", ValidationType.TYPE_FORMAT, "DebtEconomics", path, "");
			})
			.collect(toList());
	}

}
