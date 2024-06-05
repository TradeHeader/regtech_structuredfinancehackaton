package cdm.observable.asset.metafields;

import cdm.observable.asset.FloatingRateOption;
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
@RosettaDataType(value="FieldWithMetaFloatingRateOption", builder=FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaFloatingRateOption extends RosettaModelObject, FieldWithMeta<FloatingRateOption>, GlobalKey {

	FieldWithMetaFloatingRateOptionMeta metaData = new FieldWithMetaFloatingRateOptionMeta();

	/*********************** Getter Methods  ***********************/
	FloatingRateOption getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaFloatingRateOption build();
	
	FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder toBuilder();
	
	static FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder builder() {
		return new FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaFloatingRateOption> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaFloatingRateOption> getType() {
		return FieldWithMetaFloatingRateOption.class;
	}
	
	@Override
	default Class<FloatingRateOption> getValueType() {
		return FloatingRateOption.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, FloatingRateOption.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaFloatingRateOptionBuilder extends FieldWithMetaFloatingRateOption, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<FloatingRateOption> {
		FloatingRateOption.FloatingRateOptionBuilder getOrCreateValue();
		FloatingRateOption.FloatingRateOptionBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder setValue(FloatingRateOption value);
		FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, FloatingRateOption.FloatingRateOptionBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaFloatingRateOption  ***********************/
	class FieldWithMetaFloatingRateOptionImpl implements FieldWithMetaFloatingRateOption {
		private final FloatingRateOption value;
		private final MetaFields meta;
		
		protected FieldWithMetaFloatingRateOptionImpl(FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public FloatingRateOption getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaFloatingRateOption build() {
			return this;
		}
		
		@Override
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder toBuilder() {
			FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaFloatingRateOption _that = getType().cast(o);
		
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
			return "FieldWithMetaFloatingRateOption {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaFloatingRateOption  ***********************/
	class FieldWithMetaFloatingRateOptionBuilderImpl implements FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder {
	
		protected FloatingRateOption.FloatingRateOptionBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaFloatingRateOptionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public FloatingRateOption.FloatingRateOptionBuilder getValue() {
			return value;
		}
		
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder getOrCreateValue() {
			FloatingRateOption.FloatingRateOptionBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = FloatingRateOption.builder();
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
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder setValue(FloatingRateOption value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaFloatingRateOption build() {
			return new FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionImpl(this);
		}
		
		@Override
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder prune() {
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
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder o = (FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaFloatingRateOption _that = getType().cast(o);
		
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
			return "FieldWithMetaFloatingRateOptionBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaFloatingRateOptionMeta extends BasicRosettaMetaData<FieldWithMetaFloatingRateOption>{

}
