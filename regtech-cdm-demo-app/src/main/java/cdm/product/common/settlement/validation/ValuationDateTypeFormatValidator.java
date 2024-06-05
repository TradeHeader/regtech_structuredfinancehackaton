package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.ValuationDate;
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

public class ValuationDateTypeFormatValidator implements Validator<ValuationDate> {

	private List<ComparisonResult> getComparisonResults(ValuationDate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ValuationDate> validate(RosettaPath path, ValuationDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationDate", ValidationType.TYPE_FORMAT, "ValuationDate", path, "", error);
		}
		return success("ValuationDate", ValidationType.TYPE_FORMAT, "ValuationDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationDate", ValidationType.TYPE_FORMAT, "ValuationDate", path, "", res.getError());
				}
				return success("ValuationDate", ValidationType.TYPE_FORMAT, "ValuationDate", path, "");
			})
			.collect(toList());
	}

}
