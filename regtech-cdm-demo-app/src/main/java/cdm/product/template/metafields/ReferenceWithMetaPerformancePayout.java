package cdm.product.template.metafields;

import cdm.product.template.PerformancePayout;
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
@RosettaDataType(value="ReferenceWithMetaPerformancePayout", builder=ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaPerformancePayout extends RosettaModelObject, ReferenceWithMeta<PerformancePayout> {

	ReferenceWithMetaPerformancePayoutMeta metaData = new ReferenceWithMetaPerformancePayoutMeta();

	/*********************** Getter Methods  ***********************/
	PerformancePayout getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaPerformancePayout build();
	
	ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder toBuilder();
	
	static ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder builder() {
		return new ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaPerformancePayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaPerformancePayout> getType() {
		return ReferenceWithMetaPerformancePayout.class;
	}
	
	@Override
	default Class<PerformancePayout> getValueType() {
		return PerformancePayout.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, PerformancePayout.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaPerformancePayoutBuilder extends ReferenceWithMetaPerformancePayout, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<PerformancePayout> {
		PerformancePayout.PerformancePayoutBuilder getOrCreateValue();
		PerformancePayout.PerformancePayoutBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setValue(PerformancePayout value);
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setExternalReference(String externalReference);
		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, PerformancePayout.PerformancePayoutBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaPerformancePayout  ***********************/
	class ReferenceWithMetaPerformancePayoutImpl implements ReferenceWithMetaPerformancePayout {
		private final PerformancePayout value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaPerformancePayoutImpl(ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public PerformancePayout getValue() {
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
		public ReferenceWithMetaPerformancePayout build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder toBuilder() {
			ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaPerformancePayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaPerformancePayout {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaPerformancePayout  ***********************/
	class ReferenceWithMetaPerformancePayoutBuilderImpl implements ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder {
	
		protected PerformancePayout.PerformancePayoutBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaPerformancePayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public PerformancePayout.PerformancePayoutBuilder getValue() {
			return value;
		}
		
		@Override
		public PerformancePayout.PerformancePayoutBuilder getOrCreateValue() {
			PerformancePayout.PerformancePayoutBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = PerformancePayout.builder();
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
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setValue(PerformancePayout value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaPerformancePayout build() {
			return new ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutImpl(this);
		}
		
		@Override
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder prune() {
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
		public ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder o = (ReferenceWithMetaPerformancePayout.ReferenceWithMetaPerformancePayoutBuilder) other;
			
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
		
			ReferenceWithMetaPerformancePayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaPerformancePayoutBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaPerformancePayoutMeta extends BasicRosettaMetaData<ReferenceWithMetaPerformancePayout>{

}
