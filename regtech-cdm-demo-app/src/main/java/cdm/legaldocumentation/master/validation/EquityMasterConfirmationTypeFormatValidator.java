package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.EquityMasterConfirmation;
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

public class EquityMasterConfirmationTypeFormatValidator implements Validator<EquityMasterConfirmation> {

	private List<ComparisonResult> getComparisonResults(EquityMasterConfirmation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EquityMasterConfirmation> validate(RosettaPath path, EquityMasterConfirmation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquityMasterConfirmation", ValidationType.TYPE_FORMAT, "EquityMasterConfirmation", path, "", error);
		}
		return success("EquityMasterConfirmation", ValidationType.TYPE_FORMAT, "EquityMasterConfirmation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquityMasterConfirmation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquityMasterConfirmation", ValidationType.TYPE_FORMAT, "EquityMasterConfirmation", path, "", res.getError());
				}
				return success("EquityMasterConfirmation", ValidationType.TYPE_FORMAT, "EquityMasterConfirmation", path, "");
			})
			.collect(toList());
	}

}
