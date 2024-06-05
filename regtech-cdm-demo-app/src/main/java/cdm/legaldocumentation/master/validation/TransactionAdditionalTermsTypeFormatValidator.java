package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.TransactionAdditionalTerms;
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

public class TransactionAdditionalTermsTypeFormatValidator implements Validator<TransactionAdditionalTerms> {

	private List<ComparisonResult> getComparisonResults(TransactionAdditionalTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TransactionAdditionalTerms> validate(RosettaPath path, TransactionAdditionalTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TransactionAdditionalTerms", ValidationType.TYPE_FORMAT, "TransactionAdditionalTerms", path, "", error);
		}
		return success("TransactionAdditionalTerms", ValidationType.TYPE_FORMAT, "TransactionAdditionalTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TransactionAdditionalTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TransactionAdditionalTerms", ValidationType.TYPE_FORMAT, "TransactionAdditionalTerms", path, "", res.getError());
				}
				return success("TransactionAdditionalTerms", ValidationType.TYPE_FORMAT, "TransactionAdditionalTerms", path, "");
			})
			.collect(toList());
	}

}
