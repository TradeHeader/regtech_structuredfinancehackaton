package cdm.base.math;

import cdm.base.math.DatedValue;
import cdm.base.math.Schedule;
import cdm.base.math.Schedule.ScheduleBuilder;
import cdm.base.math.Schedule.ScheduleBuilderImpl;
import cdm.base.math.Schedule.ScheduleImpl;
import cdm.base.math.meta.ScheduleMeta;
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
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class defining a schedule of rates or amounts in terms of an initial value and then a series of step date and value pairs. On each step date the rate or amount changes to the new step value. The series of step date and value pairs are optional. If not specified, this implies that the initial value remains unchanged over time.
 * @version ${project.version}
 */
@RosettaDataType(value="Schedule", builder=Schedule.ScheduleBuilderImpl.class, version="${project.version}")
public interface Schedule extends RosettaModelObject {

	ScheduleMeta metaData = new ScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The initial rate or amount, as the case may be. An initial rate of 5% would be represented as 0.05.
	 */
	BigDecimal getValue();
	/**
	 * The schedule of step date and value pairs. On each step date the associated step value becomes effective. A list of steps may be ordered in the document by ascending step date. An FpML document containing an unordered list of steps is still regarded as a conformant document.
	 */
	List<? extends DatedValue> getDatedValue();

	/*********************** Build Methods  ***********************/
	Schedule build();
	
	Schedule.ScheduleBuilder toBuilder();
	
	static Schedule.ScheduleBuilder builder() {
		return new Schedule.ScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Schedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Schedule> getType() {
		return Schedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("datedValue"), processor, DatedValue.class, getDatedValue());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ScheduleBuilder extends Schedule, RosettaModelObjectBuilder {
		DatedValue.DatedValueBuilder getOrCreateDatedValue(int _index);
		List<? extends DatedValue.DatedValueBuilder> getDatedValue();
		Schedule.ScheduleBuilder setValue(BigDecimal value);
		Schedule.ScheduleBuilder addDatedValue(DatedValue datedValue0);
		Schedule.ScheduleBuilder addDatedValue(DatedValue datedValue1, int _idx);
		Schedule.ScheduleBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		Schedule.ScheduleBuilder setDatedValue(List<? extends DatedValue> datedValue3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
		}
		

		Schedule.ScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of Schedule  ***********************/
	class ScheduleImpl implements Schedule {
		private final BigDecimal value;
		private final List<? extends DatedValue> datedValue;
		
		protected ScheduleImpl(Schedule.ScheduleBuilder builder) {
			this.value = builder.getValue();
			this.datedValue = ofNullable(builder.getDatedValue()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("datedValue")
		public List<? extends DatedValue> getDatedValue() {
			return datedValue;
		}
		
		@Override
		public Schedule build() {
			return this;
		}
		
		@Override
		public Schedule.ScheduleBuilder toBuilder() {
			Schedule.ScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Schedule.ScheduleBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getDatedValue()).ifPresent(builder::setDatedValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Schedule _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!ListEquals.listEquals(datedValue, _that.getDatedValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (datedValue != null ? datedValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Schedule {" +
				"value=" + this.value + ", " +
				"datedValue=" + this.datedValue +
			'}';
		}
	}

	/*********************** Builder Implementation of Schedule  ***********************/
	class ScheduleBuilderImpl implements Schedule.ScheduleBuilder {
	
		protected BigDecimal value;
		protected List<DatedValue.DatedValueBuilder> datedValue = new ArrayList<>();
	
		public ScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("datedValue")
		public List<? extends DatedValue.DatedValueBuilder> getDatedValue() {
			return datedValue;
		}
		
		public DatedValue.DatedValueBuilder getOrCreateDatedValue(int _index) {
		
			if (datedValue==null) {
				this.datedValue = new ArrayList<>();
			}
			DatedValue.DatedValueBuilder result;
			return getIndex(datedValue, _index, () -> {
						DatedValue.DatedValueBuilder newDatedValue = DatedValue.builder();
						return newDatedValue;
					});
		}
		
	
		@Override
		@RosettaAttribute("value")
		public Schedule.ScheduleBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		public Schedule.ScheduleBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public Schedule.ScheduleBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public Schedule.ScheduleBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public Schedule.ScheduleBuilder setDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues == null)  {
				this.datedValue = new ArrayList<>();
			}
			else {
				this.datedValue = datedValues.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public Schedule build() {
			return new Schedule.ScheduleImpl(this);
		}
		
		@Override
		public Schedule.ScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Schedule.ScheduleBuilder prune() {
			datedValue = datedValue.stream().filter(b->b!=null).<DatedValue.DatedValueBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null) return true;
			if (getDatedValue()!=null && getDatedValue().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Schedule.ScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Schedule.ScheduleBuilder o = (Schedule.ScheduleBuilder) other;
			
			merger.mergeRosetta(getDatedValue(), o.getDatedValue(), this::getOrCreateDatedValue);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Schedule _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!ListEquals.listEquals(datedValue, _that.getDatedValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (datedValue != null ? datedValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ScheduleBuilder {" +
				"value=" + this.value + ", " +
				"datedValue=" + this.datedValue +
			'}';
		}
	}
}
