package cdm.legaldocumentation.csa.meta;

import cdm.legaldocumentation.csa.SecurityAgreementElections;
import cdm.legaldocumentation.csa.validation.SecurityAgreementElectionsTypeFormatValidator;
import cdm.legaldocumentation.csa.validation.SecurityAgreementElectionsValidator;
import cdm.legaldocumentation.csa.validation.exists.SecurityAgreementElectionsOnlyExistsValidator;
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
@RosettaMeta(model=SecurityAgreementElections.class)
public class SecurityAgreementElectionsMeta implements RosettaMetaData<SecurityAgreementElections> {

	@Override
	public List<Validator<? super SecurityAgreementElections>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SecurityAgreementElections, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityAgreementElections> validator() {
		return new SecurityAgreementElectionsValidator();
	}

	@Override
	public Validator<? super SecurityAgreementElections> typeFormatValidator() {
		return new SecurityAgreementElectionsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityAgreementElections, Set<String>> onlyExistsValidator() {
		return new SecurityAgreementElectionsOnlyExistsValidator();
	}
}
