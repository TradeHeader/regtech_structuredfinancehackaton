package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.EquityCorporateEvents;
import cdm.legaldocumentation.master.EquityCorporateEvents.EquityCorporateEventsBuilder;
import cdm.legaldocumentation.master.EquityCorporateEvents.EquityCorporateEventsBuilderImpl;
import cdm.legaldocumentation.master.EquityCorporateEvents.EquityCorporateEventsImpl;
import cdm.legaldocumentation.master.meta.EquityCorporateEventsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * A class for defining the merger events and their treatment.
 * @version ${project.version}
 */
@RosettaDataType(value="EquityCorporateEvents", builder=EquityCorporateEvents.EquityCorporateEventsBuilderImpl.class, version="${project.version}")
public interface EquityCorporateEvents extends RosettaModelObject {

	EquityCorporateEventsMeta metaData = new EquityCorporateEventsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	EquityCorporateEvents build();
	
	EquityCorporateEvents.EquityCorporateEventsBuilder toBuilder();
	
	static EquityCorporateEvents.EquityCorporateEventsBuilder builder() {
		return new EquityCorporateEvents.EquityCorporateEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EquityCorporateEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EquityCorporateEvents> getType() {
		return EquityCorporateEvents.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface EquityCorporateEventsBuilder extends EquityCorporateEvents, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		EquityCorporateEvents.EquityCorporateEventsBuilder prune();
	}

	/*********************** Immutable Implementation of EquityCorporateEvents  ***********************/
	class EquityCorporateEventsImpl implements EquityCorporateEvents {
		
		protected EquityCorporateEventsImpl(EquityCorporateEvents.EquityCorporateEventsBuilder builder) {
		}
		
		@Override
		public EquityCorporateEvents build() {
			return this;
		}
		
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder toBuilder() {
			EquityCorporateEvents.EquityCorporateEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EquityCorporateEvents.EquityCorporateEventsBuilder builder) {
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityCorporateEvents {" +
			'}';
		}
	}

	/*********************** Builder Implementation of EquityCorporateEvents  ***********************/
	class EquityCorporateEventsBuilderImpl implements EquityCorporateEvents.EquityCorporateEventsBuilder {
	
	
		public EquityCorporateEventsBuilderImpl() {
		}
	
	
		
		@Override
		public EquityCorporateEvents build() {
			return new EquityCorporateEvents.EquityCorporateEventsImpl(this);
		}
		
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EquityCorporateEvents.EquityCorporateEventsBuilder o = (EquityCorporateEvents.EquityCorporateEventsBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityCorporateEventsBuilder {" +
			'}';
		}
	}
}
