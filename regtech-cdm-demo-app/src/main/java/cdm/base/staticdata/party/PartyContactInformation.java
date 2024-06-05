package cdm.base.staticdata.party;

import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyContactInformation;
import cdm.base.staticdata.party.PartyContactInformation.PartyContactInformationBuilder;
import cdm.base.staticdata.party.PartyContactInformation.PartyContactInformationBuilderImpl;
import cdm.base.staticdata.party.PartyContactInformation.PartyContactInformationImpl;
import cdm.base.staticdata.party.meta.PartyContactInformationMeta;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
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
 * A class to specify contact information within a party: address and, optionally, associated business unit and person. This class also supports the ISDA CSA representation as a single string, through the address attribute.
 * @version ${project.version}
 */
@RosettaDataType(value="PartyContactInformation", builder=PartyContactInformation.PartyContactInformationBuilderImpl.class, version="${project.version}")
public interface PartyContactInformation extends RosettaModelObject {

	PartyContactInformationMeta metaData = new PartyContactInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The reference to the party to which the contact information refers to.
	 */
	ReferenceWithMetaParty getPartyReference();
	/**
	 * The postal/street address, telephone number, email address and/or web page. If the contact information is specific to the associated business unit(s), it should be associated with those.
	 */
	ContactInformation getContactInformation();
	/**
	 * Optional organization unit information used to describe the organization units (e.g. trading desks) involved in a transaction or business process, incl. the contact information (when relevant).
	 */
	List<? extends BusinessUnit> getBusinessUnit();
	/**
	 * Optional information about people involved in a transaction or business process. (These are employees of the party.)
	 */
	List<? extends NaturalPerson> getPerson();
	/**
	 * Specification of special instructions of the relevant party.
	 */
	String getAdditionalInformation();

	/*********************** Build Methods  ***********************/
	PartyContactInformation build();
	
	PartyContactInformation.PartyContactInformationBuilder toBuilder();
	
	static PartyContactInformation.PartyContactInformationBuilder builder() {
		return new PartyContactInformation.PartyContactInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartyContactInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartyContactInformation> getType() {
		return PartyContactInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
		processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.class, getContactInformation());
		processRosetta(path.newSubPath("businessUnit"), processor, BusinessUnit.class, getBusinessUnit());
		processRosetta(path.newSubPath("person"), processor, NaturalPerson.class, getPerson());
		processor.processBasic(path.newSubPath("additionalInformation"), String.class, getAdditionalInformation(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyContactInformationBuilder extends PartyContactInformation, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		ContactInformation.ContactInformationBuilder getOrCreateContactInformation();
		ContactInformation.ContactInformationBuilder getContactInformation();
		BusinessUnit.BusinessUnitBuilder getOrCreateBusinessUnit(int _index);
		List<? extends BusinessUnit.BusinessUnitBuilder> getBusinessUnit();
		NaturalPerson.NaturalPersonBuilder getOrCreatePerson(int _index);
		List<? extends NaturalPerson.NaturalPersonBuilder> getPerson();
		PartyContactInformation.PartyContactInformationBuilder setPartyReference(ReferenceWithMetaParty partyReference0);
		PartyContactInformation.PartyContactInformationBuilder setPartyReferenceValue(Party partyReference1);
		PartyContactInformation.PartyContactInformationBuilder setContactInformation(ContactInformation contactInformation);
		PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(BusinessUnit businessUnit0);
		PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(BusinessUnit businessUnit1, int _idx);
		PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(List<? extends BusinessUnit> businessUnit2);
		PartyContactInformation.PartyContactInformationBuilder setBusinessUnit(List<? extends BusinessUnit> businessUnit3);
		PartyContactInformation.PartyContactInformationBuilder addPerson(NaturalPerson person0);
		PartyContactInformation.PartyContactInformationBuilder addPerson(NaturalPerson person1, int _idx);
		PartyContactInformation.PartyContactInformationBuilder addPerson(List<? extends NaturalPerson> person2);
		PartyContactInformation.PartyContactInformationBuilder setPerson(List<? extends NaturalPerson> person3);
		PartyContactInformation.PartyContactInformationBuilder setAdditionalInformation(String additionalInformation);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
			processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.ContactInformationBuilder.class, getContactInformation());
			processRosetta(path.newSubPath("businessUnit"), processor, BusinessUnit.BusinessUnitBuilder.class, getBusinessUnit());
			processRosetta(path.newSubPath("person"), processor, NaturalPerson.NaturalPersonBuilder.class, getPerson());
			processor.processBasic(path.newSubPath("additionalInformation"), String.class, getAdditionalInformation(), this);
		}
		

		PartyContactInformation.PartyContactInformationBuilder prune();
	}

	/*********************** Immutable Implementation of PartyContactInformation  ***********************/
	class PartyContactInformationImpl implements PartyContactInformation {
		private final ReferenceWithMetaParty partyReference;
		private final ContactInformation contactInformation;
		private final List<? extends BusinessUnit> businessUnit;
		private final List<? extends NaturalPerson> person;
		private final String additionalInformation;
		
		protected PartyContactInformationImpl(PartyContactInformation.PartyContactInformationBuilder builder) {
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
			this.contactInformation = ofNullable(builder.getContactInformation()).map(f->f.build()).orElse(null);
			this.businessUnit = ofNullable(builder.getBusinessUnit()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.person = ofNullable(builder.getPerson()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.additionalInformation = builder.getAdditionalInformation();
		}
		
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		@RosettaAttribute("contactInformation")
		public ContactInformation getContactInformation() {
			return contactInformation;
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
		@RosettaAttribute("additionalInformation")
		public String getAdditionalInformation() {
			return additionalInformation;
		}
		
		@Override
		public PartyContactInformation build() {
			return this;
		}
		
		@Override
		public PartyContactInformation.PartyContactInformationBuilder toBuilder() {
			PartyContactInformation.PartyContactInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartyContactInformation.PartyContactInformationBuilder builder) {
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
			ofNullable(getContactInformation()).ifPresent(builder::setContactInformation);
			ofNullable(getBusinessUnit()).ifPresent(builder::setBusinessUnit);
			ofNullable(getPerson()).ifPresent(builder::setPerson);
			ofNullable(getAdditionalInformation()).ifPresent(builder::setAdditionalInformation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyContactInformation _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!ListEquals.listEquals(businessUnit, _that.getBusinessUnit())) return false;
			if (!ListEquals.listEquals(person, _that.getPerson())) return false;
			if (!Objects.equals(additionalInformation, _that.getAdditionalInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (businessUnit != null ? businessUnit.hashCode() : 0);
			_result = 31 * _result + (person != null ? person.hashCode() : 0);
			_result = 31 * _result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyContactInformation {" +
				"partyReference=" + this.partyReference + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"businessUnit=" + this.businessUnit + ", " +
				"person=" + this.person + ", " +
				"additionalInformation=" + this.additionalInformation +
			'}';
		}
	}

	/*********************** Builder Implementation of PartyContactInformation  ***********************/
	class PartyContactInformationBuilderImpl implements PartyContactInformation.PartyContactInformationBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
		protected ContactInformation.ContactInformationBuilder contactInformation;
		protected List<BusinessUnit.BusinessUnitBuilder> businessUnit = new ArrayList<>();
		protected List<NaturalPerson.NaturalPersonBuilder> person = new ArrayList<>();
		protected String additionalInformation;
	
		public PartyContactInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference() {
			return partyReference;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (partyReference!=null) {
				result = partyReference;
			}
			else {
				result = partyReference = ReferenceWithMetaParty.builder();
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
		@RosettaAttribute("additionalInformation")
		public String getAdditionalInformation() {
			return additionalInformation;
		}
		
	
		@Override
		@RosettaAttribute("partyReference")
		public PartyContactInformation.PartyContactInformationBuilder setPartyReference(ReferenceWithMetaParty partyReference) {
			this.partyReference = partyReference==null?null:partyReference.toBuilder();
			return this;
		}
		@Override
		public PartyContactInformation.PartyContactInformationBuilder setPartyReferenceValue(Party partyReference) {
			this.getOrCreatePartyReference().setValue(partyReference);
			return this;
		}
		@Override
		@RosettaAttribute("contactInformation")
		public PartyContactInformation.PartyContactInformationBuilder setContactInformation(ContactInformation contactInformation) {
			this.contactInformation = contactInformation==null?null:contactInformation.toBuilder();
			return this;
		}
		@Override
		public PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(BusinessUnit businessUnit) {
			if (businessUnit!=null) this.businessUnit.add(businessUnit.toBuilder());
			return this;
		}
		
		@Override
		public PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(BusinessUnit businessUnit, int _idx) {
			getIndex(this.businessUnit, _idx, () -> businessUnit.toBuilder());
			return this;
		}
		@Override 
		public PartyContactInformation.PartyContactInformationBuilder addBusinessUnit(List<? extends BusinessUnit> businessUnits) {
			if (businessUnits != null) {
				for (BusinessUnit toAdd : businessUnits) {
					this.businessUnit.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("businessUnit")
		public PartyContactInformation.PartyContactInformationBuilder setBusinessUnit(List<? extends BusinessUnit> businessUnits) {
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
		public PartyContactInformation.PartyContactInformationBuilder addPerson(NaturalPerson person) {
			if (person!=null) this.person.add(person.toBuilder());
			return this;
		}
		
		@Override
		public PartyContactInformation.PartyContactInformationBuilder addPerson(NaturalPerson person, int _idx) {
			getIndex(this.person, _idx, () -> person.toBuilder());
			return this;
		}
		@Override 
		public PartyContactInformation.PartyContactInformationBuilder addPerson(List<? extends NaturalPerson> persons) {
			if (persons != null) {
				for (NaturalPerson toAdd : persons) {
					this.person.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("person")
		public PartyContactInformation.PartyContactInformationBuilder setPerson(List<? extends NaturalPerson> persons) {
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
		@RosettaAttribute("additionalInformation")
		public PartyContactInformation.PartyContactInformationBuilder setAdditionalInformation(String additionalInformation) {
			this.additionalInformation = additionalInformation==null?null:additionalInformation;
			return this;
		}
		
		@Override
		public PartyContactInformation build() {
			return new PartyContactInformation.PartyContactInformationImpl(this);
		}
		
		@Override
		public PartyContactInformation.PartyContactInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyContactInformation.PartyContactInformationBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			if (contactInformation!=null && !contactInformation.prune().hasData()) contactInformation = null;
			businessUnit = businessUnit.stream().filter(b->b!=null).<BusinessUnit.BusinessUnitBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			person = person.stream().filter(b->b!=null).<NaturalPerson.NaturalPersonBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			if (getContactInformation()!=null && getContactInformation().hasData()) return true;
			if (getBusinessUnit()!=null && getBusinessUnit().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPerson()!=null && getPerson().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAdditionalInformation()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyContactInformation.PartyContactInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartyContactInformation.PartyContactInformationBuilder o = (PartyContactInformation.PartyContactInformationBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			merger.mergeRosetta(getContactInformation(), o.getContactInformation(), this::setContactInformation);
			merger.mergeRosetta(getBusinessUnit(), o.getBusinessUnit(), this::getOrCreateBusinessUnit);
			merger.mergeRosetta(getPerson(), o.getPerson(), this::getOrCreatePerson);
			
			merger.mergeBasic(getAdditionalInformation(), o.getAdditionalInformation(), this::setAdditionalInformation);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyContactInformation _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!ListEquals.listEquals(businessUnit, _that.getBusinessUnit())) return false;
			if (!ListEquals.listEquals(person, _that.getPerson())) return false;
			if (!Objects.equals(additionalInformation, _that.getAdditionalInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (businessUnit != null ? businessUnit.hashCode() : 0);
			_result = 31 * _result + (person != null ? person.hashCode() : 0);
			_result = 31 * _result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyContactInformationBuilder {" +
				"partyReference=" + this.partyReference + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"businessUnit=" + this.businessUnit + ", " +
				"person=" + this.person + ", " +
				"additionalInformation=" + this.additionalInformation +
			'}';
		}
	}
}
