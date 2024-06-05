package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.BusinessDayAdjustments;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AdjustableOrAdjustedDateValidator implements Validator<AdjustableOrAdjustedDate> {

	private List<ComparisonResult> getComparisonResults(AdjustableOrAdjustedDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("unadjustedDate", (Date) o.getUnadjustedDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("dateAdjustments", (BusinessDayAdjustments) o.getDateAdjustments() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedDate", (FieldWithMetaDate) o.getAdjustedDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AdjustableOrAdjustedDate> validate(RosettaPath path, AdjustableOrAdjustedDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdjustableOrAdjustedDate", ValidationType.CARDINALITY, "AdjustableOrAdjustedDate", path, "", error);
		}
		return success("AdjustableOrAdjustedDate", ValidationType.CARDINALITY, "AdjustableOrAdjustedDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustableOrAdjustedDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustableOrAdjustedDate", ValidationType.CARDINALITY, "AdjustableOrAdjustedDate", path, "", res.getError());
				}
				return success("AdjustableOrAdjustedDate", ValidationType.CARDINALITY, "AdjustableOrAdjustedDate", path, "");
			})
			.collect(toList());
	}

}
