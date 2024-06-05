package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DeterminationRolesAndTermsTypeFormatValidator implements Validator<DeterminationRolesAndTerms> {

	private List<ComparisonResult> getComparisonResults(DeterminationRolesAndTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DeterminationRolesAndTerms> validate(RosettaPath path, DeterminationRolesAndTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeterminationRolesAndTerms", ValidationType.TYPE_FORMAT, "DeterminationRolesAndTerms", path, "", error);
		}
		return success("DeterminationRolesAndTerms", ValidationType.TYPE_FORMAT, "DeterminationRolesAndTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeterminationRolesAndTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeterminationRolesAndTerms", ValidationType.TYPE_FORMAT, "DeterminationRolesAndTerms", path, "", res.getError());
				}
				return success("DeterminationRolesAndTerms", ValidationType.TYPE_FORMAT, "DeterminationRolesAndTerms", path, "");
			})
			.collect(toList());
	}

}
