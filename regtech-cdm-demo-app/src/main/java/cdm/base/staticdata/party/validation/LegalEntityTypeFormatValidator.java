package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.LegalEntity;
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

public class LegalEntityTypeFormatValidator implements Validator<LegalEntity> {

	private List<ComparisonResult> getComparisonResults(LegalEntity o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<LegalEntity> validate(RosettaPath path, LegalEntity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LegalEntity", ValidationType.TYPE_FORMAT, "LegalEntity", path, "", error);
		}
		return success("LegalEntity", ValidationType.TYPE_FORMAT, "LegalEntity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalEntity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalEntity", ValidationType.TYPE_FORMAT, "LegalEntity", path, "", res.getError());
				}
				return success("LegalEntity", ValidationType.TYPE_FORMAT, "LegalEntity", path, "");
			})
			.collect(toList());
	}

}
