package cdm.product.collateral.validation;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
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

public class EligibleCollateralSpecificationValidator implements Validator<EligibleCollateralSpecification> {

	private List<ComparisonResult> getComparisonResults(EligibleCollateralSpecification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("party", (List<? extends Party>) o.getParty() == null ? 0 : ((List<? extends Party>) o.getParty()).size(), 0, 2), 
				checkCardinality("counterparty", (List<? extends Counterparty>) o.getCounterparty() == null ? 0 : ((List<? extends Counterparty>) o.getCounterparty()).size(), 0, 2), 
				checkCardinality("criteria", (List<? extends EligibleCollateralCriteria>) o.getCriteria() == null ? 0 : ((List<? extends EligibleCollateralCriteria>) o.getCriteria()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<EligibleCollateralSpecification> validate(RosettaPath path, EligibleCollateralSpecification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EligibleCollateralSpecification", ValidationType.CARDINALITY, "EligibleCollateralSpecification", path, "", error);
		}
		return success("EligibleCollateralSpecification", ValidationType.CARDINALITY, "EligibleCollateralSpecification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EligibleCollateralSpecification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EligibleCollateralSpecification", ValidationType.CARDINALITY, "EligibleCollateralSpecification", path, "", res.getError());
				}
				return success("EligibleCollateralSpecification", ValidationType.CARDINALITY, "EligibleCollateralSpecification", path, "");
			})
			.collect(toList());
	}

}
