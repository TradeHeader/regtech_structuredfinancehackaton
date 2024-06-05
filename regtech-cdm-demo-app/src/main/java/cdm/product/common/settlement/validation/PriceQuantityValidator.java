package cdm.product.common.settlement.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.Observable;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
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

public class PriceQuantityValidator implements Validator<PriceQuantity> {

	private List<ComparisonResult> getComparisonResults(PriceQuantity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("observable", (Observable) o.getObservable() != null ? 1 : 0, 0, 1), 
				checkCardinality("buyerSeller", (BuyerSeller) o.getBuyerSeller() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectiveDate", (AdjustableOrRelativeDate) o.getEffectiveDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceQuantity", ValidationType.CARDINALITY, "PriceQuantity", path, "", error);
		}
		return success("PriceQuantity", ValidationType.CARDINALITY, "PriceQuantity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceQuantity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceQuantity", ValidationType.CARDINALITY, "PriceQuantity", path, "", res.getError());
				}
				return success("PriceQuantity", ValidationType.CARDINALITY, "PriceQuantity", path, "");
			})
			.collect(toList());
	}

}
