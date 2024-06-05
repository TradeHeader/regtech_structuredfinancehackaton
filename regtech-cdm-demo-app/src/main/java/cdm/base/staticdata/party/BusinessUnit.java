package cdm.base.staticdata.party;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.BusinessUnit.BusinessUnitBuilder;
import cdm.base.staticdata.party.BusinessUnit.BusinessUnitBuilderImpl;
import cdm.base.staticdata.party.BusinessUnit.BusinessUnitImpl;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.meta.BusinessUnitMeta;
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
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify an organizational unit.
 * @version ${project.version}
 */
@RosettaDataType(value="BusinessUnit", builder=BusinessUnit.BusinessUnitBuilderImpl.class, version="${project.version}")
public interface BusinessUnit extends RosettaModelObject, GlobalKey {

	BusinessUnitMeta metaData = new BusinessUnitMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A name used to describe the organizational unit
	 */
	String getName();
	/**
	 * An identifier used to uniquely identify the organizational unit
	 */
	Identifier getIdentifier();
	/**
	 * The contact information for such business unit, when different from the contact information associated with the party.
	 */
	ContactInformation getContactInformation();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	BusinessUnit build();
	
	BusinessUnit.BusinessUnitBuilder toBuilder();
	
	static BusinessUnit.BusinessUnitBuilder builder() {
		return new BusinessUnit.BusinessUnitBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BusinessUnit> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BusinessUnit> getType() {
		return BusinessUnit.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
		processRosetta(path.newSubPath("identifier"), processor, Identifier.class, getIdentifier());
		processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.class, getContactInformation());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BusinessUnitBuilder extends BusinessUnit, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateIdentifier();
		Identifier.IdentifierBuilder getIdentifier();
		ContactInformation.ContactInformationBuilder getOrCreateContactInformation();
		ContactInformation.ContactInformationBuilder getContactInformation();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		BusinessUnit.BusinessUnitBuilder setName(String name);
		BusinessUnit.BusinessUnitBuilder setIdentifier(Identifier identifier);
		BusinessUnit.BusinessUnitBuilder setContactInformation(ContactInformation contactInformation);
		BusinessUnit.BusinessUnitBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
			processRosetta(path.newSubPath("identifier"), processor, Identifier.IdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("contactInformation"), processor, ContactInformation.ContactInformationBuilder.class, getContactInformation());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		BusinessUnit.BusinessUnitBuilder prune();
	}

	/*********************** Immutable Implementation of BusinessUnit  ***********************/
	class BusinessUnitImpl implements BusinessUnit {
		private final String name;
		private final Identifier identifier;
		private final ContactInformation contactInformation;
		private final MetaFields meta;
		
		protected BusinessUnitImpl(BusinessUnit.BusinessUnitBuilder builder) {
			this.name = builder.getName();
			this.identifier = ofNullable(builder.getIdentifier()).map(f->f.build()).orElse(null);
			this.contactInformation = ofNullable(builder.getContactInformation()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("identifier")
		public Identifier getIdentifier() {
			return identifier;
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
		public BusinessUnit build() {
			return this;
		}
		
		@Override
		public BusinessUnit.BusinessUnitBuilder toBuilder() {
			BusinessUnit.BusinessUnitBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BusinessUnit.BusinessUnitBuilder builder) {
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getContactInformation()).ifPresent(builder::setContactInformation);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessUnit _that = getType().cast(o);
		
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessUnit {" +
				"name=" + this.name + ", " +
				"identifier=" + this.identifier + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of BusinessUnit  ***********************/
	class BusinessUnitBuilderImpl implements BusinessUnit.BusinessUnitBuilder, GlobalKeyBuilder {
	
		protected String name;
		protected Identifier.IdentifierBuilder identifier;
		protected ContactInformation.ContactInformationBuilder contactInformation;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public BusinessUnitBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("identifier")
		public Identifier.IdentifierBuilder getIdentifier() {
			return identifier;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateIdentifier() {
			Identifier.IdentifierBuilder result;
			if (identifier!=null) {
				result = identifier;
			}
			else {
				result = identifier = Identifier.builder();
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
		@RosettaAttribute("name")
		public BusinessUnit.BusinessUnitBuilder setName(String name) {
			this.name = name==null?null:name;
			return this;
		}
		@Override
		@RosettaAttribute("identifier")
		public BusinessUnit.BusinessUnitBuilder setIdentifier(Identifier identifier) {
			this.identifier = identifier==null?null:identifier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("contactInformation")
		public BusinessUnit.BusinessUnitBuilder setContactInformation(ContactInformation contactInformation) {
			this.contactInformation = contactInformation==null?null:contactInformation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public BusinessUnit.BusinessUnitBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public BusinessUnit build() {
			return new BusinessUnit.BusinessUnitImpl(this);
		}
		
		@Override
		public BusinessUnit.BusinessUnitBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessUnit.BusinessUnitBuilder prune() {
			if (identifier!=null && !identifier.prune().hasData()) identifier = null;
			if (contactInformation!=null && !contactInformation.prune().hasData()) contactInformation = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getName()!=null) return true;
			if (getIdentifier()!=null && getIdentifier().hasData()) return true;
			if (getContactInformation()!=null && getContactInformation().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessUnit.BusinessUnitBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BusinessUnit.BusinessUnitBuilder o = (BusinessUnit.BusinessUnitBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeRosetta(getContactInformation(), o.getContactInformation(), this::setContactInformation);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getName(), o.getName(), this::setName);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessUnit _that = getType().cast(o);
		
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(contactInformation, _that.getContactInformation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (contactInformation != null ? contactInformation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessUnitBuilder {" +
				"name=" + this.name + ", " +
				"identifier=" + this.identifier + ", " +
				"contactInformation=" + this.contactInformation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
