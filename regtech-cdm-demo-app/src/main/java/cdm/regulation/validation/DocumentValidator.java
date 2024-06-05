package cdm.regulation.validation;

import cdm.regulation.Document;
import cdm.regulation.FinInstrmRptgTxRpt;
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

public class DocumentValidator implements Validator<Document> {

	private List<ComparisonResult> getComparisonResults(Document o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("finInstrmRptgTxRpt", (FinInstrmRptgTxRpt) o.getFinInstrmRptgTxRpt() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Document> validate(RosettaPath path, Document o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Document", ValidationType.CARDINALITY, "Document", path, "", error);
		}
		return success("Document", ValidationType.CARDINALITY, "Document", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Document o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Document", ValidationType.CARDINALITY, "Document", path, "", res.getError());
				}
				return success("Document", ValidationType.CARDINALITY, "Document", path, "");
			})
			.collect(toList());
	}

}
