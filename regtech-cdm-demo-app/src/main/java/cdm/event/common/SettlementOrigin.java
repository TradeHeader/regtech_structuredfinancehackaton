package cdm.event.common;

import cdm.event.common.SettlementOrigin;
import cdm.event.common.SettlementOrigin.SettlementOriginBuilder;
import cdm.event.common.SettlementOrigin.SettlementOriginBuilderImpl;
import cdm.event.common.SettlementOrigin.SettlementOriginImpl;
import cdm.event.common.meta.SettlementOriginMeta;
import cdm.product.asset.CommodityPayout;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.metafields.ReferenceWithMetaCommodityPayout;
import cdm.product.asset.metafields.ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder;
import cdm.product.asset.metafields.ReferenceWithMetaCreditDefaultPayout;
import cdm.product.asset.metafields.ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.metafields.ReferenceWithMetaSettlementTerms;
import cdm.product.common.settlement.metafields.ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder;
import cdm.product.template.AssetPayout;
import cdm.product.template.FixedPricePayout;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.metafields.ReferenceWithMetaAssetPayout;
import cdm.product.template.metafields.ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder;
import cdm.product.template.metafields.ReferenceWithMetaFixedPricePayout;
import cdm.product.template.metafields.ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder;
import cdm.product.template.metafields.ReferenceWithMetaForwardPayout;
import cdm.product.template.metafields.ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder;
import cdm.product.template.metafields.ReferenceWithMetaPerformancePayout;
import cdm.product.template.metafields.ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder;
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
 * Defines the origin to the transfer as a reference for lineage purposes, whether it originated from trade level settlement terms or from payment terms on an economic payout.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementOrigin", builder=SettlementOrigin.SettlementOriginBuilderImpl.class, version="${project.version}")
public interface SettlementOrigin extends RosettaModelObject {

	SettlementOriginMeta metaData = new SettlementOriginMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a reference to an Commodity Payout.
	 */
	ReferenceWithMetaCommodityPayout getCommodityPayout();
	/**
	 * Represents a reference to a Credit Default Payout.
	 */
	ReferenceWithMetaCreditDefaultPayout getCreditDefaultPayout();
	/**
	 * Represents a reference to a Forward Payout.
	 */
	ReferenceWithMetaForwardPayout getForwardPayout();
	/**
	 * Represents a reference to an Interest Rate Payout.
	 */
	ReferenceWithMetaInterestRatePayout getInterestRatePayout();
	/**
	 * Represents a reference to an Option Payout.
	 */
	ReferenceWithMetaOptionPayout getOptionPayout();
	/**
	 * Represents a reference to an Asset Payout.
	 */
	ReferenceWithMetaAssetPayout getAssetPayout();
	/**
	 * Represents a reference to settlement terms, which may have been specified at execution.
	 */
	ReferenceWithMetaSettlementTerms getSettlementTerms();
	/**
	 * Represents a reference to a Performance Payout.
	 */
	ReferenceWithMetaPerformancePayout getPerformancePayout();
	/**
	 * Represents a reference to a Fixed Price Payout
	 */
	ReferenceWithMetaFixedPricePayout getFixedPricePayout();

	/*********************** Build Methods  ***********************/
	SettlementOrigin build();
	
	SettlementOrigin.SettlementOriginBuilder toBuilder();
	
	static SettlementOrigin.SettlementOriginBuilder builder() {
		return new SettlementOrigin.SettlementOriginBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementOrigin> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementOrigin> getType() {
		return SettlementOrigin.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("commodityPayout"), processor, ReferenceWithMetaCommodityPayout.class, getCommodityPayout());
		processRosetta(path.newSubPath("creditDefaultPayout"), processor, ReferenceWithMetaCreditDefaultPayout.class, getCreditDefaultPayout());
		processRosetta(path.newSubPath("forwardPayout"), processor, ReferenceWithMetaForwardPayout.class, getForwardPayout());
		processRosetta(path.newSubPath("interestRatePayout"), processor, ReferenceWithMetaInterestRatePayout.class, getInterestRatePayout());
		processRosetta(path.newSubPath("optionPayout"), processor, ReferenceWithMetaOptionPayout.class, getOptionPayout());
		processRosetta(path.newSubPath("assetPayout"), processor, ReferenceWithMetaAssetPayout.class, getAssetPayout());
		processRosetta(path.newSubPath("settlementTerms"), processor, ReferenceWithMetaSettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("performancePayout"), processor, ReferenceWithMetaPerformancePayout.class, getPerformancePayout());
		processRosetta(path.newSubPath("fixedPricePayout"), processor, ReferenceWithMetaFixedPricePayout.class, getFixedPricePayout());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementOriginBuilder extends SettlementOrigin, RosettaModelObjectBuilder {
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder getOrCreateCommodityPayout();
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder getCommodityPayout();
		ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder getOrCreateCreditDefaultPayout();
		ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder getCreditDefaultPayout();
		ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder getOrCreateForwardPayout();
		ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder getForwardPayout();
		ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder getOrCreateInterestRatePayout();
		ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder getInterestRatePayout();
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder getOrCreateOptionPayout();
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder getOptionPayout();
		ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder getOrCreateAssetPayout();
		ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder getAssetPayout();
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder getOrCreateSettlementTerms();
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder getSettlementTerms();
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder getOrCreatePerformancePayout();
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder getPerformancePayout();
		ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder getOrCreateFixedPricePayout();
		ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder getFixedPricePayout();
		SettlementOrigin.SettlementOriginBuilder setCommodityPayout(ReferenceWithMetaCommodityPayout commodityPayout0);
		SettlementOrigin.SettlementOriginBuilder setCommodityPayoutValue(CommodityPayout commodityPayout1);
		SettlementOrigin.SettlementOriginBuilder setCreditDefaultPayout(ReferenceWithMetaCreditDefaultPayout creditDefaultPayout0);
		SettlementOrigin.SettlementOriginBuilder setCreditDefaultPayoutValue(CreditDefaultPayout creditDefaultPayout1);
		SettlementOrigin.SettlementOriginBuilder setForwardPayout(ReferenceWithMetaForwardPayout forwardPayout0);
		SettlementOrigin.SettlementOriginBuilder setForwardPayoutValue(ForwardPayout forwardPayout1);
		SettlementOrigin.SettlementOriginBuilder setInterestRatePayout(ReferenceWithMetaInterestRatePayout interestRatePayout0);
		SettlementOrigin.SettlementOriginBuilder setInterestRatePayoutValue(InterestRatePayout interestRatePayout1);
		SettlementOrigin.SettlementOriginBuilder setOptionPayout(ReferenceWithMetaOptionPayout optionPayout0);
		SettlementOrigin.SettlementOriginBuilder setOptionPayoutValue(OptionPayout optionPayout1);
		SettlementOrigin.SettlementOriginBuilder setAssetPayout(ReferenceWithMetaAssetPayout assetPayout0);
		SettlementOrigin.SettlementOriginBuilder setAssetPayoutValue(AssetPayout assetPayout1);
		SettlementOrigin.SettlementOriginBuilder setSettlementTerms(ReferenceWithMetaSettlementTerms settlementTerms0);
		SettlementOrigin.SettlementOriginBuilder setSettlementTermsValue(SettlementTerms settlementTerms1);
		SettlementOrigin.SettlementOriginBuilder setPerformancePayout(ReferenceWithMetaPerformancePayout performancePayout0);
		SettlementOrigin.SettlementOriginBuilder setPerformancePayoutValue(PerformancePayout performancePayout1);
		SettlementOrigin.SettlementOriginBuilder setFixedPricePayout(ReferenceWithMetaFixedPricePayout fixedPricePayout0);
		SettlementOrigin.SettlementOriginBuilder setFixedPricePayoutValue(FixedPricePayout fixedPricePayout1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("commodityPayout"), processor, ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder.class, getCommodityPayout());
			processRosetta(path.newSubPath("creditDefaultPayout"), processor, ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder.class, getCreditDefaultPayout());
			processRosetta(path.newSubPath("forwardPayout"), processor, ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder.class, getForwardPayout());
			processRosetta(path.newSubPath("interestRatePayout"), processor, ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder.class, getInterestRatePayout());
			processRosetta(path.newSubPath("optionPayout"), processor, ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder.class, getOptionPayout());
			processRosetta(path.newSubPath("assetPayout"), processor, ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder.class, getAssetPayout());
			processRosetta(path.newSubPath("settlementTerms"), processor, ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("performancePayout"), processor, ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder.class, getPerformancePayout());
			processRosetta(path.newSubPath("fixedPricePayout"), processor, ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder.class, getFixedPricePayout());
		}
		

		SettlementOrigin.SettlementOriginBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementOrigin  ***********************/
	class SettlementOriginImpl implements SettlementOrigin {
		private final ReferenceWithMetaCommodityPayout commodityPayout;
		private final ReferenceWithMetaCreditDefaultPayout creditDefaultPayout;
		private final ReferenceWithMetaForwardPayout forwardPayout;
		private final ReferenceWithMetaInterestRatePayout interestRatePayout;
		private final ReferenceWithMetaOptionPayout optionPayout;
		private final ReferenceWithMetaAssetPayout assetPayout;
		private final ReferenceWithMetaSettlementTerms settlementTerms;
		private final ReferenceWithMetaPerformancePayout performancePayout;
		private final ReferenceWithMetaFixedPricePayout fixedPricePayout;
		
		protected SettlementOriginImpl(SettlementOrigin.SettlementOriginBuilder builder) {
			this.commodityPayout = ofNullable(builder.getCommodityPayout()).map(f->f.build()).orElse(null);
			this.creditDefaultPayout = ofNullable(builder.getCreditDefaultPayout()).map(f->f.build()).orElse(null);
			this.forwardPayout = ofNullable(builder.getForwardPayout()).map(f->f.build()).orElse(null);
			this.interestRatePayout = ofNullable(builder.getInterestRatePayout()).map(f->f.build()).orElse(null);
			this.optionPayout = ofNullable(builder.getOptionPayout()).map(f->f.build()).orElse(null);
			this.assetPayout = ofNullable(builder.getAssetPayout()).map(f->f.build()).orElse(null);
			this.settlementTerms = ofNullable(builder.getSettlementTerms()).map(f->f.build()).orElse(null);
			this.performancePayout = ofNullable(builder.getPerformancePayout()).map(f->f.build()).orElse(null);
			this.fixedPricePayout = ofNullable(builder.getFixedPricePayout()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("commodityPayout")
		public ReferenceWithMetaCommodityPayout getCommodityPayout() {
			return commodityPayout;
		}
		
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public ReferenceWithMetaCreditDefaultPayout getCreditDefaultPayout() {
			return creditDefaultPayout;
		}
		
		@Override
		@RosettaAttribute("forwardPayout")
		public ReferenceWithMetaForwardPayout getForwardPayout() {
			return forwardPayout;
		}
		
		@Override
		@RosettaAttribute("interestRatePayout")
		public ReferenceWithMetaInterestRatePayout getInterestRatePayout() {
			return interestRatePayout;
		}
		
		@Override
		@RosettaAttribute("optionPayout")
		public ReferenceWithMetaOptionPayout getOptionPayout() {
			return optionPayout;
		}
		
		@Override
		@RosettaAttribute("assetPayout")
		public ReferenceWithMetaAssetPayout getAssetPayout() {
			return assetPayout;
		}
		
		@Override
		@RosettaAttribute("settlementTerms")
		public ReferenceWithMetaSettlementTerms getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		@RosettaAttribute("performancePayout")
		public ReferenceWithMetaPerformancePayout getPerformancePayout() {
			return performancePayout;
		}
		
		@Override
		@RosettaAttribute("fixedPricePayout")
		public ReferenceWithMetaFixedPricePayout getFixedPricePayout() {
			return fixedPricePayout;
		}
		
		@Override
		public SettlementOrigin build() {
			return this;
		}
		
		@Override
		public SettlementOrigin.SettlementOriginBuilder toBuilder() {
			SettlementOrigin.SettlementOriginBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementOrigin.SettlementOriginBuilder builder) {
			ofNullable(getCommodityPayout()).ifPresent(builder::setCommodityPayout);
			ofNullable(getCreditDefaultPayout()).ifPresent(builder::setCreditDefaultPayout);
			ofNullable(getForwardPayout()).ifPresent(builder::setForwardPayout);
			ofNullable(getInterestRatePayout()).ifPresent(builder::setInterestRatePayout);
			ofNullable(getOptionPayout()).ifPresent(builder::setOptionPayout);
			ofNullable(getAssetPayout()).ifPresent(builder::setAssetPayout);
			ofNullable(getSettlementTerms()).ifPresent(builder::setSettlementTerms);
			ofNullable(getPerformancePayout()).ifPresent(builder::setPerformancePayout);
			ofNullable(getFixedPricePayout()).ifPresent(builder::setFixedPricePayout);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementOrigin _that = getType().cast(o);
		
			if (!Objects.equals(commodityPayout, _that.getCommodityPayout())) return false;
			if (!Objects.equals(creditDefaultPayout, _that.getCreditDefaultPayout())) return false;
			if (!Objects.equals(forwardPayout, _that.getForwardPayout())) return false;
			if (!Objects.equals(interestRatePayout, _that.getInterestRatePayout())) return false;
			if (!Objects.equals(optionPayout, _that.getOptionPayout())) return false;
			if (!Objects.equals(assetPayout, _that.getAssetPayout())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			if (!Objects.equals(performancePayout, _that.getPerformancePayout())) return false;
			if (!Objects.equals(fixedPricePayout, _that.getFixedPricePayout())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (commodityPayout != null ? commodityPayout.hashCode() : 0);
			_result = 31 * _result + (creditDefaultPayout != null ? creditDefaultPayout.hashCode() : 0);
			_result = 31 * _result + (forwardPayout != null ? forwardPayout.hashCode() : 0);
			_result = 31 * _result + (interestRatePayout != null ? interestRatePayout.hashCode() : 0);
			_result = 31 * _result + (optionPayout != null ? optionPayout.hashCode() : 0);
			_result = 31 * _result + (assetPayout != null ? assetPayout.hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			_result = 31 * _result + (performancePayout != null ? performancePayout.hashCode() : 0);
			_result = 31 * _result + (fixedPricePayout != null ? fixedPricePayout.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementOrigin {" +
				"commodityPayout=" + this.commodityPayout + ", " +
				"creditDefaultPayout=" + this.creditDefaultPayout + ", " +
				"forwardPayout=" + this.forwardPayout + ", " +
				"interestRatePayout=" + this.interestRatePayout + ", " +
				"optionPayout=" + this.optionPayout + ", " +
				"assetPayout=" + this.assetPayout + ", " +
				"settlementTerms=" + this.settlementTerms + ", " +
				"performancePayout=" + this.performancePayout + ", " +
				"fixedPricePayout=" + this.fixedPricePayout +
			'}';
		}
	}

	/*********************** Builder Implementation of SettlementOrigin  ***********************/
	class SettlementOriginBuilderImpl implements SettlementOrigin.SettlementOriginBuilder {
	
		protected ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder commodityPayout;
		protected ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder creditDefaultPayout;
		protected ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder forwardPayout;
		protected ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder interestRatePayout;
		protected ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder optionPayout;
		protected ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder assetPayout;
		protected ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder settlementTerms;
		protected ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder performancePayout;
		protected ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder fixedPricePayout;
	
		public SettlementOriginBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("commodityPayout")
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder getCommodityPayout() {
			return commodityPayout;
		}
		
		@Override
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder getOrCreateCommodityPayout() {
			ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder result;
			if (commodityPayout!=null) {
				result = commodityPayout;
			}
			else {
				result = commodityPayout = ReferenceWithMetaCommodityPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder getCreditDefaultPayout() {
			return creditDefaultPayout;
		}
		
		@Override
		public ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder getOrCreateCreditDefaultPayout() {
			ReferenceWithMetaCreditDefaultPayout.ReferenceWithMetaCreditDefaultPayoutBuilder result;
			if (creditDefaultPayout!=null) {
				result = creditDefaultPayout;
			}
			else {
				result = creditDefaultPayout = ReferenceWithMetaCreditDefaultPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("forwardPayout")
		public ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder getForwardPayout() {
			return forwardPayout;
		}
		
		@Override
		public ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder getOrCreateForwardPayout() {
			ReferenceWithMetaForwardPayout.ReferenceWithMetaForwardPayoutBuilder result;
			if (forwardPayout!=null) {
				result = forwardPayout;
			}
			else {
				result = forwardPayout = ReferenceWithMetaForwardPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interestRatePayout")
		public ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder getInterestRatePayout() {
			return interestRatePayout;
		}
		
		@Override
		public ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder getOrCreateInterestRatePayout() {
			ReferenceWithMetaInterestRatePayout.ReferenceWithMetaInterestRatePayoutBuilder result;
			if (interestRatePayout!=null) {
				result = interestRatePayout;
			}
			else {
				result = interestRatePayout = ReferenceWithMetaInterestRatePayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionPayout")
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder getOptionPayout() {
			return optionPayout;
		}
		
		@Override
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder getOrCreateOptionPayout() {
			ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder result;
			if (optionPayout!=null) {
				result = optionPayout;
			}
			else {
				result = optionPayout = ReferenceWithMetaOptionPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("assetPayout")
		public ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder getAssetPayout() {
			return assetPayout;
		}
		
		@Override
		public ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder getOrCreateAssetPayout() {
			ReferenceWithMetaAssetPayout.ReferenceWithMetaAssetPayoutBuilder result;
			if (assetPayout!=null) {
				result = assetPayout;
			}
			else {
				result = assetPayout = ReferenceWithMetaAssetPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder getOrCreateSettlementTerms() {
			ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder result;
			if (settlementTerms!=null) {
				result = settlementTerms;
			}
			else {
				result = settlementTerms = ReferenceWithMetaSettlementTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("performancePayout")
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder getPerformancePayout() {
			return performancePayout;
		}
		
		@Override
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder getOrCreatePerformancePayout() {
			ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder result;
			if (performancePayout!=null) {
				result = performancePayout;
			}
			else {
				result = performancePayout = ReferenceWithMetaPerformancePayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixedPricePayout")
		public ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder getFixedPricePayout() {
			return fixedPricePayout;
		}
		
		@Override
		public ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder getOrCreateFixedPricePayout() {
			ReferenceWithMetaFixedPricePayout.ReferenceWithMetaFixedPricePayoutBuilder result;
			if (fixedPricePayout!=null) {
				result = fixedPricePayout;
			}
			else {
				result = fixedPricePayout = ReferenceWithMetaFixedPricePayout.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("commodityPayout")
		public SettlementOrigin.SettlementOriginBuilder setCommodityPayout(ReferenceWithMetaCommodityPayout commodityPayout) {
			this.commodityPayout = commodityPayout==null?null:commodityPayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setCommodityPayoutValue(CommodityPayout commodityPayout) {
			this.getOrCreateCommodityPayout().setValue(commodityPayout);
			return this;
		}
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public SettlementOrigin.SettlementOriginBuilder setCreditDefaultPayout(ReferenceWithMetaCreditDefaultPayout creditDefaultPayout) {
			this.creditDefaultPayout = creditDefaultPayout==null?null:creditDefaultPayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setCreditDefaultPayoutValue(CreditDefaultPayout creditDefaultPayout) {
			this.getOrCreateCreditDefaultPayout().setValue(creditDefaultPayout);
			return this;
		}
		@Override
		@RosettaAttribute("forwardPayout")
		public SettlementOrigin.SettlementOriginBuilder setForwardPayout(ReferenceWithMetaForwardPayout forwardPayout) {
			this.forwardPayout = forwardPayout==null?null:forwardPayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setForwardPayoutValue(ForwardPayout forwardPayout) {
			this.getOrCreateForwardPayout().setValue(forwardPayout);
			return this;
		}
		@Override
		@RosettaAttribute("interestRatePayout")
		public SettlementOrigin.SettlementOriginBuilder setInterestRatePayout(ReferenceWithMetaInterestRatePayout interestRatePayout) {
			this.interestRatePayout = interestRatePayout==null?null:interestRatePayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setInterestRatePayoutValue(InterestRatePayout interestRatePayout) {
			this.getOrCreateInterestRatePayout().setValue(interestRatePayout);
			return this;
		}
		@Override
		@RosettaAttribute("optionPayout")
		public SettlementOrigin.SettlementOriginBuilder setOptionPayout(ReferenceWithMetaOptionPayout optionPayout) {
			this.optionPayout = optionPayout==null?null:optionPayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setOptionPayoutValue(OptionPayout optionPayout) {
			this.getOrCreateOptionPayout().setValue(optionPayout);
			return this;
		}
		@Override
		@RosettaAttribute("assetPayout")
		public SettlementOrigin.SettlementOriginBuilder setAssetPayout(ReferenceWithMetaAssetPayout assetPayout) {
			this.assetPayout = assetPayout==null?null:assetPayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setAssetPayoutValue(AssetPayout assetPayout) {
			this.getOrCreateAssetPayout().setValue(assetPayout);
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public SettlementOrigin.SettlementOriginBuilder setSettlementTerms(ReferenceWithMetaSettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setSettlementTermsValue(SettlementTerms settlementTerms) {
			this.getOrCreateSettlementTerms().setValue(settlementTerms);
			return this;
		}
		@Override
		@RosettaAttribute("performancePayout")
		public SettlementOrigin.SettlementOriginBuilder setPerformancePayout(ReferenceWithMetaPerformancePayout performancePayout) {
			this.performancePayout = performancePayout==null?null:performancePayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setPerformancePayoutValue(PerformancePayout performancePayout) {
			this.getOrCreatePerformancePayout().setValue(performancePayout);
			return this;
		}
		@Override
		@RosettaAttribute("fixedPricePayout")
		public SettlementOrigin.SettlementOriginBuilder setFixedPricePayout(ReferenceWithMetaFixedPricePayout fixedPricePayout) {
			this.fixedPricePayout = fixedPricePayout==null?null:fixedPricePayout.toBuilder();
			return this;
		}
		@Override
		public SettlementOrigin.SettlementOriginBuilder setFixedPricePayoutValue(FixedPricePayout fixedPricePayout) {
			this.getOrCreateFixedPricePayout().setValue(fixedPricePayout);
			return this;
		}
		
		@Override
		public SettlementOrigin build() {
			return new SettlementOrigin.SettlementOriginImpl(this);
		}
		
		@Override
		public SettlementOrigin.SettlementOriginBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementOrigin.SettlementOriginBuilder prune() {
			if (commodityPayout!=null && !commodityPayout.prune().hasData()) commodityPayout = null;
			if (creditDefaultPayout!=null && !creditDefaultPayout.prune().hasData()) creditDefaultPayout = null;
			if (forwardPayout!=null && !forwardPayout.prune().hasData()) forwardPayout = null;
			if (interestRatePayout!=null && !interestRatePayout.prune().hasData()) interestRatePayout = null;
			if (optionPayout!=null && !optionPayout.prune().hasData()) optionPayout = null;
			if (assetPayout!=null && !assetPayout.prune().hasData()) assetPayout = null;
			if (settlementTerms!=null && !settlementTerms.prune().hasData()) settlementTerms = null;
			if (performancePayout!=null && !performancePayout.prune().hasData()) performancePayout = null;
			if (fixedPricePayout!=null && !fixedPricePayout.prune().hasData()) fixedPricePayout = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCommodityPayout()!=null && getCommodityPayout().hasData()) return true;
			if (getCreditDefaultPayout()!=null && getCreditDefaultPayout().hasData()) return true;
			if (getForwardPayout()!=null && getForwardPayout().hasData()) return true;
			if (getInterestRatePayout()!=null && getInterestRatePayout().hasData()) return true;
			if (getOptionPayout()!=null && getOptionPayout().hasData()) return true;
			if (getAssetPayout()!=null && getAssetPayout().hasData()) return true;
			if (getSettlementTerms()!=null && getSettlementTerms().hasData()) return true;
			if (getPerformancePayout()!=null && getPerformancePayout().hasData()) return true;
			if (getFixedPricePayout()!=null && getFixedPricePayout().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementOrigin.SettlementOriginBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettlementOrigin.SettlementOriginBuilder o = (SettlementOrigin.SettlementOriginBuilder) other;
			
			merger.mergeRosetta(getCommodityPayout(), o.getCommodityPayout(), this::setCommodityPayout);
			merger.mergeRosetta(getCreditDefaultPayout(), o.getCreditDefaultPayout(), this::setCreditDefaultPayout);
			merger.mergeRosetta(getForwardPayout(), o.getForwardPayout(), this::setForwardPayout);
			merger.mergeRosetta(getInterestRatePayout(), o.getInterestRatePayout(), this::setInterestRatePayout);
			merger.mergeRosetta(getOptionPayout(), o.getOptionPayout(), this::setOptionPayout);
			merger.mergeRosetta(getAssetPayout(), o.getAssetPayout(), this::setAssetPayout);
			merger.mergeRosetta(getSettlementTerms(), o.getSettlementTerms(), this::setSettlementTerms);
			merger.mergeRosetta(getPerformancePayout(), o.getPerformancePayout(), this::setPerformancePayout);
			merger.mergeRosetta(getFixedPricePayout(), o.getFixedPricePayout(), this::setFixedPricePayout);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementOrigin _that = getType().cast(o);
		
			if (!Objects.equals(commodityPayout, _that.getCommodityPayout())) return false;
			if (!Objects.equals(creditDefaultPayout, _that.getCreditDefaultPayout())) return false;
			if (!Objects.equals(forwardPayout, _that.getForwardPayout())) return false;
			if (!Objects.equals(interestRatePayout, _that.getInterestRatePayout())) return false;
			if (!Objects.equals(optionPayout, _that.getOptionPayout())) return false;
			if (!Objects.equals(assetPayout, _that.getAssetPayout())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			if (!Objects.equals(performancePayout, _that.getPerformancePayout())) return false;
			if (!Objects.equals(fixedPricePayout, _that.getFixedPricePayout())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (commodityPayout != null ? commodityPayout.hashCode() : 0);
			_result = 31 * _result + (creditDefaultPayout != null ? creditDefaultPayout.hashCode() : 0);
			_result = 31 * _result + (forwardPayout != null ? forwardPayout.hashCode() : 0);
			_result = 31 * _result + (interestRatePayout != null ? interestRatePayout.hashCode() : 0);
			_result = 31 * _result + (optionPayout != null ? optionPayout.hashCode() : 0);
			_result = 31 * _result + (assetPayout != null ? assetPayout.hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			_result = 31 * _result + (performancePayout != null ? performancePayout.hashCode() : 0);
			_result = 31 * _result + (fixedPricePayout != null ? fixedPricePayout.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementOriginBuilder {" +
				"commodityPayout=" + this.commodityPayout + ", " +
				"creditDefaultPayout=" + this.creditDefaultPayout + ", " +
				"forwardPayout=" + this.forwardPayout + ", " +
				"interestRatePayout=" + this.interestRatePayout + ", " +
				"optionPayout=" + this.optionPayout + ", " +
				"assetPayout=" + this.assetPayout + ", " +
				"settlementTerms=" + this.settlementTerms + ", " +
				"performancePayout=" + this.performancePayout + ", " +
				"fixedPricePayout=" + this.fixedPricePayout +
			'}';
		}
	}
}
