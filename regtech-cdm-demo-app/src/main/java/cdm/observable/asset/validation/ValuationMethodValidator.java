package cdm.observable.asset.validation;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.Money;
import cdm.observable.asset.QuotationRateTypeEnum;
import cdm.observable.asset.ValuationMethod;
import cdm.observable.asset.ValuationMethodEnum;
import cdm.observable.asset.ValuationSource;
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

public class ValuationMethodValidator implements Validator<ValuationMethod> {

	private List<ComparisonResult> getComparisonResults(ValuationMethod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("valuationSource", (ValuationSource) o.getValuationSource() != null ? 1 : 0, 1, 1), 
				checkCardinality("quotationMethod", (QuotationRateTypeEnum) o.getQuotationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationMethod", (ValuationMethodEnum) o.getValuationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("quotationAmount", (Money) o.getQuotationAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("minimumQuotationAmount", (Money) o.getMinimumQuotationAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashCollateralValuationMethod", (CashCollateralValuationMethod) o.getCashCollateralValuationMethod() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationMethod", ValidationType.CARDINALITY, "ValuationMethod", path, "", error);
		}
		return success("ValuationMethod", ValidationType.CARDINALITY, "ValuationMethod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationMethod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationMethod", ValidationType.CARDINALITY, "ValuationMethod", path, "", res.getError());
				}
				return success("ValuationMethod", ValidationType.CARDINALITY, "ValuationMethod", path, "");
			})
			.collect(toList());
	}

}
