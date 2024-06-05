package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.MasterAgreementClause;
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

public class MasterAgreementClauseTypeFormatValidator implements Validator<MasterAgreementClause> {

	private List<ComparisonResult> getComparisonResults(MasterAgreementClause o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MasterAgreementClause> validate(RosettaPath path, MasterAgreementClause o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterAgreementClause", ValidationType.TYPE_FORMAT, "MasterAgreementClause", path, "", error);
		}
		return success("MasterAgreementClause", ValidationType.TYPE_FORMAT, "MasterAgreementClause", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterAgreementClause o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterAgreementClause", ValidationType.TYPE_FORMAT, "MasterAgreementClause", path, "", res.getError());
				}
				return success("MasterAgreementClause", ValidationType.TYPE_FORMAT, "MasterAgreementClause", path, "");
			})
			.collect(toList());
	}

}
