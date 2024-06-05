package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IndexReferenceInformationValidator implements Validator<IndexReferenceInformation> {

	private List<ComparisonResult> getComparisonResults(IndexReferenceInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("indexName", (FieldWithMetaString) o.getIndexName() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<IndexReferenceInformation> validate(RosettaPath path, IndexReferenceInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IndexReferenceInformation", ValidationType.CARDINALITY, "IndexReferenceInformation", path, "", error);
		}
		return success("IndexReferenceInformation", ValidationType.CARDINALITY, "IndexReferenceInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IndexReferenceInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IndexReferenceInformation", ValidationType.CARDINALITY, "IndexReferenceInformation", path, "", res.getError());
				}
				return success("IndexReferenceInformation", ValidationType.CARDINALITY, "IndexReferenceInformation", path, "");
			})
			.collect(toList());
	}

}
