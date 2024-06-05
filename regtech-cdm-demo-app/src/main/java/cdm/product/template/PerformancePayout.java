package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.ValuationDates;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.FxFeature;
import cdm.product.template.PerformancePayout;
import cdm.product.template.PerformancePayout.PerformancePayoutBuilder;
import cdm.product.template.PerformancePayout.PerformancePayoutBuilderImpl;
import cdm.product.template.PerformancePayout.PerformancePayoutImpl;
import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import cdm.product.template.meta.PerformancePayoutMeta;
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
 * Contains the necessary specifications for all performance payouts, encompassing equity return, dividend, variance, volatility and correlation products. The Performance Payout is either a straight return, or an aggregation of multiple returns, hence a required choice between returnTerms and multiple portfolioReturnTerms.
 * @version ${project.version}
 */
@RosettaDataType(value="PerformancePayout", builder=PerformancePayout.PerformancePayoutBuilderImpl.class, version="${project.version}")
public interface PerformancePayout extends PayoutBase, GlobalKey {

	PerformancePayoutMeta metaData = new PerformancePayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines how and when a performance type option or performance type swap is to be observed.
	 */
	ObservationTerms getObservationTerms();
	/**
	 * Defines how and when a performance type option or performance type swap is to be valued, including both interim and final valuation.
	 */
	ValuationDates getValuationDates();
	/**
	 * Defines the payment date schedule, as defined by the parameters that are needed to specify it, either in a parametric way or by reference to another schedule of dates (e.g. the valuation dates).
	 */
	PaymentDates getPaymentDates();
	/**
	 * Identifies the underlying product that is referenced for pricing of the applicable leg in a swap.  Referenced in the &#39;2018 ISDA CDM Equity Confirmation for Security Equity Swap&#39; as Security.
	 */
	Product getUnderlier();
	/**
	 * Defines quanto or composite FX features that are included in the swap leg.
	 */
	List<? extends FxFeature> getFxFeature();
	/**
	 * Specifies the type of return of a performance payout.
	 */
	ReturnTerms getReturnTerms();
	/**
	 * Specifies an individual type of return of a Performance Payout, when such individual return is part of an aggregation of multiple similar returns, at Performance Payout level
	 */
	List<? extends PortfolioReturnTerms> getPortfolioReturnTerms();
	/**
	 * Specifies the net initial valuation price(s) of the underlier at Performance Payout level. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getInitialValuationPrice();
	/**
	 * Specifies the net initial valuation price(s) of the underlier at Performance Payout level. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getInterimValuationPrice();
	/**
	 * Specifies the net final valuation price(s) of the underlier at Performance Payout level. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getFinalValuationPrice();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PerformancePayout build();
	
	PerformancePayout.PerformancePayoutBuilder toBuilder();
	
	static PerformancePayout.PerformancePayoutBuilder builder() {
		return new PerformancePayout.PerformancePayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PerformancePayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PerformancePayout> getType() {
		return PerformancePayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.class, getObservationTerms());
		processRosetta(path.newSubPath("valuationDates"), processor, ValuationDates.class, getValuationDates());
		processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.class, getPaymentDates());
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
		processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.class, getFxFeature());
		processRosetta(path.newSubPath("returnTerms"), processor, ReturnTerms.class, getReturnTerms());
		processRosetta(path.newSubPath("portfolioReturnTerms"), processor, PortfolioReturnTerms.class, getPortfolioReturnTerms());
		processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInitialValuationPrice());
		processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInterimValuationPrice());
		processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getFinalValuationPrice());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PerformancePayoutBuilder extends PerformancePayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms();
		ObservationTerms.ObservationTermsBuilder getObservationTerms();
		ValuationDates.ValuationDatesBuilder getOrCreateValuationDates();
		ValuationDates.ValuationDatesBuilder getValuationDates();
		PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates();
		PaymentDates.PaymentDatesBuilder getPaymentDates();
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		FxFeature.FxFeatureBuilder getOrCreateFxFeature(int _index);
		List<? extends FxFeature.FxFeatureBuilder> getFxFeature();
		ReturnTerms.ReturnTermsBuilder getOrCreateReturnTerms();
		ReturnTerms.ReturnTermsBuilder getReturnTerms();
		PortfolioReturnTerms.PortfolioReturnTermsBuilder getOrCreatePortfolioReturnTerms(int _index);
		List<? extends PortfolioReturnTerms.PortfolioReturnTermsBuilder> getPortfolioReturnTerms();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInitialValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInterimValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getFinalValuationPrice();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PerformancePayout.PerformancePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		PerformancePayout.PerformancePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		PerformancePayout.PerformancePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		PerformancePayout.PerformancePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		PerformancePayout.PerformancePayoutBuilder setObservationTerms(ObservationTerms observationTerms);
		PerformancePayout.PerformancePayoutBuilder setValuationDates(ValuationDates valuationDates);
		PerformancePayout.PerformancePayoutBuilder setPaymentDates(PaymentDates paymentDates);
		PerformancePayout.PerformancePayoutBuilder setUnderlier(Product underlier);
		PerformancePayout.PerformancePayoutBuilder addFxFeature(FxFeature fxFeature0);
		PerformancePayout.PerformancePayoutBuilder addFxFeature(FxFeature fxFeature1, int _idx);
		PerformancePayout.PerformancePayoutBuilder addFxFeature(List<? extends FxFeature> fxFeature2);
		PerformancePayout.PerformancePayoutBuilder setFxFeature(List<? extends FxFeature> fxFeature3);
		PerformancePayout.PerformancePayoutBuilder setReturnTerms(ReturnTerms returnTerms);
		PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(PortfolioReturnTerms portfolioReturnTerms0);
		PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(PortfolioReturnTerms portfolioReturnTerms1, int _idx);
		PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(List<? extends PortfolioReturnTerms> portfolioReturnTerms2);
		PerformancePayout.PerformancePayoutBuilder setPortfolioReturnTerms(List<? extends PortfolioReturnTerms> portfolioReturnTerms3);
		PerformancePayout.PerformancePayoutBuilder setInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice0);
		PerformancePayout.PerformancePayoutBuilder setInitialValuationPriceValue(PriceSchedule initialValuationPrice1);
		PerformancePayout.PerformancePayoutBuilder setInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice0);
		PerformancePayout.PerformancePayoutBuilder setInterimValuationPriceValue(PriceSchedule interimValuationPrice1);
		PerformancePayout.PerformancePayoutBuilder setFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice0);
		PerformancePayout.PerformancePayoutBuilder setFinalValuationPriceValue(PriceSchedule finalValuationPrice1);
		PerformancePayout.PerformancePayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.ObservationTermsBuilder.class, getObservationTerms());
			processRosetta(path.newSubPath("valuationDates"), processor, ValuationDates.ValuationDatesBuilder.class, getValuationDates());
			processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.PaymentDatesBuilder.class, getPaymentDates());
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
			processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.FxFeatureBuilder.class, getFxFeature());
			processRosetta(path.newSubPath("returnTerms"), processor, ReturnTerms.ReturnTermsBuilder.class, getReturnTerms());
			processRosetta(path.newSubPath("portfolioReturnTerms"), processor, PortfolioReturnTerms.PortfolioReturnTermsBuilder.class, getPortfolioReturnTerms());
			processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInitialValuationPrice());
			processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInterimValuationPrice());
			processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getFinalValuationPrice());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PerformancePayout.PerformancePayoutBuilder prune();
	}

	/*********************** Immutable Implementation of PerformancePayout  ***********************/
	class PerformancePayoutImpl extends PayoutBase.PayoutBaseImpl implements PerformancePayout {
		private final ObservationTerms observationTerms;
		private final ValuationDates valuationDates;
		private final PaymentDates paymentDates;
		private final Product underlier;
		private final List<? extends FxFeature> fxFeature;
		private final ReturnTerms returnTerms;
		private final List<? extends PortfolioReturnTerms> portfolioReturnTerms;
		private final ReferenceWithMetaPriceSchedule initialValuationPrice;
		private final ReferenceWithMetaPriceSchedule interimValuationPrice;
		private final ReferenceWithMetaPriceSchedule finalValuationPrice;
		private final MetaFields meta;
		
		protected PerformancePayoutImpl(PerformancePayout.PerformancePayoutBuilder builder) {
			super(builder);
			this.observationTerms = ofNullable(builder.getObservationTerms()).map(f->f.build()).orElse(null);
			this.valuationDates = ofNullable(builder.getValuationDates()).map(f->f.build()).orElse(null);
			this.paymentDates = ofNullable(builder.getPaymentDates()).map(f->f.build()).orElse(null);
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
			this.fxFeature = ofNullable(builder.getFxFeature()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.returnTerms = ofNullable(builder.getReturnTerms()).map(f->f.build()).orElse(null);
			this.portfolioReturnTerms = ofNullable(builder.getPortfolioReturnTerms()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.initialValuationPrice = ofNullable(builder.getInitialValuationPrice()).map(f->f.build()).orElse(null);
			this.interimValuationPrice = ofNullable(builder.getInterimValuationPrice()).map(f->f.build()).orElse(null);
			this.finalValuationPrice = ofNullable(builder.getFinalValuationPrice()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		@RosettaAttribute("valuationDates")
		public ValuationDates getValuationDates() {
			return valuationDates;
		}
		
		@Override
		@RosettaAttribute("paymentDates")
		public PaymentDates getPaymentDates() {
			return paymentDates;
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product getUnderlier() {
			return underlier;
		}
		
		@Override
		@RosettaAttribute("fxFeature")
		public List<? extends FxFeature> getFxFeature() {
			return fxFeature;
		}
		
		@Override
		@RosettaAttribute("returnTerms")
		public ReturnTerms getReturnTerms() {
			return returnTerms;
		}
		
		@Override
		@RosettaAttribute("portfolioReturnTerms")
		public List<? extends PortfolioReturnTerms> getPortfolioReturnTerms() {
			return portfolioReturnTerms;
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public ReferenceWithMetaPriceSchedule getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		@Override
		@RosettaAttribute("interimValuationPrice")
		public ReferenceWithMetaPriceSchedule getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		@Override
		@RosettaAttribute("finalValuationPrice")
		public ReferenceWithMetaPriceSchedule getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PerformancePayout build() {
			return this;
		}
		
		@Override
		public PerformancePayout.PerformancePayoutBuilder toBuilder() {
			PerformancePayout.PerformancePayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PerformancePayout.PerformancePayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getObservationTerms()).ifPresent(builder::setObservationTerms);
			ofNullable(getValuationDates()).ifPresent(builder::setValuationDates);
			ofNullable(getPaymentDates()).ifPresent(builder::setPaymentDates);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
			ofNullable(getFxFeature()).ifPresent(builder::setFxFeature);
			ofNullable(getReturnTerms()).ifPresent(builder::setReturnTerms);
			ofNullable(getPortfolioReturnTerms()).ifPresent(builder::setPortfolioReturnTerms);
			ofNullable(getInitialValuationPrice()).ifPresent(builder::setInitialValuationPrice);
			ofNullable(getInterimValuationPrice()).ifPresent(builder::setInterimValuationPrice);
			ofNullable(getFinalValuationPrice()).ifPresent(builder::setFinalValuationPrice);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			PerformancePayout _that = getType().cast(o);
		
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!ListEquals.listEquals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(returnTerms, _that.getReturnTerms())) return false;
			if (!ListEquals.listEquals(portfolioReturnTerms, _that.getPortfolioReturnTerms())) return false;
			if (!Objects.equals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!Objects.equals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!Objects.equals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (returnTerms != null ? returnTerms.hashCode() : 0);
			_result = 31 * _result + (portfolioReturnTerms != null ? portfolioReturnTerms.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PerformancePayout {" +
				"observationTerms=" + this.observationTerms + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"underlier=" + this.underlier + ", " +
				"fxFeature=" + this.fxFeature + ", " +
				"returnTerms=" + this.returnTerms + ", " +
				"portfolioReturnTerms=" + this.portfolioReturnTerms + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of PerformancePayout  ***********************/
	class PerformancePayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements PerformancePayout.PerformancePayoutBuilder, GlobalKeyBuilder {
	
		protected ObservationTerms.ObservationTermsBuilder observationTerms;
		protected ValuationDates.ValuationDatesBuilder valuationDates;
		protected PaymentDates.PaymentDatesBuilder paymentDates;
		protected Product.ProductBuilder underlier;
		protected List<FxFeature.FxFeatureBuilder> fxFeature = new ArrayList<>();
		protected ReturnTerms.ReturnTermsBuilder returnTerms;
		protected List<PortfolioReturnTerms.PortfolioReturnTermsBuilder> portfolioReturnTerms = new ArrayList<>();
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder initialValuationPrice;
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder interimValuationPrice;
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder finalValuationPrice;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PerformancePayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms.ObservationTermsBuilder getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		public ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms() {
			ObservationTerms.ObservationTermsBuilder result;
			if (observationTerms!=null) {
				result = observationTerms;
			}
			else {
				result = observationTerms = ObservationTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDates")
		public ValuationDates.ValuationDatesBuilder getValuationDates() {
			return valuationDates;
		}
		
		@Override
		public ValuationDates.ValuationDatesBuilder getOrCreateValuationDates() {
			ValuationDates.ValuationDatesBuilder result;
			if (valuationDates!=null) {
				result = valuationDates;
			}
			else {
				result = valuationDates = ValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public PaymentDates.PaymentDatesBuilder getPaymentDates() {
			return paymentDates;
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates() {
			PaymentDates.PaymentDatesBuilder result;
			if (paymentDates!=null) {
				result = paymentDates;
			}
			else {
				result = paymentDates = PaymentDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("underlier")
		public Product.ProductBuilder getUnderlier() {
			return underlier;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateUnderlier() {
			Product.ProductBuilder result;
			if (underlier!=null) {
				result = underlier;
			}
			else {
				result = underlier = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fxFeature")
		public List<? extends FxFeature.FxFeatureBuilder> getFxFeature() {
			return fxFeature;
		}
		
		public FxFeature.FxFeatureBuilder getOrCreateFxFeature(int _index) {
		
			if (fxFeature==null) {
				this.fxFeature = new ArrayList<>();
			}
			FxFeature.FxFeatureBuilder result;
			return getIndex(fxFeature, _index, () -> {
						FxFeature.FxFeatureBuilder newFxFeature = FxFeature.builder();
						return newFxFeature;
					});
		}
		
		@Override
		@RosettaAttribute("returnTerms")
		public ReturnTerms.ReturnTermsBuilder getReturnTerms() {
			return returnTerms;
		}
		
		@Override
		public ReturnTerms.ReturnTermsBuilder getOrCreateReturnTerms() {
			ReturnTerms.ReturnTermsBuilder result;
			if (returnTerms!=null) {
				result = returnTerms;
			}
			else {
				result = returnTerms = ReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("portfolioReturnTerms")
		public List<? extends PortfolioReturnTerms.PortfolioReturnTermsBuilder> getPortfolioReturnTerms() {
			return portfolioReturnTerms;
		}
		
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder getOrCreatePortfolioReturnTerms(int _index) {
		
			if (portfolioReturnTerms==null) {
				this.portfolioReturnTerms = new ArrayList<>();
			}
			PortfolioReturnTerms.PortfolioReturnTermsBuilder result;
			return getIndex(portfolioReturnTerms, _index, () -> {
						PortfolioReturnTerms.PortfolioReturnTermsBuilder newPortfolioReturnTerms = PortfolioReturnTerms.builder();
						return newPortfolioReturnTerms;
					});
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (initialValuationPrice!=null) {
				result = initialValuationPrice;
			}
			else {
				result = initialValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interimValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (interimValuationPrice!=null) {
				result = interimValuationPrice;
			}
			else {
				result = interimValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (finalValuationPrice!=null) {
				result = finalValuationPrice;
			}
			else {
				result = finalValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
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
		@RosettaAttribute("payerReceiver")
		public PerformancePayout.PerformancePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public PerformancePayout.PerformancePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public PerformancePayout.PerformancePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public PerformancePayout.PerformancePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationTerms")
		public PerformancePayout.PerformancePayoutBuilder setObservationTerms(ObservationTerms observationTerms) {
			this.observationTerms = observationTerms==null?null:observationTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDates")
		public PerformancePayout.PerformancePayoutBuilder setValuationDates(ValuationDates valuationDates) {
			this.valuationDates = valuationDates==null?null:valuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public PerformancePayout.PerformancePayoutBuilder setPaymentDates(PaymentDates paymentDates) {
			this.paymentDates = paymentDates==null?null:paymentDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public PerformancePayout.PerformancePayoutBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		@Override
		public PerformancePayout.PerformancePayoutBuilder addFxFeature(FxFeature fxFeature) {
			if (fxFeature!=null) this.fxFeature.add(fxFeature.toBuilder());
			return this;
		}
		
		@Override
		public PerformancePayout.PerformancePayoutBuilder addFxFeature(FxFeature fxFeature, int _idx) {
			getIndex(this.fxFeature, _idx, () -> fxFeature.toBuilder());
			return this;
		}
		@Override 
		public PerformancePayout.PerformancePayoutBuilder addFxFeature(List<? extends FxFeature> fxFeatures) {
			if (fxFeatures != null) {
				for (FxFeature toAdd : fxFeatures) {
					this.fxFeature.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("fxFeature")
		public PerformancePayout.PerformancePayoutBuilder setFxFeature(List<? extends FxFeature> fxFeatures) {
			if (fxFeatures == null)  {
				this.fxFeature = new ArrayList<>();
			}
			else {
				this.fxFeature = fxFeatures.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("returnTerms")
		public PerformancePayout.PerformancePayoutBuilder setReturnTerms(ReturnTerms returnTerms) {
			this.returnTerms = returnTerms==null?null:returnTerms.toBuilder();
			return this;
		}
		@Override
		public PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(PortfolioReturnTerms portfolioReturnTerms) {
			if (portfolioReturnTerms!=null) this.portfolioReturnTerms.add(portfolioReturnTerms.toBuilder());
			return this;
		}
		
		@Override
		public PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(PortfolioReturnTerms portfolioReturnTerms, int _idx) {
			getIndex(this.portfolioReturnTerms, _idx, () -> portfolioReturnTerms.toBuilder());
			return this;
		}
		@Override 
		public PerformancePayout.PerformancePayoutBuilder addPortfolioReturnTerms(List<? extends PortfolioReturnTerms> portfolioReturnTermss) {
			if (portfolioReturnTermss != null) {
				for (PortfolioReturnTerms toAdd : portfolioReturnTermss) {
					this.portfolioReturnTerms.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("portfolioReturnTerms")
		public PerformancePayout.PerformancePayoutBuilder setPortfolioReturnTerms(List<? extends PortfolioReturnTerms> portfolioReturnTermss) {
			if (portfolioReturnTermss == null)  {
				this.portfolioReturnTerms = new ArrayList<>();
			}
			else {
				this.portfolioReturnTerms = portfolioReturnTermss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public PerformancePayout.PerformancePayoutBuilder setInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice) {
			this.initialValuationPrice = initialValuationPrice==null?null:initialValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PerformancePayout.PerformancePayoutBuilder setInitialValuationPriceValue(PriceSchedule initialValuationPrice) {
			this.getOrCreateInitialValuationPrice().setValue(initialValuationPrice);
			return this;
		}
		@Override
		@RosettaAttribute("interimValuationPrice")
		public PerformancePayout.PerformancePayoutBuilder setInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice) {
			this.interimValuationPrice = interimValuationPrice==null?null:interimValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PerformancePayout.PerformancePayoutBuilder setInterimValuationPriceValue(PriceSchedule interimValuationPrice) {
			this.getOrCreateInterimValuationPrice().setValue(interimValuationPrice);
			return this;
		}
		@Override
		@RosettaAttribute("finalValuationPrice")
		public PerformancePayout.PerformancePayoutBuilder setFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice) {
			this.finalValuationPrice = finalValuationPrice==null?null:finalValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PerformancePayout.PerformancePayoutBuilder setFinalValuationPriceValue(PriceSchedule finalValuationPrice) {
			this.getOrCreateFinalValuationPrice().setValue(finalValuationPrice);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PerformancePayout.PerformancePayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PerformancePayout build() {
			return new PerformancePayout.PerformancePayoutImpl(this);
		}
		
		@Override
		public PerformancePayout.PerformancePayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PerformancePayout.PerformancePayoutBuilder prune() {
			super.prune();
			if (observationTerms!=null && !observationTerms.prune().hasData()) observationTerms = null;
			if (valuationDates!=null && !valuationDates.prune().hasData()) valuationDates = null;
			if (paymentDates!=null && !paymentDates.prune().hasData()) paymentDates = null;
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			fxFeature = fxFeature.stream().filter(b->b!=null).<FxFeature.FxFeatureBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (returnTerms!=null && !returnTerms.prune().hasData()) returnTerms = null;
			portfolioReturnTerms = portfolioReturnTerms.stream().filter(b->b!=null).<PortfolioReturnTerms.PortfolioReturnTermsBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (initialValuationPrice!=null && !initialValuationPrice.prune().hasData()) initialValuationPrice = null;
			if (interimValuationPrice!=null && !interimValuationPrice.prune().hasData()) interimValuationPrice = null;
			if (finalValuationPrice!=null && !finalValuationPrice.prune().hasData()) finalValuationPrice = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getObservationTerms()!=null && getObservationTerms().hasData()) return true;
			if (getValuationDates()!=null && getValuationDates().hasData()) return true;
			if (getPaymentDates()!=null && getPaymentDates().hasData()) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			if (getFxFeature()!=null && getFxFeature().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getReturnTerms()!=null && getReturnTerms().hasData()) return true;
			if (getPortfolioReturnTerms()!=null && getPortfolioReturnTerms().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getInitialValuationPrice()!=null && getInitialValuationPrice().hasData()) return true;
			if (getInterimValuationPrice()!=null && getInterimValuationPrice().hasData()) return true;
			if (getFinalValuationPrice()!=null && getFinalValuationPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PerformancePayout.PerformancePayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			PerformancePayout.PerformancePayoutBuilder o = (PerformancePayout.PerformancePayoutBuilder) other;
			
			merger.mergeRosetta(getObservationTerms(), o.getObservationTerms(), this::setObservationTerms);
			merger.mergeRosetta(getValuationDates(), o.getValuationDates(), this::setValuationDates);
			merger.mergeRosetta(getPaymentDates(), o.getPaymentDates(), this::setPaymentDates);
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			merger.mergeRosetta(getFxFeature(), o.getFxFeature(), this::getOrCreateFxFeature);
			merger.mergeRosetta(getReturnTerms(), o.getReturnTerms(), this::setReturnTerms);
			merger.mergeRosetta(getPortfolioReturnTerms(), o.getPortfolioReturnTerms(), this::getOrCreatePortfolioReturnTerms);
			merger.mergeRosetta(getInitialValuationPrice(), o.getInitialValuationPrice(), this::setInitialValuationPrice);
			merger.mergeRosetta(getInterimValuationPrice(), o.getInterimValuationPrice(), this::setInterimValuationPrice);
			merger.mergeRosetta(getFinalValuationPrice(), o.getFinalValuationPrice(), this::setFinalValuationPrice);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			PerformancePayout _that = getType().cast(o);
		
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!ListEquals.listEquals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(returnTerms, _that.getReturnTerms())) return false;
			if (!ListEquals.listEquals(portfolioReturnTerms, _that.getPortfolioReturnTerms())) return false;
			if (!Objects.equals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!Objects.equals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!Objects.equals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (returnTerms != null ? returnTerms.hashCode() : 0);
			_result = 31 * _result + (portfolioReturnTerms != null ? portfolioReturnTerms.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PerformancePayoutBuilder {" +
				"observationTerms=" + this.observationTerms + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"underlier=" + this.underlier + ", " +
				"fxFeature=" + this.fxFeature + ", " +
				"returnTerms=" + this.returnTerms + ", " +
				"portfolioReturnTerms=" + this.portfolioReturnTerms + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
