package cdm.observable.asset;

import cdm.observable.asset.QuotationStyleEnum;
import cdm.observable.asset.TransactedPrice;
import cdm.observable.asset.TransactedPrice.TransactedPriceBuilder;
import cdm.observable.asset.TransactedPrice.TransactedPriceBuilderImpl;
import cdm.observable.asset.TransactedPrice.TransactedPriceImpl;
import cdm.observable.asset.meta.TransactedPriceMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  A class to represent the transacted price attributes that are positioned as part of the FpML FeeLeg.
 * @version ${project.version}
 */
@RosettaDataType(value="TransactedPrice", builder=TransactedPrice.TransactedPriceBuilderImpl.class, version="${project.version}")
public interface TransactedPrice extends RosettaModelObject {

	TransactedPriceMeta metaData = new TransactedPriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An optional element that only has meaning in a credit index trade. This element contains the credit spread (&#39;fair value&#39;) at which the trade was executed. Unlike the fixedRate of an index, the marketFixedRate varies over the life of the index depending on market conditions. The marketFixedRate is the price of the index as quoted by trading desks.
	 */
	BigDecimal getMarketFixedRate();
	/**
	 * An optional element that contains the up-front points expressed as a percentage of the notional. An initialPoints value of 5% would be represented as 0.05. The initialPoints element is an alternative to marketFixedRate in quoting the traded level of a trade. When initialPoints is used, the traded level is the sum of fixedRate and initialPoints. The initialPoints is one of the items that are factored into the initialPayment calculation and is payable by the Buyer to the Seller. Note that initialPoints and marketFixedRate may both be present in the same document when both implied values are desired.
	 */
	BigDecimal getInitialPoints();
	/**
	 * An optional element that only has meaning in a credit index trade. This element contains the price at which the trade was executed and is used instead of marketFixedRate on credit trades on certain indicies which are quoted using a price rather than a spread.
	 */
	BigDecimal getMarketPrice();
	/**
	 * An optional element that contains the up-front points expressed as a percentage of the notional. An initialPoints value of 5% would be represented as 0.05. The initialPoints element is an alternative to marketFixedRate in quoting the traded level of a trade. When initialPoints is used, the traded level is the sum of fixedRate and initialPoints. The initialPoints is one of the items that are factored into the initialPayment calculation and is payable by the Buyer to the Seller. Note that initialPoints and marketFixedRate may both be present in the same document when both implied values are desired.
	 */
	QuotationStyleEnum getQuotationStyle();

	/*********************** Build Methods  ***********************/
	TransactedPrice build();
	
	TransactedPrice.TransactedPriceBuilder toBuilder();
	
	static TransactedPrice.TransactedPriceBuilder builder() {
		return new TransactedPrice.TransactedPriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransactedPrice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransactedPrice> getType() {
		return TransactedPrice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("marketFixedRate"), BigDecimal.class, getMarketFixedRate(), this);
		processor.processBasic(path.newSubPath("initialPoints"), BigDecimal.class, getInitialPoints(), this);
		processor.processBasic(path.newSubPath("marketPrice"), BigDecimal.class, getMarketPrice(), this);
		processor.processBasic(path.newSubPath("quotationStyle"), QuotationStyleEnum.class, getQuotationStyle(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransactedPriceBuilder extends TransactedPrice, RosettaModelObjectBuilder {
		TransactedPrice.TransactedPriceBuilder setMarketFixedRate(BigDecimal marketFixedRate);
		TransactedPrice.TransactedPriceBuilder setInitialPoints(BigDecimal initialPoints);
		TransactedPrice.TransactedPriceBuilder setMarketPrice(BigDecimal marketPrice);
		TransactedPrice.TransactedPriceBuilder setQuotationStyle(QuotationStyleEnum quotationStyle);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("marketFixedRate"), BigDecimal.class, getMarketFixedRate(), this);
			processor.processBasic(path.newSubPath("initialPoints"), BigDecimal.class, getInitialPoints(), this);
			processor.processBasic(path.newSubPath("marketPrice"), BigDecimal.class, getMarketPrice(), this);
			processor.processBasic(path.newSubPath("quotationStyle"), QuotationStyleEnum.class, getQuotationStyle(), this);
		}
		

		TransactedPrice.TransactedPriceBuilder prune();
	}

	/*********************** Immutable Implementation of TransactedPrice  ***********************/
	class TransactedPriceImpl implements TransactedPrice {
		private final BigDecimal marketFixedRate;
		private final BigDecimal initialPoints;
		private final BigDecimal marketPrice;
		private final QuotationStyleEnum quotationStyle;
		
		protected TransactedPriceImpl(TransactedPrice.TransactedPriceBuilder builder) {
			this.marketFixedRate = builder.getMarketFixedRate();
			this.initialPoints = builder.getInitialPoints();
			this.marketPrice = builder.getMarketPrice();
			this.quotationStyle = builder.getQuotationStyle();
		}
		
		@Override
		@RosettaAttribute("marketFixedRate")
		public BigDecimal getMarketFixedRate() {
			return marketFixedRate;
		}
		
		@Override
		@RosettaAttribute("initialPoints")
		public BigDecimal getInitialPoints() {
			return initialPoints;
		}
		
		@Override
		@RosettaAttribute("marketPrice")
		public BigDecimal getMarketPrice() {
			return marketPrice;
		}
		
		@Override
		@RosettaAttribute("quotationStyle")
		public QuotationStyleEnum getQuotationStyle() {
			return quotationStyle;
		}
		
		@Override
		public TransactedPrice build() {
			return this;
		}
		
		@Override
		public TransactedPrice.TransactedPriceBuilder toBuilder() {
			TransactedPrice.TransactedPriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransactedPrice.TransactedPriceBuilder builder) {
			ofNullable(getMarketFixedRate()).ifPresent(builder::setMarketFixedRate);
			ofNullable(getInitialPoints()).ifPresent(builder::setInitialPoints);
			ofNullable(getMarketPrice()).ifPresent(builder::setMarketPrice);
			ofNullable(getQuotationStyle()).ifPresent(builder::setQuotationStyle);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransactedPrice _that = getType().cast(o);
		
			if (!Objects.equals(marketFixedRate, _that.getMarketFixedRate())) return false;
			if (!Objects.equals(initialPoints, _that.getInitialPoints())) return false;
			if (!Objects.equals(marketPrice, _that.getMarketPrice())) return false;
			if (!Objects.equals(quotationStyle, _that.getQuotationStyle())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marketFixedRate != null ? marketFixedRate.hashCode() : 0);
			_result = 31 * _result + (initialPoints != null ? initialPoints.hashCode() : 0);
			_result = 31 * _result + (marketPrice != null ? marketPrice.hashCode() : 0);
			_result = 31 * _result + (quotationStyle != null ? quotationStyle.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransactedPrice {" +
				"marketFixedRate=" + this.marketFixedRate + ", " +
				"initialPoints=" + this.initialPoints + ", " +
				"marketPrice=" + this.marketPrice + ", " +
				"quotationStyle=" + this.quotationStyle +
			'}';
		}
	}

	/*********************** Builder Implementation of TransactedPrice  ***********************/
	class TransactedPriceBuilderImpl implements TransactedPrice.TransactedPriceBuilder {
	
		protected BigDecimal marketFixedRate;
		protected BigDecimal initialPoints;
		protected BigDecimal marketPrice;
		protected QuotationStyleEnum quotationStyle;
	
		public TransactedPriceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("marketFixedRate")
		public BigDecimal getMarketFixedRate() {
			return marketFixedRate;
		}
		
		@Override
		@RosettaAttribute("initialPoints")
		public BigDecimal getInitialPoints() {
			return initialPoints;
		}
		
		@Override
		@RosettaAttribute("marketPrice")
		public BigDecimal getMarketPrice() {
			return marketPrice;
		}
		
		@Override
		@RosettaAttribute("quotationStyle")
		public QuotationStyleEnum getQuotationStyle() {
			return quotationStyle;
		}
		
	
		@Override
		@RosettaAttribute("marketFixedRate")
		public TransactedPrice.TransactedPriceBuilder setMarketFixedRate(BigDecimal marketFixedRate) {
			this.marketFixedRate = marketFixedRate==null?null:marketFixedRate;
			return this;
		}
		@Override
		@RosettaAttribute("initialPoints")
		public TransactedPrice.TransactedPriceBuilder setInitialPoints(BigDecimal initialPoints) {
			this.initialPoints = initialPoints==null?null:initialPoints;
			return this;
		}
		@Override
		@RosettaAttribute("marketPrice")
		public TransactedPrice.TransactedPriceBuilder setMarketPrice(BigDecimal marketPrice) {
			this.marketPrice = marketPrice==null?null:marketPrice;
			return this;
		}
		@Override
		@RosettaAttribute("quotationStyle")
		public TransactedPrice.TransactedPriceBuilder setQuotationStyle(QuotationStyleEnum quotationStyle) {
			this.quotationStyle = quotationStyle==null?null:quotationStyle;
			return this;
		}
		
		@Override
		public TransactedPrice build() {
			return new TransactedPrice.TransactedPriceImpl(this);
		}
		
		@Override
		public TransactedPrice.TransactedPriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransactedPrice.TransactedPriceBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMarketFixedRate()!=null) return true;
			if (getInitialPoints()!=null) return true;
			if (getMarketPrice()!=null) return true;
			if (getQuotationStyle()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransactedPrice.TransactedPriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransactedPrice.TransactedPriceBuilder o = (TransactedPrice.TransactedPriceBuilder) other;
			
			
			merger.mergeBasic(getMarketFixedRate(), o.getMarketFixedRate(), this::setMarketFixedRate);
			merger.mergeBasic(getInitialPoints(), o.getInitialPoints(), this::setInitialPoints);
			merger.mergeBasic(getMarketPrice(), o.getMarketPrice(), this::setMarketPrice);
			merger.mergeBasic(getQuotationStyle(), o.getQuotationStyle(), this::setQuotationStyle);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransactedPrice _that = getType().cast(o);
		
			if (!Objects.equals(marketFixedRate, _that.getMarketFixedRate())) return false;
			if (!Objects.equals(initialPoints, _that.getInitialPoints())) return false;
			if (!Objects.equals(marketPrice, _that.getMarketPrice())) return false;
			if (!Objects.equals(quotationStyle, _that.getQuotationStyle())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marketFixedRate != null ? marketFixedRate.hashCode() : 0);
			_result = 31 * _result + (initialPoints != null ? initialPoints.hashCode() : 0);
			_result = 31 * _result + (marketPrice != null ? marketPrice.hashCode() : 0);
			_result = 31 * _result + (quotationStyle != null ? quotationStyle.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransactedPriceBuilder {" +
				"marketFixedRate=" + this.marketFixedRate + ", " +
				"initialPoints=" + this.initialPoints + ", " +
				"marketPrice=" + this.marketPrice + ", " +
				"quotationStyle=" + this.quotationStyle +
			'}';
		}
	}
}
