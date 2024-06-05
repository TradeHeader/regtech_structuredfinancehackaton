package cdm.event.workflow.functions;

import cdm.event.workflow.Workflow;
import cdm.event.workflow.Workflow.WorkflowBuilder;
import cdm.event.workflow.WorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Workflow.Create_WorkflowDefault.class)
public abstract class Create_Workflow implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param steps 
	* @return workflow 
	*/
	public Workflow evaluate(List<? extends WorkflowStep> steps) {
		Workflow.WorkflowBuilder workflowBuilder = doEvaluate(steps);
		
		final Workflow workflow;
		if (workflowBuilder == null) {
			workflow = null;
		} else {
			workflow = workflowBuilder.build();
			objectValidator.validate(Workflow.class, workflow);
		}
		
		return workflow;
	}

	protected abstract Workflow.WorkflowBuilder doEvaluate(List<? extends WorkflowStep> steps);

	public static class Create_WorkflowDefault extends Create_Workflow {
		@Override
		protected Workflow.WorkflowBuilder doEvaluate(List<? extends WorkflowStep> steps) {
			if (steps == null) {
				steps = Collections.emptyList();
			}
			Workflow.WorkflowBuilder workflow = Workflow.builder();
			return assignOutput(workflow, steps);
		}
		
		protected Workflow.WorkflowBuilder assignOutput(Workflow.WorkflowBuilder workflow, List<? extends WorkflowStep> steps) {
			workflow
				.addSteps(steps);
			
			return Optional.ofNullable(workflow)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
