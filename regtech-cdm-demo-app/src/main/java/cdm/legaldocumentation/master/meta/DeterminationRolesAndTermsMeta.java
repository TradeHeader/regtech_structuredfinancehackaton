package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import cdm.legaldocumentation.master.validation.DeterminationRolesAndTermsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.DeterminationRolesAndTermsValidator;
import cdm.legaldocumentation.master.validation.exists.DeterminationRolesAndTermsOnlyExistsValidator;
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
@RosettaMeta(model=DeterminationRolesAndTerms.class)
public class DeterminationRolesAndTermsMeta implements RosettaMetaData<DeterminationRolesAndTerms> {

	@Override
	public List<Validator<? super DeterminationRolesAndTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DeterminationRolesAndTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DeterminationRolesAndTerms> validator() {
		return new DeterminationRolesAndTermsValidator();
	}

	@Override
	public Validator<? super DeterminationRolesAndTerms> typeFormatValidator() {
		return new DeterminationRolesAndTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DeterminationRolesAndTerms, Set<String>> onlyExistsValidator() {
		return new DeterminationRolesAndTermsOnlyExistsValidator();
	}
}
