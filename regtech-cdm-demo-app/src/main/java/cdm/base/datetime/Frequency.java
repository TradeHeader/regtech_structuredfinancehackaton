package cdm.base.datetime;

import cdm.base.datetime.Frequency;
import cdm.base.datetime.Frequency.FrequencyBuilder;
import cdm.base.datetime.Frequency.FrequencyBuilderImpl;
import cdm.base.datetime.Frequency.FrequencyImpl;
import cdm.base.datetime.PeriodExtendedEnum;
import cdm.base.datetime.meta.FrequencyMeta;
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
 * A class for defining a date frequency, e.g. one day, three months, through the combination of an integer value and a standardized period value that is specified as part of an enumeration.
 * @version ${project.version}
 */
@RosettaDataType(value="Frequency", builder=Frequency.FrequencyBuilderImpl.class, version="${project.version}")
public interface Frequency extends RosettaModelObject, GlobalKey {

	FrequencyMeta metaData = new FrequencyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A time period multiplier, e.g. 1, 2, or 3. If the period value is T (Term) then period multiplier must contain the value 1.
	 */
	Integer getPeriodMultiplier();
	/**
	 * A time period, e.g. a day, week, month, year or term of the stream.
	 */
	PeriodExtendedEnum getPeriod();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Frequency build();
	
	Frequency.FrequencyBuilder toBuilder();
	
	static Frequency.FrequencyBuilder builder() {
		return new Frequency.FrequencyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Frequency> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Frequency> getType() {
		return Frequency.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
		processor.processBasic(path.newSubPath("period"), PeriodExtendedEnum.class, getPeriod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FrequencyBuilder extends Frequency, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Frequency.FrequencyBuilder setPeriodMultiplier(Integer periodMultiplier);
		Frequency.FrequencyBuilder setPeriod(PeriodExtendedEnum period);
		Frequency.FrequencyBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
			processor.processBasic(path.newSubPath("period"), PeriodExtendedEnum.class, getPeriod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Frequency.FrequencyBuilder prune();
	}

	/*********************** Immutable Implementation of Frequency  ***********************/
	class FrequencyImpl implements Frequency {
		private final Integer periodMultiplier;
		private final PeriodExtendedEnum period;
		private final MetaFields meta;
		
		protected FrequencyImpl(Frequency.FrequencyBuilder builder) {
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
		public PeriodExtendedEnum getPeriod() {
			return period;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Frequency build() {
			return this;
		}
		
		@Override
		public Frequency.FrequencyBuilder toBuilder() {
			Frequency.FrequencyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Frequency.FrequencyBuilder builder) {
			ofNullable(getPeriodMultiplier()).ifPresent(builder::setPeriodMultiplier);
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Frequency _that = getType().cast(o);
		
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
			return "Frequency {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Frequency  ***********************/
	class FrequencyBuilderImpl implements Frequency.FrequencyBuilder, GlobalKeyBuilder {
	
		protected Integer periodMultiplier;
		protected PeriodExtendedEnum period;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FrequencyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("periodMultiplier")
		public Integer getPeriodMultiplier() {
			return periodMultiplier;
		}
		
		@Override
		@RosettaAttribute("period")
		public PeriodExtendedEnum getPeriod() {
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
		public Frequency.FrequencyBuilder setPeriodMultiplier(Integer periodMultiplier) {
			this.periodMultiplier = periodMultiplier==null?null:periodMultiplier;
			return this;
		}
		@Override
		@RosettaAttribute("period")
		public Frequency.FrequencyBuilder setPeriod(PeriodExtendedEnum period) {
			this.period = period==null?null:period;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Frequency.FrequencyBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Frequency build() {
			return new Frequency.FrequencyImpl(this);
		}
		
		@Override
		public Frequency.FrequencyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Frequency.FrequencyBuilder prune() {
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
		public Frequency.FrequencyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Frequency.FrequencyBuilder o = (Frequency.FrequencyBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getPeriodMultiplier(), o.getPeriodMultiplier(), this::setPeriodMultiplier);
			merger.mergeBasic(getPeriod(), o.getPeriod(), this::setPeriod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Frequency _that = getType().cast(o);
		
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
			return "FrequencyBuilder {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
