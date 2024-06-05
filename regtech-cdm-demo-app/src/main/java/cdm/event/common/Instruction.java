package cdm.event.common;

import cdm.event.common.Instruction;
import cdm.event.common.Instruction.InstructionBuilder;
import cdm.event.common.Instruction.InstructionBuilderImpl;
import cdm.event.common.Instruction.InstructionImpl;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.meta.InstructionMeta;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Instruction to a function that will be used to perform a business event
 * @version ${project.version}
 */
@RosettaDataType(value="Instruction", builder=Instruction.InstructionBuilderImpl.class, version="${project.version}")
public interface Instruction extends RosettaModelObject {

	InstructionMeta metaData = new InstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the primitive instructions that will be used to call primitive event functions.
	 */
	PrimitiveInstruction getPrimitiveInstruction();
	/**
	 * Specifies the trade state that will be acted on by the primitive event functions.
	 */
	ReferenceWithMetaTradeState getBefore();

	/*********************** Build Methods  ***********************/
	Instruction build();
	
	Instruction.InstructionBuilder toBuilder();
	
	static Instruction.InstructionBuilder builder() {
		return new Instruction.InstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Instruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Instruction> getType() {
		return Instruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("primitiveInstruction"), processor, PrimitiveInstruction.class, getPrimitiveInstruction());
		processRosetta(path.newSubPath("before"), processor, ReferenceWithMetaTradeState.class, getBefore());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InstructionBuilder extends Instruction, RosettaModelObjectBuilder {
		PrimitiveInstruction.PrimitiveInstructionBuilder getOrCreatePrimitiveInstruction();
		PrimitiveInstruction.PrimitiveInstructionBuilder getPrimitiveInstruction();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateBefore();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getBefore();
		Instruction.InstructionBuilder setPrimitiveInstruction(PrimitiveInstruction primitiveInstruction);
		Instruction.InstructionBuilder setBefore(ReferenceWithMetaTradeState before0);
		Instruction.InstructionBuilder setBeforeValue(TradeState before1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("primitiveInstruction"), processor, PrimitiveInstruction.PrimitiveInstructionBuilder.class, getPrimitiveInstruction());
			processRosetta(path.newSubPath("before"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getBefore());
		}
		

		Instruction.InstructionBuilder prune();
	}

	/*********************** Immutable Implementation of Instruction  ***********************/
	class InstructionImpl implements Instruction {
		private final PrimitiveInstruction primitiveInstruction;
		private final ReferenceWithMetaTradeState before;
		
		protected InstructionImpl(Instruction.InstructionBuilder builder) {
			this.primitiveInstruction = ofNullable(builder.getPrimitiveInstruction()).map(f->f.build()).orElse(null);
			this.before = ofNullable(builder.getBefore()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("primitiveInstruction")
		public PrimitiveInstruction getPrimitiveInstruction() {
			return primitiveInstruction;
		}
		
		@Override
		@RosettaAttribute("before")
		public ReferenceWithMetaTradeState getBefore() {
			return before;
		}
		
		@Override
		public Instruction build() {
			return this;
		}
		
		@Override
		public Instruction.InstructionBuilder toBuilder() {
			Instruction.InstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Instruction.InstructionBuilder builder) {
			ofNullable(getPrimitiveInstruction()).ifPresent(builder::setPrimitiveInstruction);
			ofNullable(getBefore()).ifPresent(builder::setBefore);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Instruction _that = getType().cast(o);
		
			if (!Objects.equals(primitiveInstruction, _that.getPrimitiveInstruction())) return false;
			if (!Objects.equals(before, _that.getBefore())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primitiveInstruction != null ? primitiveInstruction.hashCode() : 0);
			_result = 31 * _result + (before != null ? before.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Instruction {" +
				"primitiveInstruction=" + this.primitiveInstruction + ", " +
				"before=" + this.before +
			'}';
		}
	}

	/*********************** Builder Implementation of Instruction  ***********************/
	class InstructionBuilderImpl implements Instruction.InstructionBuilder {
	
		protected PrimitiveInstruction.PrimitiveInstructionBuilder primitiveInstruction;
		protected ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder before;
	
		public InstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("primitiveInstruction")
		public PrimitiveInstruction.PrimitiveInstructionBuilder getPrimitiveInstruction() {
			return primitiveInstruction;
		}
		
		@Override
		public PrimitiveInstruction.PrimitiveInstructionBuilder getOrCreatePrimitiveInstruction() {
			PrimitiveInstruction.PrimitiveInstructionBuilder result;
			if (primitiveInstruction!=null) {
				result = primitiveInstruction;
			}
			else {
				result = primitiveInstruction = PrimitiveInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("before")
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getBefore() {
			return before;
		}
		
		@Override
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateBefore() {
			ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder result;
			if (before!=null) {
				result = before;
			}
			else {
				result = before = ReferenceWithMetaTradeState.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("primitiveInstruction")
		public Instruction.InstructionBuilder setPrimitiveInstruction(PrimitiveInstruction primitiveInstruction) {
			this.primitiveInstruction = primitiveInstruction==null?null:primitiveInstruction.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("before")
		public Instruction.InstructionBuilder setBefore(ReferenceWithMetaTradeState before) {
			this.before = before==null?null:before.toBuilder();
			return this;
		}
		@Override
		public Instruction.InstructionBuilder setBeforeValue(TradeState before) {
			this.getOrCreateBefore().setValue(before);
			return this;
		}
		
		@Override
		public Instruction build() {
			return new Instruction.InstructionImpl(this);
		}
		
		@Override
		public Instruction.InstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Instruction.InstructionBuilder prune() {
			if (primitiveInstruction!=null && !primitiveInstruction.prune().hasData()) primitiveInstruction = null;
			if (before!=null && !before.prune().hasData()) before = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrimitiveInstruction()!=null && getPrimitiveInstruction().hasData()) return true;
			if (getBefore()!=null && getBefore().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Instruction.InstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Instruction.InstructionBuilder o = (Instruction.InstructionBuilder) other;
			
			merger.mergeRosetta(getPrimitiveInstruction(), o.getPrimitiveInstruction(), this::setPrimitiveInstruction);
			merger.mergeRosetta(getBefore(), o.getBefore(), this::setBefore);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Instruction _that = getType().cast(o);
		
			if (!Objects.equals(primitiveInstruction, _that.getPrimitiveInstruction())) return false;
			if (!Objects.equals(before, _that.getBefore())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primitiveInstruction != null ? primitiveInstruction.hashCode() : 0);
			_result = 31 * _result + (before != null ? before.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InstructionBuilder {" +
				"primitiveInstruction=" + this.primitiveInstruction + ", " +
				"before=" + this.before +
			'}';
		}
	}
}
