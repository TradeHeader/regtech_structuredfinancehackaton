package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.PersonIdentifier;
import cdm.base.staticdata.party.validation.PersonIdentifierTypeFormatValidator;
import cdm.base.staticdata.party.validation.PersonIdentifierValidator;
import cdm.base.staticdata.party.validation.exists.PersonIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=PersonIdentifier.class)
public class PersonIdentifierMeta implements RosettaMetaData<PersonIdentifier> {

	@Override
	public List<Validator<? super PersonIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PersonIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PersonIdentifier> validator() {
		return new PersonIdentifierValidator();
	}

	@Override
	public Validator<? super PersonIdentifier> typeFormatValidator() {
		return new PersonIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PersonIdentifier, Set<String>> onlyExistsValidator() {
		return new PersonIdentifierOnlyExistsValidator();
	}
}
