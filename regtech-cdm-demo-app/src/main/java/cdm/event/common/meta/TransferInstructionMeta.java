package cdm.event.common.meta;

import cdm.event.common.TransferInstruction;
import cdm.event.common.validation.TransferInstructionTypeFormatValidator;
import cdm.event.common.validation.TransferInstructionValidator;
import cdm.event.common.validation.exists.TransferInstructionOnlyExistsValidator;
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
@RosettaMeta(model=TransferInstruction.class)
public class TransferInstructionMeta implements RosettaMetaData<TransferInstruction> {

	@Override
	public List<Validator<? super TransferInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TransferInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransferInstruction> validator() {
		return new TransferInstructionValidator();
	}

	@Override
	public Validator<? super TransferInstruction> typeFormatValidator() {
		return new TransferInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransferInstruction, Set<String>> onlyExistsValidator() {
		return new TransferInstructionOnlyExistsValidator();
	}
}
