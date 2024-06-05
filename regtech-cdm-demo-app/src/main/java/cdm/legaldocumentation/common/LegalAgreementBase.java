package cdm.legaldocumentation.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
import cdm.legaldocumentation.common.LegalAgreementBase;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseBuilder;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseBuilderImpl;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseImpl;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.Resource;
import cdm.legaldocumentation.common.meta.LegalAgreementBaseMeta;
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
 * Specifies the legal agreement baseline information, being negotiated or having been executed. It excludes specialized elections
 * @version ${project.version}
 */
@RosettaDataType(value="LegalAgreementBase", builder=LegalAgreementBase.LegalAgreementBaseBuilderImpl.class, version="${project.version}")
public interface LegalAgreementBase extends RosettaModelObject {

	LegalAgreementBaseMeta metaData = new LegalAgreementBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The date on which the legal agreement has been agreed between the parties. This corresponds to the Date of Deed in an English Law document.
	 */
	Date getAgreementDate();
	/**
	 * The date on which, or as of which, the agreement is effective, if different from the agreement date. It is expected that it will most often correspond to the agreement date, although there could be situations where the parties will explicitly agree on a distinct effective date.
	 */
	Date getEffectiveDate();
	/**
	 * The legal agreement identifier. Several identifiers can be specified.
	 */
	List<? extends Identifier> getIdentifier();
	/**
	 * The type of legal agreement, identified via a set of composable attributes: agreementName, publisher, governing law and version, e.g. ISDA 2013 Standard Credit Support Annex English Law.
	 */
	LegalAgreementIdentification getLegalAgreementIdentification();
	/**
	 * The two contractual parties to the legal agreement, which reference information is positioned as part of the partyInformation attribute.
	 */
	List<? extends ReferenceWithMetaParty> getContractualParty();
	/**
	 * The role(s) that other party(ies) may have in relation to the legal agreement, further to the contractual parties.
	 */
	List<? extends PartyRole> getOtherParty();
	/**
	 * A human readable document, for example a confirmation.
	 */
	List<? extends Resource> getAttachment();

	/*********************** Build Methods  ***********************/
	LegalAgreementBase build();
	
	LegalAgreementBase.LegalAgreementBaseBuilder toBuilder();
	
	static LegalAgreementBase.LegalAgreementBaseBuilder builder() {
		return new LegalAgreementBase.LegalAgreementBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LegalAgreementBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LegalAgreementBase> getType() {
		return LegalAgreementBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("agreementDate"), Date.class, getAgreementDate(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processRosetta(path.newSubPath("identifier"), processor, Identifier.class, getIdentifier());
		processRosetta(path.newSubPath("legalAgreementIdentification"), processor, LegalAgreementIdentification.class, getLegalAgreementIdentification());
		processRosetta(path.newSubPath("contractualParty"), processor, ReferenceWithMetaParty.class, getContractualParty());
		processRosetta(path.newSubPath("otherParty"), processor, PartyRole.class, getOtherParty());
		processRosetta(path.newSubPath("attachment"), processor, Resource.class, getAttachment());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LegalAgreementBaseBuilder extends LegalAgreementBase, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateIdentifier(int _index);
		List<? extends Identifier.IdentifierBuilder> getIdentifier();
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder getOrCreateLegalAgreementIdentification();
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder getLegalAgreementIdentification();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateContractualParty(int _index);
		List<? extends ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder> getContractualParty();
		PartyRole.PartyRoleBuilder getOrCreateOtherParty(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getOtherParty();
		Resource.ResourceBuilder getOrCreateAttachment(int _index);
		List<? extends Resource.ResourceBuilder> getAttachment();
		LegalAgreementBase.LegalAgreementBaseBuilder setAgreementDate(Date agreementDate);
		LegalAgreementBase.LegalAgreementBaseBuilder setEffectiveDate(Date effectiveDate);
		LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(Identifier identifier0);
		LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(Identifier identifier1, int _idx);
		LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(List<? extends Identifier> identifier2);
		LegalAgreementBase.LegalAgreementBaseBuilder setIdentifier(List<? extends Identifier> identifier3);
		LegalAgreementBase.LegalAgreementBaseBuilder setLegalAgreementIdentification(LegalAgreementIdentification legalAgreementIdentification);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(ReferenceWithMetaParty contractualParty0);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(ReferenceWithMetaParty contractualParty1, int _idx);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(Party contractualParty2);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(Party contractualParty3, int _idx);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(List<? extends ReferenceWithMetaParty> contractualParty4);
		LegalAgreementBase.LegalAgreementBaseBuilder setContractualParty(List<? extends ReferenceWithMetaParty> contractualParty5);
		LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(List<? extends Party> contractualParty6);
		LegalAgreementBase.LegalAgreementBaseBuilder setContractualPartyValue(List<? extends Party> contractualParty7);
		LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(PartyRole otherParty0);
		LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(PartyRole otherParty1, int _idx);
		LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(List<? extends PartyRole> otherParty2);
		LegalAgreementBase.LegalAgreementBaseBuilder setOtherParty(List<? extends PartyRole> otherParty3);
		LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(Resource attachment0);
		LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(Resource attachment1, int _idx);
		LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(List<? extends Resource> attachment2);
		LegalAgreementBase.LegalAgreementBaseBuilder setAttachment(List<? extends Resource> attachment3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("agreementDate"), Date.class, getAgreementDate(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processRosetta(path.newSubPath("identifier"), processor, Identifier.IdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("legalAgreementIdentification"), processor, LegalAgreementIdentification.LegalAgreementIdentificationBuilder.class, getLegalAgreementIdentification());
			processRosetta(path.newSubPath("contractualParty"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getContractualParty());
			processRosetta(path.newSubPath("otherParty"), processor, PartyRole.PartyRoleBuilder.class, getOtherParty());
			processRosetta(path.newSubPath("attachment"), processor, Resource.ResourceBuilder.class, getAttachment());
		}
		

		LegalAgreementBase.LegalAgreementBaseBuilder prune();
	}

	/*********************** Immutable Implementation of LegalAgreementBase  ***********************/
	class LegalAgreementBaseImpl implements LegalAgreementBase {
		private final Date agreementDate;
		private final Date effectiveDate;
		private final List<? extends Identifier> identifier;
		private final LegalAgreementIdentification legalAgreementIdentification;
		private final List<? extends ReferenceWithMetaParty> contractualParty;
		private final List<? extends PartyRole> otherParty;
		private final List<? extends Resource> attachment;
		
		protected LegalAgreementBaseImpl(LegalAgreementBase.LegalAgreementBaseBuilder builder) {
			this.agreementDate = builder.getAgreementDate();
			this.effectiveDate = builder.getEffectiveDate();
			this.identifier = ofNullable(builder.getIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.legalAgreementIdentification = ofNullable(builder.getLegalAgreementIdentification()).map(f->f.build()).orElse(null);
			this.contractualParty = ofNullable(builder.getContractualParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.otherParty = ofNullable(builder.getOtherParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.attachment = ofNullable(builder.getAttachment()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("agreementDate")
		public Date getAgreementDate() {
			return agreementDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("identifier")
		public List<? extends Identifier> getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("legalAgreementIdentification")
		public LegalAgreementIdentification getLegalAgreementIdentification() {
			return legalAgreementIdentification;
		}
		
		@Override
		@RosettaAttribute("contractualParty")
		public List<? extends ReferenceWithMetaParty> getContractualParty() {
			return contractualParty;
		}
		
		@Override
		@RosettaAttribute("otherParty")
		public List<? extends PartyRole> getOtherParty() {
			return otherParty;
		}
		
		@Override
		@RosettaAttribute("attachment")
		public List<? extends Resource> getAttachment() {
			return attachment;
		}
		
		@Override
		public LegalAgreementBase build() {
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder toBuilder() {
			LegalAgreementBase.LegalAgreementBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LegalAgreementBase.LegalAgreementBaseBuilder builder) {
			ofNullable(getAgreementDate()).ifPresent(builder::setAgreementDate);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getLegalAgreementIdentification()).ifPresent(builder::setLegalAgreementIdentification);
			ofNullable(getContractualParty()).ifPresent(builder::setContractualParty);
			ofNullable(getOtherParty()).ifPresent(builder::setOtherParty);
			ofNullable(getAttachment()).ifPresent(builder::setAttachment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalAgreementBase _that = getType().cast(o);
		
			if (!Objects.equals(agreementDate, _that.getAgreementDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(legalAgreementIdentification, _that.getLegalAgreementIdentification())) return false;
			if (!ListEquals.listEquals(contractualParty, _that.getContractualParty())) return false;
			if (!ListEquals.listEquals(otherParty, _that.getOtherParty())) return false;
			if (!ListEquals.listEquals(attachment, _that.getAttachment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agreementDate != null ? agreementDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (legalAgreementIdentification != null ? legalAgreementIdentification.hashCode() : 0);
			_result = 31 * _result + (contractualParty != null ? contractualParty.hashCode() : 0);
			_result = 31 * _result + (otherParty != null ? otherParty.hashCode() : 0);
			_result = 31 * _result + (attachment != null ? attachment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreementBase {" +
				"agreementDate=" + this.agreementDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"identifier=" + this.identifier + ", " +
				"legalAgreementIdentification=" + this.legalAgreementIdentification + ", " +
				"contractualParty=" + this.contractualParty + ", " +
				"otherParty=" + this.otherParty + ", " +
				"attachment=" + this.attachment +
			'}';
		}
	}

	/*********************** Builder Implementation of LegalAgreementBase  ***********************/
	class LegalAgreementBaseBuilderImpl implements LegalAgreementBase.LegalAgreementBaseBuilder {
	
		protected Date agreementDate;
		protected Date effectiveDate;
		protected List<Identifier.IdentifierBuilder> identifier = new ArrayList<>();
		protected LegalAgreementIdentification.LegalAgreementIdentificationBuilder legalAgreementIdentification;
		protected List<ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder> contractualParty = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> otherParty = new ArrayList<>();
		protected List<Resource.ResourceBuilder> attachment = new ArrayList<>();
	
		public LegalAgreementBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("agreementDate")
		public Date getAgreementDate() {
			return agreementDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("identifier")
		public List<? extends Identifier.IdentifierBuilder> getIdentifier() {
			return identifier;
		}
		
		public Identifier.IdentifierBuilder getOrCreateIdentifier(int _index) {
		
			if (identifier==null) {
				this.identifier = new ArrayList<>();
			}
			Identifier.IdentifierBuilder result;
			return getIndex(identifier, _index, () -> {
						Identifier.IdentifierBuilder newIdentifier = Identifier.builder();
						return newIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("legalAgreementIdentification")
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder getLegalAgreementIdentification() {
			return legalAgreementIdentification;
		}
		
		@Override
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder getOrCreateLegalAgreementIdentification() {
			LegalAgreementIdentification.LegalAgreementIdentificationBuilder result;
			if (legalAgreementIdentification!=null) {
				result = legalAgreementIdentification;
			}
			else {
				result = legalAgreementIdentification = LegalAgreementIdentification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("contractualParty")
		public List<? extends ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder> getContractualParty() {
			return contractualParty;
		}
		
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateContractualParty(int _index) {
		
			if (contractualParty==null) {
				this.contractualParty = new ArrayList<>();
			}
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			return getIndex(contractualParty, _index, () -> {
						ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder newContractualParty = ReferenceWithMetaParty.builder();
						return newContractualParty;
					});
		}
		
		@Override
		@RosettaAttribute("otherParty")
		public List<? extends PartyRole.PartyRoleBuilder> getOtherParty() {
			return otherParty;
		}
		
		public PartyRole.PartyRoleBuilder getOrCreateOtherParty(int _index) {
		
			if (otherParty==null) {
				this.otherParty = new ArrayList<>();
			}
			PartyRole.PartyRoleBuilder result;
			return getIndex(otherParty, _index, () -> {
						PartyRole.PartyRoleBuilder newOtherParty = PartyRole.builder();
						return newOtherParty;
					});
		}
		
		@Override
		@RosettaAttribute("attachment")
		public List<? extends Resource.ResourceBuilder> getAttachment() {
			return attachment;
		}
		
		public Resource.ResourceBuilder getOrCreateAttachment(int _index) {
		
			if (attachment==null) {
				this.attachment = new ArrayList<>();
			}
			Resource.ResourceBuilder result;
			return getIndex(attachment, _index, () -> {
						Resource.ResourceBuilder newAttachment = Resource.builder();
						return newAttachment;
					});
		}
		
	
		@Override
		@RosettaAttribute("agreementDate")
		public LegalAgreementBase.LegalAgreementBaseBuilder setAgreementDate(Date agreementDate) {
			this.agreementDate = agreementDate==null?null:agreementDate;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public LegalAgreementBase.LegalAgreementBaseBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(Identifier identifier) {
			if (identifier!=null) this.identifier.add(identifier.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(Identifier identifier, int _idx) {
			getIndex(this.identifier, _idx, () -> identifier.toBuilder());
			return this;
		}
		@Override 
		public LegalAgreementBase.LegalAgreementBaseBuilder addIdentifier(List<? extends Identifier> identifiers) {
			if (identifiers != null) {
				for (Identifier toAdd : identifiers) {
					this.identifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("identifier")
		public LegalAgreementBase.LegalAgreementBaseBuilder setIdentifier(List<? extends Identifier> identifiers) {
			if (identifiers == null)  {
				this.identifier = new ArrayList<>();
			}
			else {
				this.identifier = identifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("legalAgreementIdentification")
		public LegalAgreementBase.LegalAgreementBaseBuilder setLegalAgreementIdentification(LegalAgreementIdentification legalAgreementIdentification) {
			this.legalAgreementIdentification = legalAgreementIdentification==null?null:legalAgreementIdentification.toBuilder();
			return this;
		}
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(ReferenceWithMetaParty contractualParty) {
			if (contractualParty!=null) this.contractualParty.add(contractualParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(ReferenceWithMetaParty contractualParty, int _idx) {
			getIndex(this.contractualParty, _idx, () -> contractualParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(Party contractualParty) {
			this.getOrCreateContractualParty(-1).setValue(contractualParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(Party contractualParty, int _idx) {
			this.getOrCreateContractualParty(_idx).setValue(contractualParty.toBuilder());
			return this;
		}
		@Override 
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualParty(List<? extends ReferenceWithMetaParty> contractualPartys) {
			if (contractualPartys != null) {
				for (ReferenceWithMetaParty toAdd : contractualPartys) {
					this.contractualParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("contractualParty")
		public LegalAgreementBase.LegalAgreementBaseBuilder setContractualParty(List<? extends ReferenceWithMetaParty> contractualPartys) {
			if (contractualPartys == null)  {
				this.contractualParty = new ArrayList<>();
			}
			else {
				this.contractualParty = contractualPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addContractualPartyValue(List<? extends Party> contractualPartys) {
			if (contractualPartys != null) {
				for (Party toAdd : contractualPartys) {
					this.addContractualPartyValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder setContractualPartyValue(List<? extends Party> contractualPartys) {
			this.contractualParty.clear();
			if (contractualPartys!=null) {
				contractualPartys.forEach(this::addContractualPartyValue);
			}
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(PartyRole otherParty) {
			if (otherParty!=null) this.otherParty.add(otherParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(PartyRole otherParty, int _idx) {
			getIndex(this.otherParty, _idx, () -> otherParty.toBuilder());
			return this;
		}
		@Override 
		public LegalAgreementBase.LegalAgreementBaseBuilder addOtherParty(List<? extends PartyRole> otherPartys) {
			if (otherPartys != null) {
				for (PartyRole toAdd : otherPartys) {
					this.otherParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("otherParty")
		public LegalAgreementBase.LegalAgreementBaseBuilder setOtherParty(List<? extends PartyRole> otherPartys) {
			if (otherPartys == null)  {
				this.otherParty = new ArrayList<>();
			}
			else {
				this.otherParty = otherPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(Resource attachment) {
			if (attachment!=null) this.attachment.add(attachment.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(Resource attachment, int _idx) {
			getIndex(this.attachment, _idx, () -> attachment.toBuilder());
			return this;
		}
		@Override 
		public LegalAgreementBase.LegalAgreementBaseBuilder addAttachment(List<? extends Resource> attachments) {
			if (attachments != null) {
				for (Resource toAdd : attachments) {
					this.attachment.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("attachment")
		public LegalAgreementBase.LegalAgreementBaseBuilder setAttachment(List<? extends Resource> attachments) {
			if (attachments == null)  {
				this.attachment = new ArrayList<>();
			}
			else {
				this.attachment = attachments.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public LegalAgreementBase build() {
			return new LegalAgreementBase.LegalAgreementBaseImpl(this);
		}
		
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder prune() {
			identifier = identifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (legalAgreementIdentification!=null && !legalAgreementIdentification.prune().hasData()) legalAgreementIdentification = null;
			contractualParty = contractualParty.stream().filter(b->b!=null).<ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			otherParty = otherParty.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			attachment = attachment.stream().filter(b->b!=null).<Resource.ResourceBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAgreementDate()!=null) return true;
			if (getEffectiveDate()!=null) return true;
			if (getIdentifier()!=null && getIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getLegalAgreementIdentification()!=null && getLegalAgreementIdentification().hasData()) return true;
			if (getContractualParty()!=null && getContractualParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getOtherParty()!=null && getOtherParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAttachment()!=null && getAttachment().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreementBase.LegalAgreementBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			LegalAgreementBase.LegalAgreementBaseBuilder o = (LegalAgreementBase.LegalAgreementBaseBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::getOrCreateIdentifier);
			merger.mergeRosetta(getLegalAgreementIdentification(), o.getLegalAgreementIdentification(), this::setLegalAgreementIdentification);
			merger.mergeRosetta(getContractualParty(), o.getContractualParty(), this::getOrCreateContractualParty);
			merger.mergeRosetta(getOtherParty(), o.getOtherParty(), this::getOrCreateOtherParty);
			merger.mergeRosetta(getAttachment(), o.getAttachment(), this::getOrCreateAttachment);
			
			merger.mergeBasic(getAgreementDate(), o.getAgreementDate(), this::setAgreementDate);
			merger.mergeBasic(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalAgreementBase _that = getType().cast(o);
		
			if (!Objects.equals(agreementDate, _that.getAgreementDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(legalAgreementIdentification, _that.getLegalAgreementIdentification())) return false;
			if (!ListEquals.listEquals(contractualParty, _that.getContractualParty())) return false;
			if (!ListEquals.listEquals(otherParty, _that.getOtherParty())) return false;
			if (!ListEquals.listEquals(attachment, _that.getAttachment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agreementDate != null ? agreementDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (legalAgreementIdentification != null ? legalAgreementIdentification.hashCode() : 0);
			_result = 31 * _result + (contractualParty != null ? contractualParty.hashCode() : 0);
			_result = 31 * _result + (otherParty != null ? otherParty.hashCode() : 0);
			_result = 31 * _result + (attachment != null ? attachment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreementBaseBuilder {" +
				"agreementDate=" + this.agreementDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"identifier=" + this.identifier + ", " +
				"legalAgreementIdentification=" + this.legalAgreementIdentification + ", " +
				"contractualParty=" + this.contractualParty + ", " +
				"otherParty=" + this.otherParty + ", " +
				"attachment=" + this.attachment +
			'}';
		}
	}
}
