package cdm.base.math;

import cdm.base.math.Measure;
import cdm.base.math.Measure.MeasureBuilder;
import cdm.base.math.Measure.MeasureBuilderImpl;
import cdm.base.math.Measure.MeasureImpl;
import cdm.base.math.MeasureBase;
import cdm.base.math.MeasureBase.MeasureBaseBuilder;
import cdm.base.math.MeasureBase.MeasureBaseBuilderImpl;
import cdm.base.math.MeasureBase.MeasureBaseImpl;
import cdm.base.math.UnitType;
import cdm.base.math.meta.MeasureMeta;
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


/**
 * Defines a concrete measure as a number associated to a unit. It extends MeasureBase by requiring the value attribute to be present. A measure may be unit-less so the unit attribute is still optional.
 * @version ${project.version}
 */
@RosettaDataType(value="Measure", builder=Measure.MeasureBuilderImpl.class, version="${project.version}")
public interface Measure extends MeasureBase {

	MeasureMeta metaData = new MeasureMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Measure build();
	
	Measure.MeasureBuilder toBuilder();
	
	static Measure.MeasureBuilder builder() {
		return new Measure.MeasureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Measure> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Measure> getType() {
		return Measure.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("unit"), processor, UnitType.class, getUnit());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MeasureBuilder extends Measure, MeasureBase.MeasureBaseBuilder, RosettaModelObjectBuilder {
		Measure.MeasureBuilder setValue(BigDecimal value);
		Measure.MeasureBuilder setUnit(UnitType unit);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
		}
		

		Measure.MeasureBuilder prune();
	}

	/*********************** Immutable Implementation of Measure  ***********************/
	class MeasureImpl extends MeasureBase.MeasureBaseImpl implements Measure {
		
		protected MeasureImpl(Measure.MeasureBuilder builder) {
			super(builder);
		}
		
		@Override
		public Measure build() {
			return this;
		}
		
		@Override
		public Measure.MeasureBuilder toBuilder() {
			Measure.MeasureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Measure.MeasureBuilder builder) {
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
			return "Measure {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Measure  ***********************/
	class MeasureBuilderImpl extends MeasureBase.MeasureBaseBuilderImpl  implements Measure.MeasureBuilder {
	
	
		public MeasureBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("value")
		public Measure.MeasureBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public Measure.MeasureBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		
		@Override
		public Measure build() {
			return new Measure.MeasureImpl(this);
		}
		
		@Override
		public Measure.MeasureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Measure.MeasureBuilder prune() {
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
		public Measure.MeasureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Measure.MeasureBuilder o = (Measure.MeasureBuilder) other;
			
			
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
			return "MeasureBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
