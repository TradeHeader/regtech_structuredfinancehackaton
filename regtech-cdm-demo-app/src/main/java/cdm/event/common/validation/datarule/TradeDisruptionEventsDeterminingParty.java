package cdm.event.common.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.event.common.ContractDetails;
import cdm.event.common.Trade;
import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.TransactionAdditionalTerms;
import cdm.product.template.TradableProduct;
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
@RosettaDataRule("TradeDisruptionEventsDeterminingParty")
@ImplementedBy(TradeDisruptionEventsDeterminingParty.Default.class)
public interface TradeDisruptionEventsDeterminingParty extends Validator<Trade> {
	
	String NAME = "TradeDisruptionEventsDeterminingParty";
	String DEFINITION = "if contractDetails -> documentation -> agreementTerms -> agreement -> transactionAdditionalTerms -> equityAdditionalTerms -> extraordinaryEvents -> additionalDisruptionEvents -> determiningParty exists then tradableProduct -> ancillaryParty -> role contains AncillaryRoleEnum -> DisruptionEventsDeterminingParty and if tradableProduct -> ancillaryParty -> role contains AncillaryRoleEnum -> DisruptionEventsDeterminingParty then contractDetails -> documentation -> agreementTerms -> agreement -> transactionAdditionalTerms -> equityAdditionalTerms -> extraordinaryEvents -> additionalDisruptionEvents -> determiningParty exists";
	
	ValidationResult<Trade> validate(RosettaPath path, Trade trade);
	
	class Default implements TradeDisruptionEventsDeterminingParty {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			ComparisonResult result = executeDataRule(trade);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Trade", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Trade trade) {
			try {
				if (exists(MapperS.of(trade).<ContractDetails>map("getContractDetails", _trade -> _trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).<AgreementTerms>map("getAgreementTerms", legalAgreement -> legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<TransactionAdditionalTerms>map("getTransactionAdditionalTerms", agreement -> agreement.getTransactionAdditionalTerms()).<EquityAdditionalTerms>map("getEquityAdditionalTerms", transactionAdditionalTerms -> transactionAdditionalTerms.getEquityAdditionalTerms()).<ExtraordinaryEvents>map("getExtraordinaryEvents", equityAdditionalTerms -> equityAdditionalTerms.getExtraordinaryEvents()).<AdditionalDisruptionEvents>map("getAdditionalDisruptionEvents", extraordinaryEvents -> extraordinaryEvents.getAdditionalDisruptionEvents()).<AncillaryRoleEnum>map("getDeterminingParty", additionalDisruptionEvents -> additionalDisruptionEvents.getDeterminingParty())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (contains(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<AncillaryParty>mapC("getAncillaryParty", tradableProduct -> tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.DISRUPTION_EVENTS_DETERMINING_PARTY)).getOrDefault(false)) {
						ifThenElseResult = exists(MapperS.of(trade).<ContractDetails>map("getContractDetails", _trade -> _trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).<AgreementTerms>map("getAgreementTerms", legalAgreement -> legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<TransactionAdditionalTerms>map("getTransactionAdditionalTerms", agreement -> agreement.getTransactionAdditionalTerms()).<EquityAdditionalTerms>map("getEquityAdditionalTerms", transactionAdditionalTerms -> transactionAdditionalTerms.getEquityAdditionalTerms()).<ExtraordinaryEvents>map("getExtraordinaryEvents", equityAdditionalTerms -> equityAdditionalTerms.getExtraordinaryEvents()).<AdditionalDisruptionEvents>map("getAdditionalDisruptionEvents", extraordinaryEvents -> extraordinaryEvents.getAdditionalDisruptionEvents()).<AncillaryRoleEnum>map("getDeterminingParty", additionalDisruptionEvents -> additionalDisruptionEvents.getDeterminingParty()));
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return contains(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<AncillaryParty>mapC("getAncillaryParty", tradableProduct -> tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.DISRUPTION_EVENTS_DETERMINING_PARTY)).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradeDisruptionEventsDeterminingParty {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
		}
	}
}
