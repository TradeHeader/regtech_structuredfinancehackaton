package cdm.event.workflow.metafields;

import cdm.event.workflow.LimitLevelEnum;
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
@RosettaDataType(value="FieldWithMetaLimitLevelEnum", builder=FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaLimitLevelEnum extends RosettaModelObject, FieldWithMeta<LimitLevelEnum>, GlobalKey {

	FieldWithMetaLimitLevelEnumMeta metaData = new FieldWithMetaLimitLevelEnumMeta();

	/*********************** Getter Methods  ***********************/
	LimitLevelEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaLimitLevelEnum build();
	
	FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder toBuilder();
	
	static FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder builder() {
		return new FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaLimitLevelEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaLimitLevelEnum> getType() {
		return FieldWithMetaLimitLevelEnum.class;
	}
	
	@Override
	default Class<LimitLevelEnum> getValueType() {
		return LimitLevelEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), LimitLevelEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaLimitLevelEnumBuilder extends FieldWithMetaLimitLevelEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<LimitLevelEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder setValue(LimitLevelEnum value);
		FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), LimitLevelEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaLimitLevelEnum  ***********************/
	class FieldWithMetaLimitLevelEnumImpl implements FieldWithMetaLimitLevelEnum {
		private final LimitLevelEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaLimitLevelEnumImpl(FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public LimitLevelEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaLimitLevelEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder toBuilder() {
			FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaLimitLevelEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaLimitLevelEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaLimitLevelEnum  ***********************/
	class FieldWithMetaLimitLevelEnumBuilderImpl implements FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder {
	
		protected LimitLevelEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaLimitLevelEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public LimitLevelEnum getValue() {
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
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder setValue(LimitLevelEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaLimitLevelEnum build() {
			return new FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder prune() {
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
		public FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder o = (FieldWithMetaLimitLevelEnum.FieldWithMetaLimitLevelEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaLimitLevelEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaLimitLevelEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaLimitLevelEnumMeta extends BasicRosettaMetaData<FieldWithMetaLimitLevelEnum>{

}
