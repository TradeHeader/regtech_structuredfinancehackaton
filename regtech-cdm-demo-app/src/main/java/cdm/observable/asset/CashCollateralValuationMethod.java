package cdm.observable.asset;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.CashCollateralValuationMethod.CashCollateralValuationMethodBuilder;
import cdm.observable.asset.CashCollateralValuationMethod.CashCollateralValuationMethodBuilderImpl;
import cdm.observable.asset.CashCollateralValuationMethod.CashCollateralValuationMethodImpl;
import cdm.observable.asset.CsaTypeEnum;
import cdm.observable.asset.PartyDeterminationEnum;
import cdm.observable.asset.meta.CashCollateralValuationMethodMeta;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * This type is a generic structure that can represent the parameters of several mid-market valuation and replacement value methods described in the 2021 ISDA Definitions.
 * @version ${project.version}
 */
@RosettaDataType(value="CashCollateralValuationMethod", builder=CashCollateralValuationMethod.CashCollateralValuationMethodBuilderImpl.class, version="${project.version}")
public interface CashCollateralValuationMethod extends RosettaModelObject {

	CashCollateralValuationMethodMeta metaData = new CashCollateralValuationMethodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This may be used to specify what type of CSA (credit support annex/agreement) is to be used for cash settlement purposes.
	 */
	CsaTypeEnum getApplicableCsa();
	/**
	 * This may be used to indicate the currency of cash collateral for cash settlement purposes.
	 */
	String getCashCollateralCurrency();
	/**
	 * This may be used to indicate the interest rate to be used for cash collateral for cash settlement purposes.
	 */
	FieldWithMetaString getCashCollateralInterestRate();
	/**
	 * This may be used to indicate the discount rate to be used for cash collateral for cash settlement purposes.
	 */
	FieldWithMetaString getAgreedDiscountRate();
	/**
	 * This may be used to specify which party is protected (e.g. under Replacement Value cash settlement methods).
	 */
	List<PartyDeterminationEnum> getProtectedParty();
	/**
	 * This may be used to indicate that &#39;prescribed documentation adjustment&#39; is applicable.
	 */
	Boolean getPrescribedDocumentationAdjustment();

	/*********************** Build Methods  ***********************/
	CashCollateralValuationMethod build();
	
	CashCollateralValuationMethod.CashCollateralValuationMethodBuilder toBuilder();
	
	static CashCollateralValuationMethod.CashCollateralValuationMethodBuilder builder() {
		return new CashCollateralValuationMethod.CashCollateralValuationMethodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CashCollateralValuationMethod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CashCollateralValuationMethod> getType() {
		return CashCollateralValuationMethod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicableCsa"), CsaTypeEnum.class, getApplicableCsa(), this);
		processor.processBasic(path.newSubPath("cashCollateralCurrency"), String.class, getCashCollateralCurrency(), this);
		processRosetta(path.newSubPath("cashCollateralInterestRate"), processor, FieldWithMetaString.class, getCashCollateralInterestRate());
		processRosetta(path.newSubPath("agreedDiscountRate"), processor, FieldWithMetaString.class, getAgreedDiscountRate());
		processor.processBasic(path.newSubPath("protectedParty"), PartyDeterminationEnum.class, getProtectedParty(), this);
		processor.processBasic(path.newSubPath("prescribedDocumentationAdjustment"), Boolean.class, getPrescribedDocumentationAdjustment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashCollateralValuationMethodBuilder extends CashCollateralValuationMethod, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCashCollateralInterestRate();
		FieldWithMetaString.FieldWithMetaStringBuilder getCashCollateralInterestRate();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAgreedDiscountRate();
		FieldWithMetaString.FieldWithMetaStringBuilder getAgreedDiscountRate();
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setApplicableCsa(CsaTypeEnum applicableCsa);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralCurrency(String cashCollateralCurrency);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralInterestRate(FieldWithMetaString cashCollateralInterestRate0);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralInterestRateValue(String cashCollateralInterestRate1);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setAgreedDiscountRate(FieldWithMetaString agreedDiscountRate0);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setAgreedDiscountRateValue(String agreedDiscountRate1);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(PartyDeterminationEnum protectedParty0);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(PartyDeterminationEnum protectedParty1, int _idx);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(List<? extends PartyDeterminationEnum> protectedParty2);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setProtectedParty(List<? extends PartyDeterminationEnum> protectedParty3);
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setPrescribedDocumentationAdjustment(Boolean prescribedDocumentationAdjustment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicableCsa"), CsaTypeEnum.class, getApplicableCsa(), this);
			processor.processBasic(path.newSubPath("cashCollateralCurrency"), String.class, getCashCollateralCurrency(), this);
			processRosetta(path.newSubPath("cashCollateralInterestRate"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCashCollateralInterestRate());
			processRosetta(path.newSubPath("agreedDiscountRate"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getAgreedDiscountRate());
			processor.processBasic(path.newSubPath("protectedParty"), PartyDeterminationEnum.class, getProtectedParty(), this);
			processor.processBasic(path.newSubPath("prescribedDocumentationAdjustment"), Boolean.class, getPrescribedDocumentationAdjustment(), this);
		}
		

		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder prune();
	}

	/*********************** Immutable Implementation of CashCollateralValuationMethod  ***********************/
	class CashCollateralValuationMethodImpl implements CashCollateralValuationMethod {
		private final CsaTypeEnum applicableCsa;
		private final String cashCollateralCurrency;
		private final FieldWithMetaString cashCollateralInterestRate;
		private final FieldWithMetaString agreedDiscountRate;
		private final List<PartyDeterminationEnum> protectedParty;
		private final Boolean prescribedDocumentationAdjustment;
		
		protected CashCollateralValuationMethodImpl(CashCollateralValuationMethod.CashCollateralValuationMethodBuilder builder) {
			this.applicableCsa = builder.getApplicableCsa();
			this.cashCollateralCurrency = builder.getCashCollateralCurrency();
			this.cashCollateralInterestRate = ofNullable(builder.getCashCollateralInterestRate()).map(f->f.build()).orElse(null);
			this.agreedDiscountRate = ofNullable(builder.getAgreedDiscountRate()).map(f->f.build()).orElse(null);
			this.protectedParty = ofNullable(builder.getProtectedParty()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.prescribedDocumentationAdjustment = builder.getPrescribedDocumentationAdjustment();
		}
		
		@Override
		@RosettaAttribute("applicableCsa")
		public CsaTypeEnum getApplicableCsa() {
			return applicableCsa;
		}
		
		@Override
		@RosettaAttribute("cashCollateralCurrency")
		public String getCashCollateralCurrency() {
			return cashCollateralCurrency;
		}
		
		@Override
		@RosettaAttribute("cashCollateralInterestRate")
		public FieldWithMetaString getCashCollateralInterestRate() {
			return cashCollateralInterestRate;
		}
		
		@Override
		@RosettaAttribute("agreedDiscountRate")
		public FieldWithMetaString getAgreedDiscountRate() {
			return agreedDiscountRate;
		}
		
		@Override
		@RosettaAttribute("protectedParty")
		public List<PartyDeterminationEnum> getProtectedParty() {
			return protectedParty;
		}
		
		@Override
		@RosettaAttribute("prescribedDocumentationAdjustment")
		public Boolean getPrescribedDocumentationAdjustment() {
			return prescribedDocumentationAdjustment;
		}
		
		@Override
		public CashCollateralValuationMethod build() {
			return this;
		}
		
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder toBuilder() {
			CashCollateralValuationMethod.CashCollateralValuationMethodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CashCollateralValuationMethod.CashCollateralValuationMethodBuilder builder) {
			ofNullable(getApplicableCsa()).ifPresent(builder::setApplicableCsa);
			ofNullable(getCashCollateralCurrency()).ifPresent(builder::setCashCollateralCurrency);
			ofNullable(getCashCollateralInterestRate()).ifPresent(builder::setCashCollateralInterestRate);
			ofNullable(getAgreedDiscountRate()).ifPresent(builder::setAgreedDiscountRate);
			ofNullable(getProtectedParty()).ifPresent(builder::setProtectedParty);
			ofNullable(getPrescribedDocumentationAdjustment()).ifPresent(builder::setPrescribedDocumentationAdjustment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashCollateralValuationMethod _that = getType().cast(o);
		
			if (!Objects.equals(applicableCsa, _that.getApplicableCsa())) return false;
			if (!Objects.equals(cashCollateralCurrency, _that.getCashCollateralCurrency())) return false;
			if (!Objects.equals(cashCollateralInterestRate, _that.getCashCollateralInterestRate())) return false;
			if (!Objects.equals(agreedDiscountRate, _that.getAgreedDiscountRate())) return false;
			if (!ListEquals.listEquals(protectedParty, _that.getProtectedParty())) return false;
			if (!Objects.equals(prescribedDocumentationAdjustment, _that.getPrescribedDocumentationAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicableCsa != null ? applicableCsa.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashCollateralCurrency != null ? cashCollateralCurrency.hashCode() : 0);
			_result = 31 * _result + (cashCollateralInterestRate != null ? cashCollateralInterestRate.hashCode() : 0);
			_result = 31 * _result + (agreedDiscountRate != null ? agreedDiscountRate.hashCode() : 0);
			_result = 31 * _result + (protectedParty != null ? protectedParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (prescribedDocumentationAdjustment != null ? prescribedDocumentationAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashCollateralValuationMethod {" +
				"applicableCsa=" + this.applicableCsa + ", " +
				"cashCollateralCurrency=" + this.cashCollateralCurrency + ", " +
				"cashCollateralInterestRate=" + this.cashCollateralInterestRate + ", " +
				"agreedDiscountRate=" + this.agreedDiscountRate + ", " +
				"protectedParty=" + this.protectedParty + ", " +
				"prescribedDocumentationAdjustment=" + this.prescribedDocumentationAdjustment +
			'}';
		}
	}

	/*********************** Builder Implementation of CashCollateralValuationMethod  ***********************/
	class CashCollateralValuationMethodBuilderImpl implements CashCollateralValuationMethod.CashCollateralValuationMethodBuilder {
	
		protected CsaTypeEnum applicableCsa;
		protected String cashCollateralCurrency;
		protected FieldWithMetaString.FieldWithMetaStringBuilder cashCollateralInterestRate;
		protected FieldWithMetaString.FieldWithMetaStringBuilder agreedDiscountRate;
		protected List<PartyDeterminationEnum> protectedParty = new ArrayList<>();
		protected Boolean prescribedDocumentationAdjustment;
	
		public CashCollateralValuationMethodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicableCsa")
		public CsaTypeEnum getApplicableCsa() {
			return applicableCsa;
		}
		
		@Override
		@RosettaAttribute("cashCollateralCurrency")
		public String getCashCollateralCurrency() {
			return cashCollateralCurrency;
		}
		
		@Override
		@RosettaAttribute("cashCollateralInterestRate")
		public FieldWithMetaString.FieldWithMetaStringBuilder getCashCollateralInterestRate() {
			return cashCollateralInterestRate;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCashCollateralInterestRate() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (cashCollateralInterestRate!=null) {
				result = cashCollateralInterestRate;
			}
			else {
				result = cashCollateralInterestRate = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("agreedDiscountRate")
		public FieldWithMetaString.FieldWithMetaStringBuilder getAgreedDiscountRate() {
			return agreedDiscountRate;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAgreedDiscountRate() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (agreedDiscountRate!=null) {
				result = agreedDiscountRate;
			}
			else {
				result = agreedDiscountRate = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("protectedParty")
		public List<PartyDeterminationEnum> getProtectedParty() {
			return protectedParty;
		}
		
		@Override
		@RosettaAttribute("prescribedDocumentationAdjustment")
		public Boolean getPrescribedDocumentationAdjustment() {
			return prescribedDocumentationAdjustment;
		}
		
	
		@Override
		@RosettaAttribute("applicableCsa")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setApplicableCsa(CsaTypeEnum applicableCsa) {
			this.applicableCsa = applicableCsa==null?null:applicableCsa;
			return this;
		}
		@Override
		@RosettaAttribute("cashCollateralCurrency")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralCurrency(String cashCollateralCurrency) {
			this.cashCollateralCurrency = cashCollateralCurrency==null?null:cashCollateralCurrency;
			return this;
		}
		@Override
		@RosettaAttribute("cashCollateralInterestRate")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralInterestRate(FieldWithMetaString cashCollateralInterestRate) {
			this.cashCollateralInterestRate = cashCollateralInterestRate==null?null:cashCollateralInterestRate.toBuilder();
			return this;
		}
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setCashCollateralInterestRateValue(String cashCollateralInterestRate) {
			this.getOrCreateCashCollateralInterestRate().setValue(cashCollateralInterestRate);
			return this;
		}
		@Override
		@RosettaAttribute("agreedDiscountRate")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setAgreedDiscountRate(FieldWithMetaString agreedDiscountRate) {
			this.agreedDiscountRate = agreedDiscountRate==null?null:agreedDiscountRate.toBuilder();
			return this;
		}
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setAgreedDiscountRateValue(String agreedDiscountRate) {
			this.getOrCreateAgreedDiscountRate().setValue(agreedDiscountRate);
			return this;
		}
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(PartyDeterminationEnum protectedParty) {
			if (protectedParty!=null) this.protectedParty.add(protectedParty);
			return this;
		}
		
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(PartyDeterminationEnum protectedParty, int _idx) {
			getIndex(this.protectedParty, _idx, () -> protectedParty);
			return this;
		}
		@Override 
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder addProtectedParty(List<? extends PartyDeterminationEnum> protectedPartys) {
			if (protectedPartys != null) {
				for (PartyDeterminationEnum toAdd : protectedPartys) {
					this.protectedParty.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("protectedParty")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setProtectedParty(List<? extends PartyDeterminationEnum> protectedPartys) {
			if (protectedPartys == null)  {
				this.protectedParty = new ArrayList<>();
			}
			else {
				this.protectedParty = protectedPartys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("prescribedDocumentationAdjustment")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder setPrescribedDocumentationAdjustment(Boolean prescribedDocumentationAdjustment) {
			this.prescribedDocumentationAdjustment = prescribedDocumentationAdjustment==null?null:prescribedDocumentationAdjustment;
			return this;
		}
		
		@Override
		public CashCollateralValuationMethod build() {
			return new CashCollateralValuationMethod.CashCollateralValuationMethodImpl(this);
		}
		
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder prune() {
			if (cashCollateralInterestRate!=null && !cashCollateralInterestRate.prune().hasData()) cashCollateralInterestRate = null;
			if (agreedDiscountRate!=null && !agreedDiscountRate.prune().hasData()) agreedDiscountRate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicableCsa()!=null) return true;
			if (getCashCollateralCurrency()!=null) return true;
			if (getCashCollateralInterestRate()!=null) return true;
			if (getAgreedDiscountRate()!=null) return true;
			if (getProtectedParty()!=null && !getProtectedParty().isEmpty()) return true;
			if (getPrescribedDocumentationAdjustment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CashCollateralValuationMethod.CashCollateralValuationMethodBuilder o = (CashCollateralValuationMethod.CashCollateralValuationMethodBuilder) other;
			
			merger.mergeRosetta(getCashCollateralInterestRate(), o.getCashCollateralInterestRate(), this::setCashCollateralInterestRate);
			merger.mergeRosetta(getAgreedDiscountRate(), o.getAgreedDiscountRate(), this::setAgreedDiscountRate);
			
			merger.mergeBasic(getApplicableCsa(), o.getApplicableCsa(), this::setApplicableCsa);
			merger.mergeBasic(getCashCollateralCurrency(), o.getCashCollateralCurrency(), this::setCashCollateralCurrency);
			merger.mergeBasic(getProtectedParty(), o.getProtectedParty(), (Consumer<PartyDeterminationEnum>) this::addProtectedParty);
			merger.mergeBasic(getPrescribedDocumentationAdjustment(), o.getPrescribedDocumentationAdjustment(), this::setPrescribedDocumentationAdjustment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashCollateralValuationMethod _that = getType().cast(o);
		
			if (!Objects.equals(applicableCsa, _that.getApplicableCsa())) return false;
			if (!Objects.equals(cashCollateralCurrency, _that.getCashCollateralCurrency())) return false;
			if (!Objects.equals(cashCollateralInterestRate, _that.getCashCollateralInterestRate())) return false;
			if (!Objects.equals(agreedDiscountRate, _that.getAgreedDiscountRate())) return false;
			if (!ListEquals.listEquals(protectedParty, _that.getProtectedParty())) return false;
			if (!Objects.equals(prescribedDocumentationAdjustment, _that.getPrescribedDocumentationAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicableCsa != null ? applicableCsa.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashCollateralCurrency != null ? cashCollateralCurrency.hashCode() : 0);
			_result = 31 * _result + (cashCollateralInterestRate != null ? cashCollateralInterestRate.hashCode() : 0);
			_result = 31 * _result + (agreedDiscountRate != null ? agreedDiscountRate.hashCode() : 0);
			_result = 31 * _result + (protectedParty != null ? protectedParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (prescribedDocumentationAdjustment != null ? prescribedDocumentationAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashCollateralValuationMethodBuilder {" +
				"applicableCsa=" + this.applicableCsa + ", " +
				"cashCollateralCurrency=" + this.cashCollateralCurrency + ", " +
				"cashCollateralInterestRate=" + this.cashCollateralInterestRate + ", " +
				"agreedDiscountRate=" + this.agreedDiscountRate + ", " +
				"protectedParty=" + this.protectedParty + ", " +
				"prescribedDocumentationAdjustment=" + this.prescribedDocumentationAdjustment +
			'}';
		}
	}
}
