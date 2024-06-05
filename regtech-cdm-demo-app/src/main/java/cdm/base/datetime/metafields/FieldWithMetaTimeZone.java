package cdm.base.datetime.metafields;

import cdm.base.datetime.TimeZone;
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
@RosettaDataType(value="FieldWithMetaTimeZone", builder=FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaTimeZone extends RosettaModelObject, FieldWithMeta<TimeZone>, GlobalKey {

	FieldWithMetaTimeZoneMeta metaData = new FieldWithMetaTimeZoneMeta();

	/*********************** Getter Methods  ***********************/
	TimeZone getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaTimeZone build();
	
	FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder toBuilder();
	
	static FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder builder() {
		return new FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaTimeZone> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaTimeZone> getType() {
		return FieldWithMetaTimeZone.class;
	}
	
	@Override
	default Class<TimeZone> getValueType() {
		return TimeZone.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, TimeZone.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaTimeZoneBuilder extends FieldWithMetaTimeZone, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<TimeZone> {
		TimeZone.TimeZoneBuilder getOrCreateValue();
		TimeZone.TimeZoneBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder setValue(TimeZone value);
		FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, TimeZone.TimeZoneBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaTimeZone  ***********************/
	class FieldWithMetaTimeZoneImpl implements FieldWithMetaTimeZone {
		private final TimeZone value;
		private final MetaFields meta;
		
		protected FieldWithMetaTimeZoneImpl(FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public TimeZone getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaTimeZone build() {
			return this;
		}
		
		@Override
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder toBuilder() {
			FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaTimeZone _that = getType().cast(o);
		
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
			return "FieldWithMetaTimeZone {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaTimeZone  ***********************/
	class FieldWithMetaTimeZoneBuilderImpl implements FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder {
	
		protected TimeZone.TimeZoneBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaTimeZoneBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public TimeZone.TimeZoneBuilder getValue() {
			return value;
		}
		
		@Override
		public TimeZone.TimeZoneBuilder getOrCreateValue() {
			TimeZone.TimeZoneBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = TimeZone.builder();
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
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder setValue(TimeZone value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaTimeZone build() {
			return new FieldWithMetaTimeZone.FieldWithMetaTimeZoneImpl(this);
		}
		
		@Override
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder prune() {
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
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder o = (FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaTimeZone _that = getType().cast(o);
		
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
			return "FieldWithMetaTimeZoneBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaTimeZoneMeta extends BasicRosettaMetaData<FieldWithMetaTimeZone>{

}
