package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.MandatoryEarlyTermination;
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
@RosettaDataRule("MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent")
@ImplementedBy(MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent.Default.class)
public interface MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent extends Validator<MandatoryEarlyTermination> {
	
	String NAME = "MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent";
	String DEFINITION = "if calculationAgent -> calculationAgentParty exists then calculationAgent -> calculationAgentParty = AncillaryRoleEnum -> CalculationAgentMandatoryEarlyTermination";
	
	ValidationResult<MandatoryEarlyTermination> validate(RosettaPath path, MandatoryEarlyTermination mandatoryEarlyTermination);
	
	class Default implements MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent {
	
		@Override
		public ValidationResult<MandatoryEarlyTermination> validate(RosettaPath path, MandatoryEarlyTermination mandatoryEarlyTermination) {
			ComparisonResult result = executeDataRule(mandatoryEarlyTermination);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MandatoryEarlyTermination", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MandatoryEarlyTermination", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MandatoryEarlyTermination mandatoryEarlyTermination) {
			try {
				if (exists(MapperS.of(mandatoryEarlyTermination).<CalculationAgent>map("getCalculationAgent", _mandatoryEarlyTermination -> _mandatoryEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(mandatoryEarlyTermination).<CalculationAgent>map("getCalculationAgent", _mandatoryEarlyTermination -> _mandatoryEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty()), MapperS.of(AncillaryRoleEnum.CALCULATION_AGENT_MANDATORY_EARLY_TERMINATION), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MandatoryEarlyTerminationMandatoryEarlyTerminationCalculationAgent {
	
		@Override
		public ValidationResult<MandatoryEarlyTermination> validate(RosettaPath path, MandatoryEarlyTermination mandatoryEarlyTermination) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MandatoryEarlyTermination", path, DEFINITION);
		}
	}
}
