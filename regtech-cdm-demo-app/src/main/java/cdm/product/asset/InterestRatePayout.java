package cdm.product.asset;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.BondReference;
import cdm.product.asset.CashflowRepresentation;
import cdm.product.asset.CompoundingMethodEnum;
import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.InterestRatePayout.InterestRatePayoutBuilder;
import cdm.product.asset.InterestRatePayout.InterestRatePayoutBuilderImpl;
import cdm.product.asset.InterestRatePayout.InterestRatePayoutImpl;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.SpreadCalculationMethodEnum;
import cdm.product.asset.meta.InterestRatePayoutMeta;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.StubPeriod;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  A class to specify all of the terms necessary to define and calculate a cash flow based on a fixed, a floating or an inflation index rate. The interest rate payout can be applied to interest rate swaps and FRA (which both have two associated interest rate payouts), credit default swaps (to represent the fee leg when subject to periodic payments) and equity swaps (to represent the funding leg). The associated globalKey denotes the ability to associate a hash value to the InterestRatePayout instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="InterestRatePayout", builder=InterestRatePayout.InterestRatePayoutBuilderImpl.class, version="${project.version}")
public interface InterestRatePayout extends PayoutBase, GlobalKey {

	InterestRatePayoutMeta metaData = new InterestRatePayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The specification of the rate value(s) applicable to the contract using either a floating rate calculation, a single fixed rate, a fixed rate schedule, or an inflation rate calculation.
	 */
	RateSpecification getRateSpecification();
	/**
	 * The day count fraction. The cardinality has been relaxed when compared with the FpML interest rate swap for the purpose of accommodating standardized credit default swaps which DCF is not explicitly stated as part of the economic terms. The data rule InterestRatePayout_dayCountFraction requires that the DCF be stated for interest rate products.
	 */
	FieldWithMetaDayCountFractionEnum getDayCountFraction();
	/**
	 * The parameters used to generate the calculation period dates schedule, including the specification of any initial or final stub calculation periods.
	 */
	CalculationPeriodDates getCalculationPeriodDates();
	/**
	 * The payment date schedule, as defined by the parameters that are needed to specify it, either in a parametric way or by reference to another schedule of dates (e.g. the reset dates).
	 */
	PaymentDates getPaymentDates();
	/**
	 * The payment date, where only one date is specified, as for the FRA product.
	 */
	AdjustableDate getPaymentDate();
	/**
	 * Applicable to CDS on MBS to specify whether payment delays are applicable to the fixed Amount. RMBS typically have a payment delay of 5 days between the coupon date of the reference obligation and the payment date of the synthetic swap. CMBS do not, on the other hand, with both payment dates being on the 25th of each month.
	 */
	Boolean getPaymentDelay();
	/**
	 * The reset dates schedule, i.e. the dates on which the new observed index value is applied for each period and the interest rate hence begins to accrue.
	 */
	ResetDates getResetDates();
	/**
	 * The parameters specifying any discounting conventions that may apply. This element must only be included if discounting applies.
	 */
	DiscountingMethod getDiscountingMethod();
	/**
	 * If one or more calculation period contributes to a single payment amount this element specifies whether compounding is applicable and, if so, what compounding method is to be used. This element must only be included when more than one calculation period contributes to a single payment amount.
	 */
	CompoundingMethodEnum getCompoundingMethod();
	/**
	 * The cashflow representation of the swap stream.
	 */
	CashflowRepresentation getCashflowRepresentation();
	/**
	 * The stub calculation period amount parameters. This element must only be included if there is an initial or final stub calculation period. Even then, it must only be included if either the stub references a different floating rate tenor to the regular calculation periods, or if the stub is calculated as a linear interpolation of two different floating rate tenors, or if a specific stub rate or stub amount has been negotiated.
	 */
	StubPeriod getStubPeriod();
	/**
	 * Reference to a bond underlier to represent an asset swap or Condition Precedent Bond.
	 */
	BondReference getBondReference();
	/**
	 * Fixed Amount Calculation
	 */
	String getFixedAmount();
	/**
	 * Floating Amount Calculation
	 */
	String getFloatingAmount();
	/**
	 * Method by which spread is calculated. For example on an asset swap: &#39;ParPar&#39; or &#39;Proceeds&#39; may be the method indicated.
	 */
	SpreadCalculationMethodEnum getSpreadCalculationMethod();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	InterestRatePayout build();
	
	InterestRatePayout.InterestRatePayoutBuilder toBuilder();
	
	static InterestRatePayout.InterestRatePayoutBuilder builder() {
		return new InterestRatePayout.InterestRatePayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InterestRatePayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InterestRatePayout> getType() {
		return InterestRatePayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("rateSpecification"), processor, RateSpecification.class, getRateSpecification());
		processRosetta(path.newSubPath("dayCountFraction"), processor, FieldWithMetaDayCountFractionEnum.class, getDayCountFraction());
		processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.class, getCalculationPeriodDates());
		processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.class, getPaymentDates());
		processRosetta(path.newSubPath("paymentDate"), processor, AdjustableDate.class, getPaymentDate());
		processor.processBasic(path.newSubPath("paymentDelay"), Boolean.class, getPaymentDelay(), this);
		processRosetta(path.newSubPath("resetDates"), processor, ResetDates.class, getResetDates());
		processRosetta(path.newSubPath("discountingMethod"), processor, DiscountingMethod.class, getDiscountingMethod());
		processor.processBasic(path.newSubPath("compoundingMethod"), CompoundingMethodEnum.class, getCompoundingMethod(), this);
		processRosetta(path.newSubPath("cashflowRepresentation"), processor, CashflowRepresentation.class, getCashflowRepresentation());
		processRosetta(path.newSubPath("stubPeriod"), processor, StubPeriod.class, getStubPeriod());
		processRosetta(path.newSubPath("bondReference"), processor, BondReference.class, getBondReference());
		processor.processBasic(path.newSubPath("fixedAmount"), String.class, getFixedAmount(), this);
		processor.processBasic(path.newSubPath("floatingAmount"), String.class, getFloatingAmount(), this);
		processor.processBasic(path.newSubPath("spreadCalculationMethod"), SpreadCalculationMethodEnum.class, getSpreadCalculationMethod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InterestRatePayoutBuilder extends InterestRatePayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		RateSpecification.RateSpecificationBuilder getOrCreateRateSpecification();
		RateSpecification.RateSpecificationBuilder getRateSpecification();
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getOrCreateDayCountFraction();
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getDayCountFraction();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates();
		PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates();
		PaymentDates.PaymentDatesBuilder getPaymentDates();
		AdjustableDate.AdjustableDateBuilder getOrCreatePaymentDate();
		AdjustableDate.AdjustableDateBuilder getPaymentDate();
		ResetDates.ResetDatesBuilder getOrCreateResetDates();
		ResetDates.ResetDatesBuilder getResetDates();
		DiscountingMethod.DiscountingMethodBuilder getOrCreateDiscountingMethod();
		DiscountingMethod.DiscountingMethodBuilder getDiscountingMethod();
		CashflowRepresentation.CashflowRepresentationBuilder getOrCreateCashflowRepresentation();
		CashflowRepresentation.CashflowRepresentationBuilder getCashflowRepresentation();
		StubPeriod.StubPeriodBuilder getOrCreateStubPeriod();
		StubPeriod.StubPeriodBuilder getStubPeriod();
		BondReference.BondReferenceBuilder getOrCreateBondReference();
		BondReference.BondReferenceBuilder getBondReference();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		InterestRatePayout.InterestRatePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		InterestRatePayout.InterestRatePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		InterestRatePayout.InterestRatePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		InterestRatePayout.InterestRatePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		InterestRatePayout.InterestRatePayoutBuilder setRateSpecification(RateSpecification rateSpecification);
		InterestRatePayout.InterestRatePayoutBuilder setDayCountFraction(FieldWithMetaDayCountFractionEnum dayCountFraction0);
		InterestRatePayout.InterestRatePayoutBuilder setDayCountFractionValue(DayCountFractionEnum dayCountFraction1);
		InterestRatePayout.InterestRatePayoutBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates);
		InterestRatePayout.InterestRatePayoutBuilder setPaymentDates(PaymentDates paymentDates);
		InterestRatePayout.InterestRatePayoutBuilder setPaymentDate(AdjustableDate paymentDate);
		InterestRatePayout.InterestRatePayoutBuilder setPaymentDelay(Boolean paymentDelay);
		InterestRatePayout.InterestRatePayoutBuilder setResetDates(ResetDates resetDates);
		InterestRatePayout.InterestRatePayoutBuilder setDiscountingMethod(DiscountingMethod discountingMethod);
		InterestRatePayout.InterestRatePayoutBuilder setCompoundingMethod(CompoundingMethodEnum compoundingMethod);
		InterestRatePayout.InterestRatePayoutBuilder setCashflowRepresentation(CashflowRepresentation cashflowRepresentation);
		InterestRatePayout.InterestRatePayoutBuilder setStubPeriod(StubPeriod stubPeriod);
		InterestRatePayout.InterestRatePayoutBuilder setBondReference(BondReference bondReference);
		InterestRatePayout.InterestRatePayoutBuilder setFixedAmount(String fixedAmount);
		InterestRatePayout.InterestRatePayoutBuilder setFloatingAmount(String floatingAmount);
		InterestRatePayout.InterestRatePayoutBuilder setSpreadCalculationMethod(SpreadCalculationMethodEnum spreadCalculationMethod);
		InterestRatePayout.InterestRatePayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("rateSpecification"), processor, RateSpecification.RateSpecificationBuilder.class, getRateSpecification());
			processRosetta(path.newSubPath("dayCountFraction"), processor, FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder.class, getDayCountFraction());
			processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.CalculationPeriodDatesBuilder.class, getCalculationPeriodDates());
			processRosetta(path.newSubPath("paymentDates"), processor, PaymentDates.PaymentDatesBuilder.class, getPaymentDates());
			processRosetta(path.newSubPath("paymentDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getPaymentDate());
			processor.processBasic(path.newSubPath("paymentDelay"), Boolean.class, getPaymentDelay(), this);
			processRosetta(path.newSubPath("resetDates"), processor, ResetDates.ResetDatesBuilder.class, getResetDates());
			processRosetta(path.newSubPath("discountingMethod"), processor, DiscountingMethod.DiscountingMethodBuilder.class, getDiscountingMethod());
			processor.processBasic(path.newSubPath("compoundingMethod"), CompoundingMethodEnum.class, getCompoundingMethod(), this);
			processRosetta(path.newSubPath("cashflowRepresentation"), processor, CashflowRepresentation.CashflowRepresentationBuilder.class, getCashflowRepresentation());
			processRosetta(path.newSubPath("stubPeriod"), processor, StubPeriod.StubPeriodBuilder.class, getStubPeriod());
			processRosetta(path.newSubPath("bondReference"), processor, BondReference.BondReferenceBuilder.class, getBondReference());
			processor.processBasic(path.newSubPath("fixedAmount"), String.class, getFixedAmount(), this);
			processor.processBasic(path.newSubPath("floatingAmount"), String.class, getFloatingAmount(), this);
			processor.processBasic(path.newSubPath("spreadCalculationMethod"), SpreadCalculationMethodEnum.class, getSpreadCalculationMethod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		InterestRatePayout.InterestRatePayoutBuilder prune();
	}

	/*********************** Immutable Implementation of InterestRatePayout  ***********************/
	class InterestRatePayoutImpl extends PayoutBase.PayoutBaseImpl implements InterestRatePayout {
		private final RateSpecification rateSpecification;
		private final FieldWithMetaDayCountFractionEnum dayCountFraction;
		private final CalculationPeriodDates calculationPeriodDates;
		private final PaymentDates paymentDates;
		private final AdjustableDate paymentDate;
		private final Boolean paymentDelay;
		private final ResetDates resetDates;
		private final DiscountingMethod discountingMethod;
		private final CompoundingMethodEnum compoundingMethod;
		private final CashflowRepresentation cashflowRepresentation;
		private final StubPeriod stubPeriod;
		private final BondReference bondReference;
		private final String fixedAmount;
		private final String floatingAmount;
		private final SpreadCalculationMethodEnum spreadCalculationMethod;
		private final MetaFields meta;
		
		protected InterestRatePayoutImpl(InterestRatePayout.InterestRatePayoutBuilder builder) {
			super(builder);
			this.rateSpecification = ofNullable(builder.getRateSpecification()).map(f->f.build()).orElse(null);
			this.dayCountFraction = ofNullable(builder.getDayCountFraction()).map(f->f.build()).orElse(null);
			this.calculationPeriodDates = ofNullable(builder.getCalculationPeriodDates()).map(f->f.build()).orElse(null);
			this.paymentDates = ofNullable(builder.getPaymentDates()).map(f->f.build()).orElse(null);
			this.paymentDate = ofNullable(builder.getPaymentDate()).map(f->f.build()).orElse(null);
			this.paymentDelay = builder.getPaymentDelay();
			this.resetDates = ofNullable(builder.getResetDates()).map(f->f.build()).orElse(null);
			this.discountingMethod = ofNullable(builder.getDiscountingMethod()).map(f->f.build()).orElse(null);
			this.compoundingMethod = builder.getCompoundingMethod();
			this.cashflowRepresentation = ofNullable(builder.getCashflowRepresentation()).map(f->f.build()).orElse(null);
			this.stubPeriod = ofNullable(builder.getStubPeriod()).map(f->f.build()).orElse(null);
			this.bondReference = ofNullable(builder.getBondReference()).map(f->f.build()).orElse(null);
			this.fixedAmount = builder.getFixedAmount();
			this.floatingAmount = builder.getFloatingAmount();
			this.spreadCalculationMethod = builder.getSpreadCalculationMethod();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("rateSpecification")
		public RateSpecification getRateSpecification() {
			return rateSpecification;
		}
		
		@Override
		@RosettaAttribute("dayCountFraction")
		public FieldWithMetaDayCountFractionEnum getDayCountFraction() {
			return dayCountFraction;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates getCalculationPeriodDates() {
			return calculationPeriodDates;
		}
		
		@Override
		@RosettaAttribute("paymentDates")
		public PaymentDates getPaymentDates() {
			return paymentDates;
		}
		
		@Override
		@RosettaAttribute("paymentDate")
		public AdjustableDate getPaymentDate() {
			return paymentDate;
		}
		
		@Override
		@RosettaAttribute("paymentDelay")
		public Boolean getPaymentDelay() {
			return paymentDelay;
		}
		
		@Override
		@RosettaAttribute("resetDates")
		public ResetDates getResetDates() {
			return resetDates;
		}
		
		@Override
		@RosettaAttribute("discountingMethod")
		public DiscountingMethod getDiscountingMethod() {
			return discountingMethod;
		}
		
		@Override
		@RosettaAttribute("compoundingMethod")
		public CompoundingMethodEnum getCompoundingMethod() {
			return compoundingMethod;
		}
		
		@Override
		@RosettaAttribute("cashflowRepresentation")
		public CashflowRepresentation getCashflowRepresentation() {
			return cashflowRepresentation;
		}
		
		@Override
		@RosettaAttribute("stubPeriod")
		public StubPeriod getStubPeriod() {
			return stubPeriod;
		}
		
		@Override
		@RosettaAttribute("bondReference")
		public BondReference getBondReference() {
			return bondReference;
		}
		
		@Override
		@RosettaAttribute("fixedAmount")
		public String getFixedAmount() {
			return fixedAmount;
		}
		
		@Override
		@RosettaAttribute("floatingAmount")
		public String getFloatingAmount() {
			return floatingAmount;
		}
		
		@Override
		@RosettaAttribute("spreadCalculationMethod")
		public SpreadCalculationMethodEnum getSpreadCalculationMethod() {
			return spreadCalculationMethod;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public InterestRatePayout build() {
			return this;
		}
		
		@Override
		public InterestRatePayout.InterestRatePayoutBuilder toBuilder() {
			InterestRatePayout.InterestRatePayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InterestRatePayout.InterestRatePayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getRateSpecification()).ifPresent(builder::setRateSpecification);
			ofNullable(getDayCountFraction()).ifPresent(builder::setDayCountFraction);
			ofNullable(getCalculationPeriodDates()).ifPresent(builder::setCalculationPeriodDates);
			ofNullable(getPaymentDates()).ifPresent(builder::setPaymentDates);
			ofNullable(getPaymentDate()).ifPresent(builder::setPaymentDate);
			ofNullable(getPaymentDelay()).ifPresent(builder::setPaymentDelay);
			ofNullable(getResetDates()).ifPresent(builder::setResetDates);
			ofNullable(getDiscountingMethod()).ifPresent(builder::setDiscountingMethod);
			ofNullable(getCompoundingMethod()).ifPresent(builder::setCompoundingMethod);
			ofNullable(getCashflowRepresentation()).ifPresent(builder::setCashflowRepresentation);
			ofNullable(getStubPeriod()).ifPresent(builder::setStubPeriod);
			ofNullable(getBondReference()).ifPresent(builder::setBondReference);
			ofNullable(getFixedAmount()).ifPresent(builder::setFixedAmount);
			ofNullable(getFloatingAmount()).ifPresent(builder::setFloatingAmount);
			ofNullable(getSpreadCalculationMethod()).ifPresent(builder::setSpreadCalculationMethod);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			InterestRatePayout _that = getType().cast(o);
		
			if (!Objects.equals(rateSpecification, _that.getRateSpecification())) return false;
			if (!Objects.equals(dayCountFraction, _that.getDayCountFraction())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(paymentDate, _that.getPaymentDate())) return false;
			if (!Objects.equals(paymentDelay, _that.getPaymentDelay())) return false;
			if (!Objects.equals(resetDates, _that.getResetDates())) return false;
			if (!Objects.equals(discountingMethod, _that.getDiscountingMethod())) return false;
			if (!Objects.equals(compoundingMethod, _that.getCompoundingMethod())) return false;
			if (!Objects.equals(cashflowRepresentation, _that.getCashflowRepresentation())) return false;
			if (!Objects.equals(stubPeriod, _that.getStubPeriod())) return false;
			if (!Objects.equals(bondReference, _that.getBondReference())) return false;
			if (!Objects.equals(fixedAmount, _that.getFixedAmount())) return false;
			if (!Objects.equals(floatingAmount, _that.getFloatingAmount())) return false;
			if (!Objects.equals(spreadCalculationMethod, _that.getSpreadCalculationMethod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (rateSpecification != null ? rateSpecification.hashCode() : 0);
			_result = 31 * _result + (dayCountFraction != null ? dayCountFraction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (paymentDate != null ? paymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentDelay != null ? paymentDelay.hashCode() : 0);
			_result = 31 * _result + (resetDates != null ? resetDates.hashCode() : 0);
			_result = 31 * _result + (discountingMethod != null ? discountingMethod.hashCode() : 0);
			_result = 31 * _result + (compoundingMethod != null ? compoundingMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashflowRepresentation != null ? cashflowRepresentation.hashCode() : 0);
			_result = 31 * _result + (stubPeriod != null ? stubPeriod.hashCode() : 0);
			_result = 31 * _result + (bondReference != null ? bondReference.hashCode() : 0);
			_result = 31 * _result + (fixedAmount != null ? fixedAmount.hashCode() : 0);
			_result = 31 * _result + (floatingAmount != null ? floatingAmount.hashCode() : 0);
			_result = 31 * _result + (spreadCalculationMethod != null ? spreadCalculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InterestRatePayout {" +
				"rateSpecification=" + this.rateSpecification + ", " +
				"dayCountFraction=" + this.dayCountFraction + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"paymentDate=" + this.paymentDate + ", " +
				"paymentDelay=" + this.paymentDelay + ", " +
				"resetDates=" + this.resetDates + ", " +
				"discountingMethod=" + this.discountingMethod + ", " +
				"compoundingMethod=" + this.compoundingMethod + ", " +
				"cashflowRepresentation=" + this.cashflowRepresentation + ", " +
				"stubPeriod=" + this.stubPeriod + ", " +
				"bondReference=" + this.bondReference + ", " +
				"fixedAmount=" + this.fixedAmount + ", " +
				"floatingAmount=" + this.floatingAmount + ", " +
				"spreadCalculationMethod=" + this.spreadCalculationMethod + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of InterestRatePayout  ***********************/
	class InterestRatePayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements InterestRatePayout.InterestRatePayoutBuilder, GlobalKeyBuilder {
	
		protected RateSpecification.RateSpecificationBuilder rateSpecification;
		protected FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder dayCountFraction;
		protected CalculationPeriodDates.CalculationPeriodDatesBuilder calculationPeriodDates;
		protected PaymentDates.PaymentDatesBuilder paymentDates;
		protected AdjustableDate.AdjustableDateBuilder paymentDate;
		protected Boolean paymentDelay;
		protected ResetDates.ResetDatesBuilder resetDates;
		protected DiscountingMethod.DiscountingMethodBuilder discountingMethod;
		protected CompoundingMethodEnum compoundingMethod;
		protected CashflowRepresentation.CashflowRepresentationBuilder cashflowRepresentation;
		protected StubPeriod.StubPeriodBuilder stubPeriod;
		protected BondReference.BondReferenceBuilder bondReference;
		protected String fixedAmount;
		protected String floatingAmount;
		protected SpreadCalculationMethodEnum spreadCalculationMethod;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public InterestRatePayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rateSpecification")
		public RateSpecification.RateSpecificationBuilder getRateSpecification() {
			return rateSpecification;
		}
		
		@Override
		public RateSpecification.RateSpecificationBuilder getOrCreateRateSpecification() {
			RateSpecification.RateSpecificationBuilder result;
			if (rateSpecification!=null) {
				result = rateSpecification;
			}
			else {
				result = rateSpecification = RateSpecification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dayCountFraction")
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getDayCountFraction() {
			return dayCountFraction;
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getOrCreateDayCountFraction() {
			FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder result;
			if (dayCountFraction!=null) {
				result = dayCountFraction;
			}
			else {
				result = dayCountFraction = FieldWithMetaDayCountFractionEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates() {
			return calculationPeriodDates;
		}
		
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates() {
			CalculationPeriodDates.CalculationPeriodDatesBuilder result;
			if (calculationPeriodDates!=null) {
				result = calculationPeriodDates;
			}
			else {
				result = calculationPeriodDates = CalculationPeriodDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public PaymentDates.PaymentDatesBuilder getPaymentDates() {
			return paymentDates;
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder getOrCreatePaymentDates() {
			PaymentDates.PaymentDatesBuilder result;
			if (paymentDates!=null) {
				result = paymentDates;
			}
			else {
				result = paymentDates = PaymentDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDate")
		public AdjustableDate.AdjustableDateBuilder getPaymentDate() {
			return paymentDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreatePaymentDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (paymentDate!=null) {
				result = paymentDate;
			}
			else {
				result = paymentDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("paymentDelay")
		public Boolean getPaymentDelay() {
			return paymentDelay;
		}
		
		@Override
		@RosettaAttribute("resetDates")
		public ResetDates.ResetDatesBuilder getResetDates() {
			return resetDates;
		}
		
		@Override
		public ResetDates.ResetDatesBuilder getOrCreateResetDates() {
			ResetDates.ResetDatesBuilder result;
			if (resetDates!=null) {
				result = resetDates;
			}
			else {
				result = resetDates = ResetDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("discountingMethod")
		public DiscountingMethod.DiscountingMethodBuilder getDiscountingMethod() {
			return discountingMethod;
		}
		
		@Override
		public DiscountingMethod.DiscountingMethodBuilder getOrCreateDiscountingMethod() {
			DiscountingMethod.DiscountingMethodBuilder result;
			if (discountingMethod!=null) {
				result = discountingMethod;
			}
			else {
				result = discountingMethod = DiscountingMethod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("compoundingMethod")
		public CompoundingMethodEnum getCompoundingMethod() {
			return compoundingMethod;
		}
		
		@Override
		@RosettaAttribute("cashflowRepresentation")
		public CashflowRepresentation.CashflowRepresentationBuilder getCashflowRepresentation() {
			return cashflowRepresentation;
		}
		
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder getOrCreateCashflowRepresentation() {
			CashflowRepresentation.CashflowRepresentationBuilder result;
			if (cashflowRepresentation!=null) {
				result = cashflowRepresentation;
			}
			else {
				result = cashflowRepresentation = CashflowRepresentation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("stubPeriod")
		public StubPeriod.StubPeriodBuilder getStubPeriod() {
			return stubPeriod;
		}
		
		@Override
		public StubPeriod.StubPeriodBuilder getOrCreateStubPeriod() {
			StubPeriod.StubPeriodBuilder result;
			if (stubPeriod!=null) {
				result = stubPeriod;
			}
			else {
				result = stubPeriod = StubPeriod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("bondReference")
		public BondReference.BondReferenceBuilder getBondReference() {
			return bondReference;
		}
		
		@Override
		public BondReference.BondReferenceBuilder getOrCreateBondReference() {
			BondReference.BondReferenceBuilder result;
			if (bondReference!=null) {
				result = bondReference;
			}
			else {
				result = bondReference = BondReference.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixedAmount")
		public String getFixedAmount() {
			return fixedAmount;
		}
		
		@Override
		@RosettaAttribute("floatingAmount")
		public String getFloatingAmount() {
			return floatingAmount;
		}
		
		@Override
		@RosettaAttribute("spreadCalculationMethod")
		public SpreadCalculationMethodEnum getSpreadCalculationMethod() {
			return spreadCalculationMethod;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("payerReceiver")
		public InterestRatePayout.InterestRatePayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public InterestRatePayout.InterestRatePayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public InterestRatePayout.InterestRatePayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public InterestRatePayout.InterestRatePayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("rateSpecification")
		public InterestRatePayout.InterestRatePayoutBuilder setRateSpecification(RateSpecification rateSpecification) {
			this.rateSpecification = rateSpecification==null?null:rateSpecification.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dayCountFraction")
		public InterestRatePayout.InterestRatePayoutBuilder setDayCountFraction(FieldWithMetaDayCountFractionEnum dayCountFraction) {
			this.dayCountFraction = dayCountFraction==null?null:dayCountFraction.toBuilder();
			return this;
		}
		@Override
		public InterestRatePayout.InterestRatePayoutBuilder setDayCountFractionValue(DayCountFractionEnum dayCountFraction) {
			this.getOrCreateDayCountFraction().setValue(dayCountFraction);
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public InterestRatePayout.InterestRatePayoutBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates) {
			this.calculationPeriodDates = calculationPeriodDates==null?null:calculationPeriodDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDates")
		public InterestRatePayout.InterestRatePayoutBuilder setPaymentDates(PaymentDates paymentDates) {
			this.paymentDates = paymentDates==null?null:paymentDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDate")
		public InterestRatePayout.InterestRatePayoutBuilder setPaymentDate(AdjustableDate paymentDate) {
			this.paymentDate = paymentDate==null?null:paymentDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("paymentDelay")
		public InterestRatePayout.InterestRatePayoutBuilder setPaymentDelay(Boolean paymentDelay) {
			this.paymentDelay = paymentDelay==null?null:paymentDelay;
			return this;
		}
		@Override
		@RosettaAttribute("resetDates")
		public InterestRatePayout.InterestRatePayoutBuilder setResetDates(ResetDates resetDates) {
			this.resetDates = resetDates==null?null:resetDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("discountingMethod")
		public InterestRatePayout.InterestRatePayoutBuilder setDiscountingMethod(DiscountingMethod discountingMethod) {
			this.discountingMethod = discountingMethod==null?null:discountingMethod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("compoundingMethod")
		public InterestRatePayout.InterestRatePayoutBuilder setCompoundingMethod(CompoundingMethodEnum compoundingMethod) {
			this.compoundingMethod = compoundingMethod==null?null:compoundingMethod;
			return this;
		}
		@Override
		@RosettaAttribute("cashflowRepresentation")
		public InterestRatePayout.InterestRatePayoutBuilder setCashflowRepresentation(CashflowRepresentation cashflowRepresentation) {
			this.cashflowRepresentation = cashflowRepresentation==null?null:cashflowRepresentation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("stubPeriod")
		public InterestRatePayout.InterestRatePayoutBuilder setStubPeriod(StubPeriod stubPeriod) {
			this.stubPeriod = stubPeriod==null?null:stubPeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("bondReference")
		public InterestRatePayout.InterestRatePayoutBuilder setBondReference(BondReference bondReference) {
			this.bondReference = bondReference==null?null:bondReference.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixedAmount")
		public InterestRatePayout.InterestRatePayoutBuilder setFixedAmount(String fixedAmount) {
			this.fixedAmount = fixedAmount==null?null:fixedAmount;
			return this;
		}
		@Override
		@RosettaAttribute("floatingAmount")
		public InterestRatePayout.InterestRatePayoutBuilder setFloatingAmount(String floatingAmount) {
			this.floatingAmount = floatingAmount==null?null:floatingAmount;
			return this;
		}
		@Override
		@RosettaAttribute("spreadCalculationMethod")
		public InterestRatePayout.InterestRatePayoutBuilder setSpreadCalculationMethod(SpreadCalculationMethodEnum spreadCalculationMethod) {
			this.spreadCalculationMethod = spreadCalculationMethod==null?null:spreadCalculationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public InterestRatePayout.InterestRatePayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public InterestRatePayout build() {
			return new InterestRatePayout.InterestRatePayoutImpl(this);
		}
		
		@Override
		public InterestRatePayout.InterestRatePayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InterestRatePayout.InterestRatePayoutBuilder prune() {
			super.prune();
			if (rateSpecification!=null && !rateSpecification.prune().hasData()) rateSpecification = null;
			if (dayCountFraction!=null && !dayCountFraction.prune().hasData()) dayCountFraction = null;
			if (calculationPeriodDates!=null && !calculationPeriodDates.prune().hasData()) calculationPeriodDates = null;
			if (paymentDates!=null && !paymentDates.prune().hasData()) paymentDates = null;
			if (paymentDate!=null && !paymentDate.prune().hasData()) paymentDate = null;
			if (resetDates!=null && !resetDates.prune().hasData()) resetDates = null;
			if (discountingMethod!=null && !discountingMethod.prune().hasData()) discountingMethod = null;
			if (cashflowRepresentation!=null && !cashflowRepresentation.prune().hasData()) cashflowRepresentation = null;
			if (stubPeriod!=null && !stubPeriod.prune().hasData()) stubPeriod = null;
			if (bondReference!=null && !bondReference.prune().hasData()) bondReference = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getRateSpecification()!=null && getRateSpecification().hasData()) return true;
			if (getDayCountFraction()!=null) return true;
			if (getCalculationPeriodDates()!=null && getCalculationPeriodDates().hasData()) return true;
			if (getPaymentDates()!=null && getPaymentDates().hasData()) return true;
			if (getPaymentDate()!=null && getPaymentDate().hasData()) return true;
			if (getPaymentDelay()!=null) return true;
			if (getResetDates()!=null && getResetDates().hasData()) return true;
			if (getDiscountingMethod()!=null && getDiscountingMethod().hasData()) return true;
			if (getCompoundingMethod()!=null) return true;
			if (getCashflowRepresentation()!=null && getCashflowRepresentation().hasData()) return true;
			if (getStubPeriod()!=null && getStubPeriod().hasData()) return true;
			if (getBondReference()!=null && getBondReference().hasData()) return true;
			if (getFixedAmount()!=null) return true;
			if (getFloatingAmount()!=null) return true;
			if (getSpreadCalculationMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InterestRatePayout.InterestRatePayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			InterestRatePayout.InterestRatePayoutBuilder o = (InterestRatePayout.InterestRatePayoutBuilder) other;
			
			merger.mergeRosetta(getRateSpecification(), o.getRateSpecification(), this::setRateSpecification);
			merger.mergeRosetta(getDayCountFraction(), o.getDayCountFraction(), this::setDayCountFraction);
			merger.mergeRosetta(getCalculationPeriodDates(), o.getCalculationPeriodDates(), this::setCalculationPeriodDates);
			merger.mergeRosetta(getPaymentDates(), o.getPaymentDates(), this::setPaymentDates);
			merger.mergeRosetta(getPaymentDate(), o.getPaymentDate(), this::setPaymentDate);
			merger.mergeRosetta(getResetDates(), o.getResetDates(), this::setResetDates);
			merger.mergeRosetta(getDiscountingMethod(), o.getDiscountingMethod(), this::setDiscountingMethod);
			merger.mergeRosetta(getCashflowRepresentation(), o.getCashflowRepresentation(), this::setCashflowRepresentation);
			merger.mergeRosetta(getStubPeriod(), o.getStubPeriod(), this::setStubPeriod);
			merger.mergeRosetta(getBondReference(), o.getBondReference(), this::setBondReference);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getPaymentDelay(), o.getPaymentDelay(), this::setPaymentDelay);
			merger.mergeBasic(getCompoundingMethod(), o.getCompoundingMethod(), this::setCompoundingMethod);
			merger.mergeBasic(getFixedAmount(), o.getFixedAmount(), this::setFixedAmount);
			merger.mergeBasic(getFloatingAmount(), o.getFloatingAmount(), this::setFloatingAmount);
			merger.mergeBasic(getSpreadCalculationMethod(), o.getSpreadCalculationMethod(), this::setSpreadCalculationMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			InterestRatePayout _that = getType().cast(o);
		
			if (!Objects.equals(rateSpecification, _that.getRateSpecification())) return false;
			if (!Objects.equals(dayCountFraction, _that.getDayCountFraction())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(paymentDates, _that.getPaymentDates())) return false;
			if (!Objects.equals(paymentDate, _that.getPaymentDate())) return false;
			if (!Objects.equals(paymentDelay, _that.getPaymentDelay())) return false;
			if (!Objects.equals(resetDates, _that.getResetDates())) return false;
			if (!Objects.equals(discountingMethod, _that.getDiscountingMethod())) return false;
			if (!Objects.equals(compoundingMethod, _that.getCompoundingMethod())) return false;
			if (!Objects.equals(cashflowRepresentation, _that.getCashflowRepresentation())) return false;
			if (!Objects.equals(stubPeriod, _that.getStubPeriod())) return false;
			if (!Objects.equals(bondReference, _that.getBondReference())) return false;
			if (!Objects.equals(fixedAmount, _that.getFixedAmount())) return false;
			if (!Objects.equals(floatingAmount, _that.getFloatingAmount())) return false;
			if (!Objects.equals(spreadCalculationMethod, _that.getSpreadCalculationMethod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (rateSpecification != null ? rateSpecification.hashCode() : 0);
			_result = 31 * _result + (dayCountFraction != null ? dayCountFraction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (paymentDates != null ? paymentDates.hashCode() : 0);
			_result = 31 * _result + (paymentDate != null ? paymentDate.hashCode() : 0);
			_result = 31 * _result + (paymentDelay != null ? paymentDelay.hashCode() : 0);
			_result = 31 * _result + (resetDates != null ? resetDates.hashCode() : 0);
			_result = 31 * _result + (discountingMethod != null ? discountingMethod.hashCode() : 0);
			_result = 31 * _result + (compoundingMethod != null ? compoundingMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashflowRepresentation != null ? cashflowRepresentation.hashCode() : 0);
			_result = 31 * _result + (stubPeriod != null ? stubPeriod.hashCode() : 0);
			_result = 31 * _result + (bondReference != null ? bondReference.hashCode() : 0);
			_result = 31 * _result + (fixedAmount != null ? fixedAmount.hashCode() : 0);
			_result = 31 * _result + (floatingAmount != null ? floatingAmount.hashCode() : 0);
			_result = 31 * _result + (spreadCalculationMethod != null ? spreadCalculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InterestRatePayoutBuilder {" +
				"rateSpecification=" + this.rateSpecification + ", " +
				"dayCountFraction=" + this.dayCountFraction + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"paymentDates=" + this.paymentDates + ", " +
				"paymentDate=" + this.paymentDate + ", " +
				"paymentDelay=" + this.paymentDelay + ", " +
				"resetDates=" + this.resetDates + ", " +
				"discountingMethod=" + this.discountingMethod + ", " +
				"compoundingMethod=" + this.compoundingMethod + ", " +
				"cashflowRepresentation=" + this.cashflowRepresentation + ", " +
				"stubPeriod=" + this.stubPeriod + ", " +
				"bondReference=" + this.bondReference + ", " +
				"fixedAmount=" + this.fixedAmount + ", " +
				"floatingAmount=" + this.floatingAmount + ", " +
				"spreadCalculationMethod=" + this.spreadCalculationMethod + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
