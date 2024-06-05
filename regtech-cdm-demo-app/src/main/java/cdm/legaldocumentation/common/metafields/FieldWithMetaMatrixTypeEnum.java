package cdm.legaldocumentation.common.metafields;

import cdm.legaldocumentation.common.MatrixTypeEnum;
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
@RosettaDataType(value="FieldWithMetaMatrixTypeEnum", builder=FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaMatrixTypeEnum extends RosettaModelObject, FieldWithMeta<MatrixTypeEnum>, GlobalKey {

	FieldWithMetaMatrixTypeEnumMeta metaData = new FieldWithMetaMatrixTypeEnumMeta();

	/*********************** Getter Methods  ***********************/
	MatrixTypeEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaMatrixTypeEnum build();
	
	FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder toBuilder();
	
	static FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder builder() {
		return new FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaMatrixTypeEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaMatrixTypeEnum> getType() {
		return FieldWithMetaMatrixTypeEnum.class;
	}
	
	@Override
	default Class<MatrixTypeEnum> getValueType() {
		return MatrixTypeEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), MatrixTypeEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaMatrixTypeEnumBuilder extends FieldWithMetaMatrixTypeEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<MatrixTypeEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder setValue(MatrixTypeEnum value);
		FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), MatrixTypeEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaMatrixTypeEnum  ***********************/
	class FieldWithMetaMatrixTypeEnumImpl implements FieldWithMetaMatrixTypeEnum {
		private final MatrixTypeEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaMatrixTypeEnumImpl(FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public MatrixTypeEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaMatrixTypeEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder toBuilder() {
			FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaMatrixTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaMatrixTypeEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaMatrixTypeEnum  ***********************/
	class FieldWithMetaMatrixTypeEnumBuilderImpl implements FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder {
	
		protected MatrixTypeEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaMatrixTypeEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public MatrixTypeEnum getValue() {
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
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder setValue(MatrixTypeEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaMatrixTypeEnum build() {
			return new FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder prune() {
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
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder o = (FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaMatrixTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaMatrixTypeEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaMatrixTypeEnumMeta extends BasicRosettaMetaData<FieldWithMetaMatrixTypeEnum>{

}
