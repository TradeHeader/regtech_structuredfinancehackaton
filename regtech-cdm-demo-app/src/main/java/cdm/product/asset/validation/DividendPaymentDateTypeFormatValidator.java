package cdm.product.asset.validation;

import cdm.product.asset.DividendPaymentDate;
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

public class DividendPaymentDateTypeFormatValidator implements Validator<DividendPaymentDate> {

	private List<ComparisonResult> getComparisonResults(DividendPaymentDate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DividendPaymentDate> validate(RosettaPath path, DividendPaymentDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendPaymentDate", ValidationType.TYPE_FORMAT, "DividendPaymentDate", path, "", error);
		}
		return success("DividendPaymentDate", ValidationType.TYPE_FORMAT, "DividendPaymentDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendPaymentDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendPaymentDate", ValidationType.TYPE_FORMAT, "DividendPaymentDate", path, "", res.getError());
				}
				return success("DividendPaymentDate", ValidationType.TYPE_FORMAT, "DividendPaymentDate", path, "");
			})
			.collect(toList());
	}

}
