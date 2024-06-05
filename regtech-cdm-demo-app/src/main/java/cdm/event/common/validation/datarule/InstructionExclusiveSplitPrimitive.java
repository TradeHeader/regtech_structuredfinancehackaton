package cdm.event.common.validation.datarule;

import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("InstructionExclusiveSplitPrimitive")
@ImplementedBy(InstructionExclusiveSplitPrimitive.Default.class)
public interface InstructionExclusiveSplitPrimitive extends Validator<Instruction> {
	
	String NAME = "InstructionExclusiveSplitPrimitive";
	String DEFINITION = "if primitiveInstruction -> split exists then primitiveInstruction -> split only exists";
	
	ValidationResult<Instruction> validate(RosettaPath path, Instruction instruction);
	
	class Default implements InstructionExclusiveSplitPrimitive {
	
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
				if (exists(MapperS.of(instruction).<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit())).getOrDefault(false)) {
					return onlyExists(Arrays.asList(MapperS.of(instruction).<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InstructionExclusiveSplitPrimitive {
	
		@Override
		public ValidationResult<Instruction> validate(RosettaPath path, Instruction instruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Instruction", path, DEFINITION);
		}
	}
}
