package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Address;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.ContactInformation.ContactInformationBuilder;
import cdm.base.staticdata.party.ContactInformation.ContactInformationBuilderImpl;
import cdm.base.staticdata.party.ContactInformation.ContactInformationImpl;
import cdm.base.staticdata.party.TelephoneNumber;
import cdm.base.staticdata.party.meta.ContactInformationMeta;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify contact information associated with a party: telephone, postal/street address, email and web page.
 * @version ${project.version}
 */
@RosettaDataType(value="ContactInformation", builder=ContactInformation.ContactInformationBuilderImpl.class, version="${project.version}")
public interface ContactInformation extends RosettaModelObject {

	ContactInformationMeta metaData = new ContactInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The telephone number.
	 */
	List<? extends TelephoneNumber> getTelephone();
	/**
	 * The street/postal address.
	 */
	List<? extends Address> getAddress();
	/**
	 * The email address.
	 */
	List<String> getEmail();
	/**
	 * The web page. This attribute is not specified as part of the FpML ContactInformation complex type.
	 */
	List<String> getWebPage();

	/*********************** Build Methods  ***********************/
	ContactInformation build();
	
	ContactInformation.ContactInformationBuilder toBuilder();
	
	static ContactInformation.ContactInformationBuilder builder() {
		return new ContactInformation.ContactInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContactInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContactInformation> getType() {
		return ContactInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("telephone"), processor, TelephoneNumber.class, getTelephone());
		processRosetta(path.newSubPath("address"), processor, Address.class, getAddress());
		processor.processBasic(path.newSubPath("email"), String.class, getEmail(), this);
		processor.processBasic(path.newSubPath("webPage"), String.class, getWebPage(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContactInformationBuilder extends ContactInformation, RosettaModelObjectBuilder {
		TelephoneNumber.TelephoneNumberBuilder getOrCreateTelephone(int _index);
		List<? extends TelephoneNumber.TelephoneNumberBuilder> getTelephone();
		Address.AddressBuilder getOrCreateAddress(int _index);
		List<? extends Address.AddressBuilder> getAddress();
		ContactInformation.ContactInformationBuilder addTelephone(TelephoneNumber telephone0);
		ContactInformation.ContactInformationBuilder addTelephone(TelephoneNumber telephone1, int _idx);
		ContactInformation.ContactInformationBuilder addTelephone(List<? extends TelephoneNumber> telephone2);
		ContactInformation.ContactInformationBuilder setTelephone(List<? extends TelephoneNumber> telephone3);
		ContactInformation.ContactInformationBuilder addAddress(Address address0);
		ContactInformation.ContactInformationBuilder addAddress(Address address1, int _idx);
		ContactInformation.ContactInformationBuilder addAddress(List<? extends Address> address2);
		ContactInformation.ContactInformationBuilder setAddress(List<? extends Address> address3);
		ContactInformation.ContactInformationBuilder addEmail(String email0);
		ContactInformation.ContactInformationBuilder addEmail(String email1, int _idx);
		ContactInformation.ContactInformationBuilder addEmail(List<? extends String> email2);
		ContactInformation.ContactInformationBuilder setEmail(List<? extends String> email3);
		ContactInformation.ContactInformationBuilder addWebPage(String webPage0);
		ContactInformation.ContactInformationBuilder addWebPage(String webPage1, int _idx);
		ContactInformation.ContactInformationBuilder addWebPage(List<? extends String> webPage2);
		ContactInformation.ContactInformationBuilder setWebPage(List<? extends String> webPage3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("telephone"), processor, TelephoneNumber.TelephoneNumberBuilder.class, getTelephone());
			processRosetta(path.newSubPath("address"), processor, Address.AddressBuilder.class, getAddress());
			processor.processBasic(path.newSubPath("email"), String.class, getEmail(), this);
			processor.processBasic(path.newSubPath("webPage"), String.class, getWebPage(), this);
		}
		

		ContactInformation.ContactInformationBuilder prune();
	}

	/*********************** Immutable Implementation of ContactInformation  ***********************/
	class ContactInformationImpl implements ContactInformation {
		private final List<? extends TelephoneNumber> telephone;
		private final List<? extends Address> address;
		private final List<String> email;
		private final List<String> webPage;
		
		protected ContactInformationImpl(ContactInformation.ContactInformationBuilder builder) {
			this.telephone = ofNullable(builder.getTelephone()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.address = ofNullable(builder.getAddress()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.email = ofNullable(builder.getEmail()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.webPage = ofNullable(builder.getWebPage()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("telephone")
		public List<? extends TelephoneNumber> getTelephone() {
			return telephone;
		}
		
		@Override
		@RosettaAttribute("address")
		public List<? extends Address> getAddress() {
			return address;
		}
		
		@Override
		@RosettaAttribute("email")
		public List<String> getEmail() {
			return email;
		}
		
		@Override
		@RosettaAttribute("webPage")
		public List<String> getWebPage() {
			return webPage;
		}
		
		@Override
		public ContactInformation build() {
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder toBuilder() {
			ContactInformation.ContactInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContactInformation.ContactInformationBuilder builder) {
			ofNullable(getTelephone()).ifPresent(builder::setTelephone);
			ofNullable(getAddress()).ifPresent(builder::setAddress);
			ofNullable(getEmail()).ifPresent(builder::setEmail);
			ofNullable(getWebPage()).ifPresent(builder::setWebPage);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContactInformation _that = getType().cast(o);
		
			if (!ListEquals.listEquals(telephone, _that.getTelephone())) return false;
			if (!ListEquals.listEquals(address, _that.getAddress())) return false;
			if (!ListEquals.listEquals(email, _that.getEmail())) return false;
			if (!ListEquals.listEquals(webPage, _that.getWebPage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (telephone != null ? telephone.hashCode() : 0);
			_result = 31 * _result + (address != null ? address.hashCode() : 0);
			_result = 31 * _result + (email != null ? email.hashCode() : 0);
			_result = 31 * _result + (webPage != null ? webPage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContactInformation {" +
				"telephone=" + this.telephone + ", " +
				"address=" + this.address + ", " +
				"email=" + this.email + ", " +
				"webPage=" + this.webPage +
			'}';
		}
	}

	/*********************** Builder Implementation of ContactInformation  ***********************/
	class ContactInformationBuilderImpl implements ContactInformation.ContactInformationBuilder {
	
		protected List<TelephoneNumber.TelephoneNumberBuilder> telephone = new ArrayList<>();
		protected List<Address.AddressBuilder> address = new ArrayList<>();
		protected List<String> email = new ArrayList<>();
		protected List<String> webPage = new ArrayList<>();
	
		public ContactInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("telephone")
		public List<? extends TelephoneNumber.TelephoneNumberBuilder> getTelephone() {
			return telephone;
		}
		
		public TelephoneNumber.TelephoneNumberBuilder getOrCreateTelephone(int _index) {
		
			if (telephone==null) {
				this.telephone = new ArrayList<>();
			}
			TelephoneNumber.TelephoneNumberBuilder result;
			return getIndex(telephone, _index, () -> {
						TelephoneNumber.TelephoneNumberBuilder newTelephone = TelephoneNumber.builder();
						return newTelephone;
					});
		}
		
		@Override
		@RosettaAttribute("address")
		public List<? extends Address.AddressBuilder> getAddress() {
			return address;
		}
		
		public Address.AddressBuilder getOrCreateAddress(int _index) {
		
			if (address==null) {
				this.address = new ArrayList<>();
			}
			Address.AddressBuilder result;
			return getIndex(address, _index, () -> {
						Address.AddressBuilder newAddress = Address.builder();
						return newAddress;
					});
		}
		
		@Override
		@RosettaAttribute("email")
		public List<String> getEmail() {
			return email;
		}
		
		@Override
		@RosettaAttribute("webPage")
		public List<String> getWebPage() {
			return webPage;
		}
		
	
		@Override
		public ContactInformation.ContactInformationBuilder addTelephone(TelephoneNumber telephone) {
			if (telephone!=null) this.telephone.add(telephone.toBuilder());
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addTelephone(TelephoneNumber telephone, int _idx) {
			getIndex(this.telephone, _idx, () -> telephone.toBuilder());
			return this;
		}
		@Override 
		public ContactInformation.ContactInformationBuilder addTelephone(List<? extends TelephoneNumber> telephones) {
			if (telephones != null) {
				for (TelephoneNumber toAdd : telephones) {
					this.telephone.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("telephone")
		public ContactInformation.ContactInformationBuilder setTelephone(List<? extends TelephoneNumber> telephones) {
			if (telephones == null)  {
				this.telephone = new ArrayList<>();
			}
			else {
				this.telephone = telephones.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addAddress(Address address) {
			if (address!=null) this.address.add(address.toBuilder());
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addAddress(Address address, int _idx) {
			getIndex(this.address, _idx, () -> address.toBuilder());
			return this;
		}
		@Override 
		public ContactInformation.ContactInformationBuilder addAddress(List<? extends Address> addresss) {
			if (addresss != null) {
				for (Address toAdd : addresss) {
					this.address.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("address")
		public ContactInformation.ContactInformationBuilder setAddress(List<? extends Address> addresss) {
			if (addresss == null)  {
				this.address = new ArrayList<>();
			}
			else {
				this.address = addresss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addEmail(String email) {
			if (email!=null) this.email.add(email);
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addEmail(String email, int _idx) {
			getIndex(this.email, _idx, () -> email);
			return this;
		}
		@Override 
		public ContactInformation.ContactInformationBuilder addEmail(List<? extends String> emails) {
			if (emails != null) {
				for (String toAdd : emails) {
					this.email.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("email")
		public ContactInformation.ContactInformationBuilder setEmail(List<? extends String> emails) {
			if (emails == null)  {
				this.email = new ArrayList<>();
			}
			else {
				this.email = emails.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addWebPage(String webPage) {
			if (webPage!=null) this.webPage.add(webPage);
			return this;
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder addWebPage(String webPage, int _idx) {
			getIndex(this.webPage, _idx, () -> webPage);
			return this;
		}
		@Override 
		public ContactInformation.ContactInformationBuilder addWebPage(List<? extends String> webPages) {
			if (webPages != null) {
				for (String toAdd : webPages) {
					this.webPage.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("webPage")
		public ContactInformation.ContactInformationBuilder setWebPage(List<? extends String> webPages) {
			if (webPages == null)  {
				this.webPage = new ArrayList<>();
			}
			else {
				this.webPage = webPages.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ContactInformation build() {
			return new ContactInformation.ContactInformationImpl(this);
		}
		
		@Override
		public ContactInformation.ContactInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContactInformation.ContactInformationBuilder prune() {
			telephone = telephone.stream().filter(b->b!=null).<TelephoneNumber.TelephoneNumberBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			address = address.stream().filter(b->b!=null).<Address.AddressBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTelephone()!=null && getTelephone().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAddress()!=null && getAddress().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getEmail()!=null && !getEmail().isEmpty()) return true;
			if (getWebPage()!=null && !getWebPage().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContactInformation.ContactInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContactInformation.ContactInformationBuilder o = (ContactInformation.ContactInformationBuilder) other;
			
			merger.mergeRosetta(getTelephone(), o.getTelephone(), this::getOrCreateTelephone);
			merger.mergeRosetta(getAddress(), o.getAddress(), this::getOrCreateAddress);
			
			merger.mergeBasic(getEmail(), o.getEmail(), (Consumer<String>) this::addEmail);
			merger.mergeBasic(getWebPage(), o.getWebPage(), (Consumer<String>) this::addWebPage);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContactInformation _that = getType().cast(o);
		
			if (!ListEquals.listEquals(telephone, _that.getTelephone())) return false;
			if (!ListEquals.listEquals(address, _that.getAddress())) return false;
			if (!ListEquals.listEquals(email, _that.getEmail())) return false;
			if (!ListEquals.listEquals(webPage, _that.getWebPage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (telephone != null ? telephone.hashCode() : 0);
			_result = 31 * _result + (address != null ? address.hashCode() : 0);
			_result = 31 * _result + (email != null ? email.hashCode() : 0);
			_result = 31 * _result + (webPage != null ? webPage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContactInformationBuilder {" +
				"telephone=" + this.telephone + ", " +
				"address=" + this.address + ", " +
				"email=" + this.email + ", " +
				"webPage=" + this.webPage +
			'}';
		}
	}
}
