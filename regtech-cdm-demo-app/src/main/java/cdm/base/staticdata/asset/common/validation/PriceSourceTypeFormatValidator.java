package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.PriceSource;
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

public class PriceSourceTypeFormatValidator implements Validator<PriceSource> {

	private List<ComparisonResult> getComparisonResults(PriceSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PriceSource> validate(RosettaPath path, PriceSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceSource", ValidationType.TYPE_FORMAT, "PriceSource", path, "", error);
		}
		return success("PriceSource", ValidationType.TYPE_FORMAT, "PriceSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceSource", ValidationType.TYPE_FORMAT, "PriceSource", path, "", res.getError());
				}
				return success("PriceSource", ValidationType.TYPE_FORMAT, "PriceSource", path, "");
			})
			.collect(toList());
	}

}
