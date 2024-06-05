package cdm.legaldocumentation.contract.meta;

import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.contract.validation.AgreementTypeFormatValidator;
import cdm.legaldocumentation.contract.validation.AgreementValidator;
import cdm.legaldocumentation.contract.validation.exists.AgreementOnlyExistsValidator;
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
@RosettaMeta(model=Agreement.class)
public class AgreementMeta implements RosettaMetaData<Agreement> {

	@Override
	public List<Validator<? super Agreement>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.contract.validation.datarule.AgreementOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super Agreement, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Agreement> validator() {
		return new AgreementValidator();
	}

	@Override
	public Validator<? super Agreement> typeFormatValidator() {
		return new AgreementTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Agreement, Set<String>> onlyExistsValidator() {
		return new AgreementOnlyExistsValidator();
	}
}
