package cdm.event.common.validation.datarule;

import cdm.event.common.MarginCallBase;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
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
@RosettaDataRule("MarginCallBaseRegIMRoleIMOnly")
@ImplementedBy(MarginCallBaseRegIMRoleIMOnly.Default.class)
public interface MarginCallBaseRegIMRoleIMOnly extends Validator<MarginCallBase> {
	
	String NAME = "MarginCallBaseRegIMRoleIMOnly";
	String DEFINITION = "if regIMRole exists then regMarginType = RegMarginTypeEnum -> RegIM";
	
	ValidationResult<MarginCallBase> validate(RosettaPath path, MarginCallBase marginCallBase);
	
	class Default implements MarginCallBaseRegIMRoleIMOnly {
	
		@Override
		public ValidationResult<MarginCallBase> validate(RosettaPath path, MarginCallBase marginCallBase) {
			ComparisonResult result = executeDataRule(marginCallBase);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallBase", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MarginCallBase", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MarginCallBase marginCallBase) {
			try {
				if (exists(MapperS.of(marginCallBase).<RegIMRoleEnum>map("getRegIMRole", _marginCallBase -> _marginCallBase.getRegIMRole())).getOrDefault(false)) {
					return areEqual(MapperS.of(marginCallBase).<RegMarginTypeEnum>map("getRegMarginType", _marginCallBase -> _marginCallBase.getRegMarginType()), MapperS.of(RegMarginTypeEnum.REG_IM), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MarginCallBaseRegIMRoleIMOnly {
	
		@Override
		public ValidationResult<MarginCallBase> validate(RosettaPath path, MarginCallBase marginCallBase) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallBase", path, DEFINITION);
		}
	}
}
