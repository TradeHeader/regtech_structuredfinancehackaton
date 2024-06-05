package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.IndexAdjustmentEvents;
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

public class IndexAdjustmentEventsTypeFormatValidator implements Validator<IndexAdjustmentEvents> {

	private List<ComparisonResult> getComparisonResults(IndexAdjustmentEvents o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<IndexAdjustmentEvents> validate(RosettaPath path, IndexAdjustmentEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IndexAdjustmentEvents", ValidationType.TYPE_FORMAT, "IndexAdjustmentEvents", path, "", error);
		}
		return success("IndexAdjustmentEvents", ValidationType.TYPE_FORMAT, "IndexAdjustmentEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IndexAdjustmentEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IndexAdjustmentEvents", ValidationType.TYPE_FORMAT, "IndexAdjustmentEvents", path, "", res.getError());
				}
				return success("IndexAdjustmentEvents", ValidationType.TYPE_FORMAT, "IndexAdjustmentEvents", path, "");
			})
			.collect(toList());
	}

}
