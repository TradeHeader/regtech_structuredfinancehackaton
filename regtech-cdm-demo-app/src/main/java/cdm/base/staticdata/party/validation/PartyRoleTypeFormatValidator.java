package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.PartyRole;
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

public class PartyRoleTypeFormatValidator implements Validator<PartyRole> {

	private List<ComparisonResult> getComparisonResults(PartyRole o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PartyRole> validate(RosettaPath path, PartyRole o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyRole", ValidationType.TYPE_FORMAT, "PartyRole", path, "", error);
		}
		return success("PartyRole", ValidationType.TYPE_FORMAT, "PartyRole", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyRole o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyRole", ValidationType.TYPE_FORMAT, "PartyRole", path, "", res.getError());
				}
				return success("PartyRole", ValidationType.TYPE_FORMAT, "PartyRole", path, "");
			})
			.collect(toList());
	}

}
