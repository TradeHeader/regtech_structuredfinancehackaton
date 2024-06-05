package cdm.product.asset.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.product.asset.DividendPaymentDate;
import cdm.product.asset.DividendPeriod;
import cdm.product.template.Product;
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

public class DividendPeriodValidator implements Validator<DividendPeriod> {

	private List<ComparisonResult> getComparisonResults(DividendPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("startDate", (DividendPaymentDate) o.getStartDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("endDate", (DividendPaymentDate) o.getEndDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("dateAdjustments", (BusinessDayAdjustments) o.getDateAdjustments() != null ? 1 : 0, 1, 1), 
				checkCardinality("basketConstituent", (Product) o.getBasketConstituent() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendPaymentDate", (DividendPaymentDate) o.getDividendPaymentDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("dividendValuationDate", (AdjustableOrRelativeDate) o.getDividendValuationDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DividendPeriod> validate(RosettaPath path, DividendPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendPeriod", ValidationType.CARDINALITY, "DividendPeriod", path, "", error);
		}
		return success("DividendPeriod", ValidationType.CARDINALITY, "DividendPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendPeriod", ValidationType.CARDINALITY, "DividendPeriod", path, "", res.getError());
				}
				return success("DividendPeriod", ValidationType.CARDINALITY, "DividendPeriod", path, "");
			})
			.collect(toList());
	}

}
