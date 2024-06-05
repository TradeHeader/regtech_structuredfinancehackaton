package cdm.product.asset.metafields;

import cdm.product.asset.ProtectionTerms;
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
@RosettaDataType(value="ReferenceWithMetaProtectionTerms", builder=ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaProtectionTerms extends RosettaModelObject, ReferenceWithMeta<ProtectionTerms> {

	ReferenceWithMetaProtectionTermsMeta metaData = new ReferenceWithMetaProtectionTermsMeta();

	/*********************** Getter Methods  ***********************/
	ProtectionTerms getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaProtectionTerms build();
	
	ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder toBuilder();
	
	static ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder builder() {
		return new ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaProtectionTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaProtectionTerms> getType() {
		return ReferenceWithMetaProtectionTerms.class;
	}
	
	@Override
	default Class<ProtectionTerms> getValueType() {
		return ProtectionTerms.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, ProtectionTerms.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaProtectionTermsBuilder extends ReferenceWithMetaProtectionTerms, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<ProtectionTerms> {
		ProtectionTerms.ProtectionTermsBuilder getOrCreateValue();
		ProtectionTerms.ProtectionTermsBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setValue(ProtectionTerms value);
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setExternalReference(String externalReference);
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, ProtectionTerms.ProtectionTermsBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaProtectionTerms  ***********************/
	class ReferenceWithMetaProtectionTermsImpl implements ReferenceWithMetaProtectionTerms {
		private final ProtectionTerms value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaProtectionTermsImpl(ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public ProtectionTerms getValue() {
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
		public ReferenceWithMetaProtectionTerms build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder toBuilder() {
			ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaProtectionTerms _that = getType().cast(o);
		
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
			return "ReferenceWithMetaProtectionTerms {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaProtectionTerms  ***********************/
	class ReferenceWithMetaProtectionTermsBuilderImpl implements ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder {
	
		protected ProtectionTerms.ProtectionTermsBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaProtectionTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public ProtectionTerms.ProtectionTermsBuilder getValue() {
			return value;
		}
		
		@Override
		public ProtectionTerms.ProtectionTermsBuilder getOrCreateValue() {
			ProtectionTerms.ProtectionTermsBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = ProtectionTerms.builder();
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
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setValue(ProtectionTerms value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaProtectionTerms build() {
			return new ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsImpl(this);
		}
		
		@Override
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder prune() {
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
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder o = (ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder) other;
			
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
		
			ReferenceWithMetaProtectionTerms _that = getType().cast(o);
		
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
			return "ReferenceWithMetaProtectionTermsBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaProtectionTermsMeta extends BasicRosettaMetaData<ReferenceWithMetaProtectionTerms>{

}
