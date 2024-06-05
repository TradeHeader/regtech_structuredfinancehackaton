package cdm.base.math;

import cdm.base.datetime.Frequency;
import cdm.base.math.DatedValue;
import cdm.base.math.Measure;
import cdm.base.math.Quantity;
import cdm.base.math.Quantity.QuantityBuilder;
import cdm.base.math.Quantity.QuantityBuilderImpl;
import cdm.base.math.Quantity.QuantityImpl;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.QuantitySchedule.QuantityScheduleBuilder;
import cdm.base.math.QuantitySchedule.QuantityScheduleBuilderImpl;
import cdm.base.math.QuantitySchedule.QuantityScheduleImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.QuantityMeta;
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
 * Specifies a quantity as a single value to be associated to a financial product, for example a transfer amount resulting from a trade. This data type extends QuantitySchedule and requires that only the single amount value exists.
 * @version ${project.version}
 */
@RosettaDataType(value="Quantity", builder=Quantity.QuantityBuilderImpl.class, version="${project.version}")
public interface Quantity extends QuantitySchedule {

	QuantityMeta metaData = new QuantityMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Quantity build();
	
	Quantity.QuantityBuilder toBuilder();
	
	static Quantity.QuantityBuilder builder() {
		return new Quantity.QuantityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Quantity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Quantity> getType() {
		return Quantity.class;
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
	interface QuantityBuilder extends Quantity, QuantitySchedule.QuantityScheduleBuilder, RosettaModelObjectBuilder {
		Quantity.QuantityBuilder setValue(BigDecimal value);
		Quantity.QuantityBuilder setUnit(UnitType unit);
		Quantity.QuantityBuilder addDatedValue(DatedValue datedValue0);
		Quantity.QuantityBuilder addDatedValue(DatedValue datedValue1, int _idx);
		Quantity.QuantityBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		Quantity.QuantityBuilder setDatedValue(List<? extends DatedValue> datedValue3);
		Quantity.QuantityBuilder setMultiplier(Measure multiplier);
		Quantity.QuantityBuilder setFrequency(Frequency frequency);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
			processRosetta(path.newSubPath("multiplier"), processor, Measure.MeasureBuilder.class, getMultiplier());
			processRosetta(path.newSubPath("frequency"), processor, Frequency.FrequencyBuilder.class, getFrequency());
		}
		

		Quantity.QuantityBuilder prune();
	}

	/*********************** Immutable Implementation of Quantity  ***********************/
	class QuantityImpl extends QuantitySchedule.QuantityScheduleImpl implements Quantity {
		
		protected QuantityImpl(Quantity.QuantityBuilder builder) {
			super(builder);
		}
		
		@Override
		public Quantity build() {
			return this;
		}
		
		@Override
		public Quantity.QuantityBuilder toBuilder() {
			Quantity.QuantityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Quantity.QuantityBuilder builder) {
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
			return "Quantity {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Quantity  ***********************/
	class QuantityBuilderImpl extends QuantitySchedule.QuantityScheduleBuilderImpl  implements Quantity.QuantityBuilder {
	
	
		public QuantityBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("value")
		public Quantity.QuantityBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public Quantity.QuantityBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		@Override
		public Quantity.QuantityBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public Quantity.QuantityBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public Quantity.QuantityBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public Quantity.QuantityBuilder setDatedValue(List<? extends DatedValue> datedValues) {
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
		public Quantity.QuantityBuilder setMultiplier(Measure multiplier) {
			this.multiplier = multiplier==null?null:multiplier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("frequency")
		public Quantity.QuantityBuilder setFrequency(Frequency frequency) {
			this.frequency = frequency==null?null:frequency.toBuilder();
			return this;
		}
		
		@Override
		public Quantity build() {
			return new Quantity.QuantityImpl(this);
		}
		
		@Override
		public Quantity.QuantityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Quantity.QuantityBuilder prune() {
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
		public Quantity.QuantityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Quantity.QuantityBuilder o = (Quantity.QuantityBuilder) other;
			
			
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
			return "QuantityBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
