package cdm.event.common.meta;

import cdm.event.common.ClearingInstruction;
import cdm.event.common.validation.ClearingInstructionTypeFormatValidator;
import cdm.event.common.validation.ClearingInstructionValidator;
import cdm.event.common.validation.exists.ClearingInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ClearingInstruction.class)
public class ClearingInstructionMeta implements RosettaMetaData<ClearingInstruction> {

	@Override
	public List<Validator<? super ClearingInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ClearingInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ClearingInstruction> validator() {
		return new ClearingInstructionValidator();
	}

	@Override
	public Validator<? super ClearingInstruction> typeFormatValidator() {
		return new ClearingInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ClearingInstruction, Set<String>> onlyExistsValidator() {
		return new ClearingInstructionOnlyExistsValidator();
	}
}
