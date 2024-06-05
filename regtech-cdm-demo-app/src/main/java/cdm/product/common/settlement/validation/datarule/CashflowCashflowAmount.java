package cdm.product.common.settlement.validation.datarule;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.ResolvablePriceQuantity;
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
@RosettaDataRule("CashflowCashflowAmount")
@ImplementedBy(CashflowCashflowAmount.Default.class)
public interface CashflowCashflowAmount extends Validator<Cashflow> {
	
	String NAME = "CashflowCashflowAmount";
	String DEFINITION = "if priceQuantity exists then priceQuantity -> quantitySchedule -> value >= 0.0";
	
	ValidationResult<Cashflow> validate(RosettaPath path, Cashflow cashflow);
	
	class Default implements CashflowCashflowAmount {
	
		@Override
		public ValidationResult<Cashflow> validate(RosettaPath path, Cashflow cashflow) {
			ComparisonResult result = executeDataRule(cashflow);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Cashflow", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Cashflow", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Cashflow cashflow) {
			try {
				if (exists(MapperS.of(cashflow).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(cashflow).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CashflowCashflowAmount {
	
		@Override
		public ValidationResult<Cashflow> validate(RosettaPath path, Cashflow cashflow) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Cashflow", path, DEFINITION);
		}
	}
}
