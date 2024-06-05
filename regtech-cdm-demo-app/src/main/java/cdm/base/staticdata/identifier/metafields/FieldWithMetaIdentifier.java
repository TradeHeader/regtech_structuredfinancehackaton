package cdm.base.staticdata.identifier.metafields;

import cdm.base.staticdata.identifier.Identifier;
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
@RosettaDataType(value="FieldWithMetaIdentifier", builder=FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaIdentifier extends RosettaModelObject, FieldWithMeta<Identifier>, GlobalKey {

	FieldWithMetaIdentifierMeta metaData = new FieldWithMetaIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	Identifier getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaIdentifier build();
	
	FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder toBuilder();
	
	static FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder builder() {
		return new FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaIdentifier> getType() {
		return FieldWithMetaIdentifier.class;
	}
	
	@Override
	default Class<Identifier> getValueType() {
		return Identifier.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, Identifier.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaIdentifierBuilder extends FieldWithMetaIdentifier, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<Identifier> {
		Identifier.IdentifierBuilder getOrCreateValue();
		Identifier.IdentifierBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder setValue(Identifier value);
		FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, Identifier.IdentifierBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaIdentifier  ***********************/
	class FieldWithMetaIdentifierImpl implements FieldWithMetaIdentifier {
		private final Identifier value;
		private final MetaFields meta;
		
		protected FieldWithMetaIdentifierImpl(FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public Identifier getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaIdentifier build() {
			return this;
		}
		
		@Override
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder toBuilder() {
			FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaIdentifier _that = getType().cast(o);
		
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
			return "FieldWithMetaIdentifier {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaIdentifier  ***********************/
	class FieldWithMetaIdentifierBuilderImpl implements FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder {
	
		protected Identifier.IdentifierBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public Identifier.IdentifierBuilder getValue() {
			return value;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateValue() {
			Identifier.IdentifierBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = Identifier.builder();
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
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder setValue(Identifier value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaIdentifier build() {
			return new FieldWithMetaIdentifier.FieldWithMetaIdentifierImpl(this);
		}
		
		@Override
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder prune() {
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
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder o = (FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaIdentifier _that = getType().cast(o);
		
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
			return "FieldWithMetaIdentifierBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaIdentifierMeta extends BasicRosettaMetaData<FieldWithMetaIdentifier>{

}
