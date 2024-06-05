package cdm.observable.asset.validation;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.InterpolationMethodEnum;
import cdm.observable.asset.MakeWholeAmount;
import cdm.observable.asset.QuotationSideEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MakeWholeAmountValidator implements Validator<MakeWholeAmount> {

	private List<ComparisonResult> getComparisonResults(MakeWholeAmount o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("floatingRateIndex", (FloatingRateIndexEnum) o.getFloatingRateIndex() != null ? 1 : 0, 1, 1), 
				checkCardinality("indexTenor", (Period) o.getIndexTenor() != null ? 1 : 0, 0, 1), 
				checkCardinality("spread", (BigDecimal) o.getSpread() != null ? 1 : 0, 1, 1), 
				checkCardinality("side", (QuotationSideEnum) o.getSide() != null ? 1 : 0, 0, 1), 
				checkCardinality("interpolationMethod", (InterpolationMethodEnum) o.getInterpolationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("earlyCallDate", (FieldWithMetaDate) o.getEarlyCallDate() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<MakeWholeAmount> validate(RosettaPath path, MakeWholeAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MakeWholeAmount", ValidationType.CARDINALITY, "MakeWholeAmount", path, "", error);
		}
		return success("MakeWholeAmount", ValidationType.CARDINALITY, "MakeWholeAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MakeWholeAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MakeWholeAmount", ValidationType.CARDINALITY, "MakeWholeAmount", path, "", res.getError());
				}
				return success("MakeWholeAmount", ValidationType.CARDINALITY, "MakeWholeAmount", path, "");
			})
			.collect(toList());
	}

}
