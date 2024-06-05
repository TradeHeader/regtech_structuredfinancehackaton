package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class NaturalPersonValidator implements Validator<NaturalPerson> {

	private List<ComparisonResult> getComparisonResults(NaturalPerson o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("honorific", (String) o.getHonorific() != null ? 1 : 0, 0, 1), 
				checkCardinality("firstName", (String) o.getFirstName() != null ? 1 : 0, 0, 1), 
				checkCardinality("surname", (String) o.getSurname() != null ? 1 : 0, 0, 1), 
				checkCardinality("suffix", (String) o.getSuffix() != null ? 1 : 0, 0, 1), 
				checkCardinality("dateOfBirth", (Date) o.getDateOfBirth() != null ? 1 : 0, 0, 1), 
				checkCardinality("contactInformation", (ContactInformation) o.getContactInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NaturalPerson", ValidationType.CARDINALITY, "NaturalPerson", path, "", error);
		}
		return success("NaturalPerson", ValidationType.CARDINALITY, "NaturalPerson", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NaturalPerson o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NaturalPerson", ValidationType.CARDINALITY, "NaturalPerson", path, "", res.getError());
				}
				return success("NaturalPerson", ValidationType.CARDINALITY, "NaturalPerson", path, "");
			})
			.collect(toList());
	}

}
