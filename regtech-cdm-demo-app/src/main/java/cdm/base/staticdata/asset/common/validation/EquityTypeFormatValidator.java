package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Equity;
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

public class EquityTypeFormatValidator implements Validator<Equity> {

	private List<ComparisonResult> getComparisonResults(Equity o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Equity> validate(RosettaPath path, Equity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Equity", ValidationType.TYPE_FORMAT, "Equity", path, "", error);
		}
		return success("Equity", ValidationType.TYPE_FORMAT, "Equity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Equity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Equity", ValidationType.TYPE_FORMAT, "Equity", path, "", res.getError());
				}
				return success("Equity", ValidationType.TYPE_FORMAT, "Equity", path, "");
			})
			.collect(toList());
	}

}
