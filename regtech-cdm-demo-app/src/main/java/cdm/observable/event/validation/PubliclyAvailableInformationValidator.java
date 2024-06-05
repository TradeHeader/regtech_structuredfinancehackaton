package cdm.observable.event.validation;

import cdm.observable.event.PubliclyAvailableInformation;
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

public class PubliclyAvailableInformationValidator implements Validator<PubliclyAvailableInformation> {

	private List<ComparisonResult> getComparisonResults(PubliclyAvailableInformation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("standardPublicSources", (Boolean) o.getStandardPublicSources() != null ? 1 : 0, 0, 1), 
				checkCardinality("specifiedNumber", (Integer) o.getSpecifiedNumber() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PubliclyAvailableInformation> validate(RosettaPath path, PubliclyAvailableInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PubliclyAvailableInformation", ValidationType.CARDINALITY, "PubliclyAvailableInformation", path, "", error);
		}
		return success("PubliclyAvailableInformation", ValidationType.CARDINALITY, "PubliclyAvailableInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PubliclyAvailableInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PubliclyAvailableInformation", ValidationType.CARDINALITY, "PubliclyAvailableInformation", path, "", res.getError());
				}
				return success("PubliclyAvailableInformation", ValidationType.CARDINALITY, "PubliclyAvailableInformation", path, "");
			})
			.collect(toList());
	}

}
