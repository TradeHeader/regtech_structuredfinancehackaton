package cdm.product.common.settlement;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.common.settlement.DeliverableObligations;
import cdm.product.common.settlement.PhysicalSettlementPeriod;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.PhysicalSettlementTerms.PhysicalSettlementTermsBuilder;
import cdm.product.common.settlement.PhysicalSettlementTerms.PhysicalSettlementTermsBuilderImpl;
import cdm.product.common.settlement.PhysicalSettlementTerms.PhysicalSettlementTermsImpl;
import cdm.product.common.settlement.meta.PhysicalSettlementTermsMeta;
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
 * Specifies Physical Settlement Terms characteristics for the settlement of a Credit Default Swap or Option.
 * @version ${project.version}
 */
@RosettaDataType(value="PhysicalSettlementTerms", builder=PhysicalSettlementTerms.PhysicalSettlementTermsBuilderImpl.class, version="${project.version}")
public interface PhysicalSettlementTerms extends RosettaModelObject, GlobalKey {

	PhysicalSettlementTermsMeta metaData = new PhysicalSettlementTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies whether the swap resulting from physical settlement of the swaption transaction will clear through a clearing house. The meaning of Cleared Physical Settlement is defined in the 2006 ISDA Definitions, Section 15.2 (published in Supplement number 28).
	 */
	Boolean getClearedPhysicalSettlement();
	/**
	 * Specifies the clearing organization (CCP, DCO) to which the trade should be cleared.
	 */
	AncillaryRoleEnum getPredeterminedClearingOrganizationParty();
	/**
	 * The number of business days used in the determination of the physical settlement date. The physical settlement date is this number of business days after all applicable conditions to settlement are satisfied. If a number of business days is not specified fallback provisions apply for determining the number of business days. If Section 8.5/8.6 of the 1999/2003 ISDA Definitions are to apply the businessDaysNotSpecified element should be included. If a specified number of business days are to apply these should be specified in the businessDays element. If Section 8.5/8.6 of the 1999/2003 ISDA Definitions are to apply but capped at a maximum number of business days then the maximum number should be specified in the maximumBusinessDays element. ISDA 2003 Term: Physical Settlement Period.
	 */
	PhysicalSettlementPeriod getPhysicalSettlementPeriod();
	/**
	 * This element contains all the ISDA terms relevant to defining the deliverable obligations.
	 */
	DeliverableObligations getDeliverableObligations();
	/**
	 * If this element is specified and set to &#39;true&#39;, indicates that physical settlement must take place through the use of an escrow agent. (For Canadian counterparties this is always &#39;Not Applicable&#39;. ISDA 2003 Term: Escrow.
	 */
	Boolean getEscrow();
	/**
	 * If this element is specified and set to &#39;true&#39;, for a transaction documented under the 2003 ISDA Credit Derivatives Definitions, has the effect of incorporating the language set forth below into the confirmation. The section references are to the 2003 ISDA Credit Derivatives Definitions. Notwithstanding Section 1.7 or any provisions of Sections 9.9 or 9.10 to the contrary, but without prejudice to Section 9.3 and (where applicable) Sections 9.4, 9.5 and 9.6, if the Termination Date has not occurred on or prior to the date that is 60 Business Days following the Physical Settlement Date, such 60th Business Day shall be deemed to be the Termination Date with respect to this Transaction except in relation to any portion of the Transaction (an &#39;Affected Portion&#39;) in respect of which: (1) a valid notice of Buy-in Price has been delivered that is effective fewer than three Business Days prior to such 60th Business Day, in which case the Termination Date for that Affected Portion shall be the third Business Day following the date on which such notice is effective; or (2) Buyer has purchased but not Delivered Deliverable Obligations validly specified by Seller pursuant to Section 9.10(b), in which case the Termination Date for that Affected Portion shall be the tenth Business Day following the date on which Seller validly specified such Deliverable Obligations to Buyer.
	 */
	Boolean getSixtyBusinessDaySettlementCap();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PhysicalSettlementTerms build();
	
	PhysicalSettlementTerms.PhysicalSettlementTermsBuilder toBuilder();
	
	static PhysicalSettlementTerms.PhysicalSettlementTermsBuilder builder() {
		return new PhysicalSettlementTerms.PhysicalSettlementTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PhysicalSettlementTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PhysicalSettlementTerms> getType() {
		return PhysicalSettlementTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("clearedPhysicalSettlement"), Boolean.class, getClearedPhysicalSettlement(), this);
		processor.processBasic(path.newSubPath("predeterminedClearingOrganizationParty"), AncillaryRoleEnum.class, getPredeterminedClearingOrganizationParty(), this);
		processRosetta(path.newSubPath("physicalSettlementPeriod"), processor, PhysicalSettlementPeriod.class, getPhysicalSettlementPeriod());
		processRosetta(path.newSubPath("deliverableObligations"), processor, DeliverableObligations.class, getDeliverableObligations());
		processor.processBasic(path.newSubPath("escrow"), Boolean.class, getEscrow(), this);
		processor.processBasic(path.newSubPath("sixtyBusinessDaySettlementCap"), Boolean.class, getSixtyBusinessDaySettlementCap(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PhysicalSettlementTermsBuilder extends PhysicalSettlementTerms, RosettaModelObjectBuilder {
		PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder getOrCreatePhysicalSettlementPeriod();
		PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder getPhysicalSettlementPeriod();
		DeliverableObligations.DeliverableObligationsBuilder getOrCreateDeliverableObligations();
		DeliverableObligations.DeliverableObligationsBuilder getDeliverableObligations();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setClearedPhysicalSettlement(Boolean clearedPhysicalSettlement);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setPredeterminedClearingOrganizationParty(AncillaryRoleEnum predeterminedClearingOrganizationParty);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setPhysicalSettlementPeriod(PhysicalSettlementPeriod physicalSettlementPeriod);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setDeliverableObligations(DeliverableObligations deliverableObligations);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setEscrow(Boolean escrow);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setSixtyBusinessDaySettlementCap(Boolean sixtyBusinessDaySettlementCap);
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("clearedPhysicalSettlement"), Boolean.class, getClearedPhysicalSettlement(), this);
			processor.processBasic(path.newSubPath("predeterminedClearingOrganizationParty"), AncillaryRoleEnum.class, getPredeterminedClearingOrganizationParty(), this);
			processRosetta(path.newSubPath("physicalSettlementPeriod"), processor, PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder.class, getPhysicalSettlementPeriod());
			processRosetta(path.newSubPath("deliverableObligations"), processor, DeliverableObligations.DeliverableObligationsBuilder.class, getDeliverableObligations());
			processor.processBasic(path.newSubPath("escrow"), Boolean.class, getEscrow(), this);
			processor.processBasic(path.newSubPath("sixtyBusinessDaySettlementCap"), Boolean.class, getSixtyBusinessDaySettlementCap(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder prune();
	}

	/*********************** Immutable Implementation of PhysicalSettlementTerms  ***********************/
	class PhysicalSettlementTermsImpl implements PhysicalSettlementTerms {
		private final Boolean clearedPhysicalSettlement;
		private final AncillaryRoleEnum predeterminedClearingOrganizationParty;
		private final PhysicalSettlementPeriod physicalSettlementPeriod;
		private final DeliverableObligations deliverableObligations;
		private final Boolean escrow;
		private final Boolean sixtyBusinessDaySettlementCap;
		private final MetaFields meta;
		
		protected PhysicalSettlementTermsImpl(PhysicalSettlementTerms.PhysicalSettlementTermsBuilder builder) {
			this.clearedPhysicalSettlement = builder.getClearedPhysicalSettlement();
			this.predeterminedClearingOrganizationParty = builder.getPredeterminedClearingOrganizationParty();
			this.physicalSettlementPeriod = ofNullable(builder.getPhysicalSettlementPeriod()).map(f->f.build()).orElse(null);
			this.deliverableObligations = ofNullable(builder.getDeliverableObligations()).map(f->f.build()).orElse(null);
			this.escrow = builder.getEscrow();
			this.sixtyBusinessDaySettlementCap = builder.getSixtyBusinessDaySettlementCap();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("clearedPhysicalSettlement")
		public Boolean getClearedPhysicalSettlement() {
			return clearedPhysicalSettlement;
		}
		
		@Override
		@RosettaAttribute("predeterminedClearingOrganizationParty")
		public AncillaryRoleEnum getPredeterminedClearingOrganizationParty() {
			return predeterminedClearingOrganizationParty;
		}
		
		@Override
		@RosettaAttribute("physicalSettlementPeriod")
		public PhysicalSettlementPeriod getPhysicalSettlementPeriod() {
			return physicalSettlementPeriod;
		}
		
		@Override
		@RosettaAttribute("deliverableObligations")
		public DeliverableObligations getDeliverableObligations() {
			return deliverableObligations;
		}
		
		@Override
		@RosettaAttribute("escrow")
		public Boolean getEscrow() {
			return escrow;
		}
		
		@Override
		@RosettaAttribute("sixtyBusinessDaySettlementCap")
		public Boolean getSixtyBusinessDaySettlementCap() {
			return sixtyBusinessDaySettlementCap;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PhysicalSettlementTerms build() {
			return this;
		}
		
		@Override
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder toBuilder() {
			PhysicalSettlementTerms.PhysicalSettlementTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PhysicalSettlementTerms.PhysicalSettlementTermsBuilder builder) {
			ofNullable(getClearedPhysicalSettlement()).ifPresent(builder::setClearedPhysicalSettlement);
			ofNullable(getPredeterminedClearingOrganizationParty()).ifPresent(builder::setPredeterminedClearingOrganizationParty);
			ofNullable(getPhysicalSettlementPeriod()).ifPresent(builder::setPhysicalSettlementPeriod);
			ofNullable(getDeliverableObligations()).ifPresent(builder::setDeliverableObligations);
			ofNullable(getEscrow()).ifPresent(builder::setEscrow);
			ofNullable(getSixtyBusinessDaySettlementCap()).ifPresent(builder::setSixtyBusinessDaySettlementCap);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PhysicalSettlementTerms _that = getType().cast(o);
		
			if (!Objects.equals(clearedPhysicalSettlement, _that.getClearedPhysicalSettlement())) return false;
			if (!Objects.equals(predeterminedClearingOrganizationParty, _that.getPredeterminedClearingOrganizationParty())) return false;
			if (!Objects.equals(physicalSettlementPeriod, _that.getPhysicalSettlementPeriod())) return false;
			if (!Objects.equals(deliverableObligations, _that.getDeliverableObligations())) return false;
			if (!Objects.equals(escrow, _that.getEscrow())) return false;
			if (!Objects.equals(sixtyBusinessDaySettlementCap, _that.getSixtyBusinessDaySettlementCap())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (clearedPhysicalSettlement != null ? clearedPhysicalSettlement.hashCode() : 0);
			_result = 31 * _result + (predeterminedClearingOrganizationParty != null ? predeterminedClearingOrganizationParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (physicalSettlementPeriod != null ? physicalSettlementPeriod.hashCode() : 0);
			_result = 31 * _result + (deliverableObligations != null ? deliverableObligations.hashCode() : 0);
			_result = 31 * _result + (escrow != null ? escrow.hashCode() : 0);
			_result = 31 * _result + (sixtyBusinessDaySettlementCap != null ? sixtyBusinessDaySettlementCap.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PhysicalSettlementTerms {" +
				"clearedPhysicalSettlement=" + this.clearedPhysicalSettlement + ", " +
				"predeterminedClearingOrganizationParty=" + this.predeterminedClearingOrganizationParty + ", " +
				"physicalSettlementPeriod=" + this.physicalSettlementPeriod + ", " +
				"deliverableObligations=" + this.deliverableObligations + ", " +
				"escrow=" + this.escrow + ", " +
				"sixtyBusinessDaySettlementCap=" + this.sixtyBusinessDaySettlementCap + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PhysicalSettlementTerms  ***********************/
	class PhysicalSettlementTermsBuilderImpl implements PhysicalSettlementTerms.PhysicalSettlementTermsBuilder, GlobalKeyBuilder {
	
		protected Boolean clearedPhysicalSettlement;
		protected AncillaryRoleEnum predeterminedClearingOrganizationParty;
		protected PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder physicalSettlementPeriod;
		protected DeliverableObligations.DeliverableObligationsBuilder deliverableObligations;
		protected Boolean escrow;
		protected Boolean sixtyBusinessDaySettlementCap;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PhysicalSettlementTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("clearedPhysicalSettlement")
		public Boolean getClearedPhysicalSettlement() {
			return clearedPhysicalSettlement;
		}
		
		@Override
		@RosettaAttribute("predeterminedClearingOrganizationParty")
		public AncillaryRoleEnum getPredeterminedClearingOrganizationParty() {
			return predeterminedClearingOrganizationParty;
		}
		
		@Override
		@RosettaAttribute("physicalSettlementPeriod")
		public PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder getPhysicalSettlementPeriod() {
			return physicalSettlementPeriod;
		}
		
		@Override
		public PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder getOrCreatePhysicalSettlementPeriod() {
			PhysicalSettlementPeriod.PhysicalSettlementPeriodBuilder result;
			if (physicalSettlementPeriod!=null) {
				result = physicalSettlementPeriod;
			}
			else {
				result = physicalSettlementPeriod = PhysicalSettlementPeriod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliverableObligations")
		public DeliverableObligations.DeliverableObligationsBuilder getDeliverableObligations() {
			return deliverableObligations;
		}
		
		@Override
		public DeliverableObligations.DeliverableObligationsBuilder getOrCreateDeliverableObligations() {
			DeliverableObligations.DeliverableObligationsBuilder result;
			if (deliverableObligations!=null) {
				result = deliverableObligations;
			}
			else {
				result = deliverableObligations = DeliverableObligations.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("escrow")
		public Boolean getEscrow() {
			return escrow;
		}
		
		@Override
		@RosettaAttribute("sixtyBusinessDaySettlementCap")
		public Boolean getSixtyBusinessDaySettlementCap() {
			return sixtyBusinessDaySettlementCap;
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
		@RosettaAttribute("clearedPhysicalSettlement")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setClearedPhysicalSettlement(Boolean clearedPhysicalSettlement) {
			this.clearedPhysicalSettlement = clearedPhysicalSettlement==null?null:clearedPhysicalSettlement;
			return this;
		}
		@Override
		@RosettaAttribute("predeterminedClearingOrganizationParty")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setPredeterminedClearingOrganizationParty(AncillaryRoleEnum predeterminedClearingOrganizationParty) {
			this.predeterminedClearingOrganizationParty = predeterminedClearingOrganizationParty==null?null:predeterminedClearingOrganizationParty;
			return this;
		}
		@Override
		@RosettaAttribute("physicalSettlementPeriod")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setPhysicalSettlementPeriod(PhysicalSettlementPeriod physicalSettlementPeriod) {
			this.physicalSettlementPeriod = physicalSettlementPeriod==null?null:physicalSettlementPeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliverableObligations")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setDeliverableObligations(DeliverableObligations deliverableObligations) {
			this.deliverableObligations = deliverableObligations==null?null:deliverableObligations.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("escrow")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setEscrow(Boolean escrow) {
			this.escrow = escrow==null?null:escrow;
			return this;
		}
		@Override
		@RosettaAttribute("sixtyBusinessDaySettlementCap")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setSixtyBusinessDaySettlementCap(Boolean sixtyBusinessDaySettlementCap) {
			this.sixtyBusinessDaySettlementCap = sixtyBusinessDaySettlementCap==null?null:sixtyBusinessDaySettlementCap;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PhysicalSettlementTerms build() {
			return new PhysicalSettlementTerms.PhysicalSettlementTermsImpl(this);
		}
		
		@Override
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder prune() {
			if (physicalSettlementPeriod!=null && !physicalSettlementPeriod.prune().hasData()) physicalSettlementPeriod = null;
			if (deliverableObligations!=null && !deliverableObligations.prune().hasData()) deliverableObligations = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getClearedPhysicalSettlement()!=null) return true;
			if (getPredeterminedClearingOrganizationParty()!=null) return true;
			if (getPhysicalSettlementPeriod()!=null && getPhysicalSettlementPeriod().hasData()) return true;
			if (getDeliverableObligations()!=null && getDeliverableObligations().hasData()) return true;
			if (getEscrow()!=null) return true;
			if (getSixtyBusinessDaySettlementCap()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PhysicalSettlementTerms.PhysicalSettlementTermsBuilder o = (PhysicalSettlementTerms.PhysicalSettlementTermsBuilder) other;
			
			merger.mergeRosetta(getPhysicalSettlementPeriod(), o.getPhysicalSettlementPeriod(), this::setPhysicalSettlementPeriod);
			merger.mergeRosetta(getDeliverableObligations(), o.getDeliverableObligations(), this::setDeliverableObligations);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getClearedPhysicalSettlement(), o.getClearedPhysicalSettlement(), this::setClearedPhysicalSettlement);
			merger.mergeBasic(getPredeterminedClearingOrganizationParty(), o.getPredeterminedClearingOrganizationParty(), this::setPredeterminedClearingOrganizationParty);
			merger.mergeBasic(getEscrow(), o.getEscrow(), this::setEscrow);
			merger.mergeBasic(getSixtyBusinessDaySettlementCap(), o.getSixtyBusinessDaySettlementCap(), this::setSixtyBusinessDaySettlementCap);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PhysicalSettlementTerms _that = getType().cast(o);
		
			if (!Objects.equals(clearedPhysicalSettlement, _that.getClearedPhysicalSettlement())) return false;
			if (!Objects.equals(predeterminedClearingOrganizationParty, _that.getPredeterminedClearingOrganizationParty())) return false;
			if (!Objects.equals(physicalSettlementPeriod, _that.getPhysicalSettlementPeriod())) return false;
			if (!Objects.equals(deliverableObligations, _that.getDeliverableObligations())) return false;
			if (!Objects.equals(escrow, _that.getEscrow())) return false;
			if (!Objects.equals(sixtyBusinessDaySettlementCap, _that.getSixtyBusinessDaySettlementCap())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (clearedPhysicalSettlement != null ? clearedPhysicalSettlement.hashCode() : 0);
			_result = 31 * _result + (predeterminedClearingOrganizationParty != null ? predeterminedClearingOrganizationParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (physicalSettlementPeriod != null ? physicalSettlementPeriod.hashCode() : 0);
			_result = 31 * _result + (deliverableObligations != null ? deliverableObligations.hashCode() : 0);
			_result = 31 * _result + (escrow != null ? escrow.hashCode() : 0);
			_result = 31 * _result + (sixtyBusinessDaySettlementCap != null ? sixtyBusinessDaySettlementCap.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PhysicalSettlementTermsBuilder {" +
				"clearedPhysicalSettlement=" + this.clearedPhysicalSettlement + ", " +
				"predeterminedClearingOrganizationParty=" + this.predeterminedClearingOrganizationParty + ", " +
				"physicalSettlementPeriod=" + this.physicalSettlementPeriod + ", " +
				"deliverableObligations=" + this.deliverableObligations + ", " +
				"escrow=" + this.escrow + ", " +
				"sixtyBusinessDaySettlementCap=" + this.sixtyBusinessDaySettlementCap + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
