package cdm.event.common.meta;

import cdm.event.common.BillingSummaryInstruction;
import cdm.event.common.validation.BillingSummaryInstructionTypeFormatValidator;
import cdm.event.common.validation.BillingSummaryInstructionValidator;
import cdm.event.common.validation.exists.BillingSummaryInstructionOnlyExistsValidator;
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
@RosettaMeta(model=BillingSummaryInstruction.class)
public class BillingSummaryInstructionMeta implements RosettaMetaData<BillingSummaryInstruction> {

	@Override
	public List<Validator<? super BillingSummaryInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BillingSummaryInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BillingSummaryInstruction> validator() {
		return new BillingSummaryInstructionValidator();
	}

	@Override
	public Validator<? super BillingSummaryInstruction> typeFormatValidator() {
		return new BillingSummaryInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BillingSummaryInstruction, Set<String>> onlyExistsValidator() {
		return new BillingSummaryInstructionOnlyExistsValidator();
	}
}
