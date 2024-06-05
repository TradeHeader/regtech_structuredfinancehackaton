package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Counterparty.CounterpartyBuilder;
import cdm.base.staticdata.party.Counterparty.CounterpartyBuilderImpl;
import cdm.base.staticdata.party.Counterparty.CounterpartyImpl;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.meta.CounterpartyMeta;
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
 * Defines a counterparty enumerated value, e.g. Party1 or Party2, with an associated party reference. The product is agnostic to the actual parties to the transaction, with the party references abstracted away from the product definition and replaced by the CounterpartyEnum (e.g. values Party1 or Party2). The CounterpartyEnum can then be positioned in the product (e.g. to specify which counterparty is the payer, receiver etc) and this Counterparty type, which is positioned outside of the product definition, allows the CounterpartyEnum to be associated with an actual party reference.
 * @version ${project.version}
 */
@RosettaDataType(value="Counterparty", builder=Counterparty.CounterpartyBuilderImpl.class, version="${project.version}")
public interface Counterparty extends RosettaModelObject {

	CounterpartyMeta metaData = new CounterpartyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the CounterpartyEnum, e.g. either Party1 or Party2, that is associated to the partyReference.
	 */
	CounterpartyRoleEnum getRole();
	/**
	 * Specifies the party that is associated to the counterparty.
	 */
	ReferenceWithMetaParty getPartyReference();

	/*********************** Build Methods  ***********************/
	Counterparty build();
	
	Counterparty.CounterpartyBuilder toBuilder();
	
	static Counterparty.CounterpartyBuilder builder() {
		return new Counterparty.CounterpartyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Counterparty> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Counterparty> getType() {
		return Counterparty.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("role"), CounterpartyRoleEnum.class, getRole(), this);
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CounterpartyBuilder extends Counterparty, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		Counterparty.CounterpartyBuilder setRole(CounterpartyRoleEnum role);
		Counterparty.CounterpartyBuilder setPartyReference(ReferenceWithMetaParty partyReference0);
		Counterparty.CounterpartyBuilder setPartyReferenceValue(Party partyReference1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("role"), CounterpartyRoleEnum.class, getRole(), this);
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
		}
		

		Counterparty.CounterpartyBuilder prune();
	}

	/*********************** Immutable Implementation of Counterparty  ***********************/
	class CounterpartyImpl implements Counterparty {
		private final CounterpartyRoleEnum role;
		private final ReferenceWithMetaParty partyReference;
		
		protected CounterpartyImpl(Counterparty.CounterpartyBuilder builder) {
			this.role = builder.getRole();
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("role")
		public CounterpartyRoleEnum getRole() {
			return role;
		}
		
		@Override
		@RosettaAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		public Counterparty build() {
			return this;
		}
		
		@Override
		public Counterparty.CounterpartyBuilder toBuilder() {
			Counterparty.CounterpartyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Counterparty.CounterpartyBuilder builder) {
			ofNullable(getRole()).ifPresent(builder::setRole);
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Counterparty _that = getType().cast(o);
		
			if (!Objects.equals(role, _that.getRole())) return false;
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Counterparty {" +
				"role=" + this.role + ", " +
				"partyReference=" + this.partyReference +
			'}';
		}
	}

	/*********************** Builder Implementation of Counterparty  ***********************/
	class CounterpartyBuilderImpl implements Counterparty.CounterpartyBuilder {
	
		protected CounterpartyRoleEnum role;
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
	
		public CounterpartyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("role")
		public CounterpartyRoleEnum getRole() {
			return role;
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
		public Counterparty.CounterpartyBuilder setRole(CounterpartyRoleEnum role) {
			this.role = role==null?null:role;
			return this;
		}
		@Override
		@RosettaAttribute("partyReference")
		public Counterparty.CounterpartyBuilder setPartyReference(ReferenceWithMetaParty partyReference) {
			this.partyReference = partyReference==null?null:partyReference.toBuilder();
			return this;
		}
		@Override
		public Counterparty.CounterpartyBuilder setPartyReferenceValue(Party partyReference) {
			this.getOrCreatePartyReference().setValue(partyReference);
			return this;
		}
		
		@Override
		public Counterparty build() {
			return new Counterparty.CounterpartyImpl(this);
		}
		
		@Override
		public Counterparty.CounterpartyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Counterparty.CounterpartyBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRole()!=null) return true;
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Counterparty.CounterpartyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Counterparty.CounterpartyBuilder o = (Counterparty.CounterpartyBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			
			merger.mergeBasic(getRole(), o.getRole(), this::setRole);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Counterparty _that = getType().cast(o);
		
			if (!Objects.equals(role, _that.getRole())) return false;
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (role != null ? role.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CounterpartyBuilder {" +
				"role=" + this.role + ", " +
				"partyReference=" + this.partyReference +
			'}';
		}
	}
}
