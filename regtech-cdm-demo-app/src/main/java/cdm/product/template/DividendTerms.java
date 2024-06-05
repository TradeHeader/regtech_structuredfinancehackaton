package cdm.product.template;

import cdm.observable.asset.Money;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPayoutRatio;
import cdm.product.template.DividendTerms;
import cdm.product.template.DividendTerms.DividendTermsBuilder;
import cdm.product.template.DividendTerms.DividendTermsBuilderImpl;
import cdm.product.template.DividendTerms.DividendTermsImpl;
import cdm.product.template.meta.DividendTermsMeta;
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
 * Information related to dividends and payments.
 * @version ${project.version}
 */
@RosettaDataType(value="DividendTerms", builder=DividendTerms.DividendTermsBuilderImpl.class, version="${project.version}")
public interface DividendTerms extends RosettaModelObject {

	DividendTermsMeta metaData = new DividendTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the proportion of the value of the dividend on the borrowed shares that the borrower is legally obligated to return to the lender.
	 */
	DividendPayoutRatio getManufacturedIncomeRequirement();
	/**
	 * Defines the date on which the receiver of the equity return is entitled to the dividend.
	 */
	DividendEntitlementEnum getDividendEntitlement();
	/**
	 * daily fee increments accrue until a threshold is crossed, at which point payment becomes due)
	 */
	Money getMinimumBillingAmount();

	/*********************** Build Methods  ***********************/
	DividendTerms build();
	
	DividendTerms.DividendTermsBuilder toBuilder();
	
	static DividendTerms.DividendTermsBuilder builder() {
		return new DividendTerms.DividendTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendTerms> getType() {
		return DividendTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("manufacturedIncomeRequirement"), processor, DividendPayoutRatio.class, getManufacturedIncomeRequirement());
		processor.processBasic(path.newSubPath("dividendEntitlement"), DividendEntitlementEnum.class, getDividendEntitlement(), this);
		processRosetta(path.newSubPath("minimumBillingAmount"), processor, Money.class, getMinimumBillingAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendTermsBuilder extends DividendTerms, RosettaModelObjectBuilder {
		DividendPayoutRatio.DividendPayoutRatioBuilder getOrCreateManufacturedIncomeRequirement();
		DividendPayoutRatio.DividendPayoutRatioBuilder getManufacturedIncomeRequirement();
		Money.MoneyBuilder getOrCreateMinimumBillingAmount();
		Money.MoneyBuilder getMinimumBillingAmount();
		DividendTerms.DividendTermsBuilder setManufacturedIncomeRequirement(DividendPayoutRatio manufacturedIncomeRequirement);
		DividendTerms.DividendTermsBuilder setDividendEntitlement(DividendEntitlementEnum dividendEntitlement);
		DividendTerms.DividendTermsBuilder setMinimumBillingAmount(Money minimumBillingAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("manufacturedIncomeRequirement"), processor, DividendPayoutRatio.DividendPayoutRatioBuilder.class, getManufacturedIncomeRequirement());
			processor.processBasic(path.newSubPath("dividendEntitlement"), DividendEntitlementEnum.class, getDividendEntitlement(), this);
			processRosetta(path.newSubPath("minimumBillingAmount"), processor, Money.MoneyBuilder.class, getMinimumBillingAmount());
		}
		

		DividendTerms.DividendTermsBuilder prune();
	}

	/*********************** Immutable Implementation of DividendTerms  ***********************/
	class DividendTermsImpl implements DividendTerms {
		private final DividendPayoutRatio manufacturedIncomeRequirement;
		private final DividendEntitlementEnum dividendEntitlement;
		private final Money minimumBillingAmount;
		
		protected DividendTermsImpl(DividendTerms.DividendTermsBuilder builder) {
			this.manufacturedIncomeRequirement = ofNullable(builder.getManufacturedIncomeRequirement()).map(f->f.build()).orElse(null);
			this.dividendEntitlement = builder.getDividendEntitlement();
			this.minimumBillingAmount = ofNullable(builder.getMinimumBillingAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("manufacturedIncomeRequirement")
		public DividendPayoutRatio getManufacturedIncomeRequirement() {
			return manufacturedIncomeRequirement;
		}
		
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendEntitlementEnum getDividendEntitlement() {
			return dividendEntitlement;
		}
		
		@Override
		@RosettaAttribute("minimumBillingAmount")
		public Money getMinimumBillingAmount() {
			return minimumBillingAmount;
		}
		
		@Override
		public DividendTerms build() {
			return this;
		}
		
		@Override
		public DividendTerms.DividendTermsBuilder toBuilder() {
			DividendTerms.DividendTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendTerms.DividendTermsBuilder builder) {
			ofNullable(getManufacturedIncomeRequirement()).ifPresent(builder::setManufacturedIncomeRequirement);
			ofNullable(getDividendEntitlement()).ifPresent(builder::setDividendEntitlement);
			ofNullable(getMinimumBillingAmount()).ifPresent(builder::setMinimumBillingAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendTerms _that = getType().cast(o);
		
			if (!Objects.equals(manufacturedIncomeRequirement, _that.getManufacturedIncomeRequirement())) return false;
			if (!Objects.equals(dividendEntitlement, _that.getDividendEntitlement())) return false;
			if (!Objects.equals(minimumBillingAmount, _that.getMinimumBillingAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (manufacturedIncomeRequirement != null ? manufacturedIncomeRequirement.hashCode() : 0);
			_result = 31 * _result + (dividendEntitlement != null ? dividendEntitlement.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (minimumBillingAmount != null ? minimumBillingAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendTerms {" +
				"manufacturedIncomeRequirement=" + this.manufacturedIncomeRequirement + ", " +
				"dividendEntitlement=" + this.dividendEntitlement + ", " +
				"minimumBillingAmount=" + this.minimumBillingAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendTerms  ***********************/
	class DividendTermsBuilderImpl implements DividendTerms.DividendTermsBuilder {
	
		protected DividendPayoutRatio.DividendPayoutRatioBuilder manufacturedIncomeRequirement;
		protected DividendEntitlementEnum dividendEntitlement;
		protected Money.MoneyBuilder minimumBillingAmount;
	
		public DividendTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("manufacturedIncomeRequirement")
		public DividendPayoutRatio.DividendPayoutRatioBuilder getManufacturedIncomeRequirement() {
			return manufacturedIncomeRequirement;
		}
		
		@Override
		public DividendPayoutRatio.DividendPayoutRatioBuilder getOrCreateManufacturedIncomeRequirement() {
			DividendPayoutRatio.DividendPayoutRatioBuilder result;
			if (manufacturedIncomeRequirement!=null) {
				result = manufacturedIncomeRequirement;
			}
			else {
				result = manufacturedIncomeRequirement = DividendPayoutRatio.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendEntitlementEnum getDividendEntitlement() {
			return dividendEntitlement;
		}
		
		@Override
		@RosettaAttribute("minimumBillingAmount")
		public Money.MoneyBuilder getMinimumBillingAmount() {
			return minimumBillingAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMinimumBillingAmount() {
			Money.MoneyBuilder result;
			if (minimumBillingAmount!=null) {
				result = minimumBillingAmount;
			}
			else {
				result = minimumBillingAmount = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("manufacturedIncomeRequirement")
		public DividendTerms.DividendTermsBuilder setManufacturedIncomeRequirement(DividendPayoutRatio manufacturedIncomeRequirement) {
			this.manufacturedIncomeRequirement = manufacturedIncomeRequirement==null?null:manufacturedIncomeRequirement.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendTerms.DividendTermsBuilder setDividendEntitlement(DividendEntitlementEnum dividendEntitlement) {
			this.dividendEntitlement = dividendEntitlement==null?null:dividendEntitlement;
			return this;
		}
		@Override
		@RosettaAttribute("minimumBillingAmount")
		public DividendTerms.DividendTermsBuilder setMinimumBillingAmount(Money minimumBillingAmount) {
			this.minimumBillingAmount = minimumBillingAmount==null?null:minimumBillingAmount.toBuilder();
			return this;
		}
		
		@Override
		public DividendTerms build() {
			return new DividendTerms.DividendTermsImpl(this);
		}
		
		@Override
		public DividendTerms.DividendTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendTerms.DividendTermsBuilder prune() {
			if (manufacturedIncomeRequirement!=null && !manufacturedIncomeRequirement.prune().hasData()) manufacturedIncomeRequirement = null;
			if (minimumBillingAmount!=null && !minimumBillingAmount.prune().hasData()) minimumBillingAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getManufacturedIncomeRequirement()!=null && getManufacturedIncomeRequirement().hasData()) return true;
			if (getDividendEntitlement()!=null) return true;
			if (getMinimumBillingAmount()!=null && getMinimumBillingAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendTerms.DividendTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendTerms.DividendTermsBuilder o = (DividendTerms.DividendTermsBuilder) other;
			
			merger.mergeRosetta(getManufacturedIncomeRequirement(), o.getManufacturedIncomeRequirement(), this::setManufacturedIncomeRequirement);
			merger.mergeRosetta(getMinimumBillingAmount(), o.getMinimumBillingAmount(), this::setMinimumBillingAmount);
			
			merger.mergeBasic(getDividendEntitlement(), o.getDividendEntitlement(), this::setDividendEntitlement);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendTerms _that = getType().cast(o);
		
			if (!Objects.equals(manufacturedIncomeRequirement, _that.getManufacturedIncomeRequirement())) return false;
			if (!Objects.equals(dividendEntitlement, _that.getDividendEntitlement())) return false;
			if (!Objects.equals(minimumBillingAmount, _that.getMinimumBillingAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (manufacturedIncomeRequirement != null ? manufacturedIncomeRequirement.hashCode() : 0);
			_result = 31 * _result + (dividendEntitlement != null ? dividendEntitlement.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (minimumBillingAmount != null ? minimumBillingAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendTermsBuilder {" +
				"manufacturedIncomeRequirement=" + this.manufacturedIncomeRequirement + ", " +
				"dividendEntitlement=" + this.dividendEntitlement + ", " +
				"minimumBillingAmount=" + this.minimumBillingAmount +
			'}';
		}
	}
}
