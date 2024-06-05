package cdm.observable.asset;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.Money;
import cdm.observable.asset.QuotationRateTypeEnum;
import cdm.observable.asset.ValuationMethod;
import cdm.observable.asset.ValuationMethod.ValuationMethodBuilder;
import cdm.observable.asset.ValuationMethod.ValuationMethodBuilderImpl;
import cdm.observable.asset.ValuationMethod.ValuationMethodImpl;
import cdm.observable.asset.ValuationMethodEnum;
import cdm.observable.asset.ValuationSource;
import cdm.observable.asset.meta.ValuationMethodMeta;
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
 * Specifies the parameters required to obtain a valuation, including the source, quotation method (bid, mid etc.) and any applicable quotation amount.
 * @version ${project.version}
 */
@RosettaDataType(value="ValuationMethod", builder=ValuationMethod.ValuationMethodBuilderImpl.class, version="${project.version}")
public interface ValuationMethod extends RosettaModelObject {

	ValuationMethodMeta metaData = new ValuationMethodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The source for obtaining a valuation. This may come from some information source (e.g. Reuters), from a rate option fixing (e.g. FX fixing for cross-currency settlement), or from a set of reference banks. This is a mandatory attribute as the valuation method relies on one of those sources to be specified.
	 */
	ValuationSource getValuationSource();
	/**
	 * The type of price quotations to be requested from dealers when determining the market value of the reference obligation for purposes of cash settlement, or which rate quote is to be observed for a fixing. For example, Bid, Offer, Mid-market or Exercising Party Pays. ISDA 2003 Term: Quotation Method. The meaning of Exercising Party Pays is defined in the 2000 ISDA Definitions, Section 17.2. Certain Definitions Relating to Cash Settlement, paragraph (j).
	 */
	QuotationRateTypeEnum getQuotationMethod();
	/**
	 * The ISDA defined methodology for determining the final price of the reference obligation for purposes of cash settlement. (ISDA 2003 Term: Valuation Method). For example, Market, Highest etc.
	 */
	ValuationMethodEnum getValuationMethod();
	/**
	 * In the determination of a cash settlement amount, if weighted average quotations are to be obtained, the quotation amount specifies an upper limit to the outstanding principal balance of the reference obligation for which the quote should be obtained. If not specified, the ISDA definitions provide for a fallback amount equal to the floating rate payer calculation amount. ISDA 2003 Term: Quotation Amount.
	 */
	Money getQuotationAmount();
	/**
	 * In the determination of a cash settlement amount, if weighted average quotations are to be obtained, the minimum quotation amount specifies a minimum intended threshold amount of outstanding principal balance of the reference obligation for which the quote should be obtained. If not specified, the ISDA definitions provide for a fallback amount of the lower of either USD 1,000,000 (or its equivalent in the relevant obligation currency) or the quotation amount. ISDA 2003 Term: Minimum Quotation Amount.
	 */
	Money getMinimumQuotationAmount();
	/**
	 * Specifies the parameters representing several mid-market valuation and replacement value methods.
	 */
	CashCollateralValuationMethod getCashCollateralValuationMethod();

	/*********************** Build Methods  ***********************/
	ValuationMethod build();
	
	ValuationMethod.ValuationMethodBuilder toBuilder();
	
	static ValuationMethod.ValuationMethodBuilder builder() {
		return new ValuationMethod.ValuationMethodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ValuationMethod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ValuationMethod> getType() {
		return ValuationMethod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationSource"), processor, ValuationSource.class, getValuationSource());
		processor.processBasic(path.newSubPath("quotationMethod"), QuotationRateTypeEnum.class, getQuotationMethod(), this);
		processor.processBasic(path.newSubPath("valuationMethod"), ValuationMethodEnum.class, getValuationMethod(), this);
		processRosetta(path.newSubPath("quotationAmount"), processor, Money.class, getQuotationAmount());
		processRosetta(path.newSubPath("minimumQuotationAmount"), processor, Money.class, getMinimumQuotationAmount());
		processRosetta(path.newSubPath("cashCollateralValuationMethod"), processor, CashCollateralValuationMethod.class, getCashCollateralValuationMethod());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ValuationMethodBuilder extends ValuationMethod, RosettaModelObjectBuilder {
		ValuationSource.ValuationSourceBuilder getOrCreateValuationSource();
		ValuationSource.ValuationSourceBuilder getValuationSource();
		Money.MoneyBuilder getOrCreateQuotationAmount();
		Money.MoneyBuilder getQuotationAmount();
		Money.MoneyBuilder getOrCreateMinimumQuotationAmount();
		Money.MoneyBuilder getMinimumQuotationAmount();
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder getOrCreateCashCollateralValuationMethod();
		CashCollateralValuationMethod.CashCollateralValuationMethodBuilder getCashCollateralValuationMethod();
		ValuationMethod.ValuationMethodBuilder setValuationSource(ValuationSource valuationSource);
		ValuationMethod.ValuationMethodBuilder setQuotationMethod(QuotationRateTypeEnum quotationMethod);
		ValuationMethod.ValuationMethodBuilder setValuationMethod(ValuationMethodEnum valuationMethod);
		ValuationMethod.ValuationMethodBuilder setQuotationAmount(Money quotationAmount);
		ValuationMethod.ValuationMethodBuilder setMinimumQuotationAmount(Money minimumQuotationAmount);
		ValuationMethod.ValuationMethodBuilder setCashCollateralValuationMethod(CashCollateralValuationMethod cashCollateralValuationMethod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationSource"), processor, ValuationSource.ValuationSourceBuilder.class, getValuationSource());
			processor.processBasic(path.newSubPath("quotationMethod"), QuotationRateTypeEnum.class, getQuotationMethod(), this);
			processor.processBasic(path.newSubPath("valuationMethod"), ValuationMethodEnum.class, getValuationMethod(), this);
			processRosetta(path.newSubPath("quotationAmount"), processor, Money.MoneyBuilder.class, getQuotationAmount());
			processRosetta(path.newSubPath("minimumQuotationAmount"), processor, Money.MoneyBuilder.class, getMinimumQuotationAmount());
			processRosetta(path.newSubPath("cashCollateralValuationMethod"), processor, CashCollateralValuationMethod.CashCollateralValuationMethodBuilder.class, getCashCollateralValuationMethod());
		}
		

		ValuationMethod.ValuationMethodBuilder prune();
	}

	/*********************** Immutable Implementation of ValuationMethod  ***********************/
	class ValuationMethodImpl implements ValuationMethod {
		private final ValuationSource valuationSource;
		private final QuotationRateTypeEnum quotationMethod;
		private final ValuationMethodEnum valuationMethod;
		private final Money quotationAmount;
		private final Money minimumQuotationAmount;
		private final CashCollateralValuationMethod cashCollateralValuationMethod;
		
		protected ValuationMethodImpl(ValuationMethod.ValuationMethodBuilder builder) {
			this.valuationSource = ofNullable(builder.getValuationSource()).map(f->f.build()).orElse(null);
			this.quotationMethod = builder.getQuotationMethod();
			this.valuationMethod = builder.getValuationMethod();
			this.quotationAmount = ofNullable(builder.getQuotationAmount()).map(f->f.build()).orElse(null);
			this.minimumQuotationAmount = ofNullable(builder.getMinimumQuotationAmount()).map(f->f.build()).orElse(null);
			this.cashCollateralValuationMethod = ofNullable(builder.getCashCollateralValuationMethod()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("valuationSource")
		public ValuationSource getValuationSource() {
			return valuationSource;
		}
		
		@Override
		@RosettaAttribute("quotationMethod")
		public QuotationRateTypeEnum getQuotationMethod() {
			return quotationMethod;
		}
		
		@Override
		@RosettaAttribute("valuationMethod")
		public ValuationMethodEnum getValuationMethod() {
			return valuationMethod;
		}
		
		@Override
		@RosettaAttribute("quotationAmount")
		public Money getQuotationAmount() {
			return quotationAmount;
		}
		
		@Override
		@RosettaAttribute("minimumQuotationAmount")
		public Money getMinimumQuotationAmount() {
			return minimumQuotationAmount;
		}
		
		@Override
		@RosettaAttribute("cashCollateralValuationMethod")
		public CashCollateralValuationMethod getCashCollateralValuationMethod() {
			return cashCollateralValuationMethod;
		}
		
		@Override
		public ValuationMethod build() {
			return this;
		}
		
		@Override
		public ValuationMethod.ValuationMethodBuilder toBuilder() {
			ValuationMethod.ValuationMethodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ValuationMethod.ValuationMethodBuilder builder) {
			ofNullable(getValuationSource()).ifPresent(builder::setValuationSource);
			ofNullable(getQuotationMethod()).ifPresent(builder::setQuotationMethod);
			ofNullable(getValuationMethod()).ifPresent(builder::setValuationMethod);
			ofNullable(getQuotationAmount()).ifPresent(builder::setQuotationAmount);
			ofNullable(getMinimumQuotationAmount()).ifPresent(builder::setMinimumQuotationAmount);
			ofNullable(getCashCollateralValuationMethod()).ifPresent(builder::setCashCollateralValuationMethod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationMethod _that = getType().cast(o);
		
			if (!Objects.equals(valuationSource, _that.getValuationSource())) return false;
			if (!Objects.equals(quotationMethod, _that.getQuotationMethod())) return false;
			if (!Objects.equals(valuationMethod, _that.getValuationMethod())) return false;
			if (!Objects.equals(quotationAmount, _that.getQuotationAmount())) return false;
			if (!Objects.equals(minimumQuotationAmount, _that.getMinimumQuotationAmount())) return false;
			if (!Objects.equals(cashCollateralValuationMethod, _that.getCashCollateralValuationMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationSource != null ? valuationSource.hashCode() : 0);
			_result = 31 * _result + (quotationMethod != null ? quotationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationMethod != null ? valuationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (quotationAmount != null ? quotationAmount.hashCode() : 0);
			_result = 31 * _result + (minimumQuotationAmount != null ? minimumQuotationAmount.hashCode() : 0);
			_result = 31 * _result + (cashCollateralValuationMethod != null ? cashCollateralValuationMethod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationMethod {" +
				"valuationSource=" + this.valuationSource + ", " +
				"quotationMethod=" + this.quotationMethod + ", " +
				"valuationMethod=" + this.valuationMethod + ", " +
				"quotationAmount=" + this.quotationAmount + ", " +
				"minimumQuotationAmount=" + this.minimumQuotationAmount + ", " +
				"cashCollateralValuationMethod=" + this.cashCollateralValuationMethod +
			'}';
		}
	}

	/*********************** Builder Implementation of ValuationMethod  ***********************/
	class ValuationMethodBuilderImpl implements ValuationMethod.ValuationMethodBuilder {
	
		protected ValuationSource.ValuationSourceBuilder valuationSource;
		protected QuotationRateTypeEnum quotationMethod;
		protected ValuationMethodEnum valuationMethod;
		protected Money.MoneyBuilder quotationAmount;
		protected Money.MoneyBuilder minimumQuotationAmount;
		protected CashCollateralValuationMethod.CashCollateralValuationMethodBuilder cashCollateralValuationMethod;
	
		public ValuationMethodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationSource")
		public ValuationSource.ValuationSourceBuilder getValuationSource() {
			return valuationSource;
		}
		
		@Override
		public ValuationSource.ValuationSourceBuilder getOrCreateValuationSource() {
			ValuationSource.ValuationSourceBuilder result;
			if (valuationSource!=null) {
				result = valuationSource;
			}
			else {
				result = valuationSource = ValuationSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("quotationMethod")
		public QuotationRateTypeEnum getQuotationMethod() {
			return quotationMethod;
		}
		
		@Override
		@RosettaAttribute("valuationMethod")
		public ValuationMethodEnum getValuationMethod() {
			return valuationMethod;
		}
		
		@Override
		@RosettaAttribute("quotationAmount")
		public Money.MoneyBuilder getQuotationAmount() {
			return quotationAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateQuotationAmount() {
			Money.MoneyBuilder result;
			if (quotationAmount!=null) {
				result = quotationAmount;
			}
			else {
				result = quotationAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("minimumQuotationAmount")
		public Money.MoneyBuilder getMinimumQuotationAmount() {
			return minimumQuotationAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMinimumQuotationAmount() {
			Money.MoneyBuilder result;
			if (minimumQuotationAmount!=null) {
				result = minimumQuotationAmount;
			}
			else {
				result = minimumQuotationAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashCollateralValuationMethod")
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder getCashCollateralValuationMethod() {
			return cashCollateralValuationMethod;
		}
		
		@Override
		public CashCollateralValuationMethod.CashCollateralValuationMethodBuilder getOrCreateCashCollateralValuationMethod() {
			CashCollateralValuationMethod.CashCollateralValuationMethodBuilder result;
			if (cashCollateralValuationMethod!=null) {
				result = cashCollateralValuationMethod;
			}
			else {
				result = cashCollateralValuationMethod = CashCollateralValuationMethod.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("valuationSource")
		public ValuationMethod.ValuationMethodBuilder setValuationSource(ValuationSource valuationSource) {
			this.valuationSource = valuationSource==null?null:valuationSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("quotationMethod")
		public ValuationMethod.ValuationMethodBuilder setQuotationMethod(QuotationRateTypeEnum quotationMethod) {
			this.quotationMethod = quotationMethod==null?null:quotationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("valuationMethod")
		public ValuationMethod.ValuationMethodBuilder setValuationMethod(ValuationMethodEnum valuationMethod) {
			this.valuationMethod = valuationMethod==null?null:valuationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("quotationAmount")
		public ValuationMethod.ValuationMethodBuilder setQuotationAmount(Money quotationAmount) {
			this.quotationAmount = quotationAmount==null?null:quotationAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("minimumQuotationAmount")
		public ValuationMethod.ValuationMethodBuilder setMinimumQuotationAmount(Money minimumQuotationAmount) {
			this.minimumQuotationAmount = minimumQuotationAmount==null?null:minimumQuotationAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashCollateralValuationMethod")
		public ValuationMethod.ValuationMethodBuilder setCashCollateralValuationMethod(CashCollateralValuationMethod cashCollateralValuationMethod) {
			this.cashCollateralValuationMethod = cashCollateralValuationMethod==null?null:cashCollateralValuationMethod.toBuilder();
			return this;
		}
		
		@Override
		public ValuationMethod build() {
			return new ValuationMethod.ValuationMethodImpl(this);
		}
		
		@Override
		public ValuationMethod.ValuationMethodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationMethod.ValuationMethodBuilder prune() {
			if (valuationSource!=null && !valuationSource.prune().hasData()) valuationSource = null;
			if (quotationAmount!=null && !quotationAmount.prune().hasData()) quotationAmount = null;
			if (minimumQuotationAmount!=null && !minimumQuotationAmount.prune().hasData()) minimumQuotationAmount = null;
			if (cashCollateralValuationMethod!=null && !cashCollateralValuationMethod.prune().hasData()) cashCollateralValuationMethod = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationSource()!=null && getValuationSource().hasData()) return true;
			if (getQuotationMethod()!=null) return true;
			if (getValuationMethod()!=null) return true;
			if (getQuotationAmount()!=null && getQuotationAmount().hasData()) return true;
			if (getMinimumQuotationAmount()!=null && getMinimumQuotationAmount().hasData()) return true;
			if (getCashCollateralValuationMethod()!=null && getCashCollateralValuationMethod().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationMethod.ValuationMethodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ValuationMethod.ValuationMethodBuilder o = (ValuationMethod.ValuationMethodBuilder) other;
			
			merger.mergeRosetta(getValuationSource(), o.getValuationSource(), this::setValuationSource);
			merger.mergeRosetta(getQuotationAmount(), o.getQuotationAmount(), this::setQuotationAmount);
			merger.mergeRosetta(getMinimumQuotationAmount(), o.getMinimumQuotationAmount(), this::setMinimumQuotationAmount);
			merger.mergeRosetta(getCashCollateralValuationMethod(), o.getCashCollateralValuationMethod(), this::setCashCollateralValuationMethod);
			
			merger.mergeBasic(getQuotationMethod(), o.getQuotationMethod(), this::setQuotationMethod);
			merger.mergeBasic(getValuationMethod(), o.getValuationMethod(), this::setValuationMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationMethod _that = getType().cast(o);
		
			if (!Objects.equals(valuationSource, _that.getValuationSource())) return false;
			if (!Objects.equals(quotationMethod, _that.getQuotationMethod())) return false;
			if (!Objects.equals(valuationMethod, _that.getValuationMethod())) return false;
			if (!Objects.equals(quotationAmount, _that.getQuotationAmount())) return false;
			if (!Objects.equals(minimumQuotationAmount, _that.getMinimumQuotationAmount())) return false;
			if (!Objects.equals(cashCollateralValuationMethod, _that.getCashCollateralValuationMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationSource != null ? valuationSource.hashCode() : 0);
			_result = 31 * _result + (quotationMethod != null ? quotationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationMethod != null ? valuationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (quotationAmount != null ? quotationAmount.hashCode() : 0);
			_result = 31 * _result + (minimumQuotationAmount != null ? minimumQuotationAmount.hashCode() : 0);
			_result = 31 * _result + (cashCollateralValuationMethod != null ? cashCollateralValuationMethod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationMethodBuilder {" +
				"valuationSource=" + this.valuationSource + ", " +
				"quotationMethod=" + this.quotationMethod + ", " +
				"valuationMethod=" + this.valuationMethod + ", " +
				"quotationAmount=" + this.quotationAmount + ", " +
				"minimumQuotationAmount=" + this.minimumQuotationAmount + ", " +
				"cashCollateralValuationMethod=" + this.cashCollateralValuationMethod +
			'}';
		}
	}
}
