package cdm.product.template;

import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilder;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilderImpl;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder;
import cdm.product.template.ContractualProduct;
import cdm.product.template.ContractualProduct.ContractualProductBuilder;
import cdm.product.template.ContractualProduct.ContractualProductBuilderImpl;
import cdm.product.template.ContractualProduct.ContractualProductImpl;
import cdm.product.template.EconomicTerms;
import cdm.product.template.meta.ContractualProductMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.Templatable;
import com.rosetta.model.lib.Templatable.TemplatableBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaAndTemplateFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 *  A class to specify the contractual products&#39; economic terms, alongside their product identification and product taxonomy. The contractual product class is meant to be used across the pre-execution, execution and (as part of the Contract) post-execution lifecycle contexts.
 * @version ${project.version}
 */
@RosettaDataType(value="ContractualProduct", builder=ContractualProduct.ContractualProductBuilderImpl.class, version="${project.version}")
public interface ContractualProduct extends ProductBase, Templatable, GlobalKey {

	ContractualProductMeta metaData = new ContractualProductMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The economic terms associated with a contractual product, i.e. the set of features that are price-forming.
	 */
	EconomicTerms getEconomicTerms();
	/**
	 */
	MetaAndTemplateFields getMeta();

	/*********************** Build Methods  ***********************/
	ContractualProduct build();
	
	ContractualProduct.ContractualProductBuilder toBuilder();
	
	static ContractualProduct.ContractualProductBuilder builder() {
		return new ContractualProduct.ContractualProductBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContractualProduct> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContractualProduct> getType() {
		return ContractualProduct.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.class, getProductTaxonomy());
		processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.class, getProductIdentifier());
		processRosetta(path.newSubPath("economicTerms"), processor, EconomicTerms.class, getEconomicTerms());
		processRosetta(path.newSubPath("meta"), processor, MetaAndTemplateFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContractualProductBuilder extends ContractualProduct, ProductBase.ProductBaseBuilder, RosettaModelObjectBuilder {
		EconomicTerms.EconomicTermsBuilder getOrCreateEconomicTerms();
		EconomicTerms.EconomicTermsBuilder getEconomicTerms();
		MetaAndTemplateFields.MetaAndTemplateFieldsBuilder getOrCreateMeta();
		MetaAndTemplateFields.MetaAndTemplateFieldsBuilder getMeta();
		ContractualProduct.ContractualProductBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy0);
		ContractualProduct.ContractualProductBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy1, int _idx);
		ContractualProduct.ContractualProductBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy2);
		ContractualProduct.ContractualProductBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy3);
		ContractualProduct.ContractualProductBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier0);
		ContractualProduct.ContractualProductBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier1, int _idx);
		ContractualProduct.ContractualProductBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		ContractualProduct.ContractualProductBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		ContractualProduct.ContractualProductBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier4);
		ContractualProduct.ContractualProductBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier5);
		ContractualProduct.ContractualProductBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		ContractualProduct.ContractualProductBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);
		ContractualProduct.ContractualProductBuilder setEconomicTerms(EconomicTerms economicTerms);
		ContractualProduct.ContractualProductBuilder setMeta(MetaAndTemplateFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.ProductTaxonomyBuilder.class, getProductTaxonomy());
			processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder.class, getProductIdentifier());
			processRosetta(path.newSubPath("economicTerms"), processor, EconomicTerms.EconomicTermsBuilder.class, getEconomicTerms());
			processRosetta(path.newSubPath("meta"), processor, MetaAndTemplateFields.MetaAndTemplateFieldsBuilder.class, getMeta());
		}
		

		ContractualProduct.ContractualProductBuilder prune();
	}

	/*********************** Immutable Implementation of ContractualProduct  ***********************/
	class ContractualProductImpl extends ProductBase.ProductBaseImpl implements ContractualProduct {
		private final EconomicTerms economicTerms;
		private final MetaAndTemplateFields meta;
		
		protected ContractualProductImpl(ContractualProduct.ContractualProductBuilder builder) {
			super(builder);
			this.economicTerms = ofNullable(builder.getEconomicTerms()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("economicTerms")
		public EconomicTerms getEconomicTerms() {
			return economicTerms;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaAndTemplateFields getMeta() {
			return meta;
		}
		
		@Override
		public ContractualProduct build() {
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder toBuilder() {
			ContractualProduct.ContractualProductBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContractualProduct.ContractualProductBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getEconomicTerms()).ifPresent(builder::setEconomicTerms);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ContractualProduct _that = getType().cast(o);
		
			if (!Objects.equals(economicTerms, _that.getEconomicTerms())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (economicTerms != null ? economicTerms.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualProduct {" +
				"economicTerms=" + this.economicTerms + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ContractualProduct  ***********************/
	class ContractualProductBuilderImpl extends ProductBase.ProductBaseBuilderImpl  implements ContractualProduct.ContractualProductBuilder, GlobalKeyBuilder, TemplatableBuilder {
	
		protected EconomicTerms.EconomicTermsBuilder economicTerms;
		protected MetaAndTemplateFields.MetaAndTemplateFieldsBuilder meta;
	
		public ContractualProductBuilderImpl() {
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
		@RosettaAttribute("meta")
		public MetaAndTemplateFields.MetaAndTemplateFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaAndTemplateFields.MetaAndTemplateFieldsBuilder getOrCreateMeta() {
			MetaAndTemplateFields.MetaAndTemplateFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaAndTemplateFields.builder();
			}
			
			return result;
		}
	
		@Override
		public ContractualProduct.ContractualProductBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy) {
			if (productTaxonomy!=null) this.productTaxonomy.add(productTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy, int _idx) {
			getIndex(this.productTaxonomy, _idx, () -> productTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public ContractualProduct.ContractualProductBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys != null) {
				for (ProductTaxonomy toAdd : productTaxonomys) {
					this.productTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productTaxonomy")
		public ContractualProduct.ContractualProductBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
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
		public ContractualProduct.ContractualProductBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public ContractualProduct.ContractualProductBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ReferenceWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public ContractualProduct.ContractualProductBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
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
		public ContractualProduct.ContractualProductBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("economicTerms")
		public ContractualProduct.ContractualProductBuilder setEconomicTerms(EconomicTerms economicTerms) {
			this.economicTerms = economicTerms==null?null:economicTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ContractualProduct.ContractualProductBuilder setMeta(MetaAndTemplateFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ContractualProduct build() {
			return new ContractualProduct.ContractualProductImpl(this);
		}
		
		@Override
		public ContractualProduct.ContractualProductBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualProduct.ContractualProductBuilder prune() {
			super.prune();
			if (economicTerms!=null && !economicTerms.prune().hasData()) economicTerms = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getEconomicTerms()!=null && getEconomicTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualProduct.ContractualProductBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ContractualProduct.ContractualProductBuilder o = (ContractualProduct.ContractualProductBuilder) other;
			
			merger.mergeRosetta(getEconomicTerms(), o.getEconomicTerms(), this::setEconomicTerms);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ContractualProduct _that = getType().cast(o);
		
			if (!Objects.equals(economicTerms, _that.getEconomicTerms())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (economicTerms != null ? economicTerms.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualProductBuilder {" +
				"economicTerms=" + this.economicTerms + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
