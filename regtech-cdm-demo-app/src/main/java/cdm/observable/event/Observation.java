package cdm.observable.event;

import cdm.observable.asset.Price;
import cdm.observable.event.Observation;
import cdm.observable.event.Observation.ObservationBuilder;
import cdm.observable.event.Observation.ObservationBuilderImpl;
import cdm.observable.event.Observation.ObservationImpl;
import cdm.observable.event.ObservationIdentifier;
import cdm.observable.event.meta.ObservationMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines a single, numerical value that was observed in the marketplace. Observations of market data are made independently to business events or trade life-cycle events, so data instances of Observation can be created independently of any other model type, hence it is annotated as a root type. Observations will be broadly reused in many situations, so references to Observation are supported via the &#39;key&#39; annotation.
 * @version ${project.version}
 */
@RosettaDataType(value="Observation", builder=Observation.ObservationBuilderImpl.class, version="${project.version}")
public interface Observation extends RosettaModelObject, GlobalKey {

	ObservationMeta metaData = new ObservationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the observed value as a number.
	 */
	Price getObservedValue();
	/**
	 * Represents the observation was made i.e. how to uniquely identify the observed value among the population of all available market data.
	 */
	ObservationIdentifier getObservationIdentifier();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Observation build();
	
	Observation.ObservationBuilder toBuilder();
	
	static Observation.ObservationBuilder builder() {
		return new Observation.ObservationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Observation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Observation> getType() {
		return Observation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("observedValue"), processor, Price.class, getObservedValue());
		processRosetta(path.newSubPath("observationIdentifier"), processor, ObservationIdentifier.class, getObservationIdentifier());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationBuilder extends Observation, RosettaModelObjectBuilder {
		Price.PriceBuilder getOrCreateObservedValue();
		Price.PriceBuilder getObservedValue();
		ObservationIdentifier.ObservationIdentifierBuilder getOrCreateObservationIdentifier();
		ObservationIdentifier.ObservationIdentifierBuilder getObservationIdentifier();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Observation.ObservationBuilder setObservedValue(Price observedValue);
		Observation.ObservationBuilder setObservationIdentifier(ObservationIdentifier observationIdentifier);
		Observation.ObservationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("observedValue"), processor, Price.PriceBuilder.class, getObservedValue());
			processRosetta(path.newSubPath("observationIdentifier"), processor, ObservationIdentifier.ObservationIdentifierBuilder.class, getObservationIdentifier());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Observation.ObservationBuilder prune();
	}

	/*********************** Immutable Implementation of Observation  ***********************/
	class ObservationImpl implements Observation {
		private final Price observedValue;
		private final ObservationIdentifier observationIdentifier;
		private final MetaFields meta;
		
		protected ObservationImpl(Observation.ObservationBuilder builder) {
			this.observedValue = ofNullable(builder.getObservedValue()).map(f->f.build()).orElse(null);
			this.observationIdentifier = ofNullable(builder.getObservationIdentifier()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observedValue")
		public Price getObservedValue() {
			return observedValue;
		}
		
		@Override
		@RosettaAttribute("observationIdentifier")
		public ObservationIdentifier getObservationIdentifier() {
			return observationIdentifier;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Observation build() {
			return this;
		}
		
		@Override
		public Observation.ObservationBuilder toBuilder() {
			Observation.ObservationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Observation.ObservationBuilder builder) {
			ofNullable(getObservedValue()).ifPresent(builder::setObservedValue);
			ofNullable(getObservationIdentifier()).ifPresent(builder::setObservationIdentifier);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Observation _that = getType().cast(o);
		
			if (!Objects.equals(observedValue, _that.getObservedValue())) return false;
			if (!Objects.equals(observationIdentifier, _that.getObservationIdentifier())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observedValue != null ? observedValue.hashCode() : 0);
			_result = 31 * _result + (observationIdentifier != null ? observationIdentifier.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Observation {" +
				"observedValue=" + this.observedValue + ", " +
				"observationIdentifier=" + this.observationIdentifier + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Observation  ***********************/
	class ObservationBuilderImpl implements Observation.ObservationBuilder, GlobalKeyBuilder {
	
		protected Price.PriceBuilder observedValue;
		protected ObservationIdentifier.ObservationIdentifierBuilder observationIdentifier;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ObservationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observedValue")
		public Price.PriceBuilder getObservedValue() {
			return observedValue;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateObservedValue() {
			Price.PriceBuilder result;
			if (observedValue!=null) {
				result = observedValue;
			}
			else {
				result = observedValue = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationIdentifier")
		public ObservationIdentifier.ObservationIdentifierBuilder getObservationIdentifier() {
			return observationIdentifier;
		}
		
		@Override
		public ObservationIdentifier.ObservationIdentifierBuilder getOrCreateObservationIdentifier() {
			ObservationIdentifier.ObservationIdentifierBuilder result;
			if (observationIdentifier!=null) {
				result = observationIdentifier;
			}
			else {
				result = observationIdentifier = ObservationIdentifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("observedValue")
		public Observation.ObservationBuilder setObservedValue(Price observedValue) {
			this.observedValue = observedValue==null?null:observedValue.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationIdentifier")
		public Observation.ObservationBuilder setObservationIdentifier(ObservationIdentifier observationIdentifier) {
			this.observationIdentifier = observationIdentifier==null?null:observationIdentifier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Observation.ObservationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Observation build() {
			return new Observation.ObservationImpl(this);
		}
		
		@Override
		public Observation.ObservationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Observation.ObservationBuilder prune() {
			if (observedValue!=null && !observedValue.prune().hasData()) observedValue = null;
			if (observationIdentifier!=null && !observationIdentifier.prune().hasData()) observationIdentifier = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservedValue()!=null && getObservedValue().hasData()) return true;
			if (getObservationIdentifier()!=null && getObservationIdentifier().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Observation.ObservationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Observation.ObservationBuilder o = (Observation.ObservationBuilder) other;
			
			merger.mergeRosetta(getObservedValue(), o.getObservedValue(), this::setObservedValue);
			merger.mergeRosetta(getObservationIdentifier(), o.getObservationIdentifier(), this::setObservationIdentifier);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Observation _that = getType().cast(o);
		
			if (!Objects.equals(observedValue, _that.getObservedValue())) return false;
			if (!Objects.equals(observationIdentifier, _that.getObservationIdentifier())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observedValue != null ? observedValue.hashCode() : 0);
			_result = 31 * _result + (observationIdentifier != null ? observationIdentifier.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationBuilder {" +
				"observedValue=" + this.observedValue + ", " +
				"observationIdentifier=" + this.observationIdentifier + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
