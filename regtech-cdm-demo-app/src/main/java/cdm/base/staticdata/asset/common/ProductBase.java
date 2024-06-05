package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilder;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilderImpl;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.meta.ProductBaseMeta;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder;
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
 * Serves as an abstract class to specify a product using a productIdentifier.
 * @version ${project.version}
 */
@RosettaDataType(value="ProductBase", builder=ProductBase.ProductBaseBuilderImpl.class, version="${project.version}")
public interface ProductBase extends RosettaModelObject {

	ProductBaseMeta metaData = new ProductBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the product taxonomy, which is composed of a taxonomy value and a taxonomy source.
	 */
	List<? extends ProductTaxonomy> getProductTaxonomy();
	/**
	 * Comprises an identifier and a source. The associated metadata key denotes the ability to associate a hash value to the ProductIdentifier instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
	 */
	List<? extends ReferenceWithMetaProductIdentifier> getProductIdentifier();

	/*********************** Build Methods  ***********************/
	ProductBase build();
	
	ProductBase.ProductBaseBuilder toBuilder();
	
	static ProductBase.ProductBaseBuilder builder() {
		return new ProductBase.ProductBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ProductBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ProductBase> getType() {
		return ProductBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.class, getProductTaxonomy());
		processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.class, getProductIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ProductBaseBuilder extends ProductBase, RosettaModelObjectBuilder {
		ProductTaxonomy.ProductTaxonomyBuilder getOrCreateProductTaxonomy(int _index);
		List<? extends ProductTaxonomy.ProductTaxonomyBuilder> getProductTaxonomy();
		ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder getOrCreateProductIdentifier(int _index);
		List<? extends ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder> getProductIdentifier();
		ProductBase.ProductBaseBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy0);
		ProductBase.ProductBaseBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy1, int _idx);
		ProductBase.ProductBaseBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy2);
		ProductBase.ProductBaseBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy3);
		ProductBase.ProductBaseBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier0);
		ProductBase.ProductBaseBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier1, int _idx);
		ProductBase.ProductBaseBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		ProductBase.ProductBaseBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		ProductBase.ProductBaseBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier4);
		ProductBase.ProductBaseBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier5);
		ProductBase.ProductBaseBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		ProductBase.ProductBaseBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.ProductTaxonomyBuilder.class, getProductTaxonomy());
			processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder.class, getProductIdentifier());
		}
		

		ProductBase.ProductBaseBuilder prune();
	}

	/*********************** Immutable Implementation of ProductBase  ***********************/
	class ProductBaseImpl implements ProductBase {
		private final List<? extends ProductTaxonomy> productTaxonomy;
		private final List<? extends ReferenceWithMetaProductIdentifier> productIdentifier;
		
		protected ProductBaseImpl(ProductBase.ProductBaseBuilder builder) {
			this.productTaxonomy = ofNullable(builder.getProductTaxonomy()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.productIdentifier = ofNullable(builder.getProductIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("productTaxonomy")
		public List<? extends ProductTaxonomy> getProductTaxonomy() {
			return productTaxonomy;
		}
		
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends ReferenceWithMetaProductIdentifier> getProductIdentifier() {
			return productIdentifier;
		}
		
		@Override
		public ProductBase build() {
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder toBuilder() {
			ProductBase.ProductBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ProductBase.ProductBaseBuilder builder) {
			ofNullable(getProductTaxonomy()).ifPresent(builder::setProductTaxonomy);
			ofNullable(getProductIdentifier()).ifPresent(builder::setProductIdentifier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProductBase _that = getType().cast(o);
		
			if (!ListEquals.listEquals(productTaxonomy, _that.getProductTaxonomy())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (productTaxonomy != null ? productTaxonomy.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductBase {" +
				"productTaxonomy=" + this.productTaxonomy + ", " +
				"productIdentifier=" + this.productIdentifier +
			'}';
		}
	}

	/*********************** Builder Implementation of ProductBase  ***********************/
	class ProductBaseBuilderImpl implements ProductBase.ProductBaseBuilder {
	
		protected List<ProductTaxonomy.ProductTaxonomyBuilder> productTaxonomy = new ArrayList<>();
		protected List<ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder> productIdentifier = new ArrayList<>();
	
		public ProductBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("productTaxonomy")
		public List<? extends ProductTaxonomy.ProductTaxonomyBuilder> getProductTaxonomy() {
			return productTaxonomy;
		}
		
		public ProductTaxonomy.ProductTaxonomyBuilder getOrCreateProductTaxonomy(int _index) {
		
			if (productTaxonomy==null) {
				this.productTaxonomy = new ArrayList<>();
			}
			ProductTaxonomy.ProductTaxonomyBuilder result;
			return getIndex(productTaxonomy, _index, () -> {
						ProductTaxonomy.ProductTaxonomyBuilder newProductTaxonomy = ProductTaxonomy.builder();
						return newProductTaxonomy;
					});
		}
		
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder> getProductIdentifier() {
			return productIdentifier;
		}
		
		public ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder getOrCreateProductIdentifier(int _index) {
		
			if (productIdentifier==null) {
				this.productIdentifier = new ArrayList<>();
			}
			ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder result;
			return getIndex(productIdentifier, _index, () -> {
						ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder newProductIdentifier = ReferenceWithMetaProductIdentifier.builder();
						return newProductIdentifier;
					});
		}
		
	
		@Override
		public ProductBase.ProductBaseBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy) {
			if (productTaxonomy!=null) this.productTaxonomy.add(productTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy, int _idx) {
			getIndex(this.productTaxonomy, _idx, () -> productTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public ProductBase.ProductBaseBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys != null) {
				for (ProductTaxonomy toAdd : productTaxonomys) {
					this.productTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productTaxonomy")
		public ProductBase.ProductBaseBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
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
		public ProductBase.ProductBaseBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public ProductBase.ProductBaseBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ReferenceWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public ProductBase.ProductBaseBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
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
		public ProductBase.ProductBaseBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public ProductBase.ProductBaseBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		
		@Override
		public ProductBase build() {
			return new ProductBase.ProductBaseImpl(this);
		}
		
		@Override
		public ProductBase.ProductBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductBase.ProductBaseBuilder prune() {
			productTaxonomy = productTaxonomy.stream().filter(b->b!=null).<ProductTaxonomy.ProductTaxonomyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			productIdentifier = productIdentifier.stream().filter(b->b!=null).<ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getProductTaxonomy()!=null && getProductTaxonomy().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getProductIdentifier()!=null && getProductIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductBase.ProductBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ProductBase.ProductBaseBuilder o = (ProductBase.ProductBaseBuilder) other;
			
			merger.mergeRosetta(getProductTaxonomy(), o.getProductTaxonomy(), this::getOrCreateProductTaxonomy);
			merger.mergeRosetta(getProductIdentifier(), o.getProductIdentifier(), this::getOrCreateProductIdentifier);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProductBase _that = getType().cast(o);
		
			if (!ListEquals.listEquals(productTaxonomy, _that.getProductTaxonomy())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (productTaxonomy != null ? productTaxonomy.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductBaseBuilder {" +
				"productTaxonomy=" + this.productTaxonomy + ", " +
				"productIdentifier=" + this.productIdentifier +
			'}';
		}
	}
}
