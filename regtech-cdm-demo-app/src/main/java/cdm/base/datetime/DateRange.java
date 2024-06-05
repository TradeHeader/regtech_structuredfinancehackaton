package cdm.base.datetime;

import cdm.base.datetime.DateRange;
import cdm.base.datetime.DateRange.DateRangeBuilder;
import cdm.base.datetime.DateRange.DateRangeBuilderImpl;
import cdm.base.datetime.DateRange.DateRangeImpl;
import cdm.base.datetime.meta.DateRangeMeta;
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
 * A class defining a contiguous series of calendar dates. The date range is defined as all the dates between and including the start and the end date. The start date must fall on or before the end date.
 * @version ${project.version}
 */
@RosettaDataType(value="DateRange", builder=DateRange.DateRangeBuilderImpl.class, version="${project.version}")
public interface DateRange extends RosettaModelObject {

	DateRangeMeta metaData = new DateRangeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The first date of a date range.
	 */
	Date getStartDate();
	/**
	 * The last date of a date range.
	 */
	Date getEndDate();

	/*********************** Build Methods  ***********************/
	DateRange build();
	
	DateRange.DateRangeBuilder toBuilder();
	
	static DateRange.DateRangeBuilder builder() {
		return new DateRange.DateRangeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DateRange> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DateRange> getType() {
		return DateRange.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
		processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DateRangeBuilder extends DateRange, RosettaModelObjectBuilder {
		DateRange.DateRangeBuilder setStartDate(Date startDate);
		DateRange.DateRangeBuilder setEndDate(Date endDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
			processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
		}
		

		DateRange.DateRangeBuilder prune();
	}

	/*********************** Immutable Implementation of DateRange  ***********************/
	class DateRangeImpl implements DateRange {
		private final Date startDate;
		private final Date endDate;
		
		protected DateRangeImpl(DateRange.DateRangeBuilder builder) {
			this.startDate = builder.getStartDate();
			this.endDate = builder.getEndDate();
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
		public DateRange build() {
			return this;
		}
		
		@Override
		public DateRange.DateRangeBuilder toBuilder() {
			DateRange.DateRangeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DateRange.DateRangeBuilder builder) {
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRange _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRange {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate +
			'}';
		}
	}

	/*********************** Builder Implementation of DateRange  ***********************/
	class DateRangeBuilderImpl implements DateRange.DateRangeBuilder {
	
		protected Date startDate;
		protected Date endDate;
	
		public DateRangeBuilderImpl() {
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
		@RosettaAttribute("startDate")
		public DateRange.DateRangeBuilder setStartDate(Date startDate) {
			this.startDate = startDate==null?null:startDate;
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public DateRange.DateRangeBuilder setEndDate(Date endDate) {
			this.endDate = endDate==null?null:endDate;
			return this;
		}
		
		@Override
		public DateRange build() {
			return new DateRange.DateRangeImpl(this);
		}
		
		@Override
		public DateRange.DateRangeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRange.DateRangeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartDate()!=null) return true;
			if (getEndDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRange.DateRangeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DateRange.DateRangeBuilder o = (DateRange.DateRangeBuilder) other;
			
			
			merger.mergeBasic(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeBasic(getEndDate(), o.getEndDate(), this::setEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRange _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRangeBuilder {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate +
			'}';
		}
	}
}
