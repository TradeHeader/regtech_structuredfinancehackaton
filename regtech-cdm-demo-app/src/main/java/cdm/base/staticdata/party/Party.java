package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.Party.PartyBuilder;
import cdm.base.staticdata.party.Party.PartyBuilderImpl;
import cdm.base.staticdata.party.Party.PartyImpl;
import cdm.base.staticdata.party.PartyIdentifier;
import cdm.base.staticdata.party.meta.PartyMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify a party, without a qualification as to whether this party is a legal entity or a natural person, although the model provides the ability to associate a person (or set of persons) to a party, which use case would imply that such party would be a legal entity (even if not formally specified as such). 
 * @version ${project.version}
 */
@RosettaDataType(value="Party", builder=Party.PartyBuilderImpl.class, version="${project.version}")
public interface Party extends RosettaModelObject, GlobalKey {

	PartyMeta metaData = new PartyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The identifier associated with a party, e.g. the 20 digits LEI code.
	 */
	List<? extends PartyIdentifier> getPartyId();
	/**
	 * The party name.
	 */
	FieldWithMetaString getName();
	/**
	 * Optional organization unit information used to describe the organization units (e.g. trading desks) involved in a transaction or business process, incl. the contact information (when relevant).
	 */
	List<? extends BusinessUnit> getBusinessUnit();
	/**
	 * The person(s) who might be associated with the party as part of the execution, contract or legal document.
	 */
	List<? extends NaturalPerson> getPerson();
	/**
	 * The role of the person(s) 
	 */
	List<? extends NaturalPersonRole> getPersonRole();
	/**
	 * The account that might be associated with the party. At most one account can be specified, as it is expected that this information is used in the context of a contract or legal document where only one account per party can be associated with such object.
	 */
	Account getAccount();
	/**
	 * The postal/street address, telephone number, email address and/or web page. If the contact information is specific to the associated business unit(s) or person (s), it should be associated with those.
	 */
	ContactInformation getContactInformation();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Party build();
	
	Party.PartyBuilder toBuilder();
	
	static Party.PartyBuilder builder() {
		return new Party.PartyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Party> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Party> getType() {
		return Party.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyId"), processor, PartyIdentifier.class, getPartyId());
		processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.class, getName());
		processRosetta(path.newSubPath("businessUnit"), processor, BusinessUnit.class, getBusinessUnit());
		processRosetta(path.newSubPath("person"), processor, NaturalPerson.class, getPerson());
		processRosetta(path.newSubPath("personRole"), processor, NaturalPersonRole.class, getPersonRole());
		processRosetta(path.newSubPath("account"), processor, Account.class, getAccount());
		processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.class, getContactInformation());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyBuilder extends Party, RosettaModelObjectBuilder {
		PartyIdentifier.PartyIdentifierBuilder getOrCreatePartyId(int _index);
		List<? extends PartyIdentifier.PartyIdentifierBuilder> getPartyId();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateName();
		FieldWithMetaString.FieldWithMetaStringBuilder getName();
		BusinessUnit.BusinessUnitBuilder getOrCreateBusinessUnit(int _index);
		List<? extends BusinessUnit.BusinessUnitBuilder> getBusinessUnit();
		NaturalPerson.NaturalPersonBuilder getOrCreatePerson(int _index);
		List<? extends NaturalPerson.NaturalPersonBuilder> getPerson();
		NaturalPersonRole.NaturalPersonRoleBuilder getOrCreatePersonRole(int _index);
		List<? extends NaturalPersonRole.NaturalPersonRoleBuilder> getPersonRole();
		Account.AccountBuilder getOrCreateAccount();
		Account.AccountBuilder getAccount();
		ContactInformation.ContactInformationBuilder getOrCreateContactInformation();
		ContactInformation.ContactInformationBuilder getContactInformation();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Party.PartyBuilder addPartyId(PartyIdentifier partyId0);
		Party.PartyBuilder addPartyId(PartyIdentifier partyId1, int _idx);
		Party.PartyBuilder addPartyId(List<? extends PartyIdentifier> partyId2);
		Party.PartyBuilder setPartyId(List<? extends PartyIdentifier> partyId3);
		Party.PartyBuilder setName(FieldWithMetaString name0);
		Party.PartyBuilder setNameValue(String name1);
		Party.PartyBuilder addBusinessUnit(BusinessUnit businessUnit0);
		Party.PartyBuilder addBusinessUnit(BusinessUnit businessUnit1, int _idx);
		Party.PartyBuilder addBusinessUnit(List<? extends BusinessUnit> businessUnit2);
		Party.PartyBuilder setBusinessUnit(List<? extends BusinessUnit> businessUnit3);
		Party.PartyBuilder addPerson(NaturalPerson person0);
		Party.PartyBuilder addPerson(NaturalPerson person1, int _idx);
		Party.PartyBuilder addPerson(List<? extends NaturalPerson> person2);
		Party.PartyBuilder setPerson(List<? extends NaturalPerson> person3);
		Party.PartyBuilder addPersonRole(NaturalPersonRole personRole0);
		Party.PartyBuilder addPersonRole(NaturalPersonRole personRole1, int _idx);
		Party.PartyBuilder addPersonRole(List<? extends NaturalPersonRole> personRole2);
		Party.PartyBuilder setPersonRole(List<? extends NaturalPersonRole> personRole3);
		Party.PartyBuilder setAccount(Account account);
		Party.PartyBuilder setContactInformation(ContactInformation contactInformation);
		Party.PartyBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyId"), processor, PartyIdentifier.PartyIdentifierBuilder.class, getPartyId());
			processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getName());
			processRosetta(path.newSubPath("businessUnit"), processor, BusinessUnit.BusinessUnitBuilder.class, getBusinessUnit());
			processRosetta(path.newSubPath("person"), processor, NaturalPerson.NaturalPersonBuilder.class, getPerson());
			processRosetta(path.newSubPath("personRole"), processor, NaturalPersonRole.NaturalPersonRoleBuilder.class, getPersonRole());
			processRosetta(path.newSubPath("account"), processor, Account.AccountBuilder.class, getAccount());
			processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.ContactInformationBuilder.class, getContactInformation());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Party.PartyBuilder prune();
	}

	/*********************** Immutable Implementation of Party  ***********************/
	class PartyImpl implements Party {
		private final List<? extends PartyIdentifier> partyId;
		private final FieldWithMetaString name;
		private final List<? extends BusinessUnit> businessUnit;
		private final List<? extends NaturalPerson> person;
		private final List<? extends NaturalPersonRole> personRole;
		private final Account account;
		private final ContactInformation contactInformation;
		private final MetaFields meta;
		
		protected PartyImpl(Party.PartyBuilder builder) {
			this.partyId = ofNullable(builder.getPartyId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.name = ofNullable(builder.getName()).map(f->f.build()).orElse(null);
			this.businessUnit = ofNullable(builder.getBusinessUnit()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.person = ofNullable(builder.getPerson()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.personRole = ofNullable(builder.getPersonRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.account = ofNullable(builder.getAccount()).map(f->f.build()).orElse(null);
			this.contactInformation = ofNullable(builder.getContactInformation()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("partyId")
		public List<? extends PartyIdentifier> getPartyId() {
			return partyId;
		}
		
		@Override
		@RosettaAttribute("name")
		public FieldWithMetaString getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("businessUnit")
		public List<? extends BusinessUnit> getBusinessUnit() {
			return businessUnit;
		}
		
		@Override
		@RosettaAttribute("person")
		public List<? extends NaturalPerson> getPerson() {
			return person;
		}
		
		@Override
		@RosettaAttribute("personRole")
		public List<? extends NaturalPersonRole> getPersonRole() {
			return personRole;
		}
		
		@Override
		@RosettaAttribute("account")
		public Account getAccount() {
			return account;
		}
		
		@Override
		@RosettaAttribute("contactInformation")
		public ContactInformation getContactInformation() {
			return contactInformation;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Party build() {
			return this;
		}
		
		@Override
		public Party.PartyBuilder toBuilder() {
			Party.PartyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Party.PartyBuilder builder) {
			ofNullable(getPartyId()).ifPresent(builder::setPartyId);
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getBusinessUnit()).ifPresent(builder::setBusinessUnit);
			ofNullable(getPerson()).ifPresent(builder::setPerson);
			ofNullable(getPersonRole()).ifPresent(builder::setPersonRole);
			ofNullable(getAccount()).ifPresent(builder::setAccount);
			ofNullable(getContactInformation()).ifPresent(builder::setContactInformation);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Party _that = getType().cast(o);
		
			if (!ListEquals.listEquals(partyId, _that.getPartyId())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!ListEquals.listEquals(businessUnit, _that.getBusinessUnit())) return false;
			if (!ListEquals.listEquals(person, _that.getPerson())) return false;
			if (!ListEquals.listEquals(personRole, _that.getPersonRole())) return false;
			if (!Objects.equals(account, _that.getAccount())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyId != null ? partyId.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (businessUnit != null ? businessUnit.hashCode() : 0);
			_result = 31 * _result + (person != null ? person.hashCode() : 0);
			_result = 31 * _result + (personRole != null ? personRole.hashCode() : 0);
			_result = 31 * _result + (account != null ? account.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Party {" +
				"partyId=" + this.partyId + ", " +
				"name=" + this.name + ", " +
				"businessUnit=" + this.businessUnit + ", " +
				"person=" + this.person + ", " +
				"personRole=" + this.personRole + ", " +
				"account=" + this.account + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Party  ***********************/
	class PartyBuilderImpl implements Party.PartyBuilder, GlobalKeyBuilder {
	
		protected List<PartyIdentifier.PartyIdentifierBuilder> partyId = new ArrayList<>();
		protected FieldWithMetaString.FieldWithMetaStringBuilder name;
		protected List<BusinessUnit.BusinessUnitBuilder> businessUnit = new ArrayList<>();
		protected List<NaturalPerson.NaturalPersonBuilder> person = new ArrayList<>();
		protected List<NaturalPersonRole.NaturalPersonRoleBuilder> personRole = new ArrayList<>();
		protected Account.AccountBuilder account;
		protected ContactInformation.ContactInformationBuilder contactInformation;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PartyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("partyId")
		public List<? extends PartyIdentifier.PartyIdentifierBuilder> getPartyId() {
			return partyId;
		}
		
		public PartyIdentifier.PartyIdentifierBuilder getOrCreatePartyId(int _index) {
		
			if (partyId==null) {
				this.partyId = new ArrayList<>();
			}
			PartyIdentifier.PartyIdentifierBuilder result;
			return getIndex(partyId, _index, () -> {
						PartyIdentifier.PartyIdentifierBuilder newPartyId = PartyIdentifier.builder();
						return newPartyId;
					});
		}
		
		@Override
		@RosettaAttribute("name")
		public FieldWithMetaString.FieldWithMetaStringBuilder getName() {
			return name;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateName() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (name!=null) {
				result = name;
			}
			else {
				result = name = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("businessUnit")
		public List<? extends BusinessUnit.BusinessUnitBuilder> getBusinessUnit() {
			return businessUnit;
		}
		
		public BusinessUnit.BusinessUnitBuilder getOrCreateBusinessUnit(int _index) {
		
			if (businessUnit==null) {
				this.businessUnit = new ArrayList<>();
			}
			BusinessUnit.BusinessUnitBuilder result;
			return getIndex(businessUnit, _index, () -> {
						BusinessUnit.BusinessUnitBuilder newBusinessUnit = BusinessUnit.builder();
						return newBusinessUnit;
					});
		}
		
		@Override
		@RosettaAttribute("person")
		public List<? extends NaturalPerson.NaturalPersonBuilder> getPerson() {
			return person;
		}
		
		public NaturalPerson.NaturalPersonBuilder getOrCreatePerson(int _index) {
		
			if (person==null) {
				this.person = new ArrayList<>();
			}
			NaturalPerson.NaturalPersonBuilder result;
			return getIndex(person, _index, () -> {
						NaturalPerson.NaturalPersonBuilder newPerson = NaturalPerson.builder();
						return newPerson;
					});
		}
		
		@Override
		@RosettaAttribute("personRole")
		public List<? extends NaturalPersonRole.NaturalPersonRoleBuilder> getPersonRole() {
			return personRole;
		}
		
		public NaturalPersonRole.NaturalPersonRoleBuilder getOrCreatePersonRole(int _index) {
		
			if (personRole==null) {
				this.personRole = new ArrayList<>();
			}
			NaturalPersonRole.NaturalPersonRoleBuilder result;
			return getIndex(personRole, _index, () -> {
						NaturalPersonRole.NaturalPersonRoleBuilder newPersonRole = NaturalPersonRole.builder();
						return newPersonRole;
					});
		}
		
		@Override
		@RosettaAttribute("account")
		public Account.AccountBuilder getAccount() {
			return account;
		}
		
		@Override
		public Account.AccountBuilder getOrCreateAccount() {
			Account.AccountBuilder result;
			if (account!=null) {
				result = account;
			}
			else {
				result = account = Account.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("contactInformation")
		public ContactInformation.ContactInformationBuilder getContactInformation() {
			return contactInformation;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder getOrCreateContactInformation() {
			ContactInformation.ContactInformationBuilder result;
			if (contactInformation!=null) {
				result = contactInformation;
			}
			else {
				result = contactInformation = ContactInformation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		public Party.PartyBuilder addPartyId(PartyIdentifier partyId) {
			if (partyId!=null) this.partyId.add(partyId.toBuilder());
			return this;
		}
		
		@Override
		public Party.PartyBuilder addPartyId(PartyIdentifier partyId, int _idx) {
			getIndex(this.partyId, _idx, () -> partyId.toBuilder());
			return this;
		}
		@Override 
		public Party.PartyBuilder addPartyId(List<? extends PartyIdentifier> partyIds) {
			if (partyIds != null) {
				for (PartyIdentifier toAdd : partyIds) {
					this.partyId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyId")
		public Party.PartyBuilder setPartyId(List<? extends PartyIdentifier> partyIds) {
			if (partyIds == null)  {
				this.partyId = new ArrayList<>();
			}
			else {
				this.partyId = partyIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("name")
		public Party.PartyBuilder setName(FieldWithMetaString name) {
			this.name = name==null?null:name.toBuilder();
			return this;
		}
		@Override
		public Party.PartyBuilder setNameValue(String name) {
			this.getOrCreateName().setValue(name);
			return this;
		}
		@Override
		public Party.PartyBuilder addBusinessUnit(BusinessUnit businessUnit) {
			if (businessUnit!=null) this.businessUnit.add(businessUnit.toBuilder());
			return this;
		}
		
		@Override
		public Party.PartyBuilder addBusinessUnit(BusinessUnit businessUnit, int _idx) {
			getIndex(this.businessUnit, _idx, () -> businessUnit.toBuilder());
			return this;
		}
		@Override 
		public Party.PartyBuilder addBusinessUnit(List<? extends BusinessUnit> businessUnits) {
			if (businessUnits != null) {
				for (BusinessUnit toAdd : businessUnits) {
					this.businessUnit.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("businessUnit")
		public Party.PartyBuilder setBusinessUnit(List<? extends BusinessUnit> businessUnits) {
			if (businessUnits == null)  {
				this.businessUnit = new ArrayList<>();
			}
			else {
				this.businessUnit = businessUnits.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Party.PartyBuilder addPerson(NaturalPerson person) {
			if (person!=null) this.person.add(person.toBuilder());
			return this;
		}
		
		@Override
		public Party.PartyBuilder addPerson(NaturalPerson person, int _idx) {
			getIndex(this.person, _idx, () -> person.toBuilder());
			return this;
		}
		@Override 
		public Party.PartyBuilder addPerson(List<? extends NaturalPerson> persons) {
			if (persons != null) {
				for (NaturalPerson toAdd : persons) {
					this.person.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("person")
		public Party.PartyBuilder setPerson(List<? extends NaturalPerson> persons) {
			if (persons == null)  {
				this.person = new ArrayList<>();
			}
			else {
				this.person = persons.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Party.PartyBuilder addPersonRole(NaturalPersonRole personRole) {
			if (personRole!=null) this.personRole.add(personRole.toBuilder());
			return this;
		}
		
		@Override
		public Party.PartyBuilder addPersonRole(NaturalPersonRole personRole, int _idx) {
			getIndex(this.personRole, _idx, () -> personRole.toBuilder());
			return this;
		}
		@Override 
		public Party.PartyBuilder addPersonRole(List<? extends NaturalPersonRole> personRoles) {
			if (personRoles != null) {
				for (NaturalPersonRole toAdd : personRoles) {
					this.personRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("personRole")
		public Party.PartyBuilder setPersonRole(List<? extends NaturalPersonRole> personRoles) {
			if (personRoles == null)  {
				this.personRole = new ArrayList<>();
			}
			else {
				this.personRole = personRoles.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("account")
		public Party.PartyBuilder setAccount(Account account) {
			this.account = account==null?null:account.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("contactInformation")
		public Party.PartyBuilder setContactInformation(ContactInformation contactInformation) {
			this.contactInformation = contactInformation==null?null:contactInformation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Party.PartyBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Party build() {
			return new Party.PartyImpl(this);
		}
		
		@Override
		public Party.PartyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Party.PartyBuilder prune() {
			partyId = partyId.stream().filter(b->b!=null).<PartyIdentifier.PartyIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (name!=null && !name.prune().hasData()) name = null;
			businessUnit = businessUnit.stream().filter(b->b!=null).<BusinessUnit.BusinessUnitBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			person = person.stream().filter(b->b!=null).<NaturalPerson.NaturalPersonBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			personRole = personRole.stream().filter(b->b!=null).<NaturalPersonRole.NaturalPersonRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (account!=null && !account.prune().hasData()) account = null;
			if (contactInformation!=null && !contactInformation.prune().hasData()) contactInformation = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyId()!=null && getPartyId().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getName()!=null) return true;
			if (getBusinessUnit()!=null && getBusinessUnit().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPerson()!=null && getPerson().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPersonRole()!=null && getPersonRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAccount()!=null && getAccount().hasData()) return true;
			if (getContactInformation()!=null && getContactInformation().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Party.PartyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Party.PartyBuilder o = (Party.PartyBuilder) other;
			
			merger.mergeRosetta(getPartyId(), o.getPartyId(), this::getOrCreatePartyId);
			merger.mergeRosetta(getName(), o.getName(), this::setName);
			merger.mergeRosetta(getBusinessUnit(), o.getBusinessUnit(), this::getOrCreateBusinessUnit);
			merger.mergeRosetta(getPerson(), o.getPerson(), this::getOrCreatePerson);
			merger.mergeRosetta(getPersonRole(), o.getPersonRole(), this::getOrCreatePersonRole);
			merger.mergeRosetta(getAccount(), o.getAccount(), this::setAccount);
			merger.mergeRosetta(getContactInformation(), o.getContactInformation(), this::setContactInformation);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Party _that = getType().cast(o);
		
			if (!ListEquals.listEquals(partyId, _that.getPartyId())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!ListEquals.listEquals(businessUnit, _that.getBusinessUnit())) return false;
			if (!ListEquals.listEquals(person, _that.getPerson())) return false;
			if (!ListEquals.listEquals(personRole, _that.getPersonRole())) return false;
			if (!Objects.equals(account, _that.getAccount())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyId != null ? partyId.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (businessUnit != null ? businessUnit.hashCode() : 0);
			_result = 31 * _result + (person != null ? person.hashCode() : 0);
			_result = 31 * _result + (personRole != null ? personRole.hashCode() : 0);
			_result = 31 * _result + (account != null ? account.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyBuilder {" +
				"partyId=" + this.partyId + ", " +
				"name=" + this.name + ", " +
				"businessUnit=" + this.businessUnit + ", " +
				"person=" + this.person + ", " +
				"personRole=" + this.personRole + ", " +
				"account=" + this.account + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
