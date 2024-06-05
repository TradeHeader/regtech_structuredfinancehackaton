package cdm.product.template.validation.datarule;

import cdm.observable.asset.Money;
import cdm.product.template.InitialMargin;
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
@RosettaDataRule("InitialMarginMinimumTransferAmount")
@ImplementedBy(InitialMarginMinimumTransferAmount.Default.class)
public interface InitialMarginMinimumTransferAmount extends Validator<InitialMargin> {
	
	String NAME = "InitialMarginMinimumTransferAmount";
	String DEFINITION = "if minimumTransferAmount exists then minimumTransferAmount -> value > 0";
	
	ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin initialMargin);
	
	class Default implements InitialMarginMinimumTransferAmount {
	
		@Override
		public ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin initialMargin) {
			ComparisonResult result = executeDataRule(initialMargin);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialMargin", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InitialMargin", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InitialMargin initialMargin) {
			try {
				if (exists(MapperS.of(initialMargin).<Money>map("getMinimumTransferAmount", _initialMargin -> _initialMargin.getMinimumTransferAmount())).getOrDefault(false)) {
					return greaterThan(MapperS.of(initialMargin).<Money>map("getMinimumTransferAmount", _initialMargin -> _initialMargin.getMinimumTransferAmount()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InitialMarginMinimumTransferAmount {
	
		@Override
		public ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin initialMargin) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialMargin", path, DEFINITION);
		}
	}
}
