package cdm.event.common.validation.datarule;

import cdm.event.common.State;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.ClosedState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("StateClosedStateExists")
@ImplementedBy(StateClosedStateExists.Default.class)
public interface StateClosedStateExists extends Validator<State> {
	
	String NAME = "StateClosedStateExists";
	String DEFINITION = "if positionState = PositionStatusEnum -> Closed then closedState exists";
	
	ValidationResult<State> validate(RosettaPath path, State state);
	
	class Default implements StateClosedStateExists {
	
		@Override
		public ValidationResult<State> validate(RosettaPath path, State state) {
			ComparisonResult result = executeDataRule(state);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "State", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "State", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(State state) {
			try {
				if (areEqual(MapperS.of(state).<PositionStatusEnum>map("getPositionState", _state -> _state.getPositionState()), MapperS.of(PositionStatusEnum.CLOSED), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(state).<ClosedState>map("getClosedState", _state -> _state.getClosedState()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements StateClosedStateExists {
	
		@Override
		public ValidationResult<State> validate(RosettaPath path, State state) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "State", path, DEFINITION);
		}
	}
}
