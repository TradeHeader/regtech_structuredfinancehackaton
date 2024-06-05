package cdm.product.asset;

import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.Observable;
import cdm.observable.asset.Price;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ReturnTermsBase;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilder;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseBuilderImpl;
import cdm.product.asset.ReturnTermsBase.ReturnTermsBaseImpl;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.VolatilityCapFloor;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.asset.VolatilityReturnTerms.VolatilityReturnTermsBuilder;
import cdm.product.asset.VolatilityReturnTerms.VolatilityReturnTermsBuilderImpl;
import cdm.product.asset.VolatilityReturnTerms.VolatilityReturnTermsImpl;
import cdm.product.asset.meta.VolatilityReturnTermsMeta;
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
@RosettaDataType(value="VolatilityReturnTerms", builder=VolatilityReturnTerms.VolatilityReturnTermsBuilderImpl.class, version="${project.version}")
public interface VolatilityReturnTerms extends ReturnTermsBase {

	VolatilityReturnTermsMeta metaData = new VolatilityReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Volatility Strike Price in accordance with the ISDA 2011 Equity Derivatives Definitions.
	 */
	Price getVolatilityStrikePrice();
	/**
	 * Contains volatility-based barriers
	 */
	VolatilityCapFloor getVolatilityCapFloor();
	/**
	 * Specification of the exchange traded contract nearest.
	 */
	Observable getExchangeTradedContractNearest();

	/*********************** Build Methods  ***********************/
	VolatilityReturnTerms build();
	
	VolatilityReturnTerms.VolatilityReturnTermsBuilder toBuilder();
	
	static VolatilityReturnTerms.VolatilityReturnTermsBuilder builder() {
		return new VolatilityReturnTerms.VolatilityReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends VolatilityReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends VolatilityReturnTerms> getType() {
		return VolatilityReturnTerms.class;
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
		processRosetta(path.newSubPath("volatilityStrikePrice"), processor, Price.class, getVolatilityStrikePrice());
		processRosetta(path.newSubPath("volatilityCapFloor"), processor, VolatilityCapFloor.class, getVolatilityCapFloor());
		processRosetta(path.newSubPath("exchangeTradedContractNearest"), processor, Observable.class, getExchangeTradedContractNearest());
	}
	

	/*********************** Builder Interface  ***********************/
	interface VolatilityReturnTermsBuilder extends VolatilityReturnTerms, ReturnTermsBase.ReturnTermsBaseBuilder, RosettaModelObjectBuilder {
		Price.PriceBuilder getOrCreateVolatilityStrikePrice();
		Price.PriceBuilder getVolatilityStrikePrice();
		VolatilityCapFloor.VolatilityCapFloorBuilder getOrCreateVolatilityCapFloor();
		VolatilityCapFloor.VolatilityCapFloorBuilder getVolatilityCapFloor();
		Observable.ObservableBuilder getOrCreateExchangeTradedContractNearest();
		Observable.ObservableBuilder getExchangeTradedContractNearest();
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setValuationTerms(ValuationTerms valuationTerms);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setAnnualizationFactor(Integer annualizationFactor);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setDividendApplicability(DividendApplicability dividendApplicability);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setExpectedN(Integer expectedN);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setInitialLevel(BigDecimal initialLevel);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setMeanAdjustment(Boolean meanAdjustment);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setPerformance(String performance);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setVolatilityStrikePrice(Price volatilityStrikePrice);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setVolatilityCapFloor(VolatilityCapFloor volatilityCapFloor);
		VolatilityReturnTerms.VolatilityReturnTermsBuilder setExchangeTradedContractNearest(Observable exchangeTradedContractNearest);

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
			processRosetta(path.newSubPath("volatilityStrikePrice"), processor, Price.PriceBuilder.class, getVolatilityStrikePrice());
			processRosetta(path.newSubPath("volatilityCapFloor"), processor, VolatilityCapFloor.VolatilityCapFloorBuilder.class, getVolatilityCapFloor());
			processRosetta(path.newSubPath("exchangeTradedContractNearest"), processor, Observable.ObservableBuilder.class, getExchangeTradedContractNearest());
		}
		

		VolatilityReturnTerms.VolatilityReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of VolatilityReturnTerms  ***********************/
	class VolatilityReturnTermsImpl extends ReturnTermsBase.ReturnTermsBaseImpl implements VolatilityReturnTerms {
		private final Price volatilityStrikePrice;
		private final VolatilityCapFloor volatilityCapFloor;
		private final Observable exchangeTradedContractNearest;
		
		protected VolatilityReturnTermsImpl(VolatilityReturnTerms.VolatilityReturnTermsBuilder builder) {
			super(builder);
			this.volatilityStrikePrice = ofNullable(builder.getVolatilityStrikePrice()).map(f->f.build()).orElse(null);
			this.volatilityCapFloor = ofNullable(builder.getVolatilityCapFloor()).map(f->f.build()).orElse(null);
			this.exchangeTradedContractNearest = ofNullable(builder.getExchangeTradedContractNearest()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("volatilityStrikePrice")
		public Price getVolatilityStrikePrice() {
			return volatilityStrikePrice;
		}
		
		@Override
		@RosettaAttribute("volatilityCapFloor")
		public VolatilityCapFloor getVolatilityCapFloor() {
			return volatilityCapFloor;
		}
		
		@Override
		@RosettaAttribute("exchangeTradedContractNearest")
		public Observable getExchangeTradedContractNearest() {
			return exchangeTradedContractNearest;
		}
		
		@Override
		public VolatilityReturnTerms build() {
			return this;
		}
		
		@Override
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder toBuilder() {
			VolatilityReturnTerms.VolatilityReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(VolatilityReturnTerms.VolatilityReturnTermsBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getVolatilityStrikePrice()).ifPresent(builder::setVolatilityStrikePrice);
			ofNullable(getVolatilityCapFloor()).ifPresent(builder::setVolatilityCapFloor);
			ofNullable(getExchangeTradedContractNearest()).ifPresent(builder::setExchangeTradedContractNearest);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			VolatilityReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(volatilityStrikePrice, _that.getVolatilityStrikePrice())) return false;
			if (!Objects.equals(volatilityCapFloor, _that.getVolatilityCapFloor())) return false;
			if (!Objects.equals(exchangeTradedContractNearest, _that.getExchangeTradedContractNearest())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (volatilityStrikePrice != null ? volatilityStrikePrice.hashCode() : 0);
			_result = 31 * _result + (volatilityCapFloor != null ? volatilityCapFloor.hashCode() : 0);
			_result = 31 * _result + (exchangeTradedContractNearest != null ? exchangeTradedContractNearest.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VolatilityReturnTerms {" +
				"volatilityStrikePrice=" + this.volatilityStrikePrice + ", " +
				"volatilityCapFloor=" + this.volatilityCapFloor + ", " +
				"exchangeTradedContractNearest=" + this.exchangeTradedContractNearest +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of VolatilityReturnTerms  ***********************/
	class VolatilityReturnTermsBuilderImpl extends ReturnTermsBase.ReturnTermsBaseBuilderImpl  implements VolatilityReturnTerms.VolatilityReturnTermsBuilder {
	
		protected Price.PriceBuilder volatilityStrikePrice;
		protected VolatilityCapFloor.VolatilityCapFloorBuilder volatilityCapFloor;
		protected Observable.ObservableBuilder exchangeTradedContractNearest;
	
		public VolatilityReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("volatilityStrikePrice")
		public Price.PriceBuilder getVolatilityStrikePrice() {
			return volatilityStrikePrice;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateVolatilityStrikePrice() {
			Price.PriceBuilder result;
			if (volatilityStrikePrice!=null) {
				result = volatilityStrikePrice;
			}
			else {
				result = volatilityStrikePrice = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("volatilityCapFloor")
		public VolatilityCapFloor.VolatilityCapFloorBuilder getVolatilityCapFloor() {
			return volatilityCapFloor;
		}
		
		@Override
		public VolatilityCapFloor.VolatilityCapFloorBuilder getOrCreateVolatilityCapFloor() {
			VolatilityCapFloor.VolatilityCapFloorBuilder result;
			if (volatilityCapFloor!=null) {
				result = volatilityCapFloor;
			}
			else {
				result = volatilityCapFloor = VolatilityCapFloor.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exchangeTradedContractNearest")
		public Observable.ObservableBuilder getExchangeTradedContractNearest() {
			return exchangeTradedContractNearest;
		}
		
		@Override
		public Observable.ObservableBuilder getOrCreateExchangeTradedContractNearest() {
			Observable.ObservableBuilder result;
			if (exchangeTradedContractNearest!=null) {
				result = exchangeTradedContractNearest;
			}
			else {
				result = exchangeTradedContractNearest = Observable.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("valuationTerms")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setValuationTerms(ValuationTerms valuationTerms) {
			this.valuationTerms = valuationTerms==null?null:valuationTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("annualizationFactor")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setAnnualizationFactor(Integer annualizationFactor) {
			this.annualizationFactor = annualizationFactor==null?null:annualizationFactor;
			return this;
		}
		@Override
		@RosettaAttribute("dividendApplicability")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setDividendApplicability(DividendApplicability dividendApplicability) {
			this.dividendApplicability = dividendApplicability==null?null:dividendApplicability.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityUnderlierProvisions")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setEquityUnderlierProvisions(EquityUnderlierProvisions equityUnderlierProvisions) {
			this.equityUnderlierProvisions = equityUnderlierProvisions==null?null:equityUnderlierProvisions.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("sharePriceDividendAdjustment")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setSharePriceDividendAdjustment(Boolean sharePriceDividendAdjustment) {
			this.sharePriceDividendAdjustment = sharePriceDividendAdjustment==null?null:sharePriceDividendAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("expectedN")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setExpectedN(Integer expectedN) {
			this.expectedN = expectedN==null?null:expectedN;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevel")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setInitialLevel(BigDecimal initialLevel) {
			this.initialLevel = initialLevel==null?null:initialLevel;
			return this;
		}
		@Override
		@RosettaAttribute("initialLevelSource")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setInitialLevelSource(DeterminationMethodEnum initialLevelSource) {
			this.initialLevelSource = initialLevelSource==null?null:initialLevelSource;
			return this;
		}
		@Override
		@RosettaAttribute("meanAdjustment")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setMeanAdjustment(Boolean meanAdjustment) {
			this.meanAdjustment = meanAdjustment==null?null:meanAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("performance")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setPerformance(String performance) {
			this.performance = performance==null?null:performance;
			return this;
		}
		@Override
		@RosettaAttribute("volatilityStrikePrice")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setVolatilityStrikePrice(Price volatilityStrikePrice) {
			this.volatilityStrikePrice = volatilityStrikePrice==null?null:volatilityStrikePrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("volatilityCapFloor")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setVolatilityCapFloor(VolatilityCapFloor volatilityCapFloor) {
			this.volatilityCapFloor = volatilityCapFloor==null?null:volatilityCapFloor.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exchangeTradedContractNearest")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder setExchangeTradedContractNearest(Observable exchangeTradedContractNearest) {
			this.exchangeTradedContractNearest = exchangeTradedContractNearest==null?null:exchangeTradedContractNearest.toBuilder();
			return this;
		}
		
		@Override
		public VolatilityReturnTerms build() {
			return new VolatilityReturnTerms.VolatilityReturnTermsImpl(this);
		}
		
		@Override
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder prune() {
			super.prune();
			if (volatilityStrikePrice!=null && !volatilityStrikePrice.prune().hasData()) volatilityStrikePrice = null;
			if (volatilityCapFloor!=null && !volatilityCapFloor.prune().hasData()) volatilityCapFloor = null;
			if (exchangeTradedContractNearest!=null && !exchangeTradedContractNearest.prune().hasData()) exchangeTradedContractNearest = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getVolatilityStrikePrice()!=null && getVolatilityStrikePrice().hasData()) return true;
			if (getVolatilityCapFloor()!=null && getVolatilityCapFloor().hasData()) return true;
			if (getExchangeTradedContractNearest()!=null && getExchangeTradedContractNearest().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			VolatilityReturnTerms.VolatilityReturnTermsBuilder o = (VolatilityReturnTerms.VolatilityReturnTermsBuilder) other;
			
			merger.mergeRosetta(getVolatilityStrikePrice(), o.getVolatilityStrikePrice(), this::setVolatilityStrikePrice);
			merger.mergeRosetta(getVolatilityCapFloor(), o.getVolatilityCapFloor(), this::setVolatilityCapFloor);
			merger.mergeRosetta(getExchangeTradedContractNearest(), o.getExchangeTradedContractNearest(), this::setExchangeTradedContractNearest);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			VolatilityReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(volatilityStrikePrice, _that.getVolatilityStrikePrice())) return false;
			if (!Objects.equals(volatilityCapFloor, _that.getVolatilityCapFloor())) return false;
			if (!Objects.equals(exchangeTradedContractNearest, _that.getExchangeTradedContractNearest())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (volatilityStrikePrice != null ? volatilityStrikePrice.hashCode() : 0);
			_result = 31 * _result + (volatilityCapFloor != null ? volatilityCapFloor.hashCode() : 0);
			_result = 31 * _result + (exchangeTradedContractNearest != null ? exchangeTradedContractNearest.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VolatilityReturnTermsBuilder {" +
				"volatilityStrikePrice=" + this.volatilityStrikePrice + ", " +
				"volatilityCapFloor=" + this.volatilityCapFloor + ", " +
				"exchangeTradedContractNearest=" + this.exchangeTradedContractNearest +
			'}' + " " + super.toString();
		}
	}
}
