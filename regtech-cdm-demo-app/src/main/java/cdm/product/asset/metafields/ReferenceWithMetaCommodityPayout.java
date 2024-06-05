package cdm.product.asset.metafields;

import cdm.product.asset.CommodityPayout;
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
@RosettaDataType(value="ReferenceWithMetaCommodityPayout", builder=ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaCommodityPayout extends RosettaModelObject, ReferenceWithMeta<CommodityPayout> {

	ReferenceWithMetaCommodityPayoutMeta metaData = new ReferenceWithMetaCommodityPayoutMeta();

	/*********************** Getter Methods  ***********************/
	CommodityPayout getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaCommodityPayout build();
	
	ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder toBuilder();
	
	static ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder builder() {
		return new ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaCommodityPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceWithMetaCommodityPayout> getType() {
		return ReferenceWithMetaCommodityPayout.class;
	}
	
	@Override
	default Class<CommodityPayout> getValueType() {
		return CommodityPayout.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, CommodityPayout.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaCommodityPayoutBuilder extends ReferenceWithMetaCommodityPayout, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<CommodityPayout> {
		CommodityPayout.CommodityPayoutBuilder getOrCreateValue();
		CommodityPayout.CommodityPayoutBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setValue(CommodityPayout value);
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setExternalReference(String externalReference);
		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, CommodityPayout.CommodityPayoutBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaCommodityPayout  ***********************/
	class ReferenceWithMetaCommodityPayoutImpl implements ReferenceWithMetaCommodityPayout {
		private final CommodityPayout value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaCommodityPayoutImpl(ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public CommodityPayout getValue() {
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
		public ReferenceWithMetaCommodityPayout build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder toBuilder() {
			ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaCommodityPayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaCommodityPayout {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaCommodityPayout  ***********************/
	class ReferenceWithMetaCommodityPayoutBuilderImpl implements ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder {
	
		protected CommodityPayout.CommodityPayoutBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
	
		public ReferenceWithMetaCommodityPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public CommodityPayout.CommodityPayoutBuilder getValue() {
			return value;
		}
		
		@Override
		public CommodityPayout.CommodityPayoutBuilder getOrCreateValue() {
			CommodityPayout.CommodityPayoutBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = CommodityPayout.builder();
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
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setValue(CommodityPayout value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("globalReference")
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setGlobalReference(String globalReference) {
			this.globalReference = globalReference==null?null:globalReference;
			return this;
		}
		@Override
		@RosettaAttribute("externalReference")
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setExternalReference(String externalReference) {
			this.externalReference = externalReference==null?null:externalReference;
			return this;
		}
		@Override
		@RosettaAttribute("address")
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder setReference(Reference reference) {
			this.reference = reference==null?null:reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaCommodityPayout build() {
			return new ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutImpl(this);
		}
		
		@Override
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder prune() {
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
		public ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder o = (ReferenceWithMetaCommodityPayout.ReferenceWithMetaCommodityPayoutBuilder) other;
			
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
		
			ReferenceWithMetaCommodityPayout _that = getType().cast(o);
		
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
			return "ReferenceWithMetaCommodityPayoutBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaCommodityPayoutMeta extends BasicRosettaMetaData<ReferenceWithMetaCommodityPayout>{

}
