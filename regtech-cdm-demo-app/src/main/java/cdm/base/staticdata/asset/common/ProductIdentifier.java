package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.ProductIdTypeEnum;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductIdentifier.ProductIdentifierBuilder;
import cdm.base.staticdata.asset.common.ProductIdentifier.ProductIdentifierBuilderImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier.ProductIdentifierImpl;
import cdm.base.staticdata.asset.common.meta.ProductIdentifierMeta;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Comprises an identifier and a source. The associated metadata key denotes the ability to associate a hash value to the ProductIdentifier instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="ProductIdentifier", builder=ProductIdentifier.ProductIdentifierBuilderImpl.class, version="${project.version}")
public interface ProductIdentifier extends RosettaModelObject, GlobalKey {

	ProductIdentifierMeta metaData = new ProductIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Provides an identifier associated with a specific product.  The identifier is unique within the public source specified in the source attribute.
	 */
	FieldWithMetaString getIdentifier();
	/**
	 * Defines the source of the identifier.
	 */
	ProductIdTypeEnum getSource();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ProductIdentifier build();
	
	ProductIdentifier.ProductIdentifierBuilder toBuilder();
	
	static ProductIdentifier.ProductIdentifierBuilder builder() {
		return new ProductIdentifier.ProductIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ProductIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ProductIdentifier> getType() {
		return ProductIdentifier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.class, getIdentifier());
		processor.processBasic(path.newSubPath("source"), ProductIdTypeEnum.class, getSource(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ProductIdentifierBuilder extends ProductIdentifier, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIdentifier();
		FieldWithMetaString.FieldWithMetaStringBuilder getIdentifier();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ProductIdentifier.ProductIdentifierBuilder setIdentifier(FieldWithMetaString identifier0);
		ProductIdentifier.ProductIdentifierBuilder setIdentifierValue(String identifier1);
		ProductIdentifier.ProductIdentifierBuilder setSource(ProductIdTypeEnum source);
		ProductIdentifier.ProductIdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIdentifier());
			processor.processBasic(path.newSubPath("source"), ProductIdTypeEnum.class, getSource(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ProductIdentifier.ProductIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of ProductIdentifier  ***********************/
	class ProductIdentifierImpl implements ProductIdentifier {
		private final FieldWithMetaString identifier;
		private final ProductIdTypeEnum source;
		private final MetaFields meta;
		
		protected ProductIdentifierImpl(ProductIdentifier.ProductIdentifierBuilder builder) {
			this.identifier = ofNullable(builder.getIdentifier()).map(f->f.build()).orElse(null);
			this.source = builder.getSource();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifier")
		public FieldWithMetaString getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("source")
		public ProductIdTypeEnum getSource() {
			return source;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ProductIdentifier build() {
			return this;
		}
		
		@Override
		public ProductIdentifier.ProductIdentifierBuilder toBuilder() {
			ProductIdentifier.ProductIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ProductIdentifier.ProductIdentifierBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getSource()).ifPresent(builder::setSource);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProductIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductIdentifier {" +
				"identifier=" + this.identifier + ", " +
				"source=" + this.source + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ProductIdentifier  ***********************/
	class ProductIdentifierBuilderImpl implements ProductIdentifier.ProductIdentifierBuilder, GlobalKeyBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder identifier;
		protected ProductIdTypeEnum source;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ProductIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifier")
		public FieldWithMetaString.FieldWithMetaStringBuilder getIdentifier() {
			return identifier;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIdentifier() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (identifier!=null) {
				result = identifier;
			}
			else {
				result = identifier = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("source")
		public ProductIdTypeEnum getSource() {
			return source;
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
		@RosettaAttribute("identifier")
		public ProductIdentifier.ProductIdentifierBuilder setIdentifier(FieldWithMetaString identifier) {
			this.identifier = identifier==null?null:identifier.toBuilder();
			return this;
		}
		@Override
		public ProductIdentifier.ProductIdentifierBuilder setIdentifierValue(String identifier) {
			this.getOrCreateIdentifier().setValue(identifier);
			return this;
		}
		@Override
		@RosettaAttribute("source")
		public ProductIdentifier.ProductIdentifierBuilder setSource(ProductIdTypeEnum source) {
			this.source = source==null?null:source;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ProductIdentifier.ProductIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ProductIdentifier build() {
			return new ProductIdentifier.ProductIdentifierImpl(this);
		}
		
		@Override
		public ProductIdentifier.ProductIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductIdentifier.ProductIdentifierBuilder prune() {
			if (identifier!=null && !identifier.prune().hasData()) identifier = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null) return true;
			if (getSource()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductIdentifier.ProductIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ProductIdentifier.ProductIdentifierBuilder o = (ProductIdentifier.ProductIdentifierBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getSource(), o.getSource(), this::setSource);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProductIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductIdentifierBuilder {" +
				"identifier=" + this.identifier + ", " +
				"source=" + this.source + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
