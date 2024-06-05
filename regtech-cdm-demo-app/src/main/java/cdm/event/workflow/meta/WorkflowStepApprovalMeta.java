package cdm.event.workflow.meta;

import cdm.event.workflow.WorkflowStepApproval;
import cdm.event.workflow.validation.WorkflowStepApprovalTypeFormatValidator;
import cdm.event.workflow.validation.WorkflowStepApprovalValidator;
import cdm.event.workflow.validation.exists.WorkflowStepApprovalOnlyExistsValidator;
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
@RosettaMeta(model=WorkflowStepApproval.class)
public class WorkflowStepApprovalMeta implements RosettaMetaData<WorkflowStepApproval> {

	@Override
	public List<Validator<? super WorkflowStepApproval>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super WorkflowStepApproval, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super WorkflowStepApproval> validator() {
		return new WorkflowStepApprovalValidator();
	}

	@Override
	public Validator<? super WorkflowStepApproval> typeFormatValidator() {
		return new WorkflowStepApprovalTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super WorkflowStepApproval, Set<String>> onlyExistsValidator() {
		return new WorkflowStepApprovalOnlyExistsValidator();
	}
}
