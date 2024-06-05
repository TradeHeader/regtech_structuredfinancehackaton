package cdm.event.common.validation;

import cdm.event.common.Valuation;
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

public class ValuationTypeFormatValidator implements Validator<Valuation> {

	private List<ComparisonResult> getComparisonResults(Valuation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Valuation> validate(RosettaPath path, Valuation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Valuation", ValidationType.TYPE_FORMAT, "Valuation", path, "", error);
		}
		return success("Valuation", ValidationType.TYPE_FORMAT, "Valuation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Valuation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Valuation", ValidationType.TYPE_FORMAT, "Valuation", path, "", res.getError());
				}
				return success("Valuation", ValidationType.TYPE_FORMAT, "Valuation", path, "");
			})
			.collect(toList());
	}

}
