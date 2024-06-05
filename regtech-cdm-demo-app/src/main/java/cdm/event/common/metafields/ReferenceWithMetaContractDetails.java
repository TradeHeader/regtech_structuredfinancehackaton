package cdm.event.common.metafields;

import cdm.event.common.ContractDetails;
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
@RosettaDataType(value="ReferenceWithMetaContractDetails", builder=ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaContractDetails extends RosettaModelObject, ReferenceWithMeta<ContractDetails> {

	ReferenceWithMetaContractDetailsMeta metaData = new ReferenceWithMetaContractDetailsMeta();

	/*********************** Getter Methods  ***********************/
	ContractDetails getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaContractDetails build();
	
	ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder toBuilder();
	
	static ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder builder() {
		return new ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaContractDetails> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaContractDetails> getType() {
		return ReferenceWithMetaContractDetails.class;
	}
	
	@Override
	default Class<ContractDetails> getValueType() {
		return ContractDetails.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, ContractDetails.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaContractDetailsBuilder extends ReferenceWithMetaContractDetails, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<ContractDetails> {
		ContractDetails.ContractDetailsBuilder getOrCreateValue();
		ContractDetails.ContractDetailsBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setValue(ContractDetails value);
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setExternalReference(String externalReference);
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, ContractDetails.ContractDetailsBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaContractDetails  ***********************/
	class ReferenceWithMetaContractDetailsImpl implements ReferenceWithMetaContractDetails {
		private final ContractDetails value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaContractDetailsImpl(ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public ContractDetails getValue() {
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
		public ReferenceWithMetaContractDetails build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder toBuilder() {
			ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaContractDetails _that = getType().cast(o);
		
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
			return "ReferenceWithMetaContractDetails {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaContractDetails  ***********************/
	class ReferenceWithMetaContractDetailsBuilderImpl implements ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder {
	
		protected ContractDetails.ContractDetailsBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaContractDetailsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public ContractDetails.ContractDetailsBuilder getValue() {
			return value;
		}
		
		@Override
		public ContractDetails.ContractDetailsBuilder getOrCreateValue() {
			ContractDetails.ContractDetailsBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = ContractDetails.builder();
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
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setValue(ContractDetails value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaContractDetails build() {
			return new ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsImpl(this);
		}
		
		@Override
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder prune() {
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
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder o = (ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder) other;
			
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
		
			ReferenceWithMetaContractDetails _that = getType().cast(o);
		
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
			return "ReferenceWithMetaContractDetailsBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaContractDetailsMeta extends BasicRosettaMetaData<ReferenceWithMetaContractDetails>{

}
