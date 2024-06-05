package cdm.product.common.schedule;

import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.Frequency;
import cdm.base.datetime.Offset;
import cdm.product.common.schedule.PayRelativeToEnum;
import cdm.product.common.schedule.PaymentDateSchedule;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.schedule.PaymentDates.PaymentDatesBuilder;
import cdm.product.common.schedule.PaymentDates.PaymentDatesBuilderImpl;
import cdm.product.common.schedule.PaymentDates.PaymentDatesImpl;
import cdm.product.common.schedule.meta.PaymentDatesMeta;
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
 * Specifies the parameters to generate the payment date schedule, either through a parametric representation or by reference to specified dates.
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentDates", builder=PaymentDates.PaymentDatesBuilderImpl.class, version="${project.version}")
public interface PaymentDates extends RosettaModelObject, GlobalKey {

	PaymentDatesMeta metaData = new PaymentDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The frequency at which regular payment dates occur. If the payment frequency is equal to the frequency defined in the calculation period dates component then one calculation period contributes to each payment amount. If the payment frequency is less frequent than the frequency defined in the calculation period dates component then more than one calculation period will contribute to the payment amount. A payment frequency more frequent than the calculation period frequency or one that is not a multiple of the calculation period frequency is invalid. If the payment frequency is of value T (term), the period is defined by the effectiveDate and the terminationDate.
	 */
	Frequency getPaymentFrequency();
	/**
	 * The first unadjusted payment date. This day may be subject to adjustment in accordance with any business day convention specified in paymentDatesAdjustments. This element must only be included if there is an initial stub. This date will normally correspond to an unadjusted calculation period start or end date. This is true even if early or delayed payment is specified to be applicable since the actual first payment date will be the specified number of days before or after the applicable adjusted calculation period start or end date with the resulting payment date then being adjusted in accordance with any business day convention specified in paymentDatesAdjustments.
	 */
	Date getFirstPaymentDate();
	/**
	 * The last regular payment date when specified as a date, as in the FpML interest rate construct. FpML specifies that this date may be subject to adjustment in accordance with any business day convention specified in the paymentDatesAdjustments attribute.
	 */
	Date getLastRegularPaymentDate();
	/**
	 * The payment dates when specified as relative to a set of dates specified somewhere else in the instance document/transaction, e.g. the valuation dates as typically the case for equity swaps, or when specified as a calculation period schedule.
	 */
	PaymentDateSchedule getPaymentDateSchedule();
	/**
	 * Specifies whether the payments occur relative to each adjusted calculation period start date or end date, each reset date, valuation date or the last pricing date. Calculation period start date means relative to the start of the first calculation period contributing to a given payment. Similarly, calculation period end date means the end of the last calculation period contributing to a given payment. The valuation date is applicable for Brazilian-CDI and equity swaps.
	 */
	PayRelativeToEnum getPayRelativeTo();
	/**
	 * If early payment or delayed payment is required, specifies the number of days offset that the payment occurs relative to what would otherwise be the unadjusted payment date. The offset can be specified in terms of either calendar or business days. Even in the case of a calendar days offset, the resulting payment date, adjusted for the specified calendar days offset, will still be adjusted in accordance with the specified payment dates adjustments. This element should only be included if early or delayed payment is applicable, i.e. if the periodMultiplier element value is not equal to zero. An early payment would be indicated by a negative periodMultiplier element value and a delayed payment (or payment lag) would be indicated by a positive periodMultiplier element value.
	 */
	Offset getPaymentDaysOffset();
	/**
	 * The definition of the business day convention and financial business centers used for adjusting the payment date if it would otherwise fall on a day that is not a business day in the specified business center.
	 */
	BusinessDayAdjustments getPaymentDatesAdjustments();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PaymentDates build();
	
	PaymentDates.PaymentDatesBuilder toBuilder();
	
	static PaymentDates.PaymentDatesBuilder builder() {
		return new PaymentDates.PaymentDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentDates> getType() {
		return PaymentDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("paymentFrequency"), processor, Frequency.class, getPaymentFrequency());
		processor.processBasic(path.newSubPath("firstPaymentDate"), Date.class, getFirstPaymentDate(), this);
		processor.processBasic(path.newSubPath("lastRegularPaymentDate"), Date.class, getLastRegularPaymentDate(), this);
		processRosetta(path.newSubPath("paymentDateSchedule"), processor, PaymentDateSchedule.class, getPaymentDateSchedule());
		processor.processBasic(path.newSubPath("payRelativeTo"), PayRelativeToEnum.class, getPayRelativeTo(), this);
		processRosetta(path.newSubPath("paymentDaysOffset"), processor, Offset.class, getPaymentDaysOffset());
		processRosetta(path.newSubPath("paymentDatesAdjustments"), processor, BusinessDayAdjustments.class, getPaymentDatesAdjustments());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentDatesBuilder extends PaymentDates, RosettaModelObjectBuilder {
		Frequency.FrequencyBuilder getOrCreatePaymentFrequency();
		Frequency.FrequencyBuilder getPaymentFrequency();
		PaymentDateSchedule.PaymentDateScheduleBuilder getOrCreatePaymentDateSchedule();
		PaymentDateSchedule.PaymentDateScheduleBuilder getPaymentDateSchedule();
		Offset.OffsetBuilder getOrCreatePaymentDaysOffset();
		Offset.OffsetBuilder getPaymentDaysOffset();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreatePaymentDatesAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getPaymentDatesAdjustments();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PaymentDates.PaymentDatesBuilder setPaymentFrequency(Frequency paymentFrequency);
		PaymentDates.PaymentDatesBuilder setFirstPaymentDate(Date firstPaymentDate);
		PaymentDates.PaymentDatesBuilder setLastRegularPaymentDate(Date lastRegularPaymentDate);
		PaymentDates.PaymentDatesBuilder setPaymentDateSchedule(PaymentDateSchedule paymentDateSchedule);
		PaymentDates.PaymentDatesBuilder setPayRelativeTo(PayRelativeToEnum payRelativeTo);
		PaymentDates.PaymentDatesBuilder setPaymentDaysOffset(Offset paymentDaysOffset);
		PaymentDates.PaymentDatesBuilder setPaymentDatesAdjustments(BusinessDayAdjustments paymentDatesAdjustments);
		PaymentDates.PaymentDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("paymentFrequency"), processor, Frequency.FrequencyBuilder.class, getPaymentFrequency());
			processor.processBasic(path.newSubPath("firstPaymentDate"), Date.class, getFirstPaymentDate(), this);
			processor.processBasic(path.newSubPath("lastRegularPaymentDate"), Date.class, getLastRegularPaymentDate(), this);
			processRosetta(path.newSubPath("paymentDateSchedule"), processor, PaymentDateSchedule.PaymentDateScheduleBuilder.class, getPaymentDateSchedule());
			processor.processBasic(path.newSubPath("payRelativeTo"), PayRelativeToEnum.class, getPayRelativeTo(), this);
			processRosetta(path.newSubPath("paymentDaysOffset"), processor, Offset.OffsetBuilder.class, getPaymentDaysOffset());
			processRosetta(path.newSubPath("paymentDatesAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getPaymentDatesAdjustments());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PaymentDates.PaymentDatesBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentDates  ***********************/
	class PaymentDatesImpl implements PaymentDates {
		private final Frequency paymentFrequency;
		private final Date firstPaymentDate;
		private final Date lastRegularPaymentDate;
		private final PaymentDateSchedule paymentDateSchedule;
		private final PayRelativeToEnum payRelativeTo;
		private final Offset paymentDaysOffset;
		private final BusinessDayAdjustments paymentDatesAdjustments;
		private final MetaFields meta;
		
		protected PaymentDatesImpl(PaymentDates.PaymentDatesBuilder builder) {
			this.paymentFrequency = ofNullable(builder.getPaymentFrequency()).map(f->f.build()).orElse(null);
			this.firstPaymentDate = builder.getFirstPaymentDate();
			this.lastRegularPaymentDate = builder.getLastRegularPaymentDate();
			this.paymentDateSchedule = ofNullable(builder.getPaymentDateSchedule()).map(f->f.build()).orElse(null);
			this.payRelativeTo = builder.getPayRelativeTo();
			this.paymentDaysOffset = ofNullable(builder.getPaymentDaysOffset()).map(f->f.build()).orElse(null);
			this.paymentDatesAdjustments = ofNullable(builder.getPaymentDatesAdjustments()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("paymentFrequency")
		public Frequency getPaymentFrequency() {
			return paymentFrequency;
		}
		
		@Override
		@RosettaAttribute("firstPaymentDate")
		public Date getFirstPaymentDate() {
			return firstPaymentDate;
		}
		
		@Override
		@RosettaAttribute("lastRegularPaymentDate")
		public Date getLastRegularPaymentDate() {
			return lastRegularPaymentDate;
		}
		
		@Override
		@RosettaAttribute("paymentDateSchedule")
		public PaymentDateSchedule getPaymentDateSchedule() {
			return paymentDateSchedule;
		}
		
		@Override
		@RosettaAttribute("payRelativeTo")
		public PayRelativeToEnum getPayRelativeTo() {
			return payRelativeTo;
		}
		
		@Override
		@RosettaAttribute("paymentDaysOffset")
		public Offset getPaymentDaysOffset() {
			return paymentDaysOffset;
		}
		
		@Override
		@RosettaAttribute("paymentDatesAdjustments")
		public BusinessDayAdjustments getPaymentDatesAdjustments() {
			return paymentDatesAdjustments;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PaymentDates build() {
			return this;
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder toBuilder() {
			PaymentDates.PaymentDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentDates.PaymentDatesBuilder builder) {
			ofNullable(getPaymentFrequency()).ifPresent(builder::setPaymentFrequency);
			ofNullable(getFirstPaymentDate()).ifPresent(builder::setFirstPaymentDate);
			ofNullable(getLastRegularPaymentDate()).ifPresent(builder::setLastRegularPaymentDate);
			ofNullable(getPaymentDateSchedule()).ifPresent(builder::setPaymentDateSchedule);
			ofNullable(getPayRelativeTo()).ifPresent(builder::setPayRelativeTo);
			ofNullable(getPaymentDaysOffset()).ifPresent(builder::setPaymentDaysOffset);
			ofNullable(getPaymentDatesAdjustments()).ifPresent(builder::setPaymentDatesAdjustments);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDates _that = getType().cast(o);
		
			if (!Objects.equals(paymentFrequency, _that.getPaymentFrequency())) return false;
			if (!Objects.equals(firstPaymentDate, _that.getFirstPaymentDate())) return false;
			if (!Objects.equals(lastRegularPaymentDate, _that.getLastRegularPaymentDate())) return false;
			if (!Objects.equals(paymentDateSchedule, _that.getPaymentDateSchedule())) return false;
			if (!Objects.equals(payRelativeTo, _that.getPayRelativeTo())) return false;
			if (!Objects.equals(paymentDaysOffset, _that.getPaymentDaysOffset())) return false;
			if (!Objects.equals(paymentDatesAdjustments, _that.getPaymentDatesAdjustments())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentFrequency != null ? paymentFrequency.hashCode() : 0);
			_result = 31 * _result + (firstPaymentDate != null ? firstPaymentDate.hashCode() : 0);
			_result = 31 * _result + (lastRegularPaymentDate != null ? lastRegularPaymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentDateSchedule != null ? paymentDateSchedule.hashCode() : 0);
			_result = 31 * _result + (payRelativeTo != null ? payRelativeTo.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentDaysOffset != null ? paymentDaysOffset.hashCode() : 0);
			_result = 31 * _result + (paymentDatesAdjustments != null ? paymentDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDates {" +
				"paymentFrequency=" + this.paymentFrequency + ", " +
				"firstPaymentDate=" + this.firstPaymentDate + ", " +
				"lastRegularPaymentDate=" + this.lastRegularPaymentDate + ", " +
				"paymentDateSchedule=" + this.paymentDateSchedule + ", " +
				"payRelativeTo=" + this.payRelativeTo + ", " +
				"paymentDaysOffset=" + this.paymentDaysOffset + ", " +
				"paymentDatesAdjustments=" + this.paymentDatesAdjustments + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentDates  ***********************/
	class PaymentDatesBuilderImpl implements PaymentDates.PaymentDatesBuilder, GlobalKeyBuilder {
	
		protected Frequency.FrequencyBuilder paymentFrequency;
		protected Date firstPaymentDate;
		protected Date lastRegularPaymentDate;
		protected PaymentDateSchedule.PaymentDateScheduleBuilder paymentDateSchedule;
		protected PayRelativeToEnum payRelativeTo;
		protected Offset.OffsetBuilder paymentDaysOffset;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder paymentDatesAdjustments;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PaymentDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("paymentFrequency")
		public Frequency.FrequencyBuilder getPaymentFrequency() {
			return paymentFrequency;
		}
		
		@Override
		public Frequency.FrequencyBuilder getOrCreatePaymentFrequency() {
			Frequency.FrequencyBuilder result;
			if (paymentFrequency!=null) {
				result = paymentFrequency;
			}
			else {
				result = paymentFrequency = Frequency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("firstPaymentDate")
		public Date getFirstPaymentDate() {
			return firstPaymentDate;
		}
		
		@Override
		@RosettaAttribute("lastRegularPaymentDate")
		public Date getLastRegularPaymentDate() {
			return lastRegularPaymentDate;
		}
		
		@Override
		@RosettaAttribute("paymentDateSchedule")
		public PaymentDateSchedule.PaymentDateScheduleBuilder getPaymentDateSchedule() {
			return paymentDateSchedule;
		}
		
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder getOrCreatePaymentDateSchedule() {
			PaymentDateSchedule.PaymentDateScheduleBuilder result;
			if (paymentDateSchedule!=null) {
				result = paymentDateSchedule;
			}
			else {
				result = paymentDateSchedule = PaymentDateSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("payRelativeTo")
		public PayRelativeToEnum getPayRelativeTo() {
			return payRelativeTo;
		}
		
		@Override
		@RosettaAttribute("paymentDaysOffset")
		public Offset.OffsetBuilder getPaymentDaysOffset() {
			return paymentDaysOffset;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreatePaymentDaysOffset() {
			Offset.OffsetBuilder result;
			if (paymentDaysOffset!=null) {
				result = paymentDaysOffset;
			}
			else {
				result = paymentDaysOffset = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDatesAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getPaymentDatesAdjustments() {
			return paymentDatesAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreatePaymentDatesAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (paymentDatesAdjustments!=null) {
				result = paymentDatesAdjustments;
			}
			else {
				result = paymentDatesAdjustments = BusinessDayAdjustments.builder();
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
		@RosettaAttribute("paymentFrequency")
		public PaymentDates.PaymentDatesBuilder setPaymentFrequency(Frequency paymentFrequency) {
			this.paymentFrequency = paymentFrequency==null?null:paymentFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("firstPaymentDate")
		public PaymentDates.PaymentDatesBuilder setFirstPaymentDate(Date firstPaymentDate) {
			this.firstPaymentDate = firstPaymentDate==null?null:firstPaymentDate;
			return this;
		}
		@Override
		@RosettaAttribute("lastRegularPaymentDate")
		public PaymentDates.PaymentDatesBuilder setLastRegularPaymentDate(Date lastRegularPaymentDate) {
			this.lastRegularPaymentDate = lastRegularPaymentDate==null?null:lastRegularPaymentDate;
			return this;
		}
		@Override
		@RosettaAttribute("paymentDateSchedule")
		public PaymentDates.PaymentDatesBuilder setPaymentDateSchedule(PaymentDateSchedule paymentDateSchedule) {
			this.paymentDateSchedule = paymentDateSchedule==null?null:paymentDateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payRelativeTo")
		public PaymentDates.PaymentDatesBuilder setPayRelativeTo(PayRelativeToEnum payRelativeTo) {
			this.payRelativeTo = payRelativeTo==null?null:payRelativeTo;
			return this;
		}
		@Override
		@RosettaAttribute("paymentDaysOffset")
		public PaymentDates.PaymentDatesBuilder setPaymentDaysOffset(Offset paymentDaysOffset) {
			this.paymentDaysOffset = paymentDaysOffset==null?null:paymentDaysOffset.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDatesAdjustments")
		public PaymentDates.PaymentDatesBuilder setPaymentDatesAdjustments(BusinessDayAdjustments paymentDatesAdjustments) {
			this.paymentDatesAdjustments = paymentDatesAdjustments==null?null:paymentDatesAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PaymentDates.PaymentDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PaymentDates build() {
			return new PaymentDates.PaymentDatesImpl(this);
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDates.PaymentDatesBuilder prune() {
			if (paymentFrequency!=null && !paymentFrequency.prune().hasData()) paymentFrequency = null;
			if (paymentDateSchedule!=null && !paymentDateSchedule.prune().hasData()) paymentDateSchedule = null;
			if (paymentDaysOffset!=null && !paymentDaysOffset.prune().hasData()) paymentDaysOffset = null;
			if (paymentDatesAdjustments!=null && !paymentDatesAdjustments.prune().hasData()) paymentDatesAdjustments = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPaymentFrequency()!=null && getPaymentFrequency().hasData()) return true;
			if (getFirstPaymentDate()!=null) return true;
			if (getLastRegularPaymentDate()!=null) return true;
			if (getPaymentDateSchedule()!=null && getPaymentDateSchedule().hasData()) return true;
			if (getPayRelativeTo()!=null) return true;
			if (getPaymentDaysOffset()!=null && getPaymentDaysOffset().hasData()) return true;
			if (getPaymentDatesAdjustments()!=null && getPaymentDatesAdjustments().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDates.PaymentDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentDates.PaymentDatesBuilder o = (PaymentDates.PaymentDatesBuilder) other;
			
			merger.mergeRosetta(getPaymentFrequency(), o.getPaymentFrequency(), this::setPaymentFrequency);
			merger.mergeRosetta(getPaymentDateSchedule(), o.getPaymentDateSchedule(), this::setPaymentDateSchedule);
			merger.mergeRosetta(getPaymentDaysOffset(), o.getPaymentDaysOffset(), this::setPaymentDaysOffset);
			merger.mergeRosetta(getPaymentDatesAdjustments(), o.getPaymentDatesAdjustments(), this::setPaymentDatesAdjustments);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getFirstPaymentDate(), o.getFirstPaymentDate(), this::setFirstPaymentDate);
			merger.mergeBasic(getLastRegularPaymentDate(), o.getLastRegularPaymentDate(), this::setLastRegularPaymentDate);
			merger.mergeBasic(getPayRelativeTo(), o.getPayRelativeTo(), this::setPayRelativeTo);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDates _that = getType().cast(o);
		
			if (!Objects.equals(paymentFrequency, _that.getPaymentFrequency())) return false;
			if (!Objects.equals(firstPaymentDate, _that.getFirstPaymentDate())) return false;
			if (!Objects.equals(lastRegularPaymentDate, _that.getLastRegularPaymentDate())) return false;
			if (!Objects.equals(paymentDateSchedule, _that.getPaymentDateSchedule())) return false;
			if (!Objects.equals(payRelativeTo, _that.getPayRelativeTo())) return false;
			if (!Objects.equals(paymentDaysOffset, _that.getPaymentDaysOffset())) return false;
			if (!Objects.equals(paymentDatesAdjustments, _that.getPaymentDatesAdjustments())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentFrequency != null ? paymentFrequency.hashCode() : 0);
			_result = 31 * _result + (firstPaymentDate != null ? firstPaymentDate.hashCode() : 0);
			_result = 31 * _result + (lastRegularPaymentDate != null ? lastRegularPaymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentDateSchedule != null ? paymentDateSchedule.hashCode() : 0);
			_result = 31 * _result + (payRelativeTo != null ? payRelativeTo.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentDaysOffset != null ? paymentDaysOffset.hashCode() : 0);
			_result = 31 * _result + (paymentDatesAdjustments != null ? paymentDatesAdjustments.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDatesBuilder {" +
				"paymentFrequency=" + this.paymentFrequency + ", " +
				"firstPaymentDate=" + this.firstPaymentDate + ", " +
				"lastRegularPaymentDate=" + this.lastRegularPaymentDate + ", " +
				"paymentDateSchedule=" + this.paymentDateSchedule + ", " +
				"payRelativeTo=" + this.payRelativeTo + ", " +
				"paymentDaysOffset=" + this.paymentDaysOffset + ", " +
				"paymentDatesAdjustments=" + this.paymentDatesAdjustments + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
