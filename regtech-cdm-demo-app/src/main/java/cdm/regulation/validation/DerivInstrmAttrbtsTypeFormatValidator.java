package cdm.regulation.validation;

import cdm.regulation.DerivInstrmAttrbts;
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

public class DerivInstrmAttrbtsTypeFormatValidator implements Validator<DerivInstrmAttrbts> {

	private List<ComparisonResult> getComparisonResults(DerivInstrmAttrbts o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DerivInstrmAttrbts> validate(RosettaPath path, DerivInstrmAttrbts o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DerivInstrmAttrbts", ValidationType.TYPE_FORMAT, "DerivInstrmAttrbts", path, "", error);
		}
		return success("DerivInstrmAttrbts", ValidationType.TYPE_FORMAT, "DerivInstrmAttrbts", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DerivInstrmAttrbts o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DerivInstrmAttrbts", ValidationType.TYPE_FORMAT, "DerivInstrmAttrbts", path, "", res.getError());
				}
				return success("DerivInstrmAttrbts", ValidationType.TYPE_FORMAT, "DerivInstrmAttrbts", path, "");
			})
			.collect(toList());
	}

}
