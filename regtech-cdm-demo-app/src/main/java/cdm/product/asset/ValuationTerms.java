package cdm.product.asset;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.asset.FPVFinalPriceElectionFallbackEnum;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.ValuationTerms.ValuationTermsBuilder;
import cdm.product.asset.ValuationTerms.ValuationTermsBuilderImpl;
import cdm.product.asset.ValuationTerms.ValuationTermsImpl;
import cdm.product.asset.meta.ValuationTermsMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="ValuationTerms", builder=ValuationTerms.ValuationTermsBuilderImpl.class, version="${project.version}")
public interface ValuationTerms extends RosettaModelObject {

	ValuationTermsMeta metaData = new ValuationTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The official settlement price as announced by the related exchange is applicable, in accordance with the ISDA 2002 definitions.
	 */
	Boolean getFuturesPriceValuation();
	/**
	 * The official settlement price as announced by the related exchange is applicable, in accordance with the ISDA 2002 definitions
	 */
	Boolean getOptionsPriceValuation();
	/**
	 * The number of valuation dates between valuation start date and valuation end date.
	 */
	Integer getNumberOfValuationDates();
	/**
	 * Specifies the dividend valuation dates of the swap.
	 */
	AdjustableRelativeOrPeriodicDates getDividendValuationDates();
	/**
	 * Specifies the fallback provisions for Hedging Party in the determination of the Final Price.
	 */
	FPVFinalPriceElectionFallbackEnum getFPVFinalPriceElectionFallback();
	/**
	 * For an index option transaction, a flag to indicate whether a relevant Multiple Exchange Index Annex is applicable to the transaction. This annex defines additional provisions which are applicable where an index is comprised of component securities that are traded on multiple exchanges.
	 */
	Boolean getMultipleExchangeIndexAnnexFallback();
	/**
	 * For an index option transaction, a flag to indicate whether a relevant Component Security Index Annex is applicable to the transaction.
	 */
	Boolean getComponentSecurityIndexAnnexFallback();

	/*********************** Build Methods  ***********************/
	ValuationTerms build();
	
	ValuationTerms.ValuationTermsBuilder toBuilder();
	
	static ValuationTerms.ValuationTermsBuilder builder() {
		return new ValuationTerms.ValuationTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ValuationTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ValuationTerms> getType() {
		return ValuationTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("futuresPriceValuation"), Boolean.class, getFuturesPriceValuation(), this);
		processor.processBasic(path.newSubPath("optionsPriceValuation"), Boolean.class, getOptionsPriceValuation(), this);
		processor.processBasic(path.newSubPath("numberOfValuationDates"), Integer.class, getNumberOfValuationDates(), this);
		processRosetta(path.newSubPath("dividendValuationDates"), processor, AdjustableRelativeOrPeriodicDates.class, getDividendValuationDates());
		processor.processBasic(path.newSubPath("fPVFinalPriceElectionFallback"), FPVFinalPriceElectionFallbackEnum.class, getFPVFinalPriceElectionFallback(), this);
		processor.processBasic(path.newSubPath("multipleExchangeIndexAnnexFallback"), Boolean.class, getMultipleExchangeIndexAnnexFallback(), this);
		processor.processBasic(path.newSubPath("componentSecurityIndexAnnexFallback"), Boolean.class, getComponentSecurityIndexAnnexFallback(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ValuationTermsBuilder extends ValuationTerms, RosettaModelObjectBuilder {
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateDividendValuationDates();
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getDividendValuationDates();
		ValuationTerms.ValuationTermsBuilder setFuturesPriceValuation(Boolean futuresPriceValuation);
		ValuationTerms.ValuationTermsBuilder setOptionsPriceValuation(Boolean optionsPriceValuation);
		ValuationTerms.ValuationTermsBuilder setNumberOfValuationDates(Integer numberOfValuationDates);
		ValuationTerms.ValuationTermsBuilder setDividendValuationDates(AdjustableRelativeOrPeriodicDates dividendValuationDates);
		ValuationTerms.ValuationTermsBuilder setFPVFinalPriceElectionFallback(FPVFinalPriceElectionFallbackEnum fPVFinalPriceElectionFallback);
		ValuationTerms.ValuationTermsBuilder setMultipleExchangeIndexAnnexFallback(Boolean multipleExchangeIndexAnnexFallback);
		ValuationTerms.ValuationTermsBuilder setComponentSecurityIndexAnnexFallback(Boolean componentSecurityIndexAnnexFallback);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("futuresPriceValuation"), Boolean.class, getFuturesPriceValuation(), this);
			processor.processBasic(path.newSubPath("optionsPriceValuation"), Boolean.class, getOptionsPriceValuation(), this);
			processor.processBasic(path.newSubPath("numberOfValuationDates"), Integer.class, getNumberOfValuationDates(), this);
			processRosetta(path.newSubPath("dividendValuationDates"), processor, AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder.class, getDividendValuationDates());
			processor.processBasic(path.newSubPath("fPVFinalPriceElectionFallback"), FPVFinalPriceElectionFallbackEnum.class, getFPVFinalPriceElectionFallback(), this);
			processor.processBasic(path.newSubPath("multipleExchangeIndexAnnexFallback"), Boolean.class, getMultipleExchangeIndexAnnexFallback(), this);
			processor.processBasic(path.newSubPath("componentSecurityIndexAnnexFallback"), Boolean.class, getComponentSecurityIndexAnnexFallback(), this);
		}
		

		ValuationTerms.ValuationTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ValuationTerms  ***********************/
	class ValuationTermsImpl implements ValuationTerms {
		private final Boolean futuresPriceValuation;
		private final Boolean optionsPriceValuation;
		private final Integer numberOfValuationDates;
		private final AdjustableRelativeOrPeriodicDates dividendValuationDates;
		private final FPVFinalPriceElectionFallbackEnum fPVFinalPriceElectionFallback;
		private final Boolean multipleExchangeIndexAnnexFallback;
		private final Boolean componentSecurityIndexAnnexFallback;
		
		protected ValuationTermsImpl(ValuationTerms.ValuationTermsBuilder builder) {
			this.futuresPriceValuation = builder.getFuturesPriceValuation();
			this.optionsPriceValuation = builder.getOptionsPriceValuation();
			this.numberOfValuationDates = builder.getNumberOfValuationDates();
			this.dividendValuationDates = ofNullable(builder.getDividendValuationDates()).map(f->f.build()).orElse(null);
			this.fPVFinalPriceElectionFallback = builder.getFPVFinalPriceElectionFallback();
			this.multipleExchangeIndexAnnexFallback = builder.getMultipleExchangeIndexAnnexFallback();
			this.componentSecurityIndexAnnexFallback = builder.getComponentSecurityIndexAnnexFallback();
		}
		
		@Override
		@RosettaAttribute("futuresPriceValuation")
		public Boolean getFuturesPriceValuation() {
			return futuresPriceValuation;
		}
		
		@Override
		@RosettaAttribute("optionsPriceValuation")
		public Boolean getOptionsPriceValuation() {
			return optionsPriceValuation;
		}
		
		@Override
		@RosettaAttribute("numberOfValuationDates")
		public Integer getNumberOfValuationDates() {
			return numberOfValuationDates;
		}
		
		@Override
		@RosettaAttribute("dividendValuationDates")
		public AdjustableRelativeOrPeriodicDates getDividendValuationDates() {
			return dividendValuationDates;
		}
		
		@Override
		@RosettaAttribute("fPVFinalPriceElectionFallback")
		public FPVFinalPriceElectionFallbackEnum getFPVFinalPriceElectionFallback() {
			return fPVFinalPriceElectionFallback;
		}
		
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public Boolean getMultipleExchangeIndexAnnexFallback() {
			return multipleExchangeIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public Boolean getComponentSecurityIndexAnnexFallback() {
			return componentSecurityIndexAnnexFallback;
		}
		
		@Override
		public ValuationTerms build() {
			return this;
		}
		
		@Override
		public ValuationTerms.ValuationTermsBuilder toBuilder() {
			ValuationTerms.ValuationTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ValuationTerms.ValuationTermsBuilder builder) {
			ofNullable(getFuturesPriceValuation()).ifPresent(builder::setFuturesPriceValuation);
			ofNullable(getOptionsPriceValuation()).ifPresent(builder::setOptionsPriceValuation);
			ofNullable(getNumberOfValuationDates()).ifPresent(builder::setNumberOfValuationDates);
			ofNullable(getDividendValuationDates()).ifPresent(builder::setDividendValuationDates);
			ofNullable(getFPVFinalPriceElectionFallback()).ifPresent(builder::setFPVFinalPriceElectionFallback);
			ofNullable(getMultipleExchangeIndexAnnexFallback()).ifPresent(builder::setMultipleExchangeIndexAnnexFallback);
			ofNullable(getComponentSecurityIndexAnnexFallback()).ifPresent(builder::setComponentSecurityIndexAnnexFallback);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationTerms _that = getType().cast(o);
		
			if (!Objects.equals(futuresPriceValuation, _that.getFuturesPriceValuation())) return false;
			if (!Objects.equals(optionsPriceValuation, _that.getOptionsPriceValuation())) return false;
			if (!Objects.equals(numberOfValuationDates, _that.getNumberOfValuationDates())) return false;
			if (!Objects.equals(dividendValuationDates, _that.getDividendValuationDates())) return false;
			if (!Objects.equals(fPVFinalPriceElectionFallback, _that.getFPVFinalPriceElectionFallback())) return false;
			if (!Objects.equals(multipleExchangeIndexAnnexFallback, _that.getMultipleExchangeIndexAnnexFallback())) return false;
			if (!Objects.equals(componentSecurityIndexAnnexFallback, _that.getComponentSecurityIndexAnnexFallback())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (futuresPriceValuation != null ? futuresPriceValuation.hashCode() : 0);
			_result = 31 * _result + (optionsPriceValuation != null ? optionsPriceValuation.hashCode() : 0);
			_result = 31 * _result + (numberOfValuationDates != null ? numberOfValuationDates.hashCode() : 0);
			_result = 31 * _result + (dividendValuationDates != null ? dividendValuationDates.hashCode() : 0);
			_result = 31 * _result + (fPVFinalPriceElectionFallback != null ? fPVFinalPriceElectionFallback.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (multipleExchangeIndexAnnexFallback != null ? multipleExchangeIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (componentSecurityIndexAnnexFallback != null ? componentSecurityIndexAnnexFallback.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationTerms {" +
				"futuresPriceValuation=" + this.futuresPriceValuation + ", " +
				"optionsPriceValuation=" + this.optionsPriceValuation + ", " +
				"numberOfValuationDates=" + this.numberOfValuationDates + ", " +
				"dividendValuationDates=" + this.dividendValuationDates + ", " +
				"fPVFinalPriceElectionFallback=" + this.fPVFinalPriceElectionFallback + ", " +
				"multipleExchangeIndexAnnexFallback=" + this.multipleExchangeIndexAnnexFallback + ", " +
				"componentSecurityIndexAnnexFallback=" + this.componentSecurityIndexAnnexFallback +
			'}';
		}
	}

	/*********************** Builder Implementation of ValuationTerms  ***********************/
	class ValuationTermsBuilderImpl implements ValuationTerms.ValuationTermsBuilder {
	
		protected Boolean futuresPriceValuation;
		protected Boolean optionsPriceValuation;
		protected Integer numberOfValuationDates;
		protected AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder dividendValuationDates;
		protected FPVFinalPriceElectionFallbackEnum fPVFinalPriceElectionFallback;
		protected Boolean multipleExchangeIndexAnnexFallback;
		protected Boolean componentSecurityIndexAnnexFallback;
	
		public ValuationTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("futuresPriceValuation")
		public Boolean getFuturesPriceValuation() {
			return futuresPriceValuation;
		}
		
		@Override
		@RosettaAttribute("optionsPriceValuation")
		public Boolean getOptionsPriceValuation() {
			return optionsPriceValuation;
		}
		
		@Override
		@RosettaAttribute("numberOfValuationDates")
		public Integer getNumberOfValuationDates() {
			return numberOfValuationDates;
		}
		
		@Override
		@RosettaAttribute("dividendValuationDates")
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getDividendValuationDates() {
			return dividendValuationDates;
		}
		
		@Override
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateDividendValuationDates() {
			AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder result;
			if (dividendValuationDates!=null) {
				result = dividendValuationDates;
			}
			else {
				result = dividendValuationDates = AdjustableRelativeOrPeriodicDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fPVFinalPriceElectionFallback")
		public FPVFinalPriceElectionFallbackEnum getFPVFinalPriceElectionFallback() {
			return fPVFinalPriceElectionFallback;
		}
		
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public Boolean getMultipleExchangeIndexAnnexFallback() {
			return multipleExchangeIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public Boolean getComponentSecurityIndexAnnexFallback() {
			return componentSecurityIndexAnnexFallback;
		}
		
	
		@Override
		@RosettaAttribute("futuresPriceValuation")
		public ValuationTerms.ValuationTermsBuilder setFuturesPriceValuation(Boolean futuresPriceValuation) {
			this.futuresPriceValuation = futuresPriceValuation==null?null:futuresPriceValuation;
			return this;
		}
		@Override
		@RosettaAttribute("optionsPriceValuation")
		public ValuationTerms.ValuationTermsBuilder setOptionsPriceValuation(Boolean optionsPriceValuation) {
			this.optionsPriceValuation = optionsPriceValuation==null?null:optionsPriceValuation;
			return this;
		}
		@Override
		@RosettaAttribute("numberOfValuationDates")
		public ValuationTerms.ValuationTermsBuilder setNumberOfValuationDates(Integer numberOfValuationDates) {
			this.numberOfValuationDates = numberOfValuationDates==null?null:numberOfValuationDates;
			return this;
		}
		@Override
		@RosettaAttribute("dividendValuationDates")
		public ValuationTerms.ValuationTermsBuilder setDividendValuationDates(AdjustableRelativeOrPeriodicDates dividendValuationDates) {
			this.dividendValuationDates = dividendValuationDates==null?null:dividendValuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fPVFinalPriceElectionFallback")
		public ValuationTerms.ValuationTermsBuilder setFPVFinalPriceElectionFallback(FPVFinalPriceElectionFallbackEnum fPVFinalPriceElectionFallback) {
			this.fPVFinalPriceElectionFallback = fPVFinalPriceElectionFallback==null?null:fPVFinalPriceElectionFallback;
			return this;
		}
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public ValuationTerms.ValuationTermsBuilder setMultipleExchangeIndexAnnexFallback(Boolean multipleExchangeIndexAnnexFallback) {
			this.multipleExchangeIndexAnnexFallback = multipleExchangeIndexAnnexFallback==null?null:multipleExchangeIndexAnnexFallback;
			return this;
		}
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public ValuationTerms.ValuationTermsBuilder setComponentSecurityIndexAnnexFallback(Boolean componentSecurityIndexAnnexFallback) {
			this.componentSecurityIndexAnnexFallback = componentSecurityIndexAnnexFallback==null?null:componentSecurityIndexAnnexFallback;
			return this;
		}
		
		@Override
		public ValuationTerms build() {
			return new ValuationTerms.ValuationTermsImpl(this);
		}
		
		@Override
		public ValuationTerms.ValuationTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationTerms.ValuationTermsBuilder prune() {
			if (dividendValuationDates!=null && !dividendValuationDates.prune().hasData()) dividendValuationDates = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFuturesPriceValuation()!=null) return true;
			if (getOptionsPriceValuation()!=null) return true;
			if (getNumberOfValuationDates()!=null) return true;
			if (getDividendValuationDates()!=null && getDividendValuationDates().hasData()) return true;
			if (getFPVFinalPriceElectionFallback()!=null) return true;
			if (getMultipleExchangeIndexAnnexFallback()!=null) return true;
			if (getComponentSecurityIndexAnnexFallback()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationTerms.ValuationTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ValuationTerms.ValuationTermsBuilder o = (ValuationTerms.ValuationTermsBuilder) other;
			
			merger.mergeRosetta(getDividendValuationDates(), o.getDividendValuationDates(), this::setDividendValuationDates);
			
			merger.mergeBasic(getFuturesPriceValuation(), o.getFuturesPriceValuation(), this::setFuturesPriceValuation);
			merger.mergeBasic(getOptionsPriceValuation(), o.getOptionsPriceValuation(), this::setOptionsPriceValuation);
			merger.mergeBasic(getNumberOfValuationDates(), o.getNumberOfValuationDates(), this::setNumberOfValuationDates);
			merger.mergeBasic(getFPVFinalPriceElectionFallback(), o.getFPVFinalPriceElectionFallback(), this::setFPVFinalPriceElectionFallback);
			merger.mergeBasic(getMultipleExchangeIndexAnnexFallback(), o.getMultipleExchangeIndexAnnexFallback(), this::setMultipleExchangeIndexAnnexFallback);
			merger.mergeBasic(getComponentSecurityIndexAnnexFallback(), o.getComponentSecurityIndexAnnexFallback(), this::setComponentSecurityIndexAnnexFallback);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationTerms _that = getType().cast(o);
		
			if (!Objects.equals(futuresPriceValuation, _that.getFuturesPriceValuation())) return false;
			if (!Objects.equals(optionsPriceValuation, _that.getOptionsPriceValuation())) return false;
			if (!Objects.equals(numberOfValuationDates, _that.getNumberOfValuationDates())) return false;
			if (!Objects.equals(dividendValuationDates, _that.getDividendValuationDates())) return false;
			if (!Objects.equals(fPVFinalPriceElectionFallback, _that.getFPVFinalPriceElectionFallback())) return false;
			if (!Objects.equals(multipleExchangeIndexAnnexFallback, _that.getMultipleExchangeIndexAnnexFallback())) return false;
			if (!Objects.equals(componentSecurityIndexAnnexFallback, _that.getComponentSecurityIndexAnnexFallback())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (futuresPriceValuation != null ? futuresPriceValuation.hashCode() : 0);
			_result = 31 * _result + (optionsPriceValuation != null ? optionsPriceValuation.hashCode() : 0);
			_result = 31 * _result + (numberOfValuationDates != null ? numberOfValuationDates.hashCode() : 0);
			_result = 31 * _result + (dividendValuationDates != null ? dividendValuationDates.hashCode() : 0);
			_result = 31 * _result + (fPVFinalPriceElectionFallback != null ? fPVFinalPriceElectionFallback.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (multipleExchangeIndexAnnexFallback != null ? multipleExchangeIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (componentSecurityIndexAnnexFallback != null ? componentSecurityIndexAnnexFallback.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationTermsBuilder {" +
				"futuresPriceValuation=" + this.futuresPriceValuation + ", " +
				"optionsPriceValuation=" + this.optionsPriceValuation + ", " +
				"numberOfValuationDates=" + this.numberOfValuationDates + ", " +
				"dividendValuationDates=" + this.dividendValuationDates + ", " +
				"fPVFinalPriceElectionFallback=" + this.fPVFinalPriceElectionFallback + ", " +
				"multipleExchangeIndexAnnexFallback=" + this.multipleExchangeIndexAnnexFallback + ", " +
				"componentSecurityIndexAnnexFallback=" + this.componentSecurityIndexAnnexFallback +
			'}';
		}
	}
}
