package cdm.base.staticdata.party.metafields;

import cdm.base.staticdata.party.EntityTypeEnum;
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
@RosettaDataType(value="FieldWithMetaEntityTypeEnum", builder=FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaEntityTypeEnum extends RosettaModelObject, FieldWithMeta<EntityTypeEnum>, GlobalKey {

	FieldWithMetaEntityTypeEnumMeta metaData = new FieldWithMetaEntityTypeEnumMeta();

	/*********************** Getter Methods  ***********************/
	EntityTypeEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaEntityTypeEnum build();
	
	FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder toBuilder();
	
	static FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder builder() {
		return new FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaEntityTypeEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaEntityTypeEnum> getType() {
		return FieldWithMetaEntityTypeEnum.class;
	}
	
	@Override
	default Class<EntityTypeEnum> getValueType() {
		return EntityTypeEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), EntityTypeEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaEntityTypeEnumBuilder extends FieldWithMetaEntityTypeEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<EntityTypeEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder setValue(EntityTypeEnum value);
		FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), EntityTypeEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaEntityTypeEnum  ***********************/
	class FieldWithMetaEntityTypeEnumImpl implements FieldWithMetaEntityTypeEnum {
		private final EntityTypeEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaEntityTypeEnumImpl(FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public EntityTypeEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaEntityTypeEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder toBuilder() {
			FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaEntityTypeEnum _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaEntityTypeEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaEntityTypeEnum  ***********************/
	class FieldWithMetaEntityTypeEnumBuilderImpl implements FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder {
	
		protected EntityTypeEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaEntityTypeEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public EntityTypeEnum getValue() {
			return value;
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
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder setValue(EntityTypeEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaEntityTypeEnum build() {
			return new FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder o = (FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaEntityTypeEnum _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaEntityTypeEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaEntityTypeEnumMeta extends BasicRosettaMetaData<FieldWithMetaEntityTypeEnum>{

}
