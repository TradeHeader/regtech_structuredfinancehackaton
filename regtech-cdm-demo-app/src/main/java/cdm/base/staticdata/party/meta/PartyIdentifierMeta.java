package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.PartyIdentifier;
import cdm.base.staticdata.party.validation.PartyIdentifierTypeFormatValidator;
import cdm.base.staticdata.party.validation.PartyIdentifierValidator;
import cdm.base.staticdata.party.validation.exists.PartyIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=PartyIdentifier.class)
public class PartyIdentifierMeta implements RosettaMetaData<PartyIdentifier> {

	@Override
	public List<Validator<? super PartyIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PartyIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PartyIdentifier> validator() {
		return new PartyIdentifierValidator();
	}

	@Override
	public Validator<? super PartyIdentifier> typeFormatValidator() {
		return new PartyIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PartyIdentifier, Set<String>> onlyExistsValidator() {
		return new PartyIdentifierOnlyExistsValidator();
	}
}
