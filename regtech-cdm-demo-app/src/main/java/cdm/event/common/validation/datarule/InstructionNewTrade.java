package cdm.event.common.validation.datarule;

import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
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
@RosettaDataRule("InstructionNewTrade")
@ImplementedBy(InstructionNewTrade.Default.class)
public interface InstructionNewTrade extends Validator<Instruction> {
	
	String NAME = "InstructionNewTrade";
	String DEFINITION = "(if primitiveInstruction -> execution exists then before is absent) and (if before is absent then primitiveInstruction -> execution exists)";
	
	ValidationResult<Instruction> validate(RosettaPath path, Instruction instruction);
	
	class Default implements InstructionNewTrade {
	
		@Override
		public ValidationResult<Instruction> validate(RosettaPath path, Instruction instruction) {
			ComparisonResult result = executeDataRule(instruction);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Instruction", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Instruction", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Instruction instruction) {
			try {
				final ComparisonResult ifThenElseResult0;
				if (exists(MapperS.of(instruction).<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<ExecutionInstruction>map("getExecution", primitiveInstruction -> primitiveInstruction.getExecution())).getOrDefault(false)) {
					ifThenElseResult0 = notExists(MapperS.of(instruction).<ReferenceWithMetaTradeState>map("getBefore", _instruction -> _instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()));
				} else {
					ifThenElseResult0 = ComparisonResult.successEmptyOperand("");
				}
				final ComparisonResult ifThenElseResult1;
				if (notExists(MapperS.of(instruction).<ReferenceWithMetaTradeState>map("getBefore", _instruction -> _instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					ifThenElseResult1 = exists(MapperS.of(instruction).<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<ExecutionInstruction>map("getExecution", primitiveInstruction -> primitiveInstruction.getExecution()));
				} else {
					ifThenElseResult1 = ComparisonResult.successEmptyOperand("");
				}
				return ifThenElseResult0.and(ifThenElseResult1);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InstructionNewTrade {
	
		@Override
		public ValidationResult<Instruction> validate(RosettaPath path, Instruction instruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Instruction", path, DEFINITION);
		}
	}
}
