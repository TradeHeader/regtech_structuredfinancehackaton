package cdm.product.common.schedule;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.Offset;
import cdm.base.datetime.RelativeDateOffset;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.InitialFixingDate;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.ResetDates.ResetDatesBuilder;
import cdm.product.common.schedule.ResetDates.ResetDatesBuilderImpl;
import cdm.product.common.schedule.ResetDates.ResetDatesImpl;
import cdm.product.common.schedule.ResetFrequency;
import cdm.product.common.schedule.ResetRelativeToEnum;
import cdm.product.common.schedule.meta.ResetDatesMeta;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder;
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
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the parameters used to generate the reset dates schedule and associated fixing dates. The reset dates are the dates on which the new index value (which is observed on the fixing date) is applied for each period and on which the interest rate hence begins to accrue.
 * @version ${project.version}
 */
@RosettaDataType(value="ResetDates", builder=ResetDates.ResetDatesBuilderImpl.class, version="${project.version}")
public interface ResetDates extends RosettaModelObject, GlobalKey {

	ResetDatesMeta metaData = new ResetDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A pointer style reference to the associated calculation period dates component defined elsewhere in the document.
	 */
	ReferenceWithMetaCalculationPeriodDates getCalculationPeriodDatesReference();
	/**
	 * Specifies whether the reset dates are determined with respect to each adjusted calculation period start date or adjusted calculation period end date. If the reset frequency is specified as daily this element must not be included.
	 */
	ResetRelativeToEnum getResetRelativeTo();
	/**
	 * The initial fixing date.
	 */
	InitialFixingDate getInitialFixingDate();
	/**
	 * The fixing dates are the dates on which the index values are observed. The fixing dates are specified by reference to the reset date through business days offset and an associated set of financial business centers. Normally these offset calculation rules will be those specified in the ISDA definition for the relevant floating rate index (ISDA&#39;s Floating Rate Option). However, non-standard offset calculation rules may apply for a trade if mutually agreed by the principal parties to the transaction.
	 */
	RelativeDateOffset getFixingDates();
	/**
	 * This attribute is not part of the FpML ResetDate, and has been added as part of the CDM to support the credit derivatives final fixing date.
	 */
	AdjustableDate getFinalFixingDate();
	/**
	 * Specifies the number of business days before the period end date when the rate cut-off date is assumed to apply. The financial business centers associated with determining the rate cut-off date are those specified in the reset dates adjustments. The rate cut-off number of days must be a negative integer (a value of zero would imply no rate cut off applies in which case the rateCutOffDaysOffset element should not be included). The relevant rate for each reset date in the period from, and including, a rate cut-off date to, but excluding, the next applicable period end date (or, in the case of the last calculation period, the termination date) will (solely for purposes of calculating the floating amount payable on the next applicable payment date) be deemed to be the relevant rate in effect on that rate cut-off date. For example, if rate cut-off days for a daily averaging deal is -2 business days, then the refix rate applied on (period end date - 2 days) will also be applied as the reset on (period end date - 1 day), i.e. the actual number of reset dates remains the same but from the rate cut-off date until the period end date, the same refix rate is applied. Note that in the case of several calculation periods contributing to a single payment, the rate cut-off is assumed only to apply to the final calculation period contributing to that payment. The day type associated with the offset must imply a business days offset.
	 */
	Offset getRateCutOffDaysOffset();
	/**
	 * The frequency at which the reset dates occur. In the case of a weekly reset frequency, also specifies the day of the week that the reset occurs. If the reset frequency is greater than the calculation period frequency then this implies that more than one reset is established for each calculation period and some form of rate averaging is applicable.
	 */
	ResetFrequency getResetFrequency();
	/**
	 * The definition of the business day convention and financial business centers used for adjusting the reset date if it would otherwise fall on a day that is not a business day in the specified business center.
	 */
	BusinessDayAdjustments getResetDatesAdjustments();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ResetDates build();
	
	ResetDates.ResetDatesBuilder toBuilder();
	
	static ResetDates.ResetDatesBuilder builder() {
		return new ResetDates.ResetDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ResetDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ResetDates> getType() {
		return ResetDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.class, getCalculationPeriodDatesReference());
		processor.processBasic(path.newSubPath("resetRelativeTo"), ResetRelativeToEnum.class, getResetRelativeTo(), this);
		processRosetta(path.newSubPath("initialFixingDate"), processor, InitialFixingDate.class, getInitialFixingDate());
		processRosetta(path.newSubPath("fixingDates"), processor, RelativeDateOffset.class, getFixingDates());
		processRosetta(path.newSubPath("finalFixingDate"), processor, AdjustableDate.class, getFinalFixingDate());
		processRosetta(path.newSubPath("rateCutOffDaysOffset"), processor, Offset.class, getRateCutOffDaysOffset());
		processRosetta(path.newSubPath("resetFrequency"), processor, ResetFrequency.class, getResetFrequency());
		processRosetta(path.newSubPath("resetDatesAdjustments"), processor, BusinessDayAdjustments.class, getResetDatesAdjustments());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ResetDatesBuilder extends ResetDates, RosettaModelObjectBuilder {
		ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference();
		ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getCalculationPeriodDatesReference();
		InitialFixingDate.InitialFixingDateBuilder getOrCreateInitialFixingDate();
		InitialFixingDate.InitialFixingDateBuilder getInitialFixingDate();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateFixingDates();
		RelativeDateOffset.RelativeDateOffsetBuilder getFixingDates();
		AdjustableDate.AdjustableDateBuilder getOrCreateFinalFixingDate();
		AdjustableDate.AdjustableDateBuilder getFinalFixingDate();
		Offset.OffsetBuilder getOrCreateRateCutOffDaysOffset();
		Offset.OffsetBuilder getRateCutOffDaysOffset();
		ResetFrequency.ResetFrequencyBuilder getOrCreateResetFrequency();
		ResetFrequency.ResetFrequencyBuilder getResetFrequency();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateResetDatesAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getResetDatesAdjustments();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ResetDates.ResetDatesBuilder setCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference0);
		ResetDates.ResetDatesBuilder setCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference1);
		ResetDates.ResetDatesBuilder setResetRelativeTo(ResetRelativeToEnum resetRelativeTo);
		ResetDates.ResetDatesBuilder setInitialFixingDate(InitialFixingDate initialFixingDate);
		ResetDates.ResetDatesBuilder setFixingDates(RelativeDateOffset fixingDates);
		ResetDates.ResetDatesBuilder setFinalFixingDate(AdjustableDate finalFixingDate);
		ResetDates.ResetDatesBuilder setRateCutOffDaysOffset(Offset rateCutOffDaysOffset);
		ResetDates.ResetDatesBuilder setResetFrequency(ResetFrequency resetFrequency);
		ResetDates.ResetDatesBuilder setResetDatesAdjustments(BusinessDayAdjustments resetDatesAdjustments);
		ResetDates.ResetDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder.class, getCalculationPeriodDatesReference());
			processor.processBasic(path.newSubPath("resetRelativeTo"), ResetRelativeToEnum.class, getResetRelativeTo(), this);
			processRosetta(path.newSubPath("initialFixingDate"), processor, InitialFixingDate.InitialFixingDateBuilder.class, getInitialFixingDate());
			processRosetta(path.newSubPath("fixingDates"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getFixingDates());
			processRosetta(path.newSubPath("finalFixingDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getFinalFixingDate());
			processRosetta(path.newSubPath("rateCutOffDaysOffset"), processor, Offset.OffsetBuilder.class, getRateCutOffDaysOffset());
			processRosetta(path.newSubPath("resetFrequency"), processor, ResetFrequency.ResetFrequencyBuilder.class, getResetFrequency());
			processRosetta(path.newSubPath("resetDatesAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getResetDatesAdjustments());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ResetDates.ResetDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ResetDates  ***********************/
	class ResetDatesImpl implements ResetDates {
		private final ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference;
		private final ResetRelativeToEnum resetRelativeTo;
		private final InitialFixingDate initialFixingDate;
		private final RelativeDateOffset fixingDates;
		private final AdjustableDate finalFixingDate;
		private final Offset rateCutOffDaysOffset;
		private final ResetFrequency resetFrequency;
		private final BusinessDayAdjustments resetDatesAdjustments;
		private final MetaFields meta;
		
		protected ResetDatesImpl(ResetDates.ResetDatesBuilder builder) {
			this.calculationPeriodDatesReference = ofNullable(builder.getCalculationPeriodDatesReference()).map(f->f.build()).orElse(null);
			this.resetRelativeTo = builder.getResetRelativeTo();
			this.initialFixingDate = ofNullable(builder.getInitialFixingDate()).map(f->f.build()).orElse(null);
			this.fixingDates = ofNullable(builder.getFixingDates()).map(f->f.build()).orElse(null);
			this.finalFixingDate = ofNullable(builder.getFinalFixingDate()).map(f->f.build()).orElse(null);
			this.rateCutOffDaysOffset = ofNullable(builder.getRateCutOffDaysOffset()).map(f->f.build()).orElse(null);
			this.resetFrequency = ofNullable(builder.getResetFrequency()).map(f->f.build()).orElse(null);
			this.resetDatesAdjustments = ofNullable(builder.getResetDatesAdjustments()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public ReferenceWithMetaCalculationPeriodDates getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		@Override
		@RosettaAttribute("resetRelativeTo")
		public ResetRelativeToEnum getResetRelativeTo() {
			return resetRelativeTo;
		}
		
		@Override
		@RosettaAttribute("initialFixingDate")
		public InitialFixingDate getInitialFixingDate() {
			return initialFixingDate;
		}
		
		@Override
		@RosettaAttribute("fixingDates")
		public RelativeDateOffset getFixingDates() {
			return fixingDates;
		}
		
		@Override
		@RosettaAttribute("finalFixingDate")
		public AdjustableDate getFinalFixingDate() {
			return finalFixingDate;
		}
		
		@Override
		@RosettaAttribute("rateCutOffDaysOffset")
		public Offset getRateCutOffDaysOffset() {
			return rateCutOffDaysOffset;
		}
		
		@Override
		@RosettaAttribute("resetFrequency")
		public ResetFrequency getResetFrequency() {
			return resetFrequency;
		}
		
		@Override
		@RosettaAttribute("resetDatesAdjustments")
		public BusinessDayAdjustments getResetDatesAdjustments() {
			return resetDatesAdjustments;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ResetDates build() {
			return this;
		}
		
		@Override
		public ResetDates.ResetDatesBuilder toBuilder() {
			ResetDates.ResetDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ResetDates.ResetDatesBuilder builder) {
			ofNullable(getCalculationPeriodDatesReference()).ifPresent(builder::setCalculationPeriodDatesReference);
			ofNullable(getResetRelativeTo()).ifPresent(builder::setResetRelativeTo);
			ofNullable(getInitialFixingDate()).ifPresent(builder::setInitialFixingDate);
			ofNullable(getFixingDates()).ifPresent(builder::setFixingDates);
			ofNullable(getFinalFixingDate()).ifPresent(builder::setFinalFixingDate);
			ofNullable(getRateCutOffDaysOffset()).ifPresent(builder::setRateCutOffDaysOffset);
			ofNullable(getResetFrequency()).ifPresent(builder::setResetFrequency);
			ofNullable(getResetDatesAdjustments()).ifPresent(builder::setResetDatesAdjustments);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResetDates _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			if (!Objects.equals(resetRelativeTo, _that.getResetRelativeTo())) return false;
			if (!Objects.equals(initialFixingDate, _that.getInitialFixingDate())) return false;
			if (!Objects.equals(fixingDates, _that.getFixingDates())) return false;
			if (!Objects.equals(finalFixingDate, _that.getFinalFixingDate())) return false;
			if (!Objects.equals(rateCutOffDaysOffset, _that.getRateCutOffDaysOffset())) return false;
			if (!Objects.equals(resetFrequency, _that.getResetFrequency())) return false;
			if (!Objects.equals(resetDatesAdjustments, _that.getResetDatesAdjustments())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			_result = 31 * _result + (resetRelativeTo != null ? resetRelativeTo.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (initialFixingDate != null ? initialFixingDate.hashCode() : 0);
			_result = 31 * _result + (fixingDates != null ? fixingDates.hashCode() : 0);
			_result = 31 * _result + (finalFixingDate != null ? finalFixingDate.hashCode() : 0);
			_result = 31 * _result + (rateCutOffDaysOffset != null ? rateCutOffDaysOffset.hashCode() : 0);
			_result = 31 * _result + (resetFrequency != null ? resetFrequency.hashCode() : 0);
			_result = 31 * _result + (resetDatesAdjustments != null ? resetDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResetDates {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference + ", " +
				"resetRelativeTo=" + this.resetRelativeTo + ", " +
				"initialFixingDate=" + this.initialFixingDate + ", " +
				"fixingDates=" + this.fixingDates + ", " +
				"finalFixingDate=" + this.finalFixingDate + ", " +
				"rateCutOffDaysOffset=" + this.rateCutOffDaysOffset + ", " +
				"resetFrequency=" + this.resetFrequency + ", " +
				"resetDatesAdjustments=" + this.resetDatesAdjustments + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ResetDates  ***********************/
	class ResetDatesBuilderImpl implements ResetDates.ResetDatesBuilder, GlobalKeyBuilder {
	
		protected ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder calculationPeriodDatesReference;
		protected ResetRelativeToEnum resetRelativeTo;
		protected InitialFixingDate.InitialFixingDateBuilder initialFixingDate;
		protected RelativeDateOffset.RelativeDateOffsetBuilder fixingDates;
		protected AdjustableDate.AdjustableDateBuilder finalFixingDate;
		protected Offset.OffsetBuilder rateCutOffDaysOffset;
		protected ResetFrequency.ResetFrequencyBuilder resetFrequency;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder resetDatesAdjustments;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ResetDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		@Override
		public ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference() {
			ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder result;
			if (calculationPeriodDatesReference!=null) {
				result = calculationPeriodDatesReference;
			}
			else {
				result = calculationPeriodDatesReference = ReferenceWithMetaCalculationPeriodDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("resetRelativeTo")
		public ResetRelativeToEnum getResetRelativeTo() {
			return resetRelativeTo;
		}
		
		@Override
		@RosettaAttribute("initialFixingDate")
		public InitialFixingDate.InitialFixingDateBuilder getInitialFixingDate() {
			return initialFixingDate;
		}
		
		@Override
		public InitialFixingDate.InitialFixingDateBuilder getOrCreateInitialFixingDate() {
			InitialFixingDate.InitialFixingDateBuilder result;
			if (initialFixingDate!=null) {
				result = initialFixingDate;
			}
			else {
				result = initialFixingDate = InitialFixingDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixingDates")
		public RelativeDateOffset.RelativeDateOffsetBuilder getFixingDates() {
			return fixingDates;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateFixingDates() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (fixingDates!=null) {
				result = fixingDates;
			}
			else {
				result = fixingDates = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalFixingDate")
		public AdjustableDate.AdjustableDateBuilder getFinalFixingDate() {
			return finalFixingDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreateFinalFixingDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (finalFixingDate!=null) {
				result = finalFixingDate;
			}
			else {
				result = finalFixingDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("rateCutOffDaysOffset")
		public Offset.OffsetBuilder getRateCutOffDaysOffset() {
			return rateCutOffDaysOffset;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateRateCutOffDaysOffset() {
			Offset.OffsetBuilder result;
			if (rateCutOffDaysOffset!=null) {
				result = rateCutOffDaysOffset;
			}
			else {
				result = rateCutOffDaysOffset = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("resetFrequency")
		public ResetFrequency.ResetFrequencyBuilder getResetFrequency() {
			return resetFrequency;
		}
		
		@Override
		public ResetFrequency.ResetFrequencyBuilder getOrCreateResetFrequency() {
			ResetFrequency.ResetFrequencyBuilder result;
			if (resetFrequency!=null) {
				result = resetFrequency;
			}
			else {
				result = resetFrequency = ResetFrequency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("resetDatesAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getResetDatesAdjustments() {
			return resetDatesAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateResetDatesAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (resetDatesAdjustments!=null) {
				result = resetDatesAdjustments;
			}
			else {
				result = resetDatesAdjustments = BusinessDayAdjustments.builder();
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
		@RosettaAttribute("calculationPeriodDatesReference")
		public ResetDates.ResetDatesBuilder setCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference) {
			this.calculationPeriodDatesReference = calculationPeriodDatesReference==null?null:calculationPeriodDatesReference.toBuilder();
			return this;
		}
		@Override
		public ResetDates.ResetDatesBuilder setCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference) {
			this.getOrCreateCalculationPeriodDatesReference().setValue(calculationPeriodDatesReference);
			return this;
		}
		@Override
		@RosettaAttribute("resetRelativeTo")
		public ResetDates.ResetDatesBuilder setResetRelativeTo(ResetRelativeToEnum resetRelativeTo) {
			this.resetRelativeTo = resetRelativeTo==null?null:resetRelativeTo;
			return this;
		}
		@Override
		@RosettaAttribute("initialFixingDate")
		public ResetDates.ResetDatesBuilder setInitialFixingDate(InitialFixingDate initialFixingDate) {
			this.initialFixingDate = initialFixingDate==null?null:initialFixingDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixingDates")
		public ResetDates.ResetDatesBuilder setFixingDates(RelativeDateOffset fixingDates) {
			this.fixingDates = fixingDates==null?null:fixingDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("finalFixingDate")
		public ResetDates.ResetDatesBuilder setFinalFixingDate(AdjustableDate finalFixingDate) {
			this.finalFixingDate = finalFixingDate==null?null:finalFixingDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("rateCutOffDaysOffset")
		public ResetDates.ResetDatesBuilder setRateCutOffDaysOffset(Offset rateCutOffDaysOffset) {
			this.rateCutOffDaysOffset = rateCutOffDaysOffset==null?null:rateCutOffDaysOffset.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("resetFrequency")
		public ResetDates.ResetDatesBuilder setResetFrequency(ResetFrequency resetFrequency) {
			this.resetFrequency = resetFrequency==null?null:resetFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("resetDatesAdjustments")
		public ResetDates.ResetDatesBuilder setResetDatesAdjustments(BusinessDayAdjustments resetDatesAdjustments) {
			this.resetDatesAdjustments = resetDatesAdjustments==null?null:resetDatesAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ResetDates.ResetDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ResetDates build() {
			return new ResetDates.ResetDatesImpl(this);
		}
		
		@Override
		public ResetDates.ResetDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResetDates.ResetDatesBuilder prune() {
			if (calculationPeriodDatesReference!=null && !calculationPeriodDatesReference.prune().hasData()) calculationPeriodDatesReference = null;
			if (initialFixingDate!=null && !initialFixingDate.prune().hasData()) initialFixingDate = null;
			if (fixingDates!=null && !fixingDates.prune().hasData()) fixingDates = null;
			if (finalFixingDate!=null && !finalFixingDate.prune().hasData()) finalFixingDate = null;
			if (rateCutOffDaysOffset!=null && !rateCutOffDaysOffset.prune().hasData()) rateCutOffDaysOffset = null;
			if (resetFrequency!=null && !resetFrequency.prune().hasData()) resetFrequency = null;
			if (resetDatesAdjustments!=null && !resetDatesAdjustments.prune().hasData()) resetDatesAdjustments = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationPeriodDatesReference()!=null && getCalculationPeriodDatesReference().hasData()) return true;
			if (getResetRelativeTo()!=null) return true;
			if (getInitialFixingDate()!=null && getInitialFixingDate().hasData()) return true;
			if (getFixingDates()!=null && getFixingDates().hasData()) return true;
			if (getFinalFixingDate()!=null && getFinalFixingDate().hasData()) return true;
			if (getRateCutOffDaysOffset()!=null && getRateCutOffDaysOffset().hasData()) return true;
			if (getResetFrequency()!=null && getResetFrequency().hasData()) return true;
			if (getResetDatesAdjustments()!=null && getResetDatesAdjustments().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResetDates.ResetDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ResetDates.ResetDatesBuilder o = (ResetDates.ResetDatesBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriodDatesReference(), o.getCalculationPeriodDatesReference(), this::setCalculationPeriodDatesReference);
			merger.mergeRosetta(getInitialFixingDate(), o.getInitialFixingDate(), this::setInitialFixingDate);
			merger.mergeRosetta(getFixingDates(), o.getFixingDates(), this::setFixingDates);
			merger.mergeRosetta(getFinalFixingDate(), o.getFinalFixingDate(), this::setFinalFixingDate);
			merger.mergeRosetta(getRateCutOffDaysOffset(), o.getRateCutOffDaysOffset(), this::setRateCutOffDaysOffset);
			merger.mergeRosetta(getResetFrequency(), o.getResetFrequency(), this::setResetFrequency);
			merger.mergeRosetta(getResetDatesAdjustments(), o.getResetDatesAdjustments(), this::setResetDatesAdjustments);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getResetRelativeTo(), o.getResetRelativeTo(), this::setResetRelativeTo);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResetDates _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			if (!Objects.equals(resetRelativeTo, _that.getResetRelativeTo())) return false;
			if (!Objects.equals(initialFixingDate, _that.getInitialFixingDate())) return false;
			if (!Objects.equals(fixingDates, _that.getFixingDates())) return false;
			if (!Objects.equals(finalFixingDate, _that.getFinalFixingDate())) return false;
			if (!Objects.equals(rateCutOffDaysOffset, _that.getRateCutOffDaysOffset())) return false;
			if (!Objects.equals(resetFrequency, _that.getResetFrequency())) return false;
			if (!Objects.equals(resetDatesAdjustments, _that.getResetDatesAdjustments())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			_result = 31 * _result + (resetRelativeTo != null ? resetRelativeTo.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (initialFixingDate != null ? initialFixingDate.hashCode() : 0);
			_result = 31 * _result + (fixingDates != null ? fixingDates.hashCode() : 0);
			_result = 31 * _result + (finalFixingDate != null ? finalFixingDate.hashCode() : 0);
			_result = 31 * _result + (rateCutOffDaysOffset != null ? rateCutOffDaysOffset.hashCode() : 0);
			_result = 31 * _result + (resetFrequency != null ? resetFrequency.hashCode() : 0);
			_result = 31 * _result + (resetDatesAdjustments != null ? resetDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResetDatesBuilder {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference + ", " +
				"resetRelativeTo=" + this.resetRelativeTo + ", " +
				"initialFixingDate=" + this.initialFixingDate + ", " +
				"fixingDates=" + this.fixingDates + ", " +
				"finalFixingDate=" + this.finalFixingDate + ", " +
				"rateCutOffDaysOffset=" + this.rateCutOffDaysOffset + ", " +
				"resetFrequency=" + this.resetFrequency + ", " +
				"resetDatesAdjustments=" + this.resetDatesAdjustments + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
