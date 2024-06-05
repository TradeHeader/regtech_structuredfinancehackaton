package cdm.product.common.schedule;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.FxSpotRateSource;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.schedule.FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder;
import cdm.product.common.schedule.FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilderImpl;
import cdm.product.common.schedule.FxLinkedNotionalSchedule.FxLinkedNotionalScheduleImpl;
import cdm.product.common.schedule.meta.FxLinkedNotionalScheduleMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data to:  describe a notional schedule where each notional that applies to a calculation period is calculated with reference to a notional amount or notional amount schedule in a different currency by means of a spot currency exchange rate which is normally observed at the beginning of each period.
 * @version ${project.version}
 */
@RosettaDataType(value="FxLinkedNotionalSchedule", builder=FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilderImpl.class, version="${project.version}")
public interface FxLinkedNotionalSchedule extends RosettaModelObject {

	FxLinkedNotionalScheduleMeta metaData = new FxLinkedNotionalScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The currency of the varying notional amount, i.e. the notional amount being determined periodically based on observation of a spot currency exchange rate. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getVaryingNotionalCurrency();
	/**
	 * The dates on which spot currency exchange rates are observed for purposes of determining the varying notional currency amount that will apply to a calculation period.
	 */
	RelativeDateOffset getVaryingNotionalFixingDates();
	/**
	 * The information source and time at which the spot currency exchange rate will be observed.
	 */
	FxSpotRateSource getFxSpotRateSource();
	/**
	 * The time at which the spot currency exchange rate will be observed. It is specified as a time in a business day calendar location, e.g. 11:00am London time.
	 */
	BusinessCenterTime getFixingTime();
	/**
	 * The dates on which interim exchanges of notional are paid. Interim exchanges will arise as a result of changes in the spot currency exchange amount or changes in the constant notional schedule (e.g. amortisation).
	 */
	RelativeDateOffset getVaryingNotionalInterimExchangePaymentDates();

	/*********************** Build Methods  ***********************/
	FxLinkedNotionalSchedule build();
	
	FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder toBuilder();
	
	static FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder builder() {
		return new FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxLinkedNotionalSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxLinkedNotionalSchedule> getType() {
		return FxLinkedNotionalSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("varyingNotionalCurrency"), processor, FieldWithMetaString.class, getVaryingNotionalCurrency());
		processRosetta(path.newSubPath("varyingNotionalFixingDates"), processor, RelativeDateOffset.class, getVaryingNotionalFixingDates());
		processRosetta(path.newSubPath("fxSpotRateSource"), processor, FxSpotRateSource.class, getFxSpotRateSource());
		processRosetta(path.newSubPath("fixingTime"), processor, BusinessCenterTime.class, getFixingTime());
		processRosetta(path.newSubPath("varyingNotionalInterimExchangePaymentDates"), processor, RelativeDateOffset.class, getVaryingNotionalInterimExchangePaymentDates());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxLinkedNotionalScheduleBuilder extends FxLinkedNotionalSchedule, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateVaryingNotionalCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getVaryingNotionalCurrency();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateVaryingNotionalFixingDates();
		RelativeDateOffset.RelativeDateOffsetBuilder getVaryingNotionalFixingDates();
		FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateFxSpotRateSource();
		FxSpotRateSource.FxSpotRateSourceBuilder getFxSpotRateSource();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateFixingTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getFixingTime();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateVaryingNotionalInterimExchangePaymentDates();
		RelativeDateOffset.RelativeDateOffsetBuilder getVaryingNotionalInterimExchangePaymentDates();
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalCurrency(FieldWithMetaString varyingNotionalCurrency0);
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalCurrencyValue(String varyingNotionalCurrency1);
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalFixingDates(RelativeDateOffset varyingNotionalFixingDates);
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setFxSpotRateSource(FxSpotRateSource fxSpotRateSource);
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setFixingTime(BusinessCenterTime fixingTime);
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalInterimExchangePaymentDates(RelativeDateOffset varyingNotionalInterimExchangePaymentDates);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("varyingNotionalCurrency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getVaryingNotionalCurrency());
			processRosetta(path.newSubPath("varyingNotionalFixingDates"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getVaryingNotionalFixingDates());
			processRosetta(path.newSubPath("fxSpotRateSource"), processor, FxSpotRateSource.FxSpotRateSourceBuilder.class, getFxSpotRateSource());
			processRosetta(path.newSubPath("fixingTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getFixingTime());
			processRosetta(path.newSubPath("varyingNotionalInterimExchangePaymentDates"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getVaryingNotionalInterimExchangePaymentDates());
		}
		

		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of FxLinkedNotionalSchedule  ***********************/
	class FxLinkedNotionalScheduleImpl implements FxLinkedNotionalSchedule {
		private final FieldWithMetaString varyingNotionalCurrency;
		private final RelativeDateOffset varyingNotionalFixingDates;
		private final FxSpotRateSource fxSpotRateSource;
		private final BusinessCenterTime fixingTime;
		private final RelativeDateOffset varyingNotionalInterimExchangePaymentDates;
		
		protected FxLinkedNotionalScheduleImpl(FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder builder) {
			this.varyingNotionalCurrency = ofNullable(builder.getVaryingNotionalCurrency()).map(f->f.build()).orElse(null);
			this.varyingNotionalFixingDates = ofNullable(builder.getVaryingNotionalFixingDates()).map(f->f.build()).orElse(null);
			this.fxSpotRateSource = ofNullable(builder.getFxSpotRateSource()).map(f->f.build()).orElse(null);
			this.fixingTime = ofNullable(builder.getFixingTime()).map(f->f.build()).orElse(null);
			this.varyingNotionalInterimExchangePaymentDates = ofNullable(builder.getVaryingNotionalInterimExchangePaymentDates()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("varyingNotionalCurrency")
		public FieldWithMetaString getVaryingNotionalCurrency() {
			return varyingNotionalCurrency;
		}
		
		@Override
		@RosettaAttribute("varyingNotionalFixingDates")
		public RelativeDateOffset getVaryingNotionalFixingDates() {
			return varyingNotionalFixingDates;
		}
		
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public FxSpotRateSource getFxSpotRateSource() {
			return fxSpotRateSource;
		}
		
		@Override
		@RosettaAttribute("fixingTime")
		public BusinessCenterTime getFixingTime() {
			return fixingTime;
		}
		
		@Override
		@RosettaAttribute("varyingNotionalInterimExchangePaymentDates")
		public RelativeDateOffset getVaryingNotionalInterimExchangePaymentDates() {
			return varyingNotionalInterimExchangePaymentDates;
		}
		
		@Override
		public FxLinkedNotionalSchedule build() {
			return this;
		}
		
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder toBuilder() {
			FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder builder) {
			ofNullable(getVaryingNotionalCurrency()).ifPresent(builder::setVaryingNotionalCurrency);
			ofNullable(getVaryingNotionalFixingDates()).ifPresent(builder::setVaryingNotionalFixingDates);
			ofNullable(getFxSpotRateSource()).ifPresent(builder::setFxSpotRateSource);
			ofNullable(getFixingTime()).ifPresent(builder::setFixingTime);
			ofNullable(getVaryingNotionalInterimExchangePaymentDates()).ifPresent(builder::setVaryingNotionalInterimExchangePaymentDates);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxLinkedNotionalSchedule _that = getType().cast(o);
		
			if (!Objects.equals(varyingNotionalCurrency, _that.getVaryingNotionalCurrency())) return false;
			if (!Objects.equals(varyingNotionalFixingDates, _that.getVaryingNotionalFixingDates())) return false;
			if (!Objects.equals(fxSpotRateSource, _that.getFxSpotRateSource())) return false;
			if (!Objects.equals(fixingTime, _that.getFixingTime())) return false;
			if (!Objects.equals(varyingNotionalInterimExchangePaymentDates, _that.getVaryingNotionalInterimExchangePaymentDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (varyingNotionalCurrency != null ? varyingNotionalCurrency.hashCode() : 0);
			_result = 31 * _result + (varyingNotionalFixingDates != null ? varyingNotionalFixingDates.hashCode() : 0);
			_result = 31 * _result + (fxSpotRateSource != null ? fxSpotRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingTime != null ? fixingTime.hashCode() : 0);
			_result = 31 * _result + (varyingNotionalInterimExchangePaymentDates != null ? varyingNotionalInterimExchangePaymentDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxLinkedNotionalSchedule {" +
				"varyingNotionalCurrency=" + this.varyingNotionalCurrency + ", " +
				"varyingNotionalFixingDates=" + this.varyingNotionalFixingDates + ", " +
				"fxSpotRateSource=" + this.fxSpotRateSource + ", " +
				"fixingTime=" + this.fixingTime + ", " +
				"varyingNotionalInterimExchangePaymentDates=" + this.varyingNotionalInterimExchangePaymentDates +
			'}';
		}
	}

	/*********************** Builder Implementation of FxLinkedNotionalSchedule  ***********************/
	class FxLinkedNotionalScheduleBuilderImpl implements FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder varyingNotionalCurrency;
		protected RelativeDateOffset.RelativeDateOffsetBuilder varyingNotionalFixingDates;
		protected FxSpotRateSource.FxSpotRateSourceBuilder fxSpotRateSource;
		protected BusinessCenterTime.BusinessCenterTimeBuilder fixingTime;
		protected RelativeDateOffset.RelativeDateOffsetBuilder varyingNotionalInterimExchangePaymentDates;
	
		public FxLinkedNotionalScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("varyingNotionalCurrency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getVaryingNotionalCurrency() {
			return varyingNotionalCurrency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateVaryingNotionalCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (varyingNotionalCurrency!=null) {
				result = varyingNotionalCurrency;
			}
			else {
				result = varyingNotionalCurrency = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("varyingNotionalFixingDates")
		public RelativeDateOffset.RelativeDateOffsetBuilder getVaryingNotionalFixingDates() {
			return varyingNotionalFixingDates;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateVaryingNotionalFixingDates() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (varyingNotionalFixingDates!=null) {
				result = varyingNotionalFixingDates;
			}
			else {
				result = varyingNotionalFixingDates = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public FxSpotRateSource.FxSpotRateSourceBuilder getFxSpotRateSource() {
			return fxSpotRateSource;
		}
		
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateFxSpotRateSource() {
			FxSpotRateSource.FxSpotRateSourceBuilder result;
			if (fxSpotRateSource!=null) {
				result = fxSpotRateSource;
			}
			else {
				result = fxSpotRateSource = FxSpotRateSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixingTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getFixingTime() {
			return fixingTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateFixingTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (fixingTime!=null) {
				result = fixingTime;
			}
			else {
				result = fixingTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("varyingNotionalInterimExchangePaymentDates")
		public RelativeDateOffset.RelativeDateOffsetBuilder getVaryingNotionalInterimExchangePaymentDates() {
			return varyingNotionalInterimExchangePaymentDates;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateVaryingNotionalInterimExchangePaymentDates() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (varyingNotionalInterimExchangePaymentDates!=null) {
				result = varyingNotionalInterimExchangePaymentDates;
			}
			else {
				result = varyingNotionalInterimExchangePaymentDates = RelativeDateOffset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("varyingNotionalCurrency")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalCurrency(FieldWithMetaString varyingNotionalCurrency) {
			this.varyingNotionalCurrency = varyingNotionalCurrency==null?null:varyingNotionalCurrency.toBuilder();
			return this;
		}
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalCurrencyValue(String varyingNotionalCurrency) {
			this.getOrCreateVaryingNotionalCurrency().setValue(varyingNotionalCurrency);
			return this;
		}
		@Override
		@RosettaAttribute("varyingNotionalFixingDates")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalFixingDates(RelativeDateOffset varyingNotionalFixingDates) {
			this.varyingNotionalFixingDates = varyingNotionalFixingDates==null?null:varyingNotionalFixingDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setFxSpotRateSource(FxSpotRateSource fxSpotRateSource) {
			this.fxSpotRateSource = fxSpotRateSource==null?null:fxSpotRateSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixingTime")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setFixingTime(BusinessCenterTime fixingTime) {
			this.fixingTime = fixingTime==null?null:fixingTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("varyingNotionalInterimExchangePaymentDates")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder setVaryingNotionalInterimExchangePaymentDates(RelativeDateOffset varyingNotionalInterimExchangePaymentDates) {
			this.varyingNotionalInterimExchangePaymentDates = varyingNotionalInterimExchangePaymentDates==null?null:varyingNotionalInterimExchangePaymentDates.toBuilder();
			return this;
		}
		
		@Override
		public FxLinkedNotionalSchedule build() {
			return new FxLinkedNotionalSchedule.FxLinkedNotionalScheduleImpl(this);
		}
		
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder prune() {
			if (varyingNotionalCurrency!=null && !varyingNotionalCurrency.prune().hasData()) varyingNotionalCurrency = null;
			if (varyingNotionalFixingDates!=null && !varyingNotionalFixingDates.prune().hasData()) varyingNotionalFixingDates = null;
			if (fxSpotRateSource!=null && !fxSpotRateSource.prune().hasData()) fxSpotRateSource = null;
			if (fixingTime!=null && !fixingTime.prune().hasData()) fixingTime = null;
			if (varyingNotionalInterimExchangePaymentDates!=null && !varyingNotionalInterimExchangePaymentDates.prune().hasData()) varyingNotionalInterimExchangePaymentDates = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getVaryingNotionalCurrency()!=null) return true;
			if (getVaryingNotionalFixingDates()!=null && getVaryingNotionalFixingDates().hasData()) return true;
			if (getFxSpotRateSource()!=null && getFxSpotRateSource().hasData()) return true;
			if (getFixingTime()!=null && getFixingTime().hasData()) return true;
			if (getVaryingNotionalInterimExchangePaymentDates()!=null && getVaryingNotionalInterimExchangePaymentDates().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder o = (FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder) other;
			
			merger.mergeRosetta(getVaryingNotionalCurrency(), o.getVaryingNotionalCurrency(), this::setVaryingNotionalCurrency);
			merger.mergeRosetta(getVaryingNotionalFixingDates(), o.getVaryingNotionalFixingDates(), this::setVaryingNotionalFixingDates);
			merger.mergeRosetta(getFxSpotRateSource(), o.getFxSpotRateSource(), this::setFxSpotRateSource);
			merger.mergeRosetta(getFixingTime(), o.getFixingTime(), this::setFixingTime);
			merger.mergeRosetta(getVaryingNotionalInterimExchangePaymentDates(), o.getVaryingNotionalInterimExchangePaymentDates(), this::setVaryingNotionalInterimExchangePaymentDates);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxLinkedNotionalSchedule _that = getType().cast(o);
		
			if (!Objects.equals(varyingNotionalCurrency, _that.getVaryingNotionalCurrency())) return false;
			if (!Objects.equals(varyingNotionalFixingDates, _that.getVaryingNotionalFixingDates())) return false;
			if (!Objects.equals(fxSpotRateSource, _that.getFxSpotRateSource())) return false;
			if (!Objects.equals(fixingTime, _that.getFixingTime())) return false;
			if (!Objects.equals(varyingNotionalInterimExchangePaymentDates, _that.getVaryingNotionalInterimExchangePaymentDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (varyingNotionalCurrency != null ? varyingNotionalCurrency.hashCode() : 0);
			_result = 31 * _result + (varyingNotionalFixingDates != null ? varyingNotionalFixingDates.hashCode() : 0);
			_result = 31 * _result + (fxSpotRateSource != null ? fxSpotRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingTime != null ? fixingTime.hashCode() : 0);
			_result = 31 * _result + (varyingNotionalInterimExchangePaymentDates != null ? varyingNotionalInterimExchangePaymentDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxLinkedNotionalScheduleBuilder {" +
				"varyingNotionalCurrency=" + this.varyingNotionalCurrency + ", " +
				"varyingNotionalFixingDates=" + this.varyingNotionalFixingDates + ", " +
				"fxSpotRateSource=" + this.fxSpotRateSource + ", " +
				"fixingTime=" + this.fixingTime + ", " +
				"varyingNotionalInterimExchangePaymentDates=" + this.varyingNotionalInterimExchangePaymentDates +
			'}';
		}
	}
}
