package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
import cdm.legaldocumentation.master.validation.MasterAgreementVariableSetTypeFormatValidator;
import cdm.legaldocumentation.master.validation.MasterAgreementVariableSetValidator;
import cdm.legaldocumentation.master.validation.exists.MasterAgreementVariableSetOnlyExistsValidator;
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
@RosettaMeta(model=MasterAgreementVariableSet.class)
public class MasterAgreementVariableSetMeta implements RosettaMetaData<MasterAgreementVariableSet> {

	@Override
	public List<Validator<? super MasterAgreementVariableSet>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.master.validation.datarule.MasterAgreementVariableSetVariableSetExists.class),
			factory.create(cdm.legaldocumentation.master.validation.datarule.MasterAgreementVariableSetVariableSetNesting.class),
			factory.create(cdm.legaldocumentation.master.validation.datarule.MasterAgreementVariableSetNameMustExist.class),
			factory.create(cdm.legaldocumentation.master.validation.datarule.MasterAgreementVariableSetValueMustExist.class)
		);
	}
	
	@Override
	public List<Function<? super MasterAgreementVariableSet, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MasterAgreementVariableSet> validator() {
		return new MasterAgreementVariableSetValidator();
	}

	@Override
	public Validator<? super MasterAgreementVariableSet> typeFormatValidator() {
		return new MasterAgreementVariableSetTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MasterAgreementVariableSet, Set<String>> onlyExistsValidator() {
		return new MasterAgreementVariableSetOnlyExistsValidator();
	}
}
