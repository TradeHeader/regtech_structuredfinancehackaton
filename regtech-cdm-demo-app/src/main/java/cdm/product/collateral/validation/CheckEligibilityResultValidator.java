package cdm.product.collateral.validation;

import cdm.product.collateral.CheckEligibilityResult;
import cdm.product.collateral.EligibilityQuery;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecification;
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

public class CheckEligibilityResultValidator implements Validator<CheckEligibilityResult> {

	private List<ComparisonResult> getComparisonResults(CheckEligibilityResult o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("isEligible", (Boolean) o.getIsEligible() != null ? 1 : 0, 1, 1), 
				checkCardinality("matchingEligibleCriteria", (List<? extends EligibleCollateralCriteria>) o.getMatchingEligibleCriteria() == null ? 0 : ((List<? extends EligibleCollateralCriteria>) o.getMatchingEligibleCriteria()).size(), 1, 0), 
				checkCardinality("eligibilityQuery", (EligibilityQuery) o.getEligibilityQuery() != null ? 1 : 0, 0, 1), 
				checkCardinality("specification", (EligibleCollateralSpecification) o.getSpecification() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CheckEligibilityResult> validate(RosettaPath path, CheckEligibilityResult o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CheckEligibilityResult", ValidationType.CARDINALITY, "CheckEligibilityResult", path, "", error);
		}
		return success("CheckEligibilityResult", ValidationType.CARDINALITY, "CheckEligibilityResult", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CheckEligibilityResult o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CheckEligibilityResult", ValidationType.CARDINALITY, "CheckEligibilityResult", path, "", res.getError());
				}
				return success("CheckEligibilityResult", ValidationType.CARDINALITY, "CheckEligibilityResult", path, "");
			})
			.collect(toList());
	}

}
