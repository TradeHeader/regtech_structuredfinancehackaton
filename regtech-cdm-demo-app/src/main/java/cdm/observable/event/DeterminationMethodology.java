package cdm.observable.event;

import cdm.base.math.AveragingCalculationMethodEnum;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.event.DeterminationMethodology;
import cdm.observable.event.DeterminationMethodology.DeterminationMethodologyBuilder;
import cdm.observable.event.DeterminationMethodology.DeterminationMethodologyBuilderImpl;
import cdm.observable.event.DeterminationMethodology.DeterminationMethodologyImpl;
import cdm.observable.event.meta.DeterminationMethodologyMeta;
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
 * Specifies the method according to which an amount or a date is determined.
 * @version ${project.version}
 */
@RosettaDataType(value="DeterminationMethodology", builder=DeterminationMethodology.DeterminationMethodologyBuilderImpl.class, version="${project.version}")
public interface DeterminationMethodology extends RosettaModelObject {

	DeterminationMethodologyMeta metaData = new DeterminationMethodologyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a more granular dimention of observation. Typically relevent for resolving a unique equity price, which can be expressed as trade-weighted or volume-weighted averages.
	 */
	DeterminationMethodEnum getDeterminationMethod();
	/**
	 * Specifies enumerations for the type of averaging calculation.
	 */
	AveragingCalculationMethodEnum getAveragingMethod();

	/*********************** Build Methods  ***********************/
	DeterminationMethodology build();
	
	DeterminationMethodology.DeterminationMethodologyBuilder toBuilder();
	
	static DeterminationMethodology.DeterminationMethodologyBuilder builder() {
		return new DeterminationMethodology.DeterminationMethodologyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DeterminationMethodology> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DeterminationMethodology> getType() {
		return DeterminationMethodology.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("determinationMethod"), DeterminationMethodEnum.class, getDeterminationMethod(), this);
		processor.processBasic(path.newSubPath("averagingMethod"), AveragingCalculationMethodEnum.class, getAveragingMethod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DeterminationMethodologyBuilder extends DeterminationMethodology, RosettaModelObjectBuilder {
		DeterminationMethodology.DeterminationMethodologyBuilder setDeterminationMethod(DeterminationMethodEnum determinationMethod);
		DeterminationMethodology.DeterminationMethodologyBuilder setAveragingMethod(AveragingCalculationMethodEnum averagingMethod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("determinationMethod"), DeterminationMethodEnum.class, getDeterminationMethod(), this);
			processor.processBasic(path.newSubPath("averagingMethod"), AveragingCalculationMethodEnum.class, getAveragingMethod(), this);
		}
		

		DeterminationMethodology.DeterminationMethodologyBuilder prune();
	}

	/*********************** Immutable Implementation of DeterminationMethodology  ***********************/
	class DeterminationMethodologyImpl implements DeterminationMethodology {
		private final DeterminationMethodEnum determinationMethod;
		private final AveragingCalculationMethodEnum averagingMethod;
		
		protected DeterminationMethodologyImpl(DeterminationMethodology.DeterminationMethodologyBuilder builder) {
			this.determinationMethod = builder.getDeterminationMethod();
			this.averagingMethod = builder.getAveragingMethod();
		}
		
		@Override
		@RosettaAttribute("determinationMethod")
		public DeterminationMethodEnum getDeterminationMethod() {
			return determinationMethod;
		}
		
		@Override
		@RosettaAttribute("averagingMethod")
		public AveragingCalculationMethodEnum getAveragingMethod() {
			return averagingMethod;
		}
		
		@Override
		public DeterminationMethodology build() {
			return this;
		}
		
		@Override
		public DeterminationMethodology.DeterminationMethodologyBuilder toBuilder() {
			DeterminationMethodology.DeterminationMethodologyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DeterminationMethodology.DeterminationMethodologyBuilder builder) {
			ofNullable(getDeterminationMethod()).ifPresent(builder::setDeterminationMethod);
			ofNullable(getAveragingMethod()).ifPresent(builder::setAveragingMethod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeterminationMethodology _that = getType().cast(o);
		
			if (!Objects.equals(determinationMethod, _that.getDeterminationMethod())) return false;
			if (!Objects.equals(averagingMethod, _that.getAveragingMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (determinationMethod != null ? determinationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (averagingMethod != null ? averagingMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeterminationMethodology {" +
				"determinationMethod=" + this.determinationMethod + ", " +
				"averagingMethod=" + this.averagingMethod +
			'}';
		}
	}

	/*********************** Builder Implementation of DeterminationMethodology  ***********************/
	class DeterminationMethodologyBuilderImpl implements DeterminationMethodology.DeterminationMethodologyBuilder {
	
		protected DeterminationMethodEnum determinationMethod;
		protected AveragingCalculationMethodEnum averagingMethod;
	
		public DeterminationMethodologyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("determinationMethod")
		public DeterminationMethodEnum getDeterminationMethod() {
			return determinationMethod;
		}
		
		@Override
		@RosettaAttribute("averagingMethod")
		public AveragingCalculationMethodEnum getAveragingMethod() {
			return averagingMethod;
		}
		
	
		@Override
		@RosettaAttribute("determinationMethod")
		public DeterminationMethodology.DeterminationMethodologyBuilder setDeterminationMethod(DeterminationMethodEnum determinationMethod) {
			this.determinationMethod = determinationMethod==null?null:determinationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("averagingMethod")
		public DeterminationMethodology.DeterminationMethodologyBuilder setAveragingMethod(AveragingCalculationMethodEnum averagingMethod) {
			this.averagingMethod = averagingMethod==null?null:averagingMethod;
			return this;
		}
		
		@Override
		public DeterminationMethodology build() {
			return new DeterminationMethodology.DeterminationMethodologyImpl(this);
		}
		
		@Override
		public DeterminationMethodology.DeterminationMethodologyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeterminationMethodology.DeterminationMethodologyBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDeterminationMethod()!=null) return true;
			if (getAveragingMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeterminationMethodology.DeterminationMethodologyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DeterminationMethodology.DeterminationMethodologyBuilder o = (DeterminationMethodology.DeterminationMethodologyBuilder) other;
			
			
			merger.mergeBasic(getDeterminationMethod(), o.getDeterminationMethod(), this::setDeterminationMethod);
			merger.mergeBasic(getAveragingMethod(), o.getAveragingMethod(), this::setAveragingMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeterminationMethodology _that = getType().cast(o);
		
			if (!Objects.equals(determinationMethod, _that.getDeterminationMethod())) return false;
			if (!Objects.equals(averagingMethod, _that.getAveragingMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (determinationMethod != null ? determinationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (averagingMethod != null ? averagingMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeterminationMethodologyBuilder {" +
				"determinationMethod=" + this.determinationMethod + ", " +
				"averagingMethod=" + this.averagingMethod +
			'}';
		}
	}
}
