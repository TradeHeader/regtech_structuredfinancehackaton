package cdm.product.template;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder;
import cdm.base.staticdata.asset.common.AssetPool;
import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity.ReferenceWithMetaCommodityBuilder;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.asset.ForeignExchange;
import cdm.product.template.Basket;
import cdm.product.template.BasketConstituent;
import cdm.product.template.BasketConstituent.BasketConstituentBuilder;
import cdm.product.template.BasketConstituent.BasketConstituentBuilderImpl;
import cdm.product.template.BasketConstituent.BasketConstituentImpl;
import cdm.product.template.ContractualProduct;
import cdm.product.template.Product;
import cdm.product.template.Product.ProductBuilder;
import cdm.product.template.Product.ProductBuilderImpl;
import cdm.product.template.Product.ProductImpl;
import cdm.product.template.meta.BasketConstituentMeta;
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
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Identifies the constituents of the basket
 * @version ${project.version}
 */
@RosettaDataType(value="BasketConstituent", builder=BasketConstituent.BasketConstituentBuilderImpl.class, version="${project.version}")
public interface BasketConstituent extends Product {

	BasketConstituentMeta metaData = new BasketConstituentMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a quantity schedule to be associated to an individual underlier that is a basket constituent. The multiple cardinality is aligned to the one of the PriceQuantity-&gt;quantity that this quantity is referencing.
	 */
	List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> getQuantity();
	/**
	 * Specifies an initial price schedule to be associated to an individual underlier that is a basket constituent. The multiple cardinality is aligned to the one of the PriceQuantity-&gt;price that this price is referencing.
	 */
	List<? extends ReferenceWithMetaPriceSchedule> getInitialValuationPrice();
	/**
	 * Specifies an interim price schedule to be associated to an individual underlier that is a basket constituent. The multiple cardinality is aligned to the one of the PriceQuantity-&gt;price that this price is referencing.
	 */
	List<? extends ReferenceWithMetaPriceSchedule> getInterimValuationPrice();
	/**
	 * Specifies a final price schedule to be associated to an individual underlier that is a basket constituent. The multiple cardinality is aligned to the one of the PriceQuantity-&gt;price that this price is referencing.
	 */
	List<? extends ReferenceWithMetaPriceSchedule> getFinalValuationPrice();

	/*********************** Build Methods  ***********************/
	BasketConstituent build();
	
	BasketConstituent.BasketConstituentBuilder toBuilder();
	
	static BasketConstituent.BasketConstituentBuilder builder() {
		return new BasketConstituent.BasketConstituentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BasketConstituent> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BasketConstituent> getType() {
		return BasketConstituent.class;
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
		processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.class, getQuantity());
		processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInitialValuationPrice());
		processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInterimValuationPrice());
		processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getFinalValuationPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BasketConstituentBuilder extends BasketConstituent, Product.ProductBuilder, RosettaModelObjectBuilder {
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantity(int _index);
		List<? extends ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder> getQuantity();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice(int _index);
		List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getInitialValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice(int _index);
		List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getInterimValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice(int _index);
		List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getFinalValuationPrice();
		BasketConstituent.BasketConstituentBuilder setContractualProduct(ContractualProduct contractualProduct);
		BasketConstituent.BasketConstituentBuilder setIndex(Index index);
		BasketConstituent.BasketConstituentBuilder setLoan(Loan loan);
		BasketConstituent.BasketConstituentBuilder setAssetPool(AssetPool assetPool);
		BasketConstituent.BasketConstituentBuilder setForeignExchange(ForeignExchange foreignExchange);
		BasketConstituent.BasketConstituentBuilder setCommodity(ReferenceWithMetaCommodity commodity0);
		BasketConstituent.BasketConstituentBuilder setCommodityValue(Commodity commodity1);
		BasketConstituent.BasketConstituentBuilder setSecurity(Security security);
		BasketConstituent.BasketConstituentBuilder setBasket(Basket basket);
		BasketConstituent.BasketConstituentBuilder setMeta(MetaFields meta);
		BasketConstituent.BasketConstituentBuilder addQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity0);
		BasketConstituent.BasketConstituentBuilder addQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity1, int _idx);
		BasketConstituent.BasketConstituentBuilder addQuantityValue(NonNegativeQuantitySchedule quantity2);
		BasketConstituent.BasketConstituentBuilder addQuantityValue(NonNegativeQuantitySchedule quantity3, int _idx);
		BasketConstituent.BasketConstituentBuilder addQuantity(List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> quantity4);
		BasketConstituent.BasketConstituentBuilder setQuantity(List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> quantity5);
		BasketConstituent.BasketConstituentBuilder addQuantityValue(List<? extends NonNegativeQuantitySchedule> quantity6);
		BasketConstituent.BasketConstituentBuilder setQuantityValue(List<? extends NonNegativeQuantitySchedule> quantity7);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice0);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice1, int _idx);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(PriceSchedule initialValuationPrice2);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(PriceSchedule initialValuationPrice3, int _idx);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> initialValuationPrice4);
		BasketConstituent.BasketConstituentBuilder setInitialValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> initialValuationPrice5);
		BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(List<? extends PriceSchedule> initialValuationPrice6);
		BasketConstituent.BasketConstituentBuilder setInitialValuationPriceValue(List<? extends PriceSchedule> initialValuationPrice7);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice0);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice1, int _idx);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(PriceSchedule interimValuationPrice2);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(PriceSchedule interimValuationPrice3, int _idx);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> interimValuationPrice4);
		BasketConstituent.BasketConstituentBuilder setInterimValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> interimValuationPrice5);
		BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(List<? extends PriceSchedule> interimValuationPrice6);
		BasketConstituent.BasketConstituentBuilder setInterimValuationPriceValue(List<? extends PriceSchedule> interimValuationPrice7);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice0);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice1, int _idx);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(PriceSchedule finalValuationPrice2);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(PriceSchedule finalValuationPrice3, int _idx);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> finalValuationPrice4);
		BasketConstituent.BasketConstituentBuilder setFinalValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> finalValuationPrice5);
		BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(List<? extends PriceSchedule> finalValuationPrice6);
		BasketConstituent.BasketConstituentBuilder setFinalValuationPriceValue(List<? extends PriceSchedule> finalValuationPrice7);

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
			processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder.class, getQuantity());
			processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInitialValuationPrice());
			processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInterimValuationPrice());
			processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getFinalValuationPrice());
		}
		

		BasketConstituent.BasketConstituentBuilder prune();
	}

	/*********************** Immutable Implementation of BasketConstituent  ***********************/
	class BasketConstituentImpl extends Product.ProductImpl implements BasketConstituent {
		private final List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> quantity;
		private final List<? extends ReferenceWithMetaPriceSchedule> initialValuationPrice;
		private final List<? extends ReferenceWithMetaPriceSchedule> interimValuationPrice;
		private final List<? extends ReferenceWithMetaPriceSchedule> finalValuationPrice;
		
		protected BasketConstituentImpl(BasketConstituent.BasketConstituentBuilder builder) {
			super(builder);
			this.quantity = ofNullable(builder.getQuantity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.initialValuationPrice = ofNullable(builder.getInitialValuationPrice()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.interimValuationPrice = ofNullable(builder.getInterimValuationPrice()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.finalValuationPrice = ofNullable(builder.getFinalValuationPrice()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("quantity")
		public List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> getQuantity() {
			return quantity;
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule> getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		@Override
		@RosettaAttribute("interimValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule> getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		@Override
		@RosettaAttribute("finalValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule> getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		@Override
		public BasketConstituent build() {
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder toBuilder() {
			BasketConstituent.BasketConstituentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BasketConstituent.BasketConstituentBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
			ofNullable(getInitialValuationPrice()).ifPresent(builder::setInitialValuationPrice);
			ofNullable(getInterimValuationPrice()).ifPresent(builder::setInterimValuationPrice);
			ofNullable(getFinalValuationPrice()).ifPresent(builder::setFinalValuationPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			BasketConstituent _that = getType().cast(o);
		
			if (!ListEquals.listEquals(quantity, _that.getQuantity())) return false;
			if (!ListEquals.listEquals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!ListEquals.listEquals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!ListEquals.listEquals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BasketConstituent {" +
				"quantity=" + this.quantity + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of BasketConstituent  ***********************/
	class BasketConstituentBuilderImpl extends Product.ProductBuilderImpl  implements BasketConstituent.BasketConstituentBuilder {
	
		protected List<ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder> quantity = new ArrayList<>();
		protected List<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> initialValuationPrice = new ArrayList<>();
		protected List<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> interimValuationPrice = new ArrayList<>();
		protected List<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> finalValuationPrice = new ArrayList<>();
	
		public BasketConstituentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("quantity")
		public List<? extends ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder> getQuantity() {
			return quantity;
		}
		
		public ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantity(int _index) {
		
			if (quantity==null) {
				this.quantity = new ArrayList<>();
			}
			ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder result;
			return getIndex(quantity, _index, () -> {
						ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder newQuantity = ReferenceWithMetaNonNegativeQuantitySchedule.builder();
						return newQuantity;
					});
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice(int _index) {
		
			if (initialValuationPrice==null) {
				this.initialValuationPrice = new ArrayList<>();
			}
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			return getIndex(initialValuationPrice, _index, () -> {
						ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder newInitialValuationPrice = ReferenceWithMetaPriceSchedule.builder();
						return newInitialValuationPrice;
					});
		}
		
		@Override
		@RosettaAttribute("interimValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice(int _index) {
		
			if (interimValuationPrice==null) {
				this.interimValuationPrice = new ArrayList<>();
			}
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			return getIndex(interimValuationPrice, _index, () -> {
						ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder newInterimValuationPrice = ReferenceWithMetaPriceSchedule.builder();
						return newInterimValuationPrice;
					});
		}
		
		@Override
		@RosettaAttribute("finalValuationPrice")
		public List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice(int _index) {
		
			if (finalValuationPrice==null) {
				this.finalValuationPrice = new ArrayList<>();
			}
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			return getIndex(finalValuationPrice, _index, () -> {
						ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder newFinalValuationPrice = ReferenceWithMetaPriceSchedule.builder();
						return newFinalValuationPrice;
					});
		}
		
	
		@Override
		@RosettaAttribute("contractualProduct")
		public BasketConstituent.BasketConstituentBuilder setContractualProduct(ContractualProduct contractualProduct) {
			this.contractualProduct = contractualProduct==null?null:contractualProduct.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("index")
		public BasketConstituent.BasketConstituentBuilder setIndex(Index index) {
			this.index = index==null?null:index.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("loan")
		public BasketConstituent.BasketConstituentBuilder setLoan(Loan loan) {
			this.loan = loan==null?null:loan.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("assetPool")
		public BasketConstituent.BasketConstituentBuilder setAssetPool(AssetPool assetPool) {
			this.assetPool = assetPool==null?null:assetPool.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("foreignExchange")
		public BasketConstituent.BasketConstituentBuilder setForeignExchange(ForeignExchange foreignExchange) {
			this.foreignExchange = foreignExchange==null?null:foreignExchange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commodity")
		public BasketConstituent.BasketConstituentBuilder setCommodity(ReferenceWithMetaCommodity commodity) {
			this.commodity = commodity==null?null:commodity.toBuilder();
			return this;
		}
		@Override
		public BasketConstituent.BasketConstituentBuilder setCommodityValue(Commodity commodity) {
			this.getOrCreateCommodity().setValue(commodity);
			return this;
		}
		@Override
		@RosettaAttribute("security")
		public BasketConstituent.BasketConstituentBuilder setSecurity(Security security) {
			this.security = security==null?null:security.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("basket")
		public BasketConstituent.BasketConstituentBuilder setBasket(Basket basket) {
			this.basket = basket==null?null:basket.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public BasketConstituent.BasketConstituentBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		public BasketConstituent.BasketConstituentBuilder addQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity) {
			if (quantity!=null) this.quantity.add(quantity.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity, int _idx) {
			getIndex(this.quantity, _idx, () -> quantity.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addQuantityValue(NonNegativeQuantitySchedule quantity) {
			this.getOrCreateQuantity(-1).setValue(quantity.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addQuantityValue(NonNegativeQuantitySchedule quantity, int _idx) {
			this.getOrCreateQuantity(_idx).setValue(quantity.toBuilder());
			return this;
		}
		@Override 
		public BasketConstituent.BasketConstituentBuilder addQuantity(List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> quantitys) {
			if (quantitys != null) {
				for (ReferenceWithMetaNonNegativeQuantitySchedule toAdd : quantitys) {
					this.quantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("quantity")
		public BasketConstituent.BasketConstituentBuilder setQuantity(List<? extends ReferenceWithMetaNonNegativeQuantitySchedule> quantitys) {
			if (quantitys == null)  {
				this.quantity = new ArrayList<>();
			}
			else {
				this.quantity = quantitys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addQuantityValue(List<? extends NonNegativeQuantitySchedule> quantitys) {
			if (quantitys != null) {
				for (NonNegativeQuantitySchedule toAdd : quantitys) {
					this.addQuantityValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder setQuantityValue(List<? extends NonNegativeQuantitySchedule> quantitys) {
			this.quantity.clear();
			if (quantitys!=null) {
				quantitys.forEach(this::addQuantityValue);
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice) {
			if (initialValuationPrice!=null) this.initialValuationPrice.add(initialValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice, int _idx) {
			getIndex(this.initialValuationPrice, _idx, () -> initialValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(PriceSchedule initialValuationPrice) {
			this.getOrCreateInitialValuationPrice(-1).setValue(initialValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(PriceSchedule initialValuationPrice, int _idx) {
			this.getOrCreateInitialValuationPrice(_idx).setValue(initialValuationPrice.toBuilder());
			return this;
		}
		@Override 
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> initialValuationPrices) {
			if (initialValuationPrices != null) {
				for (ReferenceWithMetaPriceSchedule toAdd : initialValuationPrices) {
					this.initialValuationPrice.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("initialValuationPrice")
		public BasketConstituent.BasketConstituentBuilder setInitialValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> initialValuationPrices) {
			if (initialValuationPrices == null)  {
				this.initialValuationPrice = new ArrayList<>();
			}
			else {
				this.initialValuationPrice = initialValuationPrices.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInitialValuationPriceValue(List<? extends PriceSchedule> initialValuationPrices) {
			if (initialValuationPrices != null) {
				for (PriceSchedule toAdd : initialValuationPrices) {
					this.addInitialValuationPriceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder setInitialValuationPriceValue(List<? extends PriceSchedule> initialValuationPrices) {
			this.initialValuationPrice.clear();
			if (initialValuationPrices!=null) {
				initialValuationPrices.forEach(this::addInitialValuationPriceValue);
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice) {
			if (interimValuationPrice!=null) this.interimValuationPrice.add(interimValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice, int _idx) {
			getIndex(this.interimValuationPrice, _idx, () -> interimValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(PriceSchedule interimValuationPrice) {
			this.getOrCreateInterimValuationPrice(-1).setValue(interimValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(PriceSchedule interimValuationPrice, int _idx) {
			this.getOrCreateInterimValuationPrice(_idx).setValue(interimValuationPrice.toBuilder());
			return this;
		}
		@Override 
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> interimValuationPrices) {
			if (interimValuationPrices != null) {
				for (ReferenceWithMetaPriceSchedule toAdd : interimValuationPrices) {
					this.interimValuationPrice.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("interimValuationPrice")
		public BasketConstituent.BasketConstituentBuilder setInterimValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> interimValuationPrices) {
			if (interimValuationPrices == null)  {
				this.interimValuationPrice = new ArrayList<>();
			}
			else {
				this.interimValuationPrice = interimValuationPrices.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addInterimValuationPriceValue(List<? extends PriceSchedule> interimValuationPrices) {
			if (interimValuationPrices != null) {
				for (PriceSchedule toAdd : interimValuationPrices) {
					this.addInterimValuationPriceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder setInterimValuationPriceValue(List<? extends PriceSchedule> interimValuationPrices) {
			this.interimValuationPrice.clear();
			if (interimValuationPrices!=null) {
				interimValuationPrices.forEach(this::addInterimValuationPriceValue);
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice) {
			if (finalValuationPrice!=null) this.finalValuationPrice.add(finalValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice, int _idx) {
			getIndex(this.finalValuationPrice, _idx, () -> finalValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(PriceSchedule finalValuationPrice) {
			this.getOrCreateFinalValuationPrice(-1).setValue(finalValuationPrice.toBuilder());
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(PriceSchedule finalValuationPrice, int _idx) {
			this.getOrCreateFinalValuationPrice(_idx).setValue(finalValuationPrice.toBuilder());
			return this;
		}
		@Override 
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> finalValuationPrices) {
			if (finalValuationPrices != null) {
				for (ReferenceWithMetaPriceSchedule toAdd : finalValuationPrices) {
					this.finalValuationPrice.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("finalValuationPrice")
		public BasketConstituent.BasketConstituentBuilder setFinalValuationPrice(List<? extends ReferenceWithMetaPriceSchedule> finalValuationPrices) {
			if (finalValuationPrices == null)  {
				this.finalValuationPrice = new ArrayList<>();
			}
			else {
				this.finalValuationPrice = finalValuationPrices.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder addFinalValuationPriceValue(List<? extends PriceSchedule> finalValuationPrices) {
			if (finalValuationPrices != null) {
				for (PriceSchedule toAdd : finalValuationPrices) {
					this.addFinalValuationPriceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder setFinalValuationPriceValue(List<? extends PriceSchedule> finalValuationPrices) {
			this.finalValuationPrice.clear();
			if (finalValuationPrices!=null) {
				finalValuationPrices.forEach(this::addFinalValuationPriceValue);
			}
			return this;
		}
		
		
		@Override
		public BasketConstituent build() {
			return new BasketConstituent.BasketConstituentImpl(this);
		}
		
		@Override
		public BasketConstituent.BasketConstituentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BasketConstituent.BasketConstituentBuilder prune() {
			super.prune();
			quantity = quantity.stream().filter(b->b!=null).<ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			initialValuationPrice = initialValuationPrice.stream().filter(b->b!=null).<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			interimValuationPrice = interimValuationPrice.stream().filter(b->b!=null).<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			finalValuationPrice = finalValuationPrice.stream().filter(b->b!=null).<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getQuantity()!=null && getQuantity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getInitialValuationPrice()!=null && getInitialValuationPrice().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getInterimValuationPrice()!=null && getInterimValuationPrice().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFinalValuationPrice()!=null && getFinalValuationPrice().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BasketConstituent.BasketConstituentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			BasketConstituent.BasketConstituentBuilder o = (BasketConstituent.BasketConstituentBuilder) other;
			
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::getOrCreateQuantity);
			merger.mergeRosetta(getInitialValuationPrice(), o.getInitialValuationPrice(), this::getOrCreateInitialValuationPrice);
			merger.mergeRosetta(getInterimValuationPrice(), o.getInterimValuationPrice(), this::getOrCreateInterimValuationPrice);
			merger.mergeRosetta(getFinalValuationPrice(), o.getFinalValuationPrice(), this::getOrCreateFinalValuationPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			BasketConstituent _that = getType().cast(o);
		
			if (!ListEquals.listEquals(quantity, _that.getQuantity())) return false;
			if (!ListEquals.listEquals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!ListEquals.listEquals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!ListEquals.listEquals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BasketConstituentBuilder {" +
				"quantity=" + this.quantity + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice +
			'}' + " " + super.toString();
		}
	}
}
