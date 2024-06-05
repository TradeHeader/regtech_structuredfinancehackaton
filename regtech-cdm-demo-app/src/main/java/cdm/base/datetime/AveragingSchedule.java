package cdm.base.datetime;

import cdm.base.datetime.AveragingSchedule;
import cdm.base.datetime.AveragingSchedule.AveragingScheduleBuilder;
import cdm.base.datetime.AveragingSchedule.AveragingScheduleBuilderImpl;
import cdm.base.datetime.AveragingSchedule.AveragingScheduleImpl;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.meta.AveragingScheduleMeta;
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
 * Class to representing a method for generating a series of dates.
 * @version ${project.version}
 */
@RosettaDataType(value="AveragingSchedule", builder=AveragingSchedule.AveragingScheduleBuilderImpl.class, version="${project.version}")
public interface AveragingSchedule extends RosettaModelObject {

	AveragingScheduleMeta metaData = new AveragingScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Date on which this period begins.
	 */
	Date getStartDate();
	/**
	 * Date on which this period ends.
	 */
	Date getEndDate();
	/**
	 * The frequency at which averaging period occurs with the regular part of the valuation schedule and their roll date convention.
	 */
	CalculationPeriodFrequency getAveragingPeriodFrequency();

	/*********************** Build Methods  ***********************/
	AveragingSchedule build();
	
	AveragingSchedule.AveragingScheduleBuilder toBuilder();
	
	static AveragingSchedule.AveragingScheduleBuilder builder() {
		return new AveragingSchedule.AveragingScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AveragingSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AveragingSchedule> getType() {
		return AveragingSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
		processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
		processRosetta(path.newSubPath("averagingPeriodFrequency"), processor, CalculationPeriodFrequency.class, getAveragingPeriodFrequency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AveragingScheduleBuilder extends AveragingSchedule, RosettaModelObjectBuilder {
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreateAveragingPeriodFrequency();
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getAveragingPeriodFrequency();
		AveragingSchedule.AveragingScheduleBuilder setStartDate(Date startDate);
		AveragingSchedule.AveragingScheduleBuilder setEndDate(Date endDate);
		AveragingSchedule.AveragingScheduleBuilder setAveragingPeriodFrequency(CalculationPeriodFrequency averagingPeriodFrequency);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
			processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
			processRosetta(path.newSubPath("averagingPeriodFrequency"), processor, CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder.class, getAveragingPeriodFrequency());
		}
		

		AveragingSchedule.AveragingScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of AveragingSchedule  ***********************/
	class AveragingScheduleImpl implements AveragingSchedule {
		private final Date startDate;
		private final Date endDate;
		private final CalculationPeriodFrequency averagingPeriodFrequency;
		
		protected AveragingScheduleImpl(AveragingSchedule.AveragingScheduleBuilder builder) {
			this.startDate = builder.getStartDate();
			this.endDate = builder.getEndDate();
			this.averagingPeriodFrequency = ofNullable(builder.getAveragingPeriodFrequency()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("startDate")
		public Date getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public Date getEndDate() {
			return endDate;
		}
		
		@Override
		@RosettaAttribute("averagingPeriodFrequency")
		public CalculationPeriodFrequency getAveragingPeriodFrequency() {
			return averagingPeriodFrequency;
		}
		
		@Override
		public AveragingSchedule build() {
			return this;
		}
		
		@Override
		public AveragingSchedule.AveragingScheduleBuilder toBuilder() {
			AveragingSchedule.AveragingScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AveragingSchedule.AveragingScheduleBuilder builder) {
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
			ofNullable(getAveragingPeriodFrequency()).ifPresent(builder::setAveragingPeriodFrequency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingSchedule _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(averagingPeriodFrequency, _that.getAveragingPeriodFrequency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodFrequency != null ? averagingPeriodFrequency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingSchedule {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"averagingPeriodFrequency=" + this.averagingPeriodFrequency +
			'}';
		}
	}

	/*********************** Builder Implementation of AveragingSchedule  ***********************/
	class AveragingScheduleBuilderImpl implements AveragingSchedule.AveragingScheduleBuilder {
	
		protected Date startDate;
		protected Date endDate;
		protected CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder averagingPeriodFrequency;
	
		public AveragingScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("startDate")
		public Date getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public Date getEndDate() {
			return endDate;
		}
		
		@Override
		@RosettaAttribute("averagingPeriodFrequency")
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getAveragingPeriodFrequency() {
			return averagingPeriodFrequency;
		}
		
		@Override
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreateAveragingPeriodFrequency() {
			CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder result;
			if (averagingPeriodFrequency!=null) {
				result = averagingPeriodFrequency;
			}
			else {
				result = averagingPeriodFrequency = CalculationPeriodFrequency.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("startDate")
		public AveragingSchedule.AveragingScheduleBuilder setStartDate(Date startDate) {
			this.startDate = startDate==null?null:startDate;
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public AveragingSchedule.AveragingScheduleBuilder setEndDate(Date endDate) {
			this.endDate = endDate==null?null:endDate;
			return this;
		}
		@Override
		@RosettaAttribute("averagingPeriodFrequency")
		public AveragingSchedule.AveragingScheduleBuilder setAveragingPeriodFrequency(CalculationPeriodFrequency averagingPeriodFrequency) {
			this.averagingPeriodFrequency = averagingPeriodFrequency==null?null:averagingPeriodFrequency.toBuilder();
			return this;
		}
		
		@Override
		public AveragingSchedule build() {
			return new AveragingSchedule.AveragingScheduleImpl(this);
		}
		
		@Override
		public AveragingSchedule.AveragingScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingSchedule.AveragingScheduleBuilder prune() {
			if (averagingPeriodFrequency!=null && !averagingPeriodFrequency.prune().hasData()) averagingPeriodFrequency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartDate()!=null) return true;
			if (getEndDate()!=null) return true;
			if (getAveragingPeriodFrequency()!=null && getAveragingPeriodFrequency().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingSchedule.AveragingScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AveragingSchedule.AveragingScheduleBuilder o = (AveragingSchedule.AveragingScheduleBuilder) other;
			
			merger.mergeRosetta(getAveragingPeriodFrequency(), o.getAveragingPeriodFrequency(), this::setAveragingPeriodFrequency);
			
			merger.mergeBasic(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeBasic(getEndDate(), o.getEndDate(), this::setEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingSchedule _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(averagingPeriodFrequency, _that.getAveragingPeriodFrequency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodFrequency != null ? averagingPeriodFrequency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingScheduleBuilder {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"averagingPeriodFrequency=" + this.averagingPeriodFrequency +
			'}';
		}
	}
}
