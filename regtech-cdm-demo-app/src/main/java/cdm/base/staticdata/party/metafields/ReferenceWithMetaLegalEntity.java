package cdm.base.staticdata.party.metafields;

import cdm.base.staticdata.party.LegalEntity;
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
@RosettaDataType(value="ReferenceWithMetaLegalEntity", builder=ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaLegalEntity extends RosettaModelObject, ReferenceWithMeta<LegalEntity> {

	ReferenceWithMetaLegalEntityMeta metaData = new ReferenceWithMetaLegalEntityMeta();

	/*********************** Getter Methods  ***********************/
	LegalEntity getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaLegalEntity build();
	
	ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder toBuilder();
	
	static ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder builder() {
		return new ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaLegalEntity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaLegalEntity> getType() {
		return ReferenceWithMetaLegalEntity.class;
	}
	
	@Override
	default Class<LegalEntity> getValueType() {
		return LegalEntity.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, LegalEntity.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaLegalEntityBuilder extends ReferenceWithMetaLegalEntity, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<LegalEntity> {
		LegalEntity.LegalEntityBuilder getOrCreateValue();
		LegalEntity.LegalEntityBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setValue(LegalEntity value);
		ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setExternalReference(String externalReference);
		ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, LegalEntity.LegalEntityBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaLegalEntity  ***********************/
	class ReferenceWithMetaLegalEntityImpl implements ReferenceWithMetaLegalEntity {
		private final LegalEntity value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaLegalEntityImpl(ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public LegalEntity getValue() {
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
		public ReferenceWithMetaLegalEntity build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder toBuilder() {
			ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaLegalEntity _that = getType().cast(o);
		
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
			return "ReferenceWithMetaLegalEntity {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaLegalEntity  ***********************/
	class ReferenceWithMetaLegalEntityBuilderImpl implements ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder {
	
		protected LegalEntity.LegalEntityBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaLegalEntityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public LegalEntity.LegalEntityBuilder getValue() {
			return value;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder getOrCreateValue() {
			LegalEntity.LegalEntityBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = LegalEntity.builder();
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
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setValue(LegalEntity value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaLegalEntity build() {
			return new ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityImpl(this);
		}
		
		@Override
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder prune() {
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
		public ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder o = (ReferenceWithMetaLegalEntity.ReferenceWithMetaLegalEntityBuilder) other;
			
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
		
			ReferenceWithMetaLegalEntity _that = getType().cast(o);
		
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
			return "ReferenceWithMetaLegalEntityBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaLegalEntityMeta extends BasicRosettaMetaData<ReferenceWithMetaLegalEntity>{

}
