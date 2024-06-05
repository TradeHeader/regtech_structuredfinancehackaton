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
import cdm.event.common.MarginCallResponse;
import cdm.event.common.MarginCallResponse.MarginCallResponseBuilder;
import cdm.event.common.MarginCallResponse.MarginCallResponseBuilderImpl;
import cdm.event.common.MarginCallResponse.MarginCallResponseImpl;
import cdm.event.common.MarginCallResponseAction;
import cdm.event.common.MarginCallResponseTypeEnum;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
import cdm.event.common.meta.MarginCallResponseMeta;
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
 * Represents common attributes required for a Margin Call Response under a legal agreement such as a credit support document or equivalent.
 * @version ${project.version}
 */
@RosettaDataType(value="MarginCallResponse", builder=MarginCallResponse.MarginCallResponseBuilderImpl.class, version="${project.version}")
public interface MarginCallResponse extends MarginCallBase {

	MarginCallResponseMeta metaData = new MarginCallResponseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the margin call action details, including collateral to be moved and direction.
	 */
	List<? extends MarginCallResponseAction> getMarginCallResponseAction();
	/**
	 * Indicates the response type, such as, is the margin call response a &#39;full&#39; &#39;part&#39; agreement or &#39;dispute&#39;.
	 */
	MarginCallResponseTypeEnum getMarginResponseType();
	/**
	 * Indicates the amount that posting entity agrees to remit in response to margin call (in base currency).
	 */
	Money getAgreedAmountBaseCurrency();

	/*********************** Build Methods  ***********************/
	MarginCallResponse build();
	
	MarginCallResponse.MarginCallResponseBuilder toBuilder();
	
	static MarginCallResponse.MarginCallResponseBuilder builder() {
		return new MarginCallResponse.MarginCallResponseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MarginCallResponse> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MarginCallResponse> getType() {
		return MarginCallResponse.class;
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
		processRosetta(path.newSubPath("marginCallResponseAction"), processor, MarginCallResponseAction.class, getMarginCallResponseAction());
		processor.processBasic(path.newSubPath("marginResponseType"), MarginCallResponseTypeEnum.class, getMarginResponseType(), this);
		processRosetta(path.newSubPath("agreedAmountBaseCurrency"), processor, Money.class, getAgreedAmountBaseCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MarginCallResponseBuilder extends MarginCallResponse, MarginCallBase.MarginCallBaseBuilder, RosettaModelObjectBuilder {
		MarginCallResponseAction.MarginCallResponseActionBuilder getOrCreateMarginCallResponseAction(int _index);
		List<? extends MarginCallResponseAction.MarginCallResponseActionBuilder> getMarginCallResponseAction();
		Money.MoneyBuilder getOrCreateAgreedAmountBaseCurrency();
		Money.MoneyBuilder getAgreedAmountBaseCurrency();
		MarginCallResponse.MarginCallResponseBuilder setInstructionType(MarginCallInstructionType instructionType);
		MarginCallResponse.MarginCallResponseBuilder addParty(Party party0);
		MarginCallResponse.MarginCallResponseBuilder addParty(Party party1, int _idx);
		MarginCallResponse.MarginCallResponseBuilder addParty(List<? extends Party> party2);
		MarginCallResponse.MarginCallResponseBuilder setParty(List<? extends Party> party3);
		MarginCallResponse.MarginCallResponseBuilder addPartyRole(PartyRole partyRole0);
		MarginCallResponse.MarginCallResponseBuilder addPartyRole(PartyRole partyRole1, int _idx);
		MarginCallResponse.MarginCallResponseBuilder addPartyRole(List<? extends PartyRole> partyRole2);
		MarginCallResponse.MarginCallResponseBuilder setPartyRole(List<? extends PartyRole> partyRole3);
		MarginCallResponse.MarginCallResponseBuilder setClearingBroker(Party clearingBroker);
		MarginCallResponse.MarginCallResponseBuilder setCallIdentifier(Identifier callIdentifier);
		MarginCallResponse.MarginCallResponseBuilder setCallAgreementType(AgreementName callAgreementType);
		MarginCallResponse.MarginCallResponseBuilder setAgreementMinimumTransferAmount(Money agreementMinimumTransferAmount);
		MarginCallResponse.MarginCallResponseBuilder setAgreementThreshold(Money agreementThreshold);
		MarginCallResponse.MarginCallResponseBuilder setAgreementRounding(Money agreementRounding);
		MarginCallResponse.MarginCallResponseBuilder setRegMarginType(RegMarginTypeEnum regMarginType);
		MarginCallResponse.MarginCallResponseBuilder setRegIMRole(RegIMRoleEnum regIMRole);
		MarginCallResponse.MarginCallResponseBuilder setBaseCurrencyExposure(MarginCallExposure baseCurrencyExposure);
		MarginCallResponse.MarginCallResponseBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio0);
		MarginCallResponse.MarginCallResponseBuilder setCollateralPortfolioValue(CollateralPortfolio collateralPortfolio1);
		MarginCallResponse.MarginCallResponseBuilder setIndependentAmountBalance(CollateralBalance independentAmountBalance);
		MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(MarginCallResponseAction marginCallResponseAction0);
		MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(MarginCallResponseAction marginCallResponseAction1, int _idx);
		MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(List<? extends MarginCallResponseAction> marginCallResponseAction2);
		MarginCallResponse.MarginCallResponseBuilder setMarginCallResponseAction(List<? extends MarginCallResponseAction> marginCallResponseAction3);
		MarginCallResponse.MarginCallResponseBuilder setMarginResponseType(MarginCallResponseTypeEnum marginResponseType);
		MarginCallResponse.MarginCallResponseBuilder setAgreedAmountBaseCurrency(Money agreedAmountBaseCurrency);

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
			processRosetta(path.newSubPath("marginCallResponseAction"), processor, MarginCallResponseAction.MarginCallResponseActionBuilder.class, getMarginCallResponseAction());
			processor.processBasic(path.newSubPath("marginResponseType"), MarginCallResponseTypeEnum.class, getMarginResponseType(), this);
			processRosetta(path.newSubPath("agreedAmountBaseCurrency"), processor, Money.MoneyBuilder.class, getAgreedAmountBaseCurrency());
		}
		

		MarginCallResponse.MarginCallResponseBuilder prune();
	}

	/*********************** Immutable Implementation of MarginCallResponse  ***********************/
	class MarginCallResponseImpl extends MarginCallBase.MarginCallBaseImpl implements MarginCallResponse {
		private final List<? extends MarginCallResponseAction> marginCallResponseAction;
		private final MarginCallResponseTypeEnum marginResponseType;
		private final Money agreedAmountBaseCurrency;
		
		protected MarginCallResponseImpl(MarginCallResponse.MarginCallResponseBuilder builder) {
			super(builder);
			this.marginCallResponseAction = ofNullable(builder.getMarginCallResponseAction()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.marginResponseType = builder.getMarginResponseType();
			this.agreedAmountBaseCurrency = ofNullable(builder.getAgreedAmountBaseCurrency()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("marginCallResponseAction")
		public List<? extends MarginCallResponseAction> getMarginCallResponseAction() {
			return marginCallResponseAction;
		}
		
		@Override
		@RosettaAttribute("marginResponseType")
		public MarginCallResponseTypeEnum getMarginResponseType() {
			return marginResponseType;
		}
		
		@Override
		@RosettaAttribute("agreedAmountBaseCurrency")
		public Money getAgreedAmountBaseCurrency() {
			return agreedAmountBaseCurrency;
		}
		
		@Override
		public MarginCallResponse build() {
			return this;
		}
		
		@Override
		public MarginCallResponse.MarginCallResponseBuilder toBuilder() {
			MarginCallResponse.MarginCallResponseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MarginCallResponse.MarginCallResponseBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getMarginCallResponseAction()).ifPresent(builder::setMarginCallResponseAction);
			ofNullable(getMarginResponseType()).ifPresent(builder::setMarginResponseType);
			ofNullable(getAgreedAmountBaseCurrency()).ifPresent(builder::setAgreedAmountBaseCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MarginCallResponse _that = getType().cast(o);
		
			if (!ListEquals.listEquals(marginCallResponseAction, _that.getMarginCallResponseAction())) return false;
			if (!Objects.equals(marginResponseType, _that.getMarginResponseType())) return false;
			if (!Objects.equals(agreedAmountBaseCurrency, _that.getAgreedAmountBaseCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (marginCallResponseAction != null ? marginCallResponseAction.hashCode() : 0);
			_result = 31 * _result + (marginResponseType != null ? marginResponseType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agreedAmountBaseCurrency != null ? agreedAmountBaseCurrency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallResponse {" +
				"marginCallResponseAction=" + this.marginCallResponseAction + ", " +
				"marginResponseType=" + this.marginResponseType + ", " +
				"agreedAmountBaseCurrency=" + this.agreedAmountBaseCurrency +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of MarginCallResponse  ***********************/
	class MarginCallResponseBuilderImpl extends MarginCallBase.MarginCallBaseBuilderImpl  implements MarginCallResponse.MarginCallResponseBuilder {
	
		protected List<MarginCallResponseAction.MarginCallResponseActionBuilder> marginCallResponseAction = new ArrayList<>();
		protected MarginCallResponseTypeEnum marginResponseType;
		protected Money.MoneyBuilder agreedAmountBaseCurrency;
	
		public MarginCallResponseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("marginCallResponseAction")
		public List<? extends MarginCallResponseAction.MarginCallResponseActionBuilder> getMarginCallResponseAction() {
			return marginCallResponseAction;
		}
		
		public MarginCallResponseAction.MarginCallResponseActionBuilder getOrCreateMarginCallResponseAction(int _index) {
		
			if (marginCallResponseAction==null) {
				this.marginCallResponseAction = new ArrayList<>();
			}
			MarginCallResponseAction.MarginCallResponseActionBuilder result;
			return getIndex(marginCallResponseAction, _index, () -> {
						MarginCallResponseAction.MarginCallResponseActionBuilder newMarginCallResponseAction = MarginCallResponseAction.builder();
						return newMarginCallResponseAction;
					});
		}
		
		@Override
		@RosettaAttribute("marginResponseType")
		public MarginCallResponseTypeEnum getMarginResponseType() {
			return marginResponseType;
		}
		
		@Override
		@RosettaAttribute("agreedAmountBaseCurrency")
		public Money.MoneyBuilder getAgreedAmountBaseCurrency() {
			return agreedAmountBaseCurrency;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAgreedAmountBaseCurrency() {
			Money.MoneyBuilder result;
			if (agreedAmountBaseCurrency!=null) {
				result = agreedAmountBaseCurrency;
			}
			else {
				result = agreedAmountBaseCurrency = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("instructionType")
		public MarginCallResponse.MarginCallResponseBuilder setInstructionType(MarginCallInstructionType instructionType) {
			this.instructionType = instructionType==null?null:instructionType.toBuilder();
			return this;
		}
		@Override
		public MarginCallResponse.MarginCallResponseBuilder addParty(Party party) {
			if (party!=null) this.party.add(party.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallResponse.MarginCallResponseBuilder addParty(Party party, int _idx) {
			getIndex(this.party, _idx, () -> party.toBuilder());
			return this;
		}
		@Override 
		public MarginCallResponse.MarginCallResponseBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("party")
		public MarginCallResponse.MarginCallResponseBuilder setParty(List<? extends Party> partys) {
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
		public MarginCallResponse.MarginCallResponseBuilder addPartyRole(PartyRole partyRole) {
			if (partyRole!=null) this.partyRole.add(partyRole.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallResponse.MarginCallResponseBuilder addPartyRole(PartyRole partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> partyRole.toBuilder());
			return this;
		}
		@Override 
		public MarginCallResponse.MarginCallResponseBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRole")
		public MarginCallResponse.MarginCallResponseBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
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
		public MarginCallResponse.MarginCallResponseBuilder setClearingBroker(Party clearingBroker) {
			this.clearingBroker = clearingBroker==null?null:clearingBroker.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("callIdentifier")
		public MarginCallResponse.MarginCallResponseBuilder setCallIdentifier(Identifier callIdentifier) {
			this.callIdentifier = callIdentifier==null?null:callIdentifier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("callAgreementType")
		public MarginCallResponse.MarginCallResponseBuilder setCallAgreementType(AgreementName callAgreementType) {
			this.callAgreementType = callAgreementType==null?null:callAgreementType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementMinimumTransferAmount")
		public MarginCallResponse.MarginCallResponseBuilder setAgreementMinimumTransferAmount(Money agreementMinimumTransferAmount) {
			this.agreementMinimumTransferAmount = agreementMinimumTransferAmount==null?null:agreementMinimumTransferAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementThreshold")
		public MarginCallResponse.MarginCallResponseBuilder setAgreementThreshold(Money agreementThreshold) {
			this.agreementThreshold = agreementThreshold==null?null:agreementThreshold.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("agreementRounding")
		public MarginCallResponse.MarginCallResponseBuilder setAgreementRounding(Money agreementRounding) {
			this.agreementRounding = agreementRounding==null?null:agreementRounding.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("regMarginType")
		public MarginCallResponse.MarginCallResponseBuilder setRegMarginType(RegMarginTypeEnum regMarginType) {
			this.regMarginType = regMarginType==null?null:regMarginType;
			return this;
		}
		@Override
		@RosettaAttribute("regIMRole")
		public MarginCallResponse.MarginCallResponseBuilder setRegIMRole(RegIMRoleEnum regIMRole) {
			this.regIMRole = regIMRole==null?null:regIMRole;
			return this;
		}
		@Override
		@RosettaAttribute("baseCurrencyExposure")
		public MarginCallResponse.MarginCallResponseBuilder setBaseCurrencyExposure(MarginCallExposure baseCurrencyExposure) {
			this.baseCurrencyExposure = baseCurrencyExposure==null?null:baseCurrencyExposure.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("collateralPortfolio")
		public MarginCallResponse.MarginCallResponseBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio) {
			this.collateralPortfolio = collateralPortfolio==null?null:collateralPortfolio.toBuilder();
			return this;
		}
		@Override
		public MarginCallResponse.MarginCallResponseBuilder setCollateralPortfolioValue(CollateralPortfolio collateralPortfolio) {
			this.getOrCreateCollateralPortfolio().setValue(collateralPortfolio);
			return this;
		}
		@Override
		@RosettaAttribute("independentAmountBalance")
		public MarginCallResponse.MarginCallResponseBuilder setIndependentAmountBalance(CollateralBalance independentAmountBalance) {
			this.independentAmountBalance = independentAmountBalance==null?null:independentAmountBalance.toBuilder();
			return this;
		}
		@Override
		public MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(MarginCallResponseAction marginCallResponseAction) {
			if (marginCallResponseAction!=null) this.marginCallResponseAction.add(marginCallResponseAction.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(MarginCallResponseAction marginCallResponseAction, int _idx) {
			getIndex(this.marginCallResponseAction, _idx, () -> marginCallResponseAction.toBuilder());
			return this;
		}
		@Override 
		public MarginCallResponse.MarginCallResponseBuilder addMarginCallResponseAction(List<? extends MarginCallResponseAction> marginCallResponseActions) {
			if (marginCallResponseActions != null) {
				for (MarginCallResponseAction toAdd : marginCallResponseActions) {
					this.marginCallResponseAction.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("marginCallResponseAction")
		public MarginCallResponse.MarginCallResponseBuilder setMarginCallResponseAction(List<? extends MarginCallResponseAction> marginCallResponseActions) {
			if (marginCallResponseActions == null)  {
				this.marginCallResponseAction = new ArrayList<>();
			}
			else {
				this.marginCallResponseAction = marginCallResponseActions.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("marginResponseType")
		public MarginCallResponse.MarginCallResponseBuilder setMarginResponseType(MarginCallResponseTypeEnum marginResponseType) {
			this.marginResponseType = marginResponseType==null?null:marginResponseType;
			return this;
		}
		@Override
		@RosettaAttribute("agreedAmountBaseCurrency")
		public MarginCallResponse.MarginCallResponseBuilder setAgreedAmountBaseCurrency(Money agreedAmountBaseCurrency) {
			this.agreedAmountBaseCurrency = agreedAmountBaseCurrency==null?null:agreedAmountBaseCurrency.toBuilder();
			return this;
		}
		
		@Override
		public MarginCallResponse build() {
			return new MarginCallResponse.MarginCallResponseImpl(this);
		}
		
		@Override
		public MarginCallResponse.MarginCallResponseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallResponse.MarginCallResponseBuilder prune() {
			super.prune();
			marginCallResponseAction = marginCallResponseAction.stream().filter(b->b!=null).<MarginCallResponseAction.MarginCallResponseActionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (agreedAmountBaseCurrency!=null && !agreedAmountBaseCurrency.prune().hasData()) agreedAmountBaseCurrency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getMarginCallResponseAction()!=null && getMarginCallResponseAction().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMarginResponseType()!=null) return true;
			if (getAgreedAmountBaseCurrency()!=null && getAgreedAmountBaseCurrency().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallResponse.MarginCallResponseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			MarginCallResponse.MarginCallResponseBuilder o = (MarginCallResponse.MarginCallResponseBuilder) other;
			
			merger.mergeRosetta(getMarginCallResponseAction(), o.getMarginCallResponseAction(), this::getOrCreateMarginCallResponseAction);
			merger.mergeRosetta(getAgreedAmountBaseCurrency(), o.getAgreedAmountBaseCurrency(), this::setAgreedAmountBaseCurrency);
			
			merger.mergeBasic(getMarginResponseType(), o.getMarginResponseType(), this::setMarginResponseType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MarginCallResponse _that = getType().cast(o);
		
			if (!ListEquals.listEquals(marginCallResponseAction, _that.getMarginCallResponseAction())) return false;
			if (!Objects.equals(marginResponseType, _that.getMarginResponseType())) return false;
			if (!Objects.equals(agreedAmountBaseCurrency, _that.getAgreedAmountBaseCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (marginCallResponseAction != null ? marginCallResponseAction.hashCode() : 0);
			_result = 31 * _result + (marginResponseType != null ? marginResponseType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agreedAmountBaseCurrency != null ? agreedAmountBaseCurrency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallResponseBuilder {" +
				"marginCallResponseAction=" + this.marginCallResponseAction + ", " +
				"marginResponseType=" + this.marginResponseType + ", " +
				"agreedAmountBaseCurrency=" + this.agreedAmountBaseCurrency +
			'}' + " " + super.toString();
		}
	}
}
