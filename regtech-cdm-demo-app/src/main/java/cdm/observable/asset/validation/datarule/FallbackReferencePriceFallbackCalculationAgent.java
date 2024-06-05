package cdm.observable.asset.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.observable.asset.FallbackReferencePrice;
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
@RosettaDataRule("FallbackReferencePriceFallbackCalculationAgent")
@ImplementedBy(FallbackReferencePriceFallbackCalculationAgent.Default.class)
public interface FallbackReferencePriceFallbackCalculationAgent extends Validator<FallbackReferencePrice> {
	
	String NAME = "FallbackReferencePriceFallbackCalculationAgent";
	String DEFINITION = "if calculationAgentDetermination -> calculationAgentParty exists then calculationAgentDetermination -> calculationAgentParty = AncillaryRoleEnum -> CalculationAgentFallback";
	
	ValidationResult<FallbackReferencePrice> validate(RosettaPath path, FallbackReferencePrice fallbackReferencePrice);
	
	class Default implements FallbackReferencePriceFallbackCalculationAgent {
	
		@Override
		public ValidationResult<FallbackReferencePrice> validate(RosettaPath path, FallbackReferencePrice fallbackReferencePrice) {
			ComparisonResult result = executeDataRule(fallbackReferencePrice);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FallbackReferencePrice", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FallbackReferencePrice", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FallbackReferencePrice fallbackReferencePrice) {
			try {
				if (exists(MapperS.of(fallbackReferencePrice).<CalculationAgent>map("getCalculationAgentDetermination", _fallbackReferencePrice -> _fallbackReferencePrice.getCalculationAgentDetermination()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(fallbackReferencePrice).<CalculationAgent>map("getCalculationAgentDetermination", _fallbackReferencePrice -> _fallbackReferencePrice.getCalculationAgentDetermination()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty()), MapperS.of(AncillaryRoleEnum.CALCULATION_AGENT_FALLBACK), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FallbackReferencePriceFallbackCalculationAgent {
	
		@Override
		public ValidationResult<FallbackReferencePrice> validate(RosettaPath path, FallbackReferencePrice fallbackReferencePrice) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FallbackReferencePrice", path, DEFINITION);
		}
	}
}
