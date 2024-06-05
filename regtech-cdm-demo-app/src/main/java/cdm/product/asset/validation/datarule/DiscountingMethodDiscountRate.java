package cdm.product.asset.validation.datarule;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.product.asset.DiscountingMethod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("DiscountingMethodDiscountRate")
@ImplementedBy(DiscountingMethodDiscountRate.Default.class)
public interface DiscountingMethodDiscountRate extends Validator<DiscountingMethod> {
	
	String NAME = "DiscountingMethodDiscountRate";
	String DEFINITION = "if discountRateDayCountFraction exists then discountRate exists";
	
	ValidationResult<DiscountingMethod> validate(RosettaPath path, DiscountingMethod discountingMethod);
	
	class Default implements DiscountingMethodDiscountRate {
	
		@Override
		public ValidationResult<DiscountingMethod> validate(RosettaPath path, DiscountingMethod discountingMethod) {
			ComparisonResult result = executeDataRule(discountingMethod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DiscountingMethod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DiscountingMethod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DiscountingMethod discountingMethod) {
			try {
				if (exists(MapperS.of(discountingMethod).<FieldWithMetaDayCountFractionEnum>map("getDiscountRateDayCountFraction", _discountingMethod -> _discountingMethod.getDiscountRateDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(discountingMethod).<BigDecimal>map("getDiscountRate", _discountingMethod -> _discountingMethod.getDiscountRate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DiscountingMethodDiscountRate {
	
		@Override
		public ValidationResult<DiscountingMethod> validate(RosettaPath path, DiscountingMethod discountingMethod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DiscountingMethod", path, DEFINITION);
		}
	}
}
