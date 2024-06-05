package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.Commodity.CommodityBuilder;
import cdm.base.staticdata.asset.common.Commodity.CommodityBuilderImpl;
import cdm.base.staticdata.asset.common.Commodity.CommodityImpl;
import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.DeliveryDateParameters;
import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilder;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseBuilderImpl;
import cdm.base.staticdata.asset.common.ProductBase.ProductBaseImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.meta.CommodityMeta;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder;
import cdm.observable.asset.QuotationSideEnum;
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
 * Identifies a specific commodity by referencing a product identifier or by a product definition.
 * @version ${project.version}
 */
@RosettaDataType(value="Commodity", builder=Commodity.CommodityBuilderImpl.class, version="${project.version}")
public interface Commodity extends ProductBase {

	CommodityMeta metaData = new CommodityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the commodity underlier in the event that no ISDA Commodity Reference Benchmark exists.
	 */
	CommodityProductDefinition getCommodityProductDefinition();
	/**
	 * Describes the required quote type of the underlying price that will be observed. Example values include &#39;Bid, &#39;Ask&#39;, &#39;Settlement&#39; (for a futures contract) and &#39;WeightedAverage&#39; (for some published prices and indices).
	 */
	QuotationSideEnum getPriceQuoteType();
	/**
	 * Specifies the parameters for identifying the relevant contract date when the commodity reference price is a futures contract.
	 */
	DeliveryDateParameters getDeliveryDateReference();
	/**
	 * Provides additional information about the commodity underlier.
	 */
	String getDescription();

	/*********************** Build Methods  ***********************/
	Commodity build();
	
	Commodity.CommodityBuilder toBuilder();
	
	static Commodity.CommodityBuilder builder() {
		return new Commodity.CommodityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Commodity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Commodity> getType() {
		return Commodity.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.class, getProductTaxonomy());
		processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.class, getProductIdentifier());
		processRosetta(path.newSubPath("commodityProductDefinition"), processor, CommodityProductDefinition.class, getCommodityProductDefinition());
		processor.processBasic(path.newSubPath("priceQuoteType"), QuotationSideEnum.class, getPriceQuoteType(), this);
		processRosetta(path.newSubPath("deliveryDateReference"), processor, DeliveryDateParameters.class, getDeliveryDateReference());
		processor.processBasic(path.newSubPath("description"), String.class, getDescription(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CommodityBuilder extends Commodity, ProductBase.ProductBaseBuilder, RosettaModelObjectBuilder {
		CommodityProductDefinition.CommodityProductDefinitionBuilder getOrCreateCommodityProductDefinition();
		CommodityProductDefinition.CommodityProductDefinitionBuilder getCommodityProductDefinition();
		DeliveryDateParameters.DeliveryDateParametersBuilder getOrCreateDeliveryDateReference();
		DeliveryDateParameters.DeliveryDateParametersBuilder getDeliveryDateReference();
		Commodity.CommodityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy0);
		Commodity.CommodityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy1, int _idx);
		Commodity.CommodityBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy2);
		Commodity.CommodityBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomy3);
		Commodity.CommodityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier0);
		Commodity.CommodityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier1, int _idx);
		Commodity.CommodityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		Commodity.CommodityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		Commodity.CommodityBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier4);
		Commodity.CommodityBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifier5);
		Commodity.CommodityBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		Commodity.CommodityBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);
		Commodity.CommodityBuilder setCommodityProductDefinition(CommodityProductDefinition commodityProductDefinition);
		Commodity.CommodityBuilder setPriceQuoteType(QuotationSideEnum priceQuoteType);
		Commodity.CommodityBuilder setDeliveryDateReference(DeliveryDateParameters deliveryDateReference);
		Commodity.CommodityBuilder setDescription(String description);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productTaxonomy"), processor, ProductTaxonomy.ProductTaxonomyBuilder.class, getProductTaxonomy());
			processRosetta(path.newSubPath("productIdentifier"), processor, ReferenceWithMetaProductIdentifier.ReferenceWithMetaProductIdentifierBuilder.class, getProductIdentifier());
			processRosetta(path.newSubPath("commodityProductDefinition"), processor, CommodityProductDefinition.CommodityProductDefinitionBuilder.class, getCommodityProductDefinition());
			processor.processBasic(path.newSubPath("priceQuoteType"), QuotationSideEnum.class, getPriceQuoteType(), this);
			processRosetta(path.newSubPath("deliveryDateReference"), processor, DeliveryDateParameters.DeliveryDateParametersBuilder.class, getDeliveryDateReference());
			processor.processBasic(path.newSubPath("description"), String.class, getDescription(), this);
		}
		

		Commodity.CommodityBuilder prune();
	}

	/*********************** Immutable Implementation of Commodity  ***********************/
	class CommodityImpl extends ProductBase.ProductBaseImpl implements Commodity {
		private final CommodityProductDefinition commodityProductDefinition;
		private final QuotationSideEnum priceQuoteType;
		private final DeliveryDateParameters deliveryDateReference;
		private final String description;
		
		protected CommodityImpl(Commodity.CommodityBuilder builder) {
			super(builder);
			this.commodityProductDefinition = ofNullable(builder.getCommodityProductDefinition()).map(f->f.build()).orElse(null);
			this.priceQuoteType = builder.getPriceQuoteType();
			this.deliveryDateReference = ofNullable(builder.getDeliveryDateReference()).map(f->f.build()).orElse(null);
			this.description = builder.getDescription();
		}
		
		@Override
		@RosettaAttribute("commodityProductDefinition")
		public CommodityProductDefinition getCommodityProductDefinition() {
			return commodityProductDefinition;
		}
		
		@Override
		@RosettaAttribute("priceQuoteType")
		public QuotationSideEnum getPriceQuoteType() {
			return priceQuoteType;
		}
		
		@Override
		@RosettaAttribute("deliveryDateReference")
		public DeliveryDateParameters getDeliveryDateReference() {
			return deliveryDateReference;
		}
		
		@Override
		@RosettaAttribute("description")
		public String getDescription() {
			return description;
		}
		
		@Override
		public Commodity build() {
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder toBuilder() {
			Commodity.CommodityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Commodity.CommodityBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getCommodityProductDefinition()).ifPresent(builder::setCommodityProductDefinition);
			ofNullable(getPriceQuoteType()).ifPresent(builder::setPriceQuoteType);
			ofNullable(getDeliveryDateReference()).ifPresent(builder::setDeliveryDateReference);
			ofNullable(getDescription()).ifPresent(builder::setDescription);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Commodity _that = getType().cast(o);
		
			if (!Objects.equals(commodityProductDefinition, _that.getCommodityProductDefinition())) return false;
			if (!Objects.equals(priceQuoteType, _that.getPriceQuoteType())) return false;
			if (!Objects.equals(deliveryDateReference, _that.getDeliveryDateReference())) return false;
			if (!Objects.equals(description, _that.getDescription())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (commodityProductDefinition != null ? commodityProductDefinition.hashCode() : 0);
			_result = 31 * _result + (priceQuoteType != null ? priceQuoteType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (deliveryDateReference != null ? deliveryDateReference.hashCode() : 0);
			_result = 31 * _result + (description != null ? description.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Commodity {" +
				"commodityProductDefinition=" + this.commodityProductDefinition + ", " +
				"priceQuoteType=" + this.priceQuoteType + ", " +
				"deliveryDateReference=" + this.deliveryDateReference + ", " +
				"description=" + this.description +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Commodity  ***********************/
	class CommodityBuilderImpl extends ProductBase.ProductBaseBuilderImpl  implements Commodity.CommodityBuilder {
	
		protected CommodityProductDefinition.CommodityProductDefinitionBuilder commodityProductDefinition;
		protected QuotationSideEnum priceQuoteType;
		protected DeliveryDateParameters.DeliveryDateParametersBuilder deliveryDateReference;
		protected String description;
	
		public CommodityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("commodityProductDefinition")
		public CommodityProductDefinition.CommodityProductDefinitionBuilder getCommodityProductDefinition() {
			return commodityProductDefinition;
		}
		
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder getOrCreateCommodityProductDefinition() {
			CommodityProductDefinition.CommodityProductDefinitionBuilder result;
			if (commodityProductDefinition!=null) {
				result = commodityProductDefinition;
			}
			else {
				result = commodityProductDefinition = CommodityProductDefinition.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceQuoteType")
		public QuotationSideEnum getPriceQuoteType() {
			return priceQuoteType;
		}
		
		@Override
		@RosettaAttribute("deliveryDateReference")
		public DeliveryDateParameters.DeliveryDateParametersBuilder getDeliveryDateReference() {
			return deliveryDateReference;
		}
		
		@Override
		public DeliveryDateParameters.DeliveryDateParametersBuilder getOrCreateDeliveryDateReference() {
			DeliveryDateParameters.DeliveryDateParametersBuilder result;
			if (deliveryDateReference!=null) {
				result = deliveryDateReference;
			}
			else {
				result = deliveryDateReference = DeliveryDateParameters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("description")
		public String getDescription() {
			return description;
		}
		
	
		@Override
		public Commodity.CommodityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy) {
			if (productTaxonomy!=null) this.productTaxonomy.add(productTaxonomy.toBuilder());
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder addProductTaxonomy(ProductTaxonomy productTaxonomy, int _idx) {
			getIndex(this.productTaxonomy, _idx, () -> productTaxonomy.toBuilder());
			return this;
		}
		@Override 
		public Commodity.CommodityBuilder addProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
			if (productTaxonomys != null) {
				for (ProductTaxonomy toAdd : productTaxonomys) {
					this.productTaxonomy.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productTaxonomy")
		public Commodity.CommodityBuilder setProductTaxonomy(List<? extends ProductTaxonomy> productTaxonomys) {
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
		public Commodity.CommodityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder addProductIdentifier(ReferenceWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Commodity.CommodityBuilder addProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ReferenceWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public Commodity.CommodityBuilder setProductIdentifier(List<? extends ReferenceWithMetaProductIdentifier> productIdentifiers) {
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
		public Commodity.CommodityBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Commodity.CommodityBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("commodityProductDefinition")
		public Commodity.CommodityBuilder setCommodityProductDefinition(CommodityProductDefinition commodityProductDefinition) {
			this.commodityProductDefinition = commodityProductDefinition==null?null:commodityProductDefinition.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuoteType")
		public Commodity.CommodityBuilder setPriceQuoteType(QuotationSideEnum priceQuoteType) {
			this.priceQuoteType = priceQuoteType==null?null:priceQuoteType;
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDateReference")
		public Commodity.CommodityBuilder setDeliveryDateReference(DeliveryDateParameters deliveryDateReference) {
			this.deliveryDateReference = deliveryDateReference==null?null:deliveryDateReference.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("description")
		public Commodity.CommodityBuilder setDescription(String description) {
			this.description = description==null?null:description;
			return this;
		}
		
		@Override
		public Commodity build() {
			return new Commodity.CommodityImpl(this);
		}
		
		@Override
		public Commodity.CommodityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Commodity.CommodityBuilder prune() {
			super.prune();
			if (commodityProductDefinition!=null && !commodityProductDefinition.prune().hasData()) commodityProductDefinition = null;
			if (deliveryDateReference!=null && !deliveryDateReference.prune().hasData()) deliveryDateReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getCommodityProductDefinition()!=null && getCommodityProductDefinition().hasData()) return true;
			if (getPriceQuoteType()!=null) return true;
			if (getDeliveryDateReference()!=null && getDeliveryDateReference().hasData()) return true;
			if (getDescription()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Commodity.CommodityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Commodity.CommodityBuilder o = (Commodity.CommodityBuilder) other;
			
			merger.mergeRosetta(getCommodityProductDefinition(), o.getCommodityProductDefinition(), this::setCommodityProductDefinition);
			merger.mergeRosetta(getDeliveryDateReference(), o.getDeliveryDateReference(), this::setDeliveryDateReference);
			
			merger.mergeBasic(getPriceQuoteType(), o.getPriceQuoteType(), this::setPriceQuoteType);
			merger.mergeBasic(getDescription(), o.getDescription(), this::setDescription);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Commodity _that = getType().cast(o);
		
			if (!Objects.equals(commodityProductDefinition, _that.getCommodityProductDefinition())) return false;
			if (!Objects.equals(priceQuoteType, _that.getPriceQuoteType())) return false;
			if (!Objects.equals(deliveryDateReference, _that.getDeliveryDateReference())) return false;
			if (!Objects.equals(description, _that.getDescription())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (commodityProductDefinition != null ? commodityProductDefinition.hashCode() : 0);
			_result = 31 * _result + (priceQuoteType != null ? priceQuoteType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (deliveryDateReference != null ? deliveryDateReference.hashCode() : 0);
			_result = 31 * _result + (description != null ? description.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityBuilder {" +
				"commodityProductDefinition=" + this.commodityProductDefinition + ", " +
				"priceQuoteType=" + this.priceQuoteType + ", " +
				"deliveryDateReference=" + this.deliveryDateReference + ", " +
				"description=" + this.description +
			'}' + " " + super.toString();
		}
	}
}
