package cdm.base.staticdata.party;

import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.NaturalPersonRole.NaturalPersonRoleBuilder;
import cdm.base.staticdata.party.NaturalPersonRole.NaturalPersonRoleBuilderImpl;
import cdm.base.staticdata.party.NaturalPersonRole.NaturalPersonRoleImpl;
import cdm.base.staticdata.party.NaturalPersonRoleEnum;
import cdm.base.staticdata.party.meta.NaturalPersonRoleMeta;
import cdm.base.staticdata.party.metafields.FieldWithMetaNaturalPersonRoleEnum;
import cdm.base.staticdata.party.metafields.FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaNaturalPerson;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder;
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
 * A class to specify the role(s) that natural person(s) may have in relation to the contract.
 * @version ${project.version}
 */
@RosettaDataType(value="NaturalPersonRole", builder=NaturalPersonRole.NaturalPersonRoleBuilderImpl.class, version="${project.version}")
public interface NaturalPersonRole extends RosettaModelObject {

	NaturalPersonRoleMeta metaData = new NaturalPersonRoleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A reference to the natural person to whom the role refers to.
	 */
	ReferenceWithMetaNaturalPerson getPersonReference();
	/**
	 * FpML specifies a person role that is distinct from the party role.
	 */
	List<? extends FieldWithMetaNaturalPersonRoleEnum> getRole();

	/*********************** Build Methods  ***********************/
	NaturalPersonRole build();
	
	NaturalPersonRole.NaturalPersonRoleBuilder toBuilder();
	
	static NaturalPersonRole.NaturalPersonRoleBuilder builder() {
		return new NaturalPersonRole.NaturalPersonRoleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NaturalPersonRole> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NaturalPersonRole> getType() {
		return NaturalPersonRole.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("personReference"), processor, ReferenceWithMetaNaturalPerson.class, getPersonReference());
		processRosetta(path.newSubPath("role"), processor, FieldWithMetaNaturalPersonRoleEnum.class, getRole());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NaturalPersonRoleBuilder extends NaturalPersonRole, RosettaModelObjectBuilder {
		ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder getOrCreatePersonReference();
		ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder getPersonReference();
		FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder getOrCreateRole(int _index);
		List<? extends FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder> getRole();
		NaturalPersonRole.NaturalPersonRoleBuilder setPersonReference(ReferenceWithMetaNaturalPerson personReference0);
		NaturalPersonRole.NaturalPersonRoleBuilder setPersonReferenceValue(NaturalPerson personReference1);
		NaturalPersonRole.NaturalPersonRoleBuilder addRole(FieldWithMetaNaturalPersonRoleEnum role0);
		NaturalPersonRole.NaturalPersonRoleBuilder addRole(FieldWithMetaNaturalPersonRoleEnum role1, int _idx);
		NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(NaturalPersonRoleEnum role2);
		NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(NaturalPersonRoleEnum role3, int _idx);
		NaturalPersonRole.NaturalPersonRoleBuilder addRole(List<? extends FieldWithMetaNaturalPersonRoleEnum> role4);
		NaturalPersonRole.NaturalPersonRoleBuilder setRole(List<? extends FieldWithMetaNaturalPersonRoleEnum> role5);
		NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(List<? extends NaturalPersonRoleEnum> role6);
		NaturalPersonRole.NaturalPersonRoleBuilder setRoleValue(List<? extends NaturalPersonRoleEnum> role7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("personReference"), processor, ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder.class, getPersonReference());
			processRosetta(path.newSubPath("role"), processor, FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder.class, getRole());
		}
		

		NaturalPersonRole.NaturalPersonRoleBuilder prune();
	}

	/*********************** Immutable Implementation of NaturalPersonRole  ***********************/
	class NaturalPersonRoleImpl implements NaturalPersonRole {
		private final ReferenceWithMetaNaturalPerson personReference;
		private final List<? extends FieldWithMetaNaturalPersonRoleEnum> role;
		
		protected NaturalPersonRoleImpl(NaturalPersonRole.NaturalPersonRoleBuilder builder) {
			this.personReference = ofNullable(builder.getPersonReference()).map(f->f.build()).orElse(null);
			this.role = ofNullable(builder.getRole()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("personReference")
		public ReferenceWithMetaNaturalPerson getPersonReference() {
			return personReference;
		}
		
		@Override
		@RosettaAttribute("role")
		public List<? extends FieldWithMetaNaturalPersonRoleEnum> getRole() {
			return role;
		}
		
		@Override
		public NaturalPersonRole build() {
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder toBuilder() {
			NaturalPersonRole.NaturalPersonRoleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NaturalPersonRole.NaturalPersonRoleBuilder builder) {
			ofNullable(getPersonReference()).ifPresent(builder::setPersonReference);
			ofNullable(getRole()).ifPresent(builder::setRole);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NaturalPersonRole _that = getType().cast(o);
		
			if (!Objects.equals(personReference, _that.getPersonReference())) return false;
			if (!ListEquals.listEquals(role, _that.getRole())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (personReference != null ? personReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NaturalPersonRole {" +
				"personReference=" + this.personReference + ", " +
				"role=" + this.role +
			'}';
		}
	}

	/*********************** Builder Implementation of NaturalPersonRole  ***********************/
	class NaturalPersonRoleBuilderImpl implements NaturalPersonRole.NaturalPersonRoleBuilder {
	
		protected ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder personReference;
		protected List<FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder> role = new ArrayList<>();
	
		public NaturalPersonRoleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("personReference")
		public ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder getPersonReference() {
			return personReference;
		}
		
		@Override
		public ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder getOrCreatePersonReference() {
			ReferenceWithMetaNaturalPerson.ReferenceWithMetaNaturalPersonBuilder result;
			if (personReference!=null) {
				result = personReference;
			}
			else {
				result = personReference = ReferenceWithMetaNaturalPerson.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("role")
		public List<? extends FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder> getRole() {
			return role;
		}
		
		public FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder getOrCreateRole(int _index) {
		
			if (role==null) {
				this.role = new ArrayList<>();
			}
			FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder result;
			return getIndex(role, _index, () -> {
						FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder newRole = FieldWithMetaNaturalPersonRoleEnum.builder();
						return newRole;
					});
		}
		
	
		@Override
		@RosettaAttribute("personReference")
		public NaturalPersonRole.NaturalPersonRoleBuilder setPersonReference(ReferenceWithMetaNaturalPerson personReference) {
			this.personReference = personReference==null?null:personReference.toBuilder();
			return this;
		}
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder setPersonReferenceValue(NaturalPerson personReference) {
			this.getOrCreatePersonReference().setValue(personReference);
			return this;
		}
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder addRole(FieldWithMetaNaturalPersonRoleEnum role) {
			if (role!=null) this.role.add(role.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder addRole(FieldWithMetaNaturalPersonRoleEnum role, int _idx) {
			getIndex(this.role, _idx, () -> role.toBuilder());
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(NaturalPersonRoleEnum role) {
			this.getOrCreateRole(-1).setValue(role);
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(NaturalPersonRoleEnum role, int _idx) {
			this.getOrCreateRole(_idx).setValue(role);
			return this;
		}
		@Override 
		public NaturalPersonRole.NaturalPersonRoleBuilder addRole(List<? extends FieldWithMetaNaturalPersonRoleEnum> roles) {
			if (roles != null) {
				for (FieldWithMetaNaturalPersonRoleEnum toAdd : roles) {
					this.role.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("role")
		public NaturalPersonRole.NaturalPersonRoleBuilder setRole(List<? extends FieldWithMetaNaturalPersonRoleEnum> roles) {
			if (roles == null)  {
				this.role = new ArrayList<>();
			}
			else {
				this.role = roles.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder addRoleValue(List<? extends NaturalPersonRoleEnum> roles) {
			if (roles != null) {
				for (NaturalPersonRoleEnum toAdd : roles) {
					this.addRoleValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder setRoleValue(List<? extends NaturalPersonRoleEnum> roles) {
			this.role.clear();
			if (roles!=null) {
				roles.forEach(this::addRoleValue);
			}
			return this;
		}
		
		
		@Override
		public NaturalPersonRole build() {
			return new NaturalPersonRole.NaturalPersonRoleImpl(this);
		}
		
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder prune() {
			if (personReference!=null && !personReference.prune().hasData()) personReference = null;
			role = role.stream().filter(b->b!=null).<FieldWithMetaNaturalPersonRoleEnum.FieldWithMetaNaturalPersonRoleEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPersonReference()!=null && getPersonReference().hasData()) return true;
			if (getRole()!=null && !getRole().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NaturalPersonRole.NaturalPersonRoleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NaturalPersonRole.NaturalPersonRoleBuilder o = (NaturalPersonRole.NaturalPersonRoleBuilder) other;
			
			merger.mergeRosetta(getPersonReference(), o.getPersonReference(), this::setPersonReference);
			merger.mergeRosetta(getRole(), o.getRole(), this::getOrCreateRole);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NaturalPersonRole _that = getType().cast(o);
		
			if (!Objects.equals(personReference, _that.getPersonReference())) return false;
			if (!ListEquals.listEquals(role, _that.getRole())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (personReference != null ? personReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NaturalPersonRoleBuilder {" +
				"personReference=" + this.personReference + ", " +
				"role=" + this.role +
			'}';
		}
	}
}
