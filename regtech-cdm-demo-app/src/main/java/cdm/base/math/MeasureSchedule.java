package cdm.base.math;

import cdm.base.math.DatedValue;
import cdm.base.math.MeasureBase;
import cdm.base.math.MeasureBase.MeasureBaseBuilder;
import cdm.base.math.MeasureBase.MeasureBaseBuilderImpl;
import cdm.base.math.MeasureBase.MeasureBaseImpl;
import cdm.base.math.MeasureSchedule;
import cdm.base.math.MeasureSchedule.MeasureScheduleBuilder;
import cdm.base.math.MeasureSchedule.MeasureScheduleBuilderImpl;
import cdm.base.math.MeasureSchedule.MeasureScheduleImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.MeasureScheduleMeta;
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
 * A set of measures, all in the same unit, where the values are defined through a schedule of steps. The initial value may be defined either as part of the steps, or using the single amount attribute.
 * @version ${project.version}
 */
@RosettaDataType(value="MeasureSchedule", builder=MeasureSchedule.MeasureScheduleBuilderImpl.class, version="${project.version}")
public interface MeasureSchedule extends MeasureBase {

	MeasureScheduleMeta metaData = new MeasureScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A schedule of step date and value pairs. On each step date the associated step value becomes effective. The step dates are used to order the steps by ascending order. This attribute is optional so the data type may be used to define a schedule with a single value.
	 */
	List<? extends DatedValue> getDatedValue();

	/*********************** Build Methods  ***********************/
	MeasureSchedule build();
	
	MeasureSchedule.MeasureScheduleBuilder toBuilder();
	
	static MeasureSchedule.MeasureScheduleBuilder builder() {
		return new MeasureSchedule.MeasureScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MeasureSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MeasureSchedule> getType() {
		return MeasureSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("unit"), processor, UnitType.class, getUnit());
		processRosetta(path.newSubPath("datedValue"), processor, DatedValue.class, getDatedValue());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MeasureScheduleBuilder extends MeasureSchedule, MeasureBase.MeasureBaseBuilder, RosettaModelObjectBuilder {
		DatedValue.DatedValueBuilder getOrCreateDatedValue(int _index);
		List<? extends DatedValue.DatedValueBuilder> getDatedValue();
		MeasureSchedule.MeasureScheduleBuilder setValue(BigDecimal value);
		MeasureSchedule.MeasureScheduleBuilder setUnit(UnitType unit);
		MeasureSchedule.MeasureScheduleBuilder addDatedValue(DatedValue datedValue0);
		MeasureSchedule.MeasureScheduleBuilder addDatedValue(DatedValue datedValue1, int _idx);
		MeasureSchedule.MeasureScheduleBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		MeasureSchedule.MeasureScheduleBuilder setDatedValue(List<? extends DatedValue> datedValue3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
		}
		

		MeasureSchedule.MeasureScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of MeasureSchedule  ***********************/
	class MeasureScheduleImpl extends MeasureBase.MeasureBaseImpl implements MeasureSchedule {
		private final List<? extends DatedValue> datedValue;
		
		protected MeasureScheduleImpl(MeasureSchedule.MeasureScheduleBuilder builder) {
			super(builder);
			this.datedValue = ofNullable(builder.getDatedValue()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("datedValue")
		public List<? extends DatedValue> getDatedValue() {
			return datedValue;
		}
		
		@Override
		public MeasureSchedule build() {
			return this;
		}
		
		@Override
		public MeasureSchedule.MeasureScheduleBuilder toBuilder() {
			MeasureSchedule.MeasureScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MeasureSchedule.MeasureScheduleBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getDatedValue()).ifPresent(builder::setDatedValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MeasureSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(datedValue, _that.getDatedValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (datedValue != null ? datedValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MeasureSchedule {" +
				"datedValue=" + this.datedValue +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of MeasureSchedule  ***********************/
	class MeasureScheduleBuilderImpl extends MeasureBase.MeasureBaseBuilderImpl  implements MeasureSchedule.MeasureScheduleBuilder {
	
		protected List<DatedValue.DatedValueBuilder> datedValue = new ArrayList<>();
	
		public MeasureScheduleBuilderImpl() {
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
		public MeasureSchedule.MeasureScheduleBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public MeasureSchedule.MeasureScheduleBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		@Override
		public MeasureSchedule.MeasureScheduleBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public MeasureSchedule.MeasureScheduleBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public MeasureSchedule.MeasureScheduleBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public MeasureSchedule.MeasureScheduleBuilder setDatedValue(List<? extends DatedValue> datedValues) {
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
		public MeasureSchedule build() {
			return new MeasureSchedule.MeasureScheduleImpl(this);
		}
		
		@Override
		public MeasureSchedule.MeasureScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MeasureSchedule.MeasureScheduleBuilder prune() {
			super.prune();
			datedValue = datedValue.stream().filter(b->b!=null).<DatedValue.DatedValueBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getDatedValue()!=null && getDatedValue().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MeasureSchedule.MeasureScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			MeasureSchedule.MeasureScheduleBuilder o = (MeasureSchedule.MeasureScheduleBuilder) other;
			
			merger.mergeRosetta(getDatedValue(), o.getDatedValue(), this::getOrCreateDatedValue);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MeasureSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(datedValue, _that.getDatedValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (datedValue != null ? datedValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MeasureScheduleBuilder {" +
				"datedValue=" + this.datedValue +
			'}' + " " + super.toString();
		}
	}
}
