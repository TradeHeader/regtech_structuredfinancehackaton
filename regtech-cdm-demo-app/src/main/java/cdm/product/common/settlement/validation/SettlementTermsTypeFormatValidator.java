package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.SettlementTerms;
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

public class SettlementTermsTypeFormatValidator implements Validator<SettlementTerms> {

	private List<ComparisonResult> getComparisonResults(SettlementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SettlementTerms> validate(RosettaPath path, SettlementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementTerms", ValidationType.TYPE_FORMAT, "SettlementTerms", path, "", error);
		}
		return success("SettlementTerms", ValidationType.TYPE_FORMAT, "SettlementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementTerms", ValidationType.TYPE_FORMAT, "SettlementTerms", path, "", res.getError());
				}
				return success("SettlementTerms", ValidationType.TYPE_FORMAT, "SettlementTerms", path, "");
			})
			.collect(toList());
	}

}
