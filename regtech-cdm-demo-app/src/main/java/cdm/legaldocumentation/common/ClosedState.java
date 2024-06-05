package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.ClosedState;
import cdm.legaldocumentation.common.ClosedState.ClosedStateBuilder;
import cdm.legaldocumentation.common.ClosedState.ClosedStateBuilderImpl;
import cdm.legaldocumentation.common.ClosedState.ClosedStateImpl;
import cdm.legaldocumentation.common.ClosedStateEnum;
import cdm.legaldocumentation.common.meta.ClosedStateMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  A class to qualify the closed state of an execution or a contract through the combination or a state (e.g. terminated, novated) and a set of dates: activity date, effective date and, when relevant, last payment date.
 * @version ${project.version}
 */
@RosettaDataType(value="ClosedState", builder=ClosedState.ClosedStateBuilderImpl.class, version="${project.version}")
public interface ClosedState extends RosettaModelObject {

	ClosedStateMeta metaData = new ClosedStateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The qualification of what gave way to the contract or execution closure, e.g. allocation, termination, ...
	 */
	ClosedStateEnum getState();
	/**
	 * The activity date on which the closing state took place, i.e. either the event date of the closing event (e.g. option exercise, contract early termination) or the contractual termination date.
	 */
	Date getActivityDate();
	/**
	 * The date on which the closing event contractually takes effect, when different from the activity date. When an explicit event effective date attribute is associated with the closing event, it will be that date. In the case of a cancellation event, it will be the date on which the cancelled event took place.
	 */
	Date getEffectiveDate();
	/**
	 * The date associated with the last payment in relation to the artefact (e.g. contract) to which this closed state applies. As an example, in the case of an early termination event, it would be the settlement date of the associated fee, if applicable.
	 */
	Date getLastPaymentDate();

	/*********************** Build Methods  ***********************/
	ClosedState build();
	
	ClosedState.ClosedStateBuilder toBuilder();
	
	static ClosedState.ClosedStateBuilder builder() {
		return new ClosedState.ClosedStateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ClosedState> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ClosedState> getType() {
		return ClosedState.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("state"), ClosedStateEnum.class, getState(), this);
		processor.processBasic(path.newSubPath("activityDate"), Date.class, getActivityDate(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processor.processBasic(path.newSubPath("lastPaymentDate"), Date.class, getLastPaymentDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ClosedStateBuilder extends ClosedState, RosettaModelObjectBuilder {
		ClosedState.ClosedStateBuilder setState(ClosedStateEnum state);
		ClosedState.ClosedStateBuilder setActivityDate(Date activityDate);
		ClosedState.ClosedStateBuilder setEffectiveDate(Date effectiveDate);
		ClosedState.ClosedStateBuilder setLastPaymentDate(Date lastPaymentDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("state"), ClosedStateEnum.class, getState(), this);
			processor.processBasic(path.newSubPath("activityDate"), Date.class, getActivityDate(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processor.processBasic(path.newSubPath("lastPaymentDate"), Date.class, getLastPaymentDate(), this);
		}
		

		ClosedState.ClosedStateBuilder prune();
	}

	/*********************** Immutable Implementation of ClosedState  ***********************/
	class ClosedStateImpl implements ClosedState {
		private final ClosedStateEnum state;
		private final Date activityDate;
		private final Date effectiveDate;
		private final Date lastPaymentDate;
		
		protected ClosedStateImpl(ClosedState.ClosedStateBuilder builder) {
			this.state = builder.getState();
			this.activityDate = builder.getActivityDate();
			this.effectiveDate = builder.getEffectiveDate();
			this.lastPaymentDate = builder.getLastPaymentDate();
		}
		
		@Override
		@RosettaAttribute("state")
		public ClosedStateEnum getState() {
			return state;
		}
		
		@Override
		@RosettaAttribute("activityDate")
		public Date getActivityDate() {
			return activityDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("lastPaymentDate")
		public Date getLastPaymentDate() {
			return lastPaymentDate;
		}
		
		@Override
		public ClosedState build() {
			return this;
		}
		
		@Override
		public ClosedState.ClosedStateBuilder toBuilder() {
			ClosedState.ClosedStateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ClosedState.ClosedStateBuilder builder) {
			ofNullable(getState()).ifPresent(builder::setState);
			ofNullable(getActivityDate()).ifPresent(builder::setActivityDate);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getLastPaymentDate()).ifPresent(builder::setLastPaymentDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ClosedState _that = getType().cast(o);
		
			if (!Objects.equals(state, _that.getState())) return false;
			if (!Objects.equals(activityDate, _that.getActivityDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(lastPaymentDate, _that.getLastPaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (state != null ? state.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (activityDate != null ? activityDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (lastPaymentDate != null ? lastPaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ClosedState {" +
				"state=" + this.state + ", " +
				"activityDate=" + this.activityDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"lastPaymentDate=" + this.lastPaymentDate +
			'}';
		}
	}

	/*********************** Builder Implementation of ClosedState  ***********************/
	class ClosedStateBuilderImpl implements ClosedState.ClosedStateBuilder {
	
		protected ClosedStateEnum state;
		protected Date activityDate;
		protected Date effectiveDate;
		protected Date lastPaymentDate;
	
		public ClosedStateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("state")
		public ClosedStateEnum getState() {
			return state;
		}
		
		@Override
		@RosettaAttribute("activityDate")
		public Date getActivityDate() {
			return activityDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("lastPaymentDate")
		public Date getLastPaymentDate() {
			return lastPaymentDate;
		}
		
	
		@Override
		@RosettaAttribute("state")
		public ClosedState.ClosedStateBuilder setState(ClosedStateEnum state) {
			this.state = state==null?null:state;
			return this;
		}
		@Override
		@RosettaAttribute("activityDate")
		public ClosedState.ClosedStateBuilder setActivityDate(Date activityDate) {
			this.activityDate = activityDate==null?null:activityDate;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public ClosedState.ClosedStateBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		@Override
		@RosettaAttribute("lastPaymentDate")
		public ClosedState.ClosedStateBuilder setLastPaymentDate(Date lastPaymentDate) {
			this.lastPaymentDate = lastPaymentDate==null?null:lastPaymentDate;
			return this;
		}
		
		@Override
		public ClosedState build() {
			return new ClosedState.ClosedStateImpl(this);
		}
		
		@Override
		public ClosedState.ClosedStateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ClosedState.ClosedStateBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getState()!=null) return true;
			if (getActivityDate()!=null) return true;
			if (getEffectiveDate()!=null) return true;
			if (getLastPaymentDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ClosedState.ClosedStateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ClosedState.ClosedStateBuilder o = (ClosedState.ClosedStateBuilder) other;
			
			
			merger.mergeBasic(getState(), o.getState(), this::setState);
			merger.mergeBasic(getActivityDate(), o.getActivityDate(), this::setActivityDate);
			merger.mergeBasic(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			merger.mergeBasic(getLastPaymentDate(), o.getLastPaymentDate(), this::setLastPaymentDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ClosedState _that = getType().cast(o);
		
			if (!Objects.equals(state, _that.getState())) return false;
			if (!Objects.equals(activityDate, _that.getActivityDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(lastPaymentDate, _that.getLastPaymentDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (state != null ? state.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (activityDate != null ? activityDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (lastPaymentDate != null ? lastPaymentDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ClosedStateBuilder {" +
				"state=" + this.state + ", " +
				"activityDate=" + this.activityDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"lastPaymentDate=" + this.lastPaymentDate +
			'}';
		}
	}
}
