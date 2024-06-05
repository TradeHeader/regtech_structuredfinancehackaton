package cdm.event.common.validation;

import cdm.event.common.CallTypeEnum;
import cdm.event.common.MarginCallInstructionType;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MarginCallInstructionTypeValidator implements Validator<MarginCallInstructionType> {

	private List<ComparisonResult> getComparisonResults(MarginCallInstructionType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("callType", (CallTypeEnum) o.getCallType() != null ? 1 : 0, 1, 1), 
				checkCardinality("visibilityIndicator", (Boolean) o.getVisibilityIndicator() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<MarginCallInstructionType> validate(RosettaPath path, MarginCallInstructionType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MarginCallInstructionType", ValidationType.CARDINALITY, "MarginCallInstructionType", path, "", error);
		}
		return success("MarginCallInstructionType", ValidationType.CARDINALITY, "MarginCallInstructionType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MarginCallInstructionType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MarginCallInstructionType", ValidationType.CARDINALITY, "MarginCallInstructionType", path, "", res.getError());
				}
				return success("MarginCallInstructionType", ValidationType.CARDINALITY, "MarginCallInstructionType", path, "");
			})
			.collect(toList());
	}

}
