package cdm.product.template;

import cdm.base.datetime.RelativeDateOffset;
import cdm.base.math.Schedule;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverBuilder;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverBuilderImpl;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverImpl;
import cdm.observable.asset.Money;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder;
import cdm.product.common.schedule.AmountSchedule;
import cdm.product.template.ExerciseFeeSchedule;
import cdm.product.template.ExerciseFeeSchedule.ExerciseFeeScheduleBuilder;
import cdm.product.template.ExerciseFeeSchedule.ExerciseFeeScheduleBuilderImpl;
import cdm.product.template.ExerciseFeeSchedule.ExerciseFeeScheduleImpl;
import cdm.product.template.meta.ExerciseFeeScheduleMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to define a fee or schedule of fees to be payable on the exercise of an option. This fee may be defined as an amount or a percentage of the notional exercised. As a difference with FpML, it extends the BuyerSeller class.
 * @version ${project.version}
 */
@RosettaDataType(value="ExerciseFeeSchedule", builder=ExerciseFeeSchedule.ExerciseFeeScheduleBuilderImpl.class, version="${project.version}")
public interface ExerciseFeeSchedule extends PayerReceiver {

	ExerciseFeeScheduleMeta metaData = new ExerciseFeeScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A pointer style reference to the associated notional schedule defined elsewhere in the document.
	 */
	ReferenceWithMetaMoney getNotionalReference();
	/**
	 * The exercise fee amount schedule. The fees are expressed as currency amounts. The currency of the fee is assumed to be that of the notional schedule referenced.
	 */
	AmountSchedule getFeeAmountSchedule();
	/**
	 * The exercise free rate schedule. The fees are expressed as percentage rates of the notional being exercised. The currency of the fee is assumed to be that of the notional schedule referenced.
	 */
	Schedule getFeeRateSchedule();
	/**
	 * The date on which exercise fee(s) will be paid. It is specified as a relative date.
	 */
	RelativeDateOffset getFeePaymentDate();

	/*********************** Build Methods  ***********************/
	ExerciseFeeSchedule build();
	
	ExerciseFeeSchedule.ExerciseFeeScheduleBuilder toBuilder();
	
	static ExerciseFeeSchedule.ExerciseFeeScheduleBuilder builder() {
		return new ExerciseFeeSchedule.ExerciseFeeScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExerciseFeeSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExerciseFeeSchedule> getType() {
		return ExerciseFeeSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("payer"), CounterpartyRoleEnum.class, getPayer(), this);
		processor.processBasic(path.newSubPath("receiver"), CounterpartyRoleEnum.class, getReceiver(), this);
		processRosetta(path.newSubPath("notionalReference"), processor, ReferenceWithMetaMoney.class, getNotionalReference());
		processRosetta(path.newSubPath("feeAmountSchedule"), processor, AmountSchedule.class, getFeeAmountSchedule());
		processRosetta(path.newSubPath("feeRateSchedule"), processor, Schedule.class, getFeeRateSchedule());
		processRosetta(path.newSubPath("feePaymentDate"), processor, RelativeDateOffset.class, getFeePaymentDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExerciseFeeScheduleBuilder extends ExerciseFeeSchedule, PayerReceiver.PayerReceiverBuilder, RosettaModelObjectBuilder {
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionalReference();
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionalReference();
		AmountSchedule.AmountScheduleBuilder getOrCreateFeeAmountSchedule();
		AmountSchedule.AmountScheduleBuilder getFeeAmountSchedule();
		Schedule.ScheduleBuilder getOrCreateFeeRateSchedule();
		Schedule.ScheduleBuilder getFeeRateSchedule();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateFeePaymentDate();
		RelativeDateOffset.RelativeDateOffsetBuilder getFeePaymentDate();
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setPayer(CounterpartyRoleEnum payer);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setReceiver(CounterpartyRoleEnum receiver);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setNotionalReference(ReferenceWithMetaMoney notionalReference0);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setNotionalReferenceValue(Money notionalReference1);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeeAmountSchedule(AmountSchedule feeAmountSchedule);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeeRateSchedule(Schedule feeRateSchedule);
		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeePaymentDate(RelativeDateOffset feePaymentDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("payer"), CounterpartyRoleEnum.class, getPayer(), this);
			processor.processBasic(path.newSubPath("receiver"), CounterpartyRoleEnum.class, getReceiver(), this);
			processRosetta(path.newSubPath("notionalReference"), processor, ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder.class, getNotionalReference());
			processRosetta(path.newSubPath("feeAmountSchedule"), processor, AmountSchedule.AmountScheduleBuilder.class, getFeeAmountSchedule());
			processRosetta(path.newSubPath("feeRateSchedule"), processor, Schedule.ScheduleBuilder.class, getFeeRateSchedule());
			processRosetta(path.newSubPath("feePaymentDate"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getFeePaymentDate());
		}
		

		ExerciseFeeSchedule.ExerciseFeeScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of ExerciseFeeSchedule  ***********************/
	class ExerciseFeeScheduleImpl extends PayerReceiver.PayerReceiverImpl implements ExerciseFeeSchedule {
		private final ReferenceWithMetaMoney notionalReference;
		private final AmountSchedule feeAmountSchedule;
		private final Schedule feeRateSchedule;
		private final RelativeDateOffset feePaymentDate;
		
		protected ExerciseFeeScheduleImpl(ExerciseFeeSchedule.ExerciseFeeScheduleBuilder builder) {
			super(builder);
			this.notionalReference = ofNullable(builder.getNotionalReference()).map(f->f.build()).orElse(null);
			this.feeAmountSchedule = ofNullable(builder.getFeeAmountSchedule()).map(f->f.build()).orElse(null);
			this.feeRateSchedule = ofNullable(builder.getFeeRateSchedule()).map(f->f.build()).orElse(null);
			this.feePaymentDate = ofNullable(builder.getFeePaymentDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("notionalReference")
		public ReferenceWithMetaMoney getNotionalReference() {
			return notionalReference;
		}
		
		@Override
		@RosettaAttribute("feeAmountSchedule")
		public AmountSchedule getFeeAmountSchedule() {
			return feeAmountSchedule;
		}
		
		@Override
		@RosettaAttribute("feeRateSchedule")
		public Schedule getFeeRateSchedule() {
			return feeRateSchedule;
		}
		
		@Override
		@RosettaAttribute("feePaymentDate")
		public RelativeDateOffset getFeePaymentDate() {
			return feePaymentDate;
		}
		
		@Override
		public ExerciseFeeSchedule build() {
			return this;
		}
		
		@Override
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder toBuilder() {
			ExerciseFeeSchedule.ExerciseFeeScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExerciseFeeSchedule.ExerciseFeeScheduleBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getNotionalReference()).ifPresent(builder::setNotionalReference);
			ofNullable(getFeeAmountSchedule()).ifPresent(builder::setFeeAmountSchedule);
			ofNullable(getFeeRateSchedule()).ifPresent(builder::setFeeRateSchedule);
			ofNullable(getFeePaymentDate()).ifPresent(builder::setFeePaymentDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ExerciseFeeSchedule _that = getType().cast(o);
		
			if (!Objects.equals(notionalReference, _that.getNotionalReference())) return false;
			if (!Objects.equals(feeAmountSchedule, _that.getFeeAmountSchedule())) return false;
			if (!Objects.equals(feeRateSchedule, _that.getFeeRateSchedule())) return false;
			if (!Objects.equals(feePaymentDate, _that.getFeePaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (notionalReference != null ? notionalReference.hashCode() : 0);
			_result = 31 * _result + (feeAmountSchedule != null ? feeAmountSchedule.hashCode() : 0);
			_result = 31 * _result + (feeRateSchedule != null ? feeRateSchedule.hashCode() : 0);
			_result = 31 * _result + (feePaymentDate != null ? feePaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExerciseFeeSchedule {" +
				"notionalReference=" + this.notionalReference + ", " +
				"feeAmountSchedule=" + this.feeAmountSchedule + ", " +
				"feeRateSchedule=" + this.feeRateSchedule + ", " +
				"feePaymentDate=" + this.feePaymentDate +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ExerciseFeeSchedule  ***********************/
	class ExerciseFeeScheduleBuilderImpl extends PayerReceiver.PayerReceiverBuilderImpl  implements ExerciseFeeSchedule.ExerciseFeeScheduleBuilder {
	
		protected ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder notionalReference;
		protected AmountSchedule.AmountScheduleBuilder feeAmountSchedule;
		protected Schedule.ScheduleBuilder feeRateSchedule;
		protected RelativeDateOffset.RelativeDateOffsetBuilder feePaymentDate;
	
		public ExerciseFeeScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("notionalReference")
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionalReference() {
			return notionalReference;
		}
		
		@Override
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionalReference() {
			ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder result;
			if (notionalReference!=null) {
				result = notionalReference;
			}
			else {
				result = notionalReference = ReferenceWithMetaMoney.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("feeAmountSchedule")
		public AmountSchedule.AmountScheduleBuilder getFeeAmountSchedule() {
			return feeAmountSchedule;
		}
		
		@Override
		public AmountSchedule.AmountScheduleBuilder getOrCreateFeeAmountSchedule() {
			AmountSchedule.AmountScheduleBuilder result;
			if (feeAmountSchedule!=null) {
				result = feeAmountSchedule;
			}
			else {
				result = feeAmountSchedule = AmountSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("feeRateSchedule")
		public Schedule.ScheduleBuilder getFeeRateSchedule() {
			return feeRateSchedule;
		}
		
		@Override
		public Schedule.ScheduleBuilder getOrCreateFeeRateSchedule() {
			Schedule.ScheduleBuilder result;
			if (feeRateSchedule!=null) {
				result = feeRateSchedule;
			}
			else {
				result = feeRateSchedule = Schedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("feePaymentDate")
		public RelativeDateOffset.RelativeDateOffsetBuilder getFeePaymentDate() {
			return feePaymentDate;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateFeePaymentDate() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (feePaymentDate!=null) {
				result = feePaymentDate;
			}
			else {
				result = feePaymentDate = RelativeDateOffset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("payer")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setPayer(CounterpartyRoleEnum payer) {
			this.payer = payer==null?null:payer;
			return this;
		}
		@Override
		@RosettaAttribute("receiver")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setReceiver(CounterpartyRoleEnum receiver) {
			this.receiver = receiver==null?null:receiver;
			return this;
		}
		@Override
		@RosettaAttribute("notionalReference")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setNotionalReference(ReferenceWithMetaMoney notionalReference) {
			this.notionalReference = notionalReference==null?null:notionalReference.toBuilder();
			return this;
		}
		@Override
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setNotionalReferenceValue(Money notionalReference) {
			this.getOrCreateNotionalReference().setValue(notionalReference);
			return this;
		}
		@Override
		@RosettaAttribute("feeAmountSchedule")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeeAmountSchedule(AmountSchedule feeAmountSchedule) {
			this.feeAmountSchedule = feeAmountSchedule==null?null:feeAmountSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("feeRateSchedule")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeeRateSchedule(Schedule feeRateSchedule) {
			this.feeRateSchedule = feeRateSchedule==null?null:feeRateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("feePaymentDate")
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder setFeePaymentDate(RelativeDateOffset feePaymentDate) {
			this.feePaymentDate = feePaymentDate==null?null:feePaymentDate.toBuilder();
			return this;
		}
		
		@Override
		public ExerciseFeeSchedule build() {
			return new ExerciseFeeSchedule.ExerciseFeeScheduleImpl(this);
		}
		
		@Override
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder prune() {
			super.prune();
			if (notionalReference!=null && !notionalReference.prune().hasData()) notionalReference = null;
			if (feeAmountSchedule!=null && !feeAmountSchedule.prune().hasData()) feeAmountSchedule = null;
			if (feeRateSchedule!=null && !feeRateSchedule.prune().hasData()) feeRateSchedule = null;
			if (feePaymentDate!=null && !feePaymentDate.prune().hasData()) feePaymentDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getNotionalReference()!=null && getNotionalReference().hasData()) return true;
			if (getFeeAmountSchedule()!=null && getFeeAmountSchedule().hasData()) return true;
			if (getFeeRateSchedule()!=null && getFeeRateSchedule().hasData()) return true;
			if (getFeePaymentDate()!=null && getFeePaymentDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExerciseFeeSchedule.ExerciseFeeScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ExerciseFeeSchedule.ExerciseFeeScheduleBuilder o = (ExerciseFeeSchedule.ExerciseFeeScheduleBuilder) other;
			
			merger.mergeRosetta(getNotionalReference(), o.getNotionalReference(), this::setNotionalReference);
			merger.mergeRosetta(getFeeAmountSchedule(), o.getFeeAmountSchedule(), this::setFeeAmountSchedule);
			merger.mergeRosetta(getFeeRateSchedule(), o.getFeeRateSchedule(), this::setFeeRateSchedule);
			merger.mergeRosetta(getFeePaymentDate(), o.getFeePaymentDate(), this::setFeePaymentDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ExerciseFeeSchedule _that = getType().cast(o);
		
			if (!Objects.equals(notionalReference, _that.getNotionalReference())) return false;
			if (!Objects.equals(feeAmountSchedule, _that.getFeeAmountSchedule())) return false;
			if (!Objects.equals(feeRateSchedule, _that.getFeeRateSchedule())) return false;
			if (!Objects.equals(feePaymentDate, _that.getFeePaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (notionalReference != null ? notionalReference.hashCode() : 0);
			_result = 31 * _result + (feeAmountSchedule != null ? feeAmountSchedule.hashCode() : 0);
			_result = 31 * _result + (feeRateSchedule != null ? feeRateSchedule.hashCode() : 0);
			_result = 31 * _result + (feePaymentDate != null ? feePaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExerciseFeeScheduleBuilder {" +
				"notionalReference=" + this.notionalReference + ", " +
				"feeAmountSchedule=" + this.feeAmountSchedule + ", " +
				"feeRateSchedule=" + this.feeRateSchedule + ", " +
				"feePaymentDate=" + this.feePaymentDate +
			'}' + " " + super.toString();
		}
	}
}
