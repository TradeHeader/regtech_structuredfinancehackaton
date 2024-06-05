package cdm.base.staticdata.identifier.validation;

import cdm.base.staticdata.identifier.AssignedIdentifier;
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

public class AssignedIdentifierTypeFormatValidator implements Validator<AssignedIdentifier> {

	private List<ComparisonResult> getComparisonResults(AssignedIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("version", o.getVersion(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<AssignedIdentifier> validate(RosettaPath path, AssignedIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssignedIdentifier", ValidationType.TYPE_FORMAT, "AssignedIdentifier", path, "", error);
		}
		return success("AssignedIdentifier", ValidationType.TYPE_FORMAT, "AssignedIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssignedIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssignedIdentifier", ValidationType.TYPE_FORMAT, "AssignedIdentifier", path, "", res.getError());
				}
				return success("AssignedIdentifier", ValidationType.TYPE_FORMAT, "AssignedIdentifier", path, "");
			})
			.collect(toList());
	}

}
