package cdm.product.template;

import cdm.product.template.AutomaticExercise;
import cdm.product.template.AutomaticExercise.AutomaticExerciseBuilder;
import cdm.product.template.AutomaticExercise.AutomaticExerciseBuilderImpl;
import cdm.product.template.AutomaticExercise.AutomaticExerciseImpl;
import cdm.product.template.meta.AutomaticExerciseMeta;
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
 * A type to define automatic exercise of a swaption. With automatic exercise the option is deemed to have exercised if it is in the money by more than the threshold amount on the exercise date.
 * @version ${project.version}
 */
@RosettaDataType(value="AutomaticExercise", builder=AutomaticExercise.AutomaticExerciseBuilderImpl.class, version="${project.version}")
public interface AutomaticExercise extends RosettaModelObject {

	AutomaticExerciseMeta metaData = new AutomaticExerciseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A threshold rate. The threshold of 0.10% would be represented as 0.001
	 */
	BigDecimal getThresholdRate();
	/**
	 * Boolean that indicates if it has an automaticExercise
	 */
	Boolean getIsApplicable();

	/*********************** Build Methods  ***********************/
	AutomaticExercise build();
	
	AutomaticExercise.AutomaticExerciseBuilder toBuilder();
	
	static AutomaticExercise.AutomaticExerciseBuilder builder() {
		return new AutomaticExercise.AutomaticExerciseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AutomaticExercise> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AutomaticExercise> getType() {
		return AutomaticExercise.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("thresholdRate"), BigDecimal.class, getThresholdRate(), this);
		processor.processBasic(path.newSubPath("isApplicable"), Boolean.class, getIsApplicable(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AutomaticExerciseBuilder extends AutomaticExercise, RosettaModelObjectBuilder {
		AutomaticExercise.AutomaticExerciseBuilder setThresholdRate(BigDecimal thresholdRate);
		AutomaticExercise.AutomaticExerciseBuilder setIsApplicable(Boolean isApplicable);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("thresholdRate"), BigDecimal.class, getThresholdRate(), this);
			processor.processBasic(path.newSubPath("isApplicable"), Boolean.class, getIsApplicable(), this);
		}
		

		AutomaticExercise.AutomaticExerciseBuilder prune();
	}

	/*********************** Immutable Implementation of AutomaticExercise  ***********************/
	class AutomaticExerciseImpl implements AutomaticExercise {
		private final BigDecimal thresholdRate;
		private final Boolean isApplicable;
		
		protected AutomaticExerciseImpl(AutomaticExercise.AutomaticExerciseBuilder builder) {
			this.thresholdRate = builder.getThresholdRate();
			this.isApplicable = builder.getIsApplicable();
		}
		
		@Override
		@RosettaAttribute("thresholdRate")
		public BigDecimal getThresholdRate() {
			return thresholdRate;
		}
		
		@Override
		@RosettaAttribute("isApplicable")
		public Boolean getIsApplicable() {
			return isApplicable;
		}
		
		@Override
		public AutomaticExercise build() {
			return this;
		}
		
		@Override
		public AutomaticExercise.AutomaticExerciseBuilder toBuilder() {
			AutomaticExercise.AutomaticExerciseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AutomaticExercise.AutomaticExerciseBuilder builder) {
			ofNullable(getThresholdRate()).ifPresent(builder::setThresholdRate);
			ofNullable(getIsApplicable()).ifPresent(builder::setIsApplicable);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AutomaticExercise _that = getType().cast(o);
		
			if (!Objects.equals(thresholdRate, _that.getThresholdRate())) return false;
			if (!Objects.equals(isApplicable, _that.getIsApplicable())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (thresholdRate != null ? thresholdRate.hashCode() : 0);
			_result = 31 * _result + (isApplicable != null ? isApplicable.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AutomaticExercise {" +
				"thresholdRate=" + this.thresholdRate + ", " +
				"isApplicable=" + this.isApplicable +
			'}';
		}
	}

	/*********************** Builder Implementation of AutomaticExercise  ***********************/
	class AutomaticExerciseBuilderImpl implements AutomaticExercise.AutomaticExerciseBuilder {
	
		protected BigDecimal thresholdRate;
		protected Boolean isApplicable;
	
		public AutomaticExerciseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("thresholdRate")
		public BigDecimal getThresholdRate() {
			return thresholdRate;
		}
		
		@Override
		@RosettaAttribute("isApplicable")
		public Boolean getIsApplicable() {
			return isApplicable;
		}
		
	
		@Override
		@RosettaAttribute("thresholdRate")
		public AutomaticExercise.AutomaticExerciseBuilder setThresholdRate(BigDecimal thresholdRate) {
			this.thresholdRate = thresholdRate==null?null:thresholdRate;
			return this;
		}
		@Override
		@RosettaAttribute("isApplicable")
		public AutomaticExercise.AutomaticExerciseBuilder setIsApplicable(Boolean isApplicable) {
			this.isApplicable = isApplicable==null?null:isApplicable;
			return this;
		}
		
		@Override
		public AutomaticExercise build() {
			return new AutomaticExercise.AutomaticExerciseImpl(this);
		}
		
		@Override
		public AutomaticExercise.AutomaticExerciseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AutomaticExercise.AutomaticExerciseBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getThresholdRate()!=null) return true;
			if (getIsApplicable()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AutomaticExercise.AutomaticExerciseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AutomaticExercise.AutomaticExerciseBuilder o = (AutomaticExercise.AutomaticExerciseBuilder) other;
			
			
			merger.mergeBasic(getThresholdRate(), o.getThresholdRate(), this::setThresholdRate);
			merger.mergeBasic(getIsApplicable(), o.getIsApplicable(), this::setIsApplicable);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AutomaticExercise _that = getType().cast(o);
		
			if (!Objects.equals(thresholdRate, _that.getThresholdRate())) return false;
			if (!Objects.equals(isApplicable, _that.getIsApplicable())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (thresholdRate != null ? thresholdRate.hashCode() : 0);
			_result = 31 * _result + (isApplicable != null ? isApplicable.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AutomaticExerciseBuilder {" +
				"thresholdRate=" + this.thresholdRate + ", " +
				"isApplicable=" + this.isApplicable +
			'}';
		}
	}
}
