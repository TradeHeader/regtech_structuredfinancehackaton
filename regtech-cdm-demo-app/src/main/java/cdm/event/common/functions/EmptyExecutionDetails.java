package cdm.event.common.functions;

import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionDetails.ExecutionDetailsBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(EmptyExecutionDetails.EmptyExecutionDetailsDefault.class)
public abstract class EmptyExecutionDetails implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @return executionDetails 
	*/
	public ExecutionDetails evaluate() {
		ExecutionDetails.ExecutionDetailsBuilder executionDetailsBuilder = doEvaluate();
		
		final ExecutionDetails executionDetails;
		if (executionDetailsBuilder == null) {
			executionDetails = null;
		} else {
			executionDetails = executionDetailsBuilder.build();
			objectValidator.validate(ExecutionDetails.class, executionDetails);
		}
		
		return executionDetails;
	}

	protected abstract ExecutionDetails.ExecutionDetailsBuilder doEvaluate();

	public static class EmptyExecutionDetailsDefault extends EmptyExecutionDetails {
		@Override
		protected ExecutionDetails.ExecutionDetailsBuilder doEvaluate() {
			ExecutionDetails.ExecutionDetailsBuilder executionDetails = ExecutionDetails.builder();
			return assignOutput(executionDetails);
		}
		
		protected ExecutionDetails.ExecutionDetailsBuilder assignOutput(ExecutionDetails.ExecutionDetailsBuilder executionDetails) {
			return Optional.ofNullable(executionDetails)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
