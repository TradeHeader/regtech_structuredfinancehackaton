package cdm.event.common;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.QuantityChangeInstruction.QuantityChangeInstructionBuilder;
import cdm.event.common.QuantityChangeInstruction.QuantityChangeInstructionBuilderImpl;
import cdm.event.common.QuantityChangeInstruction.QuantityChangeInstructionImpl;
import cdm.event.common.meta.QuantityChangeInstructionMeta;
import cdm.product.common.settlement.PriceQuantity;
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
 * Instructions required to create a Quantity Change Primitive Event, which can be either an increase, a decrease or a replacement. An increase adds a new trade lot to the original trade, whereas a decrease subtracts from an existing trade lot&#39;s quantity. A replacement updates the quantity of an existing trade lot to the new value.
 * @version ${project.version}
 */
@RosettaDataType(value="QuantityChangeInstruction", builder=QuantityChangeInstruction.QuantityChangeInstructionBuilderImpl.class, version="${project.version}")
public interface QuantityChangeInstruction extends RosettaModelObject {

	QuantityChangeInstructionMeta metaData = new QuantityChangeInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Quantity by which the trade is being increased, decreased or replaced, and the price at which such quantity change is agreed. The quantity change should always be specified as a positive number, with the direction (increase/decrease/replacement) being specified by the direction enumeration. A fee can also be associated to the quantity change by specifying a Price component of type CashPrice, including the corresponding settlement date and direction.
	 */
	List<? extends PriceQuantity> getChange();
	/**
	 * Direction of the quantity change specified as either an increase, decrease or replacement.
	 */
	QuantityChangeDirectionEnum getDirection();
	/**
	 * Identifier for the new lot (in case of increase) or for the existing lot to be changed(in case of decrease or replacement). This optional attribute is mandatory in case of a decrease or replacement if the initial trade state contains multiple trade lots.
	 */
	List<? extends Identifier> getLotIdentifier();

	/*********************** Build Methods  ***********************/
	QuantityChangeInstruction build();
	
	QuantityChangeInstruction.QuantityChangeInstructionBuilder toBuilder();
	
	static QuantityChangeInstruction.QuantityChangeInstructionBuilder builder() {
		return new QuantityChangeInstruction.QuantityChangeInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends QuantityChangeInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends QuantityChangeInstruction> getType() {
		return QuantityChangeInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("change"), processor, PriceQuantity.class, getChange());
		processor.processBasic(path.newSubPath("direction"), QuantityChangeDirectionEnum.class, getDirection(), this);
		processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.class, getLotIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface QuantityChangeInstructionBuilder extends QuantityChangeInstruction, RosettaModelObjectBuilder {
		PriceQuantity.PriceQuantityBuilder getOrCreateChange(int _index);
		List<? extends PriceQuantity.PriceQuantityBuilder> getChange();
		Identifier.IdentifierBuilder getOrCreateLotIdentifier(int _index);
		List<? extends Identifier.IdentifierBuilder> getLotIdentifier();
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(PriceQuantity change0);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(PriceQuantity change1, int _idx);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(List<? extends PriceQuantity> change2);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder setChange(List<? extends PriceQuantity> change3);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder setDirection(QuantityChangeDirectionEnum direction);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(Identifier lotIdentifier0);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(Identifier lotIdentifier1, int _idx);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(List<? extends Identifier> lotIdentifier2);
		QuantityChangeInstruction.QuantityChangeInstructionBuilder setLotIdentifier(List<? extends Identifier> lotIdentifier3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("change"), processor, PriceQuantity.PriceQuantityBuilder.class, getChange());
			processor.processBasic(path.newSubPath("direction"), QuantityChangeDirectionEnum.class, getDirection(), this);
			processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.IdentifierBuilder.class, getLotIdentifier());
		}
		

		QuantityChangeInstruction.QuantityChangeInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of QuantityChangeInstruction  ***********************/
	class QuantityChangeInstructionImpl implements QuantityChangeInstruction {
		private final List<? extends PriceQuantity> change;
		private final QuantityChangeDirectionEnum direction;
		private final List<? extends Identifier> lotIdentifier;
		
		protected QuantityChangeInstructionImpl(QuantityChangeInstruction.QuantityChangeInstructionBuilder builder) {
			this.change = ofNullable(builder.getChange()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.direction = builder.getDirection();
			this.lotIdentifier = ofNullable(builder.getLotIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("change")
		public List<? extends PriceQuantity> getChange() {
			return change;
		}
		
		@Override
		@RosettaAttribute("direction")
		public QuantityChangeDirectionEnum getDirection() {
			return direction;
		}
		
		@Override
		@RosettaAttribute("lotIdentifier")
		public List<? extends Identifier> getLotIdentifier() {
			return lotIdentifier;
		}
		
		@Override
		public QuantityChangeInstruction build() {
			return this;
		}
		
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder toBuilder() {
			QuantityChangeInstruction.QuantityChangeInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(QuantityChangeInstruction.QuantityChangeInstructionBuilder builder) {
			ofNullable(getChange()).ifPresent(builder::setChange);
			ofNullable(getDirection()).ifPresent(builder::setDirection);
			ofNullable(getLotIdentifier()).ifPresent(builder::setLotIdentifier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuantityChangeInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(change, _that.getChange())) return false;
			if (!Objects.equals(direction, _that.getDirection())) return false;
			if (!ListEquals.listEquals(lotIdentifier, _that.getLotIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (change != null ? change.hashCode() : 0);
			_result = 31 * _result + (direction != null ? direction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuantityChangeInstruction {" +
				"change=" + this.change + ", " +
				"direction=" + this.direction + ", " +
				"lotIdentifier=" + this.lotIdentifier +
			'}';
		}
	}

	/*********************** Builder Implementation of QuantityChangeInstruction  ***********************/
	class QuantityChangeInstructionBuilderImpl implements QuantityChangeInstruction.QuantityChangeInstructionBuilder {
	
		protected List<PriceQuantity.PriceQuantityBuilder> change = new ArrayList<>();
		protected QuantityChangeDirectionEnum direction;
		protected List<Identifier.IdentifierBuilder> lotIdentifier = new ArrayList<>();
	
		public QuantityChangeInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("change")
		public List<? extends PriceQuantity.PriceQuantityBuilder> getChange() {
			return change;
		}
		
		public PriceQuantity.PriceQuantityBuilder getOrCreateChange(int _index) {
		
			if (change==null) {
				this.change = new ArrayList<>();
			}
			PriceQuantity.PriceQuantityBuilder result;
			return getIndex(change, _index, () -> {
						PriceQuantity.PriceQuantityBuilder newChange = PriceQuantity.builder();
						return newChange;
					});
		}
		
		@Override
		@RosettaAttribute("direction")
		public QuantityChangeDirectionEnum getDirection() {
			return direction;
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
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(PriceQuantity change) {
			if (change!=null) this.change.add(change.toBuilder());
			return this;
		}
		
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(PriceQuantity change, int _idx) {
			getIndex(this.change, _idx, () -> change.toBuilder());
			return this;
		}
		@Override 
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addChange(List<? extends PriceQuantity> changes) {
			if (changes != null) {
				for (PriceQuantity toAdd : changes) {
					this.change.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("change")
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder setChange(List<? extends PriceQuantity> changes) {
			if (changes == null)  {
				this.change = new ArrayList<>();
			}
			else {
				this.change = changes.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("direction")
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder setDirection(QuantityChangeDirectionEnum direction) {
			this.direction = direction==null?null:direction;
			return this;
		}
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(Identifier lotIdentifier) {
			if (lotIdentifier!=null) this.lotIdentifier.add(lotIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(Identifier lotIdentifier, int _idx) {
			getIndex(this.lotIdentifier, _idx, () -> lotIdentifier.toBuilder());
			return this;
		}
		@Override 
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder addLotIdentifier(List<? extends Identifier> lotIdentifiers) {
			if (lotIdentifiers != null) {
				for (Identifier toAdd : lotIdentifiers) {
					this.lotIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("lotIdentifier")
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder setLotIdentifier(List<? extends Identifier> lotIdentifiers) {
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
		public QuantityChangeInstruction build() {
			return new QuantityChangeInstruction.QuantityChangeInstructionImpl(this);
		}
		
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder prune() {
			change = change.stream().filter(b->b!=null).<PriceQuantity.PriceQuantityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			lotIdentifier = lotIdentifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getChange()!=null && getChange().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getDirection()!=null) return true;
			if (getLotIdentifier()!=null && getLotIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			QuantityChangeInstruction.QuantityChangeInstructionBuilder o = (QuantityChangeInstruction.QuantityChangeInstructionBuilder) other;
			
			merger.mergeRosetta(getChange(), o.getChange(), this::getOrCreateChange);
			merger.mergeRosetta(getLotIdentifier(), o.getLotIdentifier(), this::getOrCreateLotIdentifier);
			
			merger.mergeBasic(getDirection(), o.getDirection(), this::setDirection);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuantityChangeInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(change, _that.getChange())) return false;
			if (!Objects.equals(direction, _that.getDirection())) return false;
			if (!ListEquals.listEquals(lotIdentifier, _that.getLotIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (change != null ? change.hashCode() : 0);
			_result = 31 * _result + (direction != null ? direction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuantityChangeInstructionBuilder {" +
				"change=" + this.change + ", " +
				"direction=" + this.direction + ", " +
				"lotIdentifier=" + this.lotIdentifier +
			'}';
		}
	}
}
