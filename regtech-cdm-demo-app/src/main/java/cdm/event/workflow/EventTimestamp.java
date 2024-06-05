package cdm.event.workflow;

import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.EventTimestamp.EventTimestampBuilder;
import cdm.event.workflow.EventTimestamp.EventTimestampBuilderImpl;
import cdm.event.workflow.EventTimestamp.EventTimestampImpl;
import cdm.event.workflow.EventTimestampQualificationEnum;
import cdm.event.workflow.meta.EventTimestampMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to represent the various set of timestamps that can be associated with lifecycle events, as a collection of [dateTime, qualifier].
 * @version ${project.version}
 */
@RosettaDataType(value="EventTimestamp", builder=EventTimestamp.EventTimestampBuilderImpl.class, version="${project.version}")
public interface EventTimestamp extends RosettaModelObject {

	EventTimestampMeta metaData = new EventTimestampMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The CDM specifies that the zoned date time is to be expressed in accordance with ISO 8601, either as UTC as an offset to UTC.
	 */
	ZonedDateTime getDateTime();
	/**
	 * The timestamp qualifier is specified through an enumeration because the experience of integrating the DTCC and CME data representations suggests that a wide set of timestamps are currently utilized among service providers, while there is not at present an objective set of criteria that could help suggest a defined set of timestamps as part of the CDM. At some future point, one possible baseline could be developed from the review of the set of timestamps specified across regulatory regimes and regulations (incl. regulations such as high frequency trading). Also, the integration with a further set of implementations and the specification of business workflows such as clearing as part of the CDM development should help confirm the implementation approach in this respect.
	 */
	EventTimestampQualificationEnum getQualification();

	/*********************** Build Methods  ***********************/
	EventTimestamp build();
	
	EventTimestamp.EventTimestampBuilder toBuilder();
	
	static EventTimestamp.EventTimestampBuilder builder() {
		return new EventTimestamp.EventTimestampBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EventTimestamp> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EventTimestamp> getType() {
		return EventTimestamp.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("dateTime"), ZonedDateTime.class, getDateTime(), this);
		processor.processBasic(path.newSubPath("qualification"), EventTimestampQualificationEnum.class, getQualification(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface EventTimestampBuilder extends EventTimestamp, RosettaModelObjectBuilder {
		EventTimestamp.EventTimestampBuilder setDateTime(ZonedDateTime dateTime);
		EventTimestamp.EventTimestampBuilder setQualification(EventTimestampQualificationEnum qualification);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("dateTime"), ZonedDateTime.class, getDateTime(), this);
			processor.processBasic(path.newSubPath("qualification"), EventTimestampQualificationEnum.class, getQualification(), this);
		}
		

		EventTimestamp.EventTimestampBuilder prune();
	}

	/*********************** Immutable Implementation of EventTimestamp  ***********************/
	class EventTimestampImpl implements EventTimestamp {
		private final ZonedDateTime dateTime;
		private final EventTimestampQualificationEnum qualification;
		
		protected EventTimestampImpl(EventTimestamp.EventTimestampBuilder builder) {
			this.dateTime = builder.getDateTime();
			this.qualification = builder.getQualification();
		}
		
		@Override
		@RosettaAttribute("dateTime")
		public ZonedDateTime getDateTime() {
			return dateTime;
		}
		
		@Override
		@RosettaAttribute("qualification")
		public EventTimestampQualificationEnum getQualification() {
			return qualification;
		}
		
		@Override
		public EventTimestamp build() {
			return this;
		}
		
		@Override
		public EventTimestamp.EventTimestampBuilder toBuilder() {
			EventTimestamp.EventTimestampBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EventTimestamp.EventTimestampBuilder builder) {
			ofNullable(getDateTime()).ifPresent(builder::setDateTime);
			ofNullable(getQualification()).ifPresent(builder::setQualification);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EventTimestamp _that = getType().cast(o);
		
			if (!Objects.equals(dateTime, _that.getDateTime())) return false;
			if (!Objects.equals(qualification, _that.getQualification())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dateTime != null ? dateTime.hashCode() : 0);
			_result = 31 * _result + (qualification != null ? qualification.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EventTimestamp {" +
				"dateTime=" + this.dateTime + ", " +
				"qualification=" + this.qualification +
			'}';
		}
	}

	/*********************** Builder Implementation of EventTimestamp  ***********************/
	class EventTimestampBuilderImpl implements EventTimestamp.EventTimestampBuilder {
	
		protected ZonedDateTime dateTime;
		protected EventTimestampQualificationEnum qualification;
	
		public EventTimestampBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("dateTime")
		public ZonedDateTime getDateTime() {
			return dateTime;
		}
		
		@Override
		@RosettaAttribute("qualification")
		public EventTimestampQualificationEnum getQualification() {
			return qualification;
		}
		
	
		@Override
		@RosettaAttribute("dateTime")
		public EventTimestamp.EventTimestampBuilder setDateTime(ZonedDateTime dateTime) {
			this.dateTime = dateTime==null?null:dateTime;
			return this;
		}
		@Override
		@RosettaAttribute("qualification")
		public EventTimestamp.EventTimestampBuilder setQualification(EventTimestampQualificationEnum qualification) {
			this.qualification = qualification==null?null:qualification;
			return this;
		}
		
		@Override
		public EventTimestamp build() {
			return new EventTimestamp.EventTimestampImpl(this);
		}
		
		@Override
		public EventTimestamp.EventTimestampBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EventTimestamp.EventTimestampBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDateTime()!=null) return true;
			if (getQualification()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EventTimestamp.EventTimestampBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EventTimestamp.EventTimestampBuilder o = (EventTimestamp.EventTimestampBuilder) other;
			
			
			merger.mergeBasic(getDateTime(), o.getDateTime(), this::setDateTime);
			merger.mergeBasic(getQualification(), o.getQualification(), this::setQualification);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EventTimestamp _that = getType().cast(o);
		
			if (!Objects.equals(dateTime, _that.getDateTime())) return false;
			if (!Objects.equals(qualification, _that.getQualification())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dateTime != null ? dateTime.hashCode() : 0);
			_result = 31 * _result + (qualification != null ? qualification.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EventTimestampBuilder {" +
				"dateTime=" + this.dateTime + ", " +
				"qualification=" + this.qualification +
			'}';
		}
	}
}
