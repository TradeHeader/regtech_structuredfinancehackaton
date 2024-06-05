package cdm.product.collateral;

import cdm.base.staticdata.party.PartyContactInformation;
import cdm.product.collateral.ContactElection;
import cdm.product.collateral.ContactElection.ContactElectionBuilder;
import cdm.product.collateral.ContactElection.ContactElectionBuilderImpl;
import cdm.product.collateral.ContactElection.ContactElectionImpl;
import cdm.product.collateral.meta.ContactElectionMeta;
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
 * A class to specify the parties&#39; election to specify contact information, in relation to elections such as the Addresses for Transfer or the Demand and Notices as specified in the ISDA Credit Support Annex agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="ContactElection", builder=ContactElection.ContactElectionBuilderImpl.class, version="${project.version}")
public interface ContactElection extends RosettaModelObject {

	ContactElectionMeta metaData = new ContactElectionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The parties&#39; contact information election.
	 */
	List<? extends PartyContactInformation> getPartyElection();

	/*********************** Build Methods  ***********************/
	ContactElection build();
	
	ContactElection.ContactElectionBuilder toBuilder();
	
	static ContactElection.ContactElectionBuilder builder() {
		return new ContactElection.ContactElectionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContactElection> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContactElection> getType() {
		return ContactElection.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyElection"), processor, PartyContactInformation.class, getPartyElection());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContactElectionBuilder extends ContactElection, RosettaModelObjectBuilder {
		PartyContactInformation.PartyContactInformationBuilder getOrCreatePartyElection(int _index);
		List<? extends PartyContactInformation.PartyContactInformationBuilder> getPartyElection();
		ContactElection.ContactElectionBuilder addPartyElection(PartyContactInformation partyElection0);
		ContactElection.ContactElectionBuilder addPartyElection(PartyContactInformation partyElection1, int _idx);
		ContactElection.ContactElectionBuilder addPartyElection(List<? extends PartyContactInformation> partyElection2);
		ContactElection.ContactElectionBuilder setPartyElection(List<? extends PartyContactInformation> partyElection3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyElection"), processor, PartyContactInformation.PartyContactInformationBuilder.class, getPartyElection());
		}
		

		ContactElection.ContactElectionBuilder prune();
	}

	/*********************** Immutable Implementation of ContactElection  ***********************/
	class ContactElectionImpl implements ContactElection {
		private final List<? extends PartyContactInformation> partyElection;
		
		protected ContactElectionImpl(ContactElection.ContactElectionBuilder builder) {
			this.partyElection = ofNullable(builder.getPartyElection()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("partyElection")
		public List<? extends PartyContactInformation> getPartyElection() {
			return partyElection;
		}
		
		@Override
		public ContactElection build() {
			return this;
		}
		
		@Override
		public ContactElection.ContactElectionBuilder toBuilder() {
			ContactElection.ContactElectionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContactElection.ContactElectionBuilder builder) {
			ofNullable(getPartyElection()).ifPresent(builder::setPartyElection);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContactElection _that = getType().cast(o);
		
			if (!ListEquals.listEquals(partyElection, _that.getPartyElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyElection != null ? partyElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContactElection {" +
				"partyElection=" + this.partyElection +
			'}';
		}
	}

	/*********************** Builder Implementation of ContactElection  ***********************/
	class ContactElectionBuilderImpl implements ContactElection.ContactElectionBuilder {
	
		protected List<PartyContactInformation.PartyContactInformationBuilder> partyElection = new ArrayList<>();
	
		public ContactElectionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("partyElection")
		public List<? extends PartyContactInformation.PartyContactInformationBuilder> getPartyElection() {
			return partyElection;
		}
		
		public PartyContactInformation.PartyContactInformationBuilder getOrCreatePartyElection(int _index) {
		
			if (partyElection==null) {
				this.partyElection = new ArrayList<>();
			}
			PartyContactInformation.PartyContactInformationBuilder result;
			return getIndex(partyElection, _index, () -> {
						PartyContactInformation.PartyContactInformationBuilder newPartyElection = PartyContactInformation.builder();
						return newPartyElection;
					});
		}
		
	
		@Override
		public ContactElection.ContactElectionBuilder addPartyElection(PartyContactInformation partyElection) {
			if (partyElection!=null) this.partyElection.add(partyElection.toBuilder());
			return this;
		}
		
		@Override
		public ContactElection.ContactElectionBuilder addPartyElection(PartyContactInformation partyElection, int _idx) {
			getIndex(this.partyElection, _idx, () -> partyElection.toBuilder());
			return this;
		}
		@Override 
		public ContactElection.ContactElectionBuilder addPartyElection(List<? extends PartyContactInformation> partyElections) {
			if (partyElections != null) {
				for (PartyContactInformation toAdd : partyElections) {
					this.partyElection.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("partyElection")
		public ContactElection.ContactElectionBuilder setPartyElection(List<? extends PartyContactInformation> partyElections) {
			if (partyElections == null)  {
				this.partyElection = new ArrayList<>();
			}
			else {
				this.partyElection = partyElections.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ContactElection build() {
			return new ContactElection.ContactElectionImpl(this);
		}
		
		@Override
		public ContactElection.ContactElectionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContactElection.ContactElectionBuilder prune() {
			partyElection = partyElection.stream().filter(b->b!=null).<PartyContactInformation.PartyContactInformationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyElection()!=null && getPartyElection().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContactElection.ContactElectionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContactElection.ContactElectionBuilder o = (ContactElection.ContactElectionBuilder) other;
			
			merger.mergeRosetta(getPartyElection(), o.getPartyElection(), this::getOrCreatePartyElection);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContactElection _that = getType().cast(o);
		
			if (!ListEquals.listEquals(partyElection, _that.getPartyElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyElection != null ? partyElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContactElectionBuilder {" +
				"partyElection=" + this.partyElection +
			'}';
		}
	}
}
