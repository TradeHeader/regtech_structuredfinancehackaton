package cdm.event.workflow;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
import cdm.event.workflow.CustomisedWorkflow;
import cdm.event.workflow.PartyCustomisedWorkflow;
import cdm.event.workflow.PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder;
import cdm.event.workflow.PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilderImpl;
import cdm.event.workflow.PartyCustomisedWorkflow.PartyCustomisedWorkflowImpl;
import cdm.event.workflow.meta.PartyCustomisedWorkflowMeta;
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
 * A class to specify a party-related, non-standardized data in a generic form.
 * @version ${project.version}
 */
@RosettaDataType(value="PartyCustomisedWorkflow", builder=PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilderImpl.class, version="${project.version}")
public interface PartyCustomisedWorkflow extends RosettaModelObject {

	PartyCustomisedWorkflowMeta metaData = new PartyCustomisedWorkflowMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Reference to the party to which the workflow pertains to.
	 */
	ReferenceWithMetaParty getPartyReference();
	/**
	 * The party name to which the workflow pertains to.
	 */
	String getPartyName();
	/**
	 * Non-standardized data in a generic form.
	 */
	List<? extends CustomisedWorkflow> getCustomisedWorkflow();

	/*********************** Build Methods  ***********************/
	PartyCustomisedWorkflow build();
	
	PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder toBuilder();
	
	static PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder builder() {
		return new PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartyCustomisedWorkflow> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartyCustomisedWorkflow> getType() {
		return PartyCustomisedWorkflow.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
		processor.processBasic(path.newSubPath("partyName"), String.class, getPartyName(), this);
		processRosetta(path.newSubPath("customisedWorkflow"), processor, CustomisedWorkflow.class, getCustomisedWorkflow());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyCustomisedWorkflowBuilder extends PartyCustomisedWorkflow, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		CustomisedWorkflow.CustomisedWorkflowBuilder getOrCreateCustomisedWorkflow(int _index);
		List<? extends CustomisedWorkflow.CustomisedWorkflowBuilder> getCustomisedWorkflow();
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyReference(ReferenceWithMetaParty partyReference0);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyReferenceValue(Party partyReference1);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyName(String partyName);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(CustomisedWorkflow customisedWorkflow0);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(CustomisedWorkflow customisedWorkflow1, int _idx);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(List<? extends CustomisedWorkflow> customisedWorkflow2);
		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setCustomisedWorkflow(List<? extends CustomisedWorkflow> customisedWorkflow3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
			processor.processBasic(path.newSubPath("partyName"), String.class, getPartyName(), this);
			processRosetta(path.newSubPath("customisedWorkflow"), processor, CustomisedWorkflow.CustomisedWorkflowBuilder.class, getCustomisedWorkflow());
		}
		

		PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder prune();
	}

	/*********************** Immutable Implementation of PartyCustomisedWorkflow  ***********************/
	class PartyCustomisedWorkflowImpl implements PartyCustomisedWorkflow {
		private final ReferenceWithMetaParty partyReference;
		private final String partyName;
		private final List<? extends CustomisedWorkflow> customisedWorkflow;
		
		protected PartyCustomisedWorkflowImpl(PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder builder) {
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
			this.partyName = builder.getPartyName();
			this.customisedWorkflow = ofNullable(builder.getCustomisedWorkflow()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		@RosettaAttribute("partyName")
		public String getPartyName() {
			return partyName;
		}
		
		@Override
		@RosettaAttribute("customisedWorkflow")
		public List<? extends CustomisedWorkflow> getCustomisedWorkflow() {
			return customisedWorkflow;
		}
		
		@Override
		public PartyCustomisedWorkflow build() {
			return this;
		}
		
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder toBuilder() {
			PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder builder) {
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
			ofNullable(getPartyName()).ifPresent(builder::setPartyName);
			ofNullable(getCustomisedWorkflow()).ifPresent(builder::setCustomisedWorkflow);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyCustomisedWorkflow _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(partyName, _that.getPartyName())) return false;
			if (!ListEquals.listEquals(customisedWorkflow, _that.getCustomisedWorkflow())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (partyName != null ? partyName.hashCode() : 0);
			_result = 31 * _result + (customisedWorkflow != null ? customisedWorkflow.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyCustomisedWorkflow {" +
				"partyReference=" + this.partyReference + ", " +
				"partyName=" + this.partyName + ", " +
				"customisedWorkflow=" + this.customisedWorkflow +
			'}';
		}
	}

	/*********************** Builder Implementation of PartyCustomisedWorkflow  ***********************/
	class PartyCustomisedWorkflowBuilderImpl implements PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
		protected String partyName;
		protected List<CustomisedWorkflow.CustomisedWorkflowBuilder> customisedWorkflow = new ArrayList<>();
	
		public PartyCustomisedWorkflowBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference() {
			return partyReference;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (partyReference!=null) {
				result = partyReference;
			}
			else {
				result = partyReference = ReferenceWithMetaParty.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("partyName")
		public String getPartyName() {
			return partyName;
		}
		
		@Override
		@RosettaAttribute("customisedWorkflow")
		public List<? extends CustomisedWorkflow.CustomisedWorkflowBuilder> getCustomisedWorkflow() {
			return customisedWorkflow;
		}
		
		public CustomisedWorkflow.CustomisedWorkflowBuilder getOrCreateCustomisedWorkflow(int _index) {
		
			if (customisedWorkflow==null) {
				this.customisedWorkflow = new ArrayList<>();
			}
			CustomisedWorkflow.CustomisedWorkflowBuilder result;
			return getIndex(customisedWorkflow, _index, () -> {
						CustomisedWorkflow.CustomisedWorkflowBuilder newCustomisedWorkflow = CustomisedWorkflow.builder();
						return newCustomisedWorkflow;
					});
		}
		
	
		@Override
		@RosettaAttribute("partyReference")
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyReference(ReferenceWithMetaParty partyReference) {
			this.partyReference = partyReference==null?null:partyReference.toBuilder();
			return this;
		}
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyReferenceValue(Party partyReference) {
			this.getOrCreatePartyReference().setValue(partyReference);
			return this;
		}
		@Override
		@RosettaAttribute("partyName")
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setPartyName(String partyName) {
			this.partyName = partyName==null?null:partyName;
			return this;
		}
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(CustomisedWorkflow customisedWorkflow) {
			if (customisedWorkflow!=null) this.customisedWorkflow.add(customisedWorkflow.toBuilder());
			return this;
		}
		
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(CustomisedWorkflow customisedWorkflow, int _idx) {
			getIndex(this.customisedWorkflow, _idx, () -> customisedWorkflow.toBuilder());
			return this;
		}
		@Override 
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder addCustomisedWorkflow(List<? extends CustomisedWorkflow> customisedWorkflows) {
			if (customisedWorkflows != null) {
				for (CustomisedWorkflow toAdd : customisedWorkflows) {
					this.customisedWorkflow.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("customisedWorkflow")
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder setCustomisedWorkflow(List<? extends CustomisedWorkflow> customisedWorkflows) {
			if (customisedWorkflows == null)  {
				this.customisedWorkflow = new ArrayList<>();
			}
			else {
				this.customisedWorkflow = customisedWorkflows.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public PartyCustomisedWorkflow build() {
			return new PartyCustomisedWorkflow.PartyCustomisedWorkflowImpl(this);
		}
		
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			customisedWorkflow = customisedWorkflow.stream().filter(b->b!=null).<CustomisedWorkflow.CustomisedWorkflowBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			if (getPartyName()!=null) return true;
			if (getCustomisedWorkflow()!=null && getCustomisedWorkflow().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder o = (PartyCustomisedWorkflow.PartyCustomisedWorkflowBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			merger.mergeRosetta(getCustomisedWorkflow(), o.getCustomisedWorkflow(), this::getOrCreateCustomisedWorkflow);
			
			merger.mergeBasic(getPartyName(), o.getPartyName(), this::setPartyName);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyCustomisedWorkflow _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(partyName, _that.getPartyName())) return false;
			if (!ListEquals.listEquals(customisedWorkflow, _that.getCustomisedWorkflow())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (partyName != null ? partyName.hashCode() : 0);
			_result = 31 * _result + (customisedWorkflow != null ? customisedWorkflow.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyCustomisedWorkflowBuilder {" +
				"partyReference=" + this.partyReference + ", " +
				"partyName=" + this.partyName + ", " +
				"customisedWorkflow=" + this.customisedWorkflow +
			'}';
		}
	}
}
