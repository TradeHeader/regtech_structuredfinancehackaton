package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.PartyContactInformation;
import cdm.base.staticdata.party.validation.PartyContactInformationTypeFormatValidator;
import cdm.base.staticdata.party.validation.PartyContactInformationValidator;
import cdm.base.staticdata.party.validation.exists.PartyContactInformationOnlyExistsValidator;
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
@RosettaMeta(model=PartyContactInformation.class)
public class PartyContactInformationMeta implements RosettaMetaData<PartyContactInformation> {

	@Override
	public List<Validator<? super PartyContactInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PartyContactInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PartyContactInformation> validator() {
		return new PartyContactInformationValidator();
	}

	@Override
	public Validator<? super PartyContactInformation> typeFormatValidator() {
		return new PartyContactInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PartyContactInformation, Set<String>> onlyExistsValidator() {
		return new PartyContactInformationOnlyExistsValidator();
	}
}
