package cdm.event.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.MarginCallBase;
import cdm.event.common.MarginCallBase.MarginCallBaseBuilder;
import cdm.event.common.MarginCallBase.MarginCallBaseBuilderImpl;
import cdm.event.common.MarginCallBase.MarginCallBaseImpl;
import cdm.event.common.MarginCallExposure;
import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
import cdm.event.common.meta.MarginCallBaseMeta;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder;
import cdm.legaldocumentation.common.AgreementName;
import cdm.observable.asset.Money;
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
 * Represents common attributes required for Issuance and Response to a Margin Call action as a result of a demand for delivery or return of collateral determined under a legal agreement such as a credit support document or equivalent.
 * @version ${project.version}
 */
@RosettaDataType(value="MarginCallBase", builder=MarginCallBase.MarginCallBaseBuilderImpl.class, version="${project.version}")
public interface MarginCallBase extends RosettaModelObject {

	MarginCallBaseMeta metaData = new MarginCallBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the enumeration values to specify the call notification type, direction, specific action type.
	 */
	MarginCallInstructionType getInstructionType();
	/**
	 * Represents the parties to the margin call. The cardinality is optional to address the case where both parties of the event are specified and a third party if applicable.
	 */
	List<? extends Party> getParty();
	/**
	 * Represents the role each specified party takes in the margin call. further to the principal roles, payer and receiver.
	 */
	List<? extends PartyRole> getPartyRole();
	/**
	 * Indicates the name of the Clearing Broker FCM/DCM.
	 */
	Party getClearingBroker();
	/**
	 * Represents a unique Identifier for a margin call message, that can be referenced throughout all points of the process.
	 */
	Identifier getCallIdentifier();
	/**
	 * Specifies the legal agreement type the margin call is generated from and governed by.
	 */
	AgreementName getCallAgreementType();
	/**
	 * Specifies the collateral legal agreement minimum transfer amount in base currency.
	 */
	Money getAgreementMinimumTransferAmount();
	/**
	 * Specifies the collateral legal agreement threshold amount in base currency.
	 */
	Money getAgreementThreshold();
	/**
	 * Specifies the collateral legal agreement rounding in base currency.
	 */
	Money getAgreementRounding();
	/**
	 * Identifies margin type and if related regulatory mandate
	 */
	RegMarginTypeEnum getRegMarginType();
	/**
	 * Indicates the role of the party in an regulatory initial margin call instruction (i.e Pledgor party or Secured party).
	 */
	RegIMRoleEnum getRegIMRole();
	/**
	 * Represents the current mark to market value or IM calculation value of the trade portfolio as recorded by the principle (in base currency), to be referenced in a margin call.
	 */
	MarginCallExposure getBaseCurrencyExposure();
	/**
	 * Represents attributes to define the details of collateral assets within a collateral portfolio to be used in margin call messaging and contribute to collateral balances e.g securities in a collateral account recorded by the principal as held or posted.
	 */
	ReferenceWithMetaCollateralPortfolio getCollateralPortfolio();
	/**
	 * Represents additional credit support amount over and above mark to market value.
	 */
	CollateralBalance getIndependentAmountBalance();

	/*********************** Build Methods  ***********************/
	MarginCallBase build();
	
	MarginCallBase.MarginCallBaseBuilder toBuilder();
	
	static MarginCallBase.MarginCallBaseBuilder builder() {
		return new MarginCallBase.MarginCallBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MarginCallBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MarginCallBase> getType() {
		return MarginCallBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("instructionType"), processor, MarginCallInstructionType.class, getInstructionType());
		processRosetta(path.newSubPath("party"), processor, Party.class, getParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("clearingBroker"), processor, Party.class, getClearingBroker());
		processRosetta(path.newSubPath("callIdentifier"), processor, Identifier.class, getCallIdentifier());
		processRosetta(path.newSubPath("callAgreementType"), processor, AgreementName.class, getCallAgreementType());
		processRosetta(path.newSubPath("agreementMinimumTransferAmount"), processor, Money.class, getAgreementMinimumTransferAmount());
		processRosetta(path.newSubPath("agreementThreshold"), processor, Money.class, getAgreementThreshold());
		processRosetta(path.newSubPath("agreementRounding"), processor, Money.class, getAgreementRounding());
		processor.processBasic(path.newSubPath("regMarginType"), RegMarginTypeEnum.class, getRegMarginType(), this);
		processor.processBasic(path.newSubPath("regIMRole"), RegIMRoleEnum.class, getRegIMRole(), this);
		processRosetta(path.newSubPath("baseCurrencyExposure"), processor, MarginCallExposure.class, getBaseCurrencyExposure());
		processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.class, getCollateralPortfolio());
		processRosetta(path.newSubPath("independentAmountBalance"), processor, CollateralBalance.class, getIndependentAmountBalance());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MarginCallBaseBuilder extends MarginCallBase, RosettaModelObjectBuilder {
		MarginCallInstructionType.MarginCallInstructionTypeBuilder getOrCreateInstructionType();
		MarginCallInstructionType.MarginCallInstructionTypeBuilder getInstructionType();
		Party.PartyBuilder getOrCreateParty(int _index);
		List<? extends Party.PartyBuilder> getParty();
		PartyRole.PartyRoleBuilder getOrCreatePartyRole(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getPartyRole();
		Party.PartyBuilder getOrCreateClearingBroker();
		Party.PartyBuilder getClearingBroker();
		Identifier.IdentifierBuilder getOrCreateCallIdentifier();
		Identifier.IdentifierBuilder getCallIdentifier();
		AgreementName.AgreementNameBuilder getOrCreateCallAgreementType();
		AgreementName.AgreementNameBuilder getCallAgreementType();
		Money.MoneyBuilder getOrCreateAgreementMinimumTransferAmount();
		Money.MoneyBuilder getAgreementMinimumTransferAmount();
		Money.MoneyBuilder getOrCreateAgreementThreshold();
		Money.MoneyBuilder getAgreementThreshold();
		Money.MoneyBuilder getOrCreateAgreementRounding();
		Money.MoneyBuilder getAgreementRounding();
		MarginCallExposure.MarginCallExposureBuilder getOrCreateBaseCurrencyExposure();
		MarginCallExposure.MarginCallExposureBuilder getBaseCurrencyExposure();
		ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getOrCreateCollateralPortfolio();
		ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getCollateralPortfolio();
		CollateralBalance.CollateralBalanceBuilder getOrCreateIndependentAmountBalance();
		CollateralBalance.CollateralBalanceBuilder getIndependentAmountBalance();
		MarginCallBase.MarginCallBaseBuilder setInstructionType(MarginCallInstructionType instructionType);
		MarginCallBase.MarginCallBaseBuilder addParty(Party party0);
		MarginCallBase.MarginCallBaseBuilder addParty(Party party1, int _idx);
		MarginCallBase.MarginCallBaseBuilder addParty(List<? extends Party> party2);
		MarginCallBase.MarginCallBaseBuilder setParty(List<? extends Party> party3);
		MarginCallBase.MarginCallBaseBuilder addPartyRole(PartyRole partyRole0);
		MarginCallBase.MarginCallBaseBuilder addPartyRole(PartyRole partyRole1, int _idx);
		MarginCallBase.MarginCallBaseBuilder addPartyRole(List<? extends PartyRole> partyRole2);
		MarginCallBase.MarginCallBaseBuilder setPartyRole(List<? extends PartyRole> partyRole3);
		MarginCallBase.MarginCallBaseBuilder setClearingBroker(Party clearingBroker);
		MarginCallBase.MarginCallBaseBuilder setCallIdentifier(Identifier callIdentifier);
		MarginCallBase.MarginCallBaseBuilder setCallAgreementType(AgreementName callAgreementType);
		MarginCallBase.MarginCallBaseBuilder setAgreementMinimumTransferAmount(Money agreementMinimumTransferAmount);
		MarginCallBase.MarginCallBaseBuilder setAgreementThreshold(Money agreementThreshold);
		MarginCallBase.MarginCallBaseBuilder setAgreementRounding(Money agreementRounding);
		MarginCallBase.MarginCallBaseBuilder setRegMarginType(RegMarginTypeEnum regMarginType);
		MarginCallBase.MarginCallBaseBuilder setRegIMRole(RegIMRoleEnum regIMRole);
		MarginCallBase.MarginCallBaseBuilder setBaseCurrencyExposure(MarginCallExposure baseCurrencyExposure);
		MarginCallBase.MarginCallBaseBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio0);
		MarginCallBase.MarginCallBaseBuilder setCollateralPortfolioValue(CollateralPortfolio collateralPortfolio1);
		MarginCallBase.MarginCallBaseBuilder setIndependentAmountBalance(CollateralBalance independentAmountBalance);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("instructionType"), processor, MarginCallInstructionType.MarginCallInstructionTypeBuilder.class, getInstructionType());
			processRosetta(path.newSubPath("party"), processor, Party.PartyBuilder.class, getParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("clearingBroker"), processor, Party.PartyBuilder.class, getClearingBroker());
			processRosetta(path.newSubPath("callIdentifier"), processor, Identifier.IdentifierBuilder.class, getCallIdentifier());
			processRosetta(path.newSubPath("callAgreementType"), processor, AgreementName.AgreementNameBuilder.class, getCallAgreementType());
			processRosetta(path.newSubPath("agreementMinimumTransferAmount"), processor, Money.MoneyBuilder.class, getAgreementMinimumTransferAmount());
			processRosetta(path.newSubPath("agreementThreshold"), processor, Money.MoneyBuilder.class, getAgreementThreshold());
			processRosetta(path.newSubPath("agreementRounding"), processor, Money.MoneyBuilder.class, getAgreementRounding());
			processor.processBasic(path.newSubPath("regMarginType"), RegMarginTypeEnum.class, getRegMarginType(), this);
			processor.processBasic(path.newSubPath("regIMRole"), RegIMRoleEnum.class, getRegIMRole(), this);
			processRosetta(path.newSubPath("baseCurrencyExposure"), processor, MarginCallExposure.MarginCallExposureBuilder.class, getBaseCurrencyExposure());
			processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder.class, getCollateralPortfolio());
			processRosetta(path.newSubPath("independentAmountBalance"), processor, CollateralBalance.CollateralBalanceBuilder.class, getIndependentAmountBalance());
		}
		

		MarginCallBase.MarginCallBaseBuilder prune();
	}

	/*********************** Immutable Implementation of MarginCallBase  ***********************/
	class MarginCallBaseImpl implements MarginCallBase {
		private final MarginCallInstructionType instructionType;
		private final List<? extends Party> party;
		private final List<? extends PartyRole> partyRole;
		private final Party clearingBroker;
		private final Identifier callIdentifier;
		private final AgreementName callAgreementType;
		private final Money agreementMinimumTransferAmount;
		private final Money agreementThreshold;
		private final Money agreementRounding;
		private final RegMarginTypeEnum regMarginType;
		private final RegIMRoleEnum regIMRole;
		private final MarginCallExposure baseCurrencyExposure;
		private final ReferenceWithMetaCollateralPortfolio collateralPortfolio;
		private final CollateralBalance independentAmountBalance;
		
		protected MarginCallBaseImpl(MarginCallBase.MarginCallBaseBuilder builder) {
			this.instructionType = ofNullable(builder.getInstructionType()).map(f->f.build()).orElse(null);
			this.party = ofNullable(builder.getParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.partyRole = ofNullable(builder.getPartyRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.clearingBroker = ofNullable(builder.getClearingBroker()).map(f->f.build()).orElse(null);
			this.callIdentifier = ofNullable(builder.getCallIdentifier()).map(f->f.build()).orElse(null);
			this.callAgreementType = ofNullable(builder.getCallAgreementType()).map(f->f.build()).orElse(null);
			this.agreementMinimumTransferAmount = ofNullable(builder.getAgreementMinimumTransferAmount()).map(f->f.build()).orElse(null);
			this.agreementThreshold = ofNullable(builder.getAgreementThreshold()).map(f->f.build()).orElse(null);
			this.agreementRounding = ofNullable(builder.getAgreementRounding()).map(f->f.build()).orElse(null);
			this.regMarginType = builder.getRegMarginType();
			this.regIMRole = builder.getRegIMRole();
			this.baseCurrencyExposure = ofNullable(builder.getBaseCurrencyExposure()).map(f->f.build()).orElse(null);
			this.collateralPortfolio = ofNullable(builder.getCollateralPortfolio()).map(f->f.build()).orElse(null);
			this.independentAmountBalance = ofNullable(builder.getIndependentAmountBalance()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("instructionType")
		public MarginCallInstructionType getInstructionType() {
			return instructionType;
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
		@RosettaAttribute("clearingBroker")
		public Party getClearingBroker() {
			return clearingBroker;
		}
		
		@Override
		@RosettaAttribute("callIdentifier")
		public Identifier getCallIdentifier() {
			return callIdentifier;
		}
		
		@Override
		@RosettaAttribute("callAgreementType")
		public AgreementName getCallAgreementType() {
			return callAgreementType;
		}
		
		@Override
		@RosettaAttribute("agreementMinimumTransferAmount")
		public Money getAgreementMinimumTransferAmount() {
			return agreementMinimumTransferAmount;
		}
		
		@Override
		@RosettaAttribute("agreementThreshold")
		public Money getAgreementThreshold() {
			return agreementThreshold;
		}
		
		@Override
		@RosettaAttribute("agreementRounding")
		public Money getAgreementRounding() {
			return agreementRounding;
		}
		
		@Override
		@RosettaAttribute("regMarginType")
		public RegMarginTypeEnum getRegMarginType() {
			return regMarginType;
		}
		
		@Override
		@RosettaAttribute("regIMRole")
		public RegIMRoleEnum getRegIMRole() {
			return regIMRole;
		}
		
		@Override
		@RosettaAttribute("baseCurrencyExposure")
		public MarginCallExposure getBaseCurrencyExposure() {
			return baseCurrencyExposure;
		}
		
		@Override
		@RosettaAttribute("collateralPortfolio")
		public ReferenceWithMetaCollateralPortfolio getCollateralPortfolio() {
			return collateralPortfolio;
		}
		
		@Override
		@RosettaAttribute("independentAmountBalance")
		public CollateralBalance getIndependentAmountBalance() {
			return independentAmountBalance;
		}
		
		@Override
		public MarginCallBase build() {
			return this;
		}
		
		@Override
		public MarginCallBase.MarginCallBaseBuilder toBuilder() {
			MarginCallBase.MarginCallBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MarginCallBase.MarginCallBaseBuilder builder) {
			ofNullable(getInstructionType()).ifPresent(builder::setInstructionType);
			ofNullable(getParty()).ifPresent(builder::setParty);
			ofNullable(getPartyRole()).ifPresent(builder::setPartyRole);
			ofNullable(getClearingBroker()).ifPresent(builder::setClearingBroker);
			ofNullable(getCallIdentifier()).ifPresent(builder::setCallIdentifier);
			ofNullable(getCallAgreementType()).ifPresent(builder::setCallAgreementType);
			ofNullable(getAgreementMinimumTransferAmount()).ifPresent(builder::setAgreementMinimumTransferAmount);
			ofNullable(getAgreementThreshold()).ifPresent(builder::setAgreementThreshold);
			ofNullable(getAgreementRounding()).ifPresent(builder::setAgreementRounding);
			ofNullable(getRegMarginType()).ifPresent(builder::setRegMarginType);
			ofNullable(getRegIMRole()).ifPresent(builder::setRegIMRole);
			ofNullable(getBaseCurrencyExposure()).ifPresent(builder::setBaseCurrencyExposure);
			ofNullable(getCollateralPortfolio()).ifPresent(builder::setCollateralPortfolio);
			ofNullable(getIndependentAmountBalance()).ifPresent(builder::setIndependentAmountBalance);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallBase _that = getType().cast(o);
		
			if (!Objects.equals(instructionType, _that.getInstructionType())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(clearingBroker, _that.getClearingBroker())) return false;
			if (!Objects.equals(callIdentifier, _that.getCallIdentifier())) return false;
			if (!Objects.equals(callAgreementType, _that.getCallAgreementType())) return false;
			if (!Objects.equals(agreementMinimumTransferAmount, _that.getAgreementMinimumTransferAmount())) return false;
			if (!Objects.equals(agreementThreshold, _that.getAgreementThreshold())) return false;
			if (!Objects.equals(agreementRounding, _that.getAgreementRounding())) return false;
			if (!Objects.equals(regMarginType, _that.getRegMarginType())) return false;
			if (!Objects.equals(regIMRole, _that.getRegIMRole())) return false;
			if (!Objects.equals(baseCurrencyExposure, _that.getBaseCurrencyExposure())) return false;
			if (!Objects.equals(collateralPortfolio, _that.getCollateralPortfolio())) return false;
			if (!Objects.equals(independentAmountBalance, _that.getIndependentAmountBalance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (instructionType != null ? instructionType.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (clearingBroker != null ? clearingBroker.hashCode() : 0);
			_result = 31 * _result + (callIdentifier != null ? callIdentifier.hashCode() : 0);
			_result = 31 * _result + (callAgreementType != null ? callAgreementType.hashCode() : 0);
			_result = 31 * _result + (agreementMinimumTransferAmount != null ? agreementMinimumTransferAmount.hashCode() : 0);
			_result = 31 * _result + (agreementThreshold != null ? agreementThreshold.hashCode() : 0);
			_result = 31 * _result + (agreementRounding != null ? agreementRounding.hashCode() : 0);
			_result = 31 * _result + (regMarginType != null ? regMarginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (regIMRole != null ? regIMRole.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (baseCurrencyExposure != null ? baseCurrencyExposure.hashCode() : 0);
			_result = 31 * _result + (collateralPortfolio != null ? collateralPortfolio.hashCode() : 0);
			_result = 31 * _result + (independentAmountBalance != null ? independentAmountBalance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallBase {" +
				"instructionType=" + this.instructionType + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"clearingBroker=" + this.clearingBroker + ", " +
				"callIdentifier=" + this.callIdentifier + ", " +
				"callAgreementType=" + this.callAgreementType + ", " +
				"agreementMinimumTransferAmount=" + this.agreementMinimumTransferAmount + ", " +
				"agreementThreshold=" + this.agreementThreshold + ", " +
				"agreementRounding=" + this.agreementRounding + ", " +
				"regMarginType=" + this.regMarginType + ", " +
				"regIMRole=" + this.regIMRole + ", " +
				"baseCurrencyExposure=" + this.baseCurrencyExposure + ", " +
				"collateralPortfolio=" + this.collateralPortfolio + ", " +
				"independentAmountBalance=" + this.independentAmountBalance +
			'}';
		}
	}

	/*********************** Builder Implementation of MarginCallBase  ***********************/
	class MarginCallBaseBuilderImpl implements MarginCallBase.MarginCallBaseBuilder {
	
		protected MarginCallInstructionType.MarginCallInstructionTypeBuilder instructionType;
		protected List<Party.PartyBuilder> party = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> partyRole = new ArrayList<>();
		protected Party.PartyBuilder clearingBroker;
		protected Identifier.IdentifierBuilder callIdentifier;
		protected AgreementName.AgreementNameBuilder callAgreementType;
		protected Money.MoneyBuilder agreementMinimumTransferAmount;
		protected Money.MoneyBuilder agreementThreshold;
		protected Money.MoneyBuilder agreementRounding;
		protected RegMarginTypeEnum regMarginType;
		protected RegIMRoleEnum regIMRole;
		protected MarginCallExposure.MarginCallExposureBuilder baseCurrencyExposure;
		protected ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder collateralPortfolio;
		protected CollateralBalance.CollateralBalanceBuilder independentAmountBalance;
	
		public MarginCallBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("instructionType")
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder getInstructionType() {
			return instructionType;
		}
		
		@Override
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder getOrCreateInstructionType() {
			MarginCallInstructionType.MarginCallInstructionTypeBuilder result;
			if (instructionType!=null) {
				result = instructionType;
			}
			else {
				result = instructionType = MarginCallInstructionType.builder();
			}
			
			return result;
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
		@RosettaAttribute("clearingBroker")
		public Party.PartyBuilder getClearingBroker() {
			return clearingBroker;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateClearingBroker() {
			Party.PartyBuilder result;
			if (clearingBroker!=null) {
				result = clearingBroker;
			}
			else {
				result = clearingBroker = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("callIdentifier")
		public Identifier.IdentifierBuilder getCallIdentifier() {
			return callIdentifier;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateCallIdentifier() {
			Identifier.IdentifierBuilder result;
			if (callIdentifier!=null) {
				result = callIdentifier;
			}
			else {
				result = callIdentifier = Identifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("callAgreementType")
		public AgreementName.AgreementNameBuilder getCallAgreementType() {
			return callAgreementType;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder getOrCreateCallAgreementType() {
			AgreementName.AgreementNameBuilder result;
			if (callAgreementType!=null) {
				result = callAgreementType;
			}
			else {
				result = callAgreementType = AgreementName.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("agreementMinimumTransferAmount")
		public Money.MoneyBuilder getAgreementMinimumTransferAmount() {
			return agreementMinimumTransferAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAgreementMinimumTransferAmount() {
			Money.MoneyBuilder result;
			if (agreementMinimumTransferAmount!=null) {
				result = agreementMinimumTransferAmount;
			}
			else {
				result = agreementMinimumTransferAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("agreementThreshold")
		public Money.MoneyBuilder getAgreementThreshold() {
			return agreementThreshold;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAgreementThreshold() {
			Money.MoneyBuilder result;
			if (agreementThreshold!=null) {
				result = agreementThreshold;
			}
			else {
				result = agreementThreshold = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("agreementRounding")
		public Money.MoneyBuilder getAgreementRounding() {
			return agreementRounding;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAgreementRounding() {
			Money.MoneyBuilder result;
			if (agreementRounding!=null) {
				result = agreementRounding;
			}
			else {
				result = agreementRounding = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("regMarginType")
		public RegMarginTypeEnum getRegMarginType() {
			return regMarginType;
		}
		
		@Override
		@RosettaAttribute("regIMRole")
		public RegIMRoleEnum getRegIMRole() {
			return regIMRole;
		}
		
		@Override
		@RosettaAttribute("baseCurrencyExposure")
		public MarginCallExposure.MarginCallExposureBuilder getBaseCurrencyExposure() {
			return baseCurrencyExposure;
		}
		
		@Override
		public MarginCallExposure.MarginCallExposureBuilder getOrCreateBaseCurrencyExposure() {
			MarginCallExposure.MarginCallExposureBuilder result;
			if (baseCurrencyExposure!=null) {
				result = baseCurrencyExposure;
			}
			else {
				result = baseCurrencyExposure = MarginCallExposure.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("collateralPortfolio")
		public ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getCollateralPortfolio() {
			return collateralPortfolio;
		}
		
		@Override
		public ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getOrCreateCollateralPortfolio() {
			ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder result;
			if (collateralPortfolio!=null) {
				result = collateralPortfolio;
			}
			else {
				result = collateralPortfolio = ReferenceWithMetaCollateralPortfolio.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("independentAmountBalance")
		public CollateralBalance.CollateralBalanceBuilder getIndependentAmountBalance() {
			return independentAmountBalance;
		}
		
		@Override
		public CollateralBalance.CollateralBalanceBuilder getOrCreateIndependentAmountBalance() {
			CollateralBalance.CollateralBalanceBuilder result;
			if (independentAmountBalance!=null) {
				result = independentAmountBalance;
			}
			else {
				result = independentAmountBalance = CollateralBalance.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("instructionType")
		public MarginCallBase.MarginCallBaseBuilder setInstructionType(MarginCallInstructionType instructionType) {
			this.instructionType = instructionType==null?null:instructionType.toBuilder();
			return this;
		}
		@Override
		public MarginCallBase.MarginCallBaseBuilder addParty(Party party) {
			if (party!=null) this.party.add(party.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallBase.MarginCallBaseBuilder addParty(Party party, int _idx) {
			getIndex(this.party, _idx, () -> party.toBuilder());
			return this;
		}
		@Override 
		public MarginCallBase.MarginCallBaseBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("party")
		public MarginCallBase.MarginCallBaseBuilder setParty(List<? extends Party> partys) {
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
		public MarginCallBase.MarginCallBaseBuilder addPartyRole(PartyRole partyRole) {
			if (partyRole!=null) this.partyRole.add(partyRole.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallBase.MarginCallBaseBuilder addPartyRole(PartyRole partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> partyRole.toBuilder());
			return this;
		}
		@Override 
		public MarginCallBase.MarginCallBaseBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRole")
		public MarginCallBase.MarginCallBaseBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
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
		@RosettaAttribute("clearingBroker")
		public MarginCallBase.MarginCallBaseBuilder setClearingBroker(Party clearingBroker) {
			this.clearingBroker = clearingBroker==null?null:clearingBroker.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("callIdentifier")
		public MarginCallBase.MarginCallBaseBuilder setCallIdentifier(Identifier callIdentifier) {
			this.callIdentifier = callIdentifier==null?null:callIdentifier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("callAgreementType")
		public MarginCallBase.MarginCallBaseBuilder setCallAgreementType(AgreementName callAgreementType) {
			this.callAgreementType = callAgreementType==null?null:callAgreementType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementMinimumTransferAmount")
		public MarginCallBase.MarginCallBaseBuilder setAgreementMinimumTransferAmount(Money agreementMinimumTransferAmount) {
			this.agreementMinimumTransferAmount = agreementMinimumTransferAmount==null?null:agreementMinimumTransferAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementThreshold")
		public MarginCallBase.MarginCallBaseBuilder setAgreementThreshold(Money agreementThreshold) {
			this.agreementThreshold = agreementThreshold==null?null:agreementThreshold.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementRounding")
		public MarginCallBase.MarginCallBaseBuilder setAgreementRounding(Money agreementRounding) {
			this.agreementRounding = agreementRounding==null?null:agreementRounding.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("regMarginType")
		public MarginCallBase.MarginCallBaseBuilder setRegMarginType(RegMarginTypeEnum regMarginType) {
			this.regMarginType = regMarginType==null?null:regMarginType;
			return this;
		}
		@Override
		@RosettaAttribute("regIMRole")
		public MarginCallBase.MarginCallBaseBuilder setRegIMRole(RegIMRoleEnum regIMRole) {
			this.regIMRole = regIMRole==null?null:regIMRole;
			return this;
		}
		@Override
		@RosettaAttribute("baseCurrencyExposure")
		public MarginCallBase.MarginCallBaseBuilder setBaseCurrencyExposure(MarginCallExposure baseCurrencyExposure) {
			this.baseCurrencyExposure = baseCurrencyExposure==null?null:baseCurrencyExposure.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("collateralPortfolio")
		public MarginCallBase.MarginCallBaseBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio) {
			this.collateralPortfolio = collateralPortfolio==null?null:collateralPortfolio.toBuilder();
			return this;
		}
		@Override
		public MarginCallBase.MarginCallBaseBuilder setCollateralPortfolioValue(CollateralPortfolio collateralPortfolio) {
			this.getOrCreateCollateralPortfolio().setValue(collateralPortfolio);
			return this;
		}
		@Override
		@RosettaAttribute("independentAmountBalance")
		public MarginCallBase.MarginCallBaseBuilder setIndependentAmountBalance(CollateralBalance independentAmountBalance) {
			this.independentAmountBalance = independentAmountBalance==null?null:independentAmountBalance.toBuilder();
			return this;
		}
		
		@Override
		public MarginCallBase build() {
			return new MarginCallBase.MarginCallBaseImpl(this);
		}
		
		@Override
		public MarginCallBase.MarginCallBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallBase.MarginCallBaseBuilder prune() {
			if (instructionType!=null && !instructionType.prune().hasData()) instructionType = null;
			party = party.stream().filter(b->b!=null).<Party.PartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			partyRole = partyRole.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (clearingBroker!=null && !clearingBroker.prune().hasData()) clearingBroker = null;
			if (callIdentifier!=null && !callIdentifier.prune().hasData()) callIdentifier = null;
			if (callAgreementType!=null && !callAgreementType.prune().hasData()) callAgreementType = null;
			if (agreementMinimumTransferAmount!=null && !agreementMinimumTransferAmount.prune().hasData()) agreementMinimumTransferAmount = null;
			if (agreementThreshold!=null && !agreementThreshold.prune().hasData()) agreementThreshold = null;
			if (agreementRounding!=null && !agreementRounding.prune().hasData()) agreementRounding = null;
			if (baseCurrencyExposure!=null && !baseCurrencyExposure.prune().hasData()) baseCurrencyExposure = null;
			if (collateralPortfolio!=null && !collateralPortfolio.prune().hasData()) collateralPortfolio = null;
			if (independentAmountBalance!=null && !independentAmountBalance.prune().hasData()) independentAmountBalance = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInstructionType()!=null && getInstructionType().hasData()) return true;
			if (getParty()!=null && getParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPartyRole()!=null && getPartyRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getClearingBroker()!=null && getClearingBroker().hasData()) return true;
			if (getCallIdentifier()!=null && getCallIdentifier().hasData()) return true;
			if (getCallAgreementType()!=null && getCallAgreementType().hasData()) return true;
			if (getAgreementMinimumTransferAmount()!=null && getAgreementMinimumTransferAmount().hasData()) return true;
			if (getAgreementThreshold()!=null && getAgreementThreshold().hasData()) return true;
			if (getAgreementRounding()!=null && getAgreementRounding().hasData()) return true;
			if (getRegMarginType()!=null) return true;
			if (getRegIMRole()!=null) return true;
			if (getBaseCurrencyExposure()!=null && getBaseCurrencyExposure().hasData()) return true;
			if (getCollateralPortfolio()!=null && getCollateralPortfolio().hasData()) return true;
			if (getIndependentAmountBalance()!=null && getIndependentAmountBalance().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallBase.MarginCallBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MarginCallBase.MarginCallBaseBuilder o = (MarginCallBase.MarginCallBaseBuilder) other;
			
			merger.mergeRosetta(getInstructionType(), o.getInstructionType(), this::setInstructionType);
			merger.mergeRosetta(getParty(), o.getParty(), this::getOrCreateParty);
			merger.mergeRosetta(getPartyRole(), o.getPartyRole(), this::getOrCreatePartyRole);
			merger.mergeRosetta(getClearingBroker(), o.getClearingBroker(), this::setClearingBroker);
			merger.mergeRosetta(getCallIdentifier(), o.getCallIdentifier(), this::setCallIdentifier);
			merger.mergeRosetta(getCallAgreementType(), o.getCallAgreementType(), this::setCallAgreementType);
			merger.mergeRosetta(getAgreementMinimumTransferAmount(), o.getAgreementMinimumTransferAmount(), this::setAgreementMinimumTransferAmount);
			merger.mergeRosetta(getAgreementThreshold(), o.getAgreementThreshold(), this::setAgreementThreshold);
			merger.mergeRosetta(getAgreementRounding(), o.getAgreementRounding(), this::setAgreementRounding);
			merger.mergeRosetta(getBaseCurrencyExposure(), o.getBaseCurrencyExposure(), this::setBaseCurrencyExposure);
			merger.mergeRosetta(getCollateralPortfolio(), o.getCollateralPortfolio(), this::setCollateralPortfolio);
			merger.mergeRosetta(getIndependentAmountBalance(), o.getIndependentAmountBalance(), this::setIndependentAmountBalance);
			
			merger.mergeBasic(getRegMarginType(), o.getRegMarginType(), this::setRegMarginType);
			merger.mergeBasic(getRegIMRole(), o.getRegIMRole(), this::setRegIMRole);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallBase _that = getType().cast(o);
		
			if (!Objects.equals(instructionType, _that.getInstructionType())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(clearingBroker, _that.getClearingBroker())) return false;
			if (!Objects.equals(callIdentifier, _that.getCallIdentifier())) return false;
			if (!Objects.equals(callAgreementType, _that.getCallAgreementType())) return false;
			if (!Objects.equals(agreementMinimumTransferAmount, _that.getAgreementMinimumTransferAmount())) return false;
			if (!Objects.equals(agreementThreshold, _that.getAgreementThreshold())) return false;
			if (!Objects.equals(agreementRounding, _that.getAgreementRounding())) return false;
			if (!Objects.equals(regMarginType, _that.getRegMarginType())) return false;
			if (!Objects.equals(regIMRole, _that.getRegIMRole())) return false;
			if (!Objects.equals(baseCurrencyExposure, _that.getBaseCurrencyExposure())) return false;
			if (!Objects.equals(collateralPortfolio, _that.getCollateralPortfolio())) return false;
			if (!Objects.equals(independentAmountBalance, _that.getIndependentAmountBalance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (instructionType != null ? instructionType.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (clearingBroker != null ? clearingBroker.hashCode() : 0);
			_result = 31 * _result + (callIdentifier != null ? callIdentifier.hashCode() : 0);
			_result = 31 * _result + (callAgreementType != null ? callAgreementType.hashCode() : 0);
			_result = 31 * _result + (agreementMinimumTransferAmount != null ? agreementMinimumTransferAmount.hashCode() : 0);
			_result = 31 * _result + (agreementThreshold != null ? agreementThreshold.hashCode() : 0);
			_result = 31 * _result + (agreementRounding != null ? agreementRounding.hashCode() : 0);
			_result = 31 * _result + (regMarginType != null ? regMarginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (regIMRole != null ? regIMRole.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (baseCurrencyExposure != null ? baseCurrencyExposure.hashCode() : 0);
			_result = 31 * _result + (collateralPortfolio != null ? collateralPortfolio.hashCode() : 0);
			_result = 31 * _result + (independentAmountBalance != null ? independentAmountBalance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallBaseBuilder {" +
				"instructionType=" + this.instructionType + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"clearingBroker=" + this.clearingBroker + ", " +
				"callIdentifier=" + this.callIdentifier + ", " +
				"callAgreementType=" + this.callAgreementType + ", " +
				"agreementMinimumTransferAmount=" + this.agreementMinimumTransferAmount + ", " +
				"agreementThreshold=" + this.agreementThreshold + ", " +
				"agreementRounding=" + this.agreementRounding + ", " +
				"regMarginType=" + this.regMarginType + ", " +
				"regIMRole=" + this.regIMRole + ", " +
				"baseCurrencyExposure=" + this.baseCurrencyExposure + ", " +
				"collateralPortfolio=" + this.collateralPortfolio + ", " +
				"independentAmountBalance=" + this.independentAmountBalance +
			'}';
		}
	}
}
