package cdm.product.common.schedule;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.CalculationPeriodDates.CalculationPeriodDatesBuilder;
import cdm.product.common.schedule.CalculationPeriodDates.CalculationPeriodDatesBuilderImpl;
import cdm.product.common.schedule.CalculationPeriodDates.CalculationPeriodDatesImpl;
import cdm.product.common.schedule.StubPeriodTypeEnum;
import cdm.product.common.schedule.meta.CalculationPeriodDatesMeta;
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
 * A data for:  defining the parameters used to generate the calculation period dates schedule, including the specification of any initial or final stub calculation periods. A calculation period schedule consists of an optional initial stub calculation period, one or more regular calculation periods and an optional final stub calculation period. In the absence of any initial or final stub calculation periods, the regular part of the calculation period schedule is assumed to be between the effective date and the termination date. No implicit stubs are allowed, i.e. stubs must be explicitly specified using an appropriate combination of firstPeriodStartDate, firstRegularPeriodStartDate and lastRegularPeriodEndDate.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationPeriodDates", builder=CalculationPeriodDates.CalculationPeriodDatesBuilderImpl.class, version="${project.version}")
public interface CalculationPeriodDates extends RosettaModelObject, GlobalKey {

	CalculationPeriodDatesMeta metaData = new CalculationPeriodDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The first day of the terms of the trade. This day may be subject to adjustment in accordance with a business day convention.
	 */
	AdjustableOrRelativeDate getEffectiveDate();
	/**
	 * The last day of the terms of the trade. This date may be subject to adjustments in accordance with the business day convention. It can also be specified in relation to another scheduled date (e.g. the last payment date).
	 */
	AdjustableOrRelativeDate getTerminationDate();
	/**
	 * The specification of the business day convention and financial business centers used for adjusting any calculation period date if it would otherwise fall on a day that is not a business day in the specified business center.
	 */
	BusinessDayAdjustments getCalculationPeriodDatesAdjustments();
	/**
	 * The start date of the calculation period. FpML specifies that for interest rate swaps this date must only be specified if it is not equal to the effective date. It is always specified in the case of equity swaps and credit default swaps with periodic payments. This date may be subject to adjustment in accordance with a business day convention.
	 */
	AdjustableOrRelativeDate getFirstPeriodStartDate();
	/**
	 * The start date of the regular part of the calculation period schedule. It must only be specified if there is an initial stub calculation period. This day may be subject to adjustment in accordance with any adjustments specified in calculationPeriodDatesAdjustments.
	 */
	Date getFirstRegularPeriodStartDate();
	/**
	 * The end date of the initial compounding period when compounding is applicable. It must only be specified when the compoundingMethod element is present and not equal to a value of None. This date may be subject to adjustment in accordance with any adjustments specified in calculationPeriodDatesAdjustments.
	 */
	Date getFirstCompoundingPeriodEndDate();
	/**
	 * The end date of the regular part of the calculation period schedule. It must only be specified if there is a final stub calculation period. This day may be subject to adjustment in accordance with any adjustments specified in calculationPeriodDatesAdjustments.
	 */
	Date getLastRegularPeriodEndDate();
	/**
	 * Method to allocate any irregular period remaining after regular periods have been allocated between the effective and termination date.
	 */
	StubPeriodTypeEnum getStubPeriodType();
	/**
	 * The frequency at which calculation period end dates occur with the regular part of the calculation period schedule and their roll date convention.
	 */
	CalculationPeriodFrequency getCalculationPeriodFrequency();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CalculationPeriodDates build();
	
	CalculationPeriodDates.CalculationPeriodDatesBuilder toBuilder();
	
	static CalculationPeriodDates.CalculationPeriodDatesBuilder builder() {
		return new CalculationPeriodDates.CalculationPeriodDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationPeriodDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationPeriodDates> getType() {
		return CalculationPeriodDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("effectiveDate"), processor, AdjustableOrRelativeDate.class, getEffectiveDate());
		processRosetta(path.newSubPath("terminationDate"), processor, AdjustableOrRelativeDate.class, getTerminationDate());
		processRosetta(path.newSubPath("calculationPeriodDatesAdjustments"), processor, BusinessDayAdjustments.class, getCalculationPeriodDatesAdjustments());
		processRosetta(path.newSubPath("firstPeriodStartDate"), processor, AdjustableOrRelativeDate.class, getFirstPeriodStartDate());
		processor.processBasic(path.newSubPath("firstRegularPeriodStartDate"), Date.class, getFirstRegularPeriodStartDate(), this);
		processor.processBasic(path.newSubPath("firstCompoundingPeriodEndDate"), Date.class, getFirstCompoundingPeriodEndDate(), this);
		processor.processBasic(path.newSubPath("lastRegularPeriodEndDate"), Date.class, getLastRegularPeriodEndDate(), this);
		processor.processBasic(path.newSubPath("stubPeriodType"), StubPeriodTypeEnum.class, getStubPeriodType(), this);
		processRosetta(path.newSubPath("calculationPeriodFrequency"), processor, CalculationPeriodFrequency.class, getCalculationPeriodFrequency());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationPeriodDatesBuilder extends CalculationPeriodDates, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEffectiveDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEffectiveDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateTerminationDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getTerminationDate();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateCalculationPeriodDatesAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getCalculationPeriodDatesAdjustments();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFirstPeriodStartDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFirstPeriodStartDate();
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreateCalculationPeriodFrequency();
		CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getCalculationPeriodFrequency();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CalculationPeriodDates.CalculationPeriodDatesBuilder setEffectiveDate(AdjustableOrRelativeDate effectiveDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setTerminationDate(AdjustableOrRelativeDate terminationDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setCalculationPeriodDatesAdjustments(BusinessDayAdjustments calculationPeriodDatesAdjustments);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstPeriodStartDate(AdjustableOrRelativeDate firstPeriodStartDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstRegularPeriodStartDate(Date firstRegularPeriodStartDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstCompoundingPeriodEndDate(Date firstCompoundingPeriodEndDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setLastRegularPeriodEndDate(Date lastRegularPeriodEndDate);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setStubPeriodType(StubPeriodTypeEnum stubPeriodType);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setCalculationPeriodFrequency(CalculationPeriodFrequency calculationPeriodFrequency);
		CalculationPeriodDates.CalculationPeriodDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("effectiveDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getEffectiveDate());
			processRosetta(path.newSubPath("terminationDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getTerminationDate());
			processRosetta(path.newSubPath("calculationPeriodDatesAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getCalculationPeriodDatesAdjustments());
			processRosetta(path.newSubPath("firstPeriodStartDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getFirstPeriodStartDate());
			processor.processBasic(path.newSubPath("firstRegularPeriodStartDate"), Date.class, getFirstRegularPeriodStartDate(), this);
			processor.processBasic(path.newSubPath("firstCompoundingPeriodEndDate"), Date.class, getFirstCompoundingPeriodEndDate(), this);
			processor.processBasic(path.newSubPath("lastRegularPeriodEndDate"), Date.class, getLastRegularPeriodEndDate(), this);
			processor.processBasic(path.newSubPath("stubPeriodType"), StubPeriodTypeEnum.class, getStubPeriodType(), this);
			processRosetta(path.newSubPath("calculationPeriodFrequency"), processor, CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder.class, getCalculationPeriodFrequency());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CalculationPeriodDates.CalculationPeriodDatesBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationPeriodDates  ***********************/
	class CalculationPeriodDatesImpl implements CalculationPeriodDates {
		private final AdjustableOrRelativeDate effectiveDate;
		private final AdjustableOrRelativeDate terminationDate;
		private final BusinessDayAdjustments calculationPeriodDatesAdjustments;
		private final AdjustableOrRelativeDate firstPeriodStartDate;
		private final Date firstRegularPeriodStartDate;
		private final Date firstCompoundingPeriodEndDate;
		private final Date lastRegularPeriodEndDate;
		private final StubPeriodTypeEnum stubPeriodType;
		private final CalculationPeriodFrequency calculationPeriodFrequency;
		private final MetaFields meta;
		
		protected CalculationPeriodDatesImpl(CalculationPeriodDates.CalculationPeriodDatesBuilder builder) {
			this.effectiveDate = ofNullable(builder.getEffectiveDate()).map(f->f.build()).orElse(null);
			this.terminationDate = ofNullable(builder.getTerminationDate()).map(f->f.build()).orElse(null);
			this.calculationPeriodDatesAdjustments = ofNullable(builder.getCalculationPeriodDatesAdjustments()).map(f->f.build()).orElse(null);
			this.firstPeriodStartDate = ofNullable(builder.getFirstPeriodStartDate()).map(f->f.build()).orElse(null);
			this.firstRegularPeriodStartDate = builder.getFirstRegularPeriodStartDate();
			this.firstCompoundingPeriodEndDate = builder.getFirstCompoundingPeriodEndDate();
			this.lastRegularPeriodEndDate = builder.getLastRegularPeriodEndDate();
			this.stubPeriodType = builder.getStubPeriodType();
			this.calculationPeriodFrequency = ofNullable(builder.getCalculationPeriodFrequency()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public AdjustableOrRelativeDate getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("terminationDate")
		public AdjustableOrRelativeDate getTerminationDate() {
			return terminationDate;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDatesAdjustments")
		public BusinessDayAdjustments getCalculationPeriodDatesAdjustments() {
			return calculationPeriodDatesAdjustments;
		}
		
		@Override
		@RosettaAttribute("firstPeriodStartDate")
		public AdjustableOrRelativeDate getFirstPeriodStartDate() {
			return firstPeriodStartDate;
		}
		
		@Override
		@RosettaAttribute("firstRegularPeriodStartDate")
		public Date getFirstRegularPeriodStartDate() {
			return firstRegularPeriodStartDate;
		}
		
		@Override
		@RosettaAttribute("firstCompoundingPeriodEndDate")
		public Date getFirstCompoundingPeriodEndDate() {
			return firstCompoundingPeriodEndDate;
		}
		
		@Override
		@RosettaAttribute("lastRegularPeriodEndDate")
		public Date getLastRegularPeriodEndDate() {
			return lastRegularPeriodEndDate;
		}
		
		@Override
		@RosettaAttribute("stubPeriodType")
		public StubPeriodTypeEnum getStubPeriodType() {
			return stubPeriodType;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodFrequency")
		public CalculationPeriodFrequency getCalculationPeriodFrequency() {
			return calculationPeriodFrequency;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CalculationPeriodDates build() {
			return this;
		}
		
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder toBuilder() {
			CalculationPeriodDates.CalculationPeriodDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationPeriodDates.CalculationPeriodDatesBuilder builder) {
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getTerminationDate()).ifPresent(builder::setTerminationDate);
			ofNullable(getCalculationPeriodDatesAdjustments()).ifPresent(builder::setCalculationPeriodDatesAdjustments);
			ofNullable(getFirstPeriodStartDate()).ifPresent(builder::setFirstPeriodStartDate);
			ofNullable(getFirstRegularPeriodStartDate()).ifPresent(builder::setFirstRegularPeriodStartDate);
			ofNullable(getFirstCompoundingPeriodEndDate()).ifPresent(builder::setFirstCompoundingPeriodEndDate);
			ofNullable(getLastRegularPeriodEndDate()).ifPresent(builder::setLastRegularPeriodEndDate);
			ofNullable(getStubPeriodType()).ifPresent(builder::setStubPeriodType);
			ofNullable(getCalculationPeriodFrequency()).ifPresent(builder::setCalculationPeriodFrequency);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodDates _that = getType().cast(o);
		
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(terminationDate, _that.getTerminationDate())) return false;
			if (!Objects.equals(calculationPeriodDatesAdjustments, _that.getCalculationPeriodDatesAdjustments())) return false;
			if (!Objects.equals(firstPeriodStartDate, _that.getFirstPeriodStartDate())) return false;
			if (!Objects.equals(firstRegularPeriodStartDate, _that.getFirstRegularPeriodStartDate())) return false;
			if (!Objects.equals(firstCompoundingPeriodEndDate, _that.getFirstCompoundingPeriodEndDate())) return false;
			if (!Objects.equals(lastRegularPeriodEndDate, _that.getLastRegularPeriodEndDate())) return false;
			if (!Objects.equals(stubPeriodType, _that.getStubPeriodType())) return false;
			if (!Objects.equals(calculationPeriodFrequency, _that.getCalculationPeriodFrequency())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (terminationDate != null ? terminationDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDatesAdjustments != null ? calculationPeriodDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (firstPeriodStartDate != null ? firstPeriodStartDate.hashCode() : 0);
			_result = 31 * _result + (firstRegularPeriodStartDate != null ? firstRegularPeriodStartDate.hashCode() : 0);
			_result = 31 * _result + (firstCompoundingPeriodEndDate != null ? firstCompoundingPeriodEndDate.hashCode() : 0);
			_result = 31 * _result + (lastRegularPeriodEndDate != null ? lastRegularPeriodEndDate.hashCode() : 0);
			_result = 31 * _result + (stubPeriodType != null ? stubPeriodType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationPeriodFrequency != null ? calculationPeriodFrequency.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodDates {" +
				"effectiveDate=" + this.effectiveDate + ", " +
				"terminationDate=" + this.terminationDate + ", " +
				"calculationPeriodDatesAdjustments=" + this.calculationPeriodDatesAdjustments + ", " +
				"firstPeriodStartDate=" + this.firstPeriodStartDate + ", " +
				"firstRegularPeriodStartDate=" + this.firstRegularPeriodStartDate + ", " +
				"firstCompoundingPeriodEndDate=" + this.firstCompoundingPeriodEndDate + ", " +
				"lastRegularPeriodEndDate=" + this.lastRegularPeriodEndDate + ", " +
				"stubPeriodType=" + this.stubPeriodType + ", " +
				"calculationPeriodFrequency=" + this.calculationPeriodFrequency + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationPeriodDates  ***********************/
	class CalculationPeriodDatesBuilderImpl implements CalculationPeriodDates.CalculationPeriodDatesBuilder, GlobalKeyBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder effectiveDate;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder terminationDate;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder calculationPeriodDatesAdjustments;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder firstPeriodStartDate;
		protected Date firstRegularPeriodStartDate;
		protected Date firstCompoundingPeriodEndDate;
		protected Date lastRegularPeriodEndDate;
		protected StubPeriodTypeEnum stubPeriodType;
		protected CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder calculationPeriodFrequency;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CalculationPeriodDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("effectiveDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEffectiveDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (effectiveDate!=null) {
				result = effectiveDate;
			}
			else {
				result = effectiveDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("terminationDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getTerminationDate() {
			return terminationDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateTerminationDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (terminationDate!=null) {
				result = terminationDate;
			}
			else {
				result = terminationDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationPeriodDatesAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getCalculationPeriodDatesAdjustments() {
			return calculationPeriodDatesAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateCalculationPeriodDatesAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (calculationPeriodDatesAdjustments!=null) {
				result = calculationPeriodDatesAdjustments;
			}
			else {
				result = calculationPeriodDatesAdjustments = BusinessDayAdjustments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("firstPeriodStartDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFirstPeriodStartDate() {
			return firstPeriodStartDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFirstPeriodStartDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (firstPeriodStartDate!=null) {
				result = firstPeriodStartDate;
			}
			else {
				result = firstPeriodStartDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("firstRegularPeriodStartDate")
		public Date getFirstRegularPeriodStartDate() {
			return firstRegularPeriodStartDate;
		}
		
		@Override
		@RosettaAttribute("firstCompoundingPeriodEndDate")
		public Date getFirstCompoundingPeriodEndDate() {
			return firstCompoundingPeriodEndDate;
		}
		
		@Override
		@RosettaAttribute("lastRegularPeriodEndDate")
		public Date getLastRegularPeriodEndDate() {
			return lastRegularPeriodEndDate;
		}
		
		@Override
		@RosettaAttribute("stubPeriodType")
		public StubPeriodTypeEnum getStubPeriodType() {
			return stubPeriodType;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodFrequency")
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getCalculationPeriodFrequency() {
			return calculationPeriodFrequency;
		}
		
		@Override
		public CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder getOrCreateCalculationPeriodFrequency() {
			CalculationPeriodFrequency.CalculationPeriodFrequencyBuilder result;
			if (calculationPeriodFrequency!=null) {
				result = calculationPeriodFrequency;
			}
			else {
				result = calculationPeriodFrequency = CalculationPeriodFrequency.builder();
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
		@RosettaAttribute("effectiveDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setEffectiveDate(AdjustableOrRelativeDate effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("terminationDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setTerminationDate(AdjustableOrRelativeDate terminationDate) {
			this.terminationDate = terminationDate==null?null:terminationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodDatesAdjustments")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setCalculationPeriodDatesAdjustments(BusinessDayAdjustments calculationPeriodDatesAdjustments) {
			this.calculationPeriodDatesAdjustments = calculationPeriodDatesAdjustments==null?null:calculationPeriodDatesAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("firstPeriodStartDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstPeriodStartDate(AdjustableOrRelativeDate firstPeriodStartDate) {
			this.firstPeriodStartDate = firstPeriodStartDate==null?null:firstPeriodStartDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("firstRegularPeriodStartDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstRegularPeriodStartDate(Date firstRegularPeriodStartDate) {
			this.firstRegularPeriodStartDate = firstRegularPeriodStartDate==null?null:firstRegularPeriodStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("firstCompoundingPeriodEndDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setFirstCompoundingPeriodEndDate(Date firstCompoundingPeriodEndDate) {
			this.firstCompoundingPeriodEndDate = firstCompoundingPeriodEndDate==null?null:firstCompoundingPeriodEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("lastRegularPeriodEndDate")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setLastRegularPeriodEndDate(Date lastRegularPeriodEndDate) {
			this.lastRegularPeriodEndDate = lastRegularPeriodEndDate==null?null:lastRegularPeriodEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("stubPeriodType")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setStubPeriodType(StubPeriodTypeEnum stubPeriodType) {
			this.stubPeriodType = stubPeriodType==null?null:stubPeriodType;
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodFrequency")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setCalculationPeriodFrequency(CalculationPeriodFrequency calculationPeriodFrequency) {
			this.calculationPeriodFrequency = calculationPeriodFrequency==null?null:calculationPeriodFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CalculationPeriodDates build() {
			return new CalculationPeriodDates.CalculationPeriodDatesImpl(this);
		}
		
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder prune() {
			if (effectiveDate!=null && !effectiveDate.prune().hasData()) effectiveDate = null;
			if (terminationDate!=null && !terminationDate.prune().hasData()) terminationDate = null;
			if (calculationPeriodDatesAdjustments!=null && !calculationPeriodDatesAdjustments.prune().hasData()) calculationPeriodDatesAdjustments = null;
			if (firstPeriodStartDate!=null && !firstPeriodStartDate.prune().hasData()) firstPeriodStartDate = null;
			if (calculationPeriodFrequency!=null && !calculationPeriodFrequency.prune().hasData()) calculationPeriodFrequency = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEffectiveDate()!=null && getEffectiveDate().hasData()) return true;
			if (getTerminationDate()!=null && getTerminationDate().hasData()) return true;
			if (getCalculationPeriodDatesAdjustments()!=null && getCalculationPeriodDatesAdjustments().hasData()) return true;
			if (getFirstPeriodStartDate()!=null && getFirstPeriodStartDate().hasData()) return true;
			if (getFirstRegularPeriodStartDate()!=null) return true;
			if (getFirstCompoundingPeriodEndDate()!=null) return true;
			if (getLastRegularPeriodEndDate()!=null) return true;
			if (getStubPeriodType()!=null) return true;
			if (getCalculationPeriodFrequency()!=null && getCalculationPeriodFrequency().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationPeriodDates.CalculationPeriodDatesBuilder o = (CalculationPeriodDates.CalculationPeriodDatesBuilder) other;
			
			merger.mergeRosetta(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			merger.mergeRosetta(getTerminationDate(), o.getTerminationDate(), this::setTerminationDate);
			merger.mergeRosetta(getCalculationPeriodDatesAdjustments(), o.getCalculationPeriodDatesAdjustments(), this::setCalculationPeriodDatesAdjustments);
			merger.mergeRosetta(getFirstPeriodStartDate(), o.getFirstPeriodStartDate(), this::setFirstPeriodStartDate);
			merger.mergeRosetta(getCalculationPeriodFrequency(), o.getCalculationPeriodFrequency(), this::setCalculationPeriodFrequency);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getFirstRegularPeriodStartDate(), o.getFirstRegularPeriodStartDate(), this::setFirstRegularPeriodStartDate);
			merger.mergeBasic(getFirstCompoundingPeriodEndDate(), o.getFirstCompoundingPeriodEndDate(), this::setFirstCompoundingPeriodEndDate);
			merger.mergeBasic(getLastRegularPeriodEndDate(), o.getLastRegularPeriodEndDate(), this::setLastRegularPeriodEndDate);
			merger.mergeBasic(getStubPeriodType(), o.getStubPeriodType(), this::setStubPeriodType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationPeriodDates _that = getType().cast(o);
		
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(terminationDate, _that.getTerminationDate())) return false;
			if (!Objects.equals(calculationPeriodDatesAdjustments, _that.getCalculationPeriodDatesAdjustments())) return false;
			if (!Objects.equals(firstPeriodStartDate, _that.getFirstPeriodStartDate())) return false;
			if (!Objects.equals(firstRegularPeriodStartDate, _that.getFirstRegularPeriodStartDate())) return false;
			if (!Objects.equals(firstCompoundingPeriodEndDate, _that.getFirstCompoundingPeriodEndDate())) return false;
			if (!Objects.equals(lastRegularPeriodEndDate, _that.getLastRegularPeriodEndDate())) return false;
			if (!Objects.equals(stubPeriodType, _that.getStubPeriodType())) return false;
			if (!Objects.equals(calculationPeriodFrequency, _that.getCalculationPeriodFrequency())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (terminationDate != null ? terminationDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDatesAdjustments != null ? calculationPeriodDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (firstPeriodStartDate != null ? firstPeriodStartDate.hashCode() : 0);
			_result = 31 * _result + (firstRegularPeriodStartDate != null ? firstRegularPeriodStartDate.hashCode() : 0);
			_result = 31 * _result + (firstCompoundingPeriodEndDate != null ? firstCompoundingPeriodEndDate.hashCode() : 0);
			_result = 31 * _result + (lastRegularPeriodEndDate != null ? lastRegularPeriodEndDate.hashCode() : 0);
			_result = 31 * _result + (stubPeriodType != null ? stubPeriodType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationPeriodFrequency != null ? calculationPeriodFrequency.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodDatesBuilder {" +
				"effectiveDate=" + this.effectiveDate + ", " +
				"terminationDate=" + this.terminationDate + ", " +
				"calculationPeriodDatesAdjustments=" + this.calculationPeriodDatesAdjustments + ", " +
				"firstPeriodStartDate=" + this.firstPeriodStartDate + ", " +
				"firstRegularPeriodStartDate=" + this.firstRegularPeriodStartDate + ", " +
				"firstCompoundingPeriodEndDate=" + this.firstCompoundingPeriodEndDate + ", " +
				"lastRegularPeriodEndDate=" + this.lastRegularPeriodEndDate + ", " +
				"stubPeriodType=" + this.stubPeriodType + ", " +
				"calculationPeriodFrequency=" + this.calculationPeriodFrequency + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
