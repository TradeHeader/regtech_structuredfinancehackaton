package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyBuilder;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyBuilderImpl;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyImpl;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import cdm.base.staticdata.asset.common.meta.TaxonomyMeta;
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
 * Defines the taxonomy of an object by combining a taxonomy source (i.e. the rules to classify the object) and a value (i.e. the output of those rules on the object).
 * @version ${project.version}
 */
@RosettaDataType(value="Taxonomy", builder=Taxonomy.TaxonomyBuilderImpl.class, version="${project.version}")
public interface Taxonomy extends RosettaModelObject {

	TaxonomyMeta metaData = new TaxonomyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The source of the taxonomy that defines the rules for classifying the object. The taxonomy source is taken from a enumerated list of taxonomy names. Optional as the taxonomy source may not be provided.
	 */
	TaxonomySourceEnum getSource();
	/**
	 * The value according to that taxonomy. Optional as it may not be possible to classify the object in that taxonomy.
	 */
	TaxonomyValue getValue();

	/*********************** Build Methods  ***********************/
	Taxonomy build();
	
	Taxonomy.TaxonomyBuilder toBuilder();
	
	static Taxonomy.TaxonomyBuilder builder() {
		return new Taxonomy.TaxonomyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Taxonomy> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Taxonomy> getType() {
		return Taxonomy.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("source"), TaxonomySourceEnum.class, getSource(), this);
		processRosetta(path.newSubPath("value"), processor, TaxonomyValue.class, getValue());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TaxonomyBuilder extends Taxonomy, RosettaModelObjectBuilder {
		TaxonomyValue.TaxonomyValueBuilder getOrCreateValue();
		TaxonomyValue.TaxonomyValueBuilder getValue();
		Taxonomy.TaxonomyBuilder setSource(TaxonomySourceEnum source);
		Taxonomy.TaxonomyBuilder setValue(TaxonomyValue value);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("source"), TaxonomySourceEnum.class, getSource(), this);
			processRosetta(path.newSubPath("value"), processor, TaxonomyValue.TaxonomyValueBuilder.class, getValue());
		}
		

		Taxonomy.TaxonomyBuilder prune();
	}

	/*********************** Immutable Implementation of Taxonomy  ***********************/
	class TaxonomyImpl implements Taxonomy {
		private final TaxonomySourceEnum source;
		private final TaxonomyValue value;
		
		protected TaxonomyImpl(Taxonomy.TaxonomyBuilder builder) {
			this.source = builder.getSource();
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("source")
		public TaxonomySourceEnum getSource() {
			return source;
		}
		
		@Override
		@RosettaAttribute("value")
		public TaxonomyValue getValue() {
			return value;
		}
		
		@Override
		public Taxonomy build() {
			return this;
		}
		
		@Override
		public Taxonomy.TaxonomyBuilder toBuilder() {
			Taxonomy.TaxonomyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Taxonomy.TaxonomyBuilder builder) {
			ofNullable(getSource()).ifPresent(builder::setSource);
			ofNullable(getValue()).ifPresent(builder::setValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Taxonomy _that = getType().cast(o);
		
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Taxonomy {" +
				"source=" + this.source + ", " +
				"value=" + this.value +
			'}';
		}
	}

	/*********************** Builder Implementation of Taxonomy  ***********************/
	class TaxonomyBuilderImpl implements Taxonomy.TaxonomyBuilder {
	
		protected TaxonomySourceEnum source;
		protected TaxonomyValue.TaxonomyValueBuilder value;
	
		public TaxonomyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("source")
		public TaxonomySourceEnum getSource() {
			return source;
		}
		
		@Override
		@RosettaAttribute("value")
		public TaxonomyValue.TaxonomyValueBuilder getValue() {
			return value;
		}
		
		@Override
		public TaxonomyValue.TaxonomyValueBuilder getOrCreateValue() {
			TaxonomyValue.TaxonomyValueBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = TaxonomyValue.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("source")
		public Taxonomy.TaxonomyBuilder setSource(TaxonomySourceEnum source) {
			this.source = source==null?null:source;
			return this;
		}
		@Override
		@RosettaAttribute("value")
		public Taxonomy.TaxonomyBuilder setValue(TaxonomyValue value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		
		@Override
		public Taxonomy build() {
			return new Taxonomy.TaxonomyImpl(this);
		}
		
		@Override
		public Taxonomy.TaxonomyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Taxonomy.TaxonomyBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSource()!=null) return true;
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Taxonomy.TaxonomyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Taxonomy.TaxonomyBuilder o = (Taxonomy.TaxonomyBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			
			merger.mergeBasic(getSource(), o.getSource(), this::setSource);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Taxonomy _that = getType().cast(o);
		
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TaxonomyBuilder {" +
				"source=" + this.source + ", " +
				"value=" + this.value +
			'}';
		}
	}
}
