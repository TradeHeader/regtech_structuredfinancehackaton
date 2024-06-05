package cdm.product.asset.validation;

import cdm.product.asset.Tranche;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TrancheValidator implements Validator<Tranche> {

	private List<ComparisonResult> getComparisonResults(Tranche o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("attachmentPoint", (BigDecimal) o.getAttachmentPoint() != null ? 1 : 0, 1, 1), 
				checkCardinality("exhaustionPoint", (BigDecimal) o.getExhaustionPoint() != null ? 1 : 0, 1, 1), 
				checkCardinality("incurredRecoveryApplicable", (Boolean) o.getIncurredRecoveryApplicable() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Tranche> validate(RosettaPath path, Tranche o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Tranche", ValidationType.CARDINALITY, "Tranche", path, "", error);
		}
		return success("Tranche", ValidationType.CARDINALITY, "Tranche", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Tranche o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Tranche", ValidationType.CARDINALITY, "Tranche", path, "", res.getError());
				}
				return success("Tranche", ValidationType.CARDINALITY, "Tranche", path, "");
			})
			.collect(toList());
	}

}
