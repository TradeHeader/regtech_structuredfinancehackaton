package cdm.product.asset.validation;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.ReferencePool;
import cdm.product.asset.Tranche;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BasketReferenceInformationValidator implements Validator<BasketReferenceInformation> {

	private List<ComparisonResult> getComparisonResults(BasketReferenceInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("basketName", (FieldWithMetaString) o.getBasketName() != null ? 1 : 0, 0, 1), 
				checkCardinality("referencePool", (ReferencePool) o.getReferencePool() != null ? 1 : 0, 1, 1), 
				checkCardinality("nthToDefault", (Integer) o.getNthToDefault() != null ? 1 : 0, 0, 1), 
				checkCardinality("mthToDefault", (Integer) o.getMthToDefault() != null ? 1 : 0, 0, 1), 
				checkCardinality("tranche", (Tranche) o.getTranche() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BasketReferenceInformation> validate(RosettaPath path, BasketReferenceInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BasketReferenceInformation", ValidationType.CARDINALITY, "BasketReferenceInformation", path, "", error);
		}
		return success("BasketReferenceInformation", ValidationType.CARDINALITY, "BasketReferenceInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BasketReferenceInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BasketReferenceInformation", ValidationType.CARDINALITY, "BasketReferenceInformation", path, "", res.getError());
				}
				return success("BasketReferenceInformation", ValidationType.CARDINALITY, "BasketReferenceInformation", path, "");
			})
			.collect(toList());
	}

}
