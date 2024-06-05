package cdm.event.workflow.meta;

import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.validation.EventInstructionTypeFormatValidator;
import cdm.event.workflow.validation.EventInstructionValidator;
import cdm.event.workflow.validation.exists.EventInstructionOnlyExistsValidator;
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
@RosettaMeta(model=EventInstruction.class)
public class EventInstructionMeta implements RosettaMetaData<EventInstruction> {

	@Override
	public List<Validator<? super EventInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.workflow.validation.datarule.EventInstructionCorporateAction.class)
		);
	}
	
	@Override
	public List<Function<? super EventInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EventInstruction> validator() {
		return new EventInstructionValidator();
	}

	@Override
	public Validator<? super EventInstruction> typeFormatValidator() {
		return new EventInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EventInstruction, Set<String>> onlyExistsValidator() {
		return new EventInstructionOnlyExistsValidator();
	}
}
