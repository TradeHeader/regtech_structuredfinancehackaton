package cdm.product.template.validation;

import cdm.product.template.EconomicTerms;
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

public class EconomicTermsTypeFormatValidator implements Validator<EconomicTerms> {

	private List<ComparisonResult> getComparisonResults(EconomicTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EconomicTerms", ValidationType.TYPE_FORMAT, "EconomicTerms", path, "", error);
		}
		return success("EconomicTerms", ValidationType.TYPE_FORMAT, "EconomicTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EconomicTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EconomicTerms", ValidationType.TYPE_FORMAT, "EconomicTerms", path, "", res.getError());
				}
				return success("EconomicTerms", ValidationType.TYPE_FORMAT, "EconomicTerms", path, "");
			})
			.collect(toList());
	}

}
