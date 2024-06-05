package cdm.product.collateral.validation;

import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.SpreadSchedule;
import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.template.StrikeSchedule;
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

public class CollateralAgreementFloatingRateValidator implements Validator<CollateralAgreementFloatingRate> {

	private List<ComparisonResult> getComparisonResults(CollateralAgreementFloatingRate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rateOption", (ReferenceWithMetaFloatingRateOption) o.getRateOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("spreadSchedule", (SpreadSchedule) o.getSpreadSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("capRateSchedule", (StrikeSchedule) o.getCapRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("floorRateSchedule", (StrikeSchedule) o.getFloorRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("negativeInterest", (Boolean) o.getNegativeInterest() != null ? 1 : 0, 1, 1), 
				checkCardinality("compressibleSpread", (Boolean) o.getCompressibleSpread() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CollateralAgreementFloatingRate> validate(RosettaPath path, CollateralAgreementFloatingRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralAgreementFloatingRate", ValidationType.CARDINALITY, "CollateralAgreementFloatingRate", path, "", error);
		}
		return success("CollateralAgreementFloatingRate", ValidationType.CARDINALITY, "CollateralAgreementFloatingRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralAgreementFloatingRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralAgreementFloatingRate", ValidationType.CARDINALITY, "CollateralAgreementFloatingRate", path, "", res.getError());
				}
				return success("CollateralAgreementFloatingRate", ValidationType.CARDINALITY, "CollateralAgreementFloatingRate", path, "");
			})
			.collect(toList());
	}

}
