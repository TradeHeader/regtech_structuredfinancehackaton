package cdm.base.math;

import cdm.base.math.MeasureBase;
import cdm.base.math.MeasureBase.MeasureBaseBuilder;
import cdm.base.math.MeasureBase.MeasureBaseBuilderImpl;
import cdm.base.math.MeasureBase.MeasureBaseImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.MeasureBaseMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Provides an abstract type to define a measure as a number associated to a unit. This type is abstract because all its attributes are optional. The types that extend it can specify further existence constraints.
 * @version ${project.version}
 */
@RosettaDataType(value="MeasureBase", builder=MeasureBase.MeasureBaseBuilderImpl.class, version="${project.version}")
public interface MeasureBase extends RosettaModelObject {

	MeasureBaseMeta metaData = new MeasureBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the value of the measure as a number. Optional because in a measure vector or schedule, this single value may be omitted.
	 */
	BigDecimal getValue();
	/**
	 * Qualifies the unit by which the amount is measured. Optional because a measure may be unit-less (e.g. when representing a ratio between amounts in the same unit).
	 */
	UnitType getUnit();

	/*********************** Build Methods  ***********************/
	MeasureBase build();
	
	MeasureBase.MeasureBaseBuilder toBuilder();
	
	static MeasureBase.MeasureBaseBuilder builder() {
		return new MeasureBase.MeasureBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MeasureBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MeasureBase> getType() {
		return MeasureBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("unit"), processor, UnitType.class, getUnit());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MeasureBaseBuilder extends MeasureBase, RosettaModelObjectBuilder {
		UnitType.UnitTypeBuilder getOrCreateUnit();
		UnitType.UnitTypeBuilder getUnit();
		MeasureBase.MeasureBaseBuilder setValue(BigDecimal value);
		MeasureBase.MeasureBaseBuilder setUnit(UnitType unit);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
		}
		

		MeasureBase.MeasureBaseBuilder prune();
	}

	/*********************** Immutable Implementation of MeasureBase  ***********************/
	class MeasureBaseImpl implements MeasureBase {
		private final BigDecimal value;
		private final UnitType unit;
		
		protected MeasureBaseImpl(MeasureBase.MeasureBaseBuilder builder) {
			this.value = builder.getValue();
			this.unit = ofNullable(builder.getUnit()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("unit")
		public UnitType getUnit() {
			return unit;
		}
		
		@Override
		public MeasureBase build() {
			return this;
		}
		
		@Override
		public MeasureBase.MeasureBaseBuilder toBuilder() {
			MeasureBase.MeasureBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MeasureBase.MeasureBaseBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getUnit()).ifPresent(builder::setUnit);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MeasureBase _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(unit, _that.getUnit())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (unit != null ? unit.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MeasureBase {" +
				"value=" + this.value + ", " +
				"unit=" + this.unit +
			'}';
		}
	}

	/*********************** Builder Implementation of MeasureBase  ***********************/
	class MeasureBaseBuilderImpl implements MeasureBase.MeasureBaseBuilder {
	
		protected BigDecimal value;
		protected UnitType.UnitTypeBuilder unit;
	
		public MeasureBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("unit")
		public UnitType.UnitTypeBuilder getUnit() {
			return unit;
		}
		
		@Override
		public UnitType.UnitTypeBuilder getOrCreateUnit() {
			UnitType.UnitTypeBuilder result;
			if (unit!=null) {
				result = unit;
			}
			else {
				result = unit = UnitType.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("value")
		public MeasureBase.MeasureBaseBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public MeasureBase.MeasureBaseBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		
		@Override
		public MeasureBase build() {
			return new MeasureBase.MeasureBaseImpl(this);
		}
		
		@Override
		public MeasureBase.MeasureBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MeasureBase.MeasureBaseBuilder prune() {
			if (unit!=null && !unit.prune().hasData()) unit = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null) return true;
			if (getUnit()!=null && getUnit().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MeasureBase.MeasureBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MeasureBase.MeasureBaseBuilder o = (MeasureBase.MeasureBaseBuilder) other;
			
			merger.mergeRosetta(getUnit(), o.getUnit(), this::setUnit);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MeasureBase _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(unit, _that.getUnit())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (unit != null ? unit.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MeasureBaseBuilder {" +
				"value=" + this.value + ", " +
				"unit=" + this.unit +
			'}';
		}
	}
}
