package cdm.event.common.meta;

import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.validation.TermsChangeInstructionTypeFormatValidator;
import cdm.event.common.validation.TermsChangeInstructionValidator;
import cdm.event.common.validation.exists.TermsChangeInstructionOnlyExistsValidator;
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
@RosettaMeta(model=TermsChangeInstruction.class)
public class TermsChangeInstructionMeta implements RosettaMetaData<TermsChangeInstruction> {

	@Override
	public List<Validator<? super TermsChangeInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.TermsChangeInstructionAtLeastOneOf.class)
		);
	}
	
	@Override
	public List<Function<? super TermsChangeInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TermsChangeInstruction> validator() {
		return new TermsChangeInstructionValidator();
	}

	@Override
	public Validator<? super TermsChangeInstruction> typeFormatValidator() {
		return new TermsChangeInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TermsChangeInstruction, Set<String>> onlyExistsValidator() {
		return new TermsChangeInstructionOnlyExistsValidator();
	}
}
