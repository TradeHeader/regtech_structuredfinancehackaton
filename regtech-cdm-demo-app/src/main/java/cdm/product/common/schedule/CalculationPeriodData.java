package cdm.product.common.schedule;

import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataBuilder;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataBuilderImpl;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataImpl;
import cdm.product.common.schedule.meta.CalculationPeriodDataMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationPeriodData", builder=CalculationPeriodData.CalculationPeriodDataBuilderImpl.class, version="${project.version}")
public interface CalculationPeriodData extends RosettaModelObject {

	CalculationPeriodDataMeta metaData = new CalculationPeriodDataMeta();

	/*********************** Getter Methods  ***********************/
	Date getStartDate();
	Date getEndDate();
	Integer getDaysInPeriod();
	Integer getDaysInLeapYearPeriod();
	Boolean getIsFirstPeriod();
	Boolean getIsLastPeriod();

	/*********************** Build Methods  ***********************/
	CalculationPeriodData build();
	
	CalculationPeriodData.CalculationPeriodDataBuilder toBuilder();
	
	static CalculationPeriodData.CalculationPeriodDataBuilder builder() {
		return new CalculationPeriodData.CalculationPeriodDataBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationPeriodData> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationPeriodData> getType() {
		return CalculationPeriodData.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
		processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
		processor.processBasic(path.newSubPath("daysInPeriod"), Integer.class, getDaysInPeriod(), this);
		processor.processBasic(path.newSubPath("daysInLeapYearPeriod"), Integer.class, getDaysInLeapYearPeriod(), this);
		processor.processBasic(path.newSubPath("isFirstPeriod"), Boolean.class, getIsFirstPeriod(), this);
		processor.processBasic(path.newSubPath("isLastPeriod"), Boolean.class, getIsLastPeriod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationPeriodDataBuilder extends CalculationPeriodData, RosettaModelObjectBuilder {
		CalculationPeriodData.CalculationPeriodDataBuilder setStartDate(Date startDate);
		CalculationPeriodData.CalculationPeriodDataBuilder setEndDate(Date endDate);
		CalculationPeriodData.CalculationPeriodDataBuilder setDaysInPeriod(Integer daysInPeriod);
		CalculationPeriodData.CalculationPeriodDataBuilder setDaysInLeapYearPeriod(Integer daysInLeapYearPeriod);
		CalculationPeriodData.CalculationPeriodDataBuilder setIsFirstPeriod(Boolean isFirstPeriod);
		CalculationPeriodData.CalculationPeriodDataBuilder setIsLastPeriod(Boolean isLastPeriod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
			processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
			processor.processBasic(path.newSubPath("daysInPeriod"), Integer.class, getDaysInPeriod(), this);
			processor.processBasic(path.newSubPath("daysInLeapYearPeriod"), Integer.class, getDaysInLeapYearPeriod(), this);
			processor.processBasic(path.newSubPath("isFirstPeriod"), Boolean.class, getIsFirstPeriod(), this);
			processor.processBasic(path.newSubPath("isLastPeriod"), Boolean.class, getIsLastPeriod(), this);
		}
		

		CalculationPeriodData.CalculationPeriodDataBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationPeriodData  ***********************/
	class CalculationPeriodDataImpl implements CalculationPeriodData {
		private final Date startDate;
		private final Date endDate;
		private final Integer daysInPeriod;
		private final Integer daysInLeapYearPeriod;
		private final Boolean isFirstPeriod;
		private final Boolean isLastPeriod;
		
		protected CalculationPeriodDataImpl(CalculationPeriodData.CalculationPeriodDataBuilder builder) {
			this.startDate = builder.getStartDate();
			this.endDate = builder.getEndDate();
			this.daysInPeriod = builder.getDaysInPeriod();
			this.daysInLeapYearPeriod = builder.getDaysInLeapYearPeriod();
			this.isFirstPeriod = builder.getIsFirstPeriod();
			this.isLastPeriod = builder.getIsLastPeriod();
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
		@RosettaAttribute("daysInPeriod")
		public Integer getDaysInPeriod() {
			return daysInPeriod;
		}
		
		@Override
		@RosettaAttribute("daysInLeapYearPeriod")
		public Integer getDaysInLeapYearPeriod() {
			return daysInLeapYearPeriod;
		}
		
		@Override
		@RosettaAttribute("isFirstPeriod")
		public Boolean getIsFirstPeriod() {
			return isFirstPeriod;
		}
		
		@Override
		@RosettaAttribute("isLastPeriod")
		public Boolean getIsLastPeriod() {
			return isLastPeriod;
		}
		
		@Override
		public CalculationPeriodData build() {
			return this;
		}
		
		@Override
		public CalculationPeriodData.CalculationPeriodDataBuilder toBuilder() {
			CalculationPeriodData.CalculationPeriodDataBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationPeriodData.CalculationPeriodDataBuilder builder) {
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
			ofNullable(getDaysInPeriod()).ifPresent(builder::setDaysInPeriod);
			ofNullable(getDaysInLeapYearPeriod()).ifPresent(builder::setDaysInLeapYearPeriod);
			ofNullable(getIsFirstPeriod()).ifPresent(builder::setIsFirstPeriod);
			ofNullable(getIsLastPeriod()).ifPresent(builder::setIsLastPeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodData _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(daysInPeriod, _that.getDaysInPeriod())) return false;
			if (!Objects.equals(daysInLeapYearPeriod, _that.getDaysInLeapYearPeriod())) return false;
			if (!Objects.equals(isFirstPeriod, _that.getIsFirstPeriod())) return false;
			if (!Objects.equals(isLastPeriod, _that.getIsLastPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (daysInPeriod != null ? daysInPeriod.hashCode() : 0);
			_result = 31 * _result + (daysInLeapYearPeriod != null ? daysInLeapYearPeriod.hashCode() : 0);
			_result = 31 * _result + (isFirstPeriod != null ? isFirstPeriod.hashCode() : 0);
			_result = 31 * _result + (isLastPeriod != null ? isLastPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodData {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"daysInPeriod=" + this.daysInPeriod + ", " +
				"daysInLeapYearPeriod=" + this.daysInLeapYearPeriod + ", " +
				"isFirstPeriod=" + this.isFirstPeriod + ", " +
				"isLastPeriod=" + this.isLastPeriod +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationPeriodData  ***********************/
	class CalculationPeriodDataBuilderImpl implements CalculationPeriodData.CalculationPeriodDataBuilder {
	
		protected Date startDate;
		protected Date endDate;
		protected Integer daysInPeriod;
		protected Integer daysInLeapYearPeriod;
		protected Boolean isFirstPeriod;
		protected Boolean isLastPeriod;
	
		public CalculationPeriodDataBuilderImpl() {
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
		@RosettaAttribute("daysInPeriod")
		public Integer getDaysInPeriod() {
			return daysInPeriod;
		}
		
		@Override
		@RosettaAttribute("daysInLeapYearPeriod")
		public Integer getDaysInLeapYearPeriod() {
			return daysInLeapYearPeriod;
		}
		
		@Override
		@RosettaAttribute("isFirstPeriod")
		public Boolean getIsFirstPeriod() {
			return isFirstPeriod;
		}
		
		@Override
		@RosettaAttribute("isLastPeriod")
		public Boolean getIsLastPeriod() {
			return isLastPeriod;
		}
		
	
		@Override
		@RosettaAttribute("startDate")
		public CalculationPeriodData.CalculationPeriodDataBuilder setStartDate(Date startDate) {
			this.startDate = startDate==null?null:startDate;
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public CalculationPeriodData.CalculationPeriodDataBuilder setEndDate(Date endDate) {
			this.endDate = endDate==null?null:endDate;
			return this;
		}
		@Override
		@RosettaAttribute("daysInPeriod")
		public CalculationPeriodData.CalculationPeriodDataBuilder setDaysInPeriod(Integer daysInPeriod) {
			this.daysInPeriod = daysInPeriod==null?null:daysInPeriod;
			return this;
		}
		@Override
		@RosettaAttribute("daysInLeapYearPeriod")
		public CalculationPeriodData.CalculationPeriodDataBuilder setDaysInLeapYearPeriod(Integer daysInLeapYearPeriod) {
			this.daysInLeapYearPeriod = daysInLeapYearPeriod==null?null:daysInLeapYearPeriod;
			return this;
		}
		@Override
		@RosettaAttribute("isFirstPeriod")
		public CalculationPeriodData.CalculationPeriodDataBuilder setIsFirstPeriod(Boolean isFirstPeriod) {
			this.isFirstPeriod = isFirstPeriod==null?null:isFirstPeriod;
			return this;
		}
		@Override
		@RosettaAttribute("isLastPeriod")
		public CalculationPeriodData.CalculationPeriodDataBuilder setIsLastPeriod(Boolean isLastPeriod) {
			this.isLastPeriod = isLastPeriod==null?null:isLastPeriod;
			return this;
		}
		
		@Override
		public CalculationPeriodData build() {
			return new CalculationPeriodData.CalculationPeriodDataImpl(this);
		}
		
		@Override
		public CalculationPeriodData.CalculationPeriodDataBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodData.CalculationPeriodDataBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartDate()!=null) return true;
			if (getEndDate()!=null) return true;
			if (getDaysInPeriod()!=null) return true;
			if (getDaysInLeapYearPeriod()!=null) return true;
			if (getIsFirstPeriod()!=null) return true;
			if (getIsLastPeriod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodData.CalculationPeriodDataBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationPeriodData.CalculationPeriodDataBuilder o = (CalculationPeriodData.CalculationPeriodDataBuilder) other;
			
			
			merger.mergeBasic(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeBasic(getEndDate(), o.getEndDate(), this::setEndDate);
			merger.mergeBasic(getDaysInPeriod(), o.getDaysInPeriod(), this::setDaysInPeriod);
			merger.mergeBasic(getDaysInLeapYearPeriod(), o.getDaysInLeapYearPeriod(), this::setDaysInLeapYearPeriod);
			merger.mergeBasic(getIsFirstPeriod(), o.getIsFirstPeriod(), this::setIsFirstPeriod);
			merger.mergeBasic(getIsLastPeriod(), o.getIsLastPeriod(), this::setIsLastPeriod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodData _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(daysInPeriod, _that.getDaysInPeriod())) return false;
			if (!Objects.equals(daysInLeapYearPeriod, _that.getDaysInLeapYearPeriod())) return false;
			if (!Objects.equals(isFirstPeriod, _that.getIsFirstPeriod())) return false;
			if (!Objects.equals(isLastPeriod, _that.getIsLastPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (daysInPeriod != null ? daysInPeriod.hashCode() : 0);
			_result = 31 * _result + (daysInLeapYearPeriod != null ? daysInLeapYearPeriod.hashCode() : 0);
			_result = 31 * _result + (isFirstPeriod != null ? isFirstPeriod.hashCode() : 0);
			_result = 31 * _result + (isLastPeriod != null ? isLastPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodDataBuilder {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"daysInPeriod=" + this.daysInPeriod + ", " +
				"daysInLeapYearPeriod=" + this.daysInLeapYearPeriod + ", " +
				"isFirstPeriod=" + this.isFirstPeriod + ", " +
				"isLastPeriod=" + this.isLastPeriod +
			'}';
		}
	}
}
