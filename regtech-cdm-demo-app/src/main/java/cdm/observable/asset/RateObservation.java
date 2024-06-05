package cdm.observable.asset;

import cdm.observable.asset.RateObservation;
import cdm.observable.asset.RateObservation.RateObservationBuilder;
import cdm.observable.asset.RateObservation.RateObservationBuilderImpl;
import cdm.observable.asset.RateObservation.RateObservationImpl;
import cdm.observable.asset.meta.RateObservationMeta;
import cdm.observable.asset.metafields.ReferenceWithMetaRateObservation;
import cdm.observable.asset.metafields.ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class defining parameters associated with an individual observation or fixing. This class forms part of the cashflow representation of a stream.
 * @version ${project.version}
 */
@RosettaDataType(value="RateObservation", builder=RateObservation.RateObservationBuilderImpl.class, version="${project.version}")
public interface RateObservation extends RosettaModelObject, GlobalKey {

	RateObservationMeta metaData = new RateObservationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The reset date.
	 */
	Date getResetDate();
	/**
	 * The adjusted fixing date, i.e. the actual date the rate is observed. The date should already be adjusted for any applicable business day convention.
	 */
	Date getAdjustedFixingDate();
	/**
	 * The actual observed rate before any required rate treatment is applied, e.g. before converting a rate quoted on a discount basis to an equivalent yield. An observed rate of 5% would be represented as 0.05.
	 */
	BigDecimal getObservedRate();
	/**
	 * The observed rate after any required rate treatment is applied. A treated rate of 5% would be represented as 0.05.
	 */
	BigDecimal getTreatedRate();
	/**
	 * The number of days weighting to be associated with the rate observation, i.e. the number of days such rate is in effect. This is applicable in the case of a weighted average method of calculation where more than one reset date is established for a single calculation period.
	 */
	Integer getObservationWeight();
	/**
	 * A pointer style reference to a floating rate component defined as part of a stub calculation period amount component. It is only required when it is necessary to distinguish two rate observations for the same fixing date which could occur when linear interpolation of two different rates occurs for a stub calculation period.
	 */
	ReferenceWithMetaRateObservation getRateReference();
	/**
	 * The value representing the forecast rate used to calculate the forecast future value of the accrual period.A value of 1% should be represented as 0.01.
	 */
	BigDecimal getForecastRate();
	/**
	 * The value representing the forecast rate after applying rate treatment rules. A value of 1% should be represented as 0.01.
	 */
	BigDecimal getTreatedForecastRate();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	RateObservation build();
	
	RateObservation.RateObservationBuilder toBuilder();
	
	static RateObservation.RateObservationBuilder builder() {
		return new RateObservation.RateObservationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RateObservation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RateObservation> getType() {
		return RateObservation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("resetDate"), Date.class, getResetDate(), this);
		processor.processBasic(path.newSubPath("adjustedFixingDate"), Date.class, getAdjustedFixingDate(), this);
		processor.processBasic(path.newSubPath("observedRate"), BigDecimal.class, getObservedRate(), this);
		processor.processBasic(path.newSubPath("treatedRate"), BigDecimal.class, getTreatedRate(), this);
		processor.processBasic(path.newSubPath("observationWeight"), Integer.class, getObservationWeight(), this);
		processRosetta(path.newSubPath("rateReference"), processor, ReferenceWithMetaRateObservation.class, getRateReference());
		processor.processBasic(path.newSubPath("forecastRate"), BigDecimal.class, getForecastRate(), this);
		processor.processBasic(path.newSubPath("treatedForecastRate"), BigDecimal.class, getTreatedForecastRate(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RateObservationBuilder extends RateObservation, RosettaModelObjectBuilder {
		ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder getOrCreateRateReference();
		ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder getRateReference();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		RateObservation.RateObservationBuilder setResetDate(Date resetDate);
		RateObservation.RateObservationBuilder setAdjustedFixingDate(Date adjustedFixingDate);
		RateObservation.RateObservationBuilder setObservedRate(BigDecimal observedRate);
		RateObservation.RateObservationBuilder setTreatedRate(BigDecimal treatedRate);
		RateObservation.RateObservationBuilder setObservationWeight(Integer observationWeight);
		RateObservation.RateObservationBuilder setRateReference(ReferenceWithMetaRateObservation rateReference0);
		RateObservation.RateObservationBuilder setRateReferenceValue(RateObservation rateReference1);
		RateObservation.RateObservationBuilder setForecastRate(BigDecimal forecastRate);
		RateObservation.RateObservationBuilder setTreatedForecastRate(BigDecimal treatedForecastRate);
		RateObservation.RateObservationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("resetDate"), Date.class, getResetDate(), this);
			processor.processBasic(path.newSubPath("adjustedFixingDate"), Date.class, getAdjustedFixingDate(), this);
			processor.processBasic(path.newSubPath("observedRate"), BigDecimal.class, getObservedRate(), this);
			processor.processBasic(path.newSubPath("treatedRate"), BigDecimal.class, getTreatedRate(), this);
			processor.processBasic(path.newSubPath("observationWeight"), Integer.class, getObservationWeight(), this);
			processRosetta(path.newSubPath("rateReference"), processor, ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder.class, getRateReference());
			processor.processBasic(path.newSubPath("forecastRate"), BigDecimal.class, getForecastRate(), this);
			processor.processBasic(path.newSubPath("treatedForecastRate"), BigDecimal.class, getTreatedForecastRate(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		RateObservation.RateObservationBuilder prune();
	}

	/*********************** Immutable Implementation of RateObservation  ***********************/
	class RateObservationImpl implements RateObservation {
		private final Date resetDate;
		private final Date adjustedFixingDate;
		private final BigDecimal observedRate;
		private final BigDecimal treatedRate;
		private final Integer observationWeight;
		private final ReferenceWithMetaRateObservation rateReference;
		private final BigDecimal forecastRate;
		private final BigDecimal treatedForecastRate;
		private final MetaFields meta;
		
		protected RateObservationImpl(RateObservation.RateObservationBuilder builder) {
			this.resetDate = builder.getResetDate();
			this.adjustedFixingDate = builder.getAdjustedFixingDate();
			this.observedRate = builder.getObservedRate();
			this.treatedRate = builder.getTreatedRate();
			this.observationWeight = builder.getObservationWeight();
			this.rateReference = ofNullable(builder.getRateReference()).map(f->f.build()).orElse(null);
			this.forecastRate = builder.getForecastRate();
			this.treatedForecastRate = builder.getTreatedForecastRate();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("resetDate")
		public Date getResetDate() {
			return resetDate;
		}
		
		@Override
		@RosettaAttribute("adjustedFixingDate")
		public Date getAdjustedFixingDate() {
			return adjustedFixingDate;
		}
		
		@Override
		@RosettaAttribute("observedRate")
		public BigDecimal getObservedRate() {
			return observedRate;
		}
		
		@Override
		@RosettaAttribute("treatedRate")
		public BigDecimal getTreatedRate() {
			return treatedRate;
		}
		
		@Override
		@RosettaAttribute("observationWeight")
		public Integer getObservationWeight() {
			return observationWeight;
		}
		
		@Override
		@RosettaAttribute("rateReference")
		public ReferenceWithMetaRateObservation getRateReference() {
			return rateReference;
		}
		
		@Override
		@RosettaAttribute("forecastRate")
		public BigDecimal getForecastRate() {
			return forecastRate;
		}
		
		@Override
		@RosettaAttribute("treatedForecastRate")
		public BigDecimal getTreatedForecastRate() {
			return treatedForecastRate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public RateObservation build() {
			return this;
		}
		
		@Override
		public RateObservation.RateObservationBuilder toBuilder() {
			RateObservation.RateObservationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RateObservation.RateObservationBuilder builder) {
			ofNullable(getResetDate()).ifPresent(builder::setResetDate);
			ofNullable(getAdjustedFixingDate()).ifPresent(builder::setAdjustedFixingDate);
			ofNullable(getObservedRate()).ifPresent(builder::setObservedRate);
			ofNullable(getTreatedRate()).ifPresent(builder::setTreatedRate);
			ofNullable(getObservationWeight()).ifPresent(builder::setObservationWeight);
			ofNullable(getRateReference()).ifPresent(builder::setRateReference);
			ofNullable(getForecastRate()).ifPresent(builder::setForecastRate);
			ofNullable(getTreatedForecastRate()).ifPresent(builder::setTreatedForecastRate);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateObservation _that = getType().cast(o);
		
			if (!Objects.equals(resetDate, _that.getResetDate())) return false;
			if (!Objects.equals(adjustedFixingDate, _that.getAdjustedFixingDate())) return false;
			if (!Objects.equals(observedRate, _that.getObservedRate())) return false;
			if (!Objects.equals(treatedRate, _that.getTreatedRate())) return false;
			if (!Objects.equals(observationWeight, _that.getObservationWeight())) return false;
			if (!Objects.equals(rateReference, _that.getRateReference())) return false;
			if (!Objects.equals(forecastRate, _that.getForecastRate())) return false;
			if (!Objects.equals(treatedForecastRate, _that.getTreatedForecastRate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resetDate != null ? resetDate.hashCode() : 0);
			_result = 31 * _result + (adjustedFixingDate != null ? adjustedFixingDate.hashCode() : 0);
			_result = 31 * _result + (observedRate != null ? observedRate.hashCode() : 0);
			_result = 31 * _result + (treatedRate != null ? treatedRate.hashCode() : 0);
			_result = 31 * _result + (observationWeight != null ? observationWeight.hashCode() : 0);
			_result = 31 * _result + (rateReference != null ? rateReference.hashCode() : 0);
			_result = 31 * _result + (forecastRate != null ? forecastRate.hashCode() : 0);
			_result = 31 * _result + (treatedForecastRate != null ? treatedForecastRate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RateObservation {" +
				"resetDate=" + this.resetDate + ", " +
				"adjustedFixingDate=" + this.adjustedFixingDate + ", " +
				"observedRate=" + this.observedRate + ", " +
				"treatedRate=" + this.treatedRate + ", " +
				"observationWeight=" + this.observationWeight + ", " +
				"rateReference=" + this.rateReference + ", " +
				"forecastRate=" + this.forecastRate + ", " +
				"treatedForecastRate=" + this.treatedForecastRate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of RateObservation  ***********************/
	class RateObservationBuilderImpl implements RateObservation.RateObservationBuilder, GlobalKeyBuilder {
	
		protected Date resetDate;
		protected Date adjustedFixingDate;
		protected BigDecimal observedRate;
		protected BigDecimal treatedRate;
		protected Integer observationWeight;
		protected ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder rateReference;
		protected BigDecimal forecastRate;
		protected BigDecimal treatedForecastRate;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public RateObservationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("resetDate")
		public Date getResetDate() {
			return resetDate;
		}
		
		@Override
		@RosettaAttribute("adjustedFixingDate")
		public Date getAdjustedFixingDate() {
			return adjustedFixingDate;
		}
		
		@Override
		@RosettaAttribute("observedRate")
		public BigDecimal getObservedRate() {
			return observedRate;
		}
		
		@Override
		@RosettaAttribute("treatedRate")
		public BigDecimal getTreatedRate() {
			return treatedRate;
		}
		
		@Override
		@RosettaAttribute("observationWeight")
		public Integer getObservationWeight() {
			return observationWeight;
		}
		
		@Override
		@RosettaAttribute("rateReference")
		public ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder getRateReference() {
			return rateReference;
		}
		
		@Override
		public ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder getOrCreateRateReference() {
			ReferenceWithMetaRateObservation.ReferenceWithMetaRateObservationBuilder result;
			if (rateReference!=null) {
				result = rateReference;
			}
			else {
				result = rateReference = ReferenceWithMetaRateObservation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("forecastRate")
		public BigDecimal getForecastRate() {
			return forecastRate;
		}
		
		@Override
		@RosettaAttribute("treatedForecastRate")
		public BigDecimal getTreatedForecastRate() {
			return treatedForecastRate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("resetDate")
		public RateObservation.RateObservationBuilder setResetDate(Date resetDate) {
			this.resetDate = resetDate==null?null:resetDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedFixingDate")
		public RateObservation.RateObservationBuilder setAdjustedFixingDate(Date adjustedFixingDate) {
			this.adjustedFixingDate = adjustedFixingDate==null?null:adjustedFixingDate;
			return this;
		}
		@Override
		@RosettaAttribute("observedRate")
		public RateObservation.RateObservationBuilder setObservedRate(BigDecimal observedRate) {
			this.observedRate = observedRate==null?null:observedRate;
			return this;
		}
		@Override
		@RosettaAttribute("treatedRate")
		public RateObservation.RateObservationBuilder setTreatedRate(BigDecimal treatedRate) {
			this.treatedRate = treatedRate==null?null:treatedRate;
			return this;
		}
		@Override
		@RosettaAttribute("observationWeight")
		public RateObservation.RateObservationBuilder setObservationWeight(Integer observationWeight) {
			this.observationWeight = observationWeight==null?null:observationWeight;
			return this;
		}
		@Override
		@RosettaAttribute("rateReference")
		public RateObservation.RateObservationBuilder setRateReference(ReferenceWithMetaRateObservation rateReference) {
			this.rateReference = rateReference==null?null:rateReference.toBuilder();
			return this;
		}
		@Override
		public RateObservation.RateObservationBuilder setRateReferenceValue(RateObservation rateReference) {
			this.getOrCreateRateReference().setValue(rateReference);
			return this;
		}
		@Override
		@RosettaAttribute("forecastRate")
		public RateObservation.RateObservationBuilder setForecastRate(BigDecimal forecastRate) {
			this.forecastRate = forecastRate==null?null:forecastRate;
			return this;
		}
		@Override
		@RosettaAttribute("treatedForecastRate")
		public RateObservation.RateObservationBuilder setTreatedForecastRate(BigDecimal treatedForecastRate) {
			this.treatedForecastRate = treatedForecastRate==null?null:treatedForecastRate;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public RateObservation.RateObservationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public RateObservation build() {
			return new RateObservation.RateObservationImpl(this);
		}
		
		@Override
		public RateObservation.RateObservationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RateObservation.RateObservationBuilder prune() {
			if (rateReference!=null && !rateReference.prune().hasData()) rateReference = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getResetDate()!=null) return true;
			if (getAdjustedFixingDate()!=null) return true;
			if (getObservedRate()!=null) return true;
			if (getTreatedRate()!=null) return true;
			if (getObservationWeight()!=null) return true;
			if (getRateReference()!=null && getRateReference().hasData()) return true;
			if (getForecastRate()!=null) return true;
			if (getTreatedForecastRate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RateObservation.RateObservationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RateObservation.RateObservationBuilder o = (RateObservation.RateObservationBuilder) other;
			
			merger.mergeRosetta(getRateReference(), o.getRateReference(), this::setRateReference);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getResetDate(), o.getResetDate(), this::setResetDate);
			merger.mergeBasic(getAdjustedFixingDate(), o.getAdjustedFixingDate(), this::setAdjustedFixingDate);
			merger.mergeBasic(getObservedRate(), o.getObservedRate(), this::setObservedRate);
			merger.mergeBasic(getTreatedRate(), o.getTreatedRate(), this::setTreatedRate);
			merger.mergeBasic(getObservationWeight(), o.getObservationWeight(), this::setObservationWeight);
			merger.mergeBasic(getForecastRate(), o.getForecastRate(), this::setForecastRate);
			merger.mergeBasic(getTreatedForecastRate(), o.getTreatedForecastRate(), this::setTreatedForecastRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateObservation _that = getType().cast(o);
		
			if (!Objects.equals(resetDate, _that.getResetDate())) return false;
			if (!Objects.equals(adjustedFixingDate, _that.getAdjustedFixingDate())) return false;
			if (!Objects.equals(observedRate, _that.getObservedRate())) return false;
			if (!Objects.equals(treatedRate, _that.getTreatedRate())) return false;
			if (!Objects.equals(observationWeight, _that.getObservationWeight())) return false;
			if (!Objects.equals(rateReference, _that.getRateReference())) return false;
			if (!Objects.equals(forecastRate, _that.getForecastRate())) return false;
			if (!Objects.equals(treatedForecastRate, _that.getTreatedForecastRate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resetDate != null ? resetDate.hashCode() : 0);
			_result = 31 * _result + (adjustedFixingDate != null ? adjustedFixingDate.hashCode() : 0);
			_result = 31 * _result + (observedRate != null ? observedRate.hashCode() : 0);
			_result = 31 * _result + (treatedRate != null ? treatedRate.hashCode() : 0);
			_result = 31 * _result + (observationWeight != null ? observationWeight.hashCode() : 0);
			_result = 31 * _result + (rateReference != null ? rateReference.hashCode() : 0);
			_result = 31 * _result + (forecastRate != null ? forecastRate.hashCode() : 0);
			_result = 31 * _result + (treatedForecastRate != null ? treatedForecastRate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RateObservationBuilder {" +
				"resetDate=" + this.resetDate + ", " +
				"adjustedFixingDate=" + this.adjustedFixingDate + ", " +
				"observedRate=" + this.observedRate + ", " +
				"treatedRate=" + this.treatedRate + ", " +
				"observationWeight=" + this.observationWeight + ", " +
				"rateReference=" + this.rateReference + ", " +
				"forecastRate=" + this.forecastRate + ", " +
				"treatedForecastRate=" + this.treatedForecastRate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
