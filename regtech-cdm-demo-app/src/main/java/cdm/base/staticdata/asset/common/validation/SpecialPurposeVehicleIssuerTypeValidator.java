package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.CreditRiskEnum;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
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

public class SpecialPurposeVehicleIssuerTypeValidator implements Validator<SpecialPurposeVehicleIssuerType> {

	private List<ComparisonResult> getComparisonResults(SpecialPurposeVehicleIssuerType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("creditRisk", (CreditRiskEnum) o.getCreditRisk() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SpecialPurposeVehicleIssuerType> validate(RosettaPath path, SpecialPurposeVehicleIssuerType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SpecialPurposeVehicleIssuerType", ValidationType.CARDINALITY, "SpecialPurposeVehicleIssuerType", path, "", error);
		}
		return success("SpecialPurposeVehicleIssuerType", ValidationType.CARDINALITY, "SpecialPurposeVehicleIssuerType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SpecialPurposeVehicleIssuerType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SpecialPurposeVehicleIssuerType", ValidationType.CARDINALITY, "SpecialPurposeVehicleIssuerType", path, "", res.getError());
				}
				return success("SpecialPurposeVehicleIssuerType", ValidationType.CARDINALITY, "SpecialPurposeVehicleIssuerType", path, "");
			})
			.collect(toList());
	}

}
