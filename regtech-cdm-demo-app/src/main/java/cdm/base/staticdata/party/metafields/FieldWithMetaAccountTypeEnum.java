package cdm.base.staticdata.party.metafields;

import cdm.base.staticdata.party.AccountTypeEnum;
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
@RosettaDataType(value="FieldWithMetaAccountTypeEnum", builder=FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaAccountTypeEnum extends RosettaModelObject, FieldWithMeta<AccountTypeEnum>, GlobalKey {

	FieldWithMetaAccountTypeEnumMeta metaData = new FieldWithMetaAccountTypeEnumMeta();

	/*********************** Getter Methods  ***********************/
	AccountTypeEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaAccountTypeEnum build();
	
	FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder toBuilder();
	
	static FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder builder() {
		return new FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaAccountTypeEnum> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaAccountTypeEnum> getType() {
		return FieldWithMetaAccountTypeEnum.class;
	}
	
	@Override
	default Class<AccountTypeEnum> getValueType() {
		return AccountTypeEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), AccountTypeEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaAccountTypeEnumBuilder extends FieldWithMetaAccountTypeEnum, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<AccountTypeEnum> {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder setValue(AccountTypeEnum value);
		FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), AccountTypeEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaAccountTypeEnum  ***********************/
	class FieldWithMetaAccountTypeEnumImpl implements FieldWithMetaAccountTypeEnum {
		private final AccountTypeEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaAccountTypeEnumImpl(FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public AccountTypeEnum getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaAccountTypeEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder toBuilder() {
			FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaAccountTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaAccountTypeEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaAccountTypeEnum  ***********************/
	class FieldWithMetaAccountTypeEnumBuilderImpl implements FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder {
	
		protected AccountTypeEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaAccountTypeEnumBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public AccountTypeEnum getValue() {
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
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder setValue(AccountTypeEnum value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaAccountTypeEnum build() {
			return new FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder prune() {
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
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder o = (FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaAccountTypeEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaAccountTypeEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaAccountTypeEnumMeta extends BasicRosettaMetaData<FieldWithMetaAccountTypeEnum>{

}
