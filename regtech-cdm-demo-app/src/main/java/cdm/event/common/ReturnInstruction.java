package cdm.event.common;

import cdm.base.math.Quantity;
import cdm.event.common.ReturnInstruction;
import cdm.event.common.ReturnInstruction.ReturnInstructionBuilder;
import cdm.event.common.ReturnInstruction.ReturnInstructionBuilderImpl;
import cdm.event.common.ReturnInstruction.ReturnInstructionImpl;
import cdm.event.common.meta.ReturnInstructionMeta;
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
 * Specifies the information required to create the return of a Security Finance Transaction.
 * @version ${project.version}
 */
@RosettaDataType(value="ReturnInstruction", builder=ReturnInstruction.ReturnInstructionBuilderImpl.class, version="${project.version}")
public interface ReturnInstruction extends RosettaModelObject {

	ReturnInstructionMeta metaData = new ReturnInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the quantity of shares and cash to be returned in a partial return event.
	 */
	List<? extends Quantity> getQuantity();

	/*********************** Build Methods  ***********************/
	ReturnInstruction build();
	
	ReturnInstruction.ReturnInstructionBuilder toBuilder();
	
	static ReturnInstruction.ReturnInstructionBuilder builder() {
		return new ReturnInstruction.ReturnInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReturnInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReturnInstruction> getType() {
		return ReturnInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("quantity"), processor, Quantity.class, getQuantity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReturnInstructionBuilder extends ReturnInstruction, RosettaModelObjectBuilder {
		Quantity.QuantityBuilder getOrCreateQuantity(int _index);
		List<? extends Quantity.QuantityBuilder> getQuantity();
		ReturnInstruction.ReturnInstructionBuilder addQuantity(Quantity quantity0);
		ReturnInstruction.ReturnInstructionBuilder addQuantity(Quantity quantity1, int _idx);
		ReturnInstruction.ReturnInstructionBuilder addQuantity(List<? extends Quantity> quantity2);
		ReturnInstruction.ReturnInstructionBuilder setQuantity(List<? extends Quantity> quantity3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("quantity"), processor, Quantity.QuantityBuilder.class, getQuantity());
		}
		

		ReturnInstruction.ReturnInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of ReturnInstruction  ***********************/
	class ReturnInstructionImpl implements ReturnInstruction {
		private final List<? extends Quantity> quantity;
		
		protected ReturnInstructionImpl(ReturnInstruction.ReturnInstructionBuilder builder) {
			this.quantity = ofNullable(builder.getQuantity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("quantity")
		public List<? extends Quantity> getQuantity() {
			return quantity;
		}
		
		@Override
		public ReturnInstruction build() {
			return this;
		}
		
		@Override
		public ReturnInstruction.ReturnInstructionBuilder toBuilder() {
			ReturnInstruction.ReturnInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReturnInstruction.ReturnInstructionBuilder builder) {
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(quantity, _that.getQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnInstruction {" +
				"quantity=" + this.quantity +
			'}';
		}
	}

	/*********************** Builder Implementation of ReturnInstruction  ***********************/
	class ReturnInstructionBuilderImpl implements ReturnInstruction.ReturnInstructionBuilder {
	
		protected List<Quantity.QuantityBuilder> quantity = new ArrayList<>();
	
		public ReturnInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("quantity")
		public List<? extends Quantity.QuantityBuilder> getQuantity() {
			return quantity;
		}
		
		public Quantity.QuantityBuilder getOrCreateQuantity(int _index) {
		
			if (quantity==null) {
				this.quantity = new ArrayList<>();
			}
			Quantity.QuantityBuilder result;
			return getIndex(quantity, _index, () -> {
						Quantity.QuantityBuilder newQuantity = Quantity.builder();
						return newQuantity;
					});
		}
		
	
		@Override
		public ReturnInstruction.ReturnInstructionBuilder addQuantity(Quantity quantity) {
			if (quantity!=null) this.quantity.add(quantity.toBuilder());
			return this;
		}
		
		@Override
		public ReturnInstruction.ReturnInstructionBuilder addQuantity(Quantity quantity, int _idx) {
			getIndex(this.quantity, _idx, () -> quantity.toBuilder());
			return this;
		}
		@Override 
		public ReturnInstruction.ReturnInstructionBuilder addQuantity(List<? extends Quantity> quantitys) {
			if (quantitys != null) {
				for (Quantity toAdd : quantitys) {
					this.quantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("quantity")
		public ReturnInstruction.ReturnInstructionBuilder setQuantity(List<? extends Quantity> quantitys) {
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
		public ReturnInstruction build() {
			return new ReturnInstruction.ReturnInstructionImpl(this);
		}
		
		@Override
		public ReturnInstruction.ReturnInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnInstruction.ReturnInstructionBuilder prune() {
			quantity = quantity.stream().filter(b->b!=null).<Quantity.QuantityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getQuantity()!=null && getQuantity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnInstruction.ReturnInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReturnInstruction.ReturnInstructionBuilder o = (ReturnInstruction.ReturnInstructionBuilder) other;
			
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::getOrCreateQuantity);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(quantity, _that.getQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnInstructionBuilder {" +
				"quantity=" + this.quantity +
			'}';
		}
	}
}
