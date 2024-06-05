package cdm.product.template.validation;

import cdm.observable.asset.Money;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPayoutRatio;
import cdm.product.template.DividendTerms;
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

public class DividendTermsValidator implements Validator<DividendTerms> {

	private List<ComparisonResult> getComparisonResults(DividendTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("manufacturedIncomeRequirement", (DividendPayoutRatio) o.getManufacturedIncomeRequirement() != null ? 1 : 0, 1, 1), 
				checkCardinality("dividendEntitlement", (DividendEntitlementEnum) o.getDividendEntitlement() != null ? 1 : 0, 0, 1), 
				checkCardinality("minimumBillingAmount", (Money) o.getMinimumBillingAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DividendTerms> validate(RosettaPath path, DividendTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendTerms", ValidationType.CARDINALITY, "DividendTerms", path, "", error);
		}
		return success("DividendTerms", ValidationType.CARDINALITY, "DividendTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendTerms", ValidationType.CARDINALITY, "DividendTerms", path, "", res.getError());
				}
				return success("DividendTerms", ValidationType.CARDINALITY, "DividendTerms", path, "");
			})
			.collect(toList());
	}

}
