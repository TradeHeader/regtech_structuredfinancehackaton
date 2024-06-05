package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.credit.Obligations;
import cdm.event.common.ContractDetails;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.ContractualSupplementTypeEnum;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualSupplementTypeEnum;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.ProtectionTerms;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
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

@ImplementedBy(Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche.Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTrancheDefault.class)
public abstract class Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_CreditDefaultSwap_IndexTranche qualify_CreditDefaultSwap_IndexTranche;

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

	protected abstract MapperC<? extends Obligations> obligations(TradeState tradeState);

	public static class Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTrancheDefault extends Qualify_Credit_IndexTranche_LCDX_StandardLCDXBulletTranche {
		@Override
		protected Boolean doEvaluate(TradeState tradeState) {
			Boolean is_product = null;
			return assignOutput(is_product, tradeState);
		}
		
		protected Boolean assignOutput(Boolean is_product, TradeState tradeState) {
			is_product = ComparisonResult.of(MapperS.of(qualify_CreditDefaultSwap_IndexTranche.evaluate(economicTerms(tradeState).get()))).and(areEqual(contractualTermsSupplementType(tradeState), MapperS.of(ContractualSupplementTypeEnum.STANDARD_LCDX_BULLET_TRANCHE), CardinalityOperator.Any)).and(notExists(obligations(tradeState).<Boolean>map("getNotContingent", _obligations -> _obligations.getNotContingent())).or(areEqual(obligations(tradeState).<Boolean>map("getNotContingent", _obligations -> _obligations.getNotContingent()), MapperS.of(true), CardinalityOperator.All))).get();
			
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
		
		@Override
		protected MapperC<? extends Obligations> obligations(TradeState tradeState) {
			return economicTerms(tradeState).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<ProtectionTerms>mapC("getProtectionTerms", creditDefaultPayout -> creditDefaultPayout.getProtectionTerms()).<Obligations>map("getObligations", protectionTerms -> protectionTerms.getObligations());
		}
	}
}
