package cdm.base.staticdata.asset.common.metafields;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.FieldWithMeta;
import com.rosetta.model.lib.meta.FieldWithMeta.FieldWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="FieldWithMetaProductIdentifier", builder=FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaProductIdentifier extends RosettaModelObject, FieldWithMeta<ProductIdentifier>, GlobalKey {

	FieldWithMetaProductIdentifierMeta metaData = new FieldWithMetaProductIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	ProductIdentifier getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaProductIdentifier build();
	
	FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder toBuilder();
	
	static FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder builder() {
		return new FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaProductIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaProductIdentifier> getType() {
		return FieldWithMetaProductIdentifier.class;
	}
	
	@Override
	default Class<ProductIdentifier> getValueType() {
		return ProductIdentifier.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, ProductIdentifier.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaProductIdentifierBuilder extends FieldWithMetaProductIdentifier, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<ProductIdentifier> {
		ProductIdentifier.ProductIdentifierBuilder getOrCreateValue();
		ProductIdentifier.ProductIdentifierBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder setValue(ProductIdentifier value);
		FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaProductIdentifier  ***********************/
	class FieldWithMetaProductIdentifierImpl implements FieldWithMetaProductIdentifier {
		private final ProductIdentifier value;
		private final MetaFields meta;
		
		protected FieldWithMetaProductIdentifierImpl(FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public ProductIdentifier getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaProductIdentifier build() {
			return this;
		}
		
		@Override
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder toBuilder() {
			FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaProductIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaProductIdentifier {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaProductIdentifier  ***********************/
	class FieldWithMetaProductIdentifierBuilderImpl implements FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder {
	
		protected ProductIdentifier.ProductIdentifierBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaProductIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public ProductIdentifier.ProductIdentifierBuilder getValue() {
			return value;
		}
		
		@Override
		public ProductIdentifier.ProductIdentifierBuilder getOrCreateValue() {
			ProductIdentifier.ProductIdentifierBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = ProductIdentifier.builder();
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
		@RosettaAttribute("value")
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder setValue(ProductIdentifier value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaProductIdentifier build() {
			return new FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierImpl(this);
		}
		
		@Override
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder o = (FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaProductIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaProductIdentifierBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaProductIdentifierMeta extends BasicRosettaMetaData<FieldWithMetaProductIdentifier>{

}
