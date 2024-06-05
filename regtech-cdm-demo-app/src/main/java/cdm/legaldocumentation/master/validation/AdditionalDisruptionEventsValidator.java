package cdm.legaldocumentation.master.validation;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
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

public class AdditionalDisruptionEventsValidator implements Validator<AdditionalDisruptionEvents> {

	private List<ComparisonResult> getComparisonResults(AdditionalDisruptionEvents o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("changeInLaw", (Boolean) o.getChangeInLaw() != null ? 1 : 0, 0, 1), 
				checkCardinality("failureToDeliver", (Boolean) o.getFailureToDeliver() != null ? 1 : 0, 0, 1), 
				checkCardinality("insolvencyFiling", (Boolean) o.getInsolvencyFiling() != null ? 1 : 0, 0, 1), 
				checkCardinality("hedgingDisruption", (Boolean) o.getHedgingDisruption() != null ? 1 : 0, 0, 1), 
				checkCardinality("increasedCostOfHedging", (Boolean) o.getIncreasedCostOfHedging() != null ? 1 : 0, 0, 1), 
				checkCardinality("foreignOwnershipEvent", (Boolean) o.getForeignOwnershipEvent() != null ? 1 : 0, 0, 1), 
				checkCardinality("lossOfStockBorrow", (Boolean) o.getLossOfStockBorrow() != null ? 1 : 0, 0, 1), 
				checkCardinality("maximumStockLoanRate", (BigDecimal) o.getMaximumStockLoanRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("increasedCostOfStockBorrow", (Boolean) o.getIncreasedCostOfStockBorrow() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialStockLoanRate", (BigDecimal) o.getInitialStockLoanRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("determiningParty", (AncillaryRoleEnum) o.getDeterminingParty() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdditionalDisruptionEvents", ValidationType.CARDINALITY, "AdditionalDisruptionEvents", path, "", error);
		}
		return success("AdditionalDisruptionEvents", ValidationType.CARDINALITY, "AdditionalDisruptionEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdditionalDisruptionEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdditionalDisruptionEvents", ValidationType.CARDINALITY, "AdditionalDisruptionEvents", path, "", res.getError());
				}
				return success("AdditionalDisruptionEvents", ValidationType.CARDINALITY, "AdditionalDisruptionEvents", path, "");
			})
			.collect(toList());
	}

}
