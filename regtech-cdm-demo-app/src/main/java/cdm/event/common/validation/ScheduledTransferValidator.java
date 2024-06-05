package cdm.event.common.validation;

import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.ScheduledTransfer;
import cdm.product.common.settlement.ScheduledTransferEnum;
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

public class ScheduledTransferValidator implements Validator<ScheduledTransfer> {

	private List<ComparisonResult> getComparisonResults(ScheduledTransfer o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("transferType", (ScheduledTransferEnum) o.getTransferType() != null ? 1 : 0, 1, 1), 
				checkCardinality("corporateActionTransferType", (CorporateActionTypeEnum) o.getCorporateActionTransferType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ScheduledTransfer> validate(RosettaPath path, ScheduledTransfer o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ScheduledTransfer", ValidationType.CARDINALITY, "ScheduledTransfer", path, "", error);
		}
		return success("ScheduledTransfer", ValidationType.CARDINALITY, "ScheduledTransfer", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ScheduledTransfer o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ScheduledTransfer", ValidationType.CARDINALITY, "ScheduledTransfer", path, "", res.getError());
				}
				return success("ScheduledTransfer", ValidationType.CARDINALITY, "ScheduledTransfer", path, "");
			})
			.collect(toList());
	}

}
