package cdm.event.common.meta;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.validation.ContractFormationInstructionTypeFormatValidator;
import cdm.event.common.validation.ContractFormationInstructionValidator;
import cdm.event.common.validation.exists.ContractFormationInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ContractFormationInstruction.class)
public class ContractFormationInstructionMeta implements RosettaMetaData<ContractFormationInstruction> {

	@Override
	public List<Validator<? super ContractFormationInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ContractFormationInstructionExecutedAgreements.class)
		);
	}
	
	@Override
	public List<Function<? super ContractFormationInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContractFormationInstruction> validator() {
		return new ContractFormationInstructionValidator();
	}

	@Override
	public Validator<? super ContractFormationInstruction> typeFormatValidator() {
		return new ContractFormationInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContractFormationInstruction, Set<String>> onlyExistsValidator() {
		return new ContractFormationInstructionOnlyExistsValidator();
	}
}
