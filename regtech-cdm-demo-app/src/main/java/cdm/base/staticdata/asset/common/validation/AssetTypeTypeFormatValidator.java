package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.AssetType;
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

public class AssetTypeTypeFormatValidator implements Validator<AssetType> {

	private List<ComparisonResult> getComparisonResults(AssetType o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetType> validate(RosettaPath path, AssetType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetType", ValidationType.TYPE_FORMAT, "AssetType", path, "", error);
		}
		return success("AssetType", ValidationType.TYPE_FORMAT, "AssetType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetType", ValidationType.TYPE_FORMAT, "AssetType", path, "", res.getError());
				}
				return success("AssetType", ValidationType.TYPE_FORMAT, "AssetType", path, "");
			})
			.collect(toList());
	}

}
