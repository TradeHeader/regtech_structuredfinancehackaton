package cdm.product.common.settlement;

import cdm.product.common.settlement.PaymentRule;
import cdm.product.common.settlement.PaymentRule.PaymentRuleBuilder;
import cdm.product.common.settlement.PaymentRule.PaymentRuleBuilderImpl;
import cdm.product.common.settlement.PaymentRule.PaymentRuleImpl;
import cdm.product.common.settlement.PercentageRule;
import cdm.product.common.settlement.meta.PaymentRuleMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class defining the payment calculation rule. As of FpML 5.10, percentage rule is the only calculation rule that has been specified as part of the standard.
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentRule", builder=PaymentRule.PaymentRuleBuilderImpl.class, version="${project.version}")
public interface PaymentRule extends RosettaModelObject {

	PaymentRuleMeta metaData = new PaymentRuleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This attribute is not present as part of the FpML construct, as the payment rule is specialised by means of runtime type extension through the xsi:type.
	 */
	PercentageRule getPercentageRule();

	/*********************** Build Methods  ***********************/
	PaymentRule build();
	
	PaymentRule.PaymentRuleBuilder toBuilder();
	
	static PaymentRule.PaymentRuleBuilder builder() {
		return new PaymentRule.PaymentRuleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentRule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentRule> getType() {
		return PaymentRule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("percentageRule"), processor, PercentageRule.class, getPercentageRule());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentRuleBuilder extends PaymentRule, RosettaModelObjectBuilder {
		PercentageRule.PercentageRuleBuilder getOrCreatePercentageRule();
		PercentageRule.PercentageRuleBuilder getPercentageRule();
		PaymentRule.PaymentRuleBuilder setPercentageRule(PercentageRule percentageRule);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("percentageRule"), processor, PercentageRule.PercentageRuleBuilder.class, getPercentageRule());
		}
		

		PaymentRule.PaymentRuleBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentRule  ***********************/
	class PaymentRuleImpl implements PaymentRule {
		private final PercentageRule percentageRule;
		
		protected PaymentRuleImpl(PaymentRule.PaymentRuleBuilder builder) {
			this.percentageRule = ofNullable(builder.getPercentageRule()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("percentageRule")
		public PercentageRule getPercentageRule() {
			return percentageRule;
		}
		
		@Override
		public PaymentRule build() {
			return this;
		}
		
		@Override
		public PaymentRule.PaymentRuleBuilder toBuilder() {
			PaymentRule.PaymentRuleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentRule.PaymentRuleBuilder builder) {
			ofNullable(getPercentageRule()).ifPresent(builder::setPercentageRule);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentRule _that = getType().cast(o);
		
			if (!Objects.equals(percentageRule, _that.getPercentageRule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (percentageRule != null ? percentageRule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentRule {" +
				"percentageRule=" + this.percentageRule +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentRule  ***********************/
	class PaymentRuleBuilderImpl implements PaymentRule.PaymentRuleBuilder {
	
		protected PercentageRule.PercentageRuleBuilder percentageRule;
	
		public PaymentRuleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("percentageRule")
		public PercentageRule.PercentageRuleBuilder getPercentageRule() {
			return percentageRule;
		}
		
		@Override
		public PercentageRule.PercentageRuleBuilder getOrCreatePercentageRule() {
			PercentageRule.PercentageRuleBuilder result;
			if (percentageRule!=null) {
				result = percentageRule;
			}
			else {
				result = percentageRule = PercentageRule.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("percentageRule")
		public PaymentRule.PaymentRuleBuilder setPercentageRule(PercentageRule percentageRule) {
			this.percentageRule = percentageRule==null?null:percentageRule.toBuilder();
			return this;
		}
		
		@Override
		public PaymentRule build() {
			return new PaymentRule.PaymentRuleImpl(this);
		}
		
		@Override
		public PaymentRule.PaymentRuleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentRule.PaymentRuleBuilder prune() {
			if (percentageRule!=null && !percentageRule.prune().hasData()) percentageRule = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPercentageRule()!=null && getPercentageRule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentRule.PaymentRuleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentRule.PaymentRuleBuilder o = (PaymentRule.PaymentRuleBuilder) other;
			
			merger.mergeRosetta(getPercentageRule(), o.getPercentageRule(), this::setPercentageRule);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentRule _that = getType().cast(o);
		
			if (!Objects.equals(percentageRule, _that.getPercentageRule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (percentageRule != null ? percentageRule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentRuleBuilder {" +
				"percentageRule=" + this.percentageRule +
			'}';
		}
	}
}
