package cdm.product.asset.validation;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.GeneralTerms;
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

public class GeneralTermsValidator implements Validator<GeneralTerms> {

	private List<ComparisonResult> getComparisonResults(GeneralTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referenceInformation", (ReferenceInformation) o.getReferenceInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("indexReferenceInformation", (CreditIndexReferenceInformation) o.getIndexReferenceInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("basketReferenceInformation", (BasketReferenceInformation) o.getBasketReferenceInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("substitution", (Boolean) o.getSubstitution() != null ? 1 : 0, 0, 1), 
				checkCardinality("modifiedEquityDelivery", (Boolean) o.getModifiedEquityDelivery() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("GeneralTerms", ValidationType.CARDINALITY, "GeneralTerms", path, "", error);
		}
		return success("GeneralTerms", ValidationType.CARDINALITY, "GeneralTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, GeneralTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("GeneralTerms", ValidationType.CARDINALITY, "GeneralTerms", path, "", res.getError());
				}
				return success("GeneralTerms", ValidationType.CARDINALITY, "GeneralTerms", path, "");
			})
			.collect(toList());
	}

}
