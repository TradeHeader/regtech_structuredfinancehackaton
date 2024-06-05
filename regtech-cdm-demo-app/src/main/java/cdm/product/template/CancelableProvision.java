package cdm.product.template;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.Period;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.base.staticdata.party.BuyerSeller.BuyerSellerBuilder;
import cdm.base.staticdata.party.BuyerSeller.BuyerSellerBuilderImpl;
import cdm.base.staticdata.party.BuyerSeller.BuyerSellerImpl;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.event.common.Transfer;
import cdm.product.common.schedule.FinalCalculationPeriodDateAdjustment;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.CallingPartyEnum;
import cdm.product.template.CancelableProvision;
import cdm.product.template.CancelableProvision.CancelableProvisionBuilder;
import cdm.product.template.CancelableProvision.CancelableProvisionBuilderImpl;
import cdm.product.template.CancelableProvision.CancelableProvisionImpl;
import cdm.product.template.CancelableProvisionAdjustedDates;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.meta.CancelableProvisionMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the right of a party to cancel a swap transaction on the specified exercise dates. The provision is for &#39;walk-away&#39; cancellation (i.e. the fair value of the swap is not paid). A fee payable on exercise can be specified. As a difference from the FpML construct, the canonical model extends the BuyerSeller class.
 * @version ${project.version}
 */
@RosettaDataType(value="CancelableProvision", builder=CancelableProvision.CancelableProvisionBuilderImpl.class, version="${project.version}")
public interface CancelableProvision extends BuyerSeller {

	CancelableProvisionMeta metaData = new CancelableProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * American exercise. FpML implementations consists in an exercise substitution group.
	 */
	AmericanExercise getAmericanExercise();
	/**
	 * Bermuda exercise. FpML implementations consists in an exercise substitution group.
	 */
	BermudaExercise getBermudaExercise();
	/**
	 * European exercise. FpML implementations consists in an exercise substitution group.
	 */
	EuropeanExercise getEuropeanExercise();
	/**
	 * Definition of the party to whom notice of exercise should be given.
	 */
	ExerciseNotice getExerciseNotice();
	/**
	 * A flag to indicate whether follow-up confirmation of exercise (written or electronic) is required following telephonic notice by the buyer to the seller or seller&#39;s agent.
	 */
	Boolean getFollowUpConfirmation();
	/**
	 * The adjusted dates associated with a cancelable provision. These dates have been adjusted for any applicable business day convention.
	 */
	CancelableProvisionAdjustedDates getCancelableProvisionAdjustedDates();
	/**
	 * Business date convention adjustment to final payment period per leg (swapStream) upon exercise event. The adjustments can be made in-line with leg level BDC&#39;s or they can be specified separately.
	 */
	List<? extends FinalCalculationPeriodDateAdjustment> getFinalCalculationPeriodDateAdjustment();
	/**
	 * An initial fee for the cancelable option.
	 */
	Transfer getInitialFee();
	/**
	 * The party with right to exercise a cancellation. Allows for buyer, seller or either.
	 */
	CallingPartyEnum getCallingParty();
	/**
	 * The first day when cancelation is permitted to take effect. A party may give notice prior to this date and taken together with the effective period would be necessary to cancel on this date.
	 */
	AdjustableOrRelativeDate getEarliestDate();
	/**
	 * The last day within the term of the contract that cancelation is allowed.
	 */
	AdjustableOrRelativeDate getExpirationDate();
	/**
	 * The effective date if cancelation is invoked otherwise the cancellation period defines the cancellation date.
	 */
	AdjustableOrRelativeDates getEffectiveDate();
	/**
	 * Effective period for cancelation when notice is given. This is the period after notice is given that cancellation becomes effecticve.
	 */
	Period getEffectivePeriod();
	/**
	 * The earliest time in a business day that notice of cancelation can be given.
	 */
	BusinessCenterTime getEarliestCancellationTime();
	/**
	 * The latest time at which notice of cancelation can be given.
	 */
	BusinessCenterTime getLatestCancelationTime();

	/*********************** Build Methods  ***********************/
	CancelableProvision build();
	
	CancelableProvision.CancelableProvisionBuilder toBuilder();
	
	static CancelableProvision.CancelableProvisionBuilder builder() {
		return new CancelableProvision.CancelableProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CancelableProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CancelableProvision> getType() {
		return CancelableProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("buyer"), CounterpartyRoleEnum.class, getBuyer(), this);
		processor.processBasic(path.newSubPath("seller"), CounterpartyRoleEnum.class, getSeller(), this);
		processRosetta(path.newSubPath("americanExercise"), processor, AmericanExercise.class, getAmericanExercise());
		processRosetta(path.newSubPath("bermudaExercise"), processor, BermudaExercise.class, getBermudaExercise());
		processRosetta(path.newSubPath("europeanExercise"), processor, EuropeanExercise.class, getEuropeanExercise());
		processRosetta(path.newSubPath("exerciseNotice"), processor, ExerciseNotice.class, getExerciseNotice());
		processor.processBasic(path.newSubPath("followUpConfirmation"), Boolean.class, getFollowUpConfirmation(), this);
		processRosetta(path.newSubPath("cancelableProvisionAdjustedDates"), processor, CancelableProvisionAdjustedDates.class, getCancelableProvisionAdjustedDates());
		processRosetta(path.newSubPath("finalCalculationPeriodDateAdjustment"), processor, FinalCalculationPeriodDateAdjustment.class, getFinalCalculationPeriodDateAdjustment());
		processRosetta(path.newSubPath("initialFee"), processor, Transfer.class, getInitialFee());
		processor.processBasic(path.newSubPath("callingParty"), CallingPartyEnum.class, getCallingParty(), this);
		processRosetta(path.newSubPath("earliestDate"), processor, AdjustableOrRelativeDate.class, getEarliestDate());
		processRosetta(path.newSubPath("expirationDate"), processor, AdjustableOrRelativeDate.class, getExpirationDate());
		processRosetta(path.newSubPath("effectiveDate"), processor, AdjustableOrRelativeDates.class, getEffectiveDate());
		processRosetta(path.newSubPath("effectivePeriod"), processor, Period.class, getEffectivePeriod());
		processRosetta(path.newSubPath("earliestCancellationTime"), processor, BusinessCenterTime.class, getEarliestCancellationTime());
		processRosetta(path.newSubPath("latestCancelationTime"), processor, BusinessCenterTime.class, getLatestCancelationTime());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CancelableProvisionBuilder extends CancelableProvision, BuyerSeller.BuyerSellerBuilder, RosettaModelObjectBuilder {
		AmericanExercise.AmericanExerciseBuilder getOrCreateAmericanExercise();
		AmericanExercise.AmericanExerciseBuilder getAmericanExercise();
		BermudaExercise.BermudaExerciseBuilder getOrCreateBermudaExercise();
		BermudaExercise.BermudaExerciseBuilder getBermudaExercise();
		EuropeanExercise.EuropeanExerciseBuilder getOrCreateEuropeanExercise();
		EuropeanExercise.EuropeanExerciseBuilder getEuropeanExercise();
		ExerciseNotice.ExerciseNoticeBuilder getOrCreateExerciseNotice();
		ExerciseNotice.ExerciseNoticeBuilder getExerciseNotice();
		CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder getOrCreateCancelableProvisionAdjustedDates();
		CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder getCancelableProvisionAdjustedDates();
		FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder getOrCreateFinalCalculationPeriodDateAdjustment(int _index);
		List<? extends FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder> getFinalCalculationPeriodDateAdjustment();
		Transfer.TransferBuilder getOrCreateInitialFee();
		Transfer.TransferBuilder getInitialFee();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEarliestDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEarliestDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getExpirationDate();
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getOrCreateEffectiveDate();
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getEffectiveDate();
		Period.PeriodBuilder getOrCreateEffectivePeriod();
		Period.PeriodBuilder getEffectivePeriod();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateEarliestCancellationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getEarliestCancellationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateLatestCancelationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getLatestCancelationTime();
		CancelableProvision.CancelableProvisionBuilder setBuyer(CounterpartyRoleEnum buyer);
		CancelableProvision.CancelableProvisionBuilder setSeller(CounterpartyRoleEnum seller);
		CancelableProvision.CancelableProvisionBuilder setAmericanExercise(AmericanExercise americanExercise);
		CancelableProvision.CancelableProvisionBuilder setBermudaExercise(BermudaExercise bermudaExercise);
		CancelableProvision.CancelableProvisionBuilder setEuropeanExercise(EuropeanExercise europeanExercise);
		CancelableProvision.CancelableProvisionBuilder setExerciseNotice(ExerciseNotice exerciseNotice);
		CancelableProvision.CancelableProvisionBuilder setFollowUpConfirmation(Boolean followUpConfirmation);
		CancelableProvision.CancelableProvisionBuilder setCancelableProvisionAdjustedDates(CancelableProvisionAdjustedDates cancelableProvisionAdjustedDates);
		CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(FinalCalculationPeriodDateAdjustment finalCalculationPeriodDateAdjustment0);
		CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(FinalCalculationPeriodDateAdjustment finalCalculationPeriodDateAdjustment1, int _idx);
		CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(List<? extends FinalCalculationPeriodDateAdjustment> finalCalculationPeriodDateAdjustment2);
		CancelableProvision.CancelableProvisionBuilder setFinalCalculationPeriodDateAdjustment(List<? extends FinalCalculationPeriodDateAdjustment> finalCalculationPeriodDateAdjustment3);
		CancelableProvision.CancelableProvisionBuilder setInitialFee(Transfer initialFee);
		CancelableProvision.CancelableProvisionBuilder setCallingParty(CallingPartyEnum callingParty);
		CancelableProvision.CancelableProvisionBuilder setEarliestDate(AdjustableOrRelativeDate earliestDate);
		CancelableProvision.CancelableProvisionBuilder setExpirationDate(AdjustableOrRelativeDate expirationDate);
		CancelableProvision.CancelableProvisionBuilder setEffectiveDate(AdjustableOrRelativeDates effectiveDate);
		CancelableProvision.CancelableProvisionBuilder setEffectivePeriod(Period effectivePeriod);
		CancelableProvision.CancelableProvisionBuilder setEarliestCancellationTime(BusinessCenterTime earliestCancellationTime);
		CancelableProvision.CancelableProvisionBuilder setLatestCancelationTime(BusinessCenterTime latestCancelationTime);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("buyer"), CounterpartyRoleEnum.class, getBuyer(), this);
			processor.processBasic(path.newSubPath("seller"), CounterpartyRoleEnum.class, getSeller(), this);
			processRosetta(path.newSubPath("americanExercise"), processor, AmericanExercise.AmericanExerciseBuilder.class, getAmericanExercise());
			processRosetta(path.newSubPath("bermudaExercise"), processor, BermudaExercise.BermudaExerciseBuilder.class, getBermudaExercise());
			processRosetta(path.newSubPath("europeanExercise"), processor, EuropeanExercise.EuropeanExerciseBuilder.class, getEuropeanExercise());
			processRosetta(path.newSubPath("exerciseNotice"), processor, ExerciseNotice.ExerciseNoticeBuilder.class, getExerciseNotice());
			processor.processBasic(path.newSubPath("followUpConfirmation"), Boolean.class, getFollowUpConfirmation(), this);
			processRosetta(path.newSubPath("cancelableProvisionAdjustedDates"), processor, CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder.class, getCancelableProvisionAdjustedDates());
			processRosetta(path.newSubPath("finalCalculationPeriodDateAdjustment"), processor, FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder.class, getFinalCalculationPeriodDateAdjustment());
			processRosetta(path.newSubPath("initialFee"), processor, Transfer.TransferBuilder.class, getInitialFee());
			processor.processBasic(path.newSubPath("callingParty"), CallingPartyEnum.class, getCallingParty(), this);
			processRosetta(path.newSubPath("earliestDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getEarliestDate());
			processRosetta(path.newSubPath("expirationDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getExpirationDate());
			processRosetta(path.newSubPath("effectiveDate"), processor, AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder.class, getEffectiveDate());
			processRosetta(path.newSubPath("effectivePeriod"), processor, Period.PeriodBuilder.class, getEffectivePeriod());
			processRosetta(path.newSubPath("earliestCancellationTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getEarliestCancellationTime());
			processRosetta(path.newSubPath("latestCancelationTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getLatestCancelationTime());
		}
		

		CancelableProvision.CancelableProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of CancelableProvision  ***********************/
	class CancelableProvisionImpl extends BuyerSeller.BuyerSellerImpl implements CancelableProvision {
		private final AmericanExercise americanExercise;
		private final BermudaExercise bermudaExercise;
		private final EuropeanExercise europeanExercise;
		private final ExerciseNotice exerciseNotice;
		private final Boolean followUpConfirmation;
		private final CancelableProvisionAdjustedDates cancelableProvisionAdjustedDates;
		private final List<? extends FinalCalculationPeriodDateAdjustment> finalCalculationPeriodDateAdjustment;
		private final Transfer initialFee;
		private final CallingPartyEnum callingParty;
		private final AdjustableOrRelativeDate earliestDate;
		private final AdjustableOrRelativeDate expirationDate;
		private final AdjustableOrRelativeDates effectiveDate;
		private final Period effectivePeriod;
		private final BusinessCenterTime earliestCancellationTime;
		private final BusinessCenterTime latestCancelationTime;
		
		protected CancelableProvisionImpl(CancelableProvision.CancelableProvisionBuilder builder) {
			super(builder);
			this.americanExercise = ofNullable(builder.getAmericanExercise()).map(f->f.build()).orElse(null);
			this.bermudaExercise = ofNullable(builder.getBermudaExercise()).map(f->f.build()).orElse(null);
			this.europeanExercise = ofNullable(builder.getEuropeanExercise()).map(f->f.build()).orElse(null);
			this.exerciseNotice = ofNullable(builder.getExerciseNotice()).map(f->f.build()).orElse(null);
			this.followUpConfirmation = builder.getFollowUpConfirmation();
			this.cancelableProvisionAdjustedDates = ofNullable(builder.getCancelableProvisionAdjustedDates()).map(f->f.build()).orElse(null);
			this.finalCalculationPeriodDateAdjustment = ofNullable(builder.getFinalCalculationPeriodDateAdjustment()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.initialFee = ofNullable(builder.getInitialFee()).map(f->f.build()).orElse(null);
			this.callingParty = builder.getCallingParty();
			this.earliestDate = ofNullable(builder.getEarliestDate()).map(f->f.build()).orElse(null);
			this.expirationDate = ofNullable(builder.getExpirationDate()).map(f->f.build()).orElse(null);
			this.effectiveDate = ofNullable(builder.getEffectiveDate()).map(f->f.build()).orElse(null);
			this.effectivePeriod = ofNullable(builder.getEffectivePeriod()).map(f->f.build()).orElse(null);
			this.earliestCancellationTime = ofNullable(builder.getEarliestCancellationTime()).map(f->f.build()).orElse(null);
			this.latestCancelationTime = ofNullable(builder.getLatestCancelationTime()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("americanExercise")
		public AmericanExercise getAmericanExercise() {
			return americanExercise;
		}
		
		@Override
		@RosettaAttribute("bermudaExercise")
		public BermudaExercise getBermudaExercise() {
			return bermudaExercise;
		}
		
		@Override
		@RosettaAttribute("europeanExercise")
		public EuropeanExercise getEuropeanExercise() {
			return europeanExercise;
		}
		
		@Override
		@RosettaAttribute("exerciseNotice")
		public ExerciseNotice getExerciseNotice() {
			return exerciseNotice;
		}
		
		@Override
		@RosettaAttribute("followUpConfirmation")
		public Boolean getFollowUpConfirmation() {
			return followUpConfirmation;
		}
		
		@Override
		@RosettaAttribute("cancelableProvisionAdjustedDates")
		public CancelableProvisionAdjustedDates getCancelableProvisionAdjustedDates() {
			return cancelableProvisionAdjustedDates;
		}
		
		@Override
		@RosettaAttribute("finalCalculationPeriodDateAdjustment")
		public List<? extends FinalCalculationPeriodDateAdjustment> getFinalCalculationPeriodDateAdjustment() {
			return finalCalculationPeriodDateAdjustment;
		}
		
		@Override
		@RosettaAttribute("initialFee")
		public Transfer getInitialFee() {
			return initialFee;
		}
		
		@Override
		@RosettaAttribute("callingParty")
		public CallingPartyEnum getCallingParty() {
			return callingParty;
		}
		
		@Override
		@RosettaAttribute("earliestDate")
		public AdjustableOrRelativeDate getEarliestDate() {
			return earliestDate;
		}
		
		@Override
		@RosettaAttribute("expirationDate")
		public AdjustableOrRelativeDate getExpirationDate() {
			return expirationDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public AdjustableOrRelativeDates getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("effectivePeriod")
		public Period getEffectivePeriod() {
			return effectivePeriod;
		}
		
		@Override
		@RosettaAttribute("earliestCancellationTime")
		public BusinessCenterTime getEarliestCancellationTime() {
			return earliestCancellationTime;
		}
		
		@Override
		@RosettaAttribute("latestCancelationTime")
		public BusinessCenterTime getLatestCancelationTime() {
			return latestCancelationTime;
		}
		
		@Override
		public CancelableProvision build() {
			return this;
		}
		
		@Override
		public CancelableProvision.CancelableProvisionBuilder toBuilder() {
			CancelableProvision.CancelableProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CancelableProvision.CancelableProvisionBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getAmericanExercise()).ifPresent(builder::setAmericanExercise);
			ofNullable(getBermudaExercise()).ifPresent(builder::setBermudaExercise);
			ofNullable(getEuropeanExercise()).ifPresent(builder::setEuropeanExercise);
			ofNullable(getExerciseNotice()).ifPresent(builder::setExerciseNotice);
			ofNullable(getFollowUpConfirmation()).ifPresent(builder::setFollowUpConfirmation);
			ofNullable(getCancelableProvisionAdjustedDates()).ifPresent(builder::setCancelableProvisionAdjustedDates);
			ofNullable(getFinalCalculationPeriodDateAdjustment()).ifPresent(builder::setFinalCalculationPeriodDateAdjustment);
			ofNullable(getInitialFee()).ifPresent(builder::setInitialFee);
			ofNullable(getCallingParty()).ifPresent(builder::setCallingParty);
			ofNullable(getEarliestDate()).ifPresent(builder::setEarliestDate);
			ofNullable(getExpirationDate()).ifPresent(builder::setExpirationDate);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getEffectivePeriod()).ifPresent(builder::setEffectivePeriod);
			ofNullable(getEarliestCancellationTime()).ifPresent(builder::setEarliestCancellationTime);
			ofNullable(getLatestCancelationTime()).ifPresent(builder::setLatestCancelationTime);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CancelableProvision _that = getType().cast(o);
		
			if (!Objects.equals(americanExercise, _that.getAmericanExercise())) return false;
			if (!Objects.equals(bermudaExercise, _that.getBermudaExercise())) return false;
			if (!Objects.equals(europeanExercise, _that.getEuropeanExercise())) return false;
			if (!Objects.equals(exerciseNotice, _that.getExerciseNotice())) return false;
			if (!Objects.equals(followUpConfirmation, _that.getFollowUpConfirmation())) return false;
			if (!Objects.equals(cancelableProvisionAdjustedDates, _that.getCancelableProvisionAdjustedDates())) return false;
			if (!ListEquals.listEquals(finalCalculationPeriodDateAdjustment, _that.getFinalCalculationPeriodDateAdjustment())) return false;
			if (!Objects.equals(initialFee, _that.getInitialFee())) return false;
			if (!Objects.equals(callingParty, _that.getCallingParty())) return false;
			if (!Objects.equals(earliestDate, _that.getEarliestDate())) return false;
			if (!Objects.equals(expirationDate, _that.getExpirationDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(effectivePeriod, _that.getEffectivePeriod())) return false;
			if (!Objects.equals(earliestCancellationTime, _that.getEarliestCancellationTime())) return false;
			if (!Objects.equals(latestCancelationTime, _that.getLatestCancelationTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (americanExercise != null ? americanExercise.hashCode() : 0);
			_result = 31 * _result + (bermudaExercise != null ? bermudaExercise.hashCode() : 0);
			_result = 31 * _result + (europeanExercise != null ? europeanExercise.hashCode() : 0);
			_result = 31 * _result + (exerciseNotice != null ? exerciseNotice.hashCode() : 0);
			_result = 31 * _result + (followUpConfirmation != null ? followUpConfirmation.hashCode() : 0);
			_result = 31 * _result + (cancelableProvisionAdjustedDates != null ? cancelableProvisionAdjustedDates.hashCode() : 0);
			_result = 31 * _result + (finalCalculationPeriodDateAdjustment != null ? finalCalculationPeriodDateAdjustment.hashCode() : 0);
			_result = 31 * _result + (initialFee != null ? initialFee.hashCode() : 0);
			_result = 31 * _result + (callingParty != null ? callingParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (earliestDate != null ? earliestDate.hashCode() : 0);
			_result = 31 * _result + (expirationDate != null ? expirationDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (effectivePeriod != null ? effectivePeriod.hashCode() : 0);
			_result = 31 * _result + (earliestCancellationTime != null ? earliestCancellationTime.hashCode() : 0);
			_result = 31 * _result + (latestCancelationTime != null ? latestCancelationTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CancelableProvision {" +
				"americanExercise=" + this.americanExercise + ", " +
				"bermudaExercise=" + this.bermudaExercise + ", " +
				"europeanExercise=" + this.europeanExercise + ", " +
				"exerciseNotice=" + this.exerciseNotice + ", " +
				"followUpConfirmation=" + this.followUpConfirmation + ", " +
				"cancelableProvisionAdjustedDates=" + this.cancelableProvisionAdjustedDates + ", " +
				"finalCalculationPeriodDateAdjustment=" + this.finalCalculationPeriodDateAdjustment + ", " +
				"initialFee=" + this.initialFee + ", " +
				"callingParty=" + this.callingParty + ", " +
				"earliestDate=" + this.earliestDate + ", " +
				"expirationDate=" + this.expirationDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"effectivePeriod=" + this.effectivePeriod + ", " +
				"earliestCancellationTime=" + this.earliestCancellationTime + ", " +
				"latestCancelationTime=" + this.latestCancelationTime +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CancelableProvision  ***********************/
	class CancelableProvisionBuilderImpl extends BuyerSeller.BuyerSellerBuilderImpl  implements CancelableProvision.CancelableProvisionBuilder {
	
		protected AmericanExercise.AmericanExerciseBuilder americanExercise;
		protected BermudaExercise.BermudaExerciseBuilder bermudaExercise;
		protected EuropeanExercise.EuropeanExerciseBuilder europeanExercise;
		protected ExerciseNotice.ExerciseNoticeBuilder exerciseNotice;
		protected Boolean followUpConfirmation;
		protected CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder cancelableProvisionAdjustedDates;
		protected List<FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder> finalCalculationPeriodDateAdjustment = new ArrayList<>();
		protected Transfer.TransferBuilder initialFee;
		protected CallingPartyEnum callingParty;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder earliestDate;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder expirationDate;
		protected AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder effectiveDate;
		protected Period.PeriodBuilder effectivePeriod;
		protected BusinessCenterTime.BusinessCenterTimeBuilder earliestCancellationTime;
		protected BusinessCenterTime.BusinessCenterTimeBuilder latestCancelationTime;
	
		public CancelableProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("americanExercise")
		public AmericanExercise.AmericanExerciseBuilder getAmericanExercise() {
			return americanExercise;
		}
		
		@Override
		public AmericanExercise.AmericanExerciseBuilder getOrCreateAmericanExercise() {
			AmericanExercise.AmericanExerciseBuilder result;
			if (americanExercise!=null) {
				result = americanExercise;
			}
			else {
				result = americanExercise = AmericanExercise.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("bermudaExercise")
		public BermudaExercise.BermudaExerciseBuilder getBermudaExercise() {
			return bermudaExercise;
		}
		
		@Override
		public BermudaExercise.BermudaExerciseBuilder getOrCreateBermudaExercise() {
			BermudaExercise.BermudaExerciseBuilder result;
			if (bermudaExercise!=null) {
				result = bermudaExercise;
			}
			else {
				result = bermudaExercise = BermudaExercise.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("europeanExercise")
		public EuropeanExercise.EuropeanExerciseBuilder getEuropeanExercise() {
			return europeanExercise;
		}
		
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder getOrCreateEuropeanExercise() {
			EuropeanExercise.EuropeanExerciseBuilder result;
			if (europeanExercise!=null) {
				result = europeanExercise;
			}
			else {
				result = europeanExercise = EuropeanExercise.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exerciseNotice")
		public ExerciseNotice.ExerciseNoticeBuilder getExerciseNotice() {
			return exerciseNotice;
		}
		
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder getOrCreateExerciseNotice() {
			ExerciseNotice.ExerciseNoticeBuilder result;
			if (exerciseNotice!=null) {
				result = exerciseNotice;
			}
			else {
				result = exerciseNotice = ExerciseNotice.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("followUpConfirmation")
		public Boolean getFollowUpConfirmation() {
			return followUpConfirmation;
		}
		
		@Override
		@RosettaAttribute("cancelableProvisionAdjustedDates")
		public CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder getCancelableProvisionAdjustedDates() {
			return cancelableProvisionAdjustedDates;
		}
		
		@Override
		public CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder getOrCreateCancelableProvisionAdjustedDates() {
			CancelableProvisionAdjustedDates.CancelableProvisionAdjustedDatesBuilder result;
			if (cancelableProvisionAdjustedDates!=null) {
				result = cancelableProvisionAdjustedDates;
			}
			else {
				result = cancelableProvisionAdjustedDates = CancelableProvisionAdjustedDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalCalculationPeriodDateAdjustment")
		public List<? extends FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder> getFinalCalculationPeriodDateAdjustment() {
			return finalCalculationPeriodDateAdjustment;
		}
		
		public FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder getOrCreateFinalCalculationPeriodDateAdjustment(int _index) {
		
			if (finalCalculationPeriodDateAdjustment==null) {
				this.finalCalculationPeriodDateAdjustment = new ArrayList<>();
			}
			FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder result;
			return getIndex(finalCalculationPeriodDateAdjustment, _index, () -> {
						FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder newFinalCalculationPeriodDateAdjustment = FinalCalculationPeriodDateAdjustment.builder();
						return newFinalCalculationPeriodDateAdjustment;
					});
		}
		
		@Override
		@RosettaAttribute("initialFee")
		public Transfer.TransferBuilder getInitialFee() {
			return initialFee;
		}
		
		@Override
		public Transfer.TransferBuilder getOrCreateInitialFee() {
			Transfer.TransferBuilder result;
			if (initialFee!=null) {
				result = initialFee;
			}
			else {
				result = initialFee = Transfer.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("callingParty")
		public CallingPartyEnum getCallingParty() {
			return callingParty;
		}
		
		@Override
		@RosettaAttribute("earliestDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getEarliestDate() {
			return earliestDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateEarliestDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (earliestDate!=null) {
				result = earliestDate;
			}
			else {
				result = earliestDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("expirationDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getExpirationDate() {
			return expirationDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (expirationDate!=null) {
				result = expirationDate;
			}
			else {
				result = expirationDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getOrCreateEffectiveDate() {
			AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder result;
			if (effectiveDate!=null) {
				result = effectiveDate;
			}
			else {
				result = effectiveDate = AdjustableOrRelativeDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("effectivePeriod")
		public Period.PeriodBuilder getEffectivePeriod() {
			return effectivePeriod;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateEffectivePeriod() {
			Period.PeriodBuilder result;
			if (effectivePeriod!=null) {
				result = effectivePeriod;
			}
			else {
				result = effectivePeriod = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("earliestCancellationTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getEarliestCancellationTime() {
			return earliestCancellationTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateEarliestCancellationTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (earliestCancellationTime!=null) {
				result = earliestCancellationTime;
			}
			else {
				result = earliestCancellationTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("latestCancelationTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getLatestCancelationTime() {
			return latestCancelationTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateLatestCancelationTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (latestCancelationTime!=null) {
				result = latestCancelationTime;
			}
			else {
				result = latestCancelationTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("buyer")
		public CancelableProvision.CancelableProvisionBuilder setBuyer(CounterpartyRoleEnum buyer) {
			this.buyer = buyer==null?null:buyer;
			return this;
		}
		@Override
		@RosettaAttribute("seller")
		public CancelableProvision.CancelableProvisionBuilder setSeller(CounterpartyRoleEnum seller) {
			this.seller = seller==null?null:seller;
			return this;
		}
		@Override
		@RosettaAttribute("americanExercise")
		public CancelableProvision.CancelableProvisionBuilder setAmericanExercise(AmericanExercise americanExercise) {
			this.americanExercise = americanExercise==null?null:americanExercise.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("bermudaExercise")
		public CancelableProvision.CancelableProvisionBuilder setBermudaExercise(BermudaExercise bermudaExercise) {
			this.bermudaExercise = bermudaExercise==null?null:bermudaExercise.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("europeanExercise")
		public CancelableProvision.CancelableProvisionBuilder setEuropeanExercise(EuropeanExercise europeanExercise) {
			this.europeanExercise = europeanExercise==null?null:europeanExercise.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exerciseNotice")
		public CancelableProvision.CancelableProvisionBuilder setExerciseNotice(ExerciseNotice exerciseNotice) {
			this.exerciseNotice = exerciseNotice==null?null:exerciseNotice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("followUpConfirmation")
		public CancelableProvision.CancelableProvisionBuilder setFollowUpConfirmation(Boolean followUpConfirmation) {
			this.followUpConfirmation = followUpConfirmation==null?null:followUpConfirmation;
			return this;
		}
		@Override
		@RosettaAttribute("cancelableProvisionAdjustedDates")
		public CancelableProvision.CancelableProvisionBuilder setCancelableProvisionAdjustedDates(CancelableProvisionAdjustedDates cancelableProvisionAdjustedDates) {
			this.cancelableProvisionAdjustedDates = cancelableProvisionAdjustedDates==null?null:cancelableProvisionAdjustedDates.toBuilder();
			return this;
		}
		@Override
		public CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(FinalCalculationPeriodDateAdjustment finalCalculationPeriodDateAdjustment) {
			if (finalCalculationPeriodDateAdjustment!=null) this.finalCalculationPeriodDateAdjustment.add(finalCalculationPeriodDateAdjustment.toBuilder());
			return this;
		}
		
		@Override
		public CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(FinalCalculationPeriodDateAdjustment finalCalculationPeriodDateAdjustment, int _idx) {
			getIndex(this.finalCalculationPeriodDateAdjustment, _idx, () -> finalCalculationPeriodDateAdjustment.toBuilder());
			return this;
		}
		@Override 
		public CancelableProvision.CancelableProvisionBuilder addFinalCalculationPeriodDateAdjustment(List<? extends FinalCalculationPeriodDateAdjustment> finalCalculationPeriodDateAdjustments) {
			if (finalCalculationPeriodDateAdjustments != null) {
				for (FinalCalculationPeriodDateAdjustment toAdd : finalCalculationPeriodDateAdjustments) {
					this.finalCalculationPeriodDateAdjustment.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("finalCalculationPeriodDateAdjustment")
		public CancelableProvision.CancelableProvisionBuilder setFinalCalculationPeriodDateAdjustment(List<? extends FinalCalculationPeriodDateAdjustment> finalCalculationPeriodDateAdjustments) {
			if (finalCalculationPeriodDateAdjustments == null)  {
				this.finalCalculationPeriodDateAdjustment = new ArrayList<>();
			}
			else {
				this.finalCalculationPeriodDateAdjustment = finalCalculationPeriodDateAdjustments.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("initialFee")
		public CancelableProvision.CancelableProvisionBuilder setInitialFee(Transfer initialFee) {
			this.initialFee = initialFee==null?null:initialFee.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("callingParty")
		public CancelableProvision.CancelableProvisionBuilder setCallingParty(CallingPartyEnum callingParty) {
			this.callingParty = callingParty==null?null:callingParty;
			return this;
		}
		@Override
		@RosettaAttribute("earliestDate")
		public CancelableProvision.CancelableProvisionBuilder setEarliestDate(AdjustableOrRelativeDate earliestDate) {
			this.earliestDate = earliestDate==null?null:earliestDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("expirationDate")
		public CancelableProvision.CancelableProvisionBuilder setExpirationDate(AdjustableOrRelativeDate expirationDate) {
			this.expirationDate = expirationDate==null?null:expirationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public CancelableProvision.CancelableProvisionBuilder setEffectiveDate(AdjustableOrRelativeDates effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("effectivePeriod")
		public CancelableProvision.CancelableProvisionBuilder setEffectivePeriod(Period effectivePeriod) {
			this.effectivePeriod = effectivePeriod==null?null:effectivePeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("earliestCancellationTime")
		public CancelableProvision.CancelableProvisionBuilder setEarliestCancellationTime(BusinessCenterTime earliestCancellationTime) {
			this.earliestCancellationTime = earliestCancellationTime==null?null:earliestCancellationTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("latestCancelationTime")
		public CancelableProvision.CancelableProvisionBuilder setLatestCancelationTime(BusinessCenterTime latestCancelationTime) {
			this.latestCancelationTime = latestCancelationTime==null?null:latestCancelationTime.toBuilder();
			return this;
		}
		
		@Override
		public CancelableProvision build() {
			return new CancelableProvision.CancelableProvisionImpl(this);
		}
		
		@Override
		public CancelableProvision.CancelableProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CancelableProvision.CancelableProvisionBuilder prune() {
			super.prune();
			if (americanExercise!=null && !americanExercise.prune().hasData()) americanExercise = null;
			if (bermudaExercise!=null && !bermudaExercise.prune().hasData()) bermudaExercise = null;
			if (europeanExercise!=null && !europeanExercise.prune().hasData()) europeanExercise = null;
			if (exerciseNotice!=null && !exerciseNotice.prune().hasData()) exerciseNotice = null;
			if (cancelableProvisionAdjustedDates!=null && !cancelableProvisionAdjustedDates.prune().hasData()) cancelableProvisionAdjustedDates = null;
			finalCalculationPeriodDateAdjustment = finalCalculationPeriodDateAdjustment.stream().filter(b->b!=null).<FinalCalculationPeriodDateAdjustment.FinalCalculationPeriodDateAdjustmentBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (initialFee!=null && !initialFee.prune().hasData()) initialFee = null;
			if (earliestDate!=null && !earliestDate.prune().hasData()) earliestDate = null;
			if (expirationDate!=null && !expirationDate.prune().hasData()) expirationDate = null;
			if (effectiveDate!=null && !effectiveDate.prune().hasData()) effectiveDate = null;
			if (effectivePeriod!=null && !effectivePeriod.prune().hasData()) effectivePeriod = null;
			if (earliestCancellationTime!=null && !earliestCancellationTime.prune().hasData()) earliestCancellationTime = null;
			if (latestCancelationTime!=null && !latestCancelationTime.prune().hasData()) latestCancelationTime = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getAmericanExercise()!=null && getAmericanExercise().hasData()) return true;
			if (getBermudaExercise()!=null && getBermudaExercise().hasData()) return true;
			if (getEuropeanExercise()!=null && getEuropeanExercise().hasData()) return true;
			if (getExerciseNotice()!=null && getExerciseNotice().hasData()) return true;
			if (getFollowUpConfirmation()!=null) return true;
			if (getCancelableProvisionAdjustedDates()!=null && getCancelableProvisionAdjustedDates().hasData()) return true;
			if (getFinalCalculationPeriodDateAdjustment()!=null && getFinalCalculationPeriodDateAdjustment().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getInitialFee()!=null && getInitialFee().hasData()) return true;
			if (getCallingParty()!=null) return true;
			if (getEarliestDate()!=null && getEarliestDate().hasData()) return true;
			if (getExpirationDate()!=null && getExpirationDate().hasData()) return true;
			if (getEffectiveDate()!=null && getEffectiveDate().hasData()) return true;
			if (getEffectivePeriod()!=null && getEffectivePeriod().hasData()) return true;
			if (getEarliestCancellationTime()!=null && getEarliestCancellationTime().hasData()) return true;
			if (getLatestCancelationTime()!=null && getLatestCancelationTime().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CancelableProvision.CancelableProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CancelableProvision.CancelableProvisionBuilder o = (CancelableProvision.CancelableProvisionBuilder) other;
			
			merger.mergeRosetta(getAmericanExercise(), o.getAmericanExercise(), this::setAmericanExercise);
			merger.mergeRosetta(getBermudaExercise(), o.getBermudaExercise(), this::setBermudaExercise);
			merger.mergeRosetta(getEuropeanExercise(), o.getEuropeanExercise(), this::setEuropeanExercise);
			merger.mergeRosetta(getExerciseNotice(), o.getExerciseNotice(), this::setExerciseNotice);
			merger.mergeRosetta(getCancelableProvisionAdjustedDates(), o.getCancelableProvisionAdjustedDates(), this::setCancelableProvisionAdjustedDates);
			merger.mergeRosetta(getFinalCalculationPeriodDateAdjustment(), o.getFinalCalculationPeriodDateAdjustment(), this::getOrCreateFinalCalculationPeriodDateAdjustment);
			merger.mergeRosetta(getInitialFee(), o.getInitialFee(), this::setInitialFee);
			merger.mergeRosetta(getEarliestDate(), o.getEarliestDate(), this::setEarliestDate);
			merger.mergeRosetta(getExpirationDate(), o.getExpirationDate(), this::setExpirationDate);
			merger.mergeRosetta(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			merger.mergeRosetta(getEffectivePeriod(), o.getEffectivePeriod(), this::setEffectivePeriod);
			merger.mergeRosetta(getEarliestCancellationTime(), o.getEarliestCancellationTime(), this::setEarliestCancellationTime);
			merger.mergeRosetta(getLatestCancelationTime(), o.getLatestCancelationTime(), this::setLatestCancelationTime);
			
			merger.mergeBasic(getFollowUpConfirmation(), o.getFollowUpConfirmation(), this::setFollowUpConfirmation);
			merger.mergeBasic(getCallingParty(), o.getCallingParty(), this::setCallingParty);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CancelableProvision _that = getType().cast(o);
		
			if (!Objects.equals(americanExercise, _that.getAmericanExercise())) return false;
			if (!Objects.equals(bermudaExercise, _that.getBermudaExercise())) return false;
			if (!Objects.equals(europeanExercise, _that.getEuropeanExercise())) return false;
			if (!Objects.equals(exerciseNotice, _that.getExerciseNotice())) return false;
			if (!Objects.equals(followUpConfirmation, _that.getFollowUpConfirmation())) return false;
			if (!Objects.equals(cancelableProvisionAdjustedDates, _that.getCancelableProvisionAdjustedDates())) return false;
			if (!ListEquals.listEquals(finalCalculationPeriodDateAdjustment, _that.getFinalCalculationPeriodDateAdjustment())) return false;
			if (!Objects.equals(initialFee, _that.getInitialFee())) return false;
			if (!Objects.equals(callingParty, _that.getCallingParty())) return false;
			if (!Objects.equals(earliestDate, _that.getEarliestDate())) return false;
			if (!Objects.equals(expirationDate, _that.getExpirationDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(effectivePeriod, _that.getEffectivePeriod())) return false;
			if (!Objects.equals(earliestCancellationTime, _that.getEarliestCancellationTime())) return false;
			if (!Objects.equals(latestCancelationTime, _that.getLatestCancelationTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (americanExercise != null ? americanExercise.hashCode() : 0);
			_result = 31 * _result + (bermudaExercise != null ? bermudaExercise.hashCode() : 0);
			_result = 31 * _result + (europeanExercise != null ? europeanExercise.hashCode() : 0);
			_result = 31 * _result + (exerciseNotice != null ? exerciseNotice.hashCode() : 0);
			_result = 31 * _result + (followUpConfirmation != null ? followUpConfirmation.hashCode() : 0);
			_result = 31 * _result + (cancelableProvisionAdjustedDates != null ? cancelableProvisionAdjustedDates.hashCode() : 0);
			_result = 31 * _result + (finalCalculationPeriodDateAdjustment != null ? finalCalculationPeriodDateAdjustment.hashCode() : 0);
			_result = 31 * _result + (initialFee != null ? initialFee.hashCode() : 0);
			_result = 31 * _result + (callingParty != null ? callingParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (earliestDate != null ? earliestDate.hashCode() : 0);
			_result = 31 * _result + (expirationDate != null ? expirationDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (effectivePeriod != null ? effectivePeriod.hashCode() : 0);
			_result = 31 * _result + (earliestCancellationTime != null ? earliestCancellationTime.hashCode() : 0);
			_result = 31 * _result + (latestCancelationTime != null ? latestCancelationTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CancelableProvisionBuilder {" +
				"americanExercise=" + this.americanExercise + ", " +
				"bermudaExercise=" + this.bermudaExercise + ", " +
				"europeanExercise=" + this.europeanExercise + ", " +
				"exerciseNotice=" + this.exerciseNotice + ", " +
				"followUpConfirmation=" + this.followUpConfirmation + ", " +
				"cancelableProvisionAdjustedDates=" + this.cancelableProvisionAdjustedDates + ", " +
				"finalCalculationPeriodDateAdjustment=" + this.finalCalculationPeriodDateAdjustment + ", " +
				"initialFee=" + this.initialFee + ", " +
				"callingParty=" + this.callingParty + ", " +
				"earliestDate=" + this.earliestDate + ", " +
				"expirationDate=" + this.expirationDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"effectivePeriod=" + this.effectivePeriod + ", " +
				"earliestCancellationTime=" + this.earliestCancellationTime + ", " +
				"latestCancelationTime=" + this.latestCancelationTime +
			'}' + " " + super.toString();
		}
	}
}
