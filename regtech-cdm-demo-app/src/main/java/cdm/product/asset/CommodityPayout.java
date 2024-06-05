package cdm.product.asset;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.CommodityPayout;
import cdm.product.asset.CommodityPayout.CommodityPayoutBuilder;
import cdm.product.asset.CommodityPayout.CommodityPayoutBuilderImpl;
import cdm.product.asset.CommodityPayout.CommodityPayoutImpl;
import cdm.product.asset.meta.CommodityPayoutMeta;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.CommodityPriceReturnTerms;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PricingDates;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AveragingCalculation;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.FxFeature;
import cdm.product.template.Product;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Payout based on the averaged price of a referenced underlier. (e.g. Commodities). Can represent both average (average of many) &amp; bullet (average of 1) pricing
 * @version ${project.version}
 */
@RosettaDataType(value="CommodityPayout", builder=CommodityPayout.CommodityPayoutBuilderImpl.class, version="${project.version}")
public interface CommodityPayout extends PayoutBase, GlobalKey {

	CommodityPayoutMeta metaData = new CommodityPayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates if the averaging calculation, when applicable, is weighted or unweighted.
	 */
	AveragingCalculation getAveragingFeature();
	/**
	 * Defines parameters in which the commodity price is assessed.
	 */
	CommodityPriceReturnTerms getCommodityPriceReturnTerms();
	/**
	 * Specifies specific dates or parametric rules for the dates on which the price will be determined.
	 */
	PricingDates getPricingDates();
	/**
	 * Allows the full representation of a payout by defining a set of schedule periods. It supports standard schedule customization by expressing all the dates, quantities, and pricing data in a non-parametric way.
	 */
	CalculationSchedule getSchedule();
	/**
	 * Defines the calculation period dates schedule.
	 */
	CalculationPeriodDates getCalculationPeriodDates();
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
	FxFeature getFxFeature();
	/**
	 * Contains the information relative to the delivery of the asset.
	 */
	AssetDeliveryInformation getDelivery();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CommodityPayout build();
	
	CommodityPayout.CommodityPayoutBuilder toBuilder();
	
	static CommodityPayout.CommodityPayoutBuilder builder() {
		return new CommodityPayout.CommodityPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CommodityPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CommodityPayout> getType() {
		return CommodityPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("averagingFeature"), processor, AveragingCalculation.class, getAveragingFeature());
		processRosetta(path.newSubPath("commodityPriceReturnTerms"), processor, CommodityPriceReturnTerms.class, getCommodityPriceReturnTerms());
		processRosetta(path.newSubPath("pricingDates"), processor, PricingDates.class, getPricingDates());
		processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.class, getSchedule());
		processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.class, getCalculationPeriodDates());
		processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.class, getPaymentDates());
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
		processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.class, getFxFeature());
		processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.class, getDelivery());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CommodityPayoutBuilder extends CommodityPayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingFeature();
		AveragingCalculation.AveragingCalculationBuilder getAveragingFeature();
		CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder getOrCreateCommodityPriceReturnTerms();
		CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder getCommodityPriceReturnTerms();
		PricingDates.PricingDatesBuilder getOrCreatePricingDates();
		PricingDates.PricingDatesBuilder getPricingDates();
		CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule();
		CalculationSchedule.CalculationScheduleBuilder getSchedule();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates();
		PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates();
		PaymentDates.PaymentDatesBuilder getPaymentDates();
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		FxFeature.FxFeatureBuilder getOrCreateFxFeature();
		FxFeature.FxFeatureBuilder getFxFeature();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getOrCreateDelivery();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getDelivery();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CommodityPayout.CommodityPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		CommodityPayout.CommodityPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		CommodityPayout.CommodityPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		CommodityPayout.CommodityPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		CommodityPayout.CommodityPayoutBuilder setAveragingFeature(AveragingCalculation averagingFeature);
		CommodityPayout.CommodityPayoutBuilder setCommodityPriceReturnTerms(CommodityPriceReturnTerms commodityPriceReturnTerms);
		CommodityPayout.CommodityPayoutBuilder setPricingDates(PricingDates pricingDates);
		CommodityPayout.CommodityPayoutBuilder setSchedule(CalculationSchedule schedule);
		CommodityPayout.CommodityPayoutBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates);
		CommodityPayout.CommodityPayoutBuilder setPaymentDates(PaymentDates paymentDates);
		CommodityPayout.CommodityPayoutBuilder setUnderlier(Product underlier);
		CommodityPayout.CommodityPayoutBuilder setFxFeature(FxFeature fxFeature);
		CommodityPayout.CommodityPayoutBuilder setDelivery(AssetDeliveryInformation delivery);
		CommodityPayout.CommodityPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("averagingFeature"), processor, AveragingCalculation.AveragingCalculationBuilder.class, getAveragingFeature());
			processRosetta(path.newSubPath("commodityPriceReturnTerms"), processor, CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder.class, getCommodityPriceReturnTerms());
			processRosetta(path.newSubPath("pricingDates"), processor, PricingDates.PricingDatesBuilder.class, getPricingDates());
			processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.CalculationScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.CalculationPeriodDatesBuilder.class, getCalculationPeriodDates());
			processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.PaymentDatesBuilder.class, getPaymentDates());
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
			processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.FxFeatureBuilder.class, getFxFeature());
			processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.AssetDeliveryInformationBuilder.class, getDelivery());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CommodityPayout.CommodityPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of CommodityPayout  ***********************/
	class CommodityPayoutImpl extends PayoutBase.PayoutBaseImpl implements CommodityPayout {
		private final AveragingCalculation averagingFeature;
		private final CommodityPriceReturnTerms commodityPriceReturnTerms;
		private final PricingDates pricingDates;
		private final CalculationSchedule schedule;
		private final CalculationPeriodDates calculationPeriodDates;
		private final PaymentDates paymentDates;
		private final Product underlier;
		private final FxFeature fxFeature;
		private final AssetDeliveryInformation delivery;
		private final MetaFields meta;
		
		protected CommodityPayoutImpl(CommodityPayout.CommodityPayoutBuilder builder) {
			super(builder);
			this.averagingFeature = ofNullable(builder.getAveragingFeature()).map(f->f.build()).orElse(null);
			this.commodityPriceReturnTerms = ofNullable(builder.getCommodityPriceReturnTerms()).map(f->f.build()).orElse(null);
			this.pricingDates = ofNullable(builder.getPricingDates()).map(f->f.build()).orElse(null);
			this.schedule = ofNullable(builder.getSchedule()).map(f->f.build()).orElse(null);
			this.calculationPeriodDates = ofNullable(builder.getCalculationPeriodDates()).map(f->f.build()).orElse(null);
			this.paymentDates = ofNullable(builder.getPaymentDates()).map(f->f.build()).orElse(null);
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
			this.fxFeature = ofNullable(builder.getFxFeature()).map(f->f.build()).orElse(null);
			this.delivery = ofNullable(builder.getDelivery()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("averagingFeature")
		public AveragingCalculation getAveragingFeature() {
			return averagingFeature;
		}
		
		@Override
		@RosettaAttribute("commodityPriceReturnTerms")
		public CommodityPriceReturnTerms getCommodityPriceReturnTerms() {
			return commodityPriceReturnTerms;
		}
		
		@Override
		@RosettaAttribute("pricingDates")
		public PricingDates getPricingDates() {
			return pricingDates;
		}
		
		@Override
		@RosettaAttribute("schedule")
		public CalculationSchedule getSchedule() {
			return schedule;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates getCalculationPeriodDates() {
			return calculationPeriodDates;
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
		public FxFeature getFxFeature() {
			return fxFeature;
		}
		
		@Override
		@RosettaAttribute("delivery")
		public AssetDeliveryInformation getDelivery() {
			return delivery;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CommodityPayout build() {
			return this;
		}
		
		@Override
		public CommodityPayout.CommodityPayoutBuilder toBuilder() {
			CommodityPayout.CommodityPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CommodityPayout.CommodityPayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getAveragingFeature()).ifPresent(builder::setAveragingFeature);
			ofNullable(getCommodityPriceReturnTerms()).ifPresent(builder::setCommodityPriceReturnTerms);
			ofNullable(getPricingDates()).ifPresent(builder::setPricingDates);
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getCalculationPeriodDates()).ifPresent(builder::setCalculationPeriodDates);
			ofNullable(getPaymentDates()).ifPresent(builder::setPaymentDates);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
			ofNullable(getFxFeature()).ifPresent(builder::setFxFeature);
			ofNullable(getDelivery()).ifPresent(builder::setDelivery);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CommodityPayout _that = getType().cast(o);
		
			if (!Objects.equals(averagingFeature, _that.getAveragingFeature())) return false;
			if (!Objects.equals(commodityPriceReturnTerms, _that.getCommodityPriceReturnTerms())) return false;
			if (!Objects.equals(pricingDates, _that.getPricingDates())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (averagingFeature != null ? averagingFeature.hashCode() : 0);
			_result = 31 * _result + (commodityPriceReturnTerms != null ? commodityPriceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (pricingDates != null ? pricingDates.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityPayout {" +
				"averagingFeature=" + this.averagingFeature + ", " +
				"commodityPriceReturnTerms=" + this.commodityPriceReturnTerms + ", " +
				"pricingDates=" + this.pricingDates + ", " +
				"schedule=" + this.schedule + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"underlier=" + this.underlier + ", " +
				"fxFeature=" + this.fxFeature + ", " +
				"delivery=" + this.delivery + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CommodityPayout  ***********************/
	class CommodityPayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements CommodityPayout.CommodityPayoutBuilder, GlobalKeyBuilder {
	
		protected AveragingCalculation.AveragingCalculationBuilder averagingFeature;
		protected CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder commodityPriceReturnTerms;
		protected PricingDates.PricingDatesBuilder pricingDates;
		protected CalculationSchedule.CalculationScheduleBuilder schedule;
		protected CalculationPeriodDates.CalculationPeriodDatesBuilder calculationPeriodDates;
		protected PaymentDates.PaymentDatesBuilder paymentDates;
		protected Product.ProductBuilder underlier;
		protected FxFeature.FxFeatureBuilder fxFeature;
		protected AssetDeliveryInformation.AssetDeliveryInformationBuilder delivery;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CommodityPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("averagingFeature")
		public AveragingCalculation.AveragingCalculationBuilder getAveragingFeature() {
			return averagingFeature;
		}
		
		@Override
		public AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingFeature() {
			AveragingCalculation.AveragingCalculationBuilder result;
			if (averagingFeature!=null) {
				result = averagingFeature;
			}
			else {
				result = averagingFeature = AveragingCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commodityPriceReturnTerms")
		public CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder getCommodityPriceReturnTerms() {
			return commodityPriceReturnTerms;
		}
		
		@Override
		public CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder getOrCreateCommodityPriceReturnTerms() {
			CommodityPriceReturnTerms.CommodityPriceReturnTermsBuilder result;
			if (commodityPriceReturnTerms!=null) {
				result = commodityPriceReturnTerms;
			}
			else {
				result = commodityPriceReturnTerms = CommodityPriceReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("pricingDates")
		public PricingDates.PricingDatesBuilder getPricingDates() {
			return pricingDates;
		}
		
		@Override
		public PricingDates.PricingDatesBuilder getOrCreatePricingDates() {
			PricingDates.PricingDatesBuilder result;
			if (pricingDates!=null) {
				result = pricingDates;
			}
			else {
				result = pricingDates = PricingDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("schedule")
		public CalculationSchedule.CalculationScheduleBuilder getSchedule() {
			return schedule;
		}
		
		@Override
		public CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule() {
			CalculationSchedule.CalculationScheduleBuilder result;
			if (schedule!=null) {
				result = schedule;
			}
			else {
				result = schedule = CalculationSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates() {
			return calculationPeriodDates;
		}
		
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates() {
			CalculationPeriodDates.CalculationPeriodDatesBuilder result;
			if (calculationPeriodDates!=null) {
				result = calculationPeriodDates;
			}
			else {
				result = calculationPeriodDates = CalculationPeriodDates.builder();
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
		public FxFeature.FxFeatureBuilder getFxFeature() {
			return fxFeature;
		}
		
		@Override
		public FxFeature.FxFeatureBuilder getOrCreateFxFeature() {
			FxFeature.FxFeatureBuilder result;
			if (fxFeature!=null) {
				result = fxFeature;
			}
			else {
				result = fxFeature = FxFeature.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("delivery")
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder getDelivery() {
			return delivery;
		}
		
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder getOrCreateDelivery() {
			AssetDeliveryInformation.AssetDeliveryInformationBuilder result;
			if (delivery!=null) {
				result = delivery;
			}
			else {
				result = delivery = AssetDeliveryInformation.builder();
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
		public CommodityPayout.CommodityPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public CommodityPayout.CommodityPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public CommodityPayout.CommodityPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public CommodityPayout.CommodityPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingFeature")
		public CommodityPayout.CommodityPayoutBuilder setAveragingFeature(AveragingCalculation averagingFeature) {
			this.averagingFeature = averagingFeature==null?null:averagingFeature.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commodityPriceReturnTerms")
		public CommodityPayout.CommodityPayoutBuilder setCommodityPriceReturnTerms(CommodityPriceReturnTerms commodityPriceReturnTerms) {
			this.commodityPriceReturnTerms = commodityPriceReturnTerms==null?null:commodityPriceReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("pricingDates")
		public CommodityPayout.CommodityPayoutBuilder setPricingDates(PricingDates pricingDates) {
			this.pricingDates = pricingDates==null?null:pricingDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("schedule")
		public CommodityPayout.CommodityPayoutBuilder setSchedule(CalculationSchedule schedule) {
			this.schedule = schedule==null?null:schedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CommodityPayout.CommodityPayoutBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates) {
			this.calculationPeriodDates = calculationPeriodDates==null?null:calculationPeriodDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public CommodityPayout.CommodityPayoutBuilder setPaymentDates(PaymentDates paymentDates) {
			this.paymentDates = paymentDates==null?null:paymentDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public CommodityPayout.CommodityPayoutBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fxFeature")
		public CommodityPayout.CommodityPayoutBuilder setFxFeature(FxFeature fxFeature) {
			this.fxFeature = fxFeature==null?null:fxFeature.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("delivery")
		public CommodityPayout.CommodityPayoutBuilder setDelivery(AssetDeliveryInformation delivery) {
			this.delivery = delivery==null?null:delivery.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CommodityPayout.CommodityPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CommodityPayout build() {
			return new CommodityPayout.CommodityPayoutImpl(this);
		}
		
		@Override
		public CommodityPayout.CommodityPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityPayout.CommodityPayoutBuilder prune() {
			super.prune();
			if (averagingFeature!=null && !averagingFeature.prune().hasData()) averagingFeature = null;
			if (commodityPriceReturnTerms!=null && !commodityPriceReturnTerms.prune().hasData()) commodityPriceReturnTerms = null;
			if (pricingDates!=null && !pricingDates.prune().hasData()) pricingDates = null;
			if (schedule!=null && !schedule.prune().hasData()) schedule = null;
			if (calculationPeriodDates!=null && !calculationPeriodDates.prune().hasData()) calculationPeriodDates = null;
			if (paymentDates!=null && !paymentDates.prune().hasData()) paymentDates = null;
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			if (fxFeature!=null && !fxFeature.prune().hasData()) fxFeature = null;
			if (delivery!=null && !delivery.prune().hasData()) delivery = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getAveragingFeature()!=null && getAveragingFeature().hasData()) return true;
			if (getCommodityPriceReturnTerms()!=null && getCommodityPriceReturnTerms().hasData()) return true;
			if (getPricingDates()!=null && getPricingDates().hasData()) return true;
			if (getSchedule()!=null && getSchedule().hasData()) return true;
			if (getCalculationPeriodDates()!=null && getCalculationPeriodDates().hasData()) return true;
			if (getPaymentDates()!=null && getPaymentDates().hasData()) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			if (getFxFeature()!=null && getFxFeature().hasData()) return true;
			if (getDelivery()!=null && getDelivery().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityPayout.CommodityPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CommodityPayout.CommodityPayoutBuilder o = (CommodityPayout.CommodityPayoutBuilder) other;
			
			merger.mergeRosetta(getAveragingFeature(), o.getAveragingFeature(), this::setAveragingFeature);
			merger.mergeRosetta(getCommodityPriceReturnTerms(), o.getCommodityPriceReturnTerms(), this::setCommodityPriceReturnTerms);
			merger.mergeRosetta(getPricingDates(), o.getPricingDates(), this::setPricingDates);
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::setSchedule);
			merger.mergeRosetta(getCalculationPeriodDates(), o.getCalculationPeriodDates(), this::setCalculationPeriodDates);
			merger.mergeRosetta(getPaymentDates(), o.getPaymentDates(), this::setPaymentDates);
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			merger.mergeRosetta(getFxFeature(), o.getFxFeature(), this::setFxFeature);
			merger.mergeRosetta(getDelivery(), o.getDelivery(), this::setDelivery);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CommodityPayout _that = getType().cast(o);
		
			if (!Objects.equals(averagingFeature, _that.getAveragingFeature())) return false;
			if (!Objects.equals(commodityPriceReturnTerms, _that.getCommodityPriceReturnTerms())) return false;
			if (!Objects.equals(pricingDates, _that.getPricingDates())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (averagingFeature != null ? averagingFeature.hashCode() : 0);
			_result = 31 * _result + (commodityPriceReturnTerms != null ? commodityPriceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (pricingDates != null ? pricingDates.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityPayoutBuilder {" +
				"averagingFeature=" + this.averagingFeature + ", " +
				"commodityPriceReturnTerms=" + this.commodityPriceReturnTerms + ", " +
				"pricingDates=" + this.pricingDates + ", " +
				"schedule=" + this.schedule + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"underlier=" + this.underlier + ", " +
				"fxFeature=" + this.fxFeature + ", " +
				"delivery=" + this.delivery + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
