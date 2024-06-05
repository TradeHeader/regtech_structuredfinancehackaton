package cdm.product.collateral.meta;

import cdm.product.collateral.ContactElection;
import cdm.product.collateral.validation.ContactElectionTypeFormatValidator;
import cdm.product.collateral.validation.ContactElectionValidator;
import cdm.product.collateral.validation.exists.ContactElectionOnlyExistsValidator;
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
@RosettaMeta(model=ContactElection.class)
public class ContactElectionMeta implements RosettaMetaData<ContactElection> {

	@Override
	public List<Validator<? super ContactElection>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ContactElection, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContactElection> validator() {
		return new ContactElectionValidator();
	}

	@Override
	public Validator<? super ContactElection> typeFormatValidator() {
		return new ContactElectionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContactElection, Set<String>> onlyExistsValidator() {
		return new ContactElectionOnlyExistsValidator();
	}
}
