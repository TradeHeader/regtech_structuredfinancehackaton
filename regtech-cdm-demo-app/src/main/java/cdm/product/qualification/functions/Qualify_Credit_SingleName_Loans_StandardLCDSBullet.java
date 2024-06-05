package cdm.product.qualification.functions;

import cdm.event.common.ContractDetails;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.ContractualSupplementTypeEnum;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualSupplementTypeEnum;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Credit_SingleName_Loans_StandardLCDSBullet.Qualify_Credit_SingleName_Loans_StandardLCDSBulletDefault.class)
public abstract class Qualify_Credit_SingleName_Loans_StandardLCDSBullet implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_CreditDefaultSwap_Loan qualify_CreditDefaultSwap_Loan;

	/**
	* @param tradeState 
	* @return is_product 
	*/
	public Boolean evaluate(TradeState tradeState) {
		Boolean is_product = doEvaluate(tradeState);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(TradeState tradeState);

	protected abstract MapperS<? extends EconomicTerms> economicTerms(TradeState tradeState);

	protected abstract MapperC<ContractualSupplementTypeEnum> contractualTermsSupplementType(TradeState tradeState);

	public static class Qualify_Credit_SingleName_Loans_StandardLCDSBulletDefault extends Qualify_Credit_SingleName_Loans_StandardLCDSBullet {
		@Override
		protected Boolean doEvaluate(TradeState tradeState) {
			Boolean is_product = null;
			return assignOutput(is_product, tradeState);
		}
		
		protected Boolean assignOutput(Boolean is_product, TradeState tradeState) {
			is_product = ComparisonResult.of(MapperS.of(qualify_CreditDefaultSwap_Loan.evaluate(economicTerms(tradeState).get()))).and(areEqual(contractualTermsSupplementType(tradeState), MapperS.of(ContractualSupplementTypeEnum.STANDARD_LCDS_BULLET), CardinalityOperator.Any)).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> economicTerms(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms());
		}
		
		@Override
		protected MapperC<ContractualSupplementTypeEnum> contractualTermsSupplementType(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ContractDetails>map("getContractDetails", trade -> trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<ContractualTermsSupplement>mapC("getContractualTermsSupplement", agreementName -> agreementName.getContractualTermsSupplement()).<FieldWithMetaContractualSupplementTypeEnum>map("getContractualTermsSupplementType", contractualTermsSupplement -> contractualTermsSupplement.getContractualTermsSupplementType()).<ContractualSupplementTypeEnum>map("getValue", _f->_f.getValue());
		}
	}
}
