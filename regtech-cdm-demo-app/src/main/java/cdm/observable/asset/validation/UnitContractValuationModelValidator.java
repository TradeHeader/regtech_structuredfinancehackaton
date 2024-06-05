package cdm.observable.asset.validation;

import cdm.base.math.Quantity;
import cdm.observable.asset.Money;
import cdm.observable.asset.UnitContractValuationModel;
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

public class UnitContractValuationModelValidator implements Validator<UnitContractValuationModel> {

	private List<ComparisonResult> getComparisonResults(UnitContractValuationModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("numberOfUnits", (Quantity) o.getNumberOfUnits() != null ? 1 : 0, 1, 1), 
				checkCardinality("unitPrice", (Money) o.getUnitPrice() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<UnitContractValuationModel> validate(RosettaPath path, UnitContractValuationModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UnitContractValuationModel", ValidationType.CARDINALITY, "UnitContractValuationModel", path, "", error);
		}
		return success("UnitContractValuationModel", ValidationType.CARDINALITY, "UnitContractValuationModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UnitContractValuationModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UnitContractValuationModel", ValidationType.CARDINALITY, "UnitContractValuationModel", path, "", res.getError());
				}
				return success("UnitContractValuationModel", ValidationType.CARDINALITY, "UnitContractValuationModel", path, "");
			})
			.collect(toList());
	}

}
