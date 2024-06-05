package cdm.product.common.schedule.metafields;

import cdm.product.common.schedule.PaymentDates;
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
@RosettaDataType(value="ReferenceWithMetaPaymentDates", builder=ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaPaymentDates extends RosettaModelObject, ReferenceWithMeta<PaymentDates> {

	ReferenceWithMetaPaymentDatesMeta metaData = new ReferenceWithMetaPaymentDatesMeta();

	/*********************** Getter Methods  ***********************/
	PaymentDates getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaPaymentDates build();
	
	ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder toBuilder();
	
	static ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder builder() {
		return new ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaPaymentDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaPaymentDates> getType() {
		return ReferenceWithMetaPaymentDates.class;
	}
	
	@Override
	default Class<PaymentDates> getValueType() {
		return PaymentDates.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, PaymentDates.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaPaymentDatesBuilder extends ReferenceWithMetaPaymentDates, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<PaymentDates> {
		PaymentDates.PaymentDatesBuilder getOrCreateValue();
		PaymentDates.PaymentDatesBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setValue(PaymentDates value);
		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setExternalReference(String externalReference);
		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, PaymentDates.PaymentDatesBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaPaymentDates  ***********************/
	class ReferenceWithMetaPaymentDatesImpl implements ReferenceWithMetaPaymentDates {
		private final PaymentDates value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaPaymentDatesImpl(ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public PaymentDates getValue() {
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
		public ReferenceWithMetaPaymentDates build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder toBuilder() {
			ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaPaymentDates _that = getType().cast(o);
		
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
			return "ReferenceWithMetaPaymentDates {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaPaymentDates  ***********************/
	class ReferenceWithMetaPaymentDatesBuilderImpl implements ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder {
	
		protected PaymentDates.PaymentDatesBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaPaymentDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public PaymentDates.PaymentDatesBuilder getValue() {
			return value;
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder getOrCreateValue() {
			PaymentDates.PaymentDatesBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = PaymentDates.builder();
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
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setValue(PaymentDates value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaPaymentDates build() {
			return new ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesImpl(this);
		}
		
		@Override
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder prune() {
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
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder o = (ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder) other;
			
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
		
			ReferenceWithMetaPaymentDates _that = getType().cast(o);
		
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
			return "ReferenceWithMetaPaymentDatesBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaPaymentDatesMeta extends BasicRosettaMetaData<ReferenceWithMetaPaymentDates>{

}
