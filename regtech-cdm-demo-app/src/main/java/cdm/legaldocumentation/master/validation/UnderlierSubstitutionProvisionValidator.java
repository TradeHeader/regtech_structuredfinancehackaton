package cdm.legaldocumentation.master.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
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

public class UnderlierSubstitutionProvisionValidator implements Validator<UnderlierSubstitutionProvision> {

	private List<ComparisonResult> getComparisonResults(UnderlierSubstitutionProvision o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("whoMaySubstitute", (List<CounterpartyRoleEnum>) o.getWhoMaySubstitute() == null ? 0 : ((List<CounterpartyRoleEnum>) o.getWhoMaySubstitute()).size(), 1, 2), 
				checkCardinality("disputingParty", (CounterpartyRoleEnum) o.getDisputingParty() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<UnderlierSubstitutionProvision> validate(RosettaPath path, UnderlierSubstitutionProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UnderlierSubstitutionProvision", ValidationType.CARDINALITY, "UnderlierSubstitutionProvision", path, "", error);
		}
		return success("UnderlierSubstitutionProvision", ValidationType.CARDINALITY, "UnderlierSubstitutionProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UnderlierSubstitutionProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UnderlierSubstitutionProvision", ValidationType.CARDINALITY, "UnderlierSubstitutionProvision", path, "", res.getError());
				}
				return success("UnderlierSubstitutionProvision", ValidationType.CARDINALITY, "UnderlierSubstitutionProvision", path, "");
			})
			.collect(toList());
	}

}
