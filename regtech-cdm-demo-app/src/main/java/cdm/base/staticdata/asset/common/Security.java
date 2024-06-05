package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
import cdm.base.staticdata.asset.common.FundProductTypeEnum;
import cdm.base.staticdata.asset.common.MortgageSectorEnum;
import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilder;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilderImpl;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.Security.SecurityBuilder;
import cdm.base.staticdata.asset.common.Security.SecurityBuilderImpl;
import cdm.base.staticdata.asset.common.Security.SecurityImpl;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.base.staticdata.asset.common.meta.SecurityMeta;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder;
import cdm.product.template.EconomicTerms;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Identifies a security by referencing a product identifier and by specifying the sector.
 * @version ${project.version}
 *
 * Body ICMA
 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
 * namingConvention "Purchased Security"
 *
 * Provision As defined in GMRA paragraph 2(oo) The Purchased Securities are the Securities sold or to be sold and any New Purchased Securities transferred by Seller to Buyer under paragraph 8 (Substitution).
 *
 */
@RosettaDataType(value="Security", builder=Security.SecurityBuilderImpl.class, version="${project.version}")
public interface Security extends ProductBase {

	SecurityMeta metaData = new SecurityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the type of security using an enumerated list.
	 */
	SecurityTypeEnum getSecurityType();
	/**
	 * Identifies the type of debt and selected debt economics.
	 */
	DebtType getDebtType();
	/**
	 * Identifies the type of equity.
	 */
	EquityTypeEnum getEquityType();
	/**
	 * Identifies the type of fund.
	 */
	FundProductTypeEnum getFundType();
	/**
	 * The economic terms associated with a contractual product, i.e. the set of features that are price-forming.
	 */
	EconomicTerms getEconomicTerms();
	MortgageSectorEnum getMortgageSector();

	/*********************** Build Methods  ***********************/
	Security build();
	
	Security.SecurityBuilder toBuilder();
	
	static Security.SecurityBuilder builder() {
		return new Security.SecurityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Security> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Security> getType() {
		return Security.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.class, getProductTaxonomy());
		processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.class, getProductIdentifier());
		processor.processBasic(path.newSubPath("securityType"), SecurityTypeEnum.class, getSecurityType(), this);
		processRosetta(path.newSubPath("debtType"), processor, DebtType.class, getDebtType());
		processor.processBasic(path.newSubPath("equityType"), EquityTypeEnum.class, getEquityType(), this);
		processor.processBasic(path.newSubPath("fundType"), FundProductTypeEnum.class, getFundType(), this);
		processRosetta(path.newSubPath("economicTerms"), processor, EconomicTerms.class, getEconomicTerms());
		processor.processBasic(path.newSubPath("mortgageSector"), MortgageSectorEnum.class, getMortgageSector(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityBuilder extends Security, ProductBase.ProductBaseBuilder, RosettaModelObjectBuilder {
		DebtType.DebtTypeBuilder getOrCreateDebtType();
		DebtType.DebtTypeBuilder getDebtType();
		EconomicTerms.EconomicTermsBuilder getOrCreateEconomicTerms();
		EconomicTerms.EconomicTermsBuilder getEconomicTerms();
		Security.SecurityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy0);
		Security.SecurityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy1, int _idx);
		Security.SecurityBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy2);
		Security.SecurityBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy3);
		Security.SecurityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier0);
		Security.SecurityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier1, int _idx);
		Security.SecurityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		Security.SecurityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		Security.SecurityBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier4);
		Security.SecurityBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier5);
		Security.SecurityBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		Security.SecurityBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);
		Security.SecurityBuilder setSecurityType(SecurityTypeEnum securityType);
		Security.SecurityBuilder setDebtType(DebtType debtType);
		Security.SecurityBuilder setEquityType(EquityTypeEnum equityType);
		Security.SecurityBuilder setFundType(FundProductTypeEnum fundType);
		Security.SecurityBuilder setEconomicTerms(EconomicTerms economicTerms);
		Security.SecurityBuilder setMortgageSector(MortgageSectorEnum mortgageSector);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.ProductTaxonomyBuilder.class, getProductTaxonomy());
			processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder.class, getProductIdentifier());
			processor.processBasic(path.newSubPath("securityType"), SecurityTypeEnum.class, getSecurityType(), this);
			processRosetta(path.newSubPath("debtType"), processor, DebtType.DebtTypeBuilder.class, getDebtType());
			processor.processBasic(path.newSubPath("equityType"), EquityTypeEnum.class, getEquityType(), this);
			processor.processBasic(path.newSubPath("fundType"), FundProductTypeEnum.class, getFundType(), this);
			processRosetta(path.newSubPath("economicTerms"), processor, EconomicTerms.EconomicTermsBuilder.class, getEconomicTerms());
			processor.processBasic(path.newSubPath("mortgageSector"), MortgageSectorEnum.class, getMortgageSector(), this);
		}
		

		Security.SecurityBuilder prune();
	}

	/*********************** Immutable Implementation of Security  ***********************/
	class SecurityImpl extends ProductBase.ProductBaseImpl implements Security {
		private final SecurityTypeEnum securityType;
		private final DebtType debtType;
		private final EquityTypeEnum equityType;
		private final FundProductTypeEnum fundType;
		private final EconomicTerms economicTerms;
		private final MortgageSectorEnum mortgageSector;
		
		protected SecurityImpl(Security.SecurityBuilder builder) {
			super(builder);
			this.securityType = builder.getSecurityType();
			this.debtType = ofNullable(builder.getDebtType()).map(f->f.build()).orElse(null);
			this.equityType = builder.getEquityType();
			this.fundType = builder.getFundType();
			this.economicTerms = ofNullable(builder.getEconomicTerms()).map(f->f.build()).orElse(null);
			this.mortgageSector = builder.getMortgageSector();
		}
		
		@Override
		@RosettaAttribute("securityType")
		public SecurityTypeEnum getSecurityType() {
			return securityType;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public DebtType getDebtType() {
			return debtType;
		}
		
		@Override
		@RosettaAttribute("equityType")
		public EquityTypeEnum getEquityType() {
			return equityType;
		}
		
		@Override
		@RosettaAttribute("fundType")
		public FundProductTypeEnum getFundType() {
			return fundType;
		}
		
		@Override
		@RosettaAttribute("economicTerms")
		public EconomicTerms getEconomicTerms() {
			return economicTerms;
		}
		
		@Override
		@RosettaAttribute("mortgageSector")
		public MortgageSectorEnum getMortgageSector() {
			return mortgageSector;
		}
		
		@Override
		public Security build() {
			return this;
		}
		
		@Override
		public Security.SecurityBuilder toBuilder() {
			Security.SecurityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Security.SecurityBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getSecurityType()).ifPresent(builder::setSecurityType);
			ofNullable(getDebtType()).ifPresent(builder::setDebtType);
			ofNullable(getEquityType()).ifPresent(builder::setEquityType);
			ofNullable(getFundType()).ifPresent(builder::setFundType);
			ofNullable(getEconomicTerms()).ifPresent(builder::setEconomicTerms);
			ofNullable(getMortgageSector()).ifPresent(builder::setMortgageSector);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Security _that = getType().cast(o);
		
			if (!Objects.equals(securityType, _that.getSecurityType())) return false;
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(equityType, _that.getEquityType())) return false;
			if (!Objects.equals(fundType, _that.getFundType())) return false;
			if (!Objects.equals(economicTerms, _that.getEconomicTerms())) return false;
			if (!Objects.equals(mortgageSector, _that.getMortgageSector())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (securityType != null ? securityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (equityType != null ? equityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (fundType != null ? fundType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (economicTerms != null ? economicTerms.hashCode() : 0);
			_result = 31 * _result + (mortgageSector != null ? mortgageSector.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Security {" +
				"securityType=" + this.securityType + ", " +
				"debtType=" + this.debtType + ", " +
				"equityType=" + this.equityType + ", " +
				"fundType=" + this.fundType + ", " +
				"economicTerms=" + this.economicTerms + ", " +
				"mortgageSector=" + this.mortgageSector +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Security  ***********************/
	class SecurityBuilderImpl extends ProductBase.ProductBaseBuilderImpl  implements Security.SecurityBuilder {
	
		protected SecurityTypeEnum securityType;
		protected DebtType.DebtTypeBuilder debtType;
		protected EquityTypeEnum equityType;
		protected FundProductTypeEnum fundType;
		protected EconomicTerms.EconomicTermsBuilder economicTerms;
		protected MortgageSectorEnum mortgageSector;
	
		public SecurityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("securityType")
		public SecurityTypeEnum getSecurityType() {
			return securityType;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public DebtType.DebtTypeBuilder getDebtType() {
			return debtType;
		}
		
		@Override
		public DebtType.DebtTypeBuilder getOrCreateDebtType() {
			DebtType.DebtTypeBuilder result;
			if (debtType!=null) {
				result = debtType;
			}
			else {
				result = debtType = DebtType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("equityType")
		public EquityTypeEnum getEquityType() {
			return equityType;
		}
		
		@Override
		@RosettaAttribute("fundType")
		public FundProductTypeEnum getFundType() {
			return fundType;
		}
		
		@Override
		@RosettaAttribute("economicTerms")
		public EconomicTerms.EconomicTermsBuilder getEconomicTerms() {
			return economicTerms;
		}
		
		@Override
		public EconomicTerms.EconomicTermsBuilder getOrCreateEconomicTerms() {
			EconomicTerms.EconomicTermsBuilder result;
			if (economicTerms!=null) {
				result = economicTerms;
			}
			else {
				result = economicTerms = EconomicTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("mortgageSector")
		public MortgageSectorEnum getMortgageSector() {
			return mortgageSector;
		}
		
	
		@Override
		public Security.SecurityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy) {
			if (productTaxonomy!=null) this.productTaxonomy.add(productTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public Security.SecurityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy, int _idx) {
			getIndex(this.productTaxonomy, _idx, () -> productTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public Security.SecurityBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys != null) {
				for (ProductTaxonomy toAdd : productTaxonomys) {
					this.productTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productTaxonomy")
		public Security.SecurityBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys == null)  {
				this.productTaxonomy = new ArrayList<>();
			}
			else {
				this.productTaxonomy = productTaxonomys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Security.SecurityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Security.SecurityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Security.SecurityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Security.SecurityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Security.SecurityBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ReferenceWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public Security.SecurityBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
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
		public Security.SecurityBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Security.SecurityBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("securityType")
		public Security.SecurityBuilder setSecurityType(SecurityTypeEnum securityType) {
			this.securityType = securityType==null?null:securityType;
			return this;
		}
		@Override
		@RosettaAttribute("debtType")
		public Security.SecurityBuilder setDebtType(DebtType debtType) {
			this.debtType = debtType==null?null:debtType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityType")
		public Security.SecurityBuilder setEquityType(EquityTypeEnum equityType) {
			this.equityType = equityType==null?null:equityType;
			return this;
		}
		@Override
		@RosettaAttribute("fundType")
		public Security.SecurityBuilder setFundType(FundProductTypeEnum fundType) {
			this.fundType = fundType==null?null:fundType;
			return this;
		}
		@Override
		@RosettaAttribute("economicTerms")
		public Security.SecurityBuilder setEconomicTerms(EconomicTerms economicTerms) {
			this.economicTerms = economicTerms==null?null:economicTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("mortgageSector")
		public Security.SecurityBuilder setMortgageSector(MortgageSectorEnum mortgageSector) {
			this.mortgageSector = mortgageSector==null?null:mortgageSector;
			return this;
		}
		
		@Override
		public Security build() {
			return new Security.SecurityImpl(this);
		}
		
		@Override
		public Security.SecurityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Security.SecurityBuilder prune() {
			super.prune();
			if (debtType!=null && !debtType.prune().hasData()) debtType = null;
			if (economicTerms!=null && !economicTerms.prune().hasData()) economicTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getSecurityType()!=null) return true;
			if (getDebtType()!=null && getDebtType().hasData()) return true;
			if (getEquityType()!=null) return true;
			if (getFundType()!=null) return true;
			if (getEconomicTerms()!=null && getEconomicTerms().hasData()) return true;
			if (getMortgageSector()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Security.SecurityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Security.SecurityBuilder o = (Security.SecurityBuilder) other;
			
			merger.mergeRosetta(getDebtType(), o.getDebtType(), this::setDebtType);
			merger.mergeRosetta(getEconomicTerms(), o.getEconomicTerms(), this::setEconomicTerms);
			
			merger.mergeBasic(getSecurityType(), o.getSecurityType(), this::setSecurityType);
			merger.mergeBasic(getEquityType(), o.getEquityType(), this::setEquityType);
			merger.mergeBasic(getFundType(), o.getFundType(), this::setFundType);
			merger.mergeBasic(getMortgageSector(), o.getMortgageSector(), this::setMortgageSector);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Security _that = getType().cast(o);
		
			if (!Objects.equals(securityType, _that.getSecurityType())) return false;
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(equityType, _that.getEquityType())) return false;
			if (!Objects.equals(fundType, _that.getFundType())) return false;
			if (!Objects.equals(economicTerms, _that.getEconomicTerms())) return false;
			if (!Objects.equals(mortgageSector, _that.getMortgageSector())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (securityType != null ? securityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (equityType != null ? equityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (fundType != null ? fundType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (economicTerms != null ? economicTerms.hashCode() : 0);
			_result = 31 * _result + (mortgageSector != null ? mortgageSector.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityBuilder {" +
				"securityType=" + this.securityType + ", " +
				"debtType=" + this.debtType + ", " +
				"equityType=" + this.equityType + ", " +
				"fundType=" + this.fundType + ", " +
				"economicTerms=" + this.economicTerms + ", " +
				"mortgageSector=" + this.mortgageSector +
			'}' + " " + super.toString();
		}
	}
}
