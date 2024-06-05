package cdm.legaldocumentation.master;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilderImpl;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents.AdditionalDisruptionEventsImpl;
import cdm.legaldocumentation.master.Clause;
import cdm.legaldocumentation.master.meta.AdditionalDisruptionEventsMeta;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A type for defining the Additional Disruption Events.
 * @version ${project.version}
 */
@RosettaDataType(value="AdditionalDisruptionEvents", builder=AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilderImpl.class, version="${project.version}")
public interface AdditionalDisruptionEvents extends RosettaModelObject {

	AdditionalDisruptionEventsMeta metaData = new AdditionalDisruptionEventsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions: 
	 */
	Boolean getChangeInLaw();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions
	 */
	Boolean getFailureToDeliver();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions
	 */
	Boolean getInsolvencyFiling();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions
	 */
	Boolean getHedgingDisruption();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions
	 */
	Boolean getIncreasedCostOfHedging();
	/**
	 * Per ISDA Def 
	 */
	Boolean getForeignOwnershipEvent();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions:
	 */
	Boolean getLossOfStockBorrow();
	/**
	 * Specifies the maximum stock loan rate for Loss of Stock Borrow. A percentage of 5% is represented as 0.05.
	 */
	BigDecimal getMaximumStockLoanRate();
	/**
	 * Per 2002 ISDA Equity Derivatives Definitions
	 */
	Boolean getIncreasedCostOfStockBorrow();
	/**
	 * Specifies the initial stock loan per ISDA Def. A percentage of 5% is represented as 0.05.
	 */
	BigDecimal getInitialStockLoanRate();
	/**
	 * Specifies the party which determines additional disruption events.
	 */
	AncillaryRoleEnum getDeterminingParty();
	/**
	 * Where parties may optionnaly describe any extra bespoke agreements, in regards of the standardized Extraordinary Events.
	 */
	List<? extends Clause> getAdditionalBespokeTerms();

	/*********************** Build Methods  ***********************/
	AdditionalDisruptionEvents build();
	
	AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder toBuilder();
	
	static AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder builder() {
		return new AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdditionalDisruptionEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdditionalDisruptionEvents> getType() {
		return AdditionalDisruptionEvents.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("changeInLaw"), Boolean.class, getChangeInLaw(), this);
		processor.processBasic(path.newSubPath("failureToDeliver"), Boolean.class, getFailureToDeliver(), this);
		processor.processBasic(path.newSubPath("insolvencyFiling"), Boolean.class, getInsolvencyFiling(), this);
		processor.processBasic(path.newSubPath("hedgingDisruption"), Boolean.class, getHedgingDisruption(), this);
		processor.processBasic(path.newSubPath("increasedCostOfHedging"), Boolean.class, getIncreasedCostOfHedging(), this);
		processor.processBasic(path.newSubPath("foreignOwnershipEvent"), Boolean.class, getForeignOwnershipEvent(), this);
		processor.processBasic(path.newSubPath("lossOfStockBorrow"), Boolean.class, getLossOfStockBorrow(), this);
		processor.processBasic(path.newSubPath("maximumStockLoanRate"), BigDecimal.class, getMaximumStockLoanRate(), this);
		processor.processBasic(path.newSubPath("increasedCostOfStockBorrow"), Boolean.class, getIncreasedCostOfStockBorrow(), this);
		processor.processBasic(path.newSubPath("initialStockLoanRate"), BigDecimal.class, getInitialStockLoanRate(), this);
		processor.processBasic(path.newSubPath("determiningParty"), AncillaryRoleEnum.class, getDeterminingParty(), this);
		processRosetta(path.newSubPath("additionalBespokeTerms"), processor, Clause.class, getAdditionalBespokeTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdditionalDisruptionEventsBuilder extends AdditionalDisruptionEvents, RosettaModelObjectBuilder {
		Clause.ClauseBuilder getOrCreateAdditionalBespokeTerms(int _index);
		List<? extends Clause.ClauseBuilder> getAdditionalBespokeTerms();
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setChangeInLaw(Boolean changeInLaw);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setFailureToDeliver(Boolean failureToDeliver);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setInsolvencyFiling(Boolean insolvencyFiling);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setHedgingDisruption(Boolean hedgingDisruption);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setIncreasedCostOfHedging(Boolean increasedCostOfHedging);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setForeignOwnershipEvent(Boolean foreignOwnershipEvent);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setLossOfStockBorrow(Boolean lossOfStockBorrow);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setMaximumStockLoanRate(BigDecimal maximumStockLoanRate);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setIncreasedCostOfStockBorrow(Boolean increasedCostOfStockBorrow);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setInitialStockLoanRate(BigDecimal initialStockLoanRate);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setDeterminingParty(AncillaryRoleEnum determiningParty);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms0);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms1, int _idx);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTerms2);
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTerms3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("changeInLaw"), Boolean.class, getChangeInLaw(), this);
			processor.processBasic(path.newSubPath("failureToDeliver"), Boolean.class, getFailureToDeliver(), this);
			processor.processBasic(path.newSubPath("insolvencyFiling"), Boolean.class, getInsolvencyFiling(), this);
			processor.processBasic(path.newSubPath("hedgingDisruption"), Boolean.class, getHedgingDisruption(), this);
			processor.processBasic(path.newSubPath("increasedCostOfHedging"), Boolean.class, getIncreasedCostOfHedging(), this);
			processor.processBasic(path.newSubPath("foreignOwnershipEvent"), Boolean.class, getForeignOwnershipEvent(), this);
			processor.processBasic(path.newSubPath("lossOfStockBorrow"), Boolean.class, getLossOfStockBorrow(), this);
			processor.processBasic(path.newSubPath("maximumStockLoanRate"), BigDecimal.class, getMaximumStockLoanRate(), this);
			processor.processBasic(path.newSubPath("increasedCostOfStockBorrow"), Boolean.class, getIncreasedCostOfStockBorrow(), this);
			processor.processBasic(path.newSubPath("initialStockLoanRate"), BigDecimal.class, getInitialStockLoanRate(), this);
			processor.processBasic(path.newSubPath("determiningParty"), AncillaryRoleEnum.class, getDeterminingParty(), this);
			processRosetta(path.newSubPath("additionalBespokeTerms"), processor, Clause.ClauseBuilder.class, getAdditionalBespokeTerms());
		}
		

		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder prune();
	}

	/*********************** Immutable Implementation of AdditionalDisruptionEvents  ***********************/
	class AdditionalDisruptionEventsImpl implements AdditionalDisruptionEvents {
		private final Boolean changeInLaw;
		private final Boolean failureToDeliver;
		private final Boolean insolvencyFiling;
		private final Boolean hedgingDisruption;
		private final Boolean increasedCostOfHedging;
		private final Boolean foreignOwnershipEvent;
		private final Boolean lossOfStockBorrow;
		private final BigDecimal maximumStockLoanRate;
		private final Boolean increasedCostOfStockBorrow;
		private final BigDecimal initialStockLoanRate;
		private final AncillaryRoleEnum determiningParty;
		private final List<? extends Clause> additionalBespokeTerms;
		
		protected AdditionalDisruptionEventsImpl(AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder builder) {
			this.changeInLaw = builder.getChangeInLaw();
			this.failureToDeliver = builder.getFailureToDeliver();
			this.insolvencyFiling = builder.getInsolvencyFiling();
			this.hedgingDisruption = builder.getHedgingDisruption();
			this.increasedCostOfHedging = builder.getIncreasedCostOfHedging();
			this.foreignOwnershipEvent = builder.getForeignOwnershipEvent();
			this.lossOfStockBorrow = builder.getLossOfStockBorrow();
			this.maximumStockLoanRate = builder.getMaximumStockLoanRate();
			this.increasedCostOfStockBorrow = builder.getIncreasedCostOfStockBorrow();
			this.initialStockLoanRate = builder.getInitialStockLoanRate();
			this.determiningParty = builder.getDeterminingParty();
			this.additionalBespokeTerms = ofNullable(builder.getAdditionalBespokeTerms()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("changeInLaw")
		public Boolean getChangeInLaw() {
			return changeInLaw;
		}
		
		@Override
		@RosettaAttribute("failureToDeliver")
		public Boolean getFailureToDeliver() {
			return failureToDeliver;
		}
		
		@Override
		@RosettaAttribute("insolvencyFiling")
		public Boolean getInsolvencyFiling() {
			return insolvencyFiling;
		}
		
		@Override
		@RosettaAttribute("hedgingDisruption")
		public Boolean getHedgingDisruption() {
			return hedgingDisruption;
		}
		
		@Override
		@RosettaAttribute("increasedCostOfHedging")
		public Boolean getIncreasedCostOfHedging() {
			return increasedCostOfHedging;
		}
		
		@Override
		@RosettaAttribute("foreignOwnershipEvent")
		public Boolean getForeignOwnershipEvent() {
			return foreignOwnershipEvent;
		}
		
		@Override
		@RosettaAttribute("lossOfStockBorrow")
		public Boolean getLossOfStockBorrow() {
			return lossOfStockBorrow;
		}
		
		@Override
		@RosettaAttribute("maximumStockLoanRate")
		public BigDecimal getMaximumStockLoanRate() {
			return maximumStockLoanRate;
		}
		
		@Override
		@RosettaAttribute("increasedCostOfStockBorrow")
		public Boolean getIncreasedCostOfStockBorrow() {
			return increasedCostOfStockBorrow;
		}
		
		@Override
		@RosettaAttribute("initialStockLoanRate")
		public BigDecimal getInitialStockLoanRate() {
			return initialStockLoanRate;
		}
		
		@Override
		@RosettaAttribute("determiningParty")
		public AncillaryRoleEnum getDeterminingParty() {
			return determiningParty;
		}
		
		@Override
		@RosettaAttribute("additionalBespokeTerms")
		public List<? extends Clause> getAdditionalBespokeTerms() {
			return additionalBespokeTerms;
		}
		
		@Override
		public AdditionalDisruptionEvents build() {
			return this;
		}
		
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder toBuilder() {
			AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder builder) {
			ofNullable(getChangeInLaw()).ifPresent(builder::setChangeInLaw);
			ofNullable(getFailureToDeliver()).ifPresent(builder::setFailureToDeliver);
			ofNullable(getInsolvencyFiling()).ifPresent(builder::setInsolvencyFiling);
			ofNullable(getHedgingDisruption()).ifPresent(builder::setHedgingDisruption);
			ofNullable(getIncreasedCostOfHedging()).ifPresent(builder::setIncreasedCostOfHedging);
			ofNullable(getForeignOwnershipEvent()).ifPresent(builder::setForeignOwnershipEvent);
			ofNullable(getLossOfStockBorrow()).ifPresent(builder::setLossOfStockBorrow);
			ofNullable(getMaximumStockLoanRate()).ifPresent(builder::setMaximumStockLoanRate);
			ofNullable(getIncreasedCostOfStockBorrow()).ifPresent(builder::setIncreasedCostOfStockBorrow);
			ofNullable(getInitialStockLoanRate()).ifPresent(builder::setInitialStockLoanRate);
			ofNullable(getDeterminingParty()).ifPresent(builder::setDeterminingParty);
			ofNullable(getAdditionalBespokeTerms()).ifPresent(builder::setAdditionalBespokeTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdditionalDisruptionEvents _that = getType().cast(o);
		
			if (!Objects.equals(changeInLaw, _that.getChangeInLaw())) return false;
			if (!Objects.equals(failureToDeliver, _that.getFailureToDeliver())) return false;
			if (!Objects.equals(insolvencyFiling, _that.getInsolvencyFiling())) return false;
			if (!Objects.equals(hedgingDisruption, _that.getHedgingDisruption())) return false;
			if (!Objects.equals(increasedCostOfHedging, _that.getIncreasedCostOfHedging())) return false;
			if (!Objects.equals(foreignOwnershipEvent, _that.getForeignOwnershipEvent())) return false;
			if (!Objects.equals(lossOfStockBorrow, _that.getLossOfStockBorrow())) return false;
			if (!Objects.equals(maximumStockLoanRate, _that.getMaximumStockLoanRate())) return false;
			if (!Objects.equals(increasedCostOfStockBorrow, _that.getIncreasedCostOfStockBorrow())) return false;
			if (!Objects.equals(initialStockLoanRate, _that.getInitialStockLoanRate())) return false;
			if (!Objects.equals(determiningParty, _that.getDeterminingParty())) return false;
			if (!ListEquals.listEquals(additionalBespokeTerms, _that.getAdditionalBespokeTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (changeInLaw != null ? changeInLaw.hashCode() : 0);
			_result = 31 * _result + (failureToDeliver != null ? failureToDeliver.hashCode() : 0);
			_result = 31 * _result + (insolvencyFiling != null ? insolvencyFiling.hashCode() : 0);
			_result = 31 * _result + (hedgingDisruption != null ? hedgingDisruption.hashCode() : 0);
			_result = 31 * _result + (increasedCostOfHedging != null ? increasedCostOfHedging.hashCode() : 0);
			_result = 31 * _result + (foreignOwnershipEvent != null ? foreignOwnershipEvent.hashCode() : 0);
			_result = 31 * _result + (lossOfStockBorrow != null ? lossOfStockBorrow.hashCode() : 0);
			_result = 31 * _result + (maximumStockLoanRate != null ? maximumStockLoanRate.hashCode() : 0);
			_result = 31 * _result + (increasedCostOfStockBorrow != null ? increasedCostOfStockBorrow.hashCode() : 0);
			_result = 31 * _result + (initialStockLoanRate != null ? initialStockLoanRate.hashCode() : 0);
			_result = 31 * _result + (determiningParty != null ? determiningParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (additionalBespokeTerms != null ? additionalBespokeTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdditionalDisruptionEvents {" +
				"changeInLaw=" + this.changeInLaw + ", " +
				"failureToDeliver=" + this.failureToDeliver + ", " +
				"insolvencyFiling=" + this.insolvencyFiling + ", " +
				"hedgingDisruption=" + this.hedgingDisruption + ", " +
				"increasedCostOfHedging=" + this.increasedCostOfHedging + ", " +
				"foreignOwnershipEvent=" + this.foreignOwnershipEvent + ", " +
				"lossOfStockBorrow=" + this.lossOfStockBorrow + ", " +
				"maximumStockLoanRate=" + this.maximumStockLoanRate + ", " +
				"increasedCostOfStockBorrow=" + this.increasedCostOfStockBorrow + ", " +
				"initialStockLoanRate=" + this.initialStockLoanRate + ", " +
				"determiningParty=" + this.determiningParty + ", " +
				"additionalBespokeTerms=" + this.additionalBespokeTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of AdditionalDisruptionEvents  ***********************/
	class AdditionalDisruptionEventsBuilderImpl implements AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder {
	
		protected Boolean changeInLaw;
		protected Boolean failureToDeliver;
		protected Boolean insolvencyFiling;
		protected Boolean hedgingDisruption;
		protected Boolean increasedCostOfHedging;
		protected Boolean foreignOwnershipEvent;
		protected Boolean lossOfStockBorrow;
		protected BigDecimal maximumStockLoanRate;
		protected Boolean increasedCostOfStockBorrow;
		protected BigDecimal initialStockLoanRate;
		protected AncillaryRoleEnum determiningParty;
		protected List<Clause.ClauseBuilder> additionalBespokeTerms = new ArrayList<>();
	
		public AdditionalDisruptionEventsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("changeInLaw")
		public Boolean getChangeInLaw() {
			return changeInLaw;
		}
		
		@Override
		@RosettaAttribute("failureToDeliver")
		public Boolean getFailureToDeliver() {
			return failureToDeliver;
		}
		
		@Override
		@RosettaAttribute("insolvencyFiling")
		public Boolean getInsolvencyFiling() {
			return insolvencyFiling;
		}
		
		@Override
		@RosettaAttribute("hedgingDisruption")
		public Boolean getHedgingDisruption() {
			return hedgingDisruption;
		}
		
		@Override
		@RosettaAttribute("increasedCostOfHedging")
		public Boolean getIncreasedCostOfHedging() {
			return increasedCostOfHedging;
		}
		
		@Override
		@RosettaAttribute("foreignOwnershipEvent")
		public Boolean getForeignOwnershipEvent() {
			return foreignOwnershipEvent;
		}
		
		@Override
		@RosettaAttribute("lossOfStockBorrow")
		public Boolean getLossOfStockBorrow() {
			return lossOfStockBorrow;
		}
		
		@Override
		@RosettaAttribute("maximumStockLoanRate")
		public BigDecimal getMaximumStockLoanRate() {
			return maximumStockLoanRate;
		}
		
		@Override
		@RosettaAttribute("increasedCostOfStockBorrow")
		public Boolean getIncreasedCostOfStockBorrow() {
			return increasedCostOfStockBorrow;
		}
		
		@Override
		@RosettaAttribute("initialStockLoanRate")
		public BigDecimal getInitialStockLoanRate() {
			return initialStockLoanRate;
		}
		
		@Override
		@RosettaAttribute("determiningParty")
		public AncillaryRoleEnum getDeterminingParty() {
			return determiningParty;
		}
		
		@Override
		@RosettaAttribute("additionalBespokeTerms")
		public List<? extends Clause.ClauseBuilder> getAdditionalBespokeTerms() {
			return additionalBespokeTerms;
		}
		
		public Clause.ClauseBuilder getOrCreateAdditionalBespokeTerms(int _index) {
		
			if (additionalBespokeTerms==null) {
				this.additionalBespokeTerms = new ArrayList<>();
			}
			Clause.ClauseBuilder result;
			return getIndex(additionalBespokeTerms, _index, () -> {
						Clause.ClauseBuilder newAdditionalBespokeTerms = Clause.builder();
						return newAdditionalBespokeTerms;
					});
		}
		
	
		@Override
		@RosettaAttribute("changeInLaw")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setChangeInLaw(Boolean changeInLaw) {
			this.changeInLaw = changeInLaw==null?null:changeInLaw;
			return this;
		}
		@Override
		@RosettaAttribute("failureToDeliver")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setFailureToDeliver(Boolean failureToDeliver) {
			this.failureToDeliver = failureToDeliver==null?null:failureToDeliver;
			return this;
		}
		@Override
		@RosettaAttribute("insolvencyFiling")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setInsolvencyFiling(Boolean insolvencyFiling) {
			this.insolvencyFiling = insolvencyFiling==null?null:insolvencyFiling;
			return this;
		}
		@Override
		@RosettaAttribute("hedgingDisruption")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setHedgingDisruption(Boolean hedgingDisruption) {
			this.hedgingDisruption = hedgingDisruption==null?null:hedgingDisruption;
			return this;
		}
		@Override
		@RosettaAttribute("increasedCostOfHedging")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setIncreasedCostOfHedging(Boolean increasedCostOfHedging) {
			this.increasedCostOfHedging = increasedCostOfHedging==null?null:increasedCostOfHedging;
			return this;
		}
		@Override
		@RosettaAttribute("foreignOwnershipEvent")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setForeignOwnershipEvent(Boolean foreignOwnershipEvent) {
			this.foreignOwnershipEvent = foreignOwnershipEvent==null?null:foreignOwnershipEvent;
			return this;
		}
		@Override
		@RosettaAttribute("lossOfStockBorrow")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setLossOfStockBorrow(Boolean lossOfStockBorrow) {
			this.lossOfStockBorrow = lossOfStockBorrow==null?null:lossOfStockBorrow;
			return this;
		}
		@Override
		@RosettaAttribute("maximumStockLoanRate")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setMaximumStockLoanRate(BigDecimal maximumStockLoanRate) {
			this.maximumStockLoanRate = maximumStockLoanRate==null?null:maximumStockLoanRate;
			return this;
		}
		@Override
		@RosettaAttribute("increasedCostOfStockBorrow")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setIncreasedCostOfStockBorrow(Boolean increasedCostOfStockBorrow) {
			this.increasedCostOfStockBorrow = increasedCostOfStockBorrow==null?null:increasedCostOfStockBorrow;
			return this;
		}
		@Override
		@RosettaAttribute("initialStockLoanRate")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setInitialStockLoanRate(BigDecimal initialStockLoanRate) {
			this.initialStockLoanRate = initialStockLoanRate==null?null:initialStockLoanRate;
			return this;
		}
		@Override
		@RosettaAttribute("determiningParty")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setDeterminingParty(AncillaryRoleEnum determiningParty) {
			this.determiningParty = determiningParty==null?null:determiningParty;
			return this;
		}
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms) {
			if (additionalBespokeTerms!=null) this.additionalBespokeTerms.add(additionalBespokeTerms.toBuilder());
			return this;
		}
		
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms, int _idx) {
			getIndex(this.additionalBespokeTerms, _idx, () -> additionalBespokeTerms.toBuilder());
			return this;
		}
		@Override 
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder addAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTermss) {
			if (additionalBespokeTermss != null) {
				for (Clause toAdd : additionalBespokeTermss) {
					this.additionalBespokeTerms.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("additionalBespokeTerms")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder setAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTermss) {
			if (additionalBespokeTermss == null)  {
				this.additionalBespokeTerms = new ArrayList<>();
			}
			else {
				this.additionalBespokeTerms = additionalBespokeTermss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public AdditionalDisruptionEvents build() {
			return new AdditionalDisruptionEvents.AdditionalDisruptionEventsImpl(this);
		}
		
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder prune() {
			additionalBespokeTerms = additionalBespokeTerms.stream().filter(b->b!=null).<Clause.ClauseBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getChangeInLaw()!=null) return true;
			if (getFailureToDeliver()!=null) return true;
			if (getInsolvencyFiling()!=null) return true;
			if (getHedgingDisruption()!=null) return true;
			if (getIncreasedCostOfHedging()!=null) return true;
			if (getForeignOwnershipEvent()!=null) return true;
			if (getLossOfStockBorrow()!=null) return true;
			if (getMaximumStockLoanRate()!=null) return true;
			if (getIncreasedCostOfStockBorrow()!=null) return true;
			if (getInitialStockLoanRate()!=null) return true;
			if (getDeterminingParty()!=null) return true;
			if (getAdditionalBespokeTerms()!=null && getAdditionalBespokeTerms().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder o = (AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder) other;
			
			merger.mergeRosetta(getAdditionalBespokeTerms(), o.getAdditionalBespokeTerms(), this::getOrCreateAdditionalBespokeTerms);
			
			merger.mergeBasic(getChangeInLaw(), o.getChangeInLaw(), this::setChangeInLaw);
			merger.mergeBasic(getFailureToDeliver(), o.getFailureToDeliver(), this::setFailureToDeliver);
			merger.mergeBasic(getInsolvencyFiling(), o.getInsolvencyFiling(), this::setInsolvencyFiling);
			merger.mergeBasic(getHedgingDisruption(), o.getHedgingDisruption(), this::setHedgingDisruption);
			merger.mergeBasic(getIncreasedCostOfHedging(), o.getIncreasedCostOfHedging(), this::setIncreasedCostOfHedging);
			merger.mergeBasic(getForeignOwnershipEvent(), o.getForeignOwnershipEvent(), this::setForeignOwnershipEvent);
			merger.mergeBasic(getLossOfStockBorrow(), o.getLossOfStockBorrow(), this::setLossOfStockBorrow);
			merger.mergeBasic(getMaximumStockLoanRate(), o.getMaximumStockLoanRate(), this::setMaximumStockLoanRate);
			merger.mergeBasic(getIncreasedCostOfStockBorrow(), o.getIncreasedCostOfStockBorrow(), this::setIncreasedCostOfStockBorrow);
			merger.mergeBasic(getInitialStockLoanRate(), o.getInitialStockLoanRate(), this::setInitialStockLoanRate);
			merger.mergeBasic(getDeterminingParty(), o.getDeterminingParty(), this::setDeterminingParty);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdditionalDisruptionEvents _that = getType().cast(o);
		
			if (!Objects.equals(changeInLaw, _that.getChangeInLaw())) return false;
			if (!Objects.equals(failureToDeliver, _that.getFailureToDeliver())) return false;
			if (!Objects.equals(insolvencyFiling, _that.getInsolvencyFiling())) return false;
			if (!Objects.equals(hedgingDisruption, _that.getHedgingDisruption())) return false;
			if (!Objects.equals(increasedCostOfHedging, _that.getIncreasedCostOfHedging())) return false;
			if (!Objects.equals(foreignOwnershipEvent, _that.getForeignOwnershipEvent())) return false;
			if (!Objects.equals(lossOfStockBorrow, _that.getLossOfStockBorrow())) return false;
			if (!Objects.equals(maximumStockLoanRate, _that.getMaximumStockLoanRate())) return false;
			if (!Objects.equals(increasedCostOfStockBorrow, _that.getIncreasedCostOfStockBorrow())) return false;
			if (!Objects.equals(initialStockLoanRate, _that.getInitialStockLoanRate())) return false;
			if (!Objects.equals(determiningParty, _that.getDeterminingParty())) return false;
			if (!ListEquals.listEquals(additionalBespokeTerms, _that.getAdditionalBespokeTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (changeInLaw != null ? changeInLaw.hashCode() : 0);
			_result = 31 * _result + (failureToDeliver != null ? failureToDeliver.hashCode() : 0);
			_result = 31 * _result + (insolvencyFiling != null ? insolvencyFiling.hashCode() : 0);
			_result = 31 * _result + (hedgingDisruption != null ? hedgingDisruption.hashCode() : 0);
			_result = 31 * _result + (increasedCostOfHedging != null ? increasedCostOfHedging.hashCode() : 0);
			_result = 31 * _result + (foreignOwnershipEvent != null ? foreignOwnershipEvent.hashCode() : 0);
			_result = 31 * _result + (lossOfStockBorrow != null ? lossOfStockBorrow.hashCode() : 0);
			_result = 31 * _result + (maximumStockLoanRate != null ? maximumStockLoanRate.hashCode() : 0);
			_result = 31 * _result + (increasedCostOfStockBorrow != null ? increasedCostOfStockBorrow.hashCode() : 0);
			_result = 31 * _result + (initialStockLoanRate != null ? initialStockLoanRate.hashCode() : 0);
			_result = 31 * _result + (determiningParty != null ? determiningParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (additionalBespokeTerms != null ? additionalBespokeTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdditionalDisruptionEventsBuilder {" +
				"changeInLaw=" + this.changeInLaw + ", " +
				"failureToDeliver=" + this.failureToDeliver + ", " +
				"insolvencyFiling=" + this.insolvencyFiling + ", " +
				"hedgingDisruption=" + this.hedgingDisruption + ", " +
				"increasedCostOfHedging=" + this.increasedCostOfHedging + ", " +
				"foreignOwnershipEvent=" + this.foreignOwnershipEvent + ", " +
				"lossOfStockBorrow=" + this.lossOfStockBorrow + ", " +
				"maximumStockLoanRate=" + this.maximumStockLoanRate + ", " +
				"increasedCostOfStockBorrow=" + this.increasedCostOfStockBorrow + ", " +
				"initialStockLoanRate=" + this.initialStockLoanRate + ", " +
				"determiningParty=" + this.determiningParty + ", " +
				"additionalBespokeTerms=" + this.additionalBespokeTerms +
			'}';
		}
	}
}
