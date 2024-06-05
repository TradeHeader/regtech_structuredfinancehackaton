package cdm.event.common;

import cdm.base.datetime.TimeZone;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.event.common.Trade;
import cdm.event.common.TradePricingReport;
import cdm.event.common.TradePricingReport.TradePricingReportBuilder;
import cdm.event.common.TradePricingReport.TradePricingReportBuilderImpl;
import cdm.event.common.TradePricingReport.TradePricingReportImpl;
import cdm.event.common.meta.TradePricingReportMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The attributes that are specific for consensus based pricing reporting.
 * @version ${project.version}
 */
@RosettaDataType(value="TradePricingReport", builder=TradePricingReport.TradePricingReportBuilderImpl.class, version="${project.version}")
public interface TradePricingReport extends RosettaModelObject {

	TradePricingReportMeta metaData = new TradePricingReportMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the cosensus based pricing parameters on a trade basis.
	 */
	Trade getTrade();
	/**
	 * The regional exchange close time for the underlying contract,including time zone, at which the trades should be priced. This provides an indication for which regional snapshot should be used for pricing primarily for Global markets where there are multiple regional close times.
	 */
	TimeZone getPricingTime();
	/**
	 * It specifies the interest payable on collateral delivered under a CSA covering the trade.
	 */
	FloatingRateIndexEnum getDiscountingIndex();

	/*********************** Build Methods  ***********************/
	TradePricingReport build();
	
	TradePricingReport.TradePricingReportBuilder toBuilder();
	
	static TradePricingReport.TradePricingReportBuilder builder() {
		return new TradePricingReport.TradePricingReportBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TradePricingReport> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TradePricingReport> getType() {
		return TradePricingReport.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("trade"), processor, Trade.class, getTrade());
		processRosetta(path.newSubPath("pricingTime"), processor, TimeZone.class, getPricingTime());
		processor.processBasic(path.newSubPath("discountingIndex"), FloatingRateIndexEnum.class, getDiscountingIndex(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TradePricingReportBuilder extends TradePricingReport, RosettaModelObjectBuilder {
		Trade.TradeBuilder getOrCreateTrade();
		Trade.TradeBuilder getTrade();
		TimeZone.TimeZoneBuilder getOrCreatePricingTime();
		TimeZone.TimeZoneBuilder getPricingTime();
		TradePricingReport.TradePricingReportBuilder setTrade(Trade trade);
		TradePricingReport.TradePricingReportBuilder setPricingTime(TimeZone pricingTime);
		TradePricingReport.TradePricingReportBuilder setDiscountingIndex(FloatingRateIndexEnum discountingIndex);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("trade"), processor, Trade.TradeBuilder.class, getTrade());
			processRosetta(path.newSubPath("pricingTime"), processor, TimeZone.TimeZoneBuilder.class, getPricingTime());
			processor.processBasic(path.newSubPath("discountingIndex"), FloatingRateIndexEnum.class, getDiscountingIndex(), this);
		}
		

		TradePricingReport.TradePricingReportBuilder prune();
	}

	/*********************** Immutable Implementation of TradePricingReport  ***********************/
	class TradePricingReportImpl implements TradePricingReport {
		private final Trade trade;
		private final TimeZone pricingTime;
		private final FloatingRateIndexEnum discountingIndex;
		
		protected TradePricingReportImpl(TradePricingReport.TradePricingReportBuilder builder) {
			this.trade = ofNullable(builder.getTrade()).map(f->f.build()).orElse(null);
			this.pricingTime = ofNullable(builder.getPricingTime()).map(f->f.build()).orElse(null);
			this.discountingIndex = builder.getDiscountingIndex();
		}
		
		@Override
		@RosettaAttribute("trade")
		public Trade getTrade() {
			return trade;
		}
		
		@Override
		@RosettaAttribute("pricingTime")
		public TimeZone getPricingTime() {
			return pricingTime;
		}
		
		@Override
		@RosettaAttribute("discountingIndex")
		public FloatingRateIndexEnum getDiscountingIndex() {
			return discountingIndex;
		}
		
		@Override
		public TradePricingReport build() {
			return this;
		}
		
		@Override
		public TradePricingReport.TradePricingReportBuilder toBuilder() {
			TradePricingReport.TradePricingReportBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TradePricingReport.TradePricingReportBuilder builder) {
			ofNullable(getTrade()).ifPresent(builder::setTrade);
			ofNullable(getPricingTime()).ifPresent(builder::setPricingTime);
			ofNullable(getDiscountingIndex()).ifPresent(builder::setDiscountingIndex);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradePricingReport _that = getType().cast(o);
		
			if (!Objects.equals(trade, _that.getTrade())) return false;
			if (!Objects.equals(pricingTime, _that.getPricingTime())) return false;
			if (!Objects.equals(discountingIndex, _that.getDiscountingIndex())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trade != null ? trade.hashCode() : 0);
			_result = 31 * _result + (pricingTime != null ? pricingTime.hashCode() : 0);
			_result = 31 * _result + (discountingIndex != null ? discountingIndex.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradePricingReport {" +
				"trade=" + this.trade + ", " +
				"pricingTime=" + this.pricingTime + ", " +
				"discountingIndex=" + this.discountingIndex +
			'}';
		}
	}

	/*********************** Builder Implementation of TradePricingReport  ***********************/
	class TradePricingReportBuilderImpl implements TradePricingReport.TradePricingReportBuilder {
	
		protected Trade.TradeBuilder trade;
		protected TimeZone.TimeZoneBuilder pricingTime;
		protected FloatingRateIndexEnum discountingIndex;
	
		public TradePricingReportBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("trade")
		public Trade.TradeBuilder getTrade() {
			return trade;
		}
		
		@Override
		public Trade.TradeBuilder getOrCreateTrade() {
			Trade.TradeBuilder result;
			if (trade!=null) {
				result = trade;
			}
			else {
				result = trade = Trade.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("pricingTime")
		public TimeZone.TimeZoneBuilder getPricingTime() {
			return pricingTime;
		}
		
		@Override
		public TimeZone.TimeZoneBuilder getOrCreatePricingTime() {
			TimeZone.TimeZoneBuilder result;
			if (pricingTime!=null) {
				result = pricingTime;
			}
			else {
				result = pricingTime = TimeZone.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("discountingIndex")
		public FloatingRateIndexEnum getDiscountingIndex() {
			return discountingIndex;
		}
		
	
		@Override
		@RosettaAttribute("trade")
		public TradePricingReport.TradePricingReportBuilder setTrade(Trade trade) {
			this.trade = trade==null?null:trade.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("pricingTime")
		public TradePricingReport.TradePricingReportBuilder setPricingTime(TimeZone pricingTime) {
			this.pricingTime = pricingTime==null?null:pricingTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("discountingIndex")
		public TradePricingReport.TradePricingReportBuilder setDiscountingIndex(FloatingRateIndexEnum discountingIndex) {
			this.discountingIndex = discountingIndex==null?null:discountingIndex;
			return this;
		}
		
		@Override
		public TradePricingReport build() {
			return new TradePricingReport.TradePricingReportImpl(this);
		}
		
		@Override
		public TradePricingReport.TradePricingReportBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradePricingReport.TradePricingReportBuilder prune() {
			if (trade!=null && !trade.prune().hasData()) trade = null;
			if (pricingTime!=null && !pricingTime.prune().hasData()) pricingTime = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTrade()!=null && getTrade().hasData()) return true;
			if (getPricingTime()!=null && getPricingTime().hasData()) return true;
			if (getDiscountingIndex()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradePricingReport.TradePricingReportBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TradePricingReport.TradePricingReportBuilder o = (TradePricingReport.TradePricingReportBuilder) other;
			
			merger.mergeRosetta(getTrade(), o.getTrade(), this::setTrade);
			merger.mergeRosetta(getPricingTime(), o.getPricingTime(), this::setPricingTime);
			
			merger.mergeBasic(getDiscountingIndex(), o.getDiscountingIndex(), this::setDiscountingIndex);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradePricingReport _that = getType().cast(o);
		
			if (!Objects.equals(trade, _that.getTrade())) return false;
			if (!Objects.equals(pricingTime, _that.getPricingTime())) return false;
			if (!Objects.equals(discountingIndex, _that.getDiscountingIndex())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trade != null ? trade.hashCode() : 0);
			_result = 31 * _result + (pricingTime != null ? pricingTime.hashCode() : 0);
			_result = 31 * _result + (discountingIndex != null ? discountingIndex.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradePricingReportBuilder {" +
				"trade=" + this.trade + ", " +
				"pricingTime=" + this.pricingTime + ", " +
				"discountingIndex=" + this.discountingIndex +
			'}';
		}
	}
}
