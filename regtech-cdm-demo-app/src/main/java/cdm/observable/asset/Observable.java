package cdm.observable.asset;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaCommodity;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaCommodity.FieldWithMetaCommodityBuilder;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.Observable;
import cdm.observable.asset.Observable.ObservableBuilder;
import cdm.observable.asset.Observable.ObservableBuilderImpl;
import cdm.observable.asset.Observable.ObservableImpl;
import cdm.observable.asset.OptionReferenceTypeEnum;
import cdm.observable.asset.QuotedCurrencyPair;
import cdm.observable.asset.meta.ObservableMeta;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder;
import cdm.observable.asset.metafields.FieldWithMetaQuotedCurrencyPair;
import cdm.observable.asset.metafields.FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.Key;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the object to be observed for a price, it could be an asset or a reference.
 * @version ${project.version}
 */
@RosettaDataType(value="Observable", builder=Observable.ObservableBuilderImpl.class, version="${project.version}")
public interface Observable extends RosettaModelObject, GlobalKey {

	ObservableMeta metaData = new ObservableMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a floating rate index and tenor.
	 */
	FieldWithMetaFloatingRateOption getRateOption();
	/**
	 * Identifies a commodity by referencing a product identifier.
	 */
	FieldWithMetaCommodity getCommodity();
	/**
	 * Comprises of an identifier and a source. The associated metadata key denotes the ability to associate a hash value to the ProductIdentifier instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
	 */
	List<? extends FieldWithMetaProductIdentifier> getProductIdentifier();
	/**
	 * Describes the composition of a rate that has been quoted or is to be quoted, including the two currencies and the quotation relationship between the two currencies.
	 */
	FieldWithMetaQuotedCurrencyPair getCurrencyPair();
	/**
	 * The underlying contract which is referenced when determining the final settlement price of the instrument. Eg. Rolling Front Month Future; Spot etc.
	 */
	OptionReferenceTypeEnum getOptionReferenceType();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Observable build();
	
	Observable.ObservableBuilder toBuilder();
	
	static Observable.ObservableBuilder builder() {
		return new Observable.ObservableBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Observable> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Observable> getType() {
		return Observable.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("rateOption"), processor, FieldWithMetaFloatingRateOption.class, getRateOption());
		processRosetta(path.newSubPath("commodity"), processor, FieldWithMetaCommodity.class, getCommodity());
		processRosetta(path.newSubPath("productIdentifier"), processor, FieldWithMetaProductIdentifier.class, getProductIdentifier());
		processRosetta(path.newSubPath("currencyPair"), processor, FieldWithMetaQuotedCurrencyPair.class, getCurrencyPair());
		processor.processBasic(path.newSubPath("optionReferenceType"), OptionReferenceTypeEnum.class, getOptionReferenceType(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservableBuilder extends Observable, RosettaModelObjectBuilder {
		FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder getOrCreateRateOption();
		FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder getRateOption();
		FieldWithMetaCommodity.FieldWithMetaCommodityBuilder getOrCreateCommodity();
		FieldWithMetaCommodity.FieldWithMetaCommodityBuilder getCommodity();
		FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder getOrCreateProductIdentifier(int _index);
		List<? extends FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder> getProductIdentifier();
		FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder getOrCreateCurrencyPair();
		FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder getCurrencyPair();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Observable.ObservableBuilder setRateOption(FieldWithMetaFloatingRateOption rateOption0);
		Observable.ObservableBuilder setRateOptionValue(FloatingRateOption rateOption1);
		Observable.ObservableBuilder setCommodity(FieldWithMetaCommodity commodity0);
		Observable.ObservableBuilder setCommodityValue(Commodity commodity1);
		Observable.ObservableBuilder addProductIdentifier(FieldWithMetaProductIdentifier productIdentifier0);
		Observable.ObservableBuilder addProductIdentifier(FieldWithMetaProductIdentifier productIdentifier1, int _idx);
		Observable.ObservableBuilder addProductIdentifierValue(ProductIdentifier productIdentifier2);
		Observable.ObservableBuilder addProductIdentifierValue(ProductIdentifier productIdentifier3, int _idx);
		Observable.ObservableBuilder addProductIdentifier(List<? extends FieldWithMetaProductIdentifier> productIdentifier4);
		Observable.ObservableBuilder setProductIdentifier(List<? extends FieldWithMetaProductIdentifier> productIdentifier5);
		Observable.ObservableBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier6);
		Observable.ObservableBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifier7);
		Observable.ObservableBuilder setCurrencyPair(FieldWithMetaQuotedCurrencyPair currencyPair0);
		Observable.ObservableBuilder setCurrencyPairValue(QuotedCurrencyPair currencyPair1);
		Observable.ObservableBuilder setOptionReferenceType(OptionReferenceTypeEnum optionReferenceType);
		Observable.ObservableBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("rateOption"), processor, FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder.class, getRateOption());
			processRosetta(path.newSubPath("commodity"), processor, FieldWithMetaCommodity.FieldWithMetaCommodityBuilder.class, getCommodity());
			processRosetta(path.newSubPath("productIdentifier"), processor, FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder.class, getProductIdentifier());
			processRosetta(path.newSubPath("currencyPair"), processor, FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder.class, getCurrencyPair());
			processor.processBasic(path.newSubPath("optionReferenceType"), OptionReferenceTypeEnum.class, getOptionReferenceType(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Observable.ObservableBuilder prune();
	}

	/*********************** Immutable Implementation of Observable  ***********************/
	class ObservableImpl implements Observable {
		private final FieldWithMetaFloatingRateOption rateOption;
		private final FieldWithMetaCommodity commodity;
		private final List<? extends FieldWithMetaProductIdentifier> productIdentifier;
		private final FieldWithMetaQuotedCurrencyPair currencyPair;
		private final OptionReferenceTypeEnum optionReferenceType;
		private final MetaFields meta;
		
		protected ObservableImpl(Observable.ObservableBuilder builder) {
			this.rateOption = ofNullable(builder.getRateOption()).map(f->f.build()).orElse(null);
			this.commodity = ofNullable(builder.getCommodity()).map(f->f.build()).orElse(null);
			this.productIdentifier = ofNullable(builder.getProductIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.currencyPair = ofNullable(builder.getCurrencyPair()).map(f->f.build()).orElse(null);
			this.optionReferenceType = builder.getOptionReferenceType();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("rateOption")
		public FieldWithMetaFloatingRateOption getRateOption() {
			return rateOption;
		}
		
		@Override
		@RosettaAttribute("commodity")
		public FieldWithMetaCommodity getCommodity() {
			return commodity;
		}
		
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends FieldWithMetaProductIdentifier> getProductIdentifier() {
			return productIdentifier;
		}
		
		@Override
		@RosettaAttribute("currencyPair")
		public FieldWithMetaQuotedCurrencyPair getCurrencyPair() {
			return currencyPair;
		}
		
		@Override
		@RosettaAttribute("optionReferenceType")
		public OptionReferenceTypeEnum getOptionReferenceType() {
			return optionReferenceType;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Observable build() {
			return this;
		}
		
		@Override
		public Observable.ObservableBuilder toBuilder() {
			Observable.ObservableBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Observable.ObservableBuilder builder) {
			ofNullable(getRateOption()).ifPresent(builder::setRateOption);
			ofNullable(getCommodity()).ifPresent(builder::setCommodity);
			ofNullable(getProductIdentifier()).ifPresent(builder::setProductIdentifier);
			ofNullable(getCurrencyPair()).ifPresent(builder::setCurrencyPair);
			ofNullable(getOptionReferenceType()).ifPresent(builder::setOptionReferenceType);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Observable _that = getType().cast(o);
		
			if (!Objects.equals(rateOption, _that.getRateOption())) return false;
			if (!Objects.equals(commodity, _that.getCommodity())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			if (!Objects.equals(currencyPair, _that.getCurrencyPair())) return false;
			if (!Objects.equals(optionReferenceType, _that.getOptionReferenceType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rateOption != null ? rateOption.hashCode() : 0);
			_result = 31 * _result + (commodity != null ? commodity.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			_result = 31 * _result + (currencyPair != null ? currencyPair.hashCode() : 0);
			_result = 31 * _result + (optionReferenceType != null ? optionReferenceType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Observable {" +
				"rateOption=" + this.rateOption + ", " +
				"commodity=" + this.commodity + ", " +
				"productIdentifier=" + this.productIdentifier + ", " +
				"currencyPair=" + this.currencyPair + ", " +
				"optionReferenceType=" + this.optionReferenceType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Observable  ***********************/
	class ObservableBuilderImpl implements Observable.ObservableBuilder, GlobalKeyBuilder {
	
		protected FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder rateOption;
		protected FieldWithMetaCommodity.FieldWithMetaCommodityBuilder commodity;
		protected List<FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder> productIdentifier = new ArrayList<>();
		protected FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder currencyPair;
		protected OptionReferenceTypeEnum optionReferenceType;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ObservableBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rateOption")
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder getRateOption() {
			return rateOption;
		}
		
		@Override
		public FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder getOrCreateRateOption() {
			FieldWithMetaFloatingRateOption.FieldWithMetaFloatingRateOptionBuilder result;
			if (rateOption!=null) {
				result = rateOption;
			}
			else {
				result = rateOption = FieldWithMetaFloatingRateOption.builder();
				result.getOrCreateMeta().toBuilder().addKey(Key.builder().setScope("DOCUMENT"));
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commodity")
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder getCommodity() {
			return commodity;
		}
		
		@Override
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder getOrCreateCommodity() {
			FieldWithMetaCommodity.FieldWithMetaCommodityBuilder result;
			if (commodity!=null) {
				result = commodity;
			}
			else {
				result = commodity = FieldWithMetaCommodity.builder();
				result.getOrCreateMeta().toBuilder().addKey(Key.builder().setScope("DOCUMENT"));
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("productIdentifier")
		public List<? extends FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder> getProductIdentifier() {
			return productIdentifier;
		}
		
		public FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder getOrCreateProductIdentifier(int _index) {
		
			if (productIdentifier==null) {
				this.productIdentifier = new ArrayList<>();
			}
			FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder result;
			return getIndex(productIdentifier, _index, () -> {
						FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder newProductIdentifier = FieldWithMetaProductIdentifier.builder();
						newProductIdentifier.getOrCreateMeta().addKey(Key.builder().setScope("DOCUMENT"));
						return newProductIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("currencyPair")
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder getCurrencyPair() {
			return currencyPair;
		}
		
		@Override
		public FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder getOrCreateCurrencyPair() {
			FieldWithMetaQuotedCurrencyPair.FieldWithMetaQuotedCurrencyPairBuilder result;
			if (currencyPair!=null) {
				result = currencyPair;
			}
			else {
				result = currencyPair = FieldWithMetaQuotedCurrencyPair.builder();
				result.getOrCreateMeta().toBuilder().addKey(Key.builder().setScope("DOCUMENT"));
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionReferenceType")
		public OptionReferenceTypeEnum getOptionReferenceType() {
			return optionReferenceType;
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
		@RosettaAttribute("rateOption")
		public Observable.ObservableBuilder setRateOption(FieldWithMetaFloatingRateOption rateOption) {
			this.rateOption = rateOption==null?null:rateOption.toBuilder();
			return this;
		}
		@Override
		public Observable.ObservableBuilder setRateOptionValue(FloatingRateOption rateOption) {
			this.getOrCreateRateOption().setValue(rateOption);
			return this;
		}
		@Override
		@RosettaAttribute("commodity")
		public Observable.ObservableBuilder setCommodity(FieldWithMetaCommodity commodity) {
			this.commodity = commodity==null?null:commodity.toBuilder();
			return this;
		}
		@Override
		public Observable.ObservableBuilder setCommodityValue(Commodity commodity) {
			this.getOrCreateCommodity().setValue(commodity);
			return this;
		}
		@Override
		public Observable.ObservableBuilder addProductIdentifier(FieldWithMetaProductIdentifier productIdentifier) {
			if (productIdentifier!=null) this.productIdentifier.add(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Observable.ObservableBuilder addProductIdentifier(FieldWithMetaProductIdentifier productIdentifier, int _idx) {
			getIndex(this.productIdentifier, _idx, () -> productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Observable.ObservableBuilder addProductIdentifierValue(ProductIdentifier productIdentifier) {
			this.getOrCreateProductIdentifier(-1).setValue(productIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Observable.ObservableBuilder addProductIdentifierValue(ProductIdentifier productIdentifier, int _idx) {
			this.getOrCreateProductIdentifier(_idx).setValue(productIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Observable.ObservableBuilder addProductIdentifier(List<? extends FieldWithMetaProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (FieldWithMetaProductIdentifier toAdd : productIdentifiers) {
					this.productIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("productIdentifier")
		public Observable.ObservableBuilder setProductIdentifier(List<? extends FieldWithMetaProductIdentifier> productIdentifiers) {
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
		public Observable.ObservableBuilder addProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			if (productIdentifiers != null) {
				for (ProductIdentifier toAdd : productIdentifiers) {
					this.addProductIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Observable.ObservableBuilder setProductIdentifierValue(List<? extends ProductIdentifier> productIdentifiers) {
			this.productIdentifier.clear();
			if (productIdentifiers!=null) {
				productIdentifiers.forEach(this::addProductIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("currencyPair")
		public Observable.ObservableBuilder setCurrencyPair(FieldWithMetaQuotedCurrencyPair currencyPair) {
			this.currencyPair = currencyPair==null?null:currencyPair.toBuilder();
			return this;
		}
		@Override
		public Observable.ObservableBuilder setCurrencyPairValue(QuotedCurrencyPair currencyPair) {
			this.getOrCreateCurrencyPair().setValue(currencyPair);
			return this;
		}
		@Override
		@RosettaAttribute("optionReferenceType")
		public Observable.ObservableBuilder setOptionReferenceType(OptionReferenceTypeEnum optionReferenceType) {
			this.optionReferenceType = optionReferenceType==null?null:optionReferenceType;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Observable.ObservableBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Observable build() {
			return new Observable.ObservableImpl(this);
		}
		
		@Override
		public Observable.ObservableBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Observable.ObservableBuilder prune() {
			if (rateOption!=null && !rateOption.prune().hasData()) rateOption = null;
			if (commodity!=null && !commodity.prune().hasData()) commodity = null;
			productIdentifier = productIdentifier.stream().filter(b->b!=null).<FieldWithMetaProductIdentifier.FieldWithMetaProductIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (currencyPair!=null && !currencyPair.prune().hasData()) currencyPair = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRateOption()!=null && getRateOption().hasData()) return true;
			if (getCommodity()!=null && getCommodity().hasData()) return true;
			if (getProductIdentifier()!=null && getProductIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCurrencyPair()!=null && getCurrencyPair().hasData()) return true;
			if (getOptionReferenceType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Observable.ObservableBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Observable.ObservableBuilder o = (Observable.ObservableBuilder) other;
			
			merger.mergeRosetta(getRateOption(), o.getRateOption(), this::setRateOption);
			merger.mergeRosetta(getCommodity(), o.getCommodity(), this::setCommodity);
			merger.mergeRosetta(getProductIdentifier(), o.getProductIdentifier(), this::getOrCreateProductIdentifier);
			merger.mergeRosetta(getCurrencyPair(), o.getCurrencyPair(), this::setCurrencyPair);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getOptionReferenceType(), o.getOptionReferenceType(), this::setOptionReferenceType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Observable _that = getType().cast(o);
		
			if (!Objects.equals(rateOption, _that.getRateOption())) return false;
			if (!Objects.equals(commodity, _that.getCommodity())) return false;
			if (!ListEquals.listEquals(productIdentifier, _that.getProductIdentifier())) return false;
			if (!Objects.equals(currencyPair, _that.getCurrencyPair())) return false;
			if (!Objects.equals(optionReferenceType, _that.getOptionReferenceType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rateOption != null ? rateOption.hashCode() : 0);
			_result = 31 * _result + (commodity != null ? commodity.hashCode() : 0);
			_result = 31 * _result + (productIdentifier != null ? productIdentifier.hashCode() : 0);
			_result = 31 * _result + (currencyPair != null ? currencyPair.hashCode() : 0);
			_result = 31 * _result + (optionReferenceType != null ? optionReferenceType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservableBuilder {" +
				"rateOption=" + this.rateOption + ", " +
				"commodity=" + this.commodity + ", " +
				"productIdentifier=" + this.productIdentifier + ", " +
				"currencyPair=" + this.currencyPair + ", " +
				"optionReferenceType=" + this.optionReferenceType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
