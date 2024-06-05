package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableDate;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PrincipalPayment;
import cdm.product.common.settlement.PrincipalPayment.PrincipalPaymentBuilder;
import cdm.product.common.settlement.PrincipalPayment.PrincipalPaymentBuilderImpl;
import cdm.product.common.settlement.PrincipalPayment.PrincipalPaymentImpl;
import cdm.product.common.settlement.meta.PrincipalPaymentMeta;
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
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Any kind of principal payments when the amount is known and thus fixed.
 * @version ${project.version}
 */
@RosettaDataType(value="PrincipalPayment", builder=PrincipalPayment.PrincipalPaymentBuilderImpl.class, version="${project.version}")
public interface PrincipalPayment extends RosettaModelObject, GlobalKey {

	PrincipalPaymentMeta metaData = new PrincipalPaymentMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The date where the PrincipalPayment shall be settled.
	 */
	AdjustableDate getPrincipalPaymentDate();
	/**
	 * Specifies the parties responsible for making and receiving payments defined by this structure.
	 */
	PayerReceiver getPayerReceiver();
	/**
	 * When known at the time the transaction is made, the cash amount to be paid.
	 */
	Money getPrincipalAmount();
	/**
	 * The value representing the discount factor used to calculate the present value of the principal payment amount.
	 */
	BigDecimal getDiscountFactor();
	/**
	 * The amount representing the present value of the principal payment.
	 */
	Money getPresentValuePrincipalAmount();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PrincipalPayment build();
	
	PrincipalPayment.PrincipalPaymentBuilder toBuilder();
	
	static PrincipalPayment.PrincipalPaymentBuilder builder() {
		return new PrincipalPayment.PrincipalPaymentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PrincipalPayment> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PrincipalPayment> getType() {
		return PrincipalPayment.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("principalPaymentDate"), processor, AdjustableDate.class, getPrincipalPaymentDate());
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("principalAmount"), processor, Money.class, getPrincipalAmount());
		processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
		processRosetta(path.newSubPath("presentValuePrincipalAmount"), processor, Money.class, getPresentValuePrincipalAmount());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PrincipalPaymentBuilder extends PrincipalPayment, RosettaModelObjectBuilder {
		AdjustableDate.AdjustableDateBuilder getOrCreatePrincipalPaymentDate();
		AdjustableDate.AdjustableDateBuilder getPrincipalPaymentDate();
		PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver();
		PayerReceiver.PayerReceiverBuilder getPayerReceiver();
		Money.MoneyBuilder getOrCreatePrincipalAmount();
		Money.MoneyBuilder getPrincipalAmount();
		Money.MoneyBuilder getOrCreatePresentValuePrincipalAmount();
		Money.MoneyBuilder getPresentValuePrincipalAmount();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PrincipalPayment.PrincipalPaymentBuilder setPrincipalPaymentDate(AdjustableDate principalPaymentDate);
		PrincipalPayment.PrincipalPaymentBuilder setPayerReceiver(PayerReceiver payerReceiver);
		PrincipalPayment.PrincipalPaymentBuilder setPrincipalAmount(Money principalAmount);
		PrincipalPayment.PrincipalPaymentBuilder setDiscountFactor(BigDecimal discountFactor);
		PrincipalPayment.PrincipalPaymentBuilder setPresentValuePrincipalAmount(Money presentValuePrincipalAmount);
		PrincipalPayment.PrincipalPaymentBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("principalPaymentDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getPrincipalPaymentDate());
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("principalAmount"), processor, Money.MoneyBuilder.class, getPrincipalAmount());
			processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
			processRosetta(path.newSubPath("presentValuePrincipalAmount"), processor, Money.MoneyBuilder.class, getPresentValuePrincipalAmount());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PrincipalPayment.PrincipalPaymentBuilder prune();
	}

	/*********************** Immutable Implementation of PrincipalPayment  ***********************/
	class PrincipalPaymentImpl implements PrincipalPayment {
		private final AdjustableDate principalPaymentDate;
		private final PayerReceiver payerReceiver;
		private final Money principalAmount;
		private final BigDecimal discountFactor;
		private final Money presentValuePrincipalAmount;
		private final MetaFields meta;
		
		protected PrincipalPaymentImpl(PrincipalPayment.PrincipalPaymentBuilder builder) {
			this.principalPaymentDate = ofNullable(builder.getPrincipalPaymentDate()).map(f->f.build()).orElse(null);
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.principalAmount = ofNullable(builder.getPrincipalAmount()).map(f->f.build()).orElse(null);
			this.discountFactor = builder.getDiscountFactor();
			this.presentValuePrincipalAmount = ofNullable(builder.getPresentValuePrincipalAmount()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("principalPaymentDate")
		public AdjustableDate getPrincipalPaymentDate() {
			return principalPaymentDate;
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("principalAmount")
		public Money getPrincipalAmount() {
			return principalAmount;
		}
		
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("presentValuePrincipalAmount")
		public Money getPresentValuePrincipalAmount() {
			return presentValuePrincipalAmount;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PrincipalPayment build() {
			return this;
		}
		
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder toBuilder() {
			PrincipalPayment.PrincipalPaymentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PrincipalPayment.PrincipalPaymentBuilder builder) {
			ofNullable(getPrincipalPaymentDate()).ifPresent(builder::setPrincipalPaymentDate);
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getPrincipalAmount()).ifPresent(builder::setPrincipalAmount);
			ofNullable(getDiscountFactor()).ifPresent(builder::setDiscountFactor);
			ofNullable(getPresentValuePrincipalAmount()).ifPresent(builder::setPresentValuePrincipalAmount);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPayment _that = getType().cast(o);
		
			if (!Objects.equals(principalPaymentDate, _that.getPrincipalPaymentDate())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(principalAmount, _that.getPrincipalAmount())) return false;
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(presentValuePrincipalAmount, _that.getPresentValuePrincipalAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (principalPaymentDate != null ? principalPaymentDate.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (principalAmount != null ? principalAmount.hashCode() : 0);
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (presentValuePrincipalAmount != null ? presentValuePrincipalAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPayment {" +
				"principalPaymentDate=" + this.principalPaymentDate + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"principalAmount=" + this.principalAmount + ", " +
				"discountFactor=" + this.discountFactor + ", " +
				"presentValuePrincipalAmount=" + this.presentValuePrincipalAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PrincipalPayment  ***********************/
	class PrincipalPaymentBuilderImpl implements PrincipalPayment.PrincipalPaymentBuilder, GlobalKeyBuilder {
	
		protected AdjustableDate.AdjustableDateBuilder principalPaymentDate;
		protected PayerReceiver.PayerReceiverBuilder payerReceiver;
		protected Money.MoneyBuilder principalAmount;
		protected BigDecimal discountFactor;
		protected Money.MoneyBuilder presentValuePrincipalAmount;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PrincipalPaymentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("principalPaymentDate")
		public AdjustableDate.AdjustableDateBuilder getPrincipalPaymentDate() {
			return principalPaymentDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreatePrincipalPaymentDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (principalPaymentDate!=null) {
				result = principalPaymentDate;
			}
			else {
				result = principalPaymentDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver.PayerReceiverBuilder getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver() {
			PayerReceiver.PayerReceiverBuilder result;
			if (payerReceiver!=null) {
				result = payerReceiver;
			}
			else {
				result = payerReceiver = PayerReceiver.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("principalAmount")
		public Money.MoneyBuilder getPrincipalAmount() {
			return principalAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePrincipalAmount() {
			Money.MoneyBuilder result;
			if (principalAmount!=null) {
				result = principalAmount;
			}
			else {
				result = principalAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("presentValuePrincipalAmount")
		public Money.MoneyBuilder getPresentValuePrincipalAmount() {
			return presentValuePrincipalAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePresentValuePrincipalAmount() {
			Money.MoneyBuilder result;
			if (presentValuePrincipalAmount!=null) {
				result = presentValuePrincipalAmount;
			}
			else {
				result = presentValuePrincipalAmount = Money.builder();
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
		@RosettaAttribute("principalPaymentDate")
		public PrincipalPayment.PrincipalPaymentBuilder setPrincipalPaymentDate(AdjustableDate principalPaymentDate) {
			this.principalPaymentDate = principalPaymentDate==null?null:principalPaymentDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public PrincipalPayment.PrincipalPaymentBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalAmount")
		public PrincipalPayment.PrincipalPaymentBuilder setPrincipalAmount(Money principalAmount) {
			this.principalAmount = principalAmount==null?null:principalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("discountFactor")
		public PrincipalPayment.PrincipalPaymentBuilder setDiscountFactor(BigDecimal discountFactor) {
			this.discountFactor = discountFactor==null?null:discountFactor;
			return this;
		}
		@Override
		@RosettaAttribute("presentValuePrincipalAmount")
		public PrincipalPayment.PrincipalPaymentBuilder setPresentValuePrincipalAmount(Money presentValuePrincipalAmount) {
			this.presentValuePrincipalAmount = presentValuePrincipalAmount==null?null:presentValuePrincipalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PrincipalPayment.PrincipalPaymentBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PrincipalPayment build() {
			return new PrincipalPayment.PrincipalPaymentImpl(this);
		}
		
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder prune() {
			if (principalPaymentDate!=null && !principalPaymentDate.prune().hasData()) principalPaymentDate = null;
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			if (principalAmount!=null && !principalAmount.prune().hasData()) principalAmount = null;
			if (presentValuePrincipalAmount!=null && !presentValuePrincipalAmount.prune().hasData()) presentValuePrincipalAmount = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrincipalPaymentDate()!=null && getPrincipalPaymentDate().hasData()) return true;
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getPrincipalAmount()!=null && getPrincipalAmount().hasData()) return true;
			if (getDiscountFactor()!=null) return true;
			if (getPresentValuePrincipalAmount()!=null && getPresentValuePrincipalAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PrincipalPayment.PrincipalPaymentBuilder o = (PrincipalPayment.PrincipalPaymentBuilder) other;
			
			merger.mergeRosetta(getPrincipalPaymentDate(), o.getPrincipalPaymentDate(), this::setPrincipalPaymentDate);
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			merger.mergeRosetta(getPrincipalAmount(), o.getPrincipalAmount(), this::setPrincipalAmount);
			merger.mergeRosetta(getPresentValuePrincipalAmount(), o.getPresentValuePrincipalAmount(), this::setPresentValuePrincipalAmount);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getDiscountFactor(), o.getDiscountFactor(), this::setDiscountFactor);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPayment _that = getType().cast(o);
		
			if (!Objects.equals(principalPaymentDate, _that.getPrincipalPaymentDate())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(principalAmount, _that.getPrincipalAmount())) return false;
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(presentValuePrincipalAmount, _that.getPresentValuePrincipalAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (principalPaymentDate != null ? principalPaymentDate.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (principalAmount != null ? principalAmount.hashCode() : 0);
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (presentValuePrincipalAmount != null ? presentValuePrincipalAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPaymentBuilder {" +
				"principalPaymentDate=" + this.principalPaymentDate + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"principalAmount=" + this.principalAmount + ", " +
				"discountFactor=" + this.discountFactor + ", " +
				"presentValuePrincipalAmount=" + this.presentValuePrincipalAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
