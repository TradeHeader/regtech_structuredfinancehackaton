package cdm.event.common.meta;

import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.validation.CalculateTransferInstructionTypeFormatValidator;
import cdm.event.common.validation.CalculateTransferInstructionValidator;
import cdm.event.common.validation.exists.CalculateTransferInstructionOnlyExistsValidator;
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
@RosettaMeta(model=CalculateTransferInstruction.class)
public class CalculateTransferInstructionMeta implements RosettaMetaData<CalculateTransferInstruction> {

	@Override
	public List<Validator<? super CalculateTransferInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculateTransferInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculateTransferInstruction> validator() {
		return new CalculateTransferInstructionValidator();
	}

	@Override
	public Validator<? super CalculateTransferInstruction> typeFormatValidator() {
		return new CalculateTransferInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculateTransferInstruction, Set<String>> onlyExistsValidator() {
		return new CalculateTransferInstructionOnlyExistsValidator();
	}
}
