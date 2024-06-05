package cdm.event.common.meta;

import cdm.event.common.ObservationInstruction;
import cdm.event.common.validation.ObservationInstructionTypeFormatValidator;
import cdm.event.common.validation.ObservationInstructionValidator;
import cdm.event.common.validation.exists.ObservationInstructionOnlyExistsValidator;
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
@RosettaMeta(model=ObservationInstruction.class)
public class ObservationInstructionMeta implements RosettaMetaData<ObservationInstruction> {

	@Override
	public List<Validator<? super ObservationInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ObservationInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationInstruction> validator() {
		return new ObservationInstructionValidator();
	}

	@Override
	public Validator<? super ObservationInstruction> typeFormatValidator() {
		return new ObservationInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationInstruction, Set<String>> onlyExistsValidator() {
		return new ObservationInstructionOnlyExistsValidator();
	}
}
