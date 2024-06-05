package cdm.observable.asset.fro;

import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
import cdm.observable.asset.fro.FloatingRateIndexDefinition;
import cdm.observable.asset.fro.FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder;
import cdm.observable.asset.fro.FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilderImpl;
import cdm.observable.asset.fro.FloatingRateIndexDefinition.FloatingRateIndexDefinitionImpl;
import cdm.observable.asset.fro.FloatingRateIndexIdentification;
import cdm.observable.asset.fro.meta.FloatingRateIndexDefinitionMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateIndexDefinition", builder=FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilderImpl.class, version="${project.version}")
public interface FloatingRateIndexDefinition extends RosettaModelObject {

	FloatingRateIndexDefinitionMeta metaData = new FloatingRateIndexDefinitionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The underlying FRO name and designated maturity.
	 */
	FloatingRateIndexIdentification getFro();
	/**
	 * Any calculation default values.
	 */
	FloatingRateIndexCalculationDefaults getCalculationDefaults();

	/*********************** Build Methods  ***********************/
	FloatingRateIndexDefinition build();
	
	FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder toBuilder();
	
	static FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder builder() {
		return new FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateIndexDefinition> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateIndexDefinition> getType() {
		return FloatingRateIndexDefinition.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("fro"), processor, FloatingRateIndexIdentification.class, getFro());
		processRosetta(path.newSubPath("calculationDefaults"), processor, FloatingRateIndexCalculationDefaults.class, getCalculationDefaults());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateIndexDefinitionBuilder extends FloatingRateIndexDefinition, RosettaModelObjectBuilder {
		FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder getOrCreateFro();
		FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder getFro();
		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder getOrCreateCalculationDefaults();
		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder getCalculationDefaults();
		FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder setFro(FloatingRateIndexIdentification fro);
		FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder setCalculationDefaults(FloatingRateIndexCalculationDefaults calculationDefaults);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("fro"), processor, FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder.class, getFro());
			processRosetta(path.newSubPath("calculationDefaults"), processor, FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder.class, getCalculationDefaults());
		}
		

		FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateIndexDefinition  ***********************/
	class FloatingRateIndexDefinitionImpl implements FloatingRateIndexDefinition {
		private final FloatingRateIndexIdentification fro;
		private final FloatingRateIndexCalculationDefaults calculationDefaults;
		
		protected FloatingRateIndexDefinitionImpl(FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder builder) {
			this.fro = ofNullable(builder.getFro()).map(f->f.build()).orElse(null);
			this.calculationDefaults = ofNullable(builder.getCalculationDefaults()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("fro")
		public FloatingRateIndexIdentification getFro() {
			return fro;
		}
		
		@Override
		@RosettaAttribute("calculationDefaults")
		public FloatingRateIndexCalculationDefaults getCalculationDefaults() {
			return calculationDefaults;
		}
		
		@Override
		public FloatingRateIndexDefinition build() {
			return this;
		}
		
		@Override
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder toBuilder() {
			FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder builder) {
			ofNullable(getFro()).ifPresent(builder::setFro);
			ofNullable(getCalculationDefaults()).ifPresent(builder::setCalculationDefaults);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateIndexDefinition _that = getType().cast(o);
		
			if (!Objects.equals(fro, _that.getFro())) return false;
			if (!Objects.equals(calculationDefaults, _that.getCalculationDefaults())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fro != null ? fro.hashCode() : 0);
			_result = 31 * _result + (calculationDefaults != null ? calculationDefaults.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateIndexDefinition {" +
				"fro=" + this.fro + ", " +
				"calculationDefaults=" + this.calculationDefaults +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateIndexDefinition  ***********************/
	class FloatingRateIndexDefinitionBuilderImpl implements FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder {
	
		protected FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder fro;
		protected FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder calculationDefaults;
	
		public FloatingRateIndexDefinitionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fro")
		public FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder getFro() {
			return fro;
		}
		
		@Override
		public FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder getOrCreateFro() {
			FloatingRateIndexIdentification.FloatingRateIndexIdentificationBuilder result;
			if (fro!=null) {
				result = fro;
			}
			else {
				result = fro = FloatingRateIndexIdentification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationDefaults")
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder getCalculationDefaults() {
			return calculationDefaults;
		}
		
		@Override
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder getOrCreateCalculationDefaults() {
			FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder result;
			if (calculationDefaults!=null) {
				result = calculationDefaults;
			}
			else {
				result = calculationDefaults = FloatingRateIndexCalculationDefaults.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("fro")
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder setFro(FloatingRateIndexIdentification fro) {
			this.fro = fro==null?null:fro.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationDefaults")
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder setCalculationDefaults(FloatingRateIndexCalculationDefaults calculationDefaults) {
			this.calculationDefaults = calculationDefaults==null?null:calculationDefaults.toBuilder();
			return this;
		}
		
		@Override
		public FloatingRateIndexDefinition build() {
			return new FloatingRateIndexDefinition.FloatingRateIndexDefinitionImpl(this);
		}
		
		@Override
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder prune() {
			if (fro!=null && !fro.prune().hasData()) fro = null;
			if (calculationDefaults!=null && !calculationDefaults.prune().hasData()) calculationDefaults = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFro()!=null && getFro().hasData()) return true;
			if (getCalculationDefaults()!=null && getCalculationDefaults().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder o = (FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder) other;
			
			merger.mergeRosetta(getFro(), o.getFro(), this::setFro);
			merger.mergeRosetta(getCalculationDefaults(), o.getCalculationDefaults(), this::setCalculationDefaults);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateIndexDefinition _that = getType().cast(o);
		
			if (!Objects.equals(fro, _that.getFro())) return false;
			if (!Objects.equals(calculationDefaults, _that.getCalculationDefaults())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fro != null ? fro.hashCode() : 0);
			_result = 31 * _result + (calculationDefaults != null ? calculationDefaults.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateIndexDefinitionBuilder {" +
				"fro=" + this.fro + ", " +
				"calculationDefaults=" + this.calculationDefaults +
			'}';
		}
	}
}
