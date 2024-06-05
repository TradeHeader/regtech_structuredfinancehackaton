package cdm.event.common.meta;

import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.validation.PrimitiveInstructionTypeFormatValidator;
import cdm.event.common.validation.PrimitiveInstructionValidator;
import cdm.event.common.validation.exists.PrimitiveInstructionOnlyExistsValidator;
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
@RosettaMeta(model=PrimitiveInstruction.class)
public class PrimitiveInstructionMeta implements RosettaMetaData<PrimitiveInstruction> {

	@Override
	public List<Validator<? super PrimitiveInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PrimitiveInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PrimitiveInstruction> validator() {
		return new PrimitiveInstructionValidator();
	}

	@Override
	public Validator<? super PrimitiveInstruction> typeFormatValidator() {
		return new PrimitiveInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PrimitiveInstruction, Set<String>> onlyExistsValidator() {
		return new PrimitiveInstructionOnlyExistsValidator();
	}
}
