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
@RosettaDataRule("InitialMarginMarginThreshold")
@ImplementedBy(InitialMarginMarginThreshold.Default.class)
public interface InitialMarginMarginThreshold extends Validator<InitialMargin> {
	
	String NAME = "InitialMarginMarginThreshold";
	String DEFINITION = "if marginThreshold exists then marginThreshold -> value > 0";
	
	ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin initialMargin);
	
	class Default implements InitialMarginMarginThreshold {
	
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
				if (exists(MapperS.of(initialMargin).<Money>map("getMarginThreshold", _initialMargin -> _initialMargin.getMarginThreshold())).getOrDefault(false)) {
					return greaterThan(MapperS.of(initialMargin).<Money>map("getMarginThreshold", _initialMargin -> _initialMargin.getMarginThreshold()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InitialMarginMarginThreshold {
	
		@Override
		public ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin initialMargin) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialMargin", path, DEFINITION);
		}
	}
}
