package cdm.product.collateral;

import cdm.base.datetime.DayTypeEnum;
import cdm.product.collateral.CollateralInterestNotification;
import cdm.product.collateral.CollateralInterestNotification.CollateralInterestNotificationBuilder;
import cdm.product.collateral.CollateralInterestNotification.CollateralInterestNotificationBuilderImpl;
import cdm.product.collateral.CollateralInterestNotification.CollateralInterestNotificationImpl;
import cdm.product.collateral.meta.CollateralInterestNotificationMeta;
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
import java.time.LocalTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Represents the parameters describing when notifications should be made for required collateral interest transfers.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralInterestNotification", builder=CollateralInterestNotification.CollateralInterestNotificationBuilderImpl.class, version="${project.version}")
public interface CollateralInterestNotification extends RosettaModelObject {

	CollateralInterestNotificationMeta metaData = new CollateralInterestNotificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies what triggers notification (should be enum) Interest Statement Frequency, Period End Date.
	 */
	String getTrigger();
	/**
	 * Specifies the number of days before (negative) or after (positive) the trigger event.
	 */
	BigDecimal getOffset();
	/**
	 * Specifies the time of day that the notification should occur.
	 */
	LocalTime getNotificationTime();
	/**
	 * The type of days on which notification should occur.
	 */
	DayTypeEnum getNotificationDayType();

	/*********************** Build Methods  ***********************/
	CollateralInterestNotification build();
	
	CollateralInterestNotification.CollateralInterestNotificationBuilder toBuilder();
	
	static CollateralInterestNotification.CollateralInterestNotificationBuilder builder() {
		return new CollateralInterestNotification.CollateralInterestNotificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralInterestNotification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralInterestNotification> getType() {
		return CollateralInterestNotification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("trigger"), String.class, getTrigger(), this);
		processor.processBasic(path.newSubPath("offset"), BigDecimal.class, getOffset(), this);
		processor.processBasic(path.newSubPath("notificationTime"), LocalTime.class, getNotificationTime(), this);
		processor.processBasic(path.newSubPath("notificationDayType"), DayTypeEnum.class, getNotificationDayType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralInterestNotificationBuilder extends CollateralInterestNotification, RosettaModelObjectBuilder {
		CollateralInterestNotification.CollateralInterestNotificationBuilder setTrigger(String trigger);
		CollateralInterestNotification.CollateralInterestNotificationBuilder setOffset(BigDecimal offset);
		CollateralInterestNotification.CollateralInterestNotificationBuilder setNotificationTime(LocalTime notificationTime);
		CollateralInterestNotification.CollateralInterestNotificationBuilder setNotificationDayType(DayTypeEnum notificationDayType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("trigger"), String.class, getTrigger(), this);
			processor.processBasic(path.newSubPath("offset"), BigDecimal.class, getOffset(), this);
			processor.processBasic(path.newSubPath("notificationTime"), LocalTime.class, getNotificationTime(), this);
			processor.processBasic(path.newSubPath("notificationDayType"), DayTypeEnum.class, getNotificationDayType(), this);
		}
		

		CollateralInterestNotification.CollateralInterestNotificationBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralInterestNotification  ***********************/
	class CollateralInterestNotificationImpl implements CollateralInterestNotification {
		private final String trigger;
		private final BigDecimal offset;
		private final LocalTime notificationTime;
		private final DayTypeEnum notificationDayType;
		
		protected CollateralInterestNotificationImpl(CollateralInterestNotification.CollateralInterestNotificationBuilder builder) {
			this.trigger = builder.getTrigger();
			this.offset = builder.getOffset();
			this.notificationTime = builder.getNotificationTime();
			this.notificationDayType = builder.getNotificationDayType();
		}
		
		@Override
		@RosettaAttribute("trigger")
		public String getTrigger() {
			return trigger;
		}
		
		@Override
		@RosettaAttribute("offset")
		public BigDecimal getOffset() {
			return offset;
		}
		
		@Override
		@RosettaAttribute("notificationTime")
		public LocalTime getNotificationTime() {
			return notificationTime;
		}
		
		@Override
		@RosettaAttribute("notificationDayType")
		public DayTypeEnum getNotificationDayType() {
			return notificationDayType;
		}
		
		@Override
		public CollateralInterestNotification build() {
			return this;
		}
		
		@Override
		public CollateralInterestNotification.CollateralInterestNotificationBuilder toBuilder() {
			CollateralInterestNotification.CollateralInterestNotificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralInterestNotification.CollateralInterestNotificationBuilder builder) {
			ofNullable(getTrigger()).ifPresent(builder::setTrigger);
			ofNullable(getOffset()).ifPresent(builder::setOffset);
			ofNullable(getNotificationTime()).ifPresent(builder::setNotificationTime);
			ofNullable(getNotificationDayType()).ifPresent(builder::setNotificationDayType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestNotification _that = getType().cast(o);
		
			if (!Objects.equals(trigger, _that.getTrigger())) return false;
			if (!Objects.equals(offset, _that.getOffset())) return false;
			if (!Objects.equals(notificationTime, _that.getNotificationTime())) return false;
			if (!Objects.equals(notificationDayType, _that.getNotificationDayType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trigger != null ? trigger.hashCode() : 0);
			_result = 31 * _result + (offset != null ? offset.hashCode() : 0);
			_result = 31 * _result + (notificationTime != null ? notificationTime.hashCode() : 0);
			_result = 31 * _result + (notificationDayType != null ? notificationDayType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestNotification {" +
				"trigger=" + this.trigger + ", " +
				"offset=" + this.offset + ", " +
				"notificationTime=" + this.notificationTime + ", " +
				"notificationDayType=" + this.notificationDayType +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralInterestNotification  ***********************/
	class CollateralInterestNotificationBuilderImpl implements CollateralInterestNotification.CollateralInterestNotificationBuilder {
	
		protected String trigger;
		protected BigDecimal offset;
		protected LocalTime notificationTime;
		protected DayTypeEnum notificationDayType;
	
		public CollateralInterestNotificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("trigger")
		public String getTrigger() {
			return trigger;
		}
		
		@Override
		@RosettaAttribute("offset")
		public BigDecimal getOffset() {
			return offset;
		}
		
		@Override
		@RosettaAttribute("notificationTime")
		public LocalTime getNotificationTime() {
			return notificationTime;
		}
		
		@Override
		@RosettaAttribute("notificationDayType")
		public DayTypeEnum getNotificationDayType() {
			return notificationDayType;
		}
		
	
		@Override
		@RosettaAttribute("trigger")
		public CollateralInterestNotification.CollateralInterestNotificationBuilder setTrigger(String trigger) {
			this.trigger = trigger==null?null:trigger;
			return this;
		}
		@Override
		@RosettaAttribute("offset")
		public CollateralInterestNotification.CollateralInterestNotificationBuilder setOffset(BigDecimal offset) {
			this.offset = offset==null?null:offset;
			return this;
		}
		@Override
		@RosettaAttribute("notificationTime")
		public CollateralInterestNotification.CollateralInterestNotificationBuilder setNotificationTime(LocalTime notificationTime) {
			this.notificationTime = notificationTime==null?null:notificationTime;
			return this;
		}
		@Override
		@RosettaAttribute("notificationDayType")
		public CollateralInterestNotification.CollateralInterestNotificationBuilder setNotificationDayType(DayTypeEnum notificationDayType) {
			this.notificationDayType = notificationDayType==null?null:notificationDayType;
			return this;
		}
		
		@Override
		public CollateralInterestNotification build() {
			return new CollateralInterestNotification.CollateralInterestNotificationImpl(this);
		}
		
		@Override
		public CollateralInterestNotification.CollateralInterestNotificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestNotification.CollateralInterestNotificationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTrigger()!=null) return true;
			if (getOffset()!=null) return true;
			if (getNotificationTime()!=null) return true;
			if (getNotificationDayType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestNotification.CollateralInterestNotificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralInterestNotification.CollateralInterestNotificationBuilder o = (CollateralInterestNotification.CollateralInterestNotificationBuilder) other;
			
			
			merger.mergeBasic(getTrigger(), o.getTrigger(), this::setTrigger);
			merger.mergeBasic(getOffset(), o.getOffset(), this::setOffset);
			merger.mergeBasic(getNotificationTime(), o.getNotificationTime(), this::setNotificationTime);
			merger.mergeBasic(getNotificationDayType(), o.getNotificationDayType(), this::setNotificationDayType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestNotification _that = getType().cast(o);
		
			if (!Objects.equals(trigger, _that.getTrigger())) return false;
			if (!Objects.equals(offset, _that.getOffset())) return false;
			if (!Objects.equals(notificationTime, _that.getNotificationTime())) return false;
			if (!Objects.equals(notificationDayType, _that.getNotificationDayType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trigger != null ? trigger.hashCode() : 0);
			_result = 31 * _result + (offset != null ? offset.hashCode() : 0);
			_result = 31 * _result + (notificationTime != null ? notificationTime.hashCode() : 0);
			_result = 31 * _result + (notificationDayType != null ? notificationDayType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestNotificationBuilder {" +
				"trigger=" + this.trigger + ", " +
				"offset=" + this.offset + ", " +
				"notificationTime=" + this.notificationTime + ", " +
				"notificationDayType=" + this.notificationDayType +
			'}';
		}
	}
}
