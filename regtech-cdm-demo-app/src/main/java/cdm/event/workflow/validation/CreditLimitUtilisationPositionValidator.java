package cdm.event.workflow.validation;

import cdm.event.workflow.CreditLimitUtilisationPosition;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CreditLimitUtilisationPositionValidator implements Validator<CreditLimitUtilisationPosition> {

	private List<ComparisonResult> getComparisonResults(CreditLimitUtilisationPosition o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("shortPosition", (BigDecimal) o.getShortPosition() != null ? 1 : 0, 0, 1), 
				checkCardinality("longPosition", (BigDecimal) o.getLongPosition() != null ? 1 : 0, 0, 1), 
				checkCardinality("global", (BigDecimal) o.getGlobal() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CreditLimitUtilisationPosition> validate(RosettaPath path, CreditLimitUtilisationPosition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditLimitUtilisationPosition", ValidationType.CARDINALITY, "CreditLimitUtilisationPosition", path, "", error);
		}
		return success("CreditLimitUtilisationPosition", ValidationType.CARDINALITY, "CreditLimitUtilisationPosition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditLimitUtilisationPosition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditLimitUtilisationPosition", ValidationType.CARDINALITY, "CreditLimitUtilisationPosition", path, "", res.getError());
				}
				return success("CreditLimitUtilisationPosition", ValidationType.CARDINALITY, "CreditLimitUtilisationPosition", path, "");
			})
			.collect(toList());
	}

}
