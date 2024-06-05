package cdm.event.common.functions;

import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ExtractTradeCollateralPrice.ExtractTradeCollateralPriceDefault.class)
public abstract class ExtractTradeCollateralPrice implements RosettaFunction {

	/**
	* @param tradableProduct 
	* @return value 
	*/
	public List<BigDecimal> evaluate(TradableProduct tradableProduct) {
		List<BigDecimal> value = doEvaluate(tradableProduct);
		
		return value;
	}

	protected abstract List<BigDecimal> doEvaluate(TradableProduct tradableProduct);

	public static class ExtractTradeCollateralPriceDefault extends ExtractTradeCollateralPrice {
		@Override
		protected List<BigDecimal> doEvaluate(TradableProduct tradableProduct) {
			List<BigDecimal> value = new ArrayList<>();
			return assignOutput(value, tradableProduct);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> value, TradableProduct tradableProduct) {
			final MapperListOfLists<PriceSchedule> thenResult0 = MapperS.of(MapperS.of(tradableProduct).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
				.mapItemToList(item -> item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()));
			final MapperC<PriceSchedule> thenResult1 = thenResult0
				.flattenList();
			final MapperC<PriceSchedule> thenResult2 = thenResult1
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.ASSET_PRICE), CardinalityOperator.All).get());
			value = thenResult2
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).getMulti();
			
			return value;
		}
	}
}
