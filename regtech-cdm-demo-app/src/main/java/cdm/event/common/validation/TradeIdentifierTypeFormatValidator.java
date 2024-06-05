package cdm.event.common.validation;

import cdm.event.common.TradeIdentifier;
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

public class TradeIdentifierTypeFormatValidator implements Validator<TradeIdentifier> {

	private List<ComparisonResult> getComparisonResults(TradeIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TradeIdentifier> validate(RosettaPath path, TradeIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradeIdentifier", ValidationType.TYPE_FORMAT, "TradeIdentifier", path, "", error);
		}
		return success("TradeIdentifier", ValidationType.TYPE_FORMAT, "TradeIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradeIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradeIdentifier", ValidationType.TYPE_FORMAT, "TradeIdentifier", path, "", res.getError());
				}
				return success("TradeIdentifier", ValidationType.TYPE_FORMAT, "TradeIdentifier", path, "");
			})
			.collect(toList());
	}

}
