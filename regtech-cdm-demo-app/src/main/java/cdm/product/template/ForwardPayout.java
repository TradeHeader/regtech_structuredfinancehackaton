package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.ForwardPayout;
import cdm.product.template.ForwardPayout.ForwardPayoutBuilder;
import cdm.product.template.ForwardPayout.ForwardPayoutBuilderImpl;
import cdm.product.template.ForwardPayout.ForwardPayoutImpl;
import cdm.product.template.Product;
import cdm.product.template.meta.ForwardPayoutMeta;
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
 * Represents a forward settling payout. The underlier attribute captures the underlying payout, which is settled according to the settlementTerms attribute (which is part of PayoutBase). Both FX Spot and FX Forward should use this component.
 * @version ${project.version}
 */
@RosettaDataType(value="ForwardPayout", builder=ForwardPayout.ForwardPayoutBuilderImpl.class, version="${project.version}")
public interface ForwardPayout extends PayoutBase, GlobalKey {

	ForwardPayoutMeta metaData = new ForwardPayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Underlying product that the forward is written on, which can be of any type: FX, a contractual product, a security, etc.
	 */
	Product getUnderlier();
	/**
	 * Also called contract month or delivery month. However, it&#39;s not always a month. It is usually expressed using a code, e.g. Z23 would be the Dec 2023 contract, (Z = December). For crude oil, the corresponding contract might be called CLZ23.
	 */
	String getDeliveryTerm();
	/**
	 * Contains the information relative to the delivery of the asset.
	 */
	AssetDeliveryInformation getDelivery();
	/**
	 * Allows the full representation of a payout by defining a set of schedule periods. It supports standard schedule customization by expressing all the dates, quantities, and pricing data in a non-parametric way.
	 */
	CalculationSchedule getSchedule();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ForwardPayout build();
	
	ForwardPayout.ForwardPayoutBuilder toBuilder();
	
	static ForwardPayout.ForwardPayoutBuilder builder() {
		return new ForwardPayout.ForwardPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ForwardPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ForwardPayout> getType() {
		return ForwardPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
		processor.processBasic(path.newSubPath("deliveryTerm"), String.class, getDeliveryTerm(), this);
		processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.class, getDelivery());
		processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.class, getSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ForwardPayoutBuilder extends ForwardPayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getOrCreateDelivery();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getDelivery();
		CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule();
		CalculationSchedule.CalculationScheduleBuilder getSchedule();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ForwardPayout.ForwardPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		ForwardPayout.ForwardPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		ForwardPayout.ForwardPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		ForwardPayout.ForwardPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		ForwardPayout.ForwardPayoutBuilder setUnderlier(Product underlier);
		ForwardPayout.ForwardPayoutBuilder setDeliveryTerm(String deliveryTerm);
		ForwardPayout.ForwardPayoutBuilder setDelivery(AssetDeliveryInformation delivery);
		ForwardPayout.ForwardPayoutBuilder setSchedule(CalculationSchedule schedule);
		ForwardPayout.ForwardPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
			processor.processBasic(path.newSubPath("deliveryTerm"), String.class, getDeliveryTerm(), this);
			processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.AssetDeliveryInformationBuilder.class, getDelivery());
			processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.CalculationScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ForwardPayout.ForwardPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of ForwardPayout  ***********************/
	class ForwardPayoutImpl extends PayoutBase.PayoutBaseImpl implements ForwardPayout {
		private final Product underlier;
		private final String deliveryTerm;
		private final AssetDeliveryInformation delivery;
		private final CalculationSchedule schedule;
		private final MetaFields meta;
		
		protected ForwardPayoutImpl(ForwardPayout.ForwardPayoutBuilder builder) {
			super(builder);
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
			this.deliveryTerm = builder.getDeliveryTerm();
			this.delivery = ofNullable(builder.getDelivery()).map(f->f.build()).orElse(null);
			this.schedule = ofNullable(builder.getSchedule()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product getUnderlier() {
			return underlier;
		}
		
		@Override
		@RosettaAttribute("deliveryTerm")
		public String getDeliveryTerm() {
			return deliveryTerm;
		}
		
		@Override
		@RosettaAttribute("delivery")
		public AssetDeliveryInformation getDelivery() {
			return delivery;
		}
		
		@Override
		@RosettaAttribute("schedule")
		public CalculationSchedule getSchedule() {
			return schedule;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ForwardPayout build() {
			return this;
		}
		
		@Override
		public ForwardPayout.ForwardPayoutBuilder toBuilder() {
			ForwardPayout.ForwardPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ForwardPayout.ForwardPayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
			ofNullable(getDeliveryTerm()).ifPresent(builder::setDeliveryTerm);
			ofNullable(getDelivery()).ifPresent(builder::setDelivery);
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ForwardPayout _that = getType().cast(o);
		
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(deliveryTerm, _that.getDeliveryTerm())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (deliveryTerm != null ? deliveryTerm.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ForwardPayout {" +
				"underlier=" + this.underlier + ", " +
				"deliveryTerm=" + this.deliveryTerm + ", " +
				"delivery=" + this.delivery + ", " +
				"schedule=" + this.schedule + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ForwardPayout  ***********************/
	class ForwardPayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements ForwardPayout.ForwardPayoutBuilder, GlobalKeyBuilder {
	
		protected Product.ProductBuilder underlier;
		protected String deliveryTerm;
		protected AssetDeliveryInformation.AssetDeliveryInformationBuilder delivery;
		protected CalculationSchedule.CalculationScheduleBuilder schedule;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ForwardPayoutBuilderImpl() {
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
		@RosettaAttribute("deliveryTerm")
		public String getDeliveryTerm() {
			return deliveryTerm;
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
		public ForwardPayout.ForwardPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public ForwardPayout.ForwardPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public ForwardPayout.ForwardPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public ForwardPayout.ForwardPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public ForwardPayout.ForwardPayoutBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryTerm")
		public ForwardPayout.ForwardPayoutBuilder setDeliveryTerm(String deliveryTerm) {
			this.deliveryTerm = deliveryTerm==null?null:deliveryTerm;
			return this;
		}
		@Override
		@RosettaAttribute("delivery")
		public ForwardPayout.ForwardPayoutBuilder setDelivery(AssetDeliveryInformation delivery) {
			this.delivery = delivery==null?null:delivery.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("schedule")
		public ForwardPayout.ForwardPayoutBuilder setSchedule(CalculationSchedule schedule) {
			this.schedule = schedule==null?null:schedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ForwardPayout.ForwardPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ForwardPayout build() {
			return new ForwardPayout.ForwardPayoutImpl(this);
		}
		
		@Override
		public ForwardPayout.ForwardPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ForwardPayout.ForwardPayoutBuilder prune() {
			super.prune();
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			if (delivery!=null && !delivery.prune().hasData()) delivery = null;
			if (schedule!=null && !schedule.prune().hasData()) schedule = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			if (getDeliveryTerm()!=null) return true;
			if (getDelivery()!=null && getDelivery().hasData()) return true;
			if (getSchedule()!=null && getSchedule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ForwardPayout.ForwardPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ForwardPayout.ForwardPayoutBuilder o = (ForwardPayout.ForwardPayoutBuilder) other;
			
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			merger.mergeRosetta(getDelivery(), o.getDelivery(), this::setDelivery);
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::setSchedule);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getDeliveryTerm(), o.getDeliveryTerm(), this::setDeliveryTerm);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ForwardPayout _that = getType().cast(o);
		
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(deliveryTerm, _that.getDeliveryTerm())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (deliveryTerm != null ? deliveryTerm.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ForwardPayoutBuilder {" +
				"underlier=" + this.underlier + ", " +
				"deliveryTerm=" + this.deliveryTerm + ", " +
				"delivery=" + this.delivery + ", " +
				"schedule=" + this.schedule + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
