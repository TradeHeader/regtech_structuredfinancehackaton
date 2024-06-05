package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_AssetClass_Credit.Qualify_AssetClass_CreditDefault.class)
public abstract class Qualify_AssetClass_Credit implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.product.qualification.functions.Qualify_AssetClass_Credit qualify_AssetClass_Credit;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	protected abstract MapperS<? extends Product> optionUnderlier(EconomicTerms economicTerms);

	protected abstract MapperS<? extends Product> forwardUnderlier(EconomicTerms economicTerms);

	protected abstract MapperS<? extends Product> performanceUnderlier(EconomicTerms economicTerms);

	public static class Qualify_AssetClass_CreditDefault extends Qualify_AssetClass_Credit {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final ComparisonResult ifThenElseResult0;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult0 = areEqual(optionUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.CREDIT), CardinalityOperator.Any).or(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.CREDIT), CardinalityOperator.Any)).or(ComparisonResult.of(MapperS.of(qualify_AssetClass_Credit.evaluate(optionUnderlier(economicTerms).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).get())))).or(ComparisonResult.of(MapperS.of(qualify_AssetClass_Credit.evaluate(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<EconomicTerms>map("getEconomicTerms", security -> security.getEconomicTerms()).get()))));
			} else {
				ifThenElseResult0 = ComparisonResult.of(MapperS.of(false));
			}
			final ComparisonResult ifThenElseResult1;
			if (exists(forwardUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult1 = areEqual(forwardUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.CREDIT), CardinalityOperator.Any).or(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.CREDIT), CardinalityOperator.Any));
			} else {
				ifThenElseResult1 = ComparisonResult.of(MapperS.of(false));
			}
			final ComparisonResult ifThenElseResult2;
			if (exists(performanceUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult2 = exists(performanceUnderlier(economicTerms).<Loan>map("getLoan", product -> product.getLoan())).or(areEqual(performanceUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.DEBT), CardinalityOperator.All));
			} else {
				ifThenElseResult2 = ComparisonResult.successEmptyOperand("");
			}
			is_product = onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout())))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()))).and(ifThenElseResult0)).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()))).and(ifThenElseResult1)).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()))).and(ifThenElseResult2)).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends Product> optionUnderlier(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).get()).<Product>map("getUnderlier", optionPayout -> optionPayout.getUnderlier());
		}
		
		@Override
		protected MapperS<? extends Product> forwardUnderlier(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).get()).<Product>map("getUnderlier", forwardPayout -> forwardPayout.getUnderlier());
		}
		
		@Override
		protected MapperS<? extends Product> performanceUnderlier(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).get()).<Product>map("getUnderlier", performancePayout -> performancePayout.getUnderlier());
		}
	}
}
