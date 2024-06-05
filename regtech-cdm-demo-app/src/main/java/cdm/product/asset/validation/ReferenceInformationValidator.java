package cdm.product.asset.validation;

import cdm.base.staticdata.party.LegalEntity;
import cdm.observable.asset.Price;
import cdm.product.asset.ReferenceInformation;
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

public class ReferenceInformationValidator implements Validator<ReferenceInformation> {

	private List<ComparisonResult> getComparisonResults(ReferenceInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referenceEntity", (LegalEntity) o.getReferenceEntity() != null ? 1 : 0, 1, 1), 
				checkCardinality("noReferenceObligation", (Boolean) o.getNoReferenceObligation() != null ? 1 : 0, 0, 1), 
				checkCardinality("unknownReferenceObligation", (Boolean) o.getUnknownReferenceObligation() != null ? 1 : 0, 0, 1), 
				checkCardinality("allGuarantees", (Boolean) o.getAllGuarantees() != null ? 1 : 0, 0, 1), 
				checkCardinality("referencePrice", (Price) o.getReferencePrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("referencePolicy", (Boolean) o.getReferencePolicy() != null ? 1 : 0, 0, 1), 
				checkCardinality("securedList", (Boolean) o.getSecuredList() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ReferenceInformation> validate(RosettaPath path, ReferenceInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferenceInformation", ValidationType.CARDINALITY, "ReferenceInformation", path, "", error);
		}
		return success("ReferenceInformation", ValidationType.CARDINALITY, "ReferenceInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferenceInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferenceInformation", ValidationType.CARDINALITY, "ReferenceInformation", path, "", res.getError());
				}
				return success("ReferenceInformation", ValidationType.CARDINALITY, "ReferenceInformation", path, "");
			})
			.collect(toList());
	}

}
