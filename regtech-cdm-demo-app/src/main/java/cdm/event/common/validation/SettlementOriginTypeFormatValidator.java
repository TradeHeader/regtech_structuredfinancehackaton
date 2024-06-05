package cdm.event.common.validation;

import cdm.event.common.SettlementOrigin;
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

public class SettlementOriginTypeFormatValidator implements Validator<SettlementOrigin> {

	private List<ComparisonResult> getComparisonResults(SettlementOrigin o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SettlementOrigin> validate(RosettaPath path, SettlementOrigin o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementOrigin", ValidationType.TYPE_FORMAT, "SettlementOrigin", path, "", error);
		}
		return success("SettlementOrigin", ValidationType.TYPE_FORMAT, "SettlementOrigin", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementOrigin o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementOrigin", ValidationType.TYPE_FORMAT, "SettlementOrigin", path, "", res.getError());
				}
				return success("SettlementOrigin", ValidationType.TYPE_FORMAT, "SettlementOrigin", path, "");
			})
			.collect(toList());
	}

}
