package cdm.product.common.settlement;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.product.common.settlement.DeliverableObligations;
import cdm.product.common.settlement.DeliverableObligations.DeliverableObligationsBuilder;
import cdm.product.common.settlement.DeliverableObligations.DeliverableObligationsBuilderImpl;
import cdm.product.common.settlement.DeliverableObligations.DeliverableObligationsImpl;
import cdm.product.common.settlement.LoanParticipation;
import cdm.product.common.settlement.PCDeliverableObligationCharac;
import cdm.product.common.settlement.meta.DeliverableObligationsMeta;
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
 * A class to specify all the ISDA terms relevant to defining the deliverable obligations.
 * @version ${project.version}
 */
@RosettaDataType(value="DeliverableObligations", builder=DeliverableObligations.DeliverableObligationsBuilderImpl.class, version="${project.version}")
public interface DeliverableObligations extends RosettaModelObject {

	DeliverableObligationsMeta metaData = new DeliverableObligationsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether accrued interest is included (true) or not (false). For cash settlement this specifies whether quotations should be obtained inclusive or not of accrued interest. For physical settlement this specifies whether the buyer should deliver the obligation with an outstanding principal balance that includes or excludes accrued interest. ISDA 2003 Term: Include/Exclude Accrued Interest.
	 */
	Boolean getAccruedInterest();
	/**
	 * Used in both obligations and deliverable obligations to represent a class or type of securities which apply. ISDA 2003 Term: Obligation Category/Deliverable Obligation Category.
	 */
	ObligationCategoryEnum getCategory();
	/**
	 * An obligation and deliverable obligation characteristic. An obligation that ranks at least equal with the most senior Reference Obligation in priority of payment or, if no Reference Obligation is specified in the related Confirmation, the obligations of the Reference Entity that are senior. ISDA 2003 Term: Not Subordinated.
	 */
	Boolean getNotSubordinated();
	/**
	 * An obligation and deliverable obligation characteristic. The currency or currencies in which an obligation or deliverable obligation must be payable. ISDA 2003 Term: Specified Currency.
	 */
	SpecifiedCurrency getSpecifiedCurrency();
	/**
	 * An obligation and deliverable obligation characteristic. Any obligation that is not primarily (majority) owed to a Sovereign or Supranational Organisation. ISDA 2003 Term: Not Sovereign Lender.
	 */
	Boolean getNotSovereignLender();
	/**
	 * An obligation and deliverable obligation characteristic. Any obligation that is payable in any currency other than the domestic currency. Domestic currency is either the currency so specified or, if no currency is specified, the currency of (a) the reference entity, if the reference entity is a sovereign, or (b) the jurisdiction in which the relevant reference entity is organised, if the reference entity is not a sovereign. ISDA 2003 Term: Not Domestic Currency.
	 */
	NotDomesticCurrency getNotDomesticCurrency();
	/**
	 * An obligation and deliverable obligation characteristic. If the reference entity is a Sovereign, this means any obligation that is not subject to the laws of the reference entity. If the reference entity is not a sovereign, this means any obligation that is not subject to the laws of the jurisdiction of the reference entity. ISDA 2003 Term: Not Domestic Law.
	 */
	Boolean getNotDomesticLaw();
	/**
	 * An obligation and deliverable obligation characteristic. Indicates whether or not the obligation is quoted, listed or ordinarily purchased and sold on an exchange. ISDA 2003 Term: Listed.
	 */
	Boolean getListed();
	/**
	 * A deliverable obligation characteristic. In essence Not Contingent means the repayment of principal cannot be dependant on a formula/index, i.e. to prevent the risk of being delivered an instrument that may never pay any element of principal, and to ensure that the obligation is interest bearing (on a regular schedule). ISDA 2003 Term: Not Contingent.
	 */
	Boolean getNotContingent();
	/**
	 * An obligation and deliverable obligation characteristic. Any obligation other than an obligation that was intended to be offered for sale primarily in the domestic market of the relevant Reference Entity. This specifies that the obligation must be an internationally recognised bond. ISDA 2003 Term: Not Domestic Issuance.
	 */
	Boolean getNotDomesticIssuance();
	/**
	 * A deliverable obligation characteristic. A loan that is freely assignable to a bank or financial institution without the consent of the Reference Entity or the guarantor, if any, of the loan (or the consent of the applicable borrower if a Reference Entity is guaranteeing the loan) or any agent. ISDA 2003 Term: Assignable Loan.
	 */
	PCDeliverableObligationCharac getAssignableLoan();
	/**
	 * A deliverable obligation characteristic. A loan that is capable of being assigned with the consent of the Reference Entity or the guarantor, if any, of the loan or any agent. ISDA 2003 Term: Consent Required Loan.
	 */
	PCDeliverableObligationCharac getConsentRequiredLoan();
	/**
	 * A deliverable obligation characteristic. A loan with a participation agreement whereby the buyer is capable of creating, or procuring the creation of, a contractual right in favour of the seller that provides the seller with recourse to the participation seller for a specified share in any payments due under the relevant loan which are received by the participation seller. ISDA 2003 Term: Direct Loan Participation.
	 */
	LoanParticipation getDirectLoanParticipation();
	/**
	 * A deliverable obligation characteristic. An obligation that is transferable to institutional investors without any contractual, statutory or regulatory restrictions. ISDA 2003 Term: Transferable.
	 */
	Boolean getTransferable();
	/**
	 * A deliverable obligation characteristic. An obligation that has a remaining maturity from the Physical Settlement Date of not greater than the period specified. ISDA 2003 Term: Maximum Maturity.
	 */
	Period getMaximumMaturity();
	/**
	 * A deliverable obligation characteristic. An obligation at time of default is due to mature and due to be repaid, or as a result of downgrade/bankruptcy is due to be repaid as a result of an acceleration clause. ISDA 2003 Term: Accelerated or Matured.
	 */
	Boolean getAcceleratedOrMatured();
	/**
	 * A deliverable obligation characteristic. Any obligation that is not a bearer instrument. This applies to Bonds only and is meant to avoid tax, fraud and security/delivery provisions that can potentially be associated with Bearer Bonds. ISDA 2003 Term: Not Bearer.
	 */
	Boolean getNotBearer();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published additional provisions for U.S. Municipal as Reference Entity. ISDA 2003 Term: Full Faith and Credit Obligation Liability.
	 */
	Boolean getFullFaithAndCreditObLiability();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published additional provisions for U.S. Municipal as Reference Entity. ISDA 2003 Term: General Fund Obligation Liability.
	 */
	Boolean getGeneralFundObligationLiability();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published additional provisions for U.S. Municipal as Reference Entity. ISDA 2003 Term: Revenue Obligation Liability.
	 */
	Boolean getRevenueObligationLiability();
	/**
	 * ISDA 1999 Term: Indirect Loan Participation. NOTE: Only applicable as a deliverable obligation under ISDA Credit 1999.
	 */
	LoanParticipation getIndirectLoanParticipation();
	/**
	 * A free format string to specify any excluded obligations or deliverable obligations, as the case may be, of the reference entity or excluded types of obligations or deliverable obligations. ISDA 2003 Term: Excluded Obligations/Excluded Deliverable Obligations.
	 */
	String getExcluded();
	/**
	 * This element is used to specify any other obligations of a reference entity in both obligations and deliverable obligations. The obligations can be specified free-form. ISDA 2003 Term: Other Obligations of a Reference Entity.
	 */
	String getOthReferenceEntityObligations();

	/*********************** Build Methods  ***********************/
	DeliverableObligations build();
	
	DeliverableObligations.DeliverableObligationsBuilder toBuilder();
	
	static DeliverableObligations.DeliverableObligationsBuilder builder() {
		return new DeliverableObligations.DeliverableObligationsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DeliverableObligations> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DeliverableObligations> getType() {
		return DeliverableObligations.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("accruedInterest"), Boolean.class, getAccruedInterest(), this);
		processor.processBasic(path.newSubPath("category"), ObligationCategoryEnum.class, getCategory(), this);
		processor.processBasic(path.newSubPath("notSubordinated"), Boolean.class, getNotSubordinated(), this);
		processRosetta(path.newSubPath("specifiedCurrency"), processor, SpecifiedCurrency.class, getSpecifiedCurrency());
		processor.processBasic(path.newSubPath("notSovereignLender"), Boolean.class, getNotSovereignLender(), this);
		processRosetta(path.newSubPath("notDomesticCurrency"), processor, NotDomesticCurrency.class, getNotDomesticCurrency());
		processor.processBasic(path.newSubPath("notDomesticLaw"), Boolean.class, getNotDomesticLaw(), this);
		processor.processBasic(path.newSubPath("listed"), Boolean.class, getListed(), this);
		processor.processBasic(path.newSubPath("notContingent"), Boolean.class, getNotContingent(), this);
		processor.processBasic(path.newSubPath("notDomesticIssuance"), Boolean.class, getNotDomesticIssuance(), this);
		processRosetta(path.newSubPath("assignableLoan"), processor, PCDeliverableObligationCharac.class, getAssignableLoan());
		processRosetta(path.newSubPath("consentRequiredLoan"), processor, PCDeliverableObligationCharac.class, getConsentRequiredLoan());
		processRosetta(path.newSubPath("directLoanParticipation"), processor, LoanParticipation.class, getDirectLoanParticipation());
		processor.processBasic(path.newSubPath("transferable"), Boolean.class, getTransferable(), this);
		processRosetta(path.newSubPath("maximumMaturity"), processor, Period.class, getMaximumMaturity());
		processor.processBasic(path.newSubPath("acceleratedOrMatured"), Boolean.class, getAcceleratedOrMatured(), this);
		processor.processBasic(path.newSubPath("notBearer"), Boolean.class, getNotBearer(), this);
		processor.processBasic(path.newSubPath("fullFaithAndCreditObLiability"), Boolean.class, getFullFaithAndCreditObLiability(), this);
		processor.processBasic(path.newSubPath("generalFundObligationLiability"), Boolean.class, getGeneralFundObligationLiability(), this);
		processor.processBasic(path.newSubPath("revenueObligationLiability"), Boolean.class, getRevenueObligationLiability(), this);
		processRosetta(path.newSubPath("indirectLoanParticipation"), processor, LoanParticipation.class, getIndirectLoanParticipation());
		processor.processBasic(path.newSubPath("excluded"), String.class, getExcluded(), this);
		processor.processBasic(path.newSubPath("othReferenceEntityObligations"), String.class, getOthReferenceEntityObligations(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DeliverableObligationsBuilder extends DeliverableObligations, RosettaModelObjectBuilder {
		SpecifiedCurrency.SpecifiedCurrencyBuilder getOrCreateSpecifiedCurrency();
		SpecifiedCurrency.SpecifiedCurrencyBuilder getSpecifiedCurrency();
		NotDomesticCurrency.NotDomesticCurrencyBuilder getOrCreateNotDomesticCurrency();
		NotDomesticCurrency.NotDomesticCurrencyBuilder getNotDomesticCurrency();
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getOrCreateAssignableLoan();
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getAssignableLoan();
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getOrCreateConsentRequiredLoan();
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getConsentRequiredLoan();
		LoanParticipation.LoanParticipationBuilder getOrCreateDirectLoanParticipation();
		LoanParticipation.LoanParticipationBuilder getDirectLoanParticipation();
		Period.PeriodBuilder getOrCreateMaximumMaturity();
		Period.PeriodBuilder getMaximumMaturity();
		LoanParticipation.LoanParticipationBuilder getOrCreateIndirectLoanParticipation();
		LoanParticipation.LoanParticipationBuilder getIndirectLoanParticipation();
		DeliverableObligations.DeliverableObligationsBuilder setAccruedInterest(Boolean accruedInterest);
		DeliverableObligations.DeliverableObligationsBuilder setCategory(ObligationCategoryEnum category);
		DeliverableObligations.DeliverableObligationsBuilder setNotSubordinated(Boolean notSubordinated);
		DeliverableObligations.DeliverableObligationsBuilder setSpecifiedCurrency(SpecifiedCurrency specifiedCurrency);
		DeliverableObligations.DeliverableObligationsBuilder setNotSovereignLender(Boolean notSovereignLender);
		DeliverableObligations.DeliverableObligationsBuilder setNotDomesticCurrency(NotDomesticCurrency notDomesticCurrency);
		DeliverableObligations.DeliverableObligationsBuilder setNotDomesticLaw(Boolean notDomesticLaw);
		DeliverableObligations.DeliverableObligationsBuilder setListed(Boolean listed);
		DeliverableObligations.DeliverableObligationsBuilder setNotContingent(Boolean notContingent);
		DeliverableObligations.DeliverableObligationsBuilder setNotDomesticIssuance(Boolean notDomesticIssuance);
		DeliverableObligations.DeliverableObligationsBuilder setAssignableLoan(PCDeliverableObligationCharac assignableLoan);
		DeliverableObligations.DeliverableObligationsBuilder setConsentRequiredLoan(PCDeliverableObligationCharac consentRequiredLoan);
		DeliverableObligations.DeliverableObligationsBuilder setDirectLoanParticipation(LoanParticipation directLoanParticipation);
		DeliverableObligations.DeliverableObligationsBuilder setTransferable(Boolean transferable);
		DeliverableObligations.DeliverableObligationsBuilder setMaximumMaturity(Period maximumMaturity);
		DeliverableObligations.DeliverableObligationsBuilder setAcceleratedOrMatured(Boolean acceleratedOrMatured);
		DeliverableObligations.DeliverableObligationsBuilder setNotBearer(Boolean notBearer);
		DeliverableObligations.DeliverableObligationsBuilder setFullFaithAndCreditObLiability(Boolean fullFaithAndCreditObLiability);
		DeliverableObligations.DeliverableObligationsBuilder setGeneralFundObligationLiability(Boolean generalFundObligationLiability);
		DeliverableObligations.DeliverableObligationsBuilder setRevenueObligationLiability(Boolean revenueObligationLiability);
		DeliverableObligations.DeliverableObligationsBuilder setIndirectLoanParticipation(LoanParticipation indirectLoanParticipation);
		DeliverableObligations.DeliverableObligationsBuilder setExcluded(String excluded);
		DeliverableObligations.DeliverableObligationsBuilder setOthReferenceEntityObligations(String othReferenceEntityObligations);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("accruedInterest"), Boolean.class, getAccruedInterest(), this);
			processor.processBasic(path.newSubPath("category"), ObligationCategoryEnum.class, getCategory(), this);
			processor.processBasic(path.newSubPath("notSubordinated"), Boolean.class, getNotSubordinated(), this);
			processRosetta(path.newSubPath("specifiedCurrency"), processor, SpecifiedCurrency.SpecifiedCurrencyBuilder.class, getSpecifiedCurrency());
			processor.processBasic(path.newSubPath("notSovereignLender"), Boolean.class, getNotSovereignLender(), this);
			processRosetta(path.newSubPath("notDomesticCurrency"), processor, NotDomesticCurrency.NotDomesticCurrencyBuilder.class, getNotDomesticCurrency());
			processor.processBasic(path.newSubPath("notDomesticLaw"), Boolean.class, getNotDomesticLaw(), this);
			processor.processBasic(path.newSubPath("listed"), Boolean.class, getListed(), this);
			processor.processBasic(path.newSubPath("notContingent"), Boolean.class, getNotContingent(), this);
			processor.processBasic(path.newSubPath("notDomesticIssuance"), Boolean.class, getNotDomesticIssuance(), this);
			processRosetta(path.newSubPath("assignableLoan"), processor, PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder.class, getAssignableLoan());
			processRosetta(path.newSubPath("consentRequiredLoan"), processor, PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder.class, getConsentRequiredLoan());
			processRosetta(path.newSubPath("directLoanParticipation"), processor, LoanParticipation.LoanParticipationBuilder.class, getDirectLoanParticipation());
			processor.processBasic(path.newSubPath("transferable"), Boolean.class, getTransferable(), this);
			processRosetta(path.newSubPath("maximumMaturity"), processor, Period.PeriodBuilder.class, getMaximumMaturity());
			processor.processBasic(path.newSubPath("acceleratedOrMatured"), Boolean.class, getAcceleratedOrMatured(), this);
			processor.processBasic(path.newSubPath("notBearer"), Boolean.class, getNotBearer(), this);
			processor.processBasic(path.newSubPath("fullFaithAndCreditObLiability"), Boolean.class, getFullFaithAndCreditObLiability(), this);
			processor.processBasic(path.newSubPath("generalFundObligationLiability"), Boolean.class, getGeneralFundObligationLiability(), this);
			processor.processBasic(path.newSubPath("revenueObligationLiability"), Boolean.class, getRevenueObligationLiability(), this);
			processRosetta(path.newSubPath("indirectLoanParticipation"), processor, LoanParticipation.LoanParticipationBuilder.class, getIndirectLoanParticipation());
			processor.processBasic(path.newSubPath("excluded"), String.class, getExcluded(), this);
			processor.processBasic(path.newSubPath("othReferenceEntityObligations"), String.class, getOthReferenceEntityObligations(), this);
		}
		

		DeliverableObligations.DeliverableObligationsBuilder prune();
	}

	/*********************** Immutable Implementation of DeliverableObligations  ***********************/
	class DeliverableObligationsImpl implements DeliverableObligations {
		private final Boolean accruedInterest;
		private final ObligationCategoryEnum category;
		private final Boolean notSubordinated;
		private final SpecifiedCurrency specifiedCurrency;
		private final Boolean notSovereignLender;
		private final NotDomesticCurrency notDomesticCurrency;
		private final Boolean notDomesticLaw;
		private final Boolean listed;
		private final Boolean notContingent;
		private final Boolean notDomesticIssuance;
		private final PCDeliverableObligationCharac assignableLoan;
		private final PCDeliverableObligationCharac consentRequiredLoan;
		private final LoanParticipation directLoanParticipation;
		private final Boolean transferable;
		private final Period maximumMaturity;
		private final Boolean acceleratedOrMatured;
		private final Boolean notBearer;
		private final Boolean fullFaithAndCreditObLiability;
		private final Boolean generalFundObligationLiability;
		private final Boolean revenueObligationLiability;
		private final LoanParticipation indirectLoanParticipation;
		private final String excluded;
		private final String othReferenceEntityObligations;
		
		protected DeliverableObligationsImpl(DeliverableObligations.DeliverableObligationsBuilder builder) {
			this.accruedInterest = builder.getAccruedInterest();
			this.category = builder.getCategory();
			this.notSubordinated = builder.getNotSubordinated();
			this.specifiedCurrency = ofNullable(builder.getSpecifiedCurrency()).map(f->f.build()).orElse(null);
			this.notSovereignLender = builder.getNotSovereignLender();
			this.notDomesticCurrency = ofNullable(builder.getNotDomesticCurrency()).map(f->f.build()).orElse(null);
			this.notDomesticLaw = builder.getNotDomesticLaw();
			this.listed = builder.getListed();
			this.notContingent = builder.getNotContingent();
			this.notDomesticIssuance = builder.getNotDomesticIssuance();
			this.assignableLoan = ofNullable(builder.getAssignableLoan()).map(f->f.build()).orElse(null);
			this.consentRequiredLoan = ofNullable(builder.getConsentRequiredLoan()).map(f->f.build()).orElse(null);
			this.directLoanParticipation = ofNullable(builder.getDirectLoanParticipation()).map(f->f.build()).orElse(null);
			this.transferable = builder.getTransferable();
			this.maximumMaturity = ofNullable(builder.getMaximumMaturity()).map(f->f.build()).orElse(null);
			this.acceleratedOrMatured = builder.getAcceleratedOrMatured();
			this.notBearer = builder.getNotBearer();
			this.fullFaithAndCreditObLiability = builder.getFullFaithAndCreditObLiability();
			this.generalFundObligationLiability = builder.getGeneralFundObligationLiability();
			this.revenueObligationLiability = builder.getRevenueObligationLiability();
			this.indirectLoanParticipation = ofNullable(builder.getIndirectLoanParticipation()).map(f->f.build()).orElse(null);
			this.excluded = builder.getExcluded();
			this.othReferenceEntityObligations = builder.getOthReferenceEntityObligations();
		}
		
		@Override
		@RosettaAttribute("accruedInterest")
		public Boolean getAccruedInterest() {
			return accruedInterest;
		}
		
		@Override
		@RosettaAttribute("category")
		public ObligationCategoryEnum getCategory() {
			return category;
		}
		
		@Override
		@RosettaAttribute("notSubordinated")
		public Boolean getNotSubordinated() {
			return notSubordinated;
		}
		
		@Override
		@RosettaAttribute("specifiedCurrency")
		public SpecifiedCurrency getSpecifiedCurrency() {
			return specifiedCurrency;
		}
		
		@Override
		@RosettaAttribute("notSovereignLender")
		public Boolean getNotSovereignLender() {
			return notSovereignLender;
		}
		
		@Override
		@RosettaAttribute("notDomesticCurrency")
		public NotDomesticCurrency getNotDomesticCurrency() {
			return notDomesticCurrency;
		}
		
		@Override
		@RosettaAttribute("notDomesticLaw")
		public Boolean getNotDomesticLaw() {
			return notDomesticLaw;
		}
		
		@Override
		@RosettaAttribute("listed")
		public Boolean getListed() {
			return listed;
		}
		
		@Override
		@RosettaAttribute("notContingent")
		public Boolean getNotContingent() {
			return notContingent;
		}
		
		@Override
		@RosettaAttribute("notDomesticIssuance")
		public Boolean getNotDomesticIssuance() {
			return notDomesticIssuance;
		}
		
		@Override
		@RosettaAttribute("assignableLoan")
		public PCDeliverableObligationCharac getAssignableLoan() {
			return assignableLoan;
		}
		
		@Override
		@RosettaAttribute("consentRequiredLoan")
		public PCDeliverableObligationCharac getConsentRequiredLoan() {
			return consentRequiredLoan;
		}
		
		@Override
		@RosettaAttribute("directLoanParticipation")
		public LoanParticipation getDirectLoanParticipation() {
			return directLoanParticipation;
		}
		
		@Override
		@RosettaAttribute("transferable")
		public Boolean getTransferable() {
			return transferable;
		}
		
		@Override
		@RosettaAttribute("maximumMaturity")
		public Period getMaximumMaturity() {
			return maximumMaturity;
		}
		
		@Override
		@RosettaAttribute("acceleratedOrMatured")
		public Boolean getAcceleratedOrMatured() {
			return acceleratedOrMatured;
		}
		
		@Override
		@RosettaAttribute("notBearer")
		public Boolean getNotBearer() {
			return notBearer;
		}
		
		@Override
		@RosettaAttribute("fullFaithAndCreditObLiability")
		public Boolean getFullFaithAndCreditObLiability() {
			return fullFaithAndCreditObLiability;
		}
		
		@Override
		@RosettaAttribute("generalFundObligationLiability")
		public Boolean getGeneralFundObligationLiability() {
			return generalFundObligationLiability;
		}
		
		@Override
		@RosettaAttribute("revenueObligationLiability")
		public Boolean getRevenueObligationLiability() {
			return revenueObligationLiability;
		}
		
		@Override
		@RosettaAttribute("indirectLoanParticipation")
		public LoanParticipation getIndirectLoanParticipation() {
			return indirectLoanParticipation;
		}
		
		@Override
		@RosettaAttribute("excluded")
		public String getExcluded() {
			return excluded;
		}
		
		@Override
		@RosettaAttribute("othReferenceEntityObligations")
		public String getOthReferenceEntityObligations() {
			return othReferenceEntityObligations;
		}
		
		@Override
		public DeliverableObligations build() {
			return this;
		}
		
		@Override
		public DeliverableObligations.DeliverableObligationsBuilder toBuilder() {
			DeliverableObligations.DeliverableObligationsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DeliverableObligations.DeliverableObligationsBuilder builder) {
			ofNullable(getAccruedInterest()).ifPresent(builder::setAccruedInterest);
			ofNullable(getCategory()).ifPresent(builder::setCategory);
			ofNullable(getNotSubordinated()).ifPresent(builder::setNotSubordinated);
			ofNullable(getSpecifiedCurrency()).ifPresent(builder::setSpecifiedCurrency);
			ofNullable(getNotSovereignLender()).ifPresent(builder::setNotSovereignLender);
			ofNullable(getNotDomesticCurrency()).ifPresent(builder::setNotDomesticCurrency);
			ofNullable(getNotDomesticLaw()).ifPresent(builder::setNotDomesticLaw);
			ofNullable(getListed()).ifPresent(builder::setListed);
			ofNullable(getNotContingent()).ifPresent(builder::setNotContingent);
			ofNullable(getNotDomesticIssuance()).ifPresent(builder::setNotDomesticIssuance);
			ofNullable(getAssignableLoan()).ifPresent(builder::setAssignableLoan);
			ofNullable(getConsentRequiredLoan()).ifPresent(builder::setConsentRequiredLoan);
			ofNullable(getDirectLoanParticipation()).ifPresent(builder::setDirectLoanParticipation);
			ofNullable(getTransferable()).ifPresent(builder::setTransferable);
			ofNullable(getMaximumMaturity()).ifPresent(builder::setMaximumMaturity);
			ofNullable(getAcceleratedOrMatured()).ifPresent(builder::setAcceleratedOrMatured);
			ofNullable(getNotBearer()).ifPresent(builder::setNotBearer);
			ofNullable(getFullFaithAndCreditObLiability()).ifPresent(builder::setFullFaithAndCreditObLiability);
			ofNullable(getGeneralFundObligationLiability()).ifPresent(builder::setGeneralFundObligationLiability);
			ofNullable(getRevenueObligationLiability()).ifPresent(builder::setRevenueObligationLiability);
			ofNullable(getIndirectLoanParticipation()).ifPresent(builder::setIndirectLoanParticipation);
			ofNullable(getExcluded()).ifPresent(builder::setExcluded);
			ofNullable(getOthReferenceEntityObligations()).ifPresent(builder::setOthReferenceEntityObligations);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliverableObligations _that = getType().cast(o);
		
			if (!Objects.equals(accruedInterest, _that.getAccruedInterest())) return false;
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(notSubordinated, _that.getNotSubordinated())) return false;
			if (!Objects.equals(specifiedCurrency, _that.getSpecifiedCurrency())) return false;
			if (!Objects.equals(notSovereignLender, _that.getNotSovereignLender())) return false;
			if (!Objects.equals(notDomesticCurrency, _that.getNotDomesticCurrency())) return false;
			if (!Objects.equals(notDomesticLaw, _that.getNotDomesticLaw())) return false;
			if (!Objects.equals(listed, _that.getListed())) return false;
			if (!Objects.equals(notContingent, _that.getNotContingent())) return false;
			if (!Objects.equals(notDomesticIssuance, _that.getNotDomesticIssuance())) return false;
			if (!Objects.equals(assignableLoan, _that.getAssignableLoan())) return false;
			if (!Objects.equals(consentRequiredLoan, _that.getConsentRequiredLoan())) return false;
			if (!Objects.equals(directLoanParticipation, _that.getDirectLoanParticipation())) return false;
			if (!Objects.equals(transferable, _that.getTransferable())) return false;
			if (!Objects.equals(maximumMaturity, _that.getMaximumMaturity())) return false;
			if (!Objects.equals(acceleratedOrMatured, _that.getAcceleratedOrMatured())) return false;
			if (!Objects.equals(notBearer, _that.getNotBearer())) return false;
			if (!Objects.equals(fullFaithAndCreditObLiability, _that.getFullFaithAndCreditObLiability())) return false;
			if (!Objects.equals(generalFundObligationLiability, _that.getGeneralFundObligationLiability())) return false;
			if (!Objects.equals(revenueObligationLiability, _that.getRevenueObligationLiability())) return false;
			if (!Objects.equals(indirectLoanParticipation, _that.getIndirectLoanParticipation())) return false;
			if (!Objects.equals(excluded, _that.getExcluded())) return false;
			if (!Objects.equals(othReferenceEntityObligations, _that.getOthReferenceEntityObligations())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (accruedInterest != null ? accruedInterest.hashCode() : 0);
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notSubordinated != null ? notSubordinated.hashCode() : 0);
			_result = 31 * _result + (specifiedCurrency != null ? specifiedCurrency.hashCode() : 0);
			_result = 31 * _result + (notSovereignLender != null ? notSovereignLender.hashCode() : 0);
			_result = 31 * _result + (notDomesticCurrency != null ? notDomesticCurrency.hashCode() : 0);
			_result = 31 * _result + (notDomesticLaw != null ? notDomesticLaw.hashCode() : 0);
			_result = 31 * _result + (listed != null ? listed.hashCode() : 0);
			_result = 31 * _result + (notContingent != null ? notContingent.hashCode() : 0);
			_result = 31 * _result + (notDomesticIssuance != null ? notDomesticIssuance.hashCode() : 0);
			_result = 31 * _result + (assignableLoan != null ? assignableLoan.hashCode() : 0);
			_result = 31 * _result + (consentRequiredLoan != null ? consentRequiredLoan.hashCode() : 0);
			_result = 31 * _result + (directLoanParticipation != null ? directLoanParticipation.hashCode() : 0);
			_result = 31 * _result + (transferable != null ? transferable.hashCode() : 0);
			_result = 31 * _result + (maximumMaturity != null ? maximumMaturity.hashCode() : 0);
			_result = 31 * _result + (acceleratedOrMatured != null ? acceleratedOrMatured.hashCode() : 0);
			_result = 31 * _result + (notBearer != null ? notBearer.hashCode() : 0);
			_result = 31 * _result + (fullFaithAndCreditObLiability != null ? fullFaithAndCreditObLiability.hashCode() : 0);
			_result = 31 * _result + (generalFundObligationLiability != null ? generalFundObligationLiability.hashCode() : 0);
			_result = 31 * _result + (revenueObligationLiability != null ? revenueObligationLiability.hashCode() : 0);
			_result = 31 * _result + (indirectLoanParticipation != null ? indirectLoanParticipation.hashCode() : 0);
			_result = 31 * _result + (excluded != null ? excluded.hashCode() : 0);
			_result = 31 * _result + (othReferenceEntityObligations != null ? othReferenceEntityObligations.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliverableObligations {" +
				"accruedInterest=" + this.accruedInterest + ", " +
				"category=" + this.category + ", " +
				"notSubordinated=" + this.notSubordinated + ", " +
				"specifiedCurrency=" + this.specifiedCurrency + ", " +
				"notSovereignLender=" + this.notSovereignLender + ", " +
				"notDomesticCurrency=" + this.notDomesticCurrency + ", " +
				"notDomesticLaw=" + this.notDomesticLaw + ", " +
				"listed=" + this.listed + ", " +
				"notContingent=" + this.notContingent + ", " +
				"notDomesticIssuance=" + this.notDomesticIssuance + ", " +
				"assignableLoan=" + this.assignableLoan + ", " +
				"consentRequiredLoan=" + this.consentRequiredLoan + ", " +
				"directLoanParticipation=" + this.directLoanParticipation + ", " +
				"transferable=" + this.transferable + ", " +
				"maximumMaturity=" + this.maximumMaturity + ", " +
				"acceleratedOrMatured=" + this.acceleratedOrMatured + ", " +
				"notBearer=" + this.notBearer + ", " +
				"fullFaithAndCreditObLiability=" + this.fullFaithAndCreditObLiability + ", " +
				"generalFundObligationLiability=" + this.generalFundObligationLiability + ", " +
				"revenueObligationLiability=" + this.revenueObligationLiability + ", " +
				"indirectLoanParticipation=" + this.indirectLoanParticipation + ", " +
				"excluded=" + this.excluded + ", " +
				"othReferenceEntityObligations=" + this.othReferenceEntityObligations +
			'}';
		}
	}

	/*********************** Builder Implementation of DeliverableObligations  ***********************/
	class DeliverableObligationsBuilderImpl implements DeliverableObligations.DeliverableObligationsBuilder {
	
		protected Boolean accruedInterest;
		protected ObligationCategoryEnum category;
		protected Boolean notSubordinated;
		protected SpecifiedCurrency.SpecifiedCurrencyBuilder specifiedCurrency;
		protected Boolean notSovereignLender;
		protected NotDomesticCurrency.NotDomesticCurrencyBuilder notDomesticCurrency;
		protected Boolean notDomesticLaw;
		protected Boolean listed;
		protected Boolean notContingent;
		protected Boolean notDomesticIssuance;
		protected PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder assignableLoan;
		protected PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder consentRequiredLoan;
		protected LoanParticipation.LoanParticipationBuilder directLoanParticipation;
		protected Boolean transferable;
		protected Period.PeriodBuilder maximumMaturity;
		protected Boolean acceleratedOrMatured;
		protected Boolean notBearer;
		protected Boolean fullFaithAndCreditObLiability;
		protected Boolean generalFundObligationLiability;
		protected Boolean revenueObligationLiability;
		protected LoanParticipation.LoanParticipationBuilder indirectLoanParticipation;
		protected String excluded;
		protected String othReferenceEntityObligations;
	
		public DeliverableObligationsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("accruedInterest")
		public Boolean getAccruedInterest() {
			return accruedInterest;
		}
		
		@Override
		@RosettaAttribute("category")
		public ObligationCategoryEnum getCategory() {
			return category;
		}
		
		@Override
		@RosettaAttribute("notSubordinated")
		public Boolean getNotSubordinated() {
			return notSubordinated;
		}
		
		@Override
		@RosettaAttribute("specifiedCurrency")
		public SpecifiedCurrency.SpecifiedCurrencyBuilder getSpecifiedCurrency() {
			return specifiedCurrency;
		}
		
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder getOrCreateSpecifiedCurrency() {
			SpecifiedCurrency.SpecifiedCurrencyBuilder result;
			if (specifiedCurrency!=null) {
				result = specifiedCurrency;
			}
			else {
				result = specifiedCurrency = SpecifiedCurrency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("notSovereignLender")
		public Boolean getNotSovereignLender() {
			return notSovereignLender;
		}
		
		@Override
		@RosettaAttribute("notDomesticCurrency")
		public NotDomesticCurrency.NotDomesticCurrencyBuilder getNotDomesticCurrency() {
			return notDomesticCurrency;
		}
		
		@Override
		public NotDomesticCurrency.NotDomesticCurrencyBuilder getOrCreateNotDomesticCurrency() {
			NotDomesticCurrency.NotDomesticCurrencyBuilder result;
			if (notDomesticCurrency!=null) {
				result = notDomesticCurrency;
			}
			else {
				result = notDomesticCurrency = NotDomesticCurrency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("notDomesticLaw")
		public Boolean getNotDomesticLaw() {
			return notDomesticLaw;
		}
		
		@Override
		@RosettaAttribute("listed")
		public Boolean getListed() {
			return listed;
		}
		
		@Override
		@RosettaAttribute("notContingent")
		public Boolean getNotContingent() {
			return notContingent;
		}
		
		@Override
		@RosettaAttribute("notDomesticIssuance")
		public Boolean getNotDomesticIssuance() {
			return notDomesticIssuance;
		}
		
		@Override
		@RosettaAttribute("assignableLoan")
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getAssignableLoan() {
			return assignableLoan;
		}
		
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getOrCreateAssignableLoan() {
			PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder result;
			if (assignableLoan!=null) {
				result = assignableLoan;
			}
			else {
				result = assignableLoan = PCDeliverableObligationCharac.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("consentRequiredLoan")
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getConsentRequiredLoan() {
			return consentRequiredLoan;
		}
		
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder getOrCreateConsentRequiredLoan() {
			PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder result;
			if (consentRequiredLoan!=null) {
				result = consentRequiredLoan;
			}
			else {
				result = consentRequiredLoan = PCDeliverableObligationCharac.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("directLoanParticipation")
		public LoanParticipation.LoanParticipationBuilder getDirectLoanParticipation() {
			return directLoanParticipation;
		}
		
		@Override
		public LoanParticipation.LoanParticipationBuilder getOrCreateDirectLoanParticipation() {
			LoanParticipation.LoanParticipationBuilder result;
			if (directLoanParticipation!=null) {
				result = directLoanParticipation;
			}
			else {
				result = directLoanParticipation = LoanParticipation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("transferable")
		public Boolean getTransferable() {
			return transferable;
		}
		
		@Override
		@RosettaAttribute("maximumMaturity")
		public Period.PeriodBuilder getMaximumMaturity() {
			return maximumMaturity;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateMaximumMaturity() {
			Period.PeriodBuilder result;
			if (maximumMaturity!=null) {
				result = maximumMaturity;
			}
			else {
				result = maximumMaturity = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("acceleratedOrMatured")
		public Boolean getAcceleratedOrMatured() {
			return acceleratedOrMatured;
		}
		
		@Override
		@RosettaAttribute("notBearer")
		public Boolean getNotBearer() {
			return notBearer;
		}
		
		@Override
		@RosettaAttribute("fullFaithAndCreditObLiability")
		public Boolean getFullFaithAndCreditObLiability() {
			return fullFaithAndCreditObLiability;
		}
		
		@Override
		@RosettaAttribute("generalFundObligationLiability")
		public Boolean getGeneralFundObligationLiability() {
			return generalFundObligationLiability;
		}
		
		@Override
		@RosettaAttribute("revenueObligationLiability")
		public Boolean getRevenueObligationLiability() {
			return revenueObligationLiability;
		}
		
		@Override
		@RosettaAttribute("indirectLoanParticipation")
		public LoanParticipation.LoanParticipationBuilder getIndirectLoanParticipation() {
			return indirectLoanParticipation;
		}
		
		@Override
		public LoanParticipation.LoanParticipationBuilder getOrCreateIndirectLoanParticipation() {
			LoanParticipation.LoanParticipationBuilder result;
			if (indirectLoanParticipation!=null) {
				result = indirectLoanParticipation;
			}
			else {
				result = indirectLoanParticipation = LoanParticipation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("excluded")
		public String getExcluded() {
			return excluded;
		}
		
		@Override
		@RosettaAttribute("othReferenceEntityObligations")
		public String getOthReferenceEntityObligations() {
			return othReferenceEntityObligations;
		}
		
	
		@Override
		@RosettaAttribute("accruedInterest")
		public DeliverableObligations.DeliverableObligationsBuilder setAccruedInterest(Boolean accruedInterest) {
			this.accruedInterest = accruedInterest==null?null:accruedInterest;
			return this;
		}
		@Override
		@RosettaAttribute("category")
		public DeliverableObligations.DeliverableObligationsBuilder setCategory(ObligationCategoryEnum category) {
			this.category = category==null?null:category;
			return this;
		}
		@Override
		@RosettaAttribute("notSubordinated")
		public DeliverableObligations.DeliverableObligationsBuilder setNotSubordinated(Boolean notSubordinated) {
			this.notSubordinated = notSubordinated==null?null:notSubordinated;
			return this;
		}
		@Override
		@RosettaAttribute("specifiedCurrency")
		public DeliverableObligations.DeliverableObligationsBuilder setSpecifiedCurrency(SpecifiedCurrency specifiedCurrency) {
			this.specifiedCurrency = specifiedCurrency==null?null:specifiedCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("notSovereignLender")
		public DeliverableObligations.DeliverableObligationsBuilder setNotSovereignLender(Boolean notSovereignLender) {
			this.notSovereignLender = notSovereignLender==null?null:notSovereignLender;
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticCurrency")
		public DeliverableObligations.DeliverableObligationsBuilder setNotDomesticCurrency(NotDomesticCurrency notDomesticCurrency) {
			this.notDomesticCurrency = notDomesticCurrency==null?null:notDomesticCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticLaw")
		public DeliverableObligations.DeliverableObligationsBuilder setNotDomesticLaw(Boolean notDomesticLaw) {
			this.notDomesticLaw = notDomesticLaw==null?null:notDomesticLaw;
			return this;
		}
		@Override
		@RosettaAttribute("listed")
		public DeliverableObligations.DeliverableObligationsBuilder setListed(Boolean listed) {
			this.listed = listed==null?null:listed;
			return this;
		}
		@Override
		@RosettaAttribute("notContingent")
		public DeliverableObligations.DeliverableObligationsBuilder setNotContingent(Boolean notContingent) {
			this.notContingent = notContingent==null?null:notContingent;
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticIssuance")
		public DeliverableObligations.DeliverableObligationsBuilder setNotDomesticIssuance(Boolean notDomesticIssuance) {
			this.notDomesticIssuance = notDomesticIssuance==null?null:notDomesticIssuance;
			return this;
		}
		@Override
		@RosettaAttribute("assignableLoan")
		public DeliverableObligations.DeliverableObligationsBuilder setAssignableLoan(PCDeliverableObligationCharac assignableLoan) {
			this.assignableLoan = assignableLoan==null?null:assignableLoan.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("consentRequiredLoan")
		public DeliverableObligations.DeliverableObligationsBuilder setConsentRequiredLoan(PCDeliverableObligationCharac consentRequiredLoan) {
			this.consentRequiredLoan = consentRequiredLoan==null?null:consentRequiredLoan.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("directLoanParticipation")
		public DeliverableObligations.DeliverableObligationsBuilder setDirectLoanParticipation(LoanParticipation directLoanParticipation) {
			this.directLoanParticipation = directLoanParticipation==null?null:directLoanParticipation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("transferable")
		public DeliverableObligations.DeliverableObligationsBuilder setTransferable(Boolean transferable) {
			this.transferable = transferable==null?null:transferable;
			return this;
		}
		@Override
		@RosettaAttribute("maximumMaturity")
		public DeliverableObligations.DeliverableObligationsBuilder setMaximumMaturity(Period maximumMaturity) {
			this.maximumMaturity = maximumMaturity==null?null:maximumMaturity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("acceleratedOrMatured")
		public DeliverableObligations.DeliverableObligationsBuilder setAcceleratedOrMatured(Boolean acceleratedOrMatured) {
			this.acceleratedOrMatured = acceleratedOrMatured==null?null:acceleratedOrMatured;
			return this;
		}
		@Override
		@RosettaAttribute("notBearer")
		public DeliverableObligations.DeliverableObligationsBuilder setNotBearer(Boolean notBearer) {
			this.notBearer = notBearer==null?null:notBearer;
			return this;
		}
		@Override
		@RosettaAttribute("fullFaithAndCreditObLiability")
		public DeliverableObligations.DeliverableObligationsBuilder setFullFaithAndCreditObLiability(Boolean fullFaithAndCreditObLiability) {
			this.fullFaithAndCreditObLiability = fullFaithAndCreditObLiability==null?null:fullFaithAndCreditObLiability;
			return this;
		}
		@Override
		@RosettaAttribute("generalFundObligationLiability")
		public DeliverableObligations.DeliverableObligationsBuilder setGeneralFundObligationLiability(Boolean generalFundObligationLiability) {
			this.generalFundObligationLiability = generalFundObligationLiability==null?null:generalFundObligationLiability;
			return this;
		}
		@Override
		@RosettaAttribute("revenueObligationLiability")
		public DeliverableObligations.DeliverableObligationsBuilder setRevenueObligationLiability(Boolean revenueObligationLiability) {
			this.revenueObligationLiability = revenueObligationLiability==null?null:revenueObligationLiability;
			return this;
		}
		@Override
		@RosettaAttribute("indirectLoanParticipation")
		public DeliverableObligations.DeliverableObligationsBuilder setIndirectLoanParticipation(LoanParticipation indirectLoanParticipation) {
			this.indirectLoanParticipation = indirectLoanParticipation==null?null:indirectLoanParticipation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("excluded")
		public DeliverableObligations.DeliverableObligationsBuilder setExcluded(String excluded) {
			this.excluded = excluded==null?null:excluded;
			return this;
		}
		@Override
		@RosettaAttribute("othReferenceEntityObligations")
		public DeliverableObligations.DeliverableObligationsBuilder setOthReferenceEntityObligations(String othReferenceEntityObligations) {
			this.othReferenceEntityObligations = othReferenceEntityObligations==null?null:othReferenceEntityObligations;
			return this;
		}
		
		@Override
		public DeliverableObligations build() {
			return new DeliverableObligations.DeliverableObligationsImpl(this);
		}
		
		@Override
		public DeliverableObligations.DeliverableObligationsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliverableObligations.DeliverableObligationsBuilder prune() {
			if (specifiedCurrency!=null && !specifiedCurrency.prune().hasData()) specifiedCurrency = null;
			if (notDomesticCurrency!=null && !notDomesticCurrency.prune().hasData()) notDomesticCurrency = null;
			if (assignableLoan!=null && !assignableLoan.prune().hasData()) assignableLoan = null;
			if (consentRequiredLoan!=null && !consentRequiredLoan.prune().hasData()) consentRequiredLoan = null;
			if (directLoanParticipation!=null && !directLoanParticipation.prune().hasData()) directLoanParticipation = null;
			if (maximumMaturity!=null && !maximumMaturity.prune().hasData()) maximumMaturity = null;
			if (indirectLoanParticipation!=null && !indirectLoanParticipation.prune().hasData()) indirectLoanParticipation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAccruedInterest()!=null) return true;
			if (getCategory()!=null) return true;
			if (getNotSubordinated()!=null) return true;
			if (getSpecifiedCurrency()!=null && getSpecifiedCurrency().hasData()) return true;
			if (getNotSovereignLender()!=null) return true;
			if (getNotDomesticCurrency()!=null && getNotDomesticCurrency().hasData()) return true;
			if (getNotDomesticLaw()!=null) return true;
			if (getListed()!=null) return true;
			if (getNotContingent()!=null) return true;
			if (getNotDomesticIssuance()!=null) return true;
			if (getAssignableLoan()!=null && getAssignableLoan().hasData()) return true;
			if (getConsentRequiredLoan()!=null && getConsentRequiredLoan().hasData()) return true;
			if (getDirectLoanParticipation()!=null && getDirectLoanParticipation().hasData()) return true;
			if (getTransferable()!=null) return true;
			if (getMaximumMaturity()!=null && getMaximumMaturity().hasData()) return true;
			if (getAcceleratedOrMatured()!=null) return true;
			if (getNotBearer()!=null) return true;
			if (getFullFaithAndCreditObLiability()!=null) return true;
			if (getGeneralFundObligationLiability()!=null) return true;
			if (getRevenueObligationLiability()!=null) return true;
			if (getIndirectLoanParticipation()!=null && getIndirectLoanParticipation().hasData()) return true;
			if (getExcluded()!=null) return true;
			if (getOthReferenceEntityObligations()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliverableObligations.DeliverableObligationsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DeliverableObligations.DeliverableObligationsBuilder o = (DeliverableObligations.DeliverableObligationsBuilder) other;
			
			merger.mergeRosetta(getSpecifiedCurrency(), o.getSpecifiedCurrency(), this::setSpecifiedCurrency);
			merger.mergeRosetta(getNotDomesticCurrency(), o.getNotDomesticCurrency(), this::setNotDomesticCurrency);
			merger.mergeRosetta(getAssignableLoan(), o.getAssignableLoan(), this::setAssignableLoan);
			merger.mergeRosetta(getConsentRequiredLoan(), o.getConsentRequiredLoan(), this::setConsentRequiredLoan);
			merger.mergeRosetta(getDirectLoanParticipation(), o.getDirectLoanParticipation(), this::setDirectLoanParticipation);
			merger.mergeRosetta(getMaximumMaturity(), o.getMaximumMaturity(), this::setMaximumMaturity);
			merger.mergeRosetta(getIndirectLoanParticipation(), o.getIndirectLoanParticipation(), this::setIndirectLoanParticipation);
			
			merger.mergeBasic(getAccruedInterest(), o.getAccruedInterest(), this::setAccruedInterest);
			merger.mergeBasic(getCategory(), o.getCategory(), this::setCategory);
			merger.mergeBasic(getNotSubordinated(), o.getNotSubordinated(), this::setNotSubordinated);
			merger.mergeBasic(getNotSovereignLender(), o.getNotSovereignLender(), this::setNotSovereignLender);
			merger.mergeBasic(getNotDomesticLaw(), o.getNotDomesticLaw(), this::setNotDomesticLaw);
			merger.mergeBasic(getListed(), o.getListed(), this::setListed);
			merger.mergeBasic(getNotContingent(), o.getNotContingent(), this::setNotContingent);
			merger.mergeBasic(getNotDomesticIssuance(), o.getNotDomesticIssuance(), this::setNotDomesticIssuance);
			merger.mergeBasic(getTransferable(), o.getTransferable(), this::setTransferable);
			merger.mergeBasic(getAcceleratedOrMatured(), o.getAcceleratedOrMatured(), this::setAcceleratedOrMatured);
			merger.mergeBasic(getNotBearer(), o.getNotBearer(), this::setNotBearer);
			merger.mergeBasic(getFullFaithAndCreditObLiability(), o.getFullFaithAndCreditObLiability(), this::setFullFaithAndCreditObLiability);
			merger.mergeBasic(getGeneralFundObligationLiability(), o.getGeneralFundObligationLiability(), this::setGeneralFundObligationLiability);
			merger.mergeBasic(getRevenueObligationLiability(), o.getRevenueObligationLiability(), this::setRevenueObligationLiability);
			merger.mergeBasic(getExcluded(), o.getExcluded(), this::setExcluded);
			merger.mergeBasic(getOthReferenceEntityObligations(), o.getOthReferenceEntityObligations(), this::setOthReferenceEntityObligations);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliverableObligations _that = getType().cast(o);
		
			if (!Objects.equals(accruedInterest, _that.getAccruedInterest())) return false;
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(notSubordinated, _that.getNotSubordinated())) return false;
			if (!Objects.equals(specifiedCurrency, _that.getSpecifiedCurrency())) return false;
			if (!Objects.equals(notSovereignLender, _that.getNotSovereignLender())) return false;
			if (!Objects.equals(notDomesticCurrency, _that.getNotDomesticCurrency())) return false;
			if (!Objects.equals(notDomesticLaw, _that.getNotDomesticLaw())) return false;
			if (!Objects.equals(listed, _that.getListed())) return false;
			if (!Objects.equals(notContingent, _that.getNotContingent())) return false;
			if (!Objects.equals(notDomesticIssuance, _that.getNotDomesticIssuance())) return false;
			if (!Objects.equals(assignableLoan, _that.getAssignableLoan())) return false;
			if (!Objects.equals(consentRequiredLoan, _that.getConsentRequiredLoan())) return false;
			if (!Objects.equals(directLoanParticipation, _that.getDirectLoanParticipation())) return false;
			if (!Objects.equals(transferable, _that.getTransferable())) return false;
			if (!Objects.equals(maximumMaturity, _that.getMaximumMaturity())) return false;
			if (!Objects.equals(acceleratedOrMatured, _that.getAcceleratedOrMatured())) return false;
			if (!Objects.equals(notBearer, _that.getNotBearer())) return false;
			if (!Objects.equals(fullFaithAndCreditObLiability, _that.getFullFaithAndCreditObLiability())) return false;
			if (!Objects.equals(generalFundObligationLiability, _that.getGeneralFundObligationLiability())) return false;
			if (!Objects.equals(revenueObligationLiability, _that.getRevenueObligationLiability())) return false;
			if (!Objects.equals(indirectLoanParticipation, _that.getIndirectLoanParticipation())) return false;
			if (!Objects.equals(excluded, _that.getExcluded())) return false;
			if (!Objects.equals(othReferenceEntityObligations, _that.getOthReferenceEntityObligations())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (accruedInterest != null ? accruedInterest.hashCode() : 0);
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notSubordinated != null ? notSubordinated.hashCode() : 0);
			_result = 31 * _result + (specifiedCurrency != null ? specifiedCurrency.hashCode() : 0);
			_result = 31 * _result + (notSovereignLender != null ? notSovereignLender.hashCode() : 0);
			_result = 31 * _result + (notDomesticCurrency != null ? notDomesticCurrency.hashCode() : 0);
			_result = 31 * _result + (notDomesticLaw != null ? notDomesticLaw.hashCode() : 0);
			_result = 31 * _result + (listed != null ? listed.hashCode() : 0);
			_result = 31 * _result + (notContingent != null ? notContingent.hashCode() : 0);
			_result = 31 * _result + (notDomesticIssuance != null ? notDomesticIssuance.hashCode() : 0);
			_result = 31 * _result + (assignableLoan != null ? assignableLoan.hashCode() : 0);
			_result = 31 * _result + (consentRequiredLoan != null ? consentRequiredLoan.hashCode() : 0);
			_result = 31 * _result + (directLoanParticipation != null ? directLoanParticipation.hashCode() : 0);
			_result = 31 * _result + (transferable != null ? transferable.hashCode() : 0);
			_result = 31 * _result + (maximumMaturity != null ? maximumMaturity.hashCode() : 0);
			_result = 31 * _result + (acceleratedOrMatured != null ? acceleratedOrMatured.hashCode() : 0);
			_result = 31 * _result + (notBearer != null ? notBearer.hashCode() : 0);
			_result = 31 * _result + (fullFaithAndCreditObLiability != null ? fullFaithAndCreditObLiability.hashCode() : 0);
			_result = 31 * _result + (generalFundObligationLiability != null ? generalFundObligationLiability.hashCode() : 0);
			_result = 31 * _result + (revenueObligationLiability != null ? revenueObligationLiability.hashCode() : 0);
			_result = 31 * _result + (indirectLoanParticipation != null ? indirectLoanParticipation.hashCode() : 0);
			_result = 31 * _result + (excluded != null ? excluded.hashCode() : 0);
			_result = 31 * _result + (othReferenceEntityObligations != null ? othReferenceEntityObligations.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliverableObligationsBuilder {" +
				"accruedInterest=" + this.accruedInterest + ", " +
				"category=" + this.category + ", " +
				"notSubordinated=" + this.notSubordinated + ", " +
				"specifiedCurrency=" + this.specifiedCurrency + ", " +
				"notSovereignLender=" + this.notSovereignLender + ", " +
				"notDomesticCurrency=" + this.notDomesticCurrency + ", " +
				"notDomesticLaw=" + this.notDomesticLaw + ", " +
				"listed=" + this.listed + ", " +
				"notContingent=" + this.notContingent + ", " +
				"notDomesticIssuance=" + this.notDomesticIssuance + ", " +
				"assignableLoan=" + this.assignableLoan + ", " +
				"consentRequiredLoan=" + this.consentRequiredLoan + ", " +
				"directLoanParticipation=" + this.directLoanParticipation + ", " +
				"transferable=" + this.transferable + ", " +
				"maximumMaturity=" + this.maximumMaturity + ", " +
				"acceleratedOrMatured=" + this.acceleratedOrMatured + ", " +
				"notBearer=" + this.notBearer + ", " +
				"fullFaithAndCreditObLiability=" + this.fullFaithAndCreditObLiability + ", " +
				"generalFundObligationLiability=" + this.generalFundObligationLiability + ", " +
				"revenueObligationLiability=" + this.revenueObligationLiability + ", " +
				"indirectLoanParticipation=" + this.indirectLoanParticipation + ", " +
				"excluded=" + this.excluded + ", " +
				"othReferenceEntityObligations=" + this.othReferenceEntityObligations +
			'}';
		}
	}
}
