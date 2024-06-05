package cdm.product.template;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.ExchangeRate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.DeliveryMethodEnum;
import cdm.product.template.SecurityLeg;
import cdm.product.template.SecurityLeg.SecurityLegBuilder;
import cdm.product.template.SecurityLeg.SecurityLegBuilderImpl;
import cdm.product.template.SecurityLeg.SecurityLegImpl;
import cdm.product.template.meta.SecurityLegMeta;
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
 *  Terms defining a security leg in a securities financing transaction, which can either be the near leg or the far leg and is closely modelled onto the nearLeg and farLeg types in FpML
 * @version ${project.version}
 */
@RosettaDataType(value="SecurityLeg", builder=SecurityLeg.SecurityLegBuilderImpl.class, version="${project.version}")
public interface SecurityLeg extends RosettaModelObject, GlobalKey {

	SecurityLegMeta metaData = new SecurityLegMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Whether the leg is a buyer or seller of security
	 */
	BuyerSeller getBuyerSeller();
	/**
	 * Settlement or Payment Date for the security leg
	 */
	AdjustableOrRelativeDate getSettlementDate();
	/**
	 * Settlement amount for the security leg
	 */
	Money getSettlementAmount();
	/**
	 * Settlement Currency for use where the Settlement Amount cannot be known in advance.
	 */
	String getSettlementCurrency();
	/**
	 * FX rate in case when cash settlement amount is in a different currency to the security.
	 */
	ExchangeRate getFxRate();
	/**
	 * Delivery Date for the transaction. Delivery Date can be populated when it is not equal to the Settlement Date.
	 */
	AdjustableOrRelativeDate getDeliveryDate();
	/**
	 * Specifies a delivery method for the security transaction.
	 */
	DeliveryMethodEnum getDeliveryMethod();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	SecurityLeg build();
	
	SecurityLeg.SecurityLegBuilder toBuilder();
	
	static SecurityLeg.SecurityLegBuilder builder() {
		return new SecurityLeg.SecurityLegBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SecurityLeg> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SecurityLeg> getType() {
		return SecurityLeg.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("buyerSeller"), processor, BuyerSeller.class, getBuyerSeller());
		processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrRelativeDate.class, getSettlementDate());
		processRosetta(path.newSubPath("settlementAmount"), processor, Money.class, getSettlementAmount());
		processor.processBasic(path.newSubPath("settlementCurrency"), String.class, getSettlementCurrency(), this);
		processRosetta(path.newSubPath("fxRate"), processor, ExchangeRate.class, getFxRate());
		processRosetta(path.newSubPath("deliveryDate"), processor, AdjustableOrRelativeDate.class, getDeliveryDate());
		processor.processBasic(path.newSubPath("deliveryMethod"), DeliveryMethodEnum.class, getDeliveryMethod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityLegBuilder extends SecurityLeg, RosettaModelObjectBuilder {
		BuyerSeller.BuyerSellerBuilder getOrCreateBuyerSeller();
		BuyerSeller.BuyerSellerBuilder getBuyerSeller();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateSettlementDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getSettlementDate();
		Money.MoneyBuilder getOrCreateSettlementAmount();
		Money.MoneyBuilder getSettlementAmount();
		ExchangeRate.ExchangeRateBuilder getOrCreateFxRate();
		ExchangeRate.ExchangeRateBuilder getFxRate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateDeliveryDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getDeliveryDate();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		SecurityLeg.SecurityLegBuilder setBuyerSeller(BuyerSeller buyerSeller);
		SecurityLeg.SecurityLegBuilder setSettlementDate(AdjustableOrRelativeDate settlementDate);
		SecurityLeg.SecurityLegBuilder setSettlementAmount(Money settlementAmount);
		SecurityLeg.SecurityLegBuilder setSettlementCurrency(String settlementCurrency);
		SecurityLeg.SecurityLegBuilder setFxRate(ExchangeRate fxRate);
		SecurityLeg.SecurityLegBuilder setDeliveryDate(AdjustableOrRelativeDate deliveryDate);
		SecurityLeg.SecurityLegBuilder setDeliveryMethod(DeliveryMethodEnum deliveryMethod);
		SecurityLeg.SecurityLegBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("buyerSeller"), processor, BuyerSeller.BuyerSellerBuilder.class, getBuyerSeller());
			processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getSettlementDate());
			processRosetta(path.newSubPath("settlementAmount"), processor, Money.MoneyBuilder.class, getSettlementAmount());
			processor.processBasic(path.newSubPath("settlementCurrency"), String.class, getSettlementCurrency(), this);
			processRosetta(path.newSubPath("fxRate"), processor, ExchangeRate.ExchangeRateBuilder.class, getFxRate());
			processRosetta(path.newSubPath("deliveryDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getDeliveryDate());
			processor.processBasic(path.newSubPath("deliveryMethod"), DeliveryMethodEnum.class, getDeliveryMethod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		SecurityLeg.SecurityLegBuilder prune();
	}

	/*********************** Immutable Implementation of SecurityLeg  ***********************/
	class SecurityLegImpl implements SecurityLeg {
		private final BuyerSeller buyerSeller;
		private final AdjustableOrRelativeDate settlementDate;
		private final Money settlementAmount;
		private final String settlementCurrency;
		private final ExchangeRate fxRate;
		private final AdjustableOrRelativeDate deliveryDate;
		private final DeliveryMethodEnum deliveryMethod;
		private final MetaFields meta;
		
		protected SecurityLegImpl(SecurityLeg.SecurityLegBuilder builder) {
			this.buyerSeller = ofNullable(builder.getBuyerSeller()).map(f->f.build()).orElse(null);
			this.settlementDate = ofNullable(builder.getSettlementDate()).map(f->f.build()).orElse(null);
			this.settlementAmount = ofNullable(builder.getSettlementAmount()).map(f->f.build()).orElse(null);
			this.settlementCurrency = builder.getSettlementCurrency();
			this.fxRate = ofNullable(builder.getFxRate()).map(f->f.build()).orElse(null);
			this.deliveryDate = ofNullable(builder.getDeliveryDate()).map(f->f.build()).orElse(null);
			this.deliveryMethod = builder.getDeliveryMethod();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("buyerSeller")
		public BuyerSeller getBuyerSeller() {
			return buyerSeller;
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrRelativeDate getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		@RosettaAttribute("settlementAmount")
		public Money getSettlementAmount() {
			return settlementAmount;
		}
		
		@Override
		@RosettaAttribute("settlementCurrency")
		public String getSettlementCurrency() {
			return settlementCurrency;
		}
		
		@Override
		@RosettaAttribute("fxRate")
		public ExchangeRate getFxRate() {
			return fxRate;
		}
		
		@Override
		@RosettaAttribute("deliveryDate")
		public AdjustableOrRelativeDate getDeliveryDate() {
			return deliveryDate;
		}
		
		@Override
		@RosettaAttribute("deliveryMethod")
		public DeliveryMethodEnum getDeliveryMethod() {
			return deliveryMethod;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public SecurityLeg build() {
			return this;
		}
		
		@Override
		public SecurityLeg.SecurityLegBuilder toBuilder() {
			SecurityLeg.SecurityLegBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SecurityLeg.SecurityLegBuilder builder) {
			ofNullable(getBuyerSeller()).ifPresent(builder::setBuyerSeller);
			ofNullable(getSettlementDate()).ifPresent(builder::setSettlementDate);
			ofNullable(getSettlementAmount()).ifPresent(builder::setSettlementAmount);
			ofNullable(getSettlementCurrency()).ifPresent(builder::setSettlementCurrency);
			ofNullable(getFxRate()).ifPresent(builder::setFxRate);
			ofNullable(getDeliveryDate()).ifPresent(builder::setDeliveryDate);
			ofNullable(getDeliveryMethod()).ifPresent(builder::setDeliveryMethod);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityLeg _that = getType().cast(o);
		
			if (!Objects.equals(buyerSeller, _that.getBuyerSeller())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(settlementAmount, _that.getSettlementAmount())) return false;
			if (!Objects.equals(settlementCurrency, _that.getSettlementCurrency())) return false;
			if (!Objects.equals(fxRate, _that.getFxRate())) return false;
			if (!Objects.equals(deliveryDate, _that.getDeliveryDate())) return false;
			if (!Objects.equals(deliveryMethod, _that.getDeliveryMethod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (buyerSeller != null ? buyerSeller.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (settlementAmount != null ? settlementAmount.hashCode() : 0);
			_result = 31 * _result + (settlementCurrency != null ? settlementCurrency.hashCode() : 0);
			_result = 31 * _result + (fxRate != null ? fxRate.hashCode() : 0);
			_result = 31 * _result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
			_result = 31 * _result + (deliveryMethod != null ? deliveryMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityLeg {" +
				"buyerSeller=" + this.buyerSeller + ", " +
				"settlementDate=" + this.settlementDate + ", " +
				"settlementAmount=" + this.settlementAmount + ", " +
				"settlementCurrency=" + this.settlementCurrency + ", " +
				"fxRate=" + this.fxRate + ", " +
				"deliveryDate=" + this.deliveryDate + ", " +
				"deliveryMethod=" + this.deliveryMethod + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of SecurityLeg  ***********************/
	class SecurityLegBuilderImpl implements SecurityLeg.SecurityLegBuilder, GlobalKeyBuilder {
	
		protected BuyerSeller.BuyerSellerBuilder buyerSeller;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder settlementDate;
		protected Money.MoneyBuilder settlementAmount;
		protected String settlementCurrency;
		protected ExchangeRate.ExchangeRateBuilder fxRate;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder deliveryDate;
		protected DeliveryMethodEnum deliveryMethod;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public SecurityLegBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("buyerSeller")
		public BuyerSeller.BuyerSellerBuilder getBuyerSeller() {
			return buyerSeller;
		}
		
		@Override
		public BuyerSeller.BuyerSellerBuilder getOrCreateBuyerSeller() {
			BuyerSeller.BuyerSellerBuilder result;
			if (buyerSeller!=null) {
				result = buyerSeller;
			}
			else {
				result = buyerSeller = BuyerSeller.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateSettlementDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (settlementDate!=null) {
				result = settlementDate;
			}
			else {
				result = settlementDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementAmount")
		public Money.MoneyBuilder getSettlementAmount() {
			return settlementAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateSettlementAmount() {
			Money.MoneyBuilder result;
			if (settlementAmount!=null) {
				result = settlementAmount;
			}
			else {
				result = settlementAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementCurrency")
		public String getSettlementCurrency() {
			return settlementCurrency;
		}
		
		@Override
		@RosettaAttribute("fxRate")
		public ExchangeRate.ExchangeRateBuilder getFxRate() {
			return fxRate;
		}
		
		@Override
		public ExchangeRate.ExchangeRateBuilder getOrCreateFxRate() {
			ExchangeRate.ExchangeRateBuilder result;
			if (fxRate!=null) {
				result = fxRate;
			}
			else {
				result = fxRate = ExchangeRate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getDeliveryDate() {
			return deliveryDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateDeliveryDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (deliveryDate!=null) {
				result = deliveryDate;
			}
			else {
				result = deliveryDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryMethod")
		public DeliveryMethodEnum getDeliveryMethod() {
			return deliveryMethod;
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
		@RosettaAttribute("buyerSeller")
		public SecurityLeg.SecurityLegBuilder setBuyerSeller(BuyerSeller buyerSeller) {
			this.buyerSeller = buyerSeller==null?null:buyerSeller.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public SecurityLeg.SecurityLegBuilder setSettlementDate(AdjustableOrRelativeDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementAmount")
		public SecurityLeg.SecurityLegBuilder setSettlementAmount(Money settlementAmount) {
			this.settlementAmount = settlementAmount==null?null:settlementAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementCurrency")
		public SecurityLeg.SecurityLegBuilder setSettlementCurrency(String settlementCurrency) {
			this.settlementCurrency = settlementCurrency==null?null:settlementCurrency;
			return this;
		}
		@Override
		@RosettaAttribute("fxRate")
		public SecurityLeg.SecurityLegBuilder setFxRate(ExchangeRate fxRate) {
			this.fxRate = fxRate==null?null:fxRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDate")
		public SecurityLeg.SecurityLegBuilder setDeliveryDate(AdjustableOrRelativeDate deliveryDate) {
			this.deliveryDate = deliveryDate==null?null:deliveryDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryMethod")
		public SecurityLeg.SecurityLegBuilder setDeliveryMethod(DeliveryMethodEnum deliveryMethod) {
			this.deliveryMethod = deliveryMethod==null?null:deliveryMethod;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public SecurityLeg.SecurityLegBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public SecurityLeg build() {
			return new SecurityLeg.SecurityLegImpl(this);
		}
		
		@Override
		public SecurityLeg.SecurityLegBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityLeg.SecurityLegBuilder prune() {
			if (buyerSeller!=null && !buyerSeller.prune().hasData()) buyerSeller = null;
			if (settlementDate!=null && !settlementDate.prune().hasData()) settlementDate = null;
			if (settlementAmount!=null && !settlementAmount.prune().hasData()) settlementAmount = null;
			if (fxRate!=null && !fxRate.prune().hasData()) fxRate = null;
			if (deliveryDate!=null && !deliveryDate.prune().hasData()) deliveryDate = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBuyerSeller()!=null && getBuyerSeller().hasData()) return true;
			if (getSettlementDate()!=null && getSettlementDate().hasData()) return true;
			if (getSettlementAmount()!=null && getSettlementAmount().hasData()) return true;
			if (getSettlementCurrency()!=null) return true;
			if (getFxRate()!=null && getFxRate().hasData()) return true;
			if (getDeliveryDate()!=null && getDeliveryDate().hasData()) return true;
			if (getDeliveryMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityLeg.SecurityLegBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SecurityLeg.SecurityLegBuilder o = (SecurityLeg.SecurityLegBuilder) other;
			
			merger.mergeRosetta(getBuyerSeller(), o.getBuyerSeller(), this::setBuyerSeller);
			merger.mergeRosetta(getSettlementDate(), o.getSettlementDate(), this::setSettlementDate);
			merger.mergeRosetta(getSettlementAmount(), o.getSettlementAmount(), this::setSettlementAmount);
			merger.mergeRosetta(getFxRate(), o.getFxRate(), this::setFxRate);
			merger.mergeRosetta(getDeliveryDate(), o.getDeliveryDate(), this::setDeliveryDate);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getSettlementCurrency(), o.getSettlementCurrency(), this::setSettlementCurrency);
			merger.mergeBasic(getDeliveryMethod(), o.getDeliveryMethod(), this::setDeliveryMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityLeg _that = getType().cast(o);
		
			if (!Objects.equals(buyerSeller, _that.getBuyerSeller())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(settlementAmount, _that.getSettlementAmount())) return false;
			if (!Objects.equals(settlementCurrency, _that.getSettlementCurrency())) return false;
			if (!Objects.equals(fxRate, _that.getFxRate())) return false;
			if (!Objects.equals(deliveryDate, _that.getDeliveryDate())) return false;
			if (!Objects.equals(deliveryMethod, _that.getDeliveryMethod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (buyerSeller != null ? buyerSeller.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (settlementAmount != null ? settlementAmount.hashCode() : 0);
			_result = 31 * _result + (settlementCurrency != null ? settlementCurrency.hashCode() : 0);
			_result = 31 * _result + (fxRate != null ? fxRate.hashCode() : 0);
			_result = 31 * _result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
			_result = 31 * _result + (deliveryMethod != null ? deliveryMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityLegBuilder {" +
				"buyerSeller=" + this.buyerSeller + ", " +
				"settlementDate=" + this.settlementDate + ", " +
				"settlementAmount=" + this.settlementAmount + ", " +
				"settlementCurrency=" + this.settlementCurrency + ", " +
				"fxRate=" + this.fxRate + ", " +
				"deliveryDate=" + this.deliveryDate + ", " +
				"deliveryMethod=" + this.deliveryMethod + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
