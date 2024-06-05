package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.validation.NaturalPersonTypeFormatValidator;
import cdm.base.staticdata.party.validation.NaturalPersonValidator;
import cdm.base.staticdata.party.validation.exists.NaturalPersonOnlyExistsValidator;
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
@RosettaMeta(model=NaturalPerson.class)
public class NaturalPersonMeta implements RosettaMetaData<NaturalPerson> {

	@Override
	public List<Validator<? super NaturalPerson>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.party.validation.datarule.NaturalPersonNameOrIdChoice.class),
			factory.create(cdm.base.staticdata.party.validation.datarule.NaturalPersonNaturalPersonChoice.class)
		);
	}
	
	@Override
	public List<Function<? super NaturalPerson, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NaturalPerson> validator() {
		return new NaturalPersonValidator();
	}

	@Override
	public Validator<? super NaturalPerson> typeFormatValidator() {
		return new NaturalPersonTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NaturalPerson, Set<String>> onlyExistsValidator() {
		return new NaturalPersonOnlyExistsValidator();
	}
}
