package cdm.product.collateral;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.EligibilityQuery;
import cdm.product.collateral.EligibilityQuery.EligibilityQueryBuilder;
import cdm.product.collateral.EligibilityQuery.EligibilityQueryBuilderImpl;
import cdm.product.collateral.EligibilityQuery.EligibilityQueryImpl;
import cdm.product.collateral.meta.EligibilityQueryMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Query to check against an EligibleCollateralSpecification
 * @version ${project.version}
 */
@RosettaDataType(value="EligibilityQuery", builder=EligibilityQuery.EligibilityQueryBuilderImpl.class, version="${project.version}")
public interface EligibilityQuery extends RosettaModelObject {

	EligibilityQueryMeta metaData = new EligibilityQueryMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Maturity in years
	 */
	BigDecimal getMaturity();
	/**
	 * The asset product type.
	 */
	AssetType getCollateralAssetType();
	/**
	 * The asset country of origin.
	 */
	ISOCountryCodeEnum getAssetCountryOfOrigin();
	/**
	 * The underlying asset denominated currency.
	 */
	CurrencyCodeEnum getDenominatedCurrency();
	/**
	 * The agency rating based on default risk and creditors claim in event of default associated with specific instrument.
	 */
	AgencyRatingCriteria getAgencyRating();
	/**
	 * Represents a filter based on the type of entity issuing the asset.
	 */
	CollateralIssuerType getIssuerType();
	/**
	 * Specifies the issuing entity name or LEI.
	 */
	LegalEntity getIssuerName();

	/*********************** Build Methods  ***********************/
	EligibilityQuery build();
	
	EligibilityQuery.EligibilityQueryBuilder toBuilder();
	
	static EligibilityQuery.EligibilityQueryBuilder builder() {
		return new EligibilityQuery.EligibilityQueryBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EligibilityQuery> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EligibilityQuery> getType() {
		return EligibilityQuery.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("maturity"), BigDecimal.class, getMaturity(), this);
		processRosetta(path.newSubPath("collateralAssetType"), processor, AssetType.class, getCollateralAssetType());
		processor.processBasic(path.newSubPath("assetCountryOfOrigin"), ISOCountryCodeEnum.class, getAssetCountryOfOrigin(), this);
		processor.processBasic(path.newSubPath("denominatedCurrency"), CurrencyCodeEnum.class, getDenominatedCurrency(), this);
		processRosetta(path.newSubPath("agencyRating"), processor, AgencyRatingCriteria.class, getAgencyRating());
		processRosetta(path.newSubPath("issuerType"), processor, CollateralIssuerType.class, getIssuerType());
		processRosetta(path.newSubPath("issuerName"), processor, LegalEntity.class, getIssuerName());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EligibilityQueryBuilder extends EligibilityQuery, RosettaModelObjectBuilder {
		AssetType.AssetTypeBuilder getOrCreateCollateralAssetType();
		AssetType.AssetTypeBuilder getCollateralAssetType();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateAgencyRating();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder getAgencyRating();
		CollateralIssuerType.CollateralIssuerTypeBuilder getOrCreateIssuerType();
		CollateralIssuerType.CollateralIssuerTypeBuilder getIssuerType();
		LegalEntity.LegalEntityBuilder getOrCreateIssuerName();
		LegalEntity.LegalEntityBuilder getIssuerName();
		EligibilityQuery.EligibilityQueryBuilder setMaturity(BigDecimal maturity);
		EligibilityQuery.EligibilityQueryBuilder setCollateralAssetType(AssetType collateralAssetType);
		EligibilityQuery.EligibilityQueryBuilder setAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin);
		EligibilityQuery.EligibilityQueryBuilder setDenominatedCurrency(CurrencyCodeEnum denominatedCurrency);
		EligibilityQuery.EligibilityQueryBuilder setAgencyRating(AgencyRatingCriteria agencyRating);
		EligibilityQuery.EligibilityQueryBuilder setIssuerType(CollateralIssuerType issuerType);
		EligibilityQuery.EligibilityQueryBuilder setIssuerName(LegalEntity issuerName);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("maturity"), BigDecimal.class, getMaturity(), this);
			processRosetta(path.newSubPath("collateralAssetType"), processor, AssetType.AssetTypeBuilder.class, getCollateralAssetType());
			processor.processBasic(path.newSubPath("assetCountryOfOrigin"), ISOCountryCodeEnum.class, getAssetCountryOfOrigin(), this);
			processor.processBasic(path.newSubPath("denominatedCurrency"), CurrencyCodeEnum.class, getDenominatedCurrency(), this);
			processRosetta(path.newSubPath("agencyRating"), processor, AgencyRatingCriteria.AgencyRatingCriteriaBuilder.class, getAgencyRating());
			processRosetta(path.newSubPath("issuerType"), processor, CollateralIssuerType.CollateralIssuerTypeBuilder.class, getIssuerType());
			processRosetta(path.newSubPath("issuerName"), processor, LegalEntity.LegalEntityBuilder.class, getIssuerName());
		}
		

		EligibilityQuery.EligibilityQueryBuilder prune();
	}

	/*********************** Immutable Implementation of EligibilityQuery  ***********************/
	class EligibilityQueryImpl implements EligibilityQuery {
		private final BigDecimal maturity;
		private final AssetType collateralAssetType;
		private final ISOCountryCodeEnum assetCountryOfOrigin;
		private final CurrencyCodeEnum denominatedCurrency;
		private final AgencyRatingCriteria agencyRating;
		private final CollateralIssuerType issuerType;
		private final LegalEntity issuerName;
		
		protected EligibilityQueryImpl(EligibilityQuery.EligibilityQueryBuilder builder) {
			this.maturity = builder.getMaturity();
			this.collateralAssetType = ofNullable(builder.getCollateralAssetType()).map(f->f.build()).orElse(null);
			this.assetCountryOfOrigin = builder.getAssetCountryOfOrigin();
			this.denominatedCurrency = builder.getDenominatedCurrency();
			this.agencyRating = ofNullable(builder.getAgencyRating()).map(f->f.build()).orElse(null);
			this.issuerType = ofNullable(builder.getIssuerType()).map(f->f.build()).orElse(null);
			this.issuerName = ofNullable(builder.getIssuerName()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("maturity")
		public BigDecimal getMaturity() {
			return maturity;
		}
		
		@Override
		@RosettaAttribute("collateralAssetType")
		public AssetType getCollateralAssetType() {
			return collateralAssetType;
		}
		
		@Override
		@RosettaAttribute("assetCountryOfOrigin")
		public ISOCountryCodeEnum getAssetCountryOfOrigin() {
			return assetCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("denominatedCurrency")
		public CurrencyCodeEnum getDenominatedCurrency() {
			return denominatedCurrency;
		}
		
		@Override
		@RosettaAttribute("agencyRating")
		public AgencyRatingCriteria getAgencyRating() {
			return agencyRating;
		}
		
		@Override
		@RosettaAttribute("issuerType")
		public CollateralIssuerType getIssuerType() {
			return issuerType;
		}
		
		@Override
		@RosettaAttribute("issuerName")
		public LegalEntity getIssuerName() {
			return issuerName;
		}
		
		@Override
		public EligibilityQuery build() {
			return this;
		}
		
		@Override
		public EligibilityQuery.EligibilityQueryBuilder toBuilder() {
			EligibilityQuery.EligibilityQueryBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EligibilityQuery.EligibilityQueryBuilder builder) {
			ofNullable(getMaturity()).ifPresent(builder::setMaturity);
			ofNullable(getCollateralAssetType()).ifPresent(builder::setCollateralAssetType);
			ofNullable(getAssetCountryOfOrigin()).ifPresent(builder::setAssetCountryOfOrigin);
			ofNullable(getDenominatedCurrency()).ifPresent(builder::setDenominatedCurrency);
			ofNullable(getAgencyRating()).ifPresent(builder::setAgencyRating);
			ofNullable(getIssuerType()).ifPresent(builder::setIssuerType);
			ofNullable(getIssuerName()).ifPresent(builder::setIssuerName);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EligibilityQuery _that = getType().cast(o);
		
			if (!Objects.equals(maturity, _that.getMaturity())) return false;
			if (!Objects.equals(collateralAssetType, _that.getCollateralAssetType())) return false;
			if (!Objects.equals(assetCountryOfOrigin, _that.getAssetCountryOfOrigin())) return false;
			if (!Objects.equals(denominatedCurrency, _that.getDenominatedCurrency())) return false;
			if (!Objects.equals(agencyRating, _that.getAgencyRating())) return false;
			if (!Objects.equals(issuerType, _that.getIssuerType())) return false;
			if (!Objects.equals(issuerName, _that.getIssuerName())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (maturity != null ? maturity.hashCode() : 0);
			_result = 31 * _result + (collateralAssetType != null ? collateralAssetType.hashCode() : 0);
			_result = 31 * _result + (assetCountryOfOrigin != null ? assetCountryOfOrigin.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (denominatedCurrency != null ? denominatedCurrency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agencyRating != null ? agencyRating.hashCode() : 0);
			_result = 31 * _result + (issuerType != null ? issuerType.hashCode() : 0);
			_result = 31 * _result + (issuerName != null ? issuerName.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibilityQuery {" +
				"maturity=" + this.maturity + ", " +
				"collateralAssetType=" + this.collateralAssetType + ", " +
				"assetCountryOfOrigin=" + this.assetCountryOfOrigin + ", " +
				"denominatedCurrency=" + this.denominatedCurrency + ", " +
				"agencyRating=" + this.agencyRating + ", " +
				"issuerType=" + this.issuerType + ", " +
				"issuerName=" + this.issuerName +
			'}';
		}
	}

	/*********************** Builder Implementation of EligibilityQuery  ***********************/
	class EligibilityQueryBuilderImpl implements EligibilityQuery.EligibilityQueryBuilder {
	
		protected BigDecimal maturity;
		protected AssetType.AssetTypeBuilder collateralAssetType;
		protected ISOCountryCodeEnum assetCountryOfOrigin;
		protected CurrencyCodeEnum denominatedCurrency;
		protected AgencyRatingCriteria.AgencyRatingCriteriaBuilder agencyRating;
		protected CollateralIssuerType.CollateralIssuerTypeBuilder issuerType;
		protected LegalEntity.LegalEntityBuilder issuerName;
	
		public EligibilityQueryBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("maturity")
		public BigDecimal getMaturity() {
			return maturity;
		}
		
		@Override
		@RosettaAttribute("collateralAssetType")
		public AssetType.AssetTypeBuilder getCollateralAssetType() {
			return collateralAssetType;
		}
		
		@Override
		public AssetType.AssetTypeBuilder getOrCreateCollateralAssetType() {
			AssetType.AssetTypeBuilder result;
			if (collateralAssetType!=null) {
				result = collateralAssetType;
			}
			else {
				result = collateralAssetType = AssetType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("assetCountryOfOrigin")
		public ISOCountryCodeEnum getAssetCountryOfOrigin() {
			return assetCountryOfOrigin;
		}
		
		@Override
		@RosettaAttribute("denominatedCurrency")
		public CurrencyCodeEnum getDenominatedCurrency() {
			return denominatedCurrency;
		}
		
		@Override
		@RosettaAttribute("agencyRating")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder getAgencyRating() {
			return agencyRating;
		}
		
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder getOrCreateAgencyRating() {
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder result;
			if (agencyRating!=null) {
				result = agencyRating;
			}
			else {
				result = agencyRating = AgencyRatingCriteria.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("issuerType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder getIssuerType() {
			return issuerType;
		}
		
		@Override
		public CollateralIssuerType.CollateralIssuerTypeBuilder getOrCreateIssuerType() {
			CollateralIssuerType.CollateralIssuerTypeBuilder result;
			if (issuerType!=null) {
				result = issuerType;
			}
			else {
				result = issuerType = CollateralIssuerType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("issuerName")
		public LegalEntity.LegalEntityBuilder getIssuerName() {
			return issuerName;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder getOrCreateIssuerName() {
			LegalEntity.LegalEntityBuilder result;
			if (issuerName!=null) {
				result = issuerName;
			}
			else {
				result = issuerName = LegalEntity.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("maturity")
		public EligibilityQuery.EligibilityQueryBuilder setMaturity(BigDecimal maturity) {
			this.maturity = maturity==null?null:maturity;
			return this;
		}
		@Override
		@RosettaAttribute("collateralAssetType")
		public EligibilityQuery.EligibilityQueryBuilder setCollateralAssetType(AssetType collateralAssetType) {
			this.collateralAssetType = collateralAssetType==null?null:collateralAssetType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("assetCountryOfOrigin")
		public EligibilityQuery.EligibilityQueryBuilder setAssetCountryOfOrigin(ISOCountryCodeEnum assetCountryOfOrigin) {
			this.assetCountryOfOrigin = assetCountryOfOrigin==null?null:assetCountryOfOrigin;
			return this;
		}
		@Override
		@RosettaAttribute("denominatedCurrency")
		public EligibilityQuery.EligibilityQueryBuilder setDenominatedCurrency(CurrencyCodeEnum denominatedCurrency) {
			this.denominatedCurrency = denominatedCurrency==null?null:denominatedCurrency;
			return this;
		}
		@Override
		@RosettaAttribute("agencyRating")
		public EligibilityQuery.EligibilityQueryBuilder setAgencyRating(AgencyRatingCriteria agencyRating) {
			this.agencyRating = agencyRating==null?null:agencyRating.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("issuerType")
		public EligibilityQuery.EligibilityQueryBuilder setIssuerType(CollateralIssuerType issuerType) {
			this.issuerType = issuerType==null?null:issuerType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("issuerName")
		public EligibilityQuery.EligibilityQueryBuilder setIssuerName(LegalEntity issuerName) {
			this.issuerName = issuerName==null?null:issuerName.toBuilder();
			return this;
		}
		
		@Override
		public EligibilityQuery build() {
			return new EligibilityQuery.EligibilityQueryImpl(this);
		}
		
		@Override
		public EligibilityQuery.EligibilityQueryBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibilityQuery.EligibilityQueryBuilder prune() {
			if (collateralAssetType!=null && !collateralAssetType.prune().hasData()) collateralAssetType = null;
			if (agencyRating!=null && !agencyRating.prune().hasData()) agencyRating = null;
			if (issuerType!=null && !issuerType.prune().hasData()) issuerType = null;
			if (issuerName!=null && !issuerName.prune().hasData()) issuerName = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMaturity()!=null) return true;
			if (getCollateralAssetType()!=null && getCollateralAssetType().hasData()) return true;
			if (getAssetCountryOfOrigin()!=null) return true;
			if (getDenominatedCurrency()!=null) return true;
			if (getAgencyRating()!=null && getAgencyRating().hasData()) return true;
			if (getIssuerType()!=null && getIssuerType().hasData()) return true;
			if (getIssuerName()!=null && getIssuerName().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibilityQuery.EligibilityQueryBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EligibilityQuery.EligibilityQueryBuilder o = (EligibilityQuery.EligibilityQueryBuilder) other;
			
			merger.mergeRosetta(getCollateralAssetType(), o.getCollateralAssetType(), this::setCollateralAssetType);
			merger.mergeRosetta(getAgencyRating(), o.getAgencyRating(), this::setAgencyRating);
			merger.mergeRosetta(getIssuerType(), o.getIssuerType(), this::setIssuerType);
			merger.mergeRosetta(getIssuerName(), o.getIssuerName(), this::setIssuerName);
			
			merger.mergeBasic(getMaturity(), o.getMaturity(), this::setMaturity);
			merger.mergeBasic(getAssetCountryOfOrigin(), o.getAssetCountryOfOrigin(), this::setAssetCountryOfOrigin);
			merger.mergeBasic(getDenominatedCurrency(), o.getDenominatedCurrency(), this::setDenominatedCurrency);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EligibilityQuery _that = getType().cast(o);
		
			if (!Objects.equals(maturity, _that.getMaturity())) return false;
			if (!Objects.equals(collateralAssetType, _that.getCollateralAssetType())) return false;
			if (!Objects.equals(assetCountryOfOrigin, _that.getAssetCountryOfOrigin())) return false;
			if (!Objects.equals(denominatedCurrency, _that.getDenominatedCurrency())) return false;
			if (!Objects.equals(agencyRating, _that.getAgencyRating())) return false;
			if (!Objects.equals(issuerType, _that.getIssuerType())) return false;
			if (!Objects.equals(issuerName, _that.getIssuerName())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (maturity != null ? maturity.hashCode() : 0);
			_result = 31 * _result + (collateralAssetType != null ? collateralAssetType.hashCode() : 0);
			_result = 31 * _result + (assetCountryOfOrigin != null ? assetCountryOfOrigin.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (denominatedCurrency != null ? denominatedCurrency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agencyRating != null ? agencyRating.hashCode() : 0);
			_result = 31 * _result + (issuerType != null ? issuerType.hashCode() : 0);
			_result = 31 * _result + (issuerName != null ? issuerName.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibilityQueryBuilder {" +
				"maturity=" + this.maturity + ", " +
				"collateralAssetType=" + this.collateralAssetType + ", " +
				"assetCountryOfOrigin=" + this.assetCountryOfOrigin + ", " +
				"denominatedCurrency=" + this.denominatedCurrency + ", " +
				"agencyRating=" + this.agencyRating + ", " +
				"issuerType=" + this.issuerType + ", " +
				"issuerName=" + this.issuerName +
			'}';
		}
	}
}
