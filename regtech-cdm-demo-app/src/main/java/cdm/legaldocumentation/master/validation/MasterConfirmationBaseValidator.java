package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.MasterConfirmationBase;
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

public class MasterConfirmationBaseValidator implements Validator<MasterConfirmationBase> {

	private List<ComparisonResult> getComparisonResults(MasterConfirmationBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MasterConfirmationBase> validate(RosettaPath path, MasterConfirmationBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterConfirmationBase", ValidationType.CARDINALITY, "MasterConfirmationBase", path, "", error);
		}
		return success("MasterConfirmationBase", ValidationType.CARDINALITY, "MasterConfirmationBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterConfirmationBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterConfirmationBase", ValidationType.CARDINALITY, "MasterConfirmationBase", path, "", res.getError());
				}
				return success("MasterConfirmationBase", ValidationType.CARDINALITY, "MasterConfirmationBase", path, "");
			})
			.collect(toList());
	}

}
