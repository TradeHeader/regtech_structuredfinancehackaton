package cdm.event.workflow;

import cdm.event.workflow.Workflow;
import cdm.event.workflow.Workflow.WorkflowBuilder;
import cdm.event.workflow.Workflow.WorkflowBuilderImpl;
import cdm.event.workflow.Workflow.WorkflowImpl;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.meta.WorkflowMeta;
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
 * A collection of workflow steps which together makeup an entire workflow sequence.
 * @version ${project.version}
 */
@RosettaDataType(value="Workflow", builder=Workflow.WorkflowBuilderImpl.class, version="${project.version}")
public interface Workflow extends RosettaModelObject {

	WorkflowMeta metaData = new WorkflowMeta();

	/*********************** Getter Methods  ***********************/
	List<? extends WorkflowStep> getSteps();

	/*********************** Build Methods  ***********************/
	Workflow build();
	
	Workflow.WorkflowBuilder toBuilder();
	
	static Workflow.WorkflowBuilder builder() {
		return new Workflow.WorkflowBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Workflow> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Workflow> getType() {
		return Workflow.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("steps"), processor, WorkflowStep.class, getSteps());
	}
	

	/*********************** Builder Interface  ***********************/
	interface WorkflowBuilder extends Workflow, RosettaModelObjectBuilder {
		WorkflowStep.WorkflowStepBuilder getOrCreateSteps(int _index);
		List<? extends WorkflowStep.WorkflowStepBuilder> getSteps();
		Workflow.WorkflowBuilder addSteps(WorkflowStep steps0);
		Workflow.WorkflowBuilder addSteps(WorkflowStep steps1, int _idx);
		Workflow.WorkflowBuilder addSteps(List<? extends WorkflowStep> steps2);
		Workflow.WorkflowBuilder setSteps(List<? extends WorkflowStep> steps3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("steps"), processor, WorkflowStep.WorkflowStepBuilder.class, getSteps());
		}
		

		Workflow.WorkflowBuilder prune();
	}

	/*********************** Immutable Implementation of Workflow  ***********************/
	class WorkflowImpl implements Workflow {
		private final List<? extends WorkflowStep> steps;
		
		protected WorkflowImpl(Workflow.WorkflowBuilder builder) {
			this.steps = ofNullable(builder.getSteps()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("steps")
		public List<? extends WorkflowStep> getSteps() {
			return steps;
		}
		
		@Override
		public Workflow build() {
			return this;
		}
		
		@Override
		public Workflow.WorkflowBuilder toBuilder() {
			Workflow.WorkflowBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Workflow.WorkflowBuilder builder) {
			ofNullable(getSteps()).ifPresent(builder::setSteps);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Workflow _that = getType().cast(o);
		
			if (!ListEquals.listEquals(steps, _that.getSteps())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (steps != null ? steps.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Workflow {" +
				"steps=" + this.steps +
			'}';
		}
	}

	/*********************** Builder Implementation of Workflow  ***********************/
	class WorkflowBuilderImpl implements Workflow.WorkflowBuilder {
	
		protected List<WorkflowStep.WorkflowStepBuilder> steps = new ArrayList<>();
	
		public WorkflowBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("steps")
		public List<? extends WorkflowStep.WorkflowStepBuilder> getSteps() {
			return steps;
		}
		
		public WorkflowStep.WorkflowStepBuilder getOrCreateSteps(int _index) {
		
			if (steps==null) {
				this.steps = new ArrayList<>();
			}
			WorkflowStep.WorkflowStepBuilder result;
			return getIndex(steps, _index, () -> {
						WorkflowStep.WorkflowStepBuilder newSteps = WorkflowStep.builder();
						return newSteps;
					});
		}
		
	
		@Override
		public Workflow.WorkflowBuilder addSteps(WorkflowStep steps) {
			if (steps!=null) this.steps.add(steps.toBuilder());
			return this;
		}
		
		@Override
		public Workflow.WorkflowBuilder addSteps(WorkflowStep steps, int _idx) {
			getIndex(this.steps, _idx, () -> steps.toBuilder());
			return this;
		}
		@Override 
		public Workflow.WorkflowBuilder addSteps(List<? extends WorkflowStep> stepss) {
			if (stepss != null) {
				for (WorkflowStep toAdd : stepss) {
					this.steps.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("steps")
		public Workflow.WorkflowBuilder setSteps(List<? extends WorkflowStep> stepss) {
			if (stepss == null)  {
				this.steps = new ArrayList<>();
			}
			else {
				this.steps = stepss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public Workflow build() {
			return new Workflow.WorkflowImpl(this);
		}
		
		@Override
		public Workflow.WorkflowBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Workflow.WorkflowBuilder prune() {
			steps = steps.stream().filter(b->b!=null).<WorkflowStep.WorkflowStepBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSteps()!=null && getSteps().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Workflow.WorkflowBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Workflow.WorkflowBuilder o = (Workflow.WorkflowBuilder) other;
			
			merger.mergeRosetta(getSteps(), o.getSteps(), this::getOrCreateSteps);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Workflow _that = getType().cast(o);
		
			if (!ListEquals.listEquals(steps, _that.getSteps())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (steps != null ? steps.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "WorkflowBuilder {" +
				"steps=" + this.steps +
			'}';
		}
	}
}
