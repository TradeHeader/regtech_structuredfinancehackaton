package cdm.product.asset;

import cdm.product.asset.FloatingAmountProvisions;
import cdm.product.asset.FloatingAmountProvisions.FloatingAmountProvisionsBuilder;
import cdm.product.asset.FloatingAmountProvisions.FloatingAmountProvisionsBuilderImpl;
import cdm.product.asset.FloatingAmountProvisions.FloatingAmountProvisionsImpl;
import cdm.product.asset.meta.FloatingAmountProvisionsMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingAmountProvisions", builder=FloatingAmountProvisions.FloatingAmountProvisionsBuilderImpl.class, version="${project.version}")
public interface FloatingAmountProvisions extends RosettaModelObject {

	FloatingAmountProvisionsMeta metaData = new FloatingAmountProvisionsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * As specified by the ISDA Supplement for use with trades on mortgage-backed securities, &#39;WAC Cap&#39; means a weighted average coupon or weighted average rate cap provision (however defined in the Underlying Instruments) of the Underlying Instruments that limits, increases or decreases the interest rate or interest entitlement, as set out in the Underlying Instruments on the Effective Date without regard to any subsequent amendment The presence of the element with value set to &#39;true&#39; signifies that the provision is applicable. From a usage standpoint, this provision is typically applicable in the case of CMBS and not applicable in case of RMBS trades.
	 */
	Boolean getWacCapInterestProvision();
	/**
	 * As specified by the ISDA Standard Terms Supplement for use with trades on mortgage-backed securities. The presence of the element with value set to &#39;true&#39; signifies that the provision is applicable. If applicable, the applicable step-up terms are specified as part of that ISDA Standard Terms Supplement. From a usage standpoint, this provision is typically applicable in the case of RMBS and not applicable in case of CMBS trades.
	 */
	Boolean getStepUpProvision();

	/*********************** Build Methods  ***********************/
	FloatingAmountProvisions build();
	
	FloatingAmountProvisions.FloatingAmountProvisionsBuilder toBuilder();
	
	static FloatingAmountProvisions.FloatingAmountProvisionsBuilder builder() {
		return new FloatingAmountProvisions.FloatingAmountProvisionsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingAmountProvisions> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingAmountProvisions> getType() {
		return FloatingAmountProvisions.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("wacCapInterestProvision"), Boolean.class, getWacCapInterestProvision(), this);
		processor.processBasic(path.newSubPath("stepUpProvision"), Boolean.class, getStepUpProvision(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingAmountProvisionsBuilder extends FloatingAmountProvisions, RosettaModelObjectBuilder {
		FloatingAmountProvisions.FloatingAmountProvisionsBuilder setWacCapInterestProvision(Boolean wacCapInterestProvision);
		FloatingAmountProvisions.FloatingAmountProvisionsBuilder setStepUpProvision(Boolean stepUpProvision);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("wacCapInterestProvision"), Boolean.class, getWacCapInterestProvision(), this);
			processor.processBasic(path.newSubPath("stepUpProvision"), Boolean.class, getStepUpProvision(), this);
		}
		

		FloatingAmountProvisions.FloatingAmountProvisionsBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingAmountProvisions  ***********************/
	class FloatingAmountProvisionsImpl implements FloatingAmountProvisions {
		private final Boolean wacCapInterestProvision;
		private final Boolean stepUpProvision;
		
		protected FloatingAmountProvisionsImpl(FloatingAmountProvisions.FloatingAmountProvisionsBuilder builder) {
			this.wacCapInterestProvision = builder.getWacCapInterestProvision();
			this.stepUpProvision = builder.getStepUpProvision();
		}
		
		@Override
		@RosettaAttribute("wacCapInterestProvision")
		public Boolean getWacCapInterestProvision() {
			return wacCapInterestProvision;
		}
		
		@Override
		@RosettaAttribute("stepUpProvision")
		public Boolean getStepUpProvision() {
			return stepUpProvision;
		}
		
		@Override
		public FloatingAmountProvisions build() {
			return this;
		}
		
		@Override
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder toBuilder() {
			FloatingAmountProvisions.FloatingAmountProvisionsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingAmountProvisions.FloatingAmountProvisionsBuilder builder) {
			ofNullable(getWacCapInterestProvision()).ifPresent(builder::setWacCapInterestProvision);
			ofNullable(getStepUpProvision()).ifPresent(builder::setStepUpProvision);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountProvisions _that = getType().cast(o);
		
			if (!Objects.equals(wacCapInterestProvision, _that.getWacCapInterestProvision())) return false;
			if (!Objects.equals(stepUpProvision, _that.getStepUpProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (wacCapInterestProvision != null ? wacCapInterestProvision.hashCode() : 0);
			_result = 31 * _result + (stepUpProvision != null ? stepUpProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountProvisions {" +
				"wacCapInterestProvision=" + this.wacCapInterestProvision + ", " +
				"stepUpProvision=" + this.stepUpProvision +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingAmountProvisions  ***********************/
	class FloatingAmountProvisionsBuilderImpl implements FloatingAmountProvisions.FloatingAmountProvisionsBuilder {
	
		protected Boolean wacCapInterestProvision;
		protected Boolean stepUpProvision;
	
		public FloatingAmountProvisionsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("wacCapInterestProvision")
		public Boolean getWacCapInterestProvision() {
			return wacCapInterestProvision;
		}
		
		@Override
		@RosettaAttribute("stepUpProvision")
		public Boolean getStepUpProvision() {
			return stepUpProvision;
		}
		
	
		@Override
		@RosettaAttribute("wacCapInterestProvision")
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder setWacCapInterestProvision(Boolean wacCapInterestProvision) {
			this.wacCapInterestProvision = wacCapInterestProvision==null?null:wacCapInterestProvision;
			return this;
		}
		@Override
		@RosettaAttribute("stepUpProvision")
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder setStepUpProvision(Boolean stepUpProvision) {
			this.stepUpProvision = stepUpProvision==null?null:stepUpProvision;
			return this;
		}
		
		@Override
		public FloatingAmountProvisions build() {
			return new FloatingAmountProvisions.FloatingAmountProvisionsImpl(this);
		}
		
		@Override
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getWacCapInterestProvision()!=null) return true;
			if (getStepUpProvision()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingAmountProvisions.FloatingAmountProvisionsBuilder o = (FloatingAmountProvisions.FloatingAmountProvisionsBuilder) other;
			
			
			merger.mergeBasic(getWacCapInterestProvision(), o.getWacCapInterestProvision(), this::setWacCapInterestProvision);
			merger.mergeBasic(getStepUpProvision(), o.getStepUpProvision(), this::setStepUpProvision);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountProvisions _that = getType().cast(o);
		
			if (!Objects.equals(wacCapInterestProvision, _that.getWacCapInterestProvision())) return false;
			if (!Objects.equals(stepUpProvision, _that.getStepUpProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (wacCapInterestProvision != null ? wacCapInterestProvision.hashCode() : 0);
			_result = 31 * _result + (stepUpProvision != null ? stepUpProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountProvisionsBuilder {" +
				"wacCapInterestProvision=" + this.wacCapInterestProvision + ", " +
				"stepUpProvision=" + this.stepUpProvision +
			'}';
		}
	}
}
