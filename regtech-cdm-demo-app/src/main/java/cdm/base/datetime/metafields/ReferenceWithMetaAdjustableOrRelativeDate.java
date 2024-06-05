package cdm.base.datetime.metafields;

import cdm.base.datetime.AdjustableOrRelativeDate;
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
@RosettaDataType(value="ReferenceWithMetaAdjustableOrRelativeDate", builder=ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaAdjustableOrRelativeDate extends RosettaModelObject, ReferenceWithMeta<AdjustableOrRelativeDate> {

	ReferenceWithMetaAdjustableOrRelativeDateMeta metaData = new ReferenceWithMetaAdjustableOrRelativeDateMeta();

	/*********************** Getter Methods  ***********************/
	AdjustableOrRelativeDate getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaAdjustableOrRelativeDate build();
	
	ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder toBuilder();
	
	static ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder builder() {
		return new ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaAdjustableOrRelativeDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaAdjustableOrRelativeDate> getType() {
		return ReferenceWithMetaAdjustableOrRelativeDate.class;
	}
	
	@Override
	default Class<AdjustableOrRelativeDate> getValueType() {
		return AdjustableOrRelativeDate.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, AdjustableOrRelativeDate.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaAdjustableOrRelativeDateBuilder extends ReferenceWithMetaAdjustableOrRelativeDate, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<AdjustableOrRelativeDate> {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateValue();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setValue(AdjustableOrRelativeDate value);
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setExternalReference(String externalReference);
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaAdjustableOrRelativeDate  ***********************/
	class ReferenceWithMetaAdjustableOrRelativeDateImpl implements ReferenceWithMetaAdjustableOrRelativeDate {
		private final AdjustableOrRelativeDate value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaAdjustableOrRelativeDateImpl(ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public AdjustableOrRelativeDate getValue() {
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
		public ReferenceWithMetaAdjustableOrRelativeDate build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder toBuilder() {
			ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaAdjustableOrRelativeDate _that = getType().cast(o);
		
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
			return "ReferenceWithMetaAdjustableOrRelativeDate {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaAdjustableOrRelativeDate  ***********************/
	class ReferenceWithMetaAdjustableOrRelativeDateBuilderImpl implements ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaAdjustableOrRelativeDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getValue() {
			return value;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateValue() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = AdjustableOrRelativeDate.builder();
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
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setValue(AdjustableOrRelativeDate value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaAdjustableOrRelativeDate build() {
			return new ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateImpl(this);
		}
		
		@Override
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder prune() {
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
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder o = (ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder) other;
			
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
		
			ReferenceWithMetaAdjustableOrRelativeDate _that = getType().cast(o);
		
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
			return "ReferenceWithMetaAdjustableOrRelativeDateBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaAdjustableOrRelativeDateMeta extends BasicRosettaMetaData<ReferenceWithMetaAdjustableOrRelativeDate>{

}
