package cdm.observable.asset.validation.datarule;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceOperandEnum;
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
@RosettaDataRule("PriceCompositeArithmeticOperator")
@ImplementedBy(PriceCompositeArithmeticOperator.Default.class)
public interface PriceCompositeArithmeticOperator extends Validator<PriceComposite> {
	
	String NAME = "PriceCompositeArithmeticOperator";
	String DEFINITION = "if operandType = PriceOperandEnum -> ForwardPoint or operandType = PriceOperandEnum -> AccruedInterest then arithmeticOperator = ArithmeticOperationEnum -> Add or arithmeticOperator = ArithmeticOperationEnum -> Subtract";
	
	ValidationResult<PriceComposite> validate(RosettaPath path, PriceComposite priceComposite);
	
	class Default implements PriceCompositeArithmeticOperator {
	
		@Override
		public ValidationResult<PriceComposite> validate(RosettaPath path, PriceComposite priceComposite) {
			ComparisonResult result = executeDataRule(priceComposite);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceComposite", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceComposite", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceComposite priceComposite) {
			try {
				if (areEqual(MapperS.of(priceComposite).<PriceOperandEnum>map("getOperandType", _priceComposite -> _priceComposite.getOperandType()), MapperS.of(PriceOperandEnum.FORWARD_POINT), CardinalityOperator.All).or(areEqual(MapperS.of(priceComposite).<PriceOperandEnum>map("getOperandType", _priceComposite -> _priceComposite.getOperandType()), MapperS.of(PriceOperandEnum.ACCRUED_INTEREST), CardinalityOperator.All)).getOrDefault(false)) {
					return areEqual(MapperS.of(priceComposite).<ArithmeticOperationEnum>map("getArithmeticOperator", _priceComposite -> _priceComposite.getArithmeticOperator()), MapperS.of(ArithmeticOperationEnum.ADD), CardinalityOperator.All).or(areEqual(MapperS.of(priceComposite).<ArithmeticOperationEnum>map("getArithmeticOperator", _priceComposite -> _priceComposite.getArithmeticOperator()), MapperS.of(ArithmeticOperationEnum.SUBTRACT), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceCompositeArithmeticOperator {
	
		@Override
		public ValidationResult<PriceComposite> validate(RosettaPath path, PriceComposite priceComposite) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceComposite", path, DEFINITION);
		}
	}
}
