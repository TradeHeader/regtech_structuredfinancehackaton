package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
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

public class EquitySwapMasterConfirmation2018TypeFormatValidator implements Validator<EquitySwapMasterConfirmation2018> {

	private List<ComparisonResult> getComparisonResults(EquitySwapMasterConfirmation2018 o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EquitySwapMasterConfirmation2018> validate(RosettaPath path, EquitySwapMasterConfirmation2018 o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquitySwapMasterConfirmation2018", ValidationType.TYPE_FORMAT, "EquitySwapMasterConfirmation2018", path, "", error);
		}
		return success("EquitySwapMasterConfirmation2018", ValidationType.TYPE_FORMAT, "EquitySwapMasterConfirmation2018", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquitySwapMasterConfirmation2018 o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquitySwapMasterConfirmation2018", ValidationType.TYPE_FORMAT, "EquitySwapMasterConfirmation2018", path, "", res.getError());
				}
				return success("EquitySwapMasterConfirmation2018", ValidationType.TYPE_FORMAT, "EquitySwapMasterConfirmation2018", path, "");
			})
			.collect(toList());
	}

}
