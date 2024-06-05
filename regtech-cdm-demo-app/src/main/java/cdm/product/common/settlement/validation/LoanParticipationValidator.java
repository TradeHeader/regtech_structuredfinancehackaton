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
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class LoanParticipationValidator implements Validator<LoanParticipation> {

	private List<ComparisonResult> getComparisonResults(LoanParticipation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("partialCashSettlement", (Boolean) o.getPartialCashSettlement() != null ? 1 : 0, 0, 1), 
				checkCardinality("qualifyingParticipationSeller", (String) o.getQualifyingParticipationSeller() != null ? 1 : 0, 0, 1)
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
			return failure("LoanParticipation", ValidationType.CARDINALITY, "LoanParticipation", path, "", error);
		}
		return success("LoanParticipation", ValidationType.CARDINALITY, "LoanParticipation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LoanParticipation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LoanParticipation", ValidationType.CARDINALITY, "LoanParticipation", path, "", res.getError());
				}
				return success("LoanParticipation", ValidationType.CARDINALITY, "LoanParticipation", path, "");
			})
			.collect(toList());
	}

}
