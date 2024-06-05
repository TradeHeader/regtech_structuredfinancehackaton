package cdm.event.common.validation.datarule;

import cdm.event.common.CallTypeEnum;
import cdm.event.common.MarginCallInstructionType;
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
@RosettaDataRule("MarginCallInstructionTypeCallTypeExpectedVisibility")
@ImplementedBy(MarginCallInstructionTypeCallTypeExpectedVisibility.Default.class)
public interface MarginCallInstructionTypeCallTypeExpectedVisibility extends Validator<MarginCallInstructionType> {
	
	String NAME = "MarginCallInstructionTypeCallTypeExpectedVisibility";
	String DEFINITION = "if callType = CallTypeEnum -> ExpectedCall then visibilityIndicator exists";
	
	ValidationResult<MarginCallInstructionType> validate(RosettaPath path, MarginCallInstructionType marginCallInstructionType);
	
	class Default implements MarginCallInstructionTypeCallTypeExpectedVisibility {
	
		@Override
		public ValidationResult<MarginCallInstructionType> validate(RosettaPath path, MarginCallInstructionType marginCallInstructionType) {
			ComparisonResult result = executeDataRule(marginCallInstructionType);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallInstructionType", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MarginCallInstructionType", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MarginCallInstructionType marginCallInstructionType) {
			try {
				if (areEqual(MapperS.of(marginCallInstructionType).<CallTypeEnum>map("getCallType", _marginCallInstructionType -> _marginCallInstructionType.getCallType()), MapperS.of(CallTypeEnum.EXPECTED_CALL), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(marginCallInstructionType).<Boolean>map("getVisibilityIndicator", _marginCallInstructionType -> _marginCallInstructionType.getVisibilityIndicator()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MarginCallInstructionTypeCallTypeExpectedVisibility {
	
		@Override
		public ValidationResult<MarginCallInstructionType> validate(RosettaPath path, MarginCallInstructionType marginCallInstructionType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallInstructionType", path, DEFINITION);
		}
	}
}
