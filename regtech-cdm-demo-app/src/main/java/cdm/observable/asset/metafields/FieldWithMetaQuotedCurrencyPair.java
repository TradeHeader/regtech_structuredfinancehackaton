package cdm.observable.asset.metafields;

import cdm.observable.asset.QuotedCurrencyPair;
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
@RosettaDataType(value="FieldWithMetaQuotedCurrencyPair", builder=FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaQuotedCurrencyPair extends RosettaModelObject, FieldWithMeta<QuotedCurrencyPair>, GlobalKey {

	FieldWithMetaQuotedCurrencyPairMeta metaData = new FieldWithMetaQuotedCurrencyPairMeta();

	/*********************** Getter Methods  ***********************/
	QuotedCurrencyPair getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaQuotedCurrencyPair build();
	
	FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder toBuilder();
	
	static FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder builder() {
		return new FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaQuotedCurrencyPair> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaQuotedCurrencyPair> getType() {
		return FieldWithMetaQuotedCurrencyPair.class;
	}
	
	@Override
	default Class<QuotedCurrencyPair> getValueType() {
		return QuotedCurrencyPair.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, QuotedCurrencyPair.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaQuotedCurrencyPairBuilder extends FieldWithMetaQuotedCurrencyPair, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<QuotedCurrencyPair> {
		QuotedCurrencyPair.QuotedCurrencyPairBuilder getOrCreateValue();
		QuotedCurrencyPair.QuotedCurrencyPairBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder setValue(QuotedCurrencyPair value);
		FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, QuotedCurrencyPair.QuotedCurrencyPairBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaQuotedCurrencyPair  ***********************/
	class FieldWithMetaQuotedCurrencyPairImpl implements FieldWithMetaQuotedCurrencyPair {
		private final QuotedCurrencyPair value;
		private final MetaFields meta;
		
		protected FieldWithMetaQuotedCurrencyPairImpl(FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public QuotedCurrencyPair getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaQuotedCurrencyPair build() {
			return this;
		}
		
		@Override
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder toBuilder() {
			FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaQuotedCurrencyPair _that = getType().cast(o);
		
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
			return "FieldWithMetaQuotedCurrencyPair {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaQuotedCurrencyPair  ***********************/
	class FieldWithMetaQuotedCurrencyPairBuilderImpl implements FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder {
	
		protected QuotedCurrencyPair.QuotedCurrencyPairBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaQuotedCurrencyPairBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public QuotedCurrencyPair.QuotedCurrencyPairBuilder getValue() {
			return value;
		}
		
		@Override
		public QuotedCurrencyPair.QuotedCurrencyPairBuilder getOrCreateValue() {
			QuotedCurrencyPair.QuotedCurrencyPairBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = QuotedCurrencyPair.builder();
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
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder setValue(QuotedCurrencyPair value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaQuotedCurrencyPair build() {
			return new FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairImpl(this);
		}
		
		@Override
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder prune() {
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
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder o = (FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaQuotedCurrencyPair _that = getType().cast(o);
		
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
			return "FieldWithMetaQuotedCurrencyPairBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaQuotedCurrencyPairMeta extends BasicRosettaMetaData<FieldWithMetaQuotedCurrencyPair>{

}
