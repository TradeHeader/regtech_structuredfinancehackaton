package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.ContactInformation;
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

public class ContactInformationTypeFormatValidator implements Validator<ContactInformation> {

	private List<ComparisonResult> getComparisonResults(ContactInformation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ContactInformation> validate(RosettaPath path, ContactInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContactInformation", ValidationType.TYPE_FORMAT, "ContactInformation", path, "", error);
		}
		return success("ContactInformation", ValidationType.TYPE_FORMAT, "ContactInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContactInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContactInformation", ValidationType.TYPE_FORMAT, "ContactInformation", path, "", res.getError());
				}
				return success("ContactInformation", ValidationType.TYPE_FORMAT, "ContactInformation", path, "");
			})
			.collect(toList());
	}

}
