package cdm.product.asset.validation;

import cdm.product.asset.DividendPeriod;
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

public class DividendPeriodTypeFormatValidator implements Validator<DividendPeriod> {

	private List<ComparisonResult> getComparisonResults(DividendPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DividendPeriod> validate(RosettaPath path, DividendPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendPeriod", ValidationType.TYPE_FORMAT, "DividendPeriod", path, "", error);
		}
		return success("DividendPeriod", ValidationType.TYPE_FORMAT, "DividendPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendPeriod", ValidationType.TYPE_FORMAT, "DividendPeriod", path, "", res.getError());
				}
				return success("DividendPeriod", ValidationType.TYPE_FORMAT, "DividendPeriod", path, "");
			})
			.collect(toList());
	}

}
