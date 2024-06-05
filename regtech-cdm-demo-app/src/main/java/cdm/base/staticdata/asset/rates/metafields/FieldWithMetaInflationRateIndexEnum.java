package cdm.base.staticdata.asset.rates.metafields;

import cdm.base.staticdata.asset.rates.InflationRateIndexEnum;
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
@RosettaDataType(value="FieldWithMetaInflationRateIndexEnum", builder=FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaInflationRateIndexEnum extends RosettaModelObject, FieldWithMeta<InflationRateIndexEnum>, GlobalKey {

	FieldWithMetaInflationRateIndexEnumMeta metaData = new FieldWithMetaInflationRateIndexEnumMeta();

	/*********************** Getter Methods  ***********************/
	InflationRateIndexEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaInflationRateIndexEnum build();
	
	FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder toBuilder();
	
	static FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder builder() {
		return new FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaInflationRateIndexEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaInflationRateIndexEnum> getType() {
		return FieldWithMetaInflationRateIndexEnum.class;
	}
	
	@Override
	default Class<InflationRateIndexEnum> getValueType() {
		return InflationRateIndexEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), InflationRateIndexEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaInflationRateIndexEnumBuilder extends FieldWithMetaInflationRateIndexEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<InflationRateIndexEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder setValue(InflationRateIndexEnum value);
		FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), InflationRateIndexEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaInflationRateIndexEnum  ***********************/
	class FieldWithMetaInflationRateIndexEnumImpl implements FieldWithMetaInflationRateIndexEnum {
		private final InflationRateIndexEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaInflationRateIndexEnumImpl(FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public InflationRateIndexEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaInflationRateIndexEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder toBuilder() {
			FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInflationRateIndexEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaInflationRateIndexEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaInflationRateIndexEnum  ***********************/
	class FieldWithMetaInflationRateIndexEnumBuilderImpl implements FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder {
	
		protected InflationRateIndexEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaInflationRateIndexEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public InflationRateIndexEnum getValue() {
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
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder setValue(InflationRateIndexEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaInflationRateIndexEnum build() {
			return new FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder prune() {
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
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder o = (FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInflationRateIndexEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaInflationRateIndexEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaInflationRateIndexEnumMeta extends BasicRosettaMetaData<FieldWithMetaInflationRateIndexEnum>{

}
