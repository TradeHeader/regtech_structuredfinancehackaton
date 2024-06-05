package cdm.base.math;

import cdm.base.math.AveragingCalculationMethod;
import cdm.base.math.AveragingCalculationMethod.AveragingCalculationMethodBuilder;
import cdm.base.math.AveragingCalculationMethod.AveragingCalculationMethodBuilderImpl;
import cdm.base.math.AveragingCalculationMethod.AveragingCalculationMethodImpl;
import cdm.base.math.AveragingCalculationMethodEnum;
import cdm.base.math.meta.AveragingCalculationMethodMeta;
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
 * Defines the ways in which multiple values can be aggregated into a single value.
 * @version ${project.version}
 */
@RosettaDataType(value="AveragingCalculationMethod", builder=AveragingCalculationMethod.AveragingCalculationMethodBuilderImpl.class, version="${project.version}")
public interface AveragingCalculationMethod extends RosettaModelObject {

	AveragingCalculationMethodMeta metaData = new AveragingCalculationMethodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies whether the average values will be weighted or unweighted.
	 */
	Boolean getIsWeighted();
	/**
	 * Identifies which of the Pythagorean means is being used to compute an average value.
	 */
	AveragingCalculationMethodEnum getCalculationMethod();

	/*********************** Build Methods  ***********************/
	AveragingCalculationMethod build();
	
	AveragingCalculationMethod.AveragingCalculationMethodBuilder toBuilder();
	
	static AveragingCalculationMethod.AveragingCalculationMethodBuilder builder() {
		return new AveragingCalculationMethod.AveragingCalculationMethodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AveragingCalculationMethod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AveragingCalculationMethod> getType() {
		return AveragingCalculationMethod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("isWeighted"), Boolean.class, getIsWeighted(), this);
		processor.processBasic(path.newSubPath("calculationMethod"), AveragingCalculationMethodEnum.class, getCalculationMethod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AveragingCalculationMethodBuilder extends AveragingCalculationMethod, RosettaModelObjectBuilder {
		AveragingCalculationMethod.AveragingCalculationMethodBuilder setIsWeighted(Boolean isWeighted);
		AveragingCalculationMethod.AveragingCalculationMethodBuilder setCalculationMethod(AveragingCalculationMethodEnum calculationMethod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("isWeighted"), Boolean.class, getIsWeighted(), this);
			processor.processBasic(path.newSubPath("calculationMethod"), AveragingCalculationMethodEnum.class, getCalculationMethod(), this);
		}
		

		AveragingCalculationMethod.AveragingCalculationMethodBuilder prune();
	}

	/*********************** Immutable Implementation of AveragingCalculationMethod  ***********************/
	class AveragingCalculationMethodImpl implements AveragingCalculationMethod {
		private final Boolean isWeighted;
		private final AveragingCalculationMethodEnum calculationMethod;
		
		protected AveragingCalculationMethodImpl(AveragingCalculationMethod.AveragingCalculationMethodBuilder builder) {
			this.isWeighted = builder.getIsWeighted();
			this.calculationMethod = builder.getCalculationMethod();
		}
		
		@Override
		@RosettaAttribute("isWeighted")
		public Boolean getIsWeighted() {
			return isWeighted;
		}
		
		@Override
		@RosettaAttribute("calculationMethod")
		public AveragingCalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
		@Override
		public AveragingCalculationMethod build() {
			return this;
		}
		
		@Override
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder toBuilder() {
			AveragingCalculationMethod.AveragingCalculationMethodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AveragingCalculationMethod.AveragingCalculationMethodBuilder builder) {
			ofNullable(getIsWeighted()).ifPresent(builder::setIsWeighted);
			ofNullable(getCalculationMethod()).ifPresent(builder::setCalculationMethod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingCalculationMethod _that = getType().cast(o);
		
			if (!Objects.equals(isWeighted, _that.getIsWeighted())) return false;
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isWeighted != null ? isWeighted.hashCode() : 0);
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingCalculationMethod {" +
				"isWeighted=" + this.isWeighted + ", " +
				"calculationMethod=" + this.calculationMethod +
			'}';
		}
	}

	/*********************** Builder Implementation of AveragingCalculationMethod  ***********************/
	class AveragingCalculationMethodBuilderImpl implements AveragingCalculationMethod.AveragingCalculationMethodBuilder {
	
		protected Boolean isWeighted;
		protected AveragingCalculationMethodEnum calculationMethod;
	
		public AveragingCalculationMethodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("isWeighted")
		public Boolean getIsWeighted() {
			return isWeighted;
		}
		
		@Override
		@RosettaAttribute("calculationMethod")
		public AveragingCalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
	
		@Override
		@RosettaAttribute("isWeighted")
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder setIsWeighted(Boolean isWeighted) {
			this.isWeighted = isWeighted==null?null:isWeighted;
			return this;
		}
		@Override
		@RosettaAttribute("calculationMethod")
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder setCalculationMethod(AveragingCalculationMethodEnum calculationMethod) {
			this.calculationMethod = calculationMethod==null?null:calculationMethod;
			return this;
		}
		
		@Override
		public AveragingCalculationMethod build() {
			return new AveragingCalculationMethod.AveragingCalculationMethodImpl(this);
		}
		
		@Override
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIsWeighted()!=null) return true;
			if (getCalculationMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingCalculationMethod.AveragingCalculationMethodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AveragingCalculationMethod.AveragingCalculationMethodBuilder o = (AveragingCalculationMethod.AveragingCalculationMethodBuilder) other;
			
			
			merger.mergeBasic(getIsWeighted(), o.getIsWeighted(), this::setIsWeighted);
			merger.mergeBasic(getCalculationMethod(), o.getCalculationMethod(), this::setCalculationMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingCalculationMethod _that = getType().cast(o);
		
			if (!Objects.equals(isWeighted, _that.getIsWeighted())) return false;
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isWeighted != null ? isWeighted.hashCode() : 0);
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingCalculationMethodBuilder {" +
				"isWeighted=" + this.isWeighted + ", " +
				"calculationMethod=" + this.calculationMethod +
			'}';
		}
	}
}
