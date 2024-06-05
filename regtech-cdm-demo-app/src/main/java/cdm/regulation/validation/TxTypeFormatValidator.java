package cdm.regulation.validation;

import cdm.regulation.Tx;
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

public class TxTypeFormatValidator implements Validator<Tx> {

	private List<ComparisonResult> getComparisonResults(Tx o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Tx> validate(RosettaPath path, Tx o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Tx", ValidationType.TYPE_FORMAT, "Tx", path, "", error);
		}
		return success("Tx", ValidationType.TYPE_FORMAT, "Tx", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Tx o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Tx", ValidationType.TYPE_FORMAT, "Tx", path, "", res.getError());
				}
				return success("Tx", ValidationType.TYPE_FORMAT, "Tx", path, "");
			})
			.collect(toList());
	}

}
