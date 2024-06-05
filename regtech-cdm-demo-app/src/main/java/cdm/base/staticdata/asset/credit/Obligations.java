package cdm.base.staticdata.asset.credit;

import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.Obligations;
import cdm.base.staticdata.asset.credit.Obligations.ObligationsBuilder;
import cdm.base.staticdata.asset.credit.Obligations.ObligationsBuilderImpl;
import cdm.base.staticdata.asset.credit.Obligations.ObligationsImpl;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.base.staticdata.asset.credit.meta.ObligationsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the underlying obligations of the reference entity on which protection is purchased or sold through the Credit Default Swap.
 * @version ${project.version}
 */
@RosettaDataType(value="Obligations", builder=Obligations.ObligationsBuilderImpl.class, version="${project.version}")
public interface Obligations extends RosettaModelObject {

	ObligationsMeta metaData = new ObligationsMeta();

	/*********************** Getter Methods  ***********************/
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
	 * An obligation and deliverable obligation characteristic. Any obligation other than an obligation that was intended to be offered for sale primarily in the domestic market of the relevant Reference Entity. This specifies that the obligation must be an internationally recognised bond. ISDA 2003 Term: Not Domestic Issuance.
	 */
	Boolean getNotDomesticIssuance();
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
	 * OTE: Only allowed as an obligation characteristic under ISDA Credit 1999. In essence Not Contingent means the repayment of principal cannot be dependent on a formula/index, i.e. to prevent the risk of being delivered an instrument that may never pay any element of principal, and to ensure that the obligation is interest bearing (on a regular schedule). ISDA 2003 Term: Not Contingent.
	 */
	Boolean getNotContingent();
	/**
	 * A free format string to specify any excluded obligations or deliverable obligations, as the case may be, of the reference entity or excluded types of obligations or deliverable obligations. ISDA 2003 Term: Excluded Obligations/Excluded Deliverable Obligations.
	 */
	String getExcluded();
	/**
	 * This element is used to specify any other obligations of a reference entity in both obligations and deliverable obligations. The obligations can be specified free-form. ISDA 2003 Term: Other Obligations of a Reference Entity.
	 */
	String getOthReferenceEntityObligations();
	/**
	 * Applies to Loan CDS, to indicate what lien level is appropriate for a deliverable obligation. Applies to European Loan CDS, to indicate the Ranking of the obligation. Example: a 2nd lien Loan CDS would imply that the deliverable obligations are 1st or 2nd lien loans.
	 */
	FieldWithMetaString getDesignatedPriority();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published Standard Terms Supplement for use with CDS Transactions on Leveraged Loans. ISDA 2003 Term: Cash Settlement Only.
	 */
	Boolean getCashSettlementOnly();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published Standard Terms Supplement for use with CDS Transactions on Leveraged Loans. ISDA 2003 Term: Delivery of Commitments.
	 */
	Boolean getDeliveryOfCommitments();
	/**
	 * An obligation and deliverable obligation characteristic. Defined in the ISDA published Standard Terms Supplement for use with CDS Transactions on Leveraged Loans. ISDA 2003 Term: Continuity.
	 */
	Boolean getContinuity();

	/*********************** Build Methods  ***********************/
	Obligations build();
	
	Obligations.ObligationsBuilder toBuilder();
	
	static Obligations.ObligationsBuilder builder() {
		return new Obligations.ObligationsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Obligations> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Obligations> getType() {
		return Obligations.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("category"), ObligationCategoryEnum.class, getCategory(), this);
		processor.processBasic(path.newSubPath("notSubordinated"), Boolean.class, getNotSubordinated(), this);
		processRosetta(path.newSubPath("specifiedCurrency"), processor, SpecifiedCurrency.class, getSpecifiedCurrency());
		processor.processBasic(path.newSubPath("notSovereignLender"), Boolean.class, getNotSovereignLender(), this);
		processRosetta(path.newSubPath("notDomesticCurrency"), processor, NotDomesticCurrency.class, getNotDomesticCurrency());
		processor.processBasic(path.newSubPath("notDomesticLaw"), Boolean.class, getNotDomesticLaw(), this);
		processor.processBasic(path.newSubPath("listed"), Boolean.class, getListed(), this);
		processor.processBasic(path.newSubPath("notDomesticIssuance"), Boolean.class, getNotDomesticIssuance(), this);
		processor.processBasic(path.newSubPath("fullFaithAndCreditObLiability"), Boolean.class, getFullFaithAndCreditObLiability(), this);
		processor.processBasic(path.newSubPath("generalFundObligationLiability"), Boolean.class, getGeneralFundObligationLiability(), this);
		processor.processBasic(path.newSubPath("revenueObligationLiability"), Boolean.class, getRevenueObligationLiability(), this);
		processor.processBasic(path.newSubPath("notContingent"), Boolean.class, getNotContingent(), this);
		processor.processBasic(path.newSubPath("excluded"), String.class, getExcluded(), this);
		processor.processBasic(path.newSubPath("othReferenceEntityObligations"), String.class, getOthReferenceEntityObligations(), this);
		processRosetta(path.newSubPath("designatedPriority"), processor, FieldWithMetaString.class, getDesignatedPriority());
		processor.processBasic(path.newSubPath("cashSettlementOnly"), Boolean.class, getCashSettlementOnly(), this);
		processor.processBasic(path.newSubPath("deliveryOfCommitments"), Boolean.class, getDeliveryOfCommitments(), this);
		processor.processBasic(path.newSubPath("continuity"), Boolean.class, getContinuity(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObligationsBuilder extends Obligations, RosettaModelObjectBuilder {
		SpecifiedCurrency.SpecifiedCurrencyBuilder getOrCreateSpecifiedCurrency();
		SpecifiedCurrency.SpecifiedCurrencyBuilder getSpecifiedCurrency();
		NotDomesticCurrency.NotDomesticCurrencyBuilder getOrCreateNotDomesticCurrency();
		NotDomesticCurrency.NotDomesticCurrencyBuilder getNotDomesticCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDesignatedPriority();
		FieldWithMetaString.FieldWithMetaStringBuilder getDesignatedPriority();
		Obligations.ObligationsBuilder setCategory(ObligationCategoryEnum category);
		Obligations.ObligationsBuilder setNotSubordinated(Boolean notSubordinated);
		Obligations.ObligationsBuilder setSpecifiedCurrency(SpecifiedCurrency specifiedCurrency);
		Obligations.ObligationsBuilder setNotSovereignLender(Boolean notSovereignLender);
		Obligations.ObligationsBuilder setNotDomesticCurrency(NotDomesticCurrency notDomesticCurrency);
		Obligations.ObligationsBuilder setNotDomesticLaw(Boolean notDomesticLaw);
		Obligations.ObligationsBuilder setListed(Boolean listed);
		Obligations.ObligationsBuilder setNotDomesticIssuance(Boolean notDomesticIssuance);
		Obligations.ObligationsBuilder setFullFaithAndCreditObLiability(Boolean fullFaithAndCreditObLiability);
		Obligations.ObligationsBuilder setGeneralFundObligationLiability(Boolean generalFundObligationLiability);
		Obligations.ObligationsBuilder setRevenueObligationLiability(Boolean revenueObligationLiability);
		Obligations.ObligationsBuilder setNotContingent(Boolean notContingent);
		Obligations.ObligationsBuilder setExcluded(String excluded);
		Obligations.ObligationsBuilder setOthReferenceEntityObligations(String othReferenceEntityObligations);
		Obligations.ObligationsBuilder setDesignatedPriority(FieldWithMetaString designatedPriority0);
		Obligations.ObligationsBuilder setDesignatedPriorityValue(String designatedPriority1);
		Obligations.ObligationsBuilder setCashSettlementOnly(Boolean cashSettlementOnly);
		Obligations.ObligationsBuilder setDeliveryOfCommitments(Boolean deliveryOfCommitments);
		Obligations.ObligationsBuilder setContinuity(Boolean continuity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("category"), ObligationCategoryEnum.class, getCategory(), this);
			processor.processBasic(path.newSubPath("notSubordinated"), Boolean.class, getNotSubordinated(), this);
			processRosetta(path.newSubPath("specifiedCurrency"), processor, SpecifiedCurrency.SpecifiedCurrencyBuilder.class, getSpecifiedCurrency());
			processor.processBasic(path.newSubPath("notSovereignLender"), Boolean.class, getNotSovereignLender(), this);
			processRosetta(path.newSubPath("notDomesticCurrency"), processor, NotDomesticCurrency.NotDomesticCurrencyBuilder.class, getNotDomesticCurrency());
			processor.processBasic(path.newSubPath("notDomesticLaw"), Boolean.class, getNotDomesticLaw(), this);
			processor.processBasic(path.newSubPath("listed"), Boolean.class, getListed(), this);
			processor.processBasic(path.newSubPath("notDomesticIssuance"), Boolean.class, getNotDomesticIssuance(), this);
			processor.processBasic(path.newSubPath("fullFaithAndCreditObLiability"), Boolean.class, getFullFaithAndCreditObLiability(), this);
			processor.processBasic(path.newSubPath("generalFundObligationLiability"), Boolean.class, getGeneralFundObligationLiability(), this);
			processor.processBasic(path.newSubPath("revenueObligationLiability"), Boolean.class, getRevenueObligationLiability(), this);
			processor.processBasic(path.newSubPath("notContingent"), Boolean.class, getNotContingent(), this);
			processor.processBasic(path.newSubPath("excluded"), String.class, getExcluded(), this);
			processor.processBasic(path.newSubPath("othReferenceEntityObligations"), String.class, getOthReferenceEntityObligations(), this);
			processRosetta(path.newSubPath("designatedPriority"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getDesignatedPriority());
			processor.processBasic(path.newSubPath("cashSettlementOnly"), Boolean.class, getCashSettlementOnly(), this);
			processor.processBasic(path.newSubPath("deliveryOfCommitments"), Boolean.class, getDeliveryOfCommitments(), this);
			processor.processBasic(path.newSubPath("continuity"), Boolean.class, getContinuity(), this);
		}
		

		Obligations.ObligationsBuilder prune();
	}

	/*********************** Immutable Implementation of Obligations  ***********************/
	class ObligationsImpl implements Obligations {
		private final ObligationCategoryEnum category;
		private final Boolean notSubordinated;
		private final SpecifiedCurrency specifiedCurrency;
		private final Boolean notSovereignLender;
		private final NotDomesticCurrency notDomesticCurrency;
		private final Boolean notDomesticLaw;
		private final Boolean listed;
		private final Boolean notDomesticIssuance;
		private final Boolean fullFaithAndCreditObLiability;
		private final Boolean generalFundObligationLiability;
		private final Boolean revenueObligationLiability;
		private final Boolean notContingent;
		private final String excluded;
		private final String othReferenceEntityObligations;
		private final FieldWithMetaString designatedPriority;
		private final Boolean cashSettlementOnly;
		private final Boolean deliveryOfCommitments;
		private final Boolean continuity;
		
		protected ObligationsImpl(Obligations.ObligationsBuilder builder) {
			this.category = builder.getCategory();
			this.notSubordinated = builder.getNotSubordinated();
			this.specifiedCurrency = ofNullable(builder.getSpecifiedCurrency()).map(f->f.build()).orElse(null);
			this.notSovereignLender = builder.getNotSovereignLender();
			this.notDomesticCurrency = ofNullable(builder.getNotDomesticCurrency()).map(f->f.build()).orElse(null);
			this.notDomesticLaw = builder.getNotDomesticLaw();
			this.listed = builder.getListed();
			this.notDomesticIssuance = builder.getNotDomesticIssuance();
			this.fullFaithAndCreditObLiability = builder.getFullFaithAndCreditObLiability();
			this.generalFundObligationLiability = builder.getGeneralFundObligationLiability();
			this.revenueObligationLiability = builder.getRevenueObligationLiability();
			this.notContingent = builder.getNotContingent();
			this.excluded = builder.getExcluded();
			this.othReferenceEntityObligations = builder.getOthReferenceEntityObligations();
			this.designatedPriority = ofNullable(builder.getDesignatedPriority()).map(f->f.build()).orElse(null);
			this.cashSettlementOnly = builder.getCashSettlementOnly();
			this.deliveryOfCommitments = builder.getDeliveryOfCommitments();
			this.continuity = builder.getContinuity();
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
		@RosettaAttribute("notDomesticIssuance")
		public Boolean getNotDomesticIssuance() {
			return notDomesticIssuance;
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
		@RosettaAttribute("notContingent")
		public Boolean getNotContingent() {
			return notContingent;
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
		@RosettaAttribute("designatedPriority")
		public FieldWithMetaString getDesignatedPriority() {
			return designatedPriority;
		}
		
		@Override
		@RosettaAttribute("cashSettlementOnly")
		public Boolean getCashSettlementOnly() {
			return cashSettlementOnly;
		}
		
		@Override
		@RosettaAttribute("deliveryOfCommitments")
		public Boolean getDeliveryOfCommitments() {
			return deliveryOfCommitments;
		}
		
		@Override
		@RosettaAttribute("continuity")
		public Boolean getContinuity() {
			return continuity;
		}
		
		@Override
		public Obligations build() {
			return this;
		}
		
		@Override
		public Obligations.ObligationsBuilder toBuilder() {
			Obligations.ObligationsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Obligations.ObligationsBuilder builder) {
			ofNullable(getCategory()).ifPresent(builder::setCategory);
			ofNullable(getNotSubordinated()).ifPresent(builder::setNotSubordinated);
			ofNullable(getSpecifiedCurrency()).ifPresent(builder::setSpecifiedCurrency);
			ofNullable(getNotSovereignLender()).ifPresent(builder::setNotSovereignLender);
			ofNullable(getNotDomesticCurrency()).ifPresent(builder::setNotDomesticCurrency);
			ofNullable(getNotDomesticLaw()).ifPresent(builder::setNotDomesticLaw);
			ofNullable(getListed()).ifPresent(builder::setListed);
			ofNullable(getNotDomesticIssuance()).ifPresent(builder::setNotDomesticIssuance);
			ofNullable(getFullFaithAndCreditObLiability()).ifPresent(builder::setFullFaithAndCreditObLiability);
			ofNullable(getGeneralFundObligationLiability()).ifPresent(builder::setGeneralFundObligationLiability);
			ofNullable(getRevenueObligationLiability()).ifPresent(builder::setRevenueObligationLiability);
			ofNullable(getNotContingent()).ifPresent(builder::setNotContingent);
			ofNullable(getExcluded()).ifPresent(builder::setExcluded);
			ofNullable(getOthReferenceEntityObligations()).ifPresent(builder::setOthReferenceEntityObligations);
			ofNullable(getDesignatedPriority()).ifPresent(builder::setDesignatedPriority);
			ofNullable(getCashSettlementOnly()).ifPresent(builder::setCashSettlementOnly);
			ofNullable(getDeliveryOfCommitments()).ifPresent(builder::setDeliveryOfCommitments);
			ofNullable(getContinuity()).ifPresent(builder::setContinuity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Obligations _that = getType().cast(o);
		
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(notSubordinated, _that.getNotSubordinated())) return false;
			if (!Objects.equals(specifiedCurrency, _that.getSpecifiedCurrency())) return false;
			if (!Objects.equals(notSovereignLender, _that.getNotSovereignLender())) return false;
			if (!Objects.equals(notDomesticCurrency, _that.getNotDomesticCurrency())) return false;
			if (!Objects.equals(notDomesticLaw, _that.getNotDomesticLaw())) return false;
			if (!Objects.equals(listed, _that.getListed())) return false;
			if (!Objects.equals(notDomesticIssuance, _that.getNotDomesticIssuance())) return false;
			if (!Objects.equals(fullFaithAndCreditObLiability, _that.getFullFaithAndCreditObLiability())) return false;
			if (!Objects.equals(generalFundObligationLiability, _that.getGeneralFundObligationLiability())) return false;
			if (!Objects.equals(revenueObligationLiability, _that.getRevenueObligationLiability())) return false;
			if (!Objects.equals(notContingent, _that.getNotContingent())) return false;
			if (!Objects.equals(excluded, _that.getExcluded())) return false;
			if (!Objects.equals(othReferenceEntityObligations, _that.getOthReferenceEntityObligations())) return false;
			if (!Objects.equals(designatedPriority, _that.getDesignatedPriority())) return false;
			if (!Objects.equals(cashSettlementOnly, _that.getCashSettlementOnly())) return false;
			if (!Objects.equals(deliveryOfCommitments, _that.getDeliveryOfCommitments())) return false;
			if (!Objects.equals(continuity, _that.getContinuity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notSubordinated != null ? notSubordinated.hashCode() : 0);
			_result = 31 * _result + (specifiedCurrency != null ? specifiedCurrency.hashCode() : 0);
			_result = 31 * _result + (notSovereignLender != null ? notSovereignLender.hashCode() : 0);
			_result = 31 * _result + (notDomesticCurrency != null ? notDomesticCurrency.hashCode() : 0);
			_result = 31 * _result + (notDomesticLaw != null ? notDomesticLaw.hashCode() : 0);
			_result = 31 * _result + (listed != null ? listed.hashCode() : 0);
			_result = 31 * _result + (notDomesticIssuance != null ? notDomesticIssuance.hashCode() : 0);
			_result = 31 * _result + (fullFaithAndCreditObLiability != null ? fullFaithAndCreditObLiability.hashCode() : 0);
			_result = 31 * _result + (generalFundObligationLiability != null ? generalFundObligationLiability.hashCode() : 0);
			_result = 31 * _result + (revenueObligationLiability != null ? revenueObligationLiability.hashCode() : 0);
			_result = 31 * _result + (notContingent != null ? notContingent.hashCode() : 0);
			_result = 31 * _result + (excluded != null ? excluded.hashCode() : 0);
			_result = 31 * _result + (othReferenceEntityObligations != null ? othReferenceEntityObligations.hashCode() : 0);
			_result = 31 * _result + (designatedPriority != null ? designatedPriority.hashCode() : 0);
			_result = 31 * _result + (cashSettlementOnly != null ? cashSettlementOnly.hashCode() : 0);
			_result = 31 * _result + (deliveryOfCommitments != null ? deliveryOfCommitments.hashCode() : 0);
			_result = 31 * _result + (continuity != null ? continuity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Obligations {" +
				"category=" + this.category + ", " +
				"notSubordinated=" + this.notSubordinated + ", " +
				"specifiedCurrency=" + this.specifiedCurrency + ", " +
				"notSovereignLender=" + this.notSovereignLender + ", " +
				"notDomesticCurrency=" + this.notDomesticCurrency + ", " +
				"notDomesticLaw=" + this.notDomesticLaw + ", " +
				"listed=" + this.listed + ", " +
				"notDomesticIssuance=" + this.notDomesticIssuance + ", " +
				"fullFaithAndCreditObLiability=" + this.fullFaithAndCreditObLiability + ", " +
				"generalFundObligationLiability=" + this.generalFundObligationLiability + ", " +
				"revenueObligationLiability=" + this.revenueObligationLiability + ", " +
				"notContingent=" + this.notContingent + ", " +
				"excluded=" + this.excluded + ", " +
				"othReferenceEntityObligations=" + this.othReferenceEntityObligations + ", " +
				"designatedPriority=" + this.designatedPriority + ", " +
				"cashSettlementOnly=" + this.cashSettlementOnly + ", " +
				"deliveryOfCommitments=" + this.deliveryOfCommitments + ", " +
				"continuity=" + this.continuity +
			'}';
		}
	}

	/*********************** Builder Implementation of Obligations  ***********************/
	class ObligationsBuilderImpl implements Obligations.ObligationsBuilder {
	
		protected ObligationCategoryEnum category;
		protected Boolean notSubordinated;
		protected SpecifiedCurrency.SpecifiedCurrencyBuilder specifiedCurrency;
		protected Boolean notSovereignLender;
		protected NotDomesticCurrency.NotDomesticCurrencyBuilder notDomesticCurrency;
		protected Boolean notDomesticLaw;
		protected Boolean listed;
		protected Boolean notDomesticIssuance;
		protected Boolean fullFaithAndCreditObLiability;
		protected Boolean generalFundObligationLiability;
		protected Boolean revenueObligationLiability;
		protected Boolean notContingent;
		protected String excluded;
		protected String othReferenceEntityObligations;
		protected FieldWithMetaString.FieldWithMetaStringBuilder designatedPriority;
		protected Boolean cashSettlementOnly;
		protected Boolean deliveryOfCommitments;
		protected Boolean continuity;
	
		public ObligationsBuilderImpl() {
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
		@RosettaAttribute("notDomesticIssuance")
		public Boolean getNotDomesticIssuance() {
			return notDomesticIssuance;
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
		@RosettaAttribute("notContingent")
		public Boolean getNotContingent() {
			return notContingent;
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
		@RosettaAttribute("designatedPriority")
		public FieldWithMetaString.FieldWithMetaStringBuilder getDesignatedPriority() {
			return designatedPriority;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDesignatedPriority() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (designatedPriority!=null) {
				result = designatedPriority;
			}
			else {
				result = designatedPriority = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashSettlementOnly")
		public Boolean getCashSettlementOnly() {
			return cashSettlementOnly;
		}
		
		@Override
		@RosettaAttribute("deliveryOfCommitments")
		public Boolean getDeliveryOfCommitments() {
			return deliveryOfCommitments;
		}
		
		@Override
		@RosettaAttribute("continuity")
		public Boolean getContinuity() {
			return continuity;
		}
		
	
		@Override
		@RosettaAttribute("category")
		public Obligations.ObligationsBuilder setCategory(ObligationCategoryEnum category) {
			this.category = category==null?null:category;
			return this;
		}
		@Override
		@RosettaAttribute("notSubordinated")
		public Obligations.ObligationsBuilder setNotSubordinated(Boolean notSubordinated) {
			this.notSubordinated = notSubordinated==null?null:notSubordinated;
			return this;
		}
		@Override
		@RosettaAttribute("specifiedCurrency")
		public Obligations.ObligationsBuilder setSpecifiedCurrency(SpecifiedCurrency specifiedCurrency) {
			this.specifiedCurrency = specifiedCurrency==null?null:specifiedCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("notSovereignLender")
		public Obligations.ObligationsBuilder setNotSovereignLender(Boolean notSovereignLender) {
			this.notSovereignLender = notSovereignLender==null?null:notSovereignLender;
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticCurrency")
		public Obligations.ObligationsBuilder setNotDomesticCurrency(NotDomesticCurrency notDomesticCurrency) {
			this.notDomesticCurrency = notDomesticCurrency==null?null:notDomesticCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticLaw")
		public Obligations.ObligationsBuilder setNotDomesticLaw(Boolean notDomesticLaw) {
			this.notDomesticLaw = notDomesticLaw==null?null:notDomesticLaw;
			return this;
		}
		@Override
		@RosettaAttribute("listed")
		public Obligations.ObligationsBuilder setListed(Boolean listed) {
			this.listed = listed==null?null:listed;
			return this;
		}
		@Override
		@RosettaAttribute("notDomesticIssuance")
		public Obligations.ObligationsBuilder setNotDomesticIssuance(Boolean notDomesticIssuance) {
			this.notDomesticIssuance = notDomesticIssuance==null?null:notDomesticIssuance;
			return this;
		}
		@Override
		@RosettaAttribute("fullFaithAndCreditObLiability")
		public Obligations.ObligationsBuilder setFullFaithAndCreditObLiability(Boolean fullFaithAndCreditObLiability) {
			this.fullFaithAndCreditObLiability = fullFaithAndCreditObLiability==null?null:fullFaithAndCreditObLiability;
			return this;
		}
		@Override
		@RosettaAttribute("generalFundObligationLiability")
		public Obligations.ObligationsBuilder setGeneralFundObligationLiability(Boolean generalFundObligationLiability) {
			this.generalFundObligationLiability = generalFundObligationLiability==null?null:generalFundObligationLiability;
			return this;
		}
		@Override
		@RosettaAttribute("revenueObligationLiability")
		public Obligations.ObligationsBuilder setRevenueObligationLiability(Boolean revenueObligationLiability) {
			this.revenueObligationLiability = revenueObligationLiability==null?null:revenueObligationLiability;
			return this;
		}
		@Override
		@RosettaAttribute("notContingent")
		public Obligations.ObligationsBuilder setNotContingent(Boolean notContingent) {
			this.notContingent = notContingent==null?null:notContingent;
			return this;
		}
		@Override
		@RosettaAttribute("excluded")
		public Obligations.ObligationsBuilder setExcluded(String excluded) {
			this.excluded = excluded==null?null:excluded;
			return this;
		}
		@Override
		@RosettaAttribute("othReferenceEntityObligations")
		public Obligations.ObligationsBuilder setOthReferenceEntityObligations(String othReferenceEntityObligations) {
			this.othReferenceEntityObligations = othReferenceEntityObligations==null?null:othReferenceEntityObligations;
			return this;
		}
		@Override
		@RosettaAttribute("designatedPriority")
		public Obligations.ObligationsBuilder setDesignatedPriority(FieldWithMetaString designatedPriority) {
			this.designatedPriority = designatedPriority==null?null:designatedPriority.toBuilder();
			return this;
		}
		@Override
		public Obligations.ObligationsBuilder setDesignatedPriorityValue(String designatedPriority) {
			this.getOrCreateDesignatedPriority().setValue(designatedPriority);
			return this;
		}
		@Override
		@RosettaAttribute("cashSettlementOnly")
		public Obligations.ObligationsBuilder setCashSettlementOnly(Boolean cashSettlementOnly) {
			this.cashSettlementOnly = cashSettlementOnly==null?null:cashSettlementOnly;
			return this;
		}
		@Override
		@RosettaAttribute("deliveryOfCommitments")
		public Obligations.ObligationsBuilder setDeliveryOfCommitments(Boolean deliveryOfCommitments) {
			this.deliveryOfCommitments = deliveryOfCommitments==null?null:deliveryOfCommitments;
			return this;
		}
		@Override
		@RosettaAttribute("continuity")
		public Obligations.ObligationsBuilder setContinuity(Boolean continuity) {
			this.continuity = continuity==null?null:continuity;
			return this;
		}
		
		@Override
		public Obligations build() {
			return new Obligations.ObligationsImpl(this);
		}
		
		@Override
		public Obligations.ObligationsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Obligations.ObligationsBuilder prune() {
			if (specifiedCurrency!=null && !specifiedCurrency.prune().hasData()) specifiedCurrency = null;
			if (notDomesticCurrency!=null && !notDomesticCurrency.prune().hasData()) notDomesticCurrency = null;
			if (designatedPriority!=null && !designatedPriority.prune().hasData()) designatedPriority = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCategory()!=null) return true;
			if (getNotSubordinated()!=null) return true;
			if (getSpecifiedCurrency()!=null && getSpecifiedCurrency().hasData()) return true;
			if (getNotSovereignLender()!=null) return true;
			if (getNotDomesticCurrency()!=null && getNotDomesticCurrency().hasData()) return true;
			if (getNotDomesticLaw()!=null) return true;
			if (getListed()!=null) return true;
			if (getNotDomesticIssuance()!=null) return true;
			if (getFullFaithAndCreditObLiability()!=null) return true;
			if (getGeneralFundObligationLiability()!=null) return true;
			if (getRevenueObligationLiability()!=null) return true;
			if (getNotContingent()!=null) return true;
			if (getExcluded()!=null) return true;
			if (getOthReferenceEntityObligations()!=null) return true;
			if (getDesignatedPriority()!=null) return true;
			if (getCashSettlementOnly()!=null) return true;
			if (getDeliveryOfCommitments()!=null) return true;
			if (getContinuity()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Obligations.ObligationsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Obligations.ObligationsBuilder o = (Obligations.ObligationsBuilder) other;
			
			merger.mergeRosetta(getSpecifiedCurrency(), o.getSpecifiedCurrency(), this::setSpecifiedCurrency);
			merger.mergeRosetta(getNotDomesticCurrency(), o.getNotDomesticCurrency(), this::setNotDomesticCurrency);
			merger.mergeRosetta(getDesignatedPriority(), o.getDesignatedPriority(), this::setDesignatedPriority);
			
			merger.mergeBasic(getCategory(), o.getCategory(), this::setCategory);
			merger.mergeBasic(getNotSubordinated(), o.getNotSubordinated(), this::setNotSubordinated);
			merger.mergeBasic(getNotSovereignLender(), o.getNotSovereignLender(), this::setNotSovereignLender);
			merger.mergeBasic(getNotDomesticLaw(), o.getNotDomesticLaw(), this::setNotDomesticLaw);
			merger.mergeBasic(getListed(), o.getListed(), this::setListed);
			merger.mergeBasic(getNotDomesticIssuance(), o.getNotDomesticIssuance(), this::setNotDomesticIssuance);
			merger.mergeBasic(getFullFaithAndCreditObLiability(), o.getFullFaithAndCreditObLiability(), this::setFullFaithAndCreditObLiability);
			merger.mergeBasic(getGeneralFundObligationLiability(), o.getGeneralFundObligationLiability(), this::setGeneralFundObligationLiability);
			merger.mergeBasic(getRevenueObligationLiability(), o.getRevenueObligationLiability(), this::setRevenueObligationLiability);
			merger.mergeBasic(getNotContingent(), o.getNotContingent(), this::setNotContingent);
			merger.mergeBasic(getExcluded(), o.getExcluded(), this::setExcluded);
			merger.mergeBasic(getOthReferenceEntityObligations(), o.getOthReferenceEntityObligations(), this::setOthReferenceEntityObligations);
			merger.mergeBasic(getCashSettlementOnly(), o.getCashSettlementOnly(), this::setCashSettlementOnly);
			merger.mergeBasic(getDeliveryOfCommitments(), o.getDeliveryOfCommitments(), this::setDeliveryOfCommitments);
			merger.mergeBasic(getContinuity(), o.getContinuity(), this::setContinuity);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Obligations _that = getType().cast(o);
		
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(notSubordinated, _that.getNotSubordinated())) return false;
			if (!Objects.equals(specifiedCurrency, _that.getSpecifiedCurrency())) return false;
			if (!Objects.equals(notSovereignLender, _that.getNotSovereignLender())) return false;
			if (!Objects.equals(notDomesticCurrency, _that.getNotDomesticCurrency())) return false;
			if (!Objects.equals(notDomesticLaw, _that.getNotDomesticLaw())) return false;
			if (!Objects.equals(listed, _that.getListed())) return false;
			if (!Objects.equals(notDomesticIssuance, _that.getNotDomesticIssuance())) return false;
			if (!Objects.equals(fullFaithAndCreditObLiability, _that.getFullFaithAndCreditObLiability())) return false;
			if (!Objects.equals(generalFundObligationLiability, _that.getGeneralFundObligationLiability())) return false;
			if (!Objects.equals(revenueObligationLiability, _that.getRevenueObligationLiability())) return false;
			if (!Objects.equals(notContingent, _that.getNotContingent())) return false;
			if (!Objects.equals(excluded, _that.getExcluded())) return false;
			if (!Objects.equals(othReferenceEntityObligations, _that.getOthReferenceEntityObligations())) return false;
			if (!Objects.equals(designatedPriority, _that.getDesignatedPriority())) return false;
			if (!Objects.equals(cashSettlementOnly, _that.getCashSettlementOnly())) return false;
			if (!Objects.equals(deliveryOfCommitments, _that.getDeliveryOfCommitments())) return false;
			if (!Objects.equals(continuity, _that.getContinuity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notSubordinated != null ? notSubordinated.hashCode() : 0);
			_result = 31 * _result + (specifiedCurrency != null ? specifiedCurrency.hashCode() : 0);
			_result = 31 * _result + (notSovereignLender != null ? notSovereignLender.hashCode() : 0);
			_result = 31 * _result + (notDomesticCurrency != null ? notDomesticCurrency.hashCode() : 0);
			_result = 31 * _result + (notDomesticLaw != null ? notDomesticLaw.hashCode() : 0);
			_result = 31 * _result + (listed != null ? listed.hashCode() : 0);
			_result = 31 * _result + (notDomesticIssuance != null ? notDomesticIssuance.hashCode() : 0);
			_result = 31 * _result + (fullFaithAndCreditObLiability != null ? fullFaithAndCreditObLiability.hashCode() : 0);
			_result = 31 * _result + (generalFundObligationLiability != null ? generalFundObligationLiability.hashCode() : 0);
			_result = 31 * _result + (revenueObligationLiability != null ? revenueObligationLiability.hashCode() : 0);
			_result = 31 * _result + (notContingent != null ? notContingent.hashCode() : 0);
			_result = 31 * _result + (excluded != null ? excluded.hashCode() : 0);
			_result = 31 * _result + (othReferenceEntityObligations != null ? othReferenceEntityObligations.hashCode() : 0);
			_result = 31 * _result + (designatedPriority != null ? designatedPriority.hashCode() : 0);
			_result = 31 * _result + (cashSettlementOnly != null ? cashSettlementOnly.hashCode() : 0);
			_result = 31 * _result + (deliveryOfCommitments != null ? deliveryOfCommitments.hashCode() : 0);
			_result = 31 * _result + (continuity != null ? continuity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObligationsBuilder {" +
				"category=" + this.category + ", " +
				"notSubordinated=" + this.notSubordinated + ", " +
				"specifiedCurrency=" + this.specifiedCurrency + ", " +
				"notSovereignLender=" + this.notSovereignLender + ", " +
				"notDomesticCurrency=" + this.notDomesticCurrency + ", " +
				"notDomesticLaw=" + this.notDomesticLaw + ", " +
				"listed=" + this.listed + ", " +
				"notDomesticIssuance=" + this.notDomesticIssuance + ", " +
				"fullFaithAndCreditObLiability=" + this.fullFaithAndCreditObLiability + ", " +
				"generalFundObligationLiability=" + this.generalFundObligationLiability + ", " +
				"revenueObligationLiability=" + this.revenueObligationLiability + ", " +
				"notContingent=" + this.notContingent + ", " +
				"excluded=" + this.excluded + ", " +
				"othReferenceEntityObligations=" + this.othReferenceEntityObligations + ", " +
				"designatedPriority=" + this.designatedPriority + ", " +
				"cashSettlementOnly=" + this.cashSettlementOnly + ", " +
				"deliveryOfCommitments=" + this.deliveryOfCommitments + ", " +
				"continuity=" + this.continuity +
			'}';
		}
	}
}
