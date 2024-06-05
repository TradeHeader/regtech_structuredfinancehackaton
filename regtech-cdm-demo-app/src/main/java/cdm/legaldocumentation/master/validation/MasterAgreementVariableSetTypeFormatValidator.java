package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
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

public class MasterAgreementVariableSetTypeFormatValidator implements Validator<MasterAgreementVariableSet> {

	private List<ComparisonResult> getComparisonResults(MasterAgreementVariableSet o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterAgreementVariableSet", ValidationType.TYPE_FORMAT, "MasterAgreementVariableSet", path, "", error);
		}
		return success("MasterAgreementVariableSet", ValidationType.TYPE_FORMAT, "MasterAgreementVariableSet", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterAgreementVariableSet o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterAgreementVariableSet", ValidationType.TYPE_FORMAT, "MasterAgreementVariableSet", path, "", res.getError());
				}
				return success("MasterAgreementVariableSet", ValidationType.TYPE_FORMAT, "MasterAgreementVariableSet", path, "");
			})
			.collect(toList());
	}

}
