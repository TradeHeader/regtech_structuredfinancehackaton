package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.MasterAgreementClause;
import cdm.legaldocumentation.master.validation.MasterAgreementClauseTypeFormatValidator;
import cdm.legaldocumentation.master.validation.MasterAgreementClauseValidator;
import cdm.legaldocumentation.master.validation.exists.MasterAgreementClauseOnlyExistsValidator;
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
@RosettaMeta(model=MasterAgreementClause.class)
public class MasterAgreementClauseMeta implements RosettaMetaData<MasterAgreementClause> {

	@Override
	public List<Validator<? super MasterAgreementClause>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MasterAgreementClause, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MasterAgreementClause> validator() {
		return new MasterAgreementClauseValidator();
	}

	@Override
	public Validator<? super MasterAgreementClause> typeFormatValidator() {
		return new MasterAgreementClauseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MasterAgreementClause, Set<String>> onlyExistsValidator() {
		return new MasterAgreementClauseOnlyExistsValidator();
	}
}
