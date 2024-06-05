package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.ContactInformation;
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

public class BusinessUnitValidator implements Validator<BusinessUnit> {

	private List<ComparisonResult> getComparisonResults(BusinessUnit o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("name", (String) o.getName() != null ? 1 : 0, 1, 1), 
				checkCardinality("identifier", (Identifier) o.getIdentifier() != null ? 1 : 0, 0, 1), 
				checkCardinality("contactInformation", (ContactInformation) o.getContactInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BusinessUnit> validate(RosettaPath path, BusinessUnit o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessUnit", ValidationType.CARDINALITY, "BusinessUnit", path, "", error);
		}
		return success("BusinessUnit", ValidationType.CARDINALITY, "BusinessUnit", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessUnit o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessUnit", ValidationType.CARDINALITY, "BusinessUnit", path, "", res.getError());
				}
				return success("BusinessUnit", ValidationType.CARDINALITY, "BusinessUnit", path, "");
			})
			.collect(toList());
	}

}
