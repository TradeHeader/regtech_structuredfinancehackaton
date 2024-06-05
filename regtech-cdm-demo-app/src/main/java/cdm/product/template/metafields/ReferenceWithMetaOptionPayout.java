package cdm.product.template.metafields;

import cdm.product.template.OptionPayout;
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
@RosettaDataType(value="ReferenceWithMetaOptionPayout", builder=ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaOptionPayout extends RosettaModelObject, ReferenceWithMeta<OptionPayout> {

	ReferenceWithMetaOptionPayoutMeta metaData = new ReferenceWithMetaOptionPayoutMeta();

	/*********************** Getter Methods  ***********************/
	OptionPayout getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaOptionPayout build();
	
	ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder toBuilder();
	
	static ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder builder() {
		return new ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaOptionPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaOptionPayout> getType() {
		return ReferenceWithMetaOptionPayout.class;
	}
	
	@Override
	default Class<OptionPayout> getValueType() {
		return OptionPayout.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, OptionPayout.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaOptionPayoutBuilder extends ReferenceWithMetaOptionPayout, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<OptionPayout> {
		OptionPayout.OptionPayoutBuilder getOrCreateValue();
		OptionPayout.OptionPayoutBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setValue(OptionPayout value);
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setExternalReference(String externalReference);
		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, OptionPayout.OptionPayoutBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaOptionPayout  ***********************/
	class ReferenceWithMetaOptionPayoutImpl implements ReferenceWithMetaOptionPayout {
		private final OptionPayout value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaOptionPayoutImpl(ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public OptionPayout getValue() {
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
		public ReferenceWithMetaOptionPayout build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder toBuilder() {
			ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaOptionPayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaOptionPayout {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaOptionPayout  ***********************/
	class ReferenceWithMetaOptionPayoutBuilderImpl implements ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder {
	
		protected OptionPayout.OptionPayoutBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaOptionPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public OptionPayout.OptionPayoutBuilder getValue() {
			return value;
		}
		
		@Override
		public OptionPayout.OptionPayoutBuilder getOrCreateValue() {
			OptionPayout.OptionPayoutBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = OptionPayout.builder();
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
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setValue(OptionPayout value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaOptionPayout build() {
			return new ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutImpl(this);
		}
		
		@Override
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder prune() {
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
		public ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder o = (ReferenceWithMetaOptionPayout.ReferenceWithMetaOptionPayoutBuilder) other;
			
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
		
			ReferenceWithMetaOptionPayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaOptionPayoutBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaOptionPayoutMeta extends BasicRosettaMetaData<ReferenceWithMetaOptionPayout>{

}
