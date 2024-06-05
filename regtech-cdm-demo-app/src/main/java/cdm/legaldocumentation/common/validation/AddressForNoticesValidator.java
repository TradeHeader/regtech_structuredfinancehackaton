package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.AddressForNotices;
import cdm.product.collateral.ContactElection;
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

public class AddressForNoticesValidator implements Validator<AddressForNotices> {

	private List<ComparisonResult> getComparisonResults(AddressForNotices o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("primaryNotices", (ContactElection) o.getPrimaryNotices() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AddressForNotices> validate(RosettaPath path, AddressForNotices o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AddressForNotices", ValidationType.CARDINALITY, "AddressForNotices", path, "", error);
		}
		return success("AddressForNotices", ValidationType.CARDINALITY, "AddressForNotices", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AddressForNotices o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AddressForNotices", ValidationType.CARDINALITY, "AddressForNotices", path, "", res.getError());
				}
				return success("AddressForNotices", ValidationType.CARDINALITY, "AddressForNotices", path, "");
			})
			.collect(toList());
	}

}
