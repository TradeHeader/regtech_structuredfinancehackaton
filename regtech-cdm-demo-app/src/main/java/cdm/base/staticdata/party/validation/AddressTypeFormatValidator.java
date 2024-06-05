package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.Address;
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

public class AddressTypeFormatValidator implements Validator<Address> {

	private List<ComparisonResult> getComparisonResults(Address o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Address> validate(RosettaPath path, Address o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Address", ValidationType.TYPE_FORMAT, "Address", path, "", error);
		}
		return success("Address", ValidationType.TYPE_FORMAT, "Address", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Address o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Address", ValidationType.TYPE_FORMAT, "Address", path, "", res.getError());
				}
				return success("Address", ValidationType.TYPE_FORMAT, "Address", path, "");
			})
			.collect(toList());
	}

}
