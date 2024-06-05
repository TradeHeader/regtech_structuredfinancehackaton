package cdm.event.position;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ContractDetails;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.PositionIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaContractDetails;
import cdm.event.common.metafields.ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import cdm.event.position.ContractBase;
import cdm.event.position.ContractBase.ContractBaseBuilder;
import cdm.event.position.ContractBase.ContractBaseBuilderImpl;
import cdm.event.position.ContractBase.ContractBaseImpl;
import cdm.event.position.CounterpartyPosition;
import cdm.event.position.CounterpartyPosition.CounterpartyPositionBuilder;
import cdm.event.position.CounterpartyPosition.CounterpartyPositionBuilderImpl;
import cdm.event.position.CounterpartyPosition.CounterpartyPositionImpl;
import cdm.event.position.meta.CounterpartyPositionMeta;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder;
import cdm.product.template.TradableProduct;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A Position describes the accumulated effect of a set of securities or financial transactions.
 * @version ${project.version}
 */
@RosettaDataType(value="CounterpartyPosition", builder=CounterpartyPosition.CounterpartyPositionBuilderImpl.class, version="${project.version}")
public interface CounterpartyPosition extends ContractBase {

	CounterpartyPositionMeta metaData = new CounterpartyPositionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the identifier(s) that uniquely identify a position for an identity issuer. A position can include multiple identifiers, for example an internal position identifer and a UTI (Unique Trade Identifier).
	 */
	List<? extends PositionIdentifier> getPositionIdentifier();
	/**
	 * The date and time when the position was opened.
	 */
	LocalDateTime getOpenDateTime();
	/**
	 * Reference to all the trades that constitute the position.
	 */
	List<? extends ReferenceWithMetaTradeState> getTradeReference();
	/**
	 * The parties involved in the position, including the Clearing Organization.
	 */
	List<? extends Party> getParty();
	/**
	 * Represents the role each specified party takes in the position.
	 */
	List<? extends PartyRole> getPartyRole();
	/**
	 * Encapsulates the core constituents that characterize a position.
	 */
	TradableProduct getPositionBase();

	/*********************** Build Methods  ***********************/
	CounterpartyPosition build();
	
	CounterpartyPosition.CounterpartyPositionBuilder toBuilder();
	
	static CounterpartyPosition.CounterpartyPositionBuilder builder() {
		return new CounterpartyPosition.CounterpartyPositionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CounterpartyPosition> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CounterpartyPosition> getType() {
		return CounterpartyPosition.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("contractDetails"), processor, ReferenceWithMetaContractDetails.class, getContractDetails());
		processRosetta(path.newSubPath("executionDetails"), processor, ReferenceWithMetaExecutionDetails.class, getExecutionDetails());
		processRosetta(path.newSubPath("collateral"), processor, ReferenceWithMetaCollateral.class, getCollateral());
		processRosetta(path.newSubPath("positionIdentifier"), processor, PositionIdentifier.class, getPositionIdentifier());
		processor.processBasic(path.newSubPath("openDateTime"), LocalDateTime.class, getOpenDateTime(), this);
		processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.class, getTradeReference());
		processRosetta(path.newSubPath("party"), processor, Party.class, getParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("positionBase"), processor, TradableProduct.class, getPositionBase());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CounterpartyPositionBuilder extends CounterpartyPosition, ContractBase.ContractBaseBuilder, RosettaModelObjectBuilder {
		PositionIdentifier.PositionIdentifierBuilder getOrCreatePositionIdentifier(int _index);
		List<? extends PositionIdentifier.PositionIdentifierBuilder> getPositionIdentifier();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeReference(int _index);
		List<? extends ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder> getTradeReference();
		Party.PartyBuilder getOrCreateParty(int _index);
		List<? extends Party.PartyBuilder> getParty();
		PartyRole.PartyRoleBuilder getOrCreatePartyRole(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getPartyRole();
		TradableProduct.TradableProductBuilder getOrCreatePositionBase();
		TradableProduct.TradableProductBuilder getPositionBase();
		CounterpartyPosition.CounterpartyPositionBuilder setContractDetails(ReferenceWithMetaContractDetails contractDetails0);
		CounterpartyPosition.CounterpartyPositionBuilder setContractDetailsValue(ContractDetails contractDetails1);
		CounterpartyPosition.CounterpartyPositionBuilder setExecutionDetails(ReferenceWithMetaExecutionDetails executionDetails0);
		CounterpartyPosition.CounterpartyPositionBuilder setExecutionDetailsValue(ExecutionDetails executionDetails1);
		CounterpartyPosition.CounterpartyPositionBuilder setCollateral(ReferenceWithMetaCollateral collateral0);
		CounterpartyPosition.CounterpartyPositionBuilder setCollateralValue(Collateral collateral1);
		CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(PositionIdentifier positionIdentifier0);
		CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(PositionIdentifier positionIdentifier1, int _idx);
		CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(List<? extends PositionIdentifier> positionIdentifier2);
		CounterpartyPosition.CounterpartyPositionBuilder setPositionIdentifier(List<? extends PositionIdentifier> positionIdentifier3);
		CounterpartyPosition.CounterpartyPositionBuilder setOpenDateTime(LocalDateTime openDateTime);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(ReferenceWithMetaTradeState tradeReference0);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(ReferenceWithMetaTradeState tradeReference1, int _idx);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(TradeState tradeReference2);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(TradeState tradeReference3, int _idx);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(List<? extends ReferenceWithMetaTradeState> tradeReference4);
		CounterpartyPosition.CounterpartyPositionBuilder setTradeReference(List<? extends ReferenceWithMetaTradeState> tradeReference5);
		CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(List<? extends TradeState> tradeReference6);
		CounterpartyPosition.CounterpartyPositionBuilder setTradeReferenceValue(List<? extends TradeState> tradeReference7);
		CounterpartyPosition.CounterpartyPositionBuilder addParty(Party party0);
		CounterpartyPosition.CounterpartyPositionBuilder addParty(Party party1, int _idx);
		CounterpartyPosition.CounterpartyPositionBuilder addParty(List<? extends Party> party2);
		CounterpartyPosition.CounterpartyPositionBuilder setParty(List<? extends Party> party3);
		CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(PartyRole partyRole0);
		CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(PartyRole partyRole1, int _idx);
		CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(List<? extends PartyRole> partyRole2);
		CounterpartyPosition.CounterpartyPositionBuilder setPartyRole(List<? extends PartyRole> partyRole3);
		CounterpartyPosition.CounterpartyPositionBuilder setPositionBase(TradableProduct positionBase);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("contractDetails"), processor, ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder.class, getContractDetails());
			processRosetta(path.newSubPath("executionDetails"), processor, ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder.class, getExecutionDetails());
			processRosetta(path.newSubPath("collateral"), processor, ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder.class, getCollateral());
			processRosetta(path.newSubPath("positionIdentifier"), processor, PositionIdentifier.PositionIdentifierBuilder.class, getPositionIdentifier());
			processor.processBasic(path.newSubPath("openDateTime"), LocalDateTime.class, getOpenDateTime(), this);
			processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getTradeReference());
			processRosetta(path.newSubPath("party"), processor, Party.PartyBuilder.class, getParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("positionBase"), processor, TradableProduct.TradableProductBuilder.class, getPositionBase());
		}
		

		CounterpartyPosition.CounterpartyPositionBuilder prune();
	}

	/*********************** Immutable Implementation of CounterpartyPosition  ***********************/
	class CounterpartyPositionImpl extends ContractBase.ContractBaseImpl implements CounterpartyPosition {
		private final List<? extends PositionIdentifier> positionIdentifier;
		private final LocalDateTime openDateTime;
		private final List<? extends ReferenceWithMetaTradeState> tradeReference;
		private final List<? extends Party> party;
		private final List<? extends PartyRole> partyRole;
		private final TradableProduct positionBase;
		
		protected CounterpartyPositionImpl(CounterpartyPosition.CounterpartyPositionBuilder builder) {
			super(builder);
			this.positionIdentifier = ofNullable(builder.getPositionIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.openDateTime = builder.getOpenDateTime();
			this.tradeReference = ofNullable(builder.getTradeReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.party = ofNullable(builder.getParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.partyRole = ofNullable(builder.getPartyRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.positionBase = ofNullable(builder.getPositionBase()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("positionIdentifier")
		public List<? extends PositionIdentifier> getPositionIdentifier() {
			return positionIdentifier;
		}
		
		@Override
		@RosettaAttribute("openDateTime")
		public LocalDateTime getOpenDateTime() {
			return openDateTime;
		}
		
		@Override
		@RosettaAttribute("tradeReference")
		public List<? extends ReferenceWithMetaTradeState> getTradeReference() {
			return tradeReference;
		}
		
		@Override
		@RosettaAttribute("party")
		public List<? extends Party> getParty() {
			return party;
		}
		
		@Override
		@RosettaAttribute("partyRole")
		public List<? extends PartyRole> getPartyRole() {
			return partyRole;
		}
		
		@Override
		@RosettaAttribute("positionBase")
		public TradableProduct getPositionBase() {
			return positionBase;
		}
		
		@Override
		public CounterpartyPosition build() {
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder toBuilder() {
			CounterpartyPosition.CounterpartyPositionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CounterpartyPosition.CounterpartyPositionBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getPositionIdentifier()).ifPresent(builder::setPositionIdentifier);
			ofNullable(getOpenDateTime()).ifPresent(builder::setOpenDateTime);
			ofNullable(getTradeReference()).ifPresent(builder::setTradeReference);
			ofNullable(getParty()).ifPresent(builder::setParty);
			ofNullable(getPartyRole()).ifPresent(builder::setPartyRole);
			ofNullable(getPositionBase()).ifPresent(builder::setPositionBase);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CounterpartyPosition _that = getType().cast(o);
		
			if (!ListEquals.listEquals(positionIdentifier, _that.getPositionIdentifier())) return false;
			if (!Objects.equals(openDateTime, _that.getOpenDateTime())) return false;
			if (!ListEquals.listEquals(tradeReference, _that.getTradeReference())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(positionBase, _that.getPositionBase())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (positionIdentifier != null ? positionIdentifier.hashCode() : 0);
			_result = 31 * _result + (openDateTime != null ? openDateTime.hashCode() : 0);
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (positionBase != null ? positionBase.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CounterpartyPosition {" +
				"positionIdentifier=" + this.positionIdentifier + ", " +
				"openDateTime=" + this.openDateTime + ", " +
				"tradeReference=" + this.tradeReference + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"positionBase=" + this.positionBase +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CounterpartyPosition  ***********************/
	class CounterpartyPositionBuilderImpl extends ContractBase.ContractBaseBuilderImpl  implements CounterpartyPosition.CounterpartyPositionBuilder {
	
		protected List<PositionIdentifier.PositionIdentifierBuilder> positionIdentifier = new ArrayList<>();
		protected LocalDateTime openDateTime;
		protected List<ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder> tradeReference = new ArrayList<>();
		protected List<Party.PartyBuilder> party = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> partyRole = new ArrayList<>();
		protected TradableProduct.TradableProductBuilder positionBase;
	
		public CounterpartyPositionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("positionIdentifier")
		public List<? extends PositionIdentifier.PositionIdentifierBuilder> getPositionIdentifier() {
			return positionIdentifier;
		}
		
		public PositionIdentifier.PositionIdentifierBuilder getOrCreatePositionIdentifier(int _index) {
		
			if (positionIdentifier==null) {
				this.positionIdentifier = new ArrayList<>();
			}
			PositionIdentifier.PositionIdentifierBuilder result;
			return getIndex(positionIdentifier, _index, () -> {
						PositionIdentifier.PositionIdentifierBuilder newPositionIdentifier = PositionIdentifier.builder();
						return newPositionIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("openDateTime")
		public LocalDateTime getOpenDateTime() {
			return openDateTime;
		}
		
		@Override
		@RosettaAttribute("tradeReference")
		public List<? extends ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder> getTradeReference() {
			return tradeReference;
		}
		
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeReference(int _index) {
		
			if (tradeReference==null) {
				this.tradeReference = new ArrayList<>();
			}
			ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder result;
			return getIndex(tradeReference, _index, () -> {
						ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder newTradeReference = ReferenceWithMetaTradeState.builder();
						return newTradeReference;
					});
		}
		
		@Override
		@RosettaAttribute("party")
		public List<? extends Party.PartyBuilder> getParty() {
			return party;
		}
		
		public Party.PartyBuilder getOrCreateParty(int _index) {
		
			if (party==null) {
				this.party = new ArrayList<>();
			}
			Party.PartyBuilder result;
			return getIndex(party, _index, () -> {
						Party.PartyBuilder newParty = Party.builder();
						return newParty;
					});
		}
		
		@Override
		@RosettaAttribute("partyRole")
		public List<? extends PartyRole.PartyRoleBuilder> getPartyRole() {
			return partyRole;
		}
		
		public PartyRole.PartyRoleBuilder getOrCreatePartyRole(int _index) {
		
			if (partyRole==null) {
				this.partyRole = new ArrayList<>();
			}
			PartyRole.PartyRoleBuilder result;
			return getIndex(partyRole, _index, () -> {
						PartyRole.PartyRoleBuilder newPartyRole = PartyRole.builder();
						return newPartyRole;
					});
		}
		
		@Override
		@RosettaAttribute("positionBase")
		public TradableProduct.TradableProductBuilder getPositionBase() {
			return positionBase;
		}
		
		@Override
		public TradableProduct.TradableProductBuilder getOrCreatePositionBase() {
			TradableProduct.TradableProductBuilder result;
			if (positionBase!=null) {
				result = positionBase;
			}
			else {
				result = positionBase = TradableProduct.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("contractDetails")
		public CounterpartyPosition.CounterpartyPositionBuilder setContractDetails(ReferenceWithMetaContractDetails contractDetails) {
			this.contractDetails = contractDetails==null?null:contractDetails.toBuilder();
			return this;
		}
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder setContractDetailsValue(ContractDetails contractDetails) {
			this.getOrCreateContractDetails().setValue(contractDetails);
			return this;
		}
		@Override
		@RosettaAttribute("executionDetails")
		public CounterpartyPosition.CounterpartyPositionBuilder setExecutionDetails(ReferenceWithMetaExecutionDetails executionDetails) {
			this.executionDetails = executionDetails==null?null:executionDetails.toBuilder();
			return this;
		}
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder setExecutionDetailsValue(ExecutionDetails executionDetails) {
			this.getOrCreateExecutionDetails().setValue(executionDetails);
			return this;
		}
		@Override
		@RosettaAttribute("collateral")
		public CounterpartyPosition.CounterpartyPositionBuilder setCollateral(ReferenceWithMetaCollateral collateral) {
			this.collateral = collateral==null?null:collateral.toBuilder();
			return this;
		}
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder setCollateralValue(Collateral collateral) {
			this.getOrCreateCollateral().setValue(collateral);
			return this;
		}
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(PositionIdentifier positionIdentifier) {
			if (positionIdentifier!=null) this.positionIdentifier.add(positionIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(PositionIdentifier positionIdentifier, int _idx) {
			getIndex(this.positionIdentifier, _idx, () -> positionIdentifier.toBuilder());
			return this;
		}
		@Override 
		public CounterpartyPosition.CounterpartyPositionBuilder addPositionIdentifier(List<? extends PositionIdentifier> positionIdentifiers) {
			if (positionIdentifiers != null) {
				for (PositionIdentifier toAdd : positionIdentifiers) {
					this.positionIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("positionIdentifier")
		public CounterpartyPosition.CounterpartyPositionBuilder setPositionIdentifier(List<? extends PositionIdentifier> positionIdentifiers) {
			if (positionIdentifiers == null)  {
				this.positionIdentifier = new ArrayList<>();
			}
			else {
				this.positionIdentifier = positionIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("openDateTime")
		public CounterpartyPosition.CounterpartyPositionBuilder setOpenDateTime(LocalDateTime openDateTime) {
			this.openDateTime = openDateTime==null?null:openDateTime;
			return this;
		}
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(ReferenceWithMetaTradeState tradeReference) {
			if (tradeReference!=null) this.tradeReference.add(tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(ReferenceWithMetaTradeState tradeReference, int _idx) {
			getIndex(this.tradeReference, _idx, () -> tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(TradeState tradeReference) {
			this.getOrCreateTradeReference(-1).setValue(tradeReference.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(TradeState tradeReference, int _idx) {
			this.getOrCreateTradeReference(_idx).setValue(tradeReference.toBuilder());
			return this;
		}
		@Override 
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReference(List<? extends ReferenceWithMetaTradeState> tradeReferences) {
			if (tradeReferences != null) {
				for (ReferenceWithMetaTradeState toAdd : tradeReferences) {
					this.tradeReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("tradeReference")
		public CounterpartyPosition.CounterpartyPositionBuilder setTradeReference(List<? extends ReferenceWithMetaTradeState> tradeReferences) {
			if (tradeReferences == null)  {
				this.tradeReference = new ArrayList<>();
			}
			else {
				this.tradeReference = tradeReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addTradeReferenceValue(List<? extends TradeState> tradeReferences) {
			if (tradeReferences != null) {
				for (TradeState toAdd : tradeReferences) {
					this.addTradeReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder setTradeReferenceValue(List<? extends TradeState> tradeReferences) {
			this.tradeReference.clear();
			if (tradeReferences!=null) {
				tradeReferences.forEach(this::addTradeReferenceValue);
			}
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addParty(Party party) {
			if (party!=null) this.party.add(party.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addParty(Party party, int _idx) {
			getIndex(this.party, _idx, () -> party.toBuilder());
			return this;
		}
		@Override 
		public CounterpartyPosition.CounterpartyPositionBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("party")
		public CounterpartyPosition.CounterpartyPositionBuilder setParty(List<? extends Party> partys) {
			if (partys == null)  {
				this.party = new ArrayList<>();
			}
			else {
				this.party = partys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(PartyRole partyRole) {
			if (partyRole!=null) this.partyRole.add(partyRole.toBuilder());
			return this;
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(PartyRole partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> partyRole.toBuilder());
			return this;
		}
		@Override 
		public CounterpartyPosition.CounterpartyPositionBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRole")
		public CounterpartyPosition.CounterpartyPositionBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles == null)  {
				this.partyRole = new ArrayList<>();
			}
			else {
				this.partyRole = partyRoles.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("positionBase")
		public CounterpartyPosition.CounterpartyPositionBuilder setPositionBase(TradableProduct positionBase) {
			this.positionBase = positionBase==null?null:positionBase.toBuilder();
			return this;
		}
		
		@Override
		public CounterpartyPosition build() {
			return new CounterpartyPosition.CounterpartyPositionImpl(this);
		}
		
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder prune() {
			super.prune();
			positionIdentifier = positionIdentifier.stream().filter(b->b!=null).<PositionIdentifier.PositionIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			tradeReference = tradeReference.stream().filter(b->b!=null).<ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			party = party.stream().filter(b->b!=null).<Party.PartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			partyRole = partyRole.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (positionBase!=null && !positionBase.prune().hasData()) positionBase = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getPositionIdentifier()!=null && getPositionIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getOpenDateTime()!=null) return true;
			if (getTradeReference()!=null && getTradeReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getParty()!=null && getParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPartyRole()!=null && getPartyRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPositionBase()!=null && getPositionBase().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CounterpartyPosition.CounterpartyPositionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CounterpartyPosition.CounterpartyPositionBuilder o = (CounterpartyPosition.CounterpartyPositionBuilder) other;
			
			merger.mergeRosetta(getPositionIdentifier(), o.getPositionIdentifier(), this::getOrCreatePositionIdentifier);
			merger.mergeRosetta(getTradeReference(), o.getTradeReference(), this::getOrCreateTradeReference);
			merger.mergeRosetta(getParty(), o.getParty(), this::getOrCreateParty);
			merger.mergeRosetta(getPartyRole(), o.getPartyRole(), this::getOrCreatePartyRole);
			merger.mergeRosetta(getPositionBase(), o.getPositionBase(), this::setPositionBase);
			
			merger.mergeBasic(getOpenDateTime(), o.getOpenDateTime(), this::setOpenDateTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CounterpartyPosition _that = getType().cast(o);
		
			if (!ListEquals.listEquals(positionIdentifier, _that.getPositionIdentifier())) return false;
			if (!Objects.equals(openDateTime, _that.getOpenDateTime())) return false;
			if (!ListEquals.listEquals(tradeReference, _that.getTradeReference())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(positionBase, _that.getPositionBase())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (positionIdentifier != null ? positionIdentifier.hashCode() : 0);
			_result = 31 * _result + (openDateTime != null ? openDateTime.hashCode() : 0);
			_result = 31 * _result + (tradeReference != null ? tradeReference.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (positionBase != null ? positionBase.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CounterpartyPositionBuilder {" +
				"positionIdentifier=" + this.positionIdentifier + ", " +
				"openDateTime=" + this.openDateTime + ", " +
				"tradeReference=" + this.tradeReference + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"positionBase=" + this.positionBase +
			'}' + " " + super.toString();
		}
	}
}
