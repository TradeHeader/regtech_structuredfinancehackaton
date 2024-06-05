package cdm.product.common.schedule;

import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilderImpl;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseImpl;
import cdm.product.common.schedule.meta.CalculationPeriodBaseMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The calculation period adjusted start and end dates, which are the baseline arguments needed to compute an interest accrual calculation.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationPeriodBase", builder=CalculationPeriodBase.CalculationPeriodBaseBuilderImpl.class, version="${project.version}")
public interface CalculationPeriodBase extends RosettaModelObject, GlobalKey {

	CalculationPeriodBaseMeta metaData = new CalculationPeriodBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The calculation period start date, adjusted according to any relevant business day convention.
	 */
	Date getAdjustedStartDate();
	/**
	 * The calculation period end date, adjusted according to any relevant business day convention.
	 */
	Date getAdjustedEndDate();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CalculationPeriodBase build();
	
	CalculationPeriodBase.CalculationPeriodBaseBuilder toBuilder();
	
	static CalculationPeriodBase.CalculationPeriodBaseBuilder builder() {
		return new CalculationPeriodBase.CalculationPeriodBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationPeriodBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationPeriodBase> getType() {
		return CalculationPeriodBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("adjustedStartDate"), Date.class, getAdjustedStartDate(), this);
		processor.processBasic(path.newSubPath("adjustedEndDate"), Date.class, getAdjustedEndDate(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationPeriodBaseBuilder extends CalculationPeriodBase, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CalculationPeriodBase.CalculationPeriodBaseBuilder setAdjustedStartDate(Date adjustedStartDate);
		CalculationPeriodBase.CalculationPeriodBaseBuilder setAdjustedEndDate(Date adjustedEndDate);
		CalculationPeriodBase.CalculationPeriodBaseBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("adjustedStartDate"), Date.class, getAdjustedStartDate(), this);
			processor.processBasic(path.newSubPath("adjustedEndDate"), Date.class, getAdjustedEndDate(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CalculationPeriodBase.CalculationPeriodBaseBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationPeriodBase  ***********************/
	class CalculationPeriodBaseImpl implements CalculationPeriodBase {
		private final Date adjustedStartDate;
		private final Date adjustedEndDate;
		private final MetaFields meta;
		
		protected CalculationPeriodBaseImpl(CalculationPeriodBase.CalculationPeriodBaseBuilder builder) {
			this.adjustedStartDate = builder.getAdjustedStartDate();
			this.adjustedEndDate = builder.getAdjustedEndDate();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("adjustedStartDate")
		public Date getAdjustedStartDate() {
			return adjustedStartDate;
		}
		
		@Override
		@RosettaAttribute("adjustedEndDate")
		public Date getAdjustedEndDate() {
			return adjustedEndDate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CalculationPeriodBase build() {
			return this;
		}
		
		@Override
		public CalculationPeriodBase.CalculationPeriodBaseBuilder toBuilder() {
			CalculationPeriodBase.CalculationPeriodBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationPeriodBase.CalculationPeriodBaseBuilder builder) {
			ofNullable(getAdjustedStartDate()).ifPresent(builder::setAdjustedStartDate);
			ofNullable(getAdjustedEndDate()).ifPresent(builder::setAdjustedEndDate);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodBase _that = getType().cast(o);
		
			if (!Objects.equals(adjustedStartDate, _that.getAdjustedStartDate())) return false;
			if (!Objects.equals(adjustedEndDate, _that.getAdjustedEndDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustedStartDate != null ? adjustedStartDate.hashCode() : 0);
			_result = 31 * _result + (adjustedEndDate != null ? adjustedEndDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodBase {" +
				"adjustedStartDate=" + this.adjustedStartDate + ", " +
				"adjustedEndDate=" + this.adjustedEndDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationPeriodBase  ***********************/
	class CalculationPeriodBaseBuilderImpl implements CalculationPeriodBase.CalculationPeriodBaseBuilder, GlobalKeyBuilder {
	
		protected Date adjustedStartDate;
		protected Date adjustedEndDate;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CalculationPeriodBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("adjustedStartDate")
		public Date getAdjustedStartDate() {
			return adjustedStartDate;
		}
		
		@Override
		@RosettaAttribute("adjustedEndDate")
		public Date getAdjustedEndDate() {
			return adjustedEndDate;
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
		@RosettaAttribute("adjustedStartDate")
		public CalculationPeriodBase.CalculationPeriodBaseBuilder setAdjustedStartDate(Date adjustedStartDate) {
			this.adjustedStartDate = adjustedStartDate==null?null:adjustedStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedEndDate")
		public CalculationPeriodBase.CalculationPeriodBaseBuilder setAdjustedEndDate(Date adjustedEndDate) {
			this.adjustedEndDate = adjustedEndDate==null?null:adjustedEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CalculationPeriodBase.CalculationPeriodBaseBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CalculationPeriodBase build() {
			return new CalculationPeriodBase.CalculationPeriodBaseImpl(this);
		}
		
		@Override
		public CalculationPeriodBase.CalculationPeriodBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodBase.CalculationPeriodBaseBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdjustedStartDate()!=null) return true;
			if (getAdjustedEndDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodBase.CalculationPeriodBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder o = (CalculationPeriodBase.CalculationPeriodBaseBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getAdjustedStartDate(), o.getAdjustedStartDate(), this::setAdjustedStartDate);
			merger.mergeBasic(getAdjustedEndDate(), o.getAdjustedEndDate(), this::setAdjustedEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodBase _that = getType().cast(o);
		
			if (!Objects.equals(adjustedStartDate, _that.getAdjustedStartDate())) return false;
			if (!Objects.equals(adjustedEndDate, _that.getAdjustedEndDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustedStartDate != null ? adjustedStartDate.hashCode() : 0);
			_result = 31 * _result + (adjustedEndDate != null ? adjustedEndDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodBaseBuilder {" +
				"adjustedStartDate=" + this.adjustedStartDate + ", " +
				"adjustedEndDate=" + this.adjustedEndDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
