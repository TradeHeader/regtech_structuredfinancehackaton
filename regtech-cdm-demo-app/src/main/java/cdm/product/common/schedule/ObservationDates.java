package cdm.product.common.schedule;

import cdm.base.datetime.PeriodicDates;
import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.ObservationDates.ObservationDatesBuilder;
import cdm.product.common.schedule.ObservationDates.ObservationDatesBuilderImpl;
import cdm.product.common.schedule.ObservationDates.ObservationDatesImpl;
import cdm.product.common.schedule.ObservationSchedule;
import cdm.product.common.schedule.ParametricDates;
import cdm.product.common.schedule.meta.ObservationDatesMeta;
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
 * Describes date details for a set of observation dates in parametric or non-parametric form.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationDates", builder=ObservationDates.ObservationDatesBuilderImpl.class, version="${project.version}")
public interface ObservationDates extends RosettaModelObject {

	ObservationDatesMeta metaData = new ObservationDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a schedule of dates (non-parametric) on which market observations take place, and allows for the optional definition of weights where applicable.  When no weight is specified, then weight of each date is assumed to be 1.0
	 */
	ObservationSchedule getObservationSchedule();
	/**
	 * Specifies the date range and frequency on which market observations take place.  Weights can be assigned to dates in the schedule by assigning the weight and corresponding observationReference in the observationSchedule.
	 */
	PeriodicDates getPeriodicSchedule();
	/**
	 * Specifies parametric terms to determine which days within a given calculation period the price would be observed. Typically associated with Commodities. 
	 */
	ParametricDates getParametricDates();

	/*********************** Build Methods  ***********************/
	ObservationDates build();
	
	ObservationDates.ObservationDatesBuilder toBuilder();
	
	static ObservationDates.ObservationDatesBuilder builder() {
		return new ObservationDates.ObservationDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationDates> getType() {
		return ObservationDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("observationSchedule"), processor, ObservationSchedule.class, getObservationSchedule());
		processRosetta(path.newSubPath("periodicSchedule"), processor, PeriodicDates.class, getPeriodicSchedule());
		processRosetta(path.newSubPath("parametricDates"), processor, ParametricDates.class, getParametricDates());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationDatesBuilder extends ObservationDates, RosettaModelObjectBuilder {
		ObservationSchedule.ObservationScheduleBuilder getOrCreateObservationSchedule();
		ObservationSchedule.ObservationScheduleBuilder getObservationSchedule();
		PeriodicDates.PeriodicDatesBuilder getOrCreatePeriodicSchedule();
		PeriodicDates.PeriodicDatesBuilder getPeriodicSchedule();
		ParametricDates.ParametricDatesBuilder getOrCreateParametricDates();
		ParametricDates.ParametricDatesBuilder getParametricDates();
		ObservationDates.ObservationDatesBuilder setObservationSchedule(ObservationSchedule observationSchedule);
		ObservationDates.ObservationDatesBuilder setPeriodicSchedule(PeriodicDates periodicSchedule);
		ObservationDates.ObservationDatesBuilder setParametricDates(ParametricDates parametricDates);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("observationSchedule"), processor, ObservationSchedule.ObservationScheduleBuilder.class, getObservationSchedule());
			processRosetta(path.newSubPath("periodicSchedule"), processor, PeriodicDates.PeriodicDatesBuilder.class, getPeriodicSchedule());
			processRosetta(path.newSubPath("parametricDates"), processor, ParametricDates.ParametricDatesBuilder.class, getParametricDates());
		}
		

		ObservationDates.ObservationDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationDates  ***********************/
	class ObservationDatesImpl implements ObservationDates {
		private final ObservationSchedule observationSchedule;
		private final PeriodicDates periodicSchedule;
		private final ParametricDates parametricDates;
		
		protected ObservationDatesImpl(ObservationDates.ObservationDatesBuilder builder) {
			this.observationSchedule = ofNullable(builder.getObservationSchedule()).map(f->f.build()).orElse(null);
			this.periodicSchedule = ofNullable(builder.getPeriodicSchedule()).map(f->f.build()).orElse(null);
			this.parametricDates = ofNullable(builder.getParametricDates()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observationSchedule")
		public ObservationSchedule getObservationSchedule() {
			return observationSchedule;
		}
		
		@Override
		@RosettaAttribute("periodicSchedule")
		public PeriodicDates getPeriodicSchedule() {
			return periodicSchedule;
		}
		
		@Override
		@RosettaAttribute("parametricDates")
		public ParametricDates getParametricDates() {
			return parametricDates;
		}
		
		@Override
		public ObservationDates build() {
			return this;
		}
		
		@Override
		public ObservationDates.ObservationDatesBuilder toBuilder() {
			ObservationDates.ObservationDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationDates.ObservationDatesBuilder builder) {
			ofNullable(getObservationSchedule()).ifPresent(builder::setObservationSchedule);
			ofNullable(getPeriodicSchedule()).ifPresent(builder::setPeriodicSchedule);
			ofNullable(getParametricDates()).ifPresent(builder::setParametricDates);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationDates _that = getType().cast(o);
		
			if (!Objects.equals(observationSchedule, _that.getObservationSchedule())) return false;
			if (!Objects.equals(periodicSchedule, _that.getPeriodicSchedule())) return false;
			if (!Objects.equals(parametricDates, _that.getParametricDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationSchedule != null ? observationSchedule.hashCode() : 0);
			_result = 31 * _result + (periodicSchedule != null ? periodicSchedule.hashCode() : 0);
			_result = 31 * _result + (parametricDates != null ? parametricDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationDates {" +
				"observationSchedule=" + this.observationSchedule + ", " +
				"periodicSchedule=" + this.periodicSchedule + ", " +
				"parametricDates=" + this.parametricDates +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationDates  ***********************/
	class ObservationDatesBuilderImpl implements ObservationDates.ObservationDatesBuilder {
	
		protected ObservationSchedule.ObservationScheduleBuilder observationSchedule;
		protected PeriodicDates.PeriodicDatesBuilder periodicSchedule;
		protected ParametricDates.ParametricDatesBuilder parametricDates;
	
		public ObservationDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observationSchedule")
		public ObservationSchedule.ObservationScheduleBuilder getObservationSchedule() {
			return observationSchedule;
		}
		
		@Override
		public ObservationSchedule.ObservationScheduleBuilder getOrCreateObservationSchedule() {
			ObservationSchedule.ObservationScheduleBuilder result;
			if (observationSchedule!=null) {
				result = observationSchedule;
			}
			else {
				result = observationSchedule = ObservationSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("periodicSchedule")
		public PeriodicDates.PeriodicDatesBuilder getPeriodicSchedule() {
			return periodicSchedule;
		}
		
		@Override
		public PeriodicDates.PeriodicDatesBuilder getOrCreatePeriodicSchedule() {
			PeriodicDates.PeriodicDatesBuilder result;
			if (periodicSchedule!=null) {
				result = periodicSchedule;
			}
			else {
				result = periodicSchedule = PeriodicDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("parametricDates")
		public ParametricDates.ParametricDatesBuilder getParametricDates() {
			return parametricDates;
		}
		
		@Override
		public ParametricDates.ParametricDatesBuilder getOrCreateParametricDates() {
			ParametricDates.ParametricDatesBuilder result;
			if (parametricDates!=null) {
				result = parametricDates;
			}
			else {
				result = parametricDates = ParametricDates.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("observationSchedule")
		public ObservationDates.ObservationDatesBuilder setObservationSchedule(ObservationSchedule observationSchedule) {
			this.observationSchedule = observationSchedule==null?null:observationSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("periodicSchedule")
		public ObservationDates.ObservationDatesBuilder setPeriodicSchedule(PeriodicDates periodicSchedule) {
			this.periodicSchedule = periodicSchedule==null?null:periodicSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("parametricDates")
		public ObservationDates.ObservationDatesBuilder setParametricDates(ParametricDates parametricDates) {
			this.parametricDates = parametricDates==null?null:parametricDates.toBuilder();
			return this;
		}
		
		@Override
		public ObservationDates build() {
			return new ObservationDates.ObservationDatesImpl(this);
		}
		
		@Override
		public ObservationDates.ObservationDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationDates.ObservationDatesBuilder prune() {
			if (observationSchedule!=null && !observationSchedule.prune().hasData()) observationSchedule = null;
			if (periodicSchedule!=null && !periodicSchedule.prune().hasData()) periodicSchedule = null;
			if (parametricDates!=null && !parametricDates.prune().hasData()) parametricDates = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservationSchedule()!=null && getObservationSchedule().hasData()) return true;
			if (getPeriodicSchedule()!=null && getPeriodicSchedule().hasData()) return true;
			if (getParametricDates()!=null && getParametricDates().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationDates.ObservationDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationDates.ObservationDatesBuilder o = (ObservationDates.ObservationDatesBuilder) other;
			
			merger.mergeRosetta(getObservationSchedule(), o.getObservationSchedule(), this::setObservationSchedule);
			merger.mergeRosetta(getPeriodicSchedule(), o.getPeriodicSchedule(), this::setPeriodicSchedule);
			merger.mergeRosetta(getParametricDates(), o.getParametricDates(), this::setParametricDates);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationDates _that = getType().cast(o);
		
			if (!Objects.equals(observationSchedule, _that.getObservationSchedule())) return false;
			if (!Objects.equals(periodicSchedule, _that.getPeriodicSchedule())) return false;
			if (!Objects.equals(parametricDates, _that.getParametricDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationSchedule != null ? observationSchedule.hashCode() : 0);
			_result = 31 * _result + (periodicSchedule != null ? periodicSchedule.hashCode() : 0);
			_result = 31 * _result + (parametricDates != null ? parametricDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationDatesBuilder {" +
				"observationSchedule=" + this.observationSchedule + ", " +
				"periodicSchedule=" + this.periodicSchedule + ", " +
				"parametricDates=" + this.parametricDates +
			'}';
		}
	}
}
