package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.metafields.FieldWithMetaAccountTypeEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
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

public class AccountValidator implements Validator<Account> {

	private List<ComparisonResult> getComparisonResults(Account o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("partyReference", (ReferenceWithMetaParty) o.getPartyReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("accountNumber", (FieldWithMetaString) o.getAccountNumber() != null ? 1 : 0, 1, 1), 
				checkCardinality("accountName", (FieldWithMetaString) o.getAccountName() != null ? 1 : 0, 0, 1), 
				checkCardinality("accountType", (FieldWithMetaAccountTypeEnum) o.getAccountType() != null ? 1 : 0, 0, 1), 
				checkCardinality("accountBeneficiary", (ReferenceWithMetaParty) o.getAccountBeneficiary() != null ? 1 : 0, 0, 1), 
				checkCardinality("servicingParty", (ReferenceWithMetaParty) o.getServicingParty() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Account> validate(RosettaPath path, Account o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Account", ValidationType.CARDINALITY, "Account", path, "", error);
		}
		return success("Account", ValidationType.CARDINALITY, "Account", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Account o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Account", ValidationType.CARDINALITY, "Account", path, "", res.getError());
				}
				return success("Account", ValidationType.CARDINALITY, "Account", path, "");
			})
			.collect(toList());
	}

}
