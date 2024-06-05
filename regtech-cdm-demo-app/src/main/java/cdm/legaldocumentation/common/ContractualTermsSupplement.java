package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.ContractualSupplementTypeEnum;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.ContractualTermsSupplement.ContractualTermsSupplementBuilder;
import cdm.legaldocumentation.common.ContractualTermsSupplement.ContractualTermsSupplementBuilderImpl;
import cdm.legaldocumentation.common.ContractualTermsSupplement.ContractualTermsSupplementImpl;
import cdm.legaldocumentation.common.meta.ContractualTermsSupplementMeta;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualSupplementTypeEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A contractual supplement (such as those published by ISDA) and its publication date that will apply to the trade.
 * @version ${project.version}
 */
@RosettaDataType(value="ContractualTermsSupplement", builder=ContractualTermsSupplement.ContractualTermsSupplementBuilderImpl.class, version="${project.version}")
public interface ContractualTermsSupplement extends RosettaModelObject {

	ContractualTermsSupplementMeta metaData = new ContractualTermsSupplementMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the form of applicable contractual supplement.
	 */
	FieldWithMetaContractualSupplementTypeEnum getContractualTermsSupplementType();
	/**
	 * Specifies the publication date of the applicable version of the contractual supplement.
	 */
	Date getPublicationDate();

	/*********************** Build Methods  ***********************/
	ContractualTermsSupplement build();
	
	ContractualTermsSupplement.ContractualTermsSupplementBuilder toBuilder();
	
	static ContractualTermsSupplement.ContractualTermsSupplementBuilder builder() {
		return new ContractualTermsSupplement.ContractualTermsSupplementBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContractualTermsSupplement> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContractualTermsSupplement> getType() {
		return ContractualTermsSupplement.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("contractualTermsSupplementType"), processor, FieldWithMetaContractualSupplementTypeEnum.class, getContractualTermsSupplementType());
		processor.processBasic(path.newSubPath("publicationDate"), Date.class, getPublicationDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContractualTermsSupplementBuilder extends ContractualTermsSupplement, RosettaModelObjectBuilder {
		FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder getOrCreateContractualTermsSupplementType();
		FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder getContractualTermsSupplementType();
		ContractualTermsSupplement.ContractualTermsSupplementBuilder setContractualTermsSupplementType(FieldWithMetaContractualSupplementTypeEnum contractualTermsSupplementType0);
		ContractualTermsSupplement.ContractualTermsSupplementBuilder setContractualTermsSupplementTypeValue(ContractualSupplementTypeEnum contractualTermsSupplementType1);
		ContractualTermsSupplement.ContractualTermsSupplementBuilder setPublicationDate(Date publicationDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("contractualTermsSupplementType"), processor, FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder.class, getContractualTermsSupplementType());
			processor.processBasic(path.newSubPath("publicationDate"), Date.class, getPublicationDate(), this);
		}
		

		ContractualTermsSupplement.ContractualTermsSupplementBuilder prune();
	}

	/*********************** Immutable Implementation of ContractualTermsSupplement  ***********************/
	class ContractualTermsSupplementImpl implements ContractualTermsSupplement {
		private final FieldWithMetaContractualSupplementTypeEnum contractualTermsSupplementType;
		private final Date publicationDate;
		
		protected ContractualTermsSupplementImpl(ContractualTermsSupplement.ContractualTermsSupplementBuilder builder) {
			this.contractualTermsSupplementType = ofNullable(builder.getContractualTermsSupplementType()).map(f->f.build()).orElse(null);
			this.publicationDate = builder.getPublicationDate();
		}
		
		@Override
		@RosettaAttribute("contractualTermsSupplementType")
		public FieldWithMetaContractualSupplementTypeEnum getContractualTermsSupplementType() {
			return contractualTermsSupplementType;
		}
		
		@Override
		@RosettaAttribute("publicationDate")
		public Date getPublicationDate() {
			return publicationDate;
		}
		
		@Override
		public ContractualTermsSupplement build() {
			return this;
		}
		
		@Override
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder toBuilder() {
			ContractualTermsSupplement.ContractualTermsSupplementBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContractualTermsSupplement.ContractualTermsSupplementBuilder builder) {
			ofNullable(getContractualTermsSupplementType()).ifPresent(builder::setContractualTermsSupplementType);
			ofNullable(getPublicationDate()).ifPresent(builder::setPublicationDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractualTermsSupplement _that = getType().cast(o);
		
			if (!Objects.equals(contractualTermsSupplementType, _that.getContractualTermsSupplementType())) return false;
			if (!Objects.equals(publicationDate, _that.getPublicationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractualTermsSupplementType != null ? contractualTermsSupplementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publicationDate != null ? publicationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualTermsSupplement {" +
				"contractualTermsSupplementType=" + this.contractualTermsSupplementType + ", " +
				"publicationDate=" + this.publicationDate +
			'}';
		}
	}

	/*********************** Builder Implementation of ContractualTermsSupplement  ***********************/
	class ContractualTermsSupplementBuilderImpl implements ContractualTermsSupplement.ContractualTermsSupplementBuilder {
	
		protected FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder contractualTermsSupplementType;
		protected Date publicationDate;
	
		public ContractualTermsSupplementBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("contractualTermsSupplementType")
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder getContractualTermsSupplementType() {
			return contractualTermsSupplementType;
		}
		
		@Override
		public FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder getOrCreateContractualTermsSupplementType() {
			FieldWithMetaContractualSupplementTypeEnum.FieldWithMetaContractualSupplementTypeEnumBuilder result;
			if (contractualTermsSupplementType!=null) {
				result = contractualTermsSupplementType;
			}
			else {
				result = contractualTermsSupplementType = FieldWithMetaContractualSupplementTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("publicationDate")
		public Date getPublicationDate() {
			return publicationDate;
		}
		
	
		@Override
		@RosettaAttribute("contractualTermsSupplementType")
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder setContractualTermsSupplementType(FieldWithMetaContractualSupplementTypeEnum contractualTermsSupplementType) {
			this.contractualTermsSupplementType = contractualTermsSupplementType==null?null:contractualTermsSupplementType.toBuilder();
			return this;
		}
		@Override
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder setContractualTermsSupplementTypeValue(ContractualSupplementTypeEnum contractualTermsSupplementType) {
			this.getOrCreateContractualTermsSupplementType().setValue(contractualTermsSupplementType);
			return this;
		}
		@Override
		@RosettaAttribute("publicationDate")
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate==null?null:publicationDate;
			return this;
		}
		
		@Override
		public ContractualTermsSupplement build() {
			return new ContractualTermsSupplement.ContractualTermsSupplementImpl(this);
		}
		
		@Override
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder prune() {
			if (contractualTermsSupplementType!=null && !contractualTermsSupplementType.prune().hasData()) contractualTermsSupplementType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getContractualTermsSupplementType()!=null) return true;
			if (getPublicationDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContractualTermsSupplement.ContractualTermsSupplementBuilder o = (ContractualTermsSupplement.ContractualTermsSupplementBuilder) other;
			
			merger.mergeRosetta(getContractualTermsSupplementType(), o.getContractualTermsSupplementType(), this::setContractualTermsSupplementType);
			
			merger.mergeBasic(getPublicationDate(), o.getPublicationDate(), this::setPublicationDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractualTermsSupplement _that = getType().cast(o);
		
			if (!Objects.equals(contractualTermsSupplementType, _that.getContractualTermsSupplementType())) return false;
			if (!Objects.equals(publicationDate, _that.getPublicationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractualTermsSupplementType != null ? contractualTermsSupplementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publicationDate != null ? publicationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualTermsSupplementBuilder {" +
				"contractualTermsSupplementType=" + this.contractualTermsSupplementType + ", " +
				"publicationDate=" + this.publicationDate +
			'}';
		}
	}
}
