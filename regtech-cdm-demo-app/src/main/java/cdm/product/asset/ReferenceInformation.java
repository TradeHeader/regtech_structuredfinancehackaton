package cdm.product.asset;

import cdm.base.staticdata.party.LegalEntity;
import cdm.observable.asset.Price;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.ReferenceInformation.ReferenceInformationBuilder;
import cdm.product.asset.ReferenceInformation.ReferenceInformationBuilderImpl;
import cdm.product.asset.ReferenceInformation.ReferenceInformationImpl;
import cdm.product.asset.ReferenceObligation;
import cdm.product.asset.meta.ReferenceInformationMeta;
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
 * A class specifying the Credit Default Swap Reference Information.
 * @version ${project.version}
 */
@RosettaDataType(value="ReferenceInformation", builder=ReferenceInformation.ReferenceInformationBuilderImpl.class, version="${project.version}")
public interface ReferenceInformation extends RosettaModelObject {

	ReferenceInformationMeta metaData = new ReferenceInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The corporate or sovereign entity which is subject to the swap transaction and any successor that assumes all or substantially all of its contractual and other obligations. Reference Entities cannot be senior or subordinated. It is the obligations of the Reference Entities that can be senior or subordinated. ISDA 2014 Credit definitions article II section 2.1: `Reference Entity` means the entity specified as such in the related Confirmation.
	 */
	LegalEntity getReferenceEntity();
	/**
	 * The Reference Obligation is a financial instrument that is either issued or guaranteed by the reference entity. It serves to clarify the precise reference entity protection is being offered upon, and its legal position with regard to other related firms (parents/subsidiaries). Furthermore the Reference Obligation is ALWAYS deliverable and establishes the Pari Passu ranking (as the deliverable bonds must rank equal to the reference obligation). ISDA 2003 Term: Reference Obligation.
	 */
	List<? extends ReferenceObligation> getReferenceObligation();
	/**
	 * Used to indicate that there is no Reference Obligation associated with this Credit Default Swap and that there will never be one.
	 */
	Boolean getNoReferenceObligation();
	/**
	 * Used to indicate that the Reference obligation associated with the Credit Default Swap is currently not known. This is not valid for Legal Confirmation purposes, but is valid for earlier stages in the trade life cycle (e.g. Broker Confirmation).
	 */
	Boolean getUnknownReferenceObligation();
	/**
	 * Indicates whether an obligation of the Reference Entity, guaranteed by the Reference Entity on behalf of a non-Affiliate, is to be considered an Obligation for the purpose of the transaction. It will be considered an obligation if allGuarantees is applicable (true) and not if allGuarantees is inapplicable (false). ISDA 2003 Term: All Guarantees.
	 */
	Boolean getAllGuarantees();
	/**
	 * Used to determine (a) for physically settled trades, the Physical Settlement Amount, which equals the Floating Rate Payer Calculation Amount times the Reference Price and (b) for cash settled trades, the Cash Settlement Amount, which equals the greater of (i) the difference between the Reference Price and the Final Price and (ii) zero. ISDA 2003 Term: Reference Price.
	 */
	Price getReferencePrice();
	/**
	 * Applicable to the transactions on mortgage-backed security, which can make use of a reference policy. Presence of the element with value set to &#39;true&#39; indicates that the reference policy is applicable; absence implies that it is not.
	 */
	Boolean getReferencePolicy();
	/**
	 * With respect to any day, the list of Syndicated Secured Obligations of the Designated Priority of the Reference Entity published by Markit Group Limited or any successor thereto appointed by the Specified Dealers (the &#39;Secured List Publisher&#39;) on or most recently before such day, which list is currently available at [http://www.markit.com]. ISDA 2003 Term: Relevant Secured List.
	 */
	Boolean getSecuredList();

	/*********************** Build Methods  ***********************/
	ReferenceInformation build();
	
	ReferenceInformation.ReferenceInformationBuilder toBuilder();
	
	static ReferenceInformation.ReferenceInformationBuilder builder() {
		return new ReferenceInformation.ReferenceInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceInformation> getType() {
		return ReferenceInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referenceEntity"), processor, LegalEntity.class, getReferenceEntity());
		processRosetta(path.newSubPath("referenceObligation"), processor, ReferenceObligation.class, getReferenceObligation());
		processor.processBasic(path.newSubPath("noReferenceObligation"), Boolean.class, getNoReferenceObligation(), this);
		processor.processBasic(path.newSubPath("unknownReferenceObligation"), Boolean.class, getUnknownReferenceObligation(), this);
		processor.processBasic(path.newSubPath("allGuarantees"), Boolean.class, getAllGuarantees(), this);
		processRosetta(path.newSubPath("referencePrice"), processor, Price.class, getReferencePrice());
		processor.processBasic(path.newSubPath("referencePolicy"), Boolean.class, getReferencePolicy(), this);
		processor.processBasic(path.newSubPath("securedList"), Boolean.class, getSecuredList(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceInformationBuilder extends ReferenceInformation, RosettaModelObjectBuilder {
		LegalEntity.LegalEntityBuilder getOrCreateReferenceEntity();
		LegalEntity.LegalEntityBuilder getReferenceEntity();
		ReferenceObligation.ReferenceObligationBuilder getOrCreateReferenceObligation(int _index);
		List<? extends ReferenceObligation.ReferenceObligationBuilder> getReferenceObligation();
		Price.PriceBuilder getOrCreateReferencePrice();
		Price.PriceBuilder getReferencePrice();
		ReferenceInformation.ReferenceInformationBuilder setReferenceEntity(LegalEntity referenceEntity);
		ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(ReferenceObligation referenceObligation0);
		ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(ReferenceObligation referenceObligation1, int _idx);
		ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(List<? extends ReferenceObligation> referenceObligation2);
		ReferenceInformation.ReferenceInformationBuilder setReferenceObligation(List<? extends ReferenceObligation> referenceObligation3);
		ReferenceInformation.ReferenceInformationBuilder setNoReferenceObligation(Boolean noReferenceObligation);
		ReferenceInformation.ReferenceInformationBuilder setUnknownReferenceObligation(Boolean unknownReferenceObligation);
		ReferenceInformation.ReferenceInformationBuilder setAllGuarantees(Boolean allGuarantees);
		ReferenceInformation.ReferenceInformationBuilder setReferencePrice(Price referencePrice);
		ReferenceInformation.ReferenceInformationBuilder setReferencePolicy(Boolean referencePolicy);
		ReferenceInformation.ReferenceInformationBuilder setSecuredList(Boolean securedList);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referenceEntity"), processor, LegalEntity.LegalEntityBuilder.class, getReferenceEntity());
			processRosetta(path.newSubPath("referenceObligation"), processor, ReferenceObligation.ReferenceObligationBuilder.class, getReferenceObligation());
			processor.processBasic(path.newSubPath("noReferenceObligation"), Boolean.class, getNoReferenceObligation(), this);
			processor.processBasic(path.newSubPath("unknownReferenceObligation"), Boolean.class, getUnknownReferenceObligation(), this);
			processor.processBasic(path.newSubPath("allGuarantees"), Boolean.class, getAllGuarantees(), this);
			processRosetta(path.newSubPath("referencePrice"), processor, Price.PriceBuilder.class, getReferencePrice());
			processor.processBasic(path.newSubPath("referencePolicy"), Boolean.class, getReferencePolicy(), this);
			processor.processBasic(path.newSubPath("securedList"), Boolean.class, getSecuredList(), this);
		}
		

		ReferenceInformation.ReferenceInformationBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceInformation  ***********************/
	class ReferenceInformationImpl implements ReferenceInformation {
		private final LegalEntity referenceEntity;
		private final List<? extends ReferenceObligation> referenceObligation;
		private final Boolean noReferenceObligation;
		private final Boolean unknownReferenceObligation;
		private final Boolean allGuarantees;
		private final Price referencePrice;
		private final Boolean referencePolicy;
		private final Boolean securedList;
		
		protected ReferenceInformationImpl(ReferenceInformation.ReferenceInformationBuilder builder) {
			this.referenceEntity = ofNullable(builder.getReferenceEntity()).map(f->f.build()).orElse(null);
			this.referenceObligation = ofNullable(builder.getReferenceObligation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.noReferenceObligation = builder.getNoReferenceObligation();
			this.unknownReferenceObligation = builder.getUnknownReferenceObligation();
			this.allGuarantees = builder.getAllGuarantees();
			this.referencePrice = ofNullable(builder.getReferencePrice()).map(f->f.build()).orElse(null);
			this.referencePolicy = builder.getReferencePolicy();
			this.securedList = builder.getSecuredList();
		}
		
		@Override
		@RosettaAttribute("referenceEntity")
		public LegalEntity getReferenceEntity() {
			return referenceEntity;
		}
		
		@Override
		@RosettaAttribute("referenceObligation")
		public List<? extends ReferenceObligation> getReferenceObligation() {
			return referenceObligation;
		}
		
		@Override
		@RosettaAttribute("noReferenceObligation")
		public Boolean getNoReferenceObligation() {
			return noReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("unknownReferenceObligation")
		public Boolean getUnknownReferenceObligation() {
			return unknownReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("allGuarantees")
		public Boolean getAllGuarantees() {
			return allGuarantees;
		}
		
		@Override
		@RosettaAttribute("referencePrice")
		public Price getReferencePrice() {
			return referencePrice;
		}
		
		@Override
		@RosettaAttribute("referencePolicy")
		public Boolean getReferencePolicy() {
			return referencePolicy;
		}
		
		@Override
		@RosettaAttribute("securedList")
		public Boolean getSecuredList() {
			return securedList;
		}
		
		@Override
		public ReferenceInformation build() {
			return this;
		}
		
		@Override
		public ReferenceInformation.ReferenceInformationBuilder toBuilder() {
			ReferenceInformation.ReferenceInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceInformation.ReferenceInformationBuilder builder) {
			ofNullable(getReferenceEntity()).ifPresent(builder::setReferenceEntity);
			ofNullable(getReferenceObligation()).ifPresent(builder::setReferenceObligation);
			ofNullable(getNoReferenceObligation()).ifPresent(builder::setNoReferenceObligation);
			ofNullable(getUnknownReferenceObligation()).ifPresent(builder::setUnknownReferenceObligation);
			ofNullable(getAllGuarantees()).ifPresent(builder::setAllGuarantees);
			ofNullable(getReferencePrice()).ifPresent(builder::setReferencePrice);
			ofNullable(getReferencePolicy()).ifPresent(builder::setReferencePolicy);
			ofNullable(getSecuredList()).ifPresent(builder::setSecuredList);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(referenceEntity, _that.getReferenceEntity())) return false;
			if (!ListEquals.listEquals(referenceObligation, _that.getReferenceObligation())) return false;
			if (!Objects.equals(noReferenceObligation, _that.getNoReferenceObligation())) return false;
			if (!Objects.equals(unknownReferenceObligation, _that.getUnknownReferenceObligation())) return false;
			if (!Objects.equals(allGuarantees, _that.getAllGuarantees())) return false;
			if (!Objects.equals(referencePrice, _that.getReferencePrice())) return false;
			if (!Objects.equals(referencePolicy, _that.getReferencePolicy())) return false;
			if (!Objects.equals(securedList, _that.getSecuredList())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceEntity != null ? referenceEntity.hashCode() : 0);
			_result = 31 * _result + (referenceObligation != null ? referenceObligation.hashCode() : 0);
			_result = 31 * _result + (noReferenceObligation != null ? noReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (unknownReferenceObligation != null ? unknownReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (allGuarantees != null ? allGuarantees.hashCode() : 0);
			_result = 31 * _result + (referencePrice != null ? referencePrice.hashCode() : 0);
			_result = 31 * _result + (referencePolicy != null ? referencePolicy.hashCode() : 0);
			_result = 31 * _result + (securedList != null ? securedList.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceInformation {" +
				"referenceEntity=" + this.referenceEntity + ", " +
				"referenceObligation=" + this.referenceObligation + ", " +
				"noReferenceObligation=" + this.noReferenceObligation + ", " +
				"unknownReferenceObligation=" + this.unknownReferenceObligation + ", " +
				"allGuarantees=" + this.allGuarantees + ", " +
				"referencePrice=" + this.referencePrice + ", " +
				"referencePolicy=" + this.referencePolicy + ", " +
				"securedList=" + this.securedList +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceInformation  ***********************/
	class ReferenceInformationBuilderImpl implements ReferenceInformation.ReferenceInformationBuilder {
	
		protected LegalEntity.LegalEntityBuilder referenceEntity;
		protected List<ReferenceObligation.ReferenceObligationBuilder> referenceObligation = new ArrayList<>();
		protected Boolean noReferenceObligation;
		protected Boolean unknownReferenceObligation;
		protected Boolean allGuarantees;
		protected Price.PriceBuilder referencePrice;
		protected Boolean referencePolicy;
		protected Boolean securedList;
	
		public ReferenceInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("referenceEntity")
		public LegalEntity.LegalEntityBuilder getReferenceEntity() {
			return referenceEntity;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder getOrCreateReferenceEntity() {
			LegalEntity.LegalEntityBuilder result;
			if (referenceEntity!=null) {
				result = referenceEntity;
			}
			else {
				result = referenceEntity = LegalEntity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("referenceObligation")
		public List<? extends ReferenceObligation.ReferenceObligationBuilder> getReferenceObligation() {
			return referenceObligation;
		}
		
		public ReferenceObligation.ReferenceObligationBuilder getOrCreateReferenceObligation(int _index) {
		
			if (referenceObligation==null) {
				this.referenceObligation = new ArrayList<>();
			}
			ReferenceObligation.ReferenceObligationBuilder result;
			return getIndex(referenceObligation, _index, () -> {
						ReferenceObligation.ReferenceObligationBuilder newReferenceObligation = ReferenceObligation.builder();
						return newReferenceObligation;
					});
		}
		
		@Override
		@RosettaAttribute("noReferenceObligation")
		public Boolean getNoReferenceObligation() {
			return noReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("unknownReferenceObligation")
		public Boolean getUnknownReferenceObligation() {
			return unknownReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("allGuarantees")
		public Boolean getAllGuarantees() {
			return allGuarantees;
		}
		
		@Override
		@RosettaAttribute("referencePrice")
		public Price.PriceBuilder getReferencePrice() {
			return referencePrice;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateReferencePrice() {
			Price.PriceBuilder result;
			if (referencePrice!=null) {
				result = referencePrice;
			}
			else {
				result = referencePrice = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("referencePolicy")
		public Boolean getReferencePolicy() {
			return referencePolicy;
		}
		
		@Override
		@RosettaAttribute("securedList")
		public Boolean getSecuredList() {
			return securedList;
		}
		
	
		@Override
		@RosettaAttribute("referenceEntity")
		public ReferenceInformation.ReferenceInformationBuilder setReferenceEntity(LegalEntity referenceEntity) {
			this.referenceEntity = referenceEntity==null?null:referenceEntity.toBuilder();
			return this;
		}
		@Override
		public ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(ReferenceObligation referenceObligation) {
			if (referenceObligation!=null) this.referenceObligation.add(referenceObligation.toBuilder());
			return this;
		}
		
		@Override
		public ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(ReferenceObligation referenceObligation, int _idx) {
			getIndex(this.referenceObligation, _idx, () -> referenceObligation.toBuilder());
			return this;
		}
		@Override 
		public ReferenceInformation.ReferenceInformationBuilder addReferenceObligation(List<? extends ReferenceObligation> referenceObligations) {
			if (referenceObligations != null) {
				for (ReferenceObligation toAdd : referenceObligations) {
					this.referenceObligation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("referenceObligation")
		public ReferenceInformation.ReferenceInformationBuilder setReferenceObligation(List<? extends ReferenceObligation> referenceObligations) {
			if (referenceObligations == null)  {
				this.referenceObligation = new ArrayList<>();
			}
			else {
				this.referenceObligation = referenceObligations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("noReferenceObligation")
		public ReferenceInformation.ReferenceInformationBuilder setNoReferenceObligation(Boolean noReferenceObligation) {
			this.noReferenceObligation = noReferenceObligation==null?null:noReferenceObligation;
			return this;
		}
		@Override
		@RosettaAttribute("unknownReferenceObligation")
		public ReferenceInformation.ReferenceInformationBuilder setUnknownReferenceObligation(Boolean unknownReferenceObligation) {
			this.unknownReferenceObligation = unknownReferenceObligation==null?null:unknownReferenceObligation;
			return this;
		}
		@Override
		@RosettaAttribute("allGuarantees")
		public ReferenceInformation.ReferenceInformationBuilder setAllGuarantees(Boolean allGuarantees) {
			this.allGuarantees = allGuarantees==null?null:allGuarantees;
			return this;
		}
		@Override
		@RosettaAttribute("referencePrice")
		public ReferenceInformation.ReferenceInformationBuilder setReferencePrice(Price referencePrice) {
			this.referencePrice = referencePrice==null?null:referencePrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("referencePolicy")
		public ReferenceInformation.ReferenceInformationBuilder setReferencePolicy(Boolean referencePolicy) {
			this.referencePolicy = referencePolicy==null?null:referencePolicy;
			return this;
		}
		@Override
		@RosettaAttribute("securedList")
		public ReferenceInformation.ReferenceInformationBuilder setSecuredList(Boolean securedList) {
			this.securedList = securedList==null?null:securedList;
			return this;
		}
		
		@Override
		public ReferenceInformation build() {
			return new ReferenceInformation.ReferenceInformationImpl(this);
		}
		
		@Override
		public ReferenceInformation.ReferenceInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceInformation.ReferenceInformationBuilder prune() {
			if (referenceEntity!=null && !referenceEntity.prune().hasData()) referenceEntity = null;
			referenceObligation = referenceObligation.stream().filter(b->b!=null).<ReferenceObligation.ReferenceObligationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (referencePrice!=null && !referencePrice.prune().hasData()) referencePrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferenceEntity()!=null && getReferenceEntity().hasData()) return true;
			if (getReferenceObligation()!=null && getReferenceObligation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getNoReferenceObligation()!=null) return true;
			if (getUnknownReferenceObligation()!=null) return true;
			if (getAllGuarantees()!=null) return true;
			if (getReferencePrice()!=null && getReferencePrice().hasData()) return true;
			if (getReferencePolicy()!=null) return true;
			if (getSecuredList()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceInformation.ReferenceInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceInformation.ReferenceInformationBuilder o = (ReferenceInformation.ReferenceInformationBuilder) other;
			
			merger.mergeRosetta(getReferenceEntity(), o.getReferenceEntity(), this::setReferenceEntity);
			merger.mergeRosetta(getReferenceObligation(), o.getReferenceObligation(), this::getOrCreateReferenceObligation);
			merger.mergeRosetta(getReferencePrice(), o.getReferencePrice(), this::setReferencePrice);
			
			merger.mergeBasic(getNoReferenceObligation(), o.getNoReferenceObligation(), this::setNoReferenceObligation);
			merger.mergeBasic(getUnknownReferenceObligation(), o.getUnknownReferenceObligation(), this::setUnknownReferenceObligation);
			merger.mergeBasic(getAllGuarantees(), o.getAllGuarantees(), this::setAllGuarantees);
			merger.mergeBasic(getReferencePolicy(), o.getReferencePolicy(), this::setReferencePolicy);
			merger.mergeBasic(getSecuredList(), o.getSecuredList(), this::setSecuredList);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(referenceEntity, _that.getReferenceEntity())) return false;
			if (!ListEquals.listEquals(referenceObligation, _that.getReferenceObligation())) return false;
			if (!Objects.equals(noReferenceObligation, _that.getNoReferenceObligation())) return false;
			if (!Objects.equals(unknownReferenceObligation, _that.getUnknownReferenceObligation())) return false;
			if (!Objects.equals(allGuarantees, _that.getAllGuarantees())) return false;
			if (!Objects.equals(referencePrice, _that.getReferencePrice())) return false;
			if (!Objects.equals(referencePolicy, _that.getReferencePolicy())) return false;
			if (!Objects.equals(securedList, _that.getSecuredList())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceEntity != null ? referenceEntity.hashCode() : 0);
			_result = 31 * _result + (referenceObligation != null ? referenceObligation.hashCode() : 0);
			_result = 31 * _result + (noReferenceObligation != null ? noReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (unknownReferenceObligation != null ? unknownReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (allGuarantees != null ? allGuarantees.hashCode() : 0);
			_result = 31 * _result + (referencePrice != null ? referencePrice.hashCode() : 0);
			_result = 31 * _result + (referencePolicy != null ? referencePolicy.hashCode() : 0);
			_result = 31 * _result + (securedList != null ? securedList.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceInformationBuilder {" +
				"referenceEntity=" + this.referenceEntity + ", " +
				"referenceObligation=" + this.referenceObligation + ", " +
				"noReferenceObligation=" + this.noReferenceObligation + ", " +
				"unknownReferenceObligation=" + this.unknownReferenceObligation + ", " +
				"allGuarantees=" + this.allGuarantees + ", " +
				"referencePrice=" + this.referencePrice + ", " +
				"referencePolicy=" + this.referencePolicy + ", " +
				"securedList=" + this.securedList +
			'}';
		}
	}
}
