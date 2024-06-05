package cdm.event.common.meta;

import cdm.event.common.ExerciseInstruction;
import cdm.event.common.validation.ExerciseInstructionTypeFormatValidator;
import cdm.event.common.validation.ExerciseInstructionValidator;
import cdm.event.common.validation.exists.ExerciseInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ExerciseInstruction.class)
public class ExerciseInstructionMeta implements RosettaMetaData<ExerciseInstruction> {

	@Override
	public List<Validator<? super ExerciseInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExerciseInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExerciseInstruction> validator() {
		return new ExerciseInstructionValidator();
	}

	@Override
	public Validator<? super ExerciseInstruction> typeFormatValidator() {
		return new ExerciseInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExerciseInstruction, Set<String>> onlyExistsValidator() {
		return new ExerciseInstructionOnlyExistsValidator();
	}
}
