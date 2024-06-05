package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaNaturalPerson;
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

public class NaturalPersonRoleValidator implements Validator<NaturalPersonRole> {

	private List<ComparisonResult> getComparisonResults(NaturalPersonRole o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("personReference", (ReferenceWithMetaNaturalPerson) o.getPersonReference() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<NaturalPersonRole> validate(RosettaPath path, NaturalPersonRole o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NaturalPersonRole", ValidationType.CARDINALITY, "NaturalPersonRole", path, "", error);
		}
		return success("NaturalPersonRole", ValidationType.CARDINALITY, "NaturalPersonRole", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NaturalPersonRole o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NaturalPersonRole", ValidationType.CARDINALITY, "NaturalPersonRole", path, "", res.getError());
				}
				return success("NaturalPersonRole", ValidationType.CARDINALITY, "NaturalPersonRole", path, "");
			})
			.collect(toList());
	}

}
