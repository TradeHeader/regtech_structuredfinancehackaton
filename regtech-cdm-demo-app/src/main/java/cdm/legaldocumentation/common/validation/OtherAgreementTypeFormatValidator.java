package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.OtherAgreement;
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

public class OtherAgreementTypeFormatValidator implements Validator<OtherAgreement> {

	private List<ComparisonResult> getComparisonResults(OtherAgreement o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<OtherAgreement> validate(RosettaPath path, OtherAgreement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OtherAgreement", ValidationType.TYPE_FORMAT, "OtherAgreement", path, "", error);
		}
		return success("OtherAgreement", ValidationType.TYPE_FORMAT, "OtherAgreement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OtherAgreement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OtherAgreement", ValidationType.TYPE_FORMAT, "OtherAgreement", path, "", res.getError());
				}
				return success("OtherAgreement", ValidationType.TYPE_FORMAT, "OtherAgreement", path, "");
			})
			.collect(toList());
	}

}
