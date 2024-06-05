package cdm.product.asset.validation;

import cdm.base.datetime.Offset;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendDateReferenceEnum;
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

public class DividendDateReferenceValidator implements Validator<DividendDateReference> {

	private List<ComparisonResult> getComparisonResults(DividendDateReference o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dateReference", (DividendDateReferenceEnum) o.getDateReference() != null ? 1 : 0, 1, 1), 
				checkCardinality("paymentDateOffset", (Offset) o.getPaymentDateOffset() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DividendDateReference> validate(RosettaPath path, DividendDateReference o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendDateReference", ValidationType.CARDINALITY, "DividendDateReference", path, "", error);
		}
		return success("DividendDateReference", ValidationType.CARDINALITY, "DividendDateReference", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendDateReference o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendDateReference", ValidationType.CARDINALITY, "DividendDateReference", path, "", res.getError());
				}
				return success("DividendDateReference", ValidationType.CARDINALITY, "DividendDateReference", path, "");
			})
			.collect(toList());
	}

}
