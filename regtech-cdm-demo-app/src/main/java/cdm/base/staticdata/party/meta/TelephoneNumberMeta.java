package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.TelephoneNumber;
import cdm.base.staticdata.party.validation.TelephoneNumberTypeFormatValidator;
import cdm.base.staticdata.party.validation.TelephoneNumberValidator;
import cdm.base.staticdata.party.validation.exists.TelephoneNumberOnlyExistsValidator;
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
@RosettaMeta(model=TelephoneNumber.class)
public class TelephoneNumberMeta implements RosettaMetaData<TelephoneNumber> {

	@Override
	public List<Validator<? super TelephoneNumber>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TelephoneNumber, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TelephoneNumber> validator() {
		return new TelephoneNumberValidator();
	}

	@Override
	public Validator<? super TelephoneNumber> typeFormatValidator() {
		return new TelephoneNumberTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TelephoneNumber, Set<String>> onlyExistsValidator() {
		return new TelephoneNumberOnlyExistsValidator();
	}
}
