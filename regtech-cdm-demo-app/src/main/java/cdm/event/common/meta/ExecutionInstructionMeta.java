package cdm.event.common.meta;

import cdm.event.common.ExecutionInstruction;
import cdm.event.common.validation.ExecutionInstructionTypeFormatValidator;
import cdm.event.common.validation.ExecutionInstructionValidator;
import cdm.event.common.validation.exists.ExecutionInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ExecutionInstruction.class)
public class ExecutionInstructionMeta implements RosettaMetaData<ExecutionInstruction> {

	@Override
	public List<Validator<? super ExecutionInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExecutionInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExecutionInstruction> validator() {
		return new ExecutionInstructionValidator();
	}

	@Override
	public Validator<? super ExecutionInstruction> typeFormatValidator() {
		return new ExecutionInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExecutionInstruction, Set<String>> onlyExistsValidator() {
		return new ExecutionInstructionOnlyExistsValidator();
	}
}
