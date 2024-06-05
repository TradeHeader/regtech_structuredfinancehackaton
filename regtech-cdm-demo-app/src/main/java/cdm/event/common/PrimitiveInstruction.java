package cdm.event.common;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilder;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilderImpl;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionImpl;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.ResetInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.StockSplitInstruction;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TransferInstruction;
import cdm.event.common.ValuationInstruction;
import cdm.event.common.meta.PrimitiveInstructionMeta;
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
 * A Primitive Instruction describes the inputs required to pass into the corresponding PrimitiveEvent function.
 * @version ${project.version}
 */
@RosettaDataType(value="PrimitiveInstruction", builder=PrimitiveInstruction.PrimitiveInstructionBuilderImpl.class, version="${project.version}")
public interface PrimitiveInstruction extends RosettaModelObject {

	PrimitiveInstructionMeta metaData = new PrimitiveInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies instructions describing an contract formation primitive event.
	 */
	ContractFormationInstruction getContractFormation();
	/**
	 * Specifies instructions describing an execution primitive event.
	 */
	ExecutionInstruction getExecution();
	/**
	 * Specifies instructions describing an exercise primitive event.
	 */
	ExerciseInstruction getExercise();
	/**
	 * Specifies instructions describing a party change primitive event.
	 */
	PartyChangeInstruction getPartyChange();
	/**
	 * Specifies instructions describing an quantity change primitive event.
	 */
	QuantityChangeInstruction getQuantityChange();
	/**
	 * Specifies instructions describing a reset event.
	 */
	ResetInstruction getReset();
	/**
	 * Specifies instructions to split a trade into multiple branches.
	 */
	SplitInstruction getSplit();
	/**
	 * Specifies instructions describing a terms change primitive event.
	 */
	TermsChangeInstruction getTermsChange();
	/**
	 * Specifies instructions describing a transfer primitive event.
	 */
	TransferInstruction getTransfer();
	/**
	 * Specifies inputs needed to process a Index Transition business event.
	 */
	IndexTransitionInstruction getIndexTransition();
	/**
	 * Specifies inputs needed to process a Stock Split business event.
	 */
	StockSplitInstruction getStockSplit();
	/**
	 * Specifies inputs needed to process an observation.
	 */
	ObservationInstruction getObservation();
	/**
	 * Specifies inputs needed to process an update of a valuation.
	 */
	ValuationInstruction getValuation();

	/*********************** Build Methods  ***********************/
	PrimitiveInstruction build();
	
	PrimitiveInstruction.PrimitiveInstructionBuilder toBuilder();
	
	static PrimitiveInstruction.PrimitiveInstructionBuilder builder() {
		return new PrimitiveInstruction.PrimitiveInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PrimitiveInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PrimitiveInstruction> getType() {
		return PrimitiveInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("contractFormation"), processor, ContractFormationInstruction.class, getContractFormation());
		processRosetta(path.newSubPath("execution"), processor, ExecutionInstruction.class, getExecution());
		processRosetta(path.newSubPath("exercise"), processor, ExerciseInstruction.class, getExercise());
		processRosetta(path.newSubPath("partyChange"), processor, PartyChangeInstruction.class, getPartyChange());
		processRosetta(path.newSubPath("quantityChange"), processor, QuantityChangeInstruction.class, getQuantityChange());
		processRosetta(path.newSubPath("reset"), processor, ResetInstruction.class, getReset());
		processRosetta(path.newSubPath("split"), processor, SplitInstruction.class, getSplit());
		processRosetta(path.newSubPath("termsChange"), processor, TermsChangeInstruction.class, getTermsChange());
		processRosetta(path.newSubPath("transfer"), processor, TransferInstruction.class, getTransfer());
		processRosetta(path.newSubPath("indexTransition"), processor, IndexTransitionInstruction.class, getIndexTransition());
		processRosetta(path.newSubPath("stockSplit"), processor, StockSplitInstruction.class, getStockSplit());
		processRosetta(path.newSubPath("observation"), processor, ObservationInstruction.class, getObservation());
		processRosetta(path.newSubPath("valuation"), processor, ValuationInstruction.class, getValuation());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PrimitiveInstructionBuilder extends PrimitiveInstruction, RosettaModelObjectBuilder {
		ContractFormationInstruction.ContractFormationInstructionBuilder getOrCreateContractFormation();
		ContractFormationInstruction.ContractFormationInstructionBuilder getContractFormation();
		ExecutionInstruction.ExecutionInstructionBuilder getOrCreateExecution();
		ExecutionInstruction.ExecutionInstructionBuilder getExecution();
		ExerciseInstruction.ExerciseInstructionBuilder getOrCreateExercise();
		ExerciseInstruction.ExerciseInstructionBuilder getExercise();
		PartyChangeInstruction.PartyChangeInstructionBuilder getOrCreatePartyChange();
		PartyChangeInstruction.PartyChangeInstructionBuilder getPartyChange();
		QuantityChangeInstruction.QuantityChangeInstructionBuilder getOrCreateQuantityChange();
		QuantityChangeInstruction.QuantityChangeInstructionBuilder getQuantityChange();
		ResetInstruction.ResetInstructionBuilder getOrCreateReset();
		ResetInstruction.ResetInstructionBuilder getReset();
		SplitInstruction.SplitInstructionBuilder getOrCreateSplit();
		SplitInstruction.SplitInstructionBuilder getSplit();
		TermsChangeInstruction.TermsChangeInstructionBuilder getOrCreateTermsChange();
		TermsChangeInstruction.TermsChangeInstructionBuilder getTermsChange();
		TransferInstruction.TransferInstructionBuilder getOrCreateTransfer();
		TransferInstruction.TransferInstructionBuilder getTransfer();
		IndexTransitionInstruction.IndexTransitionInstructionBuilder getOrCreateIndexTransition();
		IndexTransitionInstruction.IndexTransitionInstructionBuilder getIndexTransition();
		StockSplitInstruction.StockSplitInstructionBuilder getOrCreateStockSplit();
		StockSplitInstruction.StockSplitInstructionBuilder getStockSplit();
		ObservationInstruction.ObservationInstructionBuilder getOrCreateObservation();
		ObservationInstruction.ObservationInstructionBuilder getObservation();
		ValuationInstruction.ValuationInstructionBuilder getOrCreateValuation();
		ValuationInstruction.ValuationInstructionBuilder getValuation();
		PrimitiveInstruction.PrimitiveInstructionBuilder setContractFormation(ContractFormationInstruction contractFormation);
		PrimitiveInstruction.PrimitiveInstructionBuilder setExecution(ExecutionInstruction execution);
		PrimitiveInstruction.PrimitiveInstructionBuilder setExercise(ExerciseInstruction exercise);
		PrimitiveInstruction.PrimitiveInstructionBuilder setPartyChange(PartyChangeInstruction partyChange);
		PrimitiveInstruction.PrimitiveInstructionBuilder setQuantityChange(QuantityChangeInstruction quantityChange);
		PrimitiveInstruction.PrimitiveInstructionBuilder setReset(ResetInstruction reset);
		PrimitiveInstruction.PrimitiveInstructionBuilder setSplit(SplitInstruction split);
		PrimitiveInstruction.PrimitiveInstructionBuilder setTermsChange(TermsChangeInstruction termsChange);
		PrimitiveInstruction.PrimitiveInstructionBuilder setTransfer(TransferInstruction transfer);
		PrimitiveInstruction.PrimitiveInstructionBuilder setIndexTransition(IndexTransitionInstruction indexTransition);
		PrimitiveInstruction.PrimitiveInstructionBuilder setStockSplit(StockSplitInstruction stockSplit);
		PrimitiveInstruction.PrimitiveInstructionBuilder setObservation(ObservationInstruction observation);
		PrimitiveInstruction.PrimitiveInstructionBuilder setValuation(ValuationInstruction valuation);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("contractFormation"), processor, ContractFormationInstruction.ContractFormationInstructionBuilder.class, getContractFormation());
			processRosetta(path.newSubPath("execution"), processor, ExecutionInstruction.ExecutionInstructionBuilder.class, getExecution());
			processRosetta(path.newSubPath("exercise"), processor, ExerciseInstruction.ExerciseInstructionBuilder.class, getExercise());
			processRosetta(path.newSubPath("partyChange"), processor, PartyChangeInstruction.PartyChangeInstructionBuilder.class, getPartyChange());
			processRosetta(path.newSubPath("quantityChange"), processor, QuantityChangeInstruction.QuantityChangeInstructionBuilder.class, getQuantityChange());
			processRosetta(path.newSubPath("reset"), processor, ResetInstruction.ResetInstructionBuilder.class, getReset());
			processRosetta(path.newSubPath("split"), processor, SplitInstruction.SplitInstructionBuilder.class, getSplit());
			processRosetta(path.newSubPath("termsChange"), processor, TermsChangeInstruction.TermsChangeInstructionBuilder.class, getTermsChange());
			processRosetta(path.newSubPath("transfer"), processor, TransferInstruction.TransferInstructionBuilder.class, getTransfer());
			processRosetta(path.newSubPath("indexTransition"), processor, IndexTransitionInstruction.IndexTransitionInstructionBuilder.class, getIndexTransition());
			processRosetta(path.newSubPath("stockSplit"), processor, StockSplitInstruction.StockSplitInstructionBuilder.class, getStockSplit());
			processRosetta(path.newSubPath("observation"), processor, ObservationInstruction.ObservationInstructionBuilder.class, getObservation());
			processRosetta(path.newSubPath("valuation"), processor, ValuationInstruction.ValuationInstructionBuilder.class, getValuation());
		}
		

		PrimitiveInstruction.PrimitiveInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of PrimitiveInstruction  ***********************/
	class PrimitiveInstructionImpl implements PrimitiveInstruction {
		private final ContractFormationInstruction contractFormation;
		private final ExecutionInstruction execution;
		private final ExerciseInstruction exercise;
		private final PartyChangeInstruction partyChange;
		private final QuantityChangeInstruction quantityChange;
		private final ResetInstruction reset;
		private final SplitInstruction split;
		private final TermsChangeInstruction termsChange;
		private final TransferInstruction transfer;
		private final IndexTransitionInstruction indexTransition;
		private final StockSplitInstruction stockSplit;
		private final ObservationInstruction observation;
		private final ValuationInstruction valuation;
		
		protected PrimitiveInstructionImpl(PrimitiveInstruction.PrimitiveInstructionBuilder builder) {
			this.contractFormation = ofNullable(builder.getContractFormation()).map(f->f.build()).orElse(null);
			this.execution = ofNullable(builder.getExecution()).map(f->f.build()).orElse(null);
			this.exercise = ofNullable(builder.getExercise()).map(f->f.build()).orElse(null);
			this.partyChange = ofNullable(builder.getPartyChange()).map(f->f.build()).orElse(null);
			this.quantityChange = ofNullable(builder.getQuantityChange()).map(f->f.build()).orElse(null);
			this.reset = ofNullable(builder.getReset()).map(f->f.build()).orElse(null);
			this.split = ofNullable(builder.getSplit()).map(f->f.build()).orElse(null);
			this.termsChange = ofNullable(builder.getTermsChange()).map(f->f.build()).orElse(null);
			this.transfer = ofNullable(builder.getTransfer()).map(f->f.build()).orElse(null);
			this.indexTransition = ofNullable(builder.getIndexTransition()).map(f->f.build()).orElse(null);
			this.stockSplit = ofNullable(builder.getStockSplit()).map(f->f.build()).orElse(null);
			this.observation = ofNullable(builder.getObservation()).map(f->f.build()).orElse(null);
			this.valuation = ofNullable(builder.getValuation()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("contractFormation")
		public ContractFormationInstruction getContractFormation() {
			return contractFormation;
		}
		
		@Override
		@RosettaAttribute("execution")
		public ExecutionInstruction getExecution() {
			return execution;
		}
		
		@Override
		@RosettaAttribute("exercise")
		public ExerciseInstruction getExercise() {
			return exercise;
		}
		
		@Override
		@RosettaAttribute("partyChange")
		public PartyChangeInstruction getPartyChange() {
			return partyChange;
		}
		
		@Override
		@RosettaAttribute("quantityChange")
		public QuantityChangeInstruction getQuantityChange() {
			return quantityChange;
		}
		
		@Override
		@RosettaAttribute("reset")
		public ResetInstruction getReset() {
			return reset;
		}
		
		@Override
		@RosettaAttribute("split")
		public SplitInstruction getSplit() {
			return split;
		}
		
		@Override
		@RosettaAttribute("termsChange")
		public TermsChangeInstruction getTermsChange() {
			return termsChange;
		}
		
		@Override
		@RosettaAttribute("transfer")
		public TransferInstruction getTransfer() {
			return transfer;
		}
		
		@Override
		@RosettaAttribute("indexTransition")
		public IndexTransitionInstruction getIndexTransition() {
			return indexTransition;
		}
		
		@Override
		@RosettaAttribute("stockSplit")
		public StockSplitInstruction getStockSplit() {
			return stockSplit;
		}
		
		@Override
		@RosettaAttribute("observation")
		public ObservationInstruction getObservation() {
			return observation;
		}
		
		@Override
		@RosettaAttribute("valuation")
		public ValuationInstruction getValuation() {
			return valuation;
		}
		
		@Override
		public PrimitiveInstruction build() {
			return this;
		}
		
		@Override
		public PrimitiveInstruction.PrimitiveInstructionBuilder toBuilder() {
			PrimitiveInstruction.PrimitiveInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PrimitiveInstruction.PrimitiveInstructionBuilder builder) {
			ofNullable(getContractFormation()).ifPresent(builder::setContractFormation);
			ofNullable(getExecution()).ifPresent(builder::setExecution);
			ofNullable(getExercise()).ifPresent(builder::setExercise);
			ofNullable(getPartyChange()).ifPresent(builder::setPartyChange);
			ofNullable(getQuantityChange()).ifPresent(builder::setQuantityChange);
			ofNullable(getReset()).ifPresent(builder::setReset);
			ofNullable(getSplit()).ifPresent(builder::setSplit);
			ofNullable(getTermsChange()).ifPresent(builder::setTermsChange);
			ofNullable(getTransfer()).ifPresent(builder::setTransfer);
			ofNullable(getIndexTransition()).ifPresent(builder::setIndexTransition);
			ofNullable(getStockSplit()).ifPresent(builder::setStockSplit);
			ofNullable(getObservation()).ifPresent(builder::setObservation);
			ofNullable(getValuation()).ifPresent(builder::setValuation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrimitiveInstruction _that = getType().cast(o);
		
			if (!Objects.equals(contractFormation, _that.getContractFormation())) return false;
			if (!Objects.equals(execution, _that.getExecution())) return false;
			if (!Objects.equals(exercise, _that.getExercise())) return false;
			if (!Objects.equals(partyChange, _that.getPartyChange())) return false;
			if (!Objects.equals(quantityChange, _that.getQuantityChange())) return false;
			if (!Objects.equals(reset, _that.getReset())) return false;
			if (!Objects.equals(split, _that.getSplit())) return false;
			if (!Objects.equals(termsChange, _that.getTermsChange())) return false;
			if (!Objects.equals(transfer, _that.getTransfer())) return false;
			if (!Objects.equals(indexTransition, _that.getIndexTransition())) return false;
			if (!Objects.equals(stockSplit, _that.getStockSplit())) return false;
			if (!Objects.equals(observation, _that.getObservation())) return false;
			if (!Objects.equals(valuation, _that.getValuation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractFormation != null ? contractFormation.hashCode() : 0);
			_result = 31 * _result + (execution != null ? execution.hashCode() : 0);
			_result = 31 * _result + (exercise != null ? exercise.hashCode() : 0);
			_result = 31 * _result + (partyChange != null ? partyChange.hashCode() : 0);
			_result = 31 * _result + (quantityChange != null ? quantityChange.hashCode() : 0);
			_result = 31 * _result + (reset != null ? reset.hashCode() : 0);
			_result = 31 * _result + (split != null ? split.hashCode() : 0);
			_result = 31 * _result + (termsChange != null ? termsChange.hashCode() : 0);
			_result = 31 * _result + (transfer != null ? transfer.hashCode() : 0);
			_result = 31 * _result + (indexTransition != null ? indexTransition.hashCode() : 0);
			_result = 31 * _result + (stockSplit != null ? stockSplit.hashCode() : 0);
			_result = 31 * _result + (observation != null ? observation.hashCode() : 0);
			_result = 31 * _result + (valuation != null ? valuation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrimitiveInstruction {" +
				"contractFormation=" + this.contractFormation + ", " +
				"execution=" + this.execution + ", " +
				"exercise=" + this.exercise + ", " +
				"partyChange=" + this.partyChange + ", " +
				"quantityChange=" + this.quantityChange + ", " +
				"reset=" + this.reset + ", " +
				"split=" + this.split + ", " +
				"termsChange=" + this.termsChange + ", " +
				"transfer=" + this.transfer + ", " +
				"indexTransition=" + this.indexTransition + ", " +
				"stockSplit=" + this.stockSplit + ", " +
				"observation=" + this.observation + ", " +
				"valuation=" + this.valuation +
			'}';
		}
	}

	/*********************** Builder Implementation of PrimitiveInstruction  ***********************/
	class PrimitiveInstructionBuilderImpl implements PrimitiveInstruction.PrimitiveInstructionBuilder {
	
		protected ContractFormationInstruction.ContractFormationInstructionBuilder contractFormation;
		protected ExecutionInstruction.ExecutionInstructionBuilder execution;
		protected ExerciseInstruction.ExerciseInstructionBuilder exercise;
		protected PartyChangeInstruction.PartyChangeInstructionBuilder partyChange;
		protected QuantityChangeInstruction.QuantityChangeInstructionBuilder quantityChange;
		protected ResetInstruction.ResetInstructionBuilder reset;
		protected SplitInstruction.SplitInstructionBuilder split;
		protected TermsChangeInstruction.TermsChangeInstructionBuilder termsChange;
		protected TransferInstruction.TransferInstructionBuilder transfer;
		protected IndexTransitionInstruction.IndexTransitionInstructionBuilder indexTransition;
		protected StockSplitInstruction.StockSplitInstructionBuilder stockSplit;
		protected ObservationInstruction.ObservationInstructionBuilder observation;
		protected ValuationInstruction.ValuationInstructionBuilder valuation;
	
		public PrimitiveInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("contractFormation")
		public ContractFormationInstruction.ContractFormationInstructionBuilder getContractFormation() {
			return contractFormation;
		}
		
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder getOrCreateContractFormation() {
			ContractFormationInstruction.ContractFormationInstructionBuilder result;
			if (contractFormation!=null) {
				result = contractFormation;
			}
			else {
				result = contractFormation = ContractFormationInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("execution")
		public ExecutionInstruction.ExecutionInstructionBuilder getExecution() {
			return execution;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder getOrCreateExecution() {
			ExecutionInstruction.ExecutionInstructionBuilder result;
			if (execution!=null) {
				result = execution;
			}
			else {
				result = execution = ExecutionInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exercise")
		public ExerciseInstruction.ExerciseInstructionBuilder getExercise() {
			return exercise;
		}
		
		@Override
		public ExerciseInstruction.ExerciseInstructionBuilder getOrCreateExercise() {
			ExerciseInstruction.ExerciseInstructionBuilder result;
			if (exercise!=null) {
				result = exercise;
			}
			else {
				result = exercise = ExerciseInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("partyChange")
		public PartyChangeInstruction.PartyChangeInstructionBuilder getPartyChange() {
			return partyChange;
		}
		
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder getOrCreatePartyChange() {
			PartyChangeInstruction.PartyChangeInstructionBuilder result;
			if (partyChange!=null) {
				result = partyChange;
			}
			else {
				result = partyChange = PartyChangeInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("quantityChange")
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder getQuantityChange() {
			return quantityChange;
		}
		
		@Override
		public QuantityChangeInstruction.QuantityChangeInstructionBuilder getOrCreateQuantityChange() {
			QuantityChangeInstruction.QuantityChangeInstructionBuilder result;
			if (quantityChange!=null) {
				result = quantityChange;
			}
			else {
				result = quantityChange = QuantityChangeInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("reset")
		public ResetInstruction.ResetInstructionBuilder getReset() {
			return reset;
		}
		
		@Override
		public ResetInstruction.ResetInstructionBuilder getOrCreateReset() {
			ResetInstruction.ResetInstructionBuilder result;
			if (reset!=null) {
				result = reset;
			}
			else {
				result = reset = ResetInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("split")
		public SplitInstruction.SplitInstructionBuilder getSplit() {
			return split;
		}
		
		@Override
		public SplitInstruction.SplitInstructionBuilder getOrCreateSplit() {
			SplitInstruction.SplitInstructionBuilder result;
			if (split!=null) {
				result = split;
			}
			else {
				result = split = SplitInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("termsChange")
		public TermsChangeInstruction.TermsChangeInstructionBuilder getTermsChange() {
			return termsChange;
		}
		
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder getOrCreateTermsChange() {
			TermsChangeInstruction.TermsChangeInstructionBuilder result;
			if (termsChange!=null) {
				result = termsChange;
			}
			else {
				result = termsChange = TermsChangeInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("transfer")
		public TransferInstruction.TransferInstructionBuilder getTransfer() {
			return transfer;
		}
		
		@Override
		public TransferInstruction.TransferInstructionBuilder getOrCreateTransfer() {
			TransferInstruction.TransferInstructionBuilder result;
			if (transfer!=null) {
				result = transfer;
			}
			else {
				result = transfer = TransferInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexTransition")
		public IndexTransitionInstruction.IndexTransitionInstructionBuilder getIndexTransition() {
			return indexTransition;
		}
		
		@Override
		public IndexTransitionInstruction.IndexTransitionInstructionBuilder getOrCreateIndexTransition() {
			IndexTransitionInstruction.IndexTransitionInstructionBuilder result;
			if (indexTransition!=null) {
				result = indexTransition;
			}
			else {
				result = indexTransition = IndexTransitionInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("stockSplit")
		public StockSplitInstruction.StockSplitInstructionBuilder getStockSplit() {
			return stockSplit;
		}
		
		@Override
		public StockSplitInstruction.StockSplitInstructionBuilder getOrCreateStockSplit() {
			StockSplitInstruction.StockSplitInstructionBuilder result;
			if (stockSplit!=null) {
				result = stockSplit;
			}
			else {
				result = stockSplit = StockSplitInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observation")
		public ObservationInstruction.ObservationInstructionBuilder getObservation() {
			return observation;
		}
		
		@Override
		public ObservationInstruction.ObservationInstructionBuilder getOrCreateObservation() {
			ObservationInstruction.ObservationInstructionBuilder result;
			if (observation!=null) {
				result = observation;
			}
			else {
				result = observation = ObservationInstruction.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuation")
		public ValuationInstruction.ValuationInstructionBuilder getValuation() {
			return valuation;
		}
		
		@Override
		public ValuationInstruction.ValuationInstructionBuilder getOrCreateValuation() {
			ValuationInstruction.ValuationInstructionBuilder result;
			if (valuation!=null) {
				result = valuation;
			}
			else {
				result = valuation = ValuationInstruction.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("contractFormation")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setContractFormation(ContractFormationInstruction contractFormation) {
			this.contractFormation = contractFormation==null?null:contractFormation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("execution")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setExecution(ExecutionInstruction execution) {
			this.execution = execution==null?null:execution.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exercise")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setExercise(ExerciseInstruction exercise) {
			this.exercise = exercise==null?null:exercise.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("partyChange")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setPartyChange(PartyChangeInstruction partyChange) {
			this.partyChange = partyChange==null?null:partyChange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("quantityChange")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setQuantityChange(QuantityChangeInstruction quantityChange) {
			this.quantityChange = quantityChange==null?null:quantityChange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("reset")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setReset(ResetInstruction reset) {
			this.reset = reset==null?null:reset.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("split")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setSplit(SplitInstruction split) {
			this.split = split==null?null:split.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("termsChange")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setTermsChange(TermsChangeInstruction termsChange) {
			this.termsChange = termsChange==null?null:termsChange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("transfer")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setTransfer(TransferInstruction transfer) {
			this.transfer = transfer==null?null:transfer.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("indexTransition")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setIndexTransition(IndexTransitionInstruction indexTransition) {
			this.indexTransition = indexTransition==null?null:indexTransition.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("stockSplit")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setStockSplit(StockSplitInstruction stockSplit) {
			this.stockSplit = stockSplit==null?null:stockSplit.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observation")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setObservation(ObservationInstruction observation) {
			this.observation = observation==null?null:observation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuation")
		public PrimitiveInstruction.PrimitiveInstructionBuilder setValuation(ValuationInstruction valuation) {
			this.valuation = valuation==null?null:valuation.toBuilder();
			return this;
		}
		
		@Override
		public PrimitiveInstruction build() {
			return new PrimitiveInstruction.PrimitiveInstructionImpl(this);
		}
		
		@Override
		public PrimitiveInstruction.PrimitiveInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrimitiveInstruction.PrimitiveInstructionBuilder prune() {
			if (contractFormation!=null && !contractFormation.prune().hasData()) contractFormation = null;
			if (execution!=null && !execution.prune().hasData()) execution = null;
			if (exercise!=null && !exercise.prune().hasData()) exercise = null;
			if (partyChange!=null && !partyChange.prune().hasData()) partyChange = null;
			if (quantityChange!=null && !quantityChange.prune().hasData()) quantityChange = null;
			if (reset!=null && !reset.prune().hasData()) reset = null;
			if (split!=null && !split.prune().hasData()) split = null;
			if (termsChange!=null && !termsChange.prune().hasData()) termsChange = null;
			if (transfer!=null && !transfer.prune().hasData()) transfer = null;
			if (indexTransition!=null && !indexTransition.prune().hasData()) indexTransition = null;
			if (stockSplit!=null && !stockSplit.prune().hasData()) stockSplit = null;
			if (observation!=null && !observation.prune().hasData()) observation = null;
			if (valuation!=null && !valuation.prune().hasData()) valuation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getContractFormation()!=null && getContractFormation().hasData()) return true;
			if (getExecution()!=null && getExecution().hasData()) return true;
			if (getExercise()!=null && getExercise().hasData()) return true;
			if (getPartyChange()!=null && getPartyChange().hasData()) return true;
			if (getQuantityChange()!=null && getQuantityChange().hasData()) return true;
			if (getReset()!=null && getReset().hasData()) return true;
			if (getSplit()!=null && getSplit().hasData()) return true;
			if (getTermsChange()!=null && getTermsChange().hasData()) return true;
			if (getTransfer()!=null && getTransfer().hasData()) return true;
			if (getIndexTransition()!=null && getIndexTransition().hasData()) return true;
			if (getStockSplit()!=null && getStockSplit().hasData()) return true;
			if (getObservation()!=null && getObservation().hasData()) return true;
			if (getValuation()!=null && getValuation().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrimitiveInstruction.PrimitiveInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PrimitiveInstruction.PrimitiveInstructionBuilder o = (PrimitiveInstruction.PrimitiveInstructionBuilder) other;
			
			merger.mergeRosetta(getContractFormation(), o.getContractFormation(), this::setContractFormation);
			merger.mergeRosetta(getExecution(), o.getExecution(), this::setExecution);
			merger.mergeRosetta(getExercise(), o.getExercise(), this::setExercise);
			merger.mergeRosetta(getPartyChange(), o.getPartyChange(), this::setPartyChange);
			merger.mergeRosetta(getQuantityChange(), o.getQuantityChange(), this::setQuantityChange);
			merger.mergeRosetta(getReset(), o.getReset(), this::setReset);
			merger.mergeRosetta(getSplit(), o.getSplit(), this::setSplit);
			merger.mergeRosetta(getTermsChange(), o.getTermsChange(), this::setTermsChange);
			merger.mergeRosetta(getTransfer(), o.getTransfer(), this::setTransfer);
			merger.mergeRosetta(getIndexTransition(), o.getIndexTransition(), this::setIndexTransition);
			merger.mergeRosetta(getStockSplit(), o.getStockSplit(), this::setStockSplit);
			merger.mergeRosetta(getObservation(), o.getObservation(), this::setObservation);
			merger.mergeRosetta(getValuation(), o.getValuation(), this::setValuation);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrimitiveInstruction _that = getType().cast(o);
		
			if (!Objects.equals(contractFormation, _that.getContractFormation())) return false;
			if (!Objects.equals(execution, _that.getExecution())) return false;
			if (!Objects.equals(exercise, _that.getExercise())) return false;
			if (!Objects.equals(partyChange, _that.getPartyChange())) return false;
			if (!Objects.equals(quantityChange, _that.getQuantityChange())) return false;
			if (!Objects.equals(reset, _that.getReset())) return false;
			if (!Objects.equals(split, _that.getSplit())) return false;
			if (!Objects.equals(termsChange, _that.getTermsChange())) return false;
			if (!Objects.equals(transfer, _that.getTransfer())) return false;
			if (!Objects.equals(indexTransition, _that.getIndexTransition())) return false;
			if (!Objects.equals(stockSplit, _that.getStockSplit())) return false;
			if (!Objects.equals(observation, _that.getObservation())) return false;
			if (!Objects.equals(valuation, _that.getValuation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractFormation != null ? contractFormation.hashCode() : 0);
			_result = 31 * _result + (execution != null ? execution.hashCode() : 0);
			_result = 31 * _result + (exercise != null ? exercise.hashCode() : 0);
			_result = 31 * _result + (partyChange != null ? partyChange.hashCode() : 0);
			_result = 31 * _result + (quantityChange != null ? quantityChange.hashCode() : 0);
			_result = 31 * _result + (reset != null ? reset.hashCode() : 0);
			_result = 31 * _result + (split != null ? split.hashCode() : 0);
			_result = 31 * _result + (termsChange != null ? termsChange.hashCode() : 0);
			_result = 31 * _result + (transfer != null ? transfer.hashCode() : 0);
			_result = 31 * _result + (indexTransition != null ? indexTransition.hashCode() : 0);
			_result = 31 * _result + (stockSplit != null ? stockSplit.hashCode() : 0);
			_result = 31 * _result + (observation != null ? observation.hashCode() : 0);
			_result = 31 * _result + (valuation != null ? valuation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrimitiveInstructionBuilder {" +
				"contractFormation=" + this.contractFormation + ", " +
				"execution=" + this.execution + ", " +
				"exercise=" + this.exercise + ", " +
				"partyChange=" + this.partyChange + ", " +
				"quantityChange=" + this.quantityChange + ", " +
				"reset=" + this.reset + ", " +
				"split=" + this.split + ", " +
				"termsChange=" + this.termsChange + ", " +
				"transfer=" + this.transfer + ", " +
				"indexTransition=" + this.indexTransition + ", " +
				"stockSplit=" + this.stockSplit + ", " +
				"observation=" + this.observation + ", " +
				"valuation=" + this.valuation +
			'}';
		}
	}
}
