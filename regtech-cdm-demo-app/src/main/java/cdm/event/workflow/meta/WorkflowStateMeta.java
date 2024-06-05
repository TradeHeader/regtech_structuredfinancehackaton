package cdm.event.workflow.meta;

import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.validation.WorkflowStateTypeFormatValidator;
import cdm.event.workflow.validation.WorkflowStateValidator;
import cdm.event.workflow.validation.exists.WorkflowStateOnlyExistsValidator;
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
@RosettaMeta(model=WorkflowState.class)
public class WorkflowStateMeta implements RosettaMetaData<WorkflowState> {

	@Override
	public List<Validator<? super WorkflowState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super WorkflowState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super WorkflowState> validator() {
		return new WorkflowStateValidator();
	}

	@Override
	public Validator<? super WorkflowState> typeFormatValidator() {
		return new WorkflowStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super WorkflowState, Set<String>> onlyExistsValidator() {
		return new WorkflowStateOnlyExistsValidator();
	}
}
