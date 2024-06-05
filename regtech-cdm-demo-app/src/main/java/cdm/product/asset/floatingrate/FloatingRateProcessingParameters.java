package cdm.product.asset.floatingrate;

import cdm.base.math.Rounding;
import cdm.observable.asset.Price;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilderImpl;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters.FloatingRateProcessingParametersImpl;
import cdm.product.asset.floatingrate.meta.FloatingRateProcessingParametersMeta;
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
 * Type to hold the processing parameters that should be or were used to calculate a floating amount.  These parameters can vary over a schedule so this type holds the acutal values applicable to this calculation.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateProcessingParameters", builder=FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilderImpl.class, version="${project.version}")
public interface FloatingRateProcessingParameters extends RosettaModelObject {

	FloatingRateProcessingParametersMeta metaData = new FloatingRateProcessingParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The rate to be applied for the initial period.
	 */
	Price getInitialRate();
	/**
	 * floating rate multiplier.
	 */
	BigDecimal getMultiplier();
	/**
	 * spread to be added to the floating rate.
	 */
	BigDecimal getSpread();
	/**
	 * US rate treatment (Bond Equivalent Yield or Money Market Yield, if applicable.
	 */
	RateTreatmentEnum getTreatment();
	/**
	 * capt to be applied to the floating rate.
	 */
	BigDecimal getCapRate();
	/**
	 * floor to be applied to the floating rate.
	 */
	BigDecimal getFloorRate();
	/**
	 * THe final rate rounding to be applied.
	 */
	Rounding getRounding();
	/**
	 * How to handle negative interest rates.
	 */
	NegativeInterestRateTreatmentEnum getNegativeTreatment();

	/*********************** Build Methods  ***********************/
	FloatingRateProcessingParameters build();
	
	FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder toBuilder();
	
	static FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder builder() {
		return new FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateProcessingParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateProcessingParameters> getType() {
		return FloatingRateProcessingParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("initialRate"), processor, Price.class, getInitialRate());
		processor.processBasic(path.newSubPath("multiplier"), BigDecimal.class, getMultiplier(), this);
		processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
		processor.processBasic(path.newSubPath("treatment"), RateTreatmentEnum.class, getTreatment(), this);
		processor.processBasic(path.newSubPath("capRate"), BigDecimal.class, getCapRate(), this);
		processor.processBasic(path.newSubPath("floorRate"), BigDecimal.class, getFloorRate(), this);
		processRosetta(path.newSubPath("rounding"), processor, Rounding.class, getRounding());
		processor.processBasic(path.newSubPath("negativeTreatment"), NegativeInterestRateTreatmentEnum.class, getNegativeTreatment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateProcessingParametersBuilder extends FloatingRateProcessingParameters, RosettaModelObjectBuilder {
		Price.PriceBuilder getOrCreateInitialRate();
		Price.PriceBuilder getInitialRate();
		Rounding.RoundingBuilder getOrCreateRounding();
		Rounding.RoundingBuilder getRounding();
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setInitialRate(Price initialRate);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setMultiplier(BigDecimal multiplier);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setSpread(BigDecimal spread);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setTreatment(RateTreatmentEnum treatment);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setCapRate(BigDecimal capRate);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setFloorRate(BigDecimal floorRate);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setRounding(Rounding rounding);
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setNegativeTreatment(NegativeInterestRateTreatmentEnum negativeTreatment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("initialRate"), processor, Price.PriceBuilder.class, getInitialRate());
			processor.processBasic(path.newSubPath("multiplier"), BigDecimal.class, getMultiplier(), this);
			processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
			processor.processBasic(path.newSubPath("treatment"), RateTreatmentEnum.class, getTreatment(), this);
			processor.processBasic(path.newSubPath("capRate"), BigDecimal.class, getCapRate(), this);
			processor.processBasic(path.newSubPath("floorRate"), BigDecimal.class, getFloorRate(), this);
			processRosetta(path.newSubPath("rounding"), processor, Rounding.RoundingBuilder.class, getRounding());
			processor.processBasic(path.newSubPath("negativeTreatment"), NegativeInterestRateTreatmentEnum.class, getNegativeTreatment(), this);
		}
		

		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateProcessingParameters  ***********************/
	class FloatingRateProcessingParametersImpl implements FloatingRateProcessingParameters {
		private final Price initialRate;
		private final BigDecimal multiplier;
		private final BigDecimal spread;
		private final RateTreatmentEnum treatment;
		private final BigDecimal capRate;
		private final BigDecimal floorRate;
		private final Rounding rounding;
		private final NegativeInterestRateTreatmentEnum negativeTreatment;
		
		protected FloatingRateProcessingParametersImpl(FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder builder) {
			this.initialRate = ofNullable(builder.getInitialRate()).map(f->f.build()).orElse(null);
			this.multiplier = builder.getMultiplier();
			this.spread = builder.getSpread();
			this.treatment = builder.getTreatment();
			this.capRate = builder.getCapRate();
			this.floorRate = builder.getFloorRate();
			this.rounding = ofNullable(builder.getRounding()).map(f->f.build()).orElse(null);
			this.negativeTreatment = builder.getNegativeTreatment();
		}
		
		@Override
		@RosettaAttribute("initialRate")
		public Price getInitialRate() {
			return initialRate;
		}
		
		@Override
		@RosettaAttribute("multiplier")
		public BigDecimal getMultiplier() {
			return multiplier;
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("treatment")
		public RateTreatmentEnum getTreatment() {
			return treatment;
		}
		
		@Override
		@RosettaAttribute("capRate")
		public BigDecimal getCapRate() {
			return capRate;
		}
		
		@Override
		@RosettaAttribute("floorRate")
		public BigDecimal getFloorRate() {
			return floorRate;
		}
		
		@Override
		@RosettaAttribute("rounding")
		public Rounding getRounding() {
			return rounding;
		}
		
		@Override
		@RosettaAttribute("negativeTreatment")
		public NegativeInterestRateTreatmentEnum getNegativeTreatment() {
			return negativeTreatment;
		}
		
		@Override
		public FloatingRateProcessingParameters build() {
			return this;
		}
		
		@Override
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder toBuilder() {
			FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder builder) {
			ofNullable(getInitialRate()).ifPresent(builder::setInitialRate);
			ofNullable(getMultiplier()).ifPresent(builder::setMultiplier);
			ofNullable(getSpread()).ifPresent(builder::setSpread);
			ofNullable(getTreatment()).ifPresent(builder::setTreatment);
			ofNullable(getCapRate()).ifPresent(builder::setCapRate);
			ofNullable(getFloorRate()).ifPresent(builder::setFloorRate);
			ofNullable(getRounding()).ifPresent(builder::setRounding);
			ofNullable(getNegativeTreatment()).ifPresent(builder::setNegativeTreatment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateProcessingParameters _that = getType().cast(o);
		
			if (!Objects.equals(initialRate, _that.getInitialRate())) return false;
			if (!Objects.equals(multiplier, _that.getMultiplier())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			if (!Objects.equals(capRate, _that.getCapRate())) return false;
			if (!Objects.equals(floorRate, _that.getFloorRate())) return false;
			if (!Objects.equals(rounding, _that.getRounding())) return false;
			if (!Objects.equals(negativeTreatment, _that.getNegativeTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialRate != null ? initialRate.hashCode() : 0);
			_result = 31 * _result + (multiplier != null ? multiplier.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (treatment != null ? treatment.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (capRate != null ? capRate.hashCode() : 0);
			_result = 31 * _result + (floorRate != null ? floorRate.hashCode() : 0);
			_result = 31 * _result + (rounding != null ? rounding.hashCode() : 0);
			_result = 31 * _result + (negativeTreatment != null ? negativeTreatment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateProcessingParameters {" +
				"initialRate=" + this.initialRate + ", " +
				"multiplier=" + this.multiplier + ", " +
				"spread=" + this.spread + ", " +
				"treatment=" + this.treatment + ", " +
				"capRate=" + this.capRate + ", " +
				"floorRate=" + this.floorRate + ", " +
				"rounding=" + this.rounding + ", " +
				"negativeTreatment=" + this.negativeTreatment +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateProcessingParameters  ***********************/
	class FloatingRateProcessingParametersBuilderImpl implements FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder {
	
		protected Price.PriceBuilder initialRate;
		protected BigDecimal multiplier;
		protected BigDecimal spread;
		protected RateTreatmentEnum treatment;
		protected BigDecimal capRate;
		protected BigDecimal floorRate;
		protected Rounding.RoundingBuilder rounding;
		protected NegativeInterestRateTreatmentEnum negativeTreatment;
	
		public FloatingRateProcessingParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("initialRate")
		public Price.PriceBuilder getInitialRate() {
			return initialRate;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateInitialRate() {
			Price.PriceBuilder result;
			if (initialRate!=null) {
				result = initialRate;
			}
			else {
				result = initialRate = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("multiplier")
		public BigDecimal getMultiplier() {
			return multiplier;
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("treatment")
		public RateTreatmentEnum getTreatment() {
			return treatment;
		}
		
		@Override
		@RosettaAttribute("capRate")
		public BigDecimal getCapRate() {
			return capRate;
		}
		
		@Override
		@RosettaAttribute("floorRate")
		public BigDecimal getFloorRate() {
			return floorRate;
		}
		
		@Override
		@RosettaAttribute("rounding")
		public Rounding.RoundingBuilder getRounding() {
			return rounding;
		}
		
		@Override
		public Rounding.RoundingBuilder getOrCreateRounding() {
			Rounding.RoundingBuilder result;
			if (rounding!=null) {
				result = rounding;
			}
			else {
				result = rounding = Rounding.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("negativeTreatment")
		public NegativeInterestRateTreatmentEnum getNegativeTreatment() {
			return negativeTreatment;
		}
		
	
		@Override
		@RosettaAttribute("initialRate")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setInitialRate(Price initialRate) {
			this.initialRate = initialRate==null?null:initialRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("multiplier")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setMultiplier(BigDecimal multiplier) {
			this.multiplier = multiplier==null?null:multiplier;
			return this;
		}
		@Override
		@RosettaAttribute("spread")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setSpread(BigDecimal spread) {
			this.spread = spread==null?null:spread;
			return this;
		}
		@Override
		@RosettaAttribute("treatment")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setTreatment(RateTreatmentEnum treatment) {
			this.treatment = treatment==null?null:treatment;
			return this;
		}
		@Override
		@RosettaAttribute("capRate")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setCapRate(BigDecimal capRate) {
			this.capRate = capRate==null?null:capRate;
			return this;
		}
		@Override
		@RosettaAttribute("floorRate")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setFloorRate(BigDecimal floorRate) {
			this.floorRate = floorRate==null?null:floorRate;
			return this;
		}
		@Override
		@RosettaAttribute("rounding")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setRounding(Rounding rounding) {
			this.rounding = rounding==null?null:rounding.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("negativeTreatment")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder setNegativeTreatment(NegativeInterestRateTreatmentEnum negativeTreatment) {
			this.negativeTreatment = negativeTreatment==null?null:negativeTreatment;
			return this;
		}
		
		@Override
		public FloatingRateProcessingParameters build() {
			return new FloatingRateProcessingParameters.FloatingRateProcessingParametersImpl(this);
		}
		
		@Override
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder prune() {
			if (initialRate!=null && !initialRate.prune().hasData()) initialRate = null;
			if (rounding!=null && !rounding.prune().hasData()) rounding = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInitialRate()!=null && getInitialRate().hasData()) return true;
			if (getMultiplier()!=null) return true;
			if (getSpread()!=null) return true;
			if (getTreatment()!=null) return true;
			if (getCapRate()!=null) return true;
			if (getFloorRate()!=null) return true;
			if (getRounding()!=null && getRounding().hasData()) return true;
			if (getNegativeTreatment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder o = (FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder) other;
			
			merger.mergeRosetta(getInitialRate(), o.getInitialRate(), this::setInitialRate);
			merger.mergeRosetta(getRounding(), o.getRounding(), this::setRounding);
			
			merger.mergeBasic(getMultiplier(), o.getMultiplier(), this::setMultiplier);
			merger.mergeBasic(getSpread(), o.getSpread(), this::setSpread);
			merger.mergeBasic(getTreatment(), o.getTreatment(), this::setTreatment);
			merger.mergeBasic(getCapRate(), o.getCapRate(), this::setCapRate);
			merger.mergeBasic(getFloorRate(), o.getFloorRate(), this::setFloorRate);
			merger.mergeBasic(getNegativeTreatment(), o.getNegativeTreatment(), this::setNegativeTreatment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateProcessingParameters _that = getType().cast(o);
		
			if (!Objects.equals(initialRate, _that.getInitialRate())) return false;
			if (!Objects.equals(multiplier, _that.getMultiplier())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			if (!Objects.equals(capRate, _that.getCapRate())) return false;
			if (!Objects.equals(floorRate, _that.getFloorRate())) return false;
			if (!Objects.equals(rounding, _that.getRounding())) return false;
			if (!Objects.equals(negativeTreatment, _that.getNegativeTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialRate != null ? initialRate.hashCode() : 0);
			_result = 31 * _result + (multiplier != null ? multiplier.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (treatment != null ? treatment.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (capRate != null ? capRate.hashCode() : 0);
			_result = 31 * _result + (floorRate != null ? floorRate.hashCode() : 0);
			_result = 31 * _result + (rounding != null ? rounding.hashCode() : 0);
			_result = 31 * _result + (negativeTreatment != null ? negativeTreatment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateProcessingParametersBuilder {" +
				"initialRate=" + this.initialRate + ", " +
				"multiplier=" + this.multiplier + ", " +
				"spread=" + this.spread + ", " +
				"treatment=" + this.treatment + ", " +
				"capRate=" + this.capRate + ", " +
				"floorRate=" + this.floorRate + ", " +
				"rounding=" + this.rounding + ", " +
				"negativeTreatment=" + this.negativeTreatment +
			'}';
		}
	}
}
