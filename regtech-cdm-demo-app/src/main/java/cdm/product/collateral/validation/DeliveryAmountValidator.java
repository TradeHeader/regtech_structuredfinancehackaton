package cdm.product.collateral.validation;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.DeliveryAmountElectionEnum;
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

public class DeliveryAmountValidator implements Validator<DeliveryAmount> {

	private List<ComparisonResult> getComparisonResults(DeliveryAmount o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("standardElection", (DeliveryAmountElectionEnum) o.getStandardElection() != null ? 1 : 0, 0, 1), 
				checkCardinality("customElection", (String) o.getCustomElection() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DeliveryAmount> validate(RosettaPath path, DeliveryAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeliveryAmount", ValidationType.CARDINALITY, "DeliveryAmount", path, "", error);
		}
		return success("DeliveryAmount", ValidationType.CARDINALITY, "DeliveryAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeliveryAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeliveryAmount", ValidationType.CARDINALITY, "DeliveryAmount", path, "", res.getError());
				}
				return success("DeliveryAmount", ValidationType.CARDINALITY, "DeliveryAmount", path, "");
			})
			.collect(toList());
	}

}
