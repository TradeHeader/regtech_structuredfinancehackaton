package cdm.product.template.validation;

import cdm.product.template.EarlyTerminationProvision;
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

public class EarlyTerminationProvisionTypeFormatValidator implements Validator<EarlyTerminationProvision> {

	private List<ComparisonResult> getComparisonResults(EarlyTerminationProvision o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EarlyTerminationProvision> validate(RosettaPath path, EarlyTerminationProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EarlyTerminationProvision", ValidationType.TYPE_FORMAT, "EarlyTerminationProvision", path, "", error);
		}
		return success("EarlyTerminationProvision", ValidationType.TYPE_FORMAT, "EarlyTerminationProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EarlyTerminationProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EarlyTerminationProvision", ValidationType.TYPE_FORMAT, "EarlyTerminationProvision", path, "", res.getError());
				}
				return success("EarlyTerminationProvision", ValidationType.TYPE_FORMAT, "EarlyTerminationProvision", path, "");
			})
			.collect(toList());
	}

}
