package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.FundProductTypeEnum;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.product.template.Basket;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_UnderlierProduct_Equity.Qualify_UnderlierProduct_EquityDefault.class)
public abstract class Qualify_UnderlierProduct_Equity implements RosettaFunction {

	/**
	* @param underlier 
	* @return is_product 
	*/
	public Boolean evaluate(Product underlier) {
		Boolean is_product = doEvaluate(underlier);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(Product underlier);

	public static class Qualify_UnderlierProduct_EquityDefault extends Qualify_UnderlierProduct_Equity {
		@Override
		protected Boolean doEvaluate(Product underlier) {
			Boolean is_product = null;
			return assignOutput(is_product, underlier);
		}
		
		protected Boolean assignOutput(Boolean is_product, Product underlier) {
			is_product = areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.EQUITY), CardinalityOperator.All).or(areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.FUND), CardinalityOperator.All).and(areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<FundProductTypeEnum>map("getFundType", security -> security.getFundType()), MapperS.of(FundProductTypeEnum.EXCHANGE_TRADED_FUND), CardinalityOperator.All))).or(areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.FUND), CardinalityOperator.All).and(areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<FundProductTypeEnum>map("getFundType", security -> security.getFundType()), MapperS.of(FundProductTypeEnum.MUTUAL_FUND), CardinalityOperator.All))).or(areEqual(MapperS.of(underlier).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.WARRANT), CardinalityOperator.All)).or(exists(MapperS.of(underlier).<Index>map("getIndex", product -> product.getIndex()))).or(exists(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket())).and(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.EQUITY), CardinalityOperator.Any).or(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.FUND), CardinalityOperator.Any).and(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<FundProductTypeEnum>map("getFundType", security -> security.getFundType()), MapperS.of(FundProductTypeEnum.EXCHANGE_TRADED_FUND), CardinalityOperator.Any))).or(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.FUND), CardinalityOperator.Any).and(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<FundProductTypeEnum>map("getFundType", security -> security.getFundType()), MapperS.of(FundProductTypeEnum.MUTUAL_FUND), CardinalityOperator.Any))).or(areEqual(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.WARRANT), CardinalityOperator.Any)).or(exists(MapperS.of(underlier).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Index>map("getIndex", product -> product.getIndex()))))).get();
			
			return is_product;
		}
	}
}
