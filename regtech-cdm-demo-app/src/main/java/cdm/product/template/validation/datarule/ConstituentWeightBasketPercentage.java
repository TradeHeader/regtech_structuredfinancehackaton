package cdm.product.template.validation.datarule;

import cdm.product.template.ConstituentWeight;
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
@RosettaDataRule("ConstituentWeightBasketPercentage")
@ImplementedBy(ConstituentWeightBasketPercentage.Default.class)
public interface ConstituentWeightBasketPercentage extends Validator<ConstituentWeight> {
	
	String NAME = "ConstituentWeightBasketPercentage";
	String DEFINITION = "if basketPercentage exists then basketPercentage >= 0.0 and basketPercentage <= 1.0";
	
	ValidationResult<ConstituentWeight> validate(RosettaPath path, ConstituentWeight constituentWeight);
	
	class Default implements ConstituentWeightBasketPercentage {
	
		@Override
		public ValidationResult<ConstituentWeight> validate(RosettaPath path, ConstituentWeight constituentWeight) {
			ComparisonResult result = executeDataRule(constituentWeight);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConstituentWeight", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ConstituentWeight", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ConstituentWeight constituentWeight) {
			try {
				if (exists(MapperS.of(constituentWeight).<BigDecimal>map("getBasketPercentage", _constituentWeight -> _constituentWeight.getBasketPercentage())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(constituentWeight).<BigDecimal>map("getBasketPercentage", _constituentWeight -> _constituentWeight.getBasketPercentage()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All).and(lessThanEquals(MapperS.of(constituentWeight).<BigDecimal>map("getBasketPercentage", _constituentWeight -> _constituentWeight.getBasketPercentage()), MapperS.of(new BigDecimal("1.0")), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ConstituentWeightBasketPercentage {
	
		@Override
		public ValidationResult<ConstituentWeight> validate(RosettaPath path, ConstituentWeight constituentWeight) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConstituentWeight", path, DEFINITION);
		}
	}
}
