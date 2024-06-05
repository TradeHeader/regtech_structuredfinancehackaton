package cdm.product.asset.validation;

import cdm.product.asset.CommodityPayout;
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

public class CommodityPayoutTypeFormatValidator implements Validator<CommodityPayout> {

	private List<ComparisonResult> getComparisonResults(CommodityPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CommodityPayout", ValidationType.TYPE_FORMAT, "CommodityPayout", path, "", error);
		}
		return success("CommodityPayout", ValidationType.TYPE_FORMAT, "CommodityPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CommodityPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CommodityPayout", ValidationType.TYPE_FORMAT, "CommodityPayout", path, "", res.getError());
				}
				return success("CommodityPayout", ValidationType.TYPE_FORMAT, "CommodityPayout", path, "");
			})
			.collect(toList());
	}

}
