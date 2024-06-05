package cdm.product.collateral.validation;

import cdm.product.collateral.ContactElection;
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

public class ContactElectionTypeFormatValidator implements Validator<ContactElection> {

	private List<ComparisonResult> getComparisonResults(ContactElection o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ContactElection> validate(RosettaPath path, ContactElection o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContactElection", ValidationType.TYPE_FORMAT, "ContactElection", path, "", error);
		}
		return success("ContactElection", ValidationType.TYPE_FORMAT, "ContactElection", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContactElection o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContactElection", ValidationType.TYPE_FORMAT, "ContactElection", path, "", res.getError());
				}
				return success("ContactElection", ValidationType.TYPE_FORMAT, "ContactElection", path, "");
			})
			.collect(toList());
	}

}
