package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.GoverningLawEnum;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementIdentification.LegalAgreementIdentificationBuilder;
import cdm.legaldocumentation.common.LegalAgreementIdentification.LegalAgreementIdentificationBuilderImpl;
import cdm.legaldocumentation.common.LegalAgreementIdentification.LegalAgreementIdentificationImpl;
import cdm.legaldocumentation.common.LegalAgreementPublisherEnum;
import cdm.legaldocumentation.common.meta.LegalAgreementIdentificationMeta;
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
 * Specifies the type of legal agreement, identified via a set of composable attributes: agreementName, publisher, governing law and version, e.g. ISDA 2013 Standard Credit Support Annex English Law.
 * @version ${project.version}
 */
@RosettaDataType(value="LegalAgreementIdentification", builder=LegalAgreementIdentification.LegalAgreementIdentificationBuilderImpl.class, version="${project.version}")
public interface LegalAgreementIdentification extends RosettaModelObject {

	LegalAgreementIdentificationMeta metaData = new LegalAgreementIdentificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The law governing the legal agreement, e.g. English Law, New York Law or Japanese Law.
	 */
	GoverningLawEnum getGoverningLaw();
	/**
	 * The legal agreement name, e.g. Credit Support Annex for Variation Margin.
	 */
	AgreementName getAgreementName();
	/**
	 * The legal agreement publisher, e.g. ISDA.
	 */
	LegalAgreementPublisherEnum getPublisher();
	/**
	 * In the case where successive definitions of the legal agreement have been developed, the vintage identification. This is typically (but not necessarily) done by referencing the year, e.g. 2013 in the case of the ISDA 2013 Standard Credit Support Annex.
	 */
	Integer getVintage();

	/*********************** Build Methods  ***********************/
	LegalAgreementIdentification build();
	
	LegalAgreementIdentification.LegalAgreementIdentificationBuilder toBuilder();
	
	static LegalAgreementIdentification.LegalAgreementIdentificationBuilder builder() {
		return new LegalAgreementIdentification.LegalAgreementIdentificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LegalAgreementIdentification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LegalAgreementIdentification> getType() {
		return LegalAgreementIdentification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("governingLaw"), GoverningLawEnum.class, getGoverningLaw(), this);
		processRosetta(path.newSubPath("agreementName"), processor, AgreementName.class, getAgreementName());
		processor.processBasic(path.newSubPath("publisher"), LegalAgreementPublisherEnum.class, getPublisher(), this);
		processor.processBasic(path.newSubPath("vintage"), Integer.class, getVintage(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface LegalAgreementIdentificationBuilder extends LegalAgreementIdentification, RosettaModelObjectBuilder {
		AgreementName.AgreementNameBuilder getOrCreateAgreementName();
		AgreementName.AgreementNameBuilder getAgreementName();
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder setGoverningLaw(GoverningLawEnum governingLaw);
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder setAgreementName(AgreementName agreementName);
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder setPublisher(LegalAgreementPublisherEnum publisher);
		LegalAgreementIdentification.LegalAgreementIdentificationBuilder setVintage(Integer vintage);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("governingLaw"), GoverningLawEnum.class, getGoverningLaw(), this);
			processRosetta(path.newSubPath("agreementName"), processor, AgreementName.AgreementNameBuilder.class, getAgreementName());
			processor.processBasic(path.newSubPath("publisher"), LegalAgreementPublisherEnum.class, getPublisher(), this);
			processor.processBasic(path.newSubPath("vintage"), Integer.class, getVintage(), this);
		}
		

		LegalAgreementIdentification.LegalAgreementIdentificationBuilder prune();
	}

	/*********************** Immutable Implementation of LegalAgreementIdentification  ***********************/
	class LegalAgreementIdentificationImpl implements LegalAgreementIdentification {
		private final GoverningLawEnum governingLaw;
		private final AgreementName agreementName;
		private final LegalAgreementPublisherEnum publisher;
		private final Integer vintage;
		
		protected LegalAgreementIdentificationImpl(LegalAgreementIdentification.LegalAgreementIdentificationBuilder builder) {
			this.governingLaw = builder.getGoverningLaw();
			this.agreementName = ofNullable(builder.getAgreementName()).map(f->f.build()).orElse(null);
			this.publisher = builder.getPublisher();
			this.vintage = builder.getVintage();
		}
		
		@Override
		@RosettaAttribute("governingLaw")
		public GoverningLawEnum getGoverningLaw() {
			return governingLaw;
		}
		
		@Override
		@RosettaAttribute("agreementName")
		public AgreementName getAgreementName() {
			return agreementName;
		}
		
		@Override
		@RosettaAttribute("publisher")
		public LegalAgreementPublisherEnum getPublisher() {
			return publisher;
		}
		
		@Override
		@RosettaAttribute("vintage")
		public Integer getVintage() {
			return vintage;
		}
		
		@Override
		public LegalAgreementIdentification build() {
			return this;
		}
		
		@Override
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder toBuilder() {
			LegalAgreementIdentification.LegalAgreementIdentificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LegalAgreementIdentification.LegalAgreementIdentificationBuilder builder) {
			ofNullable(getGoverningLaw()).ifPresent(builder::setGoverningLaw);
			ofNullable(getAgreementName()).ifPresent(builder::setAgreementName);
			ofNullable(getPublisher()).ifPresent(builder::setPublisher);
			ofNullable(getVintage()).ifPresent(builder::setVintage);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalAgreementIdentification _that = getType().cast(o);
		
			if (!Objects.equals(governingLaw, _that.getGoverningLaw())) return false;
			if (!Objects.equals(agreementName, _that.getAgreementName())) return false;
			if (!Objects.equals(publisher, _that.getPublisher())) return false;
			if (!Objects.equals(vintage, _that.getVintage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (governingLaw != null ? governingLaw.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agreementName != null ? agreementName.hashCode() : 0);
			_result = 31 * _result + (publisher != null ? publisher.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (vintage != null ? vintage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreementIdentification {" +
				"governingLaw=" + this.governingLaw + ", " +
				"agreementName=" + this.agreementName + ", " +
				"publisher=" + this.publisher + ", " +
				"vintage=" + this.vintage +
			'}';
		}
	}

	/*********************** Builder Implementation of LegalAgreementIdentification  ***********************/
	class LegalAgreementIdentificationBuilderImpl implements LegalAgreementIdentification.LegalAgreementIdentificationBuilder {
	
		protected GoverningLawEnum governingLaw;
		protected AgreementName.AgreementNameBuilder agreementName;
		protected LegalAgreementPublisherEnum publisher;
		protected Integer vintage;
	
		public LegalAgreementIdentificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("governingLaw")
		public GoverningLawEnum getGoverningLaw() {
			return governingLaw;
		}
		
		@Override
		@RosettaAttribute("agreementName")
		public AgreementName.AgreementNameBuilder getAgreementName() {
			return agreementName;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder getOrCreateAgreementName() {
			AgreementName.AgreementNameBuilder result;
			if (agreementName!=null) {
				result = agreementName;
			}
			else {
				result = agreementName = AgreementName.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("publisher")
		public LegalAgreementPublisherEnum getPublisher() {
			return publisher;
		}
		
		@Override
		@RosettaAttribute("vintage")
		public Integer getVintage() {
			return vintage;
		}
		
	
		@Override
		@RosettaAttribute("governingLaw")
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder setGoverningLaw(GoverningLawEnum governingLaw) {
			this.governingLaw = governingLaw==null?null:governingLaw;
			return this;
		}
		@Override
		@RosettaAttribute("agreementName")
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder setAgreementName(AgreementName agreementName) {
			this.agreementName = agreementName==null?null:agreementName.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("publisher")
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder setPublisher(LegalAgreementPublisherEnum publisher) {
			this.publisher = publisher==null?null:publisher;
			return this;
		}
		@Override
		@RosettaAttribute("vintage")
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder setVintage(Integer vintage) {
			this.vintage = vintage==null?null:vintage;
			return this;
		}
		
		@Override
		public LegalAgreementIdentification build() {
			return new LegalAgreementIdentification.LegalAgreementIdentificationImpl(this);
		}
		
		@Override
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder prune() {
			if (agreementName!=null && !agreementName.prune().hasData()) agreementName = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getGoverningLaw()!=null) return true;
			if (getAgreementName()!=null && getAgreementName().hasData()) return true;
			if (getPublisher()!=null) return true;
			if (getVintage()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreementIdentification.LegalAgreementIdentificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			LegalAgreementIdentification.LegalAgreementIdentificationBuilder o = (LegalAgreementIdentification.LegalAgreementIdentificationBuilder) other;
			
			merger.mergeRosetta(getAgreementName(), o.getAgreementName(), this::setAgreementName);
			
			merger.mergeBasic(getGoverningLaw(), o.getGoverningLaw(), this::setGoverningLaw);
			merger.mergeBasic(getPublisher(), o.getPublisher(), this::setPublisher);
			merger.mergeBasic(getVintage(), o.getVintage(), this::setVintage);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalAgreementIdentification _that = getType().cast(o);
		
			if (!Objects.equals(governingLaw, _that.getGoverningLaw())) return false;
			if (!Objects.equals(agreementName, _that.getAgreementName())) return false;
			if (!Objects.equals(publisher, _that.getPublisher())) return false;
			if (!Objects.equals(vintage, _that.getVintage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (governingLaw != null ? governingLaw.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (agreementName != null ? agreementName.hashCode() : 0);
			_result = 31 * _result + (publisher != null ? publisher.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (vintage != null ? vintage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreementIdentificationBuilder {" +
				"governingLaw=" + this.governingLaw + ", " +
				"agreementName=" + this.agreementName + ", " +
				"publisher=" + this.publisher + ", " +
				"vintage=" + this.vintage +
			'}';
		}
	}
}
