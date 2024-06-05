package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTermEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTypeEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ContractualMatrixValidator implements Validator<ContractualMatrix> {

	private List<ComparisonResult> getComparisonResults(ContractualMatrix o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("matrixType", (FieldWithMetaMatrixTypeEnum) o.getMatrixType() != null ? 1 : 0, 1, 1), 
				checkCardinality("matrixTerm", (FieldWithMetaMatrixTermEnum) o.getMatrixTerm() != null ? 1 : 0, 0, 1)
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
			return failure("ContractualMatrix", ValidationType.CARDINALITY, "ContractualMatrix", path, "", error);
		}
		return success("ContractualMatrix", ValidationType.CARDINALITY, "ContractualMatrix", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractualMatrix o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractualMatrix", ValidationType.CARDINALITY, "ContractualMatrix", path, "", res.getError());
				}
				return success("ContractualMatrix", ValidationType.CARDINALITY, "ContractualMatrix", path, "");
			})
			.collect(toList());
	}

}
