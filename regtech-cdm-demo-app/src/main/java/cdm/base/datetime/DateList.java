package cdm.base.datetime;

import cdm.base.datetime.DateList;
import cdm.base.datetime.DateList.DateListBuilder;
import cdm.base.datetime.DateList.DateListBuilderImpl;
import cdm.base.datetime.DateList.DateListImpl;
import cdm.base.datetime.meta.DateListMeta;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * List of dates.
 * @version ${project.version}
 */
@RosettaDataType(value="DateList", builder=DateList.DateListBuilderImpl.class, version="${project.version}")
public interface DateList extends RosettaModelObject {

	DateListMeta metaData = new DateListMeta();

	/*********************** Getter Methods  ***********************/
	List<Date> getDate();

	/*********************** Build Methods  ***********************/
	DateList build();
	
	DateList.DateListBuilder toBuilder();
	
	static DateList.DateListBuilder builder() {
		return new DateList.DateListBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DateList> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DateList> getType() {
		return DateList.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DateListBuilder extends DateList, RosettaModelObjectBuilder {
		DateList.DateListBuilder addDate(Date date0);
		DateList.DateListBuilder addDate(Date date1, int _idx);
		DateList.DateListBuilder addDate(List<? extends Date> date2);
		DateList.DateListBuilder setDate(List<? extends Date> date3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
		}
		

		DateList.DateListBuilder prune();
	}

	/*********************** Immutable Implementation of DateList  ***********************/
	class DateListImpl implements DateList {
		private final List<Date> date;
		
		protected DateListImpl(DateList.DateListBuilder builder) {
			this.date = ofNullable(builder.getDate()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("date")
		public List<Date> getDate() {
			return date;
		}
		
		@Override
		public DateList build() {
			return this;
		}
		
		@Override
		public DateList.DateListBuilder toBuilder() {
			DateList.DateListBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DateList.DateListBuilder builder) {
			ofNullable(getDate()).ifPresent(builder::setDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateList _that = getType().cast(o);
		
			if (!ListEquals.listEquals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateList {" +
				"date=" + this.date +
			'}';
		}
	}

	/*********************** Builder Implementation of DateList  ***********************/
	class DateListBuilderImpl implements DateList.DateListBuilder {
	
		protected List<Date> date = new ArrayList<>();
	
		public DateListBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("date")
		public List<Date> getDate() {
			return date;
		}
		
	
		@Override
		public DateList.DateListBuilder addDate(Date date) {
			if (date!=null) this.date.add(date);
			return this;
		}
		
		@Override
		public DateList.DateListBuilder addDate(Date date, int _idx) {
			getIndex(this.date, _idx, () -> date);
			return this;
		}
		@Override 
		public DateList.DateListBuilder addDate(List<? extends Date> dates) {
			if (dates != null) {
				for (Date toAdd : dates) {
					this.date.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("date")
		public DateList.DateListBuilder setDate(List<? extends Date> dates) {
			if (dates == null)  {
				this.date = new ArrayList<>();
			}
			else {
				this.date = dates.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public DateList build() {
			return new DateList.DateListImpl(this);
		}
		
		@Override
		public DateList.DateListBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateList.DateListBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDate()!=null && !getDate().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateList.DateListBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DateList.DateListBuilder o = (DateList.DateListBuilder) other;
			
			
			merger.mergeBasic(getDate(), o.getDate(), (Consumer<Date>) this::addDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateList _that = getType().cast(o);
		
			if (!ListEquals.listEquals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateListBuilder {" +
				"date=" + this.date +
			'}';
		}
	}
}
