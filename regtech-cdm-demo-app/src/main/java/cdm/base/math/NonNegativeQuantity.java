package cdm.base.math;

import cdm.base.datetime.Frequency;
import cdm.base.math.DatedValue;
import cdm.base.math.Measure;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.NonNegativeQuantity.NonNegativeQuantityBuilder;
import cdm.base.math.NonNegativeQuantity.NonNegativeQuantityBuilderImpl;
import cdm.base.math.NonNegativeQuantity.NonNegativeQuantityImpl;
import cdm.base.math.Quantity;
import cdm.base.math.Quantity.QuantityBuilder;
import cdm.base.math.Quantity.QuantityBuilderImpl;
import cdm.base.math.Quantity.QuantityImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.NonNegativeQuantityMeta;
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
 * Specifies a quantity as a non-negative number, which condition is enforced through a data rule that only applies to the extending class.
 * @version ${project.version}
 */
@RosettaDataType(value="NonNegativeQuantity", builder=NonNegativeQuantity.NonNegativeQuantityBuilderImpl.class, version="${project.version}")
public interface NonNegativeQuantity extends Quantity {

	NonNegativeQuantityMeta metaData = new NonNegativeQuantityMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	NonNegativeQuantity build();
	
	NonNegativeQuantity.NonNegativeQuantityBuilder toBuilder();
	
	static NonNegativeQuantity.NonNegativeQuantityBuilder builder() {
		return new NonNegativeQuantity.NonNegativeQuantityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NonNegativeQuantity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NonNegativeQuantity> getType() {
		return NonNegativeQuantity.class;
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
	interface NonNegativeQuantityBuilder extends NonNegativeQuantity, Quantity.QuantityBuilder, RosettaModelObjectBuilder {
		NonNegativeQuantity.NonNegativeQuantityBuilder setValue(BigDecimal value);
		NonNegativeQuantity.NonNegativeQuantityBuilder setUnit(UnitType unit);
		NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(DatedValue datedValue0);
		NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(DatedValue datedValue1, int _idx);
		NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		NonNegativeQuantity.NonNegativeQuantityBuilder setDatedValue(List<? extends DatedValue> datedValue3);
		NonNegativeQuantity.NonNegativeQuantityBuilder setMultiplier(Measure multiplier);
		NonNegativeQuantity.NonNegativeQuantityBuilder setFrequency(Frequency frequency);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
			processRosetta(path.newSubPath("multiplier"), processor, Measure.MeasureBuilder.class, getMultiplier());
			processRosetta(path.newSubPath("frequency"), processor, Frequency.FrequencyBuilder.class, getFrequency());
		}
		

		NonNegativeQuantity.NonNegativeQuantityBuilder prune();
	}

	/*********************** Immutable Implementation of NonNegativeQuantity  ***********************/
	class NonNegativeQuantityImpl extends Quantity.QuantityImpl implements NonNegativeQuantity {
		
		protected NonNegativeQuantityImpl(NonNegativeQuantity.NonNegativeQuantityBuilder builder) {
			super(builder);
		}
		
		@Override
		public NonNegativeQuantity build() {
			return this;
		}
		
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder toBuilder() {
			NonNegativeQuantity.NonNegativeQuantityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NonNegativeQuantity.NonNegativeQuantityBuilder builder) {
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
			return "NonNegativeQuantity {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of NonNegativeQuantity  ***********************/
	class NonNegativeQuantityBuilderImpl extends Quantity.QuantityBuilderImpl  implements NonNegativeQuantity.NonNegativeQuantityBuilder {
	
	
		public NonNegativeQuantityBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("value")
		public NonNegativeQuantity.NonNegativeQuantityBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public NonNegativeQuantity.NonNegativeQuantityBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public NonNegativeQuantity.NonNegativeQuantityBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public NonNegativeQuantity.NonNegativeQuantityBuilder setDatedValue(List<? extends DatedValue> datedValues) {
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
		public NonNegativeQuantity.NonNegativeQuantityBuilder setMultiplier(Measure multiplier) {
			this.multiplier = multiplier==null?null:multiplier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("frequency")
		public NonNegativeQuantity.NonNegativeQuantityBuilder setFrequency(Frequency frequency) {
			this.frequency = frequency==null?null:frequency.toBuilder();
			return this;
		}
		
		@Override
		public NonNegativeQuantity build() {
			return new NonNegativeQuantity.NonNegativeQuantityImpl(this);
		}
		
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder prune() {
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
		public NonNegativeQuantity.NonNegativeQuantityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			NonNegativeQuantity.NonNegativeQuantityBuilder o = (NonNegativeQuantity.NonNegativeQuantityBuilder) other;
			
			
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
			return "NonNegativeQuantityBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
