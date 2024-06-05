package cdm.event.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.Confirmation;
import cdm.event.common.Confirmation.ConfirmationBuilder;
import cdm.event.common.Confirmation.ConfirmationBuilderImpl;
import cdm.event.common.Confirmation.ConfirmationImpl;
import cdm.event.common.ConfirmationStatusEnum;
import cdm.event.common.Lineage;
import cdm.event.common.meta.ConfirmationMeta;
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
 * A class to specify a trade confirmation.
 * @version ${project.version}
 */
@RosettaDataType(value="Confirmation", builder=Confirmation.ConfirmationBuilderImpl.class, version="${project.version}")
public interface Confirmation extends RosettaModelObject {

	ConfirmationMeta metaData = new ConfirmationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The identifier(s) associated with the trade and resulting confirmation.
	 */
	List<? extends Identifier> getIdentifier();
	/**
	 * The parties associated with the trade.
	 */
	List<? extends Party> getParty();
	/**
	 * The role(s) that party(ies) may have in relation to the trade
	 */
	List<? extends PartyRole> getPartyRole();
	/**
	 * The lineage attribute provides a linkage to previous lifecycle events and associated data.
	 */
	Lineage getLineage();
	ConfirmationStatusEnum getStatus();

	/*********************** Build Methods  ***********************/
	Confirmation build();
	
	Confirmation.ConfirmationBuilder toBuilder();
	
	static Confirmation.ConfirmationBuilder builder() {
		return new Confirmation.ConfirmationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Confirmation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Confirmation> getType() {
		return Confirmation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, Identifier.class, getIdentifier());
		processRosetta(path.newSubPath("party"), processor, Party.class, getParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("lineage"), processor, Lineage.class, getLineage());
		processor.processBasic(path.newSubPath("status"), ConfirmationStatusEnum.class, getStatus(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ConfirmationBuilder extends Confirmation, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateIdentifier(int _index);
		List<? extends Identifier.IdentifierBuilder> getIdentifier();
		Party.PartyBuilder getOrCreateParty(int _index);
		List<? extends Party.PartyBuilder> getParty();
		PartyRole.PartyRoleBuilder getOrCreatePartyRole(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getPartyRole();
		Lineage.LineageBuilder getOrCreateLineage();
		Lineage.LineageBuilder getLineage();
		Confirmation.ConfirmationBuilder addIdentifier(Identifier identifier0);
		Confirmation.ConfirmationBuilder addIdentifier(Identifier identifier1, int _idx);
		Confirmation.ConfirmationBuilder addIdentifier(List<? extends Identifier> identifier2);
		Confirmation.ConfirmationBuilder setIdentifier(List<? extends Identifier> identifier3);
		Confirmation.ConfirmationBuilder addParty(Party party0);
		Confirmation.ConfirmationBuilder addParty(Party party1, int _idx);
		Confirmation.ConfirmationBuilder addParty(List<? extends Party> party2);
		Confirmation.ConfirmationBuilder setParty(List<? extends Party> party3);
		Confirmation.ConfirmationBuilder addPartyRole(PartyRole partyRole0);
		Confirmation.ConfirmationBuilder addPartyRole(PartyRole partyRole1, int _idx);
		Confirmation.ConfirmationBuilder addPartyRole(List<? extends PartyRole> partyRole2);
		Confirmation.ConfirmationBuilder setPartyRole(List<? extends PartyRole> partyRole3);
		Confirmation.ConfirmationBuilder setLineage(Lineage lineage);
		Confirmation.ConfirmationBuilder setStatus(ConfirmationStatusEnum status);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, Identifier.IdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("party"), processor, Party.PartyBuilder.class, getParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("lineage"), processor, Lineage.LineageBuilder.class, getLineage());
			processor.processBasic(path.newSubPath("status"), ConfirmationStatusEnum.class, getStatus(), this);
		}
		

		Confirmation.ConfirmationBuilder prune();
	}

	/*********************** Immutable Implementation of Confirmation  ***********************/
	class ConfirmationImpl implements Confirmation {
		private final List<? extends Identifier> identifier;
		private final List<? extends Party> party;
		private final List<? extends PartyRole> partyRole;
		private final Lineage lineage;
		private final ConfirmationStatusEnum status;
		
		protected ConfirmationImpl(Confirmation.ConfirmationBuilder builder) {
			this.identifier = ofNullable(builder.getIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.party = ofNullable(builder.getParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.partyRole = ofNullable(builder.getPartyRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.lineage = ofNullable(builder.getLineage()).map(f->f.build()).orElse(null);
			this.status = builder.getStatus();
		}
		
		@Override
		@RosettaAttribute("identifier")
		public List<? extends Identifier> getIdentifier() {
			return identifier;
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
		@RosettaAttribute("lineage")
		public Lineage getLineage() {
			return lineage;
		}
		
		@Override
		@RosettaAttribute("status")
		public ConfirmationStatusEnum getStatus() {
			return status;
		}
		
		@Override
		public Confirmation build() {
			return this;
		}
		
		@Override
		public Confirmation.ConfirmationBuilder toBuilder() {
			Confirmation.ConfirmationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Confirmation.ConfirmationBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getParty()).ifPresent(builder::setParty);
			ofNullable(getPartyRole()).ifPresent(builder::setPartyRole);
			ofNullable(getLineage()).ifPresent(builder::setLineage);
			ofNullable(getStatus()).ifPresent(builder::setStatus);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Confirmation _that = getType().cast(o);
		
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(lineage, _that.getLineage())) return false;
			if (!Objects.equals(status, _that.getStatus())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (lineage != null ? lineage.hashCode() : 0);
			_result = 31 * _result + (status != null ? status.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Confirmation {" +
				"identifier=" + this.identifier + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"lineage=" + this.lineage + ", " +
				"status=" + this.status +
			'}';
		}
	}

	/*********************** Builder Implementation of Confirmation  ***********************/
	class ConfirmationBuilderImpl implements Confirmation.ConfirmationBuilder {
	
		protected List<Identifier.IdentifierBuilder> identifier = new ArrayList<>();
		protected List<Party.PartyBuilder> party = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> partyRole = new ArrayList<>();
		protected Lineage.LineageBuilder lineage;
		protected ConfirmationStatusEnum status;
	
		public ConfirmationBuilderImpl() {
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
		@RosettaAttribute("lineage")
		public Lineage.LineageBuilder getLineage() {
			return lineage;
		}
		
		@Override
		public Lineage.LineageBuilder getOrCreateLineage() {
			Lineage.LineageBuilder result;
			if (lineage!=null) {
				result = lineage;
			}
			else {
				result = lineage = Lineage.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("status")
		public ConfirmationStatusEnum getStatus() {
			return status;
		}
		
	
		@Override
		public Confirmation.ConfirmationBuilder addIdentifier(Identifier identifier) {
			if (identifier!=null) this.identifier.add(identifier.toBuilder());
			return this;
		}
		
		@Override
		public Confirmation.ConfirmationBuilder addIdentifier(Identifier identifier, int _idx) {
			getIndex(this.identifier, _idx, () -> identifier.toBuilder());
			return this;
		}
		@Override 
		public Confirmation.ConfirmationBuilder addIdentifier(List<? extends Identifier> identifiers) {
			if (identifiers != null) {
				for (Identifier toAdd : identifiers) {
					this.identifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("identifier")
		public Confirmation.ConfirmationBuilder setIdentifier(List<? extends Identifier> identifiers) {
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
		public Confirmation.ConfirmationBuilder addParty(Party party) {
			if (party!=null) this.party.add(party.toBuilder());
			return this;
		}
		
		@Override
		public Confirmation.ConfirmationBuilder addParty(Party party, int _idx) {
			getIndex(this.party, _idx, () -> party.toBuilder());
			return this;
		}
		@Override 
		public Confirmation.ConfirmationBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("party")
		public Confirmation.ConfirmationBuilder setParty(List<? extends Party> partys) {
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
		public Confirmation.ConfirmationBuilder addPartyRole(PartyRole partyRole) {
			if (partyRole!=null) this.partyRole.add(partyRole.toBuilder());
			return this;
		}
		
		@Override
		public Confirmation.ConfirmationBuilder addPartyRole(PartyRole partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> partyRole.toBuilder());
			return this;
		}
		@Override 
		public Confirmation.ConfirmationBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRole")
		public Confirmation.ConfirmationBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
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
		@RosettaAttribute("lineage")
		public Confirmation.ConfirmationBuilder setLineage(Lineage lineage) {
			this.lineage = lineage==null?null:lineage.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("status")
		public Confirmation.ConfirmationBuilder setStatus(ConfirmationStatusEnum status) {
			this.status = status==null?null:status;
			return this;
		}
		
		@Override
		public Confirmation build() {
			return new Confirmation.ConfirmationImpl(this);
		}
		
		@Override
		public Confirmation.ConfirmationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Confirmation.ConfirmationBuilder prune() {
			identifier = identifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			party = party.stream().filter(b->b!=null).<Party.PartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			partyRole = partyRole.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (lineage!=null && !lineage.prune().hasData()) lineage = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null && getIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getParty()!=null && getParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPartyRole()!=null && getPartyRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getLineage()!=null && getLineage().hasData()) return true;
			if (getStatus()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Confirmation.ConfirmationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Confirmation.ConfirmationBuilder o = (Confirmation.ConfirmationBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::getOrCreateIdentifier);
			merger.mergeRosetta(getParty(), o.getParty(), this::getOrCreateParty);
			merger.mergeRosetta(getPartyRole(), o.getPartyRole(), this::getOrCreatePartyRole);
			merger.mergeRosetta(getLineage(), o.getLineage(), this::setLineage);
			
			merger.mergeBasic(getStatus(), o.getStatus(), this::setStatus);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Confirmation _that = getType().cast(o);
		
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!Objects.equals(lineage, _that.getLineage())) return false;
			if (!Objects.equals(status, _that.getStatus())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (lineage != null ? lineage.hashCode() : 0);
			_result = 31 * _result + (status != null ? status.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConfirmationBuilder {" +
				"identifier=" + this.identifier + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"lineage=" + this.lineage + ", " +
				"status=" + this.status +
			'}';
		}
	}
}
