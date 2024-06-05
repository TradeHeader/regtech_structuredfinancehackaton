package cdm.event.common.validation;

import cdm.event.common.BillingRecord;
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

public class BillingRecordTypeFormatValidator implements Validator<BillingRecord> {

	private List<ComparisonResult> getComparisonResults(BillingRecord o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BillingRecord> validate(RosettaPath path, BillingRecord o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingRecord", ValidationType.TYPE_FORMAT, "BillingRecord", path, "", error);
		}
		return success("BillingRecord", ValidationType.TYPE_FORMAT, "BillingRecord", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingRecord o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingRecord", ValidationType.TYPE_FORMAT, "BillingRecord", path, "", res.getError());
				}
				return success("BillingRecord", ValidationType.TYPE_FORMAT, "BillingRecord", path, "");
			})
			.collect(toList());
	}

}
