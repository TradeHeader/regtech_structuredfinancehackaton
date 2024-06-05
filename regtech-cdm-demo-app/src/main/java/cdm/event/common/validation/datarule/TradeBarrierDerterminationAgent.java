package cdm.event.common.validation.datarule;

import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.functions.FilterPartyRole;
import cdm.event.common.Trade;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("TradeBarrierDerterminationAgent")
@ImplementedBy(TradeBarrierDerterminationAgent.Default.class)
public interface TradeBarrierDerterminationAgent extends Validator<Trade> {
	
	String NAME = "TradeBarrierDerterminationAgent";
	String DEFINITION = "if partyRole -> role contains PartyRoleEnum -> BarrierDeterminationAgent then FilterPartyRole(partyRole, PartyRoleEnum -> BarrierDeterminationAgent) count <= 1";
	
	ValidationResult<Trade> validate(RosettaPath path, Trade trade);
	
	class Default implements TradeBarrierDerterminationAgent {
	
		@Inject protected FilterPartyRole filterPartyRole;
		
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			ComparisonResult result = executeDataRule(trade);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Trade", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Trade trade) {
			try {
				if (contains(MapperS.of(trade).<PartyRole>mapC("getPartyRole", _trade -> _trade.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.BARRIER_DETERMINATION_AGENT)).getOrDefault(false)) {
					return lessThanEquals(MapperS.of(MapperC.<PartyRole>of(filterPartyRole.evaluate(MapperS.of(trade).<PartyRole>mapC("getPartyRole", _trade -> _trade.getPartyRole()).getMulti(), PartyRoleEnum.BARRIER_DETERMINATION_AGENT)).resultCount()), MapperS.of(1), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradeBarrierDerterminationAgent {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
		}
	}
}
