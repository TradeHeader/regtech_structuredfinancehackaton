package cdm.base.staticdata.identifier;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.CommodityLocationIdentifierTypeEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilderImpl;
import cdm.base.staticdata.identifier.Identifier.IdentifierImpl;
import cdm.base.staticdata.identifier.LocationIdentifier;
import cdm.base.staticdata.identifier.LocationIdentifier.LocationIdentifierBuilder;
import cdm.base.staticdata.identifier.LocationIdentifier.LocationIdentifierBuilderImpl;
import cdm.base.staticdata.identifier.LocationIdentifier.LocationIdentifierImpl;
import cdm.base.staticdata.identifier.meta.LocationIdentifierMeta;
import cdm.base.staticdata.party.Party;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies a location identifier. An issuer and an identifier type can be associated with the actual identifier value as a way to properly qualify it.
 * @version ${project.version}
 */
@RosettaDataType(value="LocationIdentifier", builder=LocationIdentifier.LocationIdentifierBuilderImpl.class, version="${project.version}")
public interface LocationIdentifier extends Identifier {

	LocationIdentifierMeta metaData = new LocationIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the nature of a location identifier.
	 */
	CommodityLocationIdentifierTypeEnum getLocationIdentifierType();

	/*********************** Build Methods  ***********************/
	LocationIdentifier build();
	
	LocationIdentifier.LocationIdentifierBuilder toBuilder();
	
	static LocationIdentifier.LocationIdentifierBuilder builder() {
		return new LocationIdentifier.LocationIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LocationIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LocationIdentifier> getType() {
		return LocationIdentifier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.class, getIssuerReference());
		processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.class, getIssuer());
		processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.class, getAssignedIdentifier());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("locationIdentifierType"), CommodityLocationIdentifierTypeEnum.class, getLocationIdentifierType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface LocationIdentifierBuilder extends LocationIdentifier, Identifier.IdentifierBuilder, RosettaModelObjectBuilder {
		LocationIdentifier.LocationIdentifierBuilder setIssuerReference(ReferenceWithMetaParty issuerReference0);
		LocationIdentifier.LocationIdentifierBuilder setIssuerReferenceValue(Party issuerReference1);
		LocationIdentifier.LocationIdentifierBuilder setIssuer(FieldWithMetaString issuer0);
		LocationIdentifier.LocationIdentifierBuilder setIssuerValue(String issuer1);
		LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier0);
		LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier1, int _idx);
		LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier2);
		LocationIdentifier.LocationIdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier3);
		LocationIdentifier.LocationIdentifierBuilder setMeta(MetaFields meta);
		LocationIdentifier.LocationIdentifierBuilder setLocationIdentifierType(CommodityLocationIdentifierTypeEnum locationIdentifierType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getIssuerReference());
			processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIssuer());
			processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.AssignedIdentifierBuilder.class, getAssignedIdentifier());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("locationIdentifierType"), CommodityLocationIdentifierTypeEnum.class, getLocationIdentifierType(), this);
		}
		

		LocationIdentifier.LocationIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of LocationIdentifier  ***********************/
	class LocationIdentifierImpl extends Identifier.IdentifierImpl implements LocationIdentifier {
		private final CommodityLocationIdentifierTypeEnum locationIdentifierType;
		
		protected LocationIdentifierImpl(LocationIdentifier.LocationIdentifierBuilder builder) {
			super(builder);
			this.locationIdentifierType = builder.getLocationIdentifierType();
		}
		
		@Override
		@RosettaAttribute("locationIdentifierType")
		public CommodityLocationIdentifierTypeEnum getLocationIdentifierType() {
			return locationIdentifierType;
		}
		
		@Override
		public LocationIdentifier build() {
			return this;
		}
		
		@Override
		public LocationIdentifier.LocationIdentifierBuilder toBuilder() {
			LocationIdentifier.LocationIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LocationIdentifier.LocationIdentifierBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getLocationIdentifierType()).ifPresent(builder::setLocationIdentifierType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LocationIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(locationIdentifierType, _that.getLocationIdentifierType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (locationIdentifierType != null ? locationIdentifierType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LocationIdentifier {" +
				"locationIdentifierType=" + this.locationIdentifierType +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of LocationIdentifier  ***********************/
	class LocationIdentifierBuilderImpl extends Identifier.IdentifierBuilderImpl  implements LocationIdentifier.LocationIdentifierBuilder {
	
		protected CommodityLocationIdentifierTypeEnum locationIdentifierType;
	
		public LocationIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("locationIdentifierType")
		public CommodityLocationIdentifierTypeEnum getLocationIdentifierType() {
			return locationIdentifierType;
		}
		
	
		@Override
		@RosettaAttribute("issuerReference")
		public LocationIdentifier.LocationIdentifierBuilder setIssuerReference(ReferenceWithMetaParty issuerReference) {
			this.issuerReference = issuerReference==null?null:issuerReference.toBuilder();
			return this;
		}
		@Override
		public LocationIdentifier.LocationIdentifierBuilder setIssuerReferenceValue(Party issuerReference) {
			this.getOrCreateIssuerReference().setValue(issuerReference);
			return this;
		}
		@Override
		@RosettaAttribute("issuer")
		public LocationIdentifier.LocationIdentifierBuilder setIssuer(FieldWithMetaString issuer) {
			this.issuer = issuer==null?null:issuer.toBuilder();
			return this;
		}
		@Override
		public LocationIdentifier.LocationIdentifierBuilder setIssuerValue(String issuer) {
			this.getOrCreateIssuer().setValue(issuer);
			return this;
		}
		@Override
		public LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier) {
			if (assignedIdentifier!=null) this.assignedIdentifier.add(assignedIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier, int _idx) {
			getIndex(this.assignedIdentifier, _idx, () -> assignedIdentifier.toBuilder());
			return this;
		}
		@Override 
		public LocationIdentifier.LocationIdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
			if (assignedIdentifiers != null) {
				for (AssignedIdentifier toAdd : assignedIdentifiers) {
					this.assignedIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("assignedIdentifier")
		public LocationIdentifier.LocationIdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
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
		public LocationIdentifier.LocationIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("locationIdentifierType")
		public LocationIdentifier.LocationIdentifierBuilder setLocationIdentifierType(CommodityLocationIdentifierTypeEnum locationIdentifierType) {
			this.locationIdentifierType = locationIdentifierType==null?null:locationIdentifierType;
			return this;
		}
		
		@Override
		public LocationIdentifier build() {
			return new LocationIdentifier.LocationIdentifierImpl(this);
		}
		
		@Override
		public LocationIdentifier.LocationIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LocationIdentifier.LocationIdentifierBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getLocationIdentifierType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LocationIdentifier.LocationIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			LocationIdentifier.LocationIdentifierBuilder o = (LocationIdentifier.LocationIdentifierBuilder) other;
			
			
			merger.mergeBasic(getLocationIdentifierType(), o.getLocationIdentifierType(), this::setLocationIdentifierType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LocationIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(locationIdentifierType, _that.getLocationIdentifierType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (locationIdentifierType != null ? locationIdentifierType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LocationIdentifierBuilder {" +
				"locationIdentifierType=" + this.locationIdentifierType +
			'}' + " " + super.toString();
		}
	}
}
