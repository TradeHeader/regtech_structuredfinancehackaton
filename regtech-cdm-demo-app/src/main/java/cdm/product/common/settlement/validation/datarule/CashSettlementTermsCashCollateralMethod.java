package cdm.product.common.settlement.validation.datarule;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.ValuationMethod;
import cdm.product.common.settlement.CashSettlementMethodEnum;
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

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("CashSettlementTermsCashCollateralMethod")
@ImplementedBy(CashSettlementTermsCashCollateralMethod.Default.class)
public interface CashSettlementTermsCashCollateralMethod extends Validator<CashSettlementTerms> {
	
	String NAME = "CashSettlementTermsCashCollateralMethod";
	String DEFINITION = "if valuationMethod -> cashCollateralValuationMethod exists then cashSettlementMethod = CashSettlementMethodEnum -> MidMarketIndicativeQuotations or cashSettlementMethod = CashSettlementMethodEnum -> MidMarketIndicativeQuotationsAlternate or cashSettlementMethod = CashSettlementMethodEnum -> MidMarketCalculationAgentDetermination or cashSettlementMethod = CashSettlementMethodEnum -> ReplacementValueFirmQuotations or cashSettlementMethod = CashSettlementMethodEnum -> ReplacementValueCalculationAgentDetermination";
	
	ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms cashSettlementTerms);
	
	class Default implements CashSettlementTermsCashCollateralMethod {
	
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
				if (exists(MapperS.of(cashSettlementTerms).<ValuationMethod>map("getValuationMethod", _cashSettlementTerms -> _cashSettlementTerms.getValuationMethod()).<CashCollateralValuationMethod>map("getCashCollateralValuationMethod", valuationMethod -> valuationMethod.getCashCollateralValuationMethod())).getOrDefault(false)) {
					return areEqual(MapperS.of(cashSettlementTerms).<CashSettlementMethodEnum>map("getCashSettlementMethod", _cashSettlementTerms -> _cashSettlementTerms.getCashSettlementMethod()), MapperS.of(CashSettlementMethodEnum.MID_MARKET_INDICATIVE_QUOTATIONS), CardinalityOperator.All).or(areEqual(MapperS.of(cashSettlementTerms).<CashSettlementMethodEnum>map("getCashSettlementMethod", _cashSettlementTerms -> _cashSettlementTerms.getCashSettlementMethod()), MapperS.of(CashSettlementMethodEnum.MID_MARKET_INDICATIVE_QUOTATIONS_ALTERNATE), CardinalityOperator.All)).or(areEqual(MapperS.of(cashSettlementTerms).<CashSettlementMethodEnum>map("getCashSettlementMethod", _cashSettlementTerms -> _cashSettlementTerms.getCashSettlementMethod()), MapperS.of(CashSettlementMethodEnum.MID_MARKET_CALCULATION_AGENT_DETERMINATION), CardinalityOperator.All)).or(areEqual(MapperS.of(cashSettlementTerms).<CashSettlementMethodEnum>map("getCashSettlementMethod", _cashSettlementTerms -> _cashSettlementTerms.getCashSettlementMethod()), MapperS.of(CashSettlementMethodEnum.REPLACEMENT_VALUE_FIRM_QUOTATIONS), CardinalityOperator.All)).or(areEqual(MapperS.of(cashSettlementTerms).<CashSettlementMethodEnum>map("getCashSettlementMethod", _cashSettlementTerms -> _cashSettlementTerms.getCashSettlementMethod()), MapperS.of(CashSettlementMethodEnum.REPLACEMENT_VALUE_CALCULATION_AGENT_DETERMINATION), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CashSettlementTermsCashCollateralMethod {
	
		@Override
		public ValidationResult<CashSettlementTerms> validate(RosettaPath path, CashSettlementTerms cashSettlementTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashSettlementTerms", path, DEFINITION);
		}
	}
}
