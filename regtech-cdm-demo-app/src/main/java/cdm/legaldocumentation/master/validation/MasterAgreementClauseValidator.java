package cdm.legaldocumentation.master.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.legaldocumentation.master.MasterAgreementClause;
import cdm.legaldocumentation.master.MasterAgreementClauseIdentifierEnum;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
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

public class MasterAgreementClauseValidator implements Validator<MasterAgreementClause> {

	private List<ComparisonResult> getComparisonResults(MasterAgreementClause o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifer", (MasterAgreementClauseIdentifierEnum) o.getIdentifer() != null ? 1 : 0, 1, 1), 
				checkCardinality("name", (String) o.getName() != null ? 1 : 0, 0, 1), 
				checkCardinality("counterparty", (List<CounterpartyRoleEnum>) o.getCounterparty() == null ? 0 : ((List<CounterpartyRoleEnum>) o.getCounterparty()).size(), 0, 2), 
				checkCardinality("variant", (List<? extends MasterAgreementClauseVariant>) o.getVariant() == null ? 0 : ((List<? extends MasterAgreementClauseVariant>) o.getVariant()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<MasterAgreementClause> validate(RosettaPath path, MasterAgreementClause o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterAgreementClause", ValidationType.CARDINALITY, "MasterAgreementClause", path, "", error);
		}
		return success("MasterAgreementClause", ValidationType.CARDINALITY, "MasterAgreementClause", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterAgreementClause o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterAgreementClause", ValidationType.CARDINALITY, "MasterAgreementClause", path, "", res.getError());
				}
				return success("MasterAgreementClause", ValidationType.CARDINALITY, "MasterAgreementClause", path, "");
			})
			.collect(toList());
	}

}
