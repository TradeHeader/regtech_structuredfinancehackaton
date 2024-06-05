package cdm.observable.asset.calculatedrate.validation;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class FallbackRateParametersValidator implements Validator<FallbackRateParameters> {

	private List<ComparisonResult> getComparisonResults(FallbackRateParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("floatingRateIndex", (FloatingRateIndexEnum) o.getFloatingRateIndex() != null ? 1 : 0, 1, 1), 
				checkCardinality("effectiveDate", (Date) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationParameters", (FloatingRateCalculationParameters) o.getCalculationParameters() != null ? 1 : 0, 0, 1), 
				checkCardinality("spreadAdjustment", (BigDecimal) o.getSpreadAdjustment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FallbackRateParameters> validate(RosettaPath path, FallbackRateParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FallbackRateParameters", ValidationType.CARDINALITY, "FallbackRateParameters", path, "", error);
		}
		return success("FallbackRateParameters", ValidationType.CARDINALITY, "FallbackRateParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FallbackRateParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FallbackRateParameters", ValidationType.CARDINALITY, "FallbackRateParameters", path, "", res.getError());
				}
				return success("FallbackRateParameters", ValidationType.CARDINALITY, "FallbackRateParameters", path, "");
			})
			.collect(toList());
	}

}
