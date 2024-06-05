package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.MasterAgreementSchedule;
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

public class MasterAgreementScheduleTypeFormatValidator implements Validator<MasterAgreementSchedule> {

	private List<ComparisonResult> getComparisonResults(MasterAgreementSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MasterAgreementSchedule> validate(RosettaPath path, MasterAgreementSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterAgreementSchedule", ValidationType.TYPE_FORMAT, "MasterAgreementSchedule", path, "", error);
		}
		return success("MasterAgreementSchedule", ValidationType.TYPE_FORMAT, "MasterAgreementSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterAgreementSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterAgreementSchedule", ValidationType.TYPE_FORMAT, "MasterAgreementSchedule", path, "", res.getError());
				}
				return success("MasterAgreementSchedule", ValidationType.TYPE_FORMAT, "MasterAgreementSchedule", path, "");
			})
			.collect(toList());
	}

}
