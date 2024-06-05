package cdm.event.common.meta;

import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.validation.QuantityChangeInstructionTypeFormatValidator;
import cdm.event.common.validation.QuantityChangeInstructionValidator;
import cdm.event.common.validation.exists.QuantityChangeInstructionOnlyExistsValidator;
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
@RosettaMeta(model=QuantityChangeInstruction.class)
public class QuantityChangeInstructionMeta implements RosettaMetaData<QuantityChangeInstruction> {

	@Override
	public List<Validator<? super QuantityChangeInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super QuantityChangeInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super QuantityChangeInstruction> validator() {
		return new QuantityChangeInstructionValidator();
	}

	@Override
	public Validator<? super QuantityChangeInstruction> typeFormatValidator() {
		return new QuantityChangeInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super QuantityChangeInstruction, Set<String>> onlyExistsValidator() {
		return new QuantityChangeInstructionOnlyExistsValidator();
	}
}
