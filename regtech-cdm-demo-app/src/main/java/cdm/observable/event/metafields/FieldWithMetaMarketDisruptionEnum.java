package cdm.observable.event.metafields;

import cdm.observable.event.MarketDisruptionEnum;
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
@RosettaDataType(value="FieldWithMetaMarketDisruptionEnum", builder=FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaMarketDisruptionEnum extends RosettaModelObject, FieldWithMeta<MarketDisruptionEnum>, GlobalKey {

	FieldWithMetaMarketDisruptionEnumMeta metaData = new FieldWithMetaMarketDisruptionEnumMeta();

	/*********************** Getter Methods  ***********************/
	MarketDisruptionEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaMarketDisruptionEnum build();
	
	FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder toBuilder();
	
	static FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder builder() {
		return new FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaMarketDisruptionEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaMarketDisruptionEnum> getType() {
		return FieldWithMetaMarketDisruptionEnum.class;
	}
	
	@Override
	default Class<MarketDisruptionEnum> getValueType() {
		return MarketDisruptionEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), MarketDisruptionEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaMarketDisruptionEnumBuilder extends FieldWithMetaMarketDisruptionEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<MarketDisruptionEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder setValue(MarketDisruptionEnum value);
		FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), MarketDisruptionEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaMarketDisruptionEnum  ***********************/
	class FieldWithMetaMarketDisruptionEnumImpl implements FieldWithMetaMarketDisruptionEnum {
		private final MarketDisruptionEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaMarketDisruptionEnumImpl(FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public MarketDisruptionEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaMarketDisruptionEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder toBuilder() {
			FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaMarketDisruptionEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaMarketDisruptionEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaMarketDisruptionEnum  ***********************/
	class FieldWithMetaMarketDisruptionEnumBuilderImpl implements FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder {
	
		protected MarketDisruptionEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaMarketDisruptionEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public MarketDisruptionEnum getValue() {
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
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder setValue(MarketDisruptionEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaMarketDisruptionEnum build() {
			return new FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder prune() {
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
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder o = (FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaMarketDisruptionEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaMarketDisruptionEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaMarketDisruptionEnumMeta extends BasicRosettaMetaData<FieldWithMetaMarketDisruptionEnum>{

}
