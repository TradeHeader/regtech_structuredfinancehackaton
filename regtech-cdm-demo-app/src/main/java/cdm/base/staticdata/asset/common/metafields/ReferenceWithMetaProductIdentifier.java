package cdm.base.staticdata.asset.common.metafields;

import cdm.base.staticdata.asset.common.ProductIdentifier;
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
@RosettaDataType(value="ReferenceWithMetaProductIdentifier", builder=ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaProductIdentifier extends RosettaModelObject, ReferenceWithMeta<ProductIdentifier> {

	ReferenceWithMetaProductIdentifierMeta metaData = new ReferenceWithMetaProductIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	ProductIdentifier getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaProductIdentifier build();
	
	ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder toBuilder();
	
	static ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder builder() {
		return new ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaProductIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaProductIdentifier> getType() {
		return ReferenceWithMetaProductIdentifier.class;
	}
	
	@Override
	default Class<ProductIdentifier> getValueType() {
		return ProductIdentifier.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, ProductIdentifier.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaProductIdentifierBuilder extends ReferenceWithMetaProductIdentifier, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<ProductIdentifier> {
		ProductIdentifier.ProductIdentifierBuilder getOrCreateValue();
		ProductIdentifier.ProductIdentifierBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setValue(ProductIdentifier value);
		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setExternalReference(String externalReference);
		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaProductIdentifier  ***********************/
	class ReferenceWithMetaProductIdentifierImpl implements ReferenceWithMetaProductIdentifier {
		private final ProductIdentifier value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaProductIdentifierImpl(ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public ProductIdentifier getValue() {
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
		public ReferenceWithMetaProductIdentifier build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder toBuilder() {
			ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaProductIdentifier _that = getType().cast(o);
		
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
			return "ReferenceWithMetaProductIdentifier {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaProductIdentifier  ***********************/
	class ReferenceWithMetaProductIdentifierBuilderImpl implements ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder {
	
		protected ProductIdentifier.ProductIdentifierBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaProductIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public ProductIdentifier.ProductIdentifierBuilder getValue() {
			return value;
		}
		
		@Override
		public ProductIdentifier.ProductIdentifierBuilder getOrCreateValue() {
			ProductIdentifier.ProductIdentifierBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = ProductIdentifier.builder();
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
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setValue(ProductIdentifier value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaProductIdentifier build() {
			return new ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierImpl(this);
		}
		
		@Override
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder prune() {
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
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder o = (ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder) other;
			
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
		
			ReferenceWithMetaProductIdentifier _that = getType().cast(o);
		
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
			return "ReferenceWithMetaProductIdentifierBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaProductIdentifierMeta extends BasicRosettaMetaData<ReferenceWithMetaProductIdentifier>{

}
