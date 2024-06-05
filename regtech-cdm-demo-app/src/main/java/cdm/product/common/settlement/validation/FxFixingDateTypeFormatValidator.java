package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.FxFixingDate;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FxFixingDateTypeFormatValidator implements Validator<FxFixingDate> {

	private List<ComparisonResult> getComparisonResults(FxFixingDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<FxFixingDate> validate(RosettaPath path, FxFixingDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxFixingDate", ValidationType.TYPE_FORMAT, "FxFixingDate", path, "", error);
		}
		return success("FxFixingDate", ValidationType.TYPE_FORMAT, "FxFixingDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxFixingDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxFixingDate", ValidationType.TYPE_FORMAT, "FxFixingDate", path, "", res.getError());
				}
				return success("FxFixingDate", ValidationType.TYPE_FORMAT, "FxFixingDate", path, "");
			})
			.collect(toList());
	}

}
