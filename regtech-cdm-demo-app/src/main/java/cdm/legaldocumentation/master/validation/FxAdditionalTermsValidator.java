package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.FxAdditionalTerms;
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

public class FxAdditionalTermsValidator implements Validator<FxAdditionalTerms> {

	private List<ComparisonResult> getComparisonResults(FxAdditionalTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxAdditionalTerms> validate(RosettaPath path, FxAdditionalTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxAdditionalTerms", ValidationType.CARDINALITY, "FxAdditionalTerms", path, "", error);
		}
		return success("FxAdditionalTerms", ValidationType.CARDINALITY, "FxAdditionalTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxAdditionalTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxAdditionalTerms", ValidationType.CARDINALITY, "FxAdditionalTerms", path, "", res.getError());
				}
				return success("FxAdditionalTerms", ValidationType.CARDINALITY, "FxAdditionalTerms", path, "");
			})
			.collect(toList());
	}

}
