package cdm.product.asset.validation;

import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.product.asset.InterestShortFall;
import cdm.product.asset.InterestShortfallCapEnum;
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

public class InterestShortFallValidator implements Validator<InterestShortFall> {

	private List<ComparisonResult> getComparisonResults(InterestShortFall o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("interestShortfallCap", (InterestShortfallCapEnum) o.getInterestShortfallCap() != null ? 1 : 0, 1, 1), 
				checkCardinality("compounding", (Boolean) o.getCompounding() != null ? 1 : 0, 1, 1), 
				checkCardinality("rateSource", (FieldWithMetaFloatingRateIndexEnum) o.getRateSource() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<InterestShortFall> validate(RosettaPath path, InterestShortFall o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InterestShortFall", ValidationType.CARDINALITY, "InterestShortFall", path, "", error);
		}
		return success("InterestShortFall", ValidationType.CARDINALITY, "InterestShortFall", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InterestShortFall o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InterestShortFall", ValidationType.CARDINALITY, "InterestShortFall", path, "", res.getError());
				}
				return success("InterestShortFall", ValidationType.CARDINALITY, "InterestShortFall", path, "");
			})
			.collect(toList());
	}

}
