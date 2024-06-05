package cdm.product.collateral;

import cdm.base.datetime.PeriodRange;
import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralTaxonomy;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.asset.common.MaturityTypeEnum;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.AssetCriteria.AssetCriteriaBuilder;
import cdm.product.collateral.AssetCriteria.AssetCriteriaBuilderImpl;
import cdm.product.collateral.AssetCriteria.AssetCriteriaImpl;
import cdm.product.collateral.ListingType;
import cdm.product.collateral.meta.AssetCriteriaMeta;
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
 * Represents a set of criteria used to specify eligible collateral assets.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetCriteria", builder=AssetCriteria.AssetCriteriaBuilderImpl.class, version="${project.version}")
public interface AssetCriteria extends RosettaModelObject {

	AssetCriteriaMeta metaData = new AssetCriteriaMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a filter based on the asset product type.
	 */
	List<? extends AssetType> getCollateralAssetType();
	/**
	 * Represents a filter on the asset country of origin based on the ISO Standard 3166.
	 */
	List<ISOCountryCodeEnum> getAssetCountryOfOrigin();
	/**
	 * Represents a filter on the underlying asset denominated currency based on ISO Standards.
	 */
	List<CurrencyCodeEnum> getDenominatedCurrency();
	/**
	 * Represents an agency rating based on default risk and creditors claim in event of default associated with specific instrument.
	 */
	List<? extends AgencyRatingCriteria> getAgencyRating();
	/**
	 * Specifies whether the maturity range is the remaining or original maturity.
	 */
	MaturityTypeEnum getMaturityType();
	/**
	 * Represents a filter based on the underlying asset maturity.
	 */
	PeriodRange getMaturityRange();
	/**
	 * Represents a filter based on specific instrument identifiers (e.g. specific ISINs, CUSIPs etc).
	 */
	List<? extends ProductIdentifier> getProductIdentifier();
	/**
	 * Specifies the collateral taxonomy,which is composed of a taxonomy value and a taxonomy source.
	 */
	List<? extends CollateralTaxonomy> getCollateralTaxonomy();
	/**
	 * Identifies that the Security must be denominated in the domestic currency of the issuer.
	 */
	Boolean getDomesticCurrencyIssued();
	/**
	 * Specifies the exchange, index or sector specific to listing of a security.
	 */
	ListingType getListing();

	/*********************** Build Methods  ***********************/
	AssetCriteria build();
	
	AssetCriteria.AssetCriteriaBuilder toBuilder();
	
	static AssetCriteria.AssetCriteriaBuilder builder() {
		return new AssetCriteria.AssetCriteriaBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetCriteria> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetCriteria> getType() {
		return AssetCriteria.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("collateralAssetType"), processor, AssetType.class, getCollateralAssetType());
		processor.processBasic(path.newSubPath("assetCountryOfOrigin"), ISOCountryCodeEnum.class, getAssetCountryOfOrigin(), this);
		processor.processBasic(path.newSubPath("denominatedCurrency"), CurrencyCodeEnum.class, getDenominatedCurrency(), this);
		processRosetta(path.newSubPath("agencyRating"), processor, AgencyRatingCriteria.class, getAgencyRating());
		processor.processBasic(path.newSubPath("maturityType"), MaturityTypeEnum.class, getMaturityType(), this);
		processRosetta(path.newSubPath("maturityRange"), processor, PeriodRange.class, getMaturityRange());
		processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.class, getProductIdentifier());
		processRosetta(path.newSubPath("collateralTaxonomy"), processor, CollateralTaxonomy.class, getCollateralTaxonomy());
		processor.processBasic(path.newSubPath("domesticCurrencyIssued"), Boolean.class, getDomesticCurrencyIssued(), this);
		processRosetta(path.newSubPath("listing"), processor, ListingType.class, getListing());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetCriteriaBuilder extends AssetCriteria, RosettaModelObjectBuilder {
		AssetType.AssetTypeBuilder getOrCreateCollateralAssetType(int _index);
		List<? extends AssetType.AssetTypeBuilder> getCollateralAssetType();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateAgencyRating(int _index);
		List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getAgencyRating();
		PeriodRange.PeriodRangeBuilder getOrCreateMaturityRange();
		PeriodRange.PeriodRangeBuilder getMaturityRange();
		ProductIdentifier.ProductIdentifierBuilder getOrCreateProductIdentifier(int _index);
		List<? extends ProductIdentifier.ProductIdentifierBuilder> getProductIdentifier();
		CollateralTaxonomy.CollateralTaxonomyBuilder getOrCreateCollateralTaxonomy(int _index);
		List<? extends CollateralTaxonomy.CollateralTaxonomyBuilder> getCollateralTaxonomy();
		ListingType.ListingTypeBuilder getOrCreateListing();
		ListingType.ListingTypeBuilder getListing();
		AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(AssetType collateralAssetType0);
		AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(AssetType collateralAssetType1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(List<? extends AssetType> collateralAssetType2);
		AssetCriteria.AssetCriteriaBuilder setCollateralAssetType(List<? extends AssetType> collateralAssetType3);
		AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin0);
		AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(List<? extends ISOCountryCodeEnum> assetCountryOfOrigin2);
		AssetCriteria.AssetCriteriaBuilder setAssetCountryOfOrigin(List<? extends ISOCountryCodeEnum> assetCountryOfOrigin3);
		AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(CurrencyCodeEnum denominatedCurrency0);
		AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(CurrencyCodeEnum denominatedCurrency1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(List<? extends CurrencyCodeEnum> denominatedCurrency2);
		AssetCriteria.AssetCriteriaBuilder setDenominatedCurrency(List<? extends CurrencyCodeEnum> denominatedCurrency3);
		AssetCriteria.AssetCriteriaBuilder addAgencyRating(AgencyRatingCriteria agencyRating0);
		AssetCriteria.AssetCriteriaBuilder addAgencyRating(AgencyRatingCriteria agencyRating1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addAgencyRating(List<? extends AgencyRatingCriteria> agencyRating2);
		AssetCriteria.AssetCriteriaBuilder setAgencyRating(List<? extends AgencyRatingCriteria> agencyRating3);
		AssetCriteria.AssetCriteriaBuilder setMaturityType(MaturityTypeEnum maturityType);
		AssetCriteria.AssetCriteriaBuilder setMaturityRange(PeriodRange maturityRange);
		AssetCriteria.AssetCriteriaBuilder addProductIdentifier(ProductIdentifier productIdentifier0);
		AssetCriteria.AssetCriteriaBuilder addProductIdentifier(ProductIdentifier productIdentifier1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addProductIdentifier(List<? extends ProductIdentifier> productIdentifier2);
		AssetCriteria.AssetCriteriaBuilder setProductIdentifier(List<? extends ProductIdentifier> productIdentifier3);
		AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(CollateralTaxonomy collateralTaxonomy0);
		AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(CollateralTaxonomy collateralTaxonomy1, int _idx);
		AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(List<? extends CollateralTaxonomy> collateralTaxonomy2);
		AssetCriteria.AssetCriteriaBuilder setCollateralTaxonomy(List<? extends CollateralTaxonomy> collateralTaxonomy3);
		AssetCriteria.AssetCriteriaBuilder setDomesticCurrencyIssued(Boolean domesticCurrencyIssued);
		AssetCriteria.AssetCriteriaBuilder setListing(ListingType listing);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("collateralAssetType"), processor, AssetType.AssetTypeBuilder.class, getCollateralAssetType());
			processor.processBasic(path.newSubPath("assetCountryOfOrigin"), ISOCountryCodeEnum.class, getAssetCountryOfOrigin(), this);
			processor.processBasic(path.newSubPath("denominatedCurrency"), CurrencyCodeEnum.class, getDenominatedCurrency(), this);
			processRosetta(path.newSubPath("agencyRating"), processor, AgencyRatingCriteria.AgencyRatingCriteriaBuilder.class, getAgencyRating());
			processor.processBasic(path.newSubPath("maturityType"), MaturityTypeEnum.class, getMaturityType(), this);
			processRosetta(path.newSubPath("maturityRange"), processor, PeriodRange.PeriodRangeBuilder.class, getMaturityRange());
			processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getProductIdentifier());
			processRosetta(path.newSubPath("collateralTaxonomy"), processor, CollateralTaxonomy.CollateralTaxonomyBuilder.class, getCollateralTaxonomy());
			processor.processBasic(path.newSubPath("domesticCurrencyIssued"), Boolean.class, getDomesticCurrencyIssued(), this);
			processRosetta(path.newSubPath("listing"), processor, ListingType.ListingTypeBuilder.class, getListing());
		}
		

		AssetCriteria.AssetCriteriaBuilder prune();
	}

	/*********************** Immutable Implementation of AssetCriteria  ***********************/
	class AssetCriteriaImpl implements AssetCriteria {
		private final List<? extends AssetType> collateralAssetType;
		private final List<ISOCountryCodeEnum> assetCountryOfOrigin;
		private final List<CurrencyCodeEnum> denominatedCurrency;
		private final List<? extends AgencyRatingCriteria> agencyRating;
		private final MaturityTypeEnum maturityType;
		private final PeriodRange maturityRange;
		private final List<? extends ProductIdentifier> productIdentifier;
		private final List<? extends CollateralTaxonomy> collateralTaxonomy;
		private final Boolean domesticCurrencyIssued;
		private final ListingType listing;
		
		protected AssetCriteriaImpl(AssetCriteria.AssetCriteriaBuilder builder) {
			this.collateralAssetType = ofNullable(builder.getCollateralAssetType()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.assetCountryOfOrigin = ofNullable(builder.getAssetCountryOfOrigin()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.denominatedCurrency = ofNullable(builder.getDenominatedCurrency()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.agencyRating = ofNullable(builder.getAgencyRating()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.maturityType = builder.getMaturityType();
			this.maturityRange = ofNullable(builder.getMaturityRange()).map(f->f.build()).orElse(null);
			this.productIdentifier = ofNullable(builder.getProductIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.collateralTaxonomy = ofNullable(builder.getCollateralTaxonomy()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.domesticCurrencyIssued = builder.getDomesticCurrencyIssued();
			this.listing = ofNullable(builder.getListing()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("collateralAssetType")
		public List<? extends AssetType> getCollateralAssetType() {
			return collateralAssetType;
		}
		
		@Override
		@RosettaAttribute("assetCountryOfOrigin")
		public List<ISOCountryCodeEnum> getAssetCountryOfOrigin() {
			return assetCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("denominatedCurrency")
		public List<CurrencyCodeEnum> getDenominatedCurrency() {
			return denominatedCurrency;
		}
		
		@Override
		@RosettaAttribute("agencyRating")
		public List<? extends AgencyRatingCriteria> getAgencyRating() {
			return agencyRating;
		}
		
		@Override
		@RosettaAttribute("maturityType")
		public MaturityTypeEnum getMaturityType() {
			return maturityType;
		}
		
		@Override
		@RosettaAttribute("maturityRange")
		public PeriodRange getMaturityRange() {
			return maturityRange;
		}
		
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends ProductIdentifier> getProductIdentifier() {
			return productIdentifier;
		}
		
		@Override
		@RosettaAttribute("collateralTaxonomy")
		public List<? extends CollateralTaxonomy> getCollateralTaxonomy() {
			return collateralTaxonomy;
		}
		
		@Override
		@RosettaAttribute("domesticCurrencyIssued")
		public Boolean getDomesticCurrencyIssued() {
			return domesticCurrencyIssued;
		}
		
		@Override
		@RosettaAttribute("listing")
		public ListingType getListing() {
			return listing;
		}
		
		@Override
		public AssetCriteria build() {
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder toBuilder() {
			AssetCriteria.AssetCriteriaBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetCriteria.AssetCriteriaBuilder builder) {
			ofNullable(getCollateralAssetType()).ifPresent(builder::setCollateralAssetType);
			ofNullable(getAssetCountryOfOrigin()).ifPresent(builder::setAssetCountryOfOrigin);
			ofNullable(getDenominatedCurrency()).ifPresent(builder::setDenominatedCurrency);
			ofNullable(getAgencyRating()).ifPresent(builder::setAgencyRating);
			ofNullable(getMaturityType()).ifPresent(builder::setMaturityType);
			ofNullable(getMaturityRange()).ifPresent(builder::setMaturityRange);
			ofNullable(getProductIdentifier()).ifPresent(builder::setProductIdentifier);
			ofNullable(getCollateralTaxonomy()).ifPresent(builder::setCollateralTaxonomy);
			ofNullable(getDomesticCurrencyIssued()).ifPresent(builder::setDomesticCurrencyIssued);
			ofNullable(getListing()).ifPresent(builder::setListing);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetCriteria _that = getType().cast(o);
		
			if (!ListEquals.listEquals(collateralAssetType, _that.getCollateralAssetType())) return false;
			if (!ListEquals.listEquals(assetCountryOfOrigin, _that.getAssetCountryOfOrigin())) return false;
			if (!ListEquals.listEquals(denominatedCurrency, _that.getDenominatedCurrency())) return false;
			if (!ListEquals.listEquals(agencyRating, _that.getAgencyRating())) return false;
			if (!Objects.equals(maturityType, _that.getMaturityType())) return false;
			if (!Objects.equals(maturityRange, _that.getMaturityRange())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			if (!ListEquals.listEquals(collateralTaxonomy, _that.getCollateralTaxonomy())) return false;
			if (!Objects.equals(domesticCurrencyIssued, _that.getDomesticCurrencyIssued())) return false;
			if (!Objects.equals(listing, _that.getListing())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralAssetType != null ? collateralAssetType.hashCode() : 0);
			_result = 31 * _result + (assetCountryOfOrigin != null ? assetCountryOfOrigin.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (denominatedCurrency != null ? denominatedCurrency.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (agencyRating != null ? agencyRating.hashCode() : 0);
			_result = 31 * _result + (maturityType != null ? maturityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (maturityRange != null ? maturityRange.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralTaxonomy != null ? collateralTaxonomy.hashCode() : 0);
			_result = 31 * _result + (domesticCurrencyIssued != null ? domesticCurrencyIssued.hashCode() : 0);
			_result = 31 * _result + (listing != null ? listing.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetCriteria {" +
				"collateralAssetType=" + this.collateralAssetType + ", " +
				"assetCountryOfOrigin=" + this.assetCountryOfOrigin + ", " +
				"denominatedCurrency=" + this.denominatedCurrency + ", " +
				"agencyRating=" + this.agencyRating + ", " +
				"maturityType=" + this.maturityType + ", " +
				"maturityRange=" + this.maturityRange + ", " +
				"productIdentifier=" + this.productIdentifier + ", " +
				"collateralTaxonomy=" + this.collateralTaxonomy + ", " +
				"domesticCurrencyIssued=" + this.domesticCurrencyIssued + ", " +
				"listing=" + this.listing +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetCriteria  ***********************/
	class AssetCriteriaBuilderImpl implements AssetCriteria.AssetCriteriaBuilder {
	
		protected List<AssetType.AssetTypeBuilder> collateralAssetType = new ArrayList<>();
		protected List<ISOCountryCodeEnum> assetCountryOfOrigin = new ArrayList<>();
		protected List<CurrencyCodeEnum> denominatedCurrency = new ArrayList<>();
		protected List<AgencyRatingCriteria.AgencyRatingCriteriaBuilder> agencyRating = new ArrayList<>();
		protected MaturityTypeEnum maturityType;
		protected PeriodRange.PeriodRangeBuilder maturityRange;
		protected List<ProductIdentifier.ProductIdentifierBuilder> productIdentifier = new ArrayList<>();
		protected List<CollateralTaxonomy.CollateralTaxonomyBuilder> collateralTaxonomy = new ArrayList<>();
		protected Boolean domesticCurrencyIssued;
		protected ListingType.ListingTypeBuilder listing;
	
		public AssetCriteriaBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("collateralAssetType")
		public List<? extends AssetType.AssetTypeBuilder> getCollateralAssetType() {
			return collateralAssetType;
		}
		
		public AssetType.AssetTypeBuilder getOrCreateCollateralAssetType(int _index) {
		
			if (collateralAssetType==null) {
				this.collateralAssetType = new ArrayList<>();
			}
			AssetType.AssetTypeBuilder result;
			return getIndex(collateralAssetType, _index, () -> {
						AssetType.AssetTypeBuilder newCollateralAssetType = AssetType.builder();
						return newCollateralAssetType;
					});
		}
		
		@Override
		@RosettaAttribute("assetCountryOfOrigin")
		public List<ISOCountryCodeEnum> getAssetCountryOfOrigin() {
			return assetCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("denominatedCurrency")
		public List<CurrencyCodeEnum> getDenominatedCurrency() {
			return denominatedCurrency;
		}
		
		@Override
		@RosettaAttribute("agencyRating")
		public List<? extends AgencyRatingCriteria.AgencyRatingCriteriaBuilder> getAgencyRating() {
			return agencyRating;
		}
		
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateAgencyRating(int _index) {
		
			if (agencyRating==null) {
				this.agencyRating = new ArrayList<>();
			}
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder result;
			return getIndex(agencyRating, _index, () -> {
						AgencyRatingCriteria.AgencyRatingCriteriaBuilder newAgencyRating = AgencyRatingCriteria.builder();
						return newAgencyRating;
					});
		}
		
		@Override
		@RosettaAttribute("maturityType")
		public MaturityTypeEnum getMaturityType() {
			return maturityType;
		}
		
		@Override
		@RosettaAttribute("maturityRange")
		public PeriodRange.PeriodRangeBuilder getMaturityRange() {
			return maturityRange;
		}
		
		@Override
		public PeriodRange.PeriodRangeBuilder getOrCreateMaturityRange() {
			PeriodRange.PeriodRangeBuilder result;
			if (maturityRange!=null) {
				result = maturityRange;
			}
			else {
				result = maturityRange = PeriodRange.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends ProductIdentifier.ProductIdentifierBuilder> getProductIdentifier() {
			return productIdentifier;
		}
		
		public ProductIdentifier.ProductIdentifierBuilder getOrCreateProductIdentifier(int _index) {
		
			if (productIdentifier==null) {
				this.productIdentifier = new ArrayList<>();
			}
			ProductIdentifier.ProductIdentifierBuilder result;
			return getIndex(productIdentifier, _index, () -> {
						ProductIdentifier.ProductIdentifierBuilder newProductIdentifier = ProductIdentifier.builder();
						return newProductIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("collateralTaxonomy")
		public List<? extends CollateralTaxonomy.CollateralTaxonomyBuilder> getCollateralTaxonomy() {
			return collateralTaxonomy;
		}
		
		public CollateralTaxonomy.CollateralTaxonomyBuilder getOrCreateCollateralTaxonomy(int _index) {
		
			if (collateralTaxonomy==null) {
				this.collateralTaxonomy = new ArrayList<>();
			}
			CollateralTaxonomy.CollateralTaxonomyBuilder result;
			return getIndex(collateralTaxonomy, _index, () -> {
						CollateralTaxonomy.CollateralTaxonomyBuilder newCollateralTaxonomy = CollateralTaxonomy.builder();
						return newCollateralTaxonomy;
					});
		}
		
		@Override
		@RosettaAttribute("domesticCurrencyIssued")
		public Boolean getDomesticCurrencyIssued() {
			return domesticCurrencyIssued;
		}
		
		@Override
		@RosettaAttribute("listing")
		public ListingType.ListingTypeBuilder getListing() {
			return listing;
		}
		
		@Override
		public ListingType.ListingTypeBuilder getOrCreateListing() {
			ListingType.ListingTypeBuilder result;
			if (listing!=null) {
				result = listing;
			}
			else {
				result = listing = ListingType.builder();
			}
			
			return result;
		}
	
		@Override
		public AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(AssetType collateralAssetType) {
			if (collateralAssetType!=null) this.collateralAssetType.add(collateralAssetType.toBuilder());
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(AssetType collateralAssetType, int _idx) {
			getIndex(this.collateralAssetType, _idx, () -> collateralAssetType.toBuilder());
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addCollateralAssetType(List<? extends AssetType> collateralAssetTypes) {
			if (collateralAssetTypes != null) {
				for (AssetType toAdd : collateralAssetTypes) {
					this.collateralAssetType.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralAssetType")
		public AssetCriteria.AssetCriteriaBuilder setCollateralAssetType(List<? extends AssetType> collateralAssetTypes) {
			if (collateralAssetTypes == null)  {
				this.collateralAssetType = new ArrayList<>();
			}
			else {
				this.collateralAssetType = collateralAssetTypes.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin) {
			if (assetCountryOfOrigin!=null) this.assetCountryOfOrigin.add(assetCountryOfOrigin);
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin, int _idx) {
			getIndex(this.assetCountryOfOrigin, _idx, () -> assetCountryOfOrigin);
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addAssetCountryOfOrigin(List<? extends ISOCountryCodeEnum> assetCountryOfOrigins) {
			if (assetCountryOfOrigins != null) {
				for (ISOCountryCodeEnum toAdd : assetCountryOfOrigins) {
					this.assetCountryOfOrigin.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("assetCountryOfOrigin")
		public AssetCriteria.AssetCriteriaBuilder setAssetCountryOfOrigin(List<? extends ISOCountryCodeEnum> assetCountryOfOrigins) {
			if (assetCountryOfOrigins == null)  {
				this.assetCountryOfOrigin = new ArrayList<>();
			}
			else {
				this.assetCountryOfOrigin = assetCountryOfOrigins.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(CurrencyCodeEnum denominatedCurrency) {
			if (denominatedCurrency!=null) this.denominatedCurrency.add(denominatedCurrency);
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(CurrencyCodeEnum denominatedCurrency, int _idx) {
			getIndex(this.denominatedCurrency, _idx, () -> denominatedCurrency);
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addDenominatedCurrency(List<? extends CurrencyCodeEnum> denominatedCurrencys) {
			if (denominatedCurrencys != null) {
				for (CurrencyCodeEnum toAdd : denominatedCurrencys) {
					this.denominatedCurrency.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("denominatedCurrency")
		public AssetCriteria.AssetCriteriaBuilder setDenominatedCurrency(List<? extends CurrencyCodeEnum> denominatedCurrencys) {
			if (denominatedCurrencys == null)  {
				this.denominatedCurrency = new ArrayList<>();
			}
			else {
				this.denominatedCurrency = denominatedCurrencys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addAgencyRating(AgencyRatingCriteria agencyRating) {
			if (agencyRating!=null) this.agencyRating.add(agencyRating.toBuilder());
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addAgencyRating(AgencyRatingCriteria agencyRating, int _idx) {
			getIndex(this.agencyRating, _idx, () -> agencyRating.toBuilder());
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addAgencyRating(List<? extends AgencyRatingCriteria> agencyRatings) {
			if (agencyRatings != null) {
				for (AgencyRatingCriteria toAdd : agencyRatings) {
					this.agencyRating.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("agencyRating")
		public AssetCriteria.AssetCriteriaBuilder setAgencyRating(List<? extends AgencyRatingCriteria> agencyRatings) {
			if (agencyRatings == null)  {
				this.agencyRating = new ArrayList<>();
			}
			else {
				this.agencyRating = agencyRatings.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("maturityType")
		public AssetCriteria.AssetCriteriaBuilder setMaturityType(MaturityTypeEnum maturityType) {
			this.maturityType = maturityType==null?null:maturityType;
			return this;
		}
		@Override
		@RosettaAttribute("maturityRange")
		public AssetCriteria.AssetCriteriaBuilder setMaturityRange(PeriodRange maturityRange) {
			this.maturityRange = maturityRange==null?null:maturityRange.toBuilder();
			return this;
		}
		@Override
		public AssetCriteria.AssetCriteriaBuilder addProductIdentifier(ProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addProductIdentifier(ProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addProductIdentifier(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public AssetCriteria.AssetCriteriaBuilder setProductIdentifier(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers == null)  {
				this.productIdentifier = new ArrayList<>();
			}
			else {
				this.productIdentifier = productIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(CollateralTaxonomy collateralTaxonomy) {
			if (collateralTaxonomy!=null) this.collateralTaxonomy.add(collateralTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(CollateralTaxonomy collateralTaxonomy, int _idx) {
			getIndex(this.collateralTaxonomy, _idx, () -> collateralTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public AssetCriteria.AssetCriteriaBuilder addCollateralTaxonomy(List<? extends CollateralTaxonomy> collateralTaxonomys) {
			if (collateralTaxonomys != null) {
				for (CollateralTaxonomy toAdd : collateralTaxonomys) {
					this.collateralTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralTaxonomy")
		public AssetCriteria.AssetCriteriaBuilder setCollateralTaxonomy(List<? extends CollateralTaxonomy> collateralTaxonomys) {
			if (collateralTaxonomys == null)  {
				this.collateralTaxonomy = new ArrayList<>();
			}
			else {
				this.collateralTaxonomy = collateralTaxonomys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("domesticCurrencyIssued")
		public AssetCriteria.AssetCriteriaBuilder setDomesticCurrencyIssued(Boolean domesticCurrencyIssued) {
			this.domesticCurrencyIssued = domesticCurrencyIssued==null?null:domesticCurrencyIssued;
			return this;
		}
		@Override
		@RosettaAttribute("listing")
		public AssetCriteria.AssetCriteriaBuilder setListing(ListingType listing) {
			this.listing = listing==null?null:listing.toBuilder();
			return this;
		}
		
		@Override
		public AssetCriteria build() {
			return new AssetCriteria.AssetCriteriaImpl(this);
		}
		
		@Override
		public AssetCriteria.AssetCriteriaBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetCriteria.AssetCriteriaBuilder prune() {
			collateralAssetType = collateralAssetType.stream().filter(b->b!=null).<AssetType.AssetTypeBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			agencyRating = agencyRating.stream().filter(b->b!=null).<AgencyRatingCriteria.AgencyRatingCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (maturityRange!=null && !maturityRange.prune().hasData()) maturityRange = null;
			productIdentifier = productIdentifier.stream().filter(b->b!=null).<ProductIdentifier.ProductIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			collateralTaxonomy = collateralTaxonomy.stream().filter(b->b!=null).<CollateralTaxonomy.CollateralTaxonomyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (listing!=null && !listing.prune().hasData()) listing = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCollateralAssetType()!=null && getCollateralAssetType().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAssetCountryOfOrigin()!=null && !getAssetCountryOfOrigin().isEmpty()) return true;
			if (getDenominatedCurrency()!=null && !getDenominatedCurrency().isEmpty()) return true;
			if (getAgencyRating()!=null && getAgencyRating().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMaturityType()!=null) return true;
			if (getMaturityRange()!=null && getMaturityRange().hasData()) return true;
			if (getProductIdentifier()!=null && getProductIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCollateralTaxonomy()!=null && getCollateralTaxonomy().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getDomesticCurrencyIssued()!=null) return true;
			if (getListing()!=null && getListing().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetCriteria.AssetCriteriaBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetCriteria.AssetCriteriaBuilder o = (AssetCriteria.AssetCriteriaBuilder) other;
			
			merger.mergeRosetta(getCollateralAssetType(), o.getCollateralAssetType(), this::getOrCreateCollateralAssetType);
			merger.mergeRosetta(getAgencyRating(), o.getAgencyRating(), this::getOrCreateAgencyRating);
			merger.mergeRosetta(getMaturityRange(), o.getMaturityRange(), this::setMaturityRange);
			merger.mergeRosetta(getProductIdentifier(), o.getProductIdentifier(), this::getOrCreateProductIdentifier);
			merger.mergeRosetta(getCollateralTaxonomy(), o.getCollateralTaxonomy(), this::getOrCreateCollateralTaxonomy);
			merger.mergeRosetta(getListing(), o.getListing(), this::setListing);
			
			merger.mergeBasic(getAssetCountryOfOrigin(), o.getAssetCountryOfOrigin(), (Consumer<ISOCountryCodeEnum>) this::addAssetCountryOfOrigin);
			merger.mergeBasic(getDenominatedCurrency(), o.getDenominatedCurrency(), (Consumer<CurrencyCodeEnum>) this::addDenominatedCurrency);
			merger.mergeBasic(getMaturityType(), o.getMaturityType(), this::setMaturityType);
			merger.mergeBasic(getDomesticCurrencyIssued(), o.getDomesticCurrencyIssued(), this::setDomesticCurrencyIssued);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetCriteria _that = getType().cast(o);
		
			if (!ListEquals.listEquals(collateralAssetType, _that.getCollateralAssetType())) return false;
			if (!ListEquals.listEquals(assetCountryOfOrigin, _that.getAssetCountryOfOrigin())) return false;
			if (!ListEquals.listEquals(denominatedCurrency, _that.getDenominatedCurrency())) return false;
			if (!ListEquals.listEquals(agencyRating, _that.getAgencyRating())) return false;
			if (!Objects.equals(maturityType, _that.getMaturityType())) return false;
			if (!Objects.equals(maturityRange, _that.getMaturityRange())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			if (!ListEquals.listEquals(collateralTaxonomy, _that.getCollateralTaxonomy())) return false;
			if (!Objects.equals(domesticCurrencyIssued, _that.getDomesticCurrencyIssued())) return false;
			if (!Objects.equals(listing, _that.getListing())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralAssetType != null ? collateralAssetType.hashCode() : 0);
			_result = 31 * _result + (assetCountryOfOrigin != null ? assetCountryOfOrigin.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (denominatedCurrency != null ? denominatedCurrency.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (agencyRating != null ? agencyRating.hashCode() : 0);
			_result = 31 * _result + (maturityType != null ? maturityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (maturityRange != null ? maturityRange.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralTaxonomy != null ? collateralTaxonomy.hashCode() : 0);
			_result = 31 * _result + (domesticCurrencyIssued != null ? domesticCurrencyIssued.hashCode() : 0);
			_result = 31 * _result + (listing != null ? listing.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetCriteriaBuilder {" +
				"collateralAssetType=" + this.collateralAssetType + ", " +
				"assetCountryOfOrigin=" + this.assetCountryOfOrigin + ", " +
				"denominatedCurrency=" + this.denominatedCurrency + ", " +
				"agencyRating=" + this.agencyRating + ", " +
				"maturityType=" + this.maturityType + ", " +
				"maturityRange=" + this.maturityRange + ", " +
				"productIdentifier=" + this.productIdentifier + ", " +
				"collateralTaxonomy=" + this.collateralTaxonomy + ", " +
				"domesticCurrencyIssued=" + this.domesticCurrencyIssued + ", " +
				"listing=" + this.listing +
			'}';
		}
	}
}
