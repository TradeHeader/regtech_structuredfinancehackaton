package cdm.observable.asset.validation;

import cdm.observable.asset.Curve;
import cdm.observable.asset.InterestRateCurve;
import cdm.observable.asset.metafields.FieldWithMetaCommodityReferencePriceEnum;
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

public class CurveValidator implements Validator<Curve> {

	private List<ComparisonResult> getComparisonResults(Curve o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("interestRateCurve", (InterestRateCurve) o.getInterestRateCurve() != null ? 1 : 0, 0, 1), 
				checkCardinality("commodityCurve", (FieldWithMetaCommodityReferencePriceEnum) o.getCommodityCurve() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Curve> validate(RosettaPath path, Curve o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Curve", ValidationType.CARDINALITY, "Curve", path, "", error);
		}
		return success("Curve", ValidationType.CARDINALITY, "Curve", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Curve o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Curve", ValidationType.CARDINALITY, "Curve", path, "", res.getError());
				}
				return success("Curve", ValidationType.CARDINALITY, "Curve", path, "");
			})
			.collect(toList());
	}

}
