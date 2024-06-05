package cdm.base.staticdata.party;

import cdm.base.staticdata.party.PartyIdentifier;
import cdm.base.staticdata.party.PartyIdentifier.PartyIdentifierBuilder;
import cdm.base.staticdata.party.PartyIdentifier.PartyIdentifierBuilderImpl;
import cdm.base.staticdata.party.PartyIdentifier.PartyIdentifierImpl;
import cdm.base.staticdata.party.PartyIdentifierTypeEnum;
import cdm.base.staticdata.party.meta.PartyIdentifierMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Comprises an identifier and a source. The associated metadata key denotes the ability to associate a hash value to the PartyIdentifier instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="PartyIdentifier", builder=PartyIdentifier.PartyIdentifierBuilderImpl.class, version="${project.version}")
public interface PartyIdentifier extends RosettaModelObject, GlobalKey {

	PartyIdentifierMeta metaData = new PartyIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Provides an identifier associated with a party. The identifier is unique within the public source specified in the source attribute.
	 */
	FieldWithMetaString getIdentifier();
	/**
	 * Defines the source of the identifier.
	 */
	PartyIdentifierTypeEnum getIdentifierType();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PartyIdentifier build();
	
	PartyIdentifier.PartyIdentifierBuilder toBuilder();
	
	static PartyIdentifier.PartyIdentifierBuilder builder() {
		return new PartyIdentifier.PartyIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartyIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartyIdentifier> getType() {
		return PartyIdentifier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.class, getIdentifier());
		processor.processBasic(path.newSubPath("identifierType"), PartyIdentifierTypeEnum.class, getIdentifierType(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartyIdentifierBuilder extends PartyIdentifier, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIdentifier();
		FieldWithMetaString.FieldWithMetaStringBuilder getIdentifier();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PartyIdentifier.PartyIdentifierBuilder setIdentifier(FieldWithMetaString identifier0);
		PartyIdentifier.PartyIdentifierBuilder setIdentifierValue(String identifier1);
		PartyIdentifier.PartyIdentifierBuilder setIdentifierType(PartyIdentifierTypeEnum identifierType);
		PartyIdentifier.PartyIdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIdentifier());
			processor.processBasic(path.newSubPath("identifierType"), PartyIdentifierTypeEnum.class, getIdentifierType(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PartyIdentifier.PartyIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of PartyIdentifier  ***********************/
	class PartyIdentifierImpl implements PartyIdentifier {
		private final FieldWithMetaString identifier;
		private final PartyIdentifierTypeEnum identifierType;
		private final MetaFields meta;
		
		protected PartyIdentifierImpl(PartyIdentifier.PartyIdentifierBuilder builder) {
			this.identifier = ofNullable(builder.getIdentifier()).map(f->f.build()).orElse(null);
			this.identifierType = builder.getIdentifierType();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifier")
		public FieldWithMetaString getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("identifierType")
		public PartyIdentifierTypeEnum getIdentifierType() {
			return identifierType;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PartyIdentifier build() {
			return this;
		}
		
		@Override
		public PartyIdentifier.PartyIdentifierBuilder toBuilder() {
			PartyIdentifier.PartyIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartyIdentifier.PartyIdentifierBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getIdentifierType()).ifPresent(builder::setIdentifierType);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(identifierType, _that.getIdentifierType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (identifierType != null ? identifierType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyIdentifier {" +
				"identifier=" + this.identifier + ", " +
				"identifierType=" + this.identifierType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PartyIdentifier  ***********************/
	class PartyIdentifierBuilderImpl implements PartyIdentifier.PartyIdentifierBuilder, GlobalKeyBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder identifier;
		protected PartyIdentifierTypeEnum identifierType;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PartyIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifier")
		public FieldWithMetaString.FieldWithMetaStringBuilder getIdentifier() {
			return identifier;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIdentifier() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (identifier!=null) {
				result = identifier;
			}
			else {
				result = identifier = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("identifierType")
		public PartyIdentifierTypeEnum getIdentifierType() {
			return identifierType;
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
		@RosettaAttribute("identifier")
		public PartyIdentifier.PartyIdentifierBuilder setIdentifier(FieldWithMetaString identifier) {
			this.identifier = identifier==null?null:identifier.toBuilder();
			return this;
		}
		@Override
		public PartyIdentifier.PartyIdentifierBuilder setIdentifierValue(String identifier) {
			this.getOrCreateIdentifier().setValue(identifier);
			return this;
		}
		@Override
		@RosettaAttribute("identifierType")
		public PartyIdentifier.PartyIdentifierBuilder setIdentifierType(PartyIdentifierTypeEnum identifierType) {
			this.identifierType = identifierType==null?null:identifierType;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PartyIdentifier.PartyIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PartyIdentifier build() {
			return new PartyIdentifier.PartyIdentifierImpl(this);
		}
		
		@Override
		public PartyIdentifier.PartyIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyIdentifier.PartyIdentifierBuilder prune() {
			if (identifier!=null && !identifier.prune().hasData()) identifier = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null) return true;
			if (getIdentifierType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartyIdentifier.PartyIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartyIdentifier.PartyIdentifierBuilder o = (PartyIdentifier.PartyIdentifierBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getIdentifierType(), o.getIdentifierType(), this::setIdentifierType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartyIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(identifierType, _that.getIdentifierType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (identifierType != null ? identifierType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartyIdentifierBuilder {" +
				"identifier=" + this.identifier + ", " +
				"identifierType=" + this.identifierType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
