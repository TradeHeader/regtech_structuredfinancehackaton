package cdm.event.workflow.metafields;

import cdm.event.workflow.WorkflowStep;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.Reference;
import com.rosetta.model.lib.meta.ReferenceWithMeta;
import com.rosetta.model.lib.meta.ReferenceWithMeta.ReferenceWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.AttributeMeta;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="ReferenceWithMetaWorkflowStep", builder=ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaWorkflowStep extends RosettaModelObject, ReferenceWithMeta<WorkflowStep> {

	ReferenceWithMetaWorkflowStepMeta metaData = new ReferenceWithMetaWorkflowStepMeta();

	/*********************** Getter Methods  ***********************/
	WorkflowStep getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaWorkflowStep build();
	
	ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder toBuilder();
	
	static ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder builder() {
		return new ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaWorkflowStep> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaWorkflowStep> getType() {
		return ReferenceWithMetaWorkflowStep.class;
	}
	
	@Override
	default Class<WorkflowStep> getValueType() {
		return WorkflowStep.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, WorkflowStep.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaWorkflowStepBuilder extends ReferenceWithMetaWorkflowStep, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<WorkflowStep> {
		WorkflowStep.WorkflowStepBuilder getOrCreateValue();
		WorkflowStep.WorkflowStepBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setValue(WorkflowStep value);
		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setExternalReference(String externalReference);
		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, WorkflowStep.WorkflowStepBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaWorkflowStep  ***********************/
	class ReferenceWithMetaWorkflowStepImpl implements ReferenceWithMetaWorkflowStep {
		private final WorkflowStep value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaWorkflowStepImpl(ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public WorkflowStep getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("globalReference")
		public String getGlobalReference() {
			return globalReference;
		}
		
		@Override
		@RosettaAttribute("externalReference")
		public String getExternalReference() {
			return externalReference;
		}
		
		@Override
		@RosettaAttribute("address")
		public Reference getReference() {
			return reference;
		}
		
		@Override
		public ReferenceWithMetaWorkflowStep build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder toBuilder() {
			ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaWorkflowStep _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(globalReference, _that.getGlobalReference())) return false;
			if (!Objects.equals(externalReference, _that.getExternalReference())) return false;
			if (!Objects.equals(reference, _that.getReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (globalReference != null ? globalReference.hashCode() : 0);
			_result = 31 * _result + (externalReference != null ? externalReference.hashCode() : 0);
			_result = 31 * _result + (reference != null ? reference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceWithMetaWorkflowStep {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaWorkflowStep  ***********************/
	class ReferenceWithMetaWorkflowStepBuilderImpl implements ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder {
	
		protected WorkflowStep.WorkflowStepBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaWorkflowStepBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public WorkflowStep.WorkflowStepBuilder getValue() {
			return value;
		}
		
		@Override
		public WorkflowStep.WorkflowStepBuilder getOrCreateValue() {
			WorkflowStep.WorkflowStepBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = WorkflowStep.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("globalReference")
		public String getGlobalReference() {
			return globalReference;
		}
		
		@Override
		@RosettaAttribute("externalReference")
		public String getExternalReference() {
			return externalReference;
		}
		
		@Override
		@RosettaAttribute("address")
		public Reference.ReferenceBuilder getReference() {
			return reference;
		}
		
		@Override
		public Reference.ReferenceBuilder getOrCreateReference() {
			Reference.ReferenceBuilder result;
			if (reference!=null) {
				result = reference;
			}
			else {
				result = reference = Reference.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("value")
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setValue(WorkflowStep value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaWorkflowStep build() {
			return new ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepImpl(this);
		}
		
		@Override
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (reference!=null && !reference.prune().hasData()) reference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			if (getGlobalReference()!=null) return true;
			if (getExternalReference()!=null) return true;
			if (getReference()!=null && getReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder o = (ReferenceWithMetaWorkflowStep.ReferenceWithMetaWorkflowStepBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getReference(), o.getReference(), this::setReference);
			
			merger.mergeBasic(getGlobalReference(), o.getGlobalReference(), this::setGlobalReference);
			merger.mergeBasic(getExternalReference(), o.getExternalReference(), this::setExternalReference);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaWorkflowStep _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(globalReference, _that.getGlobalReference())) return false;
			if (!Objects.equals(externalReference, _that.getExternalReference())) return false;
			if (!Objects.equals(reference, _that.getReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (globalReference != null ? globalReference.hashCode() : 0);
			_result = 31 * _result + (externalReference != null ? externalReference.hashCode() : 0);
			_result = 31 * _result + (reference != null ? reference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceWithMetaWorkflowStepBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaWorkflowStepMeta extends BasicRosettaMetaData<ReferenceWithMetaWorkflowStep>{

}
