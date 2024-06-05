package cdm.event.workflow.meta;

import cdm.event.workflow.Workflow;
import cdm.event.workflow.validation.WorkflowTypeFormatValidator;
import cdm.event.workflow.validation.WorkflowValidator;
import cdm.event.workflow.validation.exists.WorkflowOnlyExistsValidator;
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
@RosettaMeta(model=Workflow.class)
public class WorkflowMeta implements RosettaMetaData<Workflow> {

	@Override
	public List<Validator<? super Workflow>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Workflow, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Workflow> validator() {
		return new WorkflowValidator();
	}

	@Override
	public Validator<? super Workflow> typeFormatValidator() {
		return new WorkflowTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Workflow, Set<String>> onlyExistsValidator() {
		return new WorkflowOnlyExistsValidator();
	}
}
