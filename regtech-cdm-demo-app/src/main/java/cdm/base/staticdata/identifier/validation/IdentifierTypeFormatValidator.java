package cdm.base.staticdata.identifier.validation;

import cdm.base.staticdata.identifier.Identifier;
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

public class IdentifierTypeFormatValidator implements Validator<Identifier> {

	private List<ComparisonResult> getComparisonResults(Identifier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Identifier> validate(RosettaPath path, Identifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Identifier", ValidationType.TYPE_FORMAT, "Identifier", path, "", error);
		}
		return success("Identifier", ValidationType.TYPE_FORMAT, "Identifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Identifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Identifier", ValidationType.TYPE_FORMAT, "Identifier", path, "", res.getError());
				}
				return success("Identifier", ValidationType.TYPE_FORMAT, "Identifier", path, "");
			})
			.collect(toList());
	}

}
