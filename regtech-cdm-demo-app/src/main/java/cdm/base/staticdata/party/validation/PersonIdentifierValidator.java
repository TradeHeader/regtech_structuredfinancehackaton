package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.PersonIdentifier;
import cdm.base.staticdata.party.PersonIdentifierTypeEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PersonIdentifierValidator implements Validator<PersonIdentifier> {

	private List<ComparisonResult> getComparisonResults(PersonIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (FieldWithMetaString) o.getIdentifier() != null ? 1 : 0, 1, 1), 
				checkCardinality("identifierType", (PersonIdentifierTypeEnum) o.getIdentifierType() != null ? 1 : 0, 0, 1), 
				checkCardinality("country", (FieldWithMetaString) o.getCountry() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PersonIdentifier> validate(RosettaPath path, PersonIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PersonIdentifier", ValidationType.CARDINALITY, "PersonIdentifier", path, "", error);
		}
		return success("PersonIdentifier", ValidationType.CARDINALITY, "PersonIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PersonIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PersonIdentifier", ValidationType.CARDINALITY, "PersonIdentifier", path, "", res.getError());
				}
				return success("PersonIdentifier", ValidationType.CARDINALITY, "PersonIdentifier", path, "");
			})
			.collect(toList());
	}

}
