package cdm.product.collateral.validation;

import cdm.base.datetime.CalculationFrequency;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestParameters;
import cdm.product.collateral.CollateralMarginTypeEnum;
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

public class CollateralInterestParametersValidator implements Validator<CollateralInterestParameters> {

	private List<ComparisonResult> getComparisonResults(CollateralInterestParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("postingParty", (CounterpartyRoleEnum) o.getPostingParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("marginType", (CollateralMarginTypeEnum) o.getMarginType() != null ? 1 : 0, 0, 1), 
				checkCardinality("currency", (String) o.getCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestCalculationParameters", (CollateralInterestCalculationParameters) o.getInterestCalculationParameters() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestCalculationFrequency", (CalculationFrequency) o.getInterestCalculationFrequency() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestHandlingParameters", (CollateralInterestHandlingParameters) o.getInterestHandlingParameters() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CollateralInterestParameters> validate(RosettaPath path, CollateralInterestParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralInterestParameters", ValidationType.CARDINALITY, "CollateralInterestParameters", path, "", error);
		}
		return success("CollateralInterestParameters", ValidationType.CARDINALITY, "CollateralInterestParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralInterestParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralInterestParameters", ValidationType.CARDINALITY, "CollateralInterestParameters", path, "", res.getError());
				}
				return success("CollateralInterestParameters", ValidationType.CARDINALITY, "CollateralInterestParameters", path, "");
			})
			.collect(toList());
	}

}
