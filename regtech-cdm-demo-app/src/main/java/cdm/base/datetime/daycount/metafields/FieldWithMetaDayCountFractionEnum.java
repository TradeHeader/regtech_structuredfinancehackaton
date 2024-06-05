package cdm.base.datetime.daycount.metafields;

import cdm.base.datetime.daycount.DayCountFractionEnum;
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
@RosettaDataType(value="FieldWithMetaDayCountFractionEnum", builder=FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaDayCountFractionEnum extends RosettaModelObject, FieldWithMeta<DayCountFractionEnum>, GlobalKey {

	FieldWithMetaDayCountFractionEnumMeta metaData = new FieldWithMetaDayCountFractionEnumMeta();

	/*********************** Getter Methods  ***********************/
	DayCountFractionEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaDayCountFractionEnum build();
	
	FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder toBuilder();
	
	static FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder builder() {
		return new FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaDayCountFractionEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaDayCountFractionEnum> getType() {
		return FieldWithMetaDayCountFractionEnum.class;
	}
	
	@Override
	default Class<DayCountFractionEnum> getValueType() {
		return DayCountFractionEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), DayCountFractionEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaDayCountFractionEnumBuilder extends FieldWithMetaDayCountFractionEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<DayCountFractionEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder setValue(DayCountFractionEnum value);
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), DayCountFractionEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaDayCountFractionEnum  ***********************/
	class FieldWithMetaDayCountFractionEnumImpl implements FieldWithMetaDayCountFractionEnum {
		private final DayCountFractionEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaDayCountFractionEnumImpl(FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public DayCountFractionEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder toBuilder() {
			FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaDayCountFractionEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaDayCountFractionEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaDayCountFractionEnum  ***********************/
	class FieldWithMetaDayCountFractionEnumBuilderImpl implements FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder {
	
		protected DayCountFractionEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaDayCountFractionEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public DayCountFractionEnum getValue() {
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
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder setValue(DayCountFractionEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum build() {
			return new FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder prune() {
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
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder o = (FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaDayCountFractionEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaDayCountFractionEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaDayCountFractionEnumMeta extends BasicRosettaMetaData<FieldWithMetaDayCountFractionEnum>{

}
