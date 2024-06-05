package cdm.product.template;

import cdm.base.staticdata.identifier.Identifier;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradeLot;
import cdm.product.template.TradeLot.TradeLotBuilder;
import cdm.product.template.TradeLot.TradeLotBuilderImpl;
import cdm.product.template.TradeLot.TradeLotImpl;
import cdm.product.template.meta.TradeLotMeta;
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
 * Specifies the price and quantity of a trade lot, where the same product could be traded multiple times with the same counterparty but in different lots (at a different date, in a different quantity and at a different price). One trade lot combined with a product definition specifies the entire economics of a trade. The lifecycle mechanics of each such trade lot (e.g. cashflow payments) is independent of the other lots.
 * @version ${project.version}
 */
@RosettaDataType(value="TradeLot", builder=TradeLot.TradeLotBuilderImpl.class, version="${project.version}")
public interface TradeLot extends RosettaModelObject {

	TradeLotMeta metaData = new TradeLotMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies one or more identifiers for the lot, if any.
	 */
	List<? extends Identifier> getLotIdentifier();
	/**
	 * Specifies the settlement characteristics of a trade lot: price, quantity, observable (optionally) and the settlement terms. This attribute has a multiple cardinality to allow to specify the price, quantity and observable of different legs in a single, composite product (e.g. a Swap).
	 */
	List<? extends PriceQuantity> getPriceQuantity();

	/*********************** Build Methods  ***********************/
	TradeLot build();
	
	TradeLot.TradeLotBuilder toBuilder();
	
	static TradeLot.TradeLotBuilder builder() {
		return new TradeLot.TradeLotBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TradeLot> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TradeLot> getType() {
		return TradeLot.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.class, getLotIdentifier());
		processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.class, getPriceQuantity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TradeLotBuilder extends TradeLot, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateLotIdentifier(int _index);
		List<? extends Identifier.IdentifierBuilder> getLotIdentifier();
		PriceQuantity.PriceQuantityBuilder getOrCreatePriceQuantity(int _index);
		List<? extends PriceQuantity.PriceQuantityBuilder> getPriceQuantity();
		TradeLot.TradeLotBuilder addLotIdentifier(Identifier lotIdentifier0);
		TradeLot.TradeLotBuilder addLotIdentifier(Identifier lotIdentifier1, int _idx);
		TradeLot.TradeLotBuilder addLotIdentifier(List<? extends Identifier> lotIdentifier2);
		TradeLot.TradeLotBuilder setLotIdentifier(List<? extends Identifier> lotIdentifier3);
		TradeLot.TradeLotBuilder addPriceQuantity(PriceQuantity priceQuantity0);
		TradeLot.TradeLotBuilder addPriceQuantity(PriceQuantity priceQuantity1, int _idx);
		TradeLot.TradeLotBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantity2);
		TradeLot.TradeLotBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantity3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.IdentifierBuilder.class, getLotIdentifier());
			processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.PriceQuantityBuilder.class, getPriceQuantity());
		}
		

		TradeLot.TradeLotBuilder prune();
	}

	/*********************** Immutable Implementation of TradeLot  ***********************/
	class TradeLotImpl implements TradeLot {
		private final List<? extends Identifier> lotIdentifier;
		private final List<? extends PriceQuantity> priceQuantity;
		
		protected TradeLotImpl(TradeLot.TradeLotBuilder builder) {
			this.lotIdentifier = ofNullable(builder.getLotIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.priceQuantity = ofNullable(builder.getPriceQuantity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("lotIdentifier")
		public List<? extends Identifier> getLotIdentifier() {
			return lotIdentifier;
		}
		
		@Override
		@RosettaAttribute("priceQuantity")
		public List<? extends PriceQuantity> getPriceQuantity() {
			return priceQuantity;
		}
		
		@Override
		public TradeLot build() {
			return this;
		}
		
		@Override
		public TradeLot.TradeLotBuilder toBuilder() {
			TradeLot.TradeLotBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TradeLot.TradeLotBuilder builder) {
			ofNullable(getLotIdentifier()).ifPresent(builder::setLotIdentifier);
			ofNullable(getPriceQuantity()).ifPresent(builder::setPriceQuantity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradeLot _that = getType().cast(o);
		
			if (!ListEquals.listEquals(lotIdentifier, _that.getLotIdentifier())) return false;
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradeLot {" +
				"lotIdentifier=" + this.lotIdentifier + ", " +
				"priceQuantity=" + this.priceQuantity +
			'}';
		}
	}

	/*********************** Builder Implementation of TradeLot  ***********************/
	class TradeLotBuilderImpl implements TradeLot.TradeLotBuilder {
	
		protected List<Identifier.IdentifierBuilder> lotIdentifier = new ArrayList<>();
		protected List<PriceQuantity.PriceQuantityBuilder> priceQuantity = new ArrayList<>();
	
		public TradeLotBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lotIdentifier")
		public List<? extends Identifier.IdentifierBuilder> getLotIdentifier() {
			return lotIdentifier;
		}
		
		public Identifier.IdentifierBuilder getOrCreateLotIdentifier(int _index) {
		
			if (lotIdentifier==null) {
				this.lotIdentifier = new ArrayList<>();
			}
			Identifier.IdentifierBuilder result;
			return getIndex(lotIdentifier, _index, () -> {
						Identifier.IdentifierBuilder newLotIdentifier = Identifier.builder();
						return newLotIdentifier;
					});
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
		public TradeLot.TradeLotBuilder addLotIdentifier(Identifier lotIdentifier) {
			if (lotIdentifier!=null) this.lotIdentifier.add(lotIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public TradeLot.TradeLotBuilder addLotIdentifier(Identifier lotIdentifier, int _idx) {
			getIndex(this.lotIdentifier, _idx, () -> lotIdentifier.toBuilder());
			return this;
		}
		@Override 
		public TradeLot.TradeLotBuilder addLotIdentifier(List<? extends Identifier> lotIdentifiers) {
			if (lotIdentifiers != null) {
				for (Identifier toAdd : lotIdentifiers) {
					this.lotIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("lotIdentifier")
		public TradeLot.TradeLotBuilder setLotIdentifier(List<? extends Identifier> lotIdentifiers) {
			if (lotIdentifiers == null)  {
				this.lotIdentifier = new ArrayList<>();
			}
			else {
				this.lotIdentifier = lotIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public TradeLot.TradeLotBuilder addPriceQuantity(PriceQuantity priceQuantity) {
			if (priceQuantity!=null) this.priceQuantity.add(priceQuantity.toBuilder());
			return this;
		}
		
		@Override
		public TradeLot.TradeLotBuilder addPriceQuantity(PriceQuantity priceQuantity, int _idx) {
			getIndex(this.priceQuantity, _idx, () -> priceQuantity.toBuilder());
			return this;
		}
		@Override 
		public TradeLot.TradeLotBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys != null) {
				for (PriceQuantity toAdd : priceQuantitys) {
					this.priceQuantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("priceQuantity")
		public TradeLot.TradeLotBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
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
		public TradeLot build() {
			return new TradeLot.TradeLotImpl(this);
		}
		
		@Override
		public TradeLot.TradeLotBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradeLot.TradeLotBuilder prune() {
			lotIdentifier = lotIdentifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			priceQuantity = priceQuantity.stream().filter(b->b!=null).<PriceQuantity.PriceQuantityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLotIdentifier()!=null && getLotIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPriceQuantity()!=null && getPriceQuantity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradeLot.TradeLotBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TradeLot.TradeLotBuilder o = (TradeLot.TradeLotBuilder) other;
			
			merger.mergeRosetta(getLotIdentifier(), o.getLotIdentifier(), this::getOrCreateLotIdentifier);
			merger.mergeRosetta(getPriceQuantity(), o.getPriceQuantity(), this::getOrCreatePriceQuantity);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TradeLot _that = getType().cast(o);
		
			if (!ListEquals.listEquals(lotIdentifier, _that.getLotIdentifier())) return false;
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradeLotBuilder {" +
				"lotIdentifier=" + this.lotIdentifier + ", " +
				"priceQuantity=" + this.priceQuantity +
			'}';
		}
	}
}
