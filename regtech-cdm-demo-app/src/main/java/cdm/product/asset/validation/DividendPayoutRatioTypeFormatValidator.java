package cdm.product.asset.validation;

import cdm.product.asset.DividendPayoutRatio;
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

public class DividendPayoutRatioTypeFormatValidator implements Validator<DividendPayoutRatio> {

	private List<ComparisonResult> getComparisonResults(DividendPayoutRatio o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DividendPayoutRatio> validate(RosettaPath path, DividendPayoutRatio o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendPayoutRatio", ValidationType.TYPE_FORMAT, "DividendPayoutRatio", path, "", error);
		}
		return success("DividendPayoutRatio", ValidationType.TYPE_FORMAT, "DividendPayoutRatio", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendPayoutRatio o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendPayoutRatio", ValidationType.TYPE_FORMAT, "DividendPayoutRatio", path, "", res.getError());
				}
				return success("DividendPayoutRatio", ValidationType.TYPE_FORMAT, "DividendPayoutRatio", path, "");
			})
			.collect(toList());
	}

}
