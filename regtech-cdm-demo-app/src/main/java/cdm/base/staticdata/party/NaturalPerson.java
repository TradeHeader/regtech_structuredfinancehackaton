package cdm.base.staticdata.party;

import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.NaturalPerson.NaturalPersonBuilder;
import cdm.base.staticdata.party.NaturalPerson.NaturalPersonBuilderImpl;
import cdm.base.staticdata.party.NaturalPerson.NaturalPersonImpl;
import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.PersonIdentifier;
import cdm.base.staticdata.party.meta.NaturalPersonMeta;
import cdm.base.staticdata.party.metafields.FieldWithMetaPersonIdentifier;
import cdm.base.staticdata.party.metafields.FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to represent the attributes that are specific to a natural person.
 * @version ${project.version}
 */
@RosettaDataType(value="NaturalPerson", builder=NaturalPerson.NaturalPersonBuilderImpl.class, version="${project.version}")
public interface NaturalPerson extends RosettaModelObject, GlobalKey {

	NaturalPersonMeta metaData = new NaturalPersonMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The identifier associated with a person, e.g. the internal identification code.
	 */
	List<? extends FieldWithMetaPersonIdentifier> getPersonId();
	/**
	 * An honorific title, such as Mr., Ms., Dr. etc.
	 */
	String getHonorific();
	/**
	 * The natural person&#39;s first name. It is optional in FpML.
	 */
	String getFirstName();
	/**
	 * The natural person&#39;s middle name(s). If a middle name is provided then an initial should be absent.
	 */
	List<String> getMiddleName();
	/**
	 * The natural person&#39;s middle initial(s). If a middle initial is provided then a name should be absent.
	 */
	List<String> getInitial();
	/**
	 * The natural person&#39;s surname.
	 */
	String getSurname();
	/**
	 * Name suffix, such as Jr., III, etc.
	 */
	String getSuffix();
	/**
	 * The natural person&#39;s date of birth.
	 */
	Date getDateOfBirth();
	/**
	 * The contact information for such person, when different from the contact information associated with the party.
	 */
	ContactInformation getContactInformation();
	/**
	 * The role of the person(s) 
	 */
	List<? extends NaturalPersonRole> getPersonRole();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	NaturalPerson build();
	
	NaturalPerson.NaturalPersonBuilder toBuilder();
	
	static NaturalPerson.NaturalPersonBuilder builder() {
		return new NaturalPerson.NaturalPersonBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NaturalPerson> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NaturalPerson> getType() {
		return NaturalPerson.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("personId"), processor, FieldWithMetaPersonIdentifier.class, getPersonId());
		processor.processBasic(path.newSubPath("honorific"), String.class, getHonorific(), this);
		processor.processBasic(path.newSubPath("firstName"), String.class, getFirstName(), this);
		processor.processBasic(path.newSubPath("middleName"), String.class, getMiddleName(), this);
		processor.processBasic(path.newSubPath("initial"), String.class, getInitial(), this);
		processor.processBasic(path.newSubPath("surname"), String.class, getSurname(), this);
		processor.processBasic(path.newSubPath("suffix"), String.class, getSuffix(), this);
		processor.processBasic(path.newSubPath("dateOfBirth"), Date.class, getDateOfBirth(), this);
		processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.class, getContactInformation());
		processRosetta(path.newSubPath("personRole"), processor, NaturalPersonRole.class, getPersonRole());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NaturalPersonBuilder extends NaturalPerson, RosettaModelObjectBuilder {
		FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder getOrCreatePersonId(int _index);
		List<? extends FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder> getPersonId();
		ContactInformation.ContactInformationBuilder getOrCreateContactInformation();
		ContactInformation.ContactInformationBuilder getContactInformation();
		NaturalPersonRole.NaturalPersonRoleBuilder getOrCreatePersonRole(int _index);
		List<? extends NaturalPersonRole.NaturalPersonRoleBuilder> getPersonRole();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		NaturalPerson.NaturalPersonBuilder addPersonId(FieldWithMetaPersonIdentifier personId0);
		NaturalPerson.NaturalPersonBuilder addPersonId(FieldWithMetaPersonIdentifier personId1, int _idx);
		NaturalPerson.NaturalPersonBuilder addPersonIdValue(PersonIdentifier personId2);
		NaturalPerson.NaturalPersonBuilder addPersonIdValue(PersonIdentifier personId3, int _idx);
		NaturalPerson.NaturalPersonBuilder addPersonId(List<? extends FieldWithMetaPersonIdentifier> personId4);
		NaturalPerson.NaturalPersonBuilder setPersonId(List<? extends FieldWithMetaPersonIdentifier> personId5);
		NaturalPerson.NaturalPersonBuilder addPersonIdValue(List<? extends PersonIdentifier> personId6);
		NaturalPerson.NaturalPersonBuilder setPersonIdValue(List<? extends PersonIdentifier> personId7);
		NaturalPerson.NaturalPersonBuilder setHonorific(String honorific);
		NaturalPerson.NaturalPersonBuilder setFirstName(String firstName);
		NaturalPerson.NaturalPersonBuilder addMiddleName(String middleName0);
		NaturalPerson.NaturalPersonBuilder addMiddleName(String middleName1, int _idx);
		NaturalPerson.NaturalPersonBuilder addMiddleName(List<? extends String> middleName2);
		NaturalPerson.NaturalPersonBuilder setMiddleName(List<? extends String> middleName3);
		NaturalPerson.NaturalPersonBuilder addInitial(String initial0);
		NaturalPerson.NaturalPersonBuilder addInitial(String initial1, int _idx);
		NaturalPerson.NaturalPersonBuilder addInitial(List<? extends String> initial2);
		NaturalPerson.NaturalPersonBuilder setInitial(List<? extends String> initial3);
		NaturalPerson.NaturalPersonBuilder setSurname(String surname);
		NaturalPerson.NaturalPersonBuilder setSuffix(String suffix);
		NaturalPerson.NaturalPersonBuilder setDateOfBirth(Date dateOfBirth);
		NaturalPerson.NaturalPersonBuilder setContactInformation(ContactInformation contactInformation);
		NaturalPerson.NaturalPersonBuilder addPersonRole(NaturalPersonRole personRole0);
		NaturalPerson.NaturalPersonBuilder addPersonRole(NaturalPersonRole personRole1, int _idx);
		NaturalPerson.NaturalPersonBuilder addPersonRole(List<? extends NaturalPersonRole> personRole2);
		NaturalPerson.NaturalPersonBuilder setPersonRole(List<? extends NaturalPersonRole> personRole3);
		NaturalPerson.NaturalPersonBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("personId"), processor, FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder.class, getPersonId());
			processor.processBasic(path.newSubPath("honorific"), String.class, getHonorific(), this);
			processor.processBasic(path.newSubPath("firstName"), String.class, getFirstName(), this);
			processor.processBasic(path.newSubPath("middleName"), String.class, getMiddleName(), this);
			processor.processBasic(path.newSubPath("initial"), String.class, getInitial(), this);
			processor.processBasic(path.newSubPath("surname"), String.class, getSurname(), this);
			processor.processBasic(path.newSubPath("suffix"), String.class, getSuffix(), this);
			processor.processBasic(path.newSubPath("dateOfBirth"), Date.class, getDateOfBirth(), this);
			processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.ContactInformationBuilder.class, getContactInformation());
			processRosetta(path.newSubPath("personRole"), processor, NaturalPersonRole.NaturalPersonRoleBuilder.class, getPersonRole());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		NaturalPerson.NaturalPersonBuilder prune();
	}

	/*********************** Immutable Implementation of NaturalPerson  ***********************/
	class NaturalPersonImpl implements NaturalPerson {
		private final List<? extends FieldWithMetaPersonIdentifier> personId;
		private final String honorific;
		private final String firstName;
		private final List<String> middleName;
		private final List<String> initial;
		private final String surname;
		private final String suffix;
		private final Date dateOfBirth;
		private final ContactInformation contactInformation;
		private final List<? extends NaturalPersonRole> personRole;
		private final MetaFields meta;
		
		protected NaturalPersonImpl(NaturalPerson.NaturalPersonBuilder builder) {
			this.personId = ofNullable(builder.getPersonId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.honorific = builder.getHonorific();
			this.firstName = builder.getFirstName();
			this.middleName = ofNullable(builder.getMiddleName()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.initial = ofNullable(builder.getInitial()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.surname = builder.getSurname();
			this.suffix = builder.getSuffix();
			this.dateOfBirth = builder.getDateOfBirth();
			this.contactInformation = ofNullable(builder.getContactInformation()).map(f->f.build()).orElse(null);
			this.personRole = ofNullable(builder.getPersonRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("personId")
		public List<? extends FieldWithMetaPersonIdentifier> getPersonId() {
			return personId;
		}
		
		@Override
		@RosettaAttribute("honorific")
		public String getHonorific() {
			return honorific;
		}
		
		@Override
		@RosettaAttribute("firstName")
		public String getFirstName() {
			return firstName;
		}
		
		@Override
		@RosettaAttribute("middleName")
		public List<String> getMiddleName() {
			return middleName;
		}
		
		@Override
		@RosettaAttribute("initial")
		public List<String> getInitial() {
			return initial;
		}
		
		@Override
		@RosettaAttribute("surname")
		public String getSurname() {
			return surname;
		}
		
		@Override
		@RosettaAttribute("suffix")
		public String getSuffix() {
			return suffix;
		}
		
		@Override
		@RosettaAttribute("dateOfBirth")
		public Date getDateOfBirth() {
			return dateOfBirth;
		}
		
		@Override
		@RosettaAttribute("contactInformation")
		public ContactInformation getContactInformation() {
			return contactInformation;
		}
		
		@Override
		@RosettaAttribute("personRole")
		public List<? extends NaturalPersonRole> getPersonRole() {
			return personRole;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public NaturalPerson build() {
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder toBuilder() {
			NaturalPerson.NaturalPersonBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NaturalPerson.NaturalPersonBuilder builder) {
			ofNullable(getPersonId()).ifPresent(builder::setPersonId);
			ofNullable(getHonorific()).ifPresent(builder::setHonorific);
			ofNullable(getFirstName()).ifPresent(builder::setFirstName);
			ofNullable(getMiddleName()).ifPresent(builder::setMiddleName);
			ofNullable(getInitial()).ifPresent(builder::setInitial);
			ofNullable(getSurname()).ifPresent(builder::setSurname);
			ofNullable(getSuffix()).ifPresent(builder::setSuffix);
			ofNullable(getDateOfBirth()).ifPresent(builder::setDateOfBirth);
			ofNullable(getContactInformation()).ifPresent(builder::setContactInformation);
			ofNullable(getPersonRole()).ifPresent(builder::setPersonRole);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NaturalPerson _that = getType().cast(o);
		
			if (!ListEquals.listEquals(personId, _that.getPersonId())) return false;
			if (!Objects.equals(honorific, _that.getHonorific())) return false;
			if (!Objects.equals(firstName, _that.getFirstName())) return false;
			if (!ListEquals.listEquals(middleName, _that.getMiddleName())) return false;
			if (!ListEquals.listEquals(initial, _that.getInitial())) return false;
			if (!Objects.equals(surname, _that.getSurname())) return false;
			if (!Objects.equals(suffix, _that.getSuffix())) return false;
			if (!Objects.equals(dateOfBirth, _that.getDateOfBirth())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!ListEquals.listEquals(personRole, _that.getPersonRole())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (personId != null ? personId.hashCode() : 0);
			_result = 31 * _result + (honorific != null ? honorific.hashCode() : 0);
			_result = 31 * _result + (firstName != null ? firstName.hashCode() : 0);
			_result = 31 * _result + (middleName != null ? middleName.hashCode() : 0);
			_result = 31 * _result + (initial != null ? initial.hashCode() : 0);
			_result = 31 * _result + (surname != null ? surname.hashCode() : 0);
			_result = 31 * _result + (suffix != null ? suffix.hashCode() : 0);
			_result = 31 * _result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (personRole != null ? personRole.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NaturalPerson {" +
				"personId=" + this.personId + ", " +
				"honorific=" + this.honorific + ", " +
				"firstName=" + this.firstName + ", " +
				"middleName=" + this.middleName + ", " +
				"initial=" + this.initial + ", " +
				"surname=" + this.surname + ", " +
				"suffix=" + this.suffix + ", " +
				"dateOfBirth=" + this.dateOfBirth + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"personRole=" + this.personRole + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of NaturalPerson  ***********************/
	class NaturalPersonBuilderImpl implements NaturalPerson.NaturalPersonBuilder, GlobalKeyBuilder {
	
		protected List<FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder> personId = new ArrayList<>();
		protected String honorific;
		protected String firstName;
		protected List<String> middleName = new ArrayList<>();
		protected List<String> initial = new ArrayList<>();
		protected String surname;
		protected String suffix;
		protected Date dateOfBirth;
		protected ContactInformation.ContactInformationBuilder contactInformation;
		protected List<NaturalPersonRole.NaturalPersonRoleBuilder> personRole = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public NaturalPersonBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("personId")
		public List<? extends FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder> getPersonId() {
			return personId;
		}
		
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder getOrCreatePersonId(int _index) {
		
			if (personId==null) {
				this.personId = new ArrayList<>();
			}
			FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder result;
			return getIndex(personId, _index, () -> {
						FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder newPersonId = FieldWithMetaPersonIdentifier.builder();
						return newPersonId;
					});
		}
		
		@Override
		@RosettaAttribute("honorific")
		public String getHonorific() {
			return honorific;
		}
		
		@Override
		@RosettaAttribute("firstName")
		public String getFirstName() {
			return firstName;
		}
		
		@Override
		@RosettaAttribute("middleName")
		public List<String> getMiddleName() {
			return middleName;
		}
		
		@Override
		@RosettaAttribute("initial")
		public List<String> getInitial() {
			return initial;
		}
		
		@Override
		@RosettaAttribute("surname")
		public String getSurname() {
			return surname;
		}
		
		@Override
		@RosettaAttribute("suffix")
		public String getSuffix() {
			return suffix;
		}
		
		@Override
		@RosettaAttribute("dateOfBirth")
		public Date getDateOfBirth() {
			return dateOfBirth;
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
		public NaturalPerson.NaturalPersonBuilder addPersonId(FieldWithMetaPersonIdentifier personId) {
			if (personId!=null) this.personId.add(personId.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonId(FieldWithMetaPersonIdentifier personId, int _idx) {
			getIndex(this.personId, _idx, () -> personId.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonIdValue(PersonIdentifier personId) {
			this.getOrCreatePersonId(-1).setValue(personId.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonIdValue(PersonIdentifier personId, int _idx) {
			this.getOrCreatePersonId(_idx).setValue(personId.toBuilder());
			return this;
		}
		@Override 
		public NaturalPerson.NaturalPersonBuilder addPersonId(List<? extends FieldWithMetaPersonIdentifier> personIds) {
			if (personIds != null) {
				for (FieldWithMetaPersonIdentifier toAdd : personIds) {
					this.personId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("personId")
		public NaturalPerson.NaturalPersonBuilder setPersonId(List<? extends FieldWithMetaPersonIdentifier> personIds) {
			if (personIds == null)  {
				this.personId = new ArrayList<>();
			}
			else {
				this.personId = personIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonIdValue(List<? extends PersonIdentifier> personIds) {
			if (personIds != null) {
				for (PersonIdentifier toAdd : personIds) {
					this.addPersonIdValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder setPersonIdValue(List<? extends PersonIdentifier> personIds) {
			this.personId.clear();
			if (personIds!=null) {
				personIds.forEach(this::addPersonIdValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("honorific")
		public NaturalPerson.NaturalPersonBuilder setHonorific(String honorific) {
			this.honorific = honorific==null?null:honorific;
			return this;
		}
		@Override
		@RosettaAttribute("firstName")
		public NaturalPerson.NaturalPersonBuilder setFirstName(String firstName) {
			this.firstName = firstName==null?null:firstName;
			return this;
		}
		@Override
		public NaturalPerson.NaturalPersonBuilder addMiddleName(String middleName) {
			if (middleName!=null) this.middleName.add(middleName);
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addMiddleName(String middleName, int _idx) {
			getIndex(this.middleName, _idx, () -> middleName);
			return this;
		}
		@Override 
		public NaturalPerson.NaturalPersonBuilder addMiddleName(List<? extends String> middleNames) {
			if (middleNames != null) {
				for (String toAdd : middleNames) {
					this.middleName.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("middleName")
		public NaturalPerson.NaturalPersonBuilder setMiddleName(List<? extends String> middleNames) {
			if (middleNames == null)  {
				this.middleName = new ArrayList<>();
			}
			else {
				this.middleName = middleNames.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addInitial(String initial) {
			if (initial!=null) this.initial.add(initial);
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addInitial(String initial, int _idx) {
			getIndex(this.initial, _idx, () -> initial);
			return this;
		}
		@Override 
		public NaturalPerson.NaturalPersonBuilder addInitial(List<? extends String> initials) {
			if (initials != null) {
				for (String toAdd : initials) {
					this.initial.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("initial")
		public NaturalPerson.NaturalPersonBuilder setInitial(List<? extends String> initials) {
			if (initials == null)  {
				this.initial = new ArrayList<>();
			}
			else {
				this.initial = initials.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("surname")
		public NaturalPerson.NaturalPersonBuilder setSurname(String surname) {
			this.surname = surname==null?null:surname;
			return this;
		}
		@Override
		@RosettaAttribute("suffix")
		public NaturalPerson.NaturalPersonBuilder setSuffix(String suffix) {
			this.suffix = suffix==null?null:suffix;
			return this;
		}
		@Override
		@RosettaAttribute("dateOfBirth")
		public NaturalPerson.NaturalPersonBuilder setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth==null?null:dateOfBirth;
			return this;
		}
		@Override
		@RosettaAttribute("contactInformation")
		public NaturalPerson.NaturalPersonBuilder setContactInformation(ContactInformation contactInformation) {
			this.contactInformation = contactInformation==null?null:contactInformation.toBuilder();
			return this;
		}
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonRole(NaturalPersonRole personRole) {
			if (personRole!=null) this.personRole.add(personRole.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder addPersonRole(NaturalPersonRole personRole, int _idx) {
			getIndex(this.personRole, _idx, () -> personRole.toBuilder());
			return this;
		}
		@Override 
		public NaturalPerson.NaturalPersonBuilder addPersonRole(List<? extends NaturalPersonRole> personRoles) {
			if (personRoles != null) {
				for (NaturalPersonRole toAdd : personRoles) {
					this.personRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("personRole")
		public NaturalPerson.NaturalPersonBuilder setPersonRole(List<? extends NaturalPersonRole> personRoles) {
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
		@RosettaAttribute("meta")
		public NaturalPerson.NaturalPersonBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public NaturalPerson build() {
			return new NaturalPerson.NaturalPersonImpl(this);
		}
		
		@Override
		public NaturalPerson.NaturalPersonBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NaturalPerson.NaturalPersonBuilder prune() {
			personId = personId.stream().filter(b->b!=null).<FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (contactInformation!=null && !contactInformation.prune().hasData()) contactInformation = null;
			personRole = personRole.stream().filter(b->b!=null).<NaturalPersonRole.NaturalPersonRoleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPersonId()!=null && getPersonId().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getHonorific()!=null) return true;
			if (getFirstName()!=null) return true;
			if (getMiddleName()!=null && !getMiddleName().isEmpty()) return true;
			if (getInitial()!=null && !getInitial().isEmpty()) return true;
			if (getSurname()!=null) return true;
			if (getSuffix()!=null) return true;
			if (getDateOfBirth()!=null) return true;
			if (getContactInformation()!=null && getContactInformation().hasData()) return true;
			if (getPersonRole()!=null && getPersonRole().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NaturalPerson.NaturalPersonBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NaturalPerson.NaturalPersonBuilder o = (NaturalPerson.NaturalPersonBuilder) other;
			
			merger.mergeRosetta(getPersonId(), o.getPersonId(), this::getOrCreatePersonId);
			merger.mergeRosetta(getContactInformation(), o.getContactInformation(), this::setContactInformation);
			merger.mergeRosetta(getPersonRole(), o.getPersonRole(), this::getOrCreatePersonRole);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getHonorific(), o.getHonorific(), this::setHonorific);
			merger.mergeBasic(getFirstName(), o.getFirstName(), this::setFirstName);
			merger.mergeBasic(getMiddleName(), o.getMiddleName(), (Consumer<String>) this::addMiddleName);
			merger.mergeBasic(getInitial(), o.getInitial(), (Consumer<String>) this::addInitial);
			merger.mergeBasic(getSurname(), o.getSurname(), this::setSurname);
			merger.mergeBasic(getSuffix(), o.getSuffix(), this::setSuffix);
			merger.mergeBasic(getDateOfBirth(), o.getDateOfBirth(), this::setDateOfBirth);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NaturalPerson _that = getType().cast(o);
		
			if (!ListEquals.listEquals(personId, _that.getPersonId())) return false;
			if (!Objects.equals(honorific, _that.getHonorific())) return false;
			if (!Objects.equals(firstName, _that.getFirstName())) return false;
			if (!ListEquals.listEquals(middleName, _that.getMiddleName())) return false;
			if (!ListEquals.listEquals(initial, _that.getInitial())) return false;
			if (!Objects.equals(surname, _that.getSurname())) return false;
			if (!Objects.equals(suffix, _that.getSuffix())) return false;
			if (!Objects.equals(dateOfBirth, _that.getDateOfBirth())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!ListEquals.listEquals(personRole, _that.getPersonRole())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (personId != null ? personId.hashCode() : 0);
			_result = 31 * _result + (honorific != null ? honorific.hashCode() : 0);
			_result = 31 * _result + (firstName != null ? firstName.hashCode() : 0);
			_result = 31 * _result + (middleName != null ? middleName.hashCode() : 0);
			_result = 31 * _result + (initial != null ? initial.hashCode() : 0);
			_result = 31 * _result + (surname != null ? surname.hashCode() : 0);
			_result = 31 * _result + (suffix != null ? suffix.hashCode() : 0);
			_result = 31 * _result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (personRole != null ? personRole.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NaturalPersonBuilder {" +
				"personId=" + this.personId + ", " +
				"honorific=" + this.honorific + ", " +
				"firstName=" + this.firstName + ", " +
				"middleName=" + this.middleName + ", " +
				"initial=" + this.initial + ", " +
				"surname=" + this.surname + ", " +
				"suffix=" + this.suffix + ", " +
				"dateOfBirth=" + this.dateOfBirth + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"personRole=" + this.personRole + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
