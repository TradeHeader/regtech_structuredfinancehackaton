package cdm.product.common.settlement.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.Money;
import cdm.observable.asset.ValuationMethod;
import cdm.product.common.settlement.CashSettlementMethodEnum;
import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.ValuationDate;
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

public class CashSettlementTermsValidator implements Validator<CashSettlementTerms> {

	private List<ComparisonResult> getComparisonResults(CashSettlementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cashSettlementMethod", (CashSettlementMethodEnum) o.getCashSettlementMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationMethod", (ValuationMethod) o.getValuationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDate", (ValuationDate) o.getValuationDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationTime", (BusinessCenterTime) o.getValuationTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashSettlementAmount", (Money) o.getCashSettlementAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("recoveryFactor", (BigDecimal) o.getRecoveryFactor() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixedSettlement", (Boolean) o.getFixedSettlement() != null ? 1 : 0, 0, 1), 
				checkCardinality("accruedInterest", (Boolean) o.getAccruedInterest() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CashSettlementTerms", ValidationType.CARDINALITY, "CashSettlementTerms", path, "", error);
		}
		return success("CashSettlementTerms", ValidationType.CARDINALITY, "CashSettlementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CashSettlementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CashSettlementTerms", ValidationType.CARDINALITY, "CashSettlementTerms", path, "", res.getError());
				}
				return success("CashSettlementTerms", ValidationType.CARDINALITY, "CashSettlementTerms", path, "");
			})
			.collect(toList());
	}

}
