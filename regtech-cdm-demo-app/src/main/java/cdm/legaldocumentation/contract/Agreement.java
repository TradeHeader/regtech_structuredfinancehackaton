package cdm.legaldocumentation.contract;

import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.contract.Agreement.AgreementBuilder;
import cdm.legaldocumentation.contract.Agreement.AgreementBuilderImpl;
import cdm.legaldocumentation.contract.Agreement.AgreementImpl;
import cdm.legaldocumentation.contract.meta.AgreementMeta;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import cdm.legaldocumentation.csa.CreditSupportAgreementElections;
import cdm.legaldocumentation.csa.SecurityAgreementElections;
import cdm.legaldocumentation.master.MasterAgreementSchedule;
import cdm.legaldocumentation.master.TransactionAdditionalTerms;
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
 * Specification of the standard set of terms that define a legal agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="Agreement", builder=Agreement.AgreementBuilderImpl.class, version="${project.version}")
public interface Agreement extends RosettaModelObject {

	AgreementMeta metaData = new AgreementMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Elections to specify a Credit Support Annex or Credit Support Deed for Intial or Variation Margin.
	 */
	CreditSupportAgreementElections getCreditSupportAgreementElections();
	/**
	 * Elections to specify a Collateral Transfer Agreement.
	 */
	CollateralTransferAgreementElections getCollateralTransferAgreementElections();
	/**
	 * Elections to specify a Security agreement.
	 */
	SecurityAgreementElections getSecurityAgreementElections();
	/**
	 * Elections to specify a Master Agreement Schedule.
	 */
	MasterAgreementSchedule getMasterAgreementSchedule();
	/**
	 * Any additional terms which mainly intend to specify the extraordinary events that may affect a trade and the related contractual rights and obligation of the parties when this happens
	 */
	TransactionAdditionalTerms getTransactionAdditionalTerms();

	/*********************** Build Methods  ***********************/
	Agreement build();
	
	Agreement.AgreementBuilder toBuilder();
	
	static Agreement.AgreementBuilder builder() {
		return new Agreement.AgreementBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Agreement> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Agreement> getType() {
		return Agreement.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("creditSupportAgreementElections"), processor, CreditSupportAgreementElections.class, getCreditSupportAgreementElections());
		processRosetta(path.newSubPath("collateralTransferAgreementElections"), processor, CollateralTransferAgreementElections.class, getCollateralTransferAgreementElections());
		processRosetta(path.newSubPath("securityAgreementElections"), processor, SecurityAgreementElections.class, getSecurityAgreementElections());
		processRosetta(path.newSubPath("masterAgreementSchedule"), processor, MasterAgreementSchedule.class, getMasterAgreementSchedule());
		processRosetta(path.newSubPath("transactionAdditionalTerms"), processor, TransactionAdditionalTerms.class, getTransactionAdditionalTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AgreementBuilder extends Agreement, RosettaModelObjectBuilder {
		CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder getOrCreateCreditSupportAgreementElections();
		CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder getCreditSupportAgreementElections();
		CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder getOrCreateCollateralTransferAgreementElections();
		CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder getCollateralTransferAgreementElections();
		SecurityAgreementElections.SecurityAgreementElectionsBuilder getOrCreateSecurityAgreementElections();
		SecurityAgreementElections.SecurityAgreementElectionsBuilder getSecurityAgreementElections();
		MasterAgreementSchedule.MasterAgreementScheduleBuilder getOrCreateMasterAgreementSchedule();
		MasterAgreementSchedule.MasterAgreementScheduleBuilder getMasterAgreementSchedule();
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder getOrCreateTransactionAdditionalTerms();
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder getTransactionAdditionalTerms();
		Agreement.AgreementBuilder setCreditSupportAgreementElections(CreditSupportAgreementElections creditSupportAgreementElections);
		Agreement.AgreementBuilder setCollateralTransferAgreementElections(CollateralTransferAgreementElections collateralTransferAgreementElections);
		Agreement.AgreementBuilder setSecurityAgreementElections(SecurityAgreementElections securityAgreementElections);
		Agreement.AgreementBuilder setMasterAgreementSchedule(MasterAgreementSchedule masterAgreementSchedule);
		Agreement.AgreementBuilder setTransactionAdditionalTerms(TransactionAdditionalTerms transactionAdditionalTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("creditSupportAgreementElections"), processor, CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder.class, getCreditSupportAgreementElections());
			processRosetta(path.newSubPath("collateralTransferAgreementElections"), processor, CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder.class, getCollateralTransferAgreementElections());
			processRosetta(path.newSubPath("securityAgreementElections"), processor, SecurityAgreementElections.SecurityAgreementElectionsBuilder.class, getSecurityAgreementElections());
			processRosetta(path.newSubPath("masterAgreementSchedule"), processor, MasterAgreementSchedule.MasterAgreementScheduleBuilder.class, getMasterAgreementSchedule());
			processRosetta(path.newSubPath("transactionAdditionalTerms"), processor, TransactionAdditionalTerms.TransactionAdditionalTermsBuilder.class, getTransactionAdditionalTerms());
		}
		

		Agreement.AgreementBuilder prune();
	}

	/*********************** Immutable Implementation of Agreement  ***********************/
	class AgreementImpl implements Agreement {
		private final CreditSupportAgreementElections creditSupportAgreementElections;
		private final CollateralTransferAgreementElections collateralTransferAgreementElections;
		private final SecurityAgreementElections securityAgreementElections;
		private final MasterAgreementSchedule masterAgreementSchedule;
		private final TransactionAdditionalTerms transactionAdditionalTerms;
		
		protected AgreementImpl(Agreement.AgreementBuilder builder) {
			this.creditSupportAgreementElections = ofNullable(builder.getCreditSupportAgreementElections()).map(f->f.build()).orElse(null);
			this.collateralTransferAgreementElections = ofNullable(builder.getCollateralTransferAgreementElections()).map(f->f.build()).orElse(null);
			this.securityAgreementElections = ofNullable(builder.getSecurityAgreementElections()).map(f->f.build()).orElse(null);
			this.masterAgreementSchedule = ofNullable(builder.getMasterAgreementSchedule()).map(f->f.build()).orElse(null);
			this.transactionAdditionalTerms = ofNullable(builder.getTransactionAdditionalTerms()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("creditSupportAgreementElections")
		public CreditSupportAgreementElections getCreditSupportAgreementElections() {
			return creditSupportAgreementElections;
		}
		
		@Override
		@RosettaAttribute("collateralTransferAgreementElections")
		public CollateralTransferAgreementElections getCollateralTransferAgreementElections() {
			return collateralTransferAgreementElections;
		}
		
		@Override
		@RosettaAttribute("securityAgreementElections")
		public SecurityAgreementElections getSecurityAgreementElections() {
			return securityAgreementElections;
		}
		
		@Override
		@RosettaAttribute("masterAgreementSchedule")
		public MasterAgreementSchedule getMasterAgreementSchedule() {
			return masterAgreementSchedule;
		}
		
		@Override
		@RosettaAttribute("transactionAdditionalTerms")
		public TransactionAdditionalTerms getTransactionAdditionalTerms() {
			return transactionAdditionalTerms;
		}
		
		@Override
		public Agreement build() {
			return this;
		}
		
		@Override
		public Agreement.AgreementBuilder toBuilder() {
			Agreement.AgreementBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Agreement.AgreementBuilder builder) {
			ofNullable(getCreditSupportAgreementElections()).ifPresent(builder::setCreditSupportAgreementElections);
			ofNullable(getCollateralTransferAgreementElections()).ifPresent(builder::setCollateralTransferAgreementElections);
			ofNullable(getSecurityAgreementElections()).ifPresent(builder::setSecurityAgreementElections);
			ofNullable(getMasterAgreementSchedule()).ifPresent(builder::setMasterAgreementSchedule);
			ofNullable(getTransactionAdditionalTerms()).ifPresent(builder::setTransactionAdditionalTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Agreement _that = getType().cast(o);
		
			if (!Objects.equals(creditSupportAgreementElections, _that.getCreditSupportAgreementElections())) return false;
			if (!Objects.equals(collateralTransferAgreementElections, _that.getCollateralTransferAgreementElections())) return false;
			if (!Objects.equals(securityAgreementElections, _that.getSecurityAgreementElections())) return false;
			if (!Objects.equals(masterAgreementSchedule, _that.getMasterAgreementSchedule())) return false;
			if (!Objects.equals(transactionAdditionalTerms, _that.getTransactionAdditionalTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditSupportAgreementElections != null ? creditSupportAgreementElections.hashCode() : 0);
			_result = 31 * _result + (collateralTransferAgreementElections != null ? collateralTransferAgreementElections.hashCode() : 0);
			_result = 31 * _result + (securityAgreementElections != null ? securityAgreementElections.hashCode() : 0);
			_result = 31 * _result + (masterAgreementSchedule != null ? masterAgreementSchedule.hashCode() : 0);
			_result = 31 * _result + (transactionAdditionalTerms != null ? transactionAdditionalTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Agreement {" +
				"creditSupportAgreementElections=" + this.creditSupportAgreementElections + ", " +
				"collateralTransferAgreementElections=" + this.collateralTransferAgreementElections + ", " +
				"securityAgreementElections=" + this.securityAgreementElections + ", " +
				"masterAgreementSchedule=" + this.masterAgreementSchedule + ", " +
				"transactionAdditionalTerms=" + this.transactionAdditionalTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of Agreement  ***********************/
	class AgreementBuilderImpl implements Agreement.AgreementBuilder {
	
		protected CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder creditSupportAgreementElections;
		protected CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder collateralTransferAgreementElections;
		protected SecurityAgreementElections.SecurityAgreementElectionsBuilder securityAgreementElections;
		protected MasterAgreementSchedule.MasterAgreementScheduleBuilder masterAgreementSchedule;
		protected TransactionAdditionalTerms.TransactionAdditionalTermsBuilder transactionAdditionalTerms;
	
		public AgreementBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("creditSupportAgreementElections")
		public CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder getCreditSupportAgreementElections() {
			return creditSupportAgreementElections;
		}
		
		@Override
		public CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder getOrCreateCreditSupportAgreementElections() {
			CreditSupportAgreementElections.CreditSupportAgreementElectionsBuilder result;
			if (creditSupportAgreementElections!=null) {
				result = creditSupportAgreementElections;
			}
			else {
				result = creditSupportAgreementElections = CreditSupportAgreementElections.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("collateralTransferAgreementElections")
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder getCollateralTransferAgreementElections() {
			return collateralTransferAgreementElections;
		}
		
		@Override
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder getOrCreateCollateralTransferAgreementElections() {
			CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder result;
			if (collateralTransferAgreementElections!=null) {
				result = collateralTransferAgreementElections;
			}
			else {
				result = collateralTransferAgreementElections = CollateralTransferAgreementElections.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("securityAgreementElections")
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder getSecurityAgreementElections() {
			return securityAgreementElections;
		}
		
		@Override
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder getOrCreateSecurityAgreementElections() {
			SecurityAgreementElections.SecurityAgreementElectionsBuilder result;
			if (securityAgreementElections!=null) {
				result = securityAgreementElections;
			}
			else {
				result = securityAgreementElections = SecurityAgreementElections.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("masterAgreementSchedule")
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder getMasterAgreementSchedule() {
			return masterAgreementSchedule;
		}
		
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder getOrCreateMasterAgreementSchedule() {
			MasterAgreementSchedule.MasterAgreementScheduleBuilder result;
			if (masterAgreementSchedule!=null) {
				result = masterAgreementSchedule;
			}
			else {
				result = masterAgreementSchedule = MasterAgreementSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("transactionAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder getTransactionAdditionalTerms() {
			return transactionAdditionalTerms;
		}
		
		@Override
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder getOrCreateTransactionAdditionalTerms() {
			TransactionAdditionalTerms.TransactionAdditionalTermsBuilder result;
			if (transactionAdditionalTerms!=null) {
				result = transactionAdditionalTerms;
			}
			else {
				result = transactionAdditionalTerms = TransactionAdditionalTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("creditSupportAgreementElections")
		public Agreement.AgreementBuilder setCreditSupportAgreementElections(CreditSupportAgreementElections creditSupportAgreementElections) {
			this.creditSupportAgreementElections = creditSupportAgreementElections==null?null:creditSupportAgreementElections.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("collateralTransferAgreementElections")
		public Agreement.AgreementBuilder setCollateralTransferAgreementElections(CollateralTransferAgreementElections collateralTransferAgreementElections) {
			this.collateralTransferAgreementElections = collateralTransferAgreementElections==null?null:collateralTransferAgreementElections.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("securityAgreementElections")
		public Agreement.AgreementBuilder setSecurityAgreementElections(SecurityAgreementElections securityAgreementElections) {
			this.securityAgreementElections = securityAgreementElections==null?null:securityAgreementElections.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("masterAgreementSchedule")
		public Agreement.AgreementBuilder setMasterAgreementSchedule(MasterAgreementSchedule masterAgreementSchedule) {
			this.masterAgreementSchedule = masterAgreementSchedule==null?null:masterAgreementSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("transactionAdditionalTerms")
		public Agreement.AgreementBuilder setTransactionAdditionalTerms(TransactionAdditionalTerms transactionAdditionalTerms) {
			this.transactionAdditionalTerms = transactionAdditionalTerms==null?null:transactionAdditionalTerms.toBuilder();
			return this;
		}
		
		@Override
		public Agreement build() {
			return new Agreement.AgreementImpl(this);
		}
		
		@Override
		public Agreement.AgreementBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Agreement.AgreementBuilder prune() {
			if (creditSupportAgreementElections!=null && !creditSupportAgreementElections.prune().hasData()) creditSupportAgreementElections = null;
			if (collateralTransferAgreementElections!=null && !collateralTransferAgreementElections.prune().hasData()) collateralTransferAgreementElections = null;
			if (securityAgreementElections!=null && !securityAgreementElections.prune().hasData()) securityAgreementElections = null;
			if (masterAgreementSchedule!=null && !masterAgreementSchedule.prune().hasData()) masterAgreementSchedule = null;
			if (transactionAdditionalTerms!=null && !transactionAdditionalTerms.prune().hasData()) transactionAdditionalTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCreditSupportAgreementElections()!=null && getCreditSupportAgreementElections().hasData()) return true;
			if (getCollateralTransferAgreementElections()!=null && getCollateralTransferAgreementElections().hasData()) return true;
			if (getSecurityAgreementElections()!=null && getSecurityAgreementElections().hasData()) return true;
			if (getMasterAgreementSchedule()!=null && getMasterAgreementSchedule().hasData()) return true;
			if (getTransactionAdditionalTerms()!=null && getTransactionAdditionalTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Agreement.AgreementBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Agreement.AgreementBuilder o = (Agreement.AgreementBuilder) other;
			
			merger.mergeRosetta(getCreditSupportAgreementElections(), o.getCreditSupportAgreementElections(), this::setCreditSupportAgreementElections);
			merger.mergeRosetta(getCollateralTransferAgreementElections(), o.getCollateralTransferAgreementElections(), this::setCollateralTransferAgreementElections);
			merger.mergeRosetta(getSecurityAgreementElections(), o.getSecurityAgreementElections(), this::setSecurityAgreementElections);
			merger.mergeRosetta(getMasterAgreementSchedule(), o.getMasterAgreementSchedule(), this::setMasterAgreementSchedule);
			merger.mergeRosetta(getTransactionAdditionalTerms(), o.getTransactionAdditionalTerms(), this::setTransactionAdditionalTerms);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Agreement _that = getType().cast(o);
		
			if (!Objects.equals(creditSupportAgreementElections, _that.getCreditSupportAgreementElections())) return false;
			if (!Objects.equals(collateralTransferAgreementElections, _that.getCollateralTransferAgreementElections())) return false;
			if (!Objects.equals(securityAgreementElections, _that.getSecurityAgreementElections())) return false;
			if (!Objects.equals(masterAgreementSchedule, _that.getMasterAgreementSchedule())) return false;
			if (!Objects.equals(transactionAdditionalTerms, _that.getTransactionAdditionalTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditSupportAgreementElections != null ? creditSupportAgreementElections.hashCode() : 0);
			_result = 31 * _result + (collateralTransferAgreementElections != null ? collateralTransferAgreementElections.hashCode() : 0);
			_result = 31 * _result + (securityAgreementElections != null ? securityAgreementElections.hashCode() : 0);
			_result = 31 * _result + (masterAgreementSchedule != null ? masterAgreementSchedule.hashCode() : 0);
			_result = 31 * _result + (transactionAdditionalTerms != null ? transactionAdditionalTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AgreementBuilder {" +
				"creditSupportAgreementElections=" + this.creditSupportAgreementElections + ", " +
				"collateralTransferAgreementElections=" + this.collateralTransferAgreementElections + ", " +
				"securityAgreementElections=" + this.securityAgreementElections + ", " +
				"masterAgreementSchedule=" + this.masterAgreementSchedule + ", " +
				"transactionAdditionalTerms=" + this.transactionAdditionalTerms +
			'}';
		}
	}
}
