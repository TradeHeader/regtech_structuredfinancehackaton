package cdm.regulation.validation;

import cdm.regulation.UndrlygInstrm;
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

public class UndrlygInstrmTypeFormatValidator implements Validator<UndrlygInstrm> {

	private List<ComparisonResult> getComparisonResults(UndrlygInstrm o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<UndrlygInstrm> validate(RosettaPath path, UndrlygInstrm o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UndrlygInstrm", ValidationType.TYPE_FORMAT, "UndrlygInstrm", path, "", error);
		}
		return success("UndrlygInstrm", ValidationType.TYPE_FORMAT, "UndrlygInstrm", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UndrlygInstrm o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UndrlygInstrm", ValidationType.TYPE_FORMAT, "UndrlygInstrm", path, "", res.getError());
				}
				return success("UndrlygInstrm", ValidationType.TYPE_FORMAT, "UndrlygInstrm", path, "");
			})
			.collect(toList());
	}

}
