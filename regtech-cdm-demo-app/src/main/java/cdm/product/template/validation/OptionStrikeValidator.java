package cdm.product.template.validation;

import cdm.observable.asset.Price;
import cdm.observable.asset.ReferenceSwapCurve;
import cdm.product.asset.metafields.ReferenceWithMetaFixedRateSpecification;
import cdm.product.template.AveragingStrikeFeature;
import cdm.product.template.OptionStrike;
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

public class OptionStrikeValidator implements Validator<OptionStrike> {

	private List<ComparisonResult> getComparisonResults(OptionStrike o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("strikePrice", (Price) o.getStrikePrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("strikeReference", (ReferenceWithMetaFixedRateSpecification) o.getStrikeReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("referenceSwapCurve", (ReferenceSwapCurve) o.getReferenceSwapCurve() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingStrikeFeature", (AveragingStrikeFeature) o.getAveragingStrikeFeature() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionStrike> validate(RosettaPath path, OptionStrike o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionStrike", ValidationType.CARDINALITY, "OptionStrike", path, "", error);
		}
		return success("OptionStrike", ValidationType.CARDINALITY, "OptionStrike", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionStrike o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionStrike", ValidationType.CARDINALITY, "OptionStrike", path, "", res.getError());
				}
				return success("OptionStrike", ValidationType.CARDINALITY, "OptionStrike", path, "");
			})
			.collect(toList());
	}

}
