package cdm.event.common;

import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.BillingRecordInstruction.BillingRecordInstructionBuilder;
import cdm.event.common.BillingRecordInstruction.BillingRecordInstructionBuilderImpl;
import cdm.event.common.BillingRecordInstruction.BillingRecordInstructionImpl;
import cdm.event.common.TradeState;
import cdm.event.common.meta.BillingRecordInstructionMeta;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import cdm.observable.event.Observation;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the instructions for creation of a billing record.
 * @version ${project.version}
 */
@RosettaDataType(value="BillingRecordInstruction", builder=BillingRecordInstruction.BillingRecordInstructionBuilderImpl.class, version="${project.version}")
public interface BillingRecordInstruction extends RosettaModelObject {

	BillingRecordInstructionMeta metaData = new BillingRecordInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The trade for the individual billing record.
	 */
	ReferenceWithMetaTradeState getTradeState();
	/**
	 * The observations used to calculate the billing amount.
	 */
	List<? extends Observation> getObservation();
	/**
	 * The starting date of the period described by this record
	 */
	Date getRecordStartDate();
	/**
	 * The ending date of the period described by this record
	 */
	Date getRecordEndDate();
	/**
	 * The date for settlement of the transfer.
	 */
	Date getSettlementDate();

	/*********************** Build Methods  ***********************/
	BillingRecordInstruction build();
	
	BillingRecordInstruction.BillingRecordInstructionBuilder toBuilder();
	
	static BillingRecordInstruction.BillingRecordInstructionBuilder builder() {
		return new BillingRecordInstruction.BillingRecordInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BillingRecordInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BillingRecordInstruction> getType() {
		return BillingRecordInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tradeState"), processor, ReferenceWithMetaTradeState.class, getTradeState());
		processRosetta(path.newSubPath("observation"), processor, Observation.class, getObservation());
		processor.processBasic(path.newSubPath("recordStartDate"), Date.class, getRecordStartDate(), this);
		processor.processBasic(path.newSubPath("recordEndDate"), Date.class, getRecordEndDate(), this);
		processor.processBasic(path.newSubPath("settlementDate"), Date.class, getSettlementDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface BillingRecordInstructionBuilder extends BillingRecordInstruction, RosettaModelObjectBuilder {
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeState();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeState();
		Observation.ObservationBuilder getOrCreateObservation(int _index);
		List<? extends Observation.ObservationBuilder> getObservation();
		BillingRecordInstruction.BillingRecordInstructionBuilder setTradeState(ReferenceWithMetaTradeState tradeState0);
		BillingRecordInstruction.BillingRecordInstructionBuilder setTradeStateValue(TradeState tradeState1);
		BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(Observation observation0);
		BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(Observation observation1, int _idx);
		BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(List<? extends Observation> observation2);
		BillingRecordInstruction.BillingRecordInstructionBuilder setObservation(List<? extends Observation> observation3);
		BillingRecordInstruction.BillingRecordInstructionBuilder setRecordStartDate(Date recordStartDate);
		BillingRecordInstruction.BillingRecordInstructionBuilder setRecordEndDate(Date recordEndDate);
		BillingRecordInstruction.BillingRecordInstructionBuilder setSettlementDate(Date settlementDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tradeState"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getTradeState());
			processRosetta(path.newSubPath("observation"), processor, Observation.ObservationBuilder.class, getObservation());
			processor.processBasic(path.newSubPath("recordStartDate"), Date.class, getRecordStartDate(), this);
			processor.processBasic(path.newSubPath("recordEndDate"), Date.class, getRecordEndDate(), this);
			processor.processBasic(path.newSubPath("settlementDate"), Date.class, getSettlementDate(), this);
		}
		

		BillingRecordInstruction.BillingRecordInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of BillingRecordInstruction  ***********************/
	class BillingRecordInstructionImpl implements BillingRecordInstruction {
		private final ReferenceWithMetaTradeState tradeState;
		private final List<? extends Observation> observation;
		private final Date recordStartDate;
		private final Date recordEndDate;
		private final Date settlementDate;
		
		protected BillingRecordInstructionImpl(BillingRecordInstruction.BillingRecordInstructionBuilder builder) {
			this.tradeState = ofNullable(builder.getTradeState()).map(f->f.build()).orElse(null);
			this.observation = ofNullable(builder.getObservation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.recordStartDate = builder.getRecordStartDate();
			this.recordEndDate = builder.getRecordEndDate();
			this.settlementDate = builder.getSettlementDate();
		}
		
		@Override
		@RosettaAttribute("tradeState")
		public ReferenceWithMetaTradeState getTradeState() {
			return tradeState;
		}
		
		@Override
		@RosettaAttribute("observation")
		public List<? extends Observation> getObservation() {
			return observation;
		}
		
		@Override
		@RosettaAttribute("recordStartDate")
		public Date getRecordStartDate() {
			return recordStartDate;
		}
		
		@Override
		@RosettaAttribute("recordEndDate")
		public Date getRecordEndDate() {
			return recordEndDate;
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public Date getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public BillingRecordInstruction build() {
			return this;
		}
		
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder toBuilder() {
			BillingRecordInstruction.BillingRecordInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BillingRecordInstruction.BillingRecordInstructionBuilder builder) {
			ofNullable(getTradeState()).ifPresent(builder::setTradeState);
			ofNullable(getObservation()).ifPresent(builder::setObservation);
			ofNullable(getRecordStartDate()).ifPresent(builder::setRecordStartDate);
			ofNullable(getRecordEndDate()).ifPresent(builder::setRecordEndDate);
			ofNullable(getSettlementDate()).ifPresent(builder::setSettlementDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingRecordInstruction _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!ListEquals.listEquals(observation, _that.getObservation())) return false;
			if (!Objects.equals(recordStartDate, _that.getRecordStartDate())) return false;
			if (!Objects.equals(recordEndDate, _that.getRecordEndDate())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (observation != null ? observation.hashCode() : 0);
			_result = 31 * _result + (recordStartDate != null ? recordStartDate.hashCode() : 0);
			_result = 31 * _result + (recordEndDate != null ? recordEndDate.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingRecordInstruction {" +
				"tradeState=" + this.tradeState + ", " +
				"observation=" + this.observation + ", " +
				"recordStartDate=" + this.recordStartDate + ", " +
				"recordEndDate=" + this.recordEndDate + ", " +
				"settlementDate=" + this.settlementDate +
			'}';
		}
	}

	/*********************** Builder Implementation of BillingRecordInstruction  ***********************/
	class BillingRecordInstructionBuilderImpl implements BillingRecordInstruction.BillingRecordInstructionBuilder {
	
		protected ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder tradeState;
		protected List<Observation.ObservationBuilder> observation = new ArrayList<>();
		protected Date recordStartDate;
		protected Date recordEndDate;
		protected Date settlementDate;
	
		public BillingRecordInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tradeState")
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeState() {
			return tradeState;
		}
		
		@Override
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeState() {
			ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder result;
			if (tradeState!=null) {
				result = tradeState;
			}
			else {
				result = tradeState = ReferenceWithMetaTradeState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observation")
		public List<? extends Observation.ObservationBuilder> getObservation() {
			return observation;
		}
		
		public Observation.ObservationBuilder getOrCreateObservation(int _index) {
		
			if (observation==null) {
				this.observation = new ArrayList<>();
			}
			Observation.ObservationBuilder result;
			return getIndex(observation, _index, () -> {
						Observation.ObservationBuilder newObservation = Observation.builder();
						return newObservation;
					});
		}
		
		@Override
		@RosettaAttribute("recordStartDate")
		public Date getRecordStartDate() {
			return recordStartDate;
		}
		
		@Override
		@RosettaAttribute("recordEndDate")
		public Date getRecordEndDate() {
			return recordEndDate;
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public Date getSettlementDate() {
			return settlementDate;
		}
		
	
		@Override
		@RosettaAttribute("tradeState")
		public BillingRecordInstruction.BillingRecordInstructionBuilder setTradeState(ReferenceWithMetaTradeState tradeState) {
			this.tradeState = tradeState==null?null:tradeState.toBuilder();
			return this;
		}
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder setTradeStateValue(TradeState tradeState) {
			this.getOrCreateTradeState().setValue(tradeState);
			return this;
		}
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(Observation observation) {
			if (observation!=null) this.observation.add(observation.toBuilder());
			return this;
		}
		
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(Observation observation, int _idx) {
			getIndex(this.observation, _idx, () -> observation.toBuilder());
			return this;
		}
		@Override 
		public BillingRecordInstruction.BillingRecordInstructionBuilder addObservation(List<? extends Observation> observations) {
			if (observations != null) {
				for (Observation toAdd : observations) {
					this.observation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("observation")
		public BillingRecordInstruction.BillingRecordInstructionBuilder setObservation(List<? extends Observation> observations) {
			if (observations == null)  {
				this.observation = new ArrayList<>();
			}
			else {
				this.observation = observations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("recordStartDate")
		public BillingRecordInstruction.BillingRecordInstructionBuilder setRecordStartDate(Date recordStartDate) {
			this.recordStartDate = recordStartDate==null?null:recordStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("recordEndDate")
		public BillingRecordInstruction.BillingRecordInstructionBuilder setRecordEndDate(Date recordEndDate) {
			this.recordEndDate = recordEndDate==null?null:recordEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public BillingRecordInstruction.BillingRecordInstructionBuilder setSettlementDate(Date settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate;
			return this;
		}
		
		@Override
		public BillingRecordInstruction build() {
			return new BillingRecordInstruction.BillingRecordInstructionImpl(this);
		}
		
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder prune() {
			if (tradeState!=null && !tradeState.prune().hasData()) tradeState = null;
			observation = observation.stream().filter(b->b!=null).<Observation.ObservationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTradeState()!=null && getTradeState().hasData()) return true;
			if (getObservation()!=null && getObservation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getRecordStartDate()!=null) return true;
			if (getRecordEndDate()!=null) return true;
			if (getSettlementDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingRecordInstruction.BillingRecordInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BillingRecordInstruction.BillingRecordInstructionBuilder o = (BillingRecordInstruction.BillingRecordInstructionBuilder) other;
			
			merger.mergeRosetta(getTradeState(), o.getTradeState(), this::setTradeState);
			merger.mergeRosetta(getObservation(), o.getObservation(), this::getOrCreateObservation);
			
			merger.mergeBasic(getRecordStartDate(), o.getRecordStartDate(), this::setRecordStartDate);
			merger.mergeBasic(getRecordEndDate(), o.getRecordEndDate(), this::setRecordEndDate);
			merger.mergeBasic(getSettlementDate(), o.getSettlementDate(), this::setSettlementDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingRecordInstruction _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!ListEquals.listEquals(observation, _that.getObservation())) return false;
			if (!Objects.equals(recordStartDate, _that.getRecordStartDate())) return false;
			if (!Objects.equals(recordEndDate, _that.getRecordEndDate())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (observation != null ? observation.hashCode() : 0);
			_result = 31 * _result + (recordStartDate != null ? recordStartDate.hashCode() : 0);
			_result = 31 * _result + (recordEndDate != null ? recordEndDate.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingRecordInstructionBuilder {" +
				"tradeState=" + this.tradeState + ", " +
				"observation=" + this.observation + ", " +
				"recordStartDate=" + this.recordStartDate + ", " +
				"recordEndDate=" + this.recordEndDate + ", " +
				"settlementDate=" + this.settlementDate +
			'}';
		}
	}
}
