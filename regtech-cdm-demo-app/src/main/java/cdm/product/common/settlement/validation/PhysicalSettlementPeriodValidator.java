package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PhysicalSettlementPeriod;
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

public class PhysicalSettlementPeriodValidator implements Validator<PhysicalSettlementPeriod> {

	private List<ComparisonResult> getComparisonResults(PhysicalSettlementPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("businessDaysNotSpecified", (Boolean) o.getBusinessDaysNotSpecified() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessDays", (Integer) o.getBusinessDays() != null ? 1 : 0, 0, 1), 
				checkCardinality("maximumBusinessDays", (Integer) o.getMaximumBusinessDays() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PhysicalSettlementPeriod> validate(RosettaPath path, PhysicalSettlementPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PhysicalSettlementPeriod", ValidationType.CARDINALITY, "PhysicalSettlementPeriod", path, "", error);
		}
		return success("PhysicalSettlementPeriod", ValidationType.CARDINALITY, "PhysicalSettlementPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PhysicalSettlementPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PhysicalSettlementPeriod", ValidationType.CARDINALITY, "PhysicalSettlementPeriod", path, "", res.getError());
				}
				return success("PhysicalSettlementPeriod", ValidationType.CARDINALITY, "PhysicalSettlementPeriod", path, "");
			})
			.collect(toList());
	}

}
