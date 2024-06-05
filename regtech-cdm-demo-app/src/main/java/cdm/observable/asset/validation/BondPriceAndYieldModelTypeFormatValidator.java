package cdm.observable.asset.validation;

import cdm.observable.asset.BondPriceAndYieldModel;
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

public class BondPriceAndYieldModelTypeFormatValidator implements Validator<BondPriceAndYieldModel> {

	private List<ComparisonResult> getComparisonResults(BondPriceAndYieldModel o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BondPriceAndYieldModel> validate(RosettaPath path, BondPriceAndYieldModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BondPriceAndYieldModel", ValidationType.TYPE_FORMAT, "BondPriceAndYieldModel", path, "", error);
		}
		return success("BondPriceAndYieldModel", ValidationType.TYPE_FORMAT, "BondPriceAndYieldModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondPriceAndYieldModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondPriceAndYieldModel", ValidationType.TYPE_FORMAT, "BondPriceAndYieldModel", path, "", res.getError());
				}
				return success("BondPriceAndYieldModel", ValidationType.TYPE_FORMAT, "BondPriceAndYieldModel", path, "");
			})
			.collect(toList());
	}

}
