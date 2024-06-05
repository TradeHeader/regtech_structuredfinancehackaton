package cdm.event.common;

import cdm.base.staticdata.party.Party;
import cdm.event.common.ClearingInstruction;
import cdm.event.common.ClearingInstruction.ClearingInstructionBuilder;
import cdm.event.common.ClearingInstruction.ClearingInstructionBuilderImpl;
import cdm.event.common.ClearingInstruction.ClearingInstructionImpl;
import cdm.event.common.TradeState;
import cdm.event.common.meta.ClearingInstructionMeta;
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
 * All information required to perform the clear life cycle event; the clearing party (CCP), the two parties facing each other on the alpha contract, and optionally the parties acting as clearing members.
 * @version ${project.version}
 */
@RosettaDataType(value="ClearingInstruction", builder=ClearingInstruction.ClearingInstructionBuilderImpl.class, version="${project.version}")
public interface ClearingInstruction extends RosettaModelObject {

	ClearingInstructionMeta metaData = new ClearingInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The contract that will be submitted to the clearing house for clearing. The contract should indicate that it should be cleared by assigning a clearing organisation as a party role.
	 */
	TradeState getAlphaContract();
	/**
	 * The Central Counter party (CCP) that the contract will be submitted to for clearing.
	 */
	Party getClearingParty();
	/**
	 * First party facing the CCP if it is clearing for its own account.
	 */
	Party getParty1();
	/**
	 * Second party facing the CCP if it is clearing for its own account.
	 */
	Party getParty2();
	/**
	 * Optional party facing the CCP, acting as clearing member for party1.
	 */
	Party getClearerParty1();
	/**
	 * Optional party facing the CCP, acting as clearing member for party2.
	 */
	Party getClearerParty2();
	/**
	 * Open Offer
	 */
	Boolean getIsOpenOffer();

	/*********************** Build Methods  ***********************/
	ClearingInstruction build();
	
	ClearingInstruction.ClearingInstructionBuilder toBuilder();
	
	static ClearingInstruction.ClearingInstructionBuilder builder() {
		return new ClearingInstruction.ClearingInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ClearingInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ClearingInstruction> getType() {
		return ClearingInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("alphaContract"), processor, TradeState.class, getAlphaContract());
		processRosetta(path.newSubPath("clearingParty"), processor, Party.class, getClearingParty());
		processRosetta(path.newSubPath("party1"), processor, Party.class, getParty1());
		processRosetta(path.newSubPath("party2"), processor, Party.class, getParty2());
		processRosetta(path.newSubPath("clearerParty1"), processor, Party.class, getClearerParty1());
		processRosetta(path.newSubPath("clearerParty2"), processor, Party.class, getClearerParty2());
		processor.processBasic(path.newSubPath("isOpenOffer"), Boolean.class, getIsOpenOffer(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ClearingInstructionBuilder extends ClearingInstruction, RosettaModelObjectBuilder {
		TradeState.TradeStateBuilder getOrCreateAlphaContract();
		TradeState.TradeStateBuilder getAlphaContract();
		Party.PartyBuilder getOrCreateClearingParty();
		Party.PartyBuilder getClearingParty();
		Party.PartyBuilder getOrCreateParty1();
		Party.PartyBuilder getParty1();
		Party.PartyBuilder getOrCreateParty2();
		Party.PartyBuilder getParty2();
		Party.PartyBuilder getOrCreateClearerParty1();
		Party.PartyBuilder getClearerParty1();
		Party.PartyBuilder getOrCreateClearerParty2();
		Party.PartyBuilder getClearerParty2();
		ClearingInstruction.ClearingInstructionBuilder setAlphaContract(TradeState alphaContract);
		ClearingInstruction.ClearingInstructionBuilder setClearingParty(Party clearingParty);
		ClearingInstruction.ClearingInstructionBuilder setParty1(Party party1);
		ClearingInstruction.ClearingInstructionBuilder setParty2(Party party2);
		ClearingInstruction.ClearingInstructionBuilder setClearerParty1(Party clearerParty1);
		ClearingInstruction.ClearingInstructionBuilder setClearerParty2(Party clearerParty2);
		ClearingInstruction.ClearingInstructionBuilder setIsOpenOffer(Boolean isOpenOffer);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("alphaContract"), processor, TradeState.TradeStateBuilder.class, getAlphaContract());
			processRosetta(path.newSubPath("clearingParty"), processor, Party.PartyBuilder.class, getClearingParty());
			processRosetta(path.newSubPath("party1"), processor, Party.PartyBuilder.class, getParty1());
			processRosetta(path.newSubPath("party2"), processor, Party.PartyBuilder.class, getParty2());
			processRosetta(path.newSubPath("clearerParty1"), processor, Party.PartyBuilder.class, getClearerParty1());
			processRosetta(path.newSubPath("clearerParty2"), processor, Party.PartyBuilder.class, getClearerParty2());
			processor.processBasic(path.newSubPath("isOpenOffer"), Boolean.class, getIsOpenOffer(), this);
		}
		

		ClearingInstruction.ClearingInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of ClearingInstruction  ***********************/
	class ClearingInstructionImpl implements ClearingInstruction {
		private final TradeState alphaContract;
		private final Party clearingParty;
		private final Party party1;
		private final Party party2;
		private final Party clearerParty1;
		private final Party clearerParty2;
		private final Boolean isOpenOffer;
		
		protected ClearingInstructionImpl(ClearingInstruction.ClearingInstructionBuilder builder) {
			this.alphaContract = ofNullable(builder.getAlphaContract()).map(f->f.build()).orElse(null);
			this.clearingParty = ofNullable(builder.getClearingParty()).map(f->f.build()).orElse(null);
			this.party1 = ofNullable(builder.getParty1()).map(f->f.build()).orElse(null);
			this.party2 = ofNullable(builder.getParty2()).map(f->f.build()).orElse(null);
			this.clearerParty1 = ofNullable(builder.getClearerParty1()).map(f->f.build()).orElse(null);
			this.clearerParty2 = ofNullable(builder.getClearerParty2()).map(f->f.build()).orElse(null);
			this.isOpenOffer = builder.getIsOpenOffer();
		}
		
		@Override
		@RosettaAttribute("alphaContract")
		public TradeState getAlphaContract() {
			return alphaContract;
		}
		
		@Override
		@RosettaAttribute("clearingParty")
		public Party getClearingParty() {
			return clearingParty;
		}
		
		@Override
		@RosettaAttribute("party1")
		public Party getParty1() {
			return party1;
		}
		
		@Override
		@RosettaAttribute("party2")
		public Party getParty2() {
			return party2;
		}
		
		@Override
		@RosettaAttribute("clearerParty1")
		public Party getClearerParty1() {
			return clearerParty1;
		}
		
		@Override
		@RosettaAttribute("clearerParty2")
		public Party getClearerParty2() {
			return clearerParty2;
		}
		
		@Override
		@RosettaAttribute("isOpenOffer")
		public Boolean getIsOpenOffer() {
			return isOpenOffer;
		}
		
		@Override
		public ClearingInstruction build() {
			return this;
		}
		
		@Override
		public ClearingInstruction.ClearingInstructionBuilder toBuilder() {
			ClearingInstruction.ClearingInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ClearingInstruction.ClearingInstructionBuilder builder) {
			ofNullable(getAlphaContract()).ifPresent(builder::setAlphaContract);
			ofNullable(getClearingParty()).ifPresent(builder::setClearingParty);
			ofNullable(getParty1()).ifPresent(builder::setParty1);
			ofNullable(getParty2()).ifPresent(builder::setParty2);
			ofNullable(getClearerParty1()).ifPresent(builder::setClearerParty1);
			ofNullable(getClearerParty2()).ifPresent(builder::setClearerParty2);
			ofNullable(getIsOpenOffer()).ifPresent(builder::setIsOpenOffer);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ClearingInstruction _that = getType().cast(o);
		
			if (!Objects.equals(alphaContract, _that.getAlphaContract())) return false;
			if (!Objects.equals(clearingParty, _that.getClearingParty())) return false;
			if (!Objects.equals(party1, _that.getParty1())) return false;
			if (!Objects.equals(party2, _that.getParty2())) return false;
			if (!Objects.equals(clearerParty1, _that.getClearerParty1())) return false;
			if (!Objects.equals(clearerParty2, _that.getClearerParty2())) return false;
			if (!Objects.equals(isOpenOffer, _that.getIsOpenOffer())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (alphaContract != null ? alphaContract.hashCode() : 0);
			_result = 31 * _result + (clearingParty != null ? clearingParty.hashCode() : 0);
			_result = 31 * _result + (party1 != null ? party1.hashCode() : 0);
			_result = 31 * _result + (party2 != null ? party2.hashCode() : 0);
			_result = 31 * _result + (clearerParty1 != null ? clearerParty1.hashCode() : 0);
			_result = 31 * _result + (clearerParty2 != null ? clearerParty2.hashCode() : 0);
			_result = 31 * _result + (isOpenOffer != null ? isOpenOffer.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ClearingInstruction {" +
				"alphaContract=" + this.alphaContract + ", " +
				"clearingParty=" + this.clearingParty + ", " +
				"party1=" + this.party1 + ", " +
				"party2=" + this.party2 + ", " +
				"clearerParty1=" + this.clearerParty1 + ", " +
				"clearerParty2=" + this.clearerParty2 + ", " +
				"isOpenOffer=" + this.isOpenOffer +
			'}';
		}
	}

	/*********************** Builder Implementation of ClearingInstruction  ***********************/
	class ClearingInstructionBuilderImpl implements ClearingInstruction.ClearingInstructionBuilder {
	
		protected TradeState.TradeStateBuilder alphaContract;
		protected Party.PartyBuilder clearingParty;
		protected Party.PartyBuilder party1;
		protected Party.PartyBuilder party2;
		protected Party.PartyBuilder clearerParty1;
		protected Party.PartyBuilder clearerParty2;
		protected Boolean isOpenOffer;
	
		public ClearingInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("alphaContract")
		public TradeState.TradeStateBuilder getAlphaContract() {
			return alphaContract;
		}
		
		@Override
		public TradeState.TradeStateBuilder getOrCreateAlphaContract() {
			TradeState.TradeStateBuilder result;
			if (alphaContract!=null) {
				result = alphaContract;
			}
			else {
				result = alphaContract = TradeState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("clearingParty")
		public Party.PartyBuilder getClearingParty() {
			return clearingParty;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateClearingParty() {
			Party.PartyBuilder result;
			if (clearingParty!=null) {
				result = clearingParty;
			}
			else {
				result = clearingParty = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("party1")
		public Party.PartyBuilder getParty1() {
			return party1;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateParty1() {
			Party.PartyBuilder result;
			if (party1!=null) {
				result = party1;
			}
			else {
				result = party1 = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("party2")
		public Party.PartyBuilder getParty2() {
			return party2;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateParty2() {
			Party.PartyBuilder result;
			if (party2!=null) {
				result = party2;
			}
			else {
				result = party2 = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("clearerParty1")
		public Party.PartyBuilder getClearerParty1() {
			return clearerParty1;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateClearerParty1() {
			Party.PartyBuilder result;
			if (clearerParty1!=null) {
				result = clearerParty1;
			}
			else {
				result = clearerParty1 = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("clearerParty2")
		public Party.PartyBuilder getClearerParty2() {
			return clearerParty2;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateClearerParty2() {
			Party.PartyBuilder result;
			if (clearerParty2!=null) {
				result = clearerParty2;
			}
			else {
				result = clearerParty2 = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("isOpenOffer")
		public Boolean getIsOpenOffer() {
			return isOpenOffer;
		}
		
	
		@Override
		@RosettaAttribute("alphaContract")
		public ClearingInstruction.ClearingInstructionBuilder setAlphaContract(TradeState alphaContract) {
			this.alphaContract = alphaContract==null?null:alphaContract.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("clearingParty")
		public ClearingInstruction.ClearingInstructionBuilder setClearingParty(Party clearingParty) {
			this.clearingParty = clearingParty==null?null:clearingParty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("party1")
		public ClearingInstruction.ClearingInstructionBuilder setParty1(Party party1) {
			this.party1 = party1==null?null:party1.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("party2")
		public ClearingInstruction.ClearingInstructionBuilder setParty2(Party party2) {
			this.party2 = party2==null?null:party2.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("clearerParty1")
		public ClearingInstruction.ClearingInstructionBuilder setClearerParty1(Party clearerParty1) {
			this.clearerParty1 = clearerParty1==null?null:clearerParty1.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("clearerParty2")
		public ClearingInstruction.ClearingInstructionBuilder setClearerParty2(Party clearerParty2) {
			this.clearerParty2 = clearerParty2==null?null:clearerParty2.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("isOpenOffer")
		public ClearingInstruction.ClearingInstructionBuilder setIsOpenOffer(Boolean isOpenOffer) {
			this.isOpenOffer = isOpenOffer==null?null:isOpenOffer;
			return this;
		}
		
		@Override
		public ClearingInstruction build() {
			return new ClearingInstruction.ClearingInstructionImpl(this);
		}
		
		@Override
		public ClearingInstruction.ClearingInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ClearingInstruction.ClearingInstructionBuilder prune() {
			if (alphaContract!=null && !alphaContract.prune().hasData()) alphaContract = null;
			if (clearingParty!=null && !clearingParty.prune().hasData()) clearingParty = null;
			if (party1!=null && !party1.prune().hasData()) party1 = null;
			if (party2!=null && !party2.prune().hasData()) party2 = null;
			if (clearerParty1!=null && !clearerParty1.prune().hasData()) clearerParty1 = null;
			if (clearerParty2!=null && !clearerParty2.prune().hasData()) clearerParty2 = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAlphaContract()!=null && getAlphaContract().hasData()) return true;
			if (getClearingParty()!=null && getClearingParty().hasData()) return true;
			if (getParty1()!=null && getParty1().hasData()) return true;
			if (getParty2()!=null && getParty2().hasData()) return true;
			if (getClearerParty1()!=null && getClearerParty1().hasData()) return true;
			if (getClearerParty2()!=null && getClearerParty2().hasData()) return true;
			if (getIsOpenOffer()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ClearingInstruction.ClearingInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ClearingInstruction.ClearingInstructionBuilder o = (ClearingInstruction.ClearingInstructionBuilder) other;
			
			merger.mergeRosetta(getAlphaContract(), o.getAlphaContract(), this::setAlphaContract);
			merger.mergeRosetta(getClearingParty(), o.getClearingParty(), this::setClearingParty);
			merger.mergeRosetta(getParty1(), o.getParty1(), this::setParty1);
			merger.mergeRosetta(getParty2(), o.getParty2(), this::setParty2);
			merger.mergeRosetta(getClearerParty1(), o.getClearerParty1(), this::setClearerParty1);
			merger.mergeRosetta(getClearerParty2(), o.getClearerParty2(), this::setClearerParty2);
			
			merger.mergeBasic(getIsOpenOffer(), o.getIsOpenOffer(), this::setIsOpenOffer);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ClearingInstruction _that = getType().cast(o);
		
			if (!Objects.equals(alphaContract, _that.getAlphaContract())) return false;
			if (!Objects.equals(clearingParty, _that.getClearingParty())) return false;
			if (!Objects.equals(party1, _that.getParty1())) return false;
			if (!Objects.equals(party2, _that.getParty2())) return false;
			if (!Objects.equals(clearerParty1, _that.getClearerParty1())) return false;
			if (!Objects.equals(clearerParty2, _that.getClearerParty2())) return false;
			if (!Objects.equals(isOpenOffer, _that.getIsOpenOffer())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (alphaContract != null ? alphaContract.hashCode() : 0);
			_result = 31 * _result + (clearingParty != null ? clearingParty.hashCode() : 0);
			_result = 31 * _result + (party1 != null ? party1.hashCode() : 0);
			_result = 31 * _result + (party2 != null ? party2.hashCode() : 0);
			_result = 31 * _result + (clearerParty1 != null ? clearerParty1.hashCode() : 0);
			_result = 31 * _result + (clearerParty2 != null ? clearerParty2.hashCode() : 0);
			_result = 31 * _result + (isOpenOffer != null ? isOpenOffer.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ClearingInstructionBuilder {" +
				"alphaContract=" + this.alphaContract + ", " +
				"clearingParty=" + this.clearingParty + ", " +
				"party1=" + this.party1 + ", " +
				"party2=" + this.party2 + ", " +
				"clearerParty1=" + this.clearerParty1 + ", " +
				"clearerParty2=" + this.clearerParty2 + ", " +
				"isOpenOffer=" + this.isOpenOffer +
			'}';
		}
	}
}
