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
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PCDeliverableObligationCharacTypeFormatValidator implements Validator<PCDeliverableObligationCharac> {

	private List<ComparisonResult> getComparisonResults(PCDeliverableObligationCharac o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("PCDeliverableObligationCharac", ValidationType.TYPE_FORMAT, "PCDeliverableObligationCharac", path, "", error);
		}
		return success("PCDeliverableObligationCharac", ValidationType.TYPE_FORMAT, "PCDeliverableObligationCharac", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PCDeliverableObligationCharac o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PCDeliverableObligationCharac", ValidationType.TYPE_FORMAT, "PCDeliverableObligationCharac", path, "", res.getError());
				}
				return success("PCDeliverableObligationCharac", ValidationType.TYPE_FORMAT, "PCDeliverableObligationCharac", path, "");
			})
			.collect(toList());
	}

}
