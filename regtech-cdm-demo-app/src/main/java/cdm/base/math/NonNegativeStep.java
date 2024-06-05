package cdm.base.math;

import cdm.base.math.NonNegativeStep;
import cdm.base.math.NonNegativeStep.NonNegativeStepBuilder;
import cdm.base.math.NonNegativeStep.NonNegativeStepBuilderImpl;
import cdm.base.math.NonNegativeStep.NonNegativeStepImpl;
import cdm.base.math.meta.NonNegativeStepMeta;
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
 * A class defining a step date and non-negative step value pair. This step definitions are used to define varying rate or amount schedules, e.g. a notional amortisation or a step-up coupon schedule.
 * @version ${project.version}
 */
@RosettaDataType(value="NonNegativeStep", builder=NonNegativeStep.NonNegativeStepBuilderImpl.class, version="${project.version}")
public interface NonNegativeStep extends RosettaModelObject, GlobalKey {

	NonNegativeStepMeta metaData = new NonNegativeStepMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The date on which the associated stepValue becomes effective. This day may be subject to adjustment in accordance with a business day convention.
	 */
	Date getStepDate();
	/**
	 * The non-negative rate or amount which becomes effective on the associated stepDate. A rate of 5% would be represented as 0.05.
	 */
	BigDecimal getStepValue();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	NonNegativeStep build();
	
	NonNegativeStep.NonNegativeStepBuilder toBuilder();
	
	static NonNegativeStep.NonNegativeStepBuilder builder() {
		return new NonNegativeStep.NonNegativeStepBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NonNegativeStep> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NonNegativeStep> getType() {
		return NonNegativeStep.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("stepDate"), Date.class, getStepDate(), this);
		processor.processBasic(path.newSubPath("stepValue"), BigDecimal.class, getStepValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NonNegativeStepBuilder extends NonNegativeStep, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		NonNegativeStep.NonNegativeStepBuilder setStepDate(Date stepDate);
		NonNegativeStep.NonNegativeStepBuilder setStepValue(BigDecimal stepValue);
		NonNegativeStep.NonNegativeStepBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("stepDate"), Date.class, getStepDate(), this);
			processor.processBasic(path.newSubPath("stepValue"), BigDecimal.class, getStepValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		NonNegativeStep.NonNegativeStepBuilder prune();
	}

	/*********************** Immutable Implementation of NonNegativeStep  ***********************/
	class NonNegativeStepImpl implements NonNegativeStep {
		private final Date stepDate;
		private final BigDecimal stepValue;
		private final MetaFields meta;
		
		protected NonNegativeStepImpl(NonNegativeStep.NonNegativeStepBuilder builder) {
			this.stepDate = builder.getStepDate();
			this.stepValue = builder.getStepValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("stepDate")
		public Date getStepDate() {
			return stepDate;
		}
		
		@Override
		@RosettaAttribute("stepValue")
		public BigDecimal getStepValue() {
			return stepValue;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public NonNegativeStep build() {
			return this;
		}
		
		@Override
		public NonNegativeStep.NonNegativeStepBuilder toBuilder() {
			NonNegativeStep.NonNegativeStepBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NonNegativeStep.NonNegativeStepBuilder builder) {
			ofNullable(getStepDate()).ifPresent(builder::setStepDate);
			ofNullable(getStepValue()).ifPresent(builder::setStepValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NonNegativeStep _that = getType().cast(o);
		
			if (!Objects.equals(stepDate, _that.getStepDate())) return false;
			if (!Objects.equals(stepValue, _that.getStepValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (stepDate != null ? stepDate.hashCode() : 0);
			_result = 31 * _result + (stepValue != null ? stepValue.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NonNegativeStep {" +
				"stepDate=" + this.stepDate + ", " +
				"stepValue=" + this.stepValue + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of NonNegativeStep  ***********************/
	class NonNegativeStepBuilderImpl implements NonNegativeStep.NonNegativeStepBuilder, GlobalKeyBuilder {
	
		protected Date stepDate;
		protected BigDecimal stepValue;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public NonNegativeStepBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("stepDate")
		public Date getStepDate() {
			return stepDate;
		}
		
		@Override
		@RosettaAttribute("stepValue")
		public BigDecimal getStepValue() {
			return stepValue;
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
		@RosettaAttribute("stepDate")
		public NonNegativeStep.NonNegativeStepBuilder setStepDate(Date stepDate) {
			this.stepDate = stepDate==null?null:stepDate;
			return this;
		}
		@Override
		@RosettaAttribute("stepValue")
		public NonNegativeStep.NonNegativeStepBuilder setStepValue(BigDecimal stepValue) {
			this.stepValue = stepValue==null?null:stepValue;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public NonNegativeStep.NonNegativeStepBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public NonNegativeStep build() {
			return new NonNegativeStep.NonNegativeStepImpl(this);
		}
		
		@Override
		public NonNegativeStep.NonNegativeStepBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NonNegativeStep.NonNegativeStepBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStepDate()!=null) return true;
			if (getStepValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NonNegativeStep.NonNegativeStepBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NonNegativeStep.NonNegativeStepBuilder o = (NonNegativeStep.NonNegativeStepBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getStepDate(), o.getStepDate(), this::setStepDate);
			merger.mergeBasic(getStepValue(), o.getStepValue(), this::setStepValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NonNegativeStep _that = getType().cast(o);
		
			if (!Objects.equals(stepDate, _that.getStepDate())) return false;
			if (!Objects.equals(stepValue, _that.getStepValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (stepDate != null ? stepDate.hashCode() : 0);
			_result = 31 * _result + (stepValue != null ? stepValue.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NonNegativeStepBuilder {" +
				"stepDate=" + this.stepDate + ", " +
				"stepValue=" + this.stepValue + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
