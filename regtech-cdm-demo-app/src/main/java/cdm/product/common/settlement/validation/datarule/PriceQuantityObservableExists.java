package cdm.product.common.settlement.validation.datarule;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("PriceQuantityObservableExists")
@ImplementedBy(PriceQuantityObservableExists.Default.class)
public interface PriceQuantityObservableExists extends Validator<PriceQuantity> {
	
	String NAME = "PriceQuantityObservableExists";
	String DEFINITION = "if price -> arithmeticOperator exists then observable exists";
	
	ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity);
	
	class Default implements PriceQuantityObservableExists {
	
		@Override
		public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity) {
			ComparisonResult result = executeDataRule(priceQuantity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceQuantity priceQuantity) {
			try {
				if (exists(MapperS.of(priceQuantity).<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<ArithmeticOperationEnum>map("getArithmeticOperator", priceSchedule -> priceSchedule.getArithmeticOperator())).getOrDefault(false)) {
					return exists(MapperS.of(priceQuantity).<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceQuantityObservableExists {
	
		@Override
		public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION);
		}
	}
}
