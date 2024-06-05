package cdm.product.template.validation;

import cdm.product.template.TerminationProvision;
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

public class TerminationProvisionTypeFormatValidator implements Validator<TerminationProvision> {

	private List<ComparisonResult> getComparisonResults(TerminationProvision o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TerminationProvision> validate(RosettaPath path, TerminationProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TerminationProvision", ValidationType.TYPE_FORMAT, "TerminationProvision", path, "", error);
		}
		return success("TerminationProvision", ValidationType.TYPE_FORMAT, "TerminationProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TerminationProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TerminationProvision", ValidationType.TYPE_FORMAT, "TerminationProvision", path, "", res.getError());
				}
				return success("TerminationProvision", ValidationType.TYPE_FORMAT, "TerminationProvision", path, "");
			})
			.collect(toList());
	}

}
