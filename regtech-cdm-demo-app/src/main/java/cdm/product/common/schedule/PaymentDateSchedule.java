package cdm.product.common.schedule;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.common.schedule.PaymentDateSchedule;
import cdm.product.common.schedule.PaymentDateSchedule.PaymentDateScheduleBuilder;
import cdm.product.common.schedule.PaymentDateSchedule.PaymentDateScheduleBuilderImpl;
import cdm.product.common.schedule.PaymentDateSchedule.PaymentDateScheduleImpl;
import cdm.product.common.schedule.meta.PaymentDateScheduleMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * The payment dates when specified as relative to a set of dates specified somewhere else in the instance document/transaction, e.g. the valuation dates as typically the case for equity swaps, or when specified as a calculation period schedule.
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentDateSchedule", builder=PaymentDateSchedule.PaymentDateScheduleBuilderImpl.class, version="${project.version}")
public interface PaymentDateSchedule extends RosettaModelObject {

	PaymentDateScheduleMeta metaData = new PaymentDateScheduleMeta();

	/*********************** Getter Methods  ***********************/
	List<? extends AdjustableRelativeOrPeriodicDates> getInterimPaymentDates();
	/**
	 * The last payment when specified as an adjustable or relative date, as in the FpML total return construct.
	 */
	AdjustableOrRelativeDate getFinalPaymentDate();

	/*********************** Build Methods  ***********************/
	PaymentDateSchedule build();
	
	PaymentDateSchedule.PaymentDateScheduleBuilder toBuilder();
	
	static PaymentDateSchedule.PaymentDateScheduleBuilder builder() {
		return new PaymentDateSchedule.PaymentDateScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentDateSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentDateSchedule> getType() {
		return PaymentDateSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("interimPaymentDates"), processor, AdjustableRelativeOrPeriodicDates.class, getInterimPaymentDates());
		processRosetta(path.newSubPath("finalPaymentDate"), processor, AdjustableOrRelativeDate.class, getFinalPaymentDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentDateScheduleBuilder extends PaymentDateSchedule, RosettaModelObjectBuilder {
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateInterimPaymentDates(int _index);
		List<? extends AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder> getInterimPaymentDates();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFinalPaymentDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFinalPaymentDate();
		PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(AdjustableRelativeOrPeriodicDates interimPaymentDates0);
		PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(AdjustableRelativeOrPeriodicDates interimPaymentDates1, int _idx);
		PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(List<? extends AdjustableRelativeOrPeriodicDates> interimPaymentDates2);
		PaymentDateSchedule.PaymentDateScheduleBuilder setInterimPaymentDates(List<? extends AdjustableRelativeOrPeriodicDates> interimPaymentDates3);
		PaymentDateSchedule.PaymentDateScheduleBuilder setFinalPaymentDate(AdjustableOrRelativeDate finalPaymentDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("interimPaymentDates"), processor, AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder.class, getInterimPaymentDates());
			processRosetta(path.newSubPath("finalPaymentDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getFinalPaymentDate());
		}
		

		PaymentDateSchedule.PaymentDateScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentDateSchedule  ***********************/
	class PaymentDateScheduleImpl implements PaymentDateSchedule {
		private final List<? extends AdjustableRelativeOrPeriodicDates> interimPaymentDates;
		private final AdjustableOrRelativeDate finalPaymentDate;
		
		protected PaymentDateScheduleImpl(PaymentDateSchedule.PaymentDateScheduleBuilder builder) {
			this.interimPaymentDates = ofNullable(builder.getInterimPaymentDates()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.finalPaymentDate = ofNullable(builder.getFinalPaymentDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("interimPaymentDates")
		public List<? extends AdjustableRelativeOrPeriodicDates> getInterimPaymentDates() {
			return interimPaymentDates;
		}
		
		@Override
		@RosettaAttribute("finalPaymentDate")
		public AdjustableOrRelativeDate getFinalPaymentDate() {
			return finalPaymentDate;
		}
		
		@Override
		public PaymentDateSchedule build() {
			return this;
		}
		
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder toBuilder() {
			PaymentDateSchedule.PaymentDateScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentDateSchedule.PaymentDateScheduleBuilder builder) {
			ofNullable(getInterimPaymentDates()).ifPresent(builder::setInterimPaymentDates);
			ofNullable(getFinalPaymentDate()).ifPresent(builder::setFinalPaymentDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDateSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interimPaymentDates, _that.getInterimPaymentDates())) return false;
			if (!Objects.equals(finalPaymentDate, _that.getFinalPaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interimPaymentDates != null ? interimPaymentDates.hashCode() : 0);
			_result = 31 * _result + (finalPaymentDate != null ? finalPaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDateSchedule {" +
				"interimPaymentDates=" + this.interimPaymentDates + ", " +
				"finalPaymentDate=" + this.finalPaymentDate +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentDateSchedule  ***********************/
	class PaymentDateScheduleBuilderImpl implements PaymentDateSchedule.PaymentDateScheduleBuilder {
	
		protected List<AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder> interimPaymentDates = new ArrayList<>();
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder finalPaymentDate;
	
		public PaymentDateScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interimPaymentDates")
		public List<? extends AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder> getInterimPaymentDates() {
			return interimPaymentDates;
		}
		
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateInterimPaymentDates(int _index) {
		
			if (interimPaymentDates==null) {
				this.interimPaymentDates = new ArrayList<>();
			}
			AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder result;
			return getIndex(interimPaymentDates, _index, () -> {
						AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder newInterimPaymentDates = AdjustableRelativeOrPeriodicDates.builder();
						return newInterimPaymentDates;
					});
		}
		
		@Override
		@RosettaAttribute("finalPaymentDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getFinalPaymentDate() {
			return finalPaymentDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateFinalPaymentDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (finalPaymentDate!=null) {
				result = finalPaymentDate;
			}
			else {
				result = finalPaymentDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(AdjustableRelativeOrPeriodicDates interimPaymentDates) {
			if (interimPaymentDates!=null) this.interimPaymentDates.add(interimPaymentDates.toBuilder());
			return this;
		}
		
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(AdjustableRelativeOrPeriodicDates interimPaymentDates, int _idx) {
			getIndex(this.interimPaymentDates, _idx, () -> interimPaymentDates.toBuilder());
			return this;
		}
		@Override 
		public PaymentDateSchedule.PaymentDateScheduleBuilder addInterimPaymentDates(List<? extends AdjustableRelativeOrPeriodicDates> interimPaymentDatess) {
			if (interimPaymentDatess != null) {
				for (AdjustableRelativeOrPeriodicDates toAdd : interimPaymentDatess) {
					this.interimPaymentDates.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("interimPaymentDates")
		public PaymentDateSchedule.PaymentDateScheduleBuilder setInterimPaymentDates(List<? extends AdjustableRelativeOrPeriodicDates> interimPaymentDatess) {
			if (interimPaymentDatess == null)  {
				this.interimPaymentDates = new ArrayList<>();
			}
			else {
				this.interimPaymentDates = interimPaymentDatess.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("finalPaymentDate")
		public PaymentDateSchedule.PaymentDateScheduleBuilder setFinalPaymentDate(AdjustableOrRelativeDate finalPaymentDate) {
			this.finalPaymentDate = finalPaymentDate==null?null:finalPaymentDate.toBuilder();
			return this;
		}
		
		@Override
		public PaymentDateSchedule build() {
			return new PaymentDateSchedule.PaymentDateScheduleImpl(this);
		}
		
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder prune() {
			interimPaymentDates = interimPaymentDates.stream().filter(b->b!=null).<AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (finalPaymentDate!=null && !finalPaymentDate.prune().hasData()) finalPaymentDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterimPaymentDates()!=null && getInterimPaymentDates().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFinalPaymentDate()!=null && getFinalPaymentDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentDateSchedule.PaymentDateScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentDateSchedule.PaymentDateScheduleBuilder o = (PaymentDateSchedule.PaymentDateScheduleBuilder) other;
			
			merger.mergeRosetta(getInterimPaymentDates(), o.getInterimPaymentDates(), this::getOrCreateInterimPaymentDates);
			merger.mergeRosetta(getFinalPaymentDate(), o.getFinalPaymentDate(), this::setFinalPaymentDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentDateSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interimPaymentDates, _that.getInterimPaymentDates())) return false;
			if (!Objects.equals(finalPaymentDate, _that.getFinalPaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interimPaymentDates != null ? interimPaymentDates.hashCode() : 0);
			_result = 31 * _result + (finalPaymentDate != null ? finalPaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentDateScheduleBuilder {" +
				"interimPaymentDates=" + this.interimPaymentDates + ", " +
				"finalPaymentDate=" + this.finalPaymentDate +
			'}';
		}
	}
}
