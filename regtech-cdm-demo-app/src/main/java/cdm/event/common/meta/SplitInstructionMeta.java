package cdm.event.common.meta;

import cdm.event.common.SplitInstruction;
import cdm.event.common.validation.SplitInstructionTypeFormatValidator;
import cdm.event.common.validation.SplitInstructionValidator;
import cdm.event.common.validation.exists.SplitInstructionOnlyExistsValidator;
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
@RosettaMeta(model=SplitInstruction.class)
public class SplitInstructionMeta implements RosettaMetaData<SplitInstruction> {

	@Override
	public List<Validator<? super SplitInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SplitInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SplitInstruction> validator() {
		return new SplitInstructionValidator();
	}

	@Override
	public Validator<? super SplitInstruction> typeFormatValidator() {
		return new SplitInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SplitInstruction, Set<String>> onlyExistsValidator() {
		return new SplitInstructionOnlyExistsValidator();
	}
}
