package cdm.event.workflow.meta;

import cdm.event.workflow.CustomisedWorkflow;
import cdm.event.workflow.validation.CustomisedWorkflowTypeFormatValidator;
import cdm.event.workflow.validation.CustomisedWorkflowValidator;
import cdm.event.workflow.validation.exists.CustomisedWorkflowOnlyExistsValidator;
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
@RosettaMeta(model=CustomisedWorkflow.class)
public class CustomisedWorkflowMeta implements RosettaMetaData<CustomisedWorkflow> {

	@Override
	public List<Validator<? super CustomisedWorkflow>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CustomisedWorkflow, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CustomisedWorkflow> validator() {
		return new CustomisedWorkflowValidator();
	}

	@Override
	public Validator<? super CustomisedWorkflow> typeFormatValidator() {
		return new CustomisedWorkflowTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CustomisedWorkflow, Set<String>> onlyExistsValidator() {
		return new CustomisedWorkflowOnlyExistsValidator();
	}
}
