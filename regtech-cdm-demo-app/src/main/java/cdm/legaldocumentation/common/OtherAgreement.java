package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.OtherAgreement;
import cdm.legaldocumentation.common.OtherAgreement.OtherAgreementBuilder;
import cdm.legaldocumentation.common.OtherAgreement.OtherAgreementBuilderImpl;
import cdm.legaldocumentation.common.OtherAgreement.OtherAgreementImpl;
import cdm.legaldocumentation.common.meta.OtherAgreementMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class for defining an agreement executed between parties.
 * @version ${project.version}
 */
@RosettaDataType(value="OtherAgreement", builder=OtherAgreement.OtherAgreementBuilderImpl.class, version="${project.version}")
public interface OtherAgreement extends RosettaModelObject {

	OtherAgreementMeta metaData = new OtherAgreementMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An identifier that has been created to identify the agreement.
	 */
	FieldWithMetaString getIdentifier();
	/**
	 * The agreement executed between the parties and intended to govern product-specific derivatives transactions between those parties.
	 */
	FieldWithMetaString getOtherAgreementType();
	/**
	 * The version of the agreement.
	 */
	FieldWithMetaString getVersion();
	/**
	 * The date on which the agreement was signed.
	 */
	Date getDate();

	/*********************** Build Methods  ***********************/
	OtherAgreement build();
	
	OtherAgreement.OtherAgreementBuilder toBuilder();
	
	static OtherAgreement.OtherAgreementBuilder builder() {
		return new OtherAgreement.OtherAgreementBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OtherAgreement> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OtherAgreement> getType() {
		return OtherAgreement.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.class, getIdentifier());
		processRosetta(path.newSubPath("otherAgreementType"), processor, FieldWithMetaString.class, getOtherAgreementType());
		processRosetta(path.newSubPath("version"), processor, FieldWithMetaString.class, getVersion());
		processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface OtherAgreementBuilder extends OtherAgreement, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIdentifier();
		FieldWithMetaString.FieldWithMetaStringBuilder getIdentifier();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateOtherAgreementType();
		FieldWithMetaString.FieldWithMetaStringBuilder getOtherAgreementType();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateVersion();
		FieldWithMetaString.FieldWithMetaStringBuilder getVersion();
		OtherAgreement.OtherAgreementBuilder setIdentifier(FieldWithMetaString identifier0);
		OtherAgreement.OtherAgreementBuilder setIdentifierValue(String identifier1);
		OtherAgreement.OtherAgreementBuilder setOtherAgreementType(FieldWithMetaString otherAgreementType0);
		OtherAgreement.OtherAgreementBuilder setOtherAgreementTypeValue(String otherAgreementType1);
		OtherAgreement.OtherAgreementBuilder setVersion(FieldWithMetaString version0);
		OtherAgreement.OtherAgreementBuilder setVersionValue(String version1);
		OtherAgreement.OtherAgreementBuilder setDate(Date date);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("otherAgreementType"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getOtherAgreementType());
			processRosetta(path.newSubPath("version"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getVersion());
			processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
		}
		

		OtherAgreement.OtherAgreementBuilder prune();
	}

	/*********************** Immutable Implementation of OtherAgreement  ***********************/
	class OtherAgreementImpl implements OtherAgreement {
		private final FieldWithMetaString identifier;
		private final FieldWithMetaString otherAgreementType;
		private final FieldWithMetaString version;
		private final Date date;
		
		protected OtherAgreementImpl(OtherAgreement.OtherAgreementBuilder builder) {
			this.identifier = ofNullable(builder.getIdentifier()).map(f->f.build()).orElse(null);
			this.otherAgreementType = ofNullable(builder.getOtherAgreementType()).map(f->f.build()).orElse(null);
			this.version = ofNullable(builder.getVersion()).map(f->f.build()).orElse(null);
			this.date = builder.getDate();
		}
		
		@Override
		@RosettaAttribute("identifier")
		public FieldWithMetaString getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("otherAgreementType")
		public FieldWithMetaString getOtherAgreementType() {
			return otherAgreementType;
		}
		
		@Override
		@RosettaAttribute("version")
		public FieldWithMetaString getVersion() {
			return version;
		}
		
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
		@Override
		public OtherAgreement build() {
			return this;
		}
		
		@Override
		public OtherAgreement.OtherAgreementBuilder toBuilder() {
			OtherAgreement.OtherAgreementBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OtherAgreement.OtherAgreementBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getOtherAgreementType()).ifPresent(builder::setOtherAgreementType);
			ofNullable(getVersion()).ifPresent(builder::setVersion);
			ofNullable(getDate()).ifPresent(builder::setDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OtherAgreement _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(otherAgreementType, _that.getOtherAgreementType())) return false;
			if (!Objects.equals(version, _that.getVersion())) return false;
			if (!Objects.equals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (otherAgreementType != null ? otherAgreementType.hashCode() : 0);
			_result = 31 * _result + (version != null ? version.hashCode() : 0);
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OtherAgreement {" +
				"identifier=" + this.identifier + ", " +
				"otherAgreementType=" + this.otherAgreementType + ", " +
				"version=" + this.version + ", " +
				"date=" + this.date +
			'}';
		}
	}

	/*********************** Builder Implementation of OtherAgreement  ***********************/
	class OtherAgreementBuilderImpl implements OtherAgreement.OtherAgreementBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder identifier;
		protected FieldWithMetaString.FieldWithMetaStringBuilder otherAgreementType;
		protected FieldWithMetaString.FieldWithMetaStringBuilder version;
		protected Date date;
	
		public OtherAgreementBuilderImpl() {
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
		@RosettaAttribute("otherAgreementType")
		public FieldWithMetaString.FieldWithMetaStringBuilder getOtherAgreementType() {
			return otherAgreementType;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateOtherAgreementType() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (otherAgreementType!=null) {
				result = otherAgreementType;
			}
			else {
				result = otherAgreementType = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("version")
		public FieldWithMetaString.FieldWithMetaStringBuilder getVersion() {
			return version;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateVersion() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (version!=null) {
				result = version;
			}
			else {
				result = version = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
	
		@Override
		@RosettaAttribute("identifier")
		public OtherAgreement.OtherAgreementBuilder setIdentifier(FieldWithMetaString identifier) {
			this.identifier = identifier==null?null:identifier.toBuilder();
			return this;
		}
		@Override
		public OtherAgreement.OtherAgreementBuilder setIdentifierValue(String identifier) {
			this.getOrCreateIdentifier().setValue(identifier);
			return this;
		}
		@Override
		@RosettaAttribute("otherAgreementType")
		public OtherAgreement.OtherAgreementBuilder setOtherAgreementType(FieldWithMetaString otherAgreementType) {
			this.otherAgreementType = otherAgreementType==null?null:otherAgreementType.toBuilder();
			return this;
		}
		@Override
		public OtherAgreement.OtherAgreementBuilder setOtherAgreementTypeValue(String otherAgreementType) {
			this.getOrCreateOtherAgreementType().setValue(otherAgreementType);
			return this;
		}
		@Override
		@RosettaAttribute("version")
		public OtherAgreement.OtherAgreementBuilder setVersion(FieldWithMetaString version) {
			this.version = version==null?null:version.toBuilder();
			return this;
		}
		@Override
		public OtherAgreement.OtherAgreementBuilder setVersionValue(String version) {
			this.getOrCreateVersion().setValue(version);
			return this;
		}
		@Override
		@RosettaAttribute("date")
		public OtherAgreement.OtherAgreementBuilder setDate(Date date) {
			this.date = date==null?null:date;
			return this;
		}
		
		@Override
		public OtherAgreement build() {
			return new OtherAgreement.OtherAgreementImpl(this);
		}
		
		@Override
		public OtherAgreement.OtherAgreementBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OtherAgreement.OtherAgreementBuilder prune() {
			if (identifier!=null && !identifier.prune().hasData()) identifier = null;
			if (otherAgreementType!=null && !otherAgreementType.prune().hasData()) otherAgreementType = null;
			if (version!=null && !version.prune().hasData()) version = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null) return true;
			if (getOtherAgreementType()!=null) return true;
			if (getVersion()!=null) return true;
			if (getDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OtherAgreement.OtherAgreementBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OtherAgreement.OtherAgreementBuilder o = (OtherAgreement.OtherAgreementBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeRosetta(getOtherAgreementType(), o.getOtherAgreementType(), this::setOtherAgreementType);
			merger.mergeRosetta(getVersion(), o.getVersion(), this::setVersion);
			
			merger.mergeBasic(getDate(), o.getDate(), this::setDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OtherAgreement _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(otherAgreementType, _that.getOtherAgreementType())) return false;
			if (!Objects.equals(version, _that.getVersion())) return false;
			if (!Objects.equals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (otherAgreementType != null ? otherAgreementType.hashCode() : 0);
			_result = 31 * _result + (version != null ? version.hashCode() : 0);
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OtherAgreementBuilder {" +
				"identifier=" + this.identifier + ", " +
				"otherAgreementType=" + this.otherAgreementType + ", " +
				"version=" + this.version + ", " +
				"date=" + this.date +
			'}';
		}
	}
}
