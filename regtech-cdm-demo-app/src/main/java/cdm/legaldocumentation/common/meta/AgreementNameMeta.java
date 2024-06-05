package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.validation.AgreementNameTypeFormatValidator;
import cdm.legaldocumentation.common.validation.AgreementNameValidator;
import cdm.legaldocumentation.common.validation.exists.AgreementNameOnlyExistsValidator;
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
@RosettaMeta(model=AgreementName.class)
public class AgreementNameMeta implements RosettaMetaData<AgreementName> {

	@Override
	public List<Validator<? super AgreementName>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.common.validation.datarule.AgreementNameAgreementType.class),
			factory.create(cdm.legaldocumentation.common.validation.datarule.AgreementNameCreditSupportAgreement.class),
			factory.create(cdm.legaldocumentation.common.validation.datarule.AgreementNameMasterConfirmation.class),
			factory.create(cdm.legaldocumentation.common.validation.datarule.AgreementNameCSAMarginType.class)
		);
	}
	
	@Override
	public List<Function<? super AgreementName, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AgreementName> validator() {
		return new AgreementNameValidator();
	}

	@Override
	public Validator<? super AgreementName> typeFormatValidator() {
		return new AgreementNameTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AgreementName, Set<String>> onlyExistsValidator() {
		return new AgreementNameOnlyExistsValidator();
	}
}
