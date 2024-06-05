package cdm.product.asset;

import cdm.base.datetime.Offset;
import cdm.base.math.AveragingWeightingMethodEnum;
import cdm.base.math.Rounding;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.InterpolationMethodEnum;
import cdm.observable.asset.Price;
import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.InflationCalculationMethodEnum;
import cdm.observable.asset.calculatedrate.InflationCalculationStyleEnum;
import cdm.observable.asset.metafields.FieldWithMetaInterpolationMethodEnum;
import cdm.observable.asset.metafields.FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption.ReferenceWithMetaFloatingRateOptionBuilder;
import cdm.product.asset.FinalPrincipalExchangeCalculationEnum;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.FloatingRateSpecification.FloatingRateSpecificationBuilder;
import cdm.product.asset.FloatingRateSpecification.FloatingRateSpecificationBuilderImpl;
import cdm.product.asset.FloatingRateSpecification.FloatingRateSpecificationImpl;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.asset.InflationRateSpecification.InflationRateSpecificationBuilder;
import cdm.product.asset.InflationRateSpecification.InflationRateSpecificationBuilderImpl;
import cdm.product.asset.InflationRateSpecification.InflationRateSpecificationImpl;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.SpreadSchedule;
import cdm.product.asset.meta.InflationRateSpecificationMeta;
import cdm.product.common.schedule.RateSchedule;
import cdm.product.template.StrikeSchedule;
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
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data to:  specify the inflation rate.
 * @version ${project.version}
 */
@RosettaDataType(value="InflationRateSpecification", builder=InflationRateSpecification.InflationRateSpecificationBuilderImpl.class, version="${project.version}")
public interface InflationRateSpecification extends FloatingRateSpecification {

	InflationRateSpecificationMeta metaData = new InflationRateSpecificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An off-setting period from the payment date which determines the reference period for which the inflation index is observed.
	 */
	Offset getInflationLag();
	/**
	 * The reference source such as Reuters or Bloomberg. FpML specifies indexSource to be of type rateSourcePageScheme, but without specifying actual values.
	 */
	FieldWithMetaString getIndexSource();
	/**
	 * The current main publication source such as relevant web site or a government body. FpML specifies mainPublication to be of type mainPublicationSource, but without specifying actual values.
	 */
	FieldWithMetaString getMainPublication();
	/**
	 * The method used when calculating the Inflation Index Level from multiple points. The most common is Linear.
	 */
	FieldWithMetaInterpolationMethodEnum getInterpolationMethod();
	/**
	 * Initial known index level for the first calculation period.
	 */
	BigDecimal getInitialIndexLevel();
	/**
	 * The applicability of a fallback bond as defined in the 2006 ISDA Inflation Derivatives Definitions, sections 1.3 and 1.8.
	 */
	Boolean getFallbackBondApplicable();
	/**
	 * Indicates how to use the inflation index to calculate the payment (e.g. Ratio, Return, Spread). Added for Inflation Asset Swap
	 */
	InflationCalculationMethodEnum getCalculationMethod();
	/**
	 * Indicates the style of how the inflation index calculates the payment (e.g. YearOnYear, ZeroCoupon).
	 */
	InflationCalculationStyleEnum getCalculationStyle();
	/**
	 * To be specified only for products that embed a redemption payment.
	 */
	FinalPrincipalExchangeCalculationEnum getFinalPrincipalExchangeCalculation();

	/*********************** Build Methods  ***********************/
	InflationRateSpecification build();
	
	InflationRateSpecification.InflationRateSpecificationBuilder toBuilder();
	
	static InflationRateSpecification.InflationRateSpecificationBuilder builder() {
		return new InflationRateSpecification.InflationRateSpecificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InflationRateSpecification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InflationRateSpecification> getType() {
		return InflationRateSpecification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("rateOption"), processor, ReferenceWithMetaFloatingRateOption.class, getRateOption());
		processRosetta(path.newSubPath("spreadSchedule"), processor, SpreadSchedule.class, getSpreadSchedule());
		processRosetta(path.newSubPath("capRateSchedule"), processor, StrikeSchedule.class, getCapRateSchedule());
		processRosetta(path.newSubPath("floorRateSchedule"), processor, StrikeSchedule.class, getFloorRateSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processRosetta(path.newSubPath("floatingRateMultiplierSchedule"), processor, RateSchedule.class, getFloatingRateMultiplierSchedule());
		processor.processBasic(path.newSubPath("rateTreatment"), RateTreatmentEnum.class, getRateTreatment(), this);
		processRosetta(path.newSubPath("calculationParameters"), processor, FloatingRateCalculationParameters.class, getCalculationParameters());
		processRosetta(path.newSubPath("fallbackRate"), processor, FallbackRateParameters.class, getFallbackRate());
		processRosetta(path.newSubPath("initialRate"), processor, Price.class, getInitialRate());
		processRosetta(path.newSubPath("finalRateRounding"), processor, Rounding.class, getFinalRateRounding());
		processor.processBasic(path.newSubPath("averagingMethod"), AveragingWeightingMethodEnum.class, getAveragingMethod(), this);
		processor.processBasic(path.newSubPath("negativeInterestRateTreatment"), NegativeInterestRateTreatmentEnum.class, getNegativeInterestRateTreatment(), this);
		processRosetta(path.newSubPath("inflationLag"), processor, Offset.class, getInflationLag());
		processRosetta(path.newSubPath("indexSource"), processor, FieldWithMetaString.class, getIndexSource());
		processRosetta(path.newSubPath("mainPublication"), processor, FieldWithMetaString.class, getMainPublication());
		processRosetta(path.newSubPath("interpolationMethod"), processor, FieldWithMetaInterpolationMethodEnum.class, getInterpolationMethod());
		processor.processBasic(path.newSubPath("initialIndexLevel"), BigDecimal.class, getInitialIndexLevel(), this);
		processor.processBasic(path.newSubPath("fallbackBondApplicable"), Boolean.class, getFallbackBondApplicable(), this);
		processor.processBasic(path.newSubPath("calculationMethod"), InflationCalculationMethodEnum.class, getCalculationMethod(), this);
		processor.processBasic(path.newSubPath("calculationStyle"), InflationCalculationStyleEnum.class, getCalculationStyle(), this);
		processor.processBasic(path.newSubPath("finalPrincipalExchangeCalculation"), FinalPrincipalExchangeCalculationEnum.class, getFinalPrincipalExchangeCalculation(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface InflationRateSpecificationBuilder extends InflationRateSpecification, FloatingRateSpecification.FloatingRateSpecificationBuilder, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateInflationLag();
		Offset.OffsetBuilder getInflationLag();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexSource();
		FieldWithMetaString.FieldWithMetaStringBuilder getIndexSource();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMainPublication();
		FieldWithMetaString.FieldWithMetaStringBuilder getMainPublication();
		FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder getOrCreateInterpolationMethod();
		FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder getInterpolationMethod();
		InflationRateSpecification.InflationRateSpecificationBuilder setRateOption(ReferenceWithMetaFloatingRateOption rateOption0);
		InflationRateSpecification.InflationRateSpecificationBuilder setRateOptionValue(FloatingRateOption rateOption1);
		InflationRateSpecification.InflationRateSpecificationBuilder setSpreadSchedule(SpreadSchedule spreadSchedule);
		InflationRateSpecification.InflationRateSpecificationBuilder setCapRateSchedule(StrikeSchedule capRateSchedule);
		InflationRateSpecification.InflationRateSpecificationBuilder setFloorRateSchedule(StrikeSchedule floorRateSchedule);
		InflationRateSpecification.InflationRateSpecificationBuilder setMeta(MetaFields meta);
		InflationRateSpecification.InflationRateSpecificationBuilder setFloatingRateMultiplierSchedule(RateSchedule floatingRateMultiplierSchedule);
		InflationRateSpecification.InflationRateSpecificationBuilder setRateTreatment(RateTreatmentEnum rateTreatment);
		InflationRateSpecification.InflationRateSpecificationBuilder setCalculationParameters(FloatingRateCalculationParameters calculationParameters);
		InflationRateSpecification.InflationRateSpecificationBuilder setFallbackRate(FallbackRateParameters fallbackRate);
		InflationRateSpecification.InflationRateSpecificationBuilder setInitialRate(Price initialRate);
		InflationRateSpecification.InflationRateSpecificationBuilder setFinalRateRounding(Rounding finalRateRounding);
		InflationRateSpecification.InflationRateSpecificationBuilder setAveragingMethod(AveragingWeightingMethodEnum averagingMethod);
		InflationRateSpecification.InflationRateSpecificationBuilder setNegativeInterestRateTreatment(NegativeInterestRateTreatmentEnum negativeInterestRateTreatment);
		InflationRateSpecification.InflationRateSpecificationBuilder setInflationLag(Offset inflationLag);
		InflationRateSpecification.InflationRateSpecificationBuilder setIndexSource(FieldWithMetaString indexSource0);
		InflationRateSpecification.InflationRateSpecificationBuilder setIndexSourceValue(String indexSource1);
		InflationRateSpecification.InflationRateSpecificationBuilder setMainPublication(FieldWithMetaString mainPublication0);
		InflationRateSpecification.InflationRateSpecificationBuilder setMainPublicationValue(String mainPublication1);
		InflationRateSpecification.InflationRateSpecificationBuilder setInterpolationMethod(FieldWithMetaInterpolationMethodEnum interpolationMethod0);
		InflationRateSpecification.InflationRateSpecificationBuilder setInterpolationMethodValue(InterpolationMethodEnum interpolationMethod1);
		InflationRateSpecification.InflationRateSpecificationBuilder setInitialIndexLevel(BigDecimal initialIndexLevel);
		InflationRateSpecification.InflationRateSpecificationBuilder setFallbackBondApplicable(Boolean fallbackBondApplicable);
		InflationRateSpecification.InflationRateSpecificationBuilder setCalculationMethod(InflationCalculationMethodEnum calculationMethod);
		InflationRateSpecification.InflationRateSpecificationBuilder setCalculationStyle(InflationCalculationStyleEnum calculationStyle);
		InflationRateSpecification.InflationRateSpecificationBuilder setFinalPrincipalExchangeCalculation(FinalPrincipalExchangeCalculationEnum finalPrincipalExchangeCalculation);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("rateOption"), processor, ReferenceWithMetaFloatingRateOption.ReferenceWithMetaFloatingRateOptionBuilder.class, getRateOption());
			processRosetta(path.newSubPath("spreadSchedule"), processor, SpreadSchedule.SpreadScheduleBuilder.class, getSpreadSchedule());
			processRosetta(path.newSubPath("capRateSchedule"), processor, StrikeSchedule.StrikeScheduleBuilder.class, getCapRateSchedule());
			processRosetta(path.newSubPath("floorRateSchedule"), processor, StrikeSchedule.StrikeScheduleBuilder.class, getFloorRateSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processRosetta(path.newSubPath("floatingRateMultiplierSchedule"), processor, RateSchedule.RateScheduleBuilder.class, getFloatingRateMultiplierSchedule());
			processor.processBasic(path.newSubPath("rateTreatment"), RateTreatmentEnum.class, getRateTreatment(), this);
			processRosetta(path.newSubPath("calculationParameters"), processor, FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder.class, getCalculationParameters());
			processRosetta(path.newSubPath("fallbackRate"), processor, FallbackRateParameters.FallbackRateParametersBuilder.class, getFallbackRate());
			processRosetta(path.newSubPath("initialRate"), processor, Price.PriceBuilder.class, getInitialRate());
			processRosetta(path.newSubPath("finalRateRounding"), processor, Rounding.RoundingBuilder.class, getFinalRateRounding());
			processor.processBasic(path.newSubPath("averagingMethod"), AveragingWeightingMethodEnum.class, getAveragingMethod(), this);
			processor.processBasic(path.newSubPath("negativeInterestRateTreatment"), NegativeInterestRateTreatmentEnum.class, getNegativeInterestRateTreatment(), this);
			processRosetta(path.newSubPath("inflationLag"), processor, Offset.OffsetBuilder.class, getInflationLag());
			processRosetta(path.newSubPath("indexSource"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIndexSource());
			processRosetta(path.newSubPath("mainPublication"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getMainPublication());
			processRosetta(path.newSubPath("interpolationMethod"), processor, FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder.class, getInterpolationMethod());
			processor.processBasic(path.newSubPath("initialIndexLevel"), BigDecimal.class, getInitialIndexLevel(), this);
			processor.processBasic(path.newSubPath("fallbackBondApplicable"), Boolean.class, getFallbackBondApplicable(), this);
			processor.processBasic(path.newSubPath("calculationMethod"), InflationCalculationMethodEnum.class, getCalculationMethod(), this);
			processor.processBasic(path.newSubPath("calculationStyle"), InflationCalculationStyleEnum.class, getCalculationStyle(), this);
			processor.processBasic(path.newSubPath("finalPrincipalExchangeCalculation"), FinalPrincipalExchangeCalculationEnum.class, getFinalPrincipalExchangeCalculation(), this);
		}
		

		InflationRateSpecification.InflationRateSpecificationBuilder prune();
	}

	/*********************** Immutable Implementation of InflationRateSpecification  ***********************/
	class InflationRateSpecificationImpl extends FloatingRateSpecification.FloatingRateSpecificationImpl implements InflationRateSpecification {
		private final Offset inflationLag;
		private final FieldWithMetaString indexSource;
		private final FieldWithMetaString mainPublication;
		private final FieldWithMetaInterpolationMethodEnum interpolationMethod;
		private final BigDecimal initialIndexLevel;
		private final Boolean fallbackBondApplicable;
		private final InflationCalculationMethodEnum calculationMethod;
		private final InflationCalculationStyleEnum calculationStyle;
		private final FinalPrincipalExchangeCalculationEnum finalPrincipalExchangeCalculation;
		
		protected InflationRateSpecificationImpl(InflationRateSpecification.InflationRateSpecificationBuilder builder) {
			super(builder);
			this.inflationLag = ofNullable(builder.getInflationLag()).map(f->f.build()).orElse(null);
			this.indexSource = ofNullable(builder.getIndexSource()).map(f->f.build()).orElse(null);
			this.mainPublication = ofNullable(builder.getMainPublication()).map(f->f.build()).orElse(null);
			this.interpolationMethod = ofNullable(builder.getInterpolationMethod()).map(f->f.build()).orElse(null);
			this.initialIndexLevel = builder.getInitialIndexLevel();
			this.fallbackBondApplicable = builder.getFallbackBondApplicable();
			this.calculationMethod = builder.getCalculationMethod();
			this.calculationStyle = builder.getCalculationStyle();
			this.finalPrincipalExchangeCalculation = builder.getFinalPrincipalExchangeCalculation();
		}
		
		@Override
		@RosettaAttribute("inflationLag")
		public Offset getInflationLag() {
			return inflationLag;
		}
		
		@Override
		@RosettaAttribute("indexSource")
		public FieldWithMetaString getIndexSource() {
			return indexSource;
		}
		
		@Override
		@RosettaAttribute("mainPublication")
		public FieldWithMetaString getMainPublication() {
			return mainPublication;
		}
		
		@Override
		@RosettaAttribute("interpolationMethod")
		public FieldWithMetaInterpolationMethodEnum getInterpolationMethod() {
			return interpolationMethod;
		}
		
		@Override
		@RosettaAttribute("initialIndexLevel")
		public BigDecimal getInitialIndexLevel() {
			return initialIndexLevel;
		}
		
		@Override
		@RosettaAttribute("fallbackBondApplicable")
		public Boolean getFallbackBondApplicable() {
			return fallbackBondApplicable;
		}
		
		@Override
		@RosettaAttribute("calculationMethod")
		public InflationCalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
		@Override
		@RosettaAttribute("calculationStyle")
		public InflationCalculationStyleEnum getCalculationStyle() {
			return calculationStyle;
		}
		
		@Override
		@RosettaAttribute("finalPrincipalExchangeCalculation")
		public FinalPrincipalExchangeCalculationEnum getFinalPrincipalExchangeCalculation() {
			return finalPrincipalExchangeCalculation;
		}
		
		@Override
		public InflationRateSpecification build() {
			return this;
		}
		
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder toBuilder() {
			InflationRateSpecification.InflationRateSpecificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InflationRateSpecification.InflationRateSpecificationBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getInflationLag()).ifPresent(builder::setInflationLag);
			ofNullable(getIndexSource()).ifPresent(builder::setIndexSource);
			ofNullable(getMainPublication()).ifPresent(builder::setMainPublication);
			ofNullable(getInterpolationMethod()).ifPresent(builder::setInterpolationMethod);
			ofNullable(getInitialIndexLevel()).ifPresent(builder::setInitialIndexLevel);
			ofNullable(getFallbackBondApplicable()).ifPresent(builder::setFallbackBondApplicable);
			ofNullable(getCalculationMethod()).ifPresent(builder::setCalculationMethod);
			ofNullable(getCalculationStyle()).ifPresent(builder::setCalculationStyle);
			ofNullable(getFinalPrincipalExchangeCalculation()).ifPresent(builder::setFinalPrincipalExchangeCalculation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			InflationRateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(inflationLag, _that.getInflationLag())) return false;
			if (!Objects.equals(indexSource, _that.getIndexSource())) return false;
			if (!Objects.equals(mainPublication, _that.getMainPublication())) return false;
			if (!Objects.equals(interpolationMethod, _that.getInterpolationMethod())) return false;
			if (!Objects.equals(initialIndexLevel, _that.getInitialIndexLevel())) return false;
			if (!Objects.equals(fallbackBondApplicable, _that.getFallbackBondApplicable())) return false;
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			if (!Objects.equals(calculationStyle, _that.getCalculationStyle())) return false;
			if (!Objects.equals(finalPrincipalExchangeCalculation, _that.getFinalPrincipalExchangeCalculation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (inflationLag != null ? inflationLag.hashCode() : 0);
			_result = 31 * _result + (indexSource != null ? indexSource.hashCode() : 0);
			_result = 31 * _result + (mainPublication != null ? mainPublication.hashCode() : 0);
			_result = 31 * _result + (interpolationMethod != null ? interpolationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (initialIndexLevel != null ? initialIndexLevel.hashCode() : 0);
			_result = 31 * _result + (fallbackBondApplicable != null ? fallbackBondApplicable.hashCode() : 0);
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationStyle != null ? calculationStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (finalPrincipalExchangeCalculation != null ? finalPrincipalExchangeCalculation.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InflationRateSpecification {" +
				"inflationLag=" + this.inflationLag + ", " +
				"indexSource=" + this.indexSource + ", " +
				"mainPublication=" + this.mainPublication + ", " +
				"interpolationMethod=" + this.interpolationMethod + ", " +
				"initialIndexLevel=" + this.initialIndexLevel + ", " +
				"fallbackBondApplicable=" + this.fallbackBondApplicable + ", " +
				"calculationMethod=" + this.calculationMethod + ", " +
				"calculationStyle=" + this.calculationStyle + ", " +
				"finalPrincipalExchangeCalculation=" + this.finalPrincipalExchangeCalculation +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of InflationRateSpecification  ***********************/
	class InflationRateSpecificationBuilderImpl extends FloatingRateSpecification.FloatingRateSpecificationBuilderImpl  implements InflationRateSpecification.InflationRateSpecificationBuilder {
	
		protected Offset.OffsetBuilder inflationLag;
		protected FieldWithMetaString.FieldWithMetaStringBuilder indexSource;
		protected FieldWithMetaString.FieldWithMetaStringBuilder mainPublication;
		protected FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder interpolationMethod;
		protected BigDecimal initialIndexLevel;
		protected Boolean fallbackBondApplicable;
		protected InflationCalculationMethodEnum calculationMethod;
		protected InflationCalculationStyleEnum calculationStyle;
		protected FinalPrincipalExchangeCalculationEnum finalPrincipalExchangeCalculation;
	
		public InflationRateSpecificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("inflationLag")
		public Offset.OffsetBuilder getInflationLag() {
			return inflationLag;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateInflationLag() {
			Offset.OffsetBuilder result;
			if (inflationLag!=null) {
				result = inflationLag;
			}
			else {
				result = inflationLag = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexSource")
		public FieldWithMetaString.FieldWithMetaStringBuilder getIndexSource() {
			return indexSource;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexSource() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (indexSource!=null) {
				result = indexSource;
			}
			else {
				result = indexSource = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("mainPublication")
		public FieldWithMetaString.FieldWithMetaStringBuilder getMainPublication() {
			return mainPublication;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMainPublication() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (mainPublication!=null) {
				result = mainPublication;
			}
			else {
				result = mainPublication = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interpolationMethod")
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder getInterpolationMethod() {
			return interpolationMethod;
		}
		
		@Override
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder getOrCreateInterpolationMethod() {
			FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder result;
			if (interpolationMethod!=null) {
				result = interpolationMethod;
			}
			else {
				result = interpolationMethod = FieldWithMetaInterpolationMethodEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("initialIndexLevel")
		public BigDecimal getInitialIndexLevel() {
			return initialIndexLevel;
		}
		
		@Override
		@RosettaAttribute("fallbackBondApplicable")
		public Boolean getFallbackBondApplicable() {
			return fallbackBondApplicable;
		}
		
		@Override
		@RosettaAttribute("calculationMethod")
		public InflationCalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
		@Override
		@RosettaAttribute("calculationStyle")
		public InflationCalculationStyleEnum getCalculationStyle() {
			return calculationStyle;
		}
		
		@Override
		@RosettaAttribute("finalPrincipalExchangeCalculation")
		public FinalPrincipalExchangeCalculationEnum getFinalPrincipalExchangeCalculation() {
			return finalPrincipalExchangeCalculation;
		}
		
	
		@Override
		@RosettaAttribute("rateOption")
		public InflationRateSpecification.InflationRateSpecificationBuilder setRateOption(ReferenceWithMetaFloatingRateOption rateOption) {
			this.rateOption = rateOption==null?null:rateOption.toBuilder();
			return this;
		}
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder setRateOptionValue(FloatingRateOption rateOption) {
			this.getOrCreateRateOption().setValue(rateOption);
			return this;
		}
		@Override
		@RosettaAttribute("spreadSchedule")
		public InflationRateSpecification.InflationRateSpecificationBuilder setSpreadSchedule(SpreadSchedule spreadSchedule) {
			this.spreadSchedule = spreadSchedule==null?null:spreadSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("capRateSchedule")
		public InflationRateSpecification.InflationRateSpecificationBuilder setCapRateSchedule(StrikeSchedule capRateSchedule) {
			this.capRateSchedule = capRateSchedule==null?null:capRateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floorRateSchedule")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFloorRateSchedule(StrikeSchedule floorRateSchedule) {
			this.floorRateSchedule = floorRateSchedule==null?null:floorRateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public InflationRateSpecification.InflationRateSpecificationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floatingRateMultiplierSchedule")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFloatingRateMultiplierSchedule(RateSchedule floatingRateMultiplierSchedule) {
			this.floatingRateMultiplierSchedule = floatingRateMultiplierSchedule==null?null:floatingRateMultiplierSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("rateTreatment")
		public InflationRateSpecification.InflationRateSpecificationBuilder setRateTreatment(RateTreatmentEnum rateTreatment) {
			this.rateTreatment = rateTreatment==null?null:rateTreatment;
			return this;
		}
		@Override
		@RosettaAttribute("calculationParameters")
		public InflationRateSpecification.InflationRateSpecificationBuilder setCalculationParameters(FloatingRateCalculationParameters calculationParameters) {
			this.calculationParameters = calculationParameters==null?null:calculationParameters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fallbackRate")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFallbackRate(FallbackRateParameters fallbackRate) {
			this.fallbackRate = fallbackRate==null?null:fallbackRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("initialRate")
		public InflationRateSpecification.InflationRateSpecificationBuilder setInitialRate(Price initialRate) {
			this.initialRate = initialRate==null?null:initialRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("finalRateRounding")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFinalRateRounding(Rounding finalRateRounding) {
			this.finalRateRounding = finalRateRounding==null?null:finalRateRounding.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingMethod")
		public InflationRateSpecification.InflationRateSpecificationBuilder setAveragingMethod(AveragingWeightingMethodEnum averagingMethod) {
			this.averagingMethod = averagingMethod==null?null:averagingMethod;
			return this;
		}
		@Override
		@RosettaAttribute("negativeInterestRateTreatment")
		public InflationRateSpecification.InflationRateSpecificationBuilder setNegativeInterestRateTreatment(NegativeInterestRateTreatmentEnum negativeInterestRateTreatment) {
			this.negativeInterestRateTreatment = negativeInterestRateTreatment==null?null:negativeInterestRateTreatment;
			return this;
		}
		@Override
		@RosettaAttribute("inflationLag")
		public InflationRateSpecification.InflationRateSpecificationBuilder setInflationLag(Offset inflationLag) {
			this.inflationLag = inflationLag==null?null:inflationLag.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("indexSource")
		public InflationRateSpecification.InflationRateSpecificationBuilder setIndexSource(FieldWithMetaString indexSource) {
			this.indexSource = indexSource==null?null:indexSource.toBuilder();
			return this;
		}
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder setIndexSourceValue(String indexSource) {
			this.getOrCreateIndexSource().setValue(indexSource);
			return this;
		}
		@Override
		@RosettaAttribute("mainPublication")
		public InflationRateSpecification.InflationRateSpecificationBuilder setMainPublication(FieldWithMetaString mainPublication) {
			this.mainPublication = mainPublication==null?null:mainPublication.toBuilder();
			return this;
		}
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder setMainPublicationValue(String mainPublication) {
			this.getOrCreateMainPublication().setValue(mainPublication);
			return this;
		}
		@Override
		@RosettaAttribute("interpolationMethod")
		public InflationRateSpecification.InflationRateSpecificationBuilder setInterpolationMethod(FieldWithMetaInterpolationMethodEnum interpolationMethod) {
			this.interpolationMethod = interpolationMethod==null?null:interpolationMethod.toBuilder();
			return this;
		}
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder setInterpolationMethodValue(InterpolationMethodEnum interpolationMethod) {
			this.getOrCreateInterpolationMethod().setValue(interpolationMethod);
			return this;
		}
		@Override
		@RosettaAttribute("initialIndexLevel")
		public InflationRateSpecification.InflationRateSpecificationBuilder setInitialIndexLevel(BigDecimal initialIndexLevel) {
			this.initialIndexLevel = initialIndexLevel==null?null:initialIndexLevel;
			return this;
		}
		@Override
		@RosettaAttribute("fallbackBondApplicable")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFallbackBondApplicable(Boolean fallbackBondApplicable) {
			this.fallbackBondApplicable = fallbackBondApplicable==null?null:fallbackBondApplicable;
			return this;
		}
		@Override
		@RosettaAttribute("calculationMethod")
		public InflationRateSpecification.InflationRateSpecificationBuilder setCalculationMethod(InflationCalculationMethodEnum calculationMethod) {
			this.calculationMethod = calculationMethod==null?null:calculationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("calculationStyle")
		public InflationRateSpecification.InflationRateSpecificationBuilder setCalculationStyle(InflationCalculationStyleEnum calculationStyle) {
			this.calculationStyle = calculationStyle==null?null:calculationStyle;
			return this;
		}
		@Override
		@RosettaAttribute("finalPrincipalExchangeCalculation")
		public InflationRateSpecification.InflationRateSpecificationBuilder setFinalPrincipalExchangeCalculation(FinalPrincipalExchangeCalculationEnum finalPrincipalExchangeCalculation) {
			this.finalPrincipalExchangeCalculation = finalPrincipalExchangeCalculation==null?null:finalPrincipalExchangeCalculation;
			return this;
		}
		
		@Override
		public InflationRateSpecification build() {
			return new InflationRateSpecification.InflationRateSpecificationImpl(this);
		}
		
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder prune() {
			super.prune();
			if (inflationLag!=null && !inflationLag.prune().hasData()) inflationLag = null;
			if (indexSource!=null && !indexSource.prune().hasData()) indexSource = null;
			if (mainPublication!=null && !mainPublication.prune().hasData()) mainPublication = null;
			if (interpolationMethod!=null && !interpolationMethod.prune().hasData()) interpolationMethod = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getInflationLag()!=null && getInflationLag().hasData()) return true;
			if (getIndexSource()!=null) return true;
			if (getMainPublication()!=null) return true;
			if (getInterpolationMethod()!=null) return true;
			if (getInitialIndexLevel()!=null) return true;
			if (getFallbackBondApplicable()!=null) return true;
			if (getCalculationMethod()!=null) return true;
			if (getCalculationStyle()!=null) return true;
			if (getFinalPrincipalExchangeCalculation()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			InflationRateSpecification.InflationRateSpecificationBuilder o = (InflationRateSpecification.InflationRateSpecificationBuilder) other;
			
			merger.mergeRosetta(getInflationLag(), o.getInflationLag(), this::setInflationLag);
			merger.mergeRosetta(getIndexSource(), o.getIndexSource(), this::setIndexSource);
			merger.mergeRosetta(getMainPublication(), o.getMainPublication(), this::setMainPublication);
			merger.mergeRosetta(getInterpolationMethod(), o.getInterpolationMethod(), this::setInterpolationMethod);
			
			merger.mergeBasic(getInitialIndexLevel(), o.getInitialIndexLevel(), this::setInitialIndexLevel);
			merger.mergeBasic(getFallbackBondApplicable(), o.getFallbackBondApplicable(), this::setFallbackBondApplicable);
			merger.mergeBasic(getCalculationMethod(), o.getCalculationMethod(), this::setCalculationMethod);
			merger.mergeBasic(getCalculationStyle(), o.getCalculationStyle(), this::setCalculationStyle);
			merger.mergeBasic(getFinalPrincipalExchangeCalculation(), o.getFinalPrincipalExchangeCalculation(), this::setFinalPrincipalExchangeCalculation);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			InflationRateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(inflationLag, _that.getInflationLag())) return false;
			if (!Objects.equals(indexSource, _that.getIndexSource())) return false;
			if (!Objects.equals(mainPublication, _that.getMainPublication())) return false;
			if (!Objects.equals(interpolationMethod, _that.getInterpolationMethod())) return false;
			if (!Objects.equals(initialIndexLevel, _that.getInitialIndexLevel())) return false;
			if (!Objects.equals(fallbackBondApplicable, _that.getFallbackBondApplicable())) return false;
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			if (!Objects.equals(calculationStyle, _that.getCalculationStyle())) return false;
			if (!Objects.equals(finalPrincipalExchangeCalculation, _that.getFinalPrincipalExchangeCalculation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (inflationLag != null ? inflationLag.hashCode() : 0);
			_result = 31 * _result + (indexSource != null ? indexSource.hashCode() : 0);
			_result = 31 * _result + (mainPublication != null ? mainPublication.hashCode() : 0);
			_result = 31 * _result + (interpolationMethod != null ? interpolationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (initialIndexLevel != null ? initialIndexLevel.hashCode() : 0);
			_result = 31 * _result + (fallbackBondApplicable != null ? fallbackBondApplicable.hashCode() : 0);
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (calculationStyle != null ? calculationStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (finalPrincipalExchangeCalculation != null ? finalPrincipalExchangeCalculation.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InflationRateSpecificationBuilder {" +
				"inflationLag=" + this.inflationLag + ", " +
				"indexSource=" + this.indexSource + ", " +
				"mainPublication=" + this.mainPublication + ", " +
				"interpolationMethod=" + this.interpolationMethod + ", " +
				"initialIndexLevel=" + this.initialIndexLevel + ", " +
				"fallbackBondApplicable=" + this.fallbackBondApplicable + ", " +
				"calculationMethod=" + this.calculationMethod + ", " +
				"calculationStyle=" + this.calculationStyle + ", " +
				"finalPrincipalExchangeCalculation=" + this.finalPrincipalExchangeCalculation +
			'}' + " " + super.toString();
		}
	}
}
