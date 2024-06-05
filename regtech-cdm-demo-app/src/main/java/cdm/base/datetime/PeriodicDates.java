package cdm.base.datetime;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodicDates;
import cdm.base.datetime.PeriodicDates.PeriodicDatesBuilder;
import cdm.base.datetime.PeriodicDates.PeriodicDatesBuilderImpl;
import cdm.base.datetime.PeriodicDates.PeriodicDatesImpl;
import cdm.base.datetime.meta.PeriodicDatesMeta;
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
 * A class for specifying a calculation period schedule.
 * @version ${project.version}
 */
@RosettaDataType(value="PeriodicDates", builder=PeriodicDates.PeriodicDatesBuilderImpl.class, version="${project.version}")
public interface PeriodicDates extends RosettaModelObject {

	PeriodicDatesMeta metaData = new PeriodicDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The start date of the calculation period. FpML specifies that for interest rate swaps this date must only be specified if it is not equal to the effective date. It is always specified in the case of equity swaps and credit default swaps with periodic payments. This date may be subject to adjustment in accordance with a business day convention.
	 */
	AdjustableOrRelativeDate getStartDate();
	/**
	 * The end date of the calculation period. FpML specifies that for interest rate swaps this date must only be specified if it is not equal to the termination date. It is always specified in the case of equity swaps with periodic payments. This date may be subject to adjustment in accordance with a business day convention.
	 */
	AdjustableOrRelativeDate getEndDate();
	/**
	 * The frequency at which calculation period end dates occur with the regular part of the calculation period schedule and their roll date convention.
	 */
	CalculationPeriodFrequency getPeriodFrequency();
	/**
	 * The specification of the business day convention and financial business centers used for adjusting any calculation period date if it would otherwise fall on a day that is not a business day in the specified business center.
	 */
	BusinessDayAdjustments getPeriodDatesAdjustments();
	/**
	 * Denotes the enumerated values to specify the day type classification used in counting the number of days between two dates.
	 */
	DayTypeEnum getDayType();

	/*********************** Build Methods  ***********************/
	PeriodicDates build();
	
	PeriodicDates.PeriodicDatesBuilder toBuilder();
	
	static PeriodicDates.PeriodicDatesBuilder builder() {
		return new PeriodicDates.PeriodicDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PeriodicDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PeriodicDates> getType() {
		return PeriodicDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("startDate"), processor, AdjustableOrRelativeDate.class, getStartDate());
		processRosetta(path.newSubPath("endDate"), processor, AdjustableOrRelativeDate.class, getEndDate());
		processRosetta(path.newSubPath("periodFrequency"), processor, CalculationPeriodFrequency.class, getPeriodFrequency());
		processRosetta(path.newSubPath("periodDatesAdjustments"), processor, BusinessDayAdjustments.class, getPeriodDatesAdjustments());
		processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PeriodicDatesBuilder extends PeriodicDates, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateStartDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getStartDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEndDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEndDate();
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreatePeriodFrequency();
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getPeriodFrequency();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreatePeriodDatesAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getPeriodDatesAdjustments();
		PeriodicDates.PeriodicDatesBuilder setStartDate(AdjustableOrRelativeDate startDate);
		PeriodicDates.PeriodicDatesBuilder setEndDate(AdjustableOrRelativeDate endDate);
		PeriodicDates.PeriodicDatesBuilder setPeriodFrequency(CalculationPeriodFrequency periodFrequency);
		PeriodicDates.PeriodicDatesBuilder setPeriodDatesAdjustments(BusinessDayAdjustments periodDatesAdjustments);
		PeriodicDates.PeriodicDatesBuilder setDayType(DayTypeEnum dayType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("startDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getStartDate());
			processRosetta(path.newSubPath("endDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getEndDate());
			processRosetta(path.newSubPath("periodFrequency"), processor, CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder.class, getPeriodFrequency());
			processRosetta(path.newSubPath("periodDatesAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getPeriodDatesAdjustments());
			processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
		}
		

		PeriodicDates.PeriodicDatesBuilder prune();
	}

	/*********************** Immutable Implementation of PeriodicDates  ***********************/
	class PeriodicDatesImpl implements PeriodicDates {
		private final AdjustableOrRelativeDate startDate;
		private final AdjustableOrRelativeDate endDate;
		private final CalculationPeriodFrequency periodFrequency;
		private final BusinessDayAdjustments periodDatesAdjustments;
		private final DayTypeEnum dayType;
		
		protected PeriodicDatesImpl(PeriodicDates.PeriodicDatesBuilder builder) {
			this.startDate = ofNullable(builder.getStartDate()).map(f->f.build()).orElse(null);
			this.endDate = ofNullable(builder.getEndDate()).map(f->f.build()).orElse(null);
			this.periodFrequency = ofNullable(builder.getPeriodFrequency()).map(f->f.build()).orElse(null);
			this.periodDatesAdjustments = ofNullable(builder.getPeriodDatesAdjustments()).map(f->f.build()).orElse(null);
			this.dayType = builder.getDayType();
		}
		
		@Override
		@RosettaAttribute("startDate")
		public AdjustableOrRelativeDate getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public AdjustableOrRelativeDate getEndDate() {
			return endDate;
		}
		
		@Override
		@RosettaAttribute("periodFrequency")
		public CalculationPeriodFrequency getPeriodFrequency() {
			return periodFrequency;
		}
		
		@Override
		@RosettaAttribute("periodDatesAdjustments")
		public BusinessDayAdjustments getPeriodDatesAdjustments() {
			return periodDatesAdjustments;
		}
		
		@Override
		@RosettaAttribute("dayType")
		public DayTypeEnum getDayType() {
			return dayType;
		}
		
		@Override
		public PeriodicDates build() {
			return this;
		}
		
		@Override
		public PeriodicDates.PeriodicDatesBuilder toBuilder() {
			PeriodicDates.PeriodicDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PeriodicDates.PeriodicDatesBuilder builder) {
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
			ofNullable(getPeriodFrequency()).ifPresent(builder::setPeriodFrequency);
			ofNullable(getPeriodDatesAdjustments()).ifPresent(builder::setPeriodDatesAdjustments);
			ofNullable(getDayType()).ifPresent(builder::setDayType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodicDates _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(periodFrequency, _that.getPeriodFrequency())) return false;
			if (!Objects.equals(periodDatesAdjustments, _that.getPeriodDatesAdjustments())) return false;
			if (!Objects.equals(dayType, _that.getDayType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (periodFrequency != null ? periodFrequency.hashCode() : 0);
			_result = 31 * _result + (periodDatesAdjustments != null ? periodDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (dayType != null ? dayType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PeriodicDates {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"periodFrequency=" + this.periodFrequency + ", " +
				"periodDatesAdjustments=" + this.periodDatesAdjustments + ", " +
				"dayType=" + this.dayType +
			'}';
		}
	}

	/*********************** Builder Implementation of PeriodicDates  ***********************/
	class PeriodicDatesBuilderImpl implements PeriodicDates.PeriodicDatesBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder startDate;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder endDate;
		protected CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder periodFrequency;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder periodDatesAdjustments;
		protected DayTypeEnum dayType;
	
		public PeriodicDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("startDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getStartDate() {
			return startDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateStartDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (startDate!=null) {
				result = startDate;
			}
			else {
				result = startDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("endDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEndDate() {
			return endDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEndDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (endDate!=null) {
				result = endDate;
			}
			else {
				result = endDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("periodFrequency")
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getPeriodFrequency() {
			return periodFrequency;
		}
		
		@Override
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreatePeriodFrequency() {
			CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder result;
			if (periodFrequency!=null) {
				result = periodFrequency;
			}
			else {
				result = periodFrequency = CalculationPeriodFrequency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("periodDatesAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getPeriodDatesAdjustments() {
			return periodDatesAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreatePeriodDatesAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (periodDatesAdjustments!=null) {
				result = periodDatesAdjustments;
			}
			else {
				result = periodDatesAdjustments = BusinessDayAdjustments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dayType")
		public DayTypeEnum getDayType() {
			return dayType;
		}
		
	
		@Override
		@RosettaAttribute("startDate")
		public PeriodicDates.PeriodicDatesBuilder setStartDate(AdjustableOrRelativeDate startDate) {
			this.startDate = startDate==null?null:startDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public PeriodicDates.PeriodicDatesBuilder setEndDate(AdjustableOrRelativeDate endDate) {
			this.endDate = endDate==null?null:endDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("periodFrequency")
		public PeriodicDates.PeriodicDatesBuilder setPeriodFrequency(CalculationPeriodFrequency periodFrequency) {
			this.periodFrequency = periodFrequency==null?null:periodFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("periodDatesAdjustments")
		public PeriodicDates.PeriodicDatesBuilder setPeriodDatesAdjustments(BusinessDayAdjustments periodDatesAdjustments) {
			this.periodDatesAdjustments = periodDatesAdjustments==null?null:periodDatesAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dayType")
		public PeriodicDates.PeriodicDatesBuilder setDayType(DayTypeEnum dayType) {
			this.dayType = dayType==null?null:dayType;
			return this;
		}
		
		@Override
		public PeriodicDates build() {
			return new PeriodicDates.PeriodicDatesImpl(this);
		}
		
		@Override
		public PeriodicDates.PeriodicDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PeriodicDates.PeriodicDatesBuilder prune() {
			if (startDate!=null && !startDate.prune().hasData()) startDate = null;
			if (endDate!=null && !endDate.prune().hasData()) endDate = null;
			if (periodFrequency!=null && !periodFrequency.prune().hasData()) periodFrequency = null;
			if (periodDatesAdjustments!=null && !periodDatesAdjustments.prune().hasData()) periodDatesAdjustments = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartDate()!=null && getStartDate().hasData()) return true;
			if (getEndDate()!=null && getEndDate().hasData()) return true;
			if (getPeriodFrequency()!=null && getPeriodFrequency().hasData()) return true;
			if (getPeriodDatesAdjustments()!=null && getPeriodDatesAdjustments().hasData()) return true;
			if (getDayType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PeriodicDates.PeriodicDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PeriodicDates.PeriodicDatesBuilder o = (PeriodicDates.PeriodicDatesBuilder) other;
			
			merger.mergeRosetta(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeRosetta(getEndDate(), o.getEndDate(), this::setEndDate);
			merger.mergeRosetta(getPeriodFrequency(), o.getPeriodFrequency(), this::setPeriodFrequency);
			merger.mergeRosetta(getPeriodDatesAdjustments(), o.getPeriodDatesAdjustments(), this::setPeriodDatesAdjustments);
			
			merger.mergeBasic(getDayType(), o.getDayType(), this::setDayType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodicDates _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(periodFrequency, _that.getPeriodFrequency())) return false;
			if (!Objects.equals(periodDatesAdjustments, _that.getPeriodDatesAdjustments())) return false;
			if (!Objects.equals(dayType, _that.getDayType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (periodFrequency != null ? periodFrequency.hashCode() : 0);
			_result = 31 * _result + (periodDatesAdjustments != null ? periodDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (dayType != null ? dayType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PeriodicDatesBuilder {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"periodFrequency=" + this.periodFrequency + ", " +
				"periodDatesAdjustments=" + this.periodDatesAdjustments + ", " +
				"dayType=" + this.dayType +
			'}';
		}
	}
}
