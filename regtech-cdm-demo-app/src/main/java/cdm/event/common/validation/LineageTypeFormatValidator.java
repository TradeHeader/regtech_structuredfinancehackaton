package cdm.event.common.validation;

import cdm.event.common.Lineage;
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

public class LineageTypeFormatValidator implements Validator<Lineage> {

	private List<ComparisonResult> getComparisonResults(Lineage o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Lineage> validate(RosettaPath path, Lineage o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Lineage", ValidationType.TYPE_FORMAT, "Lineage", path, "", error);
		}
		return success("Lineage", ValidationType.TYPE_FORMAT, "Lineage", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Lineage o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Lineage", ValidationType.TYPE_FORMAT, "Lineage", path, "", res.getError());
				}
				return success("Lineage", ValidationType.TYPE_FORMAT, "Lineage", path, "");
			})
			.collect(toList());
	}

}
