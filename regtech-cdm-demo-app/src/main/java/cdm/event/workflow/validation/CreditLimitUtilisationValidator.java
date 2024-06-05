package cdm.event.workflow.validation;

import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.CreditLimitUtilisationPosition;
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

public class CreditLimitUtilisationValidator implements Validator<CreditLimitUtilisation> {

	private List<ComparisonResult> getComparisonResults(CreditLimitUtilisation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("executed", (CreditLimitUtilisationPosition) o.getExecuted() != null ? 1 : 0, 0, 1), 
				checkCardinality("pending", (CreditLimitUtilisationPosition) o.getPending() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CreditLimitUtilisation> validate(RosettaPath path, CreditLimitUtilisation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditLimitUtilisation", ValidationType.CARDINALITY, "CreditLimitUtilisation", path, "", error);
		}
		return success("CreditLimitUtilisation", ValidationType.CARDINALITY, "CreditLimitUtilisation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditLimitUtilisation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditLimitUtilisation", ValidationType.CARDINALITY, "CreditLimitUtilisation", path, "", res.getError());
				}
				return success("CreditLimitUtilisation", ValidationType.CARDINALITY, "CreditLimitUtilisation", path, "");
			})
			.collect(toList());
	}

}
