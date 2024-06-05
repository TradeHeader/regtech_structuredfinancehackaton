package cdm.product.template.validation;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.CalculationAgent;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.OptionalEarlyTermination;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
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

public class OptionalEarlyTerminationValidator implements Validator<OptionalEarlyTermination> {

	private List<ComparisonResult> getComparisonResults(OptionalEarlyTermination o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("singlePartyOption", (BuyerSeller) o.getSinglePartyOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("mutualEarlyTermination", (Boolean) o.getMutualEarlyTermination() != null ? 1 : 0, 0, 1), 
				checkCardinality("americanExercise", (AmericanExercise) o.getAmericanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("bermudaExercise", (BermudaExercise) o.getBermudaExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("europeanExercise", (EuropeanExercise) o.getEuropeanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("followUpConfirmation", (Boolean) o.getFollowUpConfirmation() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgent", (CalculationAgent) o.getCalculationAgent() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashSettlement", (SettlementTerms) o.getCashSettlement() != null ? 1 : 0, 0, 1), 
				checkCardinality("optionalEarlyTerminationAdjustedDates", (OptionalEarlyTerminationAdjustedDates) o.getOptionalEarlyTerminationAdjustedDates() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionalEarlyTermination", ValidationType.CARDINALITY, "OptionalEarlyTermination", path, "", error);
		}
		return success("OptionalEarlyTermination", ValidationType.CARDINALITY, "OptionalEarlyTermination", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionalEarlyTermination o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionalEarlyTermination", ValidationType.CARDINALITY, "OptionalEarlyTermination", path, "", res.getError());
				}
				return success("OptionalEarlyTermination", ValidationType.CARDINALITY, "OptionalEarlyTermination", path, "");
			})
			.collect(toList());
	}

}
