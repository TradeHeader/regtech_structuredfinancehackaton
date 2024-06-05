package cdm.observable.asset.validation;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaInflationRateIndexEnum;
import cdm.observable.asset.FloatingRateOption;
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

public class FloatingRateOptionValidator implements Validator<FloatingRateOption> {

	private List<ComparisonResult> getComparisonResults(FloatingRateOption o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("floatingRateIndex", (FieldWithMetaFloatingRateIndexEnum) o.getFloatingRateIndex() != null ? 1 : 0, 0, 1), 
				checkCardinality("inflationRateIndex", (FieldWithMetaInflationRateIndexEnum) o.getInflationRateIndex() != null ? 1 : 0, 0, 1), 
				checkCardinality("indexTenor", (Period) o.getIndexTenor() != null ? 1 : 0, 0, 1), 
				checkCardinality("indexReferenceInformation", (IndexReferenceInformation) o.getIndexReferenceInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRateOption> validate(RosettaPath path, FloatingRateOption o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateOption", ValidationType.CARDINALITY, "FloatingRateOption", path, "", error);
		}
		return success("FloatingRateOption", ValidationType.CARDINALITY, "FloatingRateOption", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateOption o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateOption", ValidationType.CARDINALITY, "FloatingRateOption", path, "", res.getError());
				}
				return success("FloatingRateOption", ValidationType.CARDINALITY, "FloatingRateOption", path, "");
			})
			.collect(toList());
	}

}
