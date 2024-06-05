package cdm.base.datetime.metafields;

import cdm.base.datetime.CommodityBusinessCalendarEnum;
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
@RosettaDataType(value="FieldWithMetaCommodityBusinessCalendarEnum", builder=FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaCommodityBusinessCalendarEnum extends RosettaModelObject, FieldWithMeta<CommodityBusinessCalendarEnum>, GlobalKey {

	FieldWithMetaCommodityBusinessCalendarEnumMeta metaData = new FieldWithMetaCommodityBusinessCalendarEnumMeta();

	/*********************** Getter Methods  ***********************/
	CommodityBusinessCalendarEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaCommodityBusinessCalendarEnum build();
	
	FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder toBuilder();
	
	static FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder builder() {
		return new FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaCommodityBusinessCalendarEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaCommodityBusinessCalendarEnum> getType() {
		return FieldWithMetaCommodityBusinessCalendarEnum.class;
	}
	
	@Override
	default Class<CommodityBusinessCalendarEnum> getValueType() {
		return CommodityBusinessCalendarEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), CommodityBusinessCalendarEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaCommodityBusinessCalendarEnumBuilder extends FieldWithMetaCommodityBusinessCalendarEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<CommodityBusinessCalendarEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder setValue(CommodityBusinessCalendarEnum value);
		FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), CommodityBusinessCalendarEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaCommodityBusinessCalendarEnum  ***********************/
	class FieldWithMetaCommodityBusinessCalendarEnumImpl implements FieldWithMetaCommodityBusinessCalendarEnum {
		private final CommodityBusinessCalendarEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaCommodityBusinessCalendarEnumImpl(FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public CommodityBusinessCalendarEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaCommodityBusinessCalendarEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder toBuilder() {
			FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCommodityBusinessCalendarEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaCommodityBusinessCalendarEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaCommodityBusinessCalendarEnum  ***********************/
	class FieldWithMetaCommodityBusinessCalendarEnumBuilderImpl implements FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder {
	
		protected CommodityBusinessCalendarEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaCommodityBusinessCalendarEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public CommodityBusinessCalendarEnum getValue() {
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
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder setValue(CommodityBusinessCalendarEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaCommodityBusinessCalendarEnum build() {
			return new FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder prune() {
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
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder o = (FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCommodityBusinessCalendarEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaCommodityBusinessCalendarEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaCommodityBusinessCalendarEnumMeta extends BasicRosettaMetaData<FieldWithMetaCommodityBusinessCalendarEnum>{

}
