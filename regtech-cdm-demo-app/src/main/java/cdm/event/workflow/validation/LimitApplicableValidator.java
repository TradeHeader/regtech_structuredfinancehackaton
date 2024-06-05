package cdm.event.workflow.validation;

import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.LimitApplicable;
import cdm.event.workflow.Velocity;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class LimitApplicableValidator implements Validator<LimitApplicable> {

	private List<ComparisonResult> getComparisonResults(LimitApplicable o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("limitType", (FieldWithMetaCreditLimitTypeEnum) o.getLimitType() != null ? 1 : 0, 0, 1), 
				checkCardinality("clipSize", (Integer) o.getClipSize() != null ? 1 : 0, 0, 1), 
				checkCardinality("amountUtilized", (BigDecimal) o.getAmountUtilized() != null ? 1 : 0, 0, 1), 
				checkCardinality("utilization", (CreditLimitUtilisation) o.getUtilization() != null ? 1 : 0, 0, 1), 
				checkCardinality("amountRemaining", (BigDecimal) o.getAmountRemaining() != null ? 1 : 0, 0, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("velocity", (Velocity) o.getVelocity() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<LimitApplicable> validate(RosettaPath path, LimitApplicable o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LimitApplicable", ValidationType.CARDINALITY, "LimitApplicable", path, "", error);
		}
		return success("LimitApplicable", ValidationType.CARDINALITY, "LimitApplicable", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LimitApplicable o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LimitApplicable", ValidationType.CARDINALITY, "LimitApplicable", path, "", res.getError());
				}
				return success("LimitApplicable", ValidationType.CARDINALITY, "LimitApplicable", path, "");
			})
			.collect(toList());
	}

}
