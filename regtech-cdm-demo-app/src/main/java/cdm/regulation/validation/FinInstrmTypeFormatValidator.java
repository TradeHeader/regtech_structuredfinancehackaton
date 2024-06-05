package cdm.regulation.validation;

import cdm.regulation.FinInstrm;
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

public class FinInstrmTypeFormatValidator implements Validator<FinInstrm> {

	private List<ComparisonResult> getComparisonResults(FinInstrm o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FinInstrm> validate(RosettaPath path, FinInstrm o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FinInstrm", ValidationType.TYPE_FORMAT, "FinInstrm", path, "", error);
		}
		return success("FinInstrm", ValidationType.TYPE_FORMAT, "FinInstrm", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FinInstrm o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FinInstrm", ValidationType.TYPE_FORMAT, "FinInstrm", path, "", res.getError());
				}
				return success("FinInstrm", ValidationType.TYPE_FORMAT, "FinInstrm", path, "");
			})
			.collect(toList());
	}

}
