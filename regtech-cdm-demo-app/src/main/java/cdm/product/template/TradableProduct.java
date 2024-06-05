package cdm.product.template;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradableProduct.TradableProductBuilder;
import cdm.product.template.TradableProduct.TradableProductBuilderImpl;
import cdm.product.template.TradableProduct.TradableProductImpl;
import cdm.product.template.TradeLot;
import cdm.product.template.meta.TradableProductMeta;
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
 * Definition of a product as ready to be traded, i.e. included in an execution or contract, by associating a specific price and quantity to this product plus an (optional) mechanism for any potential future quantity adjustment.
 * @version ${project.version}
 */
@RosettaDataType(value="TradableProduct", builder=TradableProduct.TradableProductBuilderImpl.class, version="${project.version}")
public interface TradableProduct extends RosettaModelObject {

	TradableProductMeta metaData = new TradableProductMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The underlying product to be included in a contract or execution.
	 */
	Product getProduct();
	/**
	 * Specifies the price, quantity and effective date of each trade lot, when the same product may be traded multiple times in different lots with the same counterparty. In a trade increase, a new trade lot is added to the list, with the corresponding effective date. In a trade decrease, the existing trade lot(s) are decreased of the corresponding quantity (and an unwind fee may have to be settled). The multiple cardinality and the ability to increase existing trades is used for Equity Swaps in particular.
	 */
	List<? extends TradeLot> getTradeLot();
	/**
	 * Specifies the parties which are the two counterparties to the transaction.  The product is agnostic to the actual parties to the transaction, with the party references abstracted away from the product definition and replaced by the counterparty enum (e.g. CounterpartyEnum values Party1 or Party2). The counterparty enum can then be positioned in the product (e.g. to specify which counterparty is the payer, receiver etc) and this counterparties attribute, which is positioned outside of the product definition, allows the counterparty enum to be associated with an actual party reference.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Party"
	 *
	 * Provision Parties entering into GMRA, as specified on page 1 of the GMRA and under 1. (a).
	 *
	 */
	List<? extends Counterparty> getCounterparty();
	/**
	 * Specifies the parties with ancillary roles in the transaction. The product is agnostic to the actual parties involved in the transaction, with the party references abstracted away from the product definition and replaced by the AncillaryRoleEnum. The AncillaryRoleEnum can then be positioned in the product and this AncillaryParty type, which is positioned outside of the product definition, allows the AncillaryRoleEnum to be associated with an actual party reference.
	 */
	List<? extends AncillaryParty> getAncillaryParty();
	/**
	 * Specifies the conditions that govern the adjustment to the quantity of a product being traded: e.g. execution, portfolio rebalancing etc. It is typically used in the context of Equity Swaps.
	 */
	NotionalAdjustmentEnum getAdjustment();

	/*********************** Build Methods  ***********************/
	TradableProduct build();
	
	TradableProduct.TradableProductBuilder toBuilder();
	
	static TradableProduct.TradableProductBuilder builder() {
		return new TradableProduct.TradableProductBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TradableProduct> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TradableProduct> getType() {
		return TradableProduct.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("product"), processor, Product.class, getProduct());
		processRosetta(path.newSubPath("tradeLot"), processor, TradeLot.class, getTradeLot());
		processRosetta(path.newSubPath("counterparty"), processor, Counterparty.class, getCounterparty());
		processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.class, getAncillaryParty());
		processor.processBasic(path.newSubPath("adjustment"), NotionalAdjustmentEnum.class, getAdjustment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TradableProductBuilder extends TradableProduct, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateProduct();
		Product.ProductBuilder getProduct();
		TradeLot.TradeLotBuilder getOrCreateTradeLot(int _index);
		List<? extends TradeLot.TradeLotBuilder> getTradeLot();
		Counterparty.CounterpartyBuilder getOrCreateCounterparty(int _index);
		List<? extends Counterparty.CounterpartyBuilder> getCounterparty();
		AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index);
		List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty();
		TradableProduct.TradableProductBuilder setProduct(Product product);
		TradableProduct.TradableProductBuilder addTradeLot(TradeLot tradeLot0);
		TradableProduct.TradableProductBuilder addTradeLot(TradeLot tradeLot1, int _idx);
		TradableProduct.TradableProductBuilder addTradeLot(List<? extends TradeLot> tradeLot2);
		TradableProduct.TradableProductBuilder setTradeLot(List<? extends TradeLot> tradeLot3);
		TradableProduct.TradableProductBuilder addCounterparty(Counterparty counterparty0);
		TradableProduct.TradableProductBuilder addCounterparty(Counterparty counterparty1, int _idx);
		TradableProduct.TradableProductBuilder addCounterparty(List<? extends Counterparty> counterparty2);
		TradableProduct.TradableProductBuilder setCounterparty(List<? extends Counterparty> counterparty3);
		TradableProduct.TradableProductBuilder addAncillaryParty(AncillaryParty ancillaryParty0);
		TradableProduct.TradableProductBuilder addAncillaryParty(AncillaryParty ancillaryParty1, int _idx);
		TradableProduct.TradableProductBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryParty2);
		TradableProduct.TradableProductBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryParty3);
		TradableProduct.TradableProductBuilder setAdjustment(NotionalAdjustmentEnum adjustment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("product"), processor, Product.ProductBuilder.class, getProduct());
			processRosetta(path.newSubPath("tradeLot"), processor, TradeLot.TradeLotBuilder.class, getTradeLot());
			processRosetta(path.newSubPath("counterparty"), processor, Counterparty.CounterpartyBuilder.class, getCounterparty());
			processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.AncillaryPartyBuilder.class, getAncillaryParty());
			processor.processBasic(path.newSubPath("adjustment"), NotionalAdjustmentEnum.class, getAdjustment(), this);
		}
		

		TradableProduct.TradableProductBuilder prune();
	}

	/*********************** Immutable Implementation of TradableProduct  ***********************/
	class TradableProductImpl implements TradableProduct {
		private final Product product;
		private final List<? extends TradeLot> tradeLot;
		private final List<? extends Counterparty> counterparty;
		private final List<? extends AncillaryParty> ancillaryParty;
		private final NotionalAdjustmentEnum adjustment;
		
		protected TradableProductImpl(TradableProduct.TradableProductBuilder builder) {
			this.product = ofNullable(builder.getProduct()).map(f->f.build()).orElse(null);
			this.tradeLot = ofNullable(builder.getTradeLot()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.counterparty = ofNullable(builder.getCounterparty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.ancillaryParty = ofNullable(builder.getAncillaryParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.adjustment = builder.getAdjustment();
		}
		
		@Override
		@RosettaAttribute("product")
		public Product getProduct() {
			return product;
		}
		
		@Override
		@RosettaAttribute("tradeLot")
		public List<? extends TradeLot> getTradeLot() {
			return tradeLot;
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public List<? extends Counterparty> getCounterparty() {
			return counterparty;
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty> getAncillaryParty() {
			return ancillaryParty;
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public NotionalAdjustmentEnum getAdjustment() {
			return adjustment;
		}
		
		@Override
		public TradableProduct build() {
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder toBuilder() {
			TradableProduct.TradableProductBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TradableProduct.TradableProductBuilder builder) {
			ofNullable(getProduct()).ifPresent(builder::setProduct);
			ofNullable(getTradeLot()).ifPresent(builder::setTradeLot);
			ofNullable(getCounterparty()).ifPresent(builder::setCounterparty);
			ofNullable(getAncillaryParty()).ifPresent(builder::setAncillaryParty);
			ofNullable(getAdjustment()).ifPresent(builder::setAdjustment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradableProduct _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(tradeLot, _that.getTradeLot())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(adjustment, _that.getAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (tradeLot != null ? tradeLot.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (adjustment != null ? adjustment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradableProduct {" +
				"product=" + this.product + ", " +
				"tradeLot=" + this.tradeLot + ", " +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"adjustment=" + this.adjustment +
			'}';
		}
	}

	/*********************** Builder Implementation of TradableProduct  ***********************/
	class TradableProductBuilderImpl implements TradableProduct.TradableProductBuilder {
	
		protected Product.ProductBuilder product;
		protected List<TradeLot.TradeLotBuilder> tradeLot = new ArrayList<>();
		protected List<Counterparty.CounterpartyBuilder> counterparty = new ArrayList<>();
		protected List<AncillaryParty.AncillaryPartyBuilder> ancillaryParty = new ArrayList<>();
		protected NotionalAdjustmentEnum adjustment;
	
		public TradableProductBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("product")
		public Product.ProductBuilder getProduct() {
			return product;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateProduct() {
			Product.ProductBuilder result;
			if (product!=null) {
				result = product;
			}
			else {
				result = product = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeLot")
		public List<? extends TradeLot.TradeLotBuilder> getTradeLot() {
			return tradeLot;
		}
		
		public TradeLot.TradeLotBuilder getOrCreateTradeLot(int _index) {
		
			if (tradeLot==null) {
				this.tradeLot = new ArrayList<>();
			}
			TradeLot.TradeLotBuilder result;
			return getIndex(tradeLot, _index, () -> {
						TradeLot.TradeLotBuilder newTradeLot = TradeLot.builder();
						return newTradeLot;
					});
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public List<? extends Counterparty.CounterpartyBuilder> getCounterparty() {
			return counterparty;
		}
		
		public Counterparty.CounterpartyBuilder getOrCreateCounterparty(int _index) {
		
			if (counterparty==null) {
				this.counterparty = new ArrayList<>();
			}
			Counterparty.CounterpartyBuilder result;
			return getIndex(counterparty, _index, () -> {
						Counterparty.CounterpartyBuilder newCounterparty = Counterparty.builder();
						return newCounterparty;
					});
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty() {
			return ancillaryParty;
		}
		
		public AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index) {
		
			if (ancillaryParty==null) {
				this.ancillaryParty = new ArrayList<>();
			}
			AncillaryParty.AncillaryPartyBuilder result;
			return getIndex(ancillaryParty, _index, () -> {
						AncillaryParty.AncillaryPartyBuilder newAncillaryParty = AncillaryParty.builder();
						return newAncillaryParty;
					});
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public NotionalAdjustmentEnum getAdjustment() {
			return adjustment;
		}
		
	
		@Override
		@RosettaAttribute("product")
		public TradableProduct.TradableProductBuilder setProduct(Product product) {
			this.product = product==null?null:product.toBuilder();
			return this;
		}
		@Override
		public TradableProduct.TradableProductBuilder addTradeLot(TradeLot tradeLot) {
			if (tradeLot!=null) this.tradeLot.add(tradeLot.toBuilder());
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder addTradeLot(TradeLot tradeLot, int _idx) {
			getIndex(this.tradeLot, _idx, () -> tradeLot.toBuilder());
			return this;
		}
		@Override 
		public TradableProduct.TradableProductBuilder addTradeLot(List<? extends TradeLot> tradeLots) {
			if (tradeLots != null) {
				for (TradeLot toAdd : tradeLots) {
					this.tradeLot.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("tradeLot")
		public TradableProduct.TradableProductBuilder setTradeLot(List<? extends TradeLot> tradeLots) {
			if (tradeLots == null)  {
				this.tradeLot = new ArrayList<>();
			}
			else {
				this.tradeLot = tradeLots.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder addCounterparty(Counterparty counterparty) {
			if (counterparty!=null) this.counterparty.add(counterparty.toBuilder());
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder addCounterparty(Counterparty counterparty, int _idx) {
			getIndex(this.counterparty, _idx, () -> counterparty.toBuilder());
			return this;
		}
		@Override 
		public TradableProduct.TradableProductBuilder addCounterparty(List<? extends Counterparty> counterpartys) {
			if (counterpartys != null) {
				for (Counterparty toAdd : counterpartys) {
					this.counterparty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("counterparty")
		public TradableProduct.TradableProductBuilder setCounterparty(List<? extends Counterparty> counterpartys) {
			if (counterpartys == null)  {
				this.counterparty = new ArrayList<>();
			}
			else {
				this.counterparty = counterpartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder addAncillaryParty(AncillaryParty ancillaryParty) {
			if (ancillaryParty!=null) this.ancillaryParty.add(ancillaryParty.toBuilder());
			return this;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder addAncillaryParty(AncillaryParty ancillaryParty, int _idx) {
			getIndex(this.ancillaryParty, _idx, () -> ancillaryParty.toBuilder());
			return this;
		}
		@Override 
		public TradableProduct.TradableProductBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys != null) {
				for (AncillaryParty toAdd : ancillaryPartys) {
					this.ancillaryParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("ancillaryParty")
		public TradableProduct.TradableProductBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys == null)  {
				this.ancillaryParty = new ArrayList<>();
			}
			else {
				this.ancillaryParty = ancillaryPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public TradableProduct.TradableProductBuilder setAdjustment(NotionalAdjustmentEnum adjustment) {
			this.adjustment = adjustment==null?null:adjustment;
			return this;
		}
		
		@Override
		public TradableProduct build() {
			return new TradableProduct.TradableProductImpl(this);
		}
		
		@Override
		public TradableProduct.TradableProductBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradableProduct.TradableProductBuilder prune() {
			if (product!=null && !product.prune().hasData()) product = null;
			tradeLot = tradeLot.stream().filter(b->b!=null).<TradeLot.TradeLotBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			counterparty = counterparty.stream().filter(b->b!=null).<Counterparty.CounterpartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			ancillaryParty = ancillaryParty.stream().filter(b->b!=null).<AncillaryParty.AncillaryPartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getProduct()!=null && getProduct().hasData()) return true;
			if (getTradeLot()!=null && getTradeLot().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCounterparty()!=null && getCounterparty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAncillaryParty()!=null && getAncillaryParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAdjustment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradableProduct.TradableProductBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TradableProduct.TradableProductBuilder o = (TradableProduct.TradableProductBuilder) other;
			
			merger.mergeRosetta(getProduct(), o.getProduct(), this::setProduct);
			merger.mergeRosetta(getTradeLot(), o.getTradeLot(), this::getOrCreateTradeLot);
			merger.mergeRosetta(getCounterparty(), o.getCounterparty(), this::getOrCreateCounterparty);
			merger.mergeRosetta(getAncillaryParty(), o.getAncillaryParty(), this::getOrCreateAncillaryParty);
			
			merger.mergeBasic(getAdjustment(), o.getAdjustment(), this::setAdjustment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradableProduct _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(tradeLot, _that.getTradeLot())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(adjustment, _that.getAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (tradeLot != null ? tradeLot.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (adjustment != null ? adjustment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradableProductBuilder {" +
				"product=" + this.product + ", " +
				"tradeLot=" + this.tradeLot + ", " +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"adjustment=" + this.adjustment +
			'}';
		}
	}
}
