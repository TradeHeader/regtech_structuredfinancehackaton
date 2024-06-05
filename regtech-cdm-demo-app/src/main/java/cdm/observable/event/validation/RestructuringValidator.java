package cdm.observable.event.validation;

import cdm.observable.event.Restructuring;
import cdm.observable.event.metafields.FieldWithMetaRestructuringEnum;
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

public class RestructuringValidator implements Validator<Restructuring> {

	private List<ComparisonResult> getComparisonResults(Restructuring o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("restructuringType", (FieldWithMetaRestructuringEnum) o.getRestructuringType() != null ? 1 : 0, 0, 1), 
				checkCardinality("multipleHolderObligation", (Boolean) o.getMultipleHolderObligation() != null ? 1 : 0, 0, 1), 
				checkCardinality("multipleCreditEventNotices", (Boolean) o.getMultipleCreditEventNotices() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Restructuring> validate(RosettaPath path, Restructuring o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Restructuring", ValidationType.CARDINALITY, "Restructuring", path, "", error);
		}
		return success("Restructuring", ValidationType.CARDINALITY, "Restructuring", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Restructuring o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Restructuring", ValidationType.CARDINALITY, "Restructuring", path, "", res.getError());
				}
				return success("Restructuring", ValidationType.CARDINALITY, "Restructuring", path, "");
			})
			.collect(toList());
	}

}
