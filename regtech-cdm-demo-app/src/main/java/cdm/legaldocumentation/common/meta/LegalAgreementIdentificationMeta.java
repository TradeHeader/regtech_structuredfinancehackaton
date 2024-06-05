package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.validation.LegalAgreementIdentificationTypeFormatValidator;
import cdm.legaldocumentation.common.validation.LegalAgreementIdentificationValidator;
import cdm.legaldocumentation.common.validation.exists.LegalAgreementIdentificationOnlyExistsValidator;
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
@RosettaMeta(model=LegalAgreementIdentification.class)
public class LegalAgreementIdentificationMeta implements RosettaMetaData<LegalAgreementIdentification> {

	@Override
	public List<Validator<? super LegalAgreementIdentification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.common.validation.datarule.LegalAgreementIdentificationCSAMarginType.class)
		);
	}
	
	@Override
	public List<Function<? super LegalAgreementIdentification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super LegalAgreementIdentification> validator() {
		return new LegalAgreementIdentificationValidator();
	}

	@Override
	public Validator<? super LegalAgreementIdentification> typeFormatValidator() {
		return new LegalAgreementIdentificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super LegalAgreementIdentification, Set<String>> onlyExistsValidator() {
		return new LegalAgreementIdentificationOnlyExistsValidator();
	}
}
