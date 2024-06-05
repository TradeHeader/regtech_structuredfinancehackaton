package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.Offset;
import cdm.base.datetime.Offset.OffsetBuilder;
import cdm.base.datetime.Offset.OffsetBuilderImpl;
import cdm.base.datetime.Offset.OffsetImpl;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
import cdm.product.common.schedule.DateRelativeToPaymentDates;
import cdm.product.common.schedule.DateRelativeToValuationDates;
import cdm.product.common.settlement.FxFixingDate;
import cdm.product.common.settlement.FxFixingDate.FxFixingDateBuilder;
import cdm.product.common.settlement.FxFixingDate.FxFixingDateBuilderImpl;
import cdm.product.common.settlement.FxFixingDate.FxFixingDateImpl;
import cdm.product.common.settlement.meta.FxFixingDateMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Extends the Offset structure to specify an FX fixing date as an offset to dates specified somewhere else in the document.
 * @version ${project.version}
 */
@RosettaDataType(value="FxFixingDate", builder=FxFixingDate.FxFixingDateBuilderImpl.class, version="${project.version}")
public interface FxFixingDate extends Offset {

	FxFixingDateMeta metaData = new FxFixingDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The convention for adjusting a date if it would otherwise fall on a day that is not a business day, as specified by an ISDA convention (e.g. Following, Precedent).
	 */
	BusinessDayConventionEnum getBusinessDayConvention();
	BusinessCenters getBusinessCenters();
	/**
	 * A reference to a set of financial business centers defined elsewhere in the document. This set of business centers is used to determine whether a particular day is a business day or not.
	 */
	ReferenceWithMetaBusinessCenters getBusinessCentersReference();
	/**
	 * The payment date references on which settlements in non-deliverable currency are due and will then have to be converted according to the terms specified through the other parts of the nonDeliverableSettlement structure.
	 */
	DateRelativeToPaymentDates getDateRelativeToPaymentDates();
	/**
	 * The calculation period references on which settlements in non-deliverable currency are due and will then have to be converted according to the terms specified through the other parts of the nonDeliverableSettlement structure. Implemented for Brazilian-CDI swaps where it will refer to the termination date of the appropriate leg.
	 */
	DateRelativeToCalculationPeriodDates getDateRelativeToCalculationPeriodDates();
	/**
	 * The calculation period references on which settlements in non-deliverable currency are due and will then have to be converted according to the terms specified through the other parts of the nonDeliverableSettlement structure. Implemented for Brazilian-CDI swaps where it will refer to the termination date of the appropriate leg.
	 */
	DateRelativeToValuationDates getDateRelativeToValuationDates();
	/**
	 * Describes the specific date when a non-deliverable forward or cash-settled option will &#39;fix&#39; against a particular rate, which will be used to compute the ultimate cash settlement. This element should be omitted where a single, discrete fixing date cannot be identified e.g. on an american option, where fixing may occur at any date on a continuous range.  This attribute was formerly part of &#39;fxSettlementTerms&#39;, which is now being harmonised into a common &#39;CashSettlementTerms&#39; that includes a &#39;ValuationDate&#39;.
	 */
	AdjustableOrRelativeDate getFxFixingDate();

	/*********************** Build Methods  ***********************/
	FxFixingDate build();
	
	FxFixingDate.FxFixingDateBuilder toBuilder();
	
	static FxFixingDate.FxFixingDateBuilder builder() {
		return new FxFixingDate.FxFixingDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxFixingDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxFixingDate> getType() {
		return FxFixingDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
		processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
		processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
		processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.class, getBusinessCenters());
		processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.class, getBusinessCentersReference());
		processRosetta(path.newSubPath("dateRelativeToPaymentDates"), processor, DateRelativeToPaymentDates.class, getDateRelativeToPaymentDates());
		processRosetta(path.newSubPath("dateRelativeToCalculationPeriodDates"), processor, DateRelativeToCalculationPeriodDates.class, getDateRelativeToCalculationPeriodDates());
		processRosetta(path.newSubPath("dateRelativeToValuationDates"), processor, DateRelativeToValuationDates.class, getDateRelativeToValuationDates());
		processRosetta(path.newSubPath("fxFixingDate"), processor, AdjustableOrRelativeDate.class, getFxFixingDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxFixingDateBuilder extends FxFixingDate, Offset.OffsetBuilder, RosettaModelObjectBuilder {
		BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters();
		BusinessCenters.BusinessCentersBuilder getBusinessCenters();
		ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getOrCreateBusinessCentersReference();
		ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getBusinessCentersReference();
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder getOrCreateDateRelativeToPaymentDates();
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder getDateRelativeToPaymentDates();
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder getOrCreateDateRelativeToCalculationPeriodDates();
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder getDateRelativeToCalculationPeriodDates();
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder getOrCreateDateRelativeToValuationDates();
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder getDateRelativeToValuationDates();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFxFixingDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFxFixingDate();
		FxFixingDate.FxFixingDateBuilder setPeriodMultiplier(Integer periodMultiplier);
		FxFixingDate.FxFixingDateBuilder setPeriod(PeriodEnum period);
		FxFixingDate.FxFixingDateBuilder setMeta(MetaFields meta);
		FxFixingDate.FxFixingDateBuilder setDayType(DayTypeEnum dayType);
		FxFixingDate.FxFixingDateBuilder setBusinessDayConvention(BusinessDayConventionEnum businessDayConvention);
		FxFixingDate.FxFixingDateBuilder setBusinessCenters(BusinessCenters businessCenters);
		FxFixingDate.FxFixingDateBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters businessCentersReference0);
		FxFixingDate.FxFixingDateBuilder setBusinessCentersReferenceValue(BusinessCenters businessCentersReference1);
		FxFixingDate.FxFixingDateBuilder setDateRelativeToPaymentDates(DateRelativeToPaymentDates dateRelativeToPaymentDates);
		FxFixingDate.FxFixingDateBuilder setDateRelativeToCalculationPeriodDates(DateRelativeToCalculationPeriodDates dateRelativeToCalculationPeriodDates);
		FxFixingDate.FxFixingDateBuilder setDateRelativeToValuationDates(DateRelativeToValuationDates dateRelativeToValuationDates);
		FxFixingDate.FxFixingDateBuilder setFxFixingDate(AdjustableOrRelativeDate fxFixingDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
			processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
			processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
			processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.BusinessCentersBuilder.class, getBusinessCenters());
			processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder.class, getBusinessCentersReference());
			processRosetta(path.newSubPath("dateRelativeToPaymentDates"), processor, DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder.class, getDateRelativeToPaymentDates());
			processRosetta(path.newSubPath("dateRelativeToCalculationPeriodDates"), processor, DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder.class, getDateRelativeToCalculationPeriodDates());
			processRosetta(path.newSubPath("dateRelativeToValuationDates"), processor, DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder.class, getDateRelativeToValuationDates());
			processRosetta(path.newSubPath("fxFixingDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getFxFixingDate());
		}
		

		FxFixingDate.FxFixingDateBuilder prune();
	}

	/*********************** Immutable Implementation of FxFixingDate  ***********************/
	class FxFixingDateImpl extends Offset.OffsetImpl implements FxFixingDate {
		private final BusinessDayConventionEnum businessDayConvention;
		private final BusinessCenters businessCenters;
		private final ReferenceWithMetaBusinessCenters businessCentersReference;
		private final DateRelativeToPaymentDates dateRelativeToPaymentDates;
		private final DateRelativeToCalculationPeriodDates dateRelativeToCalculationPeriodDates;
		private final DateRelativeToValuationDates dateRelativeToValuationDates;
		private final AdjustableOrRelativeDate fxFixingDate;
		
		protected FxFixingDateImpl(FxFixingDate.FxFixingDateBuilder builder) {
			super(builder);
			this.businessDayConvention = builder.getBusinessDayConvention();
			this.businessCenters = ofNullable(builder.getBusinessCenters()).map(f->f.build()).orElse(null);
			this.businessCentersReference = ofNullable(builder.getBusinessCentersReference()).map(f->f.build()).orElse(null);
			this.dateRelativeToPaymentDates = ofNullable(builder.getDateRelativeToPaymentDates()).map(f->f.build()).orElse(null);
			this.dateRelativeToCalculationPeriodDates = ofNullable(builder.getDateRelativeToCalculationPeriodDates()).map(f->f.build()).orElse(null);
			this.dateRelativeToValuationDates = ofNullable(builder.getDateRelativeToValuationDates()).map(f->f.build()).orElse(null);
			this.fxFixingDate = ofNullable(builder.getFxFixingDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("businessDayConvention")
		public BusinessDayConventionEnum getBusinessDayConvention() {
			return businessDayConvention;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		@RosettaAttribute("businessCentersReference")
		public ReferenceWithMetaBusinessCenters getBusinessCentersReference() {
			return businessCentersReference;
		}
		
		@Override
		@RosettaAttribute("dateRelativeToPaymentDates")
		public DateRelativeToPaymentDates getDateRelativeToPaymentDates() {
			return dateRelativeToPaymentDates;
		}
		
		@Override
		@RosettaAttribute("dateRelativeToCalculationPeriodDates")
		public DateRelativeToCalculationPeriodDates getDateRelativeToCalculationPeriodDates() {
			return dateRelativeToCalculationPeriodDates;
		}
		
		@Override
		@RosettaAttribute("dateRelativeToValuationDates")
		public DateRelativeToValuationDates getDateRelativeToValuationDates() {
			return dateRelativeToValuationDates;
		}
		
		@Override
		@RosettaAttribute("fxFixingDate")
		public AdjustableOrRelativeDate getFxFixingDate() {
			return fxFixingDate;
		}
		
		@Override
		public FxFixingDate build() {
			return this;
		}
		
		@Override
		public FxFixingDate.FxFixingDateBuilder toBuilder() {
			FxFixingDate.FxFixingDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxFixingDate.FxFixingDateBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getBusinessDayConvention()).ifPresent(builder::setBusinessDayConvention);
			ofNullable(getBusinessCenters()).ifPresent(builder::setBusinessCenters);
			ofNullable(getBusinessCentersReference()).ifPresent(builder::setBusinessCentersReference);
			ofNullable(getDateRelativeToPaymentDates()).ifPresent(builder::setDateRelativeToPaymentDates);
			ofNullable(getDateRelativeToCalculationPeriodDates()).ifPresent(builder::setDateRelativeToCalculationPeriodDates);
			ofNullable(getDateRelativeToValuationDates()).ifPresent(builder::setDateRelativeToValuationDates);
			ofNullable(getFxFixingDate()).ifPresent(builder::setFxFixingDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			FxFixingDate _that = getType().cast(o);
		
			if (!Objects.equals(businessDayConvention, _that.getBusinessDayConvention())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			if (!Objects.equals(businessCentersReference, _that.getBusinessCentersReference())) return false;
			if (!Objects.equals(dateRelativeToPaymentDates, _that.getDateRelativeToPaymentDates())) return false;
			if (!Objects.equals(dateRelativeToCalculationPeriodDates, _that.getDateRelativeToCalculationPeriodDates())) return false;
			if (!Objects.equals(dateRelativeToValuationDates, _that.getDateRelativeToValuationDates())) return false;
			if (!Objects.equals(fxFixingDate, _that.getFxFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (businessDayConvention != null ? businessDayConvention.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			_result = 31 * _result + (businessCentersReference != null ? businessCentersReference.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToPaymentDates != null ? dateRelativeToPaymentDates.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToCalculationPeriodDates != null ? dateRelativeToCalculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToValuationDates != null ? dateRelativeToValuationDates.hashCode() : 0);
			_result = 31 * _result + (fxFixingDate != null ? fxFixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxFixingDate {" +
				"businessDayConvention=" + this.businessDayConvention + ", " +
				"businessCenters=" + this.businessCenters + ", " +
				"businessCentersReference=" + this.businessCentersReference + ", " +
				"dateRelativeToPaymentDates=" + this.dateRelativeToPaymentDates + ", " +
				"dateRelativeToCalculationPeriodDates=" + this.dateRelativeToCalculationPeriodDates + ", " +
				"dateRelativeToValuationDates=" + this.dateRelativeToValuationDates + ", " +
				"fxFixingDate=" + this.fxFixingDate +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of FxFixingDate  ***********************/
	class FxFixingDateBuilderImpl extends Offset.OffsetBuilderImpl  implements FxFixingDate.FxFixingDateBuilder {
	
		protected BusinessDayConventionEnum businessDayConvention;
		protected BusinessCenters.BusinessCentersBuilder businessCenters;
		protected ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder businessCentersReference;
		protected DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder dateRelativeToPaymentDates;
		protected DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder dateRelativeToCalculationPeriodDates;
		protected DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder dateRelativeToValuationDates;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder fxFixingDate;
	
		public FxFixingDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("businessDayConvention")
		public BusinessDayConventionEnum getBusinessDayConvention() {
			return businessDayConvention;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters.BusinessCentersBuilder getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters() {
			BusinessCenters.BusinessCentersBuilder result;
			if (businessCenters!=null) {
				result = businessCenters;
			}
			else {
				result = businessCenters = BusinessCenters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("businessCentersReference")
		public ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getBusinessCentersReference() {
			return businessCentersReference;
		}
		
		@Override
		public ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getOrCreateBusinessCentersReference() {
			ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder result;
			if (businessCentersReference!=null) {
				result = businessCentersReference;
			}
			else {
				result = businessCentersReference = ReferenceWithMetaBusinessCenters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dateRelativeToPaymentDates")
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder getDateRelativeToPaymentDates() {
			return dateRelativeToPaymentDates;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder getOrCreateDateRelativeToPaymentDates() {
			DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder result;
			if (dateRelativeToPaymentDates!=null) {
				result = dateRelativeToPaymentDates;
			}
			else {
				result = dateRelativeToPaymentDates = DateRelativeToPaymentDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dateRelativeToCalculationPeriodDates")
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder getDateRelativeToCalculationPeriodDates() {
			return dateRelativeToCalculationPeriodDates;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder getOrCreateDateRelativeToCalculationPeriodDates() {
			DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder result;
			if (dateRelativeToCalculationPeriodDates!=null) {
				result = dateRelativeToCalculationPeriodDates;
			}
			else {
				result = dateRelativeToCalculationPeriodDates = DateRelativeToCalculationPeriodDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dateRelativeToValuationDates")
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder getDateRelativeToValuationDates() {
			return dateRelativeToValuationDates;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder getOrCreateDateRelativeToValuationDates() {
			DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder result;
			if (dateRelativeToValuationDates!=null) {
				result = dateRelativeToValuationDates;
			}
			else {
				result = dateRelativeToValuationDates = DateRelativeToValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fxFixingDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFxFixingDate() {
			return fxFixingDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFxFixingDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (fxFixingDate!=null) {
				result = fxFixingDate;
			}
			else {
				result = fxFixingDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("periodMultiplier")
		public FxFixingDate.FxFixingDateBuilder setPeriodMultiplier(Integer periodMultiplier) {
			this.periodMultiplier = periodMultiplier==null?null:periodMultiplier;
			return this;
		}
		@Override
		@RosettaAttribute("period")
		public FxFixingDate.FxFixingDateBuilder setPeriod(PeriodEnum period) {
			this.period = period==null?null:period;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FxFixingDate.FxFixingDateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dayType")
		public FxFixingDate.FxFixingDateBuilder setDayType(DayTypeEnum dayType) {
			this.dayType = dayType==null?null:dayType;
			return this;
		}
		@Override
		@RosettaAttribute("businessDayConvention")
		public FxFixingDate.FxFixingDateBuilder setBusinessDayConvention(BusinessDayConventionEnum businessDayConvention) {
			this.businessDayConvention = businessDayConvention==null?null:businessDayConvention;
			return this;
		}
		@Override
		@RosettaAttribute("businessCenters")
		public FxFixingDate.FxFixingDateBuilder setBusinessCenters(BusinessCenters businessCenters) {
			this.businessCenters = businessCenters==null?null:businessCenters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("businessCentersReference")
		public FxFixingDate.FxFixingDateBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters businessCentersReference) {
			this.businessCentersReference = businessCentersReference==null?null:businessCentersReference.toBuilder();
			return this;
		}
		@Override
		public FxFixingDate.FxFixingDateBuilder setBusinessCentersReferenceValue(BusinessCenters businessCentersReference) {
			this.getOrCreateBusinessCentersReference().setValue(businessCentersReference);
			return this;
		}
		@Override
		@RosettaAttribute("dateRelativeToPaymentDates")
		public FxFixingDate.FxFixingDateBuilder setDateRelativeToPaymentDates(DateRelativeToPaymentDates dateRelativeToPaymentDates) {
			this.dateRelativeToPaymentDates = dateRelativeToPaymentDates==null?null:dateRelativeToPaymentDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dateRelativeToCalculationPeriodDates")
		public FxFixingDate.FxFixingDateBuilder setDateRelativeToCalculationPeriodDates(DateRelativeToCalculationPeriodDates dateRelativeToCalculationPeriodDates) {
			this.dateRelativeToCalculationPeriodDates = dateRelativeToCalculationPeriodDates==null?null:dateRelativeToCalculationPeriodDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dateRelativeToValuationDates")
		public FxFixingDate.FxFixingDateBuilder setDateRelativeToValuationDates(DateRelativeToValuationDates dateRelativeToValuationDates) {
			this.dateRelativeToValuationDates = dateRelativeToValuationDates==null?null:dateRelativeToValuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fxFixingDate")
		public FxFixingDate.FxFixingDateBuilder setFxFixingDate(AdjustableOrRelativeDate fxFixingDate) {
			this.fxFixingDate = fxFixingDate==null?null:fxFixingDate.toBuilder();
			return this;
		}
		
		@Override
		public FxFixingDate build() {
			return new FxFixingDate.FxFixingDateImpl(this);
		}
		
		@Override
		public FxFixingDate.FxFixingDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxFixingDate.FxFixingDateBuilder prune() {
			super.prune();
			if (businessCenters!=null && !businessCenters.prune().hasData()) businessCenters = null;
			if (businessCentersReference!=null && !businessCentersReference.prune().hasData()) businessCentersReference = null;
			if (dateRelativeToPaymentDates!=null && !dateRelativeToPaymentDates.prune().hasData()) dateRelativeToPaymentDates = null;
			if (dateRelativeToCalculationPeriodDates!=null && !dateRelativeToCalculationPeriodDates.prune().hasData()) dateRelativeToCalculationPeriodDates = null;
			if (dateRelativeToValuationDates!=null && !dateRelativeToValuationDates.prune().hasData()) dateRelativeToValuationDates = null;
			if (fxFixingDate!=null && !fxFixingDate.prune().hasData()) fxFixingDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getBusinessDayConvention()!=null) return true;
			if (getBusinessCenters()!=null && getBusinessCenters().hasData()) return true;
			if (getBusinessCentersReference()!=null && getBusinessCentersReference().hasData()) return true;
			if (getDateRelativeToPaymentDates()!=null && getDateRelativeToPaymentDates().hasData()) return true;
			if (getDateRelativeToCalculationPeriodDates()!=null && getDateRelativeToCalculationPeriodDates().hasData()) return true;
			if (getDateRelativeToValuationDates()!=null && getDateRelativeToValuationDates().hasData()) return true;
			if (getFxFixingDate()!=null && getFxFixingDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxFixingDate.FxFixingDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			FxFixingDate.FxFixingDateBuilder o = (FxFixingDate.FxFixingDateBuilder) other;
			
			merger.mergeRosetta(getBusinessCenters(), o.getBusinessCenters(), this::setBusinessCenters);
			merger.mergeRosetta(getBusinessCentersReference(), o.getBusinessCentersReference(), this::setBusinessCentersReference);
			merger.mergeRosetta(getDateRelativeToPaymentDates(), o.getDateRelativeToPaymentDates(), this::setDateRelativeToPaymentDates);
			merger.mergeRosetta(getDateRelativeToCalculationPeriodDates(), o.getDateRelativeToCalculationPeriodDates(), this::setDateRelativeToCalculationPeriodDates);
			merger.mergeRosetta(getDateRelativeToValuationDates(), o.getDateRelativeToValuationDates(), this::setDateRelativeToValuationDates);
			merger.mergeRosetta(getFxFixingDate(), o.getFxFixingDate(), this::setFxFixingDate);
			
			merger.mergeBasic(getBusinessDayConvention(), o.getBusinessDayConvention(), this::setBusinessDayConvention);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			FxFixingDate _that = getType().cast(o);
		
			if (!Objects.equals(businessDayConvention, _that.getBusinessDayConvention())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			if (!Objects.equals(businessCentersReference, _that.getBusinessCentersReference())) return false;
			if (!Objects.equals(dateRelativeToPaymentDates, _that.getDateRelativeToPaymentDates())) return false;
			if (!Objects.equals(dateRelativeToCalculationPeriodDates, _that.getDateRelativeToCalculationPeriodDates())) return false;
			if (!Objects.equals(dateRelativeToValuationDates, _that.getDateRelativeToValuationDates())) return false;
			if (!Objects.equals(fxFixingDate, _that.getFxFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (businessDayConvention != null ? businessDayConvention.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			_result = 31 * _result + (businessCentersReference != null ? businessCentersReference.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToPaymentDates != null ? dateRelativeToPaymentDates.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToCalculationPeriodDates != null ? dateRelativeToCalculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (dateRelativeToValuationDates != null ? dateRelativeToValuationDates.hashCode() : 0);
			_result = 31 * _result + (fxFixingDate != null ? fxFixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxFixingDateBuilder {" +
				"businessDayConvention=" + this.businessDayConvention + ", " +
				"businessCenters=" + this.businessCenters + ", " +
				"businessCentersReference=" + this.businessCentersReference + ", " +
				"dateRelativeToPaymentDates=" + this.dateRelativeToPaymentDates + ", " +
				"dateRelativeToCalculationPeriodDates=" + this.dateRelativeToCalculationPeriodDates + ", " +
				"dateRelativeToValuationDates=" + this.dateRelativeToValuationDates + ", " +
				"fxFixingDate=" + this.fxFixingDate +
			'}' + " " + super.toString();
		}
	}
}
