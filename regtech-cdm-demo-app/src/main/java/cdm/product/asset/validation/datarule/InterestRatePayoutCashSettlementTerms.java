package cdm.product.asset.validation.datarule;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.ValuationMethod;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.ValuationDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("InterestRatePayoutCashSettlementTerms")
@ImplementedBy(InterestRatePayoutCashSettlementTerms.Default.class)
public interface InterestRatePayoutCashSettlementTerms extends Validator<InterestRatePayout> {
	
	String NAME = "InterestRatePayoutCashSettlementTerms";
	String DEFINITION = "if settlementTerms -> settlementCurrency exists and (settlementTerms -> settlementCurrency <> priceQuantity -> quantitySchedule -> unit -> currency or settlementTerms -> settlementCurrency <> priceQuantity -> quantityMultiplier -> fxLinkedNotionalSchedule -> varyingNotionalCurrency) then (settlementTerms -> cashSettlementTerms -> valuationMethod exists and settlementTerms -> cashSettlementTerms -> valuationDate exists) or priceQuantity -> quantityMultiplier -> fxLinkedNotionalSchedule -> fxSpotRateSource exists";
	
	ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout);
	
	class Default implements InterestRatePayoutCashSettlementTerms {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			ComparisonResult result = executeDataRule(interestRatePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InterestRatePayout interestRatePayout) {
			try {
				if (exists(MapperS.of(interestRatePayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<FieldWithMetaString>map("getSettlementCurrency", settlementBase -> settlementBase.getSettlementCurrency()).<String>map("getValue", _f->_f.getValue())).and(notEqual(MapperS.of(interestRatePayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<FieldWithMetaString>map("getSettlementCurrency", settlementBase -> settlementBase.getSettlementCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.Any).or(notEqual(MapperS.of(interestRatePayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<FieldWithMetaString>map("getSettlementCurrency", settlementBase -> settlementBase.getSettlementCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<QuantityMultiplier>map("getQuantityMultiplier", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantityMultiplier()).<FxLinkedNotionalSchedule>map("getFxLinkedNotionalSchedule", quantityMultiplier -> quantityMultiplier.getFxLinkedNotionalSchedule()).<FieldWithMetaString>map("getVaryingNotionalCurrency", fxLinkedNotionalSchedule -> fxLinkedNotionalSchedule.getVaryingNotionalCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.Any))).getOrDefault(false)) {
					return exists(MapperS.of(interestRatePayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<CashSettlementTerms>mapC("getCashSettlementTerms", settlementTerms -> settlementTerms.getCashSettlementTerms()).<ValuationMethod>map("getValuationMethod", cashSettlementTerms -> cashSettlementTerms.getValuationMethod())).and(exists(MapperS.of(interestRatePayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<CashSettlementTerms>mapC("getCashSettlementTerms", settlementTerms -> settlementTerms.getCashSettlementTerms()).<ValuationDate>map("getValuationDate", cashSettlementTerms -> cashSettlementTerms.getValuationDate()))).or(exists(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<QuantityMultiplier>map("getQuantityMultiplier", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantityMultiplier()).<FxLinkedNotionalSchedule>map("getFxLinkedNotionalSchedule", quantityMultiplier -> quantityMultiplier.getFxLinkedNotionalSchedule()).<FxSpotRateSource>map("getFxSpotRateSource", fxLinkedNotionalSchedule -> fxLinkedNotionalSchedule.getFxSpotRateSource())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InterestRatePayoutCashSettlementTerms {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
		}
	}
}
