package cdm.event.common.validation.datarule;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("TradePackageTrade")
@ImplementedBy(TradePackageTrade.Default.class)
public interface TradePackageTrade extends Validator<Trade> {
	
	String NAME = "TradePackageTrade";
	String DEFINITION = "if executionDetails -> packageReference exists then executionDetails -> packageReference -> componentId -> assignedIdentifier contains tradeIdentifier -> assignedIdentifier";
	
	ValidationResult<Trade> validate(RosettaPath path, Trade trade);
	
	class Default implements TradePackageTrade {
	
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
				if (exists(MapperS.of(trade).<ExecutionDetails>map("getExecutionDetails", _trade -> _trade.getExecutionDetails()).<IdentifiedList>map("getPackageReference", executionDetails -> executionDetails.getPackageReference())).getOrDefault(false)) {
					return contains(MapperS.of(trade).<ExecutionDetails>map("getExecutionDetails", _trade -> _trade.getExecutionDetails()).<IdentifiedList>map("getPackageReference", executionDetails -> executionDetails.getPackageReference()).<Identifier>mapC("getComponentId", identifiedList -> identifiedList.getComponentId()).<AssignedIdentifier>mapC("getAssignedIdentifier", identifier -> identifier.getAssignedIdentifier()), MapperS.of(trade).<TradeIdentifier>mapC("getTradeIdentifier", _trade -> _trade.getTradeIdentifier()).<AssignedIdentifier>mapC("getAssignedIdentifier", identifier -> identifier.getAssignedIdentifier()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradePackageTrade {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
		}
	}
}
