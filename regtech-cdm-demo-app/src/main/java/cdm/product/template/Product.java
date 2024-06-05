package cdm.product.template;

import cdm.base.staticdata.asset.common.AssetPool;
import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder;
import cdm.product.asset.ForeignExchange;
import cdm.product.template.Basket;
import cdm.product.template.ContractualProduct;
import cdm.product.template.Product;
import cdm.product.template.Product.ProductBuilder;
import cdm.product.template.Product.ProductBuilderImpl;
import cdm.product.template.Product.ProductImpl;
import cdm.product.template.meta.ProductMeta;
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
 * Defines the product that is the subject of a tradable product definition, an underlying product definition, a physical exercise, a position, or other purposes.
 * @version ${project.version}
 */
@RosettaDataType(value="Product", builder=Product.ProductBuilderImpl.class, version="${project.version}")
public interface Product extends RosettaModelObject, GlobalKey {

	ProductMeta metaData = new ProductMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the contractual product&#39;s economic terms, product identifier, and product taxonomy.
	 */
	ContractualProduct getContractualProduct();
	/**
	 * Identifies an index by referencing a product identifier.
	 */
	Index getIndex();
	/**
	 * Identifies a loan by referencing a product identifier and an optional set of attributes.
	 */
	Loan getLoan();
	/**
	 * Identifies an asset pool product for defining pool of assets backing an asset backed security.
	 */
	AssetPool getAssetPool();
	/**
	 * Defines a foreign exchange spot or forward transaction.
	 */
	ForeignExchange getForeignExchange();
	/**
	 * Identifies a commodity by referencing a product identifier.
	 */
	ReferenceWithMetaCommodity getCommodity();
	/**
	 * Identifies a security by referencing a product identifier and a security type, plus an optional set of attributes.
	 */
	Security getSecurity();
	/**
	 * Identifies a custom basket by referencing a product identifier and its constituents.
	 */
	Basket getBasket();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Product build();
	
	Product.ProductBuilder toBuilder();
	
	static Product.ProductBuilder builder() {
		return new Product.ProductBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Product> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Product> getType() {
		return Product.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("contractualProduct"), processor, ContractualProduct.class, getContractualProduct());
		processRosetta(path.newSubPath("index"), processor, Index.class, getIndex());
		processRosetta(path.newSubPath("loan"), processor, Loan.class, getLoan());
		processRosetta(path.newSubPath("assetPool"), processor, AssetPool.class, getAssetPool());
		processRosetta(path.newSubPath("foreignExchange"), processor, ForeignExchange.class, getForeignExchange());
		processRosetta(path.newSubPath("commodity"), processor, ReferenceWithMetaCommodity.class, getCommodity());
		processRosetta(path.newSubPath("security"), processor, Security.class, getSecurity());
		processRosetta(path.newSubPath("basket"), processor, Basket.class, getBasket());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ProductBuilder extends Product, RosettaModelObjectBuilder {
		ContractualProduct.ContractualProductBuilder getOrCreateContractualProduct();
		ContractualProduct.ContractualProductBuilder getContractualProduct();
		Index.IndexBuilder getOrCreateIndex();
		Index.IndexBuilder getIndex();
		Loan.LoanBuilder getOrCreateLoan();
		Loan.LoanBuilder getLoan();
		AssetPool.AssetPoolBuilder getOrCreateAssetPool();
		AssetPool.AssetPoolBuilder getAssetPool();
		ForeignExchange.ForeignExchangeBuilder getOrCreateForeignExchange();
		ForeignExchange.ForeignExchangeBuilder getForeignExchange();
		ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder getOrCreateCommodity();
		ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder getCommodity();
		Security.SecurityBuilder getOrCreateSecurity();
		Security.SecurityBuilder getSecurity();
		Basket.BasketBuilder getOrCreateBasket();
		Basket.BasketBuilder getBasket();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Product.ProductBuilder setContractualProduct(ContractualProduct contractualProduct);
		Product.ProductBuilder setIndex(Index index);
		Product.ProductBuilder setLoan(Loan loan);
		Product.ProductBuilder setAssetPool(AssetPool assetPool);
		Product.ProductBuilder setForeignExchange(ForeignExchange foreignExchange);
		Product.ProductBuilder setCommodity(ReferenceWithMetaCommodity commodity0);
		Product.ProductBuilder setCommodityValue(Commodity commodity1);
		Product.ProductBuilder setSecurity(Security security);
		Product.ProductBuilder setBasket(Basket basket);
		Product.ProductBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("contractualProduct"), processor, ContractualProduct.ContractualProductBuilder.class, getContractualProduct());
			processRosetta(path.newSubPath("index"), processor, Index.IndexBuilder.class, getIndex());
			processRosetta(path.newSubPath("loan"), processor, Loan.LoanBuilder.class, getLoan());
			processRosetta(path.newSubPath("assetPool"), processor, AssetPool.AssetPoolBuilder.class, getAssetPool());
			processRosetta(path.newSubPath("foreignExchange"), processor, ForeignExchange.ForeignExchangeBuilder.class, getForeignExchange());
			processRosetta(path.newSubPath("commodity"), processor, ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder.class, getCommodity());
			processRosetta(path.newSubPath("security"), processor, Security.SecurityBuilder.class, getSecurity());
			processRosetta(path.newSubPath("basket"), processor, Basket.BasketBuilder.class, getBasket());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Product.ProductBuilder prune();
	}

	/*********************** Immutable Implementation of Product  ***********************/
	class ProductImpl implements Product {
		private final ContractualProduct contractualProduct;
		private final Index index;
		private final Loan loan;
		private final AssetPool assetPool;
		private final ForeignExchange foreignExchange;
		private final ReferenceWithMetaCommodity commodity;
		private final Security security;
		private final Basket basket;
		private final MetaFields meta;
		
		protected ProductImpl(Product.ProductBuilder builder) {
			this.contractualProduct = ofNullable(builder.getContractualProduct()).map(f->f.build()).orElse(null);
			this.index = ofNullable(builder.getIndex()).map(f->f.build()).orElse(null);
			this.loan = ofNullable(builder.getLoan()).map(f->f.build()).orElse(null);
			this.assetPool = ofNullable(builder.getAssetPool()).map(f->f.build()).orElse(null);
			this.foreignExchange = ofNullable(builder.getForeignExchange()).map(f->f.build()).orElse(null);
			this.commodity = ofNullable(builder.getCommodity()).map(f->f.build()).orElse(null);
			this.security = ofNullable(builder.getSecurity()).map(f->f.build()).orElse(null);
			this.basket = ofNullable(builder.getBasket()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("contractualProduct")
		public ContractualProduct getContractualProduct() {
			return contractualProduct;
		}
		
		@Override
		@RosettaAttribute("index")
		public Index getIndex() {
			return index;
		}
		
		@Override
		@RosettaAttribute("loan")
		public Loan getLoan() {
			return loan;
		}
		
		@Override
		@RosettaAttribute("assetPool")
		public AssetPool getAssetPool() {
			return assetPool;
		}
		
		@Override
		@RosettaAttribute("foreignExchange")
		public ForeignExchange getForeignExchange() {
			return foreignExchange;
		}
		
		@Override
		@RosettaAttribute("commodity")
		public ReferenceWithMetaCommodity getCommodity() {
			return commodity;
		}
		
		@Override
		@RosettaAttribute("security")
		public Security getSecurity() {
			return security;
		}
		
		@Override
		@RosettaAttribute("basket")
		public Basket getBasket() {
			return basket;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Product build() {
			return this;
		}
		
		@Override
		public Product.ProductBuilder toBuilder() {
			Product.ProductBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Product.ProductBuilder builder) {
			ofNullable(getContractualProduct()).ifPresent(builder::setContractualProduct);
			ofNullable(getIndex()).ifPresent(builder::setIndex);
			ofNullable(getLoan()).ifPresent(builder::setLoan);
			ofNullable(getAssetPool()).ifPresent(builder::setAssetPool);
			ofNullable(getForeignExchange()).ifPresent(builder::setForeignExchange);
			ofNullable(getCommodity()).ifPresent(builder::setCommodity);
			ofNullable(getSecurity()).ifPresent(builder::setSecurity);
			ofNullable(getBasket()).ifPresent(builder::setBasket);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Product _that = getType().cast(o);
		
			if (!Objects.equals(contractualProduct, _that.getContractualProduct())) return false;
			if (!Objects.equals(index, _that.getIndex())) return false;
			if (!Objects.equals(loan, _that.getLoan())) return false;
			if (!Objects.equals(assetPool, _that.getAssetPool())) return false;
			if (!Objects.equals(foreignExchange, _that.getForeignExchange())) return false;
			if (!Objects.equals(commodity, _that.getCommodity())) return false;
			if (!Objects.equals(security, _that.getSecurity())) return false;
			if (!Objects.equals(basket, _that.getBasket())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractualProduct != null ? contractualProduct.hashCode() : 0);
			_result = 31 * _result + (index != null ? index.hashCode() : 0);
			_result = 31 * _result + (loan != null ? loan.hashCode() : 0);
			_result = 31 * _result + (assetPool != null ? assetPool.hashCode() : 0);
			_result = 31 * _result + (foreignExchange != null ? foreignExchange.hashCode() : 0);
			_result = 31 * _result + (commodity != null ? commodity.hashCode() : 0);
			_result = 31 * _result + (security != null ? security.hashCode() : 0);
			_result = 31 * _result + (basket != null ? basket.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Product {" +
				"contractualProduct=" + this.contractualProduct + ", " +
				"index=" + this.index + ", " +
				"loan=" + this.loan + ", " +
				"assetPool=" + this.assetPool + ", " +
				"foreignExchange=" + this.foreignExchange + ", " +
				"commodity=" + this.commodity + ", " +
				"security=" + this.security + ", " +
				"basket=" + this.basket + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Product  ***********************/
	class ProductBuilderImpl implements Product.ProductBuilder, GlobalKeyBuilder {
	
		protected ContractualProduct.ContractualProductBuilder contractualProduct;
		protected Index.IndexBuilder index;
		protected Loan.LoanBuilder loan;
		protected AssetPool.AssetPoolBuilder assetPool;
		protected ForeignExchange.ForeignExchangeBuilder foreignExchange;
		protected ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder commodity;
		protected Security.SecurityBuilder security;
		protected Basket.BasketBuilder basket;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ProductBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("contractualProduct")
		public ContractualProduct.ContractualProductBuilder getContractualProduct() {
			return contractualProduct;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder getOrCreateContractualProduct() {
			ContractualProduct.ContractualProductBuilder result;
			if (contractualProduct!=null) {
				result = contractualProduct;
			}
			else {
				result = contractualProduct = ContractualProduct.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("index")
		public Index.IndexBuilder getIndex() {
			return index;
		}
		
		@Override
		public Index.IndexBuilder getOrCreateIndex() {
			Index.IndexBuilder result;
			if (index!=null) {
				result = index;
			}
			else {
				result = index = Index.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("loan")
		public Loan.LoanBuilder getLoan() {
			return loan;
		}
		
		@Override
		public Loan.LoanBuilder getOrCreateLoan() {
			Loan.LoanBuilder result;
			if (loan!=null) {
				result = loan;
			}
			else {
				result = loan = Loan.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("assetPool")
		public AssetPool.AssetPoolBuilder getAssetPool() {
			return assetPool;
		}
		
		@Override
		public AssetPool.AssetPoolBuilder getOrCreateAssetPool() {
			AssetPool.AssetPoolBuilder result;
			if (assetPool!=null) {
				result = assetPool;
			}
			else {
				result = assetPool = AssetPool.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("foreignExchange")
		public ForeignExchange.ForeignExchangeBuilder getForeignExchange() {
			return foreignExchange;
		}
		
		@Override
		public ForeignExchange.ForeignExchangeBuilder getOrCreateForeignExchange() {
			ForeignExchange.ForeignExchangeBuilder result;
			if (foreignExchange!=null) {
				result = foreignExchange;
			}
			else {
				result = foreignExchange = ForeignExchange.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commodity")
		public ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder getCommodity() {
			return commodity;
		}
		
		@Override
		public ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder getOrCreateCommodity() {
			ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder result;
			if (commodity!=null) {
				result = commodity;
			}
			else {
				result = commodity = ReferenceWithMetaCommodity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("security")
		public Security.SecurityBuilder getSecurity() {
			return security;
		}
		
		@Override
		public Security.SecurityBuilder getOrCreateSecurity() {
			Security.SecurityBuilder result;
			if (security!=null) {
				result = security;
			}
			else {
				result = security = Security.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("basket")
		public Basket.BasketBuilder getBasket() {
			return basket;
		}
		
		@Override
		public Basket.BasketBuilder getOrCreateBasket() {
			Basket.BasketBuilder result;
			if (basket!=null) {
				result = basket;
			}
			else {
				result = basket = Basket.builder();
			}
			
			return result;
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
		@RosettaAttribute("contractualProduct")
		public Product.ProductBuilder setContractualProduct(ContractualProduct contractualProduct) {
			this.contractualProduct = contractualProduct==null?null:contractualProduct.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("index")
		public Product.ProductBuilder setIndex(Index index) {
			this.index = index==null?null:index.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("loan")
		public Product.ProductBuilder setLoan(Loan loan) {
			this.loan = loan==null?null:loan.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("assetPool")
		public Product.ProductBuilder setAssetPool(AssetPool assetPool) {
			this.assetPool = assetPool==null?null:assetPool.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("foreignExchange")
		public Product.ProductBuilder setForeignExchange(ForeignExchange foreignExchange) {
			this.foreignExchange = foreignExchange==null?null:foreignExchange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commodity")
		public Product.ProductBuilder setCommodity(ReferenceWithMetaCommodity commodity) {
			this.commodity = commodity==null?null:commodity.toBuilder();
			return this;
		}
		@Override
		public Product.ProductBuilder setCommodityValue(Commodity commodity) {
			this.getOrCreateCommodity().setValue(commodity);
			return this;
		}
		@Override
		@RosettaAttribute("security")
		public Product.ProductBuilder setSecurity(Security security) {
			this.security = security==null?null:security.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("basket")
		public Product.ProductBuilder setBasket(Basket basket) {
			this.basket = basket==null?null:basket.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Product.ProductBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Product build() {
			return new Product.ProductImpl(this);
		}
		
		@Override
		public Product.ProductBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Product.ProductBuilder prune() {
			if (contractualProduct!=null && !contractualProduct.prune().hasData()) contractualProduct = null;
			if (index!=null && !index.prune().hasData()) index = null;
			if (loan!=null && !loan.prune().hasData()) loan = null;
			if (assetPool!=null && !assetPool.prune().hasData()) assetPool = null;
			if (foreignExchange!=null && !foreignExchange.prune().hasData()) foreignExchange = null;
			if (commodity!=null && !commodity.prune().hasData()) commodity = null;
			if (security!=null && !security.prune().hasData()) security = null;
			if (basket!=null && !basket.prune().hasData()) basket = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getContractualProduct()!=null && getContractualProduct().hasData()) return true;
			if (getIndex()!=null && getIndex().hasData()) return true;
			if (getLoan()!=null && getLoan().hasData()) return true;
			if (getAssetPool()!=null && getAssetPool().hasData()) return true;
			if (getForeignExchange()!=null && getForeignExchange().hasData()) return true;
			if (getCommodity()!=null && getCommodity().hasData()) return true;
			if (getSecurity()!=null && getSecurity().hasData()) return true;
			if (getBasket()!=null && getBasket().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Product.ProductBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Product.ProductBuilder o = (Product.ProductBuilder) other;
			
			merger.mergeRosetta(getContractualProduct(), o.getContractualProduct(), this::setContractualProduct);
			merger.mergeRosetta(getIndex(), o.getIndex(), this::setIndex);
			merger.mergeRosetta(getLoan(), o.getLoan(), this::setLoan);
			merger.mergeRosetta(getAssetPool(), o.getAssetPool(), this::setAssetPool);
			merger.mergeRosetta(getForeignExchange(), o.getForeignExchange(), this::setForeignExchange);
			merger.mergeRosetta(getCommodity(), o.getCommodity(), this::setCommodity);
			merger.mergeRosetta(getSecurity(), o.getSecurity(), this::setSecurity);
			merger.mergeRosetta(getBasket(), o.getBasket(), this::setBasket);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Product _that = getType().cast(o);
		
			if (!Objects.equals(contractualProduct, _that.getContractualProduct())) return false;
			if (!Objects.equals(index, _that.getIndex())) return false;
			if (!Objects.equals(loan, _that.getLoan())) return false;
			if (!Objects.equals(assetPool, _that.getAssetPool())) return false;
			if (!Objects.equals(foreignExchange, _that.getForeignExchange())) return false;
			if (!Objects.equals(commodity, _that.getCommodity())) return false;
			if (!Objects.equals(security, _that.getSecurity())) return false;
			if (!Objects.equals(basket, _that.getBasket())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractualProduct != null ? contractualProduct.hashCode() : 0);
			_result = 31 * _result + (index != null ? index.hashCode() : 0);
			_result = 31 * _result + (loan != null ? loan.hashCode() : 0);
			_result = 31 * _result + (assetPool != null ? assetPool.hashCode() : 0);
			_result = 31 * _result + (foreignExchange != null ? foreignExchange.hashCode() : 0);
			_result = 31 * _result + (commodity != null ? commodity.hashCode() : 0);
			_result = 31 * _result + (security != null ? security.hashCode() : 0);
			_result = 31 * _result + (basket != null ? basket.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductBuilder {" +
				"contractualProduct=" + this.contractualProduct + ", " +
				"index=" + this.index + ", " +
				"loan=" + this.loan + ", " +
				"assetPool=" + this.assetPool + ", " +
				"foreignExchange=" + this.foreignExchange + ", " +
				"commodity=" + this.commodity + ", " +
				"security=" + this.security + ", " +
				"basket=" + this.basket + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
