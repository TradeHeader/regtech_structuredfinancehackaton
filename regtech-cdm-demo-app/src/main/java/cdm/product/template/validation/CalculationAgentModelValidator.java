package cdm.product.template.validation;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.CalculationAgentModel;
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

public class CalculationAgentModelValidator implements Validator<CalculationAgentModel> {

	private List<ComparisonResult> getComparisonResults(CalculationAgentModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationAgent", (CalculationAgent) o.getCalculationAgent() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgentBusinessCenter", (BusinessCenterEnum) o.getCalculationAgentBusinessCenter() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CalculationAgentModel> validate(RosettaPath path, CalculationAgentModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationAgentModel", ValidationType.CARDINALITY, "CalculationAgentModel", path, "", error);
		}
		return success("CalculationAgentModel", ValidationType.CARDINALITY, "CalculationAgentModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationAgentModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationAgentModel", ValidationType.CARDINALITY, "CalculationAgentModel", path, "", res.getError());
				}
				return success("CalculationAgentModel", ValidationType.CARDINALITY, "CalculationAgentModel", path, "");
			})
			.collect(toList());
	}

}
