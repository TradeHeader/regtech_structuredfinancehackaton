package cdm.observable.asset.metafields;

import cdm.observable.asset.PriceSchedule;
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
@RosettaDataType(value="FieldWithMetaPriceSchedule", builder=FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaPriceSchedule extends RosettaModelObject, FieldWithMeta<PriceSchedule>, GlobalKey {

	FieldWithMetaPriceScheduleMeta metaData = new FieldWithMetaPriceScheduleMeta();

	/*********************** Getter Methods  ***********************/
	PriceSchedule getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaPriceSchedule build();
	
	FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder toBuilder();
	
	static FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder builder() {
		return new FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaPriceSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaPriceSchedule> getType() {
		return FieldWithMetaPriceSchedule.class;
	}
	
	@Override
	default Class<PriceSchedule> getValueType() {
		return PriceSchedule.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, PriceSchedule.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaPriceScheduleBuilder extends FieldWithMetaPriceSchedule, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<PriceSchedule> {
		PriceSchedule.PriceScheduleBuilder getOrCreateValue();
		PriceSchedule.PriceScheduleBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder setValue(PriceSchedule value);
		FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, PriceSchedule.PriceScheduleBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaPriceSchedule  ***********************/
	class FieldWithMetaPriceScheduleImpl implements FieldWithMetaPriceSchedule {
		private final PriceSchedule value;
		private final MetaFields meta;
		
		protected FieldWithMetaPriceScheduleImpl(FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public PriceSchedule getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaPriceSchedule build() {
			return this;
		}
		
		@Override
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder toBuilder() {
			FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaPriceSchedule _that = getType().cast(o);
		
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
			return "FieldWithMetaPriceSchedule {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaPriceSchedule  ***********************/
	class FieldWithMetaPriceScheduleBuilderImpl implements FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder {
	
		protected PriceSchedule.PriceScheduleBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaPriceScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public PriceSchedule.PriceScheduleBuilder getValue() {
			return value;
		}
		
		@Override
		public PriceSchedule.PriceScheduleBuilder getOrCreateValue() {
			PriceSchedule.PriceScheduleBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = PriceSchedule.builder();
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
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder setValue(PriceSchedule value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaPriceSchedule build() {
			return new FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleImpl(this);
		}
		
		@Override
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder prune() {
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
		public FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder o = (FieldWithMetaPriceSchedule.FieldWithMetaPriceScheduleBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaPriceSchedule _that = getType().cast(o);
		
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
			return "FieldWithMetaPriceScheduleBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaPriceScheduleMeta extends BasicRosettaMetaData<FieldWithMetaPriceSchedule>{

}
