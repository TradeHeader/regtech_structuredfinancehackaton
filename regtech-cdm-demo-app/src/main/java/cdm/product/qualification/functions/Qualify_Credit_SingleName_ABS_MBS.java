package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.MortgageSectorEnum;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
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
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.ReferenceObligation;
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

@ImplementedBy(Qualify_Credit_SingleName_ABS_MBS.Qualify_Credit_SingleName_ABS_MBSDefault.class)
public abstract class Qualify_Credit_SingleName_ABS_MBS implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_CreditDefaultSwap_SingleName qualify_CreditDefaultSwap_SingleName;

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

	protected abstract MapperC<? extends Security> security(TradeState tradeState);

	protected abstract MapperC<ContractualSupplementTypeEnum> contractualTermsSupplementType(TradeState tradeState);

	public static class Qualify_Credit_SingleName_ABS_MBSDefault extends Qualify_Credit_SingleName_ABS_MBS {
		@Override
		protected Boolean doEvaluate(TradeState tradeState) {
			Boolean is_product = null;
			return assignOutput(is_product, tradeState);
		}
		
		protected Boolean assignOutput(Boolean is_product, TradeState tradeState) {
			is_product = ComparisonResult.of(MapperS.of(qualify_CreditDefaultSwap_SingleName.evaluate(economicTerms(tradeState).get()))).and(areEqual(security(tradeState).<SecurityTypeEnum>map("getSecurityType", _security -> _security.getSecurityType()), MapperS.of(SecurityTypeEnum.MORTGAGE), CardinalityOperator.Any)).and(areEqual(security(tradeState).<MortgageSectorEnum>map("getMortgageSector", _security -> _security.getMortgageSector()), MapperS.of(MortgageSectorEnum.CMBS), CardinalityOperator.Any).or(areEqual(security(tradeState).<MortgageSectorEnum>map("getMortgageSector", _security -> _security.getMortgageSector()), MapperS.of(MortgageSectorEnum.RMBS), CardinalityOperator.Any))).and(areEqual(contractualTermsSupplementType(tradeState), MapperS.of(ContractualSupplementTypeEnum.CD_SON_MBS), CardinalityOperator.Any)).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> economicTerms(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms());
		}
		
		@Override
		protected MapperC<? extends Security> security(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<ReferenceInformation>map("getReferenceInformation", generalTerms -> generalTerms.getReferenceInformation()).<ReferenceObligation>mapC("getReferenceObligation", referenceInformation -> referenceInformation.getReferenceObligation()).<Security>map("getSecurity", referenceObligation -> referenceObligation.getSecurity());
		}
		
		@Override
		protected MapperC<ContractualSupplementTypeEnum> contractualTermsSupplementType(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ContractDetails>map("getContractDetails", trade -> trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<ContractualTermsSupplement>mapC("getContractualTermsSupplement", agreementName -> agreementName.getContractualTermsSupplement()).<FieldWithMetaContractualSupplementTypeEnum>map("getContractualTermsSupplementType", contractualTermsSupplement -> contractualTermsSupplement.getContractualTermsSupplementType()).<ContractualSupplementTypeEnum>map("getValue", _f->_f.getValue());
		}
	}
}
