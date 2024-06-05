package cdm.event.position.validation;

import cdm.event.position.InventoryRecord;
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

public class InventoryRecordTypeFormatValidator implements Validator<InventoryRecord> {

	private List<ComparisonResult> getComparisonResults(InventoryRecord o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InventoryRecord> validate(RosettaPath path, InventoryRecord o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InventoryRecord", ValidationType.TYPE_FORMAT, "InventoryRecord", path, "", error);
		}
		return success("InventoryRecord", ValidationType.TYPE_FORMAT, "InventoryRecord", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InventoryRecord o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InventoryRecord", ValidationType.TYPE_FORMAT, "InventoryRecord", path, "", res.getError());
				}
				return success("InventoryRecord", ValidationType.TYPE_FORMAT, "InventoryRecord", path, "");
			})
			.collect(toList());
	}

}
