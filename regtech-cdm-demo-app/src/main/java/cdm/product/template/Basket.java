package cdm.product.template;

import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilder;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilderImpl;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder;
import cdm.product.template.Basket;
import cdm.product.template.Basket.BasketBuilder;
import cdm.product.template.Basket.BasketBuilderImpl;
import cdm.product.template.Basket.BasketImpl;
import cdm.product.template.BasketConstituent;
import cdm.product.template.Product;
import cdm.product.template.meta.BasketMeta;
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
 * Defines a custom basket by referencing a product identifier and its consituents.
 * @version ${project.version}
 */
@RosettaDataType(value="Basket", builder=Basket.BasketBuilderImpl.class, version="${project.version}")
public interface Basket extends ProductBase {

	BasketMeta metaData = new BasketMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the constituents of the basket
	 */
	List<? extends Product> getBasketConstituent();
	/**
	 * Identifies the constituents of the basket
	 */
	List<? extends BasketConstituent> getPortfolioBasketConstituent();

	/*********************** Build Methods  ***********************/
	Basket build();
	
	Basket.BasketBuilder toBuilder();
	
	static Basket.BasketBuilder builder() {
		return new Basket.BasketBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Basket> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Basket> getType() {
		return Basket.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.class, getProductTaxonomy());
		processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.class, getProductIdentifier());
		processRosetta(path.newSubPath("basketConstituent"), processor, Product.class, getBasketConstituent());
		processRosetta(path.newSubPath("portfolioBasketConstituent"), processor, BasketConstituent.class, getPortfolioBasketConstituent());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BasketBuilder extends Basket, ProductBase.ProductBaseBuilder, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateBasketConstituent(int _index);
		List<? extends Product.ProductBuilder> getBasketConstituent();
		BasketConstituent.BasketConstituentBuilder getOrCreatePortfolioBasketConstituent(int _index);
		List<? extends BasketConstituent.BasketConstituentBuilder> getPortfolioBasketConstituent();
		Basket.BasketBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy0);
		Basket.BasketBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy1, int _idx);
		Basket.BasketBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy2);
		Basket.BasketBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy3);
		Basket.BasketBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier0);
		Basket.BasketBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier1, int _idx);
		Basket.BasketBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		Basket.BasketBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		Basket.BasketBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier4);
		Basket.BasketBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier5);
		Basket.BasketBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		Basket.BasketBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);
		Basket.BasketBuilder addBasketConstituent(Product basketConstituent0);
		Basket.BasketBuilder addBasketConstituent(Product basketConstituent1, int _idx);
		Basket.BasketBuilder addBasketConstituent(List<? extends Product> basketConstituent2);
		Basket.BasketBuilder setBasketConstituent(List<? extends Product> basketConstituent3);
		Basket.BasketBuilder addPortfolioBasketConstituent(BasketConstituent portfolioBasketConstituent0);
		Basket.BasketBuilder addPortfolioBasketConstituent(BasketConstituent portfolioBasketConstituent1, int _idx);
		Basket.BasketBuilder addPortfolioBasketConstituent(List<? extends BasketConstituent> portfolioBasketConstituent2);
		Basket.BasketBuilder setPortfolioBasketConstituent(List<? extends BasketConstituent> portfolioBasketConstituent3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.ProductTaxonomyBuilder.class, getProductTaxonomy());
			processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder.class, getProductIdentifier());
			processRosetta(path.newSubPath("basketConstituent"), processor, Product.ProductBuilder.class, getBasketConstituent());
			processRosetta(path.newSubPath("portfolioBasketConstituent"), processor, BasketConstituent.BasketConstituentBuilder.class, getPortfolioBasketConstituent());
		}
		

		Basket.BasketBuilder prune();
	}

	/*********************** Immutable Implementation of Basket  ***********************/
	class BasketImpl extends ProductBase.ProductBaseImpl implements Basket {
		private final List<? extends Product> basketConstituent;
		private final List<? extends BasketConstituent> portfolioBasketConstituent;
		
		protected BasketImpl(Basket.BasketBuilder builder) {
			super(builder);
			this.basketConstituent = ofNullable(builder.getBasketConstituent()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.portfolioBasketConstituent = ofNullable(builder.getPortfolioBasketConstituent()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("basketConstituent")
		public List<? extends Product> getBasketConstituent() {
			return basketConstituent;
		}
		
		@Override
		@RosettaAttribute("portfolioBasketConstituent")
		public List<? extends BasketConstituent> getPortfolioBasketConstituent() {
			return portfolioBasketConstituent;
		}
		
		@Override
		public Basket build() {
			return this;
		}
		
		@Override
		public Basket.BasketBuilder toBuilder() {
			Basket.BasketBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Basket.BasketBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getBasketConstituent()).ifPresent(builder::setBasketConstituent);
			ofNullable(getPortfolioBasketConstituent()).ifPresent(builder::setPortfolioBasketConstituent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Basket _that = getType().cast(o);
		
			if (!ListEquals.listEquals(basketConstituent, _that.getBasketConstituent())) return false;
			if (!ListEquals.listEquals(portfolioBasketConstituent, _that.getPortfolioBasketConstituent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (basketConstituent != null ? basketConstituent.hashCode() : 0);
			_result = 31 * _result + (portfolioBasketConstituent != null ? portfolioBasketConstituent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Basket {" +
				"basketConstituent=" + this.basketConstituent + ", " +
				"portfolioBasketConstituent=" + this.portfolioBasketConstituent +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Basket  ***********************/
	class BasketBuilderImpl extends ProductBase.ProductBaseBuilderImpl  implements Basket.BasketBuilder {
	
		protected List<Product.ProductBuilder> basketConstituent = new ArrayList<>();
		protected List<BasketConstituent.BasketConstituentBuilder> portfolioBasketConstituent = new ArrayList<>();
	
		public BasketBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("basketConstituent")
		public List<? extends Product.ProductBuilder> getBasketConstituent() {
			return basketConstituent;
		}
		
		public Product.ProductBuilder getOrCreateBasketConstituent(int _index) {
		
			if (basketConstituent==null) {
				this.basketConstituent = new ArrayList<>();
			}
			Product.ProductBuilder result;
			return getIndex(basketConstituent, _index, () -> {
						Product.ProductBuilder newBasketConstituent = Product.builder();
						return newBasketConstituent;
					});
		}
		
		@Override
		@RosettaAttribute("portfolioBasketConstituent")
		public List<? extends BasketConstituent.BasketConstituentBuilder> getPortfolioBasketConstituent() {
			return portfolioBasketConstituent;
		}
		
		public BasketConstituent.BasketConstituentBuilder getOrCreatePortfolioBasketConstituent(int _index) {
		
			if (portfolioBasketConstituent==null) {
				this.portfolioBasketConstituent = new ArrayList<>();
			}
			BasketConstituent.BasketConstituentBuilder result;
			return getIndex(portfolioBasketConstituent, _index, () -> {
						BasketConstituent.BasketConstituentBuilder newPortfolioBasketConstituent = BasketConstituent.builder();
						return newPortfolioBasketConstituent;
					});
		}
		
	
		@Override
		public Basket.BasketBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy) {
			if (productTaxonomy!=null) this.productTaxonomy.add(productTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy, int _idx) {
			getIndex(this.productTaxonomy, _idx, () -> productTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public Basket.BasketBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys != null) {
				for (ProductTaxonomy toAdd : productTaxonomys) {
					this.productTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productTaxonomy")
		public Basket.BasketBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
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
		public Basket.BasketBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Basket.BasketBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ReferenceWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public Basket.BasketBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
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
		public Basket.BasketBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Basket.BasketBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addBasketConstituent(Product basketConstituent) {
			if (basketConstituent!=null) this.basketConstituent.add(basketConstituent.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addBasketConstituent(Product basketConstituent, int _idx) {
			getIndex(this.basketConstituent, _idx, () -> basketConstituent.toBuilder());
			return this;
		}
		@Override 
		public Basket.BasketBuilder addBasketConstituent(List<? extends Product> basketConstituents) {
			if (basketConstituents != null) {
				for (Product toAdd : basketConstituents) {
					this.basketConstituent.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("basketConstituent")
		public Basket.BasketBuilder setBasketConstituent(List<? extends Product> basketConstituents) {
			if (basketConstituents == null)  {
				this.basketConstituent = new ArrayList<>();
			}
			else {
				this.basketConstituent = basketConstituents.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addPortfolioBasketConstituent(BasketConstituent portfolioBasketConstituent) {
			if (portfolioBasketConstituent!=null) this.portfolioBasketConstituent.add(portfolioBasketConstituent.toBuilder());
			return this;
		}
		
		@Override
		public Basket.BasketBuilder addPortfolioBasketConstituent(BasketConstituent portfolioBasketConstituent, int _idx) {
			getIndex(this.portfolioBasketConstituent, _idx, () -> portfolioBasketConstituent.toBuilder());
			return this;
		}
		@Override 
		public Basket.BasketBuilder addPortfolioBasketConstituent(List<? extends BasketConstituent> portfolioBasketConstituents) {
			if (portfolioBasketConstituents != null) {
				for (BasketConstituent toAdd : portfolioBasketConstituents) {
					this.portfolioBasketConstituent.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("portfolioBasketConstituent")
		public Basket.BasketBuilder setPortfolioBasketConstituent(List<? extends BasketConstituent> portfolioBasketConstituents) {
			if (portfolioBasketConstituents == null)  {
				this.portfolioBasketConstituent = new ArrayList<>();
			}
			else {
				this.portfolioBasketConstituent = portfolioBasketConstituents.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public Basket build() {
			return new Basket.BasketImpl(this);
		}
		
		@Override
		public Basket.BasketBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Basket.BasketBuilder prune() {
			super.prune();
			basketConstituent = basketConstituent.stream().filter(b->b!=null).<Product.ProductBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			portfolioBasketConstituent = portfolioBasketConstituent.stream().filter(b->b!=null).<BasketConstituent.BasketConstituentBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getBasketConstituent()!=null && getBasketConstituent().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPortfolioBasketConstituent()!=null && getPortfolioBasketConstituent().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Basket.BasketBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Basket.BasketBuilder o = (Basket.BasketBuilder) other;
			
			merger.mergeRosetta(getBasketConstituent(), o.getBasketConstituent(), this::getOrCreateBasketConstituent);
			merger.mergeRosetta(getPortfolioBasketConstituent(), o.getPortfolioBasketConstituent(), this::getOrCreatePortfolioBasketConstituent);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Basket _that = getType().cast(o);
		
			if (!ListEquals.listEquals(basketConstituent, _that.getBasketConstituent())) return false;
			if (!ListEquals.listEquals(portfolioBasketConstituent, _that.getPortfolioBasketConstituent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (basketConstituent != null ? basketConstituent.hashCode() : 0);
			_result = 31 * _result + (portfolioBasketConstituent != null ? portfolioBasketConstituent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BasketBuilder {" +
				"basketConstituent=" + this.basketConstituent + ", " +
				"portfolioBasketConstituent=" + this.portfolioBasketConstituent +
			'}' + " " + super.toString();
		}
	}
}
