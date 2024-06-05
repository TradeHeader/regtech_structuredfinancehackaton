package cdm.product.collateral.validation;

import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction;
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

public class EligibleCollateralSpecificationInstructionValidator implements Validator<EligibleCollateralSpecificationInstruction> {

	private List<ComparisonResult> getComparisonResults(EligibleCollateralSpecificationInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("common", (EligibleCollateralCriteria) o.getCommon() != null ? 1 : 0, 1, 1), 
				checkCardinality("variable", (List<? extends EligibleCollateralCriteria>) o.getVariable() == null ? 0 : ((List<? extends EligibleCollateralCriteria>) o.getVariable()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<EligibleCollateralSpecificationInstruction> validate(RosettaPath path, EligibleCollateralSpecificationInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EligibleCollateralSpecificationInstruction", ValidationType.CARDINALITY, "EligibleCollateralSpecificationInstruction", path, "", error);
		}
		return success("EligibleCollateralSpecificationInstruction", ValidationType.CARDINALITY, "EligibleCollateralSpecificationInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EligibleCollateralSpecificationInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EligibleCollateralSpecificationInstruction", ValidationType.CARDINALITY, "EligibleCollateralSpecificationInstruction", path, "", res.getError());
				}
				return success("EligibleCollateralSpecificationInstruction", ValidationType.CARDINALITY, "EligibleCollateralSpecificationInstruction", path, "");
			})
			.collect(toList());
	}

}
