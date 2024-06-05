package cdm.product.common.schedule;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.math.Rounding;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.Observable;
import cdm.observable.common.TimeTypeEnum;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.schedule.ObservationTerms.ObservationTermsBuilder;
import cdm.product.common.schedule.ObservationTerms.ObservationTermsBuilderImpl;
import cdm.product.common.schedule.ObservationTerms.ObservationTermsImpl;
import cdm.product.common.schedule.meta.ObservationTermsMeta;
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
 * Class containing terms that are associated with observing a price/benchmark/index across either single or multiple observations. 
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationTerms", builder=ObservationTerms.ObservationTermsBuilderImpl.class, version="${project.version}")
public interface ObservationTerms extends RosettaModelObject {

	ObservationTermsMeta metaData = new ObservationTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines time in respect to a business calendar location that the price/benchmark/index is observed
	 */
	BusinessCenterTime getPricingTime();
	/**
	 * The enumerated values to specify points in the day when option exercise and valuation can occur.
	 */
	TimeTypeEnum getPricingTimeType();
	/**
	 * The information source where a published or displayed market rate will be obtained, e.g. Telerate Page 3750.
	 */
	FxSpotRateSource getInformationSource();
	/**
	 * Defines rounding rules and precision to be used in the rounding of observations.
	 */
	Rounding getPrecision();
	/**
	 * Defines parameters used to generate the calculation period dates schedule, including the specification of any initial or final stub calculation periods. A calculation period schedule consists of an optional initial stub calculation period, one or more regular calculation periods and an optional final stub calculation period. In the absence of any initial or final stub calculation periods, the regular part of the calculation period schedule is assumed to be between the effective date and the termination date. No implicit stubs are allowed, i.e. stubs must be explicitly specified using an appropriate combination of firstPeriodStartDate, firstRegularPeriodStartDate and lastRegularPeriodEndDate.
	 */
	CalculationPeriodDates getCalculationPeriodDates();
	/**
	 * Specifies the object to be observed for a price, it could be an asset or a reference.
	 */
	Observable getObservable();
	/**
	 * Describes date details for a set of observation dates in parametric or non-parametric form.
	 */
	ObservationDates getObservationDates();
	/**
	 * The number of observation dates between observation start date and observation end date.
	 */
	Integer getNumberOfObservationDates();

	/*********************** Build Methods  ***********************/
	ObservationTerms build();
	
	ObservationTerms.ObservationTermsBuilder toBuilder();
	
	static ObservationTerms.ObservationTermsBuilder builder() {
		return new ObservationTerms.ObservationTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationTerms> getType() {
		return ObservationTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("pricingTime"), processor, BusinessCenterTime.class, getPricingTime());
		processor.processBasic(path.newSubPath("pricingTimeType"), TimeTypeEnum.class, getPricingTimeType(), this);
		processRosetta(path.newSubPath("informationSource"), processor, FxSpotRateSource.class, getInformationSource());
		processRosetta(path.newSubPath("precision"), processor, Rounding.class, getPrecision());
		processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.class, getCalculationPeriodDates());
		processRosetta(path.newSubPath("observable"), processor, Observable.class, getObservable());
		processRosetta(path.newSubPath("observationDates"), processor, ObservationDates.class, getObservationDates());
		processor.processBasic(path.newSubPath("numberOfObservationDates"), Integer.class, getNumberOfObservationDates(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationTermsBuilder extends ObservationTerms, RosettaModelObjectBuilder {
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreatePricingTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getPricingTime();
		FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateInformationSource();
		FxSpotRateSource.FxSpotRateSourceBuilder getInformationSource();
		Rounding.RoundingBuilder getOrCreatePrecision();
		Rounding.RoundingBuilder getPrecision();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates();
		CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates();
		Observable.ObservableBuilder getOrCreateObservable();
		Observable.ObservableBuilder getObservable();
		ObservationDates.ObservationDatesBuilder getOrCreateObservationDates();
		ObservationDates.ObservationDatesBuilder getObservationDates();
		ObservationTerms.ObservationTermsBuilder setPricingTime(BusinessCenterTime pricingTime);
		ObservationTerms.ObservationTermsBuilder setPricingTimeType(TimeTypeEnum pricingTimeType);
		ObservationTerms.ObservationTermsBuilder setInformationSource(FxSpotRateSource informationSource);
		ObservationTerms.ObservationTermsBuilder setPrecision(Rounding precision);
		ObservationTerms.ObservationTermsBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates);
		ObservationTerms.ObservationTermsBuilder setObservable(Observable observable);
		ObservationTerms.ObservationTermsBuilder setObservationDates(ObservationDates observationDates);
		ObservationTerms.ObservationTermsBuilder setNumberOfObservationDates(Integer numberOfObservationDates);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("pricingTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getPricingTime());
			processor.processBasic(path.newSubPath("pricingTimeType"), TimeTypeEnum.class, getPricingTimeType(), this);
			processRosetta(path.newSubPath("informationSource"), processor, FxSpotRateSource.FxSpotRateSourceBuilder.class, getInformationSource());
			processRosetta(path.newSubPath("precision"), processor, Rounding.RoundingBuilder.class, getPrecision());
			processRosetta(path.newSubPath("calculationPeriodDates"), processor, CalculationPeriodDates.CalculationPeriodDatesBuilder.class, getCalculationPeriodDates());
			processRosetta(path.newSubPath("observable"), processor, Observable.ObservableBuilder.class, getObservable());
			processRosetta(path.newSubPath("observationDates"), processor, ObservationDates.ObservationDatesBuilder.class, getObservationDates());
			processor.processBasic(path.newSubPath("numberOfObservationDates"), Integer.class, getNumberOfObservationDates(), this);
		}
		

		ObservationTerms.ObservationTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationTerms  ***********************/
	class ObservationTermsImpl implements ObservationTerms {
		private final BusinessCenterTime pricingTime;
		private final TimeTypeEnum pricingTimeType;
		private final FxSpotRateSource informationSource;
		private final Rounding precision;
		private final CalculationPeriodDates calculationPeriodDates;
		private final Observable observable;
		private final ObservationDates observationDates;
		private final Integer numberOfObservationDates;
		
		protected ObservationTermsImpl(ObservationTerms.ObservationTermsBuilder builder) {
			this.pricingTime = ofNullable(builder.getPricingTime()).map(f->f.build()).orElse(null);
			this.pricingTimeType = builder.getPricingTimeType();
			this.informationSource = ofNullable(builder.getInformationSource()).map(f->f.build()).orElse(null);
			this.precision = ofNullable(builder.getPrecision()).map(f->f.build()).orElse(null);
			this.calculationPeriodDates = ofNullable(builder.getCalculationPeriodDates()).map(f->f.build()).orElse(null);
			this.observable = ofNullable(builder.getObservable()).map(f->f.build()).orElse(null);
			this.observationDates = ofNullable(builder.getObservationDates()).map(f->f.build()).orElse(null);
			this.numberOfObservationDates = builder.getNumberOfObservationDates();
		}
		
		@Override
		@RosettaAttribute("pricingTime")
		public BusinessCenterTime getPricingTime() {
			return pricingTime;
		}
		
		@Override
		@RosettaAttribute("pricingTimeType")
		public TimeTypeEnum getPricingTimeType() {
			return pricingTimeType;
		}
		
		@Override
		@RosettaAttribute("informationSource")
		public FxSpotRateSource getInformationSource() {
			return informationSource;
		}
		
		@Override
		@RosettaAttribute("precision")
		public Rounding getPrecision() {
			return precision;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates getCalculationPeriodDates() {
			return calculationPeriodDates;
		}
		
		@Override
		@RosettaAttribute("observable")
		public Observable getObservable() {
			return observable;
		}
		
		@Override
		@RosettaAttribute("observationDates")
		public ObservationDates getObservationDates() {
			return observationDates;
		}
		
		@Override
		@RosettaAttribute("numberOfObservationDates")
		public Integer getNumberOfObservationDates() {
			return numberOfObservationDates;
		}
		
		@Override
		public ObservationTerms build() {
			return this;
		}
		
		@Override
		public ObservationTerms.ObservationTermsBuilder toBuilder() {
			ObservationTerms.ObservationTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationTerms.ObservationTermsBuilder builder) {
			ofNullable(getPricingTime()).ifPresent(builder::setPricingTime);
			ofNullable(getPricingTimeType()).ifPresent(builder::setPricingTimeType);
			ofNullable(getInformationSource()).ifPresent(builder::setInformationSource);
			ofNullable(getPrecision()).ifPresent(builder::setPrecision);
			ofNullable(getCalculationPeriodDates()).ifPresent(builder::setCalculationPeriodDates);
			ofNullable(getObservable()).ifPresent(builder::setObservable);
			ofNullable(getObservationDates()).ifPresent(builder::setObservationDates);
			ofNullable(getNumberOfObservationDates()).ifPresent(builder::setNumberOfObservationDates);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationTerms _that = getType().cast(o);
		
			if (!Objects.equals(pricingTime, _that.getPricingTime())) return false;
			if (!Objects.equals(pricingTimeType, _that.getPricingTimeType())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			if (!Objects.equals(precision, _that.getPrecision())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(observationDates, _that.getObservationDates())) return false;
			if (!Objects.equals(numberOfObservationDates, _that.getNumberOfObservationDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pricingTime != null ? pricingTime.hashCode() : 0);
			_result = 31 * _result + (pricingTimeType != null ? pricingTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			_result = 31 * _result + (precision != null ? precision.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (numberOfObservationDates != null ? numberOfObservationDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationTerms {" +
				"pricingTime=" + this.pricingTime + ", " +
				"pricingTimeType=" + this.pricingTimeType + ", " +
				"informationSource=" + this.informationSource + ", " +
				"precision=" + this.precision + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"observable=" + this.observable + ", " +
				"observationDates=" + this.observationDates + ", " +
				"numberOfObservationDates=" + this.numberOfObservationDates +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationTerms  ***********************/
	class ObservationTermsBuilderImpl implements ObservationTerms.ObservationTermsBuilder {
	
		protected BusinessCenterTime.BusinessCenterTimeBuilder pricingTime;
		protected TimeTypeEnum pricingTimeType;
		protected FxSpotRateSource.FxSpotRateSourceBuilder informationSource;
		protected Rounding.RoundingBuilder precision;
		protected CalculationPeriodDates.CalculationPeriodDatesBuilder calculationPeriodDates;
		protected Observable.ObservableBuilder observable;
		protected ObservationDates.ObservationDatesBuilder observationDates;
		protected Integer numberOfObservationDates;
	
		public ObservationTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("pricingTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getPricingTime() {
			return pricingTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreatePricingTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (pricingTime!=null) {
				result = pricingTime;
			}
			else {
				result = pricingTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("pricingTimeType")
		public TimeTypeEnum getPricingTimeType() {
			return pricingTimeType;
		}
		
		@Override
		@RosettaAttribute("informationSource")
		public FxSpotRateSource.FxSpotRateSourceBuilder getInformationSource() {
			return informationSource;
		}
		
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateInformationSource() {
			FxSpotRateSource.FxSpotRateSourceBuilder result;
			if (informationSource!=null) {
				result = informationSource;
			}
			else {
				result = informationSource = FxSpotRateSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("precision")
		public Rounding.RoundingBuilder getPrecision() {
			return precision;
		}
		
		@Override
		public Rounding.RoundingBuilder getOrCreatePrecision() {
			Rounding.RoundingBuilder result;
			if (precision!=null) {
				result = precision;
			}
			else {
				result = precision = Rounding.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getCalculationPeriodDates() {
			return calculationPeriodDates;
		}
		
		@Override
		public CalculationPeriodDates.CalculationPeriodDatesBuilder getOrCreateCalculationPeriodDates() {
			CalculationPeriodDates.CalculationPeriodDatesBuilder result;
			if (calculationPeriodDates!=null) {
				result = calculationPeriodDates;
			}
			else {
				result = calculationPeriodDates = CalculationPeriodDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observable")
		public Observable.ObservableBuilder getObservable() {
			return observable;
		}
		
		@Override
		public Observable.ObservableBuilder getOrCreateObservable() {
			Observable.ObservableBuilder result;
			if (observable!=null) {
				result = observable;
			}
			else {
				result = observable = Observable.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationDates")
		public ObservationDates.ObservationDatesBuilder getObservationDates() {
			return observationDates;
		}
		
		@Override
		public ObservationDates.ObservationDatesBuilder getOrCreateObservationDates() {
			ObservationDates.ObservationDatesBuilder result;
			if (observationDates!=null) {
				result = observationDates;
			}
			else {
				result = observationDates = ObservationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("numberOfObservationDates")
		public Integer getNumberOfObservationDates() {
			return numberOfObservationDates;
		}
		
	
		@Override
		@RosettaAttribute("pricingTime")
		public ObservationTerms.ObservationTermsBuilder setPricingTime(BusinessCenterTime pricingTime) {
			this.pricingTime = pricingTime==null?null:pricingTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("pricingTimeType")
		public ObservationTerms.ObservationTermsBuilder setPricingTimeType(TimeTypeEnum pricingTimeType) {
			this.pricingTimeType = pricingTimeType==null?null:pricingTimeType;
			return this;
		}
		@Override
		@RosettaAttribute("informationSource")
		public ObservationTerms.ObservationTermsBuilder setInformationSource(FxSpotRateSource informationSource) {
			this.informationSource = informationSource==null?null:informationSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("precision")
		public ObservationTerms.ObservationTermsBuilder setPrecision(Rounding precision) {
			this.precision = precision==null?null:precision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodDates")
		public ObservationTerms.ObservationTermsBuilder setCalculationPeriodDates(CalculationPeriodDates calculationPeriodDates) {
			this.calculationPeriodDates = calculationPeriodDates==null?null:calculationPeriodDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observable")
		public ObservationTerms.ObservationTermsBuilder setObservable(Observable observable) {
			this.observable = observable==null?null:observable.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationDates")
		public ObservationTerms.ObservationTermsBuilder setObservationDates(ObservationDates observationDates) {
			this.observationDates = observationDates==null?null:observationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("numberOfObservationDates")
		public ObservationTerms.ObservationTermsBuilder setNumberOfObservationDates(Integer numberOfObservationDates) {
			this.numberOfObservationDates = numberOfObservationDates==null?null:numberOfObservationDates;
			return this;
		}
		
		@Override
		public ObservationTerms build() {
			return new ObservationTerms.ObservationTermsImpl(this);
		}
		
		@Override
		public ObservationTerms.ObservationTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationTerms.ObservationTermsBuilder prune() {
			if (pricingTime!=null && !pricingTime.prune().hasData()) pricingTime = null;
			if (informationSource!=null && !informationSource.prune().hasData()) informationSource = null;
			if (precision!=null && !precision.prune().hasData()) precision = null;
			if (calculationPeriodDates!=null && !calculationPeriodDates.prune().hasData()) calculationPeriodDates = null;
			if (observable!=null && !observable.prune().hasData()) observable = null;
			if (observationDates!=null && !observationDates.prune().hasData()) observationDates = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPricingTime()!=null && getPricingTime().hasData()) return true;
			if (getPricingTimeType()!=null) return true;
			if (getInformationSource()!=null && getInformationSource().hasData()) return true;
			if (getPrecision()!=null && getPrecision().hasData()) return true;
			if (getCalculationPeriodDates()!=null && getCalculationPeriodDates().hasData()) return true;
			if (getObservable()!=null && getObservable().hasData()) return true;
			if (getObservationDates()!=null && getObservationDates().hasData()) return true;
			if (getNumberOfObservationDates()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationTerms.ObservationTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationTerms.ObservationTermsBuilder o = (ObservationTerms.ObservationTermsBuilder) other;
			
			merger.mergeRosetta(getPricingTime(), o.getPricingTime(), this::setPricingTime);
			merger.mergeRosetta(getInformationSource(), o.getInformationSource(), this::setInformationSource);
			merger.mergeRosetta(getPrecision(), o.getPrecision(), this::setPrecision);
			merger.mergeRosetta(getCalculationPeriodDates(), o.getCalculationPeriodDates(), this::setCalculationPeriodDates);
			merger.mergeRosetta(getObservable(), o.getObservable(), this::setObservable);
			merger.mergeRosetta(getObservationDates(), o.getObservationDates(), this::setObservationDates);
			
			merger.mergeBasic(getPricingTimeType(), o.getPricingTimeType(), this::setPricingTimeType);
			merger.mergeBasic(getNumberOfObservationDates(), o.getNumberOfObservationDates(), this::setNumberOfObservationDates);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationTerms _that = getType().cast(o);
		
			if (!Objects.equals(pricingTime, _that.getPricingTime())) return false;
			if (!Objects.equals(pricingTimeType, _that.getPricingTimeType())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			if (!Objects.equals(precision, _that.getPrecision())) return false;
			if (!Objects.equals(calculationPeriodDates, _that.getCalculationPeriodDates())) return false;
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(observationDates, _that.getObservationDates())) return false;
			if (!Objects.equals(numberOfObservationDates, _that.getNumberOfObservationDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pricingTime != null ? pricingTime.hashCode() : 0);
			_result = 31 * _result + (pricingTimeType != null ? pricingTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			_result = 31 * _result + (precision != null ? precision.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodDates != null ? calculationPeriodDates.hashCode() : 0);
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (numberOfObservationDates != null ? numberOfObservationDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationTermsBuilder {" +
				"pricingTime=" + this.pricingTime + ", " +
				"pricingTimeType=" + this.pricingTimeType + ", " +
				"informationSource=" + this.informationSource + ", " +
				"precision=" + this.precision + ", " +
				"calculationPeriodDates=" + this.calculationPeriodDates + ", " +
				"observable=" + this.observable + ", " +
				"observationDates=" + this.observationDates + ", " +
				"numberOfObservationDates=" + this.numberOfObservationDates +
			'}';
		}
	}
}
