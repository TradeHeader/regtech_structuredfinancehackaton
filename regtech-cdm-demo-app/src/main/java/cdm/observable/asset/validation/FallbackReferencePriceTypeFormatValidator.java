package cdm.observable.asset.validation;

import cdm.observable.asset.FallbackReferencePrice;
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

public class FallbackReferencePriceTypeFormatValidator implements Validator<FallbackReferencePrice> {

	private List<ComparisonResult> getComparisonResults(FallbackReferencePrice o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FallbackReferencePrice> validate(RosettaPath path, FallbackReferencePrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FallbackReferencePrice", ValidationType.TYPE_FORMAT, "FallbackReferencePrice", path, "", error);
		}
		return success("FallbackReferencePrice", ValidationType.TYPE_FORMAT, "FallbackReferencePrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FallbackReferencePrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FallbackReferencePrice", ValidationType.TYPE_FORMAT, "FallbackReferencePrice", path, "", res.getError());
				}
				return success("FallbackReferencePrice", ValidationType.TYPE_FORMAT, "FallbackReferencePrice", path, "");
			})
			.collect(toList());
	}

}
