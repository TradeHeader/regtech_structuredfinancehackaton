package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.TelephoneNumber;
import cdm.base.staticdata.party.TelephoneTypeEnum;
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

public class TelephoneNumberValidator implements Validator<TelephoneNumber> {

	private List<ComparisonResult> getComparisonResults(TelephoneNumber o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("telephoneNumberType", (TelephoneTypeEnum) o.getTelephoneNumberType() != null ? 1 : 0, 0, 1), 
				checkCardinality("number", (String) o.getNumber() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<TelephoneNumber> validate(RosettaPath path, TelephoneNumber o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TelephoneNumber", ValidationType.CARDINALITY, "TelephoneNumber", path, "", error);
		}
		return success("TelephoneNumber", ValidationType.CARDINALITY, "TelephoneNumber", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TelephoneNumber o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TelephoneNumber", ValidationType.CARDINALITY, "TelephoneNumber", path, "", res.getError());
				}
				return success("TelephoneNumber", ValidationType.CARDINALITY, "TelephoneNumber", path, "");
			})
			.collect(toList());
	}

}
