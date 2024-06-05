package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.ProductTaxonomy.ProductTaxonomyBuilder;
import cdm.base.staticdata.asset.common.ProductTaxonomy.ProductTaxonomyBuilderImpl;
import cdm.base.staticdata.asset.common.ProductTaxonomy.ProductTaxonomyImpl;
import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyBuilder;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyBuilderImpl;
import cdm.base.staticdata.asset.common.Taxonomy.TaxonomyImpl;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import cdm.base.staticdata.asset.common.meta.ProductTaxonomyMeta;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder;
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
 * Specifies the product taxonomy, which is composed of a taxonomy value and a taxonomy source.
 * @version ${project.version}
 */
@RosettaDataType(value="ProductTaxonomy", builder=ProductTaxonomy.ProductTaxonomyBuilderImpl.class, version="${project.version}")
public interface ProductTaxonomy extends Taxonomy {

	ProductTaxonomyMeta metaData = new ProductTaxonomyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Classifies the most important risk class of the trade.
	 */
	FieldWithMetaAssetClassEnum getPrimaryAssetClass();
	/**
	 *  Classifies additional risk classes of the trade, if any.
	 */
	List<? extends FieldWithMetaAssetClassEnum> getSecondaryAssetClass();
	/**
	 * Derived from the product payout features using a CDM product qualification function that determines the product type based on the product payout features.
	 */
	String getProductQualifier();

	/*********************** Build Methods  ***********************/
	ProductTaxonomy build();
	
	ProductTaxonomy.ProductTaxonomyBuilder toBuilder();
	
	static ProductTaxonomy.ProductTaxonomyBuilder builder() {
		return new ProductTaxonomy.ProductTaxonomyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ProductTaxonomy> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ProductTaxonomy> getType() {
		return ProductTaxonomy.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("source"), TaxonomySourceEnum.class, getSource(), this);
		processRosetta(path.newSubPath("value"), processor, TaxonomyValue.class, getValue());
		processRosetta(path.newSubPath("primaryAssetClass"), processor, FieldWithMetaAssetClassEnum.class, getPrimaryAssetClass());
		processRosetta(path.newSubPath("secondaryAssetClass"), processor, FieldWithMetaAssetClassEnum.class, getSecondaryAssetClass());
		processor.processBasic(path.newSubPath("productQualifier"), String.class, getProductQualifier(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ProductTaxonomyBuilder extends ProductTaxonomy, Taxonomy.TaxonomyBuilder, RosettaModelObjectBuilder {
		FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getOrCreatePrimaryAssetClass();
		FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getPrimaryAssetClass();
		FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getOrCreateSecondaryAssetClass(int _index);
		List<? extends FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder> getSecondaryAssetClass();
		ProductTaxonomy.ProductTaxonomyBuilder setSource(TaxonomySourceEnum source);
		ProductTaxonomy.ProductTaxonomyBuilder setValue(TaxonomyValue value);
		ProductTaxonomy.ProductTaxonomyBuilder setPrimaryAssetClass(FieldWithMetaAssetClassEnum primaryAssetClass0);
		ProductTaxonomy.ProductTaxonomyBuilder setPrimaryAssetClassValue(AssetClassEnum primaryAssetClass1);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(FieldWithMetaAssetClassEnum secondaryAssetClass0);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(FieldWithMetaAssetClassEnum secondaryAssetClass1, int _idx);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(AssetClassEnum secondaryAssetClass2);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(AssetClassEnum secondaryAssetClass3, int _idx);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(List<? extends FieldWithMetaAssetClassEnum> secondaryAssetClass4);
		ProductTaxonomy.ProductTaxonomyBuilder setSecondaryAssetClass(List<? extends FieldWithMetaAssetClassEnum> secondaryAssetClass5);
		ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(List<? extends AssetClassEnum> secondaryAssetClass6);
		ProductTaxonomy.ProductTaxonomyBuilder setSecondaryAssetClassValue(List<? extends AssetClassEnum> secondaryAssetClass7);
		ProductTaxonomy.ProductTaxonomyBuilder setProductQualifier(String productQualifier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("source"), TaxonomySourceEnum.class, getSource(), this);
			processRosetta(path.newSubPath("value"), processor, TaxonomyValue.TaxonomyValueBuilder.class, getValue());
			processRosetta(path.newSubPath("primaryAssetClass"), processor, FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder.class, getPrimaryAssetClass());
			processRosetta(path.newSubPath("secondaryAssetClass"), processor, FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder.class, getSecondaryAssetClass());
			processor.processBasic(path.newSubPath("productQualifier"), String.class, getProductQualifier(), this);
		}
		

		ProductTaxonomy.ProductTaxonomyBuilder prune();
	}

	/*********************** Immutable Implementation of ProductTaxonomy  ***********************/
	class ProductTaxonomyImpl extends Taxonomy.TaxonomyImpl implements ProductTaxonomy {
		private final FieldWithMetaAssetClassEnum primaryAssetClass;
		private final List<? extends FieldWithMetaAssetClassEnum> secondaryAssetClass;
		private final String productQualifier;
		
		protected ProductTaxonomyImpl(ProductTaxonomy.ProductTaxonomyBuilder builder) {
			super(builder);
			this.primaryAssetClass = ofNullable(builder.getPrimaryAssetClass()).map(f->f.build()).orElse(null);
			this.secondaryAssetClass = ofNullable(builder.getSecondaryAssetClass()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.productQualifier = builder.getProductQualifier();
		}
		
		@Override
		@RosettaAttribute("primaryAssetClass")
		public FieldWithMetaAssetClassEnum getPrimaryAssetClass() {
			return primaryAssetClass;
		}
		
		@Override
		@RosettaAttribute("secondaryAssetClass")
		public List<? extends FieldWithMetaAssetClassEnum> getSecondaryAssetClass() {
			return secondaryAssetClass;
		}
		
		@Override
		@RosettaAttribute("productQualifier")
		public String getProductQualifier() {
			return productQualifier;
		}
		
		@Override
		public ProductTaxonomy build() {
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder toBuilder() {
			ProductTaxonomy.ProductTaxonomyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ProductTaxonomy.ProductTaxonomyBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getPrimaryAssetClass()).ifPresent(builder::setPrimaryAssetClass);
			ofNullable(getSecondaryAssetClass()).ifPresent(builder::setSecondaryAssetClass);
			ofNullable(getProductQualifier()).ifPresent(builder::setProductQualifier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ProductTaxonomy _that = getType().cast(o);
		
			if (!Objects.equals(primaryAssetClass, _that.getPrimaryAssetClass())) return false;
			if (!ListEquals.listEquals(secondaryAssetClass, _that.getSecondaryAssetClass())) return false;
			if (!Objects.equals(productQualifier, _that.getProductQualifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (primaryAssetClass != null ? primaryAssetClass.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (secondaryAssetClass != null ? secondaryAssetClass.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (productQualifier != null ? productQualifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductTaxonomy {" +
				"primaryAssetClass=" + this.primaryAssetClass + ", " +
				"secondaryAssetClass=" + this.secondaryAssetClass + ", " +
				"productQualifier=" + this.productQualifier +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ProductTaxonomy  ***********************/
	class ProductTaxonomyBuilderImpl extends Taxonomy.TaxonomyBuilderImpl  implements ProductTaxonomy.ProductTaxonomyBuilder {
	
		protected FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder primaryAssetClass;
		protected List<FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder> secondaryAssetClass = new ArrayList<>();
		protected String productQualifier;
	
		public ProductTaxonomyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("primaryAssetClass")
		public FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getPrimaryAssetClass() {
			return primaryAssetClass;
		}
		
		@Override
		public FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getOrCreatePrimaryAssetClass() {
			FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder result;
			if (primaryAssetClass!=null) {
				result = primaryAssetClass;
			}
			else {
				result = primaryAssetClass = FieldWithMetaAssetClassEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("secondaryAssetClass")
		public List<? extends FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder> getSecondaryAssetClass() {
			return secondaryAssetClass;
		}
		
		public FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder getOrCreateSecondaryAssetClass(int _index) {
		
			if (secondaryAssetClass==null) {
				this.secondaryAssetClass = new ArrayList<>();
			}
			FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder result;
			return getIndex(secondaryAssetClass, _index, () -> {
						FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder newSecondaryAssetClass = FieldWithMetaAssetClassEnum.builder();
						return newSecondaryAssetClass;
					});
		}
		
		@Override
		@RosettaAttribute("productQualifier")
		public String getProductQualifier() {
			return productQualifier;
		}
		
	
		@Override
		@RosettaAttribute("source")
		public ProductTaxonomy.ProductTaxonomyBuilder setSource(TaxonomySourceEnum source) {
			this.source = source==null?null:source;
			return this;
		}
		@Override
		@RosettaAttribute("value")
		public ProductTaxonomy.ProductTaxonomyBuilder setValue(TaxonomyValue value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("primaryAssetClass")
		public ProductTaxonomy.ProductTaxonomyBuilder setPrimaryAssetClass(FieldWithMetaAssetClassEnum primaryAssetClass) {
			this.primaryAssetClass = primaryAssetClass==null?null:primaryAssetClass.toBuilder();
			return this;
		}
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder setPrimaryAssetClassValue(AssetClassEnum primaryAssetClass) {
			this.getOrCreatePrimaryAssetClass().setValue(primaryAssetClass);
			return this;
		}
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(FieldWithMetaAssetClassEnum secondaryAssetClass) {
			if (secondaryAssetClass!=null) this.secondaryAssetClass.add(secondaryAssetClass.toBuilder());
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(FieldWithMetaAssetClassEnum secondaryAssetClass, int _idx) {
			getIndex(this.secondaryAssetClass, _idx, () -> secondaryAssetClass.toBuilder());
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(AssetClassEnum secondaryAssetClass) {
			this.getOrCreateSecondaryAssetClass(-1).setValue(secondaryAssetClass);
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(AssetClassEnum secondaryAssetClass, int _idx) {
			this.getOrCreateSecondaryAssetClass(_idx).setValue(secondaryAssetClass);
			return this;
		}
		@Override 
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClass(List<? extends FieldWithMetaAssetClassEnum> secondaryAssetClasss) {
			if (secondaryAssetClasss != null) {
				for (FieldWithMetaAssetClassEnum toAdd : secondaryAssetClasss) {
					this.secondaryAssetClass.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("secondaryAssetClass")
		public ProductTaxonomy.ProductTaxonomyBuilder setSecondaryAssetClass(List<? extends FieldWithMetaAssetClassEnum> secondaryAssetClasss) {
			if (secondaryAssetClasss == null)  {
				this.secondaryAssetClass = new ArrayList<>();
			}
			else {
				this.secondaryAssetClass = secondaryAssetClasss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder addSecondaryAssetClassValue(List<? extends AssetClassEnum> secondaryAssetClasss) {
			if (secondaryAssetClasss != null) {
				for (AssetClassEnum toAdd : secondaryAssetClasss) {
					this.addSecondaryAssetClassValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder setSecondaryAssetClassValue(List<? extends AssetClassEnum> secondaryAssetClasss) {
			this.secondaryAssetClass.clear();
			if (secondaryAssetClasss!=null) {
				secondaryAssetClasss.forEach(this::addSecondaryAssetClassValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("productQualifier")
		public ProductTaxonomy.ProductTaxonomyBuilder setProductQualifier(String productQualifier) {
			this.productQualifier = productQualifier==null?null:productQualifier;
			return this;
		}
		
		@Override
		public ProductTaxonomy build() {
			return new ProductTaxonomy.ProductTaxonomyImpl(this);
		}
		
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder prune() {
			super.prune();
			if (primaryAssetClass!=null && !primaryAssetClass.prune().hasData()) primaryAssetClass = null;
			secondaryAssetClass = secondaryAssetClass.stream().filter(b->b!=null).<FieldWithMetaAssetClassEnum.FieldWithMetaAssetClassEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getPrimaryAssetClass()!=null) return true;
			if (getSecondaryAssetClass()!=null && !getSecondaryAssetClass().isEmpty()) return true;
			if (getProductQualifier()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProductTaxonomy.ProductTaxonomyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ProductTaxonomy.ProductTaxonomyBuilder o = (ProductTaxonomy.ProductTaxonomyBuilder) other;
			
			merger.mergeRosetta(getPrimaryAssetClass(), o.getPrimaryAssetClass(), this::setPrimaryAssetClass);
			merger.mergeRosetta(getSecondaryAssetClass(), o.getSecondaryAssetClass(), this::getOrCreateSecondaryAssetClass);
			
			merger.mergeBasic(getProductQualifier(), o.getProductQualifier(), this::setProductQualifier);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ProductTaxonomy _that = getType().cast(o);
		
			if (!Objects.equals(primaryAssetClass, _that.getPrimaryAssetClass())) return false;
			if (!ListEquals.listEquals(secondaryAssetClass, _that.getSecondaryAssetClass())) return false;
			if (!Objects.equals(productQualifier, _that.getProductQualifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (primaryAssetClass != null ? primaryAssetClass.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (secondaryAssetClass != null ? secondaryAssetClass.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (productQualifier != null ? productQualifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProductTaxonomyBuilder {" +
				"primaryAssetClass=" + this.primaryAssetClass + ", " +
				"secondaryAssetClass=" + this.secondaryAssetClass + ", " +
				"productQualifier=" + this.productQualifier +
			'}' + " " + super.toString();
		}
	}
}
