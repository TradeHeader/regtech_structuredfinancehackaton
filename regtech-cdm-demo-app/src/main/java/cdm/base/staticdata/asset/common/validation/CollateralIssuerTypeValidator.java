package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.IssuerTypeEnum;
import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
import cdm.base.staticdata.asset.common.SupraNationalIssuerTypeEnum;
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

public class CollateralIssuerTypeValidator implements Validator<CollateralIssuerType> {

	private List<ComparisonResult> getComparisonResults(CollateralIssuerType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("issuerType", (IssuerTypeEnum) o.getIssuerType() != null ? 1 : 0, 1, 1), 
				checkCardinality("supraNationalType", (SupraNationalIssuerTypeEnum) o.getSupraNationalType() != null ? 1 : 0, 0, 1), 
				checkCardinality("quasiGovernmentType", (QuasiGovernmentIssuerType) o.getQuasiGovernmentType() != null ? 1 : 0, 0, 1), 
				checkCardinality("regionalGovernmentType", (RegionalGovernmentIssuerType) o.getRegionalGovernmentType() != null ? 1 : 0, 0, 1), 
				checkCardinality("specialPurposeVehicleType", (SpecialPurposeVehicleIssuerType) o.getSpecialPurposeVehicleType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CollateralIssuerType> validate(RosettaPath path, CollateralIssuerType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralIssuerType", ValidationType.CARDINALITY, "CollateralIssuerType", path, "", error);
		}
		return success("CollateralIssuerType", ValidationType.CARDINALITY, "CollateralIssuerType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralIssuerType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralIssuerType", ValidationType.CARDINALITY, "CollateralIssuerType", path, "", res.getError());
				}
				return success("CollateralIssuerType", ValidationType.CARDINALITY, "CollateralIssuerType", path, "");
			})
			.collect(toList());
	}

}
