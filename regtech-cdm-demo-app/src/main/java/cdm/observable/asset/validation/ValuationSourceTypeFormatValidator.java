package cdm.observable.asset.validation;

import cdm.observable.asset.ValuationSource;
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

public class ValuationSourceTypeFormatValidator implements Validator<ValuationSource> {

	private List<ComparisonResult> getComparisonResults(ValuationSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ValuationSource> validate(RosettaPath path, ValuationSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationSource", ValidationType.TYPE_FORMAT, "ValuationSource", path, "", error);
		}
		return success("ValuationSource", ValidationType.TYPE_FORMAT, "ValuationSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationSource", ValidationType.TYPE_FORMAT, "ValuationSource", path, "", res.getError());
				}
				return success("ValuationSource", ValidationType.TYPE_FORMAT, "ValuationSource", path, "");
			})
			.collect(toList());
	}

}
