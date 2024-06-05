package cdm.product.collateral;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.IssuerCriteria;
import cdm.product.collateral.IssuerCriteria.IssuerCriteriaBuilder;
import cdm.product.collateral.IssuerCriteria.IssuerCriteriaBuilderImpl;
import cdm.product.collateral.IssuerCriteria.IssuerCriteriaImpl;
import cdm.product.collateral.meta.IssuerCriteriaMeta;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents a criteria used to specify eligible collateral issuers.
 * @version ${project.version}
 */
@RosettaDataType(value="IssuerCriteria", builder=IssuerCriteria.IssuerCriteriaBuilderImpl.class, version="${project.version}")
public interface IssuerCriteria extends RosettaModelObject {

	IssuerCriteriaMeta metaData = new IssuerCriteriaMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a filter based on the type of entity issuing the asset.
	 */
	List<? extends CollateralIssuerType> getIssuerType();
	/**
	 * Represents a filter on the issuing entity country of origin based on the ISO Standard 3166, which is the same as filtering by eligible Sovereigns.
	 */
	List<ISOCountryCodeEnum> getIssuerCountryOfOrigin();
	/**
	 * Specifies the issuing entity name or LEI.
	 */
	List<? extends LegalEntity> getIssuerName();
	/**
	 * Represents an agency rating based on default risk and creditors claim in event of default associated with asset issuer.
	 */
	List<? extends AgencyRatingCriteria> getIssuerAgencyRating();
	/**
	 * Represents an agency rating based on default risk of the country of the issuer.
	 */
	List<? extends AgencyRatingCriteria> getSovereignAgencyRating();
	/**
	 * Represents a filter based on whether it is permitted for the underlying asset to be issued by the posting entity or part of their corporate family.
	 */
	Boolean getCounterpartyOwnIssuePermitted();

	/*********************** Build Methods  ***********************/
	IssuerCriteria build();
	
	IssuerCriteria.IssuerCriteriaBuilder toBuilder();
	
	static IssuerCriteria.IssuerCriteriaBuilder builder() {
		return new IssuerCriteria.IssuerCriteriaBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends IssuerCriteria> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends IssuerCriteria> getType() {
		return IssuerCriteria.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuerType"), processor, CollateralIssuerType.class, getIssuerType());
		processor.processBasic(path.newSubPath("issuerCountryOfOrigin"), ISOCountryCodeEnum.class, getIssuerCountryOfOrigin(), this);
		processRosetta(path.newSubPath("issuerName"), processor, LegalEntity.class, getIssuerName());
		processRosetta(path.newSubPath("issuerAgencyRating"), processor, AgencyRatingCriteria.class, getIssuerAgencyRating());
		processRosetta(path.newSubPath("sovereignAgencyRating"), processor, AgencyRatingCriteria.class, getSovereignAgencyRating());
		processor.processBasic(path.newSubPath("counterpartyOwnIssuePermitted"), Boolean.class, getCounterpartyOwnIssuePermitted(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface IssuerCriteriaBuilder extends IssuerCriteria, RosettaModelObjectBuilder {
		CollateralIssuerType.CollateralIssuerTypeBuilder getOrCreateIssuerType(int _index);
		List<? extends CollateralIssuerType.CollateralIssuerTypeBuilder> getIssuerType();
		LegalEntity.LegalEntityBuilder getOrCreateIssuerName(int _index);
		List<? extends LegalEntity.LegalEntityBuilder> getIssuerName();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateIssuerAgencyRating(int _index);
		List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getIssuerAgencyRating();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateSovereignAgencyRating(int _index);
		List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getSovereignAgencyRating();
		IssuerCriteria.IssuerCriteriaBuilder addIssuerType(CollateralIssuerType issuerType0);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerType(CollateralIssuerType issuerType1, int _idx);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerType(List<? extends CollateralIssuerType> issuerType2);
		IssuerCriteria.IssuerCriteriaBuilder setIssuerType(List<? extends CollateralIssuerType> issuerType3);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(ISOCountryCodeEnum issuerCountryOfOrigin0);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(ISOCountryCodeEnum issuerCountryOfOrigin1, int _idx);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(List<? extends ISOCountryCodeEnum> issuerCountryOfOrigin2);
		IssuerCriteria.IssuerCriteriaBuilder setIssuerCountryOfOrigin(List<? extends ISOCountryCodeEnum> issuerCountryOfOrigin3);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerName(LegalEntity issuerName0);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerName(LegalEntity issuerName1, int _idx);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerName(List<? extends LegalEntity> issuerName2);
		IssuerCriteria.IssuerCriteriaBuilder setIssuerName(List<? extends LegalEntity> issuerName3);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(AgencyRatingCriteria issuerAgencyRating0);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(AgencyRatingCriteria issuerAgencyRating1, int _idx);
		IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(List<? extends AgencyRatingCriteria> issuerAgencyRating2);
		IssuerCriteria.IssuerCriteriaBuilder setIssuerAgencyRating(List<? extends AgencyRatingCriteria> issuerAgencyRating3);
		IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(AgencyRatingCriteria sovereignAgencyRating0);
		IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(AgencyRatingCriteria sovereignAgencyRating1, int _idx);
		IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(List<? extends AgencyRatingCriteria> sovereignAgencyRating2);
		IssuerCriteria.IssuerCriteriaBuilder setSovereignAgencyRating(List<? extends AgencyRatingCriteria> sovereignAgencyRating3);
		IssuerCriteria.IssuerCriteriaBuilder setCounterpartyOwnIssuePermitted(Boolean counterpartyOwnIssuePermitted);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuerType"), processor, CollateralIssuerType.CollateralIssuerTypeBuilder.class, getIssuerType());
			processor.processBasic(path.newSubPath("issuerCountryOfOrigin"), ISOCountryCodeEnum.class, getIssuerCountryOfOrigin(), this);
			processRosetta(path.newSubPath("issuerName"), processor, LegalEntity.LegalEntityBuilder.class, getIssuerName());
			processRosetta(path.newSubPath("issuerAgencyRating"), processor, AgencyRatingCriteria.AgencyRatingCriteriaBuilder.class, getIssuerAgencyRating());
			processRosetta(path.newSubPath("sovereignAgencyRating"), processor, AgencyRatingCriteria.AgencyRatingCriteriaBuilder.class, getSovereignAgencyRating());
			processor.processBasic(path.newSubPath("counterpartyOwnIssuePermitted"), Boolean.class, getCounterpartyOwnIssuePermitted(), this);
		}
		

		IssuerCriteria.IssuerCriteriaBuilder prune();
	}

	/*********************** Immutable Implementation of IssuerCriteria  ***********************/
	class IssuerCriteriaImpl implements IssuerCriteria {
		private final List<? extends CollateralIssuerType> issuerType;
		private final List<ISOCountryCodeEnum> issuerCountryOfOrigin;
		private final List<? extends LegalEntity> issuerName;
		private final List<? extends AgencyRatingCriteria> issuerAgencyRating;
		private final List<? extends AgencyRatingCriteria> sovereignAgencyRating;
		private final Boolean counterpartyOwnIssuePermitted;
		
		protected IssuerCriteriaImpl(IssuerCriteria.IssuerCriteriaBuilder builder) {
			this.issuerType = ofNullable(builder.getIssuerType()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.issuerCountryOfOrigin = ofNullable(builder.getIssuerCountryOfOrigin()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.issuerName = ofNullable(builder.getIssuerName()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.issuerAgencyRating = ofNullable(builder.getIssuerAgencyRating()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.sovereignAgencyRating = ofNullable(builder.getSovereignAgencyRating()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.counterpartyOwnIssuePermitted = builder.getCounterpartyOwnIssuePermitted();
		}
		
		@Override
		@RosettaAttribute("issuerType")
		public List<? extends CollateralIssuerType> getIssuerType() {
			return issuerType;
		}
		
		@Override
		@RosettaAttribute("issuerCountryOfOrigin")
		public List<ISOCountryCodeEnum> getIssuerCountryOfOrigin() {
			return issuerCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("issuerName")
		public List<? extends LegalEntity> getIssuerName() {
			return issuerName;
		}
		
		@Override
		@RosettaAttribute("issuerAgencyRating")
		public List<? extends AgencyRatingCriteria> getIssuerAgencyRating() {
			return issuerAgencyRating;
		}
		
		@Override
		@RosettaAttribute("sovereignAgencyRating")
		public List<? extends AgencyRatingCriteria> getSovereignAgencyRating() {
			return sovereignAgencyRating;
		}
		
		@Override
		@RosettaAttribute("counterpartyOwnIssuePermitted")
		public Boolean getCounterpartyOwnIssuePermitted() {
			return counterpartyOwnIssuePermitted;
		}
		
		@Override
		public IssuerCriteria build() {
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder toBuilder() {
			IssuerCriteria.IssuerCriteriaBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(IssuerCriteria.IssuerCriteriaBuilder builder) {
			ofNullable(getIssuerType()).ifPresent(builder::setIssuerType);
			ofNullable(getIssuerCountryOfOrigin()).ifPresent(builder::setIssuerCountryOfOrigin);
			ofNullable(getIssuerName()).ifPresent(builder::setIssuerName);
			ofNullable(getIssuerAgencyRating()).ifPresent(builder::setIssuerAgencyRating);
			ofNullable(getSovereignAgencyRating()).ifPresent(builder::setSovereignAgencyRating);
			ofNullable(getCounterpartyOwnIssuePermitted()).ifPresent(builder::setCounterpartyOwnIssuePermitted);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IssuerCriteria _that = getType().cast(o);
		
			if (!ListEquals.listEquals(issuerType, _that.getIssuerType())) return false;
			if (!ListEquals.listEquals(issuerCountryOfOrigin, _that.getIssuerCountryOfOrigin())) return false;
			if (!ListEquals.listEquals(issuerName, _that.getIssuerName())) return false;
			if (!ListEquals.listEquals(issuerAgencyRating, _that.getIssuerAgencyRating())) return false;
			if (!ListEquals.listEquals(sovereignAgencyRating, _that.getSovereignAgencyRating())) return false;
			if (!Objects.equals(counterpartyOwnIssuePermitted, _that.getCounterpartyOwnIssuePermitted())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerType != null ? issuerType.hashCode() : 0);
			_result = 31 * _result + (issuerCountryOfOrigin != null ? issuerCountryOfOrigin.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (issuerName != null ? issuerName.hashCode() : 0);
			_result = 31 * _result + (issuerAgencyRating != null ? issuerAgencyRating.hashCode() : 0);
			_result = 31 * _result + (sovereignAgencyRating != null ? sovereignAgencyRating.hashCode() : 0);
			_result = 31 * _result + (counterpartyOwnIssuePermitted != null ? counterpartyOwnIssuePermitted.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IssuerCriteria {" +
				"issuerType=" + this.issuerType + ", " +
				"issuerCountryOfOrigin=" + this.issuerCountryOfOrigin + ", " +
				"issuerName=" + this.issuerName + ", " +
				"issuerAgencyRating=" + this.issuerAgencyRating + ", " +
				"sovereignAgencyRating=" + this.sovereignAgencyRating + ", " +
				"counterpartyOwnIssuePermitted=" + this.counterpartyOwnIssuePermitted +
			'}';
		}
	}

	/*********************** Builder Implementation of IssuerCriteria  ***********************/
	class IssuerCriteriaBuilderImpl implements IssuerCriteria.IssuerCriteriaBuilder {
	
		protected List<CollateralIssuerType.CollateralIssuerTypeBuilder> issuerType = new ArrayList<>();
		protected List<ISOCountryCodeEnum> issuerCountryOfOrigin = new ArrayList<>();
		protected List<LegalEntity.LegalEntityBuilder> issuerName = new ArrayList<>();
		protected List<AgencyRatingCriteria.AgencyRatingCriteriaBuilder> issuerAgencyRating = new ArrayList<>();
		protected List<AgencyRatingCriteria.AgencyRatingCriteriaBuilder> sovereignAgencyRating = new ArrayList<>();
		protected Boolean counterpartyOwnIssuePermitted;
	
		public IssuerCriteriaBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("issuerType")
		public List<? extends CollateralIssuerType.CollateralIssuerTypeBuilder> getIssuerType() {
			return issuerType;
		}
		
		public CollateralIssuerType.CollateralIssuerTypeBuilder getOrCreateIssuerType(int _index) {
		
			if (issuerType==null) {
				this.issuerType = new ArrayList<>();
			}
			CollateralIssuerType.CollateralIssuerTypeBuilder result;
			return getIndex(issuerType, _index, () -> {
						CollateralIssuerType.CollateralIssuerTypeBuilder newIssuerType = CollateralIssuerType.builder();
						return newIssuerType;
					});
		}
		
		@Override
		@RosettaAttribute("issuerCountryOfOrigin")
		public List<ISOCountryCodeEnum> getIssuerCountryOfOrigin() {
			return issuerCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("issuerName")
		public List<? extends LegalEntity.LegalEntityBuilder> getIssuerName() {
			return issuerName;
		}
		
		public LegalEntity.LegalEntityBuilder getOrCreateIssuerName(int _index) {
		
			if (issuerName==null) {
				this.issuerName = new ArrayList<>();
			}
			LegalEntity.LegalEntityBuilder result;
			return getIndex(issuerName, _index, () -> {
						LegalEntity.LegalEntityBuilder newIssuerName = LegalEntity.builder();
						return newIssuerName;
					});
		}
		
		@Override
		@RosettaAttribute("issuerAgencyRating")
		public List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getIssuerAgencyRating() {
			return issuerAgencyRating;
		}
		
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateIssuerAgencyRating(int _index) {
		
			if (issuerAgencyRating==null) {
				this.issuerAgencyRating = new ArrayList<>();
			}
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder result;
			return getIndex(issuerAgencyRating, _index, () -> {
						AgencyRatingCriteria.AgencyRatingCriteriaBuilder newIssuerAgencyRating = AgencyRatingCriteria.builder();
						return newIssuerAgencyRating;
					});
		}
		
		@Override
		@RosettaAttribute("sovereignAgencyRating")
		public List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getSovereignAgencyRating() {
			return sovereignAgencyRating;
		}
		
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateSovereignAgencyRating(int _index) {
		
			if (sovereignAgencyRating==null) {
				this.sovereignAgencyRating = new ArrayList<>();
			}
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder result;
			return getIndex(sovereignAgencyRating, _index, () -> {
						AgencyRatingCriteria.AgencyRatingCriteriaBuilder newSovereignAgencyRating = AgencyRatingCriteria.builder();
						return newSovereignAgencyRating;
					});
		}
		
		@Override
		@RosettaAttribute("counterpartyOwnIssuePermitted")
		public Boolean getCounterpartyOwnIssuePermitted() {
			return counterpartyOwnIssuePermitted;
		}
		
	
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerType(CollateralIssuerType issuerType) {
			if (issuerType!=null) this.issuerType.add(issuerType.toBuilder());
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerType(CollateralIssuerType issuerType, int _idx) {
			getIndex(this.issuerType, _idx, () -> issuerType.toBuilder());
			return this;
		}
		@Override 
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerType(List<? extends CollateralIssuerType> issuerTypes) {
			if (issuerTypes != null) {
				for (CollateralIssuerType toAdd : issuerTypes) {
					this.issuerType.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuerType")
		public IssuerCriteria.IssuerCriteriaBuilder setIssuerType(List<? extends CollateralIssuerType> issuerTypes) {
			if (issuerTypes == null)  {
				this.issuerType = new ArrayList<>();
			}
			else {
				this.issuerType = issuerTypes.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(ISOCountryCodeEnum issuerCountryOfOrigin) {
			if (issuerCountryOfOrigin!=null) this.issuerCountryOfOrigin.add(issuerCountryOfOrigin);
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(ISOCountryCodeEnum issuerCountryOfOrigin, int _idx) {
			getIndex(this.issuerCountryOfOrigin, _idx, () -> issuerCountryOfOrigin);
			return this;
		}
		@Override 
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerCountryOfOrigin(List<? extends ISOCountryCodeEnum> issuerCountryOfOrigins) {
			if (issuerCountryOfOrigins != null) {
				for (ISOCountryCodeEnum toAdd : issuerCountryOfOrigins) {
					this.issuerCountryOfOrigin.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuerCountryOfOrigin")
		public IssuerCriteria.IssuerCriteriaBuilder setIssuerCountryOfOrigin(List<? extends ISOCountryCodeEnum> issuerCountryOfOrigins) {
			if (issuerCountryOfOrigins == null)  {
				this.issuerCountryOfOrigin = new ArrayList<>();
			}
			else {
				this.issuerCountryOfOrigin = issuerCountryOfOrigins.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerName(LegalEntity issuerName) {
			if (issuerName!=null) this.issuerName.add(issuerName.toBuilder());
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerName(LegalEntity issuerName, int _idx) {
			getIndex(this.issuerName, _idx, () -> issuerName.toBuilder());
			return this;
		}
		@Override 
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerName(List<? extends LegalEntity> issuerNames) {
			if (issuerNames != null) {
				for (LegalEntity toAdd : issuerNames) {
					this.issuerName.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuerName")
		public IssuerCriteria.IssuerCriteriaBuilder setIssuerName(List<? extends LegalEntity> issuerNames) {
			if (issuerNames == null)  {
				this.issuerName = new ArrayList<>();
			}
			else {
				this.issuerName = issuerNames.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(AgencyRatingCriteria issuerAgencyRating) {
			if (issuerAgencyRating!=null) this.issuerAgencyRating.add(issuerAgencyRating.toBuilder());
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(AgencyRatingCriteria issuerAgencyRating, int _idx) {
			getIndex(this.issuerAgencyRating, _idx, () -> issuerAgencyRating.toBuilder());
			return this;
		}
		@Override 
		public IssuerCriteria.IssuerCriteriaBuilder addIssuerAgencyRating(List<? extends AgencyRatingCriteria> issuerAgencyRatings) {
			if (issuerAgencyRatings != null) {
				for (AgencyRatingCriteria toAdd : issuerAgencyRatings) {
					this.issuerAgencyRating.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuerAgencyRating")
		public IssuerCriteria.IssuerCriteriaBuilder setIssuerAgencyRating(List<? extends AgencyRatingCriteria> issuerAgencyRatings) {
			if (issuerAgencyRatings == null)  {
				this.issuerAgencyRating = new ArrayList<>();
			}
			else {
				this.issuerAgencyRating = issuerAgencyRatings.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(AgencyRatingCriteria sovereignAgencyRating) {
			if (sovereignAgencyRating!=null) this.sovereignAgencyRating.add(sovereignAgencyRating.toBuilder());
			return this;
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(AgencyRatingCriteria sovereignAgencyRating, int _idx) {
			getIndex(this.sovereignAgencyRating, _idx, () -> sovereignAgencyRating.toBuilder());
			return this;
		}
		@Override 
		public IssuerCriteria.IssuerCriteriaBuilder addSovereignAgencyRating(List<? extends AgencyRatingCriteria> sovereignAgencyRatings) {
			if (sovereignAgencyRatings != null) {
				for (AgencyRatingCriteria toAdd : sovereignAgencyRatings) {
					this.sovereignAgencyRating.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("sovereignAgencyRating")
		public IssuerCriteria.IssuerCriteriaBuilder setSovereignAgencyRating(List<? extends AgencyRatingCriteria> sovereignAgencyRatings) {
			if (sovereignAgencyRatings == null)  {
				this.sovereignAgencyRating = new ArrayList<>();
			}
			else {
				this.sovereignAgencyRating = sovereignAgencyRatings.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("counterpartyOwnIssuePermitted")
		public IssuerCriteria.IssuerCriteriaBuilder setCounterpartyOwnIssuePermitted(Boolean counterpartyOwnIssuePermitted) {
			this.counterpartyOwnIssuePermitted = counterpartyOwnIssuePermitted==null?null:counterpartyOwnIssuePermitted;
			return this;
		}
		
		@Override
		public IssuerCriteria build() {
			return new IssuerCriteria.IssuerCriteriaImpl(this);
		}
		
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder prune() {
			issuerType = issuerType.stream().filter(b->b!=null).<CollateralIssuerType.CollateralIssuerTypeBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			issuerName = issuerName.stream().filter(b->b!=null).<LegalEntity.LegalEntityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			issuerAgencyRating = issuerAgencyRating.stream().filter(b->b!=null).<AgencyRatingCriteria.AgencyRatingCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			sovereignAgencyRating = sovereignAgencyRating.stream().filter(b->b!=null).<AgencyRatingCriteria.AgencyRatingCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIssuerType()!=null && getIssuerType().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getIssuerCountryOfOrigin()!=null && !getIssuerCountryOfOrigin().isEmpty()) return true;
			if (getIssuerName()!=null && getIssuerName().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getIssuerAgencyRating()!=null && getIssuerAgencyRating().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getSovereignAgencyRating()!=null && getSovereignAgencyRating().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCounterpartyOwnIssuePermitted()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IssuerCriteria.IssuerCriteriaBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			IssuerCriteria.IssuerCriteriaBuilder o = (IssuerCriteria.IssuerCriteriaBuilder) other;
			
			merger.mergeRosetta(getIssuerType(), o.getIssuerType(), this::getOrCreateIssuerType);
			merger.mergeRosetta(getIssuerName(), o.getIssuerName(), this::getOrCreateIssuerName);
			merger.mergeRosetta(getIssuerAgencyRating(), o.getIssuerAgencyRating(), this::getOrCreateIssuerAgencyRating);
			merger.mergeRosetta(getSovereignAgencyRating(), o.getSovereignAgencyRating(), this::getOrCreateSovereignAgencyRating);
			
			merger.mergeBasic(getIssuerCountryOfOrigin(), o.getIssuerCountryOfOrigin(), (Consumer<ISOCountryCodeEnum>) this::addIssuerCountryOfOrigin);
			merger.mergeBasic(getCounterpartyOwnIssuePermitted(), o.getCounterpartyOwnIssuePermitted(), this::setCounterpartyOwnIssuePermitted);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IssuerCriteria _that = getType().cast(o);
		
			if (!ListEquals.listEquals(issuerType, _that.getIssuerType())) return false;
			if (!ListEquals.listEquals(issuerCountryOfOrigin, _that.getIssuerCountryOfOrigin())) return false;
			if (!ListEquals.listEquals(issuerName, _that.getIssuerName())) return false;
			if (!ListEquals.listEquals(issuerAgencyRating, _that.getIssuerAgencyRating())) return false;
			if (!ListEquals.listEquals(sovereignAgencyRating, _that.getSovereignAgencyRating())) return false;
			if (!Objects.equals(counterpartyOwnIssuePermitted, _that.getCounterpartyOwnIssuePermitted())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerType != null ? issuerType.hashCode() : 0);
			_result = 31 * _result + (issuerCountryOfOrigin != null ? issuerCountryOfOrigin.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (issuerName != null ? issuerName.hashCode() : 0);
			_result = 31 * _result + (issuerAgencyRating != null ? issuerAgencyRating.hashCode() : 0);
			_result = 31 * _result + (sovereignAgencyRating != null ? sovereignAgencyRating.hashCode() : 0);
			_result = 31 * _result + (counterpartyOwnIssuePermitted != null ? counterpartyOwnIssuePermitted.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IssuerCriteriaBuilder {" +
				"issuerType=" + this.issuerType + ", " +
				"issuerCountryOfOrigin=" + this.issuerCountryOfOrigin + ", " +
				"issuerName=" + this.issuerName + ", " +
				"issuerAgencyRating=" + this.issuerAgencyRating + ", " +
				"sovereignAgencyRating=" + this.sovereignAgencyRating + ", " +
				"counterpartyOwnIssuePermitted=" + this.counterpartyOwnIssuePermitted +
			'}';
		}
	}
}
