package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
import cdm.legaldocumentation.master.validation.MasterAgreementClauseVariantTypeFormatValidator;
import cdm.legaldocumentation.master.validation.MasterAgreementClauseVariantValidator;
import cdm.legaldocumentation.master.validation.exists.MasterAgreementClauseVariantOnlyExistsValidator;
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
@RosettaMeta(model=MasterAgreementClauseVariant.class)
public class MasterAgreementClauseVariantMeta implements RosettaMetaData<MasterAgreementClauseVariant> {

	@Override
	public List<Validator<? super MasterAgreementClauseVariant>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MasterAgreementClauseVariant, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MasterAgreementClauseVariant> validator() {
		return new MasterAgreementClauseVariantValidator();
	}

	@Override
	public Validator<? super MasterAgreementClauseVariant> typeFormatValidator() {
		return new MasterAgreementClauseVariantTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MasterAgreementClauseVariant, Set<String>> onlyExistsValidator() {
		return new MasterAgreementClauseVariantOnlyExistsValidator();
	}
}
