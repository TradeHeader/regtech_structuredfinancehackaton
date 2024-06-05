package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.Cashflow;
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

public class CashflowTypeFormatValidator implements Validator<Cashflow> {

	private List<ComparisonResult> getComparisonResults(Cashflow o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Cashflow> validate(RosettaPath path, Cashflow o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Cashflow", ValidationType.TYPE_FORMAT, "Cashflow", path, "", error);
		}
		return success("Cashflow", ValidationType.TYPE_FORMAT, "Cashflow", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Cashflow o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Cashflow", ValidationType.TYPE_FORMAT, "Cashflow", path, "", res.getError());
				}
				return success("Cashflow", ValidationType.TYPE_FORMAT, "Cashflow", path, "");
			})
			.collect(toList());
	}

}
