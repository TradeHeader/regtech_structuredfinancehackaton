package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.EquityCorporateEvents;
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

public class EquityCorporateEventsTypeFormatValidator implements Validator<EquityCorporateEvents> {

	private List<ComparisonResult> getComparisonResults(EquityCorporateEvents o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EquityCorporateEvents> validate(RosettaPath path, EquityCorporateEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquityCorporateEvents", ValidationType.TYPE_FORMAT, "EquityCorporateEvents", path, "", error);
		}
		return success("EquityCorporateEvents", ValidationType.TYPE_FORMAT, "EquityCorporateEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquityCorporateEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquityCorporateEvents", ValidationType.TYPE_FORMAT, "EquityCorporateEvents", path, "", res.getError());
				}
				return success("EquityCorporateEvents", ValidationType.TYPE_FORMAT, "EquityCorporateEvents", path, "");
			})
			.collect(toList());
	}

}
