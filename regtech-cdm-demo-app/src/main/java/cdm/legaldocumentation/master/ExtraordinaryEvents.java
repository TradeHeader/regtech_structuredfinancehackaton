package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.Clause;
import cdm.legaldocumentation.master.EquityCorporateEvents;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.ExtraordinaryEvents.ExtraordinaryEventsBuilder;
import cdm.legaldocumentation.master.ExtraordinaryEvents.ExtraordinaryEventsBuilderImpl;
import cdm.legaldocumentation.master.ExtraordinaryEvents.ExtraordinaryEventsImpl;
import cdm.legaldocumentation.master.IndexAdjustmentEvents;
import cdm.legaldocumentation.master.NationalizationOrInsolvencyOrDelistingEventEnum;
import cdm.legaldocumentation.master.Representations;
import cdm.legaldocumentation.master.meta.ExtraordinaryEventsMeta;
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
 * Where the underlying is shares, defines market events affecting the issuer of those shares that may require the terms of the transaction to be adjusted.
 * @version ${project.version}
 */
@RosettaDataType(value="ExtraordinaryEvents", builder=ExtraordinaryEvents.ExtraordinaryEventsBuilderImpl.class, version="${project.version}")
public interface ExtraordinaryEvents extends RosettaModelObject {

	ExtraordinaryEventsMeta metaData = new ExtraordinaryEventsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Where parties may optionnaly describe any extra bespoke agreements, in regards of the standardized Extraordinary Events.
	 */
	List<? extends Clause> getAdditionalBespokeTerms();
	/**
	 * Per the 2018 ISDA CDM Equity Confirmation for Security Equity Swap
	 */
	EquityCorporateEvents getMergerEvents();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions: 
	 */
	EquityCorporateEvents getTenderOfferEvents();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions: 
	 */
	Boolean getCompositionOfCombinedConsideration();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions: Adjustments to Indices 
	 */
	IndexAdjustmentEvents getIndexAdjustmentEvents();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions | 2018 ISDA CDM Equity Confirmation for Security Equity Swaps
	 */
	AdditionalDisruptionEvents getAdditionalDisruptionEvents();
	/**
	 * If true, failure to deliver is applicable.
	 */
	Boolean getFailureToDeliver();
	Representations getRepresentations();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions | 2018 ISDA CDM Equity Confirmation for Security Equity Swap
	 */
	NationalizationOrInsolvencyOrDelistingEventEnum getNationalizationOrInsolvency();
	/**
	 * Per the 2002 ISDA Equity Derivatives Definitions | 2018 ISDA CDM Equity Confirmation for Security Equity Swap:
	 */
	NationalizationOrInsolvencyOrDelistingEventEnum getDelisting();

	/*********************** Build Methods  ***********************/
	ExtraordinaryEvents build();
	
	ExtraordinaryEvents.ExtraordinaryEventsBuilder toBuilder();
	
	static ExtraordinaryEvents.ExtraordinaryEventsBuilder builder() {
		return new ExtraordinaryEvents.ExtraordinaryEventsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExtraordinaryEvents> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExtraordinaryEvents> getType() {
		return ExtraordinaryEvents.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("additionalBespokeTerms"), processor, Clause.class, getAdditionalBespokeTerms());
		processRosetta(path.newSubPath("mergerEvents"), processor, EquityCorporateEvents.class, getMergerEvents());
		processRosetta(path.newSubPath("tenderOfferEvents"), processor, EquityCorporateEvents.class, getTenderOfferEvents());
		processor.processBasic(path.newSubPath("compositionOfCombinedConsideration"), Boolean.class, getCompositionOfCombinedConsideration(), this);
		processRosetta(path.newSubPath("indexAdjustmentEvents"), processor, IndexAdjustmentEvents.class, getIndexAdjustmentEvents());
		processRosetta(path.newSubPath("additionalDisruptionEvents"), processor, AdditionalDisruptionEvents.class, getAdditionalDisruptionEvents());
		processor.processBasic(path.newSubPath("failureToDeliver"), Boolean.class, getFailureToDeliver(), this);
		processRosetta(path.newSubPath("representations"), processor, Representations.class, getRepresentations());
		processor.processBasic(path.newSubPath("nationalizationOrInsolvency"), NationalizationOrInsolvencyOrDelistingEventEnum.class, getNationalizationOrInsolvency(), this);
		processor.processBasic(path.newSubPath("delisting"), NationalizationOrInsolvencyOrDelistingEventEnum.class, getDelisting(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExtraordinaryEventsBuilder extends ExtraordinaryEvents, RosettaModelObjectBuilder {
		Clause.ClauseBuilder getOrCreateAdditionalBespokeTerms(int _index);
		List<? extends Clause.ClauseBuilder> getAdditionalBespokeTerms();
		EquityCorporateEvents.EquityCorporateEventsBuilder getOrCreateMergerEvents();
		EquityCorporateEvents.EquityCorporateEventsBuilder getMergerEvents();
		EquityCorporateEvents.EquityCorporateEventsBuilder getOrCreateTenderOfferEvents();
		EquityCorporateEvents.EquityCorporateEventsBuilder getTenderOfferEvents();
		IndexAdjustmentEvents.IndexAdjustmentEventsBuilder getOrCreateIndexAdjustmentEvents();
		IndexAdjustmentEvents.IndexAdjustmentEventsBuilder getIndexAdjustmentEvents();
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder getOrCreateAdditionalDisruptionEvents();
		AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder getAdditionalDisruptionEvents();
		Representations.RepresentationsBuilder getOrCreateRepresentations();
		Representations.RepresentationsBuilder getRepresentations();
		ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms0);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms1, int _idx);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTerms2);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTerms3);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setMergerEvents(EquityCorporateEvents mergerEvents);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setTenderOfferEvents(EquityCorporateEvents tenderOfferEvents);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setCompositionOfCombinedConsideration(Boolean compositionOfCombinedConsideration);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setIndexAdjustmentEvents(IndexAdjustmentEvents indexAdjustmentEvents);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setAdditionalDisruptionEvents(AdditionalDisruptionEvents additionalDisruptionEvents);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setFailureToDeliver(Boolean failureToDeliver);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setRepresentations(Representations representations);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setNationalizationOrInsolvency(NationalizationOrInsolvencyOrDelistingEventEnum nationalizationOrInsolvency);
		ExtraordinaryEvents.ExtraordinaryEventsBuilder setDelisting(NationalizationOrInsolvencyOrDelistingEventEnum delisting);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("additionalBespokeTerms"), processor, Clause.ClauseBuilder.class, getAdditionalBespokeTerms());
			processRosetta(path.newSubPath("mergerEvents"), processor, EquityCorporateEvents.EquityCorporateEventsBuilder.class, getMergerEvents());
			processRosetta(path.newSubPath("tenderOfferEvents"), processor, EquityCorporateEvents.EquityCorporateEventsBuilder.class, getTenderOfferEvents());
			processor.processBasic(path.newSubPath("compositionOfCombinedConsideration"), Boolean.class, getCompositionOfCombinedConsideration(), this);
			processRosetta(path.newSubPath("indexAdjustmentEvents"), processor, IndexAdjustmentEvents.IndexAdjustmentEventsBuilder.class, getIndexAdjustmentEvents());
			processRosetta(path.newSubPath("additionalDisruptionEvents"), processor, AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder.class, getAdditionalDisruptionEvents());
			processor.processBasic(path.newSubPath("failureToDeliver"), Boolean.class, getFailureToDeliver(), this);
			processRosetta(path.newSubPath("representations"), processor, Representations.RepresentationsBuilder.class, getRepresentations());
			processor.processBasic(path.newSubPath("nationalizationOrInsolvency"), NationalizationOrInsolvencyOrDelistingEventEnum.class, getNationalizationOrInsolvency(), this);
			processor.processBasic(path.newSubPath("delisting"), NationalizationOrInsolvencyOrDelistingEventEnum.class, getDelisting(), this);
		}
		

		ExtraordinaryEvents.ExtraordinaryEventsBuilder prune();
	}

	/*********************** Immutable Implementation of ExtraordinaryEvents  ***********************/
	class ExtraordinaryEventsImpl implements ExtraordinaryEvents {
		private final List<? extends Clause> additionalBespokeTerms;
		private final EquityCorporateEvents mergerEvents;
		private final EquityCorporateEvents tenderOfferEvents;
		private final Boolean compositionOfCombinedConsideration;
		private final IndexAdjustmentEvents indexAdjustmentEvents;
		private final AdditionalDisruptionEvents additionalDisruptionEvents;
		private final Boolean failureToDeliver;
		private final Representations representations;
		private final NationalizationOrInsolvencyOrDelistingEventEnum nationalizationOrInsolvency;
		private final NationalizationOrInsolvencyOrDelistingEventEnum delisting;
		
		protected ExtraordinaryEventsImpl(ExtraordinaryEvents.ExtraordinaryEventsBuilder builder) {
			this.additionalBespokeTerms = ofNullable(builder.getAdditionalBespokeTerms()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.mergerEvents = ofNullable(builder.getMergerEvents()).map(f->f.build()).orElse(null);
			this.tenderOfferEvents = ofNullable(builder.getTenderOfferEvents()).map(f->f.build()).orElse(null);
			this.compositionOfCombinedConsideration = builder.getCompositionOfCombinedConsideration();
			this.indexAdjustmentEvents = ofNullable(builder.getIndexAdjustmentEvents()).map(f->f.build()).orElse(null);
			this.additionalDisruptionEvents = ofNullable(builder.getAdditionalDisruptionEvents()).map(f->f.build()).orElse(null);
			this.failureToDeliver = builder.getFailureToDeliver();
			this.representations = ofNullable(builder.getRepresentations()).map(f->f.build()).orElse(null);
			this.nationalizationOrInsolvency = builder.getNationalizationOrInsolvency();
			this.delisting = builder.getDelisting();
		}
		
		@Override
		@RosettaAttribute("additionalBespokeTerms")
		public List<? extends Clause> getAdditionalBespokeTerms() {
			return additionalBespokeTerms;
		}
		
		@Override
		@RosettaAttribute("mergerEvents")
		public EquityCorporateEvents getMergerEvents() {
			return mergerEvents;
		}
		
		@Override
		@RosettaAttribute("tenderOfferEvents")
		public EquityCorporateEvents getTenderOfferEvents() {
			return tenderOfferEvents;
		}
		
		@Override
		@RosettaAttribute("compositionOfCombinedConsideration")
		public Boolean getCompositionOfCombinedConsideration() {
			return compositionOfCombinedConsideration;
		}
		
		@Override
		@RosettaAttribute("indexAdjustmentEvents")
		public IndexAdjustmentEvents getIndexAdjustmentEvents() {
			return indexAdjustmentEvents;
		}
		
		@Override
		@RosettaAttribute("additionalDisruptionEvents")
		public AdditionalDisruptionEvents getAdditionalDisruptionEvents() {
			return additionalDisruptionEvents;
		}
		
		@Override
		@RosettaAttribute("failureToDeliver")
		public Boolean getFailureToDeliver() {
			return failureToDeliver;
		}
		
		@Override
		@RosettaAttribute("representations")
		public Representations getRepresentations() {
			return representations;
		}
		
		@Override
		@RosettaAttribute("nationalizationOrInsolvency")
		public NationalizationOrInsolvencyOrDelistingEventEnum getNationalizationOrInsolvency() {
			return nationalizationOrInsolvency;
		}
		
		@Override
		@RosettaAttribute("delisting")
		public NationalizationOrInsolvencyOrDelistingEventEnum getDelisting() {
			return delisting;
		}
		
		@Override
		public ExtraordinaryEvents build() {
			return this;
		}
		
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder toBuilder() {
			ExtraordinaryEvents.ExtraordinaryEventsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExtraordinaryEvents.ExtraordinaryEventsBuilder builder) {
			ofNullable(getAdditionalBespokeTerms()).ifPresent(builder::setAdditionalBespokeTerms);
			ofNullable(getMergerEvents()).ifPresent(builder::setMergerEvents);
			ofNullable(getTenderOfferEvents()).ifPresent(builder::setTenderOfferEvents);
			ofNullable(getCompositionOfCombinedConsideration()).ifPresent(builder::setCompositionOfCombinedConsideration);
			ofNullable(getIndexAdjustmentEvents()).ifPresent(builder::setIndexAdjustmentEvents);
			ofNullable(getAdditionalDisruptionEvents()).ifPresent(builder::setAdditionalDisruptionEvents);
			ofNullable(getFailureToDeliver()).ifPresent(builder::setFailureToDeliver);
			ofNullable(getRepresentations()).ifPresent(builder::setRepresentations);
			ofNullable(getNationalizationOrInsolvency()).ifPresent(builder::setNationalizationOrInsolvency);
			ofNullable(getDelisting()).ifPresent(builder::setDelisting);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExtraordinaryEvents _that = getType().cast(o);
		
			if (!ListEquals.listEquals(additionalBespokeTerms, _that.getAdditionalBespokeTerms())) return false;
			if (!Objects.equals(mergerEvents, _that.getMergerEvents())) return false;
			if (!Objects.equals(tenderOfferEvents, _that.getTenderOfferEvents())) return false;
			if (!Objects.equals(compositionOfCombinedConsideration, _that.getCompositionOfCombinedConsideration())) return false;
			if (!Objects.equals(indexAdjustmentEvents, _that.getIndexAdjustmentEvents())) return false;
			if (!Objects.equals(additionalDisruptionEvents, _that.getAdditionalDisruptionEvents())) return false;
			if (!Objects.equals(failureToDeliver, _that.getFailureToDeliver())) return false;
			if (!Objects.equals(representations, _that.getRepresentations())) return false;
			if (!Objects.equals(nationalizationOrInsolvency, _that.getNationalizationOrInsolvency())) return false;
			if (!Objects.equals(delisting, _that.getDelisting())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (additionalBespokeTerms != null ? additionalBespokeTerms.hashCode() : 0);
			_result = 31 * _result + (mergerEvents != null ? mergerEvents.hashCode() : 0);
			_result = 31 * _result + (tenderOfferEvents != null ? tenderOfferEvents.hashCode() : 0);
			_result = 31 * _result + (compositionOfCombinedConsideration != null ? compositionOfCombinedConsideration.hashCode() : 0);
			_result = 31 * _result + (indexAdjustmentEvents != null ? indexAdjustmentEvents.hashCode() : 0);
			_result = 31 * _result + (additionalDisruptionEvents != null ? additionalDisruptionEvents.hashCode() : 0);
			_result = 31 * _result + (failureToDeliver != null ? failureToDeliver.hashCode() : 0);
			_result = 31 * _result + (representations != null ? representations.hashCode() : 0);
			_result = 31 * _result + (nationalizationOrInsolvency != null ? nationalizationOrInsolvency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (delisting != null ? delisting.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExtraordinaryEvents {" +
				"additionalBespokeTerms=" + this.additionalBespokeTerms + ", " +
				"mergerEvents=" + this.mergerEvents + ", " +
				"tenderOfferEvents=" + this.tenderOfferEvents + ", " +
				"compositionOfCombinedConsideration=" + this.compositionOfCombinedConsideration + ", " +
				"indexAdjustmentEvents=" + this.indexAdjustmentEvents + ", " +
				"additionalDisruptionEvents=" + this.additionalDisruptionEvents + ", " +
				"failureToDeliver=" + this.failureToDeliver + ", " +
				"representations=" + this.representations + ", " +
				"nationalizationOrInsolvency=" + this.nationalizationOrInsolvency + ", " +
				"delisting=" + this.delisting +
			'}';
		}
	}

	/*********************** Builder Implementation of ExtraordinaryEvents  ***********************/
	class ExtraordinaryEventsBuilderImpl implements ExtraordinaryEvents.ExtraordinaryEventsBuilder {
	
		protected List<Clause.ClauseBuilder> additionalBespokeTerms = new ArrayList<>();
		protected EquityCorporateEvents.EquityCorporateEventsBuilder mergerEvents;
		protected EquityCorporateEvents.EquityCorporateEventsBuilder tenderOfferEvents;
		protected Boolean compositionOfCombinedConsideration;
		protected IndexAdjustmentEvents.IndexAdjustmentEventsBuilder indexAdjustmentEvents;
		protected AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder additionalDisruptionEvents;
		protected Boolean failureToDeliver;
		protected Representations.RepresentationsBuilder representations;
		protected NationalizationOrInsolvencyOrDelistingEventEnum nationalizationOrInsolvency;
		protected NationalizationOrInsolvencyOrDelistingEventEnum delisting;
	
		public ExtraordinaryEventsBuilderImpl() {
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
		@RosettaAttribute("mergerEvents")
		public EquityCorporateEvents.EquityCorporateEventsBuilder getMergerEvents() {
			return mergerEvents;
		}
		
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder getOrCreateMergerEvents() {
			EquityCorporateEvents.EquityCorporateEventsBuilder result;
			if (mergerEvents!=null) {
				result = mergerEvents;
			}
			else {
				result = mergerEvents = EquityCorporateEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tenderOfferEvents")
		public EquityCorporateEvents.EquityCorporateEventsBuilder getTenderOfferEvents() {
			return tenderOfferEvents;
		}
		
		@Override
		public EquityCorporateEvents.EquityCorporateEventsBuilder getOrCreateTenderOfferEvents() {
			EquityCorporateEvents.EquityCorporateEventsBuilder result;
			if (tenderOfferEvents!=null) {
				result = tenderOfferEvents;
			}
			else {
				result = tenderOfferEvents = EquityCorporateEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("compositionOfCombinedConsideration")
		public Boolean getCompositionOfCombinedConsideration() {
			return compositionOfCombinedConsideration;
		}
		
		@Override
		@RosettaAttribute("indexAdjustmentEvents")
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder getIndexAdjustmentEvents() {
			return indexAdjustmentEvents;
		}
		
		@Override
		public IndexAdjustmentEvents.IndexAdjustmentEventsBuilder getOrCreateIndexAdjustmentEvents() {
			IndexAdjustmentEvents.IndexAdjustmentEventsBuilder result;
			if (indexAdjustmentEvents!=null) {
				result = indexAdjustmentEvents;
			}
			else {
				result = indexAdjustmentEvents = IndexAdjustmentEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("additionalDisruptionEvents")
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder getAdditionalDisruptionEvents() {
			return additionalDisruptionEvents;
		}
		
		@Override
		public AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder getOrCreateAdditionalDisruptionEvents() {
			AdditionalDisruptionEvents.AdditionalDisruptionEventsBuilder result;
			if (additionalDisruptionEvents!=null) {
				result = additionalDisruptionEvents;
			}
			else {
				result = additionalDisruptionEvents = AdditionalDisruptionEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("failureToDeliver")
		public Boolean getFailureToDeliver() {
			return failureToDeliver;
		}
		
		@Override
		@RosettaAttribute("representations")
		public Representations.RepresentationsBuilder getRepresentations() {
			return representations;
		}
		
		@Override
		public Representations.RepresentationsBuilder getOrCreateRepresentations() {
			Representations.RepresentationsBuilder result;
			if (representations!=null) {
				result = representations;
			}
			else {
				result = representations = Representations.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("nationalizationOrInsolvency")
		public NationalizationOrInsolvencyOrDelistingEventEnum getNationalizationOrInsolvency() {
			return nationalizationOrInsolvency;
		}
		
		@Override
		@RosettaAttribute("delisting")
		public NationalizationOrInsolvencyOrDelistingEventEnum getDelisting() {
			return delisting;
		}
		
	
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms) {
			if (additionalBespokeTerms!=null) this.additionalBespokeTerms.add(additionalBespokeTerms.toBuilder());
			return this;
		}
		
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(Clause additionalBespokeTerms, int _idx) {
			getIndex(this.additionalBespokeTerms, _idx, () -> additionalBespokeTerms.toBuilder());
			return this;
		}
		@Override 
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder addAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTermss) {
			if (additionalBespokeTermss != null) {
				for (Clause toAdd : additionalBespokeTermss) {
					this.additionalBespokeTerms.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("additionalBespokeTerms")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setAdditionalBespokeTerms(List<? extends Clause> additionalBespokeTermss) {
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
		@RosettaAttribute("mergerEvents")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setMergerEvents(EquityCorporateEvents mergerEvents) {
			this.mergerEvents = mergerEvents==null?null:mergerEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tenderOfferEvents")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setTenderOfferEvents(EquityCorporateEvents tenderOfferEvents) {
			this.tenderOfferEvents = tenderOfferEvents==null?null:tenderOfferEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("compositionOfCombinedConsideration")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setCompositionOfCombinedConsideration(Boolean compositionOfCombinedConsideration) {
			this.compositionOfCombinedConsideration = compositionOfCombinedConsideration==null?null:compositionOfCombinedConsideration;
			return this;
		}
		@Override
		@RosettaAttribute("indexAdjustmentEvents")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setIndexAdjustmentEvents(IndexAdjustmentEvents indexAdjustmentEvents) {
			this.indexAdjustmentEvents = indexAdjustmentEvents==null?null:indexAdjustmentEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("additionalDisruptionEvents")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setAdditionalDisruptionEvents(AdditionalDisruptionEvents additionalDisruptionEvents) {
			this.additionalDisruptionEvents = additionalDisruptionEvents==null?null:additionalDisruptionEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("failureToDeliver")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setFailureToDeliver(Boolean failureToDeliver) {
			this.failureToDeliver = failureToDeliver==null?null:failureToDeliver;
			return this;
		}
		@Override
		@RosettaAttribute("representations")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setRepresentations(Representations representations) {
			this.representations = representations==null?null:representations.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("nationalizationOrInsolvency")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setNationalizationOrInsolvency(NationalizationOrInsolvencyOrDelistingEventEnum nationalizationOrInsolvency) {
			this.nationalizationOrInsolvency = nationalizationOrInsolvency==null?null:nationalizationOrInsolvency;
			return this;
		}
		@Override
		@RosettaAttribute("delisting")
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder setDelisting(NationalizationOrInsolvencyOrDelistingEventEnum delisting) {
			this.delisting = delisting==null?null:delisting;
			return this;
		}
		
		@Override
		public ExtraordinaryEvents build() {
			return new ExtraordinaryEvents.ExtraordinaryEventsImpl(this);
		}
		
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder prune() {
			additionalBespokeTerms = additionalBespokeTerms.stream().filter(b->b!=null).<Clause.ClauseBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (mergerEvents!=null && !mergerEvents.prune().hasData()) mergerEvents = null;
			if (tenderOfferEvents!=null && !tenderOfferEvents.prune().hasData()) tenderOfferEvents = null;
			if (indexAdjustmentEvents!=null && !indexAdjustmentEvents.prune().hasData()) indexAdjustmentEvents = null;
			if (additionalDisruptionEvents!=null && !additionalDisruptionEvents.prune().hasData()) additionalDisruptionEvents = null;
			if (representations!=null && !representations.prune().hasData()) representations = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdditionalBespokeTerms()!=null && getAdditionalBespokeTerms().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMergerEvents()!=null && getMergerEvents().hasData()) return true;
			if (getTenderOfferEvents()!=null && getTenderOfferEvents().hasData()) return true;
			if (getCompositionOfCombinedConsideration()!=null) return true;
			if (getIndexAdjustmentEvents()!=null && getIndexAdjustmentEvents().hasData()) return true;
			if (getAdditionalDisruptionEvents()!=null && getAdditionalDisruptionEvents().hasData()) return true;
			if (getFailureToDeliver()!=null) return true;
			if (getRepresentations()!=null && getRepresentations().hasData()) return true;
			if (getNationalizationOrInsolvency()!=null) return true;
			if (getDelisting()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExtraordinaryEvents.ExtraordinaryEventsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExtraordinaryEvents.ExtraordinaryEventsBuilder o = (ExtraordinaryEvents.ExtraordinaryEventsBuilder) other;
			
			merger.mergeRosetta(getAdditionalBespokeTerms(), o.getAdditionalBespokeTerms(), this::getOrCreateAdditionalBespokeTerms);
			merger.mergeRosetta(getMergerEvents(), o.getMergerEvents(), this::setMergerEvents);
			merger.mergeRosetta(getTenderOfferEvents(), o.getTenderOfferEvents(), this::setTenderOfferEvents);
			merger.mergeRosetta(getIndexAdjustmentEvents(), o.getIndexAdjustmentEvents(), this::setIndexAdjustmentEvents);
			merger.mergeRosetta(getAdditionalDisruptionEvents(), o.getAdditionalDisruptionEvents(), this::setAdditionalDisruptionEvents);
			merger.mergeRosetta(getRepresentations(), o.getRepresentations(), this::setRepresentations);
			
			merger.mergeBasic(getCompositionOfCombinedConsideration(), o.getCompositionOfCombinedConsideration(), this::setCompositionOfCombinedConsideration);
			merger.mergeBasic(getFailureToDeliver(), o.getFailureToDeliver(), this::setFailureToDeliver);
			merger.mergeBasic(getNationalizationOrInsolvency(), o.getNationalizationOrInsolvency(), this::setNationalizationOrInsolvency);
			merger.mergeBasic(getDelisting(), o.getDelisting(), this::setDelisting);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExtraordinaryEvents _that = getType().cast(o);
		
			if (!ListEquals.listEquals(additionalBespokeTerms, _that.getAdditionalBespokeTerms())) return false;
			if (!Objects.equals(mergerEvents, _that.getMergerEvents())) return false;
			if (!Objects.equals(tenderOfferEvents, _that.getTenderOfferEvents())) return false;
			if (!Objects.equals(compositionOfCombinedConsideration, _that.getCompositionOfCombinedConsideration())) return false;
			if (!Objects.equals(indexAdjustmentEvents, _that.getIndexAdjustmentEvents())) return false;
			if (!Objects.equals(additionalDisruptionEvents, _that.getAdditionalDisruptionEvents())) return false;
			if (!Objects.equals(failureToDeliver, _that.getFailureToDeliver())) return false;
			if (!Objects.equals(representations, _that.getRepresentations())) return false;
			if (!Objects.equals(nationalizationOrInsolvency, _that.getNationalizationOrInsolvency())) return false;
			if (!Objects.equals(delisting, _that.getDelisting())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (additionalBespokeTerms != null ? additionalBespokeTerms.hashCode() : 0);
			_result = 31 * _result + (mergerEvents != null ? mergerEvents.hashCode() : 0);
			_result = 31 * _result + (tenderOfferEvents != null ? tenderOfferEvents.hashCode() : 0);
			_result = 31 * _result + (compositionOfCombinedConsideration != null ? compositionOfCombinedConsideration.hashCode() : 0);
			_result = 31 * _result + (indexAdjustmentEvents != null ? indexAdjustmentEvents.hashCode() : 0);
			_result = 31 * _result + (additionalDisruptionEvents != null ? additionalDisruptionEvents.hashCode() : 0);
			_result = 31 * _result + (failureToDeliver != null ? failureToDeliver.hashCode() : 0);
			_result = 31 * _result + (representations != null ? representations.hashCode() : 0);
			_result = 31 * _result + (nationalizationOrInsolvency != null ? nationalizationOrInsolvency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (delisting != null ? delisting.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExtraordinaryEventsBuilder {" +
				"additionalBespokeTerms=" + this.additionalBespokeTerms + ", " +
				"mergerEvents=" + this.mergerEvents + ", " +
				"tenderOfferEvents=" + this.tenderOfferEvents + ", " +
				"compositionOfCombinedConsideration=" + this.compositionOfCombinedConsideration + ", " +
				"indexAdjustmentEvents=" + this.indexAdjustmentEvents + ", " +
				"additionalDisruptionEvents=" + this.additionalDisruptionEvents + ", " +
				"failureToDeliver=" + this.failureToDeliver + ", " +
				"representations=" + this.representations + ", " +
				"nationalizationOrInsolvency=" + this.nationalizationOrInsolvency + ", " +
				"delisting=" + this.delisting +
			'}';
		}
	}
}
