package cdm.product.template.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.template.Composite;
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

public class CompositeValidator implements Validator<Composite> {

	private List<ComparisonResult> getComparisonResults(Composite o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("determinationMethod", (DeterminationMethodEnum) o.getDeterminationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("relativeDate", (RelativeDateOffset) o.getRelativeDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("fxSpotRateSource", (FxSpotRateSource) o.getFxSpotRateSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixingTime", (BusinessCenterTime) o.getFixingTime() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Composite> validate(RosettaPath path, Composite o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Composite", ValidationType.CARDINALITY, "Composite", path, "", error);
		}
		return success("Composite", ValidationType.CARDINALITY, "Composite", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Composite o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Composite", ValidationType.CARDINALITY, "Composite", path, "", res.getError());
				}
				return success("Composite", ValidationType.CARDINALITY, "Composite", path, "");
			})
			.collect(toList());
	}

}
