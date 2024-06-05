package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PaymentDetail;
import cdm.product.common.settlement.PaymentDetail.PaymentDetailBuilder;
import cdm.product.common.settlement.PaymentDetail.PaymentDetailBuilderImpl;
import cdm.product.common.settlement.PaymentDetail.PaymentDetailImpl;
import cdm.product.common.settlement.PaymentRule;
import cdm.product.common.settlement.meta.PaymentDetailMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentDetail", builder=PaymentDetail.PaymentDetailBuilderImpl.class, version="${project.version}")
public interface PaymentDetail extends RosettaModelObject, GlobalKey {

	PaymentDetailMeta metaData = new PaymentDetailMeta();

	/*********************** Getter Methods  ***********************/
	AdjustableOrRelativeDate getPaymentDate();
	/**
	 * The calculation rule.
	 */
	PaymentRule getPaymentRule();
	/**
	 * A fixed payment amount.
	 */
	Money getPaymentAmount();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PaymentDetail build();
	
	PaymentDetail.PaymentDetailBuilder toBuilder();
	
	static PaymentDetail.PaymentDetailBuilder builder() {
		return new PaymentDetail.PaymentDetailBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentDetail> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentDetail> getType() {
		return PaymentDetail.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("paymentDate"), processor, AdjustableOrRelativeDate.class, getPaymentDate());
		processRosetta(path.newSubPath("paymentRule"), processor, PaymentRule.class, getPaymentRule());
		processRosetta(path.newSubPath("paymentAmount"), processor, Money.class, getPaymentAmount());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentDetailBuilder extends PaymentDetail, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreatePaymentDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getPaymentDate();
		PaymentRule.PaymentRuleBuilder getOrCreatePaymentRule();
		PaymentRule.PaymentRuleBuilder getPaymentRule();
		Money.MoneyBuilder getOrCreatePaymentAmount();
		Money.MoneyBuilder getPaymentAmount();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PaymentDetail.PaymentDetailBuilder setPaymentDate(AdjustableOrRelativeDate paymentDate);
		PaymentDetail.PaymentDetailBuilder setPaymentRule(PaymentRule paymentRule);
		PaymentDetail.PaymentDetailBuilder setPaymentAmount(Money paymentAmount);
		PaymentDetail.PaymentDetailBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("paymentDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getPaymentDate());
			processRosetta(path.newSubPath("paymentRule"), processor, PaymentRule.PaymentRuleBuilder.class, getPaymentRule());
			processRosetta(path.newSubPath("paymentAmount"), processor, Money.MoneyBuilder.class, getPaymentAmount());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PaymentDetail.PaymentDetailBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentDetail  ***********************/
	class PaymentDetailImpl implements PaymentDetail {
		private final AdjustableOrRelativeDate paymentDate;
		private final PaymentRule paymentRule;
		private final Money paymentAmount;
		private final MetaFields meta;
		
		protected PaymentDetailImpl(PaymentDetail.PaymentDetailBuilder builder) {
			this.paymentDate = ofNullable(builder.getPaymentDate()).map(f->f.build()).orElse(null);
			this.paymentRule = ofNullable(builder.getPaymentRule()).map(f->f.build()).orElse(null);
			this.paymentAmount = ofNullable(builder.getPaymentAmount()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("paymentDate")
		public AdjustableOrRelativeDate getPaymentDate() {
			return paymentDate;
		}
		
		@Override
		@RosettaAttribute("paymentRule")
		public PaymentRule getPaymentRule() {
			return paymentRule;
		}
		
		@Override
		@RosettaAttribute("paymentAmount")
		public Money getPaymentAmount() {
			return paymentAmount;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PaymentDetail build() {
			return this;
		}
		
		@Override
		public PaymentDetail.PaymentDetailBuilder toBuilder() {
			PaymentDetail.PaymentDetailBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentDetail.PaymentDetailBuilder builder) {
			ofNullable(getPaymentDate()).ifPresent(builder::setPaymentDate);
			ofNullable(getPaymentRule()).ifPresent(builder::setPaymentRule);
			ofNullable(getPaymentAmount()).ifPresent(builder::setPaymentAmount);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDetail _that = getType().cast(o);
		
			if (!Objects.equals(paymentDate, _that.getPaymentDate())) return false;
			if (!Objects.equals(paymentRule, _that.getPaymentRule())) return false;
			if (!Objects.equals(paymentAmount, _that.getPaymentAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentDate != null ? paymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentRule != null ? paymentRule.hashCode() : 0);
			_result = 31 * _result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDetail {" +
				"paymentDate=" + this.paymentDate + ", " +
				"paymentRule=" + this.paymentRule + ", " +
				"paymentAmount=" + this.paymentAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentDetail  ***********************/
	class PaymentDetailBuilderImpl implements PaymentDetail.PaymentDetailBuilder, GlobalKeyBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder paymentDate;
		protected PaymentRule.PaymentRuleBuilder paymentRule;
		protected Money.MoneyBuilder paymentAmount;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PaymentDetailBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("paymentDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getPaymentDate() {
			return paymentDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreatePaymentDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (paymentDate!=null) {
				result = paymentDate;
			}
			else {
				result = paymentDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentRule")
		public PaymentRule.PaymentRuleBuilder getPaymentRule() {
			return paymentRule;
		}
		
		@Override
		public PaymentRule.PaymentRuleBuilder getOrCreatePaymentRule() {
			PaymentRule.PaymentRuleBuilder result;
			if (paymentRule!=null) {
				result = paymentRule;
			}
			else {
				result = paymentRule = PaymentRule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentAmount")
		public Money.MoneyBuilder getPaymentAmount() {
			return paymentAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePaymentAmount() {
			Money.MoneyBuilder result;
			if (paymentAmount!=null) {
				result = paymentAmount;
			}
			else {
				result = paymentAmount = Money.builder();
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
		@RosettaAttribute("paymentDate")
		public PaymentDetail.PaymentDetailBuilder setPaymentDate(AdjustableOrRelativeDate paymentDate) {
			this.paymentDate = paymentDate==null?null:paymentDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentRule")
		public PaymentDetail.PaymentDetailBuilder setPaymentRule(PaymentRule paymentRule) {
			this.paymentRule = paymentRule==null?null:paymentRule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentAmount")
		public PaymentDetail.PaymentDetailBuilder setPaymentAmount(Money paymentAmount) {
			this.paymentAmount = paymentAmount==null?null:paymentAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PaymentDetail.PaymentDetailBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PaymentDetail build() {
			return new PaymentDetail.PaymentDetailImpl(this);
		}
		
		@Override
		public PaymentDetail.PaymentDetailBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDetail.PaymentDetailBuilder prune() {
			if (paymentDate!=null && !paymentDate.prune().hasData()) paymentDate = null;
			if (paymentRule!=null && !paymentRule.prune().hasData()) paymentRule = null;
			if (paymentAmount!=null && !paymentAmount.prune().hasData()) paymentAmount = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPaymentDate()!=null && getPaymentDate().hasData()) return true;
			if (getPaymentRule()!=null && getPaymentRule().hasData()) return true;
			if (getPaymentAmount()!=null && getPaymentAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDetail.PaymentDetailBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentDetail.PaymentDetailBuilder o = (PaymentDetail.PaymentDetailBuilder) other;
			
			merger.mergeRosetta(getPaymentDate(), o.getPaymentDate(), this::setPaymentDate);
			merger.mergeRosetta(getPaymentRule(), o.getPaymentRule(), this::setPaymentRule);
			merger.mergeRosetta(getPaymentAmount(), o.getPaymentAmount(), this::setPaymentAmount);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDetail _that = getType().cast(o);
		
			if (!Objects.equals(paymentDate, _that.getPaymentDate())) return false;
			if (!Objects.equals(paymentRule, _that.getPaymentRule())) return false;
			if (!Objects.equals(paymentAmount, _that.getPaymentAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentDate != null ? paymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentRule != null ? paymentRule.hashCode() : 0);
			_result = 31 * _result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDetailBuilder {" +
				"paymentDate=" + this.paymentDate + ", " +
				"paymentRule=" + this.paymentRule + ", " +
				"paymentAmount=" + this.paymentAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
