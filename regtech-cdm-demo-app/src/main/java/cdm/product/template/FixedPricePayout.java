package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.FixedPrice;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.FixedPricePayout;
import cdm.product.template.FixedPricePayout.FixedPricePayoutBuilder;
import cdm.product.template.FixedPricePayout.FixedPricePayoutBuilderImpl;
import cdm.product.template.FixedPricePayout.FixedPricePayoutImpl;
import cdm.product.template.meta.FixedPricePayoutMeta;
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
 * Represents a fixed price payout. There is no underlier associated with this payout type and is based on fixed pricing per a given unit (e.g. in commodities price per barrel)
 * @version ${project.version}
 */
@RosettaDataType(value="FixedPricePayout", builder=FixedPricePayout.FixedPricePayoutBuilderImpl.class, version="${project.version}")
public interface FixedPricePayout extends PayoutBase, GlobalKey {

	FixedPricePayoutMeta metaData = new FixedPricePayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the parameters to generate the payment date schedule, either through a parametric representation or by reference to specified dates.
	 */
	PaymentDates getPaymentDates();
	/**
	 * Specifies the fixed price on which fixed forward payments are based.
	 */
	FixedPrice getFixedPrice();
	/**
	 * Allows the full representation of a payout by defining a set of schedule periods. It supports standard schedule customization by expressing all the dates, quantities, and pricing data in a non-parametric way.
	 */
	CalculationSchedule getSchedule();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FixedPricePayout build();
	
	FixedPricePayout.FixedPricePayoutBuilder toBuilder();
	
	static FixedPricePayout.FixedPricePayoutBuilder builder() {
		return new FixedPricePayout.FixedPricePayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FixedPricePayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FixedPricePayout> getType() {
		return FixedPricePayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.class, getPaymentDates());
		processRosetta(path.newSubPath("fixedPrice"), processor, FixedPrice.class, getFixedPrice());
		processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.class, getSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FixedPricePayoutBuilder extends FixedPricePayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates();
		PaymentDates.PaymentDatesBuilder getPaymentDates();
		FixedPrice.FixedPriceBuilder getOrCreateFixedPrice();
		FixedPrice.FixedPriceBuilder getFixedPrice();
		CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule();
		CalculationSchedule.CalculationScheduleBuilder getSchedule();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FixedPricePayout.FixedPricePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		FixedPricePayout.FixedPricePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		FixedPricePayout.FixedPricePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		FixedPricePayout.FixedPricePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		FixedPricePayout.FixedPricePayoutBuilder setPaymentDates(PaymentDates paymentDates);
		FixedPricePayout.FixedPricePayoutBuilder setFixedPrice(FixedPrice fixedPrice);
		FixedPricePayout.FixedPricePayoutBuilder setSchedule(CalculationSchedule schedule);
		FixedPricePayout.FixedPricePayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.PaymentDatesBuilder.class, getPaymentDates());
			processRosetta(path.newSubPath("fixedPrice"), processor, FixedPrice.FixedPriceBuilder.class, getFixedPrice());
			processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.CalculationScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FixedPricePayout.FixedPricePayoutBuilder prune();
	}

	/*********************** Immutable Implementation of FixedPricePayout  ***********************/
	class FixedPricePayoutImpl extends PayoutBase.PayoutBaseImpl implements FixedPricePayout {
		private final PaymentDates paymentDates;
		private final FixedPrice fixedPrice;
		private final CalculationSchedule schedule;
		private final MetaFields meta;
		
		protected FixedPricePayoutImpl(FixedPricePayout.FixedPricePayoutBuilder builder) {
			super(builder);
			this.paymentDates = ofNullable(builder.getPaymentDates()).map(f->f.build()).orElse(null);
			this.fixedPrice = ofNullable(builder.getFixedPrice()).map(f->f.build()).orElse(null);
			this.schedule = ofNullable(builder.getSchedule()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("paymentDates")
		public PaymentDates getPaymentDates() {
			return paymentDates;
		}
		
		@Override
		@RosettaAttribute("fixedPrice")
		public FixedPrice getFixedPrice() {
			return fixedPrice;
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
		public FixedPricePayout build() {
			return this;
		}
		
		@Override
		public FixedPricePayout.FixedPricePayoutBuilder toBuilder() {
			FixedPricePayout.FixedPricePayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FixedPricePayout.FixedPricePayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getPaymentDates()).ifPresent(builder::setPaymentDates);
			ofNullable(getFixedPrice()).ifPresent(builder::setFixedPrice);
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			FixedPricePayout _that = getType().cast(o);
		
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(fixedPrice, _that.getFixedPrice())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (fixedPrice != null ? fixedPrice.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedPricePayout {" +
				"paymentDates=" + this.paymentDates + ", " +
				"fixedPrice=" + this.fixedPrice + ", " +
				"schedule=" + this.schedule + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of FixedPricePayout  ***********************/
	class FixedPricePayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements FixedPricePayout.FixedPricePayoutBuilder, GlobalKeyBuilder {
	
		protected PaymentDates.PaymentDatesBuilder paymentDates;
		protected FixedPrice.FixedPriceBuilder fixedPrice;
		protected CalculationSchedule.CalculationScheduleBuilder schedule;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FixedPricePayoutBuilderImpl() {
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
		@RosettaAttribute("fixedPrice")
		public FixedPrice.FixedPriceBuilder getFixedPrice() {
			return fixedPrice;
		}
		
		@Override
		public FixedPrice.FixedPriceBuilder getOrCreateFixedPrice() {
			FixedPrice.FixedPriceBuilder result;
			if (fixedPrice!=null) {
				result = fixedPrice;
			}
			else {
				result = fixedPrice = FixedPrice.builder();
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
		public FixedPricePayout.FixedPricePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public FixedPricePayout.FixedPricePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public FixedPricePayout.FixedPricePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public FixedPricePayout.FixedPricePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public FixedPricePayout.FixedPricePayoutBuilder setPaymentDates(PaymentDates paymentDates) {
			this.paymentDates = paymentDates==null?null:paymentDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixedPrice")
		public FixedPricePayout.FixedPricePayoutBuilder setFixedPrice(FixedPrice fixedPrice) {
			this.fixedPrice = fixedPrice==null?null:fixedPrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("schedule")
		public FixedPricePayout.FixedPricePayoutBuilder setSchedule(CalculationSchedule schedule) {
			this.schedule = schedule==null?null:schedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FixedPricePayout.FixedPricePayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FixedPricePayout build() {
			return new FixedPricePayout.FixedPricePayoutImpl(this);
		}
		
		@Override
		public FixedPricePayout.FixedPricePayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedPricePayout.FixedPricePayoutBuilder prune() {
			super.prune();
			if (paymentDates!=null && !paymentDates.prune().hasData()) paymentDates = null;
			if (fixedPrice!=null && !fixedPrice.prune().hasData()) fixedPrice = null;
			if (schedule!=null && !schedule.prune().hasData()) schedule = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getPaymentDates()!=null && getPaymentDates().hasData()) return true;
			if (getFixedPrice()!=null && getFixedPrice().hasData()) return true;
			if (getSchedule()!=null && getSchedule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedPricePayout.FixedPricePayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			FixedPricePayout.FixedPricePayoutBuilder o = (FixedPricePayout.FixedPricePayoutBuilder) other;
			
			merger.mergeRosetta(getPaymentDates(), o.getPaymentDates(), this::setPaymentDates);
			merger.mergeRosetta(getFixedPrice(), o.getFixedPrice(), this::setFixedPrice);
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::setSchedule);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			FixedPricePayout _that = getType().cast(o);
		
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(fixedPrice, _that.getFixedPrice())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (fixedPrice != null ? fixedPrice.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedPricePayoutBuilder {" +
				"paymentDates=" + this.paymentDates + ", " +
				"fixedPrice=" + this.fixedPrice + ", " +
				"schedule=" + this.schedule + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
