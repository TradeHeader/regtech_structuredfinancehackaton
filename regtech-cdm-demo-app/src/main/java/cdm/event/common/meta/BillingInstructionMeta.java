package cdm.event.common.meta;

import cdm.event.common.BillingInstruction;
import cdm.event.common.validation.BillingInstructionTypeFormatValidator;
import cdm.event.common.validation.BillingInstructionValidator;
import cdm.event.common.validation.exists.BillingInstructionOnlyExistsValidator;
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
@RosettaMeta(model=BillingInstruction.class)
public class BillingInstructionMeta implements RosettaMetaData<BillingInstruction> {

	@Override
	public List<Validator<? super BillingInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BillingInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BillingInstruction> validator() {
		return new BillingInstructionValidator();
	}

	@Override
	public Validator<? super BillingInstruction> typeFormatValidator() {
		return new BillingInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BillingInstruction, Set<String>> onlyExistsValidator() {
		return new BillingInstructionOnlyExistsValidator();
	}
}
