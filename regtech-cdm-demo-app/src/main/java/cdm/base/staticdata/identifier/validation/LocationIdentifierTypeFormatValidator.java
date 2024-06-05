package cdm.base.staticdata.identifier.validation;

import cdm.base.staticdata.identifier.LocationIdentifier;
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

public class LocationIdentifierTypeFormatValidator implements Validator<LocationIdentifier> {

	private List<ComparisonResult> getComparisonResults(LocationIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<LocationIdentifier> validate(RosettaPath path, LocationIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LocationIdentifier", ValidationType.TYPE_FORMAT, "LocationIdentifier", path, "", error);
		}
		return success("LocationIdentifier", ValidationType.TYPE_FORMAT, "LocationIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LocationIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LocationIdentifier", ValidationType.TYPE_FORMAT, "LocationIdentifier", path, "", res.getError());
				}
				return success("LocationIdentifier", ValidationType.TYPE_FORMAT, "LocationIdentifier", path, "");
			})
			.collect(toList());
	}

}
