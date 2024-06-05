package cdm.product.common.settlement;

import cdm.observable.asset.Money;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder;
import cdm.product.common.settlement.PercentageRule;
import cdm.product.common.settlement.PercentageRule.PercentageRuleBuilder;
import cdm.product.common.settlement.PercentageRule.PercentageRuleBuilderImpl;
import cdm.product.common.settlement.PercentageRule.PercentageRuleImpl;
import cdm.product.common.settlement.meta.PercentageRuleMeta;
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
 * A class defining a content model for a calculation rule defined as percentage of the notional amount.
 * @version ${project.version}
 */
@RosettaDataType(value="PercentageRule", builder=PercentageRule.PercentageRuleBuilderImpl.class, version="${project.version}")
public interface PercentageRule extends RosettaModelObject {

	PercentageRuleMeta metaData = new PercentageRuleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A percentage of the notional amount.
	 */
	BigDecimal getPaymentPercent();
	/**
	 * A reference to the notional amount.
	 */
	ReferenceWithMetaMoney getNotionalAmountReference();

	/*********************** Build Methods  ***********************/
	PercentageRule build();
	
	PercentageRule.PercentageRuleBuilder toBuilder();
	
	static PercentageRule.PercentageRuleBuilder builder() {
		return new PercentageRule.PercentageRuleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PercentageRule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PercentageRule> getType() {
		return PercentageRule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("paymentPercent"), BigDecimal.class, getPaymentPercent(), this);
		processRosetta(path.newSubPath("notionalAmountReference"), processor, ReferenceWithMetaMoney.class, getNotionalAmountReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PercentageRuleBuilder extends PercentageRule, RosettaModelObjectBuilder {
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionalAmountReference();
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionalAmountReference();
		PercentageRule.PercentageRuleBuilder setPaymentPercent(BigDecimal paymentPercent);
		PercentageRule.PercentageRuleBuilder setNotionalAmountReference(ReferenceWithMetaMoney notionalAmountReference0);
		PercentageRule.PercentageRuleBuilder setNotionalAmountReferenceValue(Money notionalAmountReference1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("paymentPercent"), BigDecimal.class, getPaymentPercent(), this);
			processRosetta(path.newSubPath("notionalAmountReference"), processor, ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder.class, getNotionalAmountReference());
		}
		

		PercentageRule.PercentageRuleBuilder prune();
	}

	/*********************** Immutable Implementation of PercentageRule  ***********************/
	class PercentageRuleImpl implements PercentageRule {
		private final BigDecimal paymentPercent;
		private final ReferenceWithMetaMoney notionalAmountReference;
		
		protected PercentageRuleImpl(PercentageRule.PercentageRuleBuilder builder) {
			this.paymentPercent = builder.getPaymentPercent();
			this.notionalAmountReference = ofNullable(builder.getNotionalAmountReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("paymentPercent")
		public BigDecimal getPaymentPercent() {
			return paymentPercent;
		}
		
		@Override
		@RosettaAttribute("notionalAmountReference")
		public ReferenceWithMetaMoney getNotionalAmountReference() {
			return notionalAmountReference;
		}
		
		@Override
		public PercentageRule build() {
			return this;
		}
		
		@Override
		public PercentageRule.PercentageRuleBuilder toBuilder() {
			PercentageRule.PercentageRuleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PercentageRule.PercentageRuleBuilder builder) {
			ofNullable(getPaymentPercent()).ifPresent(builder::setPaymentPercent);
			ofNullable(getNotionalAmountReference()).ifPresent(builder::setNotionalAmountReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PercentageRule _that = getType().cast(o);
		
			if (!Objects.equals(paymentPercent, _that.getPaymentPercent())) return false;
			if (!Objects.equals(notionalAmountReference, _that.getNotionalAmountReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentPercent != null ? paymentPercent.hashCode() : 0);
			_result = 31 * _result + (notionalAmountReference != null ? notionalAmountReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PercentageRule {" +
				"paymentPercent=" + this.paymentPercent + ", " +
				"notionalAmountReference=" + this.notionalAmountReference +
			'}';
		}
	}

	/*********************** Builder Implementation of PercentageRule  ***********************/
	class PercentageRuleBuilderImpl implements PercentageRule.PercentageRuleBuilder {
	
		protected BigDecimal paymentPercent;
		protected ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder notionalAmountReference;
	
		public PercentageRuleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("paymentPercent")
		public BigDecimal getPaymentPercent() {
			return paymentPercent;
		}
		
		@Override
		@RosettaAttribute("notionalAmountReference")
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionalAmountReference() {
			return notionalAmountReference;
		}
		
		@Override
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionalAmountReference() {
			ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder result;
			if (notionalAmountReference!=null) {
				result = notionalAmountReference;
			}
			else {
				result = notionalAmountReference = ReferenceWithMetaMoney.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("paymentPercent")
		public PercentageRule.PercentageRuleBuilder setPaymentPercent(BigDecimal paymentPercent) {
			this.paymentPercent = paymentPercent==null?null:paymentPercent;
			return this;
		}
		@Override
		@RosettaAttribute("notionalAmountReference")
		public PercentageRule.PercentageRuleBuilder setNotionalAmountReference(ReferenceWithMetaMoney notionalAmountReference) {
			this.notionalAmountReference = notionalAmountReference==null?null:notionalAmountReference.toBuilder();
			return this;
		}
		@Override
		public PercentageRule.PercentageRuleBuilder setNotionalAmountReferenceValue(Money notionalAmountReference) {
			this.getOrCreateNotionalAmountReference().setValue(notionalAmountReference);
			return this;
		}
		
		@Override
		public PercentageRule build() {
			return new PercentageRule.PercentageRuleImpl(this);
		}
		
		@Override
		public PercentageRule.PercentageRuleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PercentageRule.PercentageRuleBuilder prune() {
			if (notionalAmountReference!=null && !notionalAmountReference.prune().hasData()) notionalAmountReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPaymentPercent()!=null) return true;
			if (getNotionalAmountReference()!=null && getNotionalAmountReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PercentageRule.PercentageRuleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PercentageRule.PercentageRuleBuilder o = (PercentageRule.PercentageRuleBuilder) other;
			
			merger.mergeRosetta(getNotionalAmountReference(), o.getNotionalAmountReference(), this::setNotionalAmountReference);
			
			merger.mergeBasic(getPaymentPercent(), o.getPaymentPercent(), this::setPaymentPercent);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PercentageRule _that = getType().cast(o);
		
			if (!Objects.equals(paymentPercent, _that.getPaymentPercent())) return false;
			if (!Objects.equals(notionalAmountReference, _that.getNotionalAmountReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentPercent != null ? paymentPercent.hashCode() : 0);
			_result = 31 * _result + (notionalAmountReference != null ? notionalAmountReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PercentageRuleBuilder {" +
				"paymentPercent=" + this.paymentPercent + ", " +
				"notionalAmountReference=" + this.notionalAmountReference +
			'}';
		}
	}
}
