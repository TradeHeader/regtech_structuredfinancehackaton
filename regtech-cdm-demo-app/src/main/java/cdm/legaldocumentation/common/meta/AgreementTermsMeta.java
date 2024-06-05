package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.common.validation.AgreementTermsTypeFormatValidator;
import cdm.legaldocumentation.common.validation.AgreementTermsValidator;
import cdm.legaldocumentation.common.validation.exists.AgreementTermsOnlyExistsValidator;
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
@RosettaMeta(model=AgreementTerms.class)
public class AgreementTermsMeta implements RosettaMetaData<AgreementTerms> {

	@Override
	public List<Validator<? super AgreementTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AgreementTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AgreementTerms> validator() {
		return new AgreementTermsValidator();
	}

	@Override
	public Validator<? super AgreementTerms> typeFormatValidator() {
		return new AgreementTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AgreementTerms, Set<String>> onlyExistsValidator() {
		return new AgreementTermsOnlyExistsValidator();
	}
}
