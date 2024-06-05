package cdm.observable.asset.metafields;

import cdm.observable.asset.CreditNotation;
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
@RosettaDataType(value="FieldWithMetaCreditNotation", builder=FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaCreditNotation extends RosettaModelObject, FieldWithMeta<CreditNotation>, GlobalKey {

	FieldWithMetaCreditNotationMeta metaData = new FieldWithMetaCreditNotationMeta();

	/*********************** Getter Methods  ***********************/
	CreditNotation getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaCreditNotation build();
	
	FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder toBuilder();
	
	static FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder builder() {
		return new FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaCreditNotation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaCreditNotation> getType() {
		return FieldWithMetaCreditNotation.class;
	}
	
	@Override
	default Class<CreditNotation> getValueType() {
		return CreditNotation.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, CreditNotation.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaCreditNotationBuilder extends FieldWithMetaCreditNotation, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<CreditNotation> {
		CreditNotation.CreditNotationBuilder getOrCreateValue();
		CreditNotation.CreditNotationBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder setValue(CreditNotation value);
		FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, CreditNotation.CreditNotationBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaCreditNotation  ***********************/
	class FieldWithMetaCreditNotationImpl implements FieldWithMetaCreditNotation {
		private final CreditNotation value;
		private final MetaFields meta;
		
		protected FieldWithMetaCreditNotationImpl(FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public CreditNotation getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaCreditNotation build() {
			return this;
		}
		
		@Override
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder toBuilder() {
			FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCreditNotation _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaCreditNotation {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaCreditNotation  ***********************/
	class FieldWithMetaCreditNotationBuilderImpl implements FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder {
	
		protected CreditNotation.CreditNotationBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaCreditNotationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public CreditNotation.CreditNotationBuilder getValue() {
			return value;
		}
		
		@Override
		public CreditNotation.CreditNotationBuilder getOrCreateValue() {
			CreditNotation.CreditNotationBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = CreditNotation.builder();
			}
			
			return result;
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
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder setValue(CreditNotation value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaCreditNotation build() {
			return new FieldWithMetaCreditNotation.FieldWithMetaCreditNotationImpl(this);
		}
		
		@Override
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder o = (FieldWithMetaCreditNotation.FieldWithMetaCreditNotationBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCreditNotation _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaCreditNotationBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaCreditNotationMeta extends BasicRosettaMetaData<FieldWithMetaCreditNotation>{

}
