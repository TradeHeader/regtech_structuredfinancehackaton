package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Commodity;
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

public class CommodityTypeFormatValidator implements Validator<Commodity> {

	private List<ComparisonResult> getComparisonResults(Commodity o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Commodity> validate(RosettaPath path, Commodity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Commodity", ValidationType.TYPE_FORMAT, "Commodity", path, "", error);
		}
		return success("Commodity", ValidationType.TYPE_FORMAT, "Commodity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Commodity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Commodity", ValidationType.TYPE_FORMAT, "Commodity", path, "", res.getError());
				}
				return success("Commodity", ValidationType.TYPE_FORMAT, "Commodity", path, "");
			})
			.collect(toList());
	}

}
