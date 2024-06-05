package cdm.event.workflow.meta;

import cdm.event.workflow.PartyCustomisedWorkflow;
import cdm.event.workflow.validation.PartyCustomisedWorkflowTypeFormatValidator;
import cdm.event.workflow.validation.PartyCustomisedWorkflowValidator;
import cdm.event.workflow.validation.exists.PartyCustomisedWorkflowOnlyExistsValidator;
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
@RosettaMeta(model=PartyCustomisedWorkflow.class)
public class PartyCustomisedWorkflowMeta implements RosettaMetaData<PartyCustomisedWorkflow> {

	@Override
	public List<Validator<? super PartyCustomisedWorkflow>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.workflow.validation.datarule.PartyCustomisedWorkflowPartyCustomisedWorkflowChoice.class)
		);
	}
	
	@Override
	public List<Function<? super PartyCustomisedWorkflow, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PartyCustomisedWorkflow> validator() {
		return new PartyCustomisedWorkflowValidator();
	}

	@Override
	public Validator<? super PartyCustomisedWorkflow> typeFormatValidator() {
		return new PartyCustomisedWorkflowTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PartyCustomisedWorkflow, Set<String>> onlyExistsValidator() {
		return new PartyCustomisedWorkflowOnlyExistsValidator();
	}
}
