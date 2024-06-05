package cdm.observable.asset;

import cdm.observable.asset.CrossRate;
import cdm.observable.asset.CrossRate.CrossRateBuilder;
import cdm.observable.asset.CrossRate.CrossRateBuilderImpl;
import cdm.observable.asset.CrossRate.CrossRateImpl;
import cdm.observable.asset.QuoteBasisEnum;
import cdm.observable.asset.QuotedCurrencyPair;
import cdm.observable.asset.QuotedCurrencyPair.QuotedCurrencyPairBuilder;
import cdm.observable.asset.QuotedCurrencyPair.QuotedCurrencyPairBuilderImpl;
import cdm.observable.asset.QuotedCurrencyPair.QuotedCurrencyPairImpl;
import cdm.observable.asset.meta.CrossRateMeta;
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
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class that is used for including the currency exchange rates used to cross between the traded currencies for non-base currency FX contracts.
 * @version ${project.version}
 */
@RosettaDataType(value="CrossRate", builder=CrossRate.CrossRateBuilderImpl.class, version="${project.version}")
public interface CrossRate extends QuotedCurrencyPair {

	CrossRateMeta metaData = new CrossRateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The exchange rate used to cross between the traded currencies.
	 */
	BigDecimal getRate();
	/**
	 * An optional element used for FX forwards and certain types of FX OTC options. For deals consummated in the FX Forwards Market, this represents the current market rate for a particular currency pair.
	 */
	BigDecimal getSpotRate();
	/**
	 * An optional element used for deals consummated in the FX Forwards market. Forward points represent the interest rate differential between the two currencies traded and are quoted as a premium or a discount. Forward points are added to, or subtracted from, the spot rate to create the rate of the forward trade.
	 */
	BigDecimal getForwardPoints();

	/*********************** Build Methods  ***********************/
	CrossRate build();
	
	CrossRate.CrossRateBuilder toBuilder();
	
	static CrossRate.CrossRateBuilder builder() {
		return new CrossRate.CrossRateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CrossRate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CrossRate> getType() {
		return CrossRate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("currency1"), processor, FieldWithMetaString.class, getCurrency1());
		processRosetta(path.newSubPath("currency2"), processor, FieldWithMetaString.class, getCurrency2());
		processor.processBasic(path.newSubPath("quoteBasis"), QuoteBasisEnum.class, getQuoteBasis(), this);
		processor.processBasic(path.newSubPath("rate"), BigDecimal.class, getRate(), this);
		processor.processBasic(path.newSubPath("spotRate"), BigDecimal.class, getSpotRate(), this);
		processor.processBasic(path.newSubPath("forwardPoints"), BigDecimal.class, getForwardPoints(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CrossRateBuilder extends CrossRate, QuotedCurrencyPair.QuotedCurrencyPairBuilder, RosettaModelObjectBuilder {
		CrossRate.CrossRateBuilder setCurrency1(FieldWithMetaString currency10);
		CrossRate.CrossRateBuilder setCurrency1Value(String currency11);
		CrossRate.CrossRateBuilder setCurrency2(FieldWithMetaString currency20);
		CrossRate.CrossRateBuilder setCurrency2Value(String currency21);
		CrossRate.CrossRateBuilder setQuoteBasis(QuoteBasisEnum quoteBasis);
		CrossRate.CrossRateBuilder setRate(BigDecimal rate);
		CrossRate.CrossRateBuilder setSpotRate(BigDecimal spotRate);
		CrossRate.CrossRateBuilder setForwardPoints(BigDecimal forwardPoints);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("currency1"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency1());
			processRosetta(path.newSubPath("currency2"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency2());
			processor.processBasic(path.newSubPath("quoteBasis"), QuoteBasisEnum.class, getQuoteBasis(), this);
			processor.processBasic(path.newSubPath("rate"), BigDecimal.class, getRate(), this);
			processor.processBasic(path.newSubPath("spotRate"), BigDecimal.class, getSpotRate(), this);
			processor.processBasic(path.newSubPath("forwardPoints"), BigDecimal.class, getForwardPoints(), this);
		}
		

		CrossRate.CrossRateBuilder prune();
	}

	/*********************** Immutable Implementation of CrossRate  ***********************/
	class CrossRateImpl extends QuotedCurrencyPair.QuotedCurrencyPairImpl implements CrossRate {
		private final BigDecimal rate;
		private final BigDecimal spotRate;
		private final BigDecimal forwardPoints;
		
		protected CrossRateImpl(CrossRate.CrossRateBuilder builder) {
			super(builder);
			this.rate = builder.getRate();
			this.spotRate = builder.getSpotRate();
			this.forwardPoints = builder.getForwardPoints();
		}
		
		@Override
		@RosettaAttribute("rate")
		public BigDecimal getRate() {
			return rate;
		}
		
		@Override
		@RosettaAttribute("spotRate")
		public BigDecimal getSpotRate() {
			return spotRate;
		}
		
		@Override
		@RosettaAttribute("forwardPoints")
		public BigDecimal getForwardPoints() {
			return forwardPoints;
		}
		
		@Override
		public CrossRate build() {
			return this;
		}
		
		@Override
		public CrossRate.CrossRateBuilder toBuilder() {
			CrossRate.CrossRateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CrossRate.CrossRateBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getRate()).ifPresent(builder::setRate);
			ofNullable(getSpotRate()).ifPresent(builder::setSpotRate);
			ofNullable(getForwardPoints()).ifPresent(builder::setForwardPoints);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CrossRate _that = getType().cast(o);
		
			if (!Objects.equals(rate, _that.getRate())) return false;
			if (!Objects.equals(spotRate, _that.getSpotRate())) return false;
			if (!Objects.equals(forwardPoints, _that.getForwardPoints())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (rate != null ? rate.hashCode() : 0);
			_result = 31 * _result + (spotRate != null ? spotRate.hashCode() : 0);
			_result = 31 * _result + (forwardPoints != null ? forwardPoints.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CrossRate {" +
				"rate=" + this.rate + ", " +
				"spotRate=" + this.spotRate + ", " +
				"forwardPoints=" + this.forwardPoints +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CrossRate  ***********************/
	class CrossRateBuilderImpl extends QuotedCurrencyPair.QuotedCurrencyPairBuilderImpl  implements CrossRate.CrossRateBuilder {
	
		protected BigDecimal rate;
		protected BigDecimal spotRate;
		protected BigDecimal forwardPoints;
	
		public CrossRateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rate")
		public BigDecimal getRate() {
			return rate;
		}
		
		@Override
		@RosettaAttribute("spotRate")
		public BigDecimal getSpotRate() {
			return spotRate;
		}
		
		@Override
		@RosettaAttribute("forwardPoints")
		public BigDecimal getForwardPoints() {
			return forwardPoints;
		}
		
	
		@Override
		@RosettaAttribute("currency1")
		public CrossRate.CrossRateBuilder setCurrency1(FieldWithMetaString currency1) {
			this.currency1 = currency1==null?null:currency1.toBuilder();
			return this;
		}
		@Override
		public CrossRate.CrossRateBuilder setCurrency1Value(String currency1) {
			this.getOrCreateCurrency1().setValue(currency1);
			return this;
		}
		@Override
		@RosettaAttribute("currency2")
		public CrossRate.CrossRateBuilder setCurrency2(FieldWithMetaString currency2) {
			this.currency2 = currency2==null?null:currency2.toBuilder();
			return this;
		}
		@Override
		public CrossRate.CrossRateBuilder setCurrency2Value(String currency2) {
			this.getOrCreateCurrency2().setValue(currency2);
			return this;
		}
		@Override
		@RosettaAttribute("quoteBasis")
		public CrossRate.CrossRateBuilder setQuoteBasis(QuoteBasisEnum quoteBasis) {
			this.quoteBasis = quoteBasis==null?null:quoteBasis;
			return this;
		}
		@Override
		@RosettaAttribute("rate")
		public CrossRate.CrossRateBuilder setRate(BigDecimal rate) {
			this.rate = rate==null?null:rate;
			return this;
		}
		@Override
		@RosettaAttribute("spotRate")
		public CrossRate.CrossRateBuilder setSpotRate(BigDecimal spotRate) {
			this.spotRate = spotRate==null?null:spotRate;
			return this;
		}
		@Override
		@RosettaAttribute("forwardPoints")
		public CrossRate.CrossRateBuilder setForwardPoints(BigDecimal forwardPoints) {
			this.forwardPoints = forwardPoints==null?null:forwardPoints;
			return this;
		}
		
		@Override
		public CrossRate build() {
			return new CrossRate.CrossRateImpl(this);
		}
		
		@Override
		public CrossRate.CrossRateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CrossRate.CrossRateBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getRate()!=null) return true;
			if (getSpotRate()!=null) return true;
			if (getForwardPoints()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CrossRate.CrossRateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CrossRate.CrossRateBuilder o = (CrossRate.CrossRateBuilder) other;
			
			
			merger.mergeBasic(getRate(), o.getRate(), this::setRate);
			merger.mergeBasic(getSpotRate(), o.getSpotRate(), this::setSpotRate);
			merger.mergeBasic(getForwardPoints(), o.getForwardPoints(), this::setForwardPoints);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CrossRate _that = getType().cast(o);
		
			if (!Objects.equals(rate, _that.getRate())) return false;
			if (!Objects.equals(spotRate, _that.getSpotRate())) return false;
			if (!Objects.equals(forwardPoints, _that.getForwardPoints())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (rate != null ? rate.hashCode() : 0);
			_result = 31 * _result + (spotRate != null ? spotRate.hashCode() : 0);
			_result = 31 * _result + (forwardPoints != null ? forwardPoints.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CrossRateBuilder {" +
				"rate=" + this.rate + ", " +
				"spotRate=" + this.spotRate + ", " +
				"forwardPoints=" + this.forwardPoints +
			'}' + " " + super.toString();
		}
	}
}
