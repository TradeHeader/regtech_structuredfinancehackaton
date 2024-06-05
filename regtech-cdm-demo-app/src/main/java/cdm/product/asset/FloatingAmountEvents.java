package cdm.product.asset;

import cdm.product.asset.AdditionalFixedPayments;
import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.FloatingAmountEvents.FloatingAmountEventsBuilder;
import cdm.product.asset.FloatingAmountEvents.FloatingAmountEventsBuilderImpl;
import cdm.product.asset.FloatingAmountEvents.FloatingAmountEventsImpl;
import cdm.product.asset.FloatingAmountProvisions;
import cdm.product.asset.InterestShortFall;
import cdm.product.asset.meta.FloatingAmountEventsMeta;
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
 * A class to specify the ISDA terms relating to the floating rate payment events and the implied additional fixed payments, applicable to the credit derivatives transactions on mortgage-backed securities with pay-as-you-go or physical settlement.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingAmountEvents", builder=FloatingAmountEvents.FloatingAmountEventsBuilderImpl.class, version="${project.version}")
public interface FloatingAmountEvents extends RosettaModelObject {

	FloatingAmountEventsMeta metaData = new FloatingAmountEventsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A floating rate payment event. Corresponds to the failure by the Reference Entity to pay an expected principal amount or the payment of an actual principal amount that is less than the expected principal amount. ISDA 2003 Term: Failure to Pay Principal.
	 */
	Boolean getFailureToPayPrincipal();
	/**
	 * A floating rate payment event. With respect to any Reference Obligation Payment Date, either (a) the non-payment of an Expected Interest Amount or (b) the payment of an Actual Interest Amount that is less than the Expected Interest Amount. ISDA 2003 Term: Interest Shortfall.
	 */
	InterestShortFall getInterestShortfall();
	/**
	 * A floating rate payment event. Results from the fact that the underlier writes down its outstanding principal amount. ISDA 2003 Term: Writedown.
	 */
	Boolean getWritedown();
	/**
	 * A floating rate payment event. Results from the fact that losses occur to the underlying instruments that do not result in reductions of the outstanding principal of the reference obligation.
	 */
	Boolean getImpliedWritedown();
	/**
	 * Specifies the floating amount provisions associated with the floatingAmountEvents.
	 */
	FloatingAmountProvisions getFloatingAmountProvisions();
	/**
	 * Specifies the events that will give rise to the payment additional fixed payments.
	 */
	AdditionalFixedPayments getAdditionalFixedPayments();

	/*********************** Build Methods  ***********************/
	FloatingAmountEvents build();
	
	FloatingAmountEvents.FloatingAmountEventsBuilder toBuilder();
	
	static FloatingAmountEvents.FloatingAmountEventsBuilder builder() {
		return new FloatingAmountEvents.FloatingAmountEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingAmountEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingAmountEvents> getType() {
		return FloatingAmountEvents.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("failureToPayPrincipal"), Boolean.class, getFailureToPayPrincipal(), this);
		processRosetta(path.newSubPath("interestShortfall"), processor, InterestShortFall.class, getInterestShortfall());
		processor.processBasic(path.newSubPath("writedown"), Boolean.class, getWritedown(), this);
		processor.processBasic(path.newSubPath("impliedWritedown"), Boolean.class, getImpliedWritedown(), this);
		processRosetta(path.newSubPath("floatingAmountProvisions"), processor, FloatingAmountProvisions.class, getFloatingAmountProvisions());
		processRosetta(path.newSubPath("additionalFixedPayments"), processor, AdditionalFixedPayments.class, getAdditionalFixedPayments());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingAmountEventsBuilder extends FloatingAmountEvents, RosettaModelObjectBuilder {
		InterestShortFall.InterestShortFallBuilder getOrCreateInterestShortfall();
		InterestShortFall.InterestShortFallBuilder getInterestShortfall();
		FloatingAmountProvisions.FloatingAmountProvisionsBuilder getOrCreateFloatingAmountProvisions();
		FloatingAmountProvisions.FloatingAmountProvisionsBuilder getFloatingAmountProvisions();
		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder getOrCreateAdditionalFixedPayments();
		AdditionalFixedPayments.AdditionalFixedPaymentsBuilder getAdditionalFixedPayments();
		FloatingAmountEvents.FloatingAmountEventsBuilder setFailureToPayPrincipal(Boolean failureToPayPrincipal);
		FloatingAmountEvents.FloatingAmountEventsBuilder setInterestShortfall(InterestShortFall interestShortfall);
		FloatingAmountEvents.FloatingAmountEventsBuilder setWritedown(Boolean writedown);
		FloatingAmountEvents.FloatingAmountEventsBuilder setImpliedWritedown(Boolean impliedWritedown);
		FloatingAmountEvents.FloatingAmountEventsBuilder setFloatingAmountProvisions(FloatingAmountProvisions floatingAmountProvisions);
		FloatingAmountEvents.FloatingAmountEventsBuilder setAdditionalFixedPayments(AdditionalFixedPayments additionalFixedPayments);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("failureToPayPrincipal"), Boolean.class, getFailureToPayPrincipal(), this);
			processRosetta(path.newSubPath("interestShortfall"), processor, InterestShortFall.InterestShortFallBuilder.class, getInterestShortfall());
			processor.processBasic(path.newSubPath("writedown"), Boolean.class, getWritedown(), this);
			processor.processBasic(path.newSubPath("impliedWritedown"), Boolean.class, getImpliedWritedown(), this);
			processRosetta(path.newSubPath("floatingAmountProvisions"), processor, FloatingAmountProvisions.FloatingAmountProvisionsBuilder.class, getFloatingAmountProvisions());
			processRosetta(path.newSubPath("additionalFixedPayments"), processor, AdditionalFixedPayments.AdditionalFixedPaymentsBuilder.class, getAdditionalFixedPayments());
		}
		

		FloatingAmountEvents.FloatingAmountEventsBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingAmountEvents  ***********************/
	class FloatingAmountEventsImpl implements FloatingAmountEvents {
		private final Boolean failureToPayPrincipal;
		private final InterestShortFall interestShortfall;
		private final Boolean writedown;
		private final Boolean impliedWritedown;
		private final FloatingAmountProvisions floatingAmountProvisions;
		private final AdditionalFixedPayments additionalFixedPayments;
		
		protected FloatingAmountEventsImpl(FloatingAmountEvents.FloatingAmountEventsBuilder builder) {
			this.failureToPayPrincipal = builder.getFailureToPayPrincipal();
			this.interestShortfall = ofNullable(builder.getInterestShortfall()).map(f->f.build()).orElse(null);
			this.writedown = builder.getWritedown();
			this.impliedWritedown = builder.getImpliedWritedown();
			this.floatingAmountProvisions = ofNullable(builder.getFloatingAmountProvisions()).map(f->f.build()).orElse(null);
			this.additionalFixedPayments = ofNullable(builder.getAdditionalFixedPayments()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("failureToPayPrincipal")
		public Boolean getFailureToPayPrincipal() {
			return failureToPayPrincipal;
		}
		
		@Override
		@RosettaAttribute("interestShortfall")
		public InterestShortFall getInterestShortfall() {
			return interestShortfall;
		}
		
		@Override
		@RosettaAttribute("writedown")
		public Boolean getWritedown() {
			return writedown;
		}
		
		@Override
		@RosettaAttribute("impliedWritedown")
		public Boolean getImpliedWritedown() {
			return impliedWritedown;
		}
		
		@Override
		@RosettaAttribute("floatingAmountProvisions")
		public FloatingAmountProvisions getFloatingAmountProvisions() {
			return floatingAmountProvisions;
		}
		
		@Override
		@RosettaAttribute("additionalFixedPayments")
		public AdditionalFixedPayments getAdditionalFixedPayments() {
			return additionalFixedPayments;
		}
		
		@Override
		public FloatingAmountEvents build() {
			return this;
		}
		
		@Override
		public FloatingAmountEvents.FloatingAmountEventsBuilder toBuilder() {
			FloatingAmountEvents.FloatingAmountEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingAmountEvents.FloatingAmountEventsBuilder builder) {
			ofNullable(getFailureToPayPrincipal()).ifPresent(builder::setFailureToPayPrincipal);
			ofNullable(getInterestShortfall()).ifPresent(builder::setInterestShortfall);
			ofNullable(getWritedown()).ifPresent(builder::setWritedown);
			ofNullable(getImpliedWritedown()).ifPresent(builder::setImpliedWritedown);
			ofNullable(getFloatingAmountProvisions()).ifPresent(builder::setFloatingAmountProvisions);
			ofNullable(getAdditionalFixedPayments()).ifPresent(builder::setAdditionalFixedPayments);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountEvents _that = getType().cast(o);
		
			if (!Objects.equals(failureToPayPrincipal, _that.getFailureToPayPrincipal())) return false;
			if (!Objects.equals(interestShortfall, _that.getInterestShortfall())) return false;
			if (!Objects.equals(writedown, _that.getWritedown())) return false;
			if (!Objects.equals(impliedWritedown, _that.getImpliedWritedown())) return false;
			if (!Objects.equals(floatingAmountProvisions, _that.getFloatingAmountProvisions())) return false;
			if (!Objects.equals(additionalFixedPayments, _that.getAdditionalFixedPayments())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (failureToPayPrincipal != null ? failureToPayPrincipal.hashCode() : 0);
			_result = 31 * _result + (interestShortfall != null ? interestShortfall.hashCode() : 0);
			_result = 31 * _result + (writedown != null ? writedown.hashCode() : 0);
			_result = 31 * _result + (impliedWritedown != null ? impliedWritedown.hashCode() : 0);
			_result = 31 * _result + (floatingAmountProvisions != null ? floatingAmountProvisions.hashCode() : 0);
			_result = 31 * _result + (additionalFixedPayments != null ? additionalFixedPayments.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountEvents {" +
				"failureToPayPrincipal=" + this.failureToPayPrincipal + ", " +
				"interestShortfall=" + this.interestShortfall + ", " +
				"writedown=" + this.writedown + ", " +
				"impliedWritedown=" + this.impliedWritedown + ", " +
				"floatingAmountProvisions=" + this.floatingAmountProvisions + ", " +
				"additionalFixedPayments=" + this.additionalFixedPayments +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingAmountEvents  ***********************/
	class FloatingAmountEventsBuilderImpl implements FloatingAmountEvents.FloatingAmountEventsBuilder {
	
		protected Boolean failureToPayPrincipal;
		protected InterestShortFall.InterestShortFallBuilder interestShortfall;
		protected Boolean writedown;
		protected Boolean impliedWritedown;
		protected FloatingAmountProvisions.FloatingAmountProvisionsBuilder floatingAmountProvisions;
		protected AdditionalFixedPayments.AdditionalFixedPaymentsBuilder additionalFixedPayments;
	
		public FloatingAmountEventsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("failureToPayPrincipal")
		public Boolean getFailureToPayPrincipal() {
			return failureToPayPrincipal;
		}
		
		@Override
		@RosettaAttribute("interestShortfall")
		public InterestShortFall.InterestShortFallBuilder getInterestShortfall() {
			return interestShortfall;
		}
		
		@Override
		public InterestShortFall.InterestShortFallBuilder getOrCreateInterestShortfall() {
			InterestShortFall.InterestShortFallBuilder result;
			if (interestShortfall!=null) {
				result = interestShortfall;
			}
			else {
				result = interestShortfall = InterestShortFall.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("writedown")
		public Boolean getWritedown() {
			return writedown;
		}
		
		@Override
		@RosettaAttribute("impliedWritedown")
		public Boolean getImpliedWritedown() {
			return impliedWritedown;
		}
		
		@Override
		@RosettaAttribute("floatingAmountProvisions")
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder getFloatingAmountProvisions() {
			return floatingAmountProvisions;
		}
		
		@Override
		public FloatingAmountProvisions.FloatingAmountProvisionsBuilder getOrCreateFloatingAmountProvisions() {
			FloatingAmountProvisions.FloatingAmountProvisionsBuilder result;
			if (floatingAmountProvisions!=null) {
				result = floatingAmountProvisions;
			}
			else {
				result = floatingAmountProvisions = FloatingAmountProvisions.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("additionalFixedPayments")
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder getAdditionalFixedPayments() {
			return additionalFixedPayments;
		}
		
		@Override
		public AdditionalFixedPayments.AdditionalFixedPaymentsBuilder getOrCreateAdditionalFixedPayments() {
			AdditionalFixedPayments.AdditionalFixedPaymentsBuilder result;
			if (additionalFixedPayments!=null) {
				result = additionalFixedPayments;
			}
			else {
				result = additionalFixedPayments = AdditionalFixedPayments.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("failureToPayPrincipal")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setFailureToPayPrincipal(Boolean failureToPayPrincipal) {
			this.failureToPayPrincipal = failureToPayPrincipal==null?null:failureToPayPrincipal;
			return this;
		}
		@Override
		@RosettaAttribute("interestShortfall")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setInterestShortfall(InterestShortFall interestShortfall) {
			this.interestShortfall = interestShortfall==null?null:interestShortfall.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("writedown")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setWritedown(Boolean writedown) {
			this.writedown = writedown==null?null:writedown;
			return this;
		}
		@Override
		@RosettaAttribute("impliedWritedown")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setImpliedWritedown(Boolean impliedWritedown) {
			this.impliedWritedown = impliedWritedown==null?null:impliedWritedown;
			return this;
		}
		@Override
		@RosettaAttribute("floatingAmountProvisions")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setFloatingAmountProvisions(FloatingAmountProvisions floatingAmountProvisions) {
			this.floatingAmountProvisions = floatingAmountProvisions==null?null:floatingAmountProvisions.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("additionalFixedPayments")
		public FloatingAmountEvents.FloatingAmountEventsBuilder setAdditionalFixedPayments(AdditionalFixedPayments additionalFixedPayments) {
			this.additionalFixedPayments = additionalFixedPayments==null?null:additionalFixedPayments.toBuilder();
			return this;
		}
		
		@Override
		public FloatingAmountEvents build() {
			return new FloatingAmountEvents.FloatingAmountEventsImpl(this);
		}
		
		@Override
		public FloatingAmountEvents.FloatingAmountEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountEvents.FloatingAmountEventsBuilder prune() {
			if (interestShortfall!=null && !interestShortfall.prune().hasData()) interestShortfall = null;
			if (floatingAmountProvisions!=null && !floatingAmountProvisions.prune().hasData()) floatingAmountProvisions = null;
			if (additionalFixedPayments!=null && !additionalFixedPayments.prune().hasData()) additionalFixedPayments = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFailureToPayPrincipal()!=null) return true;
			if (getInterestShortfall()!=null && getInterestShortfall().hasData()) return true;
			if (getWritedown()!=null) return true;
			if (getImpliedWritedown()!=null) return true;
			if (getFloatingAmountProvisions()!=null && getFloatingAmountProvisions().hasData()) return true;
			if (getAdditionalFixedPayments()!=null && getAdditionalFixedPayments().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountEvents.FloatingAmountEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingAmountEvents.FloatingAmountEventsBuilder o = (FloatingAmountEvents.FloatingAmountEventsBuilder) other;
			
			merger.mergeRosetta(getInterestShortfall(), o.getInterestShortfall(), this::setInterestShortfall);
			merger.mergeRosetta(getFloatingAmountProvisions(), o.getFloatingAmountProvisions(), this::setFloatingAmountProvisions);
			merger.mergeRosetta(getAdditionalFixedPayments(), o.getAdditionalFixedPayments(), this::setAdditionalFixedPayments);
			
			merger.mergeBasic(getFailureToPayPrincipal(), o.getFailureToPayPrincipal(), this::setFailureToPayPrincipal);
			merger.mergeBasic(getWritedown(), o.getWritedown(), this::setWritedown);
			merger.mergeBasic(getImpliedWritedown(), o.getImpliedWritedown(), this::setImpliedWritedown);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountEvents _that = getType().cast(o);
		
			if (!Objects.equals(failureToPayPrincipal, _that.getFailureToPayPrincipal())) return false;
			if (!Objects.equals(interestShortfall, _that.getInterestShortfall())) return false;
			if (!Objects.equals(writedown, _that.getWritedown())) return false;
			if (!Objects.equals(impliedWritedown, _that.getImpliedWritedown())) return false;
			if (!Objects.equals(floatingAmountProvisions, _that.getFloatingAmountProvisions())) return false;
			if (!Objects.equals(additionalFixedPayments, _that.getAdditionalFixedPayments())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (failureToPayPrincipal != null ? failureToPayPrincipal.hashCode() : 0);
			_result = 31 * _result + (interestShortfall != null ? interestShortfall.hashCode() : 0);
			_result = 31 * _result + (writedown != null ? writedown.hashCode() : 0);
			_result = 31 * _result + (impliedWritedown != null ? impliedWritedown.hashCode() : 0);
			_result = 31 * _result + (floatingAmountProvisions != null ? floatingAmountProvisions.hashCode() : 0);
			_result = 31 * _result + (additionalFixedPayments != null ? additionalFixedPayments.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountEventsBuilder {" +
				"failureToPayPrincipal=" + this.failureToPayPrincipal + ", " +
				"interestShortfall=" + this.interestShortfall + ", " +
				"writedown=" + this.writedown + ", " +
				"impliedWritedown=" + this.impliedWritedown + ", " +
				"floatingAmountProvisions=" + this.floatingAmountProvisions + ", " +
				"additionalFixedPayments=" + this.additionalFixedPayments +
			'}';
		}
	}
}
