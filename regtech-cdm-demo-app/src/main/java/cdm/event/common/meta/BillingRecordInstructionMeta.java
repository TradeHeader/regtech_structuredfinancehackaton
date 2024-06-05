package cdm.event.common.meta;

import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.validation.BillingRecordInstructionTypeFormatValidator;
import cdm.event.common.validation.BillingRecordInstructionValidator;
import cdm.event.common.validation.exists.BillingRecordInstructionOnlyExistsValidator;
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
@RosettaMeta(model=BillingRecordInstruction.class)
public class BillingRecordInstructionMeta implements RosettaMetaData<BillingRecordInstruction> {

	@Override
	public List<Validator<? super BillingRecordInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BillingRecordInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BillingRecordInstruction> validator() {
		return new BillingRecordInstructionValidator();
	}

	@Override
	public Validator<? super BillingRecordInstruction> typeFormatValidator() {
		return new BillingRecordInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BillingRecordInstruction, Set<String>> onlyExistsValidator() {
		return new BillingRecordInstructionOnlyExistsValidator();
	}
}
