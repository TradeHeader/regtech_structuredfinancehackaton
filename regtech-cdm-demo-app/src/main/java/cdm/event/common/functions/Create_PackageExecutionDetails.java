package cdm.event.common.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.LegalEntity;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionDetails.ExecutionDetailsBuilder;
import cdm.event.common.ExecutionTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_PackageExecutionDetails.Create_PackageExecutionDetailsDefault.class)
public abstract class Create_PackageExecutionDetails implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param executionDetails The original execution details. These may be empty.
	* @param listId The identifier for the package.
	* @param componentId The list of identifiers for the package components. There must be at least 2.
	* @return newExecutionDetails 
	*/
	public ExecutionDetails evaluate(ExecutionDetails executionDetails, Identifier listId, List<? extends Identifier> componentId) {
		ExecutionDetails.ExecutionDetailsBuilder newExecutionDetailsBuilder = doEvaluate(executionDetails, listId, componentId);
		
		final ExecutionDetails newExecutionDetails;
		if (newExecutionDetailsBuilder == null) {
			newExecutionDetails = null;
		} else {
			newExecutionDetails = newExecutionDetailsBuilder.build();
			objectValidator.validate(ExecutionDetails.class, newExecutionDetails);
		}
		
		return newExecutionDetails;
	}

	protected abstract ExecutionDetails.ExecutionDetailsBuilder doEvaluate(ExecutionDetails executionDetails, Identifier listId, List<? extends Identifier> componentId);

	public static class Create_PackageExecutionDetailsDefault extends Create_PackageExecutionDetails {
		@Override
		protected ExecutionDetails.ExecutionDetailsBuilder doEvaluate(ExecutionDetails executionDetails, Identifier listId, List<? extends Identifier> componentId) {
			if (componentId == null) {
				componentId = Collections.emptyList();
			}
			ExecutionDetails.ExecutionDetailsBuilder newExecutionDetails = ExecutionDetails.builder();
			return assignOutput(newExecutionDetails, executionDetails, listId, componentId);
		}
		
		protected ExecutionDetails.ExecutionDetailsBuilder assignOutput(ExecutionDetails.ExecutionDetailsBuilder newExecutionDetails, ExecutionDetails executionDetails, Identifier listId, List<? extends Identifier> componentId) {
			newExecutionDetails
				.setExecutionType(MapperS.of(executionDetails).<ExecutionTypeEnum>map("getExecutionType", _executionDetails -> _executionDetails.getExecutionType()).get());
			
			newExecutionDetails
				.setExecutionVenue(MapperS.of(executionDetails).<LegalEntity>map("getExecutionVenue", _executionDetails -> _executionDetails.getExecutionVenue()).get());
			
			newExecutionDetails
				.getOrCreatePackageReference()
				.setListId(listId);
			
			newExecutionDetails
				.getOrCreatePackageReference()
				.setComponentId(componentId);
			
			return Optional.ofNullable(newExecutionDetails)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
