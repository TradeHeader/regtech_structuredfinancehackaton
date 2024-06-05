package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.ContractualMatrix;
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

public class ContractualMatrixTypeFormatValidator implements Validator<ContractualMatrix> {

	private List<ComparisonResult> getComparisonResults(ContractualMatrix o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ContractualMatrix> validate(RosettaPath path, ContractualMatrix o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContractualMatrix", ValidationType.TYPE_FORMAT, "ContractualMatrix", path, "", error);
		}
		return success("ContractualMatrix", ValidationType.TYPE_FORMAT, "ContractualMatrix", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractualMatrix o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractualMatrix", ValidationType.TYPE_FORMAT, "ContractualMatrix", path, "", res.getError());
				}
				return success("ContractualMatrix", ValidationType.TYPE_FORMAT, "ContractualMatrix", path, "");
			})
			.collect(toList());
	}

}
