package cdm.base.datetime;

import cdm.base.datetime.TimeZone;
import cdm.base.datetime.TimeZone.TimeZoneBuilder;
import cdm.base.datetime.TimeZone.TimeZoneBuilderImpl;
import cdm.base.datetime.TimeZone.TimeZoneImpl;
import cdm.base.datetime.meta.TimeZoneMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.time.LocalTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The time alongside with the timezone location information. This class makes use of the FpML TimezoneLocation construct.
 * @version ${project.version}
 */
@RosettaDataType(value="TimeZone", builder=TimeZone.TimeZoneBuilderImpl.class, version="${project.version}")
public interface TimeZone extends RosettaModelObject {

	TimeZoneMeta metaData = new TimeZoneMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The observation time.
	 */
	LocalTime getTime();
	/**
	 * FpML specifies the timezoneLocationScheme by reference to the Time Zone Database (a.k.a. tz database) maintained by IANA, the Internet Assigned Numbers Authority.
	 */
	FieldWithMetaString getLocation();

	/*********************** Build Methods  ***********************/
	TimeZone build();
	
	TimeZone.TimeZoneBuilder toBuilder();
	
	static TimeZone.TimeZoneBuilder builder() {
		return new TimeZone.TimeZoneBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TimeZone> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TimeZone> getType() {
		return TimeZone.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("time"), LocalTime.class, getTime(), this);
		processRosetta(path.newSubPath("location"), processor, FieldWithMetaString.class, getLocation());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TimeZoneBuilder extends TimeZone, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLocation();
		FieldWithMetaString.FieldWithMetaStringBuilder getLocation();
		TimeZone.TimeZoneBuilder setTime(LocalTime time);
		TimeZone.TimeZoneBuilder setLocation(FieldWithMetaString location0);
		TimeZone.TimeZoneBuilder setLocationValue(String location1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("time"), LocalTime.class, getTime(), this);
			processRosetta(path.newSubPath("location"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getLocation());
		}
		

		TimeZone.TimeZoneBuilder prune();
	}

	/*********************** Immutable Implementation of TimeZone  ***********************/
	class TimeZoneImpl implements TimeZone {
		private final LocalTime time;
		private final FieldWithMetaString location;
		
		protected TimeZoneImpl(TimeZone.TimeZoneBuilder builder) {
			this.time = builder.getTime();
			this.location = ofNullable(builder.getLocation()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("time")
		public LocalTime getTime() {
			return time;
		}
		
		@Override
		@RosettaAttribute("location")
		public FieldWithMetaString getLocation() {
			return location;
		}
		
		@Override
		public TimeZone build() {
			return this;
		}
		
		@Override
		public TimeZone.TimeZoneBuilder toBuilder() {
			TimeZone.TimeZoneBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TimeZone.TimeZoneBuilder builder) {
			ofNullable(getTime()).ifPresent(builder::setTime);
			ofNullable(getLocation()).ifPresent(builder::setLocation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TimeZone _that = getType().cast(o);
		
			if (!Objects.equals(time, _that.getTime())) return false;
			if (!Objects.equals(location, _that.getLocation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (time != null ? time.hashCode() : 0);
			_result = 31 * _result + (location != null ? location.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TimeZone {" +
				"time=" + this.time + ", " +
				"location=" + this.location +
			'}';
		}
	}

	/*********************** Builder Implementation of TimeZone  ***********************/
	class TimeZoneBuilderImpl implements TimeZone.TimeZoneBuilder {
	
		protected LocalTime time;
		protected FieldWithMetaString.FieldWithMetaStringBuilder location;
	
		public TimeZoneBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("time")
		public LocalTime getTime() {
			return time;
		}
		
		@Override
		@RosettaAttribute("location")
		public FieldWithMetaString.FieldWithMetaStringBuilder getLocation() {
			return location;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLocation() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (location!=null) {
				result = location;
			}
			else {
				result = location = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("time")
		public TimeZone.TimeZoneBuilder setTime(LocalTime time) {
			this.time = time==null?null:time;
			return this;
		}
		@Override
		@RosettaAttribute("location")
		public TimeZone.TimeZoneBuilder setLocation(FieldWithMetaString location) {
			this.location = location==null?null:location.toBuilder();
			return this;
		}
		@Override
		public TimeZone.TimeZoneBuilder setLocationValue(String location) {
			this.getOrCreateLocation().setValue(location);
			return this;
		}
		
		@Override
		public TimeZone build() {
			return new TimeZone.TimeZoneImpl(this);
		}
		
		@Override
		public TimeZone.TimeZoneBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TimeZone.TimeZoneBuilder prune() {
			if (location!=null && !location.prune().hasData()) location = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTime()!=null) return true;
			if (getLocation()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TimeZone.TimeZoneBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TimeZone.TimeZoneBuilder o = (TimeZone.TimeZoneBuilder) other;
			
			merger.mergeRosetta(getLocation(), o.getLocation(), this::setLocation);
			
			merger.mergeBasic(getTime(), o.getTime(), this::setTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TimeZone _that = getType().cast(o);
		
			if (!Objects.equals(time, _that.getTime())) return false;
			if (!Objects.equals(location, _that.getLocation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (time != null ? time.hashCode() : 0);
			_result = 31 * _result + (location != null ? location.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TimeZoneBuilder {" +
				"time=" + this.time + ", " +
				"location=" + this.location +
			'}';
		}
	}
}
