package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.AssetPool;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AssetPoolValidator implements Validator<AssetPool> {

	private List<ComparisonResult> getComparisonResults(AssetPool o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("version", (String) o.getVersion() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectiveDate", (Date) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialFactor", (BigDecimal) o.getInitialFactor() != null ? 1 : 0, 1, 1), 
				checkCardinality("currentFactor", (BigDecimal) o.getCurrentFactor() != null ? 1 : 0, 0, 1)
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
			return failure("AssetPool", ValidationType.CARDINALITY, "AssetPool", path, "", error);
		}
		return success("AssetPool", ValidationType.CARDINALITY, "AssetPool", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetPool o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetPool", ValidationType.CARDINALITY, "AssetPool", path, "", res.getError());
				}
				return success("AssetPool", ValidationType.CARDINALITY, "AssetPool", path, "");
			})
			.collect(toList());
	}

}
