package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.IndexAdjustmentEvents;
import cdm.legaldocumentation.master.IndexAdjustmentEvents.IndexAdjustmentEventsBuilder;
import cdm.legaldocumentation.master.IndexAdjustmentEvents.IndexAdjustmentEventsBuilderImpl;
import cdm.legaldocumentation.master.IndexAdjustmentEvents.IndexAdjustmentEventsImpl;
import cdm.legaldocumentation.master.meta.IndexAdjustmentEventsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * Defines the specification of the consequences of Index Events as defined by the 2002 ISDA Equity Derivatives Definitions.
 * @version ${project.version}
 */
@RosettaDataType(value="IndexAdjustmentEvents", builder=IndexAdjustmentEvents.IndexAdjustmentEventsBuilderImpl.class, version="${project.version}")
public interface IndexAdjustmentEvents extends RosettaModelObject {

	IndexAdjustmentEventsMeta metaData = new IndexAdjustmentEventsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	IndexAdjustmentEvents build();
	
	IndexAdjustmentEvents.IndexAdjustmentEventsBuilder toBuilder();
	
	static IndexAdjustmentEvents.IndexAdjustmentEventsBuilder builder() {
		return new IndexAdjustmentEvents.IndexAdjustmentEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends IndexAdjustmentEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends IndexAdjustmentEvents> getType() {
		return IndexAdjustmentEvents.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface IndexAdjustmentEventsBuilder extends IndexAdjustmentEvents, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		IndexAdjustmentEvents.IndexAdjustmentEventsBuilder prune();
	}

	/*********************** Immutable Implementation of IndexAdjustmentEvents  ***********************/
	class IndexAdjustmentEventsImpl implements IndexAdjustmentEvents {
		
		protected IndexAdjustmentEventsImpl(IndexAdjustmentEvents.IndexAdjustmentEventsBuilder builder) {
		}
		
		@Override
		public IndexAdjustmentEvents build() {
			return this;
		}
		
		@Override
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder toBuilder() {
			IndexAdjustmentEvents.IndexAdjustmentEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(IndexAdjustmentEvents.IndexAdjustmentEventsBuilder builder) {
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
			return "IndexAdjustmentEvents {" +
			'}';
		}
	}

	/*********************** Builder Implementation of IndexAdjustmentEvents  ***********************/
	class IndexAdjustmentEventsBuilderImpl implements IndexAdjustmentEvents.IndexAdjustmentEventsBuilder {
	
	
		public IndexAdjustmentEventsBuilderImpl() {
		}
	
	
		
		@Override
		public IndexAdjustmentEvents build() {
			return new IndexAdjustmentEvents.IndexAdjustmentEventsImpl(this);
		}
		
		@Override
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			IndexAdjustmentEvents.IndexAdjustmentEventsBuilder o = (IndexAdjustmentEvents.IndexAdjustmentEventsBuilder) other;
			
			
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
			return "IndexAdjustmentEventsBuilder {" +
			'}';
		}
	}
}
