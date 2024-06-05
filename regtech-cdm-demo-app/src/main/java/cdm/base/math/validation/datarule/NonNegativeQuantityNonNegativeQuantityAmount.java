package cdm.base.math.validation.datarule;

import cdm.base.math.NonNegativeQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("NonNegativeQuantityNonNegativeQuantity_amount")
@ImplementedBy(NonNegativeQuantityNonNegativeQuantityAmount.Default.class)
public interface NonNegativeQuantityNonNegativeQuantityAmount extends Validator<NonNegativeQuantity> {
	
	String NAME = "NonNegativeQuantityNonNegativeQuantity_amount";
	String DEFINITION = "value >= 0.0";
	
	ValidationResult<NonNegativeQuantity> validate(RosettaPath path, NonNegativeQuantity nonNegativeQuantity);
	
	class Default implements NonNegativeQuantityNonNegativeQuantityAmount {
	
		@Override
		public ValidationResult<NonNegativeQuantity> validate(RosettaPath path, NonNegativeQuantity nonNegativeQuantity) {
			ComparisonResult result = executeDataRule(nonNegativeQuantity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeQuantity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NonNegativeQuantity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NonNegativeQuantity nonNegativeQuantity) {
			try {
				return greaterThanEquals(MapperS.of(nonNegativeQuantity).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NonNegativeQuantityNonNegativeQuantityAmount {
	
		@Override
		public ValidationResult<NonNegativeQuantity> validate(RosettaPath path, NonNegativeQuantity nonNegativeQuantity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeQuantity", path, DEFINITION);
		}
	}
}
