package cdm.event.common;

import cdm.base.datetime.TimeZone;
import cdm.base.datetime.metafields.FieldWithMetaTimeZone;
import cdm.base.datetime.metafields.FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.ExecutionInstruction.ExecutionInstructionBuilder;
import cdm.event.common.ExecutionInstruction.ExecutionInstructionBuilderImpl;
import cdm.event.common.ExecutionInstruction.ExecutionInstructionImpl;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.meta.ExecutionInstructionMeta;
import cdm.product.collateral.Collateral;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.AttributeMeta;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import com.rosetta.model.metafields.FieldWithMetaDate.FieldWithMetaDateBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies instructions for execution of a transaction, consisting of a product, price, quantity, parties, trade identifier, execution details, and settlement terms.
 * @version ${project.version}
 */
@RosettaDataType(value="ExecutionInstruction", builder=ExecutionInstruction.ExecutionInstructionBuilderImpl.class, version="${project.version}")
public interface ExecutionInstruction extends RosettaModelObject {

	ExecutionInstructionMeta metaData = new ExecutionInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the financial product to be executed and contract formed.
	 */
	Product getProduct();
	/**
	 * Defines the prices (e.g. spread, equity price, FX rate), quantities (e.g. currency amount, no. shares) and settlement terms (e.g. initial fee, broker fee, up-front cds payment or option premium settlement) associated with the constituents of the transacted product.
	 */
	List<? extends PriceQuantity> getPriceQuantity();
	/**
	 * Maps two defined parties to counterparty enums for the transacted product.
	 */
	List<? extends Counterparty> getCounterparty();
	/**
	 * Maps any ancillary parties, e.g. parties involved in the transaction that are not one of the two principal parties.
	 */
	List<? extends AncillaryParty> getAncillaryParty();
	/**
	 * Defines all parties to that execution, including agents and brokers.
	 */
	List<? extends Party> getParties();
	/**
	 * Defines the role(s) that party(ies) may have in relation to the execution.
	 */
	List<? extends PartyRole> getPartyRoles();
	/**
	 * Specifies the type and venue of execution, e.g. via voice, or electronically.
	 */
	ExecutionDetails getExecutionDetails();
	/**
	 * Denotes the trade/execution date.
	 */
	FieldWithMetaDate getTradeDate();
	/**
	 * Denotes the trade time and timezone as agreed by the parties to the trade.
	 */
	FieldWithMetaTimeZone getTradeTime();
	/**
	 * Denotes one or more identifiers associated with the transaction.
	 */
	List<? extends TradeIdentifier> getTradeIdentifier();
	/**
	 * Detail the collateral requirement anticipated with the transaction.
	 */
	Collateral getCollateral();
	/**
	 * Lot Identifier associated with the transaction.
	 */
	Identifier getLotIdentifier();

	/*********************** Build Methods  ***********************/
	ExecutionInstruction build();
	
	ExecutionInstruction.ExecutionInstructionBuilder toBuilder();
	
	static ExecutionInstruction.ExecutionInstructionBuilder builder() {
		return new ExecutionInstruction.ExecutionInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExecutionInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExecutionInstruction> getType() {
		return ExecutionInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("product"), processor, Product.class, getProduct());
		processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("counterparty"), processor, Counterparty.class, getCounterparty());
		processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.class, getAncillaryParty());
		processRosetta(path.newSubPath("parties"), processor, Party.class, getParties());
		processRosetta(path.newSubPath("partyRoles"), processor, PartyRole.class, getPartyRoles());
		processRosetta(path.newSubPath("executionDetails"), processor, ExecutionDetails.class, getExecutionDetails());
		processRosetta(path.newSubPath("tradeDate"), processor, FieldWithMetaDate.class, getTradeDate(), AttributeMeta.GLOBAL_KEY_FIELD);
		processRosetta(path.newSubPath("tradeTime"), processor, FieldWithMetaTimeZone.class, getTradeTime(), AttributeMeta.GLOBAL_KEY_FIELD);
		processRosetta(path.newSubPath("tradeIdentifier"), processor, TradeIdentifier.class, getTradeIdentifier());
		processRosetta(path.newSubPath("collateral"), processor, Collateral.class, getCollateral());
		processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.class, getLotIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExecutionInstructionBuilder extends ExecutionInstruction, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateProduct();
		Product.ProductBuilder getProduct();
		PriceQuantity.PriceQuantityBuilder getOrCreatePriceQuantity(int _index);
		List<? extends PriceQuantity.PriceQuantityBuilder> getPriceQuantity();
		Counterparty.CounterpartyBuilder getOrCreateCounterparty(int _index);
		List<? extends Counterparty.CounterpartyBuilder> getCounterparty();
		AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index);
		List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty();
		Party.PartyBuilder getOrCreateParties(int _index);
		List<? extends Party.PartyBuilder> getParties();
		PartyRole.PartyRoleBuilder getOrCreatePartyRoles(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getPartyRoles();
		ExecutionDetails.ExecutionDetailsBuilder getOrCreateExecutionDetails();
		ExecutionDetails.ExecutionDetailsBuilder getExecutionDetails();
		FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateTradeDate();
		FieldWithMetaDate.FieldWithMetaDateBuilder getTradeDate();
		FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder getOrCreateTradeTime();
		FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder getTradeTime();
		TradeIdentifier.TradeIdentifierBuilder getOrCreateTradeIdentifier(int _index);
		List<? extends TradeIdentifier.TradeIdentifierBuilder> getTradeIdentifier();
		Collateral.CollateralBuilder getOrCreateCollateral();
		Collateral.CollateralBuilder getCollateral();
		Identifier.IdentifierBuilder getOrCreateLotIdentifier();
		Identifier.IdentifierBuilder getLotIdentifier();
		ExecutionInstruction.ExecutionInstructionBuilder setProduct(Product product);
		ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(PriceQuantity priceQuantity0);
		ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(PriceQuantity priceQuantity1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantity2);
		ExecutionInstruction.ExecutionInstructionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantity3);
		ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(Counterparty counterparty0);
		ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(Counterparty counterparty1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(List<? extends Counterparty> counterparty2);
		ExecutionInstruction.ExecutionInstructionBuilder setCounterparty(List<? extends Counterparty> counterparty3);
		ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty0);
		ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryParty2);
		ExecutionInstruction.ExecutionInstructionBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryParty3);
		ExecutionInstruction.ExecutionInstructionBuilder addParties(Party parties0);
		ExecutionInstruction.ExecutionInstructionBuilder addParties(Party parties1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addParties(List<? extends Party> parties2);
		ExecutionInstruction.ExecutionInstructionBuilder setParties(List<? extends Party> parties3);
		ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(PartyRole partyRoles0);
		ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(PartyRole partyRoles1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(List<? extends PartyRole> partyRoles2);
		ExecutionInstruction.ExecutionInstructionBuilder setPartyRoles(List<? extends PartyRole> partyRoles3);
		ExecutionInstruction.ExecutionInstructionBuilder setExecutionDetails(ExecutionDetails executionDetails);
		ExecutionInstruction.ExecutionInstructionBuilder setTradeDate(FieldWithMetaDate tradeDate0);
		ExecutionInstruction.ExecutionInstructionBuilder setTradeDateValue(Date tradeDate1);
		ExecutionInstruction.ExecutionInstructionBuilder setTradeTime(FieldWithMetaTimeZone tradeTime0);
		ExecutionInstruction.ExecutionInstructionBuilder setTradeTimeValue(TimeZone tradeTime1);
		ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(TradeIdentifier tradeIdentifier0);
		ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(TradeIdentifier tradeIdentifier1, int _idx);
		ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(List<? extends TradeIdentifier> tradeIdentifier2);
		ExecutionInstruction.ExecutionInstructionBuilder setTradeIdentifier(List<? extends TradeIdentifier> tradeIdentifier3);
		ExecutionInstruction.ExecutionInstructionBuilder setCollateral(Collateral collateral);
		ExecutionInstruction.ExecutionInstructionBuilder setLotIdentifier(Identifier lotIdentifier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("product"), processor, Product.ProductBuilder.class, getProduct());
			processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.PriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("counterparty"), processor, Counterparty.CounterpartyBuilder.class, getCounterparty());
			processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.AncillaryPartyBuilder.class, getAncillaryParty());
			processRosetta(path.newSubPath("parties"), processor, Party.PartyBuilder.class, getParties());
			processRosetta(path.newSubPath("partyRoles"), processor, PartyRole.PartyRoleBuilder.class, getPartyRoles());
			processRosetta(path.newSubPath("executionDetails"), processor, ExecutionDetails.ExecutionDetailsBuilder.class, getExecutionDetails());
			processRosetta(path.newSubPath("tradeDate"), processor, FieldWithMetaDate.FieldWithMetaDateBuilder.class, getTradeDate(), AttributeMeta.GLOBAL_KEY_FIELD);
			processRosetta(path.newSubPath("tradeTime"), processor, FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder.class, getTradeTime(), AttributeMeta.GLOBAL_KEY_FIELD);
			processRosetta(path.newSubPath("tradeIdentifier"), processor, TradeIdentifier.TradeIdentifierBuilder.class, getTradeIdentifier());
			processRosetta(path.newSubPath("collateral"), processor, Collateral.CollateralBuilder.class, getCollateral());
			processRosetta(path.newSubPath("lotIdentifier"), processor, Identifier.IdentifierBuilder.class, getLotIdentifier());
		}
		

		ExecutionInstruction.ExecutionInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of ExecutionInstruction  ***********************/
	class ExecutionInstructionImpl implements ExecutionInstruction {
		private final Product product;
		private final List<? extends PriceQuantity> priceQuantity;
		private final List<? extends Counterparty> counterparty;
		private final List<? extends AncillaryParty> ancillaryParty;
		private final List<? extends Party> parties;
		private final List<? extends PartyRole> partyRoles;
		private final ExecutionDetails executionDetails;
		private final FieldWithMetaDate tradeDate;
		private final FieldWithMetaTimeZone tradeTime;
		private final List<? extends TradeIdentifier> tradeIdentifier;
		private final Collateral collateral;
		private final Identifier lotIdentifier;
		
		protected ExecutionInstructionImpl(ExecutionInstruction.ExecutionInstructionBuilder builder) {
			this.product = ofNullable(builder.getProduct()).map(f->f.build()).orElse(null);
			this.priceQuantity = ofNullable(builder.getPriceQuantity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.counterparty = ofNullable(builder.getCounterparty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.ancillaryParty = ofNullable(builder.getAncillaryParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.parties = ofNullable(builder.getParties()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.partyRoles = ofNullable(builder.getPartyRoles()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.executionDetails = ofNullable(builder.getExecutionDetails()).map(f->f.build()).orElse(null);
			this.tradeDate = ofNullable(builder.getTradeDate()).map(f->f.build()).orElse(null);
			this.tradeTime = ofNullable(builder.getTradeTime()).map(f->f.build()).orElse(null);
			this.tradeIdentifier = ofNullable(builder.getTradeIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.collateral = ofNullable(builder.getCollateral()).map(f->f.build()).orElse(null);
			this.lotIdentifier = ofNullable(builder.getLotIdentifier()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("product")
		public Product getProduct() {
			return product;
		}
		
		@Override
		@RosettaAttribute("priceQuantity")
		public List<? extends PriceQuantity> getPriceQuantity() {
			return priceQuantity;
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public List<? extends Counterparty> getCounterparty() {
			return counterparty;
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty> getAncillaryParty() {
			return ancillaryParty;
		}
		
		@Override
		@RosettaAttribute("parties")
		public List<? extends Party> getParties() {
			return parties;
		}
		
		@Override
		@RosettaAttribute("partyRoles")
		public List<? extends PartyRole> getPartyRoles() {
			return partyRoles;
		}
		
		@Override
		@RosettaAttribute("executionDetails")
		public ExecutionDetails getExecutionDetails() {
			return executionDetails;
		}
		
		@Override
		@RosettaAttribute("tradeDate")
		public FieldWithMetaDate getTradeDate() {
			return tradeDate;
		}
		
		@Override
		@RosettaAttribute("tradeTime")
		public FieldWithMetaTimeZone getTradeTime() {
			return tradeTime;
		}
		
		@Override
		@RosettaAttribute("tradeIdentifier")
		public List<? extends TradeIdentifier> getTradeIdentifier() {
			return tradeIdentifier;
		}
		
		@Override
		@RosettaAttribute("collateral")
		public Collateral getCollateral() {
			return collateral;
		}
		
		@Override
		@RosettaAttribute("lotIdentifier")
		public Identifier getLotIdentifier() {
			return lotIdentifier;
		}
		
		@Override
		public ExecutionInstruction build() {
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder toBuilder() {
			ExecutionInstruction.ExecutionInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExecutionInstruction.ExecutionInstructionBuilder builder) {
			ofNullable(getProduct()).ifPresent(builder::setProduct);
			ofNullable(getPriceQuantity()).ifPresent(builder::setPriceQuantity);
			ofNullable(getCounterparty()).ifPresent(builder::setCounterparty);
			ofNullable(getAncillaryParty()).ifPresent(builder::setAncillaryParty);
			ofNullable(getParties()).ifPresent(builder::setParties);
			ofNullable(getPartyRoles()).ifPresent(builder::setPartyRoles);
			ofNullable(getExecutionDetails()).ifPresent(builder::setExecutionDetails);
			ofNullable(getTradeDate()).ifPresent(builder::setTradeDate);
			ofNullable(getTradeTime()).ifPresent(builder::setTradeTime);
			ofNullable(getTradeIdentifier()).ifPresent(builder::setTradeIdentifier);
			ofNullable(getCollateral()).ifPresent(builder::setCollateral);
			ofNullable(getLotIdentifier()).ifPresent(builder::setLotIdentifier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExecutionInstruction _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!ListEquals.listEquals(parties, _that.getParties())) return false;
			if (!ListEquals.listEquals(partyRoles, _that.getPartyRoles())) return false;
			if (!Objects.equals(executionDetails, _that.getExecutionDetails())) return false;
			if (!Objects.equals(tradeDate, _that.getTradeDate())) return false;
			if (!Objects.equals(tradeTime, _that.getTradeTime())) return false;
			if (!ListEquals.listEquals(tradeIdentifier, _that.getTradeIdentifier())) return false;
			if (!Objects.equals(collateral, _that.getCollateral())) return false;
			if (!Objects.equals(lotIdentifier, _that.getLotIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (parties != null ? parties.hashCode() : 0);
			_result = 31 * _result + (partyRoles != null ? partyRoles.hashCode() : 0);
			_result = 31 * _result + (executionDetails != null ? executionDetails.hashCode() : 0);
			_result = 31 * _result + (tradeDate != null ? tradeDate.hashCode() : 0);
			_result = 31 * _result + (tradeTime != null ? tradeTime.hashCode() : 0);
			_result = 31 * _result + (tradeIdentifier != null ? tradeIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateral != null ? collateral.hashCode() : 0);
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExecutionInstruction {" +
				"product=" + this.product + ", " +
				"priceQuantity=" + this.priceQuantity + ", " +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"parties=" + this.parties + ", " +
				"partyRoles=" + this.partyRoles + ", " +
				"executionDetails=" + this.executionDetails + ", " +
				"tradeDate=" + this.tradeDate + ", " +
				"tradeTime=" + this.tradeTime + ", " +
				"tradeIdentifier=" + this.tradeIdentifier + ", " +
				"collateral=" + this.collateral + ", " +
				"lotIdentifier=" + this.lotIdentifier +
			'}';
		}
	}

	/*********************** Builder Implementation of ExecutionInstruction  ***********************/
	class ExecutionInstructionBuilderImpl implements ExecutionInstruction.ExecutionInstructionBuilder {
	
		protected Product.ProductBuilder product;
		protected List<PriceQuantity.PriceQuantityBuilder> priceQuantity = new ArrayList<>();
		protected List<Counterparty.CounterpartyBuilder> counterparty = new ArrayList<>();
		protected List<AncillaryParty.AncillaryPartyBuilder> ancillaryParty = new ArrayList<>();
		protected List<Party.PartyBuilder> parties = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> partyRoles = new ArrayList<>();
		protected ExecutionDetails.ExecutionDetailsBuilder executionDetails;
		protected FieldWithMetaDate.FieldWithMetaDateBuilder tradeDate;
		protected FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder tradeTime;
		protected List<TradeIdentifier.TradeIdentifierBuilder> tradeIdentifier = new ArrayList<>();
		protected Collateral.CollateralBuilder collateral;
		protected Identifier.IdentifierBuilder lotIdentifier;
	
		public ExecutionInstructionBuilderImpl() {
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
		@RosettaAttribute("counterparty")
		public List<? extends Counterparty.CounterpartyBuilder> getCounterparty() {
			return counterparty;
		}
		
		public Counterparty.CounterpartyBuilder getOrCreateCounterparty(int _index) {
		
			if (counterparty==null) {
				this.counterparty = new ArrayList<>();
			}
			Counterparty.CounterpartyBuilder result;
			return getIndex(counterparty, _index, () -> {
						Counterparty.CounterpartyBuilder newCounterparty = Counterparty.builder();
						return newCounterparty;
					});
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty() {
			return ancillaryParty;
		}
		
		public AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index) {
		
			if (ancillaryParty==null) {
				this.ancillaryParty = new ArrayList<>();
			}
			AncillaryParty.AncillaryPartyBuilder result;
			return getIndex(ancillaryParty, _index, () -> {
						AncillaryParty.AncillaryPartyBuilder newAncillaryParty = AncillaryParty.builder();
						return newAncillaryParty;
					});
		}
		
		@Override
		@RosettaAttribute("parties")
		public List<? extends Party.PartyBuilder> getParties() {
			return parties;
		}
		
		public Party.PartyBuilder getOrCreateParties(int _index) {
		
			if (parties==null) {
				this.parties = new ArrayList<>();
			}
			Party.PartyBuilder result;
			return getIndex(parties, _index, () -> {
						Party.PartyBuilder newParties = Party.builder();
						return newParties;
					});
		}
		
		@Override
		@RosettaAttribute("partyRoles")
		public List<? extends PartyRole.PartyRoleBuilder> getPartyRoles() {
			return partyRoles;
		}
		
		public PartyRole.PartyRoleBuilder getOrCreatePartyRoles(int _index) {
		
			if (partyRoles==null) {
				this.partyRoles = new ArrayList<>();
			}
			PartyRole.PartyRoleBuilder result;
			return getIndex(partyRoles, _index, () -> {
						PartyRole.PartyRoleBuilder newPartyRoles = PartyRole.builder();
						return newPartyRoles;
					});
		}
		
		@Override
		@RosettaAttribute("executionDetails")
		public ExecutionDetails.ExecutionDetailsBuilder getExecutionDetails() {
			return executionDetails;
		}
		
		@Override
		public ExecutionDetails.ExecutionDetailsBuilder getOrCreateExecutionDetails() {
			ExecutionDetails.ExecutionDetailsBuilder result;
			if (executionDetails!=null) {
				result = executionDetails;
			}
			else {
				result = executionDetails = ExecutionDetails.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeDate")
		public FieldWithMetaDate.FieldWithMetaDateBuilder getTradeDate() {
			return tradeDate;
		}
		
		@Override
		public FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateTradeDate() {
			FieldWithMetaDate.FieldWithMetaDateBuilder result;
			if (tradeDate!=null) {
				result = tradeDate;
			}
			else {
				result = tradeDate = FieldWithMetaDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeTime")
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder getTradeTime() {
			return tradeTime;
		}
		
		@Override
		public FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder getOrCreateTradeTime() {
			FieldWithMetaTimeZone.FieldWithMetaTimeZoneBuilder result;
			if (tradeTime!=null) {
				result = tradeTime;
			}
			else {
				result = tradeTime = FieldWithMetaTimeZone.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradeIdentifier")
		public List<? extends TradeIdentifier.TradeIdentifierBuilder> getTradeIdentifier() {
			return tradeIdentifier;
		}
		
		public TradeIdentifier.TradeIdentifierBuilder getOrCreateTradeIdentifier(int _index) {
		
			if (tradeIdentifier==null) {
				this.tradeIdentifier = new ArrayList<>();
			}
			TradeIdentifier.TradeIdentifierBuilder result;
			return getIndex(tradeIdentifier, _index, () -> {
						TradeIdentifier.TradeIdentifierBuilder newTradeIdentifier = TradeIdentifier.builder();
						return newTradeIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("collateral")
		public Collateral.CollateralBuilder getCollateral() {
			return collateral;
		}
		
		@Override
		public Collateral.CollateralBuilder getOrCreateCollateral() {
			Collateral.CollateralBuilder result;
			if (collateral!=null) {
				result = collateral;
			}
			else {
				result = collateral = Collateral.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("lotIdentifier")
		public Identifier.IdentifierBuilder getLotIdentifier() {
			return lotIdentifier;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateLotIdentifier() {
			Identifier.IdentifierBuilder result;
			if (lotIdentifier!=null) {
				result = lotIdentifier;
			}
			else {
				result = lotIdentifier = Identifier.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("product")
		public ExecutionInstruction.ExecutionInstructionBuilder setProduct(Product product) {
			this.product = product==null?null:product.toBuilder();
			return this;
		}
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(PriceQuantity priceQuantity) {
			if (priceQuantity!=null) this.priceQuantity.add(priceQuantity.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(PriceQuantity priceQuantity, int _idx) {
			getIndex(this.priceQuantity, _idx, () -> priceQuantity.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys != null) {
				for (PriceQuantity toAdd : priceQuantitys) {
					this.priceQuantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("priceQuantity")
		public ExecutionInstruction.ExecutionInstructionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
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
		public ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(Counterparty counterparty) {
			if (counterparty!=null) this.counterparty.add(counterparty.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(Counterparty counterparty, int _idx) {
			getIndex(this.counterparty, _idx, () -> counterparty.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addCounterparty(List<? extends Counterparty> counterpartys) {
			if (counterpartys != null) {
				for (Counterparty toAdd : counterpartys) {
					this.counterparty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("counterparty")
		public ExecutionInstruction.ExecutionInstructionBuilder setCounterparty(List<? extends Counterparty> counterpartys) {
			if (counterpartys == null)  {
				this.counterparty = new ArrayList<>();
			}
			else {
				this.counterparty = counterpartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty) {
			if (ancillaryParty!=null) this.ancillaryParty.add(ancillaryParty.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty, int _idx) {
			getIndex(this.ancillaryParty, _idx, () -> ancillaryParty.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys != null) {
				for (AncillaryParty toAdd : ancillaryPartys) {
					this.ancillaryParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("ancillaryParty")
		public ExecutionInstruction.ExecutionInstructionBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys == null)  {
				this.ancillaryParty = new ArrayList<>();
			}
			else {
				this.ancillaryParty = ancillaryPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addParties(Party parties) {
			if (parties!=null) this.parties.add(parties.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addParties(Party parties, int _idx) {
			getIndex(this.parties, _idx, () -> parties.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addParties(List<? extends Party> partiess) {
			if (partiess != null) {
				for (Party toAdd : partiess) {
					this.parties.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("parties")
		public ExecutionInstruction.ExecutionInstructionBuilder setParties(List<? extends Party> partiess) {
			if (partiess == null)  {
				this.parties = new ArrayList<>();
			}
			else {
				this.parties = partiess.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(PartyRole partyRoles) {
			if (partyRoles!=null) this.partyRoles.add(partyRoles.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(PartyRole partyRoles, int _idx) {
			getIndex(this.partyRoles, _idx, () -> partyRoles.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addPartyRoles(List<? extends PartyRole> partyRoless) {
			if (partyRoless != null) {
				for (PartyRole toAdd : partyRoless) {
					this.partyRoles.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRoles")
		public ExecutionInstruction.ExecutionInstructionBuilder setPartyRoles(List<? extends PartyRole> partyRoless) {
			if (partyRoless == null)  {
				this.partyRoles = new ArrayList<>();
			}
			else {
				this.partyRoles = partyRoless.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("executionDetails")
		public ExecutionInstruction.ExecutionInstructionBuilder setExecutionDetails(ExecutionDetails executionDetails) {
			this.executionDetails = executionDetails==null?null:executionDetails.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tradeDate")
		public ExecutionInstruction.ExecutionInstructionBuilder setTradeDate(FieldWithMetaDate tradeDate) {
			this.tradeDate = tradeDate==null?null:tradeDate.toBuilder();
			return this;
		}
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder setTradeDateValue(Date tradeDate) {
			this.getOrCreateTradeDate().setValue(tradeDate);
			return this;
		}
		@Override
		@RosettaAttribute("tradeTime")
		public ExecutionInstruction.ExecutionInstructionBuilder setTradeTime(FieldWithMetaTimeZone tradeTime) {
			this.tradeTime = tradeTime==null?null:tradeTime.toBuilder();
			return this;
		}
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder setTradeTimeValue(TimeZone tradeTime) {
			this.getOrCreateTradeTime().setValue(tradeTime);
			return this;
		}
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(TradeIdentifier tradeIdentifier) {
			if (tradeIdentifier!=null) this.tradeIdentifier.add(tradeIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(TradeIdentifier tradeIdentifier, int _idx) {
			getIndex(this.tradeIdentifier, _idx, () -> tradeIdentifier.toBuilder());
			return this;
		}
		@Override 
		public ExecutionInstruction.ExecutionInstructionBuilder addTradeIdentifier(List<? extends TradeIdentifier> tradeIdentifiers) {
			if (tradeIdentifiers != null) {
				for (TradeIdentifier toAdd : tradeIdentifiers) {
					this.tradeIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("tradeIdentifier")
		public ExecutionInstruction.ExecutionInstructionBuilder setTradeIdentifier(List<? extends TradeIdentifier> tradeIdentifiers) {
			if (tradeIdentifiers == null)  {
				this.tradeIdentifier = new ArrayList<>();
			}
			else {
				this.tradeIdentifier = tradeIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("collateral")
		public ExecutionInstruction.ExecutionInstructionBuilder setCollateral(Collateral collateral) {
			this.collateral = collateral==null?null:collateral.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("lotIdentifier")
		public ExecutionInstruction.ExecutionInstructionBuilder setLotIdentifier(Identifier lotIdentifier) {
			this.lotIdentifier = lotIdentifier==null?null:lotIdentifier.toBuilder();
			return this;
		}
		
		@Override
		public ExecutionInstruction build() {
			return new ExecutionInstruction.ExecutionInstructionImpl(this);
		}
		
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder prune() {
			if (product!=null && !product.prune().hasData()) product = null;
			priceQuantity = priceQuantity.stream().filter(b->b!=null).<PriceQuantity.PriceQuantityBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			counterparty = counterparty.stream().filter(b->b!=null).<Counterparty.CounterpartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			ancillaryParty = ancillaryParty.stream().filter(b->b!=null).<AncillaryParty.AncillaryPartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			parties = parties.stream().filter(b->b!=null).<Party.PartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			partyRoles = partyRoles.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (executionDetails!=null && !executionDetails.prune().hasData()) executionDetails = null;
			if (tradeDate!=null && !tradeDate.prune().hasData()) tradeDate = null;
			if (tradeTime!=null && !tradeTime.prune().hasData()) tradeTime = null;
			tradeIdentifier = tradeIdentifier.stream().filter(b->b!=null).<TradeIdentifier.TradeIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (collateral!=null && !collateral.prune().hasData()) collateral = null;
			if (lotIdentifier!=null && !lotIdentifier.prune().hasData()) lotIdentifier = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getProduct()!=null && getProduct().hasData()) return true;
			if (getPriceQuantity()!=null && getPriceQuantity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCounterparty()!=null && getCounterparty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAncillaryParty()!=null && getAncillaryParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getParties()!=null && getParties().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPartyRoles()!=null && getPartyRoles().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getExecutionDetails()!=null && getExecutionDetails().hasData()) return true;
			if (getTradeDate()!=null) return true;
			if (getTradeTime()!=null && getTradeTime().hasData()) return true;
			if (getTradeIdentifier()!=null && getTradeIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCollateral()!=null && getCollateral().hasData()) return true;
			if (getLotIdentifier()!=null && getLotIdentifier().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExecutionInstruction.ExecutionInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExecutionInstruction.ExecutionInstructionBuilder o = (ExecutionInstruction.ExecutionInstructionBuilder) other;
			
			merger.mergeRosetta(getProduct(), o.getProduct(), this::setProduct);
			merger.mergeRosetta(getPriceQuantity(), o.getPriceQuantity(), this::getOrCreatePriceQuantity);
			merger.mergeRosetta(getCounterparty(), o.getCounterparty(), this::getOrCreateCounterparty);
			merger.mergeRosetta(getAncillaryParty(), o.getAncillaryParty(), this::getOrCreateAncillaryParty);
			merger.mergeRosetta(getParties(), o.getParties(), this::getOrCreateParties);
			merger.mergeRosetta(getPartyRoles(), o.getPartyRoles(), this::getOrCreatePartyRoles);
			merger.mergeRosetta(getExecutionDetails(), o.getExecutionDetails(), this::setExecutionDetails);
			merger.mergeRosetta(getTradeDate(), o.getTradeDate(), this::setTradeDate);
			merger.mergeRosetta(getTradeTime(), o.getTradeTime(), this::setTradeTime);
			merger.mergeRosetta(getTradeIdentifier(), o.getTradeIdentifier(), this::getOrCreateTradeIdentifier);
			merger.mergeRosetta(getCollateral(), o.getCollateral(), this::setCollateral);
			merger.mergeRosetta(getLotIdentifier(), o.getLotIdentifier(), this::setLotIdentifier);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExecutionInstruction _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!ListEquals.listEquals(parties, _that.getParties())) return false;
			if (!ListEquals.listEquals(partyRoles, _that.getPartyRoles())) return false;
			if (!Objects.equals(executionDetails, _that.getExecutionDetails())) return false;
			if (!Objects.equals(tradeDate, _that.getTradeDate())) return false;
			if (!Objects.equals(tradeTime, _that.getTradeTime())) return false;
			if (!ListEquals.listEquals(tradeIdentifier, _that.getTradeIdentifier())) return false;
			if (!Objects.equals(collateral, _that.getCollateral())) return false;
			if (!Objects.equals(lotIdentifier, _that.getLotIdentifier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (parties != null ? parties.hashCode() : 0);
			_result = 31 * _result + (partyRoles != null ? partyRoles.hashCode() : 0);
			_result = 31 * _result + (executionDetails != null ? executionDetails.hashCode() : 0);
			_result = 31 * _result + (tradeDate != null ? tradeDate.hashCode() : 0);
			_result = 31 * _result + (tradeTime != null ? tradeTime.hashCode() : 0);
			_result = 31 * _result + (tradeIdentifier != null ? tradeIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateral != null ? collateral.hashCode() : 0);
			_result = 31 * _result + (lotIdentifier != null ? lotIdentifier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExecutionInstructionBuilder {" +
				"product=" + this.product + ", " +
				"priceQuantity=" + this.priceQuantity + ", " +
				"counterparty=" + this.counterparty + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"parties=" + this.parties + ", " +
				"partyRoles=" + this.partyRoles + ", " +
				"executionDetails=" + this.executionDetails + ", " +
				"tradeDate=" + this.tradeDate + ", " +
				"tradeTime=" + this.tradeTime + ", " +
				"tradeIdentifier=" + this.tradeIdentifier + ", " +
				"collateral=" + this.collateral + ", " +
				"lotIdentifier=" + this.lotIdentifier +
			'}';
		}
	}
}
