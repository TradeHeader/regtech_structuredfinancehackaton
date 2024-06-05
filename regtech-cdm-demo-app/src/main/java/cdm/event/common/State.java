package cdm.event.common;

import cdm.event.common.State;
import cdm.event.common.State.StateBuilder;
import cdm.event.common.State.StateBuilderImpl;
import cdm.event.common.State.StateImpl;
import cdm.event.common.meta.StateMeta;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.ClosedState;
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
 * Defines the state of a trade at a point in the Trade&#39;s life cycle. Trades have many state dimensions, all of which are represented here. For example, states useful for position keeping are represented alongside those needed for regulatory reporting.
 * @version ${project.version}
 */
@RosettaDataType(value="State", builder=State.StateBuilderImpl.class, version="${project.version}")
public interface State extends RosettaModelObject {

	StateMeta metaData = new StateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the qualification of what led to the trade&#39;s closure alongside the dates on which this closure took effect.
	 */
	ClosedState getClosedState();
	/**
	 * Identifies the state of the position, to distinguish if just executed, formed, already settled, closed, etc.
	 */
	PositionStatusEnum getPositionState();

	/*********************** Build Methods  ***********************/
	State build();
	
	State.StateBuilder toBuilder();
	
	static State.StateBuilder builder() {
		return new State.StateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends State> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends State> getType() {
		return State.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("closedState"), processor, ClosedState.class, getClosedState());
		processor.processBasic(path.newSubPath("positionState"), PositionStatusEnum.class, getPositionState(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface StateBuilder extends State, RosettaModelObjectBuilder {
		ClosedState.ClosedStateBuilder getOrCreateClosedState();
		ClosedState.ClosedStateBuilder getClosedState();
		State.StateBuilder setClosedState(ClosedState closedState);
		State.StateBuilder setPositionState(PositionStatusEnum positionState);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("closedState"), processor, ClosedState.ClosedStateBuilder.class, getClosedState());
			processor.processBasic(path.newSubPath("positionState"), PositionStatusEnum.class, getPositionState(), this);
		}
		

		State.StateBuilder prune();
	}

	/*********************** Immutable Implementation of State  ***********************/
	class StateImpl implements State {
		private final ClosedState closedState;
		private final PositionStatusEnum positionState;
		
		protected StateImpl(State.StateBuilder builder) {
			this.closedState = ofNullable(builder.getClosedState()).map(f->f.build()).orElse(null);
			this.positionState = builder.getPositionState();
		}
		
		@Override
		@RosettaAttribute("closedState")
		public ClosedState getClosedState() {
			return closedState;
		}
		
		@Override
		@RosettaAttribute("positionState")
		public PositionStatusEnum getPositionState() {
			return positionState;
		}
		
		@Override
		public State build() {
			return this;
		}
		
		@Override
		public State.StateBuilder toBuilder() {
			State.StateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(State.StateBuilder builder) {
			ofNullable(getClosedState()).ifPresent(builder::setClosedState);
			ofNullable(getPositionState()).ifPresent(builder::setPositionState);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			State _that = getType().cast(o);
		
			if (!Objects.equals(closedState, _that.getClosedState())) return false;
			if (!Objects.equals(positionState, _that.getPositionState())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (closedState != null ? closedState.hashCode() : 0);
			_result = 31 * _result + (positionState != null ? positionState.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "State {" +
				"closedState=" + this.closedState + ", " +
				"positionState=" + this.positionState +
			'}';
		}
	}

	/*********************** Builder Implementation of State  ***********************/
	class StateBuilderImpl implements State.StateBuilder {
	
		protected ClosedState.ClosedStateBuilder closedState;
		protected PositionStatusEnum positionState;
	
		public StateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("closedState")
		public ClosedState.ClosedStateBuilder getClosedState() {
			return closedState;
		}
		
		@Override
		public ClosedState.ClosedStateBuilder getOrCreateClosedState() {
			ClosedState.ClosedStateBuilder result;
			if (closedState!=null) {
				result = closedState;
			}
			else {
				result = closedState = ClosedState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("positionState")
		public PositionStatusEnum getPositionState() {
			return positionState;
		}
		
	
		@Override
		@RosettaAttribute("closedState")
		public State.StateBuilder setClosedState(ClosedState closedState) {
			this.closedState = closedState==null?null:closedState.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("positionState")
		public State.StateBuilder setPositionState(PositionStatusEnum positionState) {
			this.positionState = positionState==null?null:positionState;
			return this;
		}
		
		@Override
		public State build() {
			return new State.StateImpl(this);
		}
		
		@Override
		public State.StateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public State.StateBuilder prune() {
			if (closedState!=null && !closedState.prune().hasData()) closedState = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getClosedState()!=null && getClosedState().hasData()) return true;
			if (getPositionState()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public State.StateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			State.StateBuilder o = (State.StateBuilder) other;
			
			merger.mergeRosetta(getClosedState(), o.getClosedState(), this::setClosedState);
			
			merger.mergeBasic(getPositionState(), o.getPositionState(), this::setPositionState);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			State _that = getType().cast(o);
		
			if (!Objects.equals(closedState, _that.getClosedState())) return false;
			if (!Objects.equals(positionState, _that.getPositionState())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (closedState != null ? closedState.hashCode() : 0);
			_result = 31 * _result + (positionState != null ? positionState.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StateBuilder {" +
				"closedState=" + this.closedState + ", " +
				"positionState=" + this.positionState +
			'}';
		}
	}
}
