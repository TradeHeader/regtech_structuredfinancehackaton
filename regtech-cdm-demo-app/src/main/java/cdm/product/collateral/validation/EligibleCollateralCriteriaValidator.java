package cdm.product.collateral.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.EligibleCollateralCriteria;
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

public class EligibleCollateralCriteriaValidator implements Validator<EligibleCollateralCriteria> {

	private List<ComparisonResult> getComparisonResults(EligibleCollateralCriteria o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("appliesTo", (List<CounterpartyRoleEnum>) o.getAppliesTo() == null ? 0 : ((List<CounterpartyRoleEnum>) o.getAppliesTo()).size(), 0, 2), 
				checkCardinality("treatment", (CollateralTreatment) o.getTreatment() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<EligibleCollateralCriteria> validate(RosettaPath path, EligibleCollateralCriteria o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EligibleCollateralCriteria", ValidationType.CARDINALITY, "EligibleCollateralCriteria", path, "", error);
		}
		return success("EligibleCollateralCriteria", ValidationType.CARDINALITY, "EligibleCollateralCriteria", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EligibleCollateralCriteria o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EligibleCollateralCriteria", ValidationType.CARDINALITY, "EligibleCollateralCriteria", path, "", res.getError());
				}
				return success("EligibleCollateralCriteria", ValidationType.CARDINALITY, "EligibleCollateralCriteria", path, "");
			})
			.collect(toList());
	}

}
