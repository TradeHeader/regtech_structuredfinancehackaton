package cdm.observable.event.metafields;

import cdm.observable.event.RestructuringEnum;
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
@RosettaDataType(value="FieldWithMetaRestructuringEnum", builder=FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaRestructuringEnum extends RosettaModelObject, FieldWithMeta<RestructuringEnum>, GlobalKey {

	FieldWithMetaRestructuringEnumMeta metaData = new FieldWithMetaRestructuringEnumMeta();

	/*********************** Getter Methods  ***********************/
	RestructuringEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaRestructuringEnum build();
	
	FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder toBuilder();
	
	static FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder builder() {
		return new FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaRestructuringEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaRestructuringEnum> getType() {
		return FieldWithMetaRestructuringEnum.class;
	}
	
	@Override
	default Class<RestructuringEnum> getValueType() {
		return RestructuringEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), RestructuringEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaRestructuringEnumBuilder extends FieldWithMetaRestructuringEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<RestructuringEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder setValue(RestructuringEnum value);
		FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), RestructuringEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaRestructuringEnum  ***********************/
	class FieldWithMetaRestructuringEnumImpl implements FieldWithMetaRestructuringEnum {
		private final RestructuringEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaRestructuringEnumImpl(FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public RestructuringEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaRestructuringEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder toBuilder() {
			FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaRestructuringEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaRestructuringEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaRestructuringEnum  ***********************/
	class FieldWithMetaRestructuringEnumBuilderImpl implements FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder {
	
		protected RestructuringEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaRestructuringEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public RestructuringEnum getValue() {
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
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder setValue(RestructuringEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaRestructuringEnum build() {
			return new FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder prune() {
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
		public FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder o = (FieldWithMetaRestructuringEnum.FieldWithMetaRestructuringEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaRestructuringEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaRestructuringEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaRestructuringEnumMeta extends BasicRosettaMetaData<FieldWithMetaRestructuringEnum>{

}
