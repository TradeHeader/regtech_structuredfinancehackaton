package cdm.event.common;

import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.ObservationInstruction.ObservationInstructionBuilder;
import cdm.event.common.ObservationInstruction.ObservationInstructionBuilderImpl;
import cdm.event.common.ObservationInstruction.ObservationInstructionImpl;
import cdm.event.common.meta.ObservationInstructionMeta;
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
 * Specifies inputs needed to process an observation.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationInstruction", builder=ObservationInstruction.ObservationInstructionBuilderImpl.class, version="${project.version}")
public interface ObservationInstruction extends RosettaModelObject {

	ObservationInstructionMeta metaData = new ObservationInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Contains all information related to an observation.
	 */
	ObservationEvent getObservationEvent();

	/*********************** Build Methods  ***********************/
	ObservationInstruction build();
	
	ObservationInstruction.ObservationInstructionBuilder toBuilder();
	
	static ObservationInstruction.ObservationInstructionBuilder builder() {
		return new ObservationInstruction.ObservationInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationInstruction> getType() {
		return ObservationInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("observationEvent"), processor, ObservationEvent.class, getObservationEvent());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationInstructionBuilder extends ObservationInstruction, RosettaModelObjectBuilder {
		ObservationEvent.ObservationEventBuilder getOrCreateObservationEvent();
		ObservationEvent.ObservationEventBuilder getObservationEvent();
		ObservationInstruction.ObservationInstructionBuilder setObservationEvent(ObservationEvent observationEvent);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("observationEvent"), processor, ObservationEvent.ObservationEventBuilder.class, getObservationEvent());
		}
		

		ObservationInstruction.ObservationInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationInstruction  ***********************/
	class ObservationInstructionImpl implements ObservationInstruction {
		private final ObservationEvent observationEvent;
		
		protected ObservationInstructionImpl(ObservationInstruction.ObservationInstructionBuilder builder) {
			this.observationEvent = ofNullable(builder.getObservationEvent()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observationEvent")
		public ObservationEvent getObservationEvent() {
			return observationEvent;
		}
		
		@Override
		public ObservationInstruction build() {
			return this;
		}
		
		@Override
		public ObservationInstruction.ObservationInstructionBuilder toBuilder() {
			ObservationInstruction.ObservationInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationInstruction.ObservationInstructionBuilder builder) {
			ofNullable(getObservationEvent()).ifPresent(builder::setObservationEvent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationInstruction _that = getType().cast(o);
		
			if (!Objects.equals(observationEvent, _that.getObservationEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationEvent != null ? observationEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationInstruction {" +
				"observationEvent=" + this.observationEvent +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationInstruction  ***********************/
	class ObservationInstructionBuilderImpl implements ObservationInstruction.ObservationInstructionBuilder {
	
		protected ObservationEvent.ObservationEventBuilder observationEvent;
	
		public ObservationInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observationEvent")
		public ObservationEvent.ObservationEventBuilder getObservationEvent() {
			return observationEvent;
		}
		
		@Override
		public ObservationEvent.ObservationEventBuilder getOrCreateObservationEvent() {
			ObservationEvent.ObservationEventBuilder result;
			if (observationEvent!=null) {
				result = observationEvent;
			}
			else {
				result = observationEvent = ObservationEvent.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("observationEvent")
		public ObservationInstruction.ObservationInstructionBuilder setObservationEvent(ObservationEvent observationEvent) {
			this.observationEvent = observationEvent==null?null:observationEvent.toBuilder();
			return this;
		}
		
		@Override
		public ObservationInstruction build() {
			return new ObservationInstruction.ObservationInstructionImpl(this);
		}
		
		@Override
		public ObservationInstruction.ObservationInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationInstruction.ObservationInstructionBuilder prune() {
			if (observationEvent!=null && !observationEvent.prune().hasData()) observationEvent = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservationEvent()!=null && getObservationEvent().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationInstruction.ObservationInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationInstruction.ObservationInstructionBuilder o = (ObservationInstruction.ObservationInstructionBuilder) other;
			
			merger.mergeRosetta(getObservationEvent(), o.getObservationEvent(), this::setObservationEvent);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationInstruction _that = getType().cast(o);
		
			if (!Objects.equals(observationEvent, _that.getObservationEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationEvent != null ? observationEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationInstructionBuilder {" +
				"observationEvent=" + this.observationEvent +
			'}';
		}
	}
}
