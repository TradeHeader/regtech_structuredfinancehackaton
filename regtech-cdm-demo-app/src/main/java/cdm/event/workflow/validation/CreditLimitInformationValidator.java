package cdm.event.workflow.validation;

import cdm.event.workflow.CreditLimitInformation;
import cdm.event.workflow.LimitApplicableExtended;
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

public class CreditLimitInformationValidator implements Validator<CreditLimitInformation> {

	private List<ComparisonResult> getComparisonResults(CreditLimitInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("limitApplicable", (List<? extends LimitApplicableExtended>) o.getLimitApplicable() == null ? 0 : ((List<? extends LimitApplicableExtended>) o.getLimitApplicable()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<CreditLimitInformation> validate(RosettaPath path, CreditLimitInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditLimitInformation", ValidationType.CARDINALITY, "CreditLimitInformation", path, "", error);
		}
		return success("CreditLimitInformation", ValidationType.CARDINALITY, "CreditLimitInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditLimitInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditLimitInformation", ValidationType.CARDINALITY, "CreditLimitInformation", path, "", res.getError());
				}
				return success("CreditLimitInformation", ValidationType.CARDINALITY, "CreditLimitInformation", path, "");
			})
			.collect(toList());
	}

}
