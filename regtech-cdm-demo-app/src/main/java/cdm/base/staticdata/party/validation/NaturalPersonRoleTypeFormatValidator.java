package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.NaturalPersonRole;
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

public class NaturalPersonRoleTypeFormatValidator implements Validator<NaturalPersonRole> {

	private List<ComparisonResult> getComparisonResults(NaturalPersonRole o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<NaturalPersonRole> validate(RosettaPath path, NaturalPersonRole o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NaturalPersonRole", ValidationType.TYPE_FORMAT, "NaturalPersonRole", path, "", error);
		}
		return success("NaturalPersonRole", ValidationType.TYPE_FORMAT, "NaturalPersonRole", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NaturalPersonRole o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NaturalPersonRole", ValidationType.TYPE_FORMAT, "NaturalPersonRole", path, "", res.getError());
				}
				return success("NaturalPersonRole", ValidationType.TYPE_FORMAT, "NaturalPersonRole", path, "");
			})
			.collect(toList());
	}

}
