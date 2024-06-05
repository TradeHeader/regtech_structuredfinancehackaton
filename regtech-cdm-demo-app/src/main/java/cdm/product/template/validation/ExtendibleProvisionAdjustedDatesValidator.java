package cdm.product.template.validation;

import cdm.product.template.ExtendibleProvisionAdjustedDates;
import cdm.product.template.ExtensionEvent;
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

public class ExtendibleProvisionAdjustedDatesValidator implements Validator<ExtendibleProvisionAdjustedDates> {

	private List<ComparisonResult> getComparisonResults(ExtendibleProvisionAdjustedDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("extensionEvent", (List<? extends ExtensionEvent>) o.getExtensionEvent() == null ? 0 : ((List<? extends ExtensionEvent>) o.getExtensionEvent()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<ExtendibleProvisionAdjustedDates> validate(RosettaPath path, ExtendibleProvisionAdjustedDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExtendibleProvisionAdjustedDates", ValidationType.CARDINALITY, "ExtendibleProvisionAdjustedDates", path, "", error);
		}
		return success("ExtendibleProvisionAdjustedDates", ValidationType.CARDINALITY, "ExtendibleProvisionAdjustedDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExtendibleProvisionAdjustedDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExtendibleProvisionAdjustedDates", ValidationType.CARDINALITY, "ExtendibleProvisionAdjustedDates", path, "", res.getError());
				}
				return success("ExtendibleProvisionAdjustedDates", ValidationType.CARDINALITY, "ExtendibleProvisionAdjustedDates", path, "");
			})
			.collect(toList());
	}

}
