package cdm.observable.asset.validation;

import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.SecurityValuationModel;
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

public class SecurityValuationModelValidator implements Validator<SecurityValuationModel> {

	private List<ComparisonResult> getComparisonResults(SecurityValuationModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("bondValuationModel", (BondValuationModel) o.getBondValuationModel() != null ? 1 : 0, 0, 1), 
				checkCardinality("unitContractValuationModel", (UnitContractValuationModel) o.getUnitContractValuationModel() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SecurityValuationModel> validate(RosettaPath path, SecurityValuationModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityValuationModel", ValidationType.CARDINALITY, "SecurityValuationModel", path, "", error);
		}
		return success("SecurityValuationModel", ValidationType.CARDINALITY, "SecurityValuationModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityValuationModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityValuationModel", ValidationType.CARDINALITY, "SecurityValuationModel", path, "", res.getError());
				}
				return success("SecurityValuationModel", ValidationType.CARDINALITY, "SecurityValuationModel", path, "");
			})
			.collect(toList());
	}

}
