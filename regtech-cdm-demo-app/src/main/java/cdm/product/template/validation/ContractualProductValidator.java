package cdm.product.template.validation;

import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
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

public class ContractualProductValidator implements Validator<ContractualProduct> {

	private List<ComparisonResult> getComparisonResults(ContractualProduct o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("economicTerms", (EconomicTerms) o.getEconomicTerms() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ContractualProduct> validate(RosettaPath path, ContractualProduct o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContractualProduct", ValidationType.CARDINALITY, "ContractualProduct", path, "", error);
		}
		return success("ContractualProduct", ValidationType.CARDINALITY, "ContractualProduct", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractualProduct o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractualProduct", ValidationType.CARDINALITY, "ContractualProduct", path, "", res.getError());
				}
				return success("ContractualProduct", ValidationType.CARDINALITY, "ContractualProduct", path, "");
			})
			.collect(toList());
	}

}
