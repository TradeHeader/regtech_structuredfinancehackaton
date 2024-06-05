package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.SettlementDate;
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

public class SettlementDateTypeFormatValidator implements Validator<SettlementDate> {

	private List<ComparisonResult> getComparisonResults(SettlementDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("cashSettlementBusinessDays", o.getCashSettlementBusinessDays(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<SettlementDate> validate(RosettaPath path, SettlementDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementDate", ValidationType.TYPE_FORMAT, "SettlementDate", path, "", error);
		}
		return success("SettlementDate", ValidationType.TYPE_FORMAT, "SettlementDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementDate", ValidationType.TYPE_FORMAT, "SettlementDate", path, "", res.getError());
				}
				return success("SettlementDate", ValidationType.TYPE_FORMAT, "SettlementDate", path, "");
			})
			.collect(toList());
	}

}
