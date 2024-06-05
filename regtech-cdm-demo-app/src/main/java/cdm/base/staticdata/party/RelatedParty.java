package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.RelatedParty;
import cdm.base.staticdata.party.RelatedParty.RelatedPartyBuilder;
import cdm.base.staticdata.party.RelatedParty.RelatedPartyBuilderImpl;
import cdm.base.staticdata.party.RelatedParty.RelatedPartyImpl;
import cdm.base.staticdata.party.meta.RelatedPartyMeta;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaAccount;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder;
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
 * @version ${project.version}
 */
@RosettaDataType(value="RelatedParty", builder=RelatedParty.RelatedPartyBuilderImpl.class, version="${project.version}")
public interface RelatedParty extends RosettaModelObject {

	RelatedPartyMeta metaData = new RelatedPartyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Reference to a party.
	 */
	ReferenceWithMetaParty getPartyReference();
	/**
	 * Reference to an account.
	 */
	ReferenceWithMetaAccount getAccountReference();
	/**
	 * The category of the relationship. The related party performs the role specified in this field for the base party. For example, if the role is ,Guarantor, the related party acts as a guarantor for the base party.
	 */
	PartyRoleEnum getRole();

	/*********************** Build Methods  ***********************/
	RelatedParty build();
	
	RelatedParty.RelatedPartyBuilder toBuilder();
	
	static RelatedParty.RelatedPartyBuilder builder() {
		return new RelatedParty.RelatedPartyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RelatedParty> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RelatedParty> getType() {
		return RelatedParty.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
		processRosetta(path.newSubPath("accountReference"), processor, ReferenceWithMetaAccount.class, getAccountReference());
		processor.processBasic(path.newSubPath("role"), PartyRoleEnum.class, getRole(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface RelatedPartyBuilder extends RelatedParty, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder getOrCreateAccountReference();
		ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder getAccountReference();
		RelatedParty.RelatedPartyBuilder setPartyReference(ReferenceWithMetaParty partyReference0);
		RelatedParty.RelatedPartyBuilder setPartyReferenceValue(Party partyReference1);
		RelatedParty.RelatedPartyBuilder setAccountReference(ReferenceWithMetaAccount accountReference0);
		RelatedParty.RelatedPartyBuilder setAccountReferenceValue(Account accountReference1);
		RelatedParty.RelatedPartyBuilder setRole(PartyRoleEnum role);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
			processRosetta(path.newSubPath("accountReference"), processor, ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder.class, getAccountReference());
			processor.processBasic(path.newSubPath("role"), PartyRoleEnum.class, getRole(), this);
		}
		

		RelatedParty.RelatedPartyBuilder prune();
	}

	/*********************** Immutable Implementation of RelatedParty  ***********************/
	class RelatedPartyImpl implements RelatedParty {
		private final ReferenceWithMetaParty partyReference;
		private final ReferenceWithMetaAccount accountReference;
		private final PartyRoleEnum role;
		
		protected RelatedPartyImpl(RelatedParty.RelatedPartyBuilder builder) {
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
			this.accountReference = ofNullable(builder.getAccountReference()).map(f->f.build()).orElse(null);
			this.role = builder.getRole();
		}
		
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		@RosettaAttribute("accountReference")
		public ReferenceWithMetaAccount getAccountReference() {
			return accountReference;
		}
		
		@Override
		@RosettaAttribute("role")
		public PartyRoleEnum getRole() {
			return role;
		}
		
		@Override
		public RelatedParty build() {
			return this;
		}
		
		@Override
		public RelatedParty.RelatedPartyBuilder toBuilder() {
			RelatedParty.RelatedPartyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RelatedParty.RelatedPartyBuilder builder) {
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
			ofNullable(getAccountReference()).ifPresent(builder::setAccountReference);
			ofNullable(getRole()).ifPresent(builder::setRole);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RelatedParty _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(accountReference, _that.getAccountReference())) return false;
			if (!Objects.equals(role, _that.getRole())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (accountReference != null ? accountReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelatedParty {" +
				"partyReference=" + this.partyReference + ", " +
				"accountReference=" + this.accountReference + ", " +
				"role=" + this.role +
			'}';
		}
	}

	/*********************** Builder Implementation of RelatedParty  ***********************/
	class RelatedPartyBuilderImpl implements RelatedParty.RelatedPartyBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
		protected ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder accountReference;
		protected PartyRoleEnum role;
	
		public RelatedPartyBuilderImpl() {
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
		@RosettaAttribute("accountReference")
		public ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder getAccountReference() {
			return accountReference;
		}
		
		@Override
		public ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder getOrCreateAccountReference() {
			ReferenceWithMetaAccount.ReferenceWithMetaAccountBuilder result;
			if (accountReference!=null) {
				result = accountReference;
			}
			else {
				result = accountReference = ReferenceWithMetaAccount.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("role")
		public PartyRoleEnum getRole() {
			return role;
		}
		
	
		@Override
		@RosettaAttribute("partyReference")
		public RelatedParty.RelatedPartyBuilder setPartyReference(ReferenceWithMetaParty partyReference) {
			this.partyReference = partyReference==null?null:partyReference.toBuilder();
			return this;
		}
		@Override
		public RelatedParty.RelatedPartyBuilder setPartyReferenceValue(Party partyReference) {
			this.getOrCreatePartyReference().setValue(partyReference);
			return this;
		}
		@Override
		@RosettaAttribute("accountReference")
		public RelatedParty.RelatedPartyBuilder setAccountReference(ReferenceWithMetaAccount accountReference) {
			this.accountReference = accountReference==null?null:accountReference.toBuilder();
			return this;
		}
		@Override
		public RelatedParty.RelatedPartyBuilder setAccountReferenceValue(Account accountReference) {
			this.getOrCreateAccountReference().setValue(accountReference);
			return this;
		}
		@Override
		@RosettaAttribute("role")
		public RelatedParty.RelatedPartyBuilder setRole(PartyRoleEnum role) {
			this.role = role==null?null:role;
			return this;
		}
		
		@Override
		public RelatedParty build() {
			return new RelatedParty.RelatedPartyImpl(this);
		}
		
		@Override
		public RelatedParty.RelatedPartyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelatedParty.RelatedPartyBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			if (accountReference!=null && !accountReference.prune().hasData()) accountReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			if (getAccountReference()!=null && getAccountReference().hasData()) return true;
			if (getRole()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelatedParty.RelatedPartyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RelatedParty.RelatedPartyBuilder o = (RelatedParty.RelatedPartyBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			merger.mergeRosetta(getAccountReference(), o.getAccountReference(), this::setAccountReference);
			
			merger.mergeBasic(getRole(), o.getRole(), this::setRole);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RelatedParty _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(accountReference, _that.getAccountReference())) return false;
			if (!Objects.equals(role, _that.getRole())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (accountReference != null ? accountReference.hashCode() : 0);
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelatedPartyBuilder {" +
				"partyReference=" + this.partyReference + ", " +
				"accountReference=" + this.accountReference + ", " +
				"role=" + this.role +
			'}';
		}
	}
}
