package cdm.base.math;

import cdm.base.math.DatedValue;
import cdm.base.math.DatedValue.DatedValueBuilder;
import cdm.base.math.DatedValue.DatedValueBuilderImpl;
import cdm.base.math.DatedValue.DatedValueImpl;
import cdm.base.math.meta.DatedValueMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines a date and value pair. This definition is used for varying rate or amount schedules, e.g. a notional amortisation or a step-up coupon schedule.
 * @version ${project.version}
 */
@RosettaDataType(value="DatedValue", builder=DatedValue.DatedValueBuilderImpl.class, version="${project.version}")
public interface DatedValue extends RosettaModelObject, GlobalKey {

	DatedValueMeta metaData = new DatedValueMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The date on which the associated step value becomes effective. This day may be subject to adjustment in accordance with a business day convention.
	 */
	Date getDate();
	/**
	 * The rate of amount which becomes effective on the associated step date. A rate of 5% would be represented as 0.05.
	 */
	BigDecimal getValue();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	DatedValue build();
	
	DatedValue.DatedValueBuilder toBuilder();
	
	static DatedValue.DatedValueBuilder builder() {
		return new DatedValue.DatedValueBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DatedValue> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DatedValue> getType() {
		return DatedValue.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DatedValueBuilder extends DatedValue, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		DatedValue.DatedValueBuilder setDate(Date date);
		DatedValue.DatedValueBuilder setValue(BigDecimal value);
		DatedValue.DatedValueBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		DatedValue.DatedValueBuilder prune();
	}

	/*********************** Immutable Implementation of DatedValue  ***********************/
	class DatedValueImpl implements DatedValue {
		private final Date date;
		private final BigDecimal value;
		private final MetaFields meta;
		
		protected DatedValueImpl(DatedValue.DatedValueBuilder builder) {
			this.date = builder.getDate();
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public DatedValue build() {
			return this;
		}
		
		@Override
		public DatedValue.DatedValueBuilder toBuilder() {
			DatedValue.DatedValueBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DatedValue.DatedValueBuilder builder) {
			ofNullable(getDate()).ifPresent(builder::setDate);
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DatedValue _that = getType().cast(o);
		
			if (!Objects.equals(date, _that.getDate())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DatedValue {" +
				"date=" + this.date + ", " +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of DatedValue  ***********************/
	class DatedValueBuilderImpl implements DatedValue.DatedValueBuilder, GlobalKeyBuilder {
	
		protected Date date;
		protected BigDecimal value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public DatedValueBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
		@Override
		@RosettaAttribute("value")
		public BigDecimal getValue() {
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
		@RosettaAttribute("date")
		public DatedValue.DatedValueBuilder setDate(Date date) {
			this.date = date==null?null:date;
			return this;
		}
		@Override
		@RosettaAttribute("value")
		public DatedValue.DatedValueBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public DatedValue.DatedValueBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public DatedValue build() {
			return new DatedValue.DatedValueImpl(this);
		}
		
		@Override
		public DatedValue.DatedValueBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DatedValue.DatedValueBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDate()!=null) return true;
			if (getValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DatedValue.DatedValueBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DatedValue.DatedValueBuilder o = (DatedValue.DatedValueBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getDate(), o.getDate(), this::setDate);
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DatedValue _that = getType().cast(o);
		
			if (!Objects.equals(date, _that.getDate())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DatedValueBuilder {" +
				"date=" + this.date + ", " +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
