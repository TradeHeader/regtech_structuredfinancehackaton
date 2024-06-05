package cdm.base.datetime;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.CalculationFrequency;
import cdm.base.datetime.CalculationFrequency.CalculationFrequencyBuilder;
import cdm.base.datetime.CalculationFrequency.CalculationFrequencyBuilderImpl;
import cdm.base.datetime.CalculationFrequency.CalculationFrequencyImpl;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.Period;
import cdm.base.datetime.meta.CalculationFrequencyMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents the parameters for describing how often something (such as collateral interest) is to be calculated.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationFrequency", builder=CalculationFrequency.CalculationFrequencyBuilderImpl.class, version="${project.version}")
public interface CalculationFrequency extends RosettaModelObject {

	CalculationFrequencyMeta metaData = new CalculationFrequencyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the time period at which calculation is performed, e.g. 1 month.
	 */
	Period getPeriod();
	/**
	 * Specifies the month of the year if used.
	 */
	BigDecimal getMonthOfYear();
	/**
	 * Specifies the day of the month if used.
	 */
	BigDecimal getDayOfMonth();
	/**
	 * Specifies the day of the week if used.
	 */
	DayOfWeekEnum getDayOfWeek();
	/**
	 * Specifies the week of the month if used.
	 */
	BigDecimal getWeekOfMonth();
	/**
	 * Specifies how many days from the trigger event should the payment occur.
	 */
	BigDecimal getOffsetDays();
	/**
	 * Specifies where is the time measured.
	 */
	BusinessCenterTime getDateLocation();
	/**
	 * Specifies the business center for adjustment of calculation period.
	 */
	List<BusinessCenterEnum> getBusinessCenter();

	/*********************** Build Methods  ***********************/
	CalculationFrequency build();
	
	CalculationFrequency.CalculationFrequencyBuilder toBuilder();
	
	static CalculationFrequency.CalculationFrequencyBuilder builder() {
		return new CalculationFrequency.CalculationFrequencyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationFrequency> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationFrequency> getType() {
		return CalculationFrequency.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("period"), processor, Period.class, getPeriod());
		processor.processBasic(path.newSubPath("monthOfYear"), BigDecimal.class, getMonthOfYear(), this);
		processor.processBasic(path.newSubPath("dayOfMonth"), BigDecimal.class, getDayOfMonth(), this);
		processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
		processor.processBasic(path.newSubPath("weekOfMonth"), BigDecimal.class, getWeekOfMonth(), this);
		processor.processBasic(path.newSubPath("offsetDays"), BigDecimal.class, getOffsetDays(), this);
		processRosetta(path.newSubPath("dateLocation"), processor, BusinessCenterTime.class, getDateLocation());
		processor.processBasic(path.newSubPath("businessCenter"), BusinessCenterEnum.class, getBusinessCenter(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationFrequencyBuilder extends CalculationFrequency, RosettaModelObjectBuilder {
		Period.PeriodBuilder getOrCreatePeriod();
		Period.PeriodBuilder getPeriod();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateDateLocation();
		BusinessCenterTime.BusinessCenterTimeBuilder getDateLocation();
		CalculationFrequency.CalculationFrequencyBuilder setPeriod(Period period);
		CalculationFrequency.CalculationFrequencyBuilder setMonthOfYear(BigDecimal monthOfYear);
		CalculationFrequency.CalculationFrequencyBuilder setDayOfMonth(BigDecimal dayOfMonth);
		CalculationFrequency.CalculationFrequencyBuilder setDayOfWeek(DayOfWeekEnum dayOfWeek);
		CalculationFrequency.CalculationFrequencyBuilder setWeekOfMonth(BigDecimal weekOfMonth);
		CalculationFrequency.CalculationFrequencyBuilder setOffsetDays(BigDecimal offsetDays);
		CalculationFrequency.CalculationFrequencyBuilder setDateLocation(BusinessCenterTime dateLocation);
		CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(BusinessCenterEnum businessCenter0);
		CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(BusinessCenterEnum businessCenter1, int _idx);
		CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(List<? extends BusinessCenterEnum> businessCenter2);
		CalculationFrequency.CalculationFrequencyBuilder setBusinessCenter(List<? extends BusinessCenterEnum> businessCenter3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("period"), processor, Period.PeriodBuilder.class, getPeriod());
			processor.processBasic(path.newSubPath("monthOfYear"), BigDecimal.class, getMonthOfYear(), this);
			processor.processBasic(path.newSubPath("dayOfMonth"), BigDecimal.class, getDayOfMonth(), this);
			processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
			processor.processBasic(path.newSubPath("weekOfMonth"), BigDecimal.class, getWeekOfMonth(), this);
			processor.processBasic(path.newSubPath("offsetDays"), BigDecimal.class, getOffsetDays(), this);
			processRosetta(path.newSubPath("dateLocation"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getDateLocation());
			processor.processBasic(path.newSubPath("businessCenter"), BusinessCenterEnum.class, getBusinessCenter(), this);
		}
		

		CalculationFrequency.CalculationFrequencyBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationFrequency  ***********************/
	class CalculationFrequencyImpl implements CalculationFrequency {
		private final Period period;
		private final BigDecimal monthOfYear;
		private final BigDecimal dayOfMonth;
		private final DayOfWeekEnum dayOfWeek;
		private final BigDecimal weekOfMonth;
		private final BigDecimal offsetDays;
		private final BusinessCenterTime dateLocation;
		private final List<BusinessCenterEnum> businessCenter;
		
		protected CalculationFrequencyImpl(CalculationFrequency.CalculationFrequencyBuilder builder) {
			this.period = ofNullable(builder.getPeriod()).map(f->f.build()).orElse(null);
			this.monthOfYear = builder.getMonthOfYear();
			this.dayOfMonth = builder.getDayOfMonth();
			this.dayOfWeek = builder.getDayOfWeek();
			this.weekOfMonth = builder.getWeekOfMonth();
			this.offsetDays = builder.getOffsetDays();
			this.dateLocation = ofNullable(builder.getDateLocation()).map(f->f.build()).orElse(null);
			this.businessCenter = ofNullable(builder.getBusinessCenter()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("period")
		public Period getPeriod() {
			return period;
		}
		
		@Override
		@RosettaAttribute("monthOfYear")
		public BigDecimal getMonthOfYear() {
			return monthOfYear;
		}
		
		@Override
		@RosettaAttribute("dayOfMonth")
		public BigDecimal getDayOfMonth() {
			return dayOfMonth;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public DayOfWeekEnum getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("weekOfMonth")
		public BigDecimal getWeekOfMonth() {
			return weekOfMonth;
		}
		
		@Override
		@RosettaAttribute("offsetDays")
		public BigDecimal getOffsetDays() {
			return offsetDays;
		}
		
		@Override
		@RosettaAttribute("dateLocation")
		public BusinessCenterTime getDateLocation() {
			return dateLocation;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public List<BusinessCenterEnum> getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		public CalculationFrequency build() {
			return this;
		}
		
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder toBuilder() {
			CalculationFrequency.CalculationFrequencyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationFrequency.CalculationFrequencyBuilder builder) {
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
			ofNullable(getMonthOfYear()).ifPresent(builder::setMonthOfYear);
			ofNullable(getDayOfMonth()).ifPresent(builder::setDayOfMonth);
			ofNullable(getDayOfWeek()).ifPresent(builder::setDayOfWeek);
			ofNullable(getWeekOfMonth()).ifPresent(builder::setWeekOfMonth);
			ofNullable(getOffsetDays()).ifPresent(builder::setOffsetDays);
			ofNullable(getDateLocation()).ifPresent(builder::setDateLocation);
			ofNullable(getBusinessCenter()).ifPresent(builder::setBusinessCenter);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationFrequency _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(monthOfYear, _that.getMonthOfYear())) return false;
			if (!Objects.equals(dayOfMonth, _that.getDayOfMonth())) return false;
			if (!Objects.equals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(weekOfMonth, _that.getWeekOfMonth())) return false;
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			if (!Objects.equals(dateLocation, _that.getDateLocation())) return false;
			if (!ListEquals.listEquals(businessCenter, _that.getBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (monthOfYear != null ? monthOfYear.hashCode() : 0);
			_result = 31 * _result + (dayOfMonth != null ? dayOfMonth.hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weekOfMonth != null ? weekOfMonth.hashCode() : 0);
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			_result = 31 * _result + (dateLocation != null ? dateLocation.hashCode() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationFrequency {" +
				"period=" + this.period + ", " +
				"monthOfYear=" + this.monthOfYear + ", " +
				"dayOfMonth=" + this.dayOfMonth + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"weekOfMonth=" + this.weekOfMonth + ", " +
				"offsetDays=" + this.offsetDays + ", " +
				"dateLocation=" + this.dateLocation + ", " +
				"businessCenter=" + this.businessCenter +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationFrequency  ***********************/
	class CalculationFrequencyBuilderImpl implements CalculationFrequency.CalculationFrequencyBuilder {
	
		protected Period.PeriodBuilder period;
		protected BigDecimal monthOfYear;
		protected BigDecimal dayOfMonth;
		protected DayOfWeekEnum dayOfWeek;
		protected BigDecimal weekOfMonth;
		protected BigDecimal offsetDays;
		protected BusinessCenterTime.BusinessCenterTimeBuilder dateLocation;
		protected List<BusinessCenterEnum> businessCenter = new ArrayList<>();
	
		public CalculationFrequencyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("period")
		public Period.PeriodBuilder getPeriod() {
			return period;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreatePeriod() {
			Period.PeriodBuilder result;
			if (period!=null) {
				result = period;
			}
			else {
				result = period = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("monthOfYear")
		public BigDecimal getMonthOfYear() {
			return monthOfYear;
		}
		
		@Override
		@RosettaAttribute("dayOfMonth")
		public BigDecimal getDayOfMonth() {
			return dayOfMonth;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public DayOfWeekEnum getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("weekOfMonth")
		public BigDecimal getWeekOfMonth() {
			return weekOfMonth;
		}
		
		@Override
		@RosettaAttribute("offsetDays")
		public BigDecimal getOffsetDays() {
			return offsetDays;
		}
		
		@Override
		@RosettaAttribute("dateLocation")
		public BusinessCenterTime.BusinessCenterTimeBuilder getDateLocation() {
			return dateLocation;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateDateLocation() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (dateLocation!=null) {
				result = dateLocation;
			}
			else {
				result = dateLocation = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("businessCenter")
		public List<BusinessCenterEnum> getBusinessCenter() {
			return businessCenter;
		}
		
	
		@Override
		@RosettaAttribute("period")
		public CalculationFrequency.CalculationFrequencyBuilder setPeriod(Period period) {
			this.period = period==null?null:period.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("monthOfYear")
		public CalculationFrequency.CalculationFrequencyBuilder setMonthOfYear(BigDecimal monthOfYear) {
			this.monthOfYear = monthOfYear==null?null:monthOfYear;
			return this;
		}
		@Override
		@RosettaAttribute("dayOfMonth")
		public CalculationFrequency.CalculationFrequencyBuilder setDayOfMonth(BigDecimal dayOfMonth) {
			this.dayOfMonth = dayOfMonth==null?null:dayOfMonth;
			return this;
		}
		@Override
		@RosettaAttribute("dayOfWeek")
		public CalculationFrequency.CalculationFrequencyBuilder setDayOfWeek(DayOfWeekEnum dayOfWeek) {
			this.dayOfWeek = dayOfWeek==null?null:dayOfWeek;
			return this;
		}
		@Override
		@RosettaAttribute("weekOfMonth")
		public CalculationFrequency.CalculationFrequencyBuilder setWeekOfMonth(BigDecimal weekOfMonth) {
			this.weekOfMonth = weekOfMonth==null?null:weekOfMonth;
			return this;
		}
		@Override
		@RosettaAttribute("offsetDays")
		public CalculationFrequency.CalculationFrequencyBuilder setOffsetDays(BigDecimal offsetDays) {
			this.offsetDays = offsetDays==null?null:offsetDays;
			return this;
		}
		@Override
		@RosettaAttribute("dateLocation")
		public CalculationFrequency.CalculationFrequencyBuilder setDateLocation(BusinessCenterTime dateLocation) {
			this.dateLocation = dateLocation==null?null:dateLocation.toBuilder();
			return this;
		}
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(BusinessCenterEnum businessCenter) {
			if (businessCenter!=null) this.businessCenter.add(businessCenter);
			return this;
		}
		
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(BusinessCenterEnum businessCenter, int _idx) {
			getIndex(this.businessCenter, _idx, () -> businessCenter);
			return this;
		}
		@Override 
		public CalculationFrequency.CalculationFrequencyBuilder addBusinessCenter(List<? extends BusinessCenterEnum> businessCenters) {
			if (businessCenters != null) {
				for (BusinessCenterEnum toAdd : businessCenters) {
					this.businessCenter.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("businessCenter")
		public CalculationFrequency.CalculationFrequencyBuilder setBusinessCenter(List<? extends BusinessCenterEnum> businessCenters) {
			if (businessCenters == null)  {
				this.businessCenter = new ArrayList<>();
			}
			else {
				this.businessCenter = businessCenters.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public CalculationFrequency build() {
			return new CalculationFrequency.CalculationFrequencyImpl(this);
		}
		
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder prune() {
			if (period!=null && !period.prune().hasData()) period = null;
			if (dateLocation!=null && !dateLocation.prune().hasData()) dateLocation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriod()!=null && getPeriod().hasData()) return true;
			if (getMonthOfYear()!=null) return true;
			if (getDayOfMonth()!=null) return true;
			if (getDayOfWeek()!=null) return true;
			if (getWeekOfMonth()!=null) return true;
			if (getOffsetDays()!=null) return true;
			if (getDateLocation()!=null && getDateLocation().hasData()) return true;
			if (getBusinessCenter()!=null && !getBusinessCenter().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationFrequency.CalculationFrequencyBuilder o = (CalculationFrequency.CalculationFrequencyBuilder) other;
			
			merger.mergeRosetta(getPeriod(), o.getPeriod(), this::setPeriod);
			merger.mergeRosetta(getDateLocation(), o.getDateLocation(), this::setDateLocation);
			
			merger.mergeBasic(getMonthOfYear(), o.getMonthOfYear(), this::setMonthOfYear);
			merger.mergeBasic(getDayOfMonth(), o.getDayOfMonth(), this::setDayOfMonth);
			merger.mergeBasic(getDayOfWeek(), o.getDayOfWeek(), this::setDayOfWeek);
			merger.mergeBasic(getWeekOfMonth(), o.getWeekOfMonth(), this::setWeekOfMonth);
			merger.mergeBasic(getOffsetDays(), o.getOffsetDays(), this::setOffsetDays);
			merger.mergeBasic(getBusinessCenter(), o.getBusinessCenter(), (Consumer<BusinessCenterEnum>) this::addBusinessCenter);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationFrequency _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(monthOfYear, _that.getMonthOfYear())) return false;
			if (!Objects.equals(dayOfMonth, _that.getDayOfMonth())) return false;
			if (!Objects.equals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(weekOfMonth, _that.getWeekOfMonth())) return false;
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			if (!Objects.equals(dateLocation, _that.getDateLocation())) return false;
			if (!ListEquals.listEquals(businessCenter, _that.getBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (monthOfYear != null ? monthOfYear.hashCode() : 0);
			_result = 31 * _result + (dayOfMonth != null ? dayOfMonth.hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weekOfMonth != null ? weekOfMonth.hashCode() : 0);
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			_result = 31 * _result + (dateLocation != null ? dateLocation.hashCode() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationFrequencyBuilder {" +
				"period=" + this.period + ", " +
				"monthOfYear=" + this.monthOfYear + ", " +
				"dayOfMonth=" + this.dayOfMonth + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"weekOfMonth=" + this.weekOfMonth + ", " +
				"offsetDays=" + this.offsetDays + ", " +
				"dateLocation=" + this.dateLocation + ", " +
				"businessCenter=" + this.businessCenter +
			'}';
		}
	}
}
