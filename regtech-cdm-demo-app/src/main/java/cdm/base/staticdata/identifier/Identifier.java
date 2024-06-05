package cdm.base.staticdata.identifier;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilderImpl;
import cdm.base.staticdata.identifier.Identifier.IdentifierImpl;
import cdm.base.staticdata.identifier.meta.IdentifierMeta;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify a generic identifier, applicable to CDM artefacts such as executions, contracts, lifecycle events and legal documents. An issuer can be associated with the actual identifier value as a way to properly qualify it.
 * @version ${project.version}
 *
 * Body ICMA
 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
 * namingConvention "Identifier"
 *
 * Provision As referenced in GMRA paragraph 3(b) Securities may be identified using identifying numbers such as CUSIP or ISIN
 *
 */
@RosettaDataType(value="Identifier", builder=Identifier.IdentifierBuilderImpl.class, version="${project.version}")
public interface Identifier extends RosettaModelObject, GlobalKey {

	IdentifierMeta metaData = new IdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The identifier issuer, when specified by reference to a party specified as part of the transaction.
	 */
	ReferenceWithMetaParty getIssuerReference();
	/**
	 * The identifier issuer, when specified explicitly alongside the identifier value (instead of being specified by reference to a party).
	 */
	FieldWithMetaString getIssuer();
	/**
	 * The identifier value. This level of indirection between the issuer and the identifier and its version provides the ability to associate multiple identifiers to one issuer, consistently with the FpML PartyTradeIdentifier.
	 */
	List<? extends AssignedIdentifier> getAssignedIdentifier();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Identifier build();
	
	Identifier.IdentifierBuilder toBuilder();
	
	static Identifier.IdentifierBuilder builder() {
		return new Identifier.IdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Identifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Identifier> getType() {
		return Identifier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.class, getIssuerReference());
		processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.class, getIssuer());
		processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.class, getAssignedIdentifier());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface IdentifierBuilder extends Identifier, RosettaModelObjectBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateIssuerReference();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getIssuerReference();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIssuer();
		FieldWithMetaString.FieldWithMetaStringBuilder getIssuer();
		AssignedIdentifier.AssignedIdentifierBuilder getOrCreateAssignedIdentifier(int _index);
		List<? extends AssignedIdentifier.AssignedIdentifierBuilder> getAssignedIdentifier();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Identifier.IdentifierBuilder setIssuerReference(ReferenceWithMetaParty issuerReference0);
		Identifier.IdentifierBuilder setIssuerReferenceValue(Party issuerReference1);
		Identifier.IdentifierBuilder setIssuer(FieldWithMetaString issuer0);
		Identifier.IdentifierBuilder setIssuerValue(String issuer1);
		Identifier.IdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier0);
		Identifier.IdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier1, int _idx);
		Identifier.IdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier2);
		Identifier.IdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier3);
		Identifier.IdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getIssuerReference());
			processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIssuer());
			processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.AssignedIdentifierBuilder.class, getAssignedIdentifier());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Identifier.IdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of Identifier  ***********************/
	class IdentifierImpl implements Identifier {
		private final ReferenceWithMetaParty issuerReference;
		private final FieldWithMetaString issuer;
		private final List<? extends AssignedIdentifier> assignedIdentifier;
		private final MetaFields meta;
		
		protected IdentifierImpl(Identifier.IdentifierBuilder builder) {
			this.issuerReference = ofNullable(builder.getIssuerReference()).map(f->f.build()).orElse(null);
			this.issuer = ofNullable(builder.getIssuer()).map(f->f.build()).orElse(null);
			this.assignedIdentifier = ofNullable(builder.getAssignedIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("issuerReference")
		public ReferenceWithMetaParty getIssuerReference() {
			return issuerReference;
		}
		
		@Override
		@RosettaAttribute("issuer")
		public FieldWithMetaString getIssuer() {
			return issuer;
		}
		
		@Override
		@RosettaAttribute("assignedIdentifier")
		public List<? extends AssignedIdentifier> getAssignedIdentifier() {
			return assignedIdentifier;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Identifier build() {
			return this;
		}
		
		@Override
		public Identifier.IdentifierBuilder toBuilder() {
			Identifier.IdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Identifier.IdentifierBuilder builder) {
			ofNullable(getIssuerReference()).ifPresent(builder::setIssuerReference);
			ofNullable(getIssuer()).ifPresent(builder::setIssuer);
			ofNullable(getAssignedIdentifier()).ifPresent(builder::setAssignedIdentifier);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Identifier _that = getType().cast(o);
		
			if (!Objects.equals(issuerReference, _that.getIssuerReference())) return false;
			if (!Objects.equals(issuer, _that.getIssuer())) return false;
			if (!ListEquals.listEquals(assignedIdentifier, _that.getAssignedIdentifier())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerReference != null ? issuerReference.hashCode() : 0);
			_result = 31 * _result + (issuer != null ? issuer.hashCode() : 0);
			_result = 31 * _result + (assignedIdentifier != null ? assignedIdentifier.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Identifier {" +
				"issuerReference=" + this.issuerReference + ", " +
				"issuer=" + this.issuer + ", " +
				"assignedIdentifier=" + this.assignedIdentifier + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Identifier  ***********************/
	class IdentifierBuilderImpl implements Identifier.IdentifierBuilder, GlobalKeyBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder issuerReference;
		protected FieldWithMetaString.FieldWithMetaStringBuilder issuer;
		protected List<AssignedIdentifier.AssignedIdentifierBuilder> assignedIdentifier = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public IdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("issuerReference")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getIssuerReference() {
			return issuerReference;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateIssuerReference() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (issuerReference!=null) {
				result = issuerReference;
			}
			else {
				result = issuerReference = ReferenceWithMetaParty.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("issuer")
		public FieldWithMetaString.FieldWithMetaStringBuilder getIssuer() {
			return issuer;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIssuer() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (issuer!=null) {
				result = issuer;
			}
			else {
				result = issuer = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("assignedIdentifier")
		public List<? extends AssignedIdentifier.AssignedIdentifierBuilder> getAssignedIdentifier() {
			return assignedIdentifier;
		}
		
		public AssignedIdentifier.AssignedIdentifierBuilder getOrCreateAssignedIdentifier(int _index) {
		
			if (assignedIdentifier==null) {
				this.assignedIdentifier = new ArrayList<>();
			}
			AssignedIdentifier.AssignedIdentifierBuilder result;
			return getIndex(assignedIdentifier, _index, () -> {
						AssignedIdentifier.AssignedIdentifierBuilder newAssignedIdentifier = AssignedIdentifier.builder();
						return newAssignedIdentifier;
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
		@RosettaAttribute("issuerReference")
		public Identifier.IdentifierBuilder setIssuerReference(ReferenceWithMetaParty issuerReference) {
			this.issuerReference = issuerReference==null?null:issuerReference.toBuilder();
			return this;
		}
		@Override
		public Identifier.IdentifierBuilder setIssuerReferenceValue(Party issuerReference) {
			this.getOrCreateIssuerReference().setValue(issuerReference);
			return this;
		}
		@Override
		@RosettaAttribute("issuer")
		public Identifier.IdentifierBuilder setIssuer(FieldWithMetaString issuer) {
			this.issuer = issuer==null?null:issuer.toBuilder();
			return this;
		}
		@Override
		public Identifier.IdentifierBuilder setIssuerValue(String issuer) {
			this.getOrCreateIssuer().setValue(issuer);
			return this;
		}
		@Override
		public Identifier.IdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier) {
			if (assignedIdentifier!=null) this.assignedIdentifier.add(assignedIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Identifier.IdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier, int _idx) {
			getIndex(this.assignedIdentifier, _idx, () -> assignedIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Identifier.IdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
			if (assignedIdentifiers != null) {
				for (AssignedIdentifier toAdd : assignedIdentifiers) {
					this.assignedIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("assignedIdentifier")
		public Identifier.IdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
			if (assignedIdentifiers == null)  {
				this.assignedIdentifier = new ArrayList<>();
			}
			else {
				this.assignedIdentifier = assignedIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public Identifier.IdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Identifier build() {
			return new Identifier.IdentifierImpl(this);
		}
		
		@Override
		public Identifier.IdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Identifier.IdentifierBuilder prune() {
			if (issuerReference!=null && !issuerReference.prune().hasData()) issuerReference = null;
			if (issuer!=null && !issuer.prune().hasData()) issuer = null;
			assignedIdentifier = assignedIdentifier.stream().filter(b->b!=null).<AssignedIdentifier.AssignedIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIssuerReference()!=null && getIssuerReference().hasData()) return true;
			if (getIssuer()!=null) return true;
			if (getAssignedIdentifier()!=null && getAssignedIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Identifier.IdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Identifier.IdentifierBuilder o = (Identifier.IdentifierBuilder) other;
			
			merger.mergeRosetta(getIssuerReference(), o.getIssuerReference(), this::setIssuerReference);
			merger.mergeRosetta(getIssuer(), o.getIssuer(), this::setIssuer);
			merger.mergeRosetta(getAssignedIdentifier(), o.getAssignedIdentifier(), this::getOrCreateAssignedIdentifier);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Identifier _that = getType().cast(o);
		
			if (!Objects.equals(issuerReference, _that.getIssuerReference())) return false;
			if (!Objects.equals(issuer, _that.getIssuer())) return false;
			if (!ListEquals.listEquals(assignedIdentifier, _that.getAssignedIdentifier())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerReference != null ? issuerReference.hashCode() : 0);
			_result = 31 * _result + (issuer != null ? issuer.hashCode() : 0);
			_result = 31 * _result + (assignedIdentifier != null ? assignedIdentifier.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IdentifierBuilder {" +
				"issuerReference=" + this.issuerReference + ", " +
				"issuer=" + this.issuer + ", " +
				"assignedIdentifier=" + this.assignedIdentifier + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
