package cdm.event.common;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.PartyChangeInstruction.PartyChangeInstructionBuilder;
import cdm.event.common.PartyChangeInstruction.PartyChangeInstructionBuilderImpl;
import cdm.event.common.PartyChangeInstruction.PartyChangeInstructionImpl;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.meta.PartyChangeInstructionMeta;
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
 * Specifies instruction to change the party on a trade. This primitive instruction is used in a number of scenarios including: clearing, allocation and novation. The instrution must include a trade identifier, because a change of party effectively results in a different trade.
 * @version ${project.version}
 */
@RosettaDataType(value="PartyChangeInstruction", builder=PartyChangeInstruction.PartyChangeInstructionBuilderImpl.class, version="${project.version}")
public interface PartyChangeInstruction extends RosettaModelObject {

	PartyChangeInstructionMeta metaData = new PartyChangeInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The new counterparty who is stepping into the trade. The stepping out counterparty is inferred based on the counterparty role that is being updated.
	 */
	Counterparty getCounterparty();
	/**
	 * Specifies an ancillary party to be added onto the new transaction, e.g. the original executing party in an allocation.
	 */
	AncillaryParty getAncillaryParty();
	/**
	 * Specifies an additional party roles to be added on to the new transaction.
	 */
	PartyRole getPartyRole();
	/**
	 * The identifier to be assigned to the new trade post change of party.
	 */
	List<? extends TradeIdentifier> getTradeId();

	/*********************** Build Methods  ***********************/
	PartyChangeInstruction build();
	
	PartyChangeInstruction.PartyChangeInstructionBuilder toBuilder();
	
	static PartyChangeInstruction.PartyChangeInstructionBuilder builder() {
		return new PartyChangeInstruction.PartyChangeInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartyChangeInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartyChangeInstruction> getType() {
		return PartyChangeInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("counterparty"), processor, Counterparty.class, getCounterparty());
		processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.class, getAncillaryParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("tradeId"), processor, TradeIdentifier.class, getTradeId());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyChangeInstructionBuilder extends PartyChangeInstruction, RosettaModelObjectBuilder {
		Counterparty.CounterpartyBuilder getOrCreateCounterparty();
		Counterparty.CounterpartyBuilder getCounterparty();
		AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty();
		AncillaryParty.AncillaryPartyBuilder getAncillaryParty();
		PartyRole.PartyRoleBuilder getOrCreatePartyRole();
		PartyRole.PartyRoleBuilder getPartyRole();
		TradeIdentifier.TradeIdentifierBuilder getOrCreateTradeId(int _index);
		List<? extends TradeIdentifier.TradeIdentifierBuilder> getTradeId();
		PartyChangeInstruction.PartyChangeInstructionBuilder setCounterparty(Counterparty counterparty);
		PartyChangeInstruction.PartyChangeInstructionBuilder setAncillaryParty(AncillaryParty ancillaryParty);
		PartyChangeInstruction.PartyChangeInstructionBuilder setPartyRole(PartyRole partyRole);
		PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(TradeIdentifier tradeId0);
		PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(TradeIdentifier tradeId1, int _idx);
		PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(List<? extends TradeIdentifier> tradeId2);
		PartyChangeInstruction.PartyChangeInstructionBuilder setTradeId(List<? extends TradeIdentifier> tradeId3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("counterparty"), processor, Counterparty.CounterpartyBuilder.class, getCounterparty());
			processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.AncillaryPartyBuilder.class, getAncillaryParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("tradeId"), processor, TradeIdentifier.TradeIdentifierBuilder.class, getTradeId());
		}
		

		PartyChangeInstruction.PartyChangeInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of PartyChangeInstruction  ***********************/
	class PartyChangeInstructionImpl implements PartyChangeInstruction {
		private final Counterparty counterparty;
		private final AncillaryParty ancillaryParty;
		private final PartyRole partyRole;
		private final List<? extends TradeIdentifier> tradeId;
		
		protected PartyChangeInstructionImpl(PartyChangeInstruction.PartyChangeInstructionBuilder builder) {
			this.counterparty = ofNullable(builder.getCounterparty()).map(f->f.build()).orElse(null);
			this.ancillaryParty = ofNullable(builder.getAncillaryParty()).map(f->f.build()).orElse(null);
			this.partyRole = ofNullable(builder.getPartyRole()).map(f->f.build()).orElse(null);
			this.tradeId = ofNullable(builder.getTradeId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public Counterparty getCounterparty() {
			return counterparty;
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public AncillaryParty getAncillaryParty() {
			return ancillaryParty;
		}
		
		@Override
		@RosettaAttribute("partyRole")
		public PartyRole getPartyRole() {
			return partyRole;
		}
		
		@Override
		@RosettaAttribute("tradeId")
		public List<? extends TradeIdentifier> getTradeId() {
			return tradeId;
		}
		
		@Override
		public PartyChangeInstruction build() {
			return this;
		}
		
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder toBuilder() {
			PartyChangeInstruction.PartyChangeInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartyChangeInstruction.PartyChangeInstructionBuilder builder) {
			ofNullable(getCounterparty()).ifPresent(builder::setCounterparty);
			ofNullable(getAncillaryParty()).ifPresent(builder::setAncillaryParty);
			ofNullable(getPartyRole()).ifPresent(builder::setPartyRole);
			ofNullable(getTradeId()).ifPresent(builder::setTradeId);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyChangeInstruction _that = getType().cast(o);
		
			if (!Objects.equals(counterparty, _that.getCounterparty())) return false;
			if (!Objects.equals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(partyRole, _that.getPartyRole())) return false;
			if (!ListEquals.listEquals(tradeId, _that.getTradeId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (tradeId != null ? tradeId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyChangeInstruction {" +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"partyRole=" + this.partyRole + ", " +
				"tradeId=" + this.tradeId +
			'}';
		}
	}

	/*********************** Builder Implementation of PartyChangeInstruction  ***********************/
	class PartyChangeInstructionBuilderImpl implements PartyChangeInstruction.PartyChangeInstructionBuilder {
	
		protected Counterparty.CounterpartyBuilder counterparty;
		protected AncillaryParty.AncillaryPartyBuilder ancillaryParty;
		protected PartyRole.PartyRoleBuilder partyRole;
		protected List<TradeIdentifier.TradeIdentifierBuilder> tradeId = new ArrayList<>();
	
		public PartyChangeInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("counterparty")
		public Counterparty.CounterpartyBuilder getCounterparty() {
			return counterparty;
		}
		
		@Override
		public Counterparty.CounterpartyBuilder getOrCreateCounterparty() {
			Counterparty.CounterpartyBuilder result;
			if (counterparty!=null) {
				result = counterparty;
			}
			else {
				result = counterparty = Counterparty.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("ancillaryParty")
		public AncillaryParty.AncillaryPartyBuilder getAncillaryParty() {
			return ancillaryParty;
		}
		
		@Override
		public AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty() {
			AncillaryParty.AncillaryPartyBuilder result;
			if (ancillaryParty!=null) {
				result = ancillaryParty;
			}
			else {
				result = ancillaryParty = AncillaryParty.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("partyRole")
		public PartyRole.PartyRoleBuilder getPartyRole() {
			return partyRole;
		}
		
		@Override
		public PartyRole.PartyRoleBuilder getOrCreatePartyRole() {
			PartyRole.PartyRoleBuilder result;
			if (partyRole!=null) {
				result = partyRole;
			}
			else {
				result = partyRole = PartyRole.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeId")
		public List<? extends TradeIdentifier.TradeIdentifierBuilder> getTradeId() {
			return tradeId;
		}
		
		public TradeIdentifier.TradeIdentifierBuilder getOrCreateTradeId(int _index) {
		
			if (tradeId==null) {
				this.tradeId = new ArrayList<>();
			}
			TradeIdentifier.TradeIdentifierBuilder result;
			return getIndex(tradeId, _index, () -> {
						TradeIdentifier.TradeIdentifierBuilder newTradeId = TradeIdentifier.builder();
						return newTradeId;
					});
		}
		
	
		@Override
		@RosettaAttribute("counterparty")
		public PartyChangeInstruction.PartyChangeInstructionBuilder setCounterparty(Counterparty counterparty) {
			this.counterparty = counterparty==null?null:counterparty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("ancillaryParty")
		public PartyChangeInstruction.PartyChangeInstructionBuilder setAncillaryParty(AncillaryParty ancillaryParty) {
			this.ancillaryParty = ancillaryParty==null?null:ancillaryParty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("partyRole")
		public PartyChangeInstruction.PartyChangeInstructionBuilder setPartyRole(PartyRole partyRole) {
			this.partyRole = partyRole==null?null:partyRole.toBuilder();
			return this;
		}
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(TradeIdentifier tradeId) {
			if (tradeId!=null) this.tradeId.add(tradeId.toBuilder());
			return this;
		}
		
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(TradeIdentifier tradeId, int _idx) {
			getIndex(this.tradeId, _idx, () -> tradeId.toBuilder());
			return this;
		}
		@Override 
		public PartyChangeInstruction.PartyChangeInstructionBuilder addTradeId(List<? extends TradeIdentifier> tradeIds) {
			if (tradeIds != null) {
				for (TradeIdentifier toAdd : tradeIds) {
					this.tradeId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("tradeId")
		public PartyChangeInstruction.PartyChangeInstructionBuilder setTradeId(List<? extends TradeIdentifier> tradeIds) {
			if (tradeIds == null)  {
				this.tradeId = new ArrayList<>();
			}
			else {
				this.tradeId = tradeIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public PartyChangeInstruction build() {
			return new PartyChangeInstruction.PartyChangeInstructionImpl(this);
		}
		
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder prune() {
			if (counterparty!=null && !counterparty.prune().hasData()) counterparty = null;
			if (ancillaryParty!=null && !ancillaryParty.prune().hasData()) ancillaryParty = null;
			if (partyRole!=null && !partyRole.prune().hasData()) partyRole = null;
			tradeId = tradeId.stream().filter(b->b!=null).<TradeIdentifier.TradeIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCounterparty()!=null && getCounterparty().hasData()) return true;
			if (getAncillaryParty()!=null && getAncillaryParty().hasData()) return true;
			if (getPartyRole()!=null && getPartyRole().hasData()) return true;
			if (getTradeId()!=null && getTradeId().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyChangeInstruction.PartyChangeInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartyChangeInstruction.PartyChangeInstructionBuilder o = (PartyChangeInstruction.PartyChangeInstructionBuilder) other;
			
			merger.mergeRosetta(getCounterparty(), o.getCounterparty(), this::setCounterparty);
			merger.mergeRosetta(getAncillaryParty(), o.getAncillaryParty(), this::setAncillaryParty);
			merger.mergeRosetta(getPartyRole(), o.getPartyRole(), this::setPartyRole);
			merger.mergeRosetta(getTradeId(), o.getTradeId(), this::getOrCreateTradeId);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyChangeInstruction _that = getType().cast(o);
		
			if (!Objects.equals(counterparty, _that.getCounterparty())) return false;
			if (!Objects.equals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(partyRole, _that.getPartyRole())) return false;
			if (!ListEquals.listEquals(tradeId, _that.getTradeId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (tradeId != null ? tradeId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyChangeInstructionBuilder {" +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"partyRole=" + this.partyRole + ", " +
				"tradeId=" + this.tradeId +
			'}';
		}
	}
}
