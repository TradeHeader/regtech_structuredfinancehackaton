package cdm.event.common.validation;

import cdm.event.common.CorporateAction;
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

public class CorporateActionTypeFormatValidator implements Validator<CorporateAction> {

	private List<ComparisonResult> getComparisonResults(CorporateAction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CorporateAction> validate(RosettaPath path, CorporateAction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CorporateAction", ValidationType.TYPE_FORMAT, "CorporateAction", path, "", error);
		}
		return success("CorporateAction", ValidationType.TYPE_FORMAT, "CorporateAction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CorporateAction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CorporateAction", ValidationType.TYPE_FORMAT, "CorporateAction", path, "", res.getError());
				}
				return success("CorporateAction", ValidationType.TYPE_FORMAT, "CorporateAction", path, "");
			})
			.collect(toList());
	}

}
