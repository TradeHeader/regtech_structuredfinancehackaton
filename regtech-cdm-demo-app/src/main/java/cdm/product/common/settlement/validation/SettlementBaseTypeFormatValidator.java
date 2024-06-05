package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.SettlementBase;
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

public class SettlementBaseTypeFormatValidator implements Validator<SettlementBase> {

	private List<ComparisonResult> getComparisonResults(SettlementBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SettlementBase> validate(RosettaPath path, SettlementBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementBase", ValidationType.TYPE_FORMAT, "SettlementBase", path, "", error);
		}
		return success("SettlementBase", ValidationType.TYPE_FORMAT, "SettlementBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementBase", ValidationType.TYPE_FORMAT, "SettlementBase", path, "", res.getError());
				}
				return success("SettlementBase", ValidationType.TYPE_FORMAT, "SettlementBase", path, "");
			})
			.collect(toList());
	}

}
