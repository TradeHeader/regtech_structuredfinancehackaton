package cdm.legaldocumentation.common.metafields;

import cdm.legaldocumentation.common.GoverningLawEnum;
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
@RosettaDataType(value="FieldWithMetaGoverningLawEnum", builder=FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaGoverningLawEnum extends RosettaModelObject, FieldWithMeta<GoverningLawEnum>, GlobalKey {

	FieldWithMetaGoverningLawEnumMeta metaData = new FieldWithMetaGoverningLawEnumMeta();

	/*********************** Getter Methods  ***********************/
	GoverningLawEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaGoverningLawEnum build();
	
	FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder toBuilder();
	
	static FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder builder() {
		return new FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaGoverningLawEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaGoverningLawEnum> getType() {
		return FieldWithMetaGoverningLawEnum.class;
	}
	
	@Override
	default Class<GoverningLawEnum> getValueType() {
		return GoverningLawEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), GoverningLawEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaGoverningLawEnumBuilder extends FieldWithMetaGoverningLawEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<GoverningLawEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder setValue(GoverningLawEnum value);
		FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), GoverningLawEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaGoverningLawEnum  ***********************/
	class FieldWithMetaGoverningLawEnumImpl implements FieldWithMetaGoverningLawEnum {
		private final GoverningLawEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaGoverningLawEnumImpl(FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public GoverningLawEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaGoverningLawEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder toBuilder() {
			FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaGoverningLawEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaGoverningLawEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaGoverningLawEnum  ***********************/
	class FieldWithMetaGoverningLawEnumBuilderImpl implements FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder {
	
		protected GoverningLawEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaGoverningLawEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public GoverningLawEnum getValue() {
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
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder setValue(GoverningLawEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaGoverningLawEnum build() {
			return new FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder prune() {
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
		public FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder o = (FieldWithMetaGoverningLawEnum.FieldWithMetaGoverningLawEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaGoverningLawEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaGoverningLawEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaGoverningLawEnumMeta extends BasicRosettaMetaData<FieldWithMetaGoverningLawEnum>{

}
