package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.MasterConfirmationBase;
import cdm.legaldocumentation.master.validation.MasterConfirmationBaseTypeFormatValidator;
import cdm.legaldocumentation.master.validation.MasterConfirmationBaseValidator;
import cdm.legaldocumentation.master.validation.exists.MasterConfirmationBaseOnlyExistsValidator;
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
@RosettaMeta(model=MasterConfirmationBase.class)
public class MasterConfirmationBaseMeta implements RosettaMetaData<MasterConfirmationBase> {

	@Override
	public List<Validator<? super MasterConfirmationBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MasterConfirmationBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MasterConfirmationBase> validator() {
		return new MasterConfirmationBaseValidator();
	}

	@Override
	public Validator<? super MasterConfirmationBase> typeFormatValidator() {
		return new MasterConfirmationBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MasterConfirmationBase, Set<String>> onlyExistsValidator() {
		return new MasterConfirmationBaseOnlyExistsValidator();
	}
}
