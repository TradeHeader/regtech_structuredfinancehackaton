package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.OptionalEarlyTermination;
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
@RosettaDataRule("OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent")
@ImplementedBy(OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent.Default.class)
public interface OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent extends Validator<OptionalEarlyTermination> {
	
	String NAME = "OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent";
	String DEFINITION = "if calculationAgent -> calculationAgentParty exists then calculationAgent -> calculationAgentParty = AncillaryRoleEnum -> CalculationAgentOptionalEarlyTermination";
	
	ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination);
	
	class Default implements OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			ComparisonResult result = executeDataRule(optionalEarlyTermination);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionalEarlyTermination optionalEarlyTermination) {
			try {
				if (exists(MapperS.of(optionalEarlyTermination).<CalculationAgent>map("getCalculationAgent", _optionalEarlyTermination -> _optionalEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(optionalEarlyTermination).<CalculationAgent>map("getCalculationAgent", _optionalEarlyTermination -> _optionalEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty()), MapperS.of(AncillaryRoleEnum.CALCULATION_AGENT_OPTIONAL_EARLY_TERMINATION), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
		}
	}
}
