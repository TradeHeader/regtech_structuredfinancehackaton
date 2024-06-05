package cdm.product.asset.validation;

import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.metafields.FieldWithMetaSettledEntityMatrixSourceEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class SettledEntityMatrixValidator implements Validator<SettledEntityMatrix> {

	private List<ComparisonResult> getComparisonResults(SettledEntityMatrix o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("matrixSource", (FieldWithMetaSettledEntityMatrixSourceEnum) o.getMatrixSource() != null ? 1 : 0, 1, 1), 
				checkCardinality("publicationDate", (Date) o.getPublicationDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SettledEntityMatrix> validate(RosettaPath path, SettledEntityMatrix o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettledEntityMatrix", ValidationType.CARDINALITY, "SettledEntityMatrix", path, "", error);
		}
		return success("SettledEntityMatrix", ValidationType.CARDINALITY, "SettledEntityMatrix", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettledEntityMatrix o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettledEntityMatrix", ValidationType.CARDINALITY, "SettledEntityMatrix", path, "", res.getError());
				}
				return success("SettledEntityMatrix", ValidationType.CARDINALITY, "SettledEntityMatrix", path, "");
			})
			.collect(toList());
	}

}
