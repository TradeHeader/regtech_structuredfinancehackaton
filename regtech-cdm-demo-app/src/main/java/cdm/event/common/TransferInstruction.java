package cdm.event.common;

import cdm.event.common.TransferInstruction;
import cdm.event.common.TransferInstruction.TransferInstructionBuilder;
import cdm.event.common.TransferInstruction.TransferInstructionBuilderImpl;
import cdm.event.common.TransferInstruction.TransferInstructionImpl;
import cdm.event.common.TransferState;
import cdm.event.common.meta.TransferInstructionMeta;
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
 * Defines the payout on which to create a Transfer along with all necessary resets.
 * @version ${project.version}
 */
@RosettaDataType(value="TransferInstruction", builder=TransferInstruction.TransferInstructionBuilderImpl.class, version="${project.version}")
public interface TransferInstruction extends RosettaModelObject {

	TransferInstructionMeta metaData = new TransferInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the terms and state of a transfers.
	 */
	List<? extends TransferState> getTransferState();

	/*********************** Build Methods  ***********************/
	TransferInstruction build();
	
	TransferInstruction.TransferInstructionBuilder toBuilder();
	
	static TransferInstruction.TransferInstructionBuilder builder() {
		return new TransferInstruction.TransferInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransferInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransferInstruction> getType() {
		return TransferInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("transferState"), processor, TransferState.class, getTransferState());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransferInstructionBuilder extends TransferInstruction, RosettaModelObjectBuilder {
		TransferState.TransferStateBuilder getOrCreateTransferState(int _index);
		List<? extends TransferState.TransferStateBuilder> getTransferState();
		TransferInstruction.TransferInstructionBuilder addTransferState(TransferState transferState0);
		TransferInstruction.TransferInstructionBuilder addTransferState(TransferState transferState1, int _idx);
		TransferInstruction.TransferInstructionBuilder addTransferState(List<? extends TransferState> transferState2);
		TransferInstruction.TransferInstructionBuilder setTransferState(List<? extends TransferState> transferState3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("transferState"), processor, TransferState.TransferStateBuilder.class, getTransferState());
		}
		

		TransferInstruction.TransferInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of TransferInstruction  ***********************/
	class TransferInstructionImpl implements TransferInstruction {
		private final List<? extends TransferState> transferState;
		
		protected TransferInstructionImpl(TransferInstruction.TransferInstructionBuilder builder) {
			this.transferState = ofNullable(builder.getTransferState()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("transferState")
		public List<? extends TransferState> getTransferState() {
			return transferState;
		}
		
		@Override
		public TransferInstruction build() {
			return this;
		}
		
		@Override
		public TransferInstruction.TransferInstructionBuilder toBuilder() {
			TransferInstruction.TransferInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransferInstruction.TransferInstructionBuilder builder) {
			ofNullable(getTransferState()).ifPresent(builder::setTransferState);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(transferState, _that.getTransferState())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transferState != null ? transferState.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferInstruction {" +
				"transferState=" + this.transferState +
			'}';
		}
	}

	/*********************** Builder Implementation of TransferInstruction  ***********************/
	class TransferInstructionBuilderImpl implements TransferInstruction.TransferInstructionBuilder {
	
		protected List<TransferState.TransferStateBuilder> transferState = new ArrayList<>();
	
		public TransferInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("transferState")
		public List<? extends TransferState.TransferStateBuilder> getTransferState() {
			return transferState;
		}
		
		public TransferState.TransferStateBuilder getOrCreateTransferState(int _index) {
		
			if (transferState==null) {
				this.transferState = new ArrayList<>();
			}
			TransferState.TransferStateBuilder result;
			return getIndex(transferState, _index, () -> {
						TransferState.TransferStateBuilder newTransferState = TransferState.builder();
						return newTransferState;
					});
		}
		
	
		@Override
		public TransferInstruction.TransferInstructionBuilder addTransferState(TransferState transferState) {
			if (transferState!=null) this.transferState.add(transferState.toBuilder());
			return this;
		}
		
		@Override
		public TransferInstruction.TransferInstructionBuilder addTransferState(TransferState transferState, int _idx) {
			getIndex(this.transferState, _idx, () -> transferState.toBuilder());
			return this;
		}
		@Override 
		public TransferInstruction.TransferInstructionBuilder addTransferState(List<? extends TransferState> transferStates) {
			if (transferStates != null) {
				for (TransferState toAdd : transferStates) {
					this.transferState.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("transferState")
		public TransferInstruction.TransferInstructionBuilder setTransferState(List<? extends TransferState> transferStates) {
			if (transferStates == null)  {
				this.transferState = new ArrayList<>();
			}
			else {
				this.transferState = transferStates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public TransferInstruction build() {
			return new TransferInstruction.TransferInstructionImpl(this);
		}
		
		@Override
		public TransferInstruction.TransferInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferInstruction.TransferInstructionBuilder prune() {
			transferState = transferState.stream().filter(b->b!=null).<TransferState.TransferStateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTransferState()!=null && getTransferState().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferInstruction.TransferInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransferInstruction.TransferInstructionBuilder o = (TransferInstruction.TransferInstructionBuilder) other;
			
			merger.mergeRosetta(getTransferState(), o.getTransferState(), this::getOrCreateTransferState);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(transferState, _that.getTransferState())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transferState != null ? transferState.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferInstructionBuilder {" +
				"transferState=" + this.transferState +
			'}';
		}
	}
}
