package cdm.observable.asset.validation;

import cdm.observable.asset.CashPrice;
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

public class CashPriceTypeFormatValidator implements Validator<CashPrice> {

	private List<ComparisonResult> getComparisonResults(CashPrice o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CashPrice> validate(RosettaPath path, CashPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CashPrice", ValidationType.TYPE_FORMAT, "CashPrice", path, "", error);
		}
		return success("CashPrice", ValidationType.TYPE_FORMAT, "CashPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CashPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CashPrice", ValidationType.TYPE_FORMAT, "CashPrice", path, "", res.getError());
				}
				return success("CashPrice", ValidationType.TYPE_FORMAT, "CashPrice", path, "");
			})
			.collect(toList());
	}

}
