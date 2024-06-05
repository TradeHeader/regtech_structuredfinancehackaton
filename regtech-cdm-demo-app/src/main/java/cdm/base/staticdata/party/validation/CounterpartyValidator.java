package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
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

public class CounterpartyValidator implements Validator<Counterparty> {

	private List<ComparisonResult> getComparisonResults(Counterparty o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("role", (CounterpartyRoleEnum) o.getRole() != null ? 1 : 0, 1, 1), 
				checkCardinality("partyReference", (ReferenceWithMetaParty) o.getPartyReference() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Counterparty> validate(RosettaPath path, Counterparty o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Counterparty", ValidationType.CARDINALITY, "Counterparty", path, "", error);
		}
		return success("Counterparty", ValidationType.CARDINALITY, "Counterparty", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Counterparty o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Counterparty", ValidationType.CARDINALITY, "Counterparty", path, "", res.getError());
				}
				return success("Counterparty", ValidationType.CARDINALITY, "Counterparty", path, "");
			})
			.collect(toList());
	}

}
