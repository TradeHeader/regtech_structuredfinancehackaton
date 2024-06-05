package cdm.product.collateral.validation;

import cdm.base.datetime.Period;
import cdm.product.collateral.SubstitutionProvisions;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SubstitutionProvisionsValidator implements Validator<SubstitutionProvisions> {

	private List<ComparisonResult> getComparisonResults(SubstitutionProvisions o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("numberOfSubstitutionsAllowed", (Integer) o.getNumberOfSubstitutionsAllowed() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticeDeadlinePeriod", (Period) o.getNoticeDeadlinePeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticeDeadlineDateTime", (ZonedDateTime) o.getNoticeDeadlineDateTime() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SubstitutionProvisions> validate(RosettaPath path, SubstitutionProvisions o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SubstitutionProvisions", ValidationType.CARDINALITY, "SubstitutionProvisions", path, "", error);
		}
		return success("SubstitutionProvisions", ValidationType.CARDINALITY, "SubstitutionProvisions", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SubstitutionProvisions o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SubstitutionProvisions", ValidationType.CARDINALITY, "SubstitutionProvisions", path, "", res.getError());
				}
				return success("SubstitutionProvisions", ValidationType.CARDINALITY, "SubstitutionProvisions", path, "");
			})
			.collect(toList());
	}

}
