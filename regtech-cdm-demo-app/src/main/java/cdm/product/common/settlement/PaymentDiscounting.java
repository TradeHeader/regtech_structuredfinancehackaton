package cdm.product.common.settlement;

import cdm.observable.asset.Money;
import cdm.product.common.settlement.PaymentDiscounting;
import cdm.product.common.settlement.PaymentDiscounting.PaymentDiscountingBuilder;
import cdm.product.common.settlement.PaymentDiscounting.PaymentDiscountingBuilderImpl;
import cdm.product.common.settlement.PaymentDiscounting.PaymentDiscountingImpl;
import cdm.product.common.settlement.meta.PaymentDiscountingMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * This class corresponds to the FpML PaymentDiscounting.model group for representing the discounting elements that can be associated with a payment.
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentDiscounting", builder=PaymentDiscounting.PaymentDiscountingBuilderImpl.class, version="${project.version}")
public interface PaymentDiscounting extends RosettaModelObject {

	PaymentDiscountingMeta metaData = new PaymentDiscountingMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The value representing the discount factor used to calculate the present value of the cash flow.
	 */
	BigDecimal getDiscountFactor();
	/**
	 * The amount representing the present value of the forecast payment.
	 */
	Money getPresentValueAmount();

	/*********************** Build Methods  ***********************/
	PaymentDiscounting build();
	
	PaymentDiscounting.PaymentDiscountingBuilder toBuilder();
	
	static PaymentDiscounting.PaymentDiscountingBuilder builder() {
		return new PaymentDiscounting.PaymentDiscountingBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentDiscounting> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentDiscounting> getType() {
		return PaymentDiscounting.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
		processRosetta(path.newSubPath("presentValueAmount"), processor, Money.class, getPresentValueAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentDiscountingBuilder extends PaymentDiscounting, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreatePresentValueAmount();
		Money.MoneyBuilder getPresentValueAmount();
		PaymentDiscounting.PaymentDiscountingBuilder setDiscountFactor(BigDecimal discountFactor);
		PaymentDiscounting.PaymentDiscountingBuilder setPresentValueAmount(Money presentValueAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
			processRosetta(path.newSubPath("presentValueAmount"), processor, Money.MoneyBuilder.class, getPresentValueAmount());
		}
		

		PaymentDiscounting.PaymentDiscountingBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentDiscounting  ***********************/
	class PaymentDiscountingImpl implements PaymentDiscounting {
		private final BigDecimal discountFactor;
		private final Money presentValueAmount;
		
		protected PaymentDiscountingImpl(PaymentDiscounting.PaymentDiscountingBuilder builder) {
			this.discountFactor = builder.getDiscountFactor();
			this.presentValueAmount = ofNullable(builder.getPresentValueAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("presentValueAmount")
		public Money getPresentValueAmount() {
			return presentValueAmount;
		}
		
		@Override
		public PaymentDiscounting build() {
			return this;
		}
		
		@Override
		public PaymentDiscounting.PaymentDiscountingBuilder toBuilder() {
			PaymentDiscounting.PaymentDiscountingBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentDiscounting.PaymentDiscountingBuilder builder) {
			ofNullable(getDiscountFactor()).ifPresent(builder::setDiscountFactor);
			ofNullable(getPresentValueAmount()).ifPresent(builder::setPresentValueAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDiscounting _that = getType().cast(o);
		
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(presentValueAmount, _that.getPresentValueAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (presentValueAmount != null ? presentValueAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDiscounting {" +
				"discountFactor=" + this.discountFactor + ", " +
				"presentValueAmount=" + this.presentValueAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentDiscounting  ***********************/
	class PaymentDiscountingBuilderImpl implements PaymentDiscounting.PaymentDiscountingBuilder {
	
		protected BigDecimal discountFactor;
		protected Money.MoneyBuilder presentValueAmount;
	
		public PaymentDiscountingBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("presentValueAmount")
		public Money.MoneyBuilder getPresentValueAmount() {
			return presentValueAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePresentValueAmount() {
			Money.MoneyBuilder result;
			if (presentValueAmount!=null) {
				result = presentValueAmount;
			}
			else {
				result = presentValueAmount = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("discountFactor")
		public PaymentDiscounting.PaymentDiscountingBuilder setDiscountFactor(BigDecimal discountFactor) {
			this.discountFactor = discountFactor==null?null:discountFactor;
			return this;
		}
		@Override
		@RosettaAttribute("presentValueAmount")
		public PaymentDiscounting.PaymentDiscountingBuilder setPresentValueAmount(Money presentValueAmount) {
			this.presentValueAmount = presentValueAmount==null?null:presentValueAmount.toBuilder();
			return this;
		}
		
		@Override
		public PaymentDiscounting build() {
			return new PaymentDiscounting.PaymentDiscountingImpl(this);
		}
		
		@Override
		public PaymentDiscounting.PaymentDiscountingBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDiscounting.PaymentDiscountingBuilder prune() {
			if (presentValueAmount!=null && !presentValueAmount.prune().hasData()) presentValueAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDiscountFactor()!=null) return true;
			if (getPresentValueAmount()!=null && getPresentValueAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDiscounting.PaymentDiscountingBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentDiscounting.PaymentDiscountingBuilder o = (PaymentDiscounting.PaymentDiscountingBuilder) other;
			
			merger.mergeRosetta(getPresentValueAmount(), o.getPresentValueAmount(), this::setPresentValueAmount);
			
			merger.mergeBasic(getDiscountFactor(), o.getDiscountFactor(), this::setDiscountFactor);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDiscounting _that = getType().cast(o);
		
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(presentValueAmount, _that.getPresentValueAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (presentValueAmount != null ? presentValueAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDiscountingBuilder {" +
				"discountFactor=" + this.discountFactor + ", " +
				"presentValueAmount=" + this.presentValueAmount +
			'}';
		}
	}
}
