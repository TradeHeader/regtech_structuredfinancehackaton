package cdm.observable.event;

import cdm.base.datetime.TimeZone;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.Observable;
import cdm.observable.event.DeterminationMethodology;
import cdm.observable.event.ObservationIdentifier;
import cdm.observable.event.ObservationIdentifier.ObservationIdentifierBuilder;
import cdm.observable.event.ObservationIdentifier.ObservationIdentifierBuilderImpl;
import cdm.observable.event.ObservationIdentifier.ObservationIdentifierImpl;
import cdm.observable.event.meta.ObservationIdentifierMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines the parameters needed to uniquely identify a piece of data among the population of all available market data.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationIdentifier", builder=ObservationIdentifier.ObservationIdentifierBuilderImpl.class, version="${project.version}")
public interface ObservationIdentifier extends RosettaModelObject {

	ObservationIdentifierMeta metaData = new ObservationIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the asset or rate to which the observation relates.
	 */
	Observable getObservable();
	/**
	 * Specifies the date value to use when resolving the market data.
	 */
	Date getObservationDate();
	/**
	 * Represents the time and time-zone.
	 */
	TimeZone getObservationTime();
	/**
	 * Represents where the market data published and should be observed.
	 */
	InformationSource getInformationSource();
	/**
	 * Specifies the method according to which an amount or a date is determined.
	 */
	DeterminationMethodology getDeterminationMethodology();

	/*********************** Build Methods  ***********************/
	ObservationIdentifier build();
	
	ObservationIdentifier.ObservationIdentifierBuilder toBuilder();
	
	static ObservationIdentifier.ObservationIdentifierBuilder builder() {
		return new ObservationIdentifier.ObservationIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationIdentifier> getType() {
		return ObservationIdentifier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("observable"), processor, Observable.class, getObservable());
		processor.processBasic(path.newSubPath("observationDate"), Date.class, getObservationDate(), this);
		processRosetta(path.newSubPath("observationTime"), processor, TimeZone.class, getObservationTime());
		processRosetta(path.newSubPath("informationSource"), processor, InformationSource.class, getInformationSource());
		processRosetta(path.newSubPath("determinationMethodology"), processor, DeterminationMethodology.class, getDeterminationMethodology());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationIdentifierBuilder extends ObservationIdentifier, RosettaModelObjectBuilder {
		Observable.ObservableBuilder getOrCreateObservable();
		Observable.ObservableBuilder getObservable();
		TimeZone.TimeZoneBuilder getOrCreateObservationTime();
		TimeZone.TimeZoneBuilder getObservationTime();
		InformationSource.InformationSourceBuilder getOrCreateInformationSource();
		InformationSource.InformationSourceBuilder getInformationSource();
		DeterminationMethodology.DeterminationMethodologyBuilder getOrCreateDeterminationMethodology();
		DeterminationMethodology.DeterminationMethodologyBuilder getDeterminationMethodology();
		ObservationIdentifier.ObservationIdentifierBuilder setObservable(Observable observable);
		ObservationIdentifier.ObservationIdentifierBuilder setObservationDate(Date observationDate);
		ObservationIdentifier.ObservationIdentifierBuilder setObservationTime(TimeZone observationTime);
		ObservationIdentifier.ObservationIdentifierBuilder setInformationSource(InformationSource informationSource);
		ObservationIdentifier.ObservationIdentifierBuilder setDeterminationMethodology(DeterminationMethodology determinationMethodology);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("observable"), processor, Observable.ObservableBuilder.class, getObservable());
			processor.processBasic(path.newSubPath("observationDate"), Date.class, getObservationDate(), this);
			processRosetta(path.newSubPath("observationTime"), processor, TimeZone.TimeZoneBuilder.class, getObservationTime());
			processRosetta(path.newSubPath("informationSource"), processor, InformationSource.InformationSourceBuilder.class, getInformationSource());
			processRosetta(path.newSubPath("determinationMethodology"), processor, DeterminationMethodology.DeterminationMethodologyBuilder.class, getDeterminationMethodology());
		}
		

		ObservationIdentifier.ObservationIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationIdentifier  ***********************/
	class ObservationIdentifierImpl implements ObservationIdentifier {
		private final Observable observable;
		private final Date observationDate;
		private final TimeZone observationTime;
		private final InformationSource informationSource;
		private final DeterminationMethodology determinationMethodology;
		
		protected ObservationIdentifierImpl(ObservationIdentifier.ObservationIdentifierBuilder builder) {
			this.observable = ofNullable(builder.getObservable()).map(f->f.build()).orElse(null);
			this.observationDate = builder.getObservationDate();
			this.observationTime = ofNullable(builder.getObservationTime()).map(f->f.build()).orElse(null);
			this.informationSource = ofNullable(builder.getInformationSource()).map(f->f.build()).orElse(null);
			this.determinationMethodology = ofNullable(builder.getDeterminationMethodology()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observable")
		public Observable getObservable() {
			return observable;
		}
		
		@Override
		@RosettaAttribute("observationDate")
		public Date getObservationDate() {
			return observationDate;
		}
		
		@Override
		@RosettaAttribute("observationTime")
		public TimeZone getObservationTime() {
			return observationTime;
		}
		
		@Override
		@RosettaAttribute("informationSource")
		public InformationSource getInformationSource() {
			return informationSource;
		}
		
		@Override
		@RosettaAttribute("determinationMethodology")
		public DeterminationMethodology getDeterminationMethodology() {
			return determinationMethodology;
		}
		
		@Override
		public ObservationIdentifier build() {
			return this;
		}
		
		@Override
		public ObservationIdentifier.ObservationIdentifierBuilder toBuilder() {
			ObservationIdentifier.ObservationIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationIdentifier.ObservationIdentifierBuilder builder) {
			ofNullable(getObservable()).ifPresent(builder::setObservable);
			ofNullable(getObservationDate()).ifPresent(builder::setObservationDate);
			ofNullable(getObservationTime()).ifPresent(builder::setObservationTime);
			ofNullable(getInformationSource()).ifPresent(builder::setInformationSource);
			ofNullable(getDeterminationMethodology()).ifPresent(builder::setDeterminationMethodology);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(observationDate, _that.getObservationDate())) return false;
			if (!Objects.equals(observationTime, _that.getObservationTime())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			if (!Objects.equals(determinationMethodology, _that.getDeterminationMethodology())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (observationDate != null ? observationDate.hashCode() : 0);
			_result = 31 * _result + (observationTime != null ? observationTime.hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			_result = 31 * _result + (determinationMethodology != null ? determinationMethodology.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationIdentifier {" +
				"observable=" + this.observable + ", " +
				"observationDate=" + this.observationDate + ", " +
				"observationTime=" + this.observationTime + ", " +
				"informationSource=" + this.informationSource + ", " +
				"determinationMethodology=" + this.determinationMethodology +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationIdentifier  ***********************/
	class ObservationIdentifierBuilderImpl implements ObservationIdentifier.ObservationIdentifierBuilder {
	
		protected Observable.ObservableBuilder observable;
		protected Date observationDate;
		protected TimeZone.TimeZoneBuilder observationTime;
		protected InformationSource.InformationSourceBuilder informationSource;
		protected DeterminationMethodology.DeterminationMethodologyBuilder determinationMethodology;
	
		public ObservationIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observable")
		public Observable.ObservableBuilder getObservable() {
			return observable;
		}
		
		@Override
		public Observable.ObservableBuilder getOrCreateObservable() {
			Observable.ObservableBuilder result;
			if (observable!=null) {
				result = observable;
			}
			else {
				result = observable = Observable.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationDate")
		public Date getObservationDate() {
			return observationDate;
		}
		
		@Override
		@RosettaAttribute("observationTime")
		public TimeZone.TimeZoneBuilder getObservationTime() {
			return observationTime;
		}
		
		@Override
		public TimeZone.TimeZoneBuilder getOrCreateObservationTime() {
			TimeZone.TimeZoneBuilder result;
			if (observationTime!=null) {
				result = observationTime;
			}
			else {
				result = observationTime = TimeZone.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("informationSource")
		public InformationSource.InformationSourceBuilder getInformationSource() {
			return informationSource;
		}
		
		@Override
		public InformationSource.InformationSourceBuilder getOrCreateInformationSource() {
			InformationSource.InformationSourceBuilder result;
			if (informationSource!=null) {
				result = informationSource;
			}
			else {
				result = informationSource = InformationSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("determinationMethodology")
		public DeterminationMethodology.DeterminationMethodologyBuilder getDeterminationMethodology() {
			return determinationMethodology;
		}
		
		@Override
		public DeterminationMethodology.DeterminationMethodologyBuilder getOrCreateDeterminationMethodology() {
			DeterminationMethodology.DeterminationMethodologyBuilder result;
			if (determinationMethodology!=null) {
				result = determinationMethodology;
			}
			else {
				result = determinationMethodology = DeterminationMethodology.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("observable")
		public ObservationIdentifier.ObservationIdentifierBuilder setObservable(Observable observable) {
			this.observable = observable==null?null:observable.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationDate")
		public ObservationIdentifier.ObservationIdentifierBuilder setObservationDate(Date observationDate) {
			this.observationDate = observationDate==null?null:observationDate;
			return this;
		}
		@Override
		@RosettaAttribute("observationTime")
		public ObservationIdentifier.ObservationIdentifierBuilder setObservationTime(TimeZone observationTime) {
			this.observationTime = observationTime==null?null:observationTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("informationSource")
		public ObservationIdentifier.ObservationIdentifierBuilder setInformationSource(InformationSource informationSource) {
			this.informationSource = informationSource==null?null:informationSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("determinationMethodology")
		public ObservationIdentifier.ObservationIdentifierBuilder setDeterminationMethodology(DeterminationMethodology determinationMethodology) {
			this.determinationMethodology = determinationMethodology==null?null:determinationMethodology.toBuilder();
			return this;
		}
		
		@Override
		public ObservationIdentifier build() {
			return new ObservationIdentifier.ObservationIdentifierImpl(this);
		}
		
		@Override
		public ObservationIdentifier.ObservationIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationIdentifier.ObservationIdentifierBuilder prune() {
			if (observable!=null && !observable.prune().hasData()) observable = null;
			if (observationTime!=null && !observationTime.prune().hasData()) observationTime = null;
			if (informationSource!=null && !informationSource.prune().hasData()) informationSource = null;
			if (determinationMethodology!=null && !determinationMethodology.prune().hasData()) determinationMethodology = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservable()!=null && getObservable().hasData()) return true;
			if (getObservationDate()!=null) return true;
			if (getObservationTime()!=null && getObservationTime().hasData()) return true;
			if (getInformationSource()!=null && getInformationSource().hasData()) return true;
			if (getDeterminationMethodology()!=null && getDeterminationMethodology().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationIdentifier.ObservationIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationIdentifier.ObservationIdentifierBuilder o = (ObservationIdentifier.ObservationIdentifierBuilder) other;
			
			merger.mergeRosetta(getObservable(), o.getObservable(), this::setObservable);
			merger.mergeRosetta(getObservationTime(), o.getObservationTime(), this::setObservationTime);
			merger.mergeRosetta(getInformationSource(), o.getInformationSource(), this::setInformationSource);
			merger.mergeRosetta(getDeterminationMethodology(), o.getDeterminationMethodology(), this::setDeterminationMethodology);
			
			merger.mergeBasic(getObservationDate(), o.getObservationDate(), this::setObservationDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(observationDate, _that.getObservationDate())) return false;
			if (!Objects.equals(observationTime, _that.getObservationTime())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			if (!Objects.equals(determinationMethodology, _that.getDeterminationMethodology())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (observationDate != null ? observationDate.hashCode() : 0);
			_result = 31 * _result + (observationTime != null ? observationTime.hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			_result = 31 * _result + (determinationMethodology != null ? determinationMethodology.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationIdentifierBuilder {" +
				"observable=" + this.observable + ", " +
				"observationDate=" + this.observationDate + ", " +
				"observationTime=" + this.observationTime + ", " +
				"informationSource=" + this.informationSource + ", " +
				"determinationMethodology=" + this.determinationMethodology +
			'}';
		}
	}
}
