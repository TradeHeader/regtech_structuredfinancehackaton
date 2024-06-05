package cdm.observable.event.metafields;

import cdm.observable.event.CreditEvents;
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
@RosettaDataType(value="ReferenceWithMetaCreditEvents", builder=ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaCreditEvents extends RosettaModelObject, ReferenceWithMeta<CreditEvents> {

	ReferenceWithMetaCreditEventsMeta metaData = new ReferenceWithMetaCreditEventsMeta();

	/*********************** Getter Methods  ***********************/
	CreditEvents getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaCreditEvents build();
	
	ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder toBuilder();
	
	static ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder builder() {
		return new ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaCreditEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaCreditEvents> getType() {
		return ReferenceWithMetaCreditEvents.class;
	}
	
	@Override
	default Class<CreditEvents> getValueType() {
		return CreditEvents.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, CreditEvents.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaCreditEventsBuilder extends ReferenceWithMetaCreditEvents, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<CreditEvents> {
		CreditEvents.CreditEventsBuilder getOrCreateValue();
		CreditEvents.CreditEventsBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setValue(CreditEvents value);
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setExternalReference(String externalReference);
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, CreditEvents.CreditEventsBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaCreditEvents  ***********************/
	class ReferenceWithMetaCreditEventsImpl implements ReferenceWithMetaCreditEvents {
		private final CreditEvents value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaCreditEventsImpl(ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public CreditEvents getValue() {
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
		public ReferenceWithMetaCreditEvents build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder toBuilder() {
			ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaCreditEvents _that = getType().cast(o);
		
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
			return "ReferenceWithMetaCreditEvents {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaCreditEvents  ***********************/
	class ReferenceWithMetaCreditEventsBuilderImpl implements ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder {
	
		protected CreditEvents.CreditEventsBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaCreditEventsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public CreditEvents.CreditEventsBuilder getValue() {
			return value;
		}
		
		@Override
		public CreditEvents.CreditEventsBuilder getOrCreateValue() {
			CreditEvents.CreditEventsBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = CreditEvents.builder();
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
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setValue(CreditEvents value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaCreditEvents build() {
			return new ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsImpl(this);
		}
		
		@Override
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder prune() {
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
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder o = (ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder) other;
			
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
		
			ReferenceWithMetaCreditEvents _that = getType().cast(o);
		
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
			return "ReferenceWithMetaCreditEventsBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaCreditEventsMeta extends BasicRosettaMetaData<ReferenceWithMetaCreditEvents>{

}
