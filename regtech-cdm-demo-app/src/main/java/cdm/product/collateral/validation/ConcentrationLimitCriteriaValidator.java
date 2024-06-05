package cdm.product.collateral.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.ConcentrationLimitCriteria;
import cdm.product.collateral.ConcentrationLimitTypeEnum;
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

public class ConcentrationLimitCriteriaValidator implements Validator<ConcentrationLimitCriteria> {

	private List<ComparisonResult> getComparisonResults(ConcentrationLimitCriteria o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("appliesTo", (List<CounterpartyRoleEnum>) o.getAppliesTo() == null ? 0 : ((List<CounterpartyRoleEnum>) o.getAppliesTo()).size(), 0, 2), 
				checkCardinality("concentrationLimitType", (ConcentrationLimitTypeEnum) o.getConcentrationLimitType() != null ? 1 : 0, 0, 1), 
				checkCardinality("averageTradingVolume", (AverageTradingVolume) o.getAverageTradingVolume() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ConcentrationLimitCriteria> validate(RosettaPath path, ConcentrationLimitCriteria o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ConcentrationLimitCriteria", ValidationType.CARDINALITY, "ConcentrationLimitCriteria", path, "", error);
		}
		return success("ConcentrationLimitCriteria", ValidationType.CARDINALITY, "ConcentrationLimitCriteria", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ConcentrationLimitCriteria o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ConcentrationLimitCriteria", ValidationType.CARDINALITY, "ConcentrationLimitCriteria", path, "", res.getError());
				}
				return success("ConcentrationLimitCriteria", ValidationType.CARDINALITY, "ConcentrationLimitCriteria", path, "");
			})
			.collect(toList());
	}

}
