package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PhysicalSettlementTerms;
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

public class PhysicalSettlementTermsTypeFormatValidator implements Validator<PhysicalSettlementTerms> {

	private List<ComparisonResult> getComparisonResults(PhysicalSettlementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PhysicalSettlementTerms> validate(RosettaPath path, PhysicalSettlementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PhysicalSettlementTerms", ValidationType.TYPE_FORMAT, "PhysicalSettlementTerms", path, "", error);
		}
		return success("PhysicalSettlementTerms", ValidationType.TYPE_FORMAT, "PhysicalSettlementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PhysicalSettlementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PhysicalSettlementTerms", ValidationType.TYPE_FORMAT, "PhysicalSettlementTerms", path, "", res.getError());
				}
				return success("PhysicalSettlementTerms", ValidationType.TYPE_FORMAT, "PhysicalSettlementTerms", path, "");
			})
			.collect(toList());
	}

}
