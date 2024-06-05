package cdm.product.template.validation.datarule;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.product.asset.ForeignExchange;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ForwardPayoutSettlementDate")
@ImplementedBy(ForwardPayoutSettlementDate.Default.class)
public interface ForwardPayoutSettlementDate extends Validator<ForwardPayout> {
	
	String NAME = "ForwardPayoutSettlementDate";
	String DEFINITION = "if underlier -> foreignExchange exists then (settlementTerms -> settlementDate -> valueDate exists and underlier -> foreignExchange -> exchangedCurrency1 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate is absent and underlier -> foreignExchange -> exchangedCurrency2 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate is absent) or (settlementTerms -> settlementDate -> valueDate is absent and underlier -> foreignExchange -> exchangedCurrency1 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate exists and underlier -> foreignExchange -> exchangedCurrency2 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate exists and underlier -> foreignExchange -> exchangedCurrency1 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate = underlier -> foreignExchange -> exchangedCurrency2 -> settlementTerms -> settlementDate -> adjustableOrRelativeDate)";
	
	ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout);
	
	class Default implements ForwardPayoutSettlementDate {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			ComparisonResult result = executeDataRule(forwardPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ForwardPayout forwardPayout) {
			try {
				if (exists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())).getOrDefault(false)) {
					return exists(MapperS.of(forwardPayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<Date>map("getValueDate", settlementDate -> settlementDate.getValueDate())).and(notExists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency1", foreignExchange -> foreignExchange.getExchangedCurrency1()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()))).and(notExists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency2", foreignExchange -> foreignExchange.getExchangedCurrency2()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()))).or(notExists(MapperS.of(forwardPayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<Date>map("getValueDate", settlementDate -> settlementDate.getValueDate())).and(exists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency1", foreignExchange -> foreignExchange.getExchangedCurrency1()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()))).and(exists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency2", foreignExchange -> foreignExchange.getExchangedCurrency2()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()))).and(areEqual(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency1", foreignExchange -> foreignExchange.getExchangedCurrency1()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()), MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()).<Cashflow>map("getExchangedCurrency2", foreignExchange -> foreignExchange.getExchangedCurrency2()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()), CardinalityOperator.All)));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ForwardPayoutSettlementDate {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
		}
	}
}
