package cdm.product.common.settlement;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.Money;
import cdm.observable.asset.ValuationMethod;
import cdm.product.common.settlement.CashSettlementMethodEnum;
import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.CashSettlementTerms.CashSettlementTermsBuilder;
import cdm.product.common.settlement.CashSettlementTerms.CashSettlementTermsBuilderImpl;
import cdm.product.common.settlement.CashSettlementTerms.CashSettlementTermsImpl;
import cdm.product.common.settlement.ValuationDate;
import cdm.product.common.settlement.meta.CashSettlementTermsMeta;
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
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines the terms required to compute and settle a cash settlement amount according to a fixing value, including the fixing source, fixing method and fixing date. In FpML, PhysicalSettlementTerms and CashSettlementTerms extend SettlementTerms. In the CDM, this extension paradigm has not been used because SettlementTerms class has been used for purposes related to securities transactions, while it is not used as such in the FpML standard (i.e. only as an abstract construct.
 * @version ${project.version}
 */
@RosettaDataType(value="CashSettlementTerms", builder=CashSettlementTerms.CashSettlementTermsBuilderImpl.class, version="${project.version}")
public interface CashSettlementTerms extends RosettaModelObject, GlobalKey {

	CashSettlementTermsMeta metaData = new CashSettlementTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the type of cash settlement method: cash price, yield curve etc.
	 */
	CashSettlementMethodEnum getCashSettlementMethod();
	/**
	 * Specifies the parameters required to obtain a valuation, including the source, quotation method (bid, mid etc.) and any applicable quotation amount.
	 */
	ValuationMethod getValuationMethod();
	/**
	 * Defines the different methods to specify a valuation date, as used for cash settlement. The Single / Multiple ValuationDate is used for the determination of recovery in a credit event, the RelativeDateOffset is used for cash-settled option, and FxFixingDate is used for cross-currency settlement.
	 */
	ValuationDate getValuationDate();
	/**
	 * The time of the cash settlement valuation date when the cash settlement amount will be determined according to the cash settlement method, if the parties have not otherwise been able to agree the cash settlement amount. When using quations, this is the time of day in the specified business center when the calculation agent seeks quotations for an amount of the reference obligation for purposes of cash settlement. ISDA 2003 Term: Valuation Time.
	 */
	BusinessCenterTime getValuationTime();
	/**
	 * The amount paid by the seller to the buyer for cash settlement on the cash settlement date. If not otherwise specified, would typically be calculated as 100 (or the Reference Price) minus the price of the Reference Obligation (all expressed as a percentage) times Floating Rate Payer Calculation Amount. ISDA 2003 Term: Cash Settlement Amount.
	 */
	Money getCashSettlementAmount();
	/**
	 * Used for fixed recovery, specifies the recovery level, determined at contract formation, to be applied on a default. Used to calculate the amount paid by the seller to the buyer for cash settlement on the cash settlement date. Amount calculation is (1 minus the Recovery Factor) multiplied by the Floating Rate Payer Calculation Amount. The currency will be derived from the Floating Rate Payer Calculation Amount.
	 */
	BigDecimal getRecoveryFactor();
	/**
	 * Used for Recovery Lock, to indicate whether fixed Settlement is Applicable or Not Applicable. If Buyer fails to deliver an effective Notice of Physical Settlement on or before the Buyer NOPS Cut-off Date, and if Seller fails to deliver an effective Seller NOPS on or before the Seller NOPS Cut-off Date, then either: (a) if Fixed Settlement is specified in the related Confirmation as not applicable, then the Seller NOPS Cut-off Date shall be the Termination Date; or (b) if Fixed Settlement is specified in the related Confirmation as applicable, then: (i) if the Fixed Settlement Amount is a positive number, Seller shall, subject to Section 3.1 (except for the requirement of satisfaction of the Notice of Physical Settlement Condition to Settlement), pay the Fixed Settlement Amount to Buyer on the Fixed Settlement Payment Date; and (ii) if the Fixed Settlement Amount is a negative number, Buyer shall, subject to Section 3.1 (except for the requirement of satisfaction of the Notice of Physical Settlement Condition to Settlement), pay the absolute value of the Fixed Settlement Amount to Seller on the Fixed Settlement Payment Date.
	 */
	Boolean getFixedSettlement();
	/**
	 * Indicates whether accrued interest is included (true) or not (false). For cash settlement this specifies whether quotations should be obtained inclusive or not of accrued interest. For physical settlement this specifies whether the buyer should deliver the obligation with an outstanding principal balance that includes or excludes accrued interest. ISDA 2003 Term: Include/Exclude Accrued Interest.
	 */
	Boolean getAccruedInterest();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CashSettlementTerms build();
	
	CashSettlementTerms.CashSettlementTermsBuilder toBuilder();
	
	static CashSettlementTerms.CashSettlementTermsBuilder builder() {
		return new CashSettlementTerms.CashSettlementTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CashSettlementTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CashSettlementTerms> getType() {
		return CashSettlementTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("cashSettlementMethod"), CashSettlementMethodEnum.class, getCashSettlementMethod(), this);
		processRosetta(path.newSubPath("valuationMethod"), processor, ValuationMethod.class, getValuationMethod());
		processRosetta(path.newSubPath("valuationDate"), processor, ValuationDate.class, getValuationDate());
		processRosetta(path.newSubPath("valuationTime"), processor, BusinessCenterTime.class, getValuationTime());
		processRosetta(path.newSubPath("cashSettlementAmount"), processor, Money.class, getCashSettlementAmount());
		processor.processBasic(path.newSubPath("recoveryFactor"), BigDecimal.class, getRecoveryFactor(), this);
		processor.processBasic(path.newSubPath("fixedSettlement"), Boolean.class, getFixedSettlement(), this);
		processor.processBasic(path.newSubPath("accruedInterest"), Boolean.class, getAccruedInterest(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashSettlementTermsBuilder extends CashSettlementTerms, RosettaModelObjectBuilder {
		ValuationMethod.ValuationMethodBuilder getOrCreateValuationMethod();
		ValuationMethod.ValuationMethodBuilder getValuationMethod();
		ValuationDate.ValuationDateBuilder getOrCreateValuationDate();
		ValuationDate.ValuationDateBuilder getValuationDate();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateValuationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getValuationTime();
		Money.MoneyBuilder getOrCreateCashSettlementAmount();
		Money.MoneyBuilder getCashSettlementAmount();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CashSettlementTerms.CashSettlementTermsBuilder setCashSettlementMethod(CashSettlementMethodEnum cashSettlementMethod);
		CashSettlementTerms.CashSettlementTermsBuilder setValuationMethod(ValuationMethod valuationMethod);
		CashSettlementTerms.CashSettlementTermsBuilder setValuationDate(ValuationDate valuationDate);
		CashSettlementTerms.CashSettlementTermsBuilder setValuationTime(BusinessCenterTime valuationTime);
		CashSettlementTerms.CashSettlementTermsBuilder setCashSettlementAmount(Money cashSettlementAmount);
		CashSettlementTerms.CashSettlementTermsBuilder setRecoveryFactor(BigDecimal recoveryFactor);
		CashSettlementTerms.CashSettlementTermsBuilder setFixedSettlement(Boolean fixedSettlement);
		CashSettlementTerms.CashSettlementTermsBuilder setAccruedInterest(Boolean accruedInterest);
		CashSettlementTerms.CashSettlementTermsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("cashSettlementMethod"), CashSettlementMethodEnum.class, getCashSettlementMethod(), this);
			processRosetta(path.newSubPath("valuationMethod"), processor, ValuationMethod.ValuationMethodBuilder.class, getValuationMethod());
			processRosetta(path.newSubPath("valuationDate"), processor, ValuationDate.ValuationDateBuilder.class, getValuationDate());
			processRosetta(path.newSubPath("valuationTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getValuationTime());
			processRosetta(path.newSubPath("cashSettlementAmount"), processor, Money.MoneyBuilder.class, getCashSettlementAmount());
			processor.processBasic(path.newSubPath("recoveryFactor"), BigDecimal.class, getRecoveryFactor(), this);
			processor.processBasic(path.newSubPath("fixedSettlement"), Boolean.class, getFixedSettlement(), this);
			processor.processBasic(path.newSubPath("accruedInterest"), Boolean.class, getAccruedInterest(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CashSettlementTerms.CashSettlementTermsBuilder prune();
	}

	/*********************** Immutable Implementation of CashSettlementTerms  ***********************/
	class CashSettlementTermsImpl implements CashSettlementTerms {
		private final CashSettlementMethodEnum cashSettlementMethod;
		private final ValuationMethod valuationMethod;
		private final ValuationDate valuationDate;
		private final BusinessCenterTime valuationTime;
		private final Money cashSettlementAmount;
		private final BigDecimal recoveryFactor;
		private final Boolean fixedSettlement;
		private final Boolean accruedInterest;
		private final MetaFields meta;
		
		protected CashSettlementTermsImpl(CashSettlementTerms.CashSettlementTermsBuilder builder) {
			this.cashSettlementMethod = builder.getCashSettlementMethod();
			this.valuationMethod = ofNullable(builder.getValuationMethod()).map(f->f.build()).orElse(null);
			this.valuationDate = ofNullable(builder.getValuationDate()).map(f->f.build()).orElse(null);
			this.valuationTime = ofNullable(builder.getValuationTime()).map(f->f.build()).orElse(null);
			this.cashSettlementAmount = ofNullable(builder.getCashSettlementAmount()).map(f->f.build()).orElse(null);
			this.recoveryFactor = builder.getRecoveryFactor();
			this.fixedSettlement = builder.getFixedSettlement();
			this.accruedInterest = builder.getAccruedInterest();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("cashSettlementMethod")
		public CashSettlementMethodEnum getCashSettlementMethod() {
			return cashSettlementMethod;
		}
		
		@Override
		@RosettaAttribute("valuationMethod")
		public ValuationMethod getValuationMethod() {
			return valuationMethod;
		}
		
		@Override
		@RosettaAttribute("valuationDate")
		public ValuationDate getValuationDate() {
			return valuationDate;
		}
		
		@Override
		@RosettaAttribute("valuationTime")
		public BusinessCenterTime getValuationTime() {
			return valuationTime;
		}
		
		@Override
		@RosettaAttribute("cashSettlementAmount")
		public Money getCashSettlementAmount() {
			return cashSettlementAmount;
		}
		
		@Override
		@RosettaAttribute("recoveryFactor")
		public BigDecimal getRecoveryFactor() {
			return recoveryFactor;
		}
		
		@Override
		@RosettaAttribute("fixedSettlement")
		public Boolean getFixedSettlement() {
			return fixedSettlement;
		}
		
		@Override
		@RosettaAttribute("accruedInterest")
		public Boolean getAccruedInterest() {
			return accruedInterest;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CashSettlementTerms build() {
			return this;
		}
		
		@Override
		public CashSettlementTerms.CashSettlementTermsBuilder toBuilder() {
			CashSettlementTerms.CashSettlementTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CashSettlementTerms.CashSettlementTermsBuilder builder) {
			ofNullable(getCashSettlementMethod()).ifPresent(builder::setCashSettlementMethod);
			ofNullable(getValuationMethod()).ifPresent(builder::setValuationMethod);
			ofNullable(getValuationDate()).ifPresent(builder::setValuationDate);
			ofNullable(getValuationTime()).ifPresent(builder::setValuationTime);
			ofNullable(getCashSettlementAmount()).ifPresent(builder::setCashSettlementAmount);
			ofNullable(getRecoveryFactor()).ifPresent(builder::setRecoveryFactor);
			ofNullable(getFixedSettlement()).ifPresent(builder::setFixedSettlement);
			ofNullable(getAccruedInterest()).ifPresent(builder::setAccruedInterest);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashSettlementTerms _that = getType().cast(o);
		
			if (!Objects.equals(cashSettlementMethod, _that.getCashSettlementMethod())) return false;
			if (!Objects.equals(valuationMethod, _that.getValuationMethod())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(valuationTime, _that.getValuationTime())) return false;
			if (!Objects.equals(cashSettlementAmount, _that.getCashSettlementAmount())) return false;
			if (!Objects.equals(recoveryFactor, _that.getRecoveryFactor())) return false;
			if (!Objects.equals(fixedSettlement, _that.getFixedSettlement())) return false;
			if (!Objects.equals(accruedInterest, _that.getAccruedInterest())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashSettlementMethod != null ? cashSettlementMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationMethod != null ? valuationMethod.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (valuationTime != null ? valuationTime.hashCode() : 0);
			_result = 31 * _result + (cashSettlementAmount != null ? cashSettlementAmount.hashCode() : 0);
			_result = 31 * _result + (recoveryFactor != null ? recoveryFactor.hashCode() : 0);
			_result = 31 * _result + (fixedSettlement != null ? fixedSettlement.hashCode() : 0);
			_result = 31 * _result + (accruedInterest != null ? accruedInterest.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashSettlementTerms {" +
				"cashSettlementMethod=" + this.cashSettlementMethod + ", " +
				"valuationMethod=" + this.valuationMethod + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"valuationTime=" + this.valuationTime + ", " +
				"cashSettlementAmount=" + this.cashSettlementAmount + ", " +
				"recoveryFactor=" + this.recoveryFactor + ", " +
				"fixedSettlement=" + this.fixedSettlement + ", " +
				"accruedInterest=" + this.accruedInterest + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of CashSettlementTerms  ***********************/
	class CashSettlementTermsBuilderImpl implements CashSettlementTerms.CashSettlementTermsBuilder, GlobalKeyBuilder {
	
		protected CashSettlementMethodEnum cashSettlementMethod;
		protected ValuationMethod.ValuationMethodBuilder valuationMethod;
		protected ValuationDate.ValuationDateBuilder valuationDate;
		protected BusinessCenterTime.BusinessCenterTimeBuilder valuationTime;
		protected Money.MoneyBuilder cashSettlementAmount;
		protected BigDecimal recoveryFactor;
		protected Boolean fixedSettlement;
		protected Boolean accruedInterest;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CashSettlementTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashSettlementMethod")
		public CashSettlementMethodEnum getCashSettlementMethod() {
			return cashSettlementMethod;
		}
		
		@Override
		@RosettaAttribute("valuationMethod")
		public ValuationMethod.ValuationMethodBuilder getValuationMethod() {
			return valuationMethod;
		}
		
		@Override
		public ValuationMethod.ValuationMethodBuilder getOrCreateValuationMethod() {
			ValuationMethod.ValuationMethodBuilder result;
			if (valuationMethod!=null) {
				result = valuationMethod;
			}
			else {
				result = valuationMethod = ValuationMethod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public ValuationDate.ValuationDateBuilder getValuationDate() {
			return valuationDate;
		}
		
		@Override
		public ValuationDate.ValuationDateBuilder getOrCreateValuationDate() {
			ValuationDate.ValuationDateBuilder result;
			if (valuationDate!=null) {
				result = valuationDate;
			}
			else {
				result = valuationDate = ValuationDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getValuationTime() {
			return valuationTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateValuationTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (valuationTime!=null) {
				result = valuationTime;
			}
			else {
				result = valuationTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashSettlementAmount")
		public Money.MoneyBuilder getCashSettlementAmount() {
			return cashSettlementAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateCashSettlementAmount() {
			Money.MoneyBuilder result;
			if (cashSettlementAmount!=null) {
				result = cashSettlementAmount;
			}
			else {
				result = cashSettlementAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("recoveryFactor")
		public BigDecimal getRecoveryFactor() {
			return recoveryFactor;
		}
		
		@Override
		@RosettaAttribute("fixedSettlement")
		public Boolean getFixedSettlement() {
			return fixedSettlement;
		}
		
		@Override
		@RosettaAttribute("accruedInterest")
		public Boolean getAccruedInterest() {
			return accruedInterest;
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
		@RosettaAttribute("cashSettlementMethod")
		public CashSettlementTerms.CashSettlementTermsBuilder setCashSettlementMethod(CashSettlementMethodEnum cashSettlementMethod) {
			this.cashSettlementMethod = cashSettlementMethod==null?null:cashSettlementMethod;
			return this;
		}
		@Override
		@RosettaAttribute("valuationMethod")
		public CashSettlementTerms.CashSettlementTermsBuilder setValuationMethod(ValuationMethod valuationMethod) {
			this.valuationMethod = valuationMethod==null?null:valuationMethod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public CashSettlementTerms.CashSettlementTermsBuilder setValuationDate(ValuationDate valuationDate) {
			this.valuationDate = valuationDate==null?null:valuationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationTime")
		public CashSettlementTerms.CashSettlementTermsBuilder setValuationTime(BusinessCenterTime valuationTime) {
			this.valuationTime = valuationTime==null?null:valuationTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashSettlementAmount")
		public CashSettlementTerms.CashSettlementTermsBuilder setCashSettlementAmount(Money cashSettlementAmount) {
			this.cashSettlementAmount = cashSettlementAmount==null?null:cashSettlementAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("recoveryFactor")
		public CashSettlementTerms.CashSettlementTermsBuilder setRecoveryFactor(BigDecimal recoveryFactor) {
			this.recoveryFactor = recoveryFactor==null?null:recoveryFactor;
			return this;
		}
		@Override
		@RosettaAttribute("fixedSettlement")
		public CashSettlementTerms.CashSettlementTermsBuilder setFixedSettlement(Boolean fixedSettlement) {
			this.fixedSettlement = fixedSettlement==null?null:fixedSettlement;
			return this;
		}
		@Override
		@RosettaAttribute("accruedInterest")
		public CashSettlementTerms.CashSettlementTermsBuilder setAccruedInterest(Boolean accruedInterest) {
			this.accruedInterest = accruedInterest==null?null:accruedInterest;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CashSettlementTerms.CashSettlementTermsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CashSettlementTerms build() {
			return new CashSettlementTerms.CashSettlementTermsImpl(this);
		}
		
		@Override
		public CashSettlementTerms.CashSettlementTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashSettlementTerms.CashSettlementTermsBuilder prune() {
			if (valuationMethod!=null && !valuationMethod.prune().hasData()) valuationMethod = null;
			if (valuationDate!=null && !valuationDate.prune().hasData()) valuationDate = null;
			if (valuationTime!=null && !valuationTime.prune().hasData()) valuationTime = null;
			if (cashSettlementAmount!=null && !cashSettlementAmount.prune().hasData()) cashSettlementAmount = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCashSettlementMethod()!=null) return true;
			if (getValuationMethod()!=null && getValuationMethod().hasData()) return true;
			if (getValuationDate()!=null && getValuationDate().hasData()) return true;
			if (getValuationTime()!=null && getValuationTime().hasData()) return true;
			if (getCashSettlementAmount()!=null && getCashSettlementAmount().hasData()) return true;
			if (getRecoveryFactor()!=null) return true;
			if (getFixedSettlement()!=null) return true;
			if (getAccruedInterest()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashSettlementTerms.CashSettlementTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CashSettlementTerms.CashSettlementTermsBuilder o = (CashSettlementTerms.CashSettlementTermsBuilder) other;
			
			merger.mergeRosetta(getValuationMethod(), o.getValuationMethod(), this::setValuationMethod);
			merger.mergeRosetta(getValuationDate(), o.getValuationDate(), this::setValuationDate);
			merger.mergeRosetta(getValuationTime(), o.getValuationTime(), this::setValuationTime);
			merger.mergeRosetta(getCashSettlementAmount(), o.getCashSettlementAmount(), this::setCashSettlementAmount);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getCashSettlementMethod(), o.getCashSettlementMethod(), this::setCashSettlementMethod);
			merger.mergeBasic(getRecoveryFactor(), o.getRecoveryFactor(), this::setRecoveryFactor);
			merger.mergeBasic(getFixedSettlement(), o.getFixedSettlement(), this::setFixedSettlement);
			merger.mergeBasic(getAccruedInterest(), o.getAccruedInterest(), this::setAccruedInterest);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashSettlementTerms _that = getType().cast(o);
		
			if (!Objects.equals(cashSettlementMethod, _that.getCashSettlementMethod())) return false;
			if (!Objects.equals(valuationMethod, _that.getValuationMethod())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(valuationTime, _that.getValuationTime())) return false;
			if (!Objects.equals(cashSettlementAmount, _that.getCashSettlementAmount())) return false;
			if (!Objects.equals(recoveryFactor, _that.getRecoveryFactor())) return false;
			if (!Objects.equals(fixedSettlement, _that.getFixedSettlement())) return false;
			if (!Objects.equals(accruedInterest, _that.getAccruedInterest())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashSettlementMethod != null ? cashSettlementMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationMethod != null ? valuationMethod.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (valuationTime != null ? valuationTime.hashCode() : 0);
			_result = 31 * _result + (cashSettlementAmount != null ? cashSettlementAmount.hashCode() : 0);
			_result = 31 * _result + (recoveryFactor != null ? recoveryFactor.hashCode() : 0);
			_result = 31 * _result + (fixedSettlement != null ? fixedSettlement.hashCode() : 0);
			_result = 31 * _result + (accruedInterest != null ? accruedInterest.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashSettlementTermsBuilder {" +
				"cashSettlementMethod=" + this.cashSettlementMethod + ", " +
				"valuationMethod=" + this.valuationMethod + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"valuationTime=" + this.valuationTime + ", " +
				"cashSettlementAmount=" + this.cashSettlementAmount + ", " +
				"recoveryFactor=" + this.recoveryFactor + ", " +
				"fixedSettlement=" + this.fixedSettlement + ", " +
				"accruedInterest=" + this.accruedInterest + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
