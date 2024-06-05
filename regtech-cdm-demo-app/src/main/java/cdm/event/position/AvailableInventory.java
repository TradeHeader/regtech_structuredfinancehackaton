package cdm.event.position;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.position.AvailableInventory;
import cdm.event.position.AvailableInventory.AvailableInventoryBuilder;
import cdm.event.position.AvailableInventory.AvailableInventoryBuilderImpl;
import cdm.event.position.AvailableInventory.AvailableInventoryImpl;
import cdm.event.position.AvailableInventoryRecord;
import cdm.event.position.meta.AvailableInventoryMeta;
import cdm.event.workflow.MessageInformation;
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
 * A data type that can be used to describe the inventory of securities that a party holds. The securities are held in the AvailableInventoryRecord, with each item in the array being an individual security and its associated criteria. Criteria can include the quantity available, the rate at which the security is available to borrow at, as well as other details that can affect the decision as to whether a party wants to utilise the securities listed.
 * @version ${project.version}
 */
@RosettaDataType(value="AvailableInventory", builder=AvailableInventory.AvailableInventoryBuilderImpl.class, version="${project.version}")
public interface AvailableInventory extends RosettaModelObject {

	AvailableInventoryMeta metaData = new AvailableInventoryMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Allows details related to the availability messaging use case to be defined
	 */
	MessageInformation getMessageInformation();
	/**
	 * Defines all parties involved for the list of inventory records in this set of inventory. For example, when used to describe securities lending availability, this could hold the sender of the availability, the intended recipient, the beneficial owner(s), the lender (which may differ from the sender as the lender may have the same piece of availability going through multiple agents), an agent or a venue.
	 */
	List<? extends Party> getParty();
	/**
	 * Defines the role(s) that party(ies) may have in relation to the inventory.
	 */
	List<? extends PartyRole> getPartyRole();
	/**
	 * An array holding the list of inventory being described. Each element in the inventoryRecord array represents an individual piece of inventory i.e. a security.
	 */
	List<? extends AvailableInventoryRecord> getAvailableInventoryRecord();

	/*********************** Build Methods  ***********************/
	AvailableInventory build();
	
	AvailableInventory.AvailableInventoryBuilder toBuilder();
	
	static AvailableInventory.AvailableInventoryBuilder builder() {
		return new AvailableInventory.AvailableInventoryBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AvailableInventory> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AvailableInventory> getType() {
		return AvailableInventory.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("messageInformation"), processor, MessageInformation.class, getMessageInformation());
		processRosetta(path.newSubPath("party"), processor, Party.class, getParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("availableInventoryRecord"), processor, AvailableInventoryRecord.class, getAvailableInventoryRecord());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AvailableInventoryBuilder extends AvailableInventory, RosettaModelObjectBuilder {
		MessageInformation.MessageInformationBuilder getOrCreateMessageInformation();
		MessageInformation.MessageInformationBuilder getMessageInformation();
		Party.PartyBuilder getOrCreateParty(int _index);
		List<? extends Party.PartyBuilder> getParty();
		PartyRole.PartyRoleBuilder getOrCreatePartyRole(int _index);
		List<? extends PartyRole.PartyRoleBuilder> getPartyRole();
		AvailableInventoryRecord.AvailableInventoryRecordBuilder getOrCreateAvailableInventoryRecord(int _index);
		List<? extends AvailableInventoryRecord.AvailableInventoryRecordBuilder> getAvailableInventoryRecord();
		AvailableInventory.AvailableInventoryBuilder setMessageInformation(MessageInformation messageInformation);
		AvailableInventory.AvailableInventoryBuilder addParty(Party party0);
		AvailableInventory.AvailableInventoryBuilder addParty(Party party1, int _idx);
		AvailableInventory.AvailableInventoryBuilder addParty(List<? extends Party> party2);
		AvailableInventory.AvailableInventoryBuilder setParty(List<? extends Party> party3);
		AvailableInventory.AvailableInventoryBuilder addPartyRole(PartyRole partyRole0);
		AvailableInventory.AvailableInventoryBuilder addPartyRole(PartyRole partyRole1, int _idx);
		AvailableInventory.AvailableInventoryBuilder addPartyRole(List<? extends PartyRole> partyRole2);
		AvailableInventory.AvailableInventoryBuilder setPartyRole(List<? extends PartyRole> partyRole3);
		AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(AvailableInventoryRecord availableInventoryRecord0);
		AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(AvailableInventoryRecord availableInventoryRecord1, int _idx);
		AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(List<? extends AvailableInventoryRecord> availableInventoryRecord2);
		AvailableInventory.AvailableInventoryBuilder setAvailableInventoryRecord(List<? extends AvailableInventoryRecord> availableInventoryRecord3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("messageInformation"), processor, MessageInformation.MessageInformationBuilder.class, getMessageInformation());
			processRosetta(path.newSubPath("party"), processor, Party.PartyBuilder.class, getParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("availableInventoryRecord"), processor, AvailableInventoryRecord.AvailableInventoryRecordBuilder.class, getAvailableInventoryRecord());
		}
		

		AvailableInventory.AvailableInventoryBuilder prune();
	}

	/*********************** Immutable Implementation of AvailableInventory  ***********************/
	class AvailableInventoryImpl implements AvailableInventory {
		private final MessageInformation messageInformation;
		private final List<? extends Party> party;
		private final List<? extends PartyRole> partyRole;
		private final List<? extends AvailableInventoryRecord> availableInventoryRecord;
		
		protected AvailableInventoryImpl(AvailableInventory.AvailableInventoryBuilder builder) {
			this.messageInformation = ofNullable(builder.getMessageInformation()).map(f->f.build()).orElse(null);
			this.party = ofNullable(builder.getParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.partyRole = ofNullable(builder.getPartyRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.availableInventoryRecord = ofNullable(builder.getAvailableInventoryRecord()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("messageInformation")
		public MessageInformation getMessageInformation() {
			return messageInformation;
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
		@RosettaAttribute("availableInventoryRecord")
		public List<? extends AvailableInventoryRecord> getAvailableInventoryRecord() {
			return availableInventoryRecord;
		}
		
		@Override
		public AvailableInventory build() {
			return this;
		}
		
		@Override
		public AvailableInventory.AvailableInventoryBuilder toBuilder() {
			AvailableInventory.AvailableInventoryBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AvailableInventory.AvailableInventoryBuilder builder) {
			ofNullable(getMessageInformation()).ifPresent(builder::setMessageInformation);
			ofNullable(getParty()).ifPresent(builder::setParty);
			ofNullable(getPartyRole()).ifPresent(builder::setPartyRole);
			ofNullable(getAvailableInventoryRecord()).ifPresent(builder::setAvailableInventoryRecord);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AvailableInventory _that = getType().cast(o);
		
			if (!Objects.equals(messageInformation, _that.getMessageInformation())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!ListEquals.listEquals(availableInventoryRecord, _that.getAvailableInventoryRecord())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (messageInformation != null ? messageInformation.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (availableInventoryRecord != null ? availableInventoryRecord.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AvailableInventory {" +
				"messageInformation=" + this.messageInformation + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"availableInventoryRecord=" + this.availableInventoryRecord +
			'}';
		}
	}

	/*********************** Builder Implementation of AvailableInventory  ***********************/
	class AvailableInventoryBuilderImpl implements AvailableInventory.AvailableInventoryBuilder {
	
		protected MessageInformation.MessageInformationBuilder messageInformation;
		protected List<Party.PartyBuilder> party = new ArrayList<>();
		protected List<PartyRole.PartyRoleBuilder> partyRole = new ArrayList<>();
		protected List<AvailableInventoryRecord.AvailableInventoryRecordBuilder> availableInventoryRecord = new ArrayList<>();
	
		public AvailableInventoryBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("messageInformation")
		public MessageInformation.MessageInformationBuilder getMessageInformation() {
			return messageInformation;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder getOrCreateMessageInformation() {
			MessageInformation.MessageInformationBuilder result;
			if (messageInformation!=null) {
				result = messageInformation;
			}
			else {
				result = messageInformation = MessageInformation.builder();
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
		@RosettaAttribute("availableInventoryRecord")
		public List<? extends AvailableInventoryRecord.AvailableInventoryRecordBuilder> getAvailableInventoryRecord() {
			return availableInventoryRecord;
		}
		
		public AvailableInventoryRecord.AvailableInventoryRecordBuilder getOrCreateAvailableInventoryRecord(int _index) {
		
			if (availableInventoryRecord==null) {
				this.availableInventoryRecord = new ArrayList<>();
			}
			AvailableInventoryRecord.AvailableInventoryRecordBuilder result;
			return getIndex(availableInventoryRecord, _index, () -> {
						AvailableInventoryRecord.AvailableInventoryRecordBuilder newAvailableInventoryRecord = AvailableInventoryRecord.builder();
						return newAvailableInventoryRecord;
					});
		}
		
	
		@Override
		@RosettaAttribute("messageInformation")
		public AvailableInventory.AvailableInventoryBuilder setMessageInformation(MessageInformation messageInformation) {
			this.messageInformation = messageInformation==null?null:messageInformation.toBuilder();
			return this;
		}
		@Override
		public AvailableInventory.AvailableInventoryBuilder addParty(Party party) {
			if (party!=null) this.party.add(party.toBuilder());
			return this;
		}
		
		@Override
		public AvailableInventory.AvailableInventoryBuilder addParty(Party party, int _idx) {
			getIndex(this.party, _idx, () -> party.toBuilder());
			return this;
		}
		@Override 
		public AvailableInventory.AvailableInventoryBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("party")
		public AvailableInventory.AvailableInventoryBuilder setParty(List<? extends Party> partys) {
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
		public AvailableInventory.AvailableInventoryBuilder addPartyRole(PartyRole partyRole) {
			if (partyRole!=null) this.partyRole.add(partyRole.toBuilder());
			return this;
		}
		
		@Override
		public AvailableInventory.AvailableInventoryBuilder addPartyRole(PartyRole partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> partyRole.toBuilder());
			return this;
		}
		@Override 
		public AvailableInventory.AvailableInventoryBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyRole")
		public AvailableInventory.AvailableInventoryBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
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
		public AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(AvailableInventoryRecord availableInventoryRecord) {
			if (availableInventoryRecord!=null) this.availableInventoryRecord.add(availableInventoryRecord.toBuilder());
			return this;
		}
		
		@Override
		public AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(AvailableInventoryRecord availableInventoryRecord, int _idx) {
			getIndex(this.availableInventoryRecord, _idx, () -> availableInventoryRecord.toBuilder());
			return this;
		}
		@Override 
		public AvailableInventory.AvailableInventoryBuilder addAvailableInventoryRecord(List<? extends AvailableInventoryRecord> availableInventoryRecords) {
			if (availableInventoryRecords != null) {
				for (AvailableInventoryRecord toAdd : availableInventoryRecords) {
					this.availableInventoryRecord.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("availableInventoryRecord")
		public AvailableInventory.AvailableInventoryBuilder setAvailableInventoryRecord(List<? extends AvailableInventoryRecord> availableInventoryRecords) {
			if (availableInventoryRecords == null)  {
				this.availableInventoryRecord = new ArrayList<>();
			}
			else {
				this.availableInventoryRecord = availableInventoryRecords.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public AvailableInventory build() {
			return new AvailableInventory.AvailableInventoryImpl(this);
		}
		
		@Override
		public AvailableInventory.AvailableInventoryBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AvailableInventory.AvailableInventoryBuilder prune() {
			if (messageInformation!=null && !messageInformation.prune().hasData()) messageInformation = null;
			party = party.stream().filter(b->b!=null).<Party.PartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			partyRole = partyRole.stream().filter(b->b!=null).<PartyRole.PartyRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			availableInventoryRecord = availableInventoryRecord.stream().filter(b->b!=null).<AvailableInventoryRecord.AvailableInventoryRecordBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMessageInformation()!=null && getMessageInformation().hasData()) return true;
			if (getParty()!=null && getParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPartyRole()!=null && getPartyRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAvailableInventoryRecord()!=null && getAvailableInventoryRecord().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AvailableInventory.AvailableInventoryBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AvailableInventory.AvailableInventoryBuilder o = (AvailableInventory.AvailableInventoryBuilder) other;
			
			merger.mergeRosetta(getMessageInformation(), o.getMessageInformation(), this::setMessageInformation);
			merger.mergeRosetta(getParty(), o.getParty(), this::getOrCreateParty);
			merger.mergeRosetta(getPartyRole(), o.getPartyRole(), this::getOrCreatePartyRole);
			merger.mergeRosetta(getAvailableInventoryRecord(), o.getAvailableInventoryRecord(), this::getOrCreateAvailableInventoryRecord);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AvailableInventory _that = getType().cast(o);
		
			if (!Objects.equals(messageInformation, _that.getMessageInformation())) return false;
			if (!ListEquals.listEquals(party, _that.getParty())) return false;
			if (!ListEquals.listEquals(partyRole, _that.getPartyRole())) return false;
			if (!ListEquals.listEquals(availableInventoryRecord, _that.getAvailableInventoryRecord())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (messageInformation != null ? messageInformation.hashCode() : 0);
			_result = 31 * _result + (party != null ? party.hashCode() : 0);
			_result = 31 * _result + (partyRole != null ? partyRole.hashCode() : 0);
			_result = 31 * _result + (availableInventoryRecord != null ? availableInventoryRecord.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AvailableInventoryBuilder {" +
				"messageInformation=" + this.messageInformation + ", " +
				"party=" + this.party + ", " +
				"partyRole=" + this.partyRole + ", " +
				"availableInventoryRecord=" + this.availableInventoryRecord +
			'}';
		}
	}
}
