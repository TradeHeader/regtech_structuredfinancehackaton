package cdm.base.staticdata.asset.credit.validation;

import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class NotDomesticCurrencyValidator implements Validator<NotDomesticCurrency> {

	private List<ComparisonResult> getComparisonResults(NotDomesticCurrency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<NotDomesticCurrency> validate(RosettaPath path, NotDomesticCurrency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NotDomesticCurrency", ValidationType.CARDINALITY, "NotDomesticCurrency", path, "", error);
		}
		return success("NotDomesticCurrency", ValidationType.CARDINALITY, "NotDomesticCurrency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NotDomesticCurrency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NotDomesticCurrency", ValidationType.CARDINALITY, "NotDomesticCurrency", path, "", res.getError());
				}
				return success("NotDomesticCurrency", ValidationType.CARDINALITY, "NotDomesticCurrency", path, "");
			})
			.collect(toList());
	}

}
