package cdm.base.datetime.metafields;

import cdm.base.datetime.BusinessCenterEnum;
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
@RosettaDataType(value="FieldWithMetaBusinessCenterEnum", builder=FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaBusinessCenterEnum extends RosettaModelObject, FieldWithMeta<BusinessCenterEnum>, GlobalKey {

	FieldWithMetaBusinessCenterEnumMeta metaData = new FieldWithMetaBusinessCenterEnumMeta();

	/*********************** Getter Methods  ***********************/
	BusinessCenterEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaBusinessCenterEnum build();
	
	FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder toBuilder();
	
	static FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder builder() {
		return new FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaBusinessCenterEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaBusinessCenterEnum> getType() {
		return FieldWithMetaBusinessCenterEnum.class;
	}
	
	@Override
	default Class<BusinessCenterEnum> getValueType() {
		return BusinessCenterEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BusinessCenterEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaBusinessCenterEnumBuilder extends FieldWithMetaBusinessCenterEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<BusinessCenterEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder setValue(BusinessCenterEnum value);
		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BusinessCenterEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaBusinessCenterEnum  ***********************/
	class FieldWithMetaBusinessCenterEnumImpl implements FieldWithMetaBusinessCenterEnum {
		private final BusinessCenterEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaBusinessCenterEnumImpl(FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public BusinessCenterEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaBusinessCenterEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder toBuilder() {
			FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaBusinessCenterEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaBusinessCenterEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaBusinessCenterEnum  ***********************/
	class FieldWithMetaBusinessCenterEnumBuilderImpl implements FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder {
	
		protected BusinessCenterEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaBusinessCenterEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public BusinessCenterEnum getValue() {
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
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder setValue(BusinessCenterEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaBusinessCenterEnum build() {
			return new FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder prune() {
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
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder o = (FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaBusinessCenterEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaBusinessCenterEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaBusinessCenterEnumMeta extends BasicRosettaMetaData<FieldWithMetaBusinessCenterEnum>{

}
