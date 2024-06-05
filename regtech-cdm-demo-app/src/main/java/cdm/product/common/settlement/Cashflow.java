package cdm.product.common.settlement;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.Cashflow.CashflowBuilder;
import cdm.product.common.settlement.Cashflow.CashflowBuilderImpl;
import cdm.product.common.settlement.Cashflow.CashflowImpl;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.PaymentDiscounting;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.meta.CashflowMeta;
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
 * Class to specify a cashflow, i.e. the outcome of either of computation (e.g. interest accrual) or an assessment of some sort (e.g. a fee). The cashflow can then be turned into a cash transfer, artefact to be used as the input to a payment system or the outcome of it. The associated globalKey denotes the ability to associate a hash value to the Cashflow instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="Cashflow", builder=Cashflow.CashflowBuilderImpl.class, version="${project.version}")
public interface Cashflow extends PayoutBase, GlobalKey {

	CashflowMeta metaData = new CashflowMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The qualification of the type of cashflow, e.g. brokerage fee, premium, upfront fee etc. Particularly relevant when it cannot be inferred directly through lineage.
	 */
	CashflowType getCashflowType();
	/**
	 * FpML specifies the FpML PaymentDiscounting.model group for representing the discounting elements that can be associated with a payment.
	 */
	PaymentDiscounting getPaymentDiscounting();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Cashflow build();
	
	Cashflow.CashflowBuilder toBuilder();
	
	static Cashflow.CashflowBuilder builder() {
		return new Cashflow.CashflowBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Cashflow> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Cashflow> getType() {
		return Cashflow.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("cashflowType"), processor, CashflowType.class, getCashflowType());
		processRosetta(path.newSubPath("paymentDiscounting"), processor, PaymentDiscounting.class, getPaymentDiscounting());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashflowBuilder extends Cashflow, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		CashflowType.CashflowTypeBuilder getOrCreateCashflowType();
		CashflowType.CashflowTypeBuilder getCashflowType();
		PaymentDiscounting.PaymentDiscountingBuilder getOrCreatePaymentDiscounting();
		PaymentDiscounting.PaymentDiscountingBuilder getPaymentDiscounting();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Cashflow.CashflowBuilder setPayerReceiver(PayerReceiver payerReceiver);
		Cashflow.CashflowBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		Cashflow.CashflowBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		Cashflow.CashflowBuilder setSettlementTerms(SettlementTerms settlementTerms);
		Cashflow.CashflowBuilder setCashflowType(CashflowType cashflowType);
		Cashflow.CashflowBuilder setPaymentDiscounting(PaymentDiscounting paymentDiscounting);
		Cashflow.CashflowBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("cashflowType"), processor, CashflowType.CashflowTypeBuilder.class, getCashflowType());
			processRosetta(path.newSubPath("paymentDiscounting"), processor, PaymentDiscounting.PaymentDiscountingBuilder.class, getPaymentDiscounting());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Cashflow.CashflowBuilder prune();
	}

	/*********************** Immutable Implementation of Cashflow  ***********************/
	class CashflowImpl extends PayoutBase.PayoutBaseImpl implements Cashflow {
		private final CashflowType cashflowType;
		private final PaymentDiscounting paymentDiscounting;
		private final MetaFields meta;
		
		protected CashflowImpl(Cashflow.CashflowBuilder builder) {
			super(builder);
			this.cashflowType = ofNullable(builder.getCashflowType()).map(f->f.build()).orElse(null);
			this.paymentDiscounting = ofNullable(builder.getPaymentDiscounting()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("cashflowType")
		public CashflowType getCashflowType() {
			return cashflowType;
		}
		
		@Override
		@RosettaAttribute("paymentDiscounting")
		public PaymentDiscounting getPaymentDiscounting() {
			return paymentDiscounting;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Cashflow build() {
			return this;
		}
		
		@Override
		public Cashflow.CashflowBuilder toBuilder() {
			Cashflow.CashflowBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Cashflow.CashflowBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getCashflowType()).ifPresent(builder::setCashflowType);
			ofNullable(getPaymentDiscounting()).ifPresent(builder::setPaymentDiscounting);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Cashflow _that = getType().cast(o);
		
			if (!Objects.equals(cashflowType, _that.getCashflowType())) return false;
			if (!Objects.equals(paymentDiscounting, _that.getPaymentDiscounting())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (cashflowType != null ? cashflowType.hashCode() : 0);
			_result = 31 * _result + (paymentDiscounting != null ? paymentDiscounting.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Cashflow {" +
				"cashflowType=" + this.cashflowType + ", " +
				"paymentDiscounting=" + this.paymentDiscounting + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Cashflow  ***********************/
	class CashflowBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements Cashflow.CashflowBuilder, GlobalKeyBuilder {
	
		protected CashflowType.CashflowTypeBuilder cashflowType;
		protected PaymentDiscounting.PaymentDiscountingBuilder paymentDiscounting;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CashflowBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashflowType")
		public CashflowType.CashflowTypeBuilder getCashflowType() {
			return cashflowType;
		}
		
		@Override
		public CashflowType.CashflowTypeBuilder getOrCreateCashflowType() {
			CashflowType.CashflowTypeBuilder result;
			if (cashflowType!=null) {
				result = cashflowType;
			}
			else {
				result = cashflowType = CashflowType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDiscounting")
		public PaymentDiscounting.PaymentDiscountingBuilder getPaymentDiscounting() {
			return paymentDiscounting;
		}
		
		@Override
		public PaymentDiscounting.PaymentDiscountingBuilder getOrCreatePaymentDiscounting() {
			PaymentDiscounting.PaymentDiscountingBuilder result;
			if (paymentDiscounting!=null) {
				result = paymentDiscounting;
			}
			else {
				result = paymentDiscounting = PaymentDiscounting.builder();
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
		public Cashflow.CashflowBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public Cashflow.CashflowBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public Cashflow.CashflowBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public Cashflow.CashflowBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashflowType")
		public Cashflow.CashflowBuilder setCashflowType(CashflowType cashflowType) {
			this.cashflowType = cashflowType==null?null:cashflowType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDiscounting")
		public Cashflow.CashflowBuilder setPaymentDiscounting(PaymentDiscounting paymentDiscounting) {
			this.paymentDiscounting = paymentDiscounting==null?null:paymentDiscounting.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Cashflow.CashflowBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Cashflow build() {
			return new Cashflow.CashflowImpl(this);
		}
		
		@Override
		public Cashflow.CashflowBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Cashflow.CashflowBuilder prune() {
			super.prune();
			if (cashflowType!=null && !cashflowType.prune().hasData()) cashflowType = null;
			if (paymentDiscounting!=null && !paymentDiscounting.prune().hasData()) paymentDiscounting = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getCashflowType()!=null && getCashflowType().hasData()) return true;
			if (getPaymentDiscounting()!=null && getPaymentDiscounting().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Cashflow.CashflowBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Cashflow.CashflowBuilder o = (Cashflow.CashflowBuilder) other;
			
			merger.mergeRosetta(getCashflowType(), o.getCashflowType(), this::setCashflowType);
			merger.mergeRosetta(getPaymentDiscounting(), o.getPaymentDiscounting(), this::setPaymentDiscounting);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Cashflow _that = getType().cast(o);
		
			if (!Objects.equals(cashflowType, _that.getCashflowType())) return false;
			if (!Objects.equals(paymentDiscounting, _that.getPaymentDiscounting())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (cashflowType != null ? cashflowType.hashCode() : 0);
			_result = 31 * _result + (paymentDiscounting != null ? paymentDiscounting.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashflowBuilder {" +
				"cashflowType=" + this.cashflowType + ", " +
				"paymentDiscounting=" + this.paymentDiscounting + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
