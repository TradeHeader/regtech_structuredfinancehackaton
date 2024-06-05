package cdm.event.common.validation;

import cdm.event.common.ContractDetails;
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

public class ContractDetailsTypeFormatValidator implements Validator<ContractDetails> {

	private List<ComparisonResult> getComparisonResults(ContractDetails o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ContractDetails> validate(RosettaPath path, ContractDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContractDetails", ValidationType.TYPE_FORMAT, "ContractDetails", path, "", error);
		}
		return success("ContractDetails", ValidationType.TYPE_FORMAT, "ContractDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractDetails", ValidationType.TYPE_FORMAT, "ContractDetails", path, "", res.getError());
				}
				return success("ContractDetails", ValidationType.TYPE_FORMAT, "ContractDetails", path, "");
			})
			.collect(toList());
	}

}
