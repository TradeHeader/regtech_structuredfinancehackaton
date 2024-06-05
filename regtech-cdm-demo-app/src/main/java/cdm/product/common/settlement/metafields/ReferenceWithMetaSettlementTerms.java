package cdm.product.common.settlement.metafields;

import cdm.product.common.settlement.SettlementTerms;
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
@RosettaDataType(value="ReferenceWithMetaSettlementTerms", builder=ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaSettlementTerms extends RosettaModelObject, ReferenceWithMeta<SettlementTerms> {

	ReferenceWithMetaSettlementTermsMeta metaData = new ReferenceWithMetaSettlementTermsMeta();

	/*********************** Getter Methods  ***********************/
	SettlementTerms getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaSettlementTerms build();
	
	ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder toBuilder();
	
	static ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder builder() {
		return new ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaSettlementTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaSettlementTerms> getType() {
		return ReferenceWithMetaSettlementTerms.class;
	}
	
	@Override
	default Class<SettlementTerms> getValueType() {
		return SettlementTerms.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, SettlementTerms.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaSettlementTermsBuilder extends ReferenceWithMetaSettlementTerms, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<SettlementTerms> {
		SettlementTerms.SettlementTermsBuilder getOrCreateValue();
		SettlementTerms.SettlementTermsBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setValue(SettlementTerms value);
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setExternalReference(String externalReference);
		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, SettlementTerms.SettlementTermsBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaSettlementTerms  ***********************/
	class ReferenceWithMetaSettlementTermsImpl implements ReferenceWithMetaSettlementTerms {
		private final SettlementTerms value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaSettlementTermsImpl(ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public SettlementTerms getValue() {
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
		public ReferenceWithMetaSettlementTerms build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder toBuilder() {
			ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaSettlementTerms _that = getType().cast(o);
		
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
			return "ReferenceWithMetaSettlementTerms {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaSettlementTerms  ***********************/
	class ReferenceWithMetaSettlementTermsBuilderImpl implements ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder {
	
		protected SettlementTerms.SettlementTermsBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaSettlementTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public SettlementTerms.SettlementTermsBuilder getValue() {
			return value;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder getOrCreateValue() {
			SettlementTerms.SettlementTermsBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = SettlementTerms.builder();
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
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setValue(SettlementTerms value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaSettlementTerms build() {
			return new ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsImpl(this);
		}
		
		@Override
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder prune() {
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
		public ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder o = (ReferenceWithMetaSettlementTerms.ReferenceWithMetaSettlementTermsBuilder) other;
			
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
		
			ReferenceWithMetaSettlementTerms _that = getType().cast(o);
		
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
			return "ReferenceWithMetaSettlementTermsBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaSettlementTermsMeta extends BasicRosettaMetaData<ReferenceWithMetaSettlementTerms>{

}
