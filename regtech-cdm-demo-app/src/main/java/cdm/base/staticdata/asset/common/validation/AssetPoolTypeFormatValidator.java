package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.AssetPool;
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

public class AssetPoolTypeFormatValidator implements Validator<AssetPool> {

	private List<ComparisonResult> getComparisonResults(AssetPool o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetPool> validate(RosettaPath path, AssetPool o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetPool", ValidationType.TYPE_FORMAT, "AssetPool", path, "", error);
		}
		return success("AssetPool", ValidationType.TYPE_FORMAT, "AssetPool", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetPool o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetPool", ValidationType.TYPE_FORMAT, "AssetPool", path, "", res.getError());
				}
				return success("AssetPool", ValidationType.TYPE_FORMAT, "AssetPool", path, "");
			})
			.collect(toList());
	}

}
