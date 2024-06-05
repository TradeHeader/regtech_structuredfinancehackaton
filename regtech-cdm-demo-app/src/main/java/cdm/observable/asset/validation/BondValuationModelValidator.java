package cdm.observable.asset.validation;

import cdm.observable.asset.BondPriceAndYieldModel;
import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.Money;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BondValuationModelValidator implements Validator<BondValuationModel> {

	private List<ComparisonResult> getComparisonResults(BondValuationModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("nominalAmount", (Money) o.getNominalAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("bondPriceAndYieldModel", (BondPriceAndYieldModel) o.getBondPriceAndYieldModel() != null ? 1 : 0, 1, 1), 
				checkCardinality("accrualsAmount", (Money) o.getAccrualsAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BondValuationModel> validate(RosettaPath path, BondValuationModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BondValuationModel", ValidationType.CARDINALITY, "BondValuationModel", path, "", error);
		}
		return success("BondValuationModel", ValidationType.CARDINALITY, "BondValuationModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondValuationModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondValuationModel", ValidationType.CARDINALITY, "BondValuationModel", path, "", res.getError());
				}
				return success("BondValuationModel", ValidationType.CARDINALITY, "BondValuationModel", path, "");
			})
			.collect(toList());
	}

}
