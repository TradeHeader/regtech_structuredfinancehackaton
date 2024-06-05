package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.DeliverableObligations;
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

public class DeliverableObligationsTypeFormatValidator implements Validator<DeliverableObligations> {

	private List<ComparisonResult> getComparisonResults(DeliverableObligations o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DeliverableObligations> validate(RosettaPath path, DeliverableObligations o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeliverableObligations", ValidationType.TYPE_FORMAT, "DeliverableObligations", path, "", error);
		}
		return success("DeliverableObligations", ValidationType.TYPE_FORMAT, "DeliverableObligations", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeliverableObligations o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeliverableObligations", ValidationType.TYPE_FORMAT, "DeliverableObligations", path, "", res.getError());
				}
				return success("DeliverableObligations", ValidationType.TYPE_FORMAT, "DeliverableObligations", path, "");
			})
			.collect(toList());
	}

}
