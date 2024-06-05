package cdm.event.common.meta;

import cdm.event.common.Instruction;
import cdm.event.common.validation.InstructionTypeFormatValidator;
import cdm.event.common.validation.InstructionValidator;
import cdm.event.common.validation.exists.InstructionOnlyExistsValidator;
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
@RosettaMeta(model=Instruction.class)
public class InstructionMeta implements RosettaMetaData<Instruction> {

	@Override
	public List<Validator<? super Instruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.InstructionExclusiveSplitPrimitive.class),
			factory.create(cdm.event.common.validation.datarule.InstructionNewTrade.class)
		);
	}
	
	@Override
	public List<Function<? super Instruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Instruction> validator() {
		return new InstructionValidator();
	}

	@Override
	public Validator<? super Instruction> typeFormatValidator() {
		return new InstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Instruction, Set<String>> onlyExistsValidator() {
		return new InstructionOnlyExistsValidator();
	}
}
