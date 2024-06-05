package cdm.product.template;

import cdm.product.asset.CommodityPayout;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.settlement.Cashflow;
import cdm.product.template.AssetPayout;
import cdm.product.template.FixedPricePayout;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.Payout.PayoutBuilder;
import cdm.product.template.Payout.PayoutBuilderImpl;
import cdm.product.template.Payout.PayoutImpl;
import cdm.product.template.PerformancePayout;
import cdm.product.template.SecurityPayout;
import cdm.product.template.meta.PayoutMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to represent the set of future cashflow methodologies in the form of specific payout class(es) that can be associated for the purpose of specifying a financial product. For example, two interest rate payouts can be combined to specify an interest rate swap, or one interest rate payout can be combined with a credit default payout to specify a credit default swap.
 * @version ${project.version}
 */
@RosettaDataType(value="Payout", builder=Payout.PayoutBuilderImpl.class, version="${project.version}")
public interface Payout extends RosettaModelObject, GlobalKey {

	PayoutMeta metaData = new PayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * All of the terms necessary to define and calculate a cash flow based on a fixed, a floating or an inflation index rate. The interest rate payout can be applied to interest rate swaps and FRA (which both have two associated interest rate payouts), credit default swaps (to represent the fee leg when subject to periodic payments) and equity swaps (to represent the funding leg).
	 */
	List<? extends InterestRatePayout> getInterestRatePayout();
	/**
	 * The credit default payout, which provides the details necessary for determining when a credit payout will be triggered as well as the parameters for calculating the payout and the settlement terms.
	 */
	CreditDefaultPayout getCreditDefaultPayout();
	/**
	 * The option payout.
	 */
	List<? extends OptionPayout> getOptionPayout();
	/**
	 * Defines the payout for the floating leg of a Commodity Swap.
	 */
	List<? extends CommodityPayout> getCommodityPayout();
	/**
	 * Represents a forward settling payout. The &#39;Underlier&#39; attribute captures the underlying payout, which is settled according to the &#39;SettlementTerms&#39; attribute. Both FX Spot and FX Forward should use this component.
	 */
	List<? extends ForwardPayout> getForwardPayout();
	/**
	 * Defines a payout in which one or more payouts are defined as a fixed price.
	 */
	List<? extends FixedPricePayout> getFixedPricePayout();
	/**
	 * The security payout when the product involves some form of securities, such as collateral in a securities financing transaction
	 */
	List<? extends SecurityPayout> getSecurityPayout();
	/**
	 * A cashflow between the parties to the trade. For interest rate and equity products, this corresponds to the FpML additionalPayment element. For credit default swaps, this corresponds to the FpML initialPayment element and the singlePayment element of the fee leg. For option products, it represents the FpML premium element.
	 */
	List<? extends Cashflow> getCashflow();
	/**
	 * The performance payout, which encompasses the equity price returns, dividend returns, volatility return, variance return and correlation provisions.
	 */
	List<? extends PerformancePayout> getPerformancePayout();
	/**
	 * The security payout when the product involves some form of securities, such as collateral in a securities financing transaction
	 */
	List<? extends AssetPayout> getAssetPayout();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Payout build();
	
	Payout.PayoutBuilder toBuilder();
	
	static Payout.PayoutBuilder builder() {
		return new Payout.PayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Payout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Payout> getType() {
		return Payout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("interestRatePayout"), processor, InterestRatePayout.class, getInterestRatePayout());
		processRosetta(path.newSubPath("creditDefaultPayout"), processor, CreditDefaultPayout.class, getCreditDefaultPayout());
		processRosetta(path.newSubPath("optionPayout"), processor, OptionPayout.class, getOptionPayout());
		processRosetta(path.newSubPath("commodityPayout"), processor, CommodityPayout.class, getCommodityPayout());
		processRosetta(path.newSubPath("forwardPayout"), processor, ForwardPayout.class, getForwardPayout());
		processRosetta(path.newSubPath("fixedPricePayout"), processor, FixedPricePayout.class, getFixedPricePayout());
		processRosetta(path.newSubPath("securityPayout"), processor, SecurityPayout.class, getSecurityPayout());
		processRosetta(path.newSubPath("cashflow"), processor, Cashflow.class, getCashflow());
		processRosetta(path.newSubPath("performancePayout"), processor, PerformancePayout.class, getPerformancePayout());
		processRosetta(path.newSubPath("assetPayout"), processor, AssetPayout.class, getAssetPayout());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PayoutBuilder extends Payout, RosettaModelObjectBuilder {
		InterestRatePayout.InterestRatePayoutBuilder getOrCreateInterestRatePayout(int _index);
		List<? extends InterestRatePayout.InterestRatePayoutBuilder> getInterestRatePayout();
		CreditDefaultPayout.CreditDefaultPayoutBuilder getOrCreateCreditDefaultPayout();
		CreditDefaultPayout.CreditDefaultPayoutBuilder getCreditDefaultPayout();
		OptionPayout.OptionPayoutBuilder getOrCreateOptionPayout(int _index);
		List<? extends OptionPayout.OptionPayoutBuilder> getOptionPayout();
		CommodityPayout.CommodityPayoutBuilder getOrCreateCommodityPayout(int _index);
		List<? extends CommodityPayout.CommodityPayoutBuilder> getCommodityPayout();
		ForwardPayout.ForwardPayoutBuilder getOrCreateForwardPayout(int _index);
		List<? extends ForwardPayout.ForwardPayoutBuilder> getForwardPayout();
		FixedPricePayout.FixedPricePayoutBuilder getOrCreateFixedPricePayout(int _index);
		List<? extends FixedPricePayout.FixedPricePayoutBuilder> getFixedPricePayout();
		SecurityPayout.SecurityPayoutBuilder getOrCreateSecurityPayout(int _index);
		List<? extends SecurityPayout.SecurityPayoutBuilder> getSecurityPayout();
		Cashflow.CashflowBuilder getOrCreateCashflow(int _index);
		List<? extends Cashflow.CashflowBuilder> getCashflow();
		PerformancePayout.PerformancePayoutBuilder getOrCreatePerformancePayout(int _index);
		List<? extends PerformancePayout.PerformancePayoutBuilder> getPerformancePayout();
		AssetPayout.AssetPayoutBuilder getOrCreateAssetPayout(int _index);
		List<? extends AssetPayout.AssetPayoutBuilder> getAssetPayout();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Payout.PayoutBuilder addInterestRatePayout(InterestRatePayout interestRatePayout0);
		Payout.PayoutBuilder addInterestRatePayout(InterestRatePayout interestRatePayout1, int _idx);
		Payout.PayoutBuilder addInterestRatePayout(List<? extends InterestRatePayout> interestRatePayout2);
		Payout.PayoutBuilder setInterestRatePayout(List<? extends InterestRatePayout> interestRatePayout3);
		Payout.PayoutBuilder setCreditDefaultPayout(CreditDefaultPayout creditDefaultPayout);
		Payout.PayoutBuilder addOptionPayout(OptionPayout optionPayout0);
		Payout.PayoutBuilder addOptionPayout(OptionPayout optionPayout1, int _idx);
		Payout.PayoutBuilder addOptionPayout(List<? extends OptionPayout> optionPayout2);
		Payout.PayoutBuilder setOptionPayout(List<? extends OptionPayout> optionPayout3);
		Payout.PayoutBuilder addCommodityPayout(CommodityPayout commodityPayout0);
		Payout.PayoutBuilder addCommodityPayout(CommodityPayout commodityPayout1, int _idx);
		Payout.PayoutBuilder addCommodityPayout(List<? extends CommodityPayout> commodityPayout2);
		Payout.PayoutBuilder setCommodityPayout(List<? extends CommodityPayout> commodityPayout3);
		Payout.PayoutBuilder addForwardPayout(ForwardPayout forwardPayout0);
		Payout.PayoutBuilder addForwardPayout(ForwardPayout forwardPayout1, int _idx);
		Payout.PayoutBuilder addForwardPayout(List<? extends ForwardPayout> forwardPayout2);
		Payout.PayoutBuilder setForwardPayout(List<? extends ForwardPayout> forwardPayout3);
		Payout.PayoutBuilder addFixedPricePayout(FixedPricePayout fixedPricePayout0);
		Payout.PayoutBuilder addFixedPricePayout(FixedPricePayout fixedPricePayout1, int _idx);
		Payout.PayoutBuilder addFixedPricePayout(List<? extends FixedPricePayout> fixedPricePayout2);
		Payout.PayoutBuilder setFixedPricePayout(List<? extends FixedPricePayout> fixedPricePayout3);
		Payout.PayoutBuilder addSecurityPayout(SecurityPayout securityPayout0);
		Payout.PayoutBuilder addSecurityPayout(SecurityPayout securityPayout1, int _idx);
		Payout.PayoutBuilder addSecurityPayout(List<? extends SecurityPayout> securityPayout2);
		Payout.PayoutBuilder setSecurityPayout(List<? extends SecurityPayout> securityPayout3);
		Payout.PayoutBuilder addCashflow(Cashflow cashflow0);
		Payout.PayoutBuilder addCashflow(Cashflow cashflow1, int _idx);
		Payout.PayoutBuilder addCashflow(List<? extends Cashflow> cashflow2);
		Payout.PayoutBuilder setCashflow(List<? extends Cashflow> cashflow3);
		Payout.PayoutBuilder addPerformancePayout(PerformancePayout performancePayout0);
		Payout.PayoutBuilder addPerformancePayout(PerformancePayout performancePayout1, int _idx);
		Payout.PayoutBuilder addPerformancePayout(List<? extends PerformancePayout> performancePayout2);
		Payout.PayoutBuilder setPerformancePayout(List<? extends PerformancePayout> performancePayout3);
		Payout.PayoutBuilder addAssetPayout(AssetPayout assetPayout0);
		Payout.PayoutBuilder addAssetPayout(AssetPayout assetPayout1, int _idx);
		Payout.PayoutBuilder addAssetPayout(List<? extends AssetPayout> assetPayout2);
		Payout.PayoutBuilder setAssetPayout(List<? extends AssetPayout> assetPayout3);
		Payout.PayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("interestRatePayout"), processor, InterestRatePayout.InterestRatePayoutBuilder.class, getInterestRatePayout());
			processRosetta(path.newSubPath("creditDefaultPayout"), processor, CreditDefaultPayout.CreditDefaultPayoutBuilder.class, getCreditDefaultPayout());
			processRosetta(path.newSubPath("optionPayout"), processor, OptionPayout.OptionPayoutBuilder.class, getOptionPayout());
			processRosetta(path.newSubPath("commodityPayout"), processor, CommodityPayout.CommodityPayoutBuilder.class, getCommodityPayout());
			processRosetta(path.newSubPath("forwardPayout"), processor, ForwardPayout.ForwardPayoutBuilder.class, getForwardPayout());
			processRosetta(path.newSubPath("fixedPricePayout"), processor, FixedPricePayout.FixedPricePayoutBuilder.class, getFixedPricePayout());
			processRosetta(path.newSubPath("securityPayout"), processor, SecurityPayout.SecurityPayoutBuilder.class, getSecurityPayout());
			processRosetta(path.newSubPath("cashflow"), processor, Cashflow.CashflowBuilder.class, getCashflow());
			processRosetta(path.newSubPath("performancePayout"), processor, PerformancePayout.PerformancePayoutBuilder.class, getPerformancePayout());
			processRosetta(path.newSubPath("assetPayout"), processor, AssetPayout.AssetPayoutBuilder.class, getAssetPayout());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Payout.PayoutBuilder prune();
	}

	/*********************** Immutable Implementation of Payout  ***********************/
	class PayoutImpl implements Payout {
		private final List<? extends InterestRatePayout> interestRatePayout;
		private final CreditDefaultPayout creditDefaultPayout;
		private final List<? extends OptionPayout> optionPayout;
		private final List<? extends CommodityPayout> commodityPayout;
		private final List<? extends ForwardPayout> forwardPayout;
		private final List<? extends FixedPricePayout> fixedPricePayout;
		private final List<? extends SecurityPayout> securityPayout;
		private final List<? extends Cashflow> cashflow;
		private final List<? extends PerformancePayout> performancePayout;
		private final List<? extends AssetPayout> assetPayout;
		private final MetaFields meta;
		
		protected PayoutImpl(Payout.PayoutBuilder builder) {
			this.interestRatePayout = ofNullable(builder.getInterestRatePayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.creditDefaultPayout = ofNullable(builder.getCreditDefaultPayout()).map(f->f.build()).orElse(null);
			this.optionPayout = ofNullable(builder.getOptionPayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.commodityPayout = ofNullable(builder.getCommodityPayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.forwardPayout = ofNullable(builder.getForwardPayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.fixedPricePayout = ofNullable(builder.getFixedPricePayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.securityPayout = ofNullable(builder.getSecurityPayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.cashflow = ofNullable(builder.getCashflow()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.performancePayout = ofNullable(builder.getPerformancePayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.assetPayout = ofNullable(builder.getAssetPayout()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("interestRatePayout")
		public List<? extends InterestRatePayout> getInterestRatePayout() {
			return interestRatePayout;
		}
		
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public CreditDefaultPayout getCreditDefaultPayout() {
			return creditDefaultPayout;
		}
		
		@Override
		@RosettaAttribute("optionPayout")
		public List<? extends OptionPayout> getOptionPayout() {
			return optionPayout;
		}
		
		@Override
		@RosettaAttribute("commodityPayout")
		public List<? extends CommodityPayout> getCommodityPayout() {
			return commodityPayout;
		}
		
		@Override
		@RosettaAttribute("forwardPayout")
		public List<? extends ForwardPayout> getForwardPayout() {
			return forwardPayout;
		}
		
		@Override
		@RosettaAttribute("fixedPricePayout")
		public List<? extends FixedPricePayout> getFixedPricePayout() {
			return fixedPricePayout;
		}
		
		@Override
		@RosettaAttribute("securityPayout")
		public List<? extends SecurityPayout> getSecurityPayout() {
			return securityPayout;
		}
		
		@Override
		@RosettaAttribute("cashflow")
		public List<? extends Cashflow> getCashflow() {
			return cashflow;
		}
		
		@Override
		@RosettaAttribute("performancePayout")
		public List<? extends PerformancePayout> getPerformancePayout() {
			return performancePayout;
		}
		
		@Override
		@RosettaAttribute("assetPayout")
		public List<? extends AssetPayout> getAssetPayout() {
			return assetPayout;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Payout build() {
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder toBuilder() {
			Payout.PayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Payout.PayoutBuilder builder) {
			ofNullable(getInterestRatePayout()).ifPresent(builder::setInterestRatePayout);
			ofNullable(getCreditDefaultPayout()).ifPresent(builder::setCreditDefaultPayout);
			ofNullable(getOptionPayout()).ifPresent(builder::setOptionPayout);
			ofNullable(getCommodityPayout()).ifPresent(builder::setCommodityPayout);
			ofNullable(getForwardPayout()).ifPresent(builder::setForwardPayout);
			ofNullable(getFixedPricePayout()).ifPresent(builder::setFixedPricePayout);
			ofNullable(getSecurityPayout()).ifPresent(builder::setSecurityPayout);
			ofNullable(getCashflow()).ifPresent(builder::setCashflow);
			ofNullable(getPerformancePayout()).ifPresent(builder::setPerformancePayout);
			ofNullable(getAssetPayout()).ifPresent(builder::setAssetPayout);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Payout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interestRatePayout, _that.getInterestRatePayout())) return false;
			if (!Objects.equals(creditDefaultPayout, _that.getCreditDefaultPayout())) return false;
			if (!ListEquals.listEquals(optionPayout, _that.getOptionPayout())) return false;
			if (!ListEquals.listEquals(commodityPayout, _that.getCommodityPayout())) return false;
			if (!ListEquals.listEquals(forwardPayout, _that.getForwardPayout())) return false;
			if (!ListEquals.listEquals(fixedPricePayout, _that.getFixedPricePayout())) return false;
			if (!ListEquals.listEquals(securityPayout, _that.getSecurityPayout())) return false;
			if (!ListEquals.listEquals(cashflow, _that.getCashflow())) return false;
			if (!ListEquals.listEquals(performancePayout, _that.getPerformancePayout())) return false;
			if (!ListEquals.listEquals(assetPayout, _that.getAssetPayout())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestRatePayout != null ? interestRatePayout.hashCode() : 0);
			_result = 31 * _result + (creditDefaultPayout != null ? creditDefaultPayout.hashCode() : 0);
			_result = 31 * _result + (optionPayout != null ? optionPayout.hashCode() : 0);
			_result = 31 * _result + (commodityPayout != null ? commodityPayout.hashCode() : 0);
			_result = 31 * _result + (forwardPayout != null ? forwardPayout.hashCode() : 0);
			_result = 31 * _result + (fixedPricePayout != null ? fixedPricePayout.hashCode() : 0);
			_result = 31 * _result + (securityPayout != null ? securityPayout.hashCode() : 0);
			_result = 31 * _result + (cashflow != null ? cashflow.hashCode() : 0);
			_result = 31 * _result + (performancePayout != null ? performancePayout.hashCode() : 0);
			_result = 31 * _result + (assetPayout != null ? assetPayout.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Payout {" +
				"interestRatePayout=" + this.interestRatePayout + ", " +
				"creditDefaultPayout=" + this.creditDefaultPayout + ", " +
				"optionPayout=" + this.optionPayout + ", " +
				"commodityPayout=" + this.commodityPayout + ", " +
				"forwardPayout=" + this.forwardPayout + ", " +
				"fixedPricePayout=" + this.fixedPricePayout + ", " +
				"securityPayout=" + this.securityPayout + ", " +
				"cashflow=" + this.cashflow + ", " +
				"performancePayout=" + this.performancePayout + ", " +
				"assetPayout=" + this.assetPayout + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Payout  ***********************/
	class PayoutBuilderImpl implements Payout.PayoutBuilder, GlobalKeyBuilder {
	
		protected List<InterestRatePayout.InterestRatePayoutBuilder> interestRatePayout = new ArrayList<>();
		protected CreditDefaultPayout.CreditDefaultPayoutBuilder creditDefaultPayout;
		protected List<OptionPayout.OptionPayoutBuilder> optionPayout = new ArrayList<>();
		protected List<CommodityPayout.CommodityPayoutBuilder> commodityPayout = new ArrayList<>();
		protected List<ForwardPayout.ForwardPayoutBuilder> forwardPayout = new ArrayList<>();
		protected List<FixedPricePayout.FixedPricePayoutBuilder> fixedPricePayout = new ArrayList<>();
		protected List<SecurityPayout.SecurityPayoutBuilder> securityPayout = new ArrayList<>();
		protected List<Cashflow.CashflowBuilder> cashflow = new ArrayList<>();
		protected List<PerformancePayout.PerformancePayoutBuilder> performancePayout = new ArrayList<>();
		protected List<AssetPayout.AssetPayoutBuilder> assetPayout = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interestRatePayout")
		public List<? extends InterestRatePayout.InterestRatePayoutBuilder> getInterestRatePayout() {
			return interestRatePayout;
		}
		
		public InterestRatePayout.InterestRatePayoutBuilder getOrCreateInterestRatePayout(int _index) {
		
			if (interestRatePayout==null) {
				this.interestRatePayout = new ArrayList<>();
			}
			InterestRatePayout.InterestRatePayoutBuilder result;
			return getIndex(interestRatePayout, _index, () -> {
						InterestRatePayout.InterestRatePayoutBuilder newInterestRatePayout = InterestRatePayout.builder();
						return newInterestRatePayout;
					});
		}
		
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder getCreditDefaultPayout() {
			return creditDefaultPayout;
		}
		
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder getOrCreateCreditDefaultPayout() {
			CreditDefaultPayout.CreditDefaultPayoutBuilder result;
			if (creditDefaultPayout!=null) {
				result = creditDefaultPayout;
			}
			else {
				result = creditDefaultPayout = CreditDefaultPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionPayout")
		public List<? extends OptionPayout.OptionPayoutBuilder> getOptionPayout() {
			return optionPayout;
		}
		
		public OptionPayout.OptionPayoutBuilder getOrCreateOptionPayout(int _index) {
		
			if (optionPayout==null) {
				this.optionPayout = new ArrayList<>();
			}
			OptionPayout.OptionPayoutBuilder result;
			return getIndex(optionPayout, _index, () -> {
						OptionPayout.OptionPayoutBuilder newOptionPayout = OptionPayout.builder();
						return newOptionPayout;
					});
		}
		
		@Override
		@RosettaAttribute("commodityPayout")
		public List<? extends CommodityPayout.CommodityPayoutBuilder> getCommodityPayout() {
			return commodityPayout;
		}
		
		public CommodityPayout.CommodityPayoutBuilder getOrCreateCommodityPayout(int _index) {
		
			if (commodityPayout==null) {
				this.commodityPayout = new ArrayList<>();
			}
			CommodityPayout.CommodityPayoutBuilder result;
			return getIndex(commodityPayout, _index, () -> {
						CommodityPayout.CommodityPayoutBuilder newCommodityPayout = CommodityPayout.builder();
						return newCommodityPayout;
					});
		}
		
		@Override
		@RosettaAttribute("forwardPayout")
		public List<? extends ForwardPayout.ForwardPayoutBuilder> getForwardPayout() {
			return forwardPayout;
		}
		
		public ForwardPayout.ForwardPayoutBuilder getOrCreateForwardPayout(int _index) {
		
			if (forwardPayout==null) {
				this.forwardPayout = new ArrayList<>();
			}
			ForwardPayout.ForwardPayoutBuilder result;
			return getIndex(forwardPayout, _index, () -> {
						ForwardPayout.ForwardPayoutBuilder newForwardPayout = ForwardPayout.builder();
						return newForwardPayout;
					});
		}
		
		@Override
		@RosettaAttribute("fixedPricePayout")
		public List<? extends FixedPricePayout.FixedPricePayoutBuilder> getFixedPricePayout() {
			return fixedPricePayout;
		}
		
		public FixedPricePayout.FixedPricePayoutBuilder getOrCreateFixedPricePayout(int _index) {
		
			if (fixedPricePayout==null) {
				this.fixedPricePayout = new ArrayList<>();
			}
			FixedPricePayout.FixedPricePayoutBuilder result;
			return getIndex(fixedPricePayout, _index, () -> {
						FixedPricePayout.FixedPricePayoutBuilder newFixedPricePayout = FixedPricePayout.builder();
						return newFixedPricePayout;
					});
		}
		
		@Override
		@RosettaAttribute("securityPayout")
		public List<? extends SecurityPayout.SecurityPayoutBuilder> getSecurityPayout() {
			return securityPayout;
		}
		
		public SecurityPayout.SecurityPayoutBuilder getOrCreateSecurityPayout(int _index) {
		
			if (securityPayout==null) {
				this.securityPayout = new ArrayList<>();
			}
			SecurityPayout.SecurityPayoutBuilder result;
			return getIndex(securityPayout, _index, () -> {
						SecurityPayout.SecurityPayoutBuilder newSecurityPayout = SecurityPayout.builder();
						return newSecurityPayout;
					});
		}
		
		@Override
		@RosettaAttribute("cashflow")
		public List<? extends Cashflow.CashflowBuilder> getCashflow() {
			return cashflow;
		}
		
		public Cashflow.CashflowBuilder getOrCreateCashflow(int _index) {
		
			if (cashflow==null) {
				this.cashflow = new ArrayList<>();
			}
			Cashflow.CashflowBuilder result;
			return getIndex(cashflow, _index, () -> {
						Cashflow.CashflowBuilder newCashflow = Cashflow.builder();
						return newCashflow;
					});
		}
		
		@Override
		@RosettaAttribute("performancePayout")
		public List<? extends PerformancePayout.PerformancePayoutBuilder> getPerformancePayout() {
			return performancePayout;
		}
		
		public PerformancePayout.PerformancePayoutBuilder getOrCreatePerformancePayout(int _index) {
		
			if (performancePayout==null) {
				this.performancePayout = new ArrayList<>();
			}
			PerformancePayout.PerformancePayoutBuilder result;
			return getIndex(performancePayout, _index, () -> {
						PerformancePayout.PerformancePayoutBuilder newPerformancePayout = PerformancePayout.builder();
						return newPerformancePayout;
					});
		}
		
		@Override
		@RosettaAttribute("assetPayout")
		public List<? extends AssetPayout.AssetPayoutBuilder> getAssetPayout() {
			return assetPayout;
		}
		
		public AssetPayout.AssetPayoutBuilder getOrCreateAssetPayout(int _index) {
		
			if (assetPayout==null) {
				this.assetPayout = new ArrayList<>();
			}
			AssetPayout.AssetPayoutBuilder result;
			return getIndex(assetPayout, _index, () -> {
						AssetPayout.AssetPayoutBuilder newAssetPayout = AssetPayout.builder();
						return newAssetPayout;
					});
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		public Payout.PayoutBuilder addInterestRatePayout(InterestRatePayout interestRatePayout) {
			if (interestRatePayout!=null) this.interestRatePayout.add(interestRatePayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addInterestRatePayout(InterestRatePayout interestRatePayout, int _idx) {
			getIndex(this.interestRatePayout, _idx, () -> interestRatePayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addInterestRatePayout(List<? extends InterestRatePayout> interestRatePayouts) {
			if (interestRatePayouts != null) {
				for (InterestRatePayout toAdd : interestRatePayouts) {
					this.interestRatePayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("interestRatePayout")
		public Payout.PayoutBuilder setInterestRatePayout(List<? extends InterestRatePayout> interestRatePayouts) {
			if (interestRatePayouts == null)  {
				this.interestRatePayout = new ArrayList<>();
			}
			else {
				this.interestRatePayout = interestRatePayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("creditDefaultPayout")
		public Payout.PayoutBuilder setCreditDefaultPayout(CreditDefaultPayout creditDefaultPayout) {
			this.creditDefaultPayout = creditDefaultPayout==null?null:creditDefaultPayout.toBuilder();
			return this;
		}
		@Override
		public Payout.PayoutBuilder addOptionPayout(OptionPayout optionPayout) {
			if (optionPayout!=null) this.optionPayout.add(optionPayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addOptionPayout(OptionPayout optionPayout, int _idx) {
			getIndex(this.optionPayout, _idx, () -> optionPayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addOptionPayout(List<? extends OptionPayout> optionPayouts) {
			if (optionPayouts != null) {
				for (OptionPayout toAdd : optionPayouts) {
					this.optionPayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("optionPayout")
		public Payout.PayoutBuilder setOptionPayout(List<? extends OptionPayout> optionPayouts) {
			if (optionPayouts == null)  {
				this.optionPayout = new ArrayList<>();
			}
			else {
				this.optionPayout = optionPayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addCommodityPayout(CommodityPayout commodityPayout) {
			if (commodityPayout!=null) this.commodityPayout.add(commodityPayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addCommodityPayout(CommodityPayout commodityPayout, int _idx) {
			getIndex(this.commodityPayout, _idx, () -> commodityPayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addCommodityPayout(List<? extends CommodityPayout> commodityPayouts) {
			if (commodityPayouts != null) {
				for (CommodityPayout toAdd : commodityPayouts) {
					this.commodityPayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("commodityPayout")
		public Payout.PayoutBuilder setCommodityPayout(List<? extends CommodityPayout> commodityPayouts) {
			if (commodityPayouts == null)  {
				this.commodityPayout = new ArrayList<>();
			}
			else {
				this.commodityPayout = commodityPayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addForwardPayout(ForwardPayout forwardPayout) {
			if (forwardPayout!=null) this.forwardPayout.add(forwardPayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addForwardPayout(ForwardPayout forwardPayout, int _idx) {
			getIndex(this.forwardPayout, _idx, () -> forwardPayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addForwardPayout(List<? extends ForwardPayout> forwardPayouts) {
			if (forwardPayouts != null) {
				for (ForwardPayout toAdd : forwardPayouts) {
					this.forwardPayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("forwardPayout")
		public Payout.PayoutBuilder setForwardPayout(List<? extends ForwardPayout> forwardPayouts) {
			if (forwardPayouts == null)  {
				this.forwardPayout = new ArrayList<>();
			}
			else {
				this.forwardPayout = forwardPayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addFixedPricePayout(FixedPricePayout fixedPricePayout) {
			if (fixedPricePayout!=null) this.fixedPricePayout.add(fixedPricePayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addFixedPricePayout(FixedPricePayout fixedPricePayout, int _idx) {
			getIndex(this.fixedPricePayout, _idx, () -> fixedPricePayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addFixedPricePayout(List<? extends FixedPricePayout> fixedPricePayouts) {
			if (fixedPricePayouts != null) {
				for (FixedPricePayout toAdd : fixedPricePayouts) {
					this.fixedPricePayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("fixedPricePayout")
		public Payout.PayoutBuilder setFixedPricePayout(List<? extends FixedPricePayout> fixedPricePayouts) {
			if (fixedPricePayouts == null)  {
				this.fixedPricePayout = new ArrayList<>();
			}
			else {
				this.fixedPricePayout = fixedPricePayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addSecurityPayout(SecurityPayout securityPayout) {
			if (securityPayout!=null) this.securityPayout.add(securityPayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addSecurityPayout(SecurityPayout securityPayout, int _idx) {
			getIndex(this.securityPayout, _idx, () -> securityPayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addSecurityPayout(List<? extends SecurityPayout> securityPayouts) {
			if (securityPayouts != null) {
				for (SecurityPayout toAdd : securityPayouts) {
					this.securityPayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("securityPayout")
		public Payout.PayoutBuilder setSecurityPayout(List<? extends SecurityPayout> securityPayouts) {
			if (securityPayouts == null)  {
				this.securityPayout = new ArrayList<>();
			}
			else {
				this.securityPayout = securityPayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addCashflow(Cashflow cashflow) {
			if (cashflow!=null) this.cashflow.add(cashflow.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addCashflow(Cashflow cashflow, int _idx) {
			getIndex(this.cashflow, _idx, () -> cashflow.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addCashflow(List<? extends Cashflow> cashflows) {
			if (cashflows != null) {
				for (Cashflow toAdd : cashflows) {
					this.cashflow.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("cashflow")
		public Payout.PayoutBuilder setCashflow(List<? extends Cashflow> cashflows) {
			if (cashflows == null)  {
				this.cashflow = new ArrayList<>();
			}
			else {
				this.cashflow = cashflows.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addPerformancePayout(PerformancePayout performancePayout) {
			if (performancePayout!=null) this.performancePayout.add(performancePayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addPerformancePayout(PerformancePayout performancePayout, int _idx) {
			getIndex(this.performancePayout, _idx, () -> performancePayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addPerformancePayout(List<? extends PerformancePayout> performancePayouts) {
			if (performancePayouts != null) {
				for (PerformancePayout toAdd : performancePayouts) {
					this.performancePayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("performancePayout")
		public Payout.PayoutBuilder setPerformancePayout(List<? extends PerformancePayout> performancePayouts) {
			if (performancePayouts == null)  {
				this.performancePayout = new ArrayList<>();
			}
			else {
				this.performancePayout = performancePayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addAssetPayout(AssetPayout assetPayout) {
			if (assetPayout!=null) this.assetPayout.add(assetPayout.toBuilder());
			return this;
		}
		
		@Override
		public Payout.PayoutBuilder addAssetPayout(AssetPayout assetPayout, int _idx) {
			getIndex(this.assetPayout, _idx, () -> assetPayout.toBuilder());
			return this;
		}
		@Override 
		public Payout.PayoutBuilder addAssetPayout(List<? extends AssetPayout> assetPayouts) {
			if (assetPayouts != null) {
				for (AssetPayout toAdd : assetPayouts) {
					this.assetPayout.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("assetPayout")
		public Payout.PayoutBuilder setAssetPayout(List<? extends AssetPayout> assetPayouts) {
			if (assetPayouts == null)  {
				this.assetPayout = new ArrayList<>();
			}
			else {
				this.assetPayout = assetPayouts.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public Payout.PayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Payout build() {
			return new Payout.PayoutImpl(this);
		}
		
		@Override
		public Payout.PayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Payout.PayoutBuilder prune() {
			interestRatePayout = interestRatePayout.stream().filter(b->b!=null).<InterestRatePayout.InterestRatePayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (creditDefaultPayout!=null && !creditDefaultPayout.prune().hasData()) creditDefaultPayout = null;
			optionPayout = optionPayout.stream().filter(b->b!=null).<OptionPayout.OptionPayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			commodityPayout = commodityPayout.stream().filter(b->b!=null).<CommodityPayout.CommodityPayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			forwardPayout = forwardPayout.stream().filter(b->b!=null).<ForwardPayout.ForwardPayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			fixedPricePayout = fixedPricePayout.stream().filter(b->b!=null).<FixedPricePayout.FixedPricePayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			securityPayout = securityPayout.stream().filter(b->b!=null).<SecurityPayout.SecurityPayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			cashflow = cashflow.stream().filter(b->b!=null).<Cashflow.CashflowBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			performancePayout = performancePayout.stream().filter(b->b!=null).<PerformancePayout.PerformancePayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			assetPayout = assetPayout.stream().filter(b->b!=null).<AssetPayout.AssetPayoutBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterestRatePayout()!=null && getInterestRatePayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCreditDefaultPayout()!=null && getCreditDefaultPayout().hasData()) return true;
			if (getOptionPayout()!=null && getOptionPayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCommodityPayout()!=null && getCommodityPayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getForwardPayout()!=null && getForwardPayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFixedPricePayout()!=null && getFixedPricePayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getSecurityPayout()!=null && getSecurityPayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCashflow()!=null && getCashflow().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPerformancePayout()!=null && getPerformancePayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAssetPayout()!=null && getAssetPayout().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Payout.PayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Payout.PayoutBuilder o = (Payout.PayoutBuilder) other;
			
			merger.mergeRosetta(getInterestRatePayout(), o.getInterestRatePayout(), this::getOrCreateInterestRatePayout);
			merger.mergeRosetta(getCreditDefaultPayout(), o.getCreditDefaultPayout(), this::setCreditDefaultPayout);
			merger.mergeRosetta(getOptionPayout(), o.getOptionPayout(), this::getOrCreateOptionPayout);
			merger.mergeRosetta(getCommodityPayout(), o.getCommodityPayout(), this::getOrCreateCommodityPayout);
			merger.mergeRosetta(getForwardPayout(), o.getForwardPayout(), this::getOrCreateForwardPayout);
			merger.mergeRosetta(getFixedPricePayout(), o.getFixedPricePayout(), this::getOrCreateFixedPricePayout);
			merger.mergeRosetta(getSecurityPayout(), o.getSecurityPayout(), this::getOrCreateSecurityPayout);
			merger.mergeRosetta(getCashflow(), o.getCashflow(), this::getOrCreateCashflow);
			merger.mergeRosetta(getPerformancePayout(), o.getPerformancePayout(), this::getOrCreatePerformancePayout);
			merger.mergeRosetta(getAssetPayout(), o.getAssetPayout(), this::getOrCreateAssetPayout);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Payout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interestRatePayout, _that.getInterestRatePayout())) return false;
			if (!Objects.equals(creditDefaultPayout, _that.getCreditDefaultPayout())) return false;
			if (!ListEquals.listEquals(optionPayout, _that.getOptionPayout())) return false;
			if (!ListEquals.listEquals(commodityPayout, _that.getCommodityPayout())) return false;
			if (!ListEquals.listEquals(forwardPayout, _that.getForwardPayout())) return false;
			if (!ListEquals.listEquals(fixedPricePayout, _that.getFixedPricePayout())) return false;
			if (!ListEquals.listEquals(securityPayout, _that.getSecurityPayout())) return false;
			if (!ListEquals.listEquals(cashflow, _that.getCashflow())) return false;
			if (!ListEquals.listEquals(performancePayout, _that.getPerformancePayout())) return false;
			if (!ListEquals.listEquals(assetPayout, _that.getAssetPayout())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestRatePayout != null ? interestRatePayout.hashCode() : 0);
			_result = 31 * _result + (creditDefaultPayout != null ? creditDefaultPayout.hashCode() : 0);
			_result = 31 * _result + (optionPayout != null ? optionPayout.hashCode() : 0);
			_result = 31 * _result + (commodityPayout != null ? commodityPayout.hashCode() : 0);
			_result = 31 * _result + (forwardPayout != null ? forwardPayout.hashCode() : 0);
			_result = 31 * _result + (fixedPricePayout != null ? fixedPricePayout.hashCode() : 0);
			_result = 31 * _result + (securityPayout != null ? securityPayout.hashCode() : 0);
			_result = 31 * _result + (cashflow != null ? cashflow.hashCode() : 0);
			_result = 31 * _result + (performancePayout != null ? performancePayout.hashCode() : 0);
			_result = 31 * _result + (assetPayout != null ? assetPayout.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PayoutBuilder {" +
				"interestRatePayout=" + this.interestRatePayout + ", " +
				"creditDefaultPayout=" + this.creditDefaultPayout + ", " +
				"optionPayout=" + this.optionPayout + ", " +
				"commodityPayout=" + this.commodityPayout + ", " +
				"forwardPayout=" + this.forwardPayout + ", " +
				"fixedPricePayout=" + this.fixedPricePayout + ", " +
				"securityPayout=" + this.securityPayout + ", " +
				"cashflow=" + this.cashflow + ", " +
				"performancePayout=" + this.performancePayout + ", " +
				"assetPayout=" + this.assetPayout + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
