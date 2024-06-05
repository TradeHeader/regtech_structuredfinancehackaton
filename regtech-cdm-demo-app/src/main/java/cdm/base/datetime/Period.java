package cdm.base.datetime;

import cdm.base.datetime.Period;
import cdm.base.datetime.Period.PeriodBuilder;
import cdm.base.datetime.Period.PeriodBuilderImpl;
import cdm.base.datetime.Period.PeriodImpl;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.meta.PeriodMeta;
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
 * A class to define recurring periods or time offsets.
 * @version ${project.version}
 */
@RosettaDataType(value="Period", builder=Period.PeriodBuilderImpl.class, version="${project.version}")
public interface Period extends RosettaModelObject, GlobalKey {

	PeriodMeta metaData = new PeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A time period multiplier, e.g. 1, 2 or 3 etc. A negative value can be used when specifying an offset relative to another date, e.g. -2 days.
	 */
	Integer getPeriodMultiplier();
	/**
	 * A time period, e.g. a day, week, month or year of the stream. If the periodMultiplier value is 0 (zero) then period must contain the value D (day).
	 */
	PeriodEnum getPeriod();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Period build();
	
	Period.PeriodBuilder toBuilder();
	
	static Period.PeriodBuilder builder() {
		return new Period.PeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Period> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Period> getType() {
		return Period.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
		processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PeriodBuilder extends Period, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Period.PeriodBuilder setPeriodMultiplier(Integer periodMultiplier);
		Period.PeriodBuilder setPeriod(PeriodEnum period);
		Period.PeriodBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
			processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Period.PeriodBuilder prune();
	}

	/*********************** Immutable Implementation of Period  ***********************/
	class PeriodImpl implements Period {
		private final Integer periodMultiplier;
		private final PeriodEnum period;
		private final MetaFields meta;
		
		protected PeriodImpl(Period.PeriodBuilder builder) {
			this.periodMultiplier = builder.getPeriodMultiplier();
			this.period = builder.getPeriod();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("periodMultiplier")
		public Integer getPeriodMultiplier() {
			return periodMultiplier;
		}
		
		@Override
		@RosettaAttribute("period")
		public PeriodEnum getPeriod() {
			return period;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Period build() {
			return this;
		}
		
		@Override
		public Period.PeriodBuilder toBuilder() {
			Period.PeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Period.PeriodBuilder builder) {
			ofNullable(getPeriodMultiplier()).ifPresent(builder::setPeriodMultiplier);
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Period _that = getType().cast(o);
		
			if (!Objects.equals(periodMultiplier, _that.getPeriodMultiplier())) return false;
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periodMultiplier != null ? periodMultiplier.hashCode() : 0);
			_result = 31 * _result + (period != null ? period.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Period {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Period  ***********************/
	class PeriodBuilderImpl implements Period.PeriodBuilder, GlobalKeyBuilder {
	
		protected Integer periodMultiplier;
		protected PeriodEnum period;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("periodMultiplier")
		public Integer getPeriodMultiplier() {
			return periodMultiplier;
		}
		
		@Override
		@RosettaAttribute("period")
		public PeriodEnum getPeriod() {
			return period;
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
		@RosettaAttribute("periodMultiplier")
		public Period.PeriodBuilder setPeriodMultiplier(Integer periodMultiplier) {
			this.periodMultiplier = periodMultiplier==null?null:periodMultiplier;
			return this;
		}
		@Override
		@RosettaAttribute("period")
		public Period.PeriodBuilder setPeriod(PeriodEnum period) {
			this.period = period==null?null:period;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Period.PeriodBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Period build() {
			return new Period.PeriodImpl(this);
		}
		
		@Override
		public Period.PeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Period.PeriodBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriodMultiplier()!=null) return true;
			if (getPeriod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Period.PeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Period.PeriodBuilder o = (Period.PeriodBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getPeriodMultiplier(), o.getPeriodMultiplier(), this::setPeriodMultiplier);
			merger.mergeBasic(getPeriod(), o.getPeriod(), this::setPeriod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Period _that = getType().cast(o);
		
			if (!Objects.equals(periodMultiplier, _that.getPeriodMultiplier())) return false;
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periodMultiplier != null ? periodMultiplier.hashCode() : 0);
			_result = 31 * _result + (period != null ? period.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PeriodBuilder {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
