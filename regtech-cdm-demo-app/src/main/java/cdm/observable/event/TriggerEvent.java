package cdm.observable.event;

import cdm.base.datetime.AveragingSchedule;
import cdm.base.datetime.DateList;
import cdm.observable.event.FeaturePayment;
import cdm.observable.event.Trigger;
import cdm.observable.event.TriggerEvent;
import cdm.observable.event.TriggerEvent.TriggerEventBuilder;
import cdm.observable.event.TriggerEvent.TriggerEventBuilderImpl;
import cdm.observable.event.TriggerEvent.TriggerEventImpl;
import cdm.observable.event.meta.TriggerEventMeta;
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
 * Observation point for trigger.
 * @version ${project.version}
 */
@RosettaDataType(value="TriggerEvent", builder=TriggerEvent.TriggerEventBuilderImpl.class, version="${project.version}")
public interface TriggerEvent extends RosettaModelObject {

	TriggerEventMeta metaData = new TriggerEventMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A derivative schedule.
	 */
	List<? extends AveragingSchedule> getSchedule();
	/**
	 * The trigger Dates.
	 */
	DateList getTriggerDates();
	/**
	 * The trigger level
	 */
	Trigger getTrigger();
	/**
	 * The feature payment, i.e. the payment made following trigger occurrence.
	 */
	FeaturePayment getFeaturePayment();

	/*********************** Build Methods  ***********************/
	TriggerEvent build();
	
	TriggerEvent.TriggerEventBuilder toBuilder();
	
	static TriggerEvent.TriggerEventBuilder builder() {
		return new TriggerEvent.TriggerEventBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TriggerEvent> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TriggerEvent> getType() {
		return TriggerEvent.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("schedule"), processor, AveragingSchedule.class, getSchedule());
		processRosetta(path.newSubPath("triggerDates"), processor, DateList.class, getTriggerDates());
		processRosetta(path.newSubPath("trigger"), processor, Trigger.class, getTrigger());
		processRosetta(path.newSubPath("featurePayment"), processor, FeaturePayment.class, getFeaturePayment());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TriggerEventBuilder extends TriggerEvent, RosettaModelObjectBuilder {
		AveragingSchedule.AveragingScheduleBuilder getOrCreateSchedule(int _index);
		List<? extends AveragingSchedule.AveragingScheduleBuilder> getSchedule();
		DateList.DateListBuilder getOrCreateTriggerDates();
		DateList.DateListBuilder getTriggerDates();
		Trigger.TriggerBuilder getOrCreateTrigger();
		Trigger.TriggerBuilder getTrigger();
		FeaturePayment.FeaturePaymentBuilder getOrCreateFeaturePayment();
		FeaturePayment.FeaturePaymentBuilder getFeaturePayment();
		TriggerEvent.TriggerEventBuilder addSchedule(AveragingSchedule schedule0);
		TriggerEvent.TriggerEventBuilder addSchedule(AveragingSchedule schedule1, int _idx);
		TriggerEvent.TriggerEventBuilder addSchedule(List<? extends AveragingSchedule> schedule2);
		TriggerEvent.TriggerEventBuilder setSchedule(List<? extends AveragingSchedule> schedule3);
		TriggerEvent.TriggerEventBuilder setTriggerDates(DateList triggerDates);
		TriggerEvent.TriggerEventBuilder setTrigger(Trigger trigger);
		TriggerEvent.TriggerEventBuilder setFeaturePayment(FeaturePayment featurePayment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("schedule"), processor, AveragingSchedule.AveragingScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("triggerDates"), processor, DateList.DateListBuilder.class, getTriggerDates());
			processRosetta(path.newSubPath("trigger"), processor, Trigger.TriggerBuilder.class, getTrigger());
			processRosetta(path.newSubPath("featurePayment"), processor, FeaturePayment.FeaturePaymentBuilder.class, getFeaturePayment());
		}
		

		TriggerEvent.TriggerEventBuilder prune();
	}

	/*********************** Immutable Implementation of TriggerEvent  ***********************/
	class TriggerEventImpl implements TriggerEvent {
		private final List<? extends AveragingSchedule> schedule;
		private final DateList triggerDates;
		private final Trigger trigger;
		private final FeaturePayment featurePayment;
		
		protected TriggerEventImpl(TriggerEvent.TriggerEventBuilder builder) {
			this.schedule = ofNullable(builder.getSchedule()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.triggerDates = ofNullable(builder.getTriggerDates()).map(f->f.build()).orElse(null);
			this.trigger = ofNullable(builder.getTrigger()).map(f->f.build()).orElse(null);
			this.featurePayment = ofNullable(builder.getFeaturePayment()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("schedule")
		public List<? extends AveragingSchedule> getSchedule() {
			return schedule;
		}
		
		@Override
		@RosettaAttribute("triggerDates")
		public DateList getTriggerDates() {
			return triggerDates;
		}
		
		@Override
		@RosettaAttribute("trigger")
		public Trigger getTrigger() {
			return trigger;
		}
		
		@Override
		@RosettaAttribute("featurePayment")
		public FeaturePayment getFeaturePayment() {
			return featurePayment;
		}
		
		@Override
		public TriggerEvent build() {
			return this;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder toBuilder() {
			TriggerEvent.TriggerEventBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TriggerEvent.TriggerEventBuilder builder) {
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getTriggerDates()).ifPresent(builder::setTriggerDates);
			ofNullable(getTrigger()).ifPresent(builder::setTrigger);
			ofNullable(getFeaturePayment()).ifPresent(builder::setFeaturePayment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TriggerEvent _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(triggerDates, _that.getTriggerDates())) return false;
			if (!Objects.equals(trigger, _that.getTrigger())) return false;
			if (!Objects.equals(featurePayment, _that.getFeaturePayment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (triggerDates != null ? triggerDates.hashCode() : 0);
			_result = 31 * _result + (trigger != null ? trigger.hashCode() : 0);
			_result = 31 * _result + (featurePayment != null ? featurePayment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TriggerEvent {" +
				"schedule=" + this.schedule + ", " +
				"triggerDates=" + this.triggerDates + ", " +
				"trigger=" + this.trigger + ", " +
				"featurePayment=" + this.featurePayment +
			'}';
		}
	}

	/*********************** Builder Implementation of TriggerEvent  ***********************/
	class TriggerEventBuilderImpl implements TriggerEvent.TriggerEventBuilder {
	
		protected List<AveragingSchedule.AveragingScheduleBuilder> schedule = new ArrayList<>();
		protected DateList.DateListBuilder triggerDates;
		protected Trigger.TriggerBuilder trigger;
		protected FeaturePayment.FeaturePaymentBuilder featurePayment;
	
		public TriggerEventBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("schedule")
		public List<? extends AveragingSchedule.AveragingScheduleBuilder> getSchedule() {
			return schedule;
		}
		
		public AveragingSchedule.AveragingScheduleBuilder getOrCreateSchedule(int _index) {
		
			if (schedule==null) {
				this.schedule = new ArrayList<>();
			}
			AveragingSchedule.AveragingScheduleBuilder result;
			return getIndex(schedule, _index, () -> {
						AveragingSchedule.AveragingScheduleBuilder newSchedule = AveragingSchedule.builder();
						return newSchedule;
					});
		}
		
		@Override
		@RosettaAttribute("triggerDates")
		public DateList.DateListBuilder getTriggerDates() {
			return triggerDates;
		}
		
		@Override
		public DateList.DateListBuilder getOrCreateTriggerDates() {
			DateList.DateListBuilder result;
			if (triggerDates!=null) {
				result = triggerDates;
			}
			else {
				result = triggerDates = DateList.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("trigger")
		public Trigger.TriggerBuilder getTrigger() {
			return trigger;
		}
		
		@Override
		public Trigger.TriggerBuilder getOrCreateTrigger() {
			Trigger.TriggerBuilder result;
			if (trigger!=null) {
				result = trigger;
			}
			else {
				result = trigger = Trigger.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("featurePayment")
		public FeaturePayment.FeaturePaymentBuilder getFeaturePayment() {
			return featurePayment;
		}
		
		@Override
		public FeaturePayment.FeaturePaymentBuilder getOrCreateFeaturePayment() {
			FeaturePayment.FeaturePaymentBuilder result;
			if (featurePayment!=null) {
				result = featurePayment;
			}
			else {
				result = featurePayment = FeaturePayment.builder();
			}
			
			return result;
		}
	
		@Override
		public TriggerEvent.TriggerEventBuilder addSchedule(AveragingSchedule schedule) {
			if (schedule!=null) this.schedule.add(schedule.toBuilder());
			return this;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder addSchedule(AveragingSchedule schedule, int _idx) {
			getIndex(this.schedule, _idx, () -> schedule.toBuilder());
			return this;
		}
		@Override 
		public TriggerEvent.TriggerEventBuilder addSchedule(List<? extends AveragingSchedule> schedules) {
			if (schedules != null) {
				for (AveragingSchedule toAdd : schedules) {
					this.schedule.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("schedule")
		public TriggerEvent.TriggerEventBuilder setSchedule(List<? extends AveragingSchedule> schedules) {
			if (schedules == null)  {
				this.schedule = new ArrayList<>();
			}
			else {
				this.schedule = schedules.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("triggerDates")
		public TriggerEvent.TriggerEventBuilder setTriggerDates(DateList triggerDates) {
			this.triggerDates = triggerDates==null?null:triggerDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("trigger")
		public TriggerEvent.TriggerEventBuilder setTrigger(Trigger trigger) {
			this.trigger = trigger==null?null:trigger.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("featurePayment")
		public TriggerEvent.TriggerEventBuilder setFeaturePayment(FeaturePayment featurePayment) {
			this.featurePayment = featurePayment==null?null:featurePayment.toBuilder();
			return this;
		}
		
		@Override
		public TriggerEvent build() {
			return new TriggerEvent.TriggerEventImpl(this);
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TriggerEvent.TriggerEventBuilder prune() {
			schedule = schedule.stream().filter(b->b!=null).<AveragingSchedule.AveragingScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (triggerDates!=null && !triggerDates.prune().hasData()) triggerDates = null;
			if (trigger!=null && !trigger.prune().hasData()) trigger = null;
			if (featurePayment!=null && !featurePayment.prune().hasData()) featurePayment = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSchedule()!=null && getSchedule().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getTriggerDates()!=null && getTriggerDates().hasData()) return true;
			if (getTrigger()!=null && getTrigger().hasData()) return true;
			if (getFeaturePayment()!=null && getFeaturePayment().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TriggerEvent.TriggerEventBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TriggerEvent.TriggerEventBuilder o = (TriggerEvent.TriggerEventBuilder) other;
			
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::getOrCreateSchedule);
			merger.mergeRosetta(getTriggerDates(), o.getTriggerDates(), this::setTriggerDates);
			merger.mergeRosetta(getTrigger(), o.getTrigger(), this::setTrigger);
			merger.mergeRosetta(getFeaturePayment(), o.getFeaturePayment(), this::setFeaturePayment);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TriggerEvent _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(triggerDates, _that.getTriggerDates())) return false;
			if (!Objects.equals(trigger, _that.getTrigger())) return false;
			if (!Objects.equals(featurePayment, _that.getFeaturePayment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (triggerDates != null ? triggerDates.hashCode() : 0);
			_result = 31 * _result + (trigger != null ? trigger.hashCode() : 0);
			_result = 31 * _result + (featurePayment != null ? featurePayment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TriggerEventBuilder {" +
				"schedule=" + this.schedule + ", " +
				"triggerDates=" + this.triggerDates + ", " +
				"trigger=" + this.trigger + ", " +
				"featurePayment=" + this.featurePayment +
			'}';
		}
	}
}
