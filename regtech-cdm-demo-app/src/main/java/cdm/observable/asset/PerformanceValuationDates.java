package cdm.observable.asset;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.PerformanceValuationDates.PerformanceValuationDatesBuilder;
import cdm.observable.asset.PerformanceValuationDates.PerformanceValuationDatesBuilderImpl;
import cdm.observable.asset.PerformanceValuationDates.PerformanceValuationDatesImpl;
import cdm.observable.asset.meta.PerformanceValuationDatesMeta;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
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
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines how and when a performance type option or performance type swap is to be valued.
 * @version ${project.version}
 */
@RosettaDataType(value="PerformanceValuationDates", builder=PerformanceValuationDates.PerformanceValuationDatesBuilderImpl.class, version="${project.version}")
public interface PerformanceValuationDates extends RosettaModelObject, GlobalKey {

	PerformanceValuationDatesMeta metaData = new PerformanceValuationDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the method according to which an amount or a date is determined.
	 */
	DeterminationMethodEnum getDeterminationMethod();
	/**
	 * 2018 ISDA CDM Equity Confirmation for Security Equity Swap: Pricing Date
	 */
	AdjustableRelativeOrPeriodicDates getValuationDates();
	/**
	 * 2018 ISDA CDM Equity Confirmation for Security Equity Swap: Pricing Date
	 */
	AdjustableOrRelativeDate getValuationDate();
	/**
	 * The specific time of day at which the calculation agent values the underlying. The SpecificTime is the only case when the valuationTime (time + business center location  e.g. 10:00:00 USNY) should be provided. You should be able to provide just the valuationTime without valuationTimeType, which infer that this is a specific time.
	 */
	BusinessCenterTime getValuationTime();
	/**
	 * The time of day at which the calculation agent values the underlying, for example the official closing time of the exchange.
	 */
	TimeTypeEnum getValuationTimeType();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PerformanceValuationDates build();
	
	PerformanceValuationDates.PerformanceValuationDatesBuilder toBuilder();
	
	static PerformanceValuationDates.PerformanceValuationDatesBuilder builder() {
		return new PerformanceValuationDates.PerformanceValuationDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PerformanceValuationDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PerformanceValuationDates> getType() {
		return PerformanceValuationDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("determinationMethod"), DeterminationMethodEnum.class, getDeterminationMethod(), this);
		processRosetta(path.newSubPath("valuationDates"), processor, AdjustableRelativeOrPeriodicDates.class, getValuationDates());
		processRosetta(path.newSubPath("valuationDate"), processor, AdjustableOrRelativeDate.class, getValuationDate());
		processRosetta(path.newSubPath("valuationTime"), processor, BusinessCenterTime.class, getValuationTime());
		processor.processBasic(path.newSubPath("valuationTimeType"), TimeTypeEnum.class, getValuationTimeType(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PerformanceValuationDatesBuilder extends PerformanceValuationDates, RosettaModelObjectBuilder {
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateValuationDates();
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getValuationDates();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateValuationDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getValuationDate();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateValuationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getValuationTime();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PerformanceValuationDates.PerformanceValuationDatesBuilder setDeterminationMethod(DeterminationMethodEnum determinationMethod);
		PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationDates(AdjustableRelativeOrPeriodicDates valuationDates);
		PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationDate(AdjustableOrRelativeDate valuationDate);
		PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationTime(BusinessCenterTime valuationTime);
		PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationTimeType(TimeTypeEnum valuationTimeType);
		PerformanceValuationDates.PerformanceValuationDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("determinationMethod"), DeterminationMethodEnum.class, getDeterminationMethod(), this);
			processRosetta(path.newSubPath("valuationDates"), processor, AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder.class, getValuationDates());
			processRosetta(path.newSubPath("valuationDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getValuationDate());
			processRosetta(path.newSubPath("valuationTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getValuationTime());
			processor.processBasic(path.newSubPath("valuationTimeType"), TimeTypeEnum.class, getValuationTimeType(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PerformanceValuationDates.PerformanceValuationDatesBuilder prune();
	}

	/*********************** Immutable Implementation of PerformanceValuationDates  ***********************/
	class PerformanceValuationDatesImpl implements PerformanceValuationDates {
		private final DeterminationMethodEnum determinationMethod;
		private final AdjustableRelativeOrPeriodicDates valuationDates;
		private final AdjustableOrRelativeDate valuationDate;
		private final BusinessCenterTime valuationTime;
		private final TimeTypeEnum valuationTimeType;
		private final MetaFields meta;
		
		protected PerformanceValuationDatesImpl(PerformanceValuationDates.PerformanceValuationDatesBuilder builder) {
			this.determinationMethod = builder.getDeterminationMethod();
			this.valuationDates = ofNullable(builder.getValuationDates()).map(f->f.build()).orElse(null);
			this.valuationDate = ofNullable(builder.getValuationDate()).map(f->f.build()).orElse(null);
			this.valuationTime = ofNullable(builder.getValuationTime()).map(f->f.build()).orElse(null);
			this.valuationTimeType = builder.getValuationTimeType();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("determinationMethod")
		public DeterminationMethodEnum getDeterminationMethod() {
			return determinationMethod;
		}
		
		@Override
		@RosettaAttribute("valuationDates")
		public AdjustableRelativeOrPeriodicDates getValuationDates() {
			return valuationDates;
		}
		
		@Override
		@RosettaAttribute("valuationDate")
		public AdjustableOrRelativeDate getValuationDate() {
			return valuationDate;
		}
		
		@Override
		@RosettaAttribute("valuationTime")
		public BusinessCenterTime getValuationTime() {
			return valuationTime;
		}
		
		@Override
		@RosettaAttribute("valuationTimeType")
		public TimeTypeEnum getValuationTimeType() {
			return valuationTimeType;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PerformanceValuationDates build() {
			return this;
		}
		
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder toBuilder() {
			PerformanceValuationDates.PerformanceValuationDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PerformanceValuationDates.PerformanceValuationDatesBuilder builder) {
			ofNullable(getDeterminationMethod()).ifPresent(builder::setDeterminationMethod);
			ofNullable(getValuationDates()).ifPresent(builder::setValuationDates);
			ofNullable(getValuationDate()).ifPresent(builder::setValuationDate);
			ofNullable(getValuationTime()).ifPresent(builder::setValuationTime);
			ofNullable(getValuationTimeType()).ifPresent(builder::setValuationTimeType);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PerformanceValuationDates _that = getType().cast(o);
		
			if (!Objects.equals(determinationMethod, _that.getDeterminationMethod())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(valuationTime, _that.getValuationTime())) return false;
			if (!Objects.equals(valuationTimeType, _that.getValuationTimeType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (determinationMethod != null ? determinationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (valuationTime != null ? valuationTime.hashCode() : 0);
			_result = 31 * _result + (valuationTimeType != null ? valuationTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PerformanceValuationDates {" +
				"determinationMethod=" + this.determinationMethod + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"valuationTime=" + this.valuationTime + ", " +
				"valuationTimeType=" + this.valuationTimeType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PerformanceValuationDates  ***********************/
	class PerformanceValuationDatesBuilderImpl implements PerformanceValuationDates.PerformanceValuationDatesBuilder, GlobalKeyBuilder {
	
		protected DeterminationMethodEnum determinationMethod;
		protected AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder valuationDates;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder valuationDate;
		protected BusinessCenterTime.BusinessCenterTimeBuilder valuationTime;
		protected TimeTypeEnum valuationTimeType;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PerformanceValuationDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("determinationMethod")
		public DeterminationMethodEnum getDeterminationMethod() {
			return determinationMethod;
		}
		
		@Override
		@RosettaAttribute("valuationDates")
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getValuationDates() {
			return valuationDates;
		}
		
		@Override
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateValuationDates() {
			AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder result;
			if (valuationDates!=null) {
				result = valuationDates;
			}
			else {
				result = valuationDates = AdjustableRelativeOrPeriodicDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getValuationDate() {
			return valuationDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateValuationDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (valuationDate!=null) {
				result = valuationDate;
			}
			else {
				result = valuationDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getValuationTime() {
			return valuationTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateValuationTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (valuationTime!=null) {
				result = valuationTime;
			}
			else {
				result = valuationTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationTimeType")
		public TimeTypeEnum getValuationTimeType() {
			return valuationTimeType;
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
		@RosettaAttribute("determinationMethod")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setDeterminationMethod(DeterminationMethodEnum determinationMethod) {
			this.determinationMethod = determinationMethod==null?null:determinationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("valuationDates")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationDates(AdjustableRelativeOrPeriodicDates valuationDates) {
			this.valuationDates = valuationDates==null?null:valuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationDate(AdjustableOrRelativeDate valuationDate) {
			this.valuationDate = valuationDate==null?null:valuationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationTime")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationTime(BusinessCenterTime valuationTime) {
			this.valuationTime = valuationTime==null?null:valuationTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationTimeType")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setValuationTimeType(TimeTypeEnum valuationTimeType) {
			this.valuationTimeType = valuationTimeType==null?null:valuationTimeType;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PerformanceValuationDates build() {
			return new PerformanceValuationDates.PerformanceValuationDatesImpl(this);
		}
		
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder prune() {
			if (valuationDates!=null && !valuationDates.prune().hasData()) valuationDates = null;
			if (valuationDate!=null && !valuationDate.prune().hasData()) valuationDate = null;
			if (valuationTime!=null && !valuationTime.prune().hasData()) valuationTime = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDeterminationMethod()!=null) return true;
			if (getValuationDates()!=null && getValuationDates().hasData()) return true;
			if (getValuationDate()!=null && getValuationDate().hasData()) return true;
			if (getValuationTime()!=null && getValuationTime().hasData()) return true;
			if (getValuationTimeType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PerformanceValuationDates.PerformanceValuationDatesBuilder o = (PerformanceValuationDates.PerformanceValuationDatesBuilder) other;
			
			merger.mergeRosetta(getValuationDates(), o.getValuationDates(), this::setValuationDates);
			merger.mergeRosetta(getValuationDate(), o.getValuationDate(), this::setValuationDate);
			merger.mergeRosetta(getValuationTime(), o.getValuationTime(), this::setValuationTime);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getDeterminationMethod(), o.getDeterminationMethod(), this::setDeterminationMethod);
			merger.mergeBasic(getValuationTimeType(), o.getValuationTimeType(), this::setValuationTimeType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PerformanceValuationDates _that = getType().cast(o);
		
			if (!Objects.equals(determinationMethod, _that.getDeterminationMethod())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(valuationTime, _that.getValuationTime())) return false;
			if (!Objects.equals(valuationTimeType, _that.getValuationTimeType())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (determinationMethod != null ? determinationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (valuationTime != null ? valuationTime.hashCode() : 0);
			_result = 31 * _result + (valuationTimeType != null ? valuationTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PerformanceValuationDatesBuilder {" +
				"determinationMethod=" + this.determinationMethod + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"valuationTime=" + this.valuationTime + ", " +
				"valuationTimeType=" + this.valuationTimeType + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
