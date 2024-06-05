package cdm.event.position;

import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import cdm.event.position.Position;
import cdm.event.position.Position.PositionBuilder;
import cdm.event.position.Position.PositionBuilderImpl;
import cdm.event.position.Position.PositionImpl;
import cdm.event.position.meta.PositionMeta;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
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
 * A Position describes how much of a given Product is being held and constitutes the atomic element of a Portfolio.
 * @version ${project.version}
 */
@RosettaDataType(value="Position", builder=Position.PositionBuilderImpl.class, version="${project.version}")
public interface Position extends RosettaModelObject {

	PositionMeta metaData = new PositionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Position with many price quantities.
	 */
	List<? extends PriceQuantity> getPriceQuantity();
	/**
	 * The product underlying the position.
	 */
	Product getProduct();
	/**
	 * The aggregate cost of proceeds
	 */
	Money getCashBalance();
	/**
	 * Reference to the Contract, in case product is contractual and the contract has been formed
	 */
	ReferenceWithMetaTradeState getTradeReference();

	/*********************** Build Methods  ***********************/
	Position build();
	
	Position.PositionBuilder toBuilder();
	
	static Position.PositionBuilder builder() {
		return new Position.PositionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Position> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Position> getType() {
		return Position.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("product"), processor, Product.class, getProduct());
		processRosetta(path.newSubPath("cashBalance"), processor, Money.class, getCashBalance());
		processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.class, getTradeReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PositionBuilder extends Position, RosettaModelObjectBuilder {
		PriceQuantity.PriceQuantityBuilder getOrCreatePriceQuantity(int _index);
		List<? extends PriceQuantity.PriceQuantityBuilder> getPriceQuantity();
		Product.ProductBuilder getOrCreateProduct();
		Product.ProductBuilder getProduct();
		Money.MoneyBuilder getOrCreateCashBalance();
		Money.MoneyBuilder getCashBalance();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeReference();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeReference();
		Position.PositionBuilder addPriceQuantity(PriceQuantity priceQuantity0);
		Position.PositionBuilder addPriceQuantity(PriceQuantity priceQuantity1, int _idx);
		Position.PositionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantity2);
		Position.PositionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantity3);
		Position.PositionBuilder setProduct(Product product);
		Position.PositionBuilder setCashBalance(Money cashBalance);
		Position.PositionBuilder setTradeReference(ReferenceWithMetaTradeState tradeReference0);
		Position.PositionBuilder setTradeReferenceValue(TradeState tradeReference1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.PriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("product"), processor, Product.ProductBuilder.class, getProduct());
			processRosetta(path.newSubPath("cashBalance"), processor, Money.MoneyBuilder.class, getCashBalance());
			processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getTradeReference());
		}
		

		Position.PositionBuilder prune();
	}

	/*********************** Immutable Implementation of Position  ***********************/
	class PositionImpl implements Position {
		private final List<? extends PriceQuantity> priceQuantity;
		private final Product product;
		private final Money cashBalance;
		private final ReferenceWithMetaTradeState tradeReference;
		
		protected PositionImpl(Position.PositionBuilder builder) {
			this.priceQuantity = ofNullable(builder.getPriceQuantity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.product = ofNullable(builder.getProduct()).map(f->f.build()).orElse(null);
			this.cashBalance = ofNullable(builder.getCashBalance()).map(f->f.build()).orElse(null);
			this.tradeReference = ofNullable(builder.getTradeReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("priceQuantity")
		public List<? extends PriceQuantity> getPriceQuantity() {
			return priceQuantity;
		}
		
		@Override
		@RosettaAttribute("product")
		public Product getProduct() {
			return product;
		}
		
		@Override
		@RosettaAttribute("cashBalance")
		public Money getCashBalance() {
			return cashBalance;
		}
		
		@Override
		@RosettaAttribute("tradeReference")
		public ReferenceWithMetaTradeState getTradeReference() {
			return tradeReference;
		}
		
		@Override
		public Position build() {
			return this;
		}
		
		@Override
		public Position.PositionBuilder toBuilder() {
			Position.PositionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Position.PositionBuilder builder) {
			ofNullable(getPriceQuantity()).ifPresent(builder::setPriceQuantity);
			ofNullable(getProduct()).ifPresent(builder::setProduct);
			ofNullable(getCashBalance()).ifPresent(builder::setCashBalance);
			ofNullable(getTradeReference()).ifPresent(builder::setTradeReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Position _that = getType().cast(o);
		
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!Objects.equals(cashBalance, _that.getCashBalance())) return false;
			if (!Objects.equals(tradeReference, _that.getTradeReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (cashBalance != null ? cashBalance.hashCode() : 0);
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Position {" +
				"priceQuantity=" + this.priceQuantity + ", " +
				"product=" + this.product + ", " +
				"cashBalance=" + this.cashBalance + ", " +
				"tradeReference=" + this.tradeReference +
			'}';
		}
	}

	/*********************** Builder Implementation of Position  ***********************/
	class PositionBuilderImpl implements Position.PositionBuilder {
	
		protected List<PriceQuantity.PriceQuantityBuilder> priceQuantity = new ArrayList<>();
		protected Product.ProductBuilder product;
		protected Money.MoneyBuilder cashBalance;
		protected ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder tradeReference;
	
		public PositionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("priceQuantity")
		public List<? extends PriceQuantity.PriceQuantityBuilder> getPriceQuantity() {
			return priceQuantity;
		}
		
		public PriceQuantity.PriceQuantityBuilder getOrCreatePriceQuantity(int _index) {
		
			if (priceQuantity==null) {
				this.priceQuantity = new ArrayList<>();
			}
			PriceQuantity.PriceQuantityBuilder result;
			return getIndex(priceQuantity, _index, () -> {
						PriceQuantity.PriceQuantityBuilder newPriceQuantity = PriceQuantity.builder();
						return newPriceQuantity;
					});
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
		@RosettaAttribute("cashBalance")
		public Money.MoneyBuilder getCashBalance() {
			return cashBalance;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateCashBalance() {
			Money.MoneyBuilder result;
			if (cashBalance!=null) {
				result = cashBalance;
			}
			else {
				result = cashBalance = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeReference")
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeReference() {
			return tradeReference;
		}
		
		@Override
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeReference() {
			ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder result;
			if (tradeReference!=null) {
				result = tradeReference;
			}
			else {
				result = tradeReference = ReferenceWithMetaTradeState.builder();
			}
			
			return result;
		}
	
		@Override
		public Position.PositionBuilder addPriceQuantity(PriceQuantity priceQuantity) {
			if (priceQuantity!=null) this.priceQuantity.add(priceQuantity.toBuilder());
			return this;
		}
		
		@Override
		public Position.PositionBuilder addPriceQuantity(PriceQuantity priceQuantity, int _idx) {
			getIndex(this.priceQuantity, _idx, () -> priceQuantity.toBuilder());
			return this;
		}
		@Override 
		public Position.PositionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys != null) {
				for (PriceQuantity toAdd : priceQuantitys) {
					this.priceQuantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("priceQuantity")
		public Position.PositionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys == null)  {
				this.priceQuantity = new ArrayList<>();
			}
			else {
				this.priceQuantity = priceQuantitys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("product")
		public Position.PositionBuilder setProduct(Product product) {
			this.product = product==null?null:product.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashBalance")
		public Position.PositionBuilder setCashBalance(Money cashBalance) {
			this.cashBalance = cashBalance==null?null:cashBalance.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tradeReference")
		public Position.PositionBuilder setTradeReference(ReferenceWithMetaTradeState tradeReference) {
			this.tradeReference = tradeReference==null?null:tradeReference.toBuilder();
			return this;
		}
		@Override
		public Position.PositionBuilder setTradeReferenceValue(TradeState tradeReference) {
			this.getOrCreateTradeReference().setValue(tradeReference);
			return this;
		}
		
		@Override
		public Position build() {
			return new Position.PositionImpl(this);
		}
		
		@Override
		public Position.PositionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Position.PositionBuilder prune() {
			priceQuantity = priceQuantity.stream().filter(b->b!=null).<PriceQuantity.PriceQuantityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (product!=null && !product.prune().hasData()) product = null;
			if (cashBalance!=null && !cashBalance.prune().hasData()) cashBalance = null;
			if (tradeReference!=null && !tradeReference.prune().hasData()) tradeReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPriceQuantity()!=null && getPriceQuantity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getProduct()!=null && getProduct().hasData()) return true;
			if (getCashBalance()!=null && getCashBalance().hasData()) return true;
			if (getTradeReference()!=null && getTradeReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Position.PositionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Position.PositionBuilder o = (Position.PositionBuilder) other;
			
			merger.mergeRosetta(getPriceQuantity(), o.getPriceQuantity(), this::getOrCreatePriceQuantity);
			merger.mergeRosetta(getProduct(), o.getProduct(), this::setProduct);
			merger.mergeRosetta(getCashBalance(), o.getCashBalance(), this::setCashBalance);
			merger.mergeRosetta(getTradeReference(), o.getTradeReference(), this::setTradeReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Position _that = getType().cast(o);
		
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!Objects.equals(cashBalance, _that.getCashBalance())) return false;
			if (!Objects.equals(tradeReference, _that.getTradeReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (cashBalance != null ? cashBalance.hashCode() : 0);
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PositionBuilder {" +
				"priceQuantity=" + this.priceQuantity + ", " +
				"product=" + this.product + ", " +
				"cashBalance=" + this.cashBalance + ", " +
				"tradeReference=" + this.tradeReference +
			'}';
		}
	}
}
