package cdm.product.asset;

import cdm.observable.asset.DividendApplicability;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ReturnTermsBase;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilder;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilderImpl;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseImpl;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.meta.ReturnTermsBaseMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Contains all common elements in variance, volatility and correlation return Terms.
 * @version ${project.version}
 */
@RosettaDataType(value="ReturnTermsBase", builder=ReturnTermsBase.ReturnTermsBaseBuilderImpl.class, version="${project.version}")
public interface ReturnTermsBase extends RosettaModelObject {

	ReturnTermsBaseMeta metaData = new ReturnTermsBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Contains all non-date valuation information.
	 */
	ValuationTerms getValuationTerms();
	/**
	 * This specifies the numerator of an annualization factor. Frequently this number is equal to the number of observations of prices in a year e.g. 252.
	 */
	Integer getAnnualizationFactor();
	/**
	 * The parameters which define whether dividends are applicable
	 */
	DividendApplicability getDividendApplicability();
	/**
	 * Contains Equity underlier provisions regarding jurisdiction and fallbacks.
	 */
	EquityUnderlierProvisions getEquityUnderlierProvisions();
	/**
	 * Indicates whether the price of shares is adjusted for dividends or not.
	 */
	Boolean getSharePriceDividendAdjustment();
	/**
	 * Expected number of trading days.
	 */
	Integer getExpectedN();
	/**
	 * Contract will strike off this initial level. Providing just the initialLevel without initialLevelSource, infers that this is AgreedInitialPrice - a specified Initial Index Level.
	 */
	BigDecimal getInitialLevel();
	/**
	 * In this context, this is AgreedInitialPrice - a specified Initial Index Level.
	 */
	DeterminationMethodEnum getInitialLevelSource();
	/**
	 * Specifies whether Mean Adjustment is applicable or not in the calculation of the Realized Volatility, Variance or Correlation
	 */
	Boolean getMeanAdjustment();
	/**
	 * Performance calculation, in accordance with Part 1 Section 12 of the 2018 ISDA CDM Equity Confirmation for Security Equity Swap, Para 75. &#39;Equity Performance&#39;. Cumulative performance is used as a notional multiplier factor on both legs of an Equity Swap.
	 */
	String getPerformance();

	/*********************** Build Methods  ***********************/
	ReturnTermsBase build();
	
	ReturnTermsBase.ReturnTermsBaseBuilder toBuilder();
	
	static ReturnTermsBase.ReturnTermsBaseBuilder builder() {
		return new ReturnTermsBase.ReturnTermsBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReturnTermsBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReturnTermsBase> getType() {
		return ReturnTermsBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationTerms"), processor, ValuationTerms.class, getValuationTerms());
		processor.processBasic(path.newSubPath("annualizationFactor"), Integer.class, getAnnualizationFactor(), this);
		processRosetta(path.newSubPath("dividendApplicability"), processor, DividendApplicability.class, getDividendApplicability());
		processRosetta(path.newSubPath("equityUnderlierProvisions"), processor, EquityUnderlierProvisions.class, getEquityUnderlierProvisions());
		processor.processBasic(path.newSubPath("sharePriceDividendAdjustment"), Boolean.class, getSharePriceDividendAdjustment(), this);
		processor.processBasic(path.newSubPath("expectedN"), Integer.class, getExpectedN(), this);
		processor.processBasic(path.newSubPath("initialLevel"), BigDecimal.class, getInitialLevel(), this);
		processor.processBasic(path.newSubPath("initialLevelSource"), DeterminationMethodEnum.class, getInitialLevelSource(), this);
		processor.processBasic(path.newSubPath("meanAdjustment"), Boolean.class, getMeanAdjustment(), this);
		processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReturnTermsBaseBuilder extends ReturnTermsBase, RosettaModelObjectBuilder {
		ValuationTerms.ValuationTermsBuilder getOrCreateValuationTerms();
		ValuationTerms.ValuationTermsBuilder getValuationTerms();
		DividendApplicability.DividendApplicabilityBuilder getOrCreateDividendApplicability();
		DividendApplicability.DividendApplicabilityBuilder getDividendApplicability();
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder getOrCreateEquityUnderlierProvisions();
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder getEquityUnderlierProvisions();
		ReturnTermsBase.ReturnTermsBaseBuilder setValuationTerms(ValuationTerms valuationTerms);
		ReturnTermsBase.ReturnTermsBaseBuilder setAnnualizationFactor(Integer annualizationFactor);
		ReturnTermsBase.ReturnTermsBaseBuilder setDividendApplicability(DividendApplicability dividendApplicability);
		ReturnTermsBase.ReturnTermsBaseBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions);
		ReturnTermsBase.ReturnTermsBaseBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment);
		ReturnTermsBase.ReturnTermsBaseBuilder setExpectedN(Integer expectedN);
		ReturnTermsBase.ReturnTermsBaseBuilder setInitialLevel(BigDecimal initialLevel);
		ReturnTermsBase.ReturnTermsBaseBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource);
		ReturnTermsBase.ReturnTermsBaseBuilder setMeanAdjustment(Boolean meanAdjustment);
		ReturnTermsBase.ReturnTermsBaseBuilder setPerformance(String performance);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationTerms"), processor, ValuationTerms.ValuationTermsBuilder.class, getValuationTerms());
			processor.processBasic(path.newSubPath("annualizationFactor"), Integer.class, getAnnualizationFactor(), this);
			processRosetta(path.newSubPath("dividendApplicability"), processor, DividendApplicability.DividendApplicabilityBuilder.class, getDividendApplicability());
			processRosetta(path.newSubPath("equityUnderlierProvisions"), processor, EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder.class, getEquityUnderlierProvisions());
			processor.processBasic(path.newSubPath("sharePriceDividendAdjustment"), Boolean.class, getSharePriceDividendAdjustment(), this);
			processor.processBasic(path.newSubPath("expectedN"), Integer.class, getExpectedN(), this);
			processor.processBasic(path.newSubPath("initialLevel"), BigDecimal.class, getInitialLevel(), this);
			processor.processBasic(path.newSubPath("initialLevelSource"), DeterminationMethodEnum.class, getInitialLevelSource(), this);
			processor.processBasic(path.newSubPath("meanAdjustment"), Boolean.class, getMeanAdjustment(), this);
			processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
		}
		

		ReturnTermsBase.ReturnTermsBaseBuilder prune();
	}

	/*********************** Immutable Implementation of ReturnTermsBase  ***********************/
	class ReturnTermsBaseImpl implements ReturnTermsBase {
		private final ValuationTerms valuationTerms;
		private final Integer annualizationFactor;
		private final DividendApplicability dividendApplicability;
		private final EquityUnderlierProvisions equityUnderlierProvisions;
		private final Boolean sharePriceDividendAdjustment;
		private final Integer expectedN;
		private final BigDecimal initialLevel;
		private final DeterminationMethodEnum initialLevelSource;
		private final Boolean meanAdjustment;
		private final String performance;
		
		protected ReturnTermsBaseImpl(ReturnTermsBase.ReturnTermsBaseBuilder builder) {
			this.valuationTerms = ofNullable(builder.getValuationTerms()).map(f->f.build()).orElse(null);
			this.annualizationFactor = builder.getAnnualizationFactor();
			this.dividendApplicability = ofNullable(builder.getDividendApplicability()).map(f->f.build()).orElse(null);
			this.equityUnderlierProvisions = ofNullable(builder.getEquityUnderlierProvisions()).map(f->f.build()).orElse(null);
			this.sharePriceDividendAdjustment = builder.getSharePriceDividendAdjustment();
			this.expectedN = builder.getExpectedN();
			this.initialLevel = builder.getInitialLevel();
			this.initialLevelSource = builder.getInitialLevelSource();
			this.meanAdjustment = builder.getMeanAdjustment();
			this.performance = builder.getPerformance();
		}
		
		@Override
		@RosettaAttribute("valuationTerms")
		public ValuationTerms getValuationTerms() {
			return valuationTerms;
		}
		
		@Override
		@RosettaAttribute("annualizationFactor")
		public Integer getAnnualizationFactor() {
			return annualizationFactor;
		}
		
		@Override
		@RosettaAttribute("dividendApplicability")
		public DividendApplicability getDividendApplicability() {
			return dividendApplicability;
		}
		
		@Override
		@RosettaAttribute("equityUnderlierProvisions")
		public EquityUnderlierProvisions getEquityUnderlierProvisions() {
			return equityUnderlierProvisions;
		}
		
		@Override
		@RosettaAttribute("sharePriceDividendAdjustment")
		public Boolean getSharePriceDividendAdjustment() {
			return sharePriceDividendAdjustment;
		}
		
		@Override
		@RosettaAttribute("expectedN")
		public Integer getExpectedN() {
			return expectedN;
		}
		
		@Override
		@RosettaAttribute("initialLevel")
		public BigDecimal getInitialLevel() {
			return initialLevel;
		}
		
		@Override
		@RosettaAttribute("initialLevelSource")
		public DeterminationMethodEnum getInitialLevelSource() {
			return initialLevelSource;
		}
		
		@Override
		@RosettaAttribute("meanAdjustment")
		public Boolean getMeanAdjustment() {
			return meanAdjustment;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
		@Override
		public ReturnTermsBase build() {
			return this;
		}
		
		@Override
		public ReturnTermsBase.ReturnTermsBaseBuilder toBuilder() {
			ReturnTermsBase.ReturnTermsBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReturnTermsBase.ReturnTermsBaseBuilder builder) {
			ofNullable(getValuationTerms()).ifPresent(builder::setValuationTerms);
			ofNullable(getAnnualizationFactor()).ifPresent(builder::setAnnualizationFactor);
			ofNullable(getDividendApplicability()).ifPresent(builder::setDividendApplicability);
			ofNullable(getEquityUnderlierProvisions()).ifPresent(builder::setEquityUnderlierProvisions);
			ofNullable(getSharePriceDividendAdjustment()).ifPresent(builder::setSharePriceDividendAdjustment);
			ofNullable(getExpectedN()).ifPresent(builder::setExpectedN);
			ofNullable(getInitialLevel()).ifPresent(builder::setInitialLevel);
			ofNullable(getInitialLevelSource()).ifPresent(builder::setInitialLevelSource);
			ofNullable(getMeanAdjustment()).ifPresent(builder::setMeanAdjustment);
			ofNullable(getPerformance()).ifPresent(builder::setPerformance);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnTermsBase _that = getType().cast(o);
		
			if (!Objects.equals(valuationTerms, _that.getValuationTerms())) return false;
			if (!Objects.equals(annualizationFactor, _that.getAnnualizationFactor())) return false;
			if (!Objects.equals(dividendApplicability, _that.getDividendApplicability())) return false;
			if (!Objects.equals(equityUnderlierProvisions, _that.getEquityUnderlierProvisions())) return false;
			if (!Objects.equals(sharePriceDividendAdjustment, _that.getSharePriceDividendAdjustment())) return false;
			if (!Objects.equals(expectedN, _that.getExpectedN())) return false;
			if (!Objects.equals(initialLevel, _that.getInitialLevel())) return false;
			if (!Objects.equals(initialLevelSource, _that.getInitialLevelSource())) return false;
			if (!Objects.equals(meanAdjustment, _that.getMeanAdjustment())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationTerms != null ? valuationTerms.hashCode() : 0);
			_result = 31 * _result + (annualizationFactor != null ? annualizationFactor.hashCode() : 0);
			_result = 31 * _result + (dividendApplicability != null ? dividendApplicability.hashCode() : 0);
			_result = 31 * _result + (equityUnderlierProvisions != null ? equityUnderlierProvisions.hashCode() : 0);
			_result = 31 * _result + (sharePriceDividendAdjustment != null ? sharePriceDividendAdjustment.hashCode() : 0);
			_result = 31 * _result + (expectedN != null ? expectedN.hashCode() : 0);
			_result = 31 * _result + (initialLevel != null ? initialLevel.hashCode() : 0);
			_result = 31 * _result + (initialLevelSource != null ? initialLevelSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meanAdjustment != null ? meanAdjustment.hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnTermsBase {" +
				"valuationTerms=" + this.valuationTerms + ", " +
				"annualizationFactor=" + this.annualizationFactor + ", " +
				"dividendApplicability=" + this.dividendApplicability + ", " +
				"equityUnderlierProvisions=" + this.equityUnderlierProvisions + ", " +
				"sharePriceDividendAdjustment=" + this.sharePriceDividendAdjustment + ", " +
				"expectedN=" + this.expectedN + ", " +
				"initialLevel=" + this.initialLevel + ", " +
				"initialLevelSource=" + this.initialLevelSource + ", " +
				"meanAdjustment=" + this.meanAdjustment + ", " +
				"performance=" + this.performance +
			'}';
		}
	}

	/*********************** Builder Implementation of ReturnTermsBase  ***********************/
	class ReturnTermsBaseBuilderImpl implements ReturnTermsBase.ReturnTermsBaseBuilder {
	
		protected ValuationTerms.ValuationTermsBuilder valuationTerms;
		protected Integer annualizationFactor;
		protected DividendApplicability.DividendApplicabilityBuilder dividendApplicability;
		protected EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder equityUnderlierProvisions;
		protected Boolean sharePriceDividendAdjustment;
		protected Integer expectedN;
		protected BigDecimal initialLevel;
		protected DeterminationMethodEnum initialLevelSource;
		protected Boolean meanAdjustment;
		protected String performance;
	
		public ReturnTermsBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationTerms")
		public ValuationTerms.ValuationTermsBuilder getValuationTerms() {
			return valuationTerms;
		}
		
		@Override
		public ValuationTerms.ValuationTermsBuilder getOrCreateValuationTerms() {
			ValuationTerms.ValuationTermsBuilder result;
			if (valuationTerms!=null) {
				result = valuationTerms;
			}
			else {
				result = valuationTerms = ValuationTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("annualizationFactor")
		public Integer getAnnualizationFactor() {
			return annualizationFactor;
		}
		
		@Override
		@RosettaAttribute("dividendApplicability")
		public DividendApplicability.DividendApplicabilityBuilder getDividendApplicability() {
			return dividendApplicability;
		}
		
		@Override
		public DividendApplicability.DividendApplicabilityBuilder getOrCreateDividendApplicability() {
			DividendApplicability.DividendApplicabilityBuilder result;
			if (dividendApplicability!=null) {
				result = dividendApplicability;
			}
			else {
				result = dividendApplicability = DividendApplicability.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("equityUnderlierProvisions")
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder getEquityUnderlierProvisions() {
			return equityUnderlierProvisions;
		}
		
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder getOrCreateEquityUnderlierProvisions() {
			EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder result;
			if (equityUnderlierProvisions!=null) {
				result = equityUnderlierProvisions;
			}
			else {
				result = equityUnderlierProvisions = EquityUnderlierProvisions.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sharePriceDividendAdjustment")
		public Boolean getSharePriceDividendAdjustment() {
			return sharePriceDividendAdjustment;
		}
		
		@Override
		@RosettaAttribute("expectedN")
		public Integer getExpectedN() {
			return expectedN;
		}
		
		@Override
		@RosettaAttribute("initialLevel")
		public BigDecimal getInitialLevel() {
			return initialLevel;
		}
		
		@Override
		@RosettaAttribute("initialLevelSource")
		public DeterminationMethodEnum getInitialLevelSource() {
			return initialLevelSource;
		}
		
		@Override
		@RosettaAttribute("meanAdjustment")
		public Boolean getMeanAdjustment() {
			return meanAdjustment;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
	
		@Override
		@RosettaAttribute("valuationTerms")
		public ReturnTermsBase.ReturnTermsBaseBuilder setValuationTerms(ValuationTerms valuationTerms) {
			this.valuationTerms = valuationTerms==null?null:valuationTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("annualizationFactor")
		public ReturnTermsBase.ReturnTermsBaseBuilder setAnnualizationFactor(Integer annualizationFactor) {
			this.annualizationFactor = annualizationFactor==null?null:annualizationFactor;
			return this;
		}
		@Override
		@RosettaAttribute("dividendApplicability")
		public ReturnTermsBase.ReturnTermsBaseBuilder setDividendApplicability(DividendApplicability dividendApplicability) {
			this.dividendApplicability = dividendApplicability==null?null:dividendApplicability.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityUnderlierProvisions")
		public ReturnTermsBase.ReturnTermsBaseBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions) {
			this.equityUnderlierProvisions = equityUnderlierProvisions==null?null:equityUnderlierProvisions.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("sharePriceDividendAdjustment")
		public ReturnTermsBase.ReturnTermsBaseBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment) {
			this.sharePriceDividendAdjustment = sharePriceDividendAdjustment==null?null:sharePriceDividendAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("expectedN")
		public ReturnTermsBase.ReturnTermsBaseBuilder setExpectedN(Integer expectedN) {
			this.expectedN = expectedN==null?null:expectedN;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevel")
		public ReturnTermsBase.ReturnTermsBaseBuilder setInitialLevel(BigDecimal initialLevel) {
			this.initialLevel = initialLevel==null?null:initialLevel;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevelSource")
		public ReturnTermsBase.ReturnTermsBaseBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource) {
			this.initialLevelSource = initialLevelSource==null?null:initialLevelSource;
			return this;
		}
		@Override
		@RosettaAttribute("meanAdjustment")
		public ReturnTermsBase.ReturnTermsBaseBuilder setMeanAdjustment(Boolean meanAdjustment) {
			this.meanAdjustment = meanAdjustment==null?null:meanAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("performance")
		public ReturnTermsBase.ReturnTermsBaseBuilder setPerformance(String performance) {
			this.performance = performance==null?null:performance;
			return this;
		}
		
		@Override
		public ReturnTermsBase build() {
			return new ReturnTermsBase.ReturnTermsBaseImpl(this);
		}
		
		@Override
		public ReturnTermsBase.ReturnTermsBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnTermsBase.ReturnTermsBaseBuilder prune() {
			if (valuationTerms!=null && !valuationTerms.prune().hasData()) valuationTerms = null;
			if (dividendApplicability!=null && !dividendApplicability.prune().hasData()) dividendApplicability = null;
			if (equityUnderlierProvisions!=null && !equityUnderlierProvisions.prune().hasData()) equityUnderlierProvisions = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationTerms()!=null && getValuationTerms().hasData()) return true;
			if (getAnnualizationFactor()!=null) return true;
			if (getDividendApplicability()!=null && getDividendApplicability().hasData()) return true;
			if (getEquityUnderlierProvisions()!=null && getEquityUnderlierProvisions().hasData()) return true;
			if (getSharePriceDividendAdjustment()!=null) return true;
			if (getExpectedN()!=null) return true;
			if (getInitialLevel()!=null) return true;
			if (getInitialLevelSource()!=null) return true;
			if (getMeanAdjustment()!=null) return true;
			if (getPerformance()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnTermsBase.ReturnTermsBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReturnTermsBase.ReturnTermsBaseBuilder o = (ReturnTermsBase.ReturnTermsBaseBuilder) other;
			
			merger.mergeRosetta(getValuationTerms(), o.getValuationTerms(), this::setValuationTerms);
			merger.mergeRosetta(getDividendApplicability(), o.getDividendApplicability(), this::setDividendApplicability);
			merger.mergeRosetta(getEquityUnderlierProvisions(), o.getEquityUnderlierProvisions(), this::setEquityUnderlierProvisions);
			
			merger.mergeBasic(getAnnualizationFactor(), o.getAnnualizationFactor(), this::setAnnualizationFactor);
			merger.mergeBasic(getSharePriceDividendAdjustment(), o.getSharePriceDividendAdjustment(), this::setSharePriceDividendAdjustment);
			merger.mergeBasic(getExpectedN(), o.getExpectedN(), this::setExpectedN);
			merger.mergeBasic(getInitialLevel(), o.getInitialLevel(), this::setInitialLevel);
			merger.mergeBasic(getInitialLevelSource(), o.getInitialLevelSource(), this::setInitialLevelSource);
			merger.mergeBasic(getMeanAdjustment(), o.getMeanAdjustment(), this::setMeanAdjustment);
			merger.mergeBasic(getPerformance(), o.getPerformance(), this::setPerformance);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnTermsBase _that = getType().cast(o);
		
			if (!Objects.equals(valuationTerms, _that.getValuationTerms())) return false;
			if (!Objects.equals(annualizationFactor, _that.getAnnualizationFactor())) return false;
			if (!Objects.equals(dividendApplicability, _that.getDividendApplicability())) return false;
			if (!Objects.equals(equityUnderlierProvisions, _that.getEquityUnderlierProvisions())) return false;
			if (!Objects.equals(sharePriceDividendAdjustment, _that.getSharePriceDividendAdjustment())) return false;
			if (!Objects.equals(expectedN, _that.getExpectedN())) return false;
			if (!Objects.equals(initialLevel, _that.getInitialLevel())) return false;
			if (!Objects.equals(initialLevelSource, _that.getInitialLevelSource())) return false;
			if (!Objects.equals(meanAdjustment, _that.getMeanAdjustment())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationTerms != null ? valuationTerms.hashCode() : 0);
			_result = 31 * _result + (annualizationFactor != null ? annualizationFactor.hashCode() : 0);
			_result = 31 * _result + (dividendApplicability != null ? dividendApplicability.hashCode() : 0);
			_result = 31 * _result + (equityUnderlierProvisions != null ? equityUnderlierProvisions.hashCode() : 0);
			_result = 31 * _result + (sharePriceDividendAdjustment != null ? sharePriceDividendAdjustment.hashCode() : 0);
			_result = 31 * _result + (expectedN != null ? expectedN.hashCode() : 0);
			_result = 31 * _result + (initialLevel != null ? initialLevel.hashCode() : 0);
			_result = 31 * _result + (initialLevelSource != null ? initialLevelSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meanAdjustment != null ? meanAdjustment.hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnTermsBaseBuilder {" +
				"valuationTerms=" + this.valuationTerms + ", " +
				"annualizationFactor=" + this.annualizationFactor + ", " +
				"dividendApplicability=" + this.dividendApplicability + ", " +
				"equityUnderlierProvisions=" + this.equityUnderlierProvisions + ", " +
				"sharePriceDividendAdjustment=" + this.sharePriceDividendAdjustment + ", " +
				"expectedN=" + this.expectedN + ", " +
				"initialLevel=" + this.initialLevel + ", " +
				"initialLevelSource=" + this.initialLevelSource + ", " +
				"meanAdjustment=" + this.meanAdjustment + ", " +
				"performance=" + this.performance +
			'}';
		}
	}
}
