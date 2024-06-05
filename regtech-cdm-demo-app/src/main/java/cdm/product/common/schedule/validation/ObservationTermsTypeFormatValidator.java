package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ObservationTerms;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ObservationTermsTypeFormatValidator implements Validator<ObservationTerms> {

	private List<ComparisonResult> getComparisonResults(ObservationTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("numberOfObservationDates", o.getNumberOfObservationDates(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<ObservationTerms> validate(RosettaPath path, ObservationTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationTerms", ValidationType.TYPE_FORMAT, "ObservationTerms", path, "", error);
		}
		return success("ObservationTerms", ValidationType.TYPE_FORMAT, "ObservationTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationTerms", ValidationType.TYPE_FORMAT, "ObservationTerms", path, "", res.getError());
				}
				return success("ObservationTerms", ValidationType.TYPE_FORMAT, "ObservationTerms", path, "");
			})
			.collect(toList());
	}

}
