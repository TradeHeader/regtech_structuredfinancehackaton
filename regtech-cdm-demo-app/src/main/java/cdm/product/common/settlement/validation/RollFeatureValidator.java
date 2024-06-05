package cdm.product.common.settlement.validation;

import cdm.base.datetime.Offset;
import cdm.product.asset.RollSourceCalendarEnum;
import cdm.product.common.settlement.RollFeature;
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

public class RollFeatureValidator implements Validator<RollFeature> {

	private List<ComparisonResult> getComparisonResults(RollFeature o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rollSourceCalendar", (RollSourceCalendarEnum) o.getRollSourceCalendar() != null ? 1 : 0, 0, 1), 
				checkCardinality("deliveryDateRollConvention", (Offset) o.getDeliveryDateRollConvention() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<RollFeature> validate(RosettaPath path, RollFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RollFeature", ValidationType.CARDINALITY, "RollFeature", path, "", error);
		}
		return success("RollFeature", ValidationType.CARDINALITY, "RollFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RollFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RollFeature", ValidationType.CARDINALITY, "RollFeature", path, "", res.getError());
				}
				return success("RollFeature", ValidationType.CARDINALITY, "RollFeature", path, "");
			})
			.collect(toList());
	}

}
