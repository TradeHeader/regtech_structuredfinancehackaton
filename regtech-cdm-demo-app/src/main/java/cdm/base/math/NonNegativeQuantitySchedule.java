package cdm.base.math;

import cdm.base.datetime.Frequency;
import cdm.base.math.DatedValue;
import cdm.base.math.Measure;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder;
import cdm.base.math.NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilderImpl;
import cdm.base.math.NonNegativeQuantitySchedule.NonNegativeQuantityScheduleImpl;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.QuantitySchedule.QuantityScheduleBuilder;
import cdm.base.math.QuantitySchedule.QuantityScheduleBuilderImpl;
import cdm.base.math.QuantitySchedule.QuantityScheduleImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.NonNegativeQuantityScheduleMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @version ${project.version}
 */
@RosettaDataType(value="NonNegativeQuantitySchedule", builder=NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilderImpl.class, version="${project.version}")
public interface NonNegativeQuantitySchedule extends QuantitySchedule {

	NonNegativeQuantityScheduleMeta metaData = new NonNegativeQuantityScheduleMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	NonNegativeQuantitySchedule build();
	
	NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder toBuilder();
	
	static NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder builder() {
		return new NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NonNegativeQuantitySchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NonNegativeQuantitySchedule> getType() {
		return NonNegativeQuantitySchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("unit"), processor, UnitType.class, getUnit());
		processRosetta(path.newSubPath("datedValue"), processor, DatedValue.class, getDatedValue());
		processRosetta(path.newSubPath("multiplier"), processor, Measure.class, getMultiplier());
		processRosetta(path.newSubPath("frequency"), processor, Frequency.class, getFrequency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NonNegativeQuantityScheduleBuilder extends NonNegativeQuantitySchedule, QuantitySchedule.QuantityScheduleBuilder, RosettaModelObjectBuilder {
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setValue(BigDecimal value);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setUnit(UnitType unit);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(DatedValue datedValue0);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(DatedValue datedValue1, int _idx);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setDatedValue(List<? extends DatedValue> datedValue3);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setMultiplier(Measure multiplier);
		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setFrequency(Frequency frequency);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
			processRosetta(path.newSubPath("multiplier"), processor, Measure.MeasureBuilder.class, getMultiplier());
			processRosetta(path.newSubPath("frequency"), processor, Frequency.FrequencyBuilder.class, getFrequency());
		}
		

		NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of NonNegativeQuantitySchedule  ***********************/
	class NonNegativeQuantityScheduleImpl extends QuantitySchedule.QuantityScheduleImpl implements NonNegativeQuantitySchedule {
		
		protected NonNegativeQuantityScheduleImpl(NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder builder) {
			super(builder);
		}
		
		@Override
		public NonNegativeQuantitySchedule build() {
			return this;
		}
		
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder toBuilder() {
			NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder builder) {
			super.setBuilderFields(builder);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "NonNegativeQuantitySchedule {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of NonNegativeQuantitySchedule  ***********************/
	class NonNegativeQuantityScheduleBuilderImpl extends QuantitySchedule.QuantityScheduleBuilderImpl  implements NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder {
	
	
		public NonNegativeQuantityScheduleBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("value")
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setDatedValue(List<? extends DatedValue> datedValues) {
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
		@RosettaAttribute("multiplier")
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setMultiplier(Measure multiplier) {
			this.multiplier = multiplier==null?null:multiplier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("frequency")
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder setFrequency(Frequency frequency) {
			this.frequency = frequency==null?null:frequency.toBuilder();
			return this;
		}
		
		@Override
		public NonNegativeQuantitySchedule build() {
			return new NonNegativeQuantitySchedule.NonNegativeQuantityScheduleImpl(this);
		}
		
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder o = (NonNegativeQuantitySchedule.NonNegativeQuantityScheduleBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "NonNegativeQuantityScheduleBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
