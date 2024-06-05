package cdm.product.template.validation.datarule;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.RelativeDateOffset;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
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
import com.rosetta.model.metafields.FieldWithMetaDate;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("EconomicTermsFpML_cd_27")
@ImplementedBy(EconomicTermsFpMLCd27.Default.class)
public interface EconomicTermsFpMLCd27 extends Validator<EconomicTerms> {
	
	String NAME = "EconomicTermsFpML_cd_27";
	String DEFINITION = "if payout -> creditDefaultPayout exists and payout -> cashflow exists and terminationDate exists and payout -> cashflow -> settlementTerms -> settlementDate -> adjustableOrRelativeDate exists then payout -> cashflow -> settlementTerms -> settlementDate -> adjustableOrRelativeDate -> unadjustedDate all < terminationDate -> adjustableDate -> unadjustedDate or payout -> cashflow -> settlementTerms -> settlementDate -> adjustableOrRelativeDate -> adjustedDate all < terminationDate -> adjustableDate -> adjustedDate or payout -> cashflow -> settlementTerms -> settlementDate -> adjustableOrRelativeDate -> relativeDate -> adjustedDate all < terminationDate -> adjustableDate -> adjustedDate";
	
	ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms);
	
	class Default implements EconomicTermsFpMLCd27 {
	
		@Override
		public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms) {
			ComparisonResult result = executeDataRule(economicTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(EconomicTerms economicTerms) {
			try {
				if (exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout())).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow()))).and(exists(MapperS.of(economicTerms).<AdjustableOrRelativeDate>map("getTerminationDate", _economicTerms -> _economicTerms.getTerminationDate()))).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()))).getOrDefault(false)) {
					return lessThan(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()).<Date>map("getUnadjustedDate", adjustableOrAdjustedOrRelativeDate -> adjustableOrAdjustedOrRelativeDate.getUnadjustedDate()), MapperS.of(economicTerms).<AdjustableOrRelativeDate>map("getTerminationDate", _economicTerms -> _economicTerms.getTerminationDate()).<AdjustableDate>map("getAdjustableDate", adjustableOrRelativeDate -> adjustableOrRelativeDate.getAdjustableDate()).<Date>map("getUnadjustedDate", adjustableDate -> adjustableDate.getUnadjustedDate()), CardinalityOperator.All).or(lessThan(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableOrAdjustedOrRelativeDate -> adjustableOrAdjustedOrRelativeDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()), MapperS.of(economicTerms).<AdjustableOrRelativeDate>map("getTerminationDate", _economicTerms -> _economicTerms.getTerminationDate()).<AdjustableDate>map("getAdjustableDate", adjustableOrRelativeDate -> adjustableOrRelativeDate.getAdjustableDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableDate -> adjustableDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()), CardinalityOperator.All)).or(lessThan(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<SettlementDate>map("getSettlementDate", settlementBase -> settlementBase.getSettlementDate()).<AdjustableOrAdjustedOrRelativeDate>map("getAdjustableOrRelativeDate", settlementDate -> settlementDate.getAdjustableOrRelativeDate()).<RelativeDateOffset>map("getRelativeDate", adjustableOrAdjustedOrRelativeDate -> adjustableOrAdjustedOrRelativeDate.getRelativeDate()).<Date>map("getAdjustedDate", relativeDateOffset -> relativeDateOffset.getAdjustedDate()), MapperS.of(economicTerms).<AdjustableOrRelativeDate>map("getTerminationDate", _economicTerms -> _economicTerms.getTerminationDate()).<AdjustableDate>map("getAdjustableDate", adjustableOrRelativeDate -> adjustableOrRelativeDate.getAdjustableDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableDate -> adjustableDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements EconomicTermsFpMLCd27 {
	
		@Override
		public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION);
		}
	}
}
