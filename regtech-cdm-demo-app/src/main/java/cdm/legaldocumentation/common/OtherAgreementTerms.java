package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.OtherAgreementTerms;
import cdm.legaldocumentation.common.OtherAgreementTerms.OtherAgreementTermsBuilder;
import cdm.legaldocumentation.common.OtherAgreementTerms.OtherAgreementTermsBuilderImpl;
import cdm.legaldocumentation.common.OtherAgreementTerms.OtherAgreementTermsImpl;
import cdm.legaldocumentation.common.meta.OtherAgreementTermsMeta;
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
 * A class to specify a related legal agreement. For example, ISDA 2016 Credit Support Annex for Initial Margin, paragraph 13, General Principles, (s): Other CSA and Japanese Law CSA (VM). | ISDA 2016 Credit Support Annex for Variation Margin, paragraph 13, (o): Other CSA.
 * @version ${project.version}
 */
@RosettaDataType(value="OtherAgreementTerms", builder=OtherAgreementTerms.OtherAgreementTermsBuilderImpl.class, version="${project.version}")
public interface OtherAgreementTerms extends RosettaModelObject {

	OtherAgreementTermsMeta metaData = new OtherAgreementTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The qualification of whether some other related agreement is specified (True) or not (False).
	 */
	Boolean getIsSpecified();
	/**
	 * The specification of this other agreement, when the qualification is True.
	 */
	String getLegalDocument();

	/*********************** Build Methods  ***********************/
	OtherAgreementTerms build();
	
	OtherAgreementTerms.OtherAgreementTermsBuilder toBuilder();
	
	static OtherAgreementTerms.OtherAgreementTermsBuilder builder() {
		return new OtherAgreementTerms.OtherAgreementTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OtherAgreementTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OtherAgreementTerms> getType() {
		return OtherAgreementTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("isSpecified"), Boolean.class, getIsSpecified(), this);
		processor.processBasic(path.newSubPath("legalDocument"), String.class, getLegalDocument(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface OtherAgreementTermsBuilder extends OtherAgreementTerms, RosettaModelObjectBuilder {
		OtherAgreementTerms.OtherAgreementTermsBuilder setIsSpecified(Boolean isSpecified);
		OtherAgreementTerms.OtherAgreementTermsBuilder setLegalDocument(String legalDocument);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("isSpecified"), Boolean.class, getIsSpecified(), this);
			processor.processBasic(path.newSubPath("legalDocument"), String.class, getLegalDocument(), this);
		}
		

		OtherAgreementTerms.OtherAgreementTermsBuilder prune();
	}

	/*********************** Immutable Implementation of OtherAgreementTerms  ***********************/
	class OtherAgreementTermsImpl implements OtherAgreementTerms {
		private final Boolean isSpecified;
		private final String legalDocument;
		
		protected OtherAgreementTermsImpl(OtherAgreementTerms.OtherAgreementTermsBuilder builder) {
			this.isSpecified = builder.getIsSpecified();
			this.legalDocument = builder.getLegalDocument();
		}
		
		@Override
		@RosettaAttribute("isSpecified")
		public Boolean getIsSpecified() {
			return isSpecified;
		}
		
		@Override
		@RosettaAttribute("legalDocument")
		public String getLegalDocument() {
			return legalDocument;
		}
		
		@Override
		public OtherAgreementTerms build() {
			return this;
		}
		
		@Override
		public OtherAgreementTerms.OtherAgreementTermsBuilder toBuilder() {
			OtherAgreementTerms.OtherAgreementTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OtherAgreementTerms.OtherAgreementTermsBuilder builder) {
			ofNullable(getIsSpecified()).ifPresent(builder::setIsSpecified);
			ofNullable(getLegalDocument()).ifPresent(builder::setLegalDocument);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OtherAgreementTerms _that = getType().cast(o);
		
			if (!Objects.equals(isSpecified, _that.getIsSpecified())) return false;
			if (!Objects.equals(legalDocument, _that.getLegalDocument())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isSpecified != null ? isSpecified.hashCode() : 0);
			_result = 31 * _result + (legalDocument != null ? legalDocument.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OtherAgreementTerms {" +
				"isSpecified=" + this.isSpecified + ", " +
				"legalDocument=" + this.legalDocument +
			'}';
		}
	}

	/*********************** Builder Implementation of OtherAgreementTerms  ***********************/
	class OtherAgreementTermsBuilderImpl implements OtherAgreementTerms.OtherAgreementTermsBuilder {
	
		protected Boolean isSpecified;
		protected String legalDocument;
	
		public OtherAgreementTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("isSpecified")
		public Boolean getIsSpecified() {
			return isSpecified;
		}
		
		@Override
		@RosettaAttribute("legalDocument")
		public String getLegalDocument() {
			return legalDocument;
		}
		
	
		@Override
		@RosettaAttribute("isSpecified")
		public OtherAgreementTerms.OtherAgreementTermsBuilder setIsSpecified(Boolean isSpecified) {
			this.isSpecified = isSpecified==null?null:isSpecified;
			return this;
		}
		@Override
		@RosettaAttribute("legalDocument")
		public OtherAgreementTerms.OtherAgreementTermsBuilder setLegalDocument(String legalDocument) {
			this.legalDocument = legalDocument==null?null:legalDocument;
			return this;
		}
		
		@Override
		public OtherAgreementTerms build() {
			return new OtherAgreementTerms.OtherAgreementTermsImpl(this);
		}
		
		@Override
		public OtherAgreementTerms.OtherAgreementTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OtherAgreementTerms.OtherAgreementTermsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIsSpecified()!=null) return true;
			if (getLegalDocument()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OtherAgreementTerms.OtherAgreementTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OtherAgreementTerms.OtherAgreementTermsBuilder o = (OtherAgreementTerms.OtherAgreementTermsBuilder) other;
			
			
			merger.mergeBasic(getIsSpecified(), o.getIsSpecified(), this::setIsSpecified);
			merger.mergeBasic(getLegalDocument(), o.getLegalDocument(), this::setLegalDocument);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OtherAgreementTerms _that = getType().cast(o);
		
			if (!Objects.equals(isSpecified, _that.getIsSpecified())) return false;
			if (!Objects.equals(legalDocument, _that.getLegalDocument())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isSpecified != null ? isSpecified.hashCode() : 0);
			_result = 31 * _result + (legalDocument != null ? legalDocument.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OtherAgreementTermsBuilder {" +
				"isSpecified=" + this.isSpecified + ", " +
				"legalDocument=" + this.legalDocument +
			'}';
		}
	}
}
