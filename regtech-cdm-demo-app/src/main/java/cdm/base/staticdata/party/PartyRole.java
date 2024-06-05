package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRole.PartyRoleBuilder;
import cdm.base.staticdata.party.PartyRole.PartyRoleBuilderImpl;
import cdm.base.staticdata.party.PartyRole.PartyRoleImpl;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.meta.PartyRoleMeta;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
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
 * A class to specify the role(s) that party(ies) may have in relation to the execution, contract or other legal agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="PartyRole", builder=PartyRole.PartyRoleBuilderImpl.class, version="${project.version}")
public interface PartyRole extends RosettaModelObject {

	PartyRoleMeta metaData = new PartyRoleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A reference to the party to which the role refers to.
	 */
	ReferenceWithMetaParty getPartyReference();
	/**
	 * The party role.
	 */
	PartyRoleEnum getRole();
	/**
	 * A reference to the party that has ownership of this party role information. FpML specifies that For shared trade information, this attribute will reference the originator of the data (for example, an execution facility or clearing house).
	 */
	ReferenceWithMetaParty getOwnershipPartyReference();

	/*********************** Build Methods  ***********************/
	PartyRole build();
	
	PartyRole.PartyRoleBuilder toBuilder();
	
	static PartyRole.PartyRoleBuilder builder() {
		return new PartyRole.PartyRoleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartyRole> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartyRole> getType() {
		return PartyRole.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
		processor.processBasic(path.newSubPath("role"), PartyRoleEnum.class, getRole(), this);
		processRosetta(path.newSubPath("ownershipPartyReference"), processor, ReferenceWithMetaParty.class, getOwnershipPartyReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyRoleBuilder extends PartyRole, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateOwnershipPartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOwnershipPartyReference();
		PartyRole.PartyRoleBuilder setPartyReference(ReferenceWithMetaParty partyReference0);
		PartyRole.PartyRoleBuilder setPartyReferenceValue(Party partyReference1);
		PartyRole.PartyRoleBuilder setRole(PartyRoleEnum role);
		PartyRole.PartyRoleBuilder setOwnershipPartyReference(ReferenceWithMetaParty ownershipPartyReference0);
		PartyRole.PartyRoleBuilder setOwnershipPartyReferenceValue(Party ownershipPartyReference1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
			processor.processBasic(path.newSubPath("role"), PartyRoleEnum.class, getRole(), this);
			processRosetta(path.newSubPath("ownershipPartyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getOwnershipPartyReference());
		}
		

		PartyRole.PartyRoleBuilder prune();
	}

	/*********************** Immutable Implementation of PartyRole  ***********************/
	class PartyRoleImpl implements PartyRole {
		private final ReferenceWithMetaParty partyReference;
		private final PartyRoleEnum role;
		private final ReferenceWithMetaParty ownershipPartyReference;
		
		protected PartyRoleImpl(PartyRole.PartyRoleBuilder builder) {
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
			this.role = builder.getRole();
			this.ownershipPartyReference = ofNullable(builder.getOwnershipPartyReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		@RosettaAttribute("role")
		public PartyRoleEnum getRole() {
			return role;
		}
		
		@Override
		@RosettaAttribute("ownershipPartyReference")
		public ReferenceWithMetaParty getOwnershipPartyReference() {
			return ownershipPartyReference;
		}
		
		@Override
		public PartyRole build() {
			return this;
		}
		
		@Override
		public PartyRole.PartyRoleBuilder toBuilder() {
			PartyRole.PartyRoleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartyRole.PartyRoleBuilder builder) {
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
			ofNullable(getRole()).ifPresent(builder::setRole);
			ofNullable(getOwnershipPartyReference()).ifPresent(builder::setOwnershipPartyReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyRole _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(role, _that.getRole())) return false;
			if (!Objects.equals(ownershipPartyReference, _that.getOwnershipPartyReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (ownershipPartyReference != null ? ownershipPartyReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyRole {" +
				"partyReference=" + this.partyReference + ", " +
				"role=" + this.role + ", " +
				"ownershipPartyReference=" + this.ownershipPartyReference +
			'}';
		}
	}

	/*********************** Builder Implementation of PartyRole  ***********************/
	class PartyRoleBuilderImpl implements PartyRole.PartyRoleBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
		protected PartyRoleEnum role;
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder ownershipPartyReference;
	
		public PartyRoleBuilderImpl() {
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
		@RosettaAttribute("role")
		public PartyRoleEnum getRole() {
			return role;
		}
		
		@Override
		@RosettaAttribute("ownershipPartyReference")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOwnershipPartyReference() {
			return ownershipPartyReference;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateOwnershipPartyReference() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (ownershipPartyReference!=null) {
				result = ownershipPartyReference;
			}
			else {
				result = ownershipPartyReference = ReferenceWithMetaParty.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("partyReference")
		public PartyRole.PartyRoleBuilder setPartyReference(ReferenceWithMetaParty partyReference) {
			this.partyReference = partyReference==null?null:partyReference.toBuilder();
			return this;
		}
		@Override
		public PartyRole.PartyRoleBuilder setPartyReferenceValue(Party partyReference) {
			this.getOrCreatePartyReference().setValue(partyReference);
			return this;
		}
		@Override
		@RosettaAttribute("role")
		public PartyRole.PartyRoleBuilder setRole(PartyRoleEnum role) {
			this.role = role==null?null:role;
			return this;
		}
		@Override
		@RosettaAttribute("ownershipPartyReference")
		public PartyRole.PartyRoleBuilder setOwnershipPartyReference(ReferenceWithMetaParty ownershipPartyReference) {
			this.ownershipPartyReference = ownershipPartyReference==null?null:ownershipPartyReference.toBuilder();
			return this;
		}
		@Override
		public PartyRole.PartyRoleBuilder setOwnershipPartyReferenceValue(Party ownershipPartyReference) {
			this.getOrCreateOwnershipPartyReference().setValue(ownershipPartyReference);
			return this;
		}
		
		@Override
		public PartyRole build() {
			return new PartyRole.PartyRoleImpl(this);
		}
		
		@Override
		public PartyRole.PartyRoleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyRole.PartyRoleBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			if (ownershipPartyReference!=null && !ownershipPartyReference.prune().hasData()) ownershipPartyReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			if (getRole()!=null) return true;
			if (getOwnershipPartyReference()!=null && getOwnershipPartyReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyRole.PartyRoleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartyRole.PartyRoleBuilder o = (PartyRole.PartyRoleBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			merger.mergeRosetta(getOwnershipPartyReference(), o.getOwnershipPartyReference(), this::setOwnershipPartyReference);
			
			merger.mergeBasic(getRole(), o.getRole(), this::setRole);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyRole _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(role, _that.getRole())) return false;
			if (!Objects.equals(ownershipPartyReference, _that.getOwnershipPartyReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (ownershipPartyReference != null ? ownershipPartyReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyRoleBuilder {" +
				"partyReference=" + this.partyReference + ", " +
				"role=" + this.role + ", " +
				"ownershipPartyReference=" + this.ownershipPartyReference +
			'}';
		}
	}
}
