package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.validation.ContactInformationTypeFormatValidator;
import cdm.base.staticdata.party.validation.ContactInformationValidator;
import cdm.base.staticdata.party.validation.exists.ContactInformationOnlyExistsValidator;
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
@RosettaMeta(model=ContactInformation.class)
public class ContactInformationMeta implements RosettaMetaData<ContactInformation> {

	@Override
	public List<Validator<? super ContactInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ContactInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContactInformation> validator() {
		return new ContactInformationValidator();
	}

	@Override
	public Validator<? super ContactInformation> typeFormatValidator() {
		return new ContactInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContactInformation, Set<String>> onlyExistsValidator() {
		return new ContactInformationOnlyExistsValidator();
	}
}
