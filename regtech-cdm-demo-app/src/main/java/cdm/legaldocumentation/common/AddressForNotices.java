package cdm.legaldocumentation.common;

import cdm.base.staticdata.party.PartyContactInformation;
import cdm.legaldocumentation.common.AddressForNotices;
import cdm.legaldocumentation.common.AddressForNotices.AddressForNoticesBuilder;
import cdm.legaldocumentation.common.AddressForNotices.AddressForNoticesBuilderImpl;
import cdm.legaldocumentation.common.AddressForNotices.AddressForNoticesImpl;
import cdm.legaldocumentation.common.meta.AddressForNoticesMeta;
import cdm.product.collateral.ContactElection;
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
 * Specification of the address and other details for notices.
 * @version ${project.version}
 */
@RosettaDataType(value="AddressForNotices", builder=AddressForNotices.AddressForNoticesBuilderImpl.class, version="${project.version}")
public interface AddressForNotices extends RosettaModelObject {

	AddressForNoticesMeta metaData = new AddressForNoticesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specification of primary notice details
	 */
	ContactElection getPrimaryNotices();
	/**
	 * The optional specification of additional information when a party requires notices to be delivered to more than one address.
	 */
	List<? extends PartyContactInformation> getAdditionalNotices();

	/*********************** Build Methods  ***********************/
	AddressForNotices build();
	
	AddressForNotices.AddressForNoticesBuilder toBuilder();
	
	static AddressForNotices.AddressForNoticesBuilder builder() {
		return new AddressForNotices.AddressForNoticesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AddressForNotices> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AddressForNotices> getType() {
		return AddressForNotices.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("primaryNotices"), processor, ContactElection.class, getPrimaryNotices());
		processRosetta(path.newSubPath("additionalNotices"), processor, PartyContactInformation.class, getAdditionalNotices());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AddressForNoticesBuilder extends AddressForNotices, RosettaModelObjectBuilder {
		ContactElection.ContactElectionBuilder getOrCreatePrimaryNotices();
		ContactElection.ContactElectionBuilder getPrimaryNotices();
		PartyContactInformation.PartyContactInformationBuilder getOrCreateAdditionalNotices(int _index);
		List<? extends PartyContactInformation.PartyContactInformationBuilder> getAdditionalNotices();
		AddressForNotices.AddressForNoticesBuilder setPrimaryNotices(ContactElection primaryNotices);
		AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(PartyContactInformation additionalNotices0);
		AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(PartyContactInformation additionalNotices1, int _idx);
		AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(List<? extends PartyContactInformation> additionalNotices2);
		AddressForNotices.AddressForNoticesBuilder setAdditionalNotices(List<? extends PartyContactInformation> additionalNotices3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("primaryNotices"), processor, ContactElection.ContactElectionBuilder.class, getPrimaryNotices());
			processRosetta(path.newSubPath("additionalNotices"), processor, PartyContactInformation.PartyContactInformationBuilder.class, getAdditionalNotices());
		}
		

		AddressForNotices.AddressForNoticesBuilder prune();
	}

	/*********************** Immutable Implementation of AddressForNotices  ***********************/
	class AddressForNoticesImpl implements AddressForNotices {
		private final ContactElection primaryNotices;
		private final List<? extends PartyContactInformation> additionalNotices;
		
		protected AddressForNoticesImpl(AddressForNotices.AddressForNoticesBuilder builder) {
			this.primaryNotices = ofNullable(builder.getPrimaryNotices()).map(f->f.build()).orElse(null);
			this.additionalNotices = ofNullable(builder.getAdditionalNotices()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("primaryNotices")
		public ContactElection getPrimaryNotices() {
			return primaryNotices;
		}
		
		@Override
		@RosettaAttribute("additionalNotices")
		public List<? extends PartyContactInformation> getAdditionalNotices() {
			return additionalNotices;
		}
		
		@Override
		public AddressForNotices build() {
			return this;
		}
		
		@Override
		public AddressForNotices.AddressForNoticesBuilder toBuilder() {
			AddressForNotices.AddressForNoticesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AddressForNotices.AddressForNoticesBuilder builder) {
			ofNullable(getPrimaryNotices()).ifPresent(builder::setPrimaryNotices);
			ofNullable(getAdditionalNotices()).ifPresent(builder::setAdditionalNotices);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AddressForNotices _that = getType().cast(o);
		
			if (!Objects.equals(primaryNotices, _that.getPrimaryNotices())) return false;
			if (!ListEquals.listEquals(additionalNotices, _that.getAdditionalNotices())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primaryNotices != null ? primaryNotices.hashCode() : 0);
			_result = 31 * _result + (additionalNotices != null ? additionalNotices.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AddressForNotices {" +
				"primaryNotices=" + this.primaryNotices + ", " +
				"additionalNotices=" + this.additionalNotices +
			'}';
		}
	}

	/*********************** Builder Implementation of AddressForNotices  ***********************/
	class AddressForNoticesBuilderImpl implements AddressForNotices.AddressForNoticesBuilder {
	
		protected ContactElection.ContactElectionBuilder primaryNotices;
		protected List<PartyContactInformation.PartyContactInformationBuilder> additionalNotices = new ArrayList<>();
	
		public AddressForNoticesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("primaryNotices")
		public ContactElection.ContactElectionBuilder getPrimaryNotices() {
			return primaryNotices;
		}
		
		@Override
		public ContactElection.ContactElectionBuilder getOrCreatePrimaryNotices() {
			ContactElection.ContactElectionBuilder result;
			if (primaryNotices!=null) {
				result = primaryNotices;
			}
			else {
				result = primaryNotices = ContactElection.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("additionalNotices")
		public List<? extends PartyContactInformation.PartyContactInformationBuilder> getAdditionalNotices() {
			return additionalNotices;
		}
		
		public PartyContactInformation.PartyContactInformationBuilder getOrCreateAdditionalNotices(int _index) {
		
			if (additionalNotices==null) {
				this.additionalNotices = new ArrayList<>();
			}
			PartyContactInformation.PartyContactInformationBuilder result;
			return getIndex(additionalNotices, _index, () -> {
						PartyContactInformation.PartyContactInformationBuilder newAdditionalNotices = PartyContactInformation.builder();
						return newAdditionalNotices;
					});
		}
		
	
		@Override
		@RosettaAttribute("primaryNotices")
		public AddressForNotices.AddressForNoticesBuilder setPrimaryNotices(ContactElection primaryNotices) {
			this.primaryNotices = primaryNotices==null?null:primaryNotices.toBuilder();
			return this;
		}
		@Override
		public AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(PartyContactInformation additionalNotices) {
			if (additionalNotices!=null) this.additionalNotices.add(additionalNotices.toBuilder());
			return this;
		}
		
		@Override
		public AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(PartyContactInformation additionalNotices, int _idx) {
			getIndex(this.additionalNotices, _idx, () -> additionalNotices.toBuilder());
			return this;
		}
		@Override 
		public AddressForNotices.AddressForNoticesBuilder addAdditionalNotices(List<? extends PartyContactInformation> additionalNoticess) {
			if (additionalNoticess != null) {
				for (PartyContactInformation toAdd : additionalNoticess) {
					this.additionalNotices.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("additionalNotices")
		public AddressForNotices.AddressForNoticesBuilder setAdditionalNotices(List<? extends PartyContactInformation> additionalNoticess) {
			if (additionalNoticess == null)  {
				this.additionalNotices = new ArrayList<>();
			}
			else {
				this.additionalNotices = additionalNoticess.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public AddressForNotices build() {
			return new AddressForNotices.AddressForNoticesImpl(this);
		}
		
		@Override
		public AddressForNotices.AddressForNoticesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AddressForNotices.AddressForNoticesBuilder prune() {
			if (primaryNotices!=null && !primaryNotices.prune().hasData()) primaryNotices = null;
			additionalNotices = additionalNotices.stream().filter(b->b!=null).<PartyContactInformation.PartyContactInformationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrimaryNotices()!=null && getPrimaryNotices().hasData()) return true;
			if (getAdditionalNotices()!=null && getAdditionalNotices().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AddressForNotices.AddressForNoticesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AddressForNotices.AddressForNoticesBuilder o = (AddressForNotices.AddressForNoticesBuilder) other;
			
			merger.mergeRosetta(getPrimaryNotices(), o.getPrimaryNotices(), this::setPrimaryNotices);
			merger.mergeRosetta(getAdditionalNotices(), o.getAdditionalNotices(), this::getOrCreateAdditionalNotices);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AddressForNotices _that = getType().cast(o);
		
			if (!Objects.equals(primaryNotices, _that.getPrimaryNotices())) return false;
			if (!ListEquals.listEquals(additionalNotices, _that.getAdditionalNotices())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primaryNotices != null ? primaryNotices.hashCode() : 0);
			_result = 31 * _result + (additionalNotices != null ? additionalNotices.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AddressForNoticesBuilder {" +
				"primaryNotices=" + this.primaryNotices + ", " +
				"additionalNotices=" + this.additionalNotices +
			'}';
		}
	}
}
