package cdm.product.collateral.validation;

import cdm.product.collateral.DistributionAndInterestPayment;
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

public class DistributionAndInterestPaymentValidator implements Validator<DistributionAndInterestPayment> {

	private List<ComparisonResult> getComparisonResults(DistributionAndInterestPayment o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DistributionAndInterestPayment> validate(RosettaPath path, DistributionAndInterestPayment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DistributionAndInterestPayment", ValidationType.CARDINALITY, "DistributionAndInterestPayment", path, "", error);
		}
		return success("DistributionAndInterestPayment", ValidationType.CARDINALITY, "DistributionAndInterestPayment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DistributionAndInterestPayment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DistributionAndInterestPayment", ValidationType.CARDINALITY, "DistributionAndInterestPayment", path, "", res.getError());
				}
				return success("DistributionAndInterestPayment", ValidationType.CARDINALITY, "DistributionAndInterestPayment", path, "");
			})
			.collect(toList());
	}

}
