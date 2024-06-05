package cdm.product.asset;

import cdm.base.datetime.Offset;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendDateReference.DividendDateReferenceBuilder;
import cdm.product.asset.DividendDateReference.DividendDateReferenceBuilderImpl;
import cdm.product.asset.DividendDateReference.DividendDateReferenceImpl;
import cdm.product.asset.DividendDateReferenceEnum;
import cdm.product.asset.meta.DividendDateReferenceMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the dividend date by reference to another date, with the ability to apply and offset. This class doesn&#39;t exist in FpML and is meant to simplify the choice constraint associated with the DividendPaymentDate class.
 * @version ${project.version}
 */
@RosettaDataType(value="DividendDateReference", builder=DividendDateReference.DividendDateReferenceBuilderImpl.class, version="${project.version}")
public interface DividendDateReference extends RosettaModelObject {

	DividendDateReferenceMeta metaData = new DividendDateReferenceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specification of the dividend date using an enumeration, with values such as the pay date, the ex-date or the record date.
	 */
	DividendDateReferenceEnum getDateReference();
	/**
	 * Only to be used when SharePayment has been specified in the dividendDateReference element. The number of Currency Business Days following the day on which the Issuer of the Shares pays the relevant dividend to holders of record of the Shares.
	 */
	Offset getPaymentDateOffset();

	/*********************** Build Methods  ***********************/
	DividendDateReference build();
	
	DividendDateReference.DividendDateReferenceBuilder toBuilder();
	
	static DividendDateReference.DividendDateReferenceBuilder builder() {
		return new DividendDateReference.DividendDateReferenceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendDateReference> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendDateReference> getType() {
		return DividendDateReference.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("dateReference"), DividendDateReferenceEnum.class, getDateReference(), this);
		processRosetta(path.newSubPath("paymentDateOffset"), processor, Offset.class, getPaymentDateOffset());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendDateReferenceBuilder extends DividendDateReference, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreatePaymentDateOffset();
		Offset.OffsetBuilder getPaymentDateOffset();
		DividendDateReference.DividendDateReferenceBuilder setDateReference(DividendDateReferenceEnum dateReference);
		DividendDateReference.DividendDateReferenceBuilder setPaymentDateOffset(Offset paymentDateOffset);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("dateReference"), DividendDateReferenceEnum.class, getDateReference(), this);
			processRosetta(path.newSubPath("paymentDateOffset"), processor, Offset.OffsetBuilder.class, getPaymentDateOffset());
		}
		

		DividendDateReference.DividendDateReferenceBuilder prune();
	}

	/*********************** Immutable Implementation of DividendDateReference  ***********************/
	class DividendDateReferenceImpl implements DividendDateReference {
		private final DividendDateReferenceEnum dateReference;
		private final Offset paymentDateOffset;
		
		protected DividendDateReferenceImpl(DividendDateReference.DividendDateReferenceBuilder builder) {
			this.dateReference = builder.getDateReference();
			this.paymentDateOffset = ofNullable(builder.getPaymentDateOffset()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("dateReference")
		public DividendDateReferenceEnum getDateReference() {
			return dateReference;
		}
		
		@Override
		@RosettaAttribute("paymentDateOffset")
		public Offset getPaymentDateOffset() {
			return paymentDateOffset;
		}
		
		@Override
		public DividendDateReference build() {
			return this;
		}
		
		@Override
		public DividendDateReference.DividendDateReferenceBuilder toBuilder() {
			DividendDateReference.DividendDateReferenceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendDateReference.DividendDateReferenceBuilder builder) {
			ofNullable(getDateReference()).ifPresent(builder::setDateReference);
			ofNullable(getPaymentDateOffset()).ifPresent(builder::setPaymentDateOffset);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendDateReference _that = getType().cast(o);
		
			if (!Objects.equals(dateReference, _that.getDateReference())) return false;
			if (!Objects.equals(paymentDateOffset, _that.getPaymentDateOffset())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dateReference != null ? dateReference.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentDateOffset != null ? paymentDateOffset.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendDateReference {" +
				"dateReference=" + this.dateReference + ", " +
				"paymentDateOffset=" + this.paymentDateOffset +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendDateReference  ***********************/
	class DividendDateReferenceBuilderImpl implements DividendDateReference.DividendDateReferenceBuilder {
	
		protected DividendDateReferenceEnum dateReference;
		protected Offset.OffsetBuilder paymentDateOffset;
	
		public DividendDateReferenceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("dateReference")
		public DividendDateReferenceEnum getDateReference() {
			return dateReference;
		}
		
		@Override
		@RosettaAttribute("paymentDateOffset")
		public Offset.OffsetBuilder getPaymentDateOffset() {
			return paymentDateOffset;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreatePaymentDateOffset() {
			Offset.OffsetBuilder result;
			if (paymentDateOffset!=null) {
				result = paymentDateOffset;
			}
			else {
				result = paymentDateOffset = Offset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("dateReference")
		public DividendDateReference.DividendDateReferenceBuilder setDateReference(DividendDateReferenceEnum dateReference) {
			this.dateReference = dateReference==null?null:dateReference;
			return this;
		}
		@Override
		@RosettaAttribute("paymentDateOffset")
		public DividendDateReference.DividendDateReferenceBuilder setPaymentDateOffset(Offset paymentDateOffset) {
			this.paymentDateOffset = paymentDateOffset==null?null:paymentDateOffset.toBuilder();
			return this;
		}
		
		@Override
		public DividendDateReference build() {
			return new DividendDateReference.DividendDateReferenceImpl(this);
		}
		
		@Override
		public DividendDateReference.DividendDateReferenceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendDateReference.DividendDateReferenceBuilder prune() {
			if (paymentDateOffset!=null && !paymentDateOffset.prune().hasData()) paymentDateOffset = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDateReference()!=null) return true;
			if (getPaymentDateOffset()!=null && getPaymentDateOffset().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendDateReference.DividendDateReferenceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendDateReference.DividendDateReferenceBuilder o = (DividendDateReference.DividendDateReferenceBuilder) other;
			
			merger.mergeRosetta(getPaymentDateOffset(), o.getPaymentDateOffset(), this::setPaymentDateOffset);
			
			merger.mergeBasic(getDateReference(), o.getDateReference(), this::setDateReference);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendDateReference _that = getType().cast(o);
		
			if (!Objects.equals(dateReference, _that.getDateReference())) return false;
			if (!Objects.equals(paymentDateOffset, _that.getPaymentDateOffset())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dateReference != null ? dateReference.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentDateOffset != null ? paymentDateOffset.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendDateReferenceBuilder {" +
				"dateReference=" + this.dateReference + ", " +
				"paymentDateOffset=" + this.paymentDateOffset +
			'}';
		}
	}
}
