package cdm.observable.event;

import cdm.observable.asset.Money;
import cdm.observable.event.FailureToPay;
import cdm.observable.event.FailureToPay.FailureToPayBuilder;
import cdm.observable.event.FailureToPay.FailureToPayBuilderImpl;
import cdm.observable.event.FailureToPay.FailureToPayImpl;
import cdm.observable.event.GracePeriodExtension;
import cdm.observable.event.meta.FailureToPayMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="FailureToPay", builder=FailureToPay.FailureToPayBuilderImpl.class, version="${project.version}")
public interface FailureToPay extends RosettaModelObject {

	FailureToPayMeta metaData = new FailureToPayMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the failure to pay provision is applicable.
	 */
	Boolean getApplicable();
	/**
	 * If this element is specified, indicates whether or not a grace period extension is applicable. ISDA 2003 Term: Grace Period Extension Applicable.
	 */
	GracePeriodExtension getGracePeriodExtension();
	/**
	 * Specifies a threshold for the failure to pay credit event. Market standard is USD 1,000,000 (JPY 100,000,000 for Japanese Yen trades) or its equivalent in the relevant obligation currency. This is applied on an aggregate basis across all Obligations of the Reference Entity. Intended to prevent technical/operational errors from triggering credit events. ISDA 2003 Term: Payment Requirement
	 */
	Money getPaymentRequirement();

	/*********************** Build Methods  ***********************/
	FailureToPay build();
	
	FailureToPay.FailureToPayBuilder toBuilder();
	
	static FailureToPay.FailureToPayBuilder builder() {
		return new FailureToPay.FailureToPayBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FailureToPay> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FailureToPay> getType() {
		return FailureToPay.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processRosetta(path.newSubPath("gracePeriodExtension"), processor, GracePeriodExtension.class, getGracePeriodExtension());
		processRosetta(path.newSubPath("paymentRequirement"), processor, Money.class, getPaymentRequirement());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FailureToPayBuilder extends FailureToPay, RosettaModelObjectBuilder {
		GracePeriodExtension.GracePeriodExtensionBuilder getOrCreateGracePeriodExtension();
		GracePeriodExtension.GracePeriodExtensionBuilder getGracePeriodExtension();
		Money.MoneyBuilder getOrCreatePaymentRequirement();
		Money.MoneyBuilder getPaymentRequirement();
		FailureToPay.FailureToPayBuilder setApplicable(Boolean applicable);
		FailureToPay.FailureToPayBuilder setGracePeriodExtension(GracePeriodExtension gracePeriodExtension);
		FailureToPay.FailureToPayBuilder setPaymentRequirement(Money paymentRequirement);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processRosetta(path.newSubPath("gracePeriodExtension"), processor, GracePeriodExtension.GracePeriodExtensionBuilder.class, getGracePeriodExtension());
			processRosetta(path.newSubPath("paymentRequirement"), processor, Money.MoneyBuilder.class, getPaymentRequirement());
		}
		

		FailureToPay.FailureToPayBuilder prune();
	}

	/*********************** Immutable Implementation of FailureToPay  ***********************/
	class FailureToPayImpl implements FailureToPay {
		private final Boolean applicable;
		private final GracePeriodExtension gracePeriodExtension;
		private final Money paymentRequirement;
		
		protected FailureToPayImpl(FailureToPay.FailureToPayBuilder builder) {
			this.applicable = builder.getApplicable();
			this.gracePeriodExtension = ofNullable(builder.getGracePeriodExtension()).map(f->f.build()).orElse(null);
			this.paymentRequirement = ofNullable(builder.getPaymentRequirement()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("gracePeriodExtension")
		public GracePeriodExtension getGracePeriodExtension() {
			return gracePeriodExtension;
		}
		
		@Override
		@RosettaAttribute("paymentRequirement")
		public Money getPaymentRequirement() {
			return paymentRequirement;
		}
		
		@Override
		public FailureToPay build() {
			return this;
		}
		
		@Override
		public FailureToPay.FailureToPayBuilder toBuilder() {
			FailureToPay.FailureToPayBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FailureToPay.FailureToPayBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getGracePeriodExtension()).ifPresent(builder::setGracePeriodExtension);
			ofNullable(getPaymentRequirement()).ifPresent(builder::setPaymentRequirement);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FailureToPay _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(gracePeriodExtension, _that.getGracePeriodExtension())) return false;
			if (!Objects.equals(paymentRequirement, _that.getPaymentRequirement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (gracePeriodExtension != null ? gracePeriodExtension.hashCode() : 0);
			_result = 31 * _result + (paymentRequirement != null ? paymentRequirement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FailureToPay {" +
				"applicable=" + this.applicable + ", " +
				"gracePeriodExtension=" + this.gracePeriodExtension + ", " +
				"paymentRequirement=" + this.paymentRequirement +
			'}';
		}
	}

	/*********************** Builder Implementation of FailureToPay  ***********************/
	class FailureToPayBuilderImpl implements FailureToPay.FailureToPayBuilder {
	
		protected Boolean applicable;
		protected GracePeriodExtension.GracePeriodExtensionBuilder gracePeriodExtension;
		protected Money.MoneyBuilder paymentRequirement;
	
		public FailureToPayBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("gracePeriodExtension")
		public GracePeriodExtension.GracePeriodExtensionBuilder getGracePeriodExtension() {
			return gracePeriodExtension;
		}
		
		@Override
		public GracePeriodExtension.GracePeriodExtensionBuilder getOrCreateGracePeriodExtension() {
			GracePeriodExtension.GracePeriodExtensionBuilder result;
			if (gracePeriodExtension!=null) {
				result = gracePeriodExtension;
			}
			else {
				result = gracePeriodExtension = GracePeriodExtension.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentRequirement")
		public Money.MoneyBuilder getPaymentRequirement() {
			return paymentRequirement;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePaymentRequirement() {
			Money.MoneyBuilder result;
			if (paymentRequirement!=null) {
				result = paymentRequirement;
			}
			else {
				result = paymentRequirement = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("applicable")
		public FailureToPay.FailureToPayBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("gracePeriodExtension")
		public FailureToPay.FailureToPayBuilder setGracePeriodExtension(GracePeriodExtension gracePeriodExtension) {
			this.gracePeriodExtension = gracePeriodExtension==null?null:gracePeriodExtension.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentRequirement")
		public FailureToPay.FailureToPayBuilder setPaymentRequirement(Money paymentRequirement) {
			this.paymentRequirement = paymentRequirement==null?null:paymentRequirement.toBuilder();
			return this;
		}
		
		@Override
		public FailureToPay build() {
			return new FailureToPay.FailureToPayImpl(this);
		}
		
		@Override
		public FailureToPay.FailureToPayBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FailureToPay.FailureToPayBuilder prune() {
			if (gracePeriodExtension!=null && !gracePeriodExtension.prune().hasData()) gracePeriodExtension = null;
			if (paymentRequirement!=null && !paymentRequirement.prune().hasData()) paymentRequirement = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicable()!=null) return true;
			if (getGracePeriodExtension()!=null && getGracePeriodExtension().hasData()) return true;
			if (getPaymentRequirement()!=null && getPaymentRequirement().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FailureToPay.FailureToPayBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FailureToPay.FailureToPayBuilder o = (FailureToPay.FailureToPayBuilder) other;
			
			merger.mergeRosetta(getGracePeriodExtension(), o.getGracePeriodExtension(), this::setGracePeriodExtension);
			merger.mergeRosetta(getPaymentRequirement(), o.getPaymentRequirement(), this::setPaymentRequirement);
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FailureToPay _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(gracePeriodExtension, _that.getGracePeriodExtension())) return false;
			if (!Objects.equals(paymentRequirement, _that.getPaymentRequirement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (gracePeriodExtension != null ? gracePeriodExtension.hashCode() : 0);
			_result = 31 * _result + (paymentRequirement != null ? paymentRequirement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FailureToPayBuilder {" +
				"applicable=" + this.applicable + ", " +
				"gracePeriodExtension=" + this.gracePeriodExtension + ", " +
				"paymentRequirement=" + this.paymentRequirement +
			'}';
		}
	}
}
