package cdm.regulation.validation;

import cdm.regulation.InvstmtDcsnPrsn;
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

public class InvstmtDcsnPrsnTypeFormatValidator implements Validator<InvstmtDcsnPrsn> {

	private List<ComparisonResult> getComparisonResults(InvstmtDcsnPrsn o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InvstmtDcsnPrsn> validate(RosettaPath path, InvstmtDcsnPrsn o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InvstmtDcsnPrsn", ValidationType.TYPE_FORMAT, "InvstmtDcsnPrsn", path, "", error);
		}
		return success("InvstmtDcsnPrsn", ValidationType.TYPE_FORMAT, "InvstmtDcsnPrsn", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InvstmtDcsnPrsn o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InvstmtDcsnPrsn", ValidationType.TYPE_FORMAT, "InvstmtDcsnPrsn", path, "", res.getError());
				}
				return success("InvstmtDcsnPrsn", ValidationType.TYPE_FORMAT, "InvstmtDcsnPrsn", path, "");
			})
			.collect(toList());
	}

}
