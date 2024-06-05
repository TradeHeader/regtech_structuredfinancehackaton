package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PCDeliverableObligationCharac;
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

public class PCDeliverableObligationCharacValidator implements Validator<PCDeliverableObligationCharac> {

	private List<ComparisonResult> getComparisonResults(PCDeliverableObligationCharac o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("partialCashSettlement", (Boolean) o.getPartialCashSettlement() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PCDeliverableObligationCharac> validate(RosettaPath path, PCDeliverableObligationCharac o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PCDeliverableObligationCharac", ValidationType.CARDINALITY, "PCDeliverableObligationCharac", path, "", error);
		}
		return success("PCDeliverableObligationCharac", ValidationType.CARDINALITY, "PCDeliverableObligationCharac", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PCDeliverableObligationCharac o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PCDeliverableObligationCharac", ValidationType.CARDINALITY, "PCDeliverableObligationCharac", path, "", res.getError());
				}
				return success("PCDeliverableObligationCharac", ValidationType.CARDINALITY, "PCDeliverableObligationCharac", path, "");
			})
			.collect(toList());
	}

}
