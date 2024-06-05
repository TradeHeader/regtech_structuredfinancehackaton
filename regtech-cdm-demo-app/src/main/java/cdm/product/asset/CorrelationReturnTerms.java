package cdm.product.asset;

import cdm.base.math.NumberRange;
import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.Price;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.CorrelationReturnTerms.CorrelationReturnTermsBuilder;
import cdm.product.asset.CorrelationReturnTerms.CorrelationReturnTermsBuilderImpl;
import cdm.product.asset.CorrelationReturnTerms.CorrelationReturnTermsImpl;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ReturnTermsBase;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilder;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilderImpl;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseImpl;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.meta.CorrelationReturnTermsMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="CorrelationReturnTerms", builder=CorrelationReturnTerms.CorrelationReturnTermsBuilderImpl.class, version="${project.version}")
public interface CorrelationReturnTerms extends ReturnTermsBase {

	CorrelationReturnTermsMeta metaData = new CorrelationReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Correlation Strike Price in accordance with the ISDA 2011 Equity Derivatives Definitions.
	 */
	Price getCorrelationStrikePrice();
	/**
	 * Describes correlation bounds, which form a cap and a floor on the realized correlation.
	 */
	NumberRange getBoundedCorrelation();
	/**
	 * Number of data series, normal market practice is that correlation data sets are drawn from geographic market areas, such as America, Europe and Asia Pacific, each of these geographic areas will have its own data series to avoid contagion.
	 */
	Integer getNumberOfDataSeries();

	/*********************** Build Methods  ***********************/
	CorrelationReturnTerms build();
	
	CorrelationReturnTerms.CorrelationReturnTermsBuilder toBuilder();
	
	static CorrelationReturnTerms.CorrelationReturnTermsBuilder builder() {
		return new CorrelationReturnTerms.CorrelationReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CorrelationReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CorrelationReturnTerms> getType() {
		return CorrelationReturnTerms.class;
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
		processRosetta(path.newSubPath("correlationStrikePrice"), processor, Price.class, getCorrelationStrikePrice());
		processRosetta(path.newSubPath("boundedCorrelation"), processor, NumberRange.class, getBoundedCorrelation());
		processor.processBasic(path.newSubPath("numberOfDataSeries"), Integer.class, getNumberOfDataSeries(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CorrelationReturnTermsBuilder extends CorrelationReturnTerms, ReturnTermsBase.ReturnTermsBaseBuilder, RosettaModelObjectBuilder {
		Price.PriceBuilder getOrCreateCorrelationStrikePrice();
		Price.PriceBuilder getCorrelationStrikePrice();
		NumberRange.NumberRangeBuilder getOrCreateBoundedCorrelation();
		NumberRange.NumberRangeBuilder getBoundedCorrelation();
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setValuationTerms(ValuationTerms valuationTerms);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setAnnualizationFactor(Integer annualizationFactor);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setDividendApplicability(DividendApplicability dividendApplicability);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setExpectedN(Integer expectedN);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setInitialLevel(BigDecimal initialLevel);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setMeanAdjustment(Boolean meanAdjustment);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setPerformance(String performance);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setCorrelationStrikePrice(Price correlationStrikePrice);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setBoundedCorrelation(NumberRange boundedCorrelation);
		CorrelationReturnTerms.CorrelationReturnTermsBuilder setNumberOfDataSeries(Integer numberOfDataSeries);

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
			processRosetta(path.newSubPath("correlationStrikePrice"), processor, Price.PriceBuilder.class, getCorrelationStrikePrice());
			processRosetta(path.newSubPath("boundedCorrelation"), processor, NumberRange.NumberRangeBuilder.class, getBoundedCorrelation());
			processor.processBasic(path.newSubPath("numberOfDataSeries"), Integer.class, getNumberOfDataSeries(), this);
		}
		

		CorrelationReturnTerms.CorrelationReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of CorrelationReturnTerms  ***********************/
	class CorrelationReturnTermsImpl extends ReturnTermsBase.ReturnTermsBaseImpl implements CorrelationReturnTerms {
		private final Price correlationStrikePrice;
		private final NumberRange boundedCorrelation;
		private final Integer numberOfDataSeries;
		
		protected CorrelationReturnTermsImpl(CorrelationReturnTerms.CorrelationReturnTermsBuilder builder) {
			super(builder);
			this.correlationStrikePrice = ofNullable(builder.getCorrelationStrikePrice()).map(f->f.build()).orElse(null);
			this.boundedCorrelation = ofNullable(builder.getBoundedCorrelation()).map(f->f.build()).orElse(null);
			this.numberOfDataSeries = builder.getNumberOfDataSeries();
		}
		
		@Override
		@RosettaAttribute("correlationStrikePrice")
		public Price getCorrelationStrikePrice() {
			return correlationStrikePrice;
		}
		
		@Override
		@RosettaAttribute("boundedCorrelation")
		public NumberRange getBoundedCorrelation() {
			return boundedCorrelation;
		}
		
		@Override
		@RosettaAttribute("numberOfDataSeries")
		public Integer getNumberOfDataSeries() {
			return numberOfDataSeries;
		}
		
		@Override
		public CorrelationReturnTerms build() {
			return this;
		}
		
		@Override
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder toBuilder() {
			CorrelationReturnTerms.CorrelationReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CorrelationReturnTerms.CorrelationReturnTermsBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getCorrelationStrikePrice()).ifPresent(builder::setCorrelationStrikePrice);
			ofNullable(getBoundedCorrelation()).ifPresent(builder::setBoundedCorrelation);
			ofNullable(getNumberOfDataSeries()).ifPresent(builder::setNumberOfDataSeries);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CorrelationReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(correlationStrikePrice, _that.getCorrelationStrikePrice())) return false;
			if (!Objects.equals(boundedCorrelation, _that.getBoundedCorrelation())) return false;
			if (!Objects.equals(numberOfDataSeries, _that.getNumberOfDataSeries())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (correlationStrikePrice != null ? correlationStrikePrice.hashCode() : 0);
			_result = 31 * _result + (boundedCorrelation != null ? boundedCorrelation.hashCode() : 0);
			_result = 31 * _result + (numberOfDataSeries != null ? numberOfDataSeries.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CorrelationReturnTerms {" +
				"correlationStrikePrice=" + this.correlationStrikePrice + ", " +
				"boundedCorrelation=" + this.boundedCorrelation + ", " +
				"numberOfDataSeries=" + this.numberOfDataSeries +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CorrelationReturnTerms  ***********************/
	class CorrelationReturnTermsBuilderImpl extends ReturnTermsBase.ReturnTermsBaseBuilderImpl  implements CorrelationReturnTerms.CorrelationReturnTermsBuilder {
	
		protected Price.PriceBuilder correlationStrikePrice;
		protected NumberRange.NumberRangeBuilder boundedCorrelation;
		protected Integer numberOfDataSeries;
	
		public CorrelationReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("correlationStrikePrice")
		public Price.PriceBuilder getCorrelationStrikePrice() {
			return correlationStrikePrice;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateCorrelationStrikePrice() {
			Price.PriceBuilder result;
			if (correlationStrikePrice!=null) {
				result = correlationStrikePrice;
			}
			else {
				result = correlationStrikePrice = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("boundedCorrelation")
		public NumberRange.NumberRangeBuilder getBoundedCorrelation() {
			return boundedCorrelation;
		}
		
		@Override
		public NumberRange.NumberRangeBuilder getOrCreateBoundedCorrelation() {
			NumberRange.NumberRangeBuilder result;
			if (boundedCorrelation!=null) {
				result = boundedCorrelation;
			}
			else {
				result = boundedCorrelation = NumberRange.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("numberOfDataSeries")
		public Integer getNumberOfDataSeries() {
			return numberOfDataSeries;
		}
		
	
		@Override
		@RosettaAttribute("valuationTerms")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setValuationTerms(ValuationTerms valuationTerms) {
			this.valuationTerms = valuationTerms==null?null:valuationTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("annualizationFactor")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setAnnualizationFactor(Integer annualizationFactor) {
			this.annualizationFactor = annualizationFactor==null?null:annualizationFactor;
			return this;
		}
		@Override
		@RosettaAttribute("dividendApplicability")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setDividendApplicability(DividendApplicability dividendApplicability) {
			this.dividendApplicability = dividendApplicability==null?null:dividendApplicability.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityUnderlierProvisions")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions) {
			this.equityUnderlierProvisions = equityUnderlierProvisions==null?null:equityUnderlierProvisions.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("sharePriceDividendAdjustment")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment) {
			this.sharePriceDividendAdjustment = sharePriceDividendAdjustment==null?null:sharePriceDividendAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("expectedN")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setExpectedN(Integer expectedN) {
			this.expectedN = expectedN==null?null:expectedN;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevel")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setInitialLevel(BigDecimal initialLevel) {
			this.initialLevel = initialLevel==null?null:initialLevel;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevelSource")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource) {
			this.initialLevelSource = initialLevelSource==null?null:initialLevelSource;
			return this;
		}
		@Override
		@RosettaAttribute("meanAdjustment")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setMeanAdjustment(Boolean meanAdjustment) {
			this.meanAdjustment = meanAdjustment==null?null:meanAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("performance")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setPerformance(String performance) {
			this.performance = performance==null?null:performance;
			return this;
		}
		@Override
		@RosettaAttribute("correlationStrikePrice")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setCorrelationStrikePrice(Price correlationStrikePrice) {
			this.correlationStrikePrice = correlationStrikePrice==null?null:correlationStrikePrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("boundedCorrelation")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setBoundedCorrelation(NumberRange boundedCorrelation) {
			this.boundedCorrelation = boundedCorrelation==null?null:boundedCorrelation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("numberOfDataSeries")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder setNumberOfDataSeries(Integer numberOfDataSeries) {
			this.numberOfDataSeries = numberOfDataSeries==null?null:numberOfDataSeries;
			return this;
		}
		
		@Override
		public CorrelationReturnTerms build() {
			return new CorrelationReturnTerms.CorrelationReturnTermsImpl(this);
		}
		
		@Override
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder prune() {
			super.prune();
			if (correlationStrikePrice!=null && !correlationStrikePrice.prune().hasData()) correlationStrikePrice = null;
			if (boundedCorrelation!=null && !boundedCorrelation.prune().hasData()) boundedCorrelation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getCorrelationStrikePrice()!=null && getCorrelationStrikePrice().hasData()) return true;
			if (getBoundedCorrelation()!=null && getBoundedCorrelation().hasData()) return true;
			if (getNumberOfDataSeries()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CorrelationReturnTerms.CorrelationReturnTermsBuilder o = (CorrelationReturnTerms.CorrelationReturnTermsBuilder) other;
			
			merger.mergeRosetta(getCorrelationStrikePrice(), o.getCorrelationStrikePrice(), this::setCorrelationStrikePrice);
			merger.mergeRosetta(getBoundedCorrelation(), o.getBoundedCorrelation(), this::setBoundedCorrelation);
			
			merger.mergeBasic(getNumberOfDataSeries(), o.getNumberOfDataSeries(), this::setNumberOfDataSeries);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CorrelationReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(correlationStrikePrice, _that.getCorrelationStrikePrice())) return false;
			if (!Objects.equals(boundedCorrelation, _that.getBoundedCorrelation())) return false;
			if (!Objects.equals(numberOfDataSeries, _that.getNumberOfDataSeries())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (correlationStrikePrice != null ? correlationStrikePrice.hashCode() : 0);
			_result = 31 * _result + (boundedCorrelation != null ? boundedCorrelation.hashCode() : 0);
			_result = 31 * _result + (numberOfDataSeries != null ? numberOfDataSeries.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CorrelationReturnTermsBuilder {" +
				"correlationStrikePrice=" + this.correlationStrikePrice + ", " +
				"boundedCorrelation=" + this.boundedCorrelation + ", " +
				"numberOfDataSeries=" + this.numberOfDataSeries +
			'}' + " " + super.toString();
		}
	}
}
