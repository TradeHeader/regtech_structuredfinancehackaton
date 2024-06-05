package cdm.product.collateral.validation;

import cdm.product.collateral.SubstitutionProvisions;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SubstitutionProvisionsTypeFormatValidator implements Validator<SubstitutionProvisions> {

	private List<ComparisonResult> getComparisonResults(SubstitutionProvisions o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("numberOfSubstitutionsAllowed", o.getNumberOfSubstitutionsAllowed(), empty(), of(0), empty(), empty())
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
			return failure("SubstitutionProvisions", ValidationType.TYPE_FORMAT, "SubstitutionProvisions", path, "", error);
		}
		return success("SubstitutionProvisions", ValidationType.TYPE_FORMAT, "SubstitutionProvisions", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SubstitutionProvisions o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SubstitutionProvisions", ValidationType.TYPE_FORMAT, "SubstitutionProvisions", path, "", res.getError());
				}
				return success("SubstitutionProvisions", ValidationType.TYPE_FORMAT, "SubstitutionProvisions", path, "");
			})
			.collect(toList());
	}

}
