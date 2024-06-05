package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.LoanParticipation;
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

public class LoanParticipationTypeFormatValidator implements Validator<LoanParticipation> {

	private List<ComparisonResult> getComparisonResults(LoanParticipation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<LoanParticipation> validate(RosettaPath path, LoanParticipation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LoanParticipation", ValidationType.TYPE_FORMAT, "LoanParticipation", path, "", error);
		}
		return success("LoanParticipation", ValidationType.TYPE_FORMAT, "LoanParticipation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LoanParticipation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LoanParticipation", ValidationType.TYPE_FORMAT, "LoanParticipation", path, "", res.getError());
				}
				return success("LoanParticipation", ValidationType.TYPE_FORMAT, "LoanParticipation", path, "");
			})
			.collect(toList());
	}

}
