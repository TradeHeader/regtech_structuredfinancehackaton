package cdm.event.common.meta;

import cdm.event.common.ReturnInstruction;
import cdm.event.common.validation.ReturnInstructionTypeFormatValidator;
import cdm.event.common.validation.ReturnInstructionValidator;
import cdm.event.common.validation.exists.ReturnInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ReturnInstruction.class)
public class ReturnInstructionMeta implements RosettaMetaData<ReturnInstruction> {

	@Override
	public List<Validator<? super ReturnInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ReturnInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReturnInstruction> validator() {
		return new ReturnInstructionValidator();
	}

	@Override
	public Validator<? super ReturnInstruction> typeFormatValidator() {
		return new ReturnInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReturnInstruction, Set<String>> onlyExistsValidator() {
		return new ReturnInstructionOnlyExistsValidator();
	}
}
