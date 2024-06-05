package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.validation.AncillaryPartyTypeFormatValidator;
import cdm.base.staticdata.party.validation.AncillaryPartyValidator;
import cdm.base.staticdata.party.validation.exists.AncillaryPartyOnlyExistsValidator;
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
@RosettaMeta(model=AncillaryParty.class)
public class AncillaryPartyMeta implements RosettaMetaData<AncillaryParty> {

	@Override
	public List<Validator<? super AncillaryParty>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AncillaryParty, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AncillaryParty> validator() {
		return new AncillaryPartyValidator();
	}

	@Override
	public Validator<? super AncillaryParty> typeFormatValidator() {
		return new AncillaryPartyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AncillaryParty, Set<String>> onlyExistsValidator() {
		return new AncillaryPartyOnlyExistsValidator();
	}
}
