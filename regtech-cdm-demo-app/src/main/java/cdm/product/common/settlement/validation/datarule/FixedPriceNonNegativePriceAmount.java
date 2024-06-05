package cdm.product.common.settlement.validation.datarule;

import cdm.base.math.DatedValue;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.common.settlement.FixedPrice;
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
@RosettaDataRule("FixedPriceNonNegativePrice_amount")
@ImplementedBy(FixedPriceNonNegativePriceAmount.Default.class)
public interface FixedPriceNonNegativePriceAmount extends Validator<FixedPrice> {
	
	String NAME = "FixedPriceNonNegativePrice_amount";
	String DEFINITION = "if price -> value exists then price -> value >= 0.0 and if price -> datedValue exists then price -> datedValue -> value all >= 0.0";
	
	ValidationResult<FixedPrice> validate(RosettaPath path, FixedPrice fixedPrice);
	
	class Default implements FixedPriceNonNegativePriceAmount {
	
		@Override
		public ValidationResult<FixedPrice> validate(RosettaPath path, FixedPrice fixedPrice) {
			ComparisonResult result = executeDataRule(fixedPrice);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FixedPrice", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FixedPrice", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FixedPrice fixedPrice) {
			try {
				if (exists(MapperS.of(fixedPrice).<ReferenceWithMetaPriceSchedule>map("getPrice", _fixedPrice -> _fixedPrice.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (exists(MapperS.of(fixedPrice).<ReferenceWithMetaPriceSchedule>map("getPrice", _fixedPrice -> _fixedPrice.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue())).getOrDefault(false)) {
						ifThenElseResult = greaterThanEquals(MapperS.of(fixedPrice).<ReferenceWithMetaPriceSchedule>map("getPrice", _fixedPrice -> _fixedPrice.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue()).<BigDecimal>map("getValue", datedValue -> datedValue.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return greaterThanEquals(MapperS.of(fixedPrice).<ReferenceWithMetaPriceSchedule>map("getPrice", _fixedPrice -> _fixedPrice.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FixedPriceNonNegativePriceAmount {
	
		@Override
		public ValidationResult<FixedPrice> validate(RosettaPath path, FixedPrice fixedPrice) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FixedPrice", path, DEFINITION);
		}
	}
}
