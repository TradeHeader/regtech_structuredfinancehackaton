package cdm.observable.asset.validation;

import cdm.base.staticdata.asset.common.metafields.FieldWithMetaCommodity;
import cdm.observable.asset.Observable;
import cdm.observable.asset.OptionReferenceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaQuotedCurrencyPair;
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

public class ObservableValidator implements Validator<Observable> {

	private List<ComparisonResult> getComparisonResults(Observable o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rateOption", (FieldWithMetaFloatingRateOption) o.getRateOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("commodity", (FieldWithMetaCommodity) o.getCommodity() != null ? 1 : 0, 0, 1), 
				checkCardinality("currencyPair", (FieldWithMetaQuotedCurrencyPair) o.getCurrencyPair() != null ? 1 : 0, 0, 1), 
				checkCardinality("optionReferenceType", (OptionReferenceTypeEnum) o.getOptionReferenceType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Observable> validate(RosettaPath path, Observable o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Observable", ValidationType.CARDINALITY, "Observable", path, "", error);
		}
		return success("Observable", ValidationType.CARDINALITY, "Observable", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Observable o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Observable", ValidationType.CARDINALITY, "Observable", path, "", res.getError());
				}
				return success("Observable", ValidationType.CARDINALITY, "Observable", path, "");
			})
			.collect(toList());
	}

}
