package cdm.legaldocumentation.common.metafields;

import cdm.legaldocumentation.common.ContractualSupplementTypeEnum;
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
@RosettaDataType(value="FieldWithMetaContractualSupplementTypeEnum", builder=FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaContractualSupplementTypeEnum extends RosettaModelObject, FieldWithMeta<ContractualSupplementTypeEnum>, GlobalKey {

	FieldWithMetaContractualSupplementTypeEnumMeta metaData = new FieldWithMetaContractualSupplementTypeEnumMeta();

	/*********************** Getter Methods  ***********************/
	ContractualSupplementTypeEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaContractualSupplementTypeEnum build();
	
	FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder toBuilder();
	
	static FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder builder() {
		return new FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaContractualSupplementTypeEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaContractualSupplementTypeEnum> getType() {
		return FieldWithMetaContractualSupplementTypeEnum.class;
	}
	
	@Override
	default Class<ContractualSupplementTypeEnum> getValueType() {
		return ContractualSupplementTypeEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), ContractualSupplementTypeEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaContractualSupplementTypeEnumBuilder extends FieldWithMetaContractualSupplementTypeEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<ContractualSupplementTypeEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder setValue(ContractualSupplementTypeEnum value);
		FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), ContractualSupplementTypeEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaContractualSupplementTypeEnum  ***********************/
	class FieldWithMetaContractualSupplementTypeEnumImpl implements FieldWithMetaContractualSupplementTypeEnum {
		private final ContractualSupplementTypeEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaContractualSupplementTypeEnumImpl(FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public ContractualSupplementTypeEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaContractualSupplementTypeEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder toBuilder() {
			FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaContractualSupplementTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaContractualSupplementTypeEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaContractualSupplementTypeEnum  ***********************/
	class FieldWithMetaContractualSupplementTypeEnumBuilderImpl implements FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder {
	
		protected ContractualSupplementTypeEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaContractualSupplementTypeEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public ContractualSupplementTypeEnum getValue() {
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
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder setValue(ContractualSupplementTypeEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaContractualSupplementTypeEnum build() {
			return new FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder prune() {
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
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder o = (FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaContractualSupplementTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaContractualSupplementTypeEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaContractualSupplementTypeEnumMeta extends BasicRosettaMetaData<FieldWithMetaContractualSupplementTypeEnum>{

}
