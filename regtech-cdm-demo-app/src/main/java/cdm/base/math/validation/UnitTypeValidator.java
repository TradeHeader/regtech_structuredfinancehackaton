package cdm.base.math.validation;

import cdm.base.math.CapacityUnitEnum;
import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.UnitType;
import cdm.base.math.WeatherUnitEnum;
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

public class UnitTypeValidator implements Validator<UnitType> {

	private List<ComparisonResult> getComparisonResults(UnitType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("capacityUnit", (CapacityUnitEnum) o.getCapacityUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("weatherUnit", (WeatherUnitEnum) o.getWeatherUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("financialUnit", (FinancialUnitEnum) o.getFinancialUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<UnitType> validate(RosettaPath path, UnitType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UnitType", ValidationType.CARDINALITY, "UnitType", path, "", error);
		}
		return success("UnitType", ValidationType.CARDINALITY, "UnitType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UnitType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UnitType", ValidationType.CARDINALITY, "UnitType", path, "", res.getError());
				}
				return success("UnitType", ValidationType.CARDINALITY, "UnitType", path, "");
			})
			.collect(toList());
	}

}
