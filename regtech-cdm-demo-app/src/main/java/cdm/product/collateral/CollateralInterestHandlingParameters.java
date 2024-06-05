package cdm.product.collateral;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.math.NumberBound;
import cdm.product.collateral.AlternativeToInterestAmountEnum;
import cdm.product.collateral.CollateralInterestHandlingEnum;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder;
import cdm.product.collateral.CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilderImpl;
import cdm.product.collateral.CollateralInterestHandlingParameters.CollateralInterestHandlingParametersImpl;
import cdm.product.collateral.CollateralInterestNotification;
import cdm.product.collateral.InterestAmountApplication;
import cdm.product.collateral.meta.CollateralInterestHandlingParametersMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents parameters that describe how calculated interest amounts are handled, i.e. are they transferred/distributed, or is the collateral balance adjusted, is netting done, and any other special handling.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralInterestHandlingParameters", builder=CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilderImpl.class, version="${project.version}")
public interface CollateralInterestHandlingParameters extends RosettaModelObject {

	CollateralInterestHandlingParametersMeta metaData = new CollateralInterestHandlingParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies how the collateral interest is to be handled.
	 */
	CollateralInterestHandlingEnum getInterestPaymentHandling();
	/**
	 * Specifies applicable business centers for payments.
	 */
	List<BusinessCenterEnum> getPaymentBusinessCenter();
	/**
	 * Indicates whether to net Held and Posted Interest Payments (i.e. whether interest payable for a period can be netted with interest receivable).
	 */
	Boolean getNetPostedAndHeldInterest();
	/**
	 * Indicates whether the interest amount may be offset against any margin call deliver or return amounts?   (aka &#39;net payments&#39; indicator).
	 */
	Boolean getNetInterestWithMarginCalls();
	/**
	 * Indicates whether or not to include the open interest accrual in the margin calculation.
	 */
	Boolean getIncludeAccrualInMarginCalc();
	/**
	 * Indicates whether interest accruing on unsettled interest amount is included (continues to be accrued) in the following period.
	 */
	Boolean getAccrueInterestOnUnsettledInterest();
	/**
	 * Indicates the option that accrued interest should be calculated and distributed when a full return of collateral occurs.
	 */
	Boolean getOnFullReturn();
	/**
	 * Indicates the option that accrued interest should be calculated and distributed when a partial return collateral occurs.
	 */
	Boolean getOnPartialReturn();
	/**
	 * The application of Interest Amount with respect to the Delivery Amount and the Return Amount.
	 */
	InterestAmountApplication getInterestAmountApplication();
	/**
	 * Specifies the level below which the interest will be rolled over.
	 */
	NumberBound getInterestRolloverLimit();
	/**
	 * Specifies the level below which the interest will be written off; if omitted write-off is not applicable.
	 */
	NumberBound getWriteoffLimit();
	/**
	 * Specifies the alternative to interest amounts.
	 */
	AlternativeToInterestAmountEnum getAlternativeToInterestAmount();
	/**
	 * Specifies an alternative to interest amount, when the alternative provision clause is specified.
	 */
	String getAlternativeProvision();
	/**
	 * Specifies the time of day that interest needs to be confirmed by.
	 */
	LocalTime getCutoffTime();
	/**
	 * Specifies the terms describing notification requirements.
	 */
	CollateralInterestNotification getNotification();

	/*********************** Build Methods  ***********************/
	CollateralInterestHandlingParameters build();
	
	CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder toBuilder();
	
	static CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder builder() {
		return new CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralInterestHandlingParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralInterestHandlingParameters> getType() {
		return CollateralInterestHandlingParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("interestPaymentHandling"), CollateralInterestHandlingEnum.class, getInterestPaymentHandling(), this);
		processor.processBasic(path.newSubPath("paymentBusinessCenter"), BusinessCenterEnum.class, getPaymentBusinessCenter(), this);
		processor.processBasic(path.newSubPath("netPostedAndHeldInterest"), Boolean.class, getNetPostedAndHeldInterest(), this);
		processor.processBasic(path.newSubPath("netInterestWithMarginCalls"), Boolean.class, getNetInterestWithMarginCalls(), this);
		processor.processBasic(path.newSubPath("includeAccrualInMarginCalc"), Boolean.class, getIncludeAccrualInMarginCalc(), this);
		processor.processBasic(path.newSubPath("accrueInterestOnUnsettledInterest"), Boolean.class, getAccrueInterestOnUnsettledInterest(), this);
		processor.processBasic(path.newSubPath("onFullReturn"), Boolean.class, getOnFullReturn(), this);
		processor.processBasic(path.newSubPath("onPartialReturn"), Boolean.class, getOnPartialReturn(), this);
		processRosetta(path.newSubPath("interestAmountApplication"), processor, InterestAmountApplication.class, getInterestAmountApplication());
		processRosetta(path.newSubPath("interestRolloverLimit"), processor, NumberBound.class, getInterestRolloverLimit());
		processRosetta(path.newSubPath("writeoffLimit"), processor, NumberBound.class, getWriteoffLimit());
		processor.processBasic(path.newSubPath("alternativeToInterestAmount"), AlternativeToInterestAmountEnum.class, getAlternativeToInterestAmount(), this);
		processor.processBasic(path.newSubPath("alternativeProvision"), String.class, getAlternativeProvision(), this);
		processor.processBasic(path.newSubPath("cutoffTime"), LocalTime.class, getCutoffTime(), this);
		processRosetta(path.newSubPath("notification"), processor, CollateralInterestNotification.class, getNotification());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralInterestHandlingParametersBuilder extends CollateralInterestHandlingParameters, RosettaModelObjectBuilder {
		InterestAmountApplication.InterestAmountApplicationBuilder getOrCreateInterestAmountApplication();
		InterestAmountApplication.InterestAmountApplicationBuilder getInterestAmountApplication();
		NumberBound.NumberBoundBuilder getOrCreateInterestRolloverLimit();
		NumberBound.NumberBoundBuilder getInterestRolloverLimit();
		NumberBound.NumberBoundBuilder getOrCreateWriteoffLimit();
		NumberBound.NumberBoundBuilder getWriteoffLimit();
		CollateralInterestNotification.CollateralInterestNotificationBuilder getOrCreateNotification();
		CollateralInterestNotification.CollateralInterestNotificationBuilder getNotification();
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestPaymentHandling(CollateralInterestHandlingEnum interestPaymentHandling);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(BusinessCenterEnum paymentBusinessCenter0);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(BusinessCenterEnum paymentBusinessCenter1, int _idx);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(List<? extends BusinessCenterEnum> paymentBusinessCenter2);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setPaymentBusinessCenter(List<? extends BusinessCenterEnum> paymentBusinessCenter3);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNetPostedAndHeldInterest(Boolean netPostedAndHeldInterest);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNetInterestWithMarginCalls(Boolean netInterestWithMarginCalls);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setIncludeAccrualInMarginCalc(Boolean includeAccrualInMarginCalc);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAccrueInterestOnUnsettledInterest(Boolean accrueInterestOnUnsettledInterest);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setOnFullReturn(Boolean onFullReturn);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setOnPartialReturn(Boolean onPartialReturn);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestAmountApplication(InterestAmountApplication interestAmountApplication);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestRolloverLimit(NumberBound interestRolloverLimit);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setWriteoffLimit(NumberBound writeoffLimit);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAlternativeToInterestAmount(AlternativeToInterestAmountEnum alternativeToInterestAmount);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAlternativeProvision(String alternativeProvision);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setCutoffTime(LocalTime cutoffTime);
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNotification(CollateralInterestNotification notification);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("interestPaymentHandling"), CollateralInterestHandlingEnum.class, getInterestPaymentHandling(), this);
			processor.processBasic(path.newSubPath("paymentBusinessCenter"), BusinessCenterEnum.class, getPaymentBusinessCenter(), this);
			processor.processBasic(path.newSubPath("netPostedAndHeldInterest"), Boolean.class, getNetPostedAndHeldInterest(), this);
			processor.processBasic(path.newSubPath("netInterestWithMarginCalls"), Boolean.class, getNetInterestWithMarginCalls(), this);
			processor.processBasic(path.newSubPath("includeAccrualInMarginCalc"), Boolean.class, getIncludeAccrualInMarginCalc(), this);
			processor.processBasic(path.newSubPath("accrueInterestOnUnsettledInterest"), Boolean.class, getAccrueInterestOnUnsettledInterest(), this);
			processor.processBasic(path.newSubPath("onFullReturn"), Boolean.class, getOnFullReturn(), this);
			processor.processBasic(path.newSubPath("onPartialReturn"), Boolean.class, getOnPartialReturn(), this);
			processRosetta(path.newSubPath("interestAmountApplication"), processor, InterestAmountApplication.InterestAmountApplicationBuilder.class, getInterestAmountApplication());
			processRosetta(path.newSubPath("interestRolloverLimit"), processor, NumberBound.NumberBoundBuilder.class, getInterestRolloverLimit());
			processRosetta(path.newSubPath("writeoffLimit"), processor, NumberBound.NumberBoundBuilder.class, getWriteoffLimit());
			processor.processBasic(path.newSubPath("alternativeToInterestAmount"), AlternativeToInterestAmountEnum.class, getAlternativeToInterestAmount(), this);
			processor.processBasic(path.newSubPath("alternativeProvision"), String.class, getAlternativeProvision(), this);
			processor.processBasic(path.newSubPath("cutoffTime"), LocalTime.class, getCutoffTime(), this);
			processRosetta(path.newSubPath("notification"), processor, CollateralInterestNotification.CollateralInterestNotificationBuilder.class, getNotification());
		}
		

		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralInterestHandlingParameters  ***********************/
	class CollateralInterestHandlingParametersImpl implements CollateralInterestHandlingParameters {
		private final CollateralInterestHandlingEnum interestPaymentHandling;
		private final List<BusinessCenterEnum> paymentBusinessCenter;
		private final Boolean netPostedAndHeldInterest;
		private final Boolean netInterestWithMarginCalls;
		private final Boolean includeAccrualInMarginCalc;
		private final Boolean accrueInterestOnUnsettledInterest;
		private final Boolean onFullReturn;
		private final Boolean onPartialReturn;
		private final InterestAmountApplication interestAmountApplication;
		private final NumberBound interestRolloverLimit;
		private final NumberBound writeoffLimit;
		private final AlternativeToInterestAmountEnum alternativeToInterestAmount;
		private final String alternativeProvision;
		private final LocalTime cutoffTime;
		private final CollateralInterestNotification notification;
		
		protected CollateralInterestHandlingParametersImpl(CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder builder) {
			this.interestPaymentHandling = builder.getInterestPaymentHandling();
			this.paymentBusinessCenter = ofNullable(builder.getPaymentBusinessCenter()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.netPostedAndHeldInterest = builder.getNetPostedAndHeldInterest();
			this.netInterestWithMarginCalls = builder.getNetInterestWithMarginCalls();
			this.includeAccrualInMarginCalc = builder.getIncludeAccrualInMarginCalc();
			this.accrueInterestOnUnsettledInterest = builder.getAccrueInterestOnUnsettledInterest();
			this.onFullReturn = builder.getOnFullReturn();
			this.onPartialReturn = builder.getOnPartialReturn();
			this.interestAmountApplication = ofNullable(builder.getInterestAmountApplication()).map(f->f.build()).orElse(null);
			this.interestRolloverLimit = ofNullable(builder.getInterestRolloverLimit()).map(f->f.build()).orElse(null);
			this.writeoffLimit = ofNullable(builder.getWriteoffLimit()).map(f->f.build()).orElse(null);
			this.alternativeToInterestAmount = builder.getAlternativeToInterestAmount();
			this.alternativeProvision = builder.getAlternativeProvision();
			this.cutoffTime = builder.getCutoffTime();
			this.notification = ofNullable(builder.getNotification()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("interestPaymentHandling")
		public CollateralInterestHandlingEnum getInterestPaymentHandling() {
			return interestPaymentHandling;
		}
		
		@Override
		@RosettaAttribute("paymentBusinessCenter")
		public List<BusinessCenterEnum> getPaymentBusinessCenter() {
			return paymentBusinessCenter;
		}
		
		@Override
		@RosettaAttribute("netPostedAndHeldInterest")
		public Boolean getNetPostedAndHeldInterest() {
			return netPostedAndHeldInterest;
		}
		
		@Override
		@RosettaAttribute("netInterestWithMarginCalls")
		public Boolean getNetInterestWithMarginCalls() {
			return netInterestWithMarginCalls;
		}
		
		@Override
		@RosettaAttribute("includeAccrualInMarginCalc")
		public Boolean getIncludeAccrualInMarginCalc() {
			return includeAccrualInMarginCalc;
		}
		
		@Override
		@RosettaAttribute("accrueInterestOnUnsettledInterest")
		public Boolean getAccrueInterestOnUnsettledInterest() {
			return accrueInterestOnUnsettledInterest;
		}
		
		@Override
		@RosettaAttribute("onFullReturn")
		public Boolean getOnFullReturn() {
			return onFullReturn;
		}
		
		@Override
		@RosettaAttribute("onPartialReturn")
		public Boolean getOnPartialReturn() {
			return onPartialReturn;
		}
		
		@Override
		@RosettaAttribute("interestAmountApplication")
		public InterestAmountApplication getInterestAmountApplication() {
			return interestAmountApplication;
		}
		
		@Override
		@RosettaAttribute("interestRolloverLimit")
		public NumberBound getInterestRolloverLimit() {
			return interestRolloverLimit;
		}
		
		@Override
		@RosettaAttribute("writeoffLimit")
		public NumberBound getWriteoffLimit() {
			return writeoffLimit;
		}
		
		@Override
		@RosettaAttribute("alternativeToInterestAmount")
		public AlternativeToInterestAmountEnum getAlternativeToInterestAmount() {
			return alternativeToInterestAmount;
		}
		
		@Override
		@RosettaAttribute("alternativeProvision")
		public String getAlternativeProvision() {
			return alternativeProvision;
		}
		
		@Override
		@RosettaAttribute("cutoffTime")
		public LocalTime getCutoffTime() {
			return cutoffTime;
		}
		
		@Override
		@RosettaAttribute("notification")
		public CollateralInterestNotification getNotification() {
			return notification;
		}
		
		@Override
		public CollateralInterestHandlingParameters build() {
			return this;
		}
		
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder toBuilder() {
			CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder builder) {
			ofNullable(getInterestPaymentHandling()).ifPresent(builder::setInterestPaymentHandling);
			ofNullable(getPaymentBusinessCenter()).ifPresent(builder::setPaymentBusinessCenter);
			ofNullable(getNetPostedAndHeldInterest()).ifPresent(builder::setNetPostedAndHeldInterest);
			ofNullable(getNetInterestWithMarginCalls()).ifPresent(builder::setNetInterestWithMarginCalls);
			ofNullable(getIncludeAccrualInMarginCalc()).ifPresent(builder::setIncludeAccrualInMarginCalc);
			ofNullable(getAccrueInterestOnUnsettledInterest()).ifPresent(builder::setAccrueInterestOnUnsettledInterest);
			ofNullable(getOnFullReturn()).ifPresent(builder::setOnFullReturn);
			ofNullable(getOnPartialReturn()).ifPresent(builder::setOnPartialReturn);
			ofNullable(getInterestAmountApplication()).ifPresent(builder::setInterestAmountApplication);
			ofNullable(getInterestRolloverLimit()).ifPresent(builder::setInterestRolloverLimit);
			ofNullable(getWriteoffLimit()).ifPresent(builder::setWriteoffLimit);
			ofNullable(getAlternativeToInterestAmount()).ifPresent(builder::setAlternativeToInterestAmount);
			ofNullable(getAlternativeProvision()).ifPresent(builder::setAlternativeProvision);
			ofNullable(getCutoffTime()).ifPresent(builder::setCutoffTime);
			ofNullable(getNotification()).ifPresent(builder::setNotification);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestHandlingParameters _that = getType().cast(o);
		
			if (!Objects.equals(interestPaymentHandling, _that.getInterestPaymentHandling())) return false;
			if (!ListEquals.listEquals(paymentBusinessCenter, _that.getPaymentBusinessCenter())) return false;
			if (!Objects.equals(netPostedAndHeldInterest, _that.getNetPostedAndHeldInterest())) return false;
			if (!Objects.equals(netInterestWithMarginCalls, _that.getNetInterestWithMarginCalls())) return false;
			if (!Objects.equals(includeAccrualInMarginCalc, _that.getIncludeAccrualInMarginCalc())) return false;
			if (!Objects.equals(accrueInterestOnUnsettledInterest, _that.getAccrueInterestOnUnsettledInterest())) return false;
			if (!Objects.equals(onFullReturn, _that.getOnFullReturn())) return false;
			if (!Objects.equals(onPartialReturn, _that.getOnPartialReturn())) return false;
			if (!Objects.equals(interestAmountApplication, _that.getInterestAmountApplication())) return false;
			if (!Objects.equals(interestRolloverLimit, _that.getInterestRolloverLimit())) return false;
			if (!Objects.equals(writeoffLimit, _that.getWriteoffLimit())) return false;
			if (!Objects.equals(alternativeToInterestAmount, _that.getAlternativeToInterestAmount())) return false;
			if (!Objects.equals(alternativeProvision, _that.getAlternativeProvision())) return false;
			if (!Objects.equals(cutoffTime, _that.getCutoffTime())) return false;
			if (!Objects.equals(notification, _that.getNotification())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestPaymentHandling != null ? interestPaymentHandling.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentBusinessCenter != null ? paymentBusinessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (netPostedAndHeldInterest != null ? netPostedAndHeldInterest.hashCode() : 0);
			_result = 31 * _result + (netInterestWithMarginCalls != null ? netInterestWithMarginCalls.hashCode() : 0);
			_result = 31 * _result + (includeAccrualInMarginCalc != null ? includeAccrualInMarginCalc.hashCode() : 0);
			_result = 31 * _result + (accrueInterestOnUnsettledInterest != null ? accrueInterestOnUnsettledInterest.hashCode() : 0);
			_result = 31 * _result + (onFullReturn != null ? onFullReturn.hashCode() : 0);
			_result = 31 * _result + (onPartialReturn != null ? onPartialReturn.hashCode() : 0);
			_result = 31 * _result + (interestAmountApplication != null ? interestAmountApplication.hashCode() : 0);
			_result = 31 * _result + (interestRolloverLimit != null ? interestRolloverLimit.hashCode() : 0);
			_result = 31 * _result + (writeoffLimit != null ? writeoffLimit.hashCode() : 0);
			_result = 31 * _result + (alternativeToInterestAmount != null ? alternativeToInterestAmount.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (alternativeProvision != null ? alternativeProvision.hashCode() : 0);
			_result = 31 * _result + (cutoffTime != null ? cutoffTime.hashCode() : 0);
			_result = 31 * _result + (notification != null ? notification.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestHandlingParameters {" +
				"interestPaymentHandling=" + this.interestPaymentHandling + ", " +
				"paymentBusinessCenter=" + this.paymentBusinessCenter + ", " +
				"netPostedAndHeldInterest=" + this.netPostedAndHeldInterest + ", " +
				"netInterestWithMarginCalls=" + this.netInterestWithMarginCalls + ", " +
				"includeAccrualInMarginCalc=" + this.includeAccrualInMarginCalc + ", " +
				"accrueInterestOnUnsettledInterest=" + this.accrueInterestOnUnsettledInterest + ", " +
				"onFullReturn=" + this.onFullReturn + ", " +
				"onPartialReturn=" + this.onPartialReturn + ", " +
				"interestAmountApplication=" + this.interestAmountApplication + ", " +
				"interestRolloverLimit=" + this.interestRolloverLimit + ", " +
				"writeoffLimit=" + this.writeoffLimit + ", " +
				"alternativeToInterestAmount=" + this.alternativeToInterestAmount + ", " +
				"alternativeProvision=" + this.alternativeProvision + ", " +
				"cutoffTime=" + this.cutoffTime + ", " +
				"notification=" + this.notification +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralInterestHandlingParameters  ***********************/
	class CollateralInterestHandlingParametersBuilderImpl implements CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder {
	
		protected CollateralInterestHandlingEnum interestPaymentHandling;
		protected List<BusinessCenterEnum> paymentBusinessCenter = new ArrayList<>();
		protected Boolean netPostedAndHeldInterest;
		protected Boolean netInterestWithMarginCalls;
		protected Boolean includeAccrualInMarginCalc;
		protected Boolean accrueInterestOnUnsettledInterest;
		protected Boolean onFullReturn;
		protected Boolean onPartialReturn;
		protected InterestAmountApplication.InterestAmountApplicationBuilder interestAmountApplication;
		protected NumberBound.NumberBoundBuilder interestRolloverLimit;
		protected NumberBound.NumberBoundBuilder writeoffLimit;
		protected AlternativeToInterestAmountEnum alternativeToInterestAmount;
		protected String alternativeProvision;
		protected LocalTime cutoffTime;
		protected CollateralInterestNotification.CollateralInterestNotificationBuilder notification;
	
		public CollateralInterestHandlingParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interestPaymentHandling")
		public CollateralInterestHandlingEnum getInterestPaymentHandling() {
			return interestPaymentHandling;
		}
		
		@Override
		@RosettaAttribute("paymentBusinessCenter")
		public List<BusinessCenterEnum> getPaymentBusinessCenter() {
			return paymentBusinessCenter;
		}
		
		@Override
		@RosettaAttribute("netPostedAndHeldInterest")
		public Boolean getNetPostedAndHeldInterest() {
			return netPostedAndHeldInterest;
		}
		
		@Override
		@RosettaAttribute("netInterestWithMarginCalls")
		public Boolean getNetInterestWithMarginCalls() {
			return netInterestWithMarginCalls;
		}
		
		@Override
		@RosettaAttribute("includeAccrualInMarginCalc")
		public Boolean getIncludeAccrualInMarginCalc() {
			return includeAccrualInMarginCalc;
		}
		
		@Override
		@RosettaAttribute("accrueInterestOnUnsettledInterest")
		public Boolean getAccrueInterestOnUnsettledInterest() {
			return accrueInterestOnUnsettledInterest;
		}
		
		@Override
		@RosettaAttribute("onFullReturn")
		public Boolean getOnFullReturn() {
			return onFullReturn;
		}
		
		@Override
		@RosettaAttribute("onPartialReturn")
		public Boolean getOnPartialReturn() {
			return onPartialReturn;
		}
		
		@Override
		@RosettaAttribute("interestAmountApplication")
		public InterestAmountApplication.InterestAmountApplicationBuilder getInterestAmountApplication() {
			return interestAmountApplication;
		}
		
		@Override
		public InterestAmountApplication.InterestAmountApplicationBuilder getOrCreateInterestAmountApplication() {
			InterestAmountApplication.InterestAmountApplicationBuilder result;
			if (interestAmountApplication!=null) {
				result = interestAmountApplication;
			}
			else {
				result = interestAmountApplication = InterestAmountApplication.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interestRolloverLimit")
		public NumberBound.NumberBoundBuilder getInterestRolloverLimit() {
			return interestRolloverLimit;
		}
		
		@Override
		public NumberBound.NumberBoundBuilder getOrCreateInterestRolloverLimit() {
			NumberBound.NumberBoundBuilder result;
			if (interestRolloverLimit!=null) {
				result = interestRolloverLimit;
			}
			else {
				result = interestRolloverLimit = NumberBound.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("writeoffLimit")
		public NumberBound.NumberBoundBuilder getWriteoffLimit() {
			return writeoffLimit;
		}
		
		@Override
		public NumberBound.NumberBoundBuilder getOrCreateWriteoffLimit() {
			NumberBound.NumberBoundBuilder result;
			if (writeoffLimit!=null) {
				result = writeoffLimit;
			}
			else {
				result = writeoffLimit = NumberBound.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("alternativeToInterestAmount")
		public AlternativeToInterestAmountEnum getAlternativeToInterestAmount() {
			return alternativeToInterestAmount;
		}
		
		@Override
		@RosettaAttribute("alternativeProvision")
		public String getAlternativeProvision() {
			return alternativeProvision;
		}
		
		@Override
		@RosettaAttribute("cutoffTime")
		public LocalTime getCutoffTime() {
			return cutoffTime;
		}
		
		@Override
		@RosettaAttribute("notification")
		public CollateralInterestNotification.CollateralInterestNotificationBuilder getNotification() {
			return notification;
		}
		
		@Override
		public CollateralInterestNotification.CollateralInterestNotificationBuilder getOrCreateNotification() {
			CollateralInterestNotification.CollateralInterestNotificationBuilder result;
			if (notification!=null) {
				result = notification;
			}
			else {
				result = notification = CollateralInterestNotification.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("interestPaymentHandling")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestPaymentHandling(CollateralInterestHandlingEnum interestPaymentHandling) {
			this.interestPaymentHandling = interestPaymentHandling==null?null:interestPaymentHandling;
			return this;
		}
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(BusinessCenterEnum paymentBusinessCenter) {
			if (paymentBusinessCenter!=null) this.paymentBusinessCenter.add(paymentBusinessCenter);
			return this;
		}
		
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(BusinessCenterEnum paymentBusinessCenter, int _idx) {
			getIndex(this.paymentBusinessCenter, _idx, () -> paymentBusinessCenter);
			return this;
		}
		@Override 
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder addPaymentBusinessCenter(List<? extends BusinessCenterEnum> paymentBusinessCenters) {
			if (paymentBusinessCenters != null) {
				for (BusinessCenterEnum toAdd : paymentBusinessCenters) {
					this.paymentBusinessCenter.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("paymentBusinessCenter")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setPaymentBusinessCenter(List<? extends BusinessCenterEnum> paymentBusinessCenters) {
			if (paymentBusinessCenters == null)  {
				this.paymentBusinessCenter = new ArrayList<>();
			}
			else {
				this.paymentBusinessCenter = paymentBusinessCenters.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("netPostedAndHeldInterest")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNetPostedAndHeldInterest(Boolean netPostedAndHeldInterest) {
			this.netPostedAndHeldInterest = netPostedAndHeldInterest==null?null:netPostedAndHeldInterest;
			return this;
		}
		@Override
		@RosettaAttribute("netInterestWithMarginCalls")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNetInterestWithMarginCalls(Boolean netInterestWithMarginCalls) {
			this.netInterestWithMarginCalls = netInterestWithMarginCalls==null?null:netInterestWithMarginCalls;
			return this;
		}
		@Override
		@RosettaAttribute("includeAccrualInMarginCalc")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setIncludeAccrualInMarginCalc(Boolean includeAccrualInMarginCalc) {
			this.includeAccrualInMarginCalc = includeAccrualInMarginCalc==null?null:includeAccrualInMarginCalc;
			return this;
		}
		@Override
		@RosettaAttribute("accrueInterestOnUnsettledInterest")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAccrueInterestOnUnsettledInterest(Boolean accrueInterestOnUnsettledInterest) {
			this.accrueInterestOnUnsettledInterest = accrueInterestOnUnsettledInterest==null?null:accrueInterestOnUnsettledInterest;
			return this;
		}
		@Override
		@RosettaAttribute("onFullReturn")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setOnFullReturn(Boolean onFullReturn) {
			this.onFullReturn = onFullReturn==null?null:onFullReturn;
			return this;
		}
		@Override
		@RosettaAttribute("onPartialReturn")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setOnPartialReturn(Boolean onPartialReturn) {
			this.onPartialReturn = onPartialReturn==null?null:onPartialReturn;
			return this;
		}
		@Override
		@RosettaAttribute("interestAmountApplication")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestAmountApplication(InterestAmountApplication interestAmountApplication) {
			this.interestAmountApplication = interestAmountApplication==null?null:interestAmountApplication.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("interestRolloverLimit")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setInterestRolloverLimit(NumberBound interestRolloverLimit) {
			this.interestRolloverLimit = interestRolloverLimit==null?null:interestRolloverLimit.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("writeoffLimit")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setWriteoffLimit(NumberBound writeoffLimit) {
			this.writeoffLimit = writeoffLimit==null?null:writeoffLimit.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("alternativeToInterestAmount")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAlternativeToInterestAmount(AlternativeToInterestAmountEnum alternativeToInterestAmount) {
			this.alternativeToInterestAmount = alternativeToInterestAmount==null?null:alternativeToInterestAmount;
			return this;
		}
		@Override
		@RosettaAttribute("alternativeProvision")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setAlternativeProvision(String alternativeProvision) {
			this.alternativeProvision = alternativeProvision==null?null:alternativeProvision;
			return this;
		}
		@Override
		@RosettaAttribute("cutoffTime")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setCutoffTime(LocalTime cutoffTime) {
			this.cutoffTime = cutoffTime==null?null:cutoffTime;
			return this;
		}
		@Override
		@RosettaAttribute("notification")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder setNotification(CollateralInterestNotification notification) {
			this.notification = notification==null?null:notification.toBuilder();
			return this;
		}
		
		@Override
		public CollateralInterestHandlingParameters build() {
			return new CollateralInterestHandlingParameters.CollateralInterestHandlingParametersImpl(this);
		}
		
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder prune() {
			if (interestAmountApplication!=null && !interestAmountApplication.prune().hasData()) interestAmountApplication = null;
			if (interestRolloverLimit!=null && !interestRolloverLimit.prune().hasData()) interestRolloverLimit = null;
			if (writeoffLimit!=null && !writeoffLimit.prune().hasData()) writeoffLimit = null;
			if (notification!=null && !notification.prune().hasData()) notification = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterestPaymentHandling()!=null) return true;
			if (getPaymentBusinessCenter()!=null && !getPaymentBusinessCenter().isEmpty()) return true;
			if (getNetPostedAndHeldInterest()!=null) return true;
			if (getNetInterestWithMarginCalls()!=null) return true;
			if (getIncludeAccrualInMarginCalc()!=null) return true;
			if (getAccrueInterestOnUnsettledInterest()!=null) return true;
			if (getOnFullReturn()!=null) return true;
			if (getOnPartialReturn()!=null) return true;
			if (getInterestAmountApplication()!=null && getInterestAmountApplication().hasData()) return true;
			if (getInterestRolloverLimit()!=null && getInterestRolloverLimit().hasData()) return true;
			if (getWriteoffLimit()!=null && getWriteoffLimit().hasData()) return true;
			if (getAlternativeToInterestAmount()!=null) return true;
			if (getAlternativeProvision()!=null) return true;
			if (getCutoffTime()!=null) return true;
			if (getNotification()!=null && getNotification().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder o = (CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder) other;
			
			merger.mergeRosetta(getInterestAmountApplication(), o.getInterestAmountApplication(), this::setInterestAmountApplication);
			merger.mergeRosetta(getInterestRolloverLimit(), o.getInterestRolloverLimit(), this::setInterestRolloverLimit);
			merger.mergeRosetta(getWriteoffLimit(), o.getWriteoffLimit(), this::setWriteoffLimit);
			merger.mergeRosetta(getNotification(), o.getNotification(), this::setNotification);
			
			merger.mergeBasic(getInterestPaymentHandling(), o.getInterestPaymentHandling(), this::setInterestPaymentHandling);
			merger.mergeBasic(getPaymentBusinessCenter(), o.getPaymentBusinessCenter(), (Consumer<BusinessCenterEnum>) this::addPaymentBusinessCenter);
			merger.mergeBasic(getNetPostedAndHeldInterest(), o.getNetPostedAndHeldInterest(), this::setNetPostedAndHeldInterest);
			merger.mergeBasic(getNetInterestWithMarginCalls(), o.getNetInterestWithMarginCalls(), this::setNetInterestWithMarginCalls);
			merger.mergeBasic(getIncludeAccrualInMarginCalc(), o.getIncludeAccrualInMarginCalc(), this::setIncludeAccrualInMarginCalc);
			merger.mergeBasic(getAccrueInterestOnUnsettledInterest(), o.getAccrueInterestOnUnsettledInterest(), this::setAccrueInterestOnUnsettledInterest);
			merger.mergeBasic(getOnFullReturn(), o.getOnFullReturn(), this::setOnFullReturn);
			merger.mergeBasic(getOnPartialReturn(), o.getOnPartialReturn(), this::setOnPartialReturn);
			merger.mergeBasic(getAlternativeToInterestAmount(), o.getAlternativeToInterestAmount(), this::setAlternativeToInterestAmount);
			merger.mergeBasic(getAlternativeProvision(), o.getAlternativeProvision(), this::setAlternativeProvision);
			merger.mergeBasic(getCutoffTime(), o.getCutoffTime(), this::setCutoffTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestHandlingParameters _that = getType().cast(o);
		
			if (!Objects.equals(interestPaymentHandling, _that.getInterestPaymentHandling())) return false;
			if (!ListEquals.listEquals(paymentBusinessCenter, _that.getPaymentBusinessCenter())) return false;
			if (!Objects.equals(netPostedAndHeldInterest, _that.getNetPostedAndHeldInterest())) return false;
			if (!Objects.equals(netInterestWithMarginCalls, _that.getNetInterestWithMarginCalls())) return false;
			if (!Objects.equals(includeAccrualInMarginCalc, _that.getIncludeAccrualInMarginCalc())) return false;
			if (!Objects.equals(accrueInterestOnUnsettledInterest, _that.getAccrueInterestOnUnsettledInterest())) return false;
			if (!Objects.equals(onFullReturn, _that.getOnFullReturn())) return false;
			if (!Objects.equals(onPartialReturn, _that.getOnPartialReturn())) return false;
			if (!Objects.equals(interestAmountApplication, _that.getInterestAmountApplication())) return false;
			if (!Objects.equals(interestRolloverLimit, _that.getInterestRolloverLimit())) return false;
			if (!Objects.equals(writeoffLimit, _that.getWriteoffLimit())) return false;
			if (!Objects.equals(alternativeToInterestAmount, _that.getAlternativeToInterestAmount())) return false;
			if (!Objects.equals(alternativeProvision, _that.getAlternativeProvision())) return false;
			if (!Objects.equals(cutoffTime, _that.getCutoffTime())) return false;
			if (!Objects.equals(notification, _that.getNotification())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestPaymentHandling != null ? interestPaymentHandling.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (paymentBusinessCenter != null ? paymentBusinessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (netPostedAndHeldInterest != null ? netPostedAndHeldInterest.hashCode() : 0);
			_result = 31 * _result + (netInterestWithMarginCalls != null ? netInterestWithMarginCalls.hashCode() : 0);
			_result = 31 * _result + (includeAccrualInMarginCalc != null ? includeAccrualInMarginCalc.hashCode() : 0);
			_result = 31 * _result + (accrueInterestOnUnsettledInterest != null ? accrueInterestOnUnsettledInterest.hashCode() : 0);
			_result = 31 * _result + (onFullReturn != null ? onFullReturn.hashCode() : 0);
			_result = 31 * _result + (onPartialReturn != null ? onPartialReturn.hashCode() : 0);
			_result = 31 * _result + (interestAmountApplication != null ? interestAmountApplication.hashCode() : 0);
			_result = 31 * _result + (interestRolloverLimit != null ? interestRolloverLimit.hashCode() : 0);
			_result = 31 * _result + (writeoffLimit != null ? writeoffLimit.hashCode() : 0);
			_result = 31 * _result + (alternativeToInterestAmount != null ? alternativeToInterestAmount.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (alternativeProvision != null ? alternativeProvision.hashCode() : 0);
			_result = 31 * _result + (cutoffTime != null ? cutoffTime.hashCode() : 0);
			_result = 31 * _result + (notification != null ? notification.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestHandlingParametersBuilder {" +
				"interestPaymentHandling=" + this.interestPaymentHandling + ", " +
				"paymentBusinessCenter=" + this.paymentBusinessCenter + ", " +
				"netPostedAndHeldInterest=" + this.netPostedAndHeldInterest + ", " +
				"netInterestWithMarginCalls=" + this.netInterestWithMarginCalls + ", " +
				"includeAccrualInMarginCalc=" + this.includeAccrualInMarginCalc + ", " +
				"accrueInterestOnUnsettledInterest=" + this.accrueInterestOnUnsettledInterest + ", " +
				"onFullReturn=" + this.onFullReturn + ", " +
				"onPartialReturn=" + this.onPartialReturn + ", " +
				"interestAmountApplication=" + this.interestAmountApplication + ", " +
				"interestRolloverLimit=" + this.interestRolloverLimit + ", " +
				"writeoffLimit=" + this.writeoffLimit + ", " +
				"alternativeToInterestAmount=" + this.alternativeToInterestAmount + ", " +
				"alternativeProvision=" + this.alternativeProvision + ", " +
				"cutoffTime=" + this.cutoffTime + ", " +
				"notification=" + this.notification +
			'}';
		}
	}
}
