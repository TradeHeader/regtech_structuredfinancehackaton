package cdm.event.common.validation;

import cdm.event.common.SecurityLendingInvoice;
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

public class SecurityLendingInvoiceTypeFormatValidator implements Validator<SecurityLendingInvoice> {

	private List<ComparisonResult> getComparisonResults(SecurityLendingInvoice o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityLendingInvoice> validate(RosettaPath path, SecurityLendingInvoice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityLendingInvoice", ValidationType.TYPE_FORMAT, "SecurityLendingInvoice", path, "", error);
		}
		return success("SecurityLendingInvoice", ValidationType.TYPE_FORMAT, "SecurityLendingInvoice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityLendingInvoice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityLendingInvoice", ValidationType.TYPE_FORMAT, "SecurityLendingInvoice", path, "", res.getError());
				}
				return success("SecurityLendingInvoice", ValidationType.TYPE_FORMAT, "SecurityLendingInvoice", path, "");
			})
			.collect(toList());
	}

}
