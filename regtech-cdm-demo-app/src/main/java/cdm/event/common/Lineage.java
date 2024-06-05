package cdm.event.common;

import cdm.event.common.Lineage;
import cdm.event.common.Lineage.LineageBuilder;
import cdm.event.common.Lineage.LineageBuilderImpl;
import cdm.event.common.Lineage.LineageImpl;
import cdm.event.common.Trade;
import cdm.event.common.meta.LineageMeta;
import cdm.event.common.metafields.ReferenceWithMetaTrade;
import cdm.event.common.metafields.ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder;
import cdm.event.position.PortfolioState;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder;
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
 * A class to provide lineage information across lifecycle events through a pointer or set of pointers into the event(s), contract(s) and, possibly, payout components that the event is dependent on or relates to. As an example, if an contractFormation event is corrected, the correction event will have a lineage into the initial event, which takes the form of a globalKey into that initial contract formation event. Two referencing mechanisms are provided as part of the CDM: either the globalKey, which corresponds to the hash value of the CDM class which is referred to, or a reference qualifier which is meant to provide support for the ingestion of xml documents with id/href mechanisms. The CDM recommends the use of the globalKey and provides a default implementation which is accessible in the generated code through org.isda.cdm.globalKey.GlobalKeyHashCalculator. If implementers want to use an alternative hashing mechanism, the API in which they need to plug it is com.rosetta.model.lib.HashFunction.
 * @version ${project.version}
 */
@RosettaDataType(value="Lineage", builder=Lineage.LineageBuilderImpl.class, version="${project.version}")
public interface Lineage extends RosettaModelObject {

	LineageMeta metaData = new LineageMeta();

	/*********************** Getter Methods  ***********************/
	List<? extends ReferenceWithMetaTrade> getTradeReference();
	/**
	 * The reference to the instantiation of an Event object, either through a globalKey or an xml-derived id/href mechanism. The definition associated to the Lineage class provides more details with respect to those referencing approaches, their expected usage and available implementation.
	 */
	List<? extends ReferenceWithMetaWorkflowStep> getEventReference();
	/**
	 * The reference to the previous state of a Portfolio, in a chain of Events leading up to a build of that Portfolio as the holding of Product(s) in specific Quantity(ies). As part of the PortfolioState object, a pointer to the previous PortfolioState is provided through a Lineage object, together with pointer(s) to the Event or set of Events leading up to the current (new) state.
	 */
	List<? extends ReferenceWithMetaPortfolioState> getPortfolioStateReference();

	/*********************** Build Methods  ***********************/
	Lineage build();
	
	Lineage.LineageBuilder toBuilder();
	
	static Lineage.LineageBuilder builder() {
		return new Lineage.LineageBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Lineage> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Lineage> getType() {
		return Lineage.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTrade.class, getTradeReference());
		processRosetta(path.newSubPath("eventReference"), processor, ReferenceWithMetaWorkflowStep.class, getEventReference());
		processRosetta(path.newSubPath("portfolioStateReference"), processor, ReferenceWithMetaPortfolioState.class, getPortfolioStateReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LineageBuilder extends Lineage, RosettaModelObjectBuilder {
		ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder getOrCreateTradeReference(int _index);
		List<? extends ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder> getTradeReference();
		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder getOrCreateEventReference(int _index);
		List<? extends ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder> getEventReference();
		ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getOrCreatePortfolioStateReference(int _index);
		List<? extends ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder> getPortfolioStateReference();
		Lineage.LineageBuilder addTradeReference(ReferenceWithMetaTrade tradeReference0);
		Lineage.LineageBuilder addTradeReference(ReferenceWithMetaTrade tradeReference1, int _idx);
		Lineage.LineageBuilder addTradeReferenceValue(Trade tradeReference2);
		Lineage.LineageBuilder addTradeReferenceValue(Trade tradeReference3, int _idx);
		Lineage.LineageBuilder addTradeReference(List<? extends ReferenceWithMetaTrade> tradeReference4);
		Lineage.LineageBuilder setTradeReference(List<? extends ReferenceWithMetaTrade> tradeReference5);
		Lineage.LineageBuilder addTradeReferenceValue(List<? extends Trade> tradeReference6);
		Lineage.LineageBuilder setTradeReferenceValue(List<? extends Trade> tradeReference7);
		Lineage.LineageBuilder addEventReference(ReferenceWithMetaWorkflowStep eventReference0);
		Lineage.LineageBuilder addEventReference(ReferenceWithMetaWorkflowStep eventReference1, int _idx);
		Lineage.LineageBuilder addEventReferenceValue(WorkflowStep eventReference2);
		Lineage.LineageBuilder addEventReferenceValue(WorkflowStep eventReference3, int _idx);
		Lineage.LineageBuilder addEventReference(List<? extends ReferenceWithMetaWorkflowStep> eventReference4);
		Lineage.LineageBuilder setEventReference(List<? extends ReferenceWithMetaWorkflowStep> eventReference5);
		Lineage.LineageBuilder addEventReferenceValue(List<? extends WorkflowStep> eventReference6);
		Lineage.LineageBuilder setEventReferenceValue(List<? extends WorkflowStep> eventReference7);
		Lineage.LineageBuilder addPortfolioStateReference(ReferenceWithMetaPortfolioState portfolioStateReference0);
		Lineage.LineageBuilder addPortfolioStateReference(ReferenceWithMetaPortfolioState portfolioStateReference1, int _idx);
		Lineage.LineageBuilder addPortfolioStateReferenceValue(PortfolioState portfolioStateReference2);
		Lineage.LineageBuilder addPortfolioStateReferenceValue(PortfolioState portfolioStateReference3, int _idx);
		Lineage.LineageBuilder addPortfolioStateReference(List<? extends ReferenceWithMetaPortfolioState> portfolioStateReference4);
		Lineage.LineageBuilder setPortfolioStateReference(List<? extends ReferenceWithMetaPortfolioState> portfolioStateReference5);
		Lineage.LineageBuilder addPortfolioStateReferenceValue(List<? extends PortfolioState> portfolioStateReference6);
		Lineage.LineageBuilder setPortfolioStateReferenceValue(List<? extends PortfolioState> portfolioStateReference7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder.class, getTradeReference());
			processRosetta(path.newSubPath("eventReference"), processor, ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder.class, getEventReference());
			processRosetta(path.newSubPath("portfolioStateReference"), processor, ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder.class, getPortfolioStateReference());
		}
		

		Lineage.LineageBuilder prune();
	}

	/*********************** Immutable Implementation of Lineage  ***********************/
	class LineageImpl implements Lineage {
		private final List<? extends ReferenceWithMetaTrade> tradeReference;
		private final List<? extends ReferenceWithMetaWorkflowStep> eventReference;
		private final List<? extends ReferenceWithMetaPortfolioState> portfolioStateReference;
		
		protected LineageImpl(Lineage.LineageBuilder builder) {
			this.tradeReference = ofNullable(builder.getTradeReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.eventReference = ofNullable(builder.getEventReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.portfolioStateReference = ofNullable(builder.getPortfolioStateReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("tradeReference")
		public List<? extends ReferenceWithMetaTrade> getTradeReference() {
			return tradeReference;
		}
		
		@Override
		@RosettaAttribute("eventReference")
		public List<? extends ReferenceWithMetaWorkflowStep> getEventReference() {
			return eventReference;
		}
		
		@Override
		@RosettaAttribute("portfolioStateReference")
		public List<? extends ReferenceWithMetaPortfolioState> getPortfolioStateReference() {
			return portfolioStateReference;
		}
		
		@Override
		public Lineage build() {
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder toBuilder() {
			Lineage.LineageBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Lineage.LineageBuilder builder) {
			ofNullable(getTradeReference()).ifPresent(builder::setTradeReference);
			ofNullable(getEventReference()).ifPresent(builder::setEventReference);
			ofNullable(getPortfolioStateReference()).ifPresent(builder::setPortfolioStateReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Lineage _that = getType().cast(o);
		
			if (!ListEquals.listEquals(tradeReference, _that.getTradeReference())) return false;
			if (!ListEquals.listEquals(eventReference, _that.getEventReference())) return false;
			if (!ListEquals.listEquals(portfolioStateReference, _that.getPortfolioStateReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			_result = 31 * _result + (eventReference != null ? eventReference.hashCode() : 0);
			_result = 31 * _result + (portfolioStateReference != null ? portfolioStateReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Lineage {" +
				"tradeReference=" + this.tradeReference + ", " +
				"eventReference=" + this.eventReference + ", " +
				"portfolioStateReference=" + this.portfolioStateReference +
			'}';
		}
	}

	/*********************** Builder Implementation of Lineage  ***********************/
	class LineageBuilderImpl implements Lineage.LineageBuilder {
	
		protected List<ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder> tradeReference = new ArrayList<>();
		protected List<ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder> eventReference = new ArrayList<>();
		protected List<ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder> portfolioStateReference = new ArrayList<>();
	
		public LineageBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tradeReference")
		public List<? extends ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder> getTradeReference() {
			return tradeReference;
		}
		
		public ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder getOrCreateTradeReference(int _index) {
		
			if (tradeReference==null) {
				this.tradeReference = new ArrayList<>();
			}
			ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder result;
			return getIndex(tradeReference, _index, () -> {
						ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder newTradeReference = ReferenceWithMetaTrade.builder();
						return newTradeReference;
					});
		}
		
		@Override
		@RosettaAttribute("eventReference")
		public List<? extends ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder> getEventReference() {
			return eventReference;
		}
		
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder getOrCreateEventReference(int _index) {
		
			if (eventReference==null) {
				this.eventReference = new ArrayList<>();
			}
			ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder result;
			return getIndex(eventReference, _index, () -> {
						ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder newEventReference = ReferenceWithMetaWorkflowStep.builder();
						return newEventReference;
					});
		}
		
		@Override
		@RosettaAttribute("portfolioStateReference")
		public List<? extends ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder> getPortfolioStateReference() {
			return portfolioStateReference;
		}
		
		public ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getOrCreatePortfolioStateReference(int _index) {
		
			if (portfolioStateReference==null) {
				this.portfolioStateReference = new ArrayList<>();
			}
			ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder result;
			return getIndex(portfolioStateReference, _index, () -> {
						ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder newPortfolioStateReference = ReferenceWithMetaPortfolioState.builder();
						return newPortfolioStateReference;
					});
		}
		
	
		@Override
		public Lineage.LineageBuilder addTradeReference(ReferenceWithMetaTrade tradeReference) {
			if (tradeReference!=null) this.tradeReference.add(tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addTradeReference(ReferenceWithMetaTrade tradeReference, int _idx) {
			getIndex(this.tradeReference, _idx, () -> tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addTradeReferenceValue(Trade tradeReference) {
			this.getOrCreateTradeReference(-1).setValue(tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addTradeReferenceValue(Trade tradeReference, int _idx) {
			this.getOrCreateTradeReference(_idx).setValue(tradeReference.toBuilder());
			return this;
		}
		@Override 
		public Lineage.LineageBuilder addTradeReference(List<? extends ReferenceWithMetaTrade> tradeReferences) {
			if (tradeReferences != null) {
				for (ReferenceWithMetaTrade toAdd : tradeReferences) {
					this.tradeReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("tradeReference")
		public Lineage.LineageBuilder setTradeReference(List<? extends ReferenceWithMetaTrade> tradeReferences) {
			if (tradeReferences == null)  {
				this.tradeReference = new ArrayList<>();
			}
			else {
				this.tradeReference = tradeReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addTradeReferenceValue(List<? extends Trade> tradeReferences) {
			if (tradeReferences != null) {
				for (Trade toAdd : tradeReferences) {
					this.addTradeReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder setTradeReferenceValue(List<? extends Trade> tradeReferences) {
			this.tradeReference.clear();
			if (tradeReferences!=null) {
				tradeReferences.forEach(this::addTradeReferenceValue);
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addEventReference(ReferenceWithMetaWorkflowStep eventReference) {
			if (eventReference!=null) this.eventReference.add(eventReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addEventReference(ReferenceWithMetaWorkflowStep eventReference, int _idx) {
			getIndex(this.eventReference, _idx, () -> eventReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addEventReferenceValue(WorkflowStep eventReference) {
			this.getOrCreateEventReference(-1).setValue(eventReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addEventReferenceValue(WorkflowStep eventReference, int _idx) {
			this.getOrCreateEventReference(_idx).setValue(eventReference.toBuilder());
			return this;
		}
		@Override 
		public Lineage.LineageBuilder addEventReference(List<? extends ReferenceWithMetaWorkflowStep> eventReferences) {
			if (eventReferences != null) {
				for (ReferenceWithMetaWorkflowStep toAdd : eventReferences) {
					this.eventReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("eventReference")
		public Lineage.LineageBuilder setEventReference(List<? extends ReferenceWithMetaWorkflowStep> eventReferences) {
			if (eventReferences == null)  {
				this.eventReference = new ArrayList<>();
			}
			else {
				this.eventReference = eventReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addEventReferenceValue(List<? extends WorkflowStep> eventReferences) {
			if (eventReferences != null) {
				for (WorkflowStep toAdd : eventReferences) {
					this.addEventReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder setEventReferenceValue(List<? extends WorkflowStep> eventReferences) {
			this.eventReference.clear();
			if (eventReferences!=null) {
				eventReferences.forEach(this::addEventReferenceValue);
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addPortfolioStateReference(ReferenceWithMetaPortfolioState portfolioStateReference) {
			if (portfolioStateReference!=null) this.portfolioStateReference.add(portfolioStateReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addPortfolioStateReference(ReferenceWithMetaPortfolioState portfolioStateReference, int _idx) {
			getIndex(this.portfolioStateReference, _idx, () -> portfolioStateReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addPortfolioStateReferenceValue(PortfolioState portfolioStateReference) {
			this.getOrCreatePortfolioStateReference(-1).setValue(portfolioStateReference.toBuilder());
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addPortfolioStateReferenceValue(PortfolioState portfolioStateReference, int _idx) {
			this.getOrCreatePortfolioStateReference(_idx).setValue(portfolioStateReference.toBuilder());
			return this;
		}
		@Override 
		public Lineage.LineageBuilder addPortfolioStateReference(List<? extends ReferenceWithMetaPortfolioState> portfolioStateReferences) {
			if (portfolioStateReferences != null) {
				for (ReferenceWithMetaPortfolioState toAdd : portfolioStateReferences) {
					this.portfolioStateReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("portfolioStateReference")
		public Lineage.LineageBuilder setPortfolioStateReference(List<? extends ReferenceWithMetaPortfolioState> portfolioStateReferences) {
			if (portfolioStateReferences == null)  {
				this.portfolioStateReference = new ArrayList<>();
			}
			else {
				this.portfolioStateReference = portfolioStateReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder addPortfolioStateReferenceValue(List<? extends PortfolioState> portfolioStateReferences) {
			if (portfolioStateReferences != null) {
				for (PortfolioState toAdd : portfolioStateReferences) {
					this.addPortfolioStateReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Lineage.LineageBuilder setPortfolioStateReferenceValue(List<? extends PortfolioState> portfolioStateReferences) {
			this.portfolioStateReference.clear();
			if (portfolioStateReferences!=null) {
				portfolioStateReferences.forEach(this::addPortfolioStateReferenceValue);
			}
			return this;
		}
		
		
		@Override
		public Lineage build() {
			return new Lineage.LineageImpl(this);
		}
		
		@Override
		public Lineage.LineageBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Lineage.LineageBuilder prune() {
			tradeReference = tradeReference.stream().filter(b->b!=null).<ReferenceWithMetaTrade.ReferenceWithMetaTradeBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			eventReference = eventReference.stream().filter(b->b!=null).<ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			portfolioStateReference = portfolioStateReference.stream().filter(b->b!=null).<ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTradeReference()!=null && getTradeReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getEventReference()!=null && getEventReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPortfolioStateReference()!=null && getPortfolioStateReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Lineage.LineageBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Lineage.LineageBuilder o = (Lineage.LineageBuilder) other;
			
			merger.mergeRosetta(getTradeReference(), o.getTradeReference(), this::getOrCreateTradeReference);
			merger.mergeRosetta(getEventReference(), o.getEventReference(), this::getOrCreateEventReference);
			merger.mergeRosetta(getPortfolioStateReference(), o.getPortfolioStateReference(), this::getOrCreatePortfolioStateReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Lineage _that = getType().cast(o);
		
			if (!ListEquals.listEquals(tradeReference, _that.getTradeReference())) return false;
			if (!ListEquals.listEquals(eventReference, _that.getEventReference())) return false;
			if (!ListEquals.listEquals(portfolioStateReference, _that.getPortfolioStateReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			_result = 31 * _result + (eventReference != null ? eventReference.hashCode() : 0);
			_result = 31 * _result + (portfolioStateReference != null ? portfolioStateReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LineageBuilder {" +
				"tradeReference=" + this.tradeReference + ", " +
				"eventReference=" + this.eventReference + ", " +
				"portfolioStateReference=" + this.portfolioStateReference +
			'}';
		}
	}
}
