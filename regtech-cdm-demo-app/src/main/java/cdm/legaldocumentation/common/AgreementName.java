package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.AgreementName.AgreementNameBuilder;
import cdm.legaldocumentation.common.AgreementName.AgreementNameBuilderImpl;
import cdm.legaldocumentation.common.AgreementName.AgreementNameImpl;
import cdm.legaldocumentation.common.ContractualDefinitionsEnum;
import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.common.meta.AgreementNameMeta;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualDefinitionsEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder;
import cdm.legaldocumentation.master.MasterAgreementTypeEnum;
import cdm.legaldocumentation.master.MasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.MasterConfirmationTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder;
import cdm.product.collateral.CollateralMarginTypeEnum;
import cdm.product.collateral.CreditSupportAgreementTypeEnum;
import cdm.product.collateral.metafields.FieldWithMetaCreditSupportAgreementTypeEnum;
import cdm.product.collateral.metafields.FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder;
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
 * Specifies the agreement name through an agreement type and optional detailed sub agreement type.
 * @version ${project.version}
 */
@RosettaDataType(value="AgreementName", builder=AgreementName.AgreementNameBuilderImpl.class, version="${project.version}")
public interface AgreementName extends RosettaModelObject {

	AgreementNameMeta metaData = new AgreementNameMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specification of the legal agreement type.
	 */
	LegalAgreementTypeEnum getAgreementType();
	/**
	 * Specification of the credit support agreement type.
	 */
	FieldWithMetaCreditSupportAgreementTypeEnum getCreditSupportAgreementType();
	/**
	 * specifies the type of margin for which a legal agreement is named.
	 */
	CollateralMarginTypeEnum getCreditSupportAgreementMarginType();
	/**
	 * The definitions such as those published by ISDA that will define the terms of the trade.
	 */
	List<? extends FieldWithMetaContractualDefinitionsEnum> getContractualDefinitionsType();
	/**
	 * A contractual supplement (such as those published by ISDA) that will apply to the trade.
	 */
	List<? extends ContractualTermsSupplement> getContractualTermsSupplement();
	/**
	 * A reference to a contractual matrix of elected terms/values (such as those published by ISDA) that shall be deemed to apply to the trade. The applicable matrix is identified by reference to a name and optionally a publication date. Depending on the structure of the matrix, an additional term (specified in the matrixTerm element) may be required to further identify a subset of applicable terms/values within the matrix.
	 */
	List<? extends ContractualMatrix> getContractualMatrix();
	/**
	 * Specification of the master agreement type.
	 */
	FieldWithMetaMasterAgreementTypeEnum getMasterAgreementType();
	/**
	 * The type of master confirmation executed between the parties.
	 */
	FieldWithMetaMasterConfirmationTypeEnum getMasterConfirmationType();
	/**
	 * The type of master confirmation annex executed between the parties.
	 */
	FieldWithMetaMasterConfirmationAnnexTypeEnum getMasterConfirmationAnnexType();
	/**
	 * Definition of an agreement that is not enumerated in the CDM.
	 */
	String getOtherAgreement();

	/*********************** Build Methods  ***********************/
	AgreementName build();
	
	AgreementName.AgreementNameBuilder toBuilder();
	
	static AgreementName.AgreementNameBuilder builder() {
		return new AgreementName.AgreementNameBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AgreementName> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AgreementName> getType() {
		return AgreementName.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("agreementType"), LegalAgreementTypeEnum.class, getAgreementType(), this);
		processRosetta(path.newSubPath("creditSupportAgreementType"), processor, FieldWithMetaCreditSupportAgreementTypeEnum.class, getCreditSupportAgreementType());
		processor.processBasic(path.newSubPath("creditSupportAgreementMarginType"), CollateralMarginTypeEnum.class, getCreditSupportAgreementMarginType(), this);
		processRosetta(path.newSubPath("contractualDefinitionsType"), processor, FieldWithMetaContractualDefinitionsEnum.class, getContractualDefinitionsType());
		processRosetta(path.newSubPath("contractualTermsSupplement"), processor, ContractualTermsSupplement.class, getContractualTermsSupplement());
		processRosetta(path.newSubPath("contractualMatrix"), processor, ContractualMatrix.class, getContractualMatrix());
		processRosetta(path.newSubPath("masterAgreementType"), processor, FieldWithMetaMasterAgreementTypeEnum.class, getMasterAgreementType());
		processRosetta(path.newSubPath("masterConfirmationType"), processor, FieldWithMetaMasterConfirmationTypeEnum.class, getMasterConfirmationType());
		processRosetta(path.newSubPath("masterConfirmationAnnexType"), processor, FieldWithMetaMasterConfirmationAnnexTypeEnum.class, getMasterConfirmationAnnexType());
		processor.processBasic(path.newSubPath("otherAgreement"), String.class, getOtherAgreement(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AgreementNameBuilder extends AgreementName, RosettaModelObjectBuilder {
		FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder getOrCreateCreditSupportAgreementType();
		FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder getCreditSupportAgreementType();
		FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder getOrCreateContractualDefinitionsType(int _index);
		List<? extends FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder> getContractualDefinitionsType();
		ContractualTermsSupplement.ContractualTermsSupplementBuilder getOrCreateContractualTermsSupplement(int _index);
		List<? extends ContractualTermsSupplement.ContractualTermsSupplementBuilder> getContractualTermsSupplement();
		ContractualMatrix.ContractualMatrixBuilder getOrCreateContractualMatrix(int _index);
		List<? extends ContractualMatrix.ContractualMatrixBuilder> getContractualMatrix();
		FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder getOrCreateMasterAgreementType();
		FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder getMasterAgreementType();
		FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder getOrCreateMasterConfirmationType();
		FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder getMasterConfirmationType();
		FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder getOrCreateMasterConfirmationAnnexType();
		FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder getMasterConfirmationAnnexType();
		AgreementName.AgreementNameBuilder setAgreementType(LegalAgreementTypeEnum agreementType);
		AgreementName.AgreementNameBuilder setCreditSupportAgreementType(FieldWithMetaCreditSupportAgreementTypeEnum creditSupportAgreementType0);
		AgreementName.AgreementNameBuilder setCreditSupportAgreementTypeValue(CreditSupportAgreementTypeEnum creditSupportAgreementType1);
		AgreementName.AgreementNameBuilder setCreditSupportAgreementMarginType(CollateralMarginTypeEnum creditSupportAgreementMarginType);
		AgreementName.AgreementNameBuilder addContractualDefinitionsType(FieldWithMetaContractualDefinitionsEnum contractualDefinitionsType0);
		AgreementName.AgreementNameBuilder addContractualDefinitionsType(FieldWithMetaContractualDefinitionsEnum contractualDefinitionsType1, int _idx);
		AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(ContractualDefinitionsEnum contractualDefinitionsType2);
		AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(ContractualDefinitionsEnum contractualDefinitionsType3, int _idx);
		AgreementName.AgreementNameBuilder addContractualDefinitionsType(List<? extends FieldWithMetaContractualDefinitionsEnum> contractualDefinitionsType4);
		AgreementName.AgreementNameBuilder setContractualDefinitionsType(List<? extends FieldWithMetaContractualDefinitionsEnum> contractualDefinitionsType5);
		AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(List<? extends ContractualDefinitionsEnum> contractualDefinitionsType6);
		AgreementName.AgreementNameBuilder setContractualDefinitionsTypeValue(List<? extends ContractualDefinitionsEnum> contractualDefinitionsType7);
		AgreementName.AgreementNameBuilder addContractualTermsSupplement(ContractualTermsSupplement contractualTermsSupplement0);
		AgreementName.AgreementNameBuilder addContractualTermsSupplement(ContractualTermsSupplement contractualTermsSupplement1, int _idx);
		AgreementName.AgreementNameBuilder addContractualTermsSupplement(List<? extends ContractualTermsSupplement> contractualTermsSupplement2);
		AgreementName.AgreementNameBuilder setContractualTermsSupplement(List<? extends ContractualTermsSupplement> contractualTermsSupplement3);
		AgreementName.AgreementNameBuilder addContractualMatrix(ContractualMatrix contractualMatrix0);
		AgreementName.AgreementNameBuilder addContractualMatrix(ContractualMatrix contractualMatrix1, int _idx);
		AgreementName.AgreementNameBuilder addContractualMatrix(List<? extends ContractualMatrix> contractualMatrix2);
		AgreementName.AgreementNameBuilder setContractualMatrix(List<? extends ContractualMatrix> contractualMatrix3);
		AgreementName.AgreementNameBuilder setMasterAgreementType(FieldWithMetaMasterAgreementTypeEnum masterAgreementType0);
		AgreementName.AgreementNameBuilder setMasterAgreementTypeValue(MasterAgreementTypeEnum masterAgreementType1);
		AgreementName.AgreementNameBuilder setMasterConfirmationType(FieldWithMetaMasterConfirmationTypeEnum masterConfirmationType0);
		AgreementName.AgreementNameBuilder setMasterConfirmationTypeValue(MasterConfirmationTypeEnum masterConfirmationType1);
		AgreementName.AgreementNameBuilder setMasterConfirmationAnnexType(FieldWithMetaMasterConfirmationAnnexTypeEnum masterConfirmationAnnexType0);
		AgreementName.AgreementNameBuilder setMasterConfirmationAnnexTypeValue(MasterConfirmationAnnexTypeEnum masterConfirmationAnnexType1);
		AgreementName.AgreementNameBuilder setOtherAgreement(String otherAgreement);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("agreementType"), LegalAgreementTypeEnum.class, getAgreementType(), this);
			processRosetta(path.newSubPath("creditSupportAgreementType"), processor, FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder.class, getCreditSupportAgreementType());
			processor.processBasic(path.newSubPath("creditSupportAgreementMarginType"), CollateralMarginTypeEnum.class, getCreditSupportAgreementMarginType(), this);
			processRosetta(path.newSubPath("contractualDefinitionsType"), processor, FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder.class, getContractualDefinitionsType());
			processRosetta(path.newSubPath("contractualTermsSupplement"), processor, ContractualTermsSupplement.ContractualTermsSupplementBuilder.class, getContractualTermsSupplement());
			processRosetta(path.newSubPath("contractualMatrix"), processor, ContractualMatrix.ContractualMatrixBuilder.class, getContractualMatrix());
			processRosetta(path.newSubPath("masterAgreementType"), processor, FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder.class, getMasterAgreementType());
			processRosetta(path.newSubPath("masterConfirmationType"), processor, FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder.class, getMasterConfirmationType());
			processRosetta(path.newSubPath("masterConfirmationAnnexType"), processor, FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder.class, getMasterConfirmationAnnexType());
			processor.processBasic(path.newSubPath("otherAgreement"), String.class, getOtherAgreement(), this);
		}
		

		AgreementName.AgreementNameBuilder prune();
	}

	/*********************** Immutable Implementation of AgreementName  ***********************/
	class AgreementNameImpl implements AgreementName {
		private final LegalAgreementTypeEnum agreementType;
		private final FieldWithMetaCreditSupportAgreementTypeEnum creditSupportAgreementType;
		private final CollateralMarginTypeEnum creditSupportAgreementMarginType;
		private final List<? extends FieldWithMetaContractualDefinitionsEnum> contractualDefinitionsType;
		private final List<? extends ContractualTermsSupplement> contractualTermsSupplement;
		private final List<? extends ContractualMatrix> contractualMatrix;
		private final FieldWithMetaMasterAgreementTypeEnum masterAgreementType;
		private final FieldWithMetaMasterConfirmationTypeEnum masterConfirmationType;
		private final FieldWithMetaMasterConfirmationAnnexTypeEnum masterConfirmationAnnexType;
		private final String otherAgreement;
		
		protected AgreementNameImpl(AgreementName.AgreementNameBuilder builder) {
			this.agreementType = builder.getAgreementType();
			this.creditSupportAgreementType = ofNullable(builder.getCreditSupportAgreementType()).map(f->f.build()).orElse(null);
			this.creditSupportAgreementMarginType = builder.getCreditSupportAgreementMarginType();
			this.contractualDefinitionsType = ofNullable(builder.getContractualDefinitionsType()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.contractualTermsSupplement = ofNullable(builder.getContractualTermsSupplement()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.contractualMatrix = ofNullable(builder.getContractualMatrix()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.masterAgreementType = ofNullable(builder.getMasterAgreementType()).map(f->f.build()).orElse(null);
			this.masterConfirmationType = ofNullable(builder.getMasterConfirmationType()).map(f->f.build()).orElse(null);
			this.masterConfirmationAnnexType = ofNullable(builder.getMasterConfirmationAnnexType()).map(f->f.build()).orElse(null);
			this.otherAgreement = builder.getOtherAgreement();
		}
		
		@Override
		@RosettaAttribute("agreementType")
		public LegalAgreementTypeEnum getAgreementType() {
			return agreementType;
		}
		
		@Override
		@RosettaAttribute("creditSupportAgreementType")
		public FieldWithMetaCreditSupportAgreementTypeEnum getCreditSupportAgreementType() {
			return creditSupportAgreementType;
		}
		
		@Override
		@RosettaAttribute("creditSupportAgreementMarginType")
		public CollateralMarginTypeEnum getCreditSupportAgreementMarginType() {
			return creditSupportAgreementMarginType;
		}
		
		@Override
		@RosettaAttribute("contractualDefinitionsType")
		public List<? extends FieldWithMetaContractualDefinitionsEnum> getContractualDefinitionsType() {
			return contractualDefinitionsType;
		}
		
		@Override
		@RosettaAttribute("contractualTermsSupplement")
		public List<? extends ContractualTermsSupplement> getContractualTermsSupplement() {
			return contractualTermsSupplement;
		}
		
		@Override
		@RosettaAttribute("contractualMatrix")
		public List<? extends ContractualMatrix> getContractualMatrix() {
			return contractualMatrix;
		}
		
		@Override
		@RosettaAttribute("masterAgreementType")
		public FieldWithMetaMasterAgreementTypeEnum getMasterAgreementType() {
			return masterAgreementType;
		}
		
		@Override
		@RosettaAttribute("masterConfirmationType")
		public FieldWithMetaMasterConfirmationTypeEnum getMasterConfirmationType() {
			return masterConfirmationType;
		}
		
		@Override
		@RosettaAttribute("masterConfirmationAnnexType")
		public FieldWithMetaMasterConfirmationAnnexTypeEnum getMasterConfirmationAnnexType() {
			return masterConfirmationAnnexType;
		}
		
		@Override
		@RosettaAttribute("otherAgreement")
		public String getOtherAgreement() {
			return otherAgreement;
		}
		
		@Override
		public AgreementName build() {
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder toBuilder() {
			AgreementName.AgreementNameBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AgreementName.AgreementNameBuilder builder) {
			ofNullable(getAgreementType()).ifPresent(builder::setAgreementType);
			ofNullable(getCreditSupportAgreementType()).ifPresent(builder::setCreditSupportAgreementType);
			ofNullable(getCreditSupportAgreementMarginType()).ifPresent(builder::setCreditSupportAgreementMarginType);
			ofNullable(getContractualDefinitionsType()).ifPresent(builder::setContractualDefinitionsType);
			ofNullable(getContractualTermsSupplement()).ifPresent(builder::setContractualTermsSupplement);
			ofNullable(getContractualMatrix()).ifPresent(builder::setContractualMatrix);
			ofNullable(getMasterAgreementType()).ifPresent(builder::setMasterAgreementType);
			ofNullable(getMasterConfirmationType()).ifPresent(builder::setMasterConfirmationType);
			ofNullable(getMasterConfirmationAnnexType()).ifPresent(builder::setMasterConfirmationAnnexType);
			ofNullable(getOtherAgreement()).ifPresent(builder::setOtherAgreement);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AgreementName _that = getType().cast(o);
		
			if (!Objects.equals(agreementType, _that.getAgreementType())) return false;
			if (!Objects.equals(creditSupportAgreementType, _that.getCreditSupportAgreementType())) return false;
			if (!Objects.equals(creditSupportAgreementMarginType, _that.getCreditSupportAgreementMarginType())) return false;
			if (!ListEquals.listEquals(contractualDefinitionsType, _that.getContractualDefinitionsType())) return false;
			if (!ListEquals.listEquals(contractualTermsSupplement, _that.getContractualTermsSupplement())) return false;
			if (!ListEquals.listEquals(contractualMatrix, _that.getContractualMatrix())) return false;
			if (!Objects.equals(masterAgreementType, _that.getMasterAgreementType())) return false;
			if (!Objects.equals(masterConfirmationType, _that.getMasterConfirmationType())) return false;
			if (!Objects.equals(masterConfirmationAnnexType, _that.getMasterConfirmationAnnexType())) return false;
			if (!Objects.equals(otherAgreement, _that.getOtherAgreement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agreementType != null ? agreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditSupportAgreementType != null ? creditSupportAgreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditSupportAgreementMarginType != null ? creditSupportAgreementMarginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (contractualDefinitionsType != null ? contractualDefinitionsType.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (contractualTermsSupplement != null ? contractualTermsSupplement.hashCode() : 0);
			_result = 31 * _result + (contractualMatrix != null ? contractualMatrix.hashCode() : 0);
			_result = 31 * _result + (masterAgreementType != null ? masterAgreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (masterConfirmationType != null ? masterConfirmationType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (masterConfirmationAnnexType != null ? masterConfirmationAnnexType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (otherAgreement != null ? otherAgreement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AgreementName {" +
				"agreementType=" + this.agreementType + ", " +
				"creditSupportAgreementType=" + this.creditSupportAgreementType + ", " +
				"creditSupportAgreementMarginType=" + this.creditSupportAgreementMarginType + ", " +
				"contractualDefinitionsType=" + this.contractualDefinitionsType + ", " +
				"contractualTermsSupplement=" + this.contractualTermsSupplement + ", " +
				"contractualMatrix=" + this.contractualMatrix + ", " +
				"masterAgreementType=" + this.masterAgreementType + ", " +
				"masterConfirmationType=" + this.masterConfirmationType + ", " +
				"masterConfirmationAnnexType=" + this.masterConfirmationAnnexType + ", " +
				"otherAgreement=" + this.otherAgreement +
			'}';
		}
	}

	/*********************** Builder Implementation of AgreementName  ***********************/
	class AgreementNameBuilderImpl implements AgreementName.AgreementNameBuilder {
	
		protected LegalAgreementTypeEnum agreementType;
		protected FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder creditSupportAgreementType;
		protected CollateralMarginTypeEnum creditSupportAgreementMarginType;
		protected List<FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder> contractualDefinitionsType = new ArrayList<>();
		protected List<ContractualTermsSupplement.ContractualTermsSupplementBuilder> contractualTermsSupplement = new ArrayList<>();
		protected List<ContractualMatrix.ContractualMatrixBuilder> contractualMatrix = new ArrayList<>();
		protected FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder masterAgreementType;
		protected FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder masterConfirmationType;
		protected FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder masterConfirmationAnnexType;
		protected String otherAgreement;
	
		public AgreementNameBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("agreementType")
		public LegalAgreementTypeEnum getAgreementType() {
			return agreementType;
		}
		
		@Override
		@RosettaAttribute("creditSupportAgreementType")
		public FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder getCreditSupportAgreementType() {
			return creditSupportAgreementType;
		}
		
		@Override
		public FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder getOrCreateCreditSupportAgreementType() {
			FieldWithMetaCreditSupportAgreementTypeEnum.FieldWithMetaCreditSupportAgreementTypeEnumBuilder result;
			if (creditSupportAgreementType!=null) {
				result = creditSupportAgreementType;
			}
			else {
				result = creditSupportAgreementType = FieldWithMetaCreditSupportAgreementTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("creditSupportAgreementMarginType")
		public CollateralMarginTypeEnum getCreditSupportAgreementMarginType() {
			return creditSupportAgreementMarginType;
		}
		
		@Override
		@RosettaAttribute("contractualDefinitionsType")
		public List<? extends FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder> getContractualDefinitionsType() {
			return contractualDefinitionsType;
		}
		
		public FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder getOrCreateContractualDefinitionsType(int _index) {
		
			if (contractualDefinitionsType==null) {
				this.contractualDefinitionsType = new ArrayList<>();
			}
			FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder result;
			return getIndex(contractualDefinitionsType, _index, () -> {
						FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder newContractualDefinitionsType = FieldWithMetaContractualDefinitionsEnum.builder();
						return newContractualDefinitionsType;
					});
		}
		
		@Override
		@RosettaAttribute("contractualTermsSupplement")
		public List<? extends ContractualTermsSupplement.ContractualTermsSupplementBuilder> getContractualTermsSupplement() {
			return contractualTermsSupplement;
		}
		
		public ContractualTermsSupplement.ContractualTermsSupplementBuilder getOrCreateContractualTermsSupplement(int _index) {
		
			if (contractualTermsSupplement==null) {
				this.contractualTermsSupplement = new ArrayList<>();
			}
			ContractualTermsSupplement.ContractualTermsSupplementBuilder result;
			return getIndex(contractualTermsSupplement, _index, () -> {
						ContractualTermsSupplement.ContractualTermsSupplementBuilder newContractualTermsSupplement = ContractualTermsSupplement.builder();
						return newContractualTermsSupplement;
					});
		}
		
		@Override
		@RosettaAttribute("contractualMatrix")
		public List<? extends ContractualMatrix.ContractualMatrixBuilder> getContractualMatrix() {
			return contractualMatrix;
		}
		
		public ContractualMatrix.ContractualMatrixBuilder getOrCreateContractualMatrix(int _index) {
		
			if (contractualMatrix==null) {
				this.contractualMatrix = new ArrayList<>();
			}
			ContractualMatrix.ContractualMatrixBuilder result;
			return getIndex(contractualMatrix, _index, () -> {
						ContractualMatrix.ContractualMatrixBuilder newContractualMatrix = ContractualMatrix.builder();
						return newContractualMatrix;
					});
		}
		
		@Override
		@RosettaAttribute("masterAgreementType")
		public FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder getMasterAgreementType() {
			return masterAgreementType;
		}
		
		@Override
		public FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder getOrCreateMasterAgreementType() {
			FieldWithMetaMasterAgreementTypeEnum.FieldWithMetaMasterAgreementTypeEnumBuilder result;
			if (masterAgreementType!=null) {
				result = masterAgreementType;
			}
			else {
				result = masterAgreementType = FieldWithMetaMasterAgreementTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("masterConfirmationType")
		public FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder getMasterConfirmationType() {
			return masterConfirmationType;
		}
		
		@Override
		public FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder getOrCreateMasterConfirmationType() {
			FieldWithMetaMasterConfirmationTypeEnum.FieldWithMetaMasterConfirmationTypeEnumBuilder result;
			if (masterConfirmationType!=null) {
				result = masterConfirmationType;
			}
			else {
				result = masterConfirmationType = FieldWithMetaMasterConfirmationTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("masterConfirmationAnnexType")
		public FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder getMasterConfirmationAnnexType() {
			return masterConfirmationAnnexType;
		}
		
		@Override
		public FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder getOrCreateMasterConfirmationAnnexType() {
			FieldWithMetaMasterConfirmationAnnexTypeEnum.FieldWithMetaMasterConfirmationAnnexTypeEnumBuilder result;
			if (masterConfirmationAnnexType!=null) {
				result = masterConfirmationAnnexType;
			}
			else {
				result = masterConfirmationAnnexType = FieldWithMetaMasterConfirmationAnnexTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("otherAgreement")
		public String getOtherAgreement() {
			return otherAgreement;
		}
		
	
		@Override
		@RosettaAttribute("agreementType")
		public AgreementName.AgreementNameBuilder setAgreementType(LegalAgreementTypeEnum agreementType) {
			this.agreementType = agreementType==null?null:agreementType;
			return this;
		}
		@Override
		@RosettaAttribute("creditSupportAgreementType")
		public AgreementName.AgreementNameBuilder setCreditSupportAgreementType(FieldWithMetaCreditSupportAgreementTypeEnum creditSupportAgreementType) {
			this.creditSupportAgreementType = creditSupportAgreementType==null?null:creditSupportAgreementType.toBuilder();
			return this;
		}
		@Override
		public AgreementName.AgreementNameBuilder setCreditSupportAgreementTypeValue(CreditSupportAgreementTypeEnum creditSupportAgreementType) {
			this.getOrCreateCreditSupportAgreementType().setValue(creditSupportAgreementType);
			return this;
		}
		@Override
		@RosettaAttribute("creditSupportAgreementMarginType")
		public AgreementName.AgreementNameBuilder setCreditSupportAgreementMarginType(CollateralMarginTypeEnum creditSupportAgreementMarginType) {
			this.creditSupportAgreementMarginType = creditSupportAgreementMarginType==null?null:creditSupportAgreementMarginType;
			return this;
		}
		@Override
		public AgreementName.AgreementNameBuilder addContractualDefinitionsType(FieldWithMetaContractualDefinitionsEnum contractualDefinitionsType) {
			if (contractualDefinitionsType!=null) this.contractualDefinitionsType.add(contractualDefinitionsType.toBuilder());
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualDefinitionsType(FieldWithMetaContractualDefinitionsEnum contractualDefinitionsType, int _idx) {
			getIndex(this.contractualDefinitionsType, _idx, () -> contractualDefinitionsType.toBuilder());
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(ContractualDefinitionsEnum contractualDefinitionsType) {
			this.getOrCreateContractualDefinitionsType(-1).setValue(contractualDefinitionsType);
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(ContractualDefinitionsEnum contractualDefinitionsType, int _idx) {
			this.getOrCreateContractualDefinitionsType(_idx).setValue(contractualDefinitionsType);
			return this;
		}
		@Override 
		public AgreementName.AgreementNameBuilder addContractualDefinitionsType(List<? extends FieldWithMetaContractualDefinitionsEnum> contractualDefinitionsTypes) {
			if (contractualDefinitionsTypes != null) {
				for (FieldWithMetaContractualDefinitionsEnum toAdd : contractualDefinitionsTypes) {
					this.contractualDefinitionsType.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("contractualDefinitionsType")
		public AgreementName.AgreementNameBuilder setContractualDefinitionsType(List<? extends FieldWithMetaContractualDefinitionsEnum> contractualDefinitionsTypes) {
			if (contractualDefinitionsTypes == null)  {
				this.contractualDefinitionsType = new ArrayList<>();
			}
			else {
				this.contractualDefinitionsType = contractualDefinitionsTypes.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualDefinitionsTypeValue(List<? extends ContractualDefinitionsEnum> contractualDefinitionsTypes) {
			if (contractualDefinitionsTypes != null) {
				for (ContractualDefinitionsEnum toAdd : contractualDefinitionsTypes) {
					this.addContractualDefinitionsTypeValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder setContractualDefinitionsTypeValue(List<? extends ContractualDefinitionsEnum> contractualDefinitionsTypes) {
			this.contractualDefinitionsType.clear();
			if (contractualDefinitionsTypes!=null) {
				contractualDefinitionsTypes.forEach(this::addContractualDefinitionsTypeValue);
			}
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualTermsSupplement(ContractualTermsSupplement contractualTermsSupplement) {
			if (contractualTermsSupplement!=null) this.contractualTermsSupplement.add(contractualTermsSupplement.toBuilder());
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualTermsSupplement(ContractualTermsSupplement contractualTermsSupplement, int _idx) {
			getIndex(this.contractualTermsSupplement, _idx, () -> contractualTermsSupplement.toBuilder());
			return this;
		}
		@Override 
		public AgreementName.AgreementNameBuilder addContractualTermsSupplement(List<? extends ContractualTermsSupplement> contractualTermsSupplements) {
			if (contractualTermsSupplements != null) {
				for (ContractualTermsSupplement toAdd : contractualTermsSupplements) {
					this.contractualTermsSupplement.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("contractualTermsSupplement")
		public AgreementName.AgreementNameBuilder setContractualTermsSupplement(List<? extends ContractualTermsSupplement> contractualTermsSupplements) {
			if (contractualTermsSupplements == null)  {
				this.contractualTermsSupplement = new ArrayList<>();
			}
			else {
				this.contractualTermsSupplement = contractualTermsSupplements.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualMatrix(ContractualMatrix contractualMatrix) {
			if (contractualMatrix!=null) this.contractualMatrix.add(contractualMatrix.toBuilder());
			return this;
		}
		
		@Override
		public AgreementName.AgreementNameBuilder addContractualMatrix(ContractualMatrix contractualMatrix, int _idx) {
			getIndex(this.contractualMatrix, _idx, () -> contractualMatrix.toBuilder());
			return this;
		}
		@Override 
		public AgreementName.AgreementNameBuilder addContractualMatrix(List<? extends ContractualMatrix> contractualMatrixs) {
			if (contractualMatrixs != null) {
				for (ContractualMatrix toAdd : contractualMatrixs) {
					this.contractualMatrix.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("contractualMatrix")
		public AgreementName.AgreementNameBuilder setContractualMatrix(List<? extends ContractualMatrix> contractualMatrixs) {
			if (contractualMatrixs == null)  {
				this.contractualMatrix = new ArrayList<>();
			}
			else {
				this.contractualMatrix = contractualMatrixs.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("masterAgreementType")
		public AgreementName.AgreementNameBuilder setMasterAgreementType(FieldWithMetaMasterAgreementTypeEnum masterAgreementType) {
			this.masterAgreementType = masterAgreementType==null?null:masterAgreementType.toBuilder();
			return this;
		}
		@Override
		public AgreementName.AgreementNameBuilder setMasterAgreementTypeValue(MasterAgreementTypeEnum masterAgreementType) {
			this.getOrCreateMasterAgreementType().setValue(masterAgreementType);
			return this;
		}
		@Override
		@RosettaAttribute("masterConfirmationType")
		public AgreementName.AgreementNameBuilder setMasterConfirmationType(FieldWithMetaMasterConfirmationTypeEnum masterConfirmationType) {
			this.masterConfirmationType = masterConfirmationType==null?null:masterConfirmationType.toBuilder();
			return this;
		}
		@Override
		public AgreementName.AgreementNameBuilder setMasterConfirmationTypeValue(MasterConfirmationTypeEnum masterConfirmationType) {
			this.getOrCreateMasterConfirmationType().setValue(masterConfirmationType);
			return this;
		}
		@Override
		@RosettaAttribute("masterConfirmationAnnexType")
		public AgreementName.AgreementNameBuilder setMasterConfirmationAnnexType(FieldWithMetaMasterConfirmationAnnexTypeEnum masterConfirmationAnnexType) {
			this.masterConfirmationAnnexType = masterConfirmationAnnexType==null?null:masterConfirmationAnnexType.toBuilder();
			return this;
		}
		@Override
		public AgreementName.AgreementNameBuilder setMasterConfirmationAnnexTypeValue(MasterConfirmationAnnexTypeEnum masterConfirmationAnnexType) {
			this.getOrCreateMasterConfirmationAnnexType().setValue(masterConfirmationAnnexType);
			return this;
		}
		@Override
		@RosettaAttribute("otherAgreement")
		public AgreementName.AgreementNameBuilder setOtherAgreement(String otherAgreement) {
			this.otherAgreement = otherAgreement==null?null:otherAgreement;
			return this;
		}
		
		@Override
		public AgreementName build() {
			return new AgreementName.AgreementNameImpl(this);
		}
		
		@Override
		public AgreementName.AgreementNameBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AgreementName.AgreementNameBuilder prune() {
			if (creditSupportAgreementType!=null && !creditSupportAgreementType.prune().hasData()) creditSupportAgreementType = null;
			contractualDefinitionsType = contractualDefinitionsType.stream().filter(b->b!=null).<FieldWithMetaContractualDefinitionsEnum.FieldWithMetaContractualDefinitionsEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			contractualTermsSupplement = contractualTermsSupplement.stream().filter(b->b!=null).<ContractualTermsSupplement.ContractualTermsSupplementBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			contractualMatrix = contractualMatrix.stream().filter(b->b!=null).<ContractualMatrix.ContractualMatrixBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (masterAgreementType!=null && !masterAgreementType.prune().hasData()) masterAgreementType = null;
			if (masterConfirmationType!=null && !masterConfirmationType.prune().hasData()) masterConfirmationType = null;
			if (masterConfirmationAnnexType!=null && !masterConfirmationAnnexType.prune().hasData()) masterConfirmationAnnexType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAgreementType()!=null) return true;
			if (getCreditSupportAgreementType()!=null) return true;
			if (getCreditSupportAgreementMarginType()!=null) return true;
			if (getContractualDefinitionsType()!=null && !getContractualDefinitionsType().isEmpty()) return true;
			if (getContractualTermsSupplement()!=null && getContractualTermsSupplement().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getContractualMatrix()!=null && getContractualMatrix().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMasterAgreementType()!=null) return true;
			if (getMasterConfirmationType()!=null) return true;
			if (getMasterConfirmationAnnexType()!=null) return true;
			if (getOtherAgreement()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AgreementName.AgreementNameBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AgreementName.AgreementNameBuilder o = (AgreementName.AgreementNameBuilder) other;
			
			merger.mergeRosetta(getCreditSupportAgreementType(), o.getCreditSupportAgreementType(), this::setCreditSupportAgreementType);
			merger.mergeRosetta(getContractualDefinitionsType(), o.getContractualDefinitionsType(), this::getOrCreateContractualDefinitionsType);
			merger.mergeRosetta(getContractualTermsSupplement(), o.getContractualTermsSupplement(), this::getOrCreateContractualTermsSupplement);
			merger.mergeRosetta(getContractualMatrix(), o.getContractualMatrix(), this::getOrCreateContractualMatrix);
			merger.mergeRosetta(getMasterAgreementType(), o.getMasterAgreementType(), this::setMasterAgreementType);
			merger.mergeRosetta(getMasterConfirmationType(), o.getMasterConfirmationType(), this::setMasterConfirmationType);
			merger.mergeRosetta(getMasterConfirmationAnnexType(), o.getMasterConfirmationAnnexType(), this::setMasterConfirmationAnnexType);
			
			merger.mergeBasic(getAgreementType(), o.getAgreementType(), this::setAgreementType);
			merger.mergeBasic(getCreditSupportAgreementMarginType(), o.getCreditSupportAgreementMarginType(), this::setCreditSupportAgreementMarginType);
			merger.mergeBasic(getOtherAgreement(), o.getOtherAgreement(), this::setOtherAgreement);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AgreementName _that = getType().cast(o);
		
			if (!Objects.equals(agreementType, _that.getAgreementType())) return false;
			if (!Objects.equals(creditSupportAgreementType, _that.getCreditSupportAgreementType())) return false;
			if (!Objects.equals(creditSupportAgreementMarginType, _that.getCreditSupportAgreementMarginType())) return false;
			if (!ListEquals.listEquals(contractualDefinitionsType, _that.getContractualDefinitionsType())) return false;
			if (!ListEquals.listEquals(contractualTermsSupplement, _that.getContractualTermsSupplement())) return false;
			if (!ListEquals.listEquals(contractualMatrix, _that.getContractualMatrix())) return false;
			if (!Objects.equals(masterAgreementType, _that.getMasterAgreementType())) return false;
			if (!Objects.equals(masterConfirmationType, _that.getMasterConfirmationType())) return false;
			if (!Objects.equals(masterConfirmationAnnexType, _that.getMasterConfirmationAnnexType())) return false;
			if (!Objects.equals(otherAgreement, _that.getOtherAgreement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agreementType != null ? agreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditSupportAgreementType != null ? creditSupportAgreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditSupportAgreementMarginType != null ? creditSupportAgreementMarginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (contractualDefinitionsType != null ? contractualDefinitionsType.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (contractualTermsSupplement != null ? contractualTermsSupplement.hashCode() : 0);
			_result = 31 * _result + (contractualMatrix != null ? contractualMatrix.hashCode() : 0);
			_result = 31 * _result + (masterAgreementType != null ? masterAgreementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (masterConfirmationType != null ? masterConfirmationType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (masterConfirmationAnnexType != null ? masterConfirmationAnnexType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (otherAgreement != null ? otherAgreement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AgreementNameBuilder {" +
				"agreementType=" + this.agreementType + ", " +
				"creditSupportAgreementType=" + this.creditSupportAgreementType + ", " +
				"creditSupportAgreementMarginType=" + this.creditSupportAgreementMarginType + ", " +
				"contractualDefinitionsType=" + this.contractualDefinitionsType + ", " +
				"contractualTermsSupplement=" + this.contractualTermsSupplement + ", " +
				"contractualMatrix=" + this.contractualMatrix + ", " +
				"masterAgreementType=" + this.masterAgreementType + ", " +
				"masterConfirmationType=" + this.masterConfirmationType + ", " +
				"masterConfirmationAnnexType=" + this.masterConfirmationAnnexType + ", " +
				"otherAgreement=" + this.otherAgreement +
			'}';
		}
	}
}
