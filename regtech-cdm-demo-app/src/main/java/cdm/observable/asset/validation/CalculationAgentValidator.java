package cdm.observable.asset.validation;

import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.observable.asset.PartyDeterminationEnum;
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

public class CalculationAgentValidator implements Validator<CalculationAgent> {

	private List<ComparisonResult> getComparisonResults(CalculationAgent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationAgentParty", (AncillaryRoleEnum) o.getCalculationAgentParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgentPartyEnum", (PartyDeterminationEnum) o.getCalculationAgentPartyEnum() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgentBusinessCenter", (FieldWithMetaBusinessCenterEnum) o.getCalculationAgentBusinessCenter() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CalculationAgent> validate(RosettaPath path, CalculationAgent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationAgent", ValidationType.CARDINALITY, "CalculationAgent", path, "", error);
		}
		return success("CalculationAgent", ValidationType.CARDINALITY, "CalculationAgent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationAgent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationAgent", ValidationType.CARDINALITY, "CalculationAgent", path, "", res.getError());
				}
				return success("CalculationAgent", ValidationType.CARDINALITY, "CalculationAgent", path, "");
			})
			.collect(toList());
	}

}
