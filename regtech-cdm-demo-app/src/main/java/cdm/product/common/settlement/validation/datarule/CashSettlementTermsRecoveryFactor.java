package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.CashSettlementTerms;
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
@RosettaDataRule("CashSettlementTermsRecoveryFactor")
@ImplementedBy(CashSettlementTermsRecoveryFactor.Default.class)
public interface CashSettlementTermsRecoveryFactor extends Validator<CashSettlementTerms> {
	
	String NAME = "CashSettlementTermsRecoveryFactor";
	String DEFINITION = "if recoveryFactor exists then recoveryFactor >= 0.0 and recoveryFactor <= 1.0";
	
	ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms cashSettlementTerms);
	
	class Default implements CashSettlementTermsRecoveryFactor {
	
		@Override
		public ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms cashSettlementTerms) {
			ComparisonResult result = executeDataRule(cashSettlementTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashSettlementTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CashSettlementTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CashSettlementTerms cashSettlementTerms) {
			try {
				if (exists(MapperS.of(cashSettlementTerms).<BigDecimal>map("getRecoveryFactor", _cashSettlementTerms -> _cashSettlementTerms.getRecoveryFactor())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(cashSettlementTerms).<BigDecimal>map("getRecoveryFactor", _cashSettlementTerms -> _cashSettlementTerms.getRecoveryFactor()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All).and(lessThanEquals(MapperS.of(cashSettlementTerms).<BigDecimal>map("getRecoveryFactor", _cashSettlementTerms -> _cashSettlementTerms.getRecoveryFactor()), MapperS.of(new BigDecimal("1.0")), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CashSettlementTermsRecoveryFactor {
	
		@Override
		public ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms cashSettlementTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashSettlementTerms", path, DEFINITION);
		}
	}
}
