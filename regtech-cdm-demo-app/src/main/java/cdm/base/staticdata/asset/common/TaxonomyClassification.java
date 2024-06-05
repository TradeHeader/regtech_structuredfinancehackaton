package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.TaxonomyClassification.TaxonomyClassificationBuilder;
import cdm.base.staticdata.asset.common.TaxonomyClassification.TaxonomyClassificationBuilderImpl;
import cdm.base.staticdata.asset.common.TaxonomyClassification.TaxonomyClassificationImpl;
import cdm.base.staticdata.asset.common.meta.TaxonomyClassificationMeta;
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
@RosettaDataType(value="TaxonomyClassification", builder=TaxonomyClassification.TaxonomyClassificationBuilderImpl.class, version="${project.version}")
public interface TaxonomyClassification extends RosettaModelObject {

	TaxonomyClassificationMeta metaData = new TaxonomyClassificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The name defined by the classification system for a specific attribute in the taxonomy
	 */
	String getClassName();
	/**
	 * The value set by the taxonomy that is specific to the className attribute.
	 */
	String getValue();
	/**
	 * A description of the class.
	 */
	String getDescription();
	/**
	 * In the case of multi-layered hierarchical classification systems such as commodity classification, the layer the value and className occupy in the classification hierarchy, where 1 represents the top-layer.
	 */
	Integer getOrdinal();

	/*********************** Build Methods  ***********************/
	TaxonomyClassification build();
	
	TaxonomyClassification.TaxonomyClassificationBuilder toBuilder();
	
	static TaxonomyClassification.TaxonomyClassificationBuilder builder() {
		return new TaxonomyClassification.TaxonomyClassificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TaxonomyClassification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TaxonomyClassification> getType() {
		return TaxonomyClassification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("className"), String.class, getClassName(), this);
		processor.processBasic(path.newSubPath("value"), String.class, getValue(), this);
		processor.processBasic(path.newSubPath("description"), String.class, getDescription(), this);
		processor.processBasic(path.newSubPath("ordinal"), Integer.class, getOrdinal(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TaxonomyClassificationBuilder extends TaxonomyClassification, RosettaModelObjectBuilder {
		TaxonomyClassification.TaxonomyClassificationBuilder setClassName(String className);
		TaxonomyClassification.TaxonomyClassificationBuilder setValue(String value);
		TaxonomyClassification.TaxonomyClassificationBuilder setDescription(String description);
		TaxonomyClassification.TaxonomyClassificationBuilder setOrdinal(Integer ordinal);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("className"), String.class, getClassName(), this);
			processor.processBasic(path.newSubPath("value"), String.class, getValue(), this);
			processor.processBasic(path.newSubPath("description"), String.class, getDescription(), this);
			processor.processBasic(path.newSubPath("ordinal"), Integer.class, getOrdinal(), this);
		}
		

		TaxonomyClassification.TaxonomyClassificationBuilder prune();
	}

	/*********************** Immutable Implementation of TaxonomyClassification  ***********************/
	class TaxonomyClassificationImpl implements TaxonomyClassification {
		private final String className;
		private final String value;
		private final String description;
		private final Integer ordinal;
		
		protected TaxonomyClassificationImpl(TaxonomyClassification.TaxonomyClassificationBuilder builder) {
			this.className = builder.getClassName();
			this.value = builder.getValue();
			this.description = builder.getDescription();
			this.ordinal = builder.getOrdinal();
		}
		
		@Override
		@RosettaAttribute("className")
		public String getClassName() {
			return className;
		}
		
		@Override
		@RosettaAttribute("value")
		public String getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("description")
		public String getDescription() {
			return description;
		}
		
		@Override
		@RosettaAttribute("ordinal")
		public Integer getOrdinal() {
			return ordinal;
		}
		
		@Override
		public TaxonomyClassification build() {
			return this;
		}
		
		@Override
		public TaxonomyClassification.TaxonomyClassificationBuilder toBuilder() {
			TaxonomyClassification.TaxonomyClassificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TaxonomyClassification.TaxonomyClassificationBuilder builder) {
			ofNullable(getClassName()).ifPresent(builder::setClassName);
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getDescription()).ifPresent(builder::setDescription);
			ofNullable(getOrdinal()).ifPresent(builder::setOrdinal);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TaxonomyClassification _that = getType().cast(o);
		
			if (!Objects.equals(className, _that.getClassName())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(description, _that.getDescription())) return false;
			if (!Objects.equals(ordinal, _that.getOrdinal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (className != null ? className.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (description != null ? description.hashCode() : 0);
			_result = 31 * _result + (ordinal != null ? ordinal.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TaxonomyClassification {" +
				"className=" + this.className + ", " +
				"value=" + this.value + ", " +
				"description=" + this.description + ", " +
				"ordinal=" + this.ordinal +
			'}';
		}
	}

	/*********************** Builder Implementation of TaxonomyClassification  ***********************/
	class TaxonomyClassificationBuilderImpl implements TaxonomyClassification.TaxonomyClassificationBuilder {
	
		protected String className;
		protected String value;
		protected String description;
		protected Integer ordinal;
	
		public TaxonomyClassificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("className")
		public String getClassName() {
			return className;
		}
		
		@Override
		@RosettaAttribute("value")
		public String getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("description")
		public String getDescription() {
			return description;
		}
		
		@Override
		@RosettaAttribute("ordinal")
		public Integer getOrdinal() {
			return ordinal;
		}
		
	
		@Override
		@RosettaAttribute("className")
		public TaxonomyClassification.TaxonomyClassificationBuilder setClassName(String className) {
			this.className = className==null?null:className;
			return this;
		}
		@Override
		@RosettaAttribute("value")
		public TaxonomyClassification.TaxonomyClassificationBuilder setValue(String value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("description")
		public TaxonomyClassification.TaxonomyClassificationBuilder setDescription(String description) {
			this.description = description==null?null:description;
			return this;
		}
		@Override
		@RosettaAttribute("ordinal")
		public TaxonomyClassification.TaxonomyClassificationBuilder setOrdinal(Integer ordinal) {
			this.ordinal = ordinal==null?null:ordinal;
			return this;
		}
		
		@Override
		public TaxonomyClassification build() {
			return new TaxonomyClassification.TaxonomyClassificationImpl(this);
		}
		
		@Override
		public TaxonomyClassification.TaxonomyClassificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TaxonomyClassification.TaxonomyClassificationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getClassName()!=null) return true;
			if (getValue()!=null) return true;
			if (getDescription()!=null) return true;
			if (getOrdinal()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TaxonomyClassification.TaxonomyClassificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TaxonomyClassification.TaxonomyClassificationBuilder o = (TaxonomyClassification.TaxonomyClassificationBuilder) other;
			
			
			merger.mergeBasic(getClassName(), o.getClassName(), this::setClassName);
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			merger.mergeBasic(getDescription(), o.getDescription(), this::setDescription);
			merger.mergeBasic(getOrdinal(), o.getOrdinal(), this::setOrdinal);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TaxonomyClassification _that = getType().cast(o);
		
			if (!Objects.equals(className, _that.getClassName())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(description, _that.getDescription())) return false;
			if (!Objects.equals(ordinal, _that.getOrdinal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (className != null ? className.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (description != null ? description.hashCode() : 0);
			_result = 31 * _result + (ordinal != null ? ordinal.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TaxonomyClassificationBuilder {" +
				"className=" + this.className + ", " +
				"value=" + this.value + ", " +
				"description=" + this.description + ", " +
				"ordinal=" + this.ordinal +
			'}';
		}
	}
}
