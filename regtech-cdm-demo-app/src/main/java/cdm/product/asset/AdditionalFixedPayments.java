package cdm.product.asset;

import cdm.product.asset.AdditionalFixedPayments;
import cdm.product.asset.AdditionalFixedPayments.AdditionalFixedPaymentsBuilder;
import cdm.product.asset.AdditionalFixedPayments.AdditionalFixedPaymentsBuilderImpl;
import cdm.product.asset.AdditionalFixedPayments.AdditionalFixedPaymentsImpl;
import cdm.product.asset.meta.AdditionalFixedPaymentsMeta;
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
 * A class to specify the events that will give rise to the payment additional fixed payments.
 * @version ${project.version}
 */
@RosettaDataType(value="AdditionalFixedPayments", builder=AdditionalFixedPayments.AdditionalFixedPaymentsBuilderImpl.class, version="${project.version}")
public interface AdditionalFixedPayments extends RosettaModelObject {

	AdditionalFixedPaymentsMeta metaData = new AdditionalFixedPaymentsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An additional Fixed Payment Event. Corresponds to the payment by or on behalf of the Issuer of an actual interest amount in respect to the reference obligation that is greater than the expected interest amount. ISDA 2003 Term: Interest Shortfall Reimbursement.
	 */
	Boolean getInterestShortfallReimbursement();
	/**
	 * An additional Fixed Payment Event. Corresponds to the payment by or on behalf of the Issuer of an actual principal amount in respect to the reference obligation that is greater than the expected principal amount. ISDA 2003 Term: Principal Shortfall Reimbursement.
	 */
	Boolean getPrincipalShortfallReimbursement();
	/**
	 * An Additional Fixed Payment. Corresponds to the payment by or on behalf of the issuer of an amount in respect to the reference obligation in reduction of the prior writedowns. ISDA 2003 Term: Writedown Reimbursement.
	 */
	Boolean getWritedownReimbursement();

	/*********************** Build Methods  ***********************/
	AdditionalFixedPayments build();
	
	AdditionalFixedPayments.AdditionalFixedPaymentsBuilder toBuilder();
	
	static AdditionalFixedPayments.AdditionalFixedPaymentsBuilder builder() {
		return new AdditionalFixedPayments.AdditionalFixedPaymentsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdditionalFixedPayments> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdditionalFixedPayments> getType() {
		return AdditionalFixedPayments.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("interestShortfallReimbursement"), Boolean.class, getInterestShortfallReimbursement(), this);
		processor.processBasic(path.newSubPath("principalShortfallReimbursement"), Boolean.class, getPrincipalShortfallReimbursement(), this);
		processor.processBasic(path.newSubPath("writedownReimbursement"), Boolean.class, getWritedownReimbursement(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdditionalFixedPaymentsBuilder extends AdditionalFixedPayments, RosettaModelObjectBuilder {
		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setInterestShortfallReimbursement(Boolean interestShortfallReimbursement);
		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setPrincipalShortfallReimbursement(Boolean principalShortfallReimbursement);
		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setWritedownReimbursement(Boolean writedownReimbursement);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("interestShortfallReimbursement"), Boolean.class, getInterestShortfallReimbursement(), this);
			processor.processBasic(path.newSubPath("principalShortfallReimbursement"), Boolean.class, getPrincipalShortfallReimbursement(), this);
			processor.processBasic(path.newSubPath("writedownReimbursement"), Boolean.class, getWritedownReimbursement(), this);
		}
		

		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder prune();
	}

	/*********************** Immutable Implementation of AdditionalFixedPayments  ***********************/
	class AdditionalFixedPaymentsImpl implements AdditionalFixedPayments {
		private final Boolean interestShortfallReimbursement;
		private final Boolean principalShortfallReimbursement;
		private final Boolean writedownReimbursement;
		
		protected AdditionalFixedPaymentsImpl(AdditionalFixedPayments.AdditionalFixedPaymentsBuilder builder) {
			this.interestShortfallReimbursement = builder.getInterestShortfallReimbursement();
			this.principalShortfallReimbursement = builder.getPrincipalShortfallReimbursement();
			this.writedownReimbursement = builder.getWritedownReimbursement();
		}
		
		@Override
		@RosettaAttribute("interestShortfallReimbursement")
		public Boolean getInterestShortfallReimbursement() {
			return interestShortfallReimbursement;
		}
		
		@Override
		@RosettaAttribute("principalShortfallReimbursement")
		public Boolean getPrincipalShortfallReimbursement() {
			return principalShortfallReimbursement;
		}
		
		@Override
		@RosettaAttribute("writedownReimbursement")
		public Boolean getWritedownReimbursement() {
			return writedownReimbursement;
		}
		
		@Override
		public AdditionalFixedPayments build() {
			return this;
		}
		
		@Override
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder toBuilder() {
			AdditionalFixedPayments.AdditionalFixedPaymentsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdditionalFixedPayments.AdditionalFixedPaymentsBuilder builder) {
			ofNullable(getInterestShortfallReimbursement()).ifPresent(builder::setInterestShortfallReimbursement);
			ofNullable(getPrincipalShortfallReimbursement()).ifPresent(builder::setPrincipalShortfallReimbursement);
			ofNullable(getWritedownReimbursement()).ifPresent(builder::setWritedownReimbursement);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdditionalFixedPayments _that = getType().cast(o);
		
			if (!Objects.equals(interestShortfallReimbursement, _that.getInterestShortfallReimbursement())) return false;
			if (!Objects.equals(principalShortfallReimbursement, _that.getPrincipalShortfallReimbursement())) return false;
			if (!Objects.equals(writedownReimbursement, _that.getWritedownReimbursement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestShortfallReimbursement != null ? interestShortfallReimbursement.hashCode() : 0);
			_result = 31 * _result + (principalShortfallReimbursement != null ? principalShortfallReimbursement.hashCode() : 0);
			_result = 31 * _result + (writedownReimbursement != null ? writedownReimbursement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdditionalFixedPayments {" +
				"interestShortfallReimbursement=" + this.interestShortfallReimbursement + ", " +
				"principalShortfallReimbursement=" + this.principalShortfallReimbursement + ", " +
				"writedownReimbursement=" + this.writedownReimbursement +
			'}';
		}
	}

	/*********************** Builder Implementation of AdditionalFixedPayments  ***********************/
	class AdditionalFixedPaymentsBuilderImpl implements AdditionalFixedPayments.AdditionalFixedPaymentsBuilder {
	
		protected Boolean interestShortfallReimbursement;
		protected Boolean principalShortfallReimbursement;
		protected Boolean writedownReimbursement;
	
		public AdditionalFixedPaymentsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interestShortfallReimbursement")
		public Boolean getInterestShortfallReimbursement() {
			return interestShortfallReimbursement;
		}
		
		@Override
		@RosettaAttribute("principalShortfallReimbursement")
		public Boolean getPrincipalShortfallReimbursement() {
			return principalShortfallReimbursement;
		}
		
		@Override
		@RosettaAttribute("writedownReimbursement")
		public Boolean getWritedownReimbursement() {
			return writedownReimbursement;
		}
		
	
		@Override
		@RosettaAttribute("interestShortfallReimbursement")
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setInterestShortfallReimbursement(Boolean interestShortfallReimbursement) {
			this.interestShortfallReimbursement = interestShortfallReimbursement==null?null:interestShortfallReimbursement;
			return this;
		}
		@Override
		@RosettaAttribute("principalShortfallReimbursement")
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setPrincipalShortfallReimbursement(Boolean principalShortfallReimbursement) {
			this.principalShortfallReimbursement = principalShortfallReimbursement==null?null:principalShortfallReimbursement;
			return this;
		}
		@Override
		@RosettaAttribute("writedownReimbursement")
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder setWritedownReimbursement(Boolean writedownReimbursement) {
			this.writedownReimbursement = writedownReimbursement==null?null:writedownReimbursement;
			return this;
		}
		
		@Override
		public AdditionalFixedPayments build() {
			return new AdditionalFixedPayments.AdditionalFixedPaymentsImpl(this);
		}
		
		@Override
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterestShortfallReimbursement()!=null) return true;
			if (getPrincipalShortfallReimbursement()!=null) return true;
			if (getWritedownReimbursement()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdditionalFixedPayments.AdditionalFixedPaymentsBuilder o = (AdditionalFixedPayments.AdditionalFixedPaymentsBuilder) other;
			
			
			merger.mergeBasic(getInterestShortfallReimbursement(), o.getInterestShortfallReimbursement(), this::setInterestShortfallReimbursement);
			merger.mergeBasic(getPrincipalShortfallReimbursement(), o.getPrincipalShortfallReimbursement(), this::setPrincipalShortfallReimbursement);
			merger.mergeBasic(getWritedownReimbursement(), o.getWritedownReimbursement(), this::setWritedownReimbursement);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdditionalFixedPayments _that = getType().cast(o);
		
			if (!Objects.equals(interestShortfallReimbursement, _that.getInterestShortfallReimbursement())) return false;
			if (!Objects.equals(principalShortfallReimbursement, _that.getPrincipalShortfallReimbursement())) return false;
			if (!Objects.equals(writedownReimbursement, _that.getWritedownReimbursement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestShortfallReimbursement != null ? interestShortfallReimbursement.hashCode() : 0);
			_result = 31 * _result + (principalShortfallReimbursement != null ? principalShortfallReimbursement.hashCode() : 0);
			_result = 31 * _result + (writedownReimbursement != null ? writedownReimbursement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdditionalFixedPaymentsBuilder {" +
				"interestShortfallReimbursement=" + this.interestShortfallReimbursement + ", " +
				"principalShortfallReimbursement=" + this.principalShortfallReimbursement + ", " +
				"writedownReimbursement=" + this.writedownReimbursement +
			'}';
		}
	}
}
