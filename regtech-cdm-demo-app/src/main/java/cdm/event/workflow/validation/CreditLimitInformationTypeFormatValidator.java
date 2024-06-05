package cdm.event.workflow.validation;

import cdm.event.workflow.CreditLimitInformation;
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

public class CreditLimitInformationTypeFormatValidator implements Validator<CreditLimitInformation> {

	private List<ComparisonResult> getComparisonResults(CreditLimitInformation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CreditLimitInformation> validate(RosettaPath path, CreditLimitInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditLimitInformation", ValidationType.TYPE_FORMAT, "CreditLimitInformation", path, "", error);
		}
		return success("CreditLimitInformation", ValidationType.TYPE_FORMAT, "CreditLimitInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditLimitInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditLimitInformation", ValidationType.TYPE_FORMAT, "CreditLimitInformation", path, "", res.getError());
				}
				return success("CreditLimitInformation", ValidationType.TYPE_FORMAT, "CreditLimitInformation", path, "");
			})
			.collect(toList());
	}

}
