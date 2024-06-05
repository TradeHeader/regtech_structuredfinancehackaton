package cdm.product.collateral.validation;

import cdm.base.datetime.CompoundingTypeEnum;
import cdm.base.datetime.RoundingFrequencyEnum;
import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.math.Rounding;
import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
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

public class CollateralInterestCalculationParametersValidator implements Validator<CollateralInterestCalculationParameters> {

	private List<ComparisonResult> getComparisonResults(CollateralInterestCalculationParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("fixedRate", (BigDecimal) o.getFixedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRate", (CollateralAgreementFloatingRate) o.getFloatingRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("inBaseCurrency", (Boolean) o.getInBaseCurrency() != null ? 1 : 0, 1, 1), 
				checkCardinality("compoundingType", (CompoundingTypeEnum) o.getCompoundingType() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayCountFraction", (DayCountFractionEnum) o.getDayCountFraction() != null ? 1 : 0, 1, 1), 
				checkCardinality("rounding", (Rounding) o.getRounding() != null ? 1 : 0, 0, 1), 
				checkCardinality("roundingFrequency", (RoundingFrequencyEnum) o.getRoundingFrequency() != null ? 1 : 0, 0, 1), 
				checkCardinality("withholdingTaxRate", (BigDecimal) o.getWithholdingTaxRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralInterestCalculationParameters", ValidationType.CARDINALITY, "CollateralInterestCalculationParameters", path, "", error);
		}
		return success("CollateralInterestCalculationParameters", ValidationType.CARDINALITY, "CollateralInterestCalculationParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralInterestCalculationParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralInterestCalculationParameters", ValidationType.CARDINALITY, "CollateralInterestCalculationParameters", path, "", res.getError());
				}
				return success("CollateralInterestCalculationParameters", ValidationType.CARDINALITY, "CollateralInterestCalculationParameters", path, "");
			})
			.collect(toList());
	}

}
