package cdm.event.workflow;

import cdm.event.workflow.PartyCustomisedWorkflow;
import cdm.event.workflow.WarehouseIdentityEnum;
import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.WorkflowState.WorkflowStateBuilder;
import cdm.event.workflow.WorkflowState.WorkflowStateBuilderImpl;
import cdm.event.workflow.WorkflowState.WorkflowStateImpl;
import cdm.event.workflow.WorkflowStatusEnum;
import cdm.event.workflow.meta.WorkflowStateMeta;
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
 * A class to specify workflow information, which is conceptually applicable to all lifecycle events.
 * @version ${project.version}
 */
@RosettaDataType(value="WorkflowState", builder=WorkflowState.WorkflowStateBuilderImpl.class, version="${project.version}")
public interface WorkflowState extends RosettaModelObject {

	WorkflowStateMeta metaData = new WorkflowStateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The workflow status indicator, e.g. Accepted, Rejected, ...
	 */
	WorkflowStatusEnum getWorkflowStatus();
	/**
	 * A comment field to be associated with the workflow, e.g. to specify why a transaction event was rejected by a party.
	 */
	String getComment();
	/**
	 * Workflow data that is specific to certain market participants and is expressed as part of the CDM in a very generic manner, which can be party-specific. The initial use cases have been derived from the CME clearing and the DTCC TIW submissions.
	 */
	List<? extends PartyCustomisedWorkflow> getPartyCustomisedWorkflow();
	/**
	 * The identity of the warehouse, if any, that is executing that workflow step.
	 */
	WarehouseIdentityEnum getWarehouseIdentity();

	/*********************** Build Methods  ***********************/
	WorkflowState build();
	
	WorkflowState.WorkflowStateBuilder toBuilder();
	
	static WorkflowState.WorkflowStateBuilder builder() {
		return new WorkflowState.WorkflowStateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends WorkflowState> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends WorkflowState> getType() {
		return WorkflowState.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("workflowStatus"), WorkflowStatusEnum.class, getWorkflowStatus(), this);
		processor.processBasic(path.newSubPath("comment"), String.class, getComment(), this);
		processRosetta(path.newSubPath("partyCustomisedWorkflow"), processor, PartyCustomisedWorkflow.class, getPartyCustomisedWorkflow());
		processor.processBasic(path.newSubPath("warehouseIdentity"), WarehouseIdentityEnum.class, getWarehouseIdentity(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface WorkflowStateBuilder extends WorkflowState, RosettaModelObjectBuilder {
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder getOrCreatePartyCustomisedWorkflow(int _index);
		List<? extends PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder> getPartyCustomisedWorkflow();
		WorkflowState.WorkflowStateBuilder setWorkflowStatus(WorkflowStatusEnum workflowStatus);
		WorkflowState.WorkflowStateBuilder setComment(String comment);
		WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(PartyCustomisedWorkflow partyCustomisedWorkflow0);
		WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(PartyCustomisedWorkflow partyCustomisedWorkflow1, int _idx);
		WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(List<? extends PartyCustomisedWorkflow> partyCustomisedWorkflow2);
		WorkflowState.WorkflowStateBuilder setPartyCustomisedWorkflow(List<? extends PartyCustomisedWorkflow> partyCustomisedWorkflow3);
		WorkflowState.WorkflowStateBuilder setWarehouseIdentity(WarehouseIdentityEnum warehouseIdentity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("workflowStatus"), WorkflowStatusEnum.class, getWorkflowStatus(), this);
			processor.processBasic(path.newSubPath("comment"), String.class, getComment(), this);
			processRosetta(path.newSubPath("partyCustomisedWorkflow"), processor, PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder.class, getPartyCustomisedWorkflow());
			processor.processBasic(path.newSubPath("warehouseIdentity"), WarehouseIdentityEnum.class, getWarehouseIdentity(), this);
		}
		

		WorkflowState.WorkflowStateBuilder prune();
	}

	/*********************** Immutable Implementation of WorkflowState  ***********************/
	class WorkflowStateImpl implements WorkflowState {
		private final WorkflowStatusEnum workflowStatus;
		private final String comment;
		private final List<? extends PartyCustomisedWorkflow> partyCustomisedWorkflow;
		private final WarehouseIdentityEnum warehouseIdentity;
		
		protected WorkflowStateImpl(WorkflowState.WorkflowStateBuilder builder) {
			this.workflowStatus = builder.getWorkflowStatus();
			this.comment = builder.getComment();
			this.partyCustomisedWorkflow = ofNullable(builder.getPartyCustomisedWorkflow()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.warehouseIdentity = builder.getWarehouseIdentity();
		}
		
		@Override
		@RosettaAttribute("workflowStatus")
		public WorkflowStatusEnum getWorkflowStatus() {
			return workflowStatus;
		}
		
		@Override
		@RosettaAttribute("comment")
		public String getComment() {
			return comment;
		}
		
		@Override
		@RosettaAttribute("partyCustomisedWorkflow")
		public List<? extends PartyCustomisedWorkflow> getPartyCustomisedWorkflow() {
			return partyCustomisedWorkflow;
		}
		
		@Override
		@RosettaAttribute("warehouseIdentity")
		public WarehouseIdentityEnum getWarehouseIdentity() {
			return warehouseIdentity;
		}
		
		@Override
		public WorkflowState build() {
			return this;
		}
		
		@Override
		public WorkflowState.WorkflowStateBuilder toBuilder() {
			WorkflowState.WorkflowStateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(WorkflowState.WorkflowStateBuilder builder) {
			ofNullable(getWorkflowStatus()).ifPresent(builder::setWorkflowStatus);
			ofNullable(getComment()).ifPresent(builder::setComment);
			ofNullable(getPartyCustomisedWorkflow()).ifPresent(builder::setPartyCustomisedWorkflow);
			ofNullable(getWarehouseIdentity()).ifPresent(builder::setWarehouseIdentity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			WorkflowState _that = getType().cast(o);
		
			if (!Objects.equals(workflowStatus, _that.getWorkflowStatus())) return false;
			if (!Objects.equals(comment, _that.getComment())) return false;
			if (!ListEquals.listEquals(partyCustomisedWorkflow, _that.getPartyCustomisedWorkflow())) return false;
			if (!Objects.equals(warehouseIdentity, _that.getWarehouseIdentity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (workflowStatus != null ? workflowStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (comment != null ? comment.hashCode() : 0);
			_result = 31 * _result + (partyCustomisedWorkflow != null ? partyCustomisedWorkflow.hashCode() : 0);
			_result = 31 * _result + (warehouseIdentity != null ? warehouseIdentity.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "WorkflowState {" +
				"workflowStatus=" + this.workflowStatus + ", " +
				"comment=" + this.comment + ", " +
				"partyCustomisedWorkflow=" + this.partyCustomisedWorkflow + ", " +
				"warehouseIdentity=" + this.warehouseIdentity +
			'}';
		}
	}

	/*********************** Builder Implementation of WorkflowState  ***********************/
	class WorkflowStateBuilderImpl implements WorkflowState.WorkflowStateBuilder {
	
		protected WorkflowStatusEnum workflowStatus;
		protected String comment;
		protected List<PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder> partyCustomisedWorkflow = new ArrayList<>();
		protected WarehouseIdentityEnum warehouseIdentity;
	
		public WorkflowStateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("workflowStatus")
		public WorkflowStatusEnum getWorkflowStatus() {
			return workflowStatus;
		}
		
		@Override
		@RosettaAttribute("comment")
		public String getComment() {
			return comment;
		}
		
		@Override
		@RosettaAttribute("partyCustomisedWorkflow")
		public List<? extends PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder> getPartyCustomisedWorkflow() {
			return partyCustomisedWorkflow;
		}
		
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder getOrCreatePartyCustomisedWorkflow(int _index) {
		
			if (partyCustomisedWorkflow==null) {
				this.partyCustomisedWorkflow = new ArrayList<>();
			}
			PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder result;
			return getIndex(partyCustomisedWorkflow, _index, () -> {
						PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder newPartyCustomisedWorkflow = PartyCustomisedWorkflow.builder();
						return newPartyCustomisedWorkflow;
					});
		}
		
		@Override
		@RosettaAttribute("warehouseIdentity")
		public WarehouseIdentityEnum getWarehouseIdentity() {
			return warehouseIdentity;
		}
		
	
		@Override
		@RosettaAttribute("workflowStatus")
		public WorkflowState.WorkflowStateBuilder setWorkflowStatus(WorkflowStatusEnum workflowStatus) {
			this.workflowStatus = workflowStatus==null?null:workflowStatus;
			return this;
		}
		@Override
		@RosettaAttribute("comment")
		public WorkflowState.WorkflowStateBuilder setComment(String comment) {
			this.comment = comment==null?null:comment;
			return this;
		}
		@Override
		public WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(PartyCustomisedWorkflow partyCustomisedWorkflow) {
			if (partyCustomisedWorkflow!=null) this.partyCustomisedWorkflow.add(partyCustomisedWorkflow.toBuilder());
			return this;
		}
		
		@Override
		public WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(PartyCustomisedWorkflow partyCustomisedWorkflow, int _idx) {
			getIndex(this.partyCustomisedWorkflow, _idx, () -> partyCustomisedWorkflow.toBuilder());
			return this;
		}
		@Override 
		public WorkflowState.WorkflowStateBuilder addPartyCustomisedWorkflow(List<? extends PartyCustomisedWorkflow> partyCustomisedWorkflows) {
			if (partyCustomisedWorkflows != null) {
				for (PartyCustomisedWorkflow toAdd : partyCustomisedWorkflows) {
					this.partyCustomisedWorkflow.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyCustomisedWorkflow")
		public WorkflowState.WorkflowStateBuilder setPartyCustomisedWorkflow(List<? extends PartyCustomisedWorkflow> partyCustomisedWorkflows) {
			if (partyCustomisedWorkflows == null)  {
				this.partyCustomisedWorkflow = new ArrayList<>();
			}
			else {
				this.partyCustomisedWorkflow = partyCustomisedWorkflows.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("warehouseIdentity")
		public WorkflowState.WorkflowStateBuilder setWarehouseIdentity(WarehouseIdentityEnum warehouseIdentity) {
			this.warehouseIdentity = warehouseIdentity==null?null:warehouseIdentity;
			return this;
		}
		
		@Override
		public WorkflowState build() {
			return new WorkflowState.WorkflowStateImpl(this);
		}
		
		@Override
		public WorkflowState.WorkflowStateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public WorkflowState.WorkflowStateBuilder prune() {
			partyCustomisedWorkflow = partyCustomisedWorkflow.stream().filter(b->b!=null).<PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getWorkflowStatus()!=null) return true;
			if (getComment()!=null) return true;
			if (getPartyCustomisedWorkflow()!=null && getPartyCustomisedWorkflow().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getWarehouseIdentity()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public WorkflowState.WorkflowStateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			WorkflowState.WorkflowStateBuilder o = (WorkflowState.WorkflowStateBuilder) other;
			
			merger.mergeRosetta(getPartyCustomisedWorkflow(), o.getPartyCustomisedWorkflow(), this::getOrCreatePartyCustomisedWorkflow);
			
			merger.mergeBasic(getWorkflowStatus(), o.getWorkflowStatus(), this::setWorkflowStatus);
			merger.mergeBasic(getComment(), o.getComment(), this::setComment);
			merger.mergeBasic(getWarehouseIdentity(), o.getWarehouseIdentity(), this::setWarehouseIdentity);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			WorkflowState _that = getType().cast(o);
		
			if (!Objects.equals(workflowStatus, _that.getWorkflowStatus())) return false;
			if (!Objects.equals(comment, _that.getComment())) return false;
			if (!ListEquals.listEquals(partyCustomisedWorkflow, _that.getPartyCustomisedWorkflow())) return false;
			if (!Objects.equals(warehouseIdentity, _that.getWarehouseIdentity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (workflowStatus != null ? workflowStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (comment != null ? comment.hashCode() : 0);
			_result = 31 * _result + (partyCustomisedWorkflow != null ? partyCustomisedWorkflow.hashCode() : 0);
			_result = 31 * _result + (warehouseIdentity != null ? warehouseIdentity.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "WorkflowStateBuilder {" +
				"workflowStatus=" + this.workflowStatus + ", " +
				"comment=" + this.comment + ", " +
				"partyCustomisedWorkflow=" + this.partyCustomisedWorkflow + ", " +
				"warehouseIdentity=" + this.warehouseIdentity +
			'}';
		}
	}
}
