package cdm.product.asset.validation;

import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.DividendCurrency;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.ReferenceWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DividendCurrencyValidator implements Validator<DividendCurrency> {

	private List<ComparisonResult> getComparisonResults(DividendCurrency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("determinationMethod", (DeterminationMethodEnum) o.getDeterminationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("currencyReference", (ReferenceWithMetaString) o.getCurrencyReference() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DividendCurrency> validate(RosettaPath path, DividendCurrency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendCurrency", ValidationType.CARDINALITY, "DividendCurrency", path, "", error);
		}
		return success("DividendCurrency", ValidationType.CARDINALITY, "DividendCurrency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendCurrency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendCurrency", ValidationType.CARDINALITY, "DividendCurrency", path, "", res.getError());
				}
				return success("DividendCurrency", ValidationType.CARDINALITY, "DividendCurrency", path, "");
			})
			.collect(toList());
	}

}
