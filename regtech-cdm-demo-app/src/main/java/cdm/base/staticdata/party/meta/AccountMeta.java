package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.validation.AccountTypeFormatValidator;
import cdm.base.staticdata.party.validation.AccountValidator;
import cdm.base.staticdata.party.validation.exists.AccountOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=Account.class)
public class AccountMeta implements RosettaMetaData<Account> {

	@Override
	public List<Validator<? super Account>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Account, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Account> validator() {
		return new AccountValidator();
	}

	@Override
	public Validator<? super Account> typeFormatValidator() {
		return new AccountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Account, Set<String>> onlyExistsValidator() {
		return new AccountOnlyExistsValidator();
	}
}
