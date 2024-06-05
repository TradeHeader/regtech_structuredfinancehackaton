package cdm.event.common.meta;

import cdm.event.common.ResetInstruction;
import cdm.event.common.validation.ResetInstructionTypeFormatValidator;
import cdm.event.common.validation.ResetInstructionValidator;
import cdm.event.common.validation.exists.ResetInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ResetInstruction.class)
public class ResetInstructionMeta implements RosettaMetaData<ResetInstruction> {

	@Override
	public List<Validator<? super ResetInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ResetInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ResetInstruction> validator() {
		return new ResetInstructionValidator();
	}

	@Override
	public Validator<? super ResetInstruction> typeFormatValidator() {
		return new ResetInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ResetInstruction, Set<String>> onlyExistsValidator() {
		return new ResetInstructionOnlyExistsValidator();
	}
}
